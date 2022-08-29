package p018me.zhanghai.android.materialprogressbar;

import android.graphics.Canvas;
import android.graphics.Paint;

/* renamed from: me.zhanghai.android.materialprogressbar.BasePaintDrawable */
abstract class BasePaintDrawable extends BaseDrawable {
    private Paint mPaint;

    /* access modifiers changed from: protected */
    public abstract void onDraw(Canvas canvas, int i, int i2, Paint paint);

    /* access modifiers changed from: protected */
    public abstract void onPreparePaint(Paint paint);

    BasePaintDrawable() {
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas, int i, int i2) {
        if (this.mPaint == null) {
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setAntiAlias(true);
            this.mPaint.setColor(-16777216);
            onPreparePaint(this.mPaint);
        }
        this.mPaint.setAlpha(this.mAlpha);
        this.mPaint.setColorFilter(getColorFilterForDrawing());
        onDraw(canvas, i, i2, this.mPaint);
    }
}
