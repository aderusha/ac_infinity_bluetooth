package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0001\"\b\b\u0001\u0010\u0003*\u00020\u00012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00050\u0007H@"}, mo27512d2 = {"generateNewPagingSource", "", "Key", "Value", "previousPagingSource", "Landroidx/paging/PagingSource;", "continuation", "Lkotlin/coroutines/Continuation;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
@DebugMetadata(mo28221c = "androidx.paging.PageFetcher", mo28222f = "PageFetcher.kt", mo28223i = {0, 0}, mo28224l = {211}, mo28225m = "generateNewPagingSource", mo28226n = {"this", "previousPagingSource"}, mo28227s = {"L$0", "L$1"})
/* compiled from: PageFetcher.kt */
final class PageFetcher$generateNewPagingSource$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PageFetcher this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PageFetcher$generateNewPagingSource$1(PageFetcher pageFetcher, Continuation continuation) {
        super(continuation);
        this.this$0 = pageFetcher;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.generateNewPagingSource((PagingSource) null, this);
    }
}
