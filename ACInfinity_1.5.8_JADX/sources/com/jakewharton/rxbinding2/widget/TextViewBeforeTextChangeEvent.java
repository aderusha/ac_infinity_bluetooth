package com.jakewharton.rxbinding2.widget;

import android.widget.TextView;

public abstract class TextViewBeforeTextChangeEvent {
    public abstract int after();

    public abstract int count();

    public abstract int start();

    public abstract CharSequence text();

    public abstract TextView view();

    public static TextViewBeforeTextChangeEvent create(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        return new AutoValue_TextViewBeforeTextChangeEvent(textView, charSequence, i, i2, i3);
    }

    TextViewBeforeTextChangeEvent() {
    }
}
