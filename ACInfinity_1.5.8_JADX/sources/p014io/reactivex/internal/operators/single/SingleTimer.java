package p014io.reactivex.internal.operators.single;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.Scheduler;
import p014io.reactivex.Single;
import p014io.reactivex.SingleObserver;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.single.SingleTimer */
public final class SingleTimer extends Single<Long> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    public SingleTimer(long j, TimeUnit timeUnit, Scheduler scheduler2) {
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(SingleObserver<? super Long> singleObserver) {
        TimerDisposable timerDisposable = new TimerDisposable(singleObserver);
        singleObserver.onSubscribe(timerDisposable);
        timerDisposable.setFuture(this.scheduler.scheduleDirect(timerDisposable, this.delay, this.unit));
    }

    /* renamed from: io.reactivex.internal.operators.single.SingleTimer$TimerDisposable */
    static final class TimerDisposable extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 8465401857522493082L;
        final SingleObserver<? super Long> downstream;

        TimerDisposable(SingleObserver<? super Long> singleObserver) {
            this.downstream = singleObserver;
        }

        public void run() {
            this.downstream.onSuccess(0L);
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        /* access modifiers changed from: package-private */
        public void setFuture(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }
    }
}
