package com.jakewharton.rxbinding2.widget;

import android.widget.TextView;
import java.util.Objects;

final class AutoValue_TextViewBeforeTextChangeEvent extends TextViewBeforeTextChangeEvent {
    private final int after;
    private final int count;
    private final int start;
    private final CharSequence text;
    private final TextView view;

    AutoValue_TextViewBeforeTextChangeEvent(TextView textView, CharSequence charSequence, int i, int i2, int i3) {
        Objects.requireNonNull(textView, "Null view");
        this.view = textView;
        Objects.requireNonNull(charSequence, "Null text");
        this.text = charSequence;
        this.start = i;
        this.count = i2;
        this.after = i3;
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

    public int count() {
        return this.count;
    }

    public int after() {
        return this.after;
    }

    public String toString() {
        return "TextViewBeforeTextChangeEvent{view=" + this.view + ", text=" + this.text + ", start=" + this.start + ", count=" + this.count + ", after=" + this.after + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextViewBeforeTextChangeEvent)) {
            return false;
        }
        TextViewBeforeTextChangeEvent textViewBeforeTextChangeEvent = (TextViewBeforeTextChangeEvent) obj;
        if (this.view.equals(textViewBeforeTextChangeEvent.view()) && this.text.equals(textViewBeforeTextChangeEvent.text()) && this.start == textViewBeforeTextChangeEvent.start() && this.count == textViewBeforeTextChangeEvent.count() && this.after == textViewBeforeTextChangeEvent.after()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.text.hashCode()) * 1000003) ^ this.start) * 1000003) ^ this.count) * 1000003) ^ this.after;
    }
}
