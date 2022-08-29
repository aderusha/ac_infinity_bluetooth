package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "R", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.FlowExtKt$simpleScan$1", mo28222f = "FlowExt.kt", mo28223i = {0, 0}, mo28224l = {42, 102}, mo28225m = "invokeSuspend", mo28226n = {"$this$flow", "accumulator"}, mo28227s = {"L$0", "L$1"})
/* compiled from: FlowExt.kt */
final class FlowExtKt$simpleScan$1 extends SuspendLambda implements Function2<FlowCollector<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $initial;
    final /* synthetic */ Function3 $operation;
    final /* synthetic */ Flow $this_simpleScan;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowExtKt$simpleScan$1(Flow flow, Object obj, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_simpleScan = flow;
        this.$initial = obj;
        this.$operation = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        FlowExtKt$simpleScan$1 flowExtKt$simpleScan$1 = new FlowExtKt$simpleScan$1(this.$this_simpleScan, this.$initial, this.$operation, continuation);
        flowExtKt$simpleScan$1.L$0 = obj;
        return flowExtKt$simpleScan$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowExtKt$simpleScan$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        FlowCollector flowCollector;
        Ref.ObjectRef objectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            objectRef = new Ref.ObjectRef();
            objectRef.element = this.$initial;
            T t = objectRef.element;
            this.L$0 = flowCollector2;
            this.L$1 = objectRef;
            this.label = 1;
            if (flowCollector2.emit(t, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            flowCollector = flowCollector2;
        } else if (i == 1) {
            objectRef = (Ref.ObjectRef) this.L$1;
            flowCollector = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        if (this.$this_simpleScan.collect(new FlowExtKt$simpleScan$1$invokeSuspend$$inlined$collect$1(this, flowCollector, objectRef), this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
