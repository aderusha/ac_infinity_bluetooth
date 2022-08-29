package p014io.reactivex.internal.operators.observable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Function;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.functions.ObjectHelper;
import p014io.reactivex.internal.observers.QueueDrainObserver;
import p014io.reactivex.internal.queue.MpscLinkedQueue;
import p014io.reactivex.internal.util.NotificationLite;
import p014io.reactivex.observers.DisposableObserver;
import p014io.reactivex.observers.SerializedObserver;
import p014io.reactivex.plugins.RxJavaPlugins;
import p014io.reactivex.subjects.UnicastSubject;

/* renamed from: io.reactivex.internal.operators.observable.ObservableWindowBoundarySelector */
public final class ObservableWindowBoundarySelector<T, B, V> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int bufferSize;
    final Function<? super B, ? extends ObservableSource<V>> close;
    final ObservableSource<B> open;

    public ObservableWindowBoundarySelector(ObservableSource<T> observableSource, ObservableSource<B> observableSource2, Function<? super B, ? extends ObservableSource<V>> function, int i) {
        super(observableSource);
        this.open = observableSource2;
        this.close = function;
        this.bufferSize = i;
    }

    public void subscribeActual(Observer<? super Observable<T>> observer) {
        this.source.subscribe(new WindowBoundaryMainObserver(new SerializedObserver(observer), this.open, this.close, this.bufferSize));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableWindowBoundarySelector$WindowBoundaryMainObserver */
    static final class WindowBoundaryMainObserver<T, B, V> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {
        final AtomicReference<Disposable> boundary = new AtomicReference<>();
        final int bufferSize;
        final Function<? super B, ? extends ObservableSource<V>> close;
        final ObservableSource<B> open;
        final CompositeDisposable resources;
        final AtomicBoolean stopWindows;
        Disposable upstream;
        final AtomicLong windows;

        /* renamed from: ws */
        final List<UnicastSubject<T>> f1152ws;

        public void accept(Observer<? super Observable<T>> observer, Object obj) {
        }

        WindowBoundaryMainObserver(Observer<? super Observable<T>> observer, ObservableSource<B> observableSource, Function<? super B, ? extends ObservableSource<V>> function, int i) {
            super(observer, new MpscLinkedQueue());
            AtomicLong atomicLong = new AtomicLong();
            this.windows = atomicLong;
            this.stopWindows = new AtomicBoolean();
            this.open = observableSource;
            this.close = function;
            this.bufferSize = i;
            this.resources = new CompositeDisposable();
            this.f1152ws = new ArrayList();
            atomicLong.lazySet(1);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
                if (!this.stopWindows.get()) {
                    OperatorWindowBoundaryOpenObserver operatorWindowBoundaryOpenObserver = new OperatorWindowBoundaryOpenObserver(this);
                    if (this.boundary.compareAndSet((Object) null, operatorWindowBoundaryOpenObserver)) {
                        this.open.subscribe(operatorWindowBoundaryOpenObserver);
                    }
                }
            }
        }

        public void onNext(T t) {
            if (fastEnter()) {
                for (UnicastSubject<T> onNext : this.f1152ws) {
                    onNext.onNext(t);
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            if (this.windows.decrementAndGet() == 0) {
                this.resources.dispose();
            }
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                if (enter()) {
                    drainLoop();
                }
                if (this.windows.decrementAndGet() == 0) {
                    this.resources.dispose();
                }
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void error(Throwable th) {
            this.upstream.dispose();
            this.resources.dispose();
            onError(th);
        }

        public void dispose() {
            if (this.stopWindows.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.boundary);
                if (this.windows.decrementAndGet() == 0) {
                    this.upstream.dispose();
                }
            }
        }

        public boolean isDisposed() {
            return this.stopWindows.get();
        }

        /* access modifiers changed from: package-private */
        public void disposeBoundary() {
            this.resources.dispose();
            DisposableHelper.dispose(this.boundary);
        }

        /* access modifiers changed from: package-private */
        public void drainLoop() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.queue;
            Observer observer = this.downstream;
            List<UnicastSubject<T>> list = this.f1152ws;
            int i = 1;
            while (true) {
                boolean z = this.done;
                Object poll = mpscLinkedQueue.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    disposeBoundary();
                    Throwable th = this.error;
                    if (th != null) {
                        for (UnicastSubject<T> onError : list) {
                            onError.onError(th);
                        }
                    } else {
                        for (UnicastSubject<T> onComplete : list) {
                            onComplete.onComplete();
                        }
                    }
                    list.clear();
                    return;
                } else if (z2) {
                    i = leave(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (poll instanceof WindowOperation) {
                    WindowOperation windowOperation = (WindowOperation) poll;
                    if (windowOperation.f1153w != null) {
                        if (list.remove(windowOperation.f1153w)) {
                            windowOperation.f1153w.onComplete();
                            if (this.windows.decrementAndGet() == 0) {
                                disposeBoundary();
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.stopWindows.get()) {
                        UnicastSubject create = UnicastSubject.create(this.bufferSize);
                        list.add(create);
                        observer.onNext(create);
                        try {
                            ObservableSource observableSource = (ObservableSource) ObjectHelper.requireNonNull(this.close.apply(windowOperation.open), "The ObservableSource supplied is null");
                            OperatorWindowBoundaryCloseObserver operatorWindowBoundaryCloseObserver = new OperatorWindowBoundaryCloseObserver(this, create);
                            if (this.resources.add(operatorWindowBoundaryCloseObserver)) {
                                this.windows.getAndIncrement();
                                observableSource.subscribe(operatorWindowBoundaryCloseObserver);
                            }
                        } catch (Throwable th2) {
                            Exceptions.throwIfFatal(th2);
                            this.stopWindows.set(true);
                            observer.onError(th2);
                        }
                    }
                } else {
                    for (UnicastSubject<T> onNext : list) {
                        onNext.onNext(NotificationLite.getValue(poll));
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void open(B b) {
            this.queue.offer(new WindowOperation((UnicastSubject) null, b));
            if (enter()) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        public void close(OperatorWindowBoundaryCloseObserver<T, V> operatorWindowBoundaryCloseObserver) {
            this.resources.delete(operatorWindowBoundaryCloseObserver);
            this.queue.offer(new WindowOperation(operatorWindowBoundaryCloseObserver.f1151w, null));
            if (enter()) {
                drainLoop();
            }
        }
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableWindowBoundarySelector$WindowOperation */
    static final class WindowOperation<T, B> {
        final B open;

        /* renamed from: w */
        final UnicastSubject<T> f1153w;

        WindowOperation(UnicastSubject<T> unicastSubject, B b) {
            this.f1153w = unicastSubject;
            this.open = b;
        }
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableWindowBoundarySelector$OperatorWindowBoundaryOpenObserver */
    static final class OperatorWindowBoundaryOpenObserver<T, B> extends DisposableObserver<B> {
        final WindowBoundaryMainObserver<T, B, ?> parent;

        OperatorWindowBoundaryOpenObserver(WindowBoundaryMainObserver<T, B, ?> windowBoundaryMainObserver) {
            this.parent = windowBoundaryMainObserver;
        }

        public void onNext(B b) {
            this.parent.open(b);
        }

        public void onError(Throwable th) {
            this.parent.error(th);
        }

        public void onComplete() {
            this.parent.onComplete();
        }
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableWindowBoundarySelector$OperatorWindowBoundaryCloseObserver */
    static final class OperatorWindowBoundaryCloseObserver<T, V> extends DisposableObserver<V> {
        boolean done;
        final WindowBoundaryMainObserver<T, ?, V> parent;

        /* renamed from: w */
        final UnicastSubject<T> f1151w;

        OperatorWindowBoundaryCloseObserver(WindowBoundaryMainObserver<T, ?, V> windowBoundaryMainObserver, UnicastSubject<T> unicastSubject) {
            this.parent = windowBoundaryMainObserver;
            this.f1151w = unicastSubject;
        }

        public void onNext(V v) {
            dispose();
            onComplete();
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.parent.error(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.close(this);
            }
        }
    }
}
