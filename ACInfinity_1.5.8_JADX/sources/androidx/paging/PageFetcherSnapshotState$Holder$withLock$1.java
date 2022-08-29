package androidx.paging;

import androidx.paging.PageFetcherSnapshotState;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00020\u0001\"\b\b\u0002\u0010\u0002*\u00020\u0001\"\b\b\u0003\u0010\u0003*\u00020\u00012-\u0010\u0004\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00028\u00040\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00040\u000bHH"}, mo27512d2 = {"withLock", "", "Key", "Value", "block", "Lkotlin/Function1;", "Landroidx/paging/PageFetcherSnapshotState;", "Lkotlin/ParameterName;", "name", "state", "continuation", "Lkotlin/coroutines/Continuation;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageFetcherSnapshotState$Holder", mo28222f = "PageFetcherSnapshotState.kt", mo28223i = {0, 0, 0}, mo28224l = {415}, mo28225m = "withLock", mo28226n = {"this", "block", "$this$withLock$iv"}, mo28227s = {"L$0", "L$1", "L$2"})
/* compiled from: PageFetcherSnapshotState.kt */
public final class PageFetcherSnapshotState$Holder$withLock$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageFetcherSnapshotState.Holder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PageFetcherSnapshotState$Holder$withLock$1(PageFetcherSnapshotState.Holder holder, Continuation continuation) {
        super(continuation);
        this.this$0 = holder;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.withLock((Function1) null, this);
    }
}
