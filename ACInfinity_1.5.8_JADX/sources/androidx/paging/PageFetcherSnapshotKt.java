package androidx.paging;

import androidx.paging.ViewportHint;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, mo27512d2 = {"shouldPrioritizeOver", "", "Landroidx/paging/GenerationalViewportHint;", "previous", "loadType", "Landroidx/paging/LoadType;", "paging-common"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: PageFetcherSnapshot.kt */
public final class PageFetcherSnapshotKt {
    public static final boolean shouldPrioritizeOver(GenerationalViewportHint generationalViewportHint, GenerationalViewportHint generationalViewportHint2, LoadType loadType) {
        Intrinsics.checkNotNullParameter(generationalViewportHint, "$this$shouldPrioritizeOver");
        Intrinsics.checkNotNullParameter(generationalViewportHint2, "previous");
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        if (generationalViewportHint.getGenerationId() <= generationalViewportHint2.getGenerationId() && (!(generationalViewportHint2.getHint() instanceof ViewportHint.Initial) || !(generationalViewportHint.getHint() instanceof ViewportHint.Access))) {
            if ((generationalViewportHint.getHint() instanceof ViewportHint.Initial) && (generationalViewportHint2.getHint() instanceof ViewportHint.Access)) {
                return false;
            }
            if (generationalViewportHint.getHint().getOriginalPageOffsetFirst() == generationalViewportHint2.getHint().getOriginalPageOffsetFirst() && generationalViewportHint.getHint().getOriginalPageOffsetLast() == generationalViewportHint2.getHint().getOriginalPageOffsetLast()) {
                if (loadType != LoadType.PREPEND || generationalViewportHint2.getHint().getPresentedItemsBefore() >= generationalViewportHint.getHint().getPresentedItemsBefore()) {
                    return loadType != LoadType.APPEND || generationalViewportHint2.getHint().getPresentedItemsAfter() >= generationalViewportHint.getHint().getPresentedItemsAfter();
                }
                return false;
            }
        }
    }
}
