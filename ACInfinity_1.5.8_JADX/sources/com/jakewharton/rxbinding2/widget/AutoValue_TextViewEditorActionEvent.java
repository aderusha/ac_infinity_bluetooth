package com.jakewharton.rxbinding2.widget;

import android.view.KeyEvent;
import android.widget.TextView;
import java.util.Objects;

final class AutoValue_TextViewEditorActionEvent extends TextViewEditorActionEvent {
    private final int actionId;
    private final KeyEvent keyEvent;
    private final TextView view;

    AutoValue_TextViewEditorActionEvent(TextView textView, int i, KeyEvent keyEvent2) {
        Objects.requireNonNull(textView, "Null view");
        this.view = textView;
        this.actionId = i;
        this.keyEvent = keyEvent2;
    }

    public TextView view() {
        return this.view;
    }

    public int actionId() {
        return this.actionId;
    }

    public KeyEvent keyEvent() {
        return this.keyEvent;
    }

    public String toString() {
        return "TextViewEditorActionEvent{view=" + this.view + ", actionId=" + this.actionId + ", keyEvent=" + this.keyEvent + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TextViewEditorActionEvent)) {
            return false;
        }
        TextViewEditorActionEvent textViewEditorActionEvent = (TextViewEditorActionEvent) obj;
        if (this.view.equals(textViewEditorActionEvent.view()) && this.actionId == textViewEditorActionEvent.actionId()) {
            KeyEvent keyEvent2 = this.keyEvent;
            if (keyEvent2 == null) {
                if (textViewEditorActionEvent.keyEvent() == null) {
                    return true;
                }
            } else if (keyEvent2.equals(textViewEditorActionEvent.keyEvent())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (((this.view.hashCode() ^ 1000003) * 1000003) ^ this.actionId) * 1000003;
        KeyEvent keyEvent2 = this.keyEvent;
        return hashCode ^ (keyEvent2 == null ? 0 : keyEvent2.hashCode());
    }
}
