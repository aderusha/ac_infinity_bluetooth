package com.github.iielse.switchbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class SwitchView extends View {
    private static final int STATE_SWITCH_OFF = 1;
    private static final int STATE_SWITCH_OFF2 = 2;
    private static final int STATE_SWITCH_ON = 4;
    private static final int STATE_SWITCH_ON2 = 3;
    protected float animationSpeed;
    private float bAnim;
    private float bLeft;
    private float bOff2LeftX;
    private float bOffLeftX;
    private float bOffset;
    private float bOn2LeftX;
    private float bOnLeftX;
    private final Path bPath;
    private float bRadius;
    private final RectF bRectF;
    private float bRight;
    private float bStrokeWidth;
    private float bWidth;
    protected int colorBackground;
    protected int colorBar;
    protected int colorOff;
    protected int colorOffDark;
    protected int colorPrimary;
    protected int colorPrimaryDark;
    protected int colorShadow;
    protected boolean hasShadow;
    private final AccelerateInterpolator interpolator;
    private boolean isCanVisibleDrawing;
    protected boolean isOpened;
    private int lastState;
    private OnStateChangedListener listener;
    private View.OnClickListener mOnClickListener;
    private final Paint paint;
    protected float ratioAspect;
    private float sAnim;
    private float sCenterX;
    private float sCenterY;
    private final Path sPath;
    private float sRight;
    private float sScale;
    private RadialGradient shadowGradient;
    private float shadowReservedHeight;
    private int state;

    public interface OnStateChangedListener {
        void toggleToOff(SwitchView switchView);

        void toggleToOn(SwitchView switchView);
    }

    public SwitchView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.interpolator = new AccelerateInterpolator(2.0f);
        this.paint = new Paint();
        this.sPath = new Path();
        this.bPath = new Path();
        this.bRectF = new RectF();
        this.ratioAspect = 0.68f;
        this.animationSpeed = 0.1f;
        this.isCanVisibleDrawing = false;
        this.listener = new OnStateChangedListener() {
            public void toggleToOn(SwitchView switchView) {
                SwitchView.this.toggleSwitch(true);
            }

            public void toggleToOff(SwitchView switchView) {
                SwitchView.this.toggleSwitch(false);
            }
        };
        setLayerType(1, (Paint) null);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2852R.styleable.SwitchView);
        this.colorPrimary = obtainStyledAttributes.getColor(C2852R.styleable.SwitchView_primaryColor, -11806877);
        this.colorPrimaryDark = obtainStyledAttributes.getColor(C2852R.styleable.SwitchView_primaryColorDark, -12925358);
        this.colorOff = obtainStyledAttributes.getColor(C2852R.styleable.SwitchView_offColor, -1842205);
        this.colorOffDark = obtainStyledAttributes.getColor(C2852R.styleable.SwitchView_offColorDark, -4210753);
        this.colorShadow = obtainStyledAttributes.getColor(C2852R.styleable.SwitchView_shadowColor, -13421773);
        this.colorBar = obtainStyledAttributes.getColor(C2852R.styleable.SwitchView_barColor, -1);
        this.colorBackground = obtainStyledAttributes.getColor(C2852R.styleable.SwitchView_bgColor, -1);
        this.ratioAspect = obtainStyledAttributes.getFloat(C2852R.styleable.SwitchView_ratioAspect, 0.68f);
        this.hasShadow = obtainStyledAttributes.getBoolean(C2852R.styleable.SwitchView_hasShadow, true);
        boolean z = obtainStyledAttributes.getBoolean(C2852R.styleable.SwitchView_isOpened, false);
        this.isOpened = z;
        int i = z ? 4 : 1;
        this.state = i;
        this.lastState = i;
        obtainStyledAttributes.recycle();
        if (this.colorPrimary == -11806877 && this.colorPrimaryDark == -12925358) {
            try {
                if (Build.VERSION.SDK_INT >= 21) {
                    TypedValue typedValue = new TypedValue();
                    context.getTheme().resolveAttribute(16843827, typedValue, true);
                    if (typedValue.data > 0) {
                        this.colorPrimary = typedValue.data;
                    }
                    TypedValue typedValue2 = new TypedValue();
                    context.getTheme().resolveAttribute(16843828, typedValue2, true);
                    if (typedValue2.data > 0) {
                        this.colorPrimaryDark = typedValue2.data;
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public void setColor(int i, int i2) {
        setColor(i, i2, this.colorOff, this.colorOffDark);
    }

    public void setColor(int i, int i2, int i3, int i4) {
        setColor(i, i2, i3, i4, this.colorShadow);
    }

    public void setColor(int i, int i2, int i3, int i4, int i5) {
        this.colorPrimary = i;
        this.colorPrimaryDark = i2;
        this.colorOff = i3;
        this.colorOffDark = i4;
        this.colorShadow = i5;
        invalidate();
    }

    public void setColor(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.colorPrimary = i;
        this.colorPrimaryDark = i2;
        this.colorOff = i3;
        this.colorOffDark = i4;
        this.colorShadow = i5;
        this.colorBar = i6;
        this.colorBackground = i7;
        invalidate();
    }

    public void setShadow(boolean z) {
        this.hasShadow = z;
        invalidate();
    }

    public boolean isOpened() {
        return this.isOpened;
    }

    public void setOpened(boolean z) {
        int i = z ? 4 : 1;
        if (i != this.state) {
            refreshState(i);
        }
    }

    public void toggleSwitch(boolean z) {
        int i = z ? 4 : 1;
        int i2 = this.state;
        if (i != i2) {
            if ((i == 4 && (i2 == 1 || i2 == 2)) || (i == 1 && (i2 == 4 || i2 == 3))) {
                this.sAnim = 1.0f;
            }
            this.bAnim = 1.0f;
            refreshState(i);
        }
    }

    private void refreshState(int i) {
        boolean z = this.isOpened;
        if (!z && i == 4) {
            this.isOpened = true;
        } else if (z && i == 1) {
            this.isOpened = false;
        }
        this.lastState = this.state;
        this.state = i;
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        if (mode != 1073741824) {
            int paddingLeft = ((int) ((getResources().getDisplayMetrics().density * 56.0f) + 0.5f)) + getPaddingLeft() + getPaddingRight();
            size = mode == Integer.MIN_VALUE ? Math.min(paddingLeft, size) : paddingLeft;
        }
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode2 != 1073741824) {
            int paddingTop = ((int) (((float) size) * this.ratioAspect)) + getPaddingTop() + getPaddingBottom();
            size2 = mode2 == Integer.MIN_VALUE ? Math.min(paddingTop, size2) : paddingTop;
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        super.onSizeChanged(i, i2, i3, i4);
        boolean z = i > getPaddingLeft() + getPaddingRight() && i2 > getPaddingTop() + getPaddingBottom();
        this.isCanVisibleDrawing = z;
        if (z) {
            int paddingLeft = (i - getPaddingLeft()) - getPaddingRight();
            int paddingTop = (i2 - getPaddingTop()) - getPaddingBottom();
            float f = (float) paddingLeft;
            float f2 = this.ratioAspect;
            float f3 = (float) paddingTop;
            if (f * f2 < f3) {
                i5 = getPaddingLeft();
                i7 = i - getPaddingRight();
                int i9 = ((int) (f3 - (f * this.ratioAspect))) / 2;
                i6 = getPaddingTop() + i9;
                i8 = (getHeight() - getPaddingBottom()) - i9;
            } else {
                int i10 = ((int) (f - (f3 / f2))) / 2;
                i5 = getPaddingLeft() + i10;
                i7 = (getWidth() - getPaddingRight()) - i10;
                i6 = getPaddingTop();
                i8 = getHeight() - getPaddingBottom();
            }
            float f4 = (float) ((int) (((float) (i8 - i6)) * 0.07f));
            this.shadowReservedHeight = f4;
            float f5 = (float) i5;
            float f6 = ((float) i6) + f4;
            float f7 = (float) i7;
            this.sRight = f7;
            float f8 = ((float) i8) - f4;
            float f9 = f8 - f6;
            this.sCenterX = (f7 + f5) / 2.0f;
            float f10 = (f8 + f6) / 2.0f;
            this.sCenterY = f10;
            this.bLeft = f5;
            this.bWidth = f9;
            this.bRight = f5 + f9;
            float f11 = f9 / 2.0f;
            float f12 = 0.95f * f11;
            this.bRadius = f12;
            float f13 = 0.2f * f12;
            this.bOffset = f13;
            float f14 = (f11 - f12) * 2.0f;
            this.bStrokeWidth = f14;
            float f15 = f7 - f9;
            this.bOnLeftX = f15;
            this.bOn2LeftX = f15 - f13;
            this.bOffLeftX = f5;
            this.bOff2LeftX = f13 + f5;
            this.sScale = 1.0f - (f14 / f9);
            this.sPath.reset();
            RectF rectF = new RectF();
            rectF.top = f6;
            rectF.bottom = f8;
            rectF.left = f5;
            rectF.right = f5 + f9;
            this.sPath.arcTo(rectF, 90.0f, 180.0f);
            rectF.left = this.sRight - f9;
            rectF.right = this.sRight;
            this.sPath.arcTo(rectF, 270.0f, 180.0f);
            this.sPath.close();
            this.bRectF.left = this.bLeft;
            this.bRectF.right = this.bRight;
            this.bRectF.top = f6 + (this.bStrokeWidth / 2.0f);
            this.bRectF.bottom = f8 - (this.bStrokeWidth / 2.0f);
            float f16 = (this.bRight + this.bLeft) / 2.0f;
            int i11 = this.colorShadow;
            int i12 = (i11 >> 16) & 255;
            int i13 = (i11 >> 8) & 255;
            int i14 = i11 & 255;
            this.shadowGradient = new RadialGradient(f16, f10, this.bRadius, Color.argb(200, i12, i13, i14), Color.argb(25, i12, i13, i14), Shader.TileMode.CLAMP);
        }
    }

    private void calcBPath(float f) {
        this.bPath.reset();
        this.bRectF.left = this.bLeft + (this.bStrokeWidth / 2.0f);
        this.bRectF.right = this.bRight - (this.bStrokeWidth / 2.0f);
        this.bPath.arcTo(this.bRectF, 90.0f, 180.0f);
        this.bRectF.left = this.bLeft + (this.bOffset * f) + (this.bStrokeWidth / 2.0f);
        this.bRectF.right = (this.bRight + (f * this.bOffset)) - (this.bStrokeWidth / 2.0f);
        this.bPath.arcTo(this.bRectF, 270.0f, 180.0f);
        this.bPath.close();
    }

    private float calcBTranslate(float f) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        int i = this.state;
        int i2 = i - this.lastState;
        if (i2 != -3) {
            if (i2 != -2) {
                if (i2 != -1) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            if (i2 != 3) {
                                if (i == 1) {
                                    f2 = this.bOffLeftX;
                                } else if (i == 4) {
                                    f2 = this.bOnLeftX;
                                }
                                return f2 - this.bOffLeftX;
                            }
                            f5 = this.bOnLeftX;
                            f6 = this.bOffLeftX;
                        } else if (i == 4) {
                            f5 = this.bOnLeftX;
                            f6 = this.bOffLeftX;
                        } else if (i == 3) {
                            f5 = this.bOn2LeftX;
                            f6 = this.bOffLeftX;
                        }
                    } else if (i == 2) {
                        f2 = this.bOffLeftX;
                        return f2 - this.bOffLeftX;
                    } else if (i == 4) {
                        f5 = this.bOnLeftX;
                        f6 = this.bOn2LeftX;
                    }
                    f2 = f5 - ((f5 - f6) * f);
                    return f2 - this.bOffLeftX;
                } else if (i == 3) {
                    f4 = this.bOn2LeftX;
                    f3 = this.bOnLeftX;
                } else if (i == 1) {
                    f2 = this.bOffLeftX;
                    return f2 - this.bOffLeftX;
                }
            } else if (i == 1) {
                f4 = this.bOffLeftX;
                f3 = this.bOn2LeftX;
            } else if (i == 2) {
                f4 = this.bOff2LeftX;
                f3 = this.bOnLeftX;
            }
            f2 = 0.0f;
            return f2 - this.bOffLeftX;
        }
        f4 = this.bOffLeftX;
        f3 = this.bOnLeftX;
        f2 = f4 + ((f3 - f4) * f);
        return f2 - this.bOffLeftX;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.isCanVisibleDrawing) {
            boolean z = true;
            this.paint.setAntiAlias(true);
            int i = this.state;
            boolean z2 = i == 4 || i == 3;
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(z2 ? this.colorPrimary : this.colorOff);
            canvas.drawPath(this.sPath, this.paint);
            float f = this.sAnim;
            float f2 = this.animationSpeed;
            float f3 = f - f2 > 0.0f ? f - f2 : 0.0f;
            this.sAnim = f3;
            float f4 = this.bAnim;
            this.bAnim = f4 - f2 > 0.0f ? f4 - f2 : 0.0f;
            float interpolation = this.interpolator.getInterpolation(f3);
            float interpolation2 = this.interpolator.getInterpolation(this.bAnim);
            float f5 = this.sScale * (z2 ? interpolation : 1.0f - interpolation);
            float f6 = (this.sRight - this.sCenterX) - this.bRadius;
            if (z2) {
                interpolation = 1.0f - interpolation;
            }
            canvas.save();
            canvas.scale(f5, f5, this.sCenterX + (f6 * interpolation), this.sCenterY);
            this.paint.setColor(this.colorBackground);
            canvas.drawPath(this.sPath, this.paint);
            canvas.restore();
            canvas.save();
            canvas.translate(calcBTranslate(interpolation2), this.shadowReservedHeight);
            int i2 = this.state;
            if (!(i2 == 3 || i2 == 2)) {
                z = false;
            }
            if (z) {
                interpolation2 = 1.0f - interpolation2;
            }
            calcBPath(interpolation2);
            if (this.hasShadow) {
                this.paint.setStyle(Paint.Style.FILL);
                this.paint.setShader(this.shadowGradient);
                canvas.drawPath(this.bPath, this.paint);
                this.paint.setShader((Shader) null);
            }
            canvas.translate(0.0f, -this.shadowReservedHeight);
            float f7 = this.bWidth;
            canvas.scale(0.98f, 0.98f, f7 / 2.0f, f7 / 2.0f);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(this.colorBar);
            canvas.drawPath(this.bPath, this.paint);
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(this.bStrokeWidth * 0.5f);
            this.paint.setColor(z2 ? this.colorPrimaryDark : this.colorOffDark);
            canvas.drawPath(this.bPath, this.paint);
            canvas.restore();
            this.paint.reset();
            if (this.sAnim > 0.0f || this.bAnim > 0.0f) {
                invalidate();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = this.state;
        if ((i == 4 || i == 1) && this.sAnim * this.bAnim == 0.0f) {
            int action = motionEvent.getAction();
            if (action == 0) {
                return true;
            }
            if (action == 1) {
                int i2 = this.state;
                this.lastState = i2;
                this.bAnim = 1.0f;
                if (i2 == 1) {
                    refreshState(2);
                    this.listener.toggleToOn(this);
                } else if (i2 == 4) {
                    refreshState(3);
                    this.listener.toggleToOff(this);
                }
                View.OnClickListener onClickListener = this.mOnClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(this);
                }
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.mOnClickListener = onClickListener;
    }

    public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        if (onStateChangedListener != null) {
            this.listener = onStateChangedListener;
            return;
        }
        throw new IllegalArgumentException("empty listener");
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        boolean unused = savedState.isOpened = this.isOpened;
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        boolean access$000 = savedState.isOpened;
        this.isOpened = access$000;
        this.state = access$000 ? 4 : 1;
        invalidate();
    }

    private static final class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        /* access modifiers changed from: private */
        public boolean isOpened;

        public int describeContents() {
            return 0;
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.isOpened = 1 != parcel.readInt() ? false : true;
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.isOpened ? 1 : 0);
        }
    }
}
