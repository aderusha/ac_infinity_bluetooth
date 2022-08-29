package com.eternal.widget.loadingdialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class LoadCircleView extends View {
    public final String TAG;
    private int currentLineIndex;
    private Context mContext;
    private float mPadding;
    private Paint mPaint;
    private int mWidth;
    private RectF rectF;

    public LoadCircleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public LoadCircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mPadding = 0.0f;
        this.mWidth = 0;
        this.currentLineIndex = 0;
        this.mContext = context;
        init();
    }

    public void init() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(8.0f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE && mode2 != Integer.MIN_VALUE) {
            if (size < size2) {
                size = size2;
            }
            this.mWidth = size;
        } else if (mode == Integer.MIN_VALUE && mode2 != Integer.MIN_VALUE) {
            this.mWidth = size2;
        } else if (mode != Integer.MIN_VALUE) {
            this.mWidth = size;
        } else {
            this.mWidth = SizeUtils.dip2px(this.mContext, 50.0f);
        }
        int i3 = this.mWidth;
        setMeasuredDimension(i3, i3);
        this.mPadding = 8.0f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        int i = this.mWidth;
        int i2 = i >> 1;
        int i3 = (i >> 1) - 8;
        if (this.currentLineIndex >= 12) {
            this.currentLineIndex = 0;
        }
        for (int i4 = 0; i4 < 12; i4++) {
            int i5 = this.currentLineIndex;
            if (i4 < i5 + 4 && i4 >= i5) {
                this.mPaint.setColor(-7829368);
            } else if (i5 <= 8 || i4 >= (i5 + 4) - 12) {
                this.mPaint.setColor(-1);
            } else {
                this.mPaint.setColor(-7829368);
            }
            float f = (float) i2;
            canvas.drawLine(f, (float) (((double) i2) + (((double) i3) * 0.5d)), f, (float) (i3 * 2), this.mPaint);
            canvas.rotate(30.0f, f, f);
        }
        this.currentLineIndex++;
        postInvalidateDelayed(50);
    }
}
