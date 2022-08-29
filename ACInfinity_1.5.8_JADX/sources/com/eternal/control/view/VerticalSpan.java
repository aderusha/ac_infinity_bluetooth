package com.eternal.control.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import android.util.Log;

public class VerticalSpan extends ReplacementSpan {
    private int fontSizeSp = -1;

    public VerticalSpan() {
    }

    public VerticalSpan(int i) {
        this.fontSizeSp = i;
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return (int) getCustomTextPaint(paint).measureText(charSequence, i, i2);
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        TextPaint customTextPaint = getCustomTextPaint(paint);
        Paint.FontMetricsInt fontMetricsInt = customTextPaint.getFontMetricsInt();
        int i6 = (((fontMetricsInt.ascent + i4) + i4) + fontMetricsInt.descent) - (i3 + i5);
        Log.d("VerticalAlignTextSpan", "offsetY-> " + i6);
        canvas.drawText(charSequence, i, i2, f, (float) (i4 - i6), customTextPaint);
    }

    private TextPaint getCustomTextPaint(Paint paint) {
        TextPaint textPaint = new TextPaint(paint);
        int i = this.fontSizeSp;
        if (i != -1) {
            textPaint.setTextSize(((float) i) * textPaint.density);
        }
        return textPaint;
    }
}
