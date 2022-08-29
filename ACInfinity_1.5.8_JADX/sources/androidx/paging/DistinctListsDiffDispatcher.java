package androidx.paging;

import androidx.recyclerview.widget.ListUpdateCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J8\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u0001H\u0002J4\u0010\r\u001a\u00020\u0004\"\b\b\u0000\u0010\u000e*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u000e0\u0010¨\u0006\u0012"}, mo27512d2 = {"Landroidx/paging/DistinctListsDiffDispatcher;", "", "()V", "dispatchChange", "", "callback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "startBoundary", "", "endBoundary", "start", "end", "payload", "dispatchDiff", "T", "oldList", "Landroidx/paging/NullPaddedList;", "newList", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: NullPaddedListDiffHelper.kt */
public final class DistinctListsDiffDispatcher {
    public static final DistinctListsDiffDispatcher INSTANCE = new DistinctListsDiffDispatcher();

    private DistinctListsDiffDispatcher() {
    }

    public final <T> void dispatchDiff(ListUpdateCallback listUpdateCallback, NullPaddedList<T> nullPaddedList, NullPaddedList<T> nullPaddedList2) {
        Intrinsics.checkNotNullParameter(listUpdateCallback, "callback");
        Intrinsics.checkNotNullParameter(nullPaddedList, "oldList");
        Intrinsics.checkNotNullParameter(nullPaddedList2, "newList");
        int max = Math.max(nullPaddedList.getPlaceholdersBefore(), nullPaddedList2.getPlaceholdersBefore());
        int min = Math.min(nullPaddedList.getPlaceholdersBefore() + nullPaddedList.getStorageCount(), nullPaddedList2.getPlaceholdersBefore() + nullPaddedList2.getStorageCount());
        int i = min - max;
        if (i > 0) {
            listUpdateCallback.onRemoved(max, i);
            listUpdateCallback.onInserted(max, i);
        }
        int min2 = Math.min(max, min);
        ListUpdateCallback listUpdateCallback2 = listUpdateCallback;
        int i2 = min2;
        int max2 = Math.max(max, min);
        dispatchChange(listUpdateCallback2, i2, max2, RangesKt.coerceAtMost(nullPaddedList.getPlaceholdersBefore(), nullPaddedList2.getSize()), RangesKt.coerceAtMost(nullPaddedList.getPlaceholdersBefore() + nullPaddedList.getStorageCount(), nullPaddedList2.getSize()), DiffingChangePayload.ITEM_TO_PLACEHOLDER);
        dispatchChange(listUpdateCallback2, i2, max2, RangesKt.coerceAtMost(nullPaddedList2.getPlaceholdersBefore(), nullPaddedList.getSize()), RangesKt.coerceAtMost(nullPaddedList2.getPlaceholdersBefore() + nullPaddedList2.getStorageCount(), nullPaddedList.getSize()), DiffingChangePayload.PLACEHOLDER_TO_ITEM);
        int size = nullPaddedList2.getSize() - nullPaddedList.getSize();
        if (size > 0) {
            listUpdateCallback.onInserted(nullPaddedList.getSize(), size);
        } else if (size < 0) {
            listUpdateCallback.onRemoved(nullPaddedList.getSize() + size, -size);
        }
    }

    private final void dispatchChange(ListUpdateCallback listUpdateCallback, int i, int i2, int i3, int i4, Object obj) {
        int i5 = i - i3;
        if (i5 > 0) {
            listUpdateCallback.onChanged(i3, i5, obj);
        }
        int i6 = i4 - i2;
        if (i6 > 0) {
            listUpdateCallback.onChanged(i2, i6, obj);
        }
    }
}
