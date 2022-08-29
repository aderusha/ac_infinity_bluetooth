package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import p014io.reactivex.Scheduler;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, mo27512d2 = {"asCoroutineDispatcher", "Lkotlinx/coroutines/rx2/SchedulerCoroutineDispatcher;", "Lio/reactivex/Scheduler;", "kotlinx-coroutines-rx2"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RxScheduler.kt */
public final class RxSchedulerKt {
    public static final SchedulerCoroutineDispatcher asCoroutineDispatcher(Scheduler scheduler) {
        return new SchedulerCoroutineDispatcher(scheduler);
    }
}
