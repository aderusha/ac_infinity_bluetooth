package com.jakewharton.rxbinding2.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

final class AutoValue_ViewGroupHierarchyChildViewRemoveEvent extends ViewGroupHierarchyChildViewRemoveEvent {
    private final View child;
    private final ViewGroup view;

    AutoValue_ViewGroupHierarchyChildViewRemoveEvent(ViewGroup viewGroup, View view2) {
        Objects.requireNonNull(viewGroup, "Null view");
        this.view = viewGroup;
        Objects.requireNonNull(view2, "Null child");
        this.child = view2;
    }

    public ViewGroup view() {
        return this.view;
    }

    public View child() {
        return this.child;
    }

    public String toString() {
        return "ViewGroupHierarchyChildViewRemoveEvent{view=" + this.view + ", child=" + this.child + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewGroupHierarchyChildViewRemoveEvent)) {
            return false;
        }
        ViewGroupHierarchyChildViewRemoveEvent viewGroupHierarchyChildViewRemoveEvent = (ViewGroupHierarchyChildViewRemoveEvent) obj;
        if (!this.view.equals(viewGroupHierarchyChildViewRemoveEvent.view()) || !this.child.equals(viewGroupHierarchyChildViewRemoveEvent.child())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.view.hashCode() ^ 1000003) * 1000003) ^ this.child.hashCode();
    }
}
