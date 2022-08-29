package p014io.reactivex.internal.operators.observable;

import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.util.EmptyComponent;

/* renamed from: io.reactivex.internal.operators.observable.ObservableDetach */
public final class ObservableDetach<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableDetach(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DetachObserver(observer));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableDetach$DetachObserver */
    static final class DetachObserver<T> implements Observer<T>, Disposable {
        Observer<? super T> downstream;
        Disposable upstream;

        DetachObserver(Observer<? super T> observer) {
            this.downstream = observer;
        }

        public void dispose() {
            Disposable disposable = this.upstream;
            this.upstream = EmptyComponent.INSTANCE;
            this.downstream = EmptyComponent.asObserver();
            disposable.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            Observer<? super T> observer = this.downstream;
            this.upstream = EmptyComponent.INSTANCE;
            this.downstream = EmptyComponent.asObserver();
            observer.onError(th);
        }

        public void onComplete() {
            Observer<? super T> observer = this.downstream;
            this.upstream = EmptyComponent.INSTANCE;
            this.downstream = EmptyComponent.asObserver();
            observer.onComplete();
        }
    }
}
