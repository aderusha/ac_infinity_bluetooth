package com.eternal.framework.component;

import com.afollestad.materialdialogs.MaterialDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public class BaseRxActivity extends RxAppCompatActivity {
    private MaterialDialog notifyDialog;

    public void showNotifyDialog(MaterialDialog materialDialog) {
        this.notifyDialog = materialDialog;
        materialDialog.show();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        MaterialDialog materialDialog = this.notifyDialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.notifyDialog.dismiss();
        }
        super.onDestroy();
    }
}
