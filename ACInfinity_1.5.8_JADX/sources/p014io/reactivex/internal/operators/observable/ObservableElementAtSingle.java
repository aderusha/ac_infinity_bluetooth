package p014io.reactivex.internal.operators.observable;

import java.util.NoSuchElementException;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.fuseable.FuseToObservable;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.observable.ObservableElementAtSingle */
public final class ObservableElementAtSingle<T> extends Single<T> implements FuseToObservable<T> {
    final T defaultValue;
    final long index;
    final ObservableSource<T> source;

    public ObservableElementAtSingle(ObservableSource<T> observableSource, long j, T t) {
        this.source = observableSource;
        this.index = j;
        this.defaultValue = t;
    }

    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new ElementAtObserver(singleObserver, this.index, this.defaultValue));
    }

    public Observable<T> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableElementAt(this.source, this.index, this.defaultValue, true));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableElementAtSingle$ElementAtObserver */
    static final class ElementAtObserver<T> implements Observer<T>, Disposable {
        long count;
        final T defaultValue;
        boolean done;
        final SingleObserver<? super T> downstream;
        final long index;
        Disposable upstream;

        ElementAtObserver(SingleObserver<? super T> singleObserver, long j, T t) {
            this.downstream = singleObserver;
            this.index = j;
            this.defaultValue = t;
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
            if (!this.done) {
                long j = this.count;
                if (j == this.index) {
                    this.done = true;
                    this.upstream.dispose();
                    this.downstream.onSuccess(t);
                    return;
                }
                this.count = j + 1;
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
                T t = this.defaultValue;
                if (t != null) {
                    this.downstream.onSuccess(t);
                } else {
                    this.downstream.onError(new NoSuchElementException());
                }
            }
        }
    }
}
