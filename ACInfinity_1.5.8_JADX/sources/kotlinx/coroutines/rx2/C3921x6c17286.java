package kotlinx.coroutines.rx2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo27512d2 = {"<anonymous>", "", "run"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* renamed from: kotlinx.coroutines.rx2.SchedulerCoroutineDispatcher$scheduleResumeAfterDelay$disposable$1 */
/* compiled from: RxScheduler.kt */
final class C3921x6c17286 implements Runnable {
    final /* synthetic */ CancellableContinuation $continuation;
    final /* synthetic */ SchedulerCoroutineDispatcher this$0;

    C3921x6c17286(SchedulerCoroutineDispatcher schedulerCoroutineDispatcher, CancellableContinuation cancellableContinuation) {
        this.this$0 = schedulerCoroutineDispatcher;
        this.$continuation = cancellableContinuation;
    }

    public final void run() {
        this.$continuation.resumeUndispatched(this.this$0, Unit.INSTANCE);
    }
}
