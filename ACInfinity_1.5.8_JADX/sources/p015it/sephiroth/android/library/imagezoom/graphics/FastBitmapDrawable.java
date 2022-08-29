package p015it.sephiroth.android.library.imagezoom.graphics;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import java.io.InputStream;

/* renamed from: it.sephiroth.android.library.imagezoom.graphics.FastBitmapDrawable */
public class FastBitmapDrawable extends Drawable implements IBitmapDrawable {
    protected Bitmap mBitmap;
    protected int mIntrinsicHeight;
    protected int mIntrinsicWidth;
    protected Paint mPaint;

    public int getOpacity() {
        return -3;
    }

    public FastBitmapDrawable(Bitmap bitmap) {
        this.mBitmap = bitmap;
        if (bitmap != null) {
            this.mIntrinsicWidth = bitmap.getWidth();
            this.mIntrinsicHeight = this.mBitmap.getHeight();
        } else {
            this.mIntrinsicWidth = 0;
            this.mIntrinsicHeight = 0;
        }
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setDither(true);
        this.mPaint.setFilterBitmap(true);
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public FastBitmapDrawable(Resources resources, InputStream inputStream) {
        this(BitmapFactory.decodeStream(inputStream));
    }

    public void draw(Canvas canvas) {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && !bitmap.isRecycled()) {
            canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, this.mPaint);
        }
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public int getIntrinsicWidth() {
        return this.mIntrinsicWidth;
    }

    public int getIntrinsicHeight() {
        return this.mIntrinsicHeight;
    }

    public int getMinimumWidth() {
        return this.mIntrinsicWidth;
    }

    public int getMinimumHeight() {
        return this.mIntrinsicHeight;
    }

    public void setAntiAlias(boolean z) {
        this.mPaint.setAntiAlias(z);
        invalidateSelf();
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public Paint getPaint() {
        return this.mPaint;
    }
}
