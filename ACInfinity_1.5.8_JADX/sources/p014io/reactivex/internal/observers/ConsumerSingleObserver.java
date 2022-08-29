package p014io.reactivex.internal.observers;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.functions.Functions;
import p014io.reactivex.observers.LambdaConsumerIntrospection;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.observers.ConsumerSingleObserver */
public final class ConsumerSingleObserver<T> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable, LambdaConsumerIntrospection {
    private static final long serialVersionUID = -7012088219455310787L;
    final Consumer<? super Throwable> onError;
    final Consumer<? super T> onSuccess;

    public ConsumerSingleObserver(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
        this.onSuccess = consumer;
        this.onError = consumer2;
    }

    public void onError(Throwable th) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            RxJavaPlugins.onError(new CompositeException(th, th2));
        }
    }

    public void onSubscribe(Disposable disposable) {
        DisposableHelper.setOnce(this, disposable);
    }

    public void onSuccess(T t) {
        lazySet(DisposableHelper.DISPOSED);
        try {
            this.onSuccess.accept(t);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    public void dispose() {
        DisposableHelper.dispose(this);
    }

    public boolean isDisposed() {
        return get() == DisposableHelper.DISPOSED;
    }

    public boolean hasCustomOnError() {
        return this.onError != Functions.ON_ERROR_MISSING;
    }
}
