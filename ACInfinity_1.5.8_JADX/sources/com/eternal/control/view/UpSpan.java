package com.eternal.control.view;

import android.graphics.Paint;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import com.eternal.framework.utils.ConvertUtils;

public class UpSpan extends MetricAffectingSpan {
    private float size;

    public UpSpan(float f) {
        this.size = (float) ConvertUtils.sp2px(f);
    }

    public void updateMeasureState(TextPaint textPaint) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        textPaint.setTextSize(this.size);
        Paint.FontMetrics fontMetrics2 = textPaint.getFontMetrics();
        textPaint.baselineShift = (int) (((float) textPaint.baselineShift) + (((fontMetrics.top - fontMetrics.bottom) - (fontMetrics2.top - fontMetrics2.bottom)) / 2.0f));
    }

    public void updateDrawState(TextPaint textPaint) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        textPaint.setTextSize(this.size);
        Paint.FontMetrics fontMetrics2 = textPaint.getFontMetrics();
        textPaint.baselineShift = (int) (((float) textPaint.baselineShift) + (((fontMetrics.top - fontMetrics.bottom) - (fontMetrics2.top - fontMetrics2.bottom)) / 2.0f));
    }
}
