package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;

public abstract class AdapterViewItemLongClickEvent {
    public abstract View clickedView();

    /* renamed from: id */
    public abstract long mo23683id();

    public abstract int position();

    public abstract AdapterView<?> view();

    public static AdapterViewItemLongClickEvent create(AdapterView<?> adapterView, View view, int i, long j) {
        return new AutoValue_AdapterViewItemLongClickEvent(adapterView, view, i, j);
    }

    AdapterViewItemLongClickEvent() {
    }
}
