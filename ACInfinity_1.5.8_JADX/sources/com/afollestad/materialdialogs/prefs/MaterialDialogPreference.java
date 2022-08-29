package com.afollestad.materialdialogs.prefs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.DialogPreference;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class MaterialDialogPreference extends DialogPreference {
    private Context context;
    private MaterialDialog dialog;

    public MaterialDialogPreference(Context context2) {
        super(context2);
        init(context2, (AttributeSet) null);
    }

    public MaterialDialogPreference(Context context2, AttributeSet attributeSet) {
        super(context2, attributeSet);
        init(context2, attributeSet);
    }

    public MaterialDialogPreference(Context context2, AttributeSet attributeSet, int i) {
        super(context2, attributeSet, i);
        init(context2, attributeSet);
    }

    public MaterialDialogPreference(Context context2, AttributeSet attributeSet, int i, int i2) {
        super(context2, attributeSet, i, i2);
        init(context2, attributeSet);
    }

    private void init(Context context2, AttributeSet attributeSet) {
        this.context = context2;
        PrefUtil.setLayoutResource(context2, this, attributeSet);
    }

    public Dialog getDialog() {
        return this.dialog;
    }

    /* access modifiers changed from: protected */
    public void showDialog(Bundle bundle) {
        MaterialDialog.Builder autoDismiss = new MaterialDialog.Builder(this.context).title(getDialogTitle()).icon(getDialogIcon()).dismissListener(this).onAny(new MaterialDialog.SingleButtonCallback() {
            public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                int i = C08312.$SwitchMap$com$afollestad$materialdialogs$DialogAction[dialogAction.ordinal()];
                if (i == 1) {
                    MaterialDialogPreference.this.onClick(materialDialog, -3);
                } else if (i != 2) {
                    MaterialDialogPreference.this.onClick(materialDialog, -1);
                } else {
                    MaterialDialogPreference.this.onClick(materialDialog, -2);
                }
            }
        }).positiveText(getPositiveButtonText()).negativeText(getNegativeButtonText()).autoDismiss(true);
        View onCreateDialogView = onCreateDialogView();
        if (onCreateDialogView != null) {
            onBindDialogView(onCreateDialogView);
            autoDismiss.customView(onCreateDialogView, false);
        } else {
            autoDismiss.content(getDialogMessage());
        }
        PrefUtil.registerOnActivityDestroyListener(this, this);
        MaterialDialog build = autoDismiss.build();
        this.dialog = build;
        if (bundle != null) {
            build.onRestoreInstanceState(bundle);
        }
        this.dialog.show();
    }

    /* renamed from: com.afollestad.materialdialogs.prefs.MaterialDialogPreference$2 */
    static /* synthetic */ class C08312 {
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
            throw new UnsupportedOperationException("Method not decompiled: com.afollestad.materialdialogs.prefs.MaterialDialogPreference.C08312.<clinit>():void");
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        PrefUtil.unregisterOnActivityDestroyListener(this, this);
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
