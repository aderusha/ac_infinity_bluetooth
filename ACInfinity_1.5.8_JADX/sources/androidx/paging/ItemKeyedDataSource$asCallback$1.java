package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016¨\u0006\u0006"}, mo27512d2 = {"androidx/paging/ItemKeyedDataSource$asCallback$1", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "onResult", "", "data", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ItemKeyedDataSource.kt */
public final class ItemKeyedDataSource$asCallback$1 extends ItemKeyedDataSource.LoadCallback<Value> {
    final /* synthetic */ CancellableContinuation $this_asCallback;
    final /* synthetic */ ItemKeyedDataSource this$0;

    ItemKeyedDataSource$asCallback$1(ItemKeyedDataSource itemKeyedDataSource, CancellableContinuation<? super DataSource.BaseResult<Value>> cancellableContinuation) {
        this.this$0 = itemKeyedDataSource;
        this.$this_asCallback = cancellableContinuation;
    }

    public void onResult(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        DataSource.BaseResult baseResult = new DataSource.BaseResult(list, this.this$0.getPrevKey$paging_common(list), this.this$0.getNextKey$paging_common(list), 0, 0, 24, (DefaultConstructorMarker) null);
        Result.Companion companion = Result.Companion;
        this.$this_asCallback.resumeWith(Result.m1023constructorimpl(baseResult));
    }
}
