package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;

@Metadata(mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$unsafeTransform$lambda-1$$inlined$collect$1  reason: invalid class name */
/* compiled from: Collect.kt */
public final class FlowKt__EmittersKt$unsafeTransform$lambda1$$inlined$collect$1 implements FlowCollector<T> {
    final /* synthetic */ FlowCollector $this_unsafeFlow$inlined;
    final /* synthetic */ Function3 $transform$inlined;

    public FlowKt__EmittersKt$unsafeTransform$lambda1$$inlined$collect$1(Function3 function3, FlowCollector flowCollector) {
        this.$transform$inlined = function3;
        this.$this_unsafeFlow$inlined = flowCollector;
    }

    public Object emit(T t, Continuation<? super Unit> continuation) {
        Object invoke = this.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, t, continuation);
        return invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invoke : Unit.INSTANCE;
    }

    public Object emit$$forInline(Object obj, Continuation continuation) {
        InlineMarker.mark(4);
        new ContinuationImpl(this, continuation) {
            int label;
            /* synthetic */ Object result;
            final /* synthetic */ FlowKt__EmittersKt$unsafeTransform$lambda1$$inlined$collect$1 this$0;

            {
                this.this$0 = r1;
            }

            public final Object invokeSuspend(Object obj) {
                this.result = obj;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.emit(null, this);
            }
        };
        InlineMarker.mark(5);
        this.$transform$inlined.invoke(this.$this_unsafeFlow$inlined, obj, continuation);
        return Unit.INSTANCE;
    }
}
