package com.eternal.control.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.eternal.control.C1760R;
import com.eternal.framework.utils.ConvertUtils;

public class CycleView extends View {
    private static final int SHADER = 8;
    private Bitmap background;
    private int center;
    private int maxDistance;
    private int midDistance;
    private int midStroke;
    private int minRadius;
    private Paint paint;
    private int size;

    public CycleView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CycleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CycleView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public CycleView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.minRadius = 67;
        this.midDistance = 11;
        this.midStroke = 40;
        this.maxDistance = 26;
        initPaint();
    }

    private void initPaint() {
        this.paint = new Paint(5);
    }

    private void initBackground() {
        Paint paint2 = new Paint(5);
        paint2.setStrokeWidth((float) ConvertUtils.dp2px(1.0f));
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(-1);
        paint2.setMaskFilter(new BlurMaskFilter(8.0f, BlurMaskFilter.Blur.OUTER));
        int dp2px = ConvertUtils.dp2px((float) (this.minRadius + this.midDistance + this.midStroke + this.maxDistance));
        int i = this.size;
        this.background = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.background);
        float f = (float) (this.size / 2);
        canvas.drawCircle(f, f, (float) dp2px, paint2);
        paint2.setMaskFilter((MaskFilter) null);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth((float) ConvertUtils.dp2px(40.0f));
        paint2.setShader(new LinearGradient((float) ConvertUtils.dp2px(50.0f), 0.0f, (float) (this.size - ConvertUtils.dp2px(50.0f)), 0.0f, new int[]{getResources().getColor(C1760R.C1761color.color_21409A), getResources().getColor(C1760R.C1761color.color_00B8EC)}, (float[]) null, Shader.TileMode.CLAMP));
        int dp2px2 = ConvertUtils.dp2px((float) this.midStroke) / 2;
        int dp2px3 = ConvertUtils.dp2px((float) (this.minRadius + this.midDistance)) + dp2px2;
        canvas.drawCircle(f, f, (float) dp2px3, paint2);
        paint2.setStrokeWidth((float) ConvertUtils.dp2px(2.0f));
        paint2.setColor(getResources().getColor(C1760R.C1761color.color_80000000));
        paint2.setShader((Shader) null);
        paint2.setMaskFilter(new BlurMaskFilter(20.0f, BlurMaskFilter.Blur.OUTER));
        canvas.drawCircle(f, f, (float) (dp2px3 + dp2px2 + ConvertUtils.dp2px(1.0f)), paint2);
        paint2.setColor(getResources().getColor(C1760R.C1761color.color_80FFFFFF));
        paint2.setStrokeWidth((float) ConvertUtils.dp2px(1.0f));
        paint2.setStyle(Paint.Style.FILL);
        canvas.drawCircle(f, f, (float) (dp2px3 - dp2px2), paint2);
        paint2.setMaskFilter((MaskFilter) null);
        paint2.setColor(getResources().getColor(C1760R.C1761color.color_292929));
        paint2.setStyle(Paint.Style.FILL);
        canvas.drawCircle(f, f, (float) ConvertUtils.dp2px((float) this.minRadius), paint2);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Bitmap bitmap = this.background;
        if (bitmap != null) {
            float height = (float) (this.center - (bitmap.getHeight() / 2));
            canvas.drawBitmap(this.background, height, height, this.paint);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int size2 = View.MeasureSpec.getSize(i);
        this.size = size2;
        setMeasuredDimension(size2, size2);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        int min = Math.min(i2, i);
        this.size = min;
        this.center = min / 2;
        initBackground();
    }
}
