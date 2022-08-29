package p014io.reactivex.internal.observers;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.OnErrorNotImplementedException;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.observers.LambdaConsumerIntrospection;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.observers.EmptyCompletableObserver */
public final class EmptyCompletableObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable, LambdaConsumerIntrospection {
    private static final long serialVersionUID = -7545121636549663526L;

    public boolean hasCustomOnError() {
        return false;
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public void onComplete() {
        lazySet(DisposableHelper.DISPOSED);
    }

    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        RxJavaPlugins.onError(new OnErrorNotImplementedException(th));
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }
}
