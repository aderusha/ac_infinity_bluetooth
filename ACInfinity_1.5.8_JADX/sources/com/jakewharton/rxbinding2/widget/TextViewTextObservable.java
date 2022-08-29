package com.jakewharton.rxbinding2.widget;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;

final class TextViewTextObservable extends InitialValueObservable<CharSequence> {
    private final TextView view;

    TextViewTextObservable(TextView textView) {
        this.view = textView;
    }

    /* access modifiers changed from: protected */
    public void subscribeListener(Observer<? super CharSequence> observer) {
        Listener listener = new Listener(this.view, observer);
        observer.onSubscribe(listener);
        this.view.addTextChangedListener(listener);
    }

    /* access modifiers changed from: protected */
    public CharSequence getInitialValue() {
        return this.view.getText();
    }

    static final class Listener extends MainThreadDisposable implements TextWatcher {
        private final Observer<? super CharSequence> observer;
        private final TextView view;

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        Listener(TextView textView, Observer<? super CharSequence> observer2) {
            this.view = textView;
            this.observer = observer2;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!isDisposed()) {
                this.observer.onNext(charSequence);
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.removeTextChangedListener(this);
        }
    }
}
