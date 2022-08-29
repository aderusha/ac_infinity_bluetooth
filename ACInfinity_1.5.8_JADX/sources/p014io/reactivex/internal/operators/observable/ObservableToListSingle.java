package p014io.reactivex.internal.operators.observable;

import java.util.Collection;
import java.util.concurrent.Callable;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.disposables.EmptyDisposable;
import p014io.reactivex.internal.functions.Functions;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.fuseable.FuseToObservable;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.observable.ObservableToListSingle */
public final class ObservableToListSingle<T, U extends Collection<? super T>> extends Single<U> implements FuseToObservable<U> {
    final Callable<U> collectionSupplier;
    final ObservableSource<T> source;

    public ObservableToListSingle(ObservableSource<T> observableSource, int i) {
        this.source = observableSource;
        this.collectionSupplier = Functions.createArrayList(i);
    }

    public ObservableToListSingle(ObservableSource<T> observableSource, Callable<U> callable) {
        this.source = observableSource;
        this.collectionSupplier = callable;
    }

    public void subscribeActual(SingleObserver<? super U> singleObserver) {
        try {
            this.source.subscribe(new ToListObserver(singleObserver, (Collection) ObjectHelper.requireNonNull(this.collectionSupplier.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, (SingleObserver<?>) singleObserver);
        }
    }

    public Observable<U> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableToList(this.source, this.collectionSupplier));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableToListSingle$ToListObserver */
    static final class ToListObserver<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        U collection;
        final SingleObserver<? super U> downstream;
        Disposable upstream;

        ToListObserver(SingleObserver<? super U> singleObserver, U u) {
            this.downstream = singleObserver;
            this.collection = u;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void dispose() {
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public void onNext(T t) {
            this.collection.add(t);
        }

        public void onError(Throwable th) {
            this.collection = null;
            this.downstream.onError(th);
        }

        public void onComplete() {
            U u = this.collection;
            this.collection = null;
            this.downstream.onSuccess(u);
        }
    }
}
