package p014io.reactivex.internal.operators.observable;

import java.util.Iterator;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.internal.disposables.EmptyDisposable;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.observers.BasicQueueDisposable;

/* renamed from: io.reactivex.internal.operators.observable.ObservableFromIterable */
public final class ObservableFromIterable<T> extends Observable<T> {
    final Iterable<? extends T> source;

    public ObservableFromIterable(Iterable<? extends T> iterable) {
        this.source = iterable;
    }

    public void subscribeActual(Observer<? super T> observer) {
        try {
            Iterator<? extends T> it = this.source.iterator();
            try {
                if (!it.hasNext()) {
                    EmptyDisposable.complete((Observer<?>) observer);
                    return;
                }
                FromIterableDisposable fromIterableDisposable = new FromIterableDisposable(observer, it);
                observer.onSubscribe(fromIterableDisposable);
                if (!fromIterableDisposable.fusionMode) {
                    fromIterableDisposable.run();
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, (Observer<?>) observer);
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            EmptyDisposable.error(th2, (Observer<?>) observer);
        }
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableFromIterable$FromIterableDisposable */
    static final class FromIterableDisposable<T> extends BasicQueueDisposable<T> {
        boolean checkNext;
        volatile boolean disposed;
        boolean done;
        final Observer<? super T> downstream;
        boolean fusionMode;

        /* renamed from: it */
        final Iterator<? extends T> f1141it;

        FromIterableDisposable(Observer<? super T> observer, Iterator<? extends T> it) {
            this.downstream = observer;
            this.f1141it = it;
        }

        /* access modifiers changed from: package-private */
        public void run() {
            while (!isDisposed()) {
                try {
                    this.downstream.onNext(ObjectHelper.requireNonNull(this.f1141it.next(), "The iterator returned a null value"));
                    if (!isDisposed()) {
                        try {
                            if (!this.f1141it.hasNext()) {
                                if (!isDisposed()) {
                                    this.downstream.onComplete();
                                    return;
                                }
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.downstream.onError(th);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    this.downstream.onError(th2);
                    return;
                }
            }
        }

        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.fusionMode = true;
            return 1;
        }

        public T poll() {
            if (this.done) {
                return null;
            }
            if (!this.checkNext) {
                this.checkNext = true;
            } else if (!this.f1141it.hasNext()) {
                this.done = true;
                return null;
            }
            return ObjectHelper.requireNonNull(this.f1141it.next(), "The iterator returned a null value");
        }

        public boolean isEmpty() {
            return this.done;
        }

        public void clear() {
            this.done = true;
        }

        public void dispose() {
            this.disposed = true;
        }

        public boolean isDisposed() {
            return this.disposed;
        }
    }
}
