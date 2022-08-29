package com.eternal.framework.http.interfaces;

import p014io.reactivex.disposables.Disposable;

public interface ISubscriber<T> {
    void doOnCompleted();

    void doOnError(String str);

    void doOnNext(T t);

    void doOnSubscribe(Disposable disposable);
}
