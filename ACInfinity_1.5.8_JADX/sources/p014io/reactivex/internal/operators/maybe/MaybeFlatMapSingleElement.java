package p014io.reactivex.internal.operators.maybe;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.SingleSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.functions.ObjectHelper;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeFlatMapSingleElement */
public final class MaybeFlatMapSingleElement<T, R> extends Maybe<R> {
    final Function<? super T, ? extends SingleSource<? extends R>> mapper;
    final MaybeSource<T> source;

    public MaybeFlatMapSingleElement(MaybeSource<T> maybeSource, Function<? super T, ? extends SingleSource<? extends R>> function) {
        this.source = maybeSource;
        this.mapper = function;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super R> maybeObserver) {
        this.source.subscribe(new FlatMapMaybeObserver(maybeObserver, this.mapper));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeFlatMapSingleElement$FlatMapMaybeObserver */
    static final class FlatMapMaybeObserver<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4827726964688405508L;
        final MaybeObserver<? super R> downstream;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;

        FlatMapMaybeObserver(MaybeObserver<? super R> maybeObserver, Function<? super T, ? extends SingleSource<? extends R>> function) {
            this.downstream = maybeObserver;
            this.mapper = function;
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
            try {
                ((SingleSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null SingleSource")).subscribe(new FlatMapSingleObserver(this, this.downstream));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                onError(th);
            }
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeFlatMapSingleElement$FlatMapSingleObserver */
    static final class FlatMapSingleObserver<R> implements SingleObserver<R> {
        final MaybeObserver<? super R> downstream;
        final AtomicReference<Disposable> parent;

        FlatMapSingleObserver(AtomicReference<Disposable> atomicReference, MaybeObserver<? super R> maybeObserver) {
            this.parent = atomicReference;
            this.downstream = maybeObserver;
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this.parent, disposable);
        }

        public void onSuccess(R r) {
            this.downstream.onSuccess(r);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }
}
