package com.eternal.framework.http.manage;

import p014io.reactivex.disposables.Disposable;

public interface IRxHttpManager<T> {
    void add(T t, Disposable disposable);

    void cancel(T t);

    void cancel(T... tArr);

    void cancelAll();

    void remove(T t);
}
