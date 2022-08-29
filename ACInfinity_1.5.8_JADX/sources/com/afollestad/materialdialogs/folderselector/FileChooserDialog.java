package com.afollestad.materialdialogs.folderselector;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.MimeTypeMap;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.legacy.app.ActivityCompat;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.commons.C0817R;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FileChooserDialog extends DialogFragment implements MaterialDialog.ListCallback {
    private static final String DEFAULT_TAG = "[MD_FILE_SELECTOR]";
    private FileCallback callback;
    private boolean canGoUp = true;
    private File[] parentContents;
    private File parentFolder;

    public interface FileCallback {
        void onFileChooserDismissed(FileChooserDialog fileChooserDialog);

        void onFileSelection(FileChooserDialog fileChooserDialog, File file);
    }

    /* access modifiers changed from: package-private */
    public CharSequence[] getContentsArray() {
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
    public File[] listFiles(String str, String[] strArr) {
        boolean z;
        File[] listFiles = this.parentFolder.listFiles();
        ArrayList arrayList = new ArrayList();
        if (listFiles == null) {
            return null;
        }
        MimeTypeMap singleton = MimeTypeMap.getSingleton();
        for (File file : listFiles) {
            if (file.isDirectory()) {
                arrayList.add(file);
            } else if (strArr != null) {
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        z = false;
                        break;
                    }
                    if (file.getName().toLowerCase().endsWith(strArr[i].toLowerCase())) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (z) {
                    arrayList.add(file);
                }
            } else if (str != null && fileIsMimeType(file, str, singleton)) {
                arrayList.add(file);
            }
        }
        Collections.sort(arrayList, new FileSorter());
        return (File[]) arrayList.toArray(new File[arrayList.size()]);
    }

    /* access modifiers changed from: package-private */
    public boolean fileIsMimeType(File file, String str, MimeTypeMap mimeTypeMap) {
        int lastIndexOf;
        if (str == null || str.equals("*/*")) {
            return true;
        }
        String uri = file.toURI().toString();
        int lastIndexOf2 = uri.lastIndexOf(46);
        if (lastIndexOf2 == -1) {
            return false;
        }
        String substring = uri.substring(lastIndexOf2 + 1);
        if (substring.endsWith("json")) {
            return str.startsWith("application/json");
        }
        String mimeTypeFromExtension = mimeTypeMap.getMimeTypeFromExtension(substring);
        if (mimeTypeFromExtension == null) {
            return false;
        }
        if (mimeTypeFromExtension.equals(str)) {
            return true;
        }
        int lastIndexOf3 = str.lastIndexOf(47);
        if (lastIndexOf3 == -1) {
            return false;
        }
        String substring2 = str.substring(0, lastIndexOf3);
        if (str.substring(lastIndexOf3 + 1).equals("*") && (lastIndexOf = mimeTypeFromExtension.lastIndexOf(47)) != -1 && mimeTypeFromExtension.substring(0, lastIndexOf).equals(substring2)) {
            return true;
        }
        return false;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 23 && ActivityCompat.checkSelfPermission(getActivity(), "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            return new MaterialDialog.Builder(getActivity()).title(C0817R.string.md_error_label).content(C0817R.string.md_storage_perm_error).positiveText(17039370).build();
        }
        if (getArguments() == null || !getArguments().containsKey("builder")) {
            throw new IllegalStateException("You must create a FileChooserDialog using the Builder.");
        }
        if (!getArguments().containsKey("current_path")) {
            getArguments().putString("current_path", getBuilder().initialPath);
        }
        this.parentFolder = new File(getArguments().getString("current_path"));
        checkIfCanGoUp();
        this.parentContents = listFiles(getBuilder().mimeType, getBuilder().extensions);
        return new MaterialDialog.Builder(getActivity()).title((CharSequence) this.parentFolder.getAbsolutePath()).typeface(getBuilder().mediumFont, getBuilder().regularFont).items(getContentsArray()).itemsCallback(this).onNegative(new MaterialDialog.SingleButtonCallback() {
            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                materialDialog.dismiss();
            }
        }).autoDismiss(false).negativeText(getBuilder().cancelButton).build();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        FileCallback fileCallback = this.callback;
        if (fileCallback != null) {
            fileCallback.onFileChooserDismissed(this);
        }
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
        if (this.parentFolder.isFile()) {
            this.callback.onFileSelection(this, this.parentFolder);
            dismiss();
            return;
        }
        this.parentContents = listFiles(getBuilder().mimeType, getBuilder().extensions);
        MaterialDialog materialDialog2 = (MaterialDialog) getDialog();
        materialDialog2.setTitle((CharSequence) this.parentFolder.getAbsolutePath());
        getArguments().putString("current_path", this.parentFolder.getAbsolutePath());
        materialDialog2.setItems(getContentsArray());
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

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.callback = (FileCallback) activity;
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

    public String getInitialPath() {
        return getBuilder().initialPath;
    }

    private Builder getBuilder() {
        return (Builder) getArguments().getSerializable("builder");
    }

    public static class Builder implements Serializable {
        int cancelButton = 17039360;
        final transient AppCompatActivity context;
        String[] extensions;
        String goUpLabel = "...";
        String initialPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        String mediumFont;
        String mimeType = null;
        String regularFont;
        String tag;

        public <ActivityType extends AppCompatActivity & FileCallback> Builder(ActivityType activitytype) {
            this.context = activitytype;
        }

        public Builder typeface(String str, String str2) {
            this.mediumFont = str;
            this.regularFont = str2;
            return this;
        }

        public Builder cancelButton(int i) {
            this.cancelButton = i;
            return this;
        }

        public Builder initialPath(String str) {
            if (str == null) {
                str = File.separator;
            }
            this.initialPath = str;
            return this;
        }

        public Builder mimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder extensionsFilter(String... strArr) {
            this.extensions = strArr;
            return this;
        }

        public Builder tag(String str) {
            if (str == null) {
                str = FileChooserDialog.DEFAULT_TAG;
            }
            this.tag = str;
            return this;
        }

        public Builder goUpLabel(String str) {
            this.goUpLabel = str;
            return this;
        }

        public FileChooserDialog build() {
            FileChooserDialog fileChooserDialog = new FileChooserDialog();
            Bundle bundle = new Bundle();
            bundle.putSerializable("builder", this);
            fileChooserDialog.setArguments(bundle);
            return fileChooserDialog;
        }

        public FileChooserDialog show() {
            FileChooserDialog build = build();
            build.show(this.context);
            return build;
        }
    }

    private static class FileSorter implements Comparator<File> {
        private FileSorter() {
        }

        public int compare(File file, File file2) {
            if (file.isDirectory() && !file2.isDirectory()) {
                return -1;
            }
            if (file.isDirectory() || !file2.isDirectory()) {
                return file.getName().compareTo(file2.getName());
            }
            return 1;
        }
    }
}
