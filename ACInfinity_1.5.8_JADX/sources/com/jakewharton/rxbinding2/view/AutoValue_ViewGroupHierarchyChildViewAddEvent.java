package com.jakewharton.rxbinding2.view;

import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

final class AutoValue_ViewGroupHierarchyChildViewAddEvent extends ViewGroupHierarchyChildViewAddEvent {
    private final View child;
    private final ViewGroup view;

    AutoValue_ViewGroupHierarchyChildViewAddEvent(ViewGroup viewGroup, View view2) {
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
        return "ViewGroupHierarchyChildViewAddEvent{view=" + this.view + ", child=" + this.child + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewGroupHierarchyChildViewAddEvent)) {
            return false;
        }
        ViewGroupHierarchyChildViewAddEvent viewGroupHierarchyChildViewAddEvent = (ViewGroupHierarchyChildViewAddEvent) obj;
        if (!this.view.equals(viewGroupHierarchyChildViewAddEvent.view()) || !this.child.equals(viewGroupHierarchyChildViewAddEvent.child())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.view.hashCode() ^ 1000003) * 1000003) ^ this.child.hashCode();
    }
}
