package com.jakewharton.rxbinding2.view;

import android.view.View;
import java.util.Objects;

final class AutoValue_ViewLayoutChangeEvent extends ViewLayoutChangeEvent {
    private final int bottom;
    private final int left;
    private final int oldBottom;
    private final int oldLeft;
    private final int oldRight;
    private final int oldTop;
    private final int right;
    private final int top;
    private final View view;

    AutoValue_ViewLayoutChangeEvent(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Objects.requireNonNull(view2, "Null view");
        this.view = view2;
        this.left = i;
        this.top = i2;
        this.right = i3;
        this.bottom = i4;
        this.oldLeft = i5;
        this.oldTop = i6;
        this.oldRight = i7;
        this.oldBottom = i8;
    }

    public View view() {
        return this.view;
    }

    public int left() {
        return this.left;
    }

    public int top() {
        return this.top;
    }

    public int right() {
        return this.right;
    }

    public int bottom() {
        return this.bottom;
    }

    public int oldLeft() {
        return this.oldLeft;
    }

    public int oldTop() {
        return this.oldTop;
    }

    public int oldRight() {
        return this.oldRight;
    }

    public int oldBottom() {
        return this.oldBottom;
    }

    public String toString() {
        return "ViewLayoutChangeEvent{view=" + this.view + ", left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + ", oldLeft=" + this.oldLeft + ", oldTop=" + this.oldTop + ", oldRight=" + this.oldRight + ", oldBottom=" + this.oldBottom + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewLayoutChangeEvent)) {
            return false;
        }
        ViewLayoutChangeEvent viewLayoutChangeEvent = (ViewLayoutChangeEvent) obj;
        if (this.view.equals(viewLayoutChangeEvent.view()) && this.left == viewLayoutChangeEvent.left() && this.top == viewLayoutChangeEvent.top() && this.right == viewLayoutChangeEvent.right() && this.bottom == viewLayoutChangeEvent.bottom() && this.oldLeft == viewLayoutChangeEvent.oldLeft() && this.oldTop == viewLayoutChangeEvent.oldTop() && this.oldRight == viewLayoutChangeEvent.oldRight() && this.oldBottom == viewLayoutChangeEvent.oldBottom()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.left) * 1000003) ^ this.top) * 1000003) ^ this.right) * 1000003) ^ this.bottom) * 1000003) ^ this.oldLeft) * 1000003) ^ this.oldTop) * 1000003) ^ this.oldRight) * 1000003) ^ this.oldBottom;
    }
}
