package p014io.reactivex.internal.operators.mixed;

import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.functions.ObjectHelper;

/* renamed from: io.reactivex.internal.operators.mixed.MaybeFlatMapObservable */
public final class MaybeFlatMapObservable<T, R> extends Observable<R> {
    final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
    final MaybeSource<T> source;

    public MaybeFlatMapObservable(MaybeSource<T> maybeSource, Function<? super T, ? extends ObservableSource<? extends R>> function) {
        this.source = maybeSource;
        this.mapper = function;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super R> observer) {
        FlatMapObserver flatMapObserver = new FlatMapObserver(observer, this.mapper);
        observer.onSubscribe(flatMapObserver);
        this.source.subscribe(flatMapObserver);
    }

    /* renamed from: io.reactivex.internal.operators.mixed.MaybeFlatMapObservable$FlatMapObserver */
    static final class FlatMapObserver<T, R> extends AtomicReference<Disposable> implements Observer<R>, MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -8948264376121066672L;
        final Observer<? super R> downstream;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;

        FlatMapObserver(Observer<? super R> observer, Function<? super T, ? extends ObservableSource<? extends R>> function) {
            this.downstream = observer;
            this.mapper = function;
        }

        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }

        public void onSuccess(T t) {
            try {
                ((ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher")).subscribe(this);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }
    }
}
