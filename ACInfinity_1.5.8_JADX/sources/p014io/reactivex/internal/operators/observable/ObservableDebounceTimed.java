package p014io.reactivex.internal.operators.observable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.Scheduler;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.observers.SerializedObserver;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.observable.ObservableDebounceTimed */
public final class ObservableDebounceTimed<T> extends AbstractObservableWithUpstream<T, T> {
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    public ObservableDebounceTimed(ObservableSource<T> observableSource, long j, TimeUnit timeUnit, Scheduler scheduler2) {
        super(observableSource);
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
    }

    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DebounceTimedObserver(new SerializedObserver(observer), this.timeout, this.unit, this.scheduler.createWorker()));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableDebounceTimed$DebounceTimedObserver */
    static final class DebounceTimedObserver<T> implements Observer<T>, Disposable {
        boolean done;
        final Observer<? super T> downstream;
        volatile long index;
        final long timeout;
        Disposable timer;
        final TimeUnit unit;
        Disposable upstream;
        final Scheduler.Worker worker;

        DebounceTimedObserver(Observer<? super T> observer, long j, TimeUnit timeUnit, Scheduler.Worker worker2) {
            this.downstream = observer;
            this.timeout = j;
            this.unit = timeUnit;
            this.worker = worker2;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.index + 1;
                this.index = j;
                Disposable disposable = this.timer;
                if (disposable != null) {
                    disposable.dispose();
                }
                DebounceEmitter debounceEmitter = new DebounceEmitter(t, j, this);
                this.timer = debounceEmitter;
                debounceEmitter.setResource(this.worker.schedule(debounceEmitter, this.timeout, this.unit));
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            Disposable disposable = this.timer;
            if (disposable != null) {
                disposable.dispose();
            }
            this.done = true;
            this.downstream.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                Disposable disposable = this.timer;
                if (disposable != null) {
                    disposable.dispose();
                }
                DebounceEmitter debounceEmitter = (DebounceEmitter) disposable;
                if (debounceEmitter != null) {
                    debounceEmitter.run();
                }
                this.downstream.onComplete();
                this.worker.dispose();
            }
        }

        public void dispose() {
            this.upstream.dispose();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }

        /* access modifiers changed from: package-private */
        public void emit(long j, T t, DebounceEmitter<T> debounceEmitter) {
            if (j == this.index) {
                this.downstream.onNext(t);
                debounceEmitter.dispose();
            }
        }
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableDebounceTimed$DebounceEmitter */
    static final class DebounceEmitter<T> extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final DebounceTimedObserver<T> parent;
        final T value;

        DebounceEmitter(T t, long j, DebounceTimedObserver<T> debounceTimedObserver) {
            this.value = t;
            this.idx = j;
            this.parent = debounceTimedObserver;
        }

        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.emit(this.idx, this.value, this);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }
    }
}
