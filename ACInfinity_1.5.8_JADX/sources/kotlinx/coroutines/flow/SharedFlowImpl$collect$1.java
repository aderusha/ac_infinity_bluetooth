package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27513k = 3, mo27514mv = {1, 5, 1}, mo27516xi = 48)
@DebugMetadata(mo28221c = "kotlinx.coroutines.flow.SharedFlowImpl", mo28222f = "SharedFlow.kt", mo28223i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, mo28224l = {341, 348, 351}, mo28225m = "collect", mo28226n = {"this", "collector", "slot", "this", "collector", "slot", "collectorJob", "this", "collector", "slot", "collectorJob"}, mo28227s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: SharedFlow.kt */
final class SharedFlowImpl$collect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SharedFlowImpl<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SharedFlowImpl$collect$1(SharedFlowImpl<T> sharedFlowImpl, Continuation<? super SharedFlowImpl$collect$1> continuation) {
        super(continuation);
        this.this$0 = sharedFlowImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collect((FlowCollector) null, this);
    }
}
