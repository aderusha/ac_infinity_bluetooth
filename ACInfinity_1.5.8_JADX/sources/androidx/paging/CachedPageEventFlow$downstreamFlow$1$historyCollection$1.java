package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@¢\u0006\u0004\b\u0005\u0010\u0006"}, mo27512d2 = {"<anonymous>", "", "T", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.CachedPageEventFlow$downstreamFlow$1$historyCollection$1", mo28222f = "CachedPageEventFlow.kt", mo28223i = {}, mo28224l = {292}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
/* compiled from: CachedPageEventFlow.kt */
final class CachedPageEventFlow$downstreamFlow$1$historyCollection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $lastReceivedHistoryIndex;
    final /* synthetic */ TemporaryDownstream $snapshot;
    final /* synthetic */ SimpleProducerScope $this_simpleChannelFlow;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CachedPageEventFlow$downstreamFlow$1$historyCollection$1(SimpleProducerScope simpleProducerScope, TemporaryDownstream temporaryDownstream, Ref.IntRef intRef, Continuation continuation) {
        super(2, continuation);
        this.$this_simpleChannelFlow = simpleProducerScope;
        this.$snapshot = temporaryDownstream;
        this.$lastReceivedHistoryIndex = intRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        return new CachedPageEventFlow$downstreamFlow$1$historyCollection$1(this.$this_simpleChannelFlow, this.$snapshot, this.$lastReceivedHistoryIndex, continuation);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CachedPageEventFlow$downstreamFlow$1$historyCollection$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.$snapshot.consumeHistory().collect(new C0462x274a0b99(this), this) == coroutine_suspended) {
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
