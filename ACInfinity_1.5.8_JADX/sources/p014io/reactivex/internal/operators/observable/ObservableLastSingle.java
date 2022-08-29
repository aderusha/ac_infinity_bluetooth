package p014io.reactivex.internal.operators.observable;

import java.util.NoSuchElementException;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.observable.ObservableLastSingle */
public final class ObservableLastSingle<T> extends Single<T> {
    final T defaultItem;
    final ObservableSource<T> source;

    public ObservableLastSingle(ObservableSource<T> observableSource, T t) {
        this.source = observableSource;
        this.defaultItem = t;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new LastObserver(singleObserver, this.defaultItem));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableLastSingle$LastObserver */
    static final class LastObserver<T> implements Observer<T>, Disposable {
        final T defaultItem;
        final SingleObserver<? super T> downstream;
        T item;
        Disposable upstream;

        LastObserver(SingleObserver<? super T> singleObserver, T t) {
            this.downstream = singleObserver;
            this.defaultItem = t;
        }

        public void dispose() {
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.upstream == DisposableHelper.DISPOSED;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.item = t;
        }

        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.item = null;
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.upstream = DisposableHelper.DISPOSED;
            T t = this.item;
            if (t != null) {
                this.item = null;
                this.downstream.onSuccess(t);
                return;
            }
            T t2 = this.defaultItem;
            if (t2 != null) {
                this.downstream.onSuccess(t2);
            } else {
                this.downstream.onError(new NoSuchElementException());
            }
        }
    }
}
