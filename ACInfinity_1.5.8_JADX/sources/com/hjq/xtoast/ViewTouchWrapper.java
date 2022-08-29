package com.hjq.xtoast;

import android.view.MotionEvent;
import android.view.View;

final class ViewTouchWrapper implements View.OnTouchListener {
    private final OnTouchListener mListener;
    private final XToast mToast;

    ViewTouchWrapper(XToast xToast, View view, OnTouchListener onTouchListener) {
        this.mToast = xToast;
        this.mListener = onTouchListener;
        view.setFocusable(true);
        view.setEnabled(true);
        view.setClickable(true);
        view.setOnTouchListener(this);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.mListener.onTouch(this.mToast, view, motionEvent);
    }
}
