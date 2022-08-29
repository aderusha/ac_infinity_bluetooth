package com.eternal.widget;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;

public class ColorSpan extends CharacterStyle implements UpdateAppearance {
    private int[] colors;
    private Shader shader;

    public ColorSpan(int... iArr) {
        if (iArr.length == 0) {
            this.colors = new int[]{-15561748, -14833167, -14038537};
        } else {
            this.colors = iArr;
        }
    }

    public void updateDrawState(TextPaint textPaint) {
        if (this.shader == null) {
            this.shader = new LinearGradient(0.0f, 0.0f, 0.0f, textPaint.descent() - textPaint.ascent(), this.colors, (float[]) null, Shader.TileMode.REPEAT);
        }
        textPaint.setShader(this.shader);
    }
}
