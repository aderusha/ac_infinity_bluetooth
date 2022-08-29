package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"kotlinx/coroutines/flow/FlowKt__CollectKt$collect$3", "Lkotlinx/coroutines/flow/FlowCollector;", "emit", "", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.CachedPageEventFlow$downstreamFlow$1$historyCollection$1$invokeSuspend$$inlined$collect$1 */
/* compiled from: Collect.kt */
public final class C0462x274a0b99 implements FlowCollector<IndexedValue<? extends PageEvent<T>>> {
    final /* synthetic */ CachedPageEventFlow$downstreamFlow$1$historyCollection$1 this$0;

    public C0462x274a0b99(CachedPageEventFlow$downstreamFlow$1$historyCollection$1 cachedPageEventFlow$downstreamFlow$1$historyCollection$1) {
        this.this$0 = cachedPageEventFlow$downstreamFlow$1$historyCollection$1;
    }

    public Object emit(Object obj, Continuation continuation) {
        IndexedValue indexedValue = (IndexedValue) obj;
        this.this$0.$lastReceivedHistoryIndex.element = indexedValue.getIndex();
        Object send = this.this$0.$this_simpleChannelFlow.send(indexedValue.getValue(), continuation);
        if (send == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return send;
        }
        return Unit.INSTANCE;
    }
}
