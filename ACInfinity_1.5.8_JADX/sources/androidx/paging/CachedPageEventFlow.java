package androidx.paging;

import androidx.paging.multicast.Multicaster;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B!\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0011\u0010\u0013\u001a\u00020\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, mo27512d2 = {"Landroidx/paging/CachedPageEventFlow;", "T", "", "src", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)V", "collectedFromSource", "Ljava/util/concurrent/atomic/AtomicBoolean;", "downstreamFlow", "getDownstreamFlow", "()Lkotlinx/coroutines/flow/Flow;", "multicastedSrc", "Landroidx/paging/multicast/Multicaster;", "Lkotlin/collections/IndexedValue;", "pageController", "Landroidx/paging/FlattenedPageController;", "close", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: CachedPageEventFlow.kt */
public final class CachedPageEventFlow<T> {
    /* access modifiers changed from: private */
    public final AtomicBoolean collectedFromSource = new AtomicBoolean(false);
    private final Flow<PageEvent<T>> downstreamFlow;
    /* access modifiers changed from: private */
    public final Multicaster<IndexedValue<PageEvent<T>>> multicastedSrc;
    /* access modifiers changed from: private */
    public final FlattenedPageController<T> pageController;

    public CachedPageEventFlow(Flow<? extends PageEvent<T>> flow, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(flow, "src");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        FlattenedPageController<T> flattenedPageController = new FlattenedPageController<>();
        this.pageController = flattenedPageController;
        this.multicastedSrc = new Multicaster(coroutineScope, 0, FlowKt.flow(new CachedPageEventFlow$multicastedSrc$1(this, flow, (Continuation) null)), false, new CachedPageEventFlow$multicastedSrc$2(flattenedPageController), true, 8, (DefaultConstructorMarker) null);
        this.downstreamFlow = SimpleChannelFlowKt.simpleChannelFlow(new CachedPageEventFlow$downstreamFlow$1(this, (Continuation) null));
    }

    public final Object close(Continuation<? super Unit> continuation) {
        Object close = this.multicastedSrc.close(continuation);
        if (close == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return close;
        }
        return Unit.INSTANCE;
    }

    public final Flow<PageEvent<T>> getDownstreamFlow() {
        return this.downstreamFlow;
    }
}
