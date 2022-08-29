package com.jakewharton.rxbinding2.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class TextViewBeforeTextChangeEventObservable extends InitialValueObservable<TextViewBeforeTextChangeEvent> {
    private final TextView view;

    TextViewBeforeTextChangeEventObservable(TextView textView) {
        this.view = textView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super TextViewBeforeTextChangeEvent> observer) {
        Listener listener = new Listener(this.view, observer);
        observer.onSubscribe(listener);
        this.view.addTextChangedListener(listener);
    }

    /* access modifiers changed from: protected */
    public TextViewBeforeTextChangeEvent getInitialValue() {
        TextView textView = this.view;
        return TextViewBeforeTextChangeEvent.create(textView, textView.getText(), 0, 0, 0);
    }

    static final class Listener extends MainThreadDisposable implements TextWatcher {
        private final Observer<? super TextViewBeforeTextChangeEvent> observer;
        private final TextView view;

        public void afterTextChanged(Editable editable) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        Listener(TextView textView, Observer<? super TextViewBeforeTextChangeEvent> observer2) {
            this.view = textView;
            this.observer = observer2;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.observer.onNext(TextViewBeforeTextChangeEvent.create(this.view, charSequence, i, i2, i3));
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeTextChangedListener(this);
        }
    }
}
