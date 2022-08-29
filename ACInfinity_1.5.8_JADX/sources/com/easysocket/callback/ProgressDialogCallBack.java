package com.easysocket.callback;

import android.app.Dialog;
import android.content.DialogInterface;
import com.easysocket.entity.OriginReadData;
import com.easysocket.exception.RequestCancelException;
import com.easysocket.interfaces.callback.IProgressDialog;
import com.easysocket.interfaces.callback.ProgressCancelListener;

public abstract class ProgressDialogCallBack extends SuperCallBack implements ProgressCancelListener {
    private boolean isShowProgress = true;
    private Dialog mDialog;
    private IProgressDialog progressDialog;

    public abstract void onResponse(OriginReadData originReadData);

    public ProgressDialogCallBack(IProgressDialog iProgressDialog, String str) {
        super(str);
        this.progressDialog = iProgressDialog;
        init(false);
        onStart();
    }

    public ProgressDialogCallBack(IProgressDialog iProgressDialog, boolean z, boolean z2, String str) {
        super(str);
        this.progressDialog = iProgressDialog;
        this.isShowProgress = z;
        init(z2);
        onStart();
    }

    private void init(boolean z) {
        IProgressDialog iProgressDialog = this.progressDialog;
        if (iProgressDialog != null) {
            Dialog dialog = iProgressDialog.getDialog();
            this.mDialog = dialog;
            if (dialog != null) {
                dialog.setCancelable(z);
                if (z) {
                    this.mDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        public void onCancel(DialogInterface dialogInterface) {
                            ProgressDialogCallBack.this.onCancelProgress();
                        }
                    });
                }
            }
        }
    }

    private void showProgress() {
        Dialog dialog;
        if (this.isShowProgress && (dialog = this.mDialog) != null && !dialog.isShowing()) {
            this.mDialog.show();
        }
    }

    private void dismissProgress() {
        Dialog dialog;
        if (this.isShowProgress && (dialog = this.mDialog) != null && dialog.isShowing()) {
            this.mDialog.dismiss();
        }
    }

    public void onStart() {
        showProgress();
    }

    public void onCompleted() {
        dismissProgress();
    }

    public void onError(Exception exc) {
        onCompleted();
    }

    public void onCancelProgress() {
        onCompleted();
        onError(new RequestCancelException("网络请求被取消"));
    }
}
