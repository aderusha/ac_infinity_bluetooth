package p014io.reactivex;

import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Cancellable;

/* renamed from: io.reactivex.SingleEmitter */
public interface SingleEmitter<T> {
    boolean isDisposed();

    void onError(Throwable th);

    void onSuccess(T t);

    void setCancellable(Cancellable cancellable);

    void setDisposable(Disposable disposable);

    boolean tryOnError(Throwable th);
}
