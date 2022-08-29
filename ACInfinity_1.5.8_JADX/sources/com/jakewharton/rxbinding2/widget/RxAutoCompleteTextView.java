package com.jakewharton.rxbinding2.widget;

import android.widget.AutoCompleteTextView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.functions.Consumer;

public final class RxAutoCompleteTextView {
    public static Observable<AdapterViewItemClickEvent> itemClickEvents(AutoCompleteTextView autoCompleteTextView) {
        Preconditions.checkNotNull(autoCompleteTextView, "view == null");
        return new AutoCompleteTextViewItemClickEventObservable(autoCompleteTextView);
    }

    public static Consumer<? super CharSequence> completionHint(final AutoCompleteTextView autoCompleteTextView) {
        Preconditions.checkNotNull(autoCompleteTextView, "view == null");
        return new Consumer<CharSequence>() {
            public void accept(CharSequence charSequence) {
                autoCompleteTextView.setCompletionHint(charSequence);
            }
        };
    }

    public static Consumer<? super Integer> threshold(final AutoCompleteTextView autoCompleteTextView) {
        Preconditions.checkNotNull(autoCompleteTextView, "view == null");
        return new Consumer<Integer>() {
            public void accept(Integer num) {
                autoCompleteTextView.setThreshold(num.intValue());
            }
        };
    }

    private RxAutoCompleteTextView() {
        throw new AssertionError("No instances.");
    }
}
