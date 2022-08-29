package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00060\u00050\u0004H@¢\u0006\u0004\b\u0007\u0010\b"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/collections/IndexedValue;", "Landroidx/paging/PageEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.CachedPageEventFlow$multicastedSrc$1", mo28222f = "CachedPageEventFlow.kt", mo28223i = {}, mo28224l = {292}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: CachedPageEventFlow.kt */
final class CachedPageEventFlow$multicastedSrc$1 extends SuspendLambda implements Function2<FlowCollector<? super IndexedValue<? extends PageEvent<T>>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $src;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CachedPageEventFlow this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CachedPageEventFlow$multicastedSrc$1(CachedPageEventFlow cachedPageEventFlow, Flow flow, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cachedPageEventFlow;
        this.$src = flow;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        CachedPageEventFlow$multicastedSrc$1 cachedPageEventFlow$multicastedSrc$1 = new CachedPageEventFlow$multicastedSrc$1(this.this$0, this.$src, continuation);
        cachedPageEventFlow$multicastedSrc$1.L$0 = obj;
        return cachedPageEventFlow$multicastedSrc$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CachedPageEventFlow$multicastedSrc$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            if (this.this$0.collectedFromSource.compareAndSet(false, true)) {
                Flow withIndex = FlowKt.withIndex(this.$src);
                this.label = 1;
                if (withIndex.collect(flowCollector, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
