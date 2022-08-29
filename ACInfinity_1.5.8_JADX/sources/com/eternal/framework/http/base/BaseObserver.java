package com.eternal.framework.http.base;

import com.eternal.framework.http.exception.ApiException;
import com.eternal.framework.http.interfaces.ISubscriber;
import com.eternal.framework.http.manage.RxHttpManager;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T>, ISubscriber<T> {
    /* access modifiers changed from: protected */
    public boolean isHideToast() {
        return true;
    }

    /* access modifiers changed from: protected */
    public String setTag() {
        return null;
    }

    public void onSubscribe(Disposable disposable) {
        RxHttpManager.get().add(setTag(), disposable);
        doOnSubscribe(disposable);
    }

    public void onNext(T t) {
        doOnNext(t);
    }

    public void onError(Throwable th) {
        setError(ApiException.handleException(th).getMessage());
    }

    public void onComplete() {
        doOnCompleted();
    }

    private void setError(String str) {
        doOnError(str);
    }
}
