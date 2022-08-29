package p014io.reactivex.internal.schedulers;

import java.util.concurrent.TimeUnit;
import p014io.reactivex.Scheduler;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.disposables.Disposables;

/* renamed from: io.reactivex.internal.schedulers.ImmediateThinScheduler */
public final class ImmediateThinScheduler extends Scheduler {
    static final Disposable DISPOSED;
    public static final Scheduler INSTANCE = new ImmediateThinScheduler();
    static final Scheduler.Worker WORKER = new ImmediateThinWorker();

    static {
        Disposable empty = Disposables.empty();
        DISPOSED = empty;
        empty.dispose();
    }

    private ImmediateThinScheduler() {
    }

    public Disposable scheduleDirect(Runnable runnable) {
        runnable.run();
        return DISPOSED;
    }

    public Disposable scheduleDirect(Runnable runnable, long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException("This scheduler doesn't support delayed execution");
    }

    public Disposable schedulePeriodicallyDirect(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException("This scheduler doesn't support periodic execution");
    }

    public Scheduler.Worker createWorker() {
        return WORKER;
    }

    /* renamed from: io.reactivex.internal.schedulers.ImmediateThinScheduler$ImmediateThinWorker */
    static final class ImmediateThinWorker extends Scheduler.Worker {
        public void dispose() {
        }

        public boolean isDisposed() {
            return false;
        }

        ImmediateThinWorker() {
        }

        public Disposable schedule(Runnable runnable) {
            runnable.run();
            return ImmediateThinScheduler.DISPOSED;
        }

        public Disposable schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            throw new UnsupportedOperationException("This scheduler doesn't support delayed execution");
        }

        public Disposable schedulePeriodically(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            throw new UnsupportedOperationException("This scheduler doesn't support periodic execution");
        }
    }
}
