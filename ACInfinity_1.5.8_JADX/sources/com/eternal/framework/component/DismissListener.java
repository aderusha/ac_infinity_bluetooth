package com.eternal.framework.component;

import android.content.DialogInterface;
import p014io.reactivex.disposables.Disposable;

public class DismissListener implements DialogInterface.OnDismissListener {
    private Disposable disposable;

    public DismissListener(Disposable disposable2) {
        this.disposable = disposable2;
    }

    public void setDisposable(Disposable disposable2) {
        this.disposable = disposable2;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Disposable disposable2 = this.disposable;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.disposable.dispose();
        }
    }
}
