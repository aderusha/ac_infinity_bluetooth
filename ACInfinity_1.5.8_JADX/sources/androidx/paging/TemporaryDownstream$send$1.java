package androidx.paging;

import kotlin.Metadata;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H@"}, mo27512d2 = {"send", "", "T", "event", "Lkotlin/collections/IndexedValue;", "Landroidx/paging/PageEvent;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.TemporaryDownstream", mo28222f = "CachedPageEventFlow.kt", mo28223i = {}, mo28224l = {149}, mo28225m = "send", mo28226n = {}, mo28227s = {})
/* compiled from: CachedPageEventFlow.kt */
final class TemporaryDownstream$send$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TemporaryDownstream this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TemporaryDownstream$send$1(TemporaryDownstream temporaryDownstream, Continuation continuation) {
        super(continuation);
        this.this$0 = temporaryDownstream;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.send((IndexedValue) null, this);
    }
}
