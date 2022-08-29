package com.jakewharton.rxbinding2.view;

import android.view.View;

public abstract class ViewLayoutChangeEvent {
    public abstract int bottom();

    public abstract int left();

    public abstract int oldBottom();

    public abstract int oldLeft();

    public abstract int oldRight();

    public abstract int oldTop();

    public abstract int right();

    public abstract int top();

    public abstract View view();

    public static ViewLayoutChangeEvent create(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return new AutoValue_ViewLayoutChangeEvent(view, i, i2, i3, i4, i5, i6, i7, i8);
    }

    ViewLayoutChangeEvent() {
    }
}
