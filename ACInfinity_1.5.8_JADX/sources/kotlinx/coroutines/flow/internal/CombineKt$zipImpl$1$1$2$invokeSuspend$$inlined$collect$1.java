package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: Collect.kt */
public final class CombineKt$zipImpl$1$1$2$invokeSuspend$$inlined$collect$1 implements FlowCollector<T1> {
    final /* synthetic */ Object $cnt$inlined;
    final /* synthetic */ CoroutineContext $scopeContext$inlined;
    final /* synthetic */ ReceiveChannel $second$inlined;
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ Function3 $transform$inlined;

    public CombineKt$zipImpl$1$1$2$invokeSuspend$$inlined$collect$1(CoroutineContext coroutineContext, Object obj, ReceiveChannel receiveChannel, FlowCollector flowCollector, Function3 function3) {
        this.$scopeContext$inlined = coroutineContext;
        this.$cnt$inlined = obj;
        this.$second$inlined = receiveChannel;
        this.$this_unsafeFlow$inlined = flowCollector;
        this.$transform$inlined = function3;
    }

    public Object emit(T1 t1, Continuation<? super Unit> continuation) {
        Object withContextUndispatched = ChannelFlowKt.withContextUndispatched(this.$scopeContext$inlined, Unit.INSTANCE, this.$cnt$inlined, new CombineKt$zipImpl$1$1$2$1$1(this.$second$inlined, this.$this_unsafeFlow$inlined, this.$transform$inlined, t1, (Continuation<? super CombineKt$zipImpl$1$1$2$1$1>) null), continuation);
        if (withContextUndispatched == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return withContextUndispatched;
        }
        return Unit.INSTANCE;
    }
}
