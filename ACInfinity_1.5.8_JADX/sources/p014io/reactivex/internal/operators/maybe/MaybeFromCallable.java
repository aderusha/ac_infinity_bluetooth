package p014io.reactivex.internal.operators.maybe;

import java.util.concurrent.Callable;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.disposables.Disposables;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeFromCallable */
public final class MaybeFromCallable<T> extends Maybe<T> implements Callable<T> {
    final Callable<? extends T> callable;

    public MaybeFromCallable(Callable<? extends T> callable2) {
        this.callable = callable2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        Disposable empty = Disposables.empty();
        maybeObserver.onSubscribe(empty);
        if (!empty.isDisposed()) {
            try {
                Object call = this.callable.call();
                if (empty.isDisposed()) {
                    return;
                }
                if (call == null) {
                    maybeObserver.onComplete();
                } else {
                    maybeObserver.onSuccess(call);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                if (!empty.isDisposed()) {
                    maybeObserver.onError(th);
                } else {
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }

    public T call() throws Exception {
        return this.callable.call();
    }
}
