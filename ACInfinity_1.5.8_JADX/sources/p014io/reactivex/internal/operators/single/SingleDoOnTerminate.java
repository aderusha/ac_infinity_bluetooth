package p014io.reactivex.internal.operators.single;

import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.SingleSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Action;

/* renamed from: io.reactivex.internal.operators.single.SingleDoOnTerminate */
public final class SingleDoOnTerminate<T> extends Single<T> {
    final Action onTerminate;
    final SingleSource<T> source;

    public SingleDoOnTerminate(SingleSource<T> singleSource, Action action) {
        this.source = singleSource;
        this.onTerminate = action;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new DoOnTerminate(singleObserver));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleDoOnTerminate$DoOnTerminate */
    final class DoOnTerminate implements SingleObserver<T> {
        final SingleObserver<? super T> downstream;

        DoOnTerminate(SingleObserver<? super T> singleObserver) {
            this.downstream = singleObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.downstream.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            try {
                SingleDoOnTerminate.this.onTerminate.run();
                this.downstream.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        public void onError(Throwable th) {
            try {
                SingleDoOnTerminate.this.onTerminate.run();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.downstream.onError(th);
        }
    }
}
