package com.eternal.widget;

import android.content.Context;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.Switch;
import com.eternal.framework.utils.KLog;

public class SwitchButton extends Switch {
    private boolean resize;
    private LayerDrawable thumb;
    private StateListDrawable track;

    private void init() {
    }

    public SwitchButton(Context context) {
        super(context);
        init();
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        KLog.m65e("width:" + i + " height:" + i2);
        if (!this.resize) {
            int min = (int) (((double) Math.min(i / 2, getMeasuredHeight())) * 0.8d);
            changeThumb((LayerDrawable) getThumbDrawable(), min);
            changeTick((StateListDrawable) getTrackDrawable(), i, min);
            this.resize = true;
            postInvalidate();
        }
    }

    private void changeThumb(LayerDrawable layerDrawable, int i) {
        ((GradientDrawable) layerDrawable.getDrawable(0)).setSize(i, i);
        KLog.m65e(String.format("thumb width:%d height:%d size:%d", new Object[]{Integer.valueOf(layerDrawable.getIntrinsicWidth()), Integer.valueOf(layerDrawable.getIntrinsicHeight()), Integer.valueOf(i)}));
    }

    private void changeTick(StateListDrawable stateListDrawable, int i, int i2) {
        DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) stateListDrawable.getConstantState();
        if (drawableContainerState != null) {
            for (int i3 = 0; i3 < drawableContainerState.getChildCount(); i3++) {
                ((GradientDrawable) drawableContainerState.getChild(i3)).setSize(i, i2);
            }
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }
}
