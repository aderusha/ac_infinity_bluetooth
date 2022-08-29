package androidx.paging;

import androidx.paging.PagePresenter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J \u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¨\u0006\u0010"}, mo27512d2 = {"androidx/paging/PagingDataDiffer$processPageEventCallback$1", "Landroidx/paging/PagePresenter$ProcessPageEventCallback;", "onChanged", "", "position", "", "count", "onInserted", "onRemoved", "onStateUpdate", "loadType", "Landroidx/paging/LoadType;", "fromMediator", "", "loadState", "Landroidx/paging/LoadState;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PagingDataDiffer.kt */
public final class PagingDataDiffer$processPageEventCallback$1 implements PagePresenter.ProcessPageEventCallback {
    final /* synthetic */ PagingDataDiffer this$0;

    PagingDataDiffer$processPageEventCallback$1(PagingDataDiffer pagingDataDiffer) {
        this.this$0 = pagingDataDiffer;
    }

    public void onChanged(int i, int i2) {
        this.this$0.differCallback.onChanged(i, i2);
    }

    public void onInserted(int i, int i2) {
        this.this$0.differCallback.onInserted(i, i2);
    }

    public void onRemoved(int i, int i2) {
        this.this$0.differCallback.onRemoved(i, i2);
    }

    public void onStateUpdate(LoadType loadType, boolean z, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        if (!Intrinsics.areEqual((Object) this.this$0.combinedLoadStates.get(loadType, z), (Object) loadState)) {
            this.this$0.combinedLoadStates.set(loadType, z, loadState);
            CombinedLoadStates snapshot = this.this$0.combinedLoadStates.snapshot();
            for (Function1 invoke : this.this$0.loadStateListeners) {
                invoke.invoke(snapshot);
            }
        }
    }
}
