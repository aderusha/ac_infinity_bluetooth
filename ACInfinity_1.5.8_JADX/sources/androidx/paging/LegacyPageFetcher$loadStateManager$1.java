package androidx.paging;

import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, mo27512d2 = {"androidx/paging/LegacyPageFetcher$loadStateManager$1", "Landroidx/paging/PagedList$LoadStateManager;", "onStateChanged", "", "type", "Landroidx/paging/LoadType;", "state", "Landroidx/paging/LoadState;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: LegacyPageFetcher.kt */
public final class LegacyPageFetcher$loadStateManager$1 extends PagedList.LoadStateManager {
    final /* synthetic */ LegacyPageFetcher this$0;

    LegacyPageFetcher$loadStateManager$1(LegacyPageFetcher legacyPageFetcher) {
        this.this$0 = legacyPageFetcher;
    }

    public void onStateChanged(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "type");
        Intrinsics.checkNotNullParameter(loadState, "state");
        this.this$0.getPageConsumer().onStateChanged(loadType, loadState);
    }
}
