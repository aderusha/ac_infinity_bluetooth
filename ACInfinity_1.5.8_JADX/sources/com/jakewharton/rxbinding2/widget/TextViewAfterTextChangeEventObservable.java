package com.jakewharton.rxbinding2.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class TextViewAfterTextChangeEventObservable extends InitialValueObservable<TextViewAfterTextChangeEvent> {
    private final TextView view;

    TextViewAfterTextChangeEventObservable(TextView textView) {
        this.view = textView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super TextViewAfterTextChangeEvent> observer) {
        Listener listener = new Listener(this.view, observer);
        observer.onSubscribe(listener);
        this.view.addTextChangedListener(listener);
    }

    /* access modifiers changed from: protected */
    public TextViewAfterTextChangeEvent getInitialValue() {
        TextView textView = this.view;
        return TextViewAfterTextChangeEvent.create(textView, textView.getEditableText());
    }

    static final class Listener extends MainThreadDisposable implements TextWatcher {
        private final Observer<? super TextViewAfterTextChangeEvent> observer;
        private final TextView view;

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        Listener(TextView textView, Observer<? super TextViewAfterTextChangeEvent> observer2) {
            this.view = textView;
            this.observer = observer2;
        }

        public void afterTextChanged(Editable editable) {
            this.observer.onNext(TextViewAfterTextChangeEvent.create(this.view, editable));
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeTextChangedListener(this);
        }
    }
}
