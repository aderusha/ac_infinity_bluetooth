package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0016¨\u0006\u0006¸\u0006\u0000"}, mo27512d2 = {"androidx/paging/PositionalDataSource$loadRange$2$1", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "onResult", "", "data", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* renamed from: androidx.paging.PositionalDataSource$loadRange$$inlined$suspendCancellableCoroutine$lambda$1 */
/* compiled from: PositionalDataSource.kt */
public final class C0556xfa338615 extends PositionalDataSource.LoadRangeCallback<T> {
    final /* synthetic */ CancellableContinuation $cont;
    final /* synthetic */ PositionalDataSource.LoadRangeParams $params$inlined;
    final /* synthetic */ PositionalDataSource this$0;

    C0556xfa338615(CancellableContinuation cancellableContinuation, PositionalDataSource positionalDataSource, PositionalDataSource.LoadRangeParams loadRangeParams) {
        this.$cont = cancellableContinuation;
        this.this$0 = positionalDataSource;
        this.$params$inlined = loadRangeParams;
    }

    public void onResult(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        Integer valueOf = this.$params$inlined.startPosition == 0 ? null : Integer.valueOf(this.$params$inlined.startPosition);
        if (this.this$0.isInvalid()) {
            DataSource.BaseResult empty$paging_common = DataSource.BaseResult.Companion.empty$paging_common();
            Result.Companion companion = Result.Companion;
            this.$cont.resumeWith(Result.m1023constructorimpl(empty$paging_common));
            return;
        }
        DataSource.BaseResult baseResult = new DataSource.BaseResult(list, valueOf, Integer.valueOf(this.$params$inlined.startPosition + list.size()), 0, 0, 24, (DefaultConstructorMarker) null);
        Result.Companion companion2 = Result.Companion;
        this.$cont.resumeWith(Result.m1023constructorimpl(baseResult));
    }
}
