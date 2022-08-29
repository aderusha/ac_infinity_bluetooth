package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@"}, mo27512d2 = {"initialize", "", "Key", "Value", "continuation", "Lkotlin/coroutines/Continuation;", "Landroidx/paging/RemoteMediator$InitializeAction;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.RemoteMediatorAccessImpl", mo28222f = "RemoteMediatorAccessor.kt", mo28223i = {0}, mo28224l = {394}, mo28225m = "initialize", mo28226n = {"this"}, mo28227s = {"L$0"})
/* compiled from: RemoteMediatorAccessor.kt */
final class RemoteMediatorAccessImpl$initialize$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RemoteMediatorAccessImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteMediatorAccessImpl$initialize$1(RemoteMediatorAccessImpl remoteMediatorAccessImpl, Continuation continuation) {
        super(continuation);
        this.this$0 = remoteMediatorAccessImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.initialize(this);
    }
}
