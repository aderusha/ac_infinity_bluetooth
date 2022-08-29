package com.eternal.widget;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.Layout;
import android.text.SpannableString;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;

public class NoSpaceTextView extends AppCompatTextView {
    private static final int[] COLORS = {-15561748, -14833167, -14038537};
    private int paddingTop;
    private boolean refreshMeasure;
    private ColorSpan span;

    public NoSpaceTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NoSpaceTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NoSpaceTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.refreshMeasure = true;
        this.paddingTop = getPaddingTop();
        this.span = new ColorSpan(COLORS);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        removeSpace(i, i2);
    }

    public void setText(String str, int[] iArr) {
        if (iArr != null) {
            this.span = new ColorSpan(iArr);
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(this.span, 0, spannableString.length(), 18);
        setText((CharSequence) spannableString, TextView.BufferType.SPANNABLE);
    }

    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        this.refreshMeasure = true;
    }

    private void removeSpace(int i, int i2) {
        String[] firstAndLastLines = getFirstAndLastLines();
        TextPaint paint = getPaint();
        Rect rect = new Rect();
        String str = firstAndLastLines[0];
        paint.getTextBounds(str, 0, str.length(), rect);
        Paint.FontMetricsInt fontMetricsInt = new Paint.FontMetricsInt();
        paint.getFontMetricsInt(fontMetricsInt);
        setPadding(getPaddingStart(), (fontMetricsInt.top - rect.top) + this.paddingTop, getPaddingEnd(), getPaddingBottom());
        String str2 = firstAndLastLines[1];
        paint.getTextBounds(str2, 0, str2.length(), rect);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight() - (fontMetricsInt.bottom - rect.bottom));
        if (this.refreshMeasure) {
            this.refreshMeasure = false;
            measure(i, i2);
        }
    }

    private String[] getFirstAndLastLines() {
        String charSequence = getText().toString();
        Layout layout = getLayout();
        return new String[]{charSequence.substring(0, layout.getLineEnd(0)), charSequence.substring(layout.getLineStart(layout.getLineCount() - 1))};
    }
}
