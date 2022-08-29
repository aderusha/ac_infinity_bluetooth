package androidx.paging;

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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "Landroidx/paging/SimpleProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.CancelableChannelFlowKt$cancelableChannelFlow$1", mo28222f = "CancelableChannelFlow.kt", mo28223i = {}, mo28224l = {30}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: CancelableChannelFlow.kt */
final class CancelableChannelFlowKt$cancelableChannelFlow$1 extends SuspendLambda implements Function2<SimpleProducerScope<T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $block;
    final /* synthetic */ Job $controller;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CancelableChannelFlowKt$cancelableChannelFlow$1(Job job, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$controller = job;
        this.$block = function2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        CancelableChannelFlowKt$cancelableChannelFlow$1 cancelableChannelFlowKt$cancelableChannelFlow$1 = new CancelableChannelFlowKt$cancelableChannelFlow$1(this.$controller, this.$block, continuation);
        cancelableChannelFlowKt$cancelableChannelFlow$1.L$0 = obj;
        return cancelableChannelFlowKt$cancelableChannelFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CancelableChannelFlowKt$cancelableChannelFlow$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final SimpleProducerScope simpleProducerScope = (SimpleProducerScope) this.L$0;
            this.$controller.invokeOnCompletion(new Function1<Throwable, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    SendChannel.DefaultImpls.close$default(simpleProducerScope, (Throwable) null, 1, (Object) null);
                }
            });
            Function2 function2 = this.$block;
            this.label = 1;
            if (function2.invoke(simpleProducerScope, this) == coroutine_suspended) {
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
