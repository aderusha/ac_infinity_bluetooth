package com.jakewharton.rxbinding2.widget;

import android.widget.Adapter;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.Preconditions;

public final class RxAdapter {
    public static <T extends Adapter> InitialValueObservable<T> dataChanges(T t) {
        Preconditions.checkNotNull(t, "adapter == null");
        return new AdapterDataChangeObservable(t);
    }

    private RxAdapter() {
        throw new AssertionError("No instances.");
    }
}
