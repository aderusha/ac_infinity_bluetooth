package kotlinx.coroutines.rx2;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DisposableHandle;
import p014io.reactivex.Scheduler;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u001c\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0016J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J$\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\n\u0010\f\u001a\u00060\rj\u0002`\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001e\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001e"}, mo27512d2 = {"Lkotlinx/coroutines/rx2/SchedulerCoroutineDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/Delay;", "scheduler", "Lio/reactivex/Scheduler;", "(Lio/reactivex/Scheduler;)V", "getScheduler", "()Lio/reactivex/Scheduler;", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "equals", "", "other", "", "hashCode", "", "invokeOnTimeout", "Lkotlinx/coroutines/DisposableHandle;", "timeMillis", "", "scheduleResumeAfterDelay", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "toString", "", "kotlinx-coroutines-rx2"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RxScheduler.kt */
public final class SchedulerCoroutineDispatcher extends CoroutineDispatcher implements Delay {
    private final Scheduler scheduler;

    public Object delay(long j, Continuation<? super Unit> continuation) {
        return Delay.DefaultImpls.delay(this, j, continuation);
    }

    public final Scheduler getScheduler() {
        return this.scheduler;
    }

    public SchedulerCoroutineDispatcher(Scheduler scheduler2) {
        this.scheduler = scheduler2;
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        this.scheduler.scheduleDirect(runnable);
    }

    public void scheduleResumeAfterDelay(long j, CancellableContinuation<? super Unit> cancellableContinuation) {
        RxAwaitKt.disposeOnCancellation(cancellableContinuation, this.scheduler.scheduleDirect(new C3921x6c17286(this, cancellableContinuation), j, TimeUnit.MILLISECONDS));
    }

    public DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        return new C3920x54f20b78(this.scheduler.scheduleDirect(runnable, j, TimeUnit.MILLISECONDS));
    }

    public String toString() {
        return this.scheduler.toString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof SchedulerCoroutineDispatcher) && ((SchedulerCoroutineDispatcher) obj).scheduler == this.scheduler;
    }

    public int hashCode() {
        return System.identityHashCode(this.scheduler);
    }
}
