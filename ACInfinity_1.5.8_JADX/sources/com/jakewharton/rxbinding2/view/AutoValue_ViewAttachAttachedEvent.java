package com.jakewharton.rxbinding2.view;

import android.view.View;
import java.util.Objects;

final class AutoValue_ViewAttachAttachedEvent extends ViewAttachAttachedEvent {
    private final View view;

    AutoValue_ViewAttachAttachedEvent(View view2) {
        Objects.requireNonNull(view2, "Null view");
        this.view = view2;
    }

    public View view() {
        return this.view;
    }

    public String toString() {
        return "ViewAttachAttachedEvent{view=" + this.view + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ViewAttachAttachedEvent) {
            return this.view.equals(((ViewAttachAttachedEvent) obj).view());
        }
        return false;
    }

    public int hashCode() {
        return this.view.hashCode() ^ 1000003;
    }
}
