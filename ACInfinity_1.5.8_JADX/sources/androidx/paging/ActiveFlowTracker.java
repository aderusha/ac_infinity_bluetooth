package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\ba\u0018\u00002\u00020\u0001:\u0001\bJ\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, mo27512d2 = {"Landroidx/paging/ActiveFlowTracker;", "", "onComplete", "", "flowType", "Landroidx/paging/ActiveFlowTracker$FlowType;", "(Landroidx/paging/ActiveFlowTracker$FlowType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onStart", "FlowType", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: CachedPagingData.kt */
public interface ActiveFlowTracker {

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, mo27512d2 = {"Landroidx/paging/ActiveFlowTracker$FlowType;", "", "(Ljava/lang/String;I)V", "PAGED_DATA_FLOW", "PAGE_EVENT_FLOW", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: CachedPagingData.kt */
    public enum FlowType {
        PAGED_DATA_FLOW,
        PAGE_EVENT_FLOW
    }

    Object onComplete(FlowType flowType, Continuation<? super Unit> continuation);

    Object onStart(FlowType flowType, Continuation<? super Unit> continuation);
}
