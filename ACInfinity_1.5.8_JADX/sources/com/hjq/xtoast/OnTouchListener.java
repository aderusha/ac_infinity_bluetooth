package com.hjq.xtoast;

import android.view.MotionEvent;
import android.view.View;

public interface OnTouchListener<V extends View> {
    boolean onTouch(XToast xToast, V v, MotionEvent motionEvent);
}
