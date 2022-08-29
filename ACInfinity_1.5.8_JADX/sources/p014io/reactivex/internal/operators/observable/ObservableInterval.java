package p014io.reactivex.internal.operators.observable;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.Scheduler;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;
import p014io.reactivex.internal.schedulers.TrampolineScheduler;

/* renamed from: io.reactivex.internal.operators.observable.ObservableInterval */
public final class ObservableInterval extends Observable<Long> {
    final long initialDelay;
    final long period;
    final Scheduler scheduler;
    final TimeUnit unit;

    public ObservableInterval(long j, long j2, TimeUnit timeUnit, Scheduler scheduler2) {
        this.initialDelay = j;
        this.period = j2;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
    }

    public void subscribeActual(Observer<? super Long> observer) {
        IntervalObserver intervalObserver = new IntervalObserver(observer);
        observer.onSubscribe(intervalObserver);
        Scheduler scheduler2 = this.scheduler;
        if (scheduler2 instanceof TrampolineScheduler) {
            Scheduler.Worker createWorker = scheduler2.createWorker();
            intervalObserver.setResource(createWorker);
            createWorker.schedulePeriodically(intervalObserver, this.initialDelay, this.period, this.unit);
            return;
        }
        intervalObserver.setResource(scheduler2.schedulePeriodicallyDirect(intervalObserver, this.initialDelay, this.period, this.unit));
    }

    /* renamed from: io.reactivex.internal.operators.observable.ObservableInterval$IntervalObserver */
    static final class IntervalObserver extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 346773832286157679L;
        long count;
        final Observer<? super Long> downstream;

        IntervalObserver(Observer<? super Long> observer) {
            this.downstream = observer;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            if (get() != DisposableHelper.DISPOSED) {
                Observer<? super Long> observer = this.downstream;
                long j = this.count;
                this.count = 1 + j;
                observer.onNext(Long.valueOf(j));
            }
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }
    }
}
