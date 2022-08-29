package com.eternal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;

public class NoBorderColorTextView extends AppCompatTextView {
    private int[] colors;
    private int mCenterColor;
    private int mDownColor;
    private Paint mPaint;
    private Rect mTextBound;
    private int mUpColor;
    private int mViewWidth;
    private Rect rect;

    public NoBorderColorTextView(Context context) {
        super(context);
        this.mViewWidth = 0;
        this.mTextBound = new Rect();
        this.colors = new int[3];
        this.rect = new Rect();
    }

    public NoBorderColorTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NoBorderColorTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mViewWidth = 0;
        this.mTextBound = new Rect();
        this.colors = new int[3];
        this.rect = new Rect();
        init(context, attributeSet);
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C2779R.styleable.NoBorderColorTextView);
        int color = obtainStyledAttributes.getColor(C2779R.styleable.NoBorderColorTextView_UpColor, -15561748);
        this.mUpColor = color;
        this.colors[0] = color;
        int color2 = obtainStyledAttributes.getColor(C2779R.styleable.NoBorderColorTextView_DownColor, -14038537);
        this.mDownColor = color2;
        this.colors[2] = color2;
        int color3 = obtainStyledAttributes.getColor(C2779R.styleable.NoBorderColorTextView_CenterColor, -14833167);
        this.mCenterColor = color3;
        this.colors[1] = color3;
        String string = obtainStyledAttributes.getString(C2779R.styleable.NoBorderColorTextView_Font);
        if (!TextUtils.isEmpty(string)) {
            setTypeface(Typeface.createFromAsset(context.getAssets(), string));
        }
        obtainStyledAttributes.recycle();
    }

    public void showTextColorByWhite(boolean z) {
        if (z) {
            this.colors[0] = ContextCompat.getColor(getContext(), 17170443);
            this.colors[1] = ContextCompat.getColor(getContext(), 17170443);
            this.colors[2] = ContextCompat.getColor(getContext(), 17170443);
        } else {
            int[] iArr = this.colors;
            iArr[0] = this.mUpColor;
            iArr[1] = this.mCenterColor;
            iArr[2] = this.mDownColor;
        }
        postInvalidate();
    }

    public void showTextColorByGray(boolean z) {
        if (z) {
            this.colors[0] = ContextCompat.getColor(getContext(), 17170443);
            this.colors[1] = ContextCompat.getColor(getContext(), 17170443);
            this.colors[2] = ContextCompat.getColor(getContext(), 17170443);
        } else {
            int[] iArr = this.colors;
            iArr[0] = this.mUpColor;
            iArr[1] = this.mCenterColor;
            iArr[2] = this.mDownColor;
        }
        postInvalidate();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.mViewWidth == 0) {
            int measuredWidth = getMeasuredWidth();
            this.mViewWidth = measuredWidth;
            if (measuredWidth > 0) {
                TextPaint paint = getPaint();
                this.mPaint = paint;
                Paint.FontMetrics fontMetrics = paint.getFontMetrics();
                float f = -(fontMetrics.top - fontMetrics.ascent);
                this.mPaint.getTextBounds(getText().toString(), 0, getText().toString().length(), this.mTextBound);
                this.mPaint.setShader(new LinearGradient(0.0f, f, 0.0f, ((float) this.mTextBound.height()) + f, this.colors, (float[]) null, Shader.TileMode.CLAMP));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        drawText(canvas);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        calculateTextParams();
        setMeasuredDimension(this.rect.right - this.rect.left, (-this.rect.top) + this.rect.bottom);
    }

    private String calculateTextParams() {
        String charSequence = getText().toString();
        int length = charSequence.length();
        TextPaint paint = getPaint();
        this.mPaint = paint;
        paint.getTextBounds(charSequence, 0, length, this.rect);
        if (length == 0) {
            Rect rect2 = this.rect;
            rect2.right = rect2.left;
        }
        return charSequence;
    }

    private void drawText(Canvas canvas) {
        String calculateTextParams = calculateTextParams();
        int i = this.rect.left;
        int i2 = this.rect.bottom;
        Rect rect2 = this.rect;
        rect2.offset(-rect2.left, -this.rect.top);
        TextPaint paint = getPaint();
        this.mPaint = paint;
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float f = -(fontMetrics.top - fontMetrics.ascent);
        this.mPaint.getTextBounds(getText().toString(), 0, getText().toString().length(), this.mTextBound);
        String.format("#%06X", new Object[]{Integer.valueOf(getCurrentTextColor() & ViewCompat.MEASURED_SIZE_MASK)});
        int[] iArr = this.colors;
        iArr[0] = this.mUpColor;
        iArr[1] = this.mCenterColor;
        iArr[2] = this.mDownColor;
        this.mPaint.setShader(new LinearGradient(0.0f, f, 0.0f, ((float) this.mTextBound.height()) + f, this.colors, (float[]) null, Shader.TileMode.CLAMP));
        canvas.drawText(calculateTextParams, (float) (-i), (float) (this.rect.bottom - i2), this.mPaint);
    }
}
