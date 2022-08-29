package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H@"}, mo27512d2 = {"awaitClose", "", "T", "block", "Lkotlin/Function0;", "", "continuation", "Lkotlin/coroutines/Continuation;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.SimpleProducerScopeImpl", mo28222f = "SimpleChannelFlow.kt", mo28223i = {0}, mo28224l = {97}, mo28225m = "awaitClose", mo28226n = {"block"}, mo28227s = {"L$0"})
/* compiled from: SimpleChannelFlow.kt */
final class SimpleProducerScopeImpl$awaitClose$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SimpleProducerScopeImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SimpleProducerScopeImpl$awaitClose$1(SimpleProducerScopeImpl simpleProducerScopeImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = simpleProducerScopeImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.awaitClose((Function0<Unit>) null, this);
    }
}
