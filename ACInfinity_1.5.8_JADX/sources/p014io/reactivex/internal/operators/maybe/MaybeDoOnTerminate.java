package p014io.reactivex.internal.operators.maybe;

import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Action;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeDoOnTerminate */
public final class MaybeDoOnTerminate<T> extends Maybe<T> {
    final Action onTerminate;
    final MaybeSource<T> source;

    public MaybeDoOnTerminate(MaybeSource<T> maybeSource, Action action) {
        this.source = maybeSource;
        this.onTerminate = action;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new DoOnTerminate(maybeObserver));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeDoOnTerminate$DoOnTerminate */
    final class DoOnTerminate implements MaybeObserver<T> {
        final MaybeObserver<? super T> downstream;

        DoOnTerminate(MaybeObserver<? super T> maybeObserver) {
            this.downstream = maybeObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.downstream.onSubscribe(disposable);
        }

        public void onSuccess(T t) {
            try {
                MaybeDoOnTerminate.this.onTerminate.run();
                this.downstream.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        public void onError(Throwable th) {
            try {
                MaybeDoOnTerminate.this.onTerminate.run();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.downstream.onError(th);
        }

        public void onComplete() {
            try {
                MaybeDoOnTerminate.this.onTerminate.run();
                this.downstream.onComplete();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }
    }
}
