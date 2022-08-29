package com.jakewharton.rxbinding2.widget;

import android.widget.RatingBar;
import java.util.Objects;

final class AutoValue_RatingBarChangeEvent extends RatingBarChangeEvent {
    private final boolean fromUser;
    private final float rating;
    private final RatingBar view;

    AutoValue_RatingBarChangeEvent(RatingBar ratingBar, float f, boolean z) {
        Objects.requireNonNull(ratingBar, "Null view");
        this.view = ratingBar;
        this.rating = f;
        this.fromUser = z;
    }

    public RatingBar view() {
        return this.view;
    }

    public float rating() {
        return this.rating;
    }

    public boolean fromUser() {
        return this.fromUser;
    }

    public String toString() {
        return "RatingBarChangeEvent{view=" + this.view + ", rating=" + this.rating + ", fromUser=" + this.fromUser + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RatingBarChangeEvent)) {
            return false;
        }
        RatingBarChangeEvent ratingBarChangeEvent = (RatingBarChangeEvent) obj;
        if (this.view.equals(ratingBarChangeEvent.view()) && Float.floatToIntBits(this.rating) == Float.floatToIntBits(ratingBarChangeEvent.rating()) && this.fromUser == ratingBarChangeEvent.fromUser()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.view.hashCode() ^ 1000003) * 1000003) ^ Float.floatToIntBits(this.rating)) * 1000003) ^ (this.fromUser ? 1231 : 1237);
    }
}
