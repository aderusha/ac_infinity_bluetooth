package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import java.util.Objects;

final class AutoValue_AdapterViewItemSelectionEvent extends AdapterViewItemSelectionEvent {

    /* renamed from: id */
    private final long f357id;
    private final int position;
    private final View selectedView;
    private final AdapterView<?> view;

    AutoValue_AdapterViewItemSelectionEvent(AdapterView<?> adapterView, View view2, int i, long j) {
        Objects.requireNonNull(adapterView, "Null view");
        this.view = adapterView;
        Objects.requireNonNull(view2, "Null selectedView");
        this.selectedView = view2;
        this.position = i;
        this.f357id = j;
    }

    public AdapterView<?> view() {
        return this.view;
    }

    public View selectedView() {
        return this.selectedView;
    }

    public int position() {
        return this.position;
    }

    /* renamed from: id */
    public long mo23688id() {
        return this.f357id;
    }

    public String toString() {
        return "AdapterViewItemSelectionEvent{view=" + this.view + ", selectedView=" + this.selectedView + ", position=" + this.position + ", id=" + this.f357id + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdapterViewItemSelectionEvent)) {
            return false;
        }
        AdapterViewItemSelectionEvent adapterViewItemSelectionEvent = (AdapterViewItemSelectionEvent) obj;
        if (!this.view.equals(adapterViewItemSelectionEvent.view()) || !this.selectedView.equals(adapterViewItemSelectionEvent.selectedView()) || this.position != adapterViewItemSelectionEvent.position() || this.f357id != adapterViewItemSelectionEvent.mo23688id()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.f357id;
        return ((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.selectedView.hashCode()) * 1000003) ^ this.position) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
