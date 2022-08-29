package p014io.reactivex.internal.operators.completable;

import java.util.concurrent.Callable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;

/* renamed from: io.reactivex.internal.operators.completable.CompletableToSingle */
public final class CompletableToSingle<T> extends Single<T> {
    final T completionValue;
    final Callable<? extends T> completionValueSupplier;
    final CompletableSource source;

    public CompletableToSingle(CompletableSource completableSource, Callable<? extends T> callable, T t) {
        this.source = completableSource;
        this.completionValue = t;
        this.completionValueSupplier = callable;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new ToSingle(singleObserver));
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableToSingle$ToSingle */
    final class ToSingle implements CompletableObserver {
        private final SingleObserver<? super T> observer;

        ToSingle(SingleObserver<? super T> singleObserver) {
            this.observer = singleObserver;
        }

        public void onComplete() {
            T t;
            if (CompletableToSingle.this.completionValueSupplier != null) {
                try {
                    t = CompletableToSingle.this.completionValueSupplier.call();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.observer.onError(th);
                    return;
                }
            } else {
                t = CompletableToSingle.this.completionValue;
            }
            if (t == null) {
                this.observer.onError(new NullPointerException("The value supplied is null"));
            } else {
                this.observer.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            this.observer.onError(th);
        }

        public void onSubscribe(Disposable disposable) {
            this.observer.onSubscribe(disposable);
        }
    }
}
