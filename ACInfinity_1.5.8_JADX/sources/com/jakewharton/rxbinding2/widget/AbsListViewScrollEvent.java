package com.jakewharton.rxbinding2.widget;

import android.widget.AbsListView;

public abstract class AbsListViewScrollEvent {
    public abstract int firstVisibleItem();

    public abstract int scrollState();

    public abstract int totalItemCount();

    public abstract AbsListView view();

    public abstract int visibleItemCount();

    public static AbsListViewScrollEvent create(AbsListView absListView, int i, int i2, int i3, int i4) {
        return new AutoValue_AbsListViewScrollEvent(absListView, i, i2, i3, i4);
    }

    AbsListViewScrollEvent() {
    }
}
