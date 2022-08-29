package com.eternal.widget.wheelview.graphics;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import com.eternal.widget.wheelview.common.WheelConstants;
import com.eternal.widget.wheelview.widget.WheelView;

public class CommonDrawable extends WheelDrawable {
    private static final int[] SHADOWS_COLORS = {-15658735, 11184810, 11184810};
    private GradientDrawable mBottomShadow;
    private Paint mCommonBgPaint;
    private Paint mCommonBorderPaint;
    private Paint mCommonDividerPaint;
    private Paint mCommonPaint;
    private int mItemH;
    private GradientDrawable mTopShadow;
    private int mWheelSize;

    CommonDrawable(int i, int i2, WheelView.WheelViewStyle wheelViewStyle, int i3, int i4) {
        super(i, i2, wheelViewStyle);
        GradientDrawable.Orientation orientation = GradientDrawable.Orientation.TOP_BOTTOM;
        int[] iArr = SHADOWS_COLORS;
        this.mTopShadow = new GradientDrawable(orientation, iArr);
        this.mBottomShadow = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, iArr);
        this.mWheelSize = i3;
        this.mItemH = i4;
        init();
    }

    private void init() {
        int i;
        Paint paint = new Paint();
        this.mCommonBgPaint = paint;
        if (this.mStyle.backgroundColor != -1) {
            i = this.mStyle.backgroundColor;
        } else {
            i = WheelConstants.WHEEL_SKIN_COMMON_BG;
        }
        paint.setColor(i);
        Paint paint2 = new Paint();
        this.mCommonPaint = paint2;
        paint2.setColor(WheelConstants.WHEEL_SKIN_COMMON_COLOR);
        Paint paint3 = new Paint();
        this.mCommonDividerPaint = paint3;
        paint3.setColor(WheelConstants.WHEEL_SKIN_COMMON_DIVIDER_COLOR);
        this.mCommonDividerPaint.setStrokeWidth(2.0f);
        Paint paint4 = new Paint();
        this.mCommonBorderPaint = paint4;
        paint4.setStrokeWidth(6.0f);
        this.mCommonBorderPaint.setColor(WheelConstants.WHEEL_SKIN_COMMON_BORDER_COLOR);
    }

    public void draw(Canvas canvas) {
        canvas.drawRect(0.0f, 0.0f, (float) this.mWidth, (float) this.mHeight, this.mCommonBgPaint);
        int i = this.mItemH;
        if (i != 0) {
            int i2 = this.mWheelSize >> 1;
            int i3 = i2 + 1;
            canvas.drawRect(0.0f, (float) (i * i2), (float) this.mWidth, (float) (this.mItemH * i3), this.mCommonPaint);
            canvas.drawLine(0.0f, (float) (this.mItemH * i2), (float) this.mWidth, (float) (this.mItemH * i2), this.mCommonDividerPaint);
            canvas.drawLine(0.0f, (float) (this.mItemH * i3), (float) this.mWidth, (float) (this.mItemH * i3), this.mCommonDividerPaint);
            this.mTopShadow.setBounds(0, 0, this.mWidth, this.mItemH);
            this.mTopShadow.draw(canvas);
            this.mBottomShadow.setBounds(0, this.mHeight - this.mItemH, this.mWidth, this.mHeight);
            this.mBottomShadow.draw(canvas);
            canvas.drawLine(0.0f, 0.0f, 0.0f, (float) this.mHeight, this.mCommonBorderPaint);
            canvas.drawLine((float) this.mWidth, 0.0f, (float) this.mWidth, (float) this.mHeight, this.mCommonBorderPaint);
        }
    }
}
