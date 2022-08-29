package com.eternal.widget.wheelview.graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.eternal.widget.wheelview.widget.WheelView;

public class WheelDrawable extends Drawable {
    private Paint mBgPaint;
    int mHeight;
    WheelView.WheelViewStyle mStyle;
    int mWidth;

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    WheelDrawable(int i, int i2, WheelView.WheelViewStyle wheelViewStyle) {
        this.mWidth = i;
        this.mHeight = i2;
        this.mStyle = wheelViewStyle;
        init();
    }

    private void init() {
        Paint paint = new Paint();
        this.mBgPaint = paint;
        int i = -1;
        if (this.mStyle.backgroundColor != -1) {
            i = this.mStyle.backgroundColor;
        }
        paint.setColor(i);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight, this.mBgPaint);
    }
}
