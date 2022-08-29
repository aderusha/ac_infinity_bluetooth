package com.eternal.widget.loadingdialog;

import com.eternal.widget.loadingdialog.LoadingDialog;
import p014io.reactivex.disposables.Disposable;

public class DialogDismissListener implements LoadingDialog.DismissListener {
    private Disposable disposable;

    public DialogDismissListener(Disposable disposable2) {
        this.disposable = disposable2;
    }

    public void setDisposable(Disposable disposable2) {
        this.disposable = disposable2;
    }

    public void dimiss() {
        Disposable disposable2 = this.disposable;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
