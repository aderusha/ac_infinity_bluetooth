package com.jakewharton.rxbinding2.widget;

import android.widget.TextView;

public abstract class TextViewTextChangeEvent {
    public abstract int before();

    public abstract int count();

    public abstract int start();

    public abstract CharSequence text();

    public abstract TextView view();

    public static TextViewTextChangeEvent create(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        return new AutoValue_TextViewTextChangeEvent(textView, charSequence, i, i2, i3);
    }

    TextViewTextChangeEvent() {
    }
}
