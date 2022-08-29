package com.hjq.xtoast;

import android.view.View;

final class ViewClickWrapper implements View.OnClickListener {
    private final OnClickListener mListener;
    private final XToast mToast;

    ViewClickWrapper(XToast xToast, View view, OnClickListener onClickListener) {
        this.mToast = xToast;
        this.mListener = onClickListener;
        view.setOnClickListener(this);
    }

    public final void onClick(View view) {
        this.mListener.onClick(this.mToast, view);
    }
}
