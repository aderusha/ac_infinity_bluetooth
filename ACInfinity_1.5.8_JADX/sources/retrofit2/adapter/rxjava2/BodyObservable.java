package retrofit2.adapter.rxjava2;

import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Response;

final class BodyObservable<T> extends Observable<T> {
    private final Observable<Response<T>> upstream;

    BodyObservable(Observable<Response<T>> observable) {
        this.upstream = observable;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        this.upstream.subscribe(new BodyObserver(observer));
    }

    private static class BodyObserver<R> implements Observer<Response<R>> {
        private final Observer<? super R> observer;
        private boolean terminated;

        BodyObserver(Observer<? super R> observer2) {
            this.observer = observer2;
        }

        public void onSubscribe(Disposable disposable) {
            this.observer.onSubscribe(disposable);
        }

        public void onNext(Response<R> response) {
            if (response.isSuccessful()) {
                this.observer.onNext(response.body());
                return;
            }
            this.terminated = true;
            HttpException httpException = new HttpException(response);
            try {
                this.observer.onError(httpException);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(new CompositeException(httpException, th));
            }
        }

        public void onComplete() {
            if (!this.terminated) {
                this.observer.onComplete();
            }
        }

        public void onError(Throwable th) {
            if (!this.terminated) {
                this.observer.onError(th);
                return;
            }
            AssertionError assertionError = new AssertionError("This should never happen! Report as a bug with the full stacktrace.");
            assertionError.initCause(th);
            RxJavaPlugins.onError(assertionError);
        }
    }
}
