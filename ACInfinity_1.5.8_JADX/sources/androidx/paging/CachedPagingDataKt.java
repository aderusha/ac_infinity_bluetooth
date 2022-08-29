package androidx.paging;

import androidx.paging.multicast.Multicaster;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001aB\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¨\u0006\t"}, mo27512d2 = {"cachedIn", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "tracker", "Landroidx/paging/ActiveFlowTracker;", "paging-common"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: CachedPagingData.kt */
public final class CachedPagingDataKt {
    public static final <T> Flow<PagingData<T>> cachedIn(Flow<PagingData<T>> flow, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(flow, "$this$cachedIn");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        return cachedIn(flow, coroutineScope, (ActiveFlowTracker) null);
    }

    public static /* synthetic */ Flow cachedIn$default(Flow flow, CoroutineScope coroutineScope, ActiveFlowTracker activeFlowTracker, int i, Object obj) {
        if ((i & 2) != 0) {
            activeFlowTracker = null;
        }
        return cachedIn(flow, coroutineScope, activeFlowTracker);
    }

    public static final <T> Flow<PagingData<T>> cachedIn(Flow<PagingData<T>> flow, CoroutineScope coroutineScope, ActiveFlowTracker activeFlowTracker) {
        Intrinsics.checkNotNullParameter(flow, "$this$cachedIn");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        return new Multicaster(coroutineScope, 1, FlowKt.onCompletion(FlowKt.onStart(new CachedPagingDataKt$cachedIn$$inlined$map$2(FlowExtKt.simpleRunningReduce(new CachedPagingDataKt$cachedIn$$inlined$map$1(flow, coroutineScope), new CachedPagingDataKt$cachedIn$multicastedFlow$2((Continuation) null))), new CachedPagingDataKt$cachedIn$multicastedFlow$4(activeFlowTracker, (Continuation) null)), new CachedPagingDataKt$cachedIn$multicastedFlow$5(activeFlowTracker, (Continuation) null)), false, new CachedPagingDataKt$cachedIn$1((Continuation) null), true, 8, (DefaultConstructorMarker) null).getFlow();
    }
}
