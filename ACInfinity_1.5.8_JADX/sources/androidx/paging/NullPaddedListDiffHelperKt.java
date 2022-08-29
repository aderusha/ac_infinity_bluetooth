package androidx.paging;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.RangesKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a8\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0000\u001a:\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\f\u001a\u00020\u0001H\u0000\u001a,\u0010\r\u001a\u00020\u000e*\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\f\u001a\u00020\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u00042\u0006\u0010\u000f\u001a\u00020\u000eH\u0000¨\u0006\u0010"}, mo27512d2 = {"computeDiff", "Landroidx/paging/NullPaddedDiffResult;", "T", "", "Landroidx/paging/NullPaddedList;", "newList", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "dispatchDiff", "", "callback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "diffResult", "transformAnchorIndex", "", "oldPosition", "paging-runtime_release"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: NullPaddedListDiffHelper.kt */
public final class NullPaddedListDiffHelperKt {
    public static final <T> NullPaddedDiffResult computeDiff(NullPaddedList<T> nullPaddedList, NullPaddedList<T> nullPaddedList2, DiffUtil.ItemCallback<T> itemCallback) {
        boolean z;
        Intrinsics.checkNotNullParameter(nullPaddedList, "$this$computeDiff");
        Intrinsics.checkNotNullParameter(nullPaddedList2, "newList");
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        boolean z2 = true;
        DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new NullPaddedListDiffHelperKt$computeDiff$diffResult$1(nullPaddedList, nullPaddedList2, itemCallback, nullPaddedList.getStorageCount(), nullPaddedList2.getStorageCount()), true);
        Intrinsics.checkNotNullExpressionValue(calculateDiff, "DiffUtil.calculateDiff(\n…    },\n        true\n    )");
        Iterable until = RangesKt.until(0, nullPaddedList.getStorageCount());
        if (!(until instanceof Collection) || !((Collection) until).isEmpty()) {
            Iterator it = until.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (calculateDiff.convertOldPositionToNew(((IntIterator) it).nextInt()) != -1) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
        }
        z2 = false;
        return new NullPaddedDiffResult(calculateDiff, z2);
    }

    public static final <T> void dispatchDiff(NullPaddedList<T> nullPaddedList, ListUpdateCallback listUpdateCallback, NullPaddedList<T> nullPaddedList2, NullPaddedDiffResult nullPaddedDiffResult) {
        Intrinsics.checkNotNullParameter(nullPaddedList, "$this$dispatchDiff");
        Intrinsics.checkNotNullParameter(listUpdateCallback, "callback");
        Intrinsics.checkNotNullParameter(nullPaddedList2, "newList");
        Intrinsics.checkNotNullParameter(nullPaddedDiffResult, "diffResult");
        if (nullPaddedDiffResult.getHasOverlap()) {
            OverlappingListsDiffDispatcher.INSTANCE.dispatchDiff(nullPaddedList, nullPaddedList2, listUpdateCallback, nullPaddedDiffResult);
        } else {
            DistinctListsDiffDispatcher.INSTANCE.dispatchDiff(listUpdateCallback, nullPaddedList, nullPaddedList2);
        }
    }

    public static final int transformAnchorIndex(NullPaddedList<?> nullPaddedList, NullPaddedDiffResult nullPaddedDiffResult, NullPaddedList<?> nullPaddedList2, int i) {
        int convertOldPositionToNew;
        Intrinsics.checkNotNullParameter(nullPaddedList, "$this$transformAnchorIndex");
        Intrinsics.checkNotNullParameter(nullPaddedDiffResult, "diffResult");
        Intrinsics.checkNotNullParameter(nullPaddedList2, "newList");
        if (!nullPaddedDiffResult.getHasOverlap()) {
            return RangesKt.coerceIn(i, (ClosedRange<Integer>) RangesKt.until(0, nullPaddedList2.getSize()));
        }
        int placeholdersBefore = i - nullPaddedList.getPlaceholdersBefore();
        int storageCount = nullPaddedList.getStorageCount();
        if (placeholdersBefore >= 0 && storageCount > placeholdersBefore) {
            for (int i2 = 0; i2 <= 29; i2++) {
                int i3 = i2 / 2;
                int i4 = 1;
                if (i2 % 2 == 1) {
                    i4 = -1;
                }
                int i5 = (i3 * i4) + placeholdersBefore;
                if (i5 >= 0 && i5 < nullPaddedList.getStorageCount() && (convertOldPositionToNew = nullPaddedDiffResult.getDiff().convertOldPositionToNew(i5)) != -1) {
                    return convertOldPositionToNew + nullPaddedList2.getPlaceholdersBefore();
                }
            }
        }
        return RangesKt.coerceIn(i, (ClosedRange<Integer>) RangesKt.until(0, nullPaddedList2.getSize()));
    }
}
