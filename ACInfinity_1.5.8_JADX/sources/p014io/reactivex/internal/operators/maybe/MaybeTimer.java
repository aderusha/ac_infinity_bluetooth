package p014io.reactivex.internal.operators.maybe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeObserver;
import p014io.reactivex.Scheduler;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.internal.disposables.DisposableHelper;

/* renamed from: io.reactivex.internal.operators.maybe.MaybeTimer */
public final class MaybeTimer extends Maybe<Long> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    public MaybeTimer(long j, TimeUnit timeUnit, Scheduler scheduler2) {
        this.delay = j;
        this.unit = timeUnit;
        this.scheduler = scheduler2;
    }

    /* access modifiers changed from: protected */
    public void subscribeActual(MaybeObserver<? super Long> maybeObserver) {
        TimerDisposable timerDisposable = new TimerDisposable(maybeObserver);
        maybeObserver.onSubscribe(timerDisposable);
        timerDisposable.setFuture(this.scheduler.scheduleDirect(timerDisposable, this.delay, this.unit));
    }

    /* renamed from: io.reactivex.internal.operators.maybe.MaybeTimer$TimerDisposable */
    static final class TimerDisposable extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 2875964065294031672L;
        final MaybeObserver<? super Long> downstream;

        TimerDisposable(MaybeObserver<? super Long> maybeObserver) {
            this.downstream = maybeObserver;
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
