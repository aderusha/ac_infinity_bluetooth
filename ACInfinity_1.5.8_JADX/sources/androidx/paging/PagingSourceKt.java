package androidx.paging;

import androidx.paging.PagedList;
import androidx.paging.PagingSource;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0002H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, mo27512d2 = {"toRefreshLoadParams", "Landroidx/paging/PagingSource$LoadParams;", "Key", "", "Landroidx/paging/PagedList$Config;", "key", "(Landroidx/paging/PagedList$Config;Ljava/lang/Object;)Landroidx/paging/PagingSource$LoadParams;", "paging-common"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: PagingSource.kt */
public final class PagingSourceKt {
    public static final <Key> PagingSource.LoadParams<Key> toRefreshLoadParams(PagedList.C0510Config config, Key key) {
        Intrinsics.checkNotNullParameter(config, "$this$toRefreshLoadParams");
        return new PagingSource.LoadParams.Refresh<>(key, config.initialLoadSizeHint, config.enablePlaceholders);
    }
}
