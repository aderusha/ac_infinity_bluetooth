package p014io.reactivex.internal.observers;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.observers.ResumeSingleObserver */
public final class ResumeSingleObserver<T> implements SingleObserver<T> {
    final SingleObserver<? super T> downstream;
    final AtomicReference<Disposable> parent;

    public ResumeSingleObserver(AtomicReference<Disposable> atomicReference, SingleObserver<? super T> singleObserver) {
        this.parent = atomicReference;
        this.downstream = singleObserver;
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.replace(this.parent, disposable);
    }

    public void onSuccess(T t) {
        this.downstream.onSuccess(t);
    }

    public void onError(Throwable th) {
        this.downstream.onError(th);
    }
}
