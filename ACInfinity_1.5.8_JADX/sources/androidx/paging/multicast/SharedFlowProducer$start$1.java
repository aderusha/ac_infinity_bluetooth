package androidx.paging.multicast;

import androidx.paging.multicast.ChannelManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ClosedSendChannelException;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.multicast.SharedFlowProducer$start$1", mo28222f = "SharedFlowProducer.kt", mo28223i = {}, mo28224l = {75, 80, 80}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: SharedFlowProducer.kt */
final class SharedFlowProducer$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ SharedFlowProducer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SharedFlowProducer$start$1(SharedFlowProducer sharedFlowProducer, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sharedFlowProducer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new SharedFlowProducer$start$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SharedFlowProducer$start$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Throwable th;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Job access$getCollectionJob$p = this.this$0.collectionJob;
            this.label = 1;
            if (access$getCollectionJob$p.join(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                try {
                    Function2 access$getSendUpsteamMessage$p = this.this$0.sendUpsteamMessage;
                    ChannelManager.Message.Dispatch.UpstreamFinished upstreamFinished = new ChannelManager.Message.Dispatch.UpstreamFinished(this.this$0);
                    this.L$0 = th2;
                    this.label = 3;
                    if (access$getSendUpsteamMessage$p.invoke(upstreamFinished, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } catch (ClosedSendChannelException unused) {
                }
                th = th2;
            }
        } else if (i == 2) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (ClosedSendChannelException unused2) {
            }
            return Unit.INSTANCE;
        } else if (i != 3) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            th = (Throwable) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (ClosedSendChannelException unused3) {
            }
            throw th;
        }
        Function2 access$getSendUpsteamMessage$p2 = this.this$0.sendUpsteamMessage;
        ChannelManager.Message.Dispatch.UpstreamFinished upstreamFinished2 = new ChannelManager.Message.Dispatch.UpstreamFinished(this.this$0);
        this.label = 2;
        if (access$getSendUpsteamMessage$p2.invoke(upstreamFinished2, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
