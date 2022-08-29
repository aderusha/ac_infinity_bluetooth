package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;

@Metadata(mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: SafeCollector.common.kt */
public final class FlowKt__ContextKt$flowWith$$inlined$unsafeFlow$1 implements Flow<R> {
    final /* synthetic */ int $bufferSize$inlined;
    final /* synthetic */ Function1 $builder$inlined;
    final /* synthetic */ CoroutineContext $flowContext$inlined;
    final /* synthetic */ Flow $source$inlined;

    public FlowKt__ContextKt$flowWith$$inlined$unsafeFlow$1(Flow flow, int i, Function1 function1, CoroutineContext coroutineContext) {
        this.$source$inlined = flow;
        this.$bufferSize$inlined = i;
        this.$builder$inlined = function1;
        this.$flowContext$inlined = coroutineContext;
    }

    public Object collect(FlowCollector<? super R> flowCollector, Continuation<? super Unit> continuation) {
        Object collect = FlowKt__ContextKt.buffer$default(FlowKt.flowOn((Flow) this.$builder$inlined.invoke(FlowKt__ContextKt.buffer$default(FlowKt.flowOn(this.$source$inlined, continuation.getContext().minusKey(Job.Key)), this.$bufferSize$inlined, (BufferOverflow) null, 2, (Object) null)), this.$flowContext$inlined), this.$bufferSize$inlined, (BufferOverflow) null, 2, (Object) null).collect(new FlowKt__ContextKt$flowWith$lambda3$$inlined$collect$1(flowCollector), continuation);
        if (collect == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return collect;
        }
        return Unit.INSTANCE;
    }
}
