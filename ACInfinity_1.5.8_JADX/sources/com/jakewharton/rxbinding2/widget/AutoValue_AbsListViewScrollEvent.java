package com.jakewharton.rxbinding2.widget;

import android.widget.AbsListView;
import java.util.Objects;

final class AutoValue_AbsListViewScrollEvent extends AbsListViewScrollEvent {
    private final int firstVisibleItem;
    private final int scrollState;
    private final int totalItemCount;
    private final AbsListView view;
    private final int visibleItemCount;

    AutoValue_AbsListViewScrollEvent(AbsListView absListView, int i, int i2, int i3, int i4) {
        Objects.requireNonNull(absListView, "Null view");
        this.view = absListView;
        this.scrollState = i;
        this.firstVisibleItem = i2;
        this.visibleItemCount = i3;
        this.totalItemCount = i4;
    }

    public AbsListView view() {
        return this.view;
    }

    public int scrollState() {
        return this.scrollState;
    }

    public int firstVisibleItem() {
        return this.firstVisibleItem;
    }

    public int visibleItemCount() {
        return this.visibleItemCount;
    }

    public int totalItemCount() {
        return this.totalItemCount;
    }

    public String toString() {
        return "AbsListViewScrollEvent{view=" + this.view + ", scrollState=" + this.scrollState + ", firstVisibleItem=" + this.firstVisibleItem + ", visibleItemCount=" + this.visibleItemCount + ", totalItemCount=" + this.totalItemCount + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AbsListViewScrollEvent)) {
            return false;
        }
        AbsListViewScrollEvent absListViewScrollEvent = (AbsListViewScrollEvent) obj;
        if (this.view.equals(absListViewScrollEvent.view()) && this.scrollState == absListViewScrollEvent.scrollState() && this.firstVisibleItem == absListViewScrollEvent.firstVisibleItem() && this.visibleItemCount == absListViewScrollEvent.visibleItemCount() && this.totalItemCount == absListViewScrollEvent.totalItemCount()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.scrollState) * 1000003) ^ this.firstVisibleItem) * 1000003) ^ this.visibleItemCount) * 1000003) ^ this.totalItemCount;
    }
}
