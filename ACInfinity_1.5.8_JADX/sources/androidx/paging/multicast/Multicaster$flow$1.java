package androidx.paging.multicast;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.multicast.Multicaster$flow$1", mo28222f = "Multicaster.kt", mo28223i = {}, mo28224l = {100}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: Multicaster.kt */
final class Multicaster$flow$1 extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Multicaster this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Multicaster$flow$1(Multicaster multicaster, Continuation continuation) {
        super(2, continuation);
        this.this$0 = multicaster;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        Multicaster$flow$1 multicaster$flow$1 = new Multicaster$flow$1(this.this$0, continuation);
        multicaster$flow$1.L$0 = obj;
        return multicaster$flow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((Multicaster$flow$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Channel Channel$default = ChannelKt.Channel$default(Integer.MAX_VALUE, (BufferOverflow) null, (Function1) null, 6, (Object) null);
            Flow onCompletion = FlowKt.onCompletion(FlowKt.flow(new Multicaster$flow$1$invokeSuspend$$inlined$transform$1(FlowKt.onStart(FlowKt.consumeAsFlow(Channel$default), new Multicaster$flow$1$subFlow$1(this, Channel$default, (Continuation) null)), (Continuation) null)), new Multicaster$flow$1$subFlow$3(this, Channel$default, (Continuation) null));
            this.label = 1;
            if (onCompletion.collect((FlowCollector) this.L$0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
