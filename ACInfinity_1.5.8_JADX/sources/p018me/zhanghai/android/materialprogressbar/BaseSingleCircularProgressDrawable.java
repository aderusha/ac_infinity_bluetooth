package p018me.zhanghai.android.materialprogressbar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/* renamed from: me.zhanghai.android.materialprogressbar.BaseSingleCircularProgressDrawable */
abstract class BaseSingleCircularProgressDrawable extends BaseProgressDrawable {
    private static final RectF RECT_BOUND = new RectF(-21.0f, -21.0f, 21.0f, 21.0f);
    private static final RectF RECT_PADDED_BOUND = new RectF(-24.0f, -24.0f, 24.0f, 24.0f);
    private static final RectF RECT_PROGRESS = new RectF(-19.0f, -19.0f, 19.0f, 19.0f);

    /* access modifiers changed from: protected */
    public abstract void onDrawRing(Canvas canvas, Paint paint);

    BaseSingleCircularProgressDrawable() {
    }

    /* access modifiers changed from: protected */
    public void onPreparePaint(Paint paint) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4.0f);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas, int i, int i2, Paint paint) {
        if (this.mUseIntrinsicPadding) {
            RectF rectF = RECT_PADDED_BOUND;
            canvas.scale(((float) i) / rectF.width(), ((float) i2) / rectF.height());
            canvas.translate(rectF.width() / 2.0f, rectF.height() / 2.0f);
        } else {
            RectF rectF2 = RECT_BOUND;
            canvas.scale(((float) i) / rectF2.width(), ((float) i2) / rectF2.height());
            canvas.translate(rectF2.width() / 2.0f, rectF2.height() / 2.0f);
        }
        onDrawRing(canvas, paint);
    }

    /* access modifiers changed from: protected */
    public void drawRing(Canvas canvas, Paint paint, float f, float f2) {
        canvas.drawArc(RECT_PROGRESS, f - 0.049804688f, f2, false, paint);
    }
}
