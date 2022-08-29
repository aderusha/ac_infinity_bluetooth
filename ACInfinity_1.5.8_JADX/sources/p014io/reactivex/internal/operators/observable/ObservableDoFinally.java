package p014io.reactivex.internal.operators.observable;

import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.exceptions.Exceptions;
import p014io.reactivex.functions.Action;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.fuseable.QueueDisposable;
import p014io.reactivex.internal.observers.BasicIntQueueDisposable;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.observable.ObservableDoFinally */
public final class ObservableDoFinally<T> extends AbstractObservableWithUpstream<T, T> {
    final Action onFinally;

    public ObservableDoFinally(ObservableSource<T> observableSource, Action action) {
        super(observableSource);
        this.onFinally = action;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new DoFinallyObserver(observer, this.onFinally));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableDoFinally$DoFinallyObserver */
    static final class DoFinallyObserver<T> extends BasicIntQueueDisposable<T> implements Observer<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final Observer<? super T> downstream;
        final Action onFinally;

        /* renamed from: qd */
        QueueDisposable<T> f1139qd;
        boolean syncFused;
        Disposable upstream;

        DoFinallyObserver(Observer<? super T> observer, Action action) {
            this.downstream = observer;
            this.onFinally = action;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                if (disposable instanceof QueueDisposable) {
                    this.f1139qd = (QueueDisposable) disposable;
                }
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        public void dispose() {
            this.upstream.dispose();
            runFinally();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public int requestFusion(int i) {
            QueueDisposable<T> queueDisposable = this.f1139qd;
            boolean z = false;
            if (queueDisposable == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = queueDisposable.requestFusion(i);
            if (requestFusion != 0) {
                if (requestFusion == 1) {
                    z = true;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        public void clear() {
            this.f1139qd.clear();
        }

        public boolean isEmpty() {
            return this.f1139qd.isEmpty();
        }

        public T poll() throws Exception {
            T poll = this.f1139qd.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        /* access modifiers changed from: package-private */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            }
        }
    }
}
