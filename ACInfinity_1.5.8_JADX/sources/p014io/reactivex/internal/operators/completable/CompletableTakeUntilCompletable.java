package p014io.reactivex.internal.operators.completable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.completable.CompletableTakeUntilCompletable */
public final class CompletableTakeUntilCompletable extends Completable {
    final CompletableSource other;
    final Completable source;

    public CompletableTakeUntilCompletable(Completable completable, CompletableSource completableSource) {
        this.source = completable;
        this.other = completableSource;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(CompletableObserver completableObserver) {
        TakeUntilMainObserver takeUntilMainObserver = new TakeUntilMainObserver(completableObserver);
        completableObserver.onSubscribe(takeUntilMainObserver);
        this.other.subscribe(takeUntilMainObserver.other);
        this.source.subscribe((CompletableObserver) takeUntilMainObserver);
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableTakeUntilCompletable$TakeUntilMainObserver */
    static final class TakeUntilMainObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 3533011714830024923L;
        final CompletableObserver downstream;
        final AtomicBoolean once = new AtomicBoolean();
        final OtherObserver other = new OtherObserver(this);

        TakeUntilMainObserver(CompletableObserver completableObserver) {
            this.downstream = completableObserver;
        }

        public void dispose() {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this);
                DisposableHelper.dispose(this.other);
            }
        }

        public boolean isDisposed() {
            return this.once.get();
        }

        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }

        public void onComplete() {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.other);
                this.downstream.onComplete();
            }
        }

        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.other);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        /* access modifiers changed from: package-private */
        public void innerComplete() {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this);
                this.downstream.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        public void innerError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        /* renamed from: io.reactivex.internal.operators.completable.CompletableTakeUntilCompletable$TakeUntilMainObserver$OtherObserver */
        static final class OtherObserver extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = 5176264485428790318L;
            final TakeUntilMainObserver parent;

            OtherObserver(TakeUntilMainObserver takeUntilMainObserver) {
                this.parent = takeUntilMainObserver;
            }

            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            public void onComplete() {
                this.parent.innerComplete();
            }

            public void onError(Throwable th) {
                this.parent.innerError(th);
            }
        }
    }
}
