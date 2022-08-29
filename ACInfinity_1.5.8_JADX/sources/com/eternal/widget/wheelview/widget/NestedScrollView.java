package com.eternal.widget.wheelview.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import com.eternal.widget.wheelview.common.WheelConstants;

public class NestedScrollView extends ScrollView {
    public NestedScrollView(Context context) {
        super(context);
        init();
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public NestedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        setOnTouchListener(new NestedScrollView$$ExternalSyntheticLambda0(this));
    }

    public /* synthetic */ boolean lambda$init$0$NestedScrollView(View view, MotionEvent motionEvent) {
        WheelView wheelView = (WheelView) findViewWithTag(WheelConstants.TAG);
        if (wheelView != null) {
            wheelView.getParent().requestDisallowInterceptTouchEvent(false);
        }
        return false;
    }
}
