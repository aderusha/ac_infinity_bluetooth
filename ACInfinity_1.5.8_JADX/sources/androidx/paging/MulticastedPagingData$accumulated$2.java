package androidx.paging;

import androidx.paging.ActiveFlowTracker;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H@¢\u0006\u0004\b\b\u0010\t"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Landroidx/paging/PageEvent;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.MulticastedPagingData$accumulated$2", mo28222f = "CachedPagingData.kt", mo28223i = {}, mo28224l = {40}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: CachedPagingData.kt */
final class MulticastedPagingData$accumulated$2 extends SuspendLambda implements Function3<FlowCollector<? super PageEvent<T>>, Throwable, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MulticastedPagingData this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MulticastedPagingData$accumulated$2(MulticastedPagingData multicastedPagingData, Continuation continuation) {
        super(3, continuation);
        this.this$0 = multicastedPagingData;
    }

    public final Continuation<Unit> create(FlowCollector<? super PageEvent<T>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
        Intrinsics.checkNotNullParameter(flowCollector, "$this$create");
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        return new MulticastedPagingData$accumulated$2(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return ((MulticastedPagingData$accumulated$2) create((FlowCollector) obj, (Throwable) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ActiveFlowTracker tracker = this.this$0.getTracker();
            if (tracker != null) {
                ActiveFlowTracker.FlowType flowType = ActiveFlowTracker.FlowType.PAGE_EVENT_FLOW;
                this.label = 1;
                if (tracker.onComplete(flowType, this) == coroutine_suspended) {
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
