package com.jakewharton.rxbinding2.view;

import android.view.View;
import java.util.Objects;

final class AutoValue_ViewScrollChangeEvent extends ViewScrollChangeEvent {
    private final int oldScrollX;
    private final int oldScrollY;
    private final int scrollX;
    private final int scrollY;
    private final View view;

    AutoValue_ViewScrollChangeEvent(View view2, int i, int i2, int i3, int i4) {
        Objects.requireNonNull(view2, "Null view");
        this.view = view2;
        this.scrollX = i;
        this.scrollY = i2;
        this.oldScrollX = i3;
        this.oldScrollY = i4;
    }

    public View view() {
        return this.view;
    }

    public int scrollX() {
        return this.scrollX;
    }

    public int scrollY() {
        return this.scrollY;
    }

    public int oldScrollX() {
        return this.oldScrollX;
    }

    public int oldScrollY() {
        return this.oldScrollY;
    }

    public String toString() {
        return "ViewScrollChangeEvent{view=" + this.view + ", scrollX=" + this.scrollX + ", scrollY=" + this.scrollY + ", oldScrollX=" + this.oldScrollX + ", oldScrollY=" + this.oldScrollY + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewScrollChangeEvent)) {
            return false;
        }
        ViewScrollChangeEvent viewScrollChangeEvent = (ViewScrollChangeEvent) obj;
        if (this.view.equals(viewScrollChangeEvent.view()) && this.scrollX == viewScrollChangeEvent.scrollX() && this.scrollY == viewScrollChangeEvent.scrollY() && this.oldScrollX == viewScrollChangeEvent.oldScrollX() && this.oldScrollY == viewScrollChangeEvent.oldScrollY()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.scrollX) * 1000003) ^ this.scrollY) * 1000003) ^ this.oldScrollX) * 1000003) ^ this.oldScrollY;
    }
}
