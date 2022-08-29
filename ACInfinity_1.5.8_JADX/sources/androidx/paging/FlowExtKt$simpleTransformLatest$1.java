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
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "R", "Landroidx/paging/SimpleProducerScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.FlowExtKt$simpleTransformLatest$1", mo28222f = "FlowExt.kt", mo28223i = {}, mo28224l = {76}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: FlowExt.kt */
final class FlowExtKt$simpleTransformLatest$1 extends SuspendLambda implements Function2<SimpleProducerScope<R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $this_simpleTransformLatest;
    final /* synthetic */ Function3 $transform;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowExtKt$simpleTransformLatest$1(Flow flow, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_simpleTransformLatest = flow;
        this.$transform = function3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        FlowExtKt$simpleTransformLatest$1 flowExtKt$simpleTransformLatest$1 = new FlowExtKt$simpleTransformLatest$1(this.$this_simpleTransformLatest, this.$transform, continuation);
        flowExtKt$simpleTransformLatest$1.L$0 = obj;
        return flowExtKt$simpleTransformLatest$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((FlowExtKt$simpleTransformLatest$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Flow flow = this.$this_simpleTransformLatest;
            final ChannelFlowCollector channelFlowCollector = new ChannelFlowCollector((SimpleProducerScope) this.L$0);
            this.label = 1;
            if (FlowKt.collectLatest(flow, new C04741(this, (Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "R", "value", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.FlowExtKt$simpleTransformLatest$1$1", mo28222f = "FlowExt.kt", mo28223i = {}, mo28224l = {77}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
    /* renamed from: androidx.paging.FlowExtKt$simpleTransformLatest$1$1 */
    /* compiled from: FlowExt.kt */
    static final class C04741 extends SuspendLambda implements Function2<T, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ FlowExtKt$simpleTransformLatest$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            C04741 r0 = new C04741(this.this$0, channelFlowCollector, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C04741) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Object obj2 = this.L$0;
                Function3 function3 = this.this$0.$transform;
                ChannelFlowCollector channelFlowCollector = channelFlowCollector;
                this.label = 1;
                if (function3.invoke(channelFlowCollector, obj2, this) == coroutine_suspended) {
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
