package p014io.reactivex.internal.operators.completable;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.internal.disposables.EmptyDisposable;
import p014io.reactivex.internal.disposables.SequentialDisposable;
import p014io.reactivex.internal.functions.ObjectHelper;

/* renamed from: io.reactivex.internal.operators.completable.CompletableConcatIterable */
public final class CompletableConcatIterable extends Completable {
    final Iterable<? extends CompletableSource> sources;

    public CompletableConcatIterable(Iterable<? extends CompletableSource> iterable) {
        this.sources = iterable;
    }

    public void subscribeActual(CompletableObserver completableObserver) {
        try {
            ConcatInnerObserver concatInnerObserver = new ConcatInnerObserver(completableObserver, (Iterator) ObjectHelper.requireNonNull(this.sources.iterator(), "The iterator returned is null"));
            completableObserver.onSubscribe(concatInnerObserver.f1097sd);
            concatInnerObserver.next();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, completableObserver);
        }
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableConcatIterable$ConcatInnerObserver */
    static final class ConcatInnerObserver extends AtomicInteger implements CompletableObserver {
        private static final long serialVersionUID = -7965400327305809232L;
        final CompletableObserver downstream;

        /* renamed from: sd */
        final SequentialDisposable f1097sd = new SequentialDisposable();
        final Iterator<? extends CompletableSource> sources;

        ConcatInnerObserver(CompletableObserver completableObserver, Iterator<? extends CompletableSource> it) {
            this.downstream = completableObserver;
            this.sources = it;
        }

        public void onSubscribe(Disposable disposable) {
            this.f1097sd.replace(disposable);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            next();
        }

        /* access modifiers changed from: package-private */
        public void next() {
            if (!this.f1097sd.isDisposed() && getAndIncrement() == 0) {
                Iterator<? extends CompletableSource> it = this.sources;
                while (!this.f1097sd.isDisposed()) {
                    try {
                        if (!it.hasNext()) {
                            this.downstream.onComplete();
                            return;
                        }
                        try {
                            ((CompletableSource) ObjectHelper.requireNonNull(it.next(), "The CompletableSource returned is null")).subscribe(this);
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.downstream.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        this.downstream.onError(th2);
                        return;
                    }
                }
            }
        }
    }
}
