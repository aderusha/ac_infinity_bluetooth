package p014io.reactivex.internal.operators.completable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.Scheduler;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.util.ExceptionHelper;
import p014io.reactivex.plugins.RxJavaPlugins;

/* renamed from: io.reactivex.internal.operators.completable.CompletableTimeout */
public final class CompletableTimeout extends Completable {
    final CompletableSource other;
    final Scheduler scheduler;
    final CompletableSource source;
    final long timeout;
    final TimeUnit unit;

    public CompletableTimeout(CompletableSource completableSource, long j, TimeUnit timeUnit, Scheduler scheduler2, CompletableSource completableSource2) {
        this.source = completableSource;
        this.timeout = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
        this.other = completableSource2;
    }

    public void subscribeActual(CompletableObserver completableObserver) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        completableObserver.onSubscribe(compositeDisposable);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        compositeDisposable.add(this.scheduler.scheduleDirect(new DisposeTask(atomicBoolean, compositeDisposable, completableObserver), this.timeout, this.unit));
        this.source.subscribe(new TimeOutObserver(compositeDisposable, atomicBoolean, completableObserver));
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableTimeout$TimeOutObserver */
    static final class TimeOutObserver implements CompletableObserver {
        private final CompletableObserver downstream;
        private final AtomicBoolean once;
        private final CompositeDisposable set;

        TimeOutObserver(CompositeDisposable compositeDisposable, AtomicBoolean atomicBoolean, CompletableObserver completableObserver) {
            this.set = compositeDisposable;
            this.once = atomicBoolean;
            this.downstream = completableObserver;
        }

        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
        }

        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.downstream.onComplete();
            }
        }
    }

    /* renamed from: io.reactivex.internal.operators.completable.CompletableTimeout$DisposeTask */
    final class DisposeTask implements Runnable {
        final CompletableObserver downstream;
        private final AtomicBoolean once;
        final CompositeDisposable set;

        DisposeTask(AtomicBoolean atomicBoolean, CompositeDisposable compositeDisposable, CompletableObserver completableObserver) {
            this.once = atomicBoolean;
            this.set = compositeDisposable;
            this.downstream = completableObserver;
        }

        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.set.clear();
                if (CompletableTimeout.this.other == null) {
                    this.downstream.onError(new TimeoutException(ExceptionHelper.timeoutMessage(CompletableTimeout.this.timeout, CompletableTimeout.this.unit)));
                } else {
                    CompletableTimeout.this.other.subscribe(new DisposeObserver());
                }
            }
        }

        /* renamed from: io.reactivex.internal.operators.completable.CompletableTimeout$DisposeTask$DisposeObserver */
        final class DisposeObserver implements CompletableObserver {
            DisposeObserver() {
            }

            public void onSubscribe(Disposable disposable) {
                DisposeTask.this.set.add(disposable);
            }

            public void onError(Throwable th) {
                DisposeTask.this.set.dispose();
                DisposeTask.this.downstream.onError(th);
            }

            public void onComplete() {
                DisposeTask.this.set.dispose();
                DisposeTask.this.downstream.onComplete();
            }
        }
    }
}
