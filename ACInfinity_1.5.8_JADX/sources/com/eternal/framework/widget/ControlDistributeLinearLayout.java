package com.eternal.framework.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.eternal.framework.C2171R;

public class ControlDistributeLinearLayout extends LinearLayout {
    private boolean isDistributeEvent;

    public ControlDistributeLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.isDistributeEvent = false;
        this.isDistributeEvent = getContext().obtainStyledAttributes(attributeSet, C2171R.styleable.ControlDistributeLinearLayout).getBoolean(C2171R.styleable.ControlDistributeLinearLayout_distribute_event, false);
    }

    public ControlDistributeLinearLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ControlDistributeLinearLayout(Context context) {
        this(context, (AttributeSet) null);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return isDistributeEvent();
    }

    public boolean isDistributeEvent() {
        return this.isDistributeEvent;
    }

    public void setDistributeEvent(boolean z) {
        this.isDistributeEvent = z;
    }
}
