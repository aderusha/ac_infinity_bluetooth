package com.zhihu.matisse.internal.p012ui.widget;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.res.ResourcesCompat;
import com.zhihu.matisse.C3757R;

/* renamed from: com.zhihu.matisse.internal.ui.widget.CheckRadioView */
public class CheckRadioView extends AppCompatImageView {
    private Drawable mDrawable;
    private int mSelectedColor;
    private int mUnSelectUdColor;

    public CheckRadioView(Context context) {
        super(context);
        init();
    }

    public CheckRadioView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        this.mSelectedColor = ResourcesCompat.getColor(getResources(), C3757R.C3758color.zhihu_item_checkCircle_backgroundColor, getContext().getTheme());
        this.mUnSelectUdColor = ResourcesCompat.getColor(getResources(), C3757R.C3758color.zhihu_check_original_radio_disable, getContext().getTheme());
        setChecked(false);
    }

    public void setChecked(boolean z) {
        if (z) {
            setImageResource(C3757R.C3759drawable.ic_preview_radio_on);
            Drawable drawable = getDrawable();
            this.mDrawable = drawable;
            drawable.setColorFilter(this.mSelectedColor, PorterDuff.Mode.SRC_IN);
            return;
        }
        setImageResource(C3757R.C3759drawable.ic_preview_radio_off);
        Drawable drawable2 = getDrawable();
        this.mDrawable = drawable2;
        drawable2.setColorFilter(this.mUnSelectUdColor, PorterDuff.Mode.SRC_IN);
    }

    public void setColor(int i) {
        if (this.mDrawable == null) {
            this.mDrawable = getDrawable();
        }
        this.mDrawable.setColorFilter(i, PorterDuff.Mode.SRC_IN);
    }
}
