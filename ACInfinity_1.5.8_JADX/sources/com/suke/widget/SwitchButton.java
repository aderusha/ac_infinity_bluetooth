package com.suke.widget;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import com.eternal.widget.wheelview.common.WheelConstants;

public class SwitchButton extends View implements Checkable {
    private static final int DEFAULT_HEIGHT = dp2pxInt(36.0f);
    private static final int DEFAULT_WIDTH = dp2pxInt(58.0f);
    private final int ANIMATE_STATE_DRAGING = 2;
    private final int ANIMATE_STATE_NONE = 0;
    private final int ANIMATE_STATE_PENDING_DRAG = 1;
    private final int ANIMATE_STATE_PENDING_RESET = 3;
    private final int ANIMATE_STATE_PENDING_SETTLE = 4;
    private final int ANIMATE_STATE_SWITCH = 5;
    /* access modifiers changed from: private */
    public ViewState afterState;
    /* access modifiers changed from: private */
    public int animateState = 0;
    private Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {
        public void onAnimationCancel(Animator animator) {
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
        }

        public void onAnimationEnd(Animator animator) {
            int access$300 = SwitchButton.this.animateState;
            if (access$300 == 1) {
                int unused = SwitchButton.this.animateState = 2;
                SwitchButton.this.viewState.checkedLineColor = 0;
                SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius;
                SwitchButton.this.postInvalidate();
            } else if (access$300 == 3) {
                int unused2 = SwitchButton.this.animateState = 0;
                SwitchButton.this.postInvalidate();
            } else if (access$300 == 4) {
                int unused3 = SwitchButton.this.animateState = 0;
                SwitchButton.this.postInvalidate();
                SwitchButton.this.broadcastEvent();
            } else if (access$300 == 5) {
                SwitchButton switchButton = SwitchButton.this;
                boolean unused4 = switchButton.isChecked = true ^ switchButton.isChecked;
                int unused5 = SwitchButton.this.animateState = 0;
                SwitchButton.this.postInvalidate();
                SwitchButton.this.broadcastEvent();
            }
        }
    };
    private ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() {
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
            int access$300 = SwitchButton.this.animateState;
            if (access$300 == 1 || access$300 == 3 || access$300 == 4) {
                SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(floatValue, Integer.valueOf(SwitchButton.this.beforeState.checkedLineColor), Integer.valueOf(SwitchButton.this.afterState.checkedLineColor))).intValue();
                SwitchButton.this.viewState.radius = SwitchButton.this.beforeState.radius + ((SwitchButton.this.afterState.radius - SwitchButton.this.beforeState.radius) * floatValue);
                if (SwitchButton.this.animateState != 1) {
                    SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * floatValue);
                }
                SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(floatValue, Integer.valueOf(SwitchButton.this.beforeState.checkStateColor), Integer.valueOf(SwitchButton.this.afterState.checkStateColor))).intValue();
            } else if (access$300 == 5) {
                SwitchButton.this.viewState.buttonX = SwitchButton.this.beforeState.buttonX + ((SwitchButton.this.afterState.buttonX - SwitchButton.this.beforeState.buttonX) * floatValue);
                float access$800 = (SwitchButton.this.viewState.buttonX - SwitchButton.this.buttonMinX) / (SwitchButton.this.buttonMaxX - SwitchButton.this.buttonMinX);
                SwitchButton.this.viewState.checkStateColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(access$800, Integer.valueOf(SwitchButton.this.uncheckColor), Integer.valueOf(SwitchButton.this.checkedColor))).intValue();
                SwitchButton.this.viewState.radius = SwitchButton.this.viewRadius * access$800;
                SwitchButton.this.viewState.checkedLineColor = ((Integer) SwitchButton.this.argbEvaluator.evaluate(access$800, 0, Integer.valueOf(SwitchButton.this.checkLineColor))).intValue();
            }
            SwitchButton.this.postInvalidate();
        }
    };
    /* access modifiers changed from: private */
    public final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private int background;
    /* access modifiers changed from: private */
    public ViewState beforeState;
    private int borderWidth;
    private float bottom;
    /* access modifiers changed from: private */
    public float buttonMaxX;
    /* access modifiers changed from: private */
    public float buttonMinX;
    private Paint buttonPaint;
    private float buttonRadius;
    private float centerX;
    private float centerY;
    /* access modifiers changed from: private */
    public int checkLineColor;
    private float checkLineLength;
    private int checkLineWidth;
    /* access modifiers changed from: private */
    public int checkedColor;
    private float checkedLineOffsetX;
    private float checkedLineOffsetY;
    private boolean enableEffect;
    private float height;
    /* access modifiers changed from: private */
    public boolean isChecked;
    private boolean isEventBroadcast = false;
    private boolean isTouchingDown = false;
    private boolean isUiInited = false;
    private float left;
    private OnCheckedChangeListener onCheckedChangeListener;
    private Paint paint;
    private Runnable postPendingDrag = new Runnable() {
        public void run() {
            if (!SwitchButton.this.isInAnimating()) {
                SwitchButton.this.pendingDragState();
            }
        }
    };
    private RectF rect = new RectF();
    private float right;
    private int shadowColor;
    private boolean shadowEffect;
    private int shadowOffset;
    private int shadowRadius;
    private boolean showIndicator;
    private float top;
    private long touchDownTime;
    private int uncheckCircleColor;
    private float uncheckCircleOffsetX;
    private float uncheckCircleRadius;
    private int uncheckCircleWidth;
    /* access modifiers changed from: private */
    public int uncheckColor;
    private ValueAnimator valueAnimator;
    /* access modifiers changed from: private */
    public float viewRadius;
    /* access modifiers changed from: private */
    public ViewState viewState;
    private float width;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(SwitchButton switchButton, boolean z);
    }

    public final void setOnClickListener(View.OnClickListener onClickListener) {
    }

    public final void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
    }

    public SwitchButton(Context context) {
        super(context);
        init(context, (AttributeSet) null);
    }

    public SwitchButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    public SwitchButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet);
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(0, 0, 0, 0);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = attributeSet != null ? context.obtainStyledAttributes(attributeSet, C3583R.styleable.SwitchButton) : null;
        this.shadowEffect = optBoolean(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_shadow_effect, true);
        this.uncheckCircleColor = optColor(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_uncheckcircle_color, -5592406);
        this.uncheckCircleWidth = optPixelSize(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_uncheckcircle_width, dp2pxInt(1.5f));
        this.uncheckCircleOffsetX = dp2px(10.0f);
        this.uncheckCircleRadius = optPixelSize(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_uncheckcircle_radius, dp2px(4.0f));
        this.checkedLineOffsetX = dp2px(4.0f);
        this.checkedLineOffsetY = dp2px(4.0f);
        this.shadowRadius = optPixelSize(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_shadow_radius, dp2pxInt(2.5f));
        this.shadowOffset = optPixelSize(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_shadow_offset, dp2pxInt(1.5f));
        this.shadowColor = optColor(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_shadow_color, 855638016);
        this.uncheckColor = optColor(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_uncheck_color, -2236963);
        this.checkedColor = optColor(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_checked_color, -11414681);
        this.borderWidth = optPixelSize(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_border_width, dp2pxInt(1.0f));
        this.checkLineColor = optColor(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_checkline_color, -1);
        this.checkLineWidth = optPixelSize(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_checkline_width, dp2pxInt(1.0f));
        this.checkLineLength = dp2px(6.0f);
        int optColor = optColor(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_button_color, -1);
        int optInt = optInt(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_effect_duration, WheelConstants.WHEEL_SCROLL_DELAY_DURATION);
        this.isChecked = optBoolean(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_checked, false);
        this.showIndicator = optBoolean(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_show_indicator, true);
        this.background = optColor(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_background, -1);
        this.enableEffect = optBoolean(obtainStyledAttributes, C3583R.styleable.SwitchButton_sb_enable_effect, true);
        if (obtainStyledAttributes != null) {
            obtainStyledAttributes.recycle();
        }
        this.paint = new Paint(1);
        Paint paint2 = new Paint(1);
        this.buttonPaint = paint2;
        paint2.setColor(optColor);
        if (this.shadowEffect) {
            this.buttonPaint.setShadowLayer((float) this.shadowRadius, 0.0f, (float) this.shadowOffset, this.shadowColor);
        }
        this.viewState = new ViewState();
        this.beforeState = new ViewState();
        this.afterState = new ViewState();
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        this.valueAnimator = ofFloat;
        ofFloat.setDuration((long) optInt);
        this.valueAnimator.setRepeatCount(0);
        this.valueAnimator.addUpdateListener(this.animatorUpdateListener);
        this.valueAnimator.addListener(this.animatorListener);
        super.setClickable(true);
        setPadding(0, 0, 0, 0);
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, (Paint) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == 0 || mode == Integer.MIN_VALUE) {
            i = View.MeasureSpec.makeMeasureSpec(DEFAULT_WIDTH, 1073741824);
        }
        if (mode2 == 0 || mode2 == Integer.MIN_VALUE) {
            i2 = View.MeasureSpec.makeMeasureSpec(DEFAULT_HEIGHT, 1073741824);
        }
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        float max = (float) Math.max(this.shadowRadius + this.shadowOffset, this.borderWidth);
        float f = ((float) i2) - max;
        float f2 = f - max;
        this.height = f2;
        float f3 = ((float) i) - max;
        this.width = f3 - max;
        float f4 = f2 * 0.5f;
        this.viewRadius = f4;
        this.buttonRadius = f4 - ((float) this.borderWidth);
        this.left = max;
        this.top = max;
        this.right = f3;
        this.bottom = f;
        this.centerX = (max + f3) * 0.5f;
        this.centerY = (f + max) * 0.5f;
        this.buttonMinX = max + f4;
        this.buttonMaxX = f3 - f4;
        if (isChecked()) {
            setCheckedViewState(this.viewState);
        } else {
            setUncheckViewState(this.viewState);
        }
        this.isUiInited = true;
        postInvalidate();
    }

    private void setUncheckViewState(ViewState viewState2) {
        viewState2.radius = 0.0f;
        viewState2.checkStateColor = this.uncheckColor;
        viewState2.checkedLineColor = 0;
        viewState2.buttonX = this.buttonMinX;
    }

    private void setCheckedViewState(ViewState viewState2) {
        viewState2.radius = this.viewRadius;
        viewState2.checkStateColor = this.checkedColor;
        viewState2.checkedLineColor = this.checkLineColor;
        viewState2.buttonX = this.buttonMaxX;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.paint.setStrokeWidth((float) this.borderWidth);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.background);
        Canvas canvas2 = canvas;
        drawRoundRect(canvas2, this.left, this.top, this.right, this.bottom, this.viewRadius, this.paint);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.uncheckColor);
        drawRoundRect(canvas2, this.left, this.top, this.right, this.bottom, this.viewRadius, this.paint);
        if (this.showIndicator) {
            drawUncheckIndicator(canvas);
        }
        float f = this.viewState.radius * 0.5f;
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setColor(this.viewState.checkStateColor);
        this.paint.setStrokeWidth(((float) this.borderWidth) + (f * 2.0f));
        Canvas canvas3 = canvas;
        drawRoundRect(canvas3, this.left + f, this.top + f, this.right - f, this.bottom - f, this.viewRadius, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(1.0f);
        float f2 = this.left;
        float f3 = this.top;
        float f4 = this.viewRadius;
        drawArc(canvas3, f2, f3, f2 + (f4 * 2.0f), f3 + (f4 * 2.0f), 90.0f, 180.0f, this.paint);
        canvas.drawRect(this.left + this.viewRadius, this.top, this.viewState.buttonX, this.top + (this.viewRadius * 2.0f), this.paint);
        if (this.showIndicator) {
            drawCheckedIndicator(canvas);
        }
        drawButton(canvas, this.viewState.buttonX, this.centerY);
    }

    /* access modifiers changed from: protected */
    public void drawCheckedIndicator(Canvas canvas) {
        int i = this.viewState.checkedLineColor;
        float f = (float) this.checkLineWidth;
        float f2 = this.left;
        float f3 = this.viewRadius;
        float f4 = (f2 + f3) - this.checkedLineOffsetX;
        float f5 = this.centerY;
        float f6 = this.checkLineLength;
        float f7 = f5 - f6;
        float f8 = (f2 + f3) - this.checkedLineOffsetY;
        drawCheckedIndicator(canvas, i, f, f4, f7, f8, f5 + f6, this.paint);
    }

    /* access modifiers changed from: protected */
    public void drawCheckedIndicator(Canvas canvas, int i, float f, float f2, float f3, float f4, float f5, Paint paint2) {
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(i);
        paint2.setStrokeWidth(f);
        canvas.drawLine(f2, f3, f4, f5, paint2);
    }

    private void drawUncheckIndicator(Canvas canvas) {
        drawUncheckIndicator(canvas, this.uncheckCircleColor, (float) this.uncheckCircleWidth, this.right - this.uncheckCircleOffsetX, this.centerY, this.uncheckCircleRadius, this.paint);
    }

    /* access modifiers changed from: protected */
    public void drawUncheckIndicator(Canvas canvas, int i, float f, float f2, float f3, float f4, Paint paint2) {
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(i);
        paint2.setStrokeWidth(f);
        canvas.drawCircle(f2, f3, f4, paint2);
    }

    private void drawArc(Canvas canvas, float f, float f2, float f3, float f4, float f5, float f6, Paint paint2) {
        if (Build.VERSION.SDK_INT >= 21) {
            canvas.drawArc(f, f2, f3, f4, f5, f6, true, paint2);
            return;
        }
        float f7 = f;
        float f8 = f2;
        this.rect.set(f, f2, f3, f4);
        canvas.drawArc(this.rect, f5, f6, true, paint2);
    }

    private void drawRoundRect(Canvas canvas, float f, float f2, float f3, float f4, float f5, Paint paint2) {
        if (Build.VERSION.SDK_INT >= 21) {
            canvas.drawRoundRect(f, f2, f3, f4, f5, f5, paint2);
            return;
        }
        this.rect.set(f, f2, f3, f4);
        canvas.drawRoundRect(this.rect, f5, f5, paint2);
    }

    private void drawButton(Canvas canvas, float f, float f2) {
        canvas.drawCircle(f, f2, this.buttonRadius, this.buttonPaint);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(1.0f);
        this.paint.setColor(-2236963);
        canvas.drawCircle(f, f2, this.buttonRadius, this.paint);
    }

    public void setChecked(boolean z) {
        if (z == isChecked()) {
            postInvalidate();
        } else {
            toggle(this.enableEffect, false);
        }
    }

    public boolean isChecked() {
        return this.isChecked;
    }

    public void toggle() {
        toggle(true);
    }

    public void toggle(boolean z) {
        toggle(z, true);
    }

    private void toggle(boolean z, boolean z2) {
        if (isEnabled()) {
            if (this.isEventBroadcast) {
                throw new RuntimeException("should NOT switch the state in method: [onCheckedChanged]!");
            } else if (!this.isUiInited) {
                this.isChecked = !this.isChecked;
                if (z2) {
                    broadcastEvent();
                }
            } else {
                if (this.valueAnimator.isRunning()) {
                    this.valueAnimator.cancel();
                }
                if (!this.enableEffect || !z) {
                    this.isChecked = !this.isChecked;
                    if (isChecked()) {
                        setCheckedViewState(this.viewState);
                    } else {
                        setUncheckViewState(this.viewState);
                    }
                    postInvalidate();
                    if (z2) {
                        broadcastEvent();
                        return;
                    }
                    return;
                }
                this.animateState = 5;
                this.beforeState.copy(this.viewState);
                if (isChecked()) {
                    setUncheckViewState(this.afterState);
                } else {
                    setCheckedViewState(this.afterState);
                }
                this.valueAnimator.start();
            }
        }
    }

    /* access modifiers changed from: private */
    public void broadcastEvent() {
        OnCheckedChangeListener onCheckedChangeListener2 = this.onCheckedChangeListener;
        if (onCheckedChangeListener2 != null) {
            this.isEventBroadcast = true;
            onCheckedChangeListener2.onCheckedChanged(this, isChecked());
        }
        this.isEventBroadcast = false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!isEnabled()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.isTouchingDown = true;
            this.touchDownTime = System.currentTimeMillis();
            removeCallbacks(this.postPendingDrag);
            postDelayed(this.postPendingDrag, 100);
        } else if (actionMasked == 1) {
            this.isTouchingDown = false;
            removeCallbacks(this.postPendingDrag);
            if (System.currentTimeMillis() - this.touchDownTime <= 300) {
                toggle();
            } else if (isDragState()) {
                if (Math.max(0.0f, Math.min(1.0f, motionEvent.getX() / ((float) getWidth()))) > 0.5f) {
                    z = true;
                }
                if (z == isChecked()) {
                    pendingCancelDragState();
                } else {
                    this.isChecked = z;
                    pendingSettleState();
                }
            } else if (isPendingDragState()) {
                pendingCancelDragState();
            }
        } else if (actionMasked == 2) {
            float x = motionEvent.getX();
            if (isPendingDragState()) {
                float max = Math.max(0.0f, Math.min(1.0f, x / ((float) getWidth())));
                ViewState viewState2 = this.viewState;
                float f = this.buttonMinX;
                viewState2.buttonX = f + ((this.buttonMaxX - f) * max);
            } else if (isDragState()) {
                float max2 = Math.max(0.0f, Math.min(1.0f, x / ((float) getWidth())));
                ViewState viewState3 = this.viewState;
                float f2 = this.buttonMinX;
                viewState3.buttonX = f2 + ((this.buttonMaxX - f2) * max2);
                this.viewState.checkStateColor = ((Integer) this.argbEvaluator.evaluate(max2, Integer.valueOf(this.uncheckColor), Integer.valueOf(this.checkedColor))).intValue();
                postInvalidate();
            }
        } else if (actionMasked == 3) {
            this.isTouchingDown = false;
            removeCallbacks(this.postPendingDrag);
            if (isPendingDragState() || isDragState()) {
                pendingCancelDragState();
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean isInAnimating() {
        return this.animateState != 0;
    }

    private boolean isPendingDragState() {
        int i = this.animateState;
        return i == 1 || i == 3;
    }

    private boolean isDragState() {
        return this.animateState == 2;
    }

    public void setShadowEffect(boolean z) {
        if (this.shadowEffect != z) {
            this.shadowEffect = z;
            if (z) {
                this.buttonPaint.setShadowLayer((float) this.shadowRadius, 0.0f, (float) this.shadowOffset, this.shadowColor);
            } else {
                this.buttonPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            }
        }
    }

    public void setEnableEffect(boolean z) {
        this.enableEffect = z;
    }

    /* access modifiers changed from: private */
    public void pendingDragState() {
        if (!isInAnimating() && this.isTouchingDown) {
            if (this.valueAnimator.isRunning()) {
                this.valueAnimator.cancel();
            }
            this.animateState = 1;
            this.beforeState.copy(this.viewState);
            this.afterState.copy(this.viewState);
            if (isChecked()) {
                this.afterState.checkStateColor = this.checkedColor;
                this.afterState.buttonX = this.buttonMaxX;
                this.afterState.checkedLineColor = this.checkedColor;
            } else {
                this.afterState.checkStateColor = this.uncheckColor;
                this.afterState.buttonX = this.buttonMinX;
                this.afterState.radius = this.viewRadius;
            }
            this.valueAnimator.start();
        }
    }

    private void pendingCancelDragState() {
        if (isDragState() || isPendingDragState()) {
            if (this.valueAnimator.isRunning()) {
                this.valueAnimator.cancel();
            }
            this.animateState = 3;
            this.beforeState.copy(this.viewState);
            if (isChecked()) {
                setCheckedViewState(this.afterState);
            } else {
                setUncheckViewState(this.afterState);
            }
            this.valueAnimator.start();
        }
    }

    private void pendingSettleState() {
        if (this.valueAnimator.isRunning()) {
            this.valueAnimator.cancel();
        }
        this.animateState = 4;
        this.beforeState.copy(this.viewState);
        if (isChecked()) {
            setCheckedViewState(this.afterState);
        } else {
            setUncheckViewState(this.afterState);
        }
        this.valueAnimator.start();
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener2) {
        this.onCheckedChangeListener = onCheckedChangeListener2;
    }

    private static float dp2px(float f) {
        return TypedValue.applyDimension(1, f, Resources.getSystem().getDisplayMetrics());
    }

    private static int dp2pxInt(float f) {
        return (int) dp2px(f);
    }

    private static int optInt(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getInt(i, i2);
    }

    private static float optPixelSize(TypedArray typedArray, int i, float f) {
        return typedArray == null ? f : typedArray.getDimension(i, f);
    }

    private static int optPixelSize(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getDimensionPixelOffset(i, i2);
    }

    private static int optColor(TypedArray typedArray, int i, int i2) {
        return typedArray == null ? i2 : typedArray.getColor(i, i2);
    }

    private static boolean optBoolean(TypedArray typedArray, int i, boolean z) {
        return typedArray == null ? z : typedArray.getBoolean(i, z);
    }

    private static class ViewState {
        float buttonX;
        int checkStateColor;
        int checkedLineColor;
        float radius;

        ViewState() {
        }

        /* access modifiers changed from: private */
        public void copy(ViewState viewState) {
            this.buttonX = viewState.buttonX;
            this.checkStateColor = viewState.checkStateColor;
            this.checkedLineColor = viewState.checkedLineColor;
            this.radius = viewState.radius;
        }
    }
}
