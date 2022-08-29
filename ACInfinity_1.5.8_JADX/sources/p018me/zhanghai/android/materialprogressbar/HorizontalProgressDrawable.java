package p018me.zhanghai.android.materialprogressbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

/* renamed from: me.zhanghai.android.materialprogressbar.HorizontalProgressDrawable */
public class HorizontalProgressDrawable extends BaseProgressLayerDrawable<SingleHorizontalProgressDrawable, HorizontalProgressBackgroundDrawable> {
    public /* bridge */ /* synthetic */ boolean getShowBackground() {
        return super.getShowBackground();
    }

    public /* bridge */ /* synthetic */ boolean getUseIntrinsicPadding() {
        return super.getUseIntrinsicPadding();
    }

    public /* bridge */ /* synthetic */ void setShowBackground(boolean z) {
        super.setShowBackground(z);
    }

    public /* bridge */ /* synthetic */ void setTint(int i) {
        super.setTint(i);
    }

    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    public /* bridge */ /* synthetic */ void setUseIntrinsicPadding(boolean z) {
        super.setUseIntrinsicPadding(z);
    }

    public HorizontalProgressDrawable(Context context) {
        super(new Drawable[]{new HorizontalProgressBackgroundDrawable(context), new SingleHorizontalProgressDrawable(context), new SingleHorizontalProgressDrawable(context)}, context);
    }
}
