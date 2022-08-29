package retrofit2.adapter.rxjava2;

import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Response;

final class CallExecuteObservable<T> extends Observable<Response<T>> {
    private final Call<T> originalCall;

    CallExecuteObservable(Call<T> call) {
        this.originalCall = call;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super Response<T>> observer) {
        boolean z;
        Call<T> clone = this.originalCall.clone();
        CallDisposable callDisposable = new CallDisposable(clone);
        observer.onSubscribe(callDisposable);
        try {
            Response<T> execute = clone.execute();
            if (!callDisposable.isDisposed()) {
                observer.onNext(execute);
            }
            if (!callDisposable.isDisposed()) {
                try {
                    observer.onComplete();
                } catch (Throwable th) {
                    th = th;
                    z = true;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            z = false;
            Exceptions.throwIfFatal(th);
            if (z) {
                RxJavaPlugins.onError(th);
            } else if (!callDisposable.isDisposed()) {
                try {
                    observer.onError(th);
                } catch (Throwable th3) {
                    Exceptions.throwIfFatal(th3);
                    RxJavaPlugins.onError(new CompositeException(th, th3));
                }
            }
        }
    }

    private static final class CallDisposable implements Disposable {
        private final Call<?> call;
        private volatile boolean disposed;

        CallDisposable(Call<?> call2) {
            this.call = call2;
        }

        public void dispose() {
            this.disposed = true;
            this.call.cancel();
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }
}
