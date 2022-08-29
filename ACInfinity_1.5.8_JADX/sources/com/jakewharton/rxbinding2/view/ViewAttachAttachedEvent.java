package com.jakewharton.rxbinding2.view;

import android.view.View;

public abstract class ViewAttachAttachedEvent extends ViewAttachEvent {
    public static ViewAttachAttachedEvent create(View view) {
        return new AutoValue_ViewAttachAttachedEvent(view);
    }

    ViewAttachAttachedEvent() {
    }
}
