package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016J&\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0016¨\u0006\t¸\u0006\u0000"}, mo27512d2 = {"androidx/paging/ItemKeyedDataSource$loadInitial$2$1", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "onResult", "", "data", "", "position", "", "totalCount", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.ItemKeyedDataSource$loadInitial$$inlined$suspendCancellableCoroutine$lambda$1 */
/* compiled from: ItemKeyedDataSource.kt */
public final class C0475xe515933 extends ItemKeyedDataSource.LoadInitialCallback<Value> {
    final /* synthetic */ CancellableContinuation $cont;
    final /* synthetic */ ItemKeyedDataSource.LoadInitialParams $params$inlined;
    final /* synthetic */ ItemKeyedDataSource this$0;

    C0475xe515933(CancellableContinuation cancellableContinuation, ItemKeyedDataSource itemKeyedDataSource, ItemKeyedDataSource.LoadInitialParams loadInitialParams) {
        this.$cont = cancellableContinuation;
        this.this$0 = itemKeyedDataSource;
        this.$params$inlined = loadInitialParams;
    }

    public void onResult(List<? extends Value> list, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "data");
        DataSource.BaseResult baseResult = new DataSource.BaseResult(list, this.this$0.getPrevKey$paging_common(list), this.this$0.getNextKey$paging_common(list), i, (i2 - list.size()) - i);
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(baseResult));
    }

    public void onResult(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        DataSource.BaseResult baseResult = new DataSource.BaseResult(list, this.this$0.getPrevKey$paging_common(list), this.this$0.getNextKey$paging_common(list), 0, 0, 24, (DefaultConstructorMarker) null);
        Result.Companion companion = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(baseResult));
    }
}
