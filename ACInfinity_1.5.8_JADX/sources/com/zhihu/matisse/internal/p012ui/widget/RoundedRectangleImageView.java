package com.zhihu.matisse.internal.p012ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* renamed from: com.zhihu.matisse.internal.ui.widget.RoundedRectangleImageView */
public class RoundedRectangleImageView extends AppCompatImageView {
    private float mRadius;
    private RectF mRectF;
    private Path mRoundedRectPath;

    public RoundedRectangleImageView(Context context) {
        super(context);
        init(context);
    }

    public RoundedRectangleImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public RoundedRectangleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    private void init(Context context) {
        this.mRadius = context.getResources().getDisplayMetrics().density * 2.0f;
        this.mRoundedRectPath = new Path();
        this.mRectF = new RectF();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mRectF.set(0.0f, 0.0f, (float) getMeasuredWidth(), (float) getMeasuredHeight());
        Path path = this.mRoundedRectPath;
        RectF rectF = this.mRectF;
        float f = this.mRadius;
        path.addRoundRect(rectF, f, f, Path.Direction.CW);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        canvas.clipPath(this.mRoundedRectPath);
        super.onDraw(canvas);
    }
}
