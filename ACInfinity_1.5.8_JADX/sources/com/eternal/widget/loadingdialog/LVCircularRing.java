package com.eternal.widget.loadingdialog;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class LVCircularRing extends View {
    public final String TAG;
    private int color;
    private float mPadding;
    private Paint mPaint;
    private Paint mPaint2;
    private float mWidth;
    /* access modifiers changed from: private */
    public float startAngle;
    ValueAnimator valueAnimator;

    public LVCircularRing(Context context) {
        this(context, (AttributeSet) null);
    }

    public LVCircularRing(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LVCircularRing(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = getClass().getSimpleName();
        this.mWidth = 0.0f;
        this.mPadding = 0.0f;
        this.startAngle = 0.0f;
        this.color = Color.argb(100, 255, 255, 255);
        initPaint();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getMeasuredWidth() > getHeight()) {
            this.mWidth = (float) getMeasuredHeight();
        } else {
            this.mWidth = (float) getMeasuredWidth();
        }
        this.mPadding = 5.0f;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mPaint2.setColor(this.color);
        float f = this.mWidth;
        canvas.drawCircle(f / 2.0f, f / 2.0f, (f / 2.0f) - this.mPadding, this.mPaint2);
        this.mPaint.setColor(-1);
        float f2 = this.mPadding;
        float f3 = this.mWidth;
        canvas.drawArc(new RectF(f2, f2, f3 - f2, f3 - f2), this.startAngle, 100.0f, false, this.mPaint);
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(this.color);
        this.mPaint.setStrokeWidth(8.0f);
        Paint paint2 = new Paint();
        this.mPaint2 = paint2;
        paint2.setAntiAlias(true);
        this.mPaint2.setStyle(Paint.Style.STROKE);
        this.mPaint2.setStrokeWidth(8.0f);
        this.mPaint2.setColor(this.color);
    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0.0f, 1.0f, 1000);
    }

    public void stopAnim() {
        if (this.valueAnimator != null) {
            clearAnimation();
            this.valueAnimator.setRepeatCount(1);
            this.valueAnimator.cancel();
            this.valueAnimator.end();
        }
    }

    private ValueAnimator startViewAnim(float f, float f2, long j) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{f, f2});
        this.valueAnimator = ofFloat;
        ofFloat.setDuration(j);
        this.valueAnimator.setInterpolator(new LinearInterpolator());
        this.valueAnimator.setRepeatCount(-1);
        this.valueAnimator.setRepeatMode(1);
        this.valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float unused = LVCircularRing.this.startAngle = ((Float) valueAnimator.getAnimatedValue()).floatValue() * 360.0f;
                LVCircularRing.this.invalidate();
            }
        });
        this.valueAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
            }
        });
        if (!this.valueAnimator.isRunning()) {
            this.valueAnimator.start();
        }
        return this.valueAnimator;
    }

    public void setColor(int i) {
        this.color = i;
        this.mPaint.setColor(i);
        this.mPaint2.setColor(i);
    }
}
