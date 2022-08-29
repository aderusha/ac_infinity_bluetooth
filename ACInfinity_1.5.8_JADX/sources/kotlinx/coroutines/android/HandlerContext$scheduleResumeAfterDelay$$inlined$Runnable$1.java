package kotlinx.coroutines.android;

import kotlin.Metadata;
import kotlin.Unit;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo27511d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¨\u0006\u0002"}, mo27512d2 = {"<anonymous>", "", "kotlinx/coroutines/RunnableKt$Runnable$1"}, mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Runnable.kt */
public final class HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1 implements Runnable {
    final /* synthetic */ CancellableContinuation $continuation$inlined;
    final /* synthetic */ HandlerContext this$0;

    public HandlerContext$scheduleResumeAfterDelay$$inlined$Runnable$1(CancellableContinuation cancellableContinuation, HandlerContext handlerContext) {
        this.$continuation$inlined = cancellableContinuation;
        this.this$0 = handlerContext;
    }

    public final void run() {
        this.$continuation$inlined.resumeUndispatched(this.this$0, Unit.INSTANCE);
    }
}
