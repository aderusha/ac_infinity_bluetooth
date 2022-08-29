package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@"}, mo27512d2 = {"doInitialLoad", "", "Key", "Value", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageFetcherSnapshot", mo28222f = "PageFetcherSnapshot.kt", mo28223i = {0, 0, 0, 1, 1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 8, 9}, mo28224l = {611, 272, 275, 623, 635, 647, 304, 659, 671, 329}, mo28225m = "doInitialLoad", mo28226n = {"this", "this_$iv", "$this$withLock$iv$iv", "this", "$this$withLock$iv$iv", "this", "this", "result", "this_$iv", "$this$withLock$iv$iv", "this", "result", "this_$iv", "$this$withLock$iv$iv", "insertApplied", "this", "result", "this_$iv", "$this$withLock$iv$iv", "this", "result", "$this$withLock$iv$iv", "this", "result", "this_$iv", "$this$withLock$iv$iv", "this", "result", "this_$iv", "$this$withLock$iv$iv", "$this$withLock$iv$iv"}, mo28227s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0"})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$doInitialLoad$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageFetcherSnapshot this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$doInitialLoad$1(PageFetcherSnapshot pageFetcherSnapshot, Continuation continuation) {
        super(continuation);
        this.this$0 = pageFetcherSnapshot;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doInitialLoad(this);
    }
}
