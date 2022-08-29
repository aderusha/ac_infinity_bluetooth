package p014io.reactivex.internal.operators.single;

import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.SingleSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.BiConsumer;

/* renamed from: io.reactivex.internal.operators.single.SingleDoOnEvent */
public final class SingleDoOnEvent<T> extends Single<T> {
    final BiConsumer<? super T, ? super Throwable> onEvent;
    final SingleSource<T> source;

    public SingleDoOnEvent(SingleSource<T> singleSource, BiConsumer<? super T, ? super Throwable> biConsumer) {
        this.source = singleSource;
        this.onEvent = biConsumer;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new DoOnEvent(singleObserver));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleDoOnEvent$DoOnEvent */
    final class DoOnEvent implements SingleObserver<T> {
        private final SingleObserver<? super T> downstream;

        DoOnEvent(SingleObserver<? super T> singleObserver) {
            this.downstream = singleObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.downstream.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            try {
                SingleDoOnEvent.this.onEvent.accept(t, null);
                this.downstream.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        public void onError(Throwable th) {
            try {
                SingleDoOnEvent.this.onEvent.accept(null, th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.downstream.onError(th);
        }
    }
}
