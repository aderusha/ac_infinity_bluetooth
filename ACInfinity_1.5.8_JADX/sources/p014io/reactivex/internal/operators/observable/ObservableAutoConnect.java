package p014io.reactivex.internal.operators.observable;

import java.util.concurrent.atomic.AtomicInteger;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.observables.ConnectableObservable;

/* renamed from: io.reactivex.internal.operators.observable.ObservableAutoConnect */
public final class ObservableAutoConnect<T> extends Observable<T> {
    final AtomicInteger clients = new AtomicInteger();
    final Consumer<? super Disposable> connection;
    final int numberOfObservers;
    final ConnectableObservable<? extends T> source;

    public ObservableAutoConnect(ConnectableObservable<? extends T> connectableObservable, int i, Consumer<? super Disposable> consumer) {
        this.source = connectableObservable;
        this.numberOfObservers = i;
        this.connection = consumer;
    }

    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(observer);
        if (this.clients.incrementAndGet() == this.numberOfObservers) {
            this.source.connect(this.connection);
        }
    }
}
