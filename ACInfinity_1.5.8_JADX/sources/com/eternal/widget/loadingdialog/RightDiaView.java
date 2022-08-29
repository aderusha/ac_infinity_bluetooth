package com.eternal.widget.loadingdialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RightDiaView extends View {
    private final String TAG;
    private int count;
    private boolean drawEveryTime;
    private int line1_x;
    private int line1_y;
    private int line2_x;
    private int line2_y;
    private FinishDrawListener listener;
    private Context mContext;
    private float mPadding;
    private Paint mPaint;
    private int mWidth;
    int progress;
    private RectF rectF;
    private int speed;
    private int times;

    public RightDiaView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RightDiaView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RightDiaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mWidth = 0;
        this.mPadding = 0.0f;
        this.times = 0;
        this.drawEveryTime = true;
        this.speed = 1;
        this.progress = 0;
        this.count = 0;
        init(context);
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
            this.mWidth = SizeUtils.dip2px(this.mContext, 80.0f);
        }
        int i3 = this.mWidth;
        setMeasuredDimension(i3, i3);
        this.mPadding = 8.0f;
        float f = this.mPadding;
        int i4 = this.mWidth;
        this.rectF = new RectF(f, f, ((float) i4) - f, ((float) i4) - f);
    }

    private void init(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth(8.0f);
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.drawEveryTime) {
            drawDynamic(canvas);
            return;
        }
        drawStatic(canvas);
        FinishDrawListener finishDrawListener = this.listener;
        if (finishDrawListener != null) {
            finishDrawListener.dispatchFinishEvent(this);
        }
    }

    private void drawDynamic(Canvas canvas) {
        FinishDrawListener finishDrawListener;
        int i;
        int i2;
        int i3 = this.progress;
        if (i3 < 100) {
            this.progress = i3 + this.speed;
        }
        canvas.drawArc(this.rectF, 235.0f, (float) ((this.progress * 360) / 100), false, this.mPaint);
        int i4 = this.mWidth;
        int i5 = i4 / 2;
        int i6 = i5 - (i4 / 5);
        int i7 = (i4 / 2) - 8;
        if (this.progress == 100) {
            int i8 = this.line1_x;
            int i9 = i7 / 3;
            if (i8 < i9) {
                int i10 = this.speed;
                this.line1_x = i8 + i10;
                this.line1_y += i10;
            }
            canvas.drawLine((float) i6, (float) i5, (float) (this.line1_x + i6), (float) (this.line1_y + i5), this.mPaint);
            int i11 = this.line1_x;
            if (i11 >= i9 && this.line2_x == 0 && this.line2_y == 0) {
                this.line2_x = i11;
                int i12 = this.line1_y;
                this.line2_y = i12;
                int i13 = this.speed;
                this.line1_x = i11 + i13;
                this.line1_y = i12 + i13;
            }
            int i14 = this.line1_x;
            if (i14 >= i9 && (i = this.line2_x) <= i7 && (i2 = this.line2_y) <= i5 - i9) {
                int i15 = this.speed;
                this.line2_x = i + i15;
                this.line2_y = i2 - i15;
            }
            canvas.drawLine((float) ((i14 + i6) - 1), (float) ((this.line1_y + i5) - 4), (float) (i6 + this.line2_x), (float) (i5 + this.line2_y), this.mPaint);
        }
        if (this.line2_x > i7 && this.progress >= 100 && this.line1_x != i7 / 3) {
            if (this.count == 0 && this.times == 0 && (finishDrawListener = this.listener) != null) {
                finishDrawListener.dispatchFinishEvent(this);
                this.count++;
            }
            int i16 = this.times - 1;
            this.times = i16;
            if (i16 >= 0) {
                reDraw();
                invalidate();
            } else {
                return;
            }
        }
        invalidate();
    }

    private void drawStatic(Canvas canvas) {
        canvas.drawArc(this.rectF, 0.0f, 360.0f, false, this.mPaint);
        int i = this.mWidth;
        int i2 = i / 2;
        int i3 = i2 - (i / 5);
        int i4 = (i / 2) - 8;
        int i5 = i4 / 3;
        int i6 = i3 + i5;
        int i7 = i2 + i5;
        Canvas canvas2 = canvas;
        canvas2.drawLine((float) i3, (float) i2, (float) i6, (float) i7, this.mPaint);
        canvas2.drawLine((float) (i6 - 1), (float) (i7 - 4), (float) (i3 + i4), (float) (i2 - i5), this.mPaint);
    }

    private void reDraw() {
        this.line1_x = 0;
        this.line2_x = 0;
        this.line1_y = 0;
        this.line2_y = 0;
        this.progress = 0;
    }

    /* access modifiers changed from: protected */
    public void setRepeatTime(int i) {
        if (this.drawEveryTime) {
            this.times = i;
        }
    }

    /* access modifiers changed from: protected */
    public void setDrawDynamic(boolean z) {
        this.drawEveryTime = z;
    }

    /* access modifiers changed from: protected */
    public void setSpeed(int i) {
        if (i > 0 || i < 3) {
            this.speed = i;
            return;
        }
        throw new IllegalArgumentException("support speed >0 & < 3, the speed you set is: " + i);
    }

    /* access modifiers changed from: protected */
    public void setDrawColor(int i) {
        this.mPaint.setColor(i);
    }

    public void setOnDrawFinishListener(FinishDrawListener finishDrawListener) {
        this.listener = finishDrawListener;
    }
}
