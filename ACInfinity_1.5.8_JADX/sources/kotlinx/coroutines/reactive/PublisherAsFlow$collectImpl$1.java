package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH@"}, mo27512d2 = {"collectImpl", "", "T", "injectContext", "Lkotlin/coroutines/CoroutineContext;", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.reactive.PublisherAsFlow", mo28222f = "ReactiveFlow.kt", mo28223i = {0, 0, 0, 0, 1, 1, 1, 1}, mo28224l = {97, 99}, mo28225m = "collectImpl", mo28226n = {"this", "collector", "subscriber", "consumed", "this", "collector", "subscriber", "consumed"}, mo28227s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "J$0"})
/* compiled from: ReactiveFlow.kt */
final class PublisherAsFlow$collectImpl$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PublisherAsFlow this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PublisherAsFlow$collectImpl$1(PublisherAsFlow publisherAsFlow, Continuation continuation) {
        super(continuation);
        this.this$0 = publisherAsFlow;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.collectImpl((CoroutineContext) null, (FlowCollector) null, this);
    }
}
