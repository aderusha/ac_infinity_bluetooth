package p014io.reactivex.internal.operators.observable;

import p014io.reactivex.Notification;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.observable.ObservableMaterialize */
public final class ObservableMaterialize<T> extends AbstractObservableWithUpstream<T, Notification<T>> {
    public ObservableMaterialize(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    public void subscribeActual(Observer<? super Notification<T>> observer) {
        this.source.subscribe(new MaterializeObserver(observer));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableMaterialize$MaterializeObserver */
    static final class MaterializeObserver<T> implements Observer<T>, Disposable {
        final Observer<? super Notification<T>> downstream;
        Disposable upstream;

        MaterializeObserver(Observer<? super Notification<T>> observer) {
            this.downstream = observer;
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
            this.downstream.onNext(Notification.createOnNext(t));
        }

        public void onError(Throwable th) {
            this.downstream.onNext(Notification.createOnError(th));
            this.downstream.onComplete();
        }

        public void onComplete() {
            this.downstream.onNext(Notification.createOnComplete());
            this.downstream.onComplete();
        }
    }
}
