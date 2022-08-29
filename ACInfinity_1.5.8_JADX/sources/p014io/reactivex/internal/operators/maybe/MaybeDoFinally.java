package p014io.reactivex.internal.operators.maybe;

import java.util.concurrent.atomic.AtomicInteger;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Action;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeDoFinally */
public final class MaybeDoFinally<T> extends AbstractMaybeWithUpstream<T, T> {
    final Action onFinally;

    public MaybeDoFinally(MaybeSource<T> maybeSource, Action action) {
        super(maybeSource);
        this.onFinally = action;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new DoFinallyObserver(maybeObserver, this.onFinally));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeDoFinally$DoFinallyObserver */
    static final class DoFinallyObserver<T> extends AtomicInteger implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4109457741734051389L;
        final MaybeObserver<? super T> downstream;
        final Action onFinally;
        Disposable upstream;

        DoFinallyObserver(MaybeObserver<? super T> maybeObserver, Action action) {
            this.downstream = maybeObserver;
            this.onFinally = action;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
            runFinally();
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        public void dispose() {
            this.upstream.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        /* access modifiers changed from: package-private */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
