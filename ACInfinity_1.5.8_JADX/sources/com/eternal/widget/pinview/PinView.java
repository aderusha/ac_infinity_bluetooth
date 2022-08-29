package com.eternal.widget.pinview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.MovementMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import com.eternal.widget.C2779R;
import java.util.Objects;

public class PinView extends AppCompatEditText {
    private static final int BLINK = 500;
    private static final boolean DBG = false;
    private static final int DEFAULT_COUNT = 4;
    private static final int[] HIGHLIGHT_STATES = {16842913};
    private static final InputFilter[] NO_FILTERS = new InputFilter[0];
    private static final String TAG = "PinView";
    private static final int VIEW_TYPE_LINE = 1;
    private static final int VIEW_TYPE_NONE = 2;
    private static final int VIEW_TYPE_RECTANGLE = 0;
    /* access modifiers changed from: private */
    public boolean drawCursor;
    private boolean isAnimationEnable;
    private boolean isCursorVisible;
    private boolean isPasswordHidden;
    /* access modifiers changed from: private */
    public final TextPaint mAnimatorTextPaint;
    private Blink mBlink;
    private int mCurLineColor;
    private int mCursorColor;
    private float mCursorHeight;
    private int mCursorWidth;
    private ValueAnimator mDefaultAddAnimator;
    private ColorStateList mEnteredLineColor;
    private ColorStateList mErrorLineColor;
    private boolean mErrorState;
    private boolean mHideLineWhenFilled;
    private Drawable mItemBackground;
    private int mItemBackgroundResource;
    private final RectF mItemBorderRect;
    private final PointF mItemCenterPoint;
    private final RectF mItemLineRect;
    private ColorStateList mLineColor;
    private int mLineWidth;
    private ColorStateList mNormalLineColor;
    private final Paint mPaint;
    private final Path mPath;
    private int mPinItemCount;
    private int mPinItemHeight;
    private int mPinItemRadius;
    private int mPinItemSpacing;
    private int mPinItemWidth;
    private final Rect mTextRect;
    private String mTransformed;
    private int mViewType;

    private static boolean isPasswordInputType(int i) {
        int i2 = i & 4095;
        return i2 == 129 || i2 == 225 || i2 == 18;
    }

    public boolean isSuggestionsEnabled() {
        return false;
    }

    public PinView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PinView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C2779R.attr.pinViewStyle);
    }

    public PinView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TextPaint textPaint = new TextPaint();
        this.mAnimatorTextPaint = textPaint;
        this.mCurLineColor = -16777216;
        this.mTextRect = new Rect();
        this.mItemBorderRect = new RectF();
        this.mItemLineRect = new RectF();
        this.mPath = new Path();
        this.mItemCenterPoint = new PointF();
        this.isAnimationEnable = false;
        Resources resources = getResources();
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.STROKE);
        textPaint.set(getPaint());
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C2779R.styleable.PinView, i, 0);
        this.mViewType = obtainStyledAttributes.getInt(C2779R.styleable.PinView_viewType, 0);
        this.mPinItemCount = obtainStyledAttributes.getInt(C2779R.styleable.PinView_itemCount, 4);
        this.mPinItemHeight = (int) obtainStyledAttributes.getDimension(C2779R.styleable.PinView_itemHeight, (float) resources.getDimensionPixelSize(C2779R.dimen.pv_pin_view_item_size));
        this.mPinItemWidth = (int) obtainStyledAttributes.getDimension(C2779R.styleable.PinView_itemWidth, (float) resources.getDimensionPixelSize(C2779R.dimen.pv_pin_view_item_size));
        this.mPinItemSpacing = obtainStyledAttributes.getDimensionPixelSize(C2779R.styleable.PinView_itemSpacing, resources.getDimensionPixelSize(C2779R.dimen.pv_pin_view_item_spacing));
        this.mPinItemRadius = (int) obtainStyledAttributes.getDimension(C2779R.styleable.PinView_itemRadius, 0.0f);
        this.mLineWidth = (int) obtainStyledAttributes.getDimension(C2779R.styleable.PinView_lineWidth, (float) resources.getDimensionPixelSize(C2779R.dimen.pv_pin_view_item_line_width));
        this.mLineColor = obtainStyledAttributes.getColorStateList(C2779R.styleable.PinView_pin_lineColor);
        this.mNormalLineColor = obtainStyledAttributes.getColorStateList(C2779R.styleable.PinView_normalLineColor);
        this.mEnteredLineColor = obtainStyledAttributes.getColorStateList(C2779R.styleable.PinView_enteredLineColor);
        this.mErrorLineColor = obtainStyledAttributes.getColorStateList(C2779R.styleable.PinView_errorLineColor);
        this.isCursorVisible = obtainStyledAttributes.getBoolean(C2779R.styleable.PinView_android_cursorVisible, true);
        this.mCursorColor = obtainStyledAttributes.getColor(C2779R.styleable.PinView_cursorColor, getCurrentTextColor());
        this.mCursorWidth = obtainStyledAttributes.getDimensionPixelSize(C2779R.styleable.PinView_cursorWidth, resources.getDimensionPixelSize(C2779R.dimen.pv_pin_view_cursor_width));
        this.mItemBackground = obtainStyledAttributes.getDrawable(C2779R.styleable.PinView_android_itemBackground);
        this.mHideLineWhenFilled = obtainStyledAttributes.getBoolean(C2779R.styleable.PinView_hideLineWhenFilled, false);
        obtainStyledAttributes.recycle();
        ColorStateList colorStateList = this.mLineColor;
        if (colorStateList != null) {
            this.mCurLineColor = colorStateList.getDefaultColor();
        }
        updateCursorHeight();
        checkItemRadius();
        setMaxLength(this.mPinItemCount);
        paint.setStrokeWidth((float) this.mLineWidth);
        setupAnimator();
        setTransformationMethod((TransformationMethod) null);
        disableSelectionMenu();
        this.isPasswordHidden = isPasswordInputType(getInputType());
    }

    public static void errorColor(PinView pinView, boolean z) {
        pinView.setErrorState(z);
    }

    public void setErrorState(boolean z) {
        this.mErrorState = z;
        invalidate();
    }

    public void setInputType(int i) {
        super.setInputType(i);
        this.isPasswordHidden = isPasswordInputType(getInputType());
    }

    public void setPasswordHidden(boolean z) {
        this.isPasswordHidden = z;
        requestLayout();
    }

    public boolean isPasswordHidden() {
        return this.isPasswordHidden;
    }

    public void setTypeface(Typeface typeface, int i) {
        super.setTypeface(typeface, i);
    }

    public void setTypeface(Typeface typeface) {
        super.setTypeface(typeface);
        TextPaint textPaint = this.mAnimatorTextPaint;
        if (textPaint != null) {
            textPaint.set(getPaint());
        }
    }

    private void setMaxLength(int i) {
        if (i >= 0) {
            setFilters(new InputFilter[]{new InputFilter.LengthFilter(i)});
            return;
        }
        setFilters(NO_FILTERS);
    }

    private void setupAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.5f, 1.0f});
        this.mDefaultAddAnimator = ofFloat;
        ofFloat.setDuration(150);
        this.mDefaultAddAnimator.setInterpolator(new DecelerateInterpolator());
        this.mDefaultAddAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                PinView.this.mAnimatorTextPaint.setTextSize(PinView.this.getTextSize() * floatValue);
                PinView.this.mAnimatorTextPaint.setAlpha((int) (255.0f * floatValue));
                PinView.this.postInvalidate();
            }
        });
    }

    private void checkItemRadius() {
        int i = this.mViewType;
        if (i == 1) {
            if (((float) this.mPinItemRadius) > ((float) this.mLineWidth) / 2.0f) {
                throw new IllegalArgumentException("The itemRadius can not be greater than lineWidth when viewType is line");
            }
        } else if (i == 0) {
            if (((float) this.mPinItemRadius) > ((float) this.mPinItemWidth) / 2.0f) {
                throw new IllegalArgumentException("The itemRadius can not be greater than itemWidth");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = this.mPinItemHeight;
        if (mode != 1073741824) {
            int i4 = this.mPinItemCount;
            size = ViewCompat.getPaddingStart(this) + ((i4 - 1) * this.mPinItemSpacing) + (i4 * this.mPinItemWidth) + ViewCompat.getPaddingEnd(this);
            if (this.mPinItemSpacing == 0) {
                size -= (this.mPinItemCount - 1) * this.mLineWidth;
            }
        }
        if (mode2 != 1073741824) {
            size2 = getPaddingBottom() + i3 + getPaddingTop();
        }
        setMeasuredDimension(size, size2);
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        ValueAnimator valueAnimator;
        if (i != charSequence.length()) {
            moveSelectionToEnd();
        }
        makeBlink();
        if (this.isAnimationEnable) {
            if ((i3 - i2 > 0) && (valueAnimator = this.mDefaultAddAnimator) != null) {
                valueAnimator.end();
                this.mDefaultAddAnimator.start();
            }
        }
        TransformationMethod transformationMethod = getTransformationMethod();
        if (transformationMethod == null) {
            this.mTransformed = getText().toString();
        } else {
            this.mTransformed = transformationMethod.getTransformation(getText(), this).toString();
        }
    }

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        if (z) {
            moveSelectionToEnd();
            makeBlink();
        }
    }

    /* access modifiers changed from: protected */
    public void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
        if (i2 != getText().length()) {
            moveSelectionToEnd();
        }
    }

    private void moveSelectionToEnd() {
        setSelection(getText().length());
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        ColorStateList colorStateList = this.mLineColor;
        if (colorStateList == null || colorStateList.isStateful()) {
            updateColors();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.save();
        updatePaints();
        drawPinView(canvas);
        canvas.restore();
    }

    private void updatePaints() {
        this.mPaint.setColor(this.mCurLineColor);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth((float) this.mLineWidth);
        getPaint().setColor(getCurrentTextColor());
    }

    private void drawPinView(Canvas canvas) {
        int length = getText().length();
        int i = 0;
        while (i < this.mPinItemCount) {
            boolean z = isFocused() && length == i;
            this.mPaint.setColor(z ? getLineColorForState(HIGHLIGHT_STATES) : this.mCurLineColor);
            updateItemRectF(i);
            updateCenterPoint();
            canvas.save();
            if (this.mViewType == 0) {
                updatePinBoxPath(i);
                canvas.clipPath(this.mPath);
            }
            drawItemBackground(canvas, z);
            canvas.restore();
            if (z) {
                drawCursor(canvas);
            }
            int i2 = this.mViewType;
            if (i2 == 0) {
                drawPinBox(canvas, i);
            } else if (i2 == 1) {
                drawPinLine(canvas, i, length);
            }
            if (this.mTransformed.length() > i) {
                if (getTransformationMethod() != null || !this.isPasswordHidden) {
                    drawText(canvas, i);
                } else {
                    drawCircle(canvas, i);
                }
            } else if (!TextUtils.isEmpty(getHint()) && getHint().length() == this.mPinItemCount) {
                drawHint(canvas, i);
            }
            i++;
        }
        if (isFocused() && getText().length() != this.mPinItemCount && this.mViewType == 0) {
            int length2 = getText().length();
            updateItemRectF(length2);
            updateCenterPoint();
            updatePinBoxPath(length2);
            this.mPaint.setColor(getLineColorForState(HIGHLIGHT_STATES));
            drawPinBox(canvas, length2);
        }
    }

    private int getLineColorForState(int... iArr) {
        ColorStateList colorStateList = this.mLineColor;
        return colorStateList != null ? colorStateList.getColorForState(iArr, this.mCurLineColor) : this.mCurLineColor;
    }

    private void drawItemBackground(Canvas canvas, boolean z) {
        if (this.mItemBackground != null) {
            float f = ((float) this.mLineWidth) / 2.0f;
            this.mItemBackground.setBounds(Math.round(this.mItemBorderRect.left - f), Math.round(this.mItemBorderRect.top - f), Math.round(this.mItemBorderRect.right + f), Math.round(this.mItemBorderRect.bottom + f));
            this.mItemBackground.setState(z ? HIGHLIGHT_STATES : getDrawableState());
            this.mItemBackground.draw(canvas);
        }
    }

    private void updatePinBoxPath(int i) {
        boolean z;
        boolean z2;
        if (this.mPinItemSpacing != 0) {
            z2 = true;
        } else {
            boolean z3 = i == 0 && i != this.mPinItemCount - 1;
            if (i != this.mPinItemCount - 1 || i == 0) {
                z2 = z3;
                z = false;
                RectF rectF = this.mItemBorderRect;
                int i2 = this.mPinItemRadius;
                updateRoundRectPath(rectF, (float) i2, (float) i2, z2, z);
            }
            z2 = z3;
        }
        z = true;
        RectF rectF2 = this.mItemBorderRect;
        int i22 = this.mPinItemRadius;
        updateRoundRectPath(rectF2, (float) i22, (float) i22, z2, z);
    }

    private void drawPinBox(Canvas canvas, int i) {
        if (!this.mHideLineWhenFilled || i >= getText().length()) {
            canvas.drawPath(this.mPath, this.mPaint);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawPinLine(android.graphics.Canvas r10, int r11, int r12) {
        /*
            r9 = this;
            boolean r0 = r9.mHideLineWhenFilled
            if (r0 == 0) goto L_0x000f
            android.text.Editable r0 = r9.getText()
            int r0 = r0.length()
            if (r11 >= r0) goto L_0x000f
            return
        L_0x000f:
            int r0 = r9.mPinItemSpacing
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L_0x0025
            int r0 = r9.mPinItemCount
            if (r0 <= r2) goto L_0x0025
            if (r11 != 0) goto L_0x001e
            r7 = 1
        L_0x001c:
            r8 = 0
            goto L_0x0027
        L_0x001e:
            int r0 = r0 - r2
            if (r11 != r0) goto L_0x0023
            r7 = 0
            goto L_0x0026
        L_0x0023:
            r7 = 0
            goto L_0x001c
        L_0x0025:
            r7 = 1
        L_0x0026:
            r8 = 1
        L_0x0027:
            android.graphics.Paint r0 = r9.mPaint
            android.graphics.Paint$Style r1 = android.graphics.Paint.Style.FILL
            r0.setStyle(r1)
            android.graphics.Paint r0 = r9.mPaint
            int r1 = r9.mLineWidth
            float r1 = (float) r1
            r2 = 1092616192(0x41200000, float:10.0)
            float r1 = r1 / r2
            r0.setStrokeWidth(r1)
            int r0 = r9.mLineWidth
            float r0 = (float) r0
            r1 = 1073741824(0x40000000, float:2.0)
            float r0 = r0 / r1
            android.graphics.RectF r1 = r9.mItemLineRect
            android.graphics.RectF r2 = r9.mItemBorderRect
            float r2 = r2.left
            float r2 = r2 - r0
            android.graphics.RectF r3 = r9.mItemBorderRect
            float r3 = r3.bottom
            float r3 = r3 - r0
            android.graphics.RectF r4 = r9.mItemBorderRect
            float r4 = r4.right
            float r4 = r4 + r0
            android.graphics.RectF r5 = r9.mItemBorderRect
            float r5 = r5.bottom
            float r5 = r5 + r0
            r1.set(r2, r3, r4, r5)
            android.graphics.RectF r4 = r9.mItemLineRect
            int r0 = r9.mPinItemRadius
            float r5 = (float) r0
            float r6 = (float) r0
            r3 = r9
            r3.updateRoundRectPath(r4, r5, r6, r7, r8)
            android.graphics.Paint r0 = r9.mPaint
            int r0 = r0.getColor()
            if (r11 > r12) goto L_0x0086
            boolean r11 = r9.mErrorState
            if (r11 == 0) goto L_0x007a
            android.graphics.Paint r11 = r9.mPaint
            android.content.res.ColorStateList r12 = r9.mErrorLineColor
            int r12 = r12.getDefaultColor()
            r11.setColor(r12)
            goto L_0x0091
        L_0x007a:
            android.graphics.Paint r11 = r9.mPaint
            android.content.res.ColorStateList r12 = r9.mEnteredLineColor
            int r12 = r12.getDefaultColor()
            r11.setColor(r12)
            goto L_0x0091
        L_0x0086:
            android.graphics.Paint r11 = r9.mPaint
            android.content.res.ColorStateList r12 = r9.mNormalLineColor
            int r12 = r12.getDefaultColor()
            r11.setColor(r12)
        L_0x0091:
            android.graphics.Path r11 = r9.mPath
            android.graphics.Paint r12 = r9.mPaint
            r10.drawPath(r11, r12)
            android.graphics.Paint r10 = r9.mPaint
            r10.setColor(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.widget.pinview.PinView.drawPinLine(android.graphics.Canvas, int, int):void");
    }

    private void drawCursor(Canvas canvas) {
        if (this.drawCursor) {
            float f = this.mItemCenterPoint.x;
            float f2 = this.mItemCenterPoint.y - (this.mCursorHeight / 2.0f);
            int color = this.mPaint.getColor();
            float strokeWidth = this.mPaint.getStrokeWidth();
            this.mPaint.setColor(this.mCursorColor);
            this.mPaint.setStrokeWidth((float) this.mCursorWidth);
            canvas.drawLine(f, f2, f, f2 + this.mCursorHeight, this.mPaint);
            this.mPaint.setColor(color);
            this.mPaint.setStrokeWidth(strokeWidth);
        }
    }

    private void updateRoundRectPath(RectF rectF, float f, float f2, boolean z, boolean z2) {
        updateRoundRectPath(rectF, f, f2, z, z2, z2, z);
    }

    private void updateRoundRectPath(RectF rectF, float f, float f2, boolean z, boolean z2, boolean z3, boolean z4) {
        this.mPath.reset();
        float f3 = rectF.left;
        float f4 = rectF.top;
        float f5 = (rectF.right - f3) - (f * 2.0f);
        float f6 = (rectF.bottom - f4) - (2.0f * f2);
        this.mPath.moveTo(f3, f4 + f2);
        if (z) {
            float f7 = -f2;
            this.mPath.rQuadTo(0.0f, f7, f, f7);
        } else {
            this.mPath.rLineTo(0.0f, -f2);
            this.mPath.rLineTo(f, 0.0f);
        }
        this.mPath.rLineTo(f5, 0.0f);
        if (z2) {
            this.mPath.rQuadTo(f, 0.0f, f, f2);
        } else {
            this.mPath.rLineTo(f, 0.0f);
            this.mPath.rLineTo(0.0f, f2);
        }
        this.mPath.rLineTo(0.0f, f6);
        if (z3) {
            this.mPath.rQuadTo(0.0f, f2, -f, f2);
        } else {
            this.mPath.rLineTo(0.0f, f2);
            this.mPath.rLineTo(-f, 0.0f);
        }
        this.mPath.rLineTo(-f5, 0.0f);
        if (z4) {
            float f8 = -f;
            this.mPath.rQuadTo(f8, 0.0f, f8, -f2);
        } else {
            this.mPath.rLineTo(-f, 0.0f);
            this.mPath.rLineTo(0.0f, -f2);
        }
        this.mPath.rLineTo(0.0f, -f6);
        this.mPath.close();
    }

    private void updateItemRectF(int i) {
        float f = ((float) this.mLineWidth) / 2.0f;
        int scrollX = getScrollX() + ViewCompat.getPaddingStart(this);
        int i2 = this.mPinItemSpacing;
        int i3 = this.mPinItemWidth;
        float f2 = ((float) (scrollX + ((i2 + i3) * i))) + f;
        if (i2 == 0 && i > 0) {
            f2 -= (float) (this.mLineWidth * i);
        }
        float f3 = (((float) i3) + f2) - ((float) this.mLineWidth);
        float scrollY = ((float) (getScrollY() + getPaddingTop())) + f;
        this.mItemBorderRect.set(f2, scrollY, f3, (((float) this.mPinItemHeight) + scrollY) - ((float) this.mLineWidth));
    }

    private void drawText(Canvas canvas, int i) {
        drawTextAtBox(canvas, getPaintByIndex(i), this.mTransformed, i);
    }

    private void drawHint(Canvas canvas, int i) {
        Paint paintByIndex = getPaintByIndex(i);
        paintByIndex.setColor(getCurrentHintTextColor());
        drawTextAtBox(canvas, paintByIndex, getHint(), i);
    }

    private void drawTextAtBox(Canvas canvas, Paint paint, CharSequence charSequence, int i) {
        int i2 = i + 1;
        paint.getTextBounds(charSequence.toString(), i, i2, this.mTextRect);
        float f = this.mItemCenterPoint.x;
        float f2 = this.mItemCenterPoint.y;
        canvas.drawText(charSequence, i, i2, (f - (Math.abs((float) this.mTextRect.width()) / 2.0f)) - ((float) this.mTextRect.left), (f2 + (Math.abs((float) this.mTextRect.height()) / 2.0f)) - ((float) this.mTextRect.bottom), paint);
    }

    private void drawCircle(Canvas canvas, int i) {
        Paint paintByIndex = getPaintByIndex(i);
        canvas.drawCircle(this.mItemCenterPoint.x, this.mItemCenterPoint.y, paintByIndex.getTextSize() / 2.0f, paintByIndex);
    }

    private Paint getPaintByIndex(int i) {
        if (!this.isAnimationEnable || i != getText().length() - 1) {
            return getPaint();
        }
        this.mAnimatorTextPaint.setColor(getPaint().getColor());
        return this.mAnimatorTextPaint;
    }

    private void drawAnchorLine(Canvas canvas) {
        float f = this.mItemCenterPoint.x;
        float f2 = this.mItemCenterPoint.y;
        this.mPaint.setStrokeWidth(1.0f);
        float strokeWidth = f - (this.mPaint.getStrokeWidth() / 2.0f);
        float strokeWidth2 = f2 - (this.mPaint.getStrokeWidth() / 2.0f);
        this.mPath.reset();
        this.mPath.moveTo(strokeWidth, this.mItemBorderRect.top);
        this.mPath.lineTo(strokeWidth, this.mItemBorderRect.top + Math.abs(this.mItemBorderRect.height()));
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPath.reset();
        this.mPath.moveTo(this.mItemBorderRect.left, strokeWidth2);
        this.mPath.lineTo(this.mItemBorderRect.left + Math.abs(this.mItemBorderRect.width()), strokeWidth2);
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPath.reset();
        this.mPaint.setStrokeWidth((float) this.mLineWidth);
    }

    private void updateColors() {
        int i;
        ColorStateList colorStateList = this.mLineColor;
        boolean z = false;
        if (colorStateList != null) {
            i = colorStateList.getColorForState(getDrawableState(), 0);
        } else {
            i = getCurrentTextColor();
        }
        if (i != this.mCurLineColor) {
            this.mCurLineColor = i;
            z = true;
        }
        if (z) {
            invalidate();
        }
    }

    private void updateCenterPoint() {
        this.mItemCenterPoint.set(this.mItemBorderRect.left + (Math.abs(this.mItemBorderRect.width()) / 2.0f), this.mItemBorderRect.top + (Math.abs(this.mItemBorderRect.height()) / 2.0f));
    }

    /* access modifiers changed from: protected */
    public MovementMethod getDefaultMovementMethod() {
        return DefaultMovementMethod.getInstance();
    }

    public void setLineColor(int i) {
        this.mLineColor = ColorStateList.valueOf(i);
        updateColors();
    }

    public void setLineColor(ColorStateList colorStateList) {
        Objects.requireNonNull(colorStateList);
        this.mLineColor = colorStateList;
        updateColors();
    }

    public ColorStateList getLineColors() {
        return this.mLineColor;
    }

    public int getCurrentLineColor() {
        return this.mCurLineColor;
    }

    public void setLineWidth(int i) {
        this.mLineWidth = i;
        checkItemRadius();
        requestLayout();
    }

    public int getLineWidth() {
        return this.mLineWidth;
    }

    public void setItemCount(int i) {
        this.mPinItemCount = i;
        setMaxLength(i);
        requestLayout();
    }

    public int getItemCount() {
        return this.mPinItemCount;
    }

    public void setItemRadius(int i) {
        this.mPinItemRadius = i;
        checkItemRadius();
        requestLayout();
    }

    public int getItemRadius() {
        return this.mPinItemRadius;
    }

    public void setItemSpacing(int i) {
        this.mPinItemSpacing = i;
        requestLayout();
    }

    public int getItemSpacing() {
        return this.mPinItemSpacing;
    }

    public void setItemHeight(int i) {
        this.mPinItemHeight = i;
        updateCursorHeight();
        requestLayout();
    }

    public int getItemHeight() {
        return this.mPinItemHeight;
    }

    public void setItemWidth(int i) {
        this.mPinItemWidth = i;
        checkItemRadius();
        requestLayout();
    }

    public int getItemWidth() {
        return this.mPinItemWidth;
    }

    public void setAnimationEnable(boolean z) {
        this.isAnimationEnable = z;
    }

    public void setHideLineWhenFilled(boolean z) {
        this.mHideLineWhenFilled = z;
    }

    public void setTextSize(float f) {
        super.setTextSize(f);
        updateCursorHeight();
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        updateCursorHeight();
    }

    public void setItemBackgroundResources(int i) {
        if (i == 0 || this.mItemBackgroundResource == i) {
            Drawable drawable = ResourcesCompat.getDrawable(getResources(), i, getContext().getTheme());
            this.mItemBackground = drawable;
            setItemBackground(drawable);
            this.mItemBackgroundResource = i;
        }
    }

    public void setItemBackgroundColor(int i) {
        Drawable drawable = this.mItemBackground;
        if (drawable instanceof ColorDrawable) {
            ((ColorDrawable) drawable.mutate()).setColor(i);
            this.mItemBackgroundResource = 0;
            return;
        }
        setItemBackground(new ColorDrawable(i));
    }

    public void setItemBackground(Drawable drawable) {
        this.mItemBackgroundResource = 0;
        this.mItemBackground = drawable;
        invalidate();
    }

    public void setCursorWidth(int i) {
        this.mCursorWidth = i;
        if (isCursorVisible()) {
            invalidateCursor(true);
        }
    }

    public int getCursorWidth() {
        return this.mCursorWidth;
    }

    public void setCursorColor(int i) {
        this.mCursorColor = i;
        if (isCursorVisible()) {
            invalidateCursor(true);
        }
    }

    public int getCursorColor() {
        return this.mCursorColor;
    }

    public void setCursorVisible(boolean z) {
        if (this.isCursorVisible != z) {
            this.isCursorVisible = z;
            invalidateCursor(z);
            makeBlink();
        }
    }

    public boolean isCursorVisible() {
        return this.isCursorVisible;
    }

    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        if (i == 0) {
            suspendBlink();
        } else if (i == 1) {
            resumeBlink();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        resumeBlink();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        suspendBlink();
    }

    /* access modifiers changed from: private */
    public boolean shouldBlink() {
        return isCursorVisible() && isFocused();
    }

    private void makeBlink() {
        if (shouldBlink()) {
            if (this.mBlink == null) {
                this.mBlink = new Blink();
            }
            removeCallbacks(this.mBlink);
            this.drawCursor = false;
            postDelayed(this.mBlink, 500);
            return;
        }
        Blink blink = this.mBlink;
        if (blink != null) {
            removeCallbacks(blink);
        }
    }

    private void suspendBlink() {
        Blink blink = this.mBlink;
        if (blink != null) {
            blink.cancel();
            invalidateCursor(false);
        }
    }

    private void resumeBlink() {
        Blink blink = this.mBlink;
        if (blink != null) {
            blink.uncancel();
            makeBlink();
        }
    }

    /* access modifiers changed from: private */
    public void invalidateCursor(boolean z) {
        if (this.drawCursor != z) {
            this.drawCursor = z;
            invalidate();
        }
    }

    private void updateCursorHeight() {
        float dpToPx = (float) (dpToPx(2.0f) * 2);
        this.mCursorHeight = ((float) this.mPinItemHeight) - getTextSize() > dpToPx ? getTextSize() + dpToPx : getTextSize();
    }

    private class Blink implements Runnable {
        private boolean mCancelled;

        private Blink() {
        }

        public void run() {
            if (!this.mCancelled) {
                PinView.this.removeCallbacks(this);
                if (PinView.this.shouldBlink()) {
                    PinView pinView = PinView.this;
                    pinView.invalidateCursor(!pinView.drawCursor);
                    PinView.this.postDelayed(this, 500);
                }
            }
        }

        /* access modifiers changed from: private */
        public void cancel() {
            if (!this.mCancelled) {
                PinView.this.removeCallbacks(this);
                this.mCancelled = true;
            }
        }

        /* access modifiers changed from: package-private */
        public void uncancel() {
            this.mCancelled = false;
        }
    }

    private void disableSelectionMenu() {
        setCustomSelectionActionModeCallback(new DefaultActionModeCallback());
        if (Build.VERSION.SDK_INT >= 26) {
            setCustomInsertionActionModeCallback(new DefaultActionModeCallback() {
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    menu.removeItem(16908355);
                    return true;
                }
            });
        }
    }

    private int dpToPx(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }

    private static class DefaultActionModeCallback implements ActionMode.Callback {
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        public void onDestroyActionMode(ActionMode actionMode) {
        }

        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        private DefaultActionModeCallback() {
        }
    }
}
