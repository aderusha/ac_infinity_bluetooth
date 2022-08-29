package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, mo27512d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: SafeCollector.common.kt */
public final class SafeCollector_commonKt$unsafeFlow$1 implements Flow<T> {
    final /* synthetic */ Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> $block;

    public SafeCollector_commonKt$unsafeFlow$1(Function2<? super FlowCollector<? super T>, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.$block = function2;
    }

    public Object collect(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        Object invoke = this.$block.invoke(flowCollector, continuation);
        return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
    }

    public Object collect$$forInline(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
        InlineMarker.mark(4);
        new SafeCollector_commonKt$unsafeFlow$1$collect$1(this, continuation);
        InlineMarker.mark(5);
        this.$block.invoke(flowCollector, continuation);
        return Unit.INSTANCE;
    }
}
