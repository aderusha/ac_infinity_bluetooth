package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H@"}, mo27512d2 = {"flowProcessing", "", "T", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.reactive.FlowSubscription", mo28222f = "ReactiveFlow.kt", mo28223i = {0}, mo28224l = {198}, mo28225m = "flowProcessing", mo28226n = {"this"}, mo28227s = {"L$0"})
/* compiled from: ReactiveFlow.kt */
final class FlowSubscription$flowProcessing$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowSubscription this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowSubscription$flowProcessing$1(FlowSubscription flowSubscription, Continuation continuation) {
        super(continuation);
        this.this$0 = flowSubscription;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.flowProcessing(this);
    }
}
