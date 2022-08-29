package p014io.reactivex.internal.operators.observable;

import java.util.NoSuchElementException;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.observable.ObservableElementAt */
public final class ObservableElementAt<T> extends AbstractObservableWithUpstream<T, T> {
    final T defaultValue;
    final boolean errorOnFewer;
    final long index;

    public ObservableElementAt(ObservableSource<T> observableSource, long j, T t, boolean z) {
        super(observableSource);
        this.index = j;
        this.defaultValue = t;
        this.errorOnFewer = z;
    }

    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new ElementAtObserver(observer, this.index, this.defaultValue, this.errorOnFewer));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableElementAt$ElementAtObserver */
    static final class ElementAtObserver<T> implements Observer<T>, Disposable {
        long count;
        final T defaultValue;
        boolean done;
        final Observer<? super T> downstream;
        final boolean errorOnFewer;
        final long index;
        Disposable upstream;

        ElementAtObserver(Observer<? super T> observer, long j, T t, boolean z) {
            this.downstream = observer;
            this.index = j;
            this.defaultValue = t;
            this.errorOnFewer = z;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        public void dispose() {
            this.upstream.dispose();
        }

        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.count;
                if (j == this.index) {
                    this.done = true;
                    this.upstream.dispose();
                    this.downstream.onNext(t);
                    this.downstream.onComplete();
                    return;
                }
                this.count = j + 1;
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                T t = this.defaultValue;
                if (t != null || !this.errorOnFewer) {
                    if (t != null) {
                        this.downstream.onNext(t);
                    }
                    this.downstream.onComplete();
                    return;
                }
                this.downstream.onError(new NoSuchElementException());
            }
        }
    }
}
