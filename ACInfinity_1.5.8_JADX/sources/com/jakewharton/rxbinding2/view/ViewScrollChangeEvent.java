package com.jakewharton.rxbinding2.view;

import android.view.View;

public abstract class ViewScrollChangeEvent {
    public abstract int oldScrollX();

    public abstract int oldScrollY();

    public abstract int scrollX();

    public abstract int scrollY();

    public abstract View view();

    public static ViewScrollChangeEvent create(View view, int i, int i2, int i3, int i4) {
        return new AutoValue_ViewScrollChangeEvent(view, i, i2, i3, i4);
    }

    ViewScrollChangeEvent() {
    }
}
