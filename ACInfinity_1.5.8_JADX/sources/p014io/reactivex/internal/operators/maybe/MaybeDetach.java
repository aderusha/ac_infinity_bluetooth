package p014io.reactivex.internal.operators.maybe;

import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeDetach */
public final class MaybeDetach<T> extends AbstractMaybeWithUpstream<T, T> {
    public MaybeDetach(MaybeSource<T> maybeSource) {
        super(maybeSource);
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new DetachMaybeObserver(maybeObserver));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeDetach$DetachMaybeObserver */
    static final class DetachMaybeObserver<T> implements MaybeObserver<T>, Disposable {
        MaybeObserver<? super T> downstream;
        Disposable upstream;

        DetachMaybeObserver(MaybeObserver<? super T> maybeObserver) {
            this.downstream = maybeObserver;
        }

        public void dispose() {
            this.downstream = null;
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
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
            this.upstream = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.downstream;
            if (maybeObserver != null) {
                this.downstream = null;
                maybeObserver.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.downstream;
            if (maybeObserver != null) {
                this.downstream = null;
                maybeObserver.onError(th);
            }
        }

        public void onComplete() {
            this.upstream = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.downstream;
            if (maybeObserver != null) {
                this.downstream = null;
                maybeObserver.onComplete();
            }
        }
    }
}
