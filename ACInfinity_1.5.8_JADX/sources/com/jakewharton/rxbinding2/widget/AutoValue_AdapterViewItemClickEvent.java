package com.jakewharton.rxbinding2.widget;

import android.view.View;
import android.widget.AdapterView;
import java.util.Objects;

final class AutoValue_AdapterViewItemClickEvent extends AdapterViewItemClickEvent {
    private final View clickedView;

    /* renamed from: id */
    private final long f355id;
    private final int position;
    private final AdapterView<?> view;

    AutoValue_AdapterViewItemClickEvent(AdapterView<?> adapterView, View view2, int i, long j) {
        Objects.requireNonNull(adapterView, "Null view");
        this.view = adapterView;
        Objects.requireNonNull(view2, "Null clickedView");
        this.clickedView = view2;
        this.position = i;
        this.f355id = j;
    }

    public AdapterView<?> view() {
        return this.view;
    }

    public View clickedView() {
        return this.clickedView;
    }

    public int position() {
        return this.position;
    }

    /* renamed from: id */
    public long mo23677id() {
        return this.f355id;
    }

    public String toString() {
        return "AdapterViewItemClickEvent{view=" + this.view + ", clickedView=" + this.clickedView + ", position=" + this.position + ", id=" + this.f355id + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdapterViewItemClickEvent)) {
            return false;
        }
        AdapterViewItemClickEvent adapterViewItemClickEvent = (AdapterViewItemClickEvent) obj;
        if (!this.view.equals(adapterViewItemClickEvent.view()) || !this.clickedView.equals(adapterViewItemClickEvent.clickedView()) || this.position != adapterViewItemClickEvent.position() || this.f355id != adapterViewItemClickEvent.mo23677id()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.f355id;
        return ((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.clickedView.hashCode()) * 1000003) ^ this.position) * 1000003) ^ ((int) (j ^ (j >>> 32)));
    }
}
