package com.afollestad.materialdialogs.folderselector;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.commons.C0817R;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FolderChooserDialog extends DialogFragment implements MaterialDialog.ListCallback {
    private static final String DEFAULT_TAG = "[MD_FOLDER_SELECTOR]";
    /* access modifiers changed from: private */
    public FolderCallback callback;
    private boolean canGoUp = false;
    private File[] parentContents;
    /* access modifiers changed from: private */
    public File parentFolder;

    public interface FolderCallback {
        void onFolderChooserDismissed(FolderChooserDialog folderChooserDialog);

        void onFolderSelection(FolderChooserDialog folderChooserDialog, File file);
    }

    /* access modifiers changed from: package-private */
    public String[] getContentsArray() {
        File[] fileArr = this.parentContents;
        int i = 0;
        if (fileArr != null) {
            int length = fileArr.length;
            boolean z = this.canGoUp;
            String[] strArr = new String[(length + (z ? 1 : 0))];
            if (z) {
                strArr[0] = getBuilder().goUpLabel;
            }
            while (true) {
                File[] fileArr2 = this.parentContents;
                if (i >= fileArr2.length) {
                    return strArr;
                }
                strArr[this.canGoUp ? i + 1 : i] = fileArr2[i].getName();
                i++;
            }
        } else if (!this.canGoUp) {
            return new String[0];
        } else {
            return new String[]{getBuilder().goUpLabel};
        }
    }

    /* access modifiers changed from: package-private */
    public File[] listFiles() {
        File[] listFiles = this.parentFolder.listFiles();
        ArrayList arrayList = new ArrayList();
        if (listFiles == null) {
            return null;
        }
        for (File file : listFiles) {
            if (file.isDirectory()) {
                arrayList.add(file);
            }
        }
        Collections.sort(arrayList, new FolderSorter());
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 23 && ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            return new MaterialDialog.Builder(getActivity()).title(C0817R.string.md_error_label).content(C0817R.string.md_storage_perm_error).positiveText(17039370).build();
        }
        if (getArguments() == null || !getArguments().containsKey("builder")) {
            throw new IllegalStateException("You must create a FolderChooserDialog using the Builder.");
        }
        if (!getArguments().containsKey("current_path")) {
            getArguments().putString("current_path", getBuilder().initialPath);
        }
        this.parentFolder = new File(getArguments().getString("current_path"));
        checkIfCanGoUp();
        this.parentContents = listFiles();
        MaterialDialog.Builder negativeText = new MaterialDialog.Builder(getActivity()).typeface(getBuilder().mediumFont, getBuilder().regularFont).title((CharSequence) this.parentFolder.getAbsolutePath()).items((CharSequence[]) getContentsArray()).itemsCallback(this).onPositive(new MaterialDialog.SingleButtonCallback() {
            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                materialDialog.dismiss();
                FolderCallback access$200 = FolderChooserDialog.this.callback;
                FolderChooserDialog folderChooserDialog = FolderChooserDialog.this;
                access$200.onFolderSelection(folderChooserDialog, folderChooserDialog.parentFolder);
            }
        }).onNegative(new MaterialDialog.SingleButtonCallback() {
            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                materialDialog.dismiss();
            }
        }).autoDismiss(false).positiveText(getBuilder().chooseButton).negativeText(getBuilder().cancelButton);
        if (getBuilder().allowNewFolder) {
            negativeText.neutralText(getBuilder().newFolderButton);
            negativeText.onNeutral(new MaterialDialog.SingleButtonCallback() {
                public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                    FolderChooserDialog.this.createNewFolder();
                }
            });
        }
        if ("/".equals(getBuilder().initialPath)) {
            this.canGoUp = false;
        }
        return negativeText.build();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        FolderCallback folderCallback = this.callback;
        if (folderCallback != null) {
            folderCallback.onFolderChooserDismissed(this);
        }
    }

    /* access modifiers changed from: private */
    public void createNewFolder() {
        new MaterialDialog.Builder(getActivity()).title(getBuilder().newFolderButton).input(0, 0, false, (MaterialDialog.InputCallback) new MaterialDialog.InputCallback() {
            public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {
                File file = new File(FolderChooserDialog.this.parentFolder, charSequence.toString());
                if (!file.mkdir()) {
                    Toast.makeText(FolderChooserDialog.this.getActivity(), "Unable to create folder " + file.getAbsolutePath() + ", make sure you have the WRITE_EXTERNAL_STORAGE permission or root permissions.", 1).show();
                    return;
                }
                FolderChooserDialog.this.reload();
            }
        }).show();
    }

    public void onSelection(MaterialDialog materialDialog, View view, int i, CharSequence charSequence) {
        boolean z = this.canGoUp;
        boolean z2 = true;
        if (!z || i != 0) {
            File[] fileArr = this.parentContents;
            if (z) {
                i--;
            }
            File file = fileArr[i];
            this.parentFolder = file;
            this.canGoUp = true;
            if (file.getAbsolutePath().equals("/storage/emulated")) {
                this.parentFolder = Environment.getExternalStorageDirectory();
            }
        } else {
            File parentFile = this.parentFolder.getParentFile();
            this.parentFolder = parentFile;
            if (parentFile.getAbsolutePath().equals("/storage/emulated")) {
                this.parentFolder = this.parentFolder.getParentFile();
            }
            if (this.parentFolder.getParent() == null) {
                z2 = false;
            }
            this.canGoUp = z2;
        }
        reload();
    }

    private void checkIfCanGoUp() {
        try {
            boolean z = true;
            if (this.parentFolder.getPath().split("/").length <= 1) {
                z = false;
            }
            this.canGoUp = z;
        } catch (IndexOutOfBoundsException unused) {
            this.canGoUp = false;
        }
    }

    /* access modifiers changed from: private */
    public void reload() {
        this.parentContents = listFiles();
        MaterialDialog materialDialog = (MaterialDialog) getDialog();
        materialDialog.setTitle((CharSequence) this.parentFolder.getAbsolutePath());
        getArguments().putString("current_path", this.parentFolder.getAbsolutePath());
        materialDialog.setItems((CharSequence[]) getContentsArray());
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.callback = (FolderCallback) activity;
    }

    public void show(FragmentActivity fragmentActivity) {
        String str = getBuilder().tag;
        Fragment findFragmentByTag = fragmentActivity.getSupportFragmentManager().findFragmentByTag(str);
        if (findFragmentByTag != null) {
            ((DialogFragment) findFragmentByTag).dismiss();
            fragmentActivity.getSupportFragmentManager().beginTransaction().remove(findFragmentByTag).commit();
        }
        show(fragmentActivity.getSupportFragmentManager(), str);
    }

    private Builder getBuilder() {
        return (Builder) getArguments().getSerializable("builder");
    }

    public static class Builder implements Serializable {
        boolean allowNewFolder;
        int cancelButton = 17039360;
        int chooseButton = C0817R.string.md_choose_label;
        final transient AppCompatActivity context;
        String goUpLabel = "...";
        String initialPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String mediumFont;
        int newFolderButton;
        String regularFont;
        String tag;

        public <ActivityType extends AppCompatActivity & FolderCallback> Builder(ActivityType activitytype) {
            this.context = activitytype;
        }

        public Builder typeface(String str, String str2) {
            this.mediumFont = str;
            this.regularFont = str2;
            return this;
        }

        public Builder chooseButton(int i) {
            this.chooseButton = i;
            return this;
        }

        public Builder cancelButton(int i) {
            this.cancelButton = i;
            return this;
        }

        public Builder goUpLabel(String str) {
            this.goUpLabel = str;
            return this;
        }

        public Builder allowNewFolder(boolean z, int i) {
            this.allowNewFolder = z;
            if (i == 0) {
                i = C0817R.string.new_folder;
            }
            this.newFolderButton = i;
            return this;
        }

        public Builder initialPath(String str) {
            if (str == null) {
                str = File.separator;
            }
            this.initialPath = str;
            return this;
        }

        public Builder tag(String str) {
            if (str == null) {
                str = FolderChooserDialog.DEFAULT_TAG;
            }
            this.tag = str;
            return this;
        }

        public FolderChooserDialog build() {
            FolderChooserDialog folderChooserDialog = new FolderChooserDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("builder", this);
            folderChooserDialog.setArguments(bundle);
            return folderChooserDialog;
        }

        public FolderChooserDialog show() {
            FolderChooserDialog build = build();
            build.show(this.context);
            return build;
        }
    }

    private static class FolderSorter implements Comparator<File> {
        private FolderSorter() {
        }

        public int compare(File file, File file2) {
            return file.getName().compareTo(file2.getName());
        }
    }
}
