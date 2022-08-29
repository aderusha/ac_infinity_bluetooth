package com.jakewharton.rxbinding2.view;

import android.view.View;
import android.view.ViewGroup;

public abstract class ViewGroupHierarchyChildViewRemoveEvent extends ViewGroupHierarchyChangeEvent {
    public static ViewGroupHierarchyChildViewRemoveEvent create(ViewGroup viewGroup, View view) {
        return new AutoValue_ViewGroupHierarchyChildViewRemoveEvent(viewGroup, view);
    }

    ViewGroupHierarchyChildViewRemoveEvent() {
    }
}
