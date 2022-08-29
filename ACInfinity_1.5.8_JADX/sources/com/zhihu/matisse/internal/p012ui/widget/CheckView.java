package com.zhihu.matisse.internal.p012ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.res.ResourcesCompat;
import com.zhihu.matisse.C3757R;

/* renamed from: com.zhihu.matisse.internal.ui.widget.CheckView */
public class CheckView extends View {
    private static final float BG_RADIUS = 11.0f;
    private static final int CONTENT_SIZE = 16;
    private static final float SHADOW_WIDTH = 6.0f;
    private static final int SIZE = 48;
    private static final float STROKE_RADIUS = 11.5f;
    private static final float STROKE_WIDTH = 3.0f;
    public static final int UNCHECKED = Integer.MIN_VALUE;
    private Paint mBackgroundPaint;
    private Drawable mCheckDrawable;
    private Rect mCheckRect;
    private boolean mChecked;
    private int mCheckedNum;
    private boolean mCountable;
    private float mDensity;
    private boolean mEnabled = true;
    private Paint mShadowPaint;
    private Paint mStrokePaint;
    private TextPaint mTextPaint;

    public CheckView(Context context) {
        super(context);
        init(context);
    }

    public CheckView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public CheckView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (this.mDensity * 48.0f), 1073741824);
        super.onMeasure(makeMeasureSpec, makeMeasureSpec);
    }

    private void init(Context context) {
        this.mDensity = context.getResources().getDisplayMetrics().density;
        Paint paint = new Paint();
        this.mStrokePaint = paint;
        paint.setAntiAlias(true);
        this.mStrokePaint.setStyle(Paint.Style.STROKE);
        this.mStrokePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        this.mStrokePaint.setStrokeWidth(this.mDensity * STROKE_WIDTH);
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{C3757R.attr.item_checkCircle_borderColor});
        int color = obtainStyledAttributes.getColor(0, ResourcesCompat.getColor(getResources(), C3757R.C3758color.zhihu_item_checkCircle_borderColor, getContext().getTheme()));
        obtainStyledAttributes.recycle();
        this.mStrokePaint.setColor(color);
        this.mCheckDrawable = ResourcesCompat.getDrawable(context.getResources(), C3757R.C3759drawable.ic_check_white_18dp, context.getTheme());
    }

    public void setChecked(boolean z) {
        if (!this.mCountable) {
            this.mChecked = z;
            invalidate();
            return;
        }
        throw new IllegalStateException("CheckView is countable, call setCheckedNum() instead.");
    }

    public void setCountable(boolean z) {
        this.mCountable = z;
    }

    public void setCheckedNum(int i) {
        if (!this.mCountable) {
            throw new IllegalStateException("CheckView is not countable, call setChecked() instead.");
        } else if (i == Integer.MIN_VALUE || i > 0) {
            this.mCheckedNum = i;
            invalidate();
        } else {
            throw new IllegalArgumentException("checked num can't be negative.");
        }
    }

    public void setEnabled(boolean z) {
        if (this.mEnabled != z) {
            this.mEnabled = z;
            invalidate();
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initShadowPaint();
        float f = this.mDensity;
        canvas.drawCircle((f * 48.0f) / 2.0f, (f * 48.0f) / 2.0f, f * 19.0f, this.mShadowPaint);
        float f2 = this.mDensity;
        canvas.drawCircle((f2 * 48.0f) / 2.0f, (f2 * 48.0f) / 2.0f, f2 * STROKE_RADIUS, this.mStrokePaint);
        if (this.mCountable) {
            if (this.mCheckedNum != Integer.MIN_VALUE) {
                initBackgroundPaint();
                float f3 = this.mDensity;
                canvas.drawCircle((f3 * 48.0f) / 2.0f, (48.0f * f3) / 2.0f, f3 * BG_RADIUS, this.mBackgroundPaint);
                initTextPaint();
                String valueOf = String.valueOf(this.mCheckedNum);
                canvas.drawText(valueOf, (float) (((int) (((float) canvas.getWidth()) - this.mTextPaint.measureText(valueOf))) / 2), (float) (((int) ((((float) canvas.getHeight()) - this.mTextPaint.descent()) - this.mTextPaint.ascent())) / 2), this.mTextPaint);
            }
        } else if (this.mChecked) {
            initBackgroundPaint();
            float f4 = this.mDensity;
            canvas.drawCircle((f4 * 48.0f) / 2.0f, (48.0f * f4) / 2.0f, f4 * BG_RADIUS, this.mBackgroundPaint);
            this.mCheckDrawable.setBounds(getCheckRect());
            this.mCheckDrawable.draw(canvas);
        }
        setAlpha(this.mEnabled ? 1.0f : 0.5f);
    }

    private void initShadowPaint() {
        if (this.mShadowPaint == null) {
            Paint paint = new Paint();
            this.mShadowPaint = paint;
            paint.setAntiAlias(true);
            Paint paint2 = this.mShadowPaint;
            float f = this.mDensity;
            float[] fArr = {0.21052632f, 0.5263158f, 0.68421054f, 1.0f};
            float f2 = (f * 48.0f) / 2.0f;
            float f3 = (48.0f * f) / 2.0f;
            float f4 = 19.0f * f;
            paint2.setShader(new RadialGradient(f2, f3, f4, new int[]{Color.parseColor("#00000000"), Color.parseColor("#0D000000"), Color.parseColor("#0D000000"), Color.parseColor("#00000000")}, fArr, Shader.TileMode.CLAMP));
        }
    }

    private void initBackgroundPaint() {
        if (this.mBackgroundPaint == null) {
            Paint paint = new Paint();
            this.mBackgroundPaint = paint;
            paint.setAntiAlias(true);
            this.mBackgroundPaint.setStyle(Paint.Style.FILL);
            TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(new int[]{C3757R.attr.item_checkCircle_backgroundColor});
            int color = obtainStyledAttributes.getColor(0, ResourcesCompat.getColor(getResources(), C3757R.C3758color.zhihu_item_checkCircle_backgroundColor, getContext().getTheme()));
            obtainStyledAttributes.recycle();
            this.mBackgroundPaint.setColor(color);
        }
    }

    private void initTextPaint() {
        if (this.mTextPaint == null) {
            TextPaint textPaint = new TextPaint();
            this.mTextPaint = textPaint;
            textPaint.setAntiAlias(true);
            this.mTextPaint.setColor(-1);
            this.mTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
            this.mTextPaint.setTextSize(this.mDensity * 12.0f);
        }
    }

    private Rect getCheckRect() {
        if (this.mCheckRect == null) {
            float f = this.mDensity;
            int i = (int) (((f * 48.0f) / 2.0f) - ((f * 16.0f) / 2.0f));
            float f2 = this.mDensity;
            float f3 = (float) i;
            this.mCheckRect = new Rect(i, i, (int) ((f2 * 48.0f) - f3), (int) ((f2 * 48.0f) - f3));
        }
        return this.mCheckRect;
    }
}
