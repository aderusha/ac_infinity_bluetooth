package p014io.reactivex.internal.operators.observable;

import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Predicate;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.fuseable.FuseToObservable;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.observable.ObservableAnySingle */
public final class ObservableAnySingle<T> extends Single<Boolean> implements FuseToObservable<Boolean> {
    final Predicate<? super T> predicate;
    final ObservableSource<T> source;

    public ObservableAnySingle(ObservableSource<T> observableSource, Predicate<? super T> predicate2) {
        this.source = observableSource;
        this.predicate = predicate2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.source.subscribe(new AnyObserver(singleObserver, this.predicate));
    }

    public Observable<Boolean> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableAny(this.source, this.predicate));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableAnySingle$AnyObserver */
    static final class AnyObserver<T> implements Observer<T>, Disposable {
        boolean done;
        final SingleObserver<? super Boolean> downstream;
        final Predicate<? super T> predicate;
        Disposable upstream;

        AnyObserver(SingleObserver<? super Boolean> singleObserver, Predicate<? super T> predicate2) {
            this.downstream = singleObserver;
            this.predicate = predicate2;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    if (this.predicate.test(t)) {
                        this.done = true;
                        this.upstream.dispose();
                        this.downstream.onSuccess(true);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.upstream.dispose();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onSuccess(false);
            }
        }

        public void dispose() {
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }
    }
}
