package p014io.reactivex.internal.operators.single;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.SingleSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Action;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.single.SingleDoOnDispose */
public final class SingleDoOnDispose<T> extends Single<T> {
    final Action onDispose;
    final SingleSource<T> source;

    public SingleDoOnDispose(SingleSource<T> singleSource, Action action) {
        this.source = singleSource;
        this.onDispose = action;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.source.subscribe(new DoOnDisposeObserver(singleObserver, this.onDispose));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleDoOnDispose$DoOnDisposeObserver */
    static final class DoOnDisposeObserver<T> extends AtomicReference<Action> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -8583764624474935784L;
        final SingleObserver<? super T> downstream;
        Disposable upstream;

        DoOnDisposeObserver(SingleObserver<? super T> singleObserver, Action action) {
            this.downstream = singleObserver;
            lazySet(action);
        }

        public void dispose() {
            Action action = (Action) getAndSet((Object) null);
            if (action != null) {
                try {
                    action.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
                this.upstream.dispose();
            }
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

        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }
}
