package kotlinx.coroutines.reactive;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\u0006\b\u0000\u0010\u0002 \u00002\u0006\u0010\u0003\u001a\u0002H\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@"}, mo27512d2 = {"sendSuspend", "", "T", "element", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "kotlinx.coroutines.reactive.PublisherCoroutine", mo28222f = "Publish.kt", mo28223i = {0, 0}, mo28224l = {113}, mo28225m = "sendSuspend", mo28226n = {"this", "element"}, mo28227s = {"L$0", "L$1"})
/* compiled from: Publish.kt */
final class PublisherCoroutine$sendSuspend$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PublisherCoroutine this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PublisherCoroutine$sendSuspend$1(PublisherCoroutine publisherCoroutine, Continuation continuation) {
        super(continuation);
        this.this$0 = publisherCoroutine;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendSuspend(null, this);
    }
}
