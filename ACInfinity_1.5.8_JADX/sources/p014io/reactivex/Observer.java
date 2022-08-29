package p014io.reactivex;

import p014io.reactivex.disposables.Disposable;

/* renamed from: io.reactivex.Observer */
public interface Observer<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(Disposable disposable);
}
