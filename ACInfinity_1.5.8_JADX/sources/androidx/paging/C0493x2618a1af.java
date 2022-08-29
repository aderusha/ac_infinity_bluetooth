package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0493x2618a1af implements FlowCollector<GenerationalViewportHint> {
    final /* synthetic */ LoadType $loadType$inlined;
    final /* synthetic */ PageFetcherSnapshot this$0;

    public C0493x2618a1af(PageFetcherSnapshot pageFetcherSnapshot, LoadType loadType) {
        this.this$0 = pageFetcherSnapshot;
        this.$loadType$inlined = loadType;
    }

    public Object emit(Object obj, Continuation continuation) {
        Object doLoad = this.this$0.doLoad(this.$loadType$inlined, (GenerationalViewportHint) obj, continuation);
        if (doLoad == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return doLoad;
        }
        return Unit.INSTANCE;
    }
}
