package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;

public abstract class AdapterViewItemClickEvent {
    public abstract View clickedView();

    /* renamed from: id */
    public abstract long mo23677id();

    public abstract int position();

    public abstract AdapterView<?> view();

    public static AdapterViewItemClickEvent create(AdapterView<?> adapterView, View view, int i, long j) {
        return new AutoValue_AdapterViewItemClickEvent(adapterView, view, i, j);
    }

    AdapterViewItemClickEvent() {
    }
}
