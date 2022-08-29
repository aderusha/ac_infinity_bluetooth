package com.trello.rxlifecycle2;

import com.trello.rxlifecycle2.internal.Preconditions;
import javax.annotation.ParametersAreNonnullByDefault;
import org.reactivestreams.Publisher;
import p014io.reactivex.BackpressureStrategy;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.CompletableTransformer;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableTransformer;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.MaybeTransformer;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.ObservableTransformer;
import p014io.reactivex.Single;
import p014io.reactivex.SingleSource;
import p014io.reactivex.SingleTransformer;

@ParametersAreNonnullByDefault
public final class LifecycleTransformer<T> implements ObservableTransformer<T, T>, FlowableTransformer<T, T>, SingleTransformer<T, T>, MaybeTransformer<T, T>, CompletableTransformer {
    final Observable<?> observable;

    LifecycleTransformer(Observable<?> observable2) {
        Preconditions.checkNotNull(observable2, "observable == null");
        this.observable = observable2;
    }

    public ObservableSource<T> apply(Observable<T> observable2) {
        return observable2.takeUntil((ObservableSource<U>) this.observable);
    }

    public Publisher<T> apply(Flowable<T> flowable) {
        return flowable.takeUntil((Publisher<U>) this.observable.toFlowable(BackpressureStrategy.LATEST));
    }

    public SingleSource<T> apply(Single<T> single) {
        return single.takeUntil((SingleSource<? extends E>) this.observable.firstOrError());
    }

    public MaybeSource<T> apply(Maybe<T> maybe) {
        return maybe.takeUntil((MaybeSource<U>) this.observable.firstElement());
    }

    public CompletableSource apply(Completable completable) {
        return Completable.ambArray(completable, this.observable.flatMapCompletable(Functions.CANCEL_COMPLETABLE));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.observable.equals(((LifecycleTransformer) obj).observable);
    }

    public int hashCode() {
        return this.observable.hashCode();
    }

    public String toString() {
        return "LifecycleTransformer{observable=" + this.observable + '}';
    }
}
