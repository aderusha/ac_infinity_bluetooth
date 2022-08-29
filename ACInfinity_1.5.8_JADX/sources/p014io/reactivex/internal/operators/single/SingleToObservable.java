package p014io.reactivex.internal.operators.single;

import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.SingleSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.observers.DeferredScalarDisposable;

/* renamed from: io.reactivex.internal.operators.single.SingleToObservable */
public final class SingleToObservable<T> extends Observable<T> {
    final SingleSource<? extends T> source;

    public SingleToObservable(SingleSource<? extends T> singleSource) {
        this.source = singleSource;
    }

    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(create(observer));
    }

    public static <T> SingleObserver<T> create(Observer<? super T> observer) {
        return new SingleToObservableObserver(observer);
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleToObservable$SingleToObservableObserver */
    static final class SingleToObservableObserver<T> extends DeferredScalarDisposable<T> implements SingleObserver<T> {
        private static final long serialVersionUID = 3786543492451018833L;
        Disposable upstream;

        SingleToObservableObserver(Observer<? super T> observer) {
            super(observer);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            complete(t);
        }

        public void onError(Throwable th) {
            error(th);
        }

        public void dispose() {
            super.dispose();
            this.upstream.dispose();
        }
    }
}
