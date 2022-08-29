package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.intrinsics.CancellableKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\nR\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b¸\u0006\u0000"}, mo27512d2 = {"kotlin/coroutines/ContinuationKt$Continuation$1", "Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "resumeWith", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "kotlin-stdlib"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: kotlinx.coroutines.reactive.FlowSubscription$createInitialContinuation$$inlined$Continuation$1 */
/* compiled from: Continuation.kt */
public final class C3914x4b4643a3 implements Continuation<Unit> {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ FlowSubscription this$0;

    public C3914x4b4643a3(CoroutineContext coroutineContext, FlowSubscription flowSubscription) {
        this.$context = coroutineContext;
        this.this$0 = flowSubscription;
    }

    public CoroutineContext getContext() {
        return this.$context;
    }

    public void resumeWith(Object obj) {
        CancellableKt.startCoroutineCancellable(new C3915x3e474275(this.this$0), this.this$0);
    }
}
