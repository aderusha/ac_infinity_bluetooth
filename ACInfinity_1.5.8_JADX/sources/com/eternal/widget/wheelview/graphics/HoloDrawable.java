package com.eternal.widget.wheelview.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.eternal.widget.wheelview.common.WheelConstants;
import com.eternal.widget.wheelview.widget.WheelView;

public class HoloDrawable extends WheelDrawable {
    private Paint mHoloBgPaint;
    private Paint mHoloPaint;
    private int mItemH;
    private int mWheelSize;

    HoloDrawable(int i, int i2, WheelView.WheelViewStyle wheelViewStyle, int i3, int i4) {
        super(i, i2, wheelViewStyle);
        this.mWheelSize = i3;
        this.mItemH = i4;
        init();
    }

    private void init() {
        int i;
        Paint paint = new Paint();
        this.mHoloBgPaint = paint;
        paint.setColor(this.mStyle.backgroundColor != -1 ? this.mStyle.backgroundColor : -1);
        Paint paint2 = new Paint();
        this.mHoloPaint = paint2;
        paint2.setStrokeWidth(this.mStyle.holoBorderWidth != -1 ? (float) this.mStyle.holoBorderWidth : 3.0f);
        Paint paint3 = this.mHoloPaint;
        if (this.mStyle.holoBorderColor != -1) {
            i = this.mStyle.holoBorderColor;
        } else {
            i = WheelConstants.WHEEL_SKIN_HOLO_BORDER_COLOR;
        }
        paint3.setColor(i);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight, this.mHoloBgPaint);
        int i = this.mItemH;
        if (i != 0) {
            int i2 = this.mWheelSize >> 1;
            canvas.drawLine(0.0f, (float) (i * i2), (float) this.mWidth, (float) (this.mItemH * i2), this.mHoloPaint);
            int i3 = i2 + 1;
            canvas.drawLine(0.0f, (float) (this.mItemH * i3), (float) this.mWidth, (float) (this.mItemH * i3), this.mHoloPaint);
        }
    }
}
