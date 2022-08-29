package com.eternal.framework.binding.viewadapter.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class DividerLine extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {16843284};
    private static final int DEFAULT_DIVIDER_SIZE = 1;
    private static final String TAG = "com.eternal.framework.binding.viewadapter.recyclerview.DividerLine";
    private Drawable dividerDrawable;
    private int dividerSize;
    private Context mContext;
    private LineDrawMode mMode;

    public enum LineDrawMode {
        HORIZONTAL,
        VERTICAL,
        BOTH
    }

    public DividerLine(Context context) {
        this.mMode = null;
        this.mContext = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(ATTRS);
        this.dividerDrawable = obtainStyledAttributes.getDrawable(0);
        obtainStyledAttributes.recycle();
    }

    public DividerLine(Context context, LineDrawMode lineDrawMode) {
        this(context);
        this.mMode = lineDrawMode;
    }

    public DividerLine(Context context, int i, LineDrawMode lineDrawMode) {
        this(context, lineDrawMode);
        this.dividerSize = i;
    }

    public int getDividerSize() {
        return this.dividerSize;
    }

    public void setDividerSize(int i) {
        this.dividerSize = i;
    }

    public LineDrawMode getMode() {
        return this.mMode;
    }

    public void setMode(LineDrawMode lineDrawMode) {
        this.mMode = lineDrawMode;
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        super.onDrawOver(canvas, recyclerView, state);
        if (getMode() != null) {
            int i = C21811.f184x290e66e9[getMode().ordinal()];
            if (i == 1) {
                drawVertical(canvas, recyclerView, state);
            } else if (i == 2) {
                drawHorizontal(canvas, recyclerView, state);
            } else if (i == 3) {
                drawHorizontal(canvas, recyclerView, state);
                drawVertical(canvas, recyclerView, state);
            }
        } else {
            throw new IllegalStateException("assign LineDrawMode,please!");
        }
    }

    /* renamed from: com.eternal.framework.binding.viewadapter.recyclerview.DividerLine$1 */
    static /* synthetic */ class C21811 {

        /* renamed from: $SwitchMap$com$eternal$framework$binding$viewadapter$recyclerview$DividerLine$LineDrawMode */
        static final /* synthetic */ int[] f184x290e66e9;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.eternal.framework.binding.viewadapter.recyclerview.DividerLine$LineDrawMode[] r0 = com.eternal.framework.binding.viewadapter.recyclerview.DividerLine.LineDrawMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f184x290e66e9 = r0
                com.eternal.framework.binding.viewadapter.recyclerview.DividerLine$LineDrawMode r1 = com.eternal.framework.binding.viewadapter.recyclerview.DividerLine.LineDrawMode.VERTICAL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f184x290e66e9     // Catch:{ NoSuchFieldError -> 0x001d }
                com.eternal.framework.binding.viewadapter.recyclerview.DividerLine$LineDrawMode r1 = com.eternal.framework.binding.viewadapter.recyclerview.DividerLine.LineDrawMode.HORIZONTAL     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f184x290e66e9     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.eternal.framework.binding.viewadapter.recyclerview.DividerLine$LineDrawMode r1 = com.eternal.framework.binding.viewadapter.recyclerview.DividerLine.LineDrawMode.BOTH     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.eternal.framework.binding.viewadapter.recyclerview.DividerLine.C21811.<clinit>():void");
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int top = childAt.getTop() - layoutParams.topMargin;
            int bottom = childAt.getBottom() + layoutParams.bottomMargin;
            int right = childAt.getRight() + layoutParams.rightMargin;
            this.dividerDrawable.setBounds(right, top, (getDividerSize() == 0 ? dip2px(this.mContext, 1.0f) : getDividerSize()) + right, bottom);
            this.dividerDrawable.draw(canvas);
        }
    }

    private void drawHorizontal(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int left = childAt.getLeft() - layoutParams.leftMargin;
            int bottom = childAt.getBottom() + layoutParams.topMargin;
            this.dividerDrawable.setBounds(left, bottom, childAt.getRight() - layoutParams.rightMargin, (getDividerSize() == 0 ? dip2px(this.mContext, 1.0f) : getDividerSize()) + bottom);
            this.dividerDrawable.draw(canvas);
        }
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }
}
