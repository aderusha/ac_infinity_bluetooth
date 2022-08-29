package androidx.paging;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, mo27512d2 = {"androidx/paging/PagingData$Companion$NOOP_RECEIVER$1", "Landroidx/paging/UiReceiver;", "accessHint", "", "viewportHint", "Landroidx/paging/ViewportHint;", "refresh", "retry", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PagingData.kt */
public final class PagingData$Companion$NOOP_RECEIVER$1 implements UiReceiver {
    public void accessHint(ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
    }

    public void refresh() {
    }

    public void retry() {
    }

    PagingData$Companion$NOOP_RECEIVER$1() {
    }
}
