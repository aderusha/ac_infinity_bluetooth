package com.zhihu.matisse.internal.p012ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import p015it.sephiroth.android.library.imagezoom.ImageViewTouch;

/* renamed from: com.zhihu.matisse.internal.ui.widget.PreviewViewPager */
public class PreviewViewPager extends ViewPager {
    public PreviewViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public boolean canScroll(View view, boolean z, int i, int i2, int i3) {
        if (view instanceof ImageViewTouch) {
            return ((ImageViewTouch) view).canScroll(i) || super.canScroll(view, z, i, i2, i3);
        }
        return super.canScroll(view, z, i, i2, i3);
    }
}
