package com.jakewharton.rxbinding2.widget;

import android.view.KeyEvent;
import android.widget.TextView;
import com.jakewharton.rxbinding2.internal.Preconditions;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.MainThreadDisposable;
import p014io.reactivex.functions.Predicate;

final class TextViewEditorActionObservable extends Observable<Integer> {
    private final Predicate<? super Integer> handled;
    private final TextView view;

    TextViewEditorActionObservable(TextView textView, Predicate<? super Integer> predicate) {
        this.view = textView;
        this.handled = predicate;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Integer> observer) {
        if (Preconditions.checkMainThread(observer)) {
            Listener listener = new Listener(this.view, observer, this.handled);
            observer.onSubscribe(listener);
            this.view.setOnEditorActionListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements TextView.OnEditorActionListener {
        private final Predicate<? super Integer> handled;
        private final Observer<? super Integer> observer;
        private final TextView view;

        Listener(TextView textView, Observer<? super Integer> observer2, Predicate<? super Integer> predicate) {
            this.view = textView;
            this.observer = observer2;
            this.handled = predicate;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            try {
                if (isDisposed() || !this.handled.test(Integer.valueOf(i))) {
                    return false;
                }
                this.observer.onNext(Integer.valueOf(i));
                return true;
            } catch (Exception e) {
                this.observer.onError(e);
                dispose();
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void onDispose() {
            this.view.setOnEditorActionListener((TextView.OnEditorActionListener) null);
        }
    }
}
