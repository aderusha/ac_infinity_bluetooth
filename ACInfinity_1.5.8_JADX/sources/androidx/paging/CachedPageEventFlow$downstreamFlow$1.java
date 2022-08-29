package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "T", "", "Landroidx/paging/SimpleProducerScope;", "Landroidx/paging/PageEvent;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.CachedPageEventFlow$downstreamFlow$1", mo28222f = "CachedPageEventFlow.kt", mo28223i = {0}, mo28224l = {83, 117}, mo28225m = "invokeSuspend", mo28226n = {"$this$simpleChannelFlow"}, mo28227s = {"L$0"})
/* compiled from: CachedPageEventFlow.kt */
final class CachedPageEventFlow$downstreamFlow$1 extends SuspendLambda implements Function2<SimpleProducerScope<PageEvent<T>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CachedPageEventFlow this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CachedPageEventFlow$downstreamFlow$1(CachedPageEventFlow cachedPageEventFlow, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cachedPageEventFlow;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "completion");
        CachedPageEventFlow$downstreamFlow$1 cachedPageEventFlow$downstreamFlow$1 = new CachedPageEventFlow$downstreamFlow$1(this.this$0, continuation);
        cachedPageEventFlow$downstreamFlow$1.L$0 = obj;
        return cachedPageEventFlow$downstreamFlow$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((CachedPageEventFlow$downstreamFlow$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        SimpleProducerScope simpleProducerScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            simpleProducerScope = (SimpleProducerScope) this.L$0;
            FlattenedPageController access$getPageController$p = this.this$0.pageController;
            this.L$0 = simpleProducerScope;
            this.label = 1;
            obj2 = access$getPageController$p.createTemporaryDownstream(this);
            if (obj2 == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            simpleProducerScope = (SimpleProducerScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            obj2 = obj;
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        SimpleProducerScope simpleProducerScope2 = simpleProducerScope;
        TemporaryDownstream temporaryDownstream = (TemporaryDownstream) obj2;
        Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = Integer.MIN_VALUE;
        CoroutineScope coroutineScope = simpleProducerScope2;
        Job launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CachedPageEventFlow$downstreamFlow$1$historyCollection$1(simpleProducerScope2, temporaryDownstream, intRef, (Continuation) null), 3, (Object) null);
        Job[] jobArr = {BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new CachedPageEventFlow$downstreamFlow$1$activeStreamCollection$1(this, simpleProducerScope2, temporaryDownstream, launch$default, intRef, (Continuation) null), 3, (Object) null), launch$default};
        this.L$0 = null;
        this.label = 2;
        if (AwaitKt.joinAll(jobArr, (Continuation<? super Unit>) this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
