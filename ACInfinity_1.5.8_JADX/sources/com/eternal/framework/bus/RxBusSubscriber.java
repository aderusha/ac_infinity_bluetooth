package com.eternal.framework.bus;

import p014io.reactivex.observers.DisposableObserver;

public abstract class RxBusSubscriber<T> extends DisposableObserver<T> {
    public void onComplete() {
    }

    /* access modifiers changed from: protected */
    public abstract void onEvent(T t);

    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onError(Throwable th) {
        th.printStackTrace();
    }
}
