package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.reactive.PublisherAsFlow$collectSlowPath$2", mo28222f = "ReactiveFlow.kt", mo28223i = {}, mo28224l = {86}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: ReactiveFlow.kt */
final class PublisherAsFlow$collectSlowPath$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCollector $collector;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PublisherAsFlow this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PublisherAsFlow$collectSlowPath$2(PublisherAsFlow publisherAsFlow, FlowCollector flowCollector, Continuation continuation) {
        super(2, continuation);
        this.this$0 = publisherAsFlow;
        this.$collector = flowCollector;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PublisherAsFlow$collectSlowPath$2 publisherAsFlow$collectSlowPath$2 = new PublisherAsFlow$collectSlowPath$2(this.this$0, this.$collector, continuation);
        publisherAsFlow$collectSlowPath$2.L$0 = obj;
        return publisherAsFlow$collectSlowPath$2;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((PublisherAsFlow$collectSlowPath$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.$collector;
            PublisherAsFlow publisherAsFlow = this.this$0;
            ReceiveChannel produceImpl = publisherAsFlow.produceImpl(CoroutineScopeKt.plus((CoroutineScope) this.L$0, publisherAsFlow.context));
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, produceImpl, (Continuation<? super Unit>) this) == coroutine_suspended) {
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
