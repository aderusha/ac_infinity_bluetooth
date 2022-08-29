package p014io.reactivex.internal.operators.maybe;

import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.disposables.Disposables;
import p014io.reactivex.internal.fuseable.ScalarCallable;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeJust */
public final class MaybeJust<T> extends Maybe<T> implements ScalarCallable<T> {
    final T value;

    public MaybeJust(T t) {
        this.value = t;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        maybeObserver.onSubscribe(Disposables.disposed());
        maybeObserver.onSuccess(this.value);
    }

    public T call() {
        return this.value;
    }
}
