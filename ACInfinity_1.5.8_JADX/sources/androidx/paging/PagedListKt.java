package androidx.paging;

import androidx.paging.PagedList;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aq\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004\"\b\b\u0001\u0010\u0002*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0010\b\u0002\u0010\f\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u0001H\u0003H\u0007¢\u0006\u0002\u0010\u000f¨\u0006\u0010"}, mo27512d2 = {"PagedList", "Landroidx/paging/PagedList;", "Value", "Key", "", "dataSource", "Landroidx/paging/DataSource;", "config", "Landroidx/paging/PagedList$Config;", "notifyExecutor", "Ljava/util/concurrent/Executor;", "fetchExecutor", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "initialKey", "(Landroidx/paging/DataSource;Landroidx/paging/PagedList$Config;Ljava/util/concurrent/Executor;Ljava/util/concurrent/Executor;Landroidx/paging/PagedList$BoundaryCallback;Ljava/lang/Object;)Landroidx/paging/PagedList;", "paging-common"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: PagedList.kt */
public final class PagedListKt {
    public static /* synthetic */ PagedList PagedList$default(DataSource dataSource, PagedList.C0510Config config, Executor executor, Executor executor2, PagedList.BoundaryCallback boundaryCallback, Object obj, int i, Object obj2) {
        if ((i & 16) != 0) {
            boundaryCallback = null;
        }
        return PagedList(dataSource, config, executor, executor2, boundaryCallback, (i & 32) != 0 ? null : obj);
    }

    @Deprecated(message = "DataSource is deprecated and has been replaced by PagingSource")
    public static final /* synthetic */ <Key, Value> PagedList<Value> PagedList(DataSource<Key, Value> dataSource, PagedList.C0510Config config, Executor executor, Executor executor2, PagedList.BoundaryCallback<Value> boundaryCallback, Key key) {
        Intrinsics.checkNotNullParameter(dataSource, "dataSource");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(executor, "notifyExecutor");
        Intrinsics.checkNotNullParameter(executor2, "fetchExecutor");
        return new PagedList.Builder(dataSource, config).setNotifyExecutor(executor).setFetchExecutor(executor2).setBoundaryCallback(boundaryCallback).setInitialKey(key).build();
    }
}
