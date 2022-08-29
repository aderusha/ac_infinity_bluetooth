package com.afollestad.materialdialogs.prefs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatEditText;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.commons.C0817R;
import com.afollestad.materialdialogs.internal.MDTintHelper;
import com.afollestad.materialdialogs.util.DialogUtils;

public class MaterialEditTextPreference extends EditTextPreference {
    private int color = 0;
    private MaterialDialog dialog;
    private EditText editText;

    public MaterialEditTextPreference(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public MaterialEditTextPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public MaterialEditTextPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public MaterialEditTextPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        PrefUtil.setLayoutResource(context, this, attributeSet);
        this.color = DialogUtils.resolveColor(context, C0817R.attr.md_widget_color, DialogUtils.resolveColor(context, C0817R.attr.colorAccent, Build.VERSION.SDK_INT >= 21 ? DialogUtils.resolveColor(context, 16843829) : 0));
        AppCompatEditText appCompatEditText = new AppCompatEditText(context, attributeSet);
        this.editText = appCompatEditText;
        appCompatEditText.setId(16908291);
        this.editText.setEnabled(true);
    }

    /* access modifiers changed from: protected */
    public void onAddEditTextToDialogView(View view, EditText editText2) {
        ((ViewGroup) view).addView(editText2, new LinearLayout.LayoutParams(-1, -2));
    }

    /* access modifiers changed from: protected */
    public void onBindDialogView(View view) {
        EditText editText2 = this.editText;
        editText2.setText(getText());
        if (editText2.getText().length() > 0) {
            editText2.setSelection(editText2.length());
        }
        ViewParent parent = editText2.getParent();
        if (parent != view) {
            if (parent != null) {
                ((ViewGroup) parent).removeView(editText2);
            }
            onAddEditTextToDialogView(view, editText2);
        }
    }

    /* access modifiers changed from: protected */
    public void onDialogClosed(boolean z) {
        if (z) {
            String obj = this.editText.getText().toString();
            if (callChangeListener(obj)) {
                setText(obj);
            }
        }
    }

    public EditText getEditText() {
        return this.editText;
    }

    public Dialog getDialog() {
        return this.dialog;
    }

    /* access modifiers changed from: protected */
    public void showDialog(Bundle bundle) {
        MaterialDialog.Builder dismissListener = new MaterialDialog.Builder(getContext()).title(getDialogTitle()).icon(getDialogIcon()).positiveText(getPositiveButtonText()).negativeText(getNegativeButtonText()).dismissListener(this).onAny(new MaterialDialog.SingleButtonCallback() {
            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                int i = C08342.$SwitchMap$com$afollestad$materialdialogs$DialogAction[dialogAction.ordinal()];
                if (i == 1) {
                    MaterialEditTextPreference.this.onClick(materialDialog, -3);
                } else if (i != 2) {
                    MaterialEditTextPreference.this.onClick(materialDialog, -1);
                } else {
                    MaterialEditTextPreference.this.onClick(materialDialog, -2);
                }
            }
        }).dismissListener(this);
        View inflate = LayoutInflater.from(getContext()).inflate(C0817R.layout.md_stub_inputpref, (ViewGroup) null);
        onBindDialogView(inflate);
        MDTintHelper.setTint(this.editText, this.color);
        TextView textView = (TextView) inflate.findViewById(16908299);
        if (getDialogMessage() == null || getDialogMessage().toString().length() <= 0) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setText(getDialogMessage());
        }
        dismissListener.customView(inflate, false);
        PrefUtil.registerOnActivityDestroyListener(this, this);
        MaterialDialog build = dismissListener.build();
        this.dialog = build;
        if (bundle != null) {
            build.onRestoreInstanceState(bundle);
        }
        requestInputMethod(this.dialog);
        this.dialog.show();
    }

    /* renamed from: com.afollestad.materialdialogs.prefs.MaterialEditTextPreference$2 */
    static /* synthetic */ class C08342 {
        static final /* synthetic */ int[] $SwitchMap$com$afollestad$materialdialogs$DialogAction;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.afollestad.materialdialogs.DialogAction[] r0 = com.afollestad.materialdialogs.DialogAction.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$afollestad$materialdialogs$DialogAction = r0
                com.afollestad.materialdialogs.DialogAction r1 = com.afollestad.materialdialogs.DialogAction.NEUTRAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$afollestad$materialdialogs$DialogAction     // Catch:{ NoSuchFieldError -> 0x001d }
                com.afollestad.materialdialogs.DialogAction r1 = com.afollestad.materialdialogs.DialogAction.NEGATIVE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.prefs.MaterialEditTextPreference.C08342.<clinit>():void");
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        PrefUtil.unregisterOnActivityDestroyListener(this, this);
    }

    private void requestInputMethod(Dialog dialog2) {
        Window window = dialog2.getWindow();
        if (window != null) {
            window.setSoftInputMode(5);
        }
    }

    public void onActivityDestroy() {
        super.onActivityDestroy();
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.dialog.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Dialog dialog2 = getDialog();
        if (dialog2 == null || !dialog2.isShowing()) {
            return onSaveInstanceState;
        }
        SavedState savedState = new SavedState(onSaveInstanceState);
        savedState.isDialogShowing = true;
        savedState.dialogBundle = dialog2.onSaveInstanceState();
        return savedState;
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable == null || !parcelable.getClass().equals(SavedState.class)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.isDialogShowing) {
            showDialog(savedState.dialogBundle);
        }
    }

    private static class SavedState extends Preference.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        Bundle dialogBundle;
        boolean isDialogShowing;

        SavedState(Parcel parcel) {
            super(parcel);
            this.isDialogShowing = parcel.readInt() != 1 ? false : true;
            this.dialogBundle = parcel.readBundle();
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.isDialogShowing ? 1 : 0);
            parcel.writeBundle(this.dialogBundle);
        }
    }
}
