package com.jakewharton.rxbinding2.widget;

import android.widget.AdapterView;
import java.util.Objects;

final class AutoValue_AdapterViewNothingSelectionEvent extends AdapterViewNothingSelectionEvent {
    private final AdapterView<?> view;

    AutoValue_AdapterViewNothingSelectionEvent(AdapterView<?> adapterView) {
        Objects.requireNonNull(adapterView, "Null view");
        this.view = adapterView;
    }

    public AdapterView<?> view() {
        return this.view;
    }

    public String toString() {
        return "AdapterViewNothingSelectionEvent{view=" + this.view + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AdapterViewNothingSelectionEvent) {
            return this.view.equals(((AdapterViewNothingSelectionEvent) obj).view());
        }
        return false;
    }

    public int hashCode() {
        return this.view.hashCode() ^ 1000003;
    }
}
