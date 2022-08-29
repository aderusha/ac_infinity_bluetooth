package com.jakewharton.rxbinding2.view;

import android.view.View;
import android.view.ViewGroup;

public abstract class ViewGroupHierarchyChangeEvent {
    public abstract View child();

    public abstract ViewGroup view();

    ViewGroupHierarchyChangeEvent() {
    }
}
