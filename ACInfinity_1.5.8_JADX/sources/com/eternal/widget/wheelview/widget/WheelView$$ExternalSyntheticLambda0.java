package com.eternal.widget.wheelview.widget;

import android.view.MotionEvent;
import android.view.View;

public final /* synthetic */ class WheelView$$ExternalSyntheticLambda0 implements View.OnTouchListener {
    public static final /* synthetic */ WheelView$$ExternalSyntheticLambda0 INSTANCE = new WheelView$$ExternalSyntheticLambda0();

    private /* synthetic */ WheelView$$ExternalSyntheticLambda0() {
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return view.getParent().requestDisallowInterceptTouchEvent(true);
    }
}
