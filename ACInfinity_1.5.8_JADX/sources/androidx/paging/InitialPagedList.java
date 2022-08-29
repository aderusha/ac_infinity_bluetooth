package androidx.paging;

import androidx.paging.PagedList;
import androidx.paging.PagingSource;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B/\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\r¨\u0006\u000e"}, mo27512d2 = {"Landroidx/paging/InitialPagedList;", "K", "", "V", "Landroidx/paging/ContiguousPagedList;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "notifyDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "config", "Landroidx/paging/PagedList$Config;", "initialLastKey", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/PagedList$Config;Ljava/lang/Object;)V", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: InitialPagedList.kt */
public final class InitialPagedList<K, V> extends ContiguousPagedList<K, V> {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InitialPagedList(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, PagedList.C0510Config config, K k) {
        super(new LegacyPagingSource(coroutineDispatcher, new InitialDataSource()), coroutineScope, coroutineDispatcher, coroutineDispatcher2, (PagedList.BoundaryCallback) null, config, PagingSource.LoadResult.Page.Companion.empty$paging_common(), k);
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(coroutineDispatcher2, "backgroundDispatcher");
        Intrinsics.checkNotNullParameter(config, "config");
    }
}
