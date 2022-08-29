package p014io.reactivex.internal.operators.single;

import java.util.concurrent.atomic.AtomicInteger;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.SingleSource;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.single.SingleEquals */
public final class SingleEquals<T> extends Single<Boolean> {
    final SingleSource<? extends T> first;
    final SingleSource<? extends T> second;

    public SingleEquals(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2) {
        this.first = singleSource;
        this.second = singleSource2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        AtomicInteger atomicInteger = new AtomicInteger();
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        singleObserver.onSubscribe(compositeDisposable);
        CompositeDisposable compositeDisposable2 = compositeDisposable;
        Object[] objArr = {null, null};
        SingleObserver<? super Boolean> singleObserver2 = singleObserver;
        AtomicInteger atomicInteger2 = atomicInteger;
        this.first.subscribe(new InnerObserver(0, compositeDisposable2, objArr, singleObserver2, atomicInteger2));
        this.second.subscribe(new InnerObserver(1, compositeDisposable2, objArr, singleObserver2, atomicInteger2));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleEquals$InnerObserver */
    static class InnerObserver<T> implements SingleObserver<T> {
        final AtomicInteger count;
        final SingleObserver<? super Boolean> downstream;
        final int index;
        final CompositeDisposable set;
        final Object[] values;

        InnerObserver(int i, CompositeDisposable compositeDisposable, Object[] objArr, SingleObserver<? super Boolean> singleObserver, AtomicInteger atomicInteger) {
            this.index = i;
            this.set = compositeDisposable;
            this.values = objArr;
            this.downstream = singleObserver;
            this.count = atomicInteger;
        }

        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
        }

        public void onSuccess(T t) {
            this.values[this.index] = t;
            if (this.count.incrementAndGet() == 2) {
                SingleObserver<? super Boolean> singleObserver = this.downstream;
                Object[] objArr = this.values;
                singleObserver.onSuccess(Boolean.valueOf(ObjectHelper.equals(objArr[0], objArr[1])));
            }
        }

        public void onError(Throwable th) {
            int i;
            do {
                i = this.count.get();
                if (i >= 2) {
                    RxJavaPlugins.onError(th);
                    return;
                }
            } while (!this.count.compareAndSet(i, 2));
            this.set.dispose();
            this.downstream.onError(th);
        }
    }
}
