package p014io.reactivex.internal.operators.observable;

import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.SequentialDisposable;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.observable.ObservableDelaySubscriptionOther */
public final class ObservableDelaySubscriptionOther<T, U> extends Observable<T> {
    final ObservableSource<? extends T> main;
    final ObservableSource<U> other;

    public ObservableDelaySubscriptionOther(ObservableSource<? extends T> observableSource, ObservableSource<U> observableSource2) {
        this.main = observableSource;
        this.other = observableSource2;
    }

    public void subscribeActual(Observer<? super T> observer) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        observer.onSubscribe(sequentialDisposable);
        this.other.subscribe(new DelayObserver(sequentialDisposable, observer));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableDelaySubscriptionOther$DelayObserver */
    final class DelayObserver implements Observer<U> {
        final Observer<? super T> child;
        boolean done;
        final SequentialDisposable serial;

        DelayObserver(SequentialDisposable sequentialDisposable, Observer<? super T> observer) {
            this.serial = sequentialDisposable;
            this.child = observer;
        }

        public void onSubscribe(Disposable disposable) {
            this.serial.update(disposable);
        }

        public void onNext(U u) {
            onComplete();
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.child.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                ObservableDelaySubscriptionOther.this.main.subscribe(new OnComplete());
            }
        }

        /* renamed from: io.reactivex.internal.operators.observable.ObservableDelaySubscriptionOther$DelayObserver$OnComplete */
        final class OnComplete implements Observer<T> {
            OnComplete() {
            }

            public void onSubscribe(Disposable disposable) {
                DelayObserver.this.serial.update(disposable);
            }

            public void onNext(T t) {
                DelayObserver.this.child.onNext(t);
            }

            public void onError(Throwable th) {
                DelayObserver.this.child.onError(th);
            }

            public void onComplete() {
                DelayObserver.this.child.onComplete();
            }
        }
    }
}
