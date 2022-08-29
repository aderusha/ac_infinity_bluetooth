package com.zhihu.matisse.internal.p012ui.widget;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* renamed from: com.zhihu.matisse.internal.ui.widget.MediaGridInset */
public class MediaGridInset extends RecyclerView.ItemDecoration {
    private boolean mIncludeEdge;
    private int mSpacing;
    private int mSpanCount;

    public MediaGridInset(int i, int i2, boolean z) {
        this.mSpanCount = i;
        this.mSpacing = i2;
        this.mIncludeEdge = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = this.mSpanCount;
        int i2 = childAdapterPosition % i;
        if (this.mIncludeEdge) {
            int i3 = this.mSpacing;
            rect.left = i3 - ((i2 * i3) / i);
            rect.right = ((i2 + 1) * this.mSpacing) / this.mSpanCount;
            if (childAdapterPosition < this.mSpanCount) {
                rect.top = this.mSpacing;
            }
            rect.bottom = this.mSpacing;
            return;
        }
        rect.left = (this.mSpacing * i2) / i;
        int i4 = this.mSpacing;
        rect.right = i4 - (((i2 + 1) * i4) / this.mSpanCount);
        if (childAdapterPosition >= this.mSpanCount) {
            rect.top = this.mSpacing;
        }
    }
}
