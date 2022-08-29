package p014io.reactivex.internal.operators.maybe;

import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.fuseable.HasUpstreamMaybeSource;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeContains */
public final class MaybeContains<T> extends Single<Boolean> implements HasUpstreamMaybeSource<T> {
    final MaybeSource<T> source;
    final Object value;

    public MaybeContains(MaybeSource<T> maybeSource, Object obj) {
        this.source = maybeSource;
        this.value = obj;
    }

    public MaybeSource<T> source() {
        return this.source;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.source.subscribe(new ContainsMaybeObserver(singleObserver, this.value));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeContains$ContainsMaybeObserver */
    static final class ContainsMaybeObserver implements MaybeObserver<Object>, Disposable {
        final SingleObserver<? super Boolean> downstream;
        Disposable upstream;
        final Object value;

        ContainsMaybeObserver(SingleObserver<? super Boolean> singleObserver, Object obj) {
            this.downstream = singleObserver;
            this.value = obj;
        }

        public void dispose() {
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

        public void onSuccess(Object obj) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onSuccess(Boolean.valueOf(ObjectHelper.equals(obj, this.value)));
        }

        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onSuccess(false);
        }
    }
}
