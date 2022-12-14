package p018me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.graphics.Canvas;

/* renamed from: me.zhanghai.android.materialprogressbar.HorizontalProgressBackgroundDrawable */
class HorizontalProgressBackgroundDrawable extends BaseSingleHorizontalProgressDrawable implements ShowBackgroundDrawable {
    private boolean mShow = true;

    public HorizontalProgressBackgroundDrawable(Context context) {
        super(context);
    }

    public boolean getShowBackground() {
        return this.mShow;
    }

    public void setShowBackground(boolean z) {
        if (this.mShow != z) {
            this.mShow = z;
            invalidateSelf();
        }
    }

    public void draw(Canvas canvas) {
        if (this.mShow) {
            super.draw(canvas);
        }
    }
}
