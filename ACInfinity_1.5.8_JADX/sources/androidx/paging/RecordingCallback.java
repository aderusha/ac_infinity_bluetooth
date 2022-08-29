package androidx.paging;

import androidx.paging.PagedList;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0001J\u0018\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, mo27512d2 = {"Landroidx/paging/RecordingCallback;", "Landroidx/paging/PagedList$Callback;", "()V", "list", "", "", "dispatchRecordingTo", "", "other", "onChanged", "position", "count", "onInserted", "onRemoved", "Companion", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: RecordingCallback.kt */
public final class RecordingCallback extends PagedList.Callback {
    private static final int Changed = 0;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int Inserted = 1;
    private static final int Removed = 2;
    private final List<Integer> list = new ArrayList();

    public void onChanged(int i, int i2) {
        this.list.add(0);
        this.list.add(Integer.valueOf(i));
        this.list.add(Integer.valueOf(i2));
    }

    public void onInserted(int i, int i2) {
        this.list.add(1);
        this.list.add(Integer.valueOf(i));
        this.list.add(Integer.valueOf(i2));
    }

    public void onRemoved(int i, int i2) {
        this.list.add(2);
        this.list.add(Integer.valueOf(i));
        this.list.add(Integer.valueOf(i2));
    }

    public final void dispatchRecordingTo(PagedList.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "other");
        IntProgression step = RangesKt.step((IntProgression) RangesKt.until(0, this.list.size()), 3);
        int first = step.getFirst();
        int last = step.getLast();
        int step2 = step.getStep();
        if (step2 < 0 ? first >= last : first <= last) {
            while (true) {
                int intValue = this.list.get(first).intValue();
                if (intValue == 0) {
                    callback.onChanged(this.list.get(first + 1).intValue(), this.list.get(first + 2).intValue());
                } else if (intValue == 1) {
                    callback.onInserted(this.list.get(first + 1).intValue(), this.list.get(first + 2).intValue());
                } else if (intValue == 2) {
                    callback.onRemoved(this.list.get(first + 1).intValue(), this.list.get(first + 2).intValue());
                } else {
                    throw new IllegalStateException("Unexpected recording value");
                }
                if (first == last) {
                    break;
                }
                first += step2;
            }
        }
        this.list.clear();
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, mo27512d2 = {"Landroidx/paging/RecordingCallback$Companion;", "", "()V", "Changed", "", "Inserted", "Removed", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: RecordingCallback.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
