package com.hjq.xtoast;

import java.lang.ref.WeakReference;

final class ToastDismissRunnable extends WeakReference<XToast> implements Runnable {
    ToastDismissRunnable(XToast xToast) {
        super(xToast);
    }

    public void run() {
        XToast xToast = (XToast) get();
        if (xToast != null && xToast.isShow()) {
            xToast.cancel();
        }
    }
}
