package p014io.reactivex.internal.operators.maybe;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.CompositeException;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.functions.ObjectHelper;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeOnErrorNext */
public final class MaybeOnErrorNext<T> extends AbstractMaybeWithUpstream<T, T> {
    final boolean allowFatal;
    final Function<? super Throwable, ? extends MaybeSource<? extends T>> resumeFunction;

    public MaybeOnErrorNext(MaybeSource<T> maybeSource, Function<? super Throwable, ? extends MaybeSource<? extends T>> function, boolean z) {
        super(maybeSource);
        this.resumeFunction = function;
        this.allowFatal = z;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new OnErrorNextMaybeObserver(maybeObserver, this.resumeFunction, this.allowFatal));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeOnErrorNext$OnErrorNextMaybeObserver */
    static final class OnErrorNextMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 2026620218879969836L;
        final boolean allowFatal;
        final MaybeObserver<? super T> downstream;
        final Function<? super Throwable, ? extends MaybeSource<? extends T>> resumeFunction;

        OnErrorNextMaybeObserver(MaybeObserver<? super T> maybeObserver, Function<? super Throwable, ? extends MaybeSource<? extends T>> function, boolean z) {
            this.downstream = maybeObserver;
            this.resumeFunction = function;
            this.allowFatal = z;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                this.downstream.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        public void onError(Throwable th) {
            if (this.allowFatal || (th instanceof Exception)) {
                try {
                    MaybeSource maybeSource = (MaybeSource) ObjectHelper.requireNonNull(this.resumeFunction.apply(th), "The resumeFunction returned a null MaybeSource");
                    DisposableHelper.replace(this, (Disposable) null);
                    maybeSource.subscribe(new NextMaybeObserver(this.downstream, this));
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    this.downstream.onError(new CompositeException(th, th2));
                }
            } else {
                this.downstream.onError(th);
            }
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        /* renamed from: io.reactivex.internal.operators.maybe.MaybeOnErrorNext$OnErrorNextMaybeObserver$NextMaybeObserver */
        static final class NextMaybeObserver<T> implements MaybeObserver<T> {
            final MaybeObserver<? super T> downstream;
            final AtomicReference<Disposable> upstream;

            NextMaybeObserver(MaybeObserver<? super T> maybeObserver, AtomicReference<Disposable> atomicReference) {
                this.downstream = maybeObserver;
                this.upstream = atomicReference;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this.upstream, disposable);
            }

            public void onSuccess(T t) {
                this.downstream.onSuccess(t);
            }

            public void onError(Throwable th) {
                this.downstream.onError(th);
            }

            public void onComplete() {
                this.downstream.onComplete();
            }
        }
    }
}
