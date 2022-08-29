package androidx.paging;

import androidx.recyclerview.widget.ListUpdateCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0002\u0010\u0005J\"\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0016J\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J\u0018\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, mo27512d2 = {"Landroidx/paging/OffsettingListUpdateCallback;", "Landroidx/recyclerview/widget/ListUpdateCallback;", "offset", "", "callback", "(ILandroidx/recyclerview/widget/ListUpdateCallback;)V", "onChanged", "", "position", "count", "payload", "", "onInserted", "onMoved", "fromPosition", "toPosition", "onRemoved", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: NullPaddedListDiffHelper.kt */
final class OffsettingListUpdateCallback implements ListUpdateCallback {
    private final ListUpdateCallback callback;
    private final int offset;

    public OffsettingListUpdateCallback(int i, ListUpdateCallback listUpdateCallback) {
        Intrinsics.checkNotNullParameter(listUpdateCallback, "callback");
        this.offset = i;
        this.callback = listUpdateCallback;
    }

    public void onInserted(int i, int i2) {
        this.callback.onInserted(i + this.offset, i2);
    }

    public void onRemoved(int i, int i2) {
        this.callback.onRemoved(i + this.offset, i2);
    }

    public void onMoved(int i, int i2) {
        ListUpdateCallback listUpdateCallback = this.callback;
        int i3 = this.offset;
        listUpdateCallback.onMoved(i + i3, i2 + i3);
    }

    public void onChanged(int i, int i2, Object obj) {
        this.callback.onChanged(i + this.offset, i2, obj);
    }
}
