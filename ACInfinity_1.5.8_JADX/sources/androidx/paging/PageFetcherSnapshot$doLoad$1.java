package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH@"}, mo27512d2 = {"doLoad", "", "Key", "Value", "loadType", "Landroidx/paging/LoadType;", "generationalHint", "Landroidx/paging/GenerationalViewportHint;", "continuation", "Lkotlin/coroutines/Continuation;", ""}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageFetcherSnapshot", mo28222f = "PageFetcherSnapshot.kt", mo28223i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10}, mo28224l = {684, 696, 386, 394, 708, 720, 437, 732, 456, 482, 744}, mo28225m = "doLoad", mo28226n = {"this", "loadType", "generationalHint", "itemsLoaded", "this_$iv", "$this$withLock$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "this_$iv", "$this$withLock$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "$this$withLock$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "this_$iv", "$this$withLock$iv$iv", "this", "loadType", "generationalHint", "result", "this_$iv", "$this$withLock$iv$iv", "loadType", "generationalHint", "$this$withLock$iv$iv", "state", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "dropType", "this_$iv", "$this$withLock$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "$this$withLock$iv$iv", "state", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "params", "result", "$this$withLock$iv$iv", "this", "loadType", "generationalHint", "itemsLoaded", "loadKey", "endOfPaginationReached", "this_$iv", "$this$withLock$iv$iv", "endsPrepend", "endsAppend"}, mo28227s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "I$1"})
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$doLoad$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageFetcherSnapshot this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcherSnapshot$doLoad$1(PageFetcherSnapshot pageFetcherSnapshot, Continuation continuation) {
        super(continuation);
        this.this$0 = pageFetcherSnapshot;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doLoad((LoadType) null, (GenerationalViewportHint) null, this);
    }
}
