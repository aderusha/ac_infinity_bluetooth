package com.eternal.widget.loadingdialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class WrongDiaView extends View {
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

    public WrongDiaView(Context context) {
        this(context, (AttributeSet) null);
    }

    public WrongDiaView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WrongDiaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mWidth = 0;
        this.mPadding = 0.0f;
        this.times = 0;
        this.drawEveryTime = true;
        this.speed = 1;
        this.count = 0;
        this.progress = 0;
        initPaint(context);
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

    private void initPaint(Context context) {
        this.mContext = context;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(-1);
        this.mPaint.setStrokeWidth(8.0f);
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
        int i = this.progress;
        if (i < 100) {
            this.progress = i + this.speed;
        }
        canvas.drawArc(this.rectF, 235.0f, (float) ((this.progress * 360) / 100), false, this.mPaint);
        int i2 = this.mWidth;
        int i3 = (i2 * 3) / 10;
        int i4 = (i2 * 7) / 10;
        if (this.progress == 100) {
            int i5 = this.line1_x;
            if (i5 + i3 <= i4) {
                int i6 = this.speed;
                this.line1_x = i5 + i6;
                this.line1_y += i6;
            }
            float f = (float) i3;
            canvas.drawLine(f, f, (float) (this.line1_x + i3), (float) (this.line1_y + i3), this.mPaint);
            int i7 = this.line1_x;
            int i8 = this.mWidth;
            if (i7 == (i8 * 2) / 5) {
                this.line1_x = i7 + 1;
                this.line1_y++;
            }
            if (this.line1_x >= (i8 * 2) / 5) {
                int i9 = this.line2_y;
                if (i4 - i9 >= i3) {
                    int i10 = this.line2_x;
                    int i11 = this.speed;
                    this.line2_x = i10 - i11;
                    this.line2_y = i9 + i11;
                }
            }
            canvas.drawLine((float) i4, f, (float) (this.line2_x + i4), (float) (this.line2_y + i3), this.mPaint);
            if (i4 - this.line2_y < i3) {
                if (this.count == 0 && this.times == 0 && (finishDrawListener = this.listener) != null) {
                    finishDrawListener.dispatchFinishEvent(this);
                    this.count++;
                }
                int i12 = this.times - 1;
                this.times = i12;
                if (i12 >= 0) {
                    reDraw();
                    invalidate();
                } else {
                    return;
                }
            }
        }
        invalidate();
    }

    private void drawStatic(Canvas canvas) {
        canvas.drawArc(this.rectF, 0.0f, 360.0f, false, this.mPaint);
        int i = this.mWidth;
        int i2 = (i * 3) / 10;
        int i3 = (i * 7) / 10;
        float f = (float) i2;
        float f2 = f;
        canvas.drawLine(f, f2, (float) (((i * 2) / 5) + i2), (float) (((i * 2) / 5) + i2), this.mPaint);
        int i4 = this.mWidth;
        canvas.drawLine((float) (((i4 * 2) / 5) + i2), f2, f, (float) (i2 + ((i4 * 2) / 5)), this.mPaint);
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
        throw new IllegalArgumentException("how can u set this speed??  " + i + "  do not use reflect to use this method!u can see the LoadingDialog class for how toset the speed");
    }

    /* access modifiers changed from: protected */
    public void setDrawColor(int i) {
        this.mPaint.setColor(i);
    }

    public void setOnDrawFinishListener(FinishDrawListener finishDrawListener) {
        this.listener = finishDrawListener;
    }
}
