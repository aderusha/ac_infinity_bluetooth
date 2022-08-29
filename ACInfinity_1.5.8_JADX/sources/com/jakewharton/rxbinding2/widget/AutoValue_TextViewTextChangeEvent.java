package com.jakewharton.rxbinding2.widget;

import android.widget.TextView;
import java.util.Objects;

final class AutoValue_TextViewTextChangeEvent extends TextViewTextChangeEvent {
    private final int before;
    private final int count;
    private final int start;
    private final CharSequence text;
    private final TextView view;

    AutoValue_TextViewTextChangeEvent(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        Objects.requireNonNull(textView, "Null view");
        this.view = textView;
        Objects.requireNonNull(charSequence, "Null text");
        this.text = charSequence;
        this.start = i;
        this.before = i2;
        this.count = i3;
    }

    public TextView view() {
        return this.view;
    }

    public CharSequence text() {
        return this.text;
    }

    public int start() {
        return this.start;
    }

    public int before() {
        return this.before;
    }

    public int count() {
        return this.count;
    }

    public String toString() {
        return "TextViewTextChangeEvent{view=" + this.view + ", text=" + this.text + ", start=" + this.start + ", before=" + this.before + ", count=" + this.count + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextViewTextChangeEvent)) {
            return false;
        }
        TextViewTextChangeEvent textViewTextChangeEvent = (TextViewTextChangeEvent) obj;
        if (this.view.equals(textViewTextChangeEvent.view()) && this.text.equals(textViewTextChangeEvent.text()) && this.start == textViewTextChangeEvent.start() && this.before == textViewTextChangeEvent.before() && this.count == textViewTextChangeEvent.count()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.text.hashCode()) * 1000003) ^ this.start) * 1000003) ^ this.before) * 1000003) ^ this.count;
    }
}
