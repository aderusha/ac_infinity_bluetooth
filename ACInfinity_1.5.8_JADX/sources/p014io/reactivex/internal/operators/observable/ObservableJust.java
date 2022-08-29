package p014io.reactivex.internal.operators.observable;

import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.internal.fuseable.ScalarCallable;
import p014io.reactivex.internal.operators.observable.ObservableScalarXMap;

/* renamed from: io.reactivex.internal.operators.observable.ObservableJust */
public final class ObservableJust<T> extends Observable<T> implements ScalarCallable<T> {
    private final T value;

    public ObservableJust(T t) {
        this.value = t;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        ObservableScalarXMap.ScalarDisposable scalarDisposable = new ObservableScalarXMap.ScalarDisposable(observer, this.value);
        observer.onSubscribe(scalarDisposable);
        scalarDisposable.run();
    }

    public T call() {
        return this.value;
    }
}
