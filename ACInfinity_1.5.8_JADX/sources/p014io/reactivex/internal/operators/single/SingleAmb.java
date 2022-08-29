package p014io.reactivex.internal.operators.single;

import java.util.concurrent.atomic.AtomicBoolean;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.SingleSource;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.internal.disposables.EmptyDisposable;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.single.SingleAmb */
public final class SingleAmb<T> extends Single<T> {
    private final SingleSource<? extends T>[] sources;
    private final Iterable<? extends SingleSource<? extends T>> sourcesIterable;

    public SingleAmb(SingleSource<? extends T>[] singleSourceArr, Iterable<? extends SingleSource<? extends T>> iterable) {
        this.sources = singleSourceArr;
        this.sourcesIterable = iterable;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        int i;
        SingleSource<? extends T>[] singleSourceArr = this.sources;
        if (singleSourceArr == null) {
            singleSourceArr = new SingleSource[8];
            try {
                i = 0;
                for (SingleSource<? extends T> singleSource : this.sourcesIterable) {
                    if (singleSource == null) {
                        EmptyDisposable.error((Throwable) new NullPointerException("One of the sources is null"), (SingleObserver<?>) singleObserver);
                        return;
                    }
                    if (i == singleSourceArr.length) {
                        SingleSource<? extends T>[] singleSourceArr2 = new SingleSource[((i >> 2) + i)];
                        System.arraycopy(singleSourceArr, 0, singleSourceArr2, 0, i);
                        singleSourceArr = singleSourceArr2;
                    }
                    int i2 = i + 1;
                    singleSourceArr[i] = singleSource;
                    i = i2;
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, (SingleObserver<?>) singleObserver);
                return;
            }
        } else {
            i = singleSourceArr.length;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        singleObserver.onSubscribe(compositeDisposable);
        int i3 = 0;
        while (i3 < i) {
            SingleSource<? extends T> singleSource2 = singleSourceArr[i3];
            if (!compositeDisposable.isDisposed()) {
                if (singleSource2 == null) {
                    compositeDisposable.dispose();
                    NullPointerException nullPointerException = new NullPointerException("One of the sources is null");
                    if (atomicBoolean.compareAndSet(false, true)) {
                        singleObserver.onError(nullPointerException);
                        return;
                    } else {
                        RxJavaPlugins.onError(nullPointerException);
                        return;
                    }
                } else {
                    singleSource2.subscribe(new AmbSingleObserver(singleObserver, compositeDisposable, atomicBoolean));
                    i3++;
                }
            } else {
                return;
            }
        }
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleAmb$AmbSingleObserver */
    static final class AmbSingleObserver<T> implements SingleObserver<T> {
        final SingleObserver<? super T> downstream;
        final CompositeDisposable set;
        Disposable upstream;
        final AtomicBoolean winner;

        AmbSingleObserver(SingleObserver<? super T> singleObserver, CompositeDisposable compositeDisposable, AtomicBoolean atomicBoolean) {
            this.downstream = singleObserver;
            this.set = compositeDisposable;
            this.winner = atomicBoolean;
        }

        public void onSubscribe(Disposable disposable) {
            this.upstream = disposable;
            this.set.add(disposable);
        }

        public void onSuccess(T t) {
            if (this.winner.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onSuccess(t);
            }
        }

        public void onError(Throwable th) {
            if (this.winner.compareAndSet(false, true)) {
                this.set.delete(this.upstream);
                this.set.dispose();
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }
    }
}
