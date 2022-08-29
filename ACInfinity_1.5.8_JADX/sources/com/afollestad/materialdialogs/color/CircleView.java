package com.afollestad.materialdialogs.color;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.Toast;
import androidx.core.view.ViewCompat;
import com.afollestad.materialdialogs.util.DialogUtils;

public class CircleView extends FrameLayout {
    private final int borderWidthLarge;
    private final int borderWidthSmall;
    private final Paint innerPaint;
    private final Paint outerPaint;
    private boolean selected;
    private final Paint whitePaint;

    public CircleView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    public CircleView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Resources resources = getResources();
        this.borderWidthSmall = (int) TypedValue.applyDimension(1, 3.0f, resources.getDisplayMetrics());
        this.borderWidthLarge = (int) TypedValue.applyDimension(1, 5.0f, resources.getDisplayMetrics());
        Paint paint = new Paint();
        this.whitePaint = paint;
        paint.setAntiAlias(true);
        paint.setColor(-1);
        Paint paint2 = new Paint();
        this.innerPaint = paint2;
        paint2.setAntiAlias(true);
        Paint paint3 = new Paint();
        this.outerPaint = paint3;
        paint3.setAntiAlias(true);
        update(-12303292);
        setWillNotDraw(false);
    }

    private static int translucentColor(int i) {
        return Color.argb(Math.round(((float) Color.alpha(i)) * 0.7f), Color.red(i), Color.green(i), Color.blue(i));
    }

    public static int shiftColor(int i, float f) {
        if (f == 1.0f) {
            return i;
        }
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] * f;
        return Color.HSVToColor(fArr);
    }

    public static int shiftColorDown(int i) {
        return shiftColor(i, 0.9f);
    }

    public static int shiftColorUp(int i) {
        return shiftColor(i, 1.1f);
    }

    private void update(int i) {
        this.innerPaint.setColor(i);
        this.outerPaint.setColor(shiftColorDown(i));
        Drawable createSelector = createSelector(i);
        if (Build.VERSION.SDK_INT >= 21) {
            setForeground(new RippleDrawable(new ColorStateList(new int[][]{new int[]{16842919}}, new int[]{shiftColorUp(i)}), createSelector, (Drawable) null));
            return;
        }
        setForeground(createSelector);
    }

    public void setBackgroundColor(int i) {
        update(i);
        requestLayout();
        invalidate();
    }

    public void setBackgroundResource(int i) {
        setBackgroundColor(DialogUtils.getColor(getContext(), i));
    }

    @Deprecated
    public void setBackground(Drawable drawable) {
        throw new IllegalStateException("Cannot use setBackground() on CircleView.");
    }

    @Deprecated
    public void setBackgroundDrawable(Drawable drawable) {
        throw new IllegalStateException("Cannot use setBackgroundDrawable() on CircleView.");
    }

    @Deprecated
    public void setActivated(boolean z) {
        throw new IllegalStateException("Cannot use setActivated() on CircleView.");
    }

    public void setSelected(boolean z) {
        this.selected = z;
        requestLayout();
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth() / 2;
        if (this.selected) {
            int i = measuredWidth - this.borderWidthLarge;
            int i2 = i - this.borderWidthSmall;
            canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) measuredWidth, this.outerPaint);
            canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) i, this.whitePaint);
            canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) i2, this.innerPaint);
            return;
        }
        canvas.drawCircle((float) (getMeasuredWidth() / 2), (float) (getMeasuredHeight() / 2), (float) measuredWidth, this.innerPaint);
    }

    private Drawable createSelector(int i) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setColor(translucentColor(shiftColorUp(i)));
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, shapeDrawable);
        return stateListDrawable;
    }

    public void showHint(int i) {
        int[] iArr = new int[2];
        Rect rect = new Rect();
        getLocationOnScreen(iArr);
        getWindowVisibleDisplayFrame(rect);
        Context context = getContext();
        int width = getWidth();
        int height = getHeight();
        int i2 = iArr[1] + (height / 2);
        int i3 = iArr[0] + (width / 2);
        if (ViewCompat.getLayoutDirection(this) == 0) {
            i3 = context.getResources().getDisplayMetrics().widthPixels - i3;
        }
        Toast makeText = Toast.makeText(context, String.format("#%06X", new Object[]{Integer.valueOf(i & ViewCompat.MEASURED_SIZE_MASK)}), 0);
        if (i2 < rect.height()) {
            makeText.setGravity(8388661, i3, (iArr[1] + height) - rect.top);
        } else {
            makeText.setGravity(81, 0, height);
        }
        makeText.show();
    }
}
