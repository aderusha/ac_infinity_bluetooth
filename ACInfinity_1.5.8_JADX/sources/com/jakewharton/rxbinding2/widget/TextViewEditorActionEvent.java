package com.jakewharton.rxbinding2.widget;

import android.view.KeyEvent;
import android.widget.TextView;

public abstract class TextViewEditorActionEvent {
    public abstract int actionId();

    public abstract KeyEvent keyEvent();

    public abstract TextView view();

    public static TextViewEditorActionEvent create(TextView textView, int i, KeyEvent keyEvent) {
        return new AutoValue_TextViewEditorActionEvent(textView, i, keyEvent);
    }

    TextViewEditorActionEvent() {
    }
}
