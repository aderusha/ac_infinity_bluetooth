package com.jakewharton.rxbinding2;

import p014io.reactivex.Observable;
import p014io.reactivex.Observer;

public abstract class InitialValueObservable<T> extends Observable<T> {
    /* access modifiers changed from: protected */
    public abstract T getInitialValue();

    /* access modifiers changed from: protected */
    public abstract void subscribeListener(Observer<? super T> observer);

    /* access modifiers changed from: protected */
    public final void subscribeActual(Observer<? super T> observer) {
        subscribeListener(observer);
        observer.onNext(getInitialValue());
    }

    public final Observable<T> skipInitialValue() {
        return new Skipped();
    }

    private final class Skipped extends Observable<T> {
        Skipped() {
        }

        /* access modifiers changed from: protected */
        public void subscribeActual(Observer<? super T> observer) {
            InitialValueObservable.this.subscribeListener(observer);
        }
    }
}
