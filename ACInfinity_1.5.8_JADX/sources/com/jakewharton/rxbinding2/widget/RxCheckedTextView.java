package com.jakewharton.rxbinding2.widget;

import android.widget.CheckedTextView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.functions.Consumer;

public final class RxCheckedTextView {
    public static Consumer<? super Boolean> check(final CheckedTextView checkedTextView) {
        Preconditions.checkNotNull(checkedTextView, "view == null");
        return new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                checkedTextView.setChecked(bool.booleanValue());
            }
        };
    }

    private RxCheckedTextView() {
        throw new AssertionError("No instances.");
    }
}
