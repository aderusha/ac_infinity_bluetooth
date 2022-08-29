package com.eternal.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Switch;

public class StatueSwitch extends Switch {
    private boolean isTouched;

    public StatueSwitch(Context context) {
        super(context);
    }

    public StatueSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StatueSwitch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.isTouched = true;
        } else if (motionEvent.getAction() == 1) {
            this.isTouched = false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean isTouched() {
        return this.isTouched;
    }

    public void setTouched(boolean z) {
        this.isTouched = z;
    }
}
