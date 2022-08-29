package com.jakewharton.rxbinding2.widget;

import android.widget.RatingBar;

public abstract class RatingBarChangeEvent {
    public abstract boolean fromUser();

    public abstract float rating();

    public abstract RatingBar view();

    public static RatingBarChangeEvent create(RatingBar ratingBar, float f, boolean z) {
        return new AutoValue_RatingBarChangeEvent(ratingBar, f, z);
    }

    RatingBarChangeEvent() {
    }
}
