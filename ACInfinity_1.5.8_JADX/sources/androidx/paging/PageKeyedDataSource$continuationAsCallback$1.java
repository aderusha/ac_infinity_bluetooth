package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001J%\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0007¨\u0006\b"}, mo27512d2 = {"androidx/paging/PageKeyedDataSource$continuationAsCallback$1", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "onResult", "", "data", "", "adjacentPageKey", "(Ljava/util/List;Ljava/lang/Object;)V", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PageKeyedDataSource.kt */
public final class PageKeyedDataSource$continuationAsCallback$1 extends PageKeyedDataSource.LoadCallback<Key, Value> {
    final /* synthetic */ CancellableContinuation $continuation;
    final /* synthetic */ boolean $isAppend;

    PageKeyedDataSource$continuationAsCallback$1(CancellableContinuation cancellableContinuation, boolean z) {
        this.$continuation = cancellableContinuation;
        this.$isAppend = z;
    }

    public void onResult(List<? extends Value> list, Key key) {
        Intrinsics.checkNotNullParameter(list, "data");
        Continuation continuation = this.$continuation;
        boolean z = this.$isAppend;
        DataSource.BaseResult baseResult = new DataSource.BaseResult(list, z ? null : key, z ? key : null, 0, 0, 24, (DefaultConstructorMarker) null);
        Result.Companion companion = Result.Companion;
        continuation.resumeWith(Result.m1023constructorimpl(baseResult));
    }
}
