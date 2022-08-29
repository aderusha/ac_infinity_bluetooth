package androidx.paging;

import kotlin.Metadata;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¨\u0006\t"}, mo27512d2 = {"androidx/paging/AsyncPagingDataDiffer$differCallback$1", "Landroidx/paging/DifferCallback;", "onChanged", "", "position", "", "count", "onInserted", "onRemoved", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: AsyncPagingDataDiffer.kt */
public final class AsyncPagingDataDiffer$differCallback$1 implements DifferCallback {
    final /* synthetic */ AsyncPagingDataDiffer this$0;

    AsyncPagingDataDiffer$differCallback$1(AsyncPagingDataDiffer asyncPagingDataDiffer) {
        this.this$0 = asyncPagingDataDiffer;
    }

    public void onInserted(int i, int i2) {
        if (i2 > 0) {
            this.this$0.updateCallback.onInserted(i, i2);
        }
    }

    public void onRemoved(int i, int i2) {
        if (i2 > 0) {
            this.this$0.updateCallback.onRemoved(i, i2);
        }
    }

    public void onChanged(int i, int i2) {
        if (i2 > 0) {
            this.this$0.updateCallback.onChanged(i, i2, (Object) null);
        }
    }
}
