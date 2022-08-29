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
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ClosedSendChannelException;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¢\u0006\u0004\b\u0004\u0010\u0005"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.multicast.SharedFlowProducer$collectionJob$1", mo28222f = "SharedFlowProducer.kt", mo28223i = {}, mo28224l = {97}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: SharedFlowProducer.kt */
final class SharedFlowProducer$collectionJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ SharedFlowProducer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SharedFlowProducer$collectionJob$1(SharedFlowProducer sharedFlowProducer, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sharedFlowProducer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new SharedFlowProducer$collectionJob$1(this.this$0, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((SharedFlowProducer$collectionJob$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (FlowKt.m2461catch(this.this$0.src, new C05731(this, (Continuation) null)).collect(new C0571x5d4b9271(this), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (ClosedSendChannelException unused) {
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.multicast.SharedFlowProducer$collectionJob$1$1", mo28222f = "SharedFlowProducer.kt", mo28223i = {}, mo28224l = {50}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
    /* renamed from: androidx.paging.multicast.SharedFlowProducer$collectionJob$1$1 */
    /* compiled from: SharedFlowProducer.kt */
    static final class C05731 extends SuspendLambda implements Function3<FlowCollector<? super T>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ SharedFlowProducer$collectionJob$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(FlowCollector<? super T> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            Intrinsics.checkNotNullParameter(flowCollector, "$this$create");
            Intrinsics.checkNotNullParameter(th, "it");
            Intrinsics.checkNotNullParameter(continuation, "continuation");
            C05731 r2 = new C05731(this.this$0, continuation);
            r2.L$0 = th;
            return r2;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ((C05731) create((FlowCollector) obj, (Throwable) obj2, (Continuation) obj3)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Function2 access$getSendUpsteamMessage$p = this.this$0.this$0.sendUpsteamMessage;
                ChannelManager.Message.Dispatch.Error error = new ChannelManager.Message.Dispatch.Error((Throwable) this.L$0);
                this.label = 1;
                if (access$getSendUpsteamMessage$p.invoke(error, this) == coroutine_suspended) {
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
}
