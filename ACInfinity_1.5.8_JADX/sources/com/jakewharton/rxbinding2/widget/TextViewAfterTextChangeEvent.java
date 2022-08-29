package com.jakewharton.rxbinding2.widget;

import android.text.Editable;
import android.widget.TextView;

public abstract class TextViewAfterTextChangeEvent {
    public abstract Editable editable();

    public abstract TextView view();

    public static TextViewAfterTextChangeEvent create(TextView textView, Editable editable) {
        return new AutoValue_TextViewAfterTextChangeEvent(textView, editable);
    }

    TextViewAfterTextChangeEvent() {
    }
}
