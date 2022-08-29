package com.eternal.advance;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    private static final String TAG = "CustomLinearLayoutManager";
    private int[] mMeasuredDimension = new int[2];

    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomLinearLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < getItemCount(); i5++) {
            measureScrapChild(recycler, i5, View.MeasureSpec.makeMeasureSpec(i5, 0), View.MeasureSpec.makeMeasureSpec(i5, 0), this.mMeasuredDimension);
            if (getOrientation() == 0) {
                int[] iArr = this.mMeasuredDimension;
                i4 += iArr[0];
                if (i5 == 0) {
                    i3 = iArr[1];
                }
            } else {
                int[] iArr2 = this.mMeasuredDimension;
                i3 += iArr2[1];
                if (i5 == 0) {
                    i4 = iArr2[0];
                }
            }
        }
        if (mode != 1073741824) {
            size = i4;
        }
        if (mode2 != 1073741824) {
            size2 = i3;
        }
        setMeasuredDimension(size, size2);
    }

    private void measureScrapChild(RecyclerView.Recycler recycler, int i, int i2, int i3, int[] iArr) {
        try {
            View viewForPosition = recycler.getViewForPosition(0);
            if (viewForPosition != null) {
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) viewForPosition.getLayoutParams();
                viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight(), layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom(), layoutParams.height));
                iArr[0] = viewForPosition.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
                iArr[1] = viewForPosition.getMeasuredHeight() + layoutParams.bottomMargin + layoutParams.topMargin;
                recycler.recycleView(viewForPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
