package com.eternal.widget;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.widget.MarkItemTouchHelper;

public class MarkItemCallBack extends MarkItemTouchHelper.Callback {
    private int back;
    private int front;
    private boolean isCanSwipe;

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return true;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
    }

    public MarkItemCallBack(int i, int i2) {
        this.front = i;
        this.back = i2;
    }

    public MarkItemCallBack setCanSwipe(boolean z) {
        this.isCanSwipe = z;
        return this;
    }

    public boolean isItemViewSwipeEnabled() {
        return this.isCanSwipe;
    }

    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        int i = 3;
        int i2 = 0;
        if (layoutManager instanceof GridLayoutManager) {
            i = 15;
        } else if (!(layoutManager instanceof LinearLayoutManager)) {
            i = 0;
        } else if (((LinearLayoutManager) layoutManager).getOrientation() == 1) {
            i2 = 4;
        } else {
            i = 12;
            i2 = 3;
        }
        return makeMovementFlags(i, i2);
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        if (i == 1) {
            int width = viewHolder.itemView.findViewById(this.back).getWidth();
            View findViewById = viewHolder.itemView.findViewById(this.front);
            if (findViewById != null) {
                float f3 = (float) (-width);
                if (f < f3) {
                    findViewById.setTranslationX(f3);
                } else {
                    findViewById.setTranslationX(f);
                }
            }
        } else {
            super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
        }
    }
}
