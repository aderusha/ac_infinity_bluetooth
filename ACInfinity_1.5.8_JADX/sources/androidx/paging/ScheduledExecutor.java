package androidx.paging;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import p014io.reactivex.Scheduler;
import p014io.reactivex.schedulers.Schedulers;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0001¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/ScheduledExecutor;", "Lio/reactivex/Scheduler;", "Ljava/util/concurrent/Executor;", "scheduler", "(Lio/reactivex/Scheduler;)V", "executor", "(Ljava/util/concurrent/Executor;)V", "createWorker", "Lio/reactivex/Scheduler$Worker;", "execute", "", "command", "Ljava/lang/Runnable;", "paging-rxjava2_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ScheduledExecutor.kt */
public final class ScheduledExecutor extends Scheduler implements Executor {
    private final Executor executor;
    private final Scheduler scheduler;

    public ScheduledExecutor(Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(scheduler2, "scheduler");
        final Scheduler.Worker createWorker = scheduler2.createWorker();
        Intrinsics.checkNotNullExpressionValue(createWorker, "scheduler.createWorker()");
        this.executor = new Executor() {
            public final void execute(Runnable runnable) {
                createWorker.schedule(runnable);
            }
        };
        this.scheduler = scheduler2;
    }

    public ScheduledExecutor(Executor executor2) {
        Intrinsics.checkNotNullParameter(executor2, "executor");
        this.executor = executor2;
        Scheduler from = Schedulers.from(executor2);
        Intrinsics.checkNotNullExpressionValue(from, "Schedulers.from(executor)");
        this.scheduler = from;
    }

    public Scheduler.Worker createWorker() {
        Scheduler.Worker createWorker = this.scheduler.createWorker();
        Intrinsics.checkNotNullExpressionValue(createWorker, "scheduler.createWorker()");
        return createWorker;
    }

    public void execute(Runnable runnable) {
        Intrinsics.checkNotNullParameter(runnable, "command");
        this.executor.execute(runnable);
    }
}
