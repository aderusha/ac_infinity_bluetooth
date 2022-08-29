package androidx.paging;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.GlobalScope;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a}\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00042\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001a}\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00042\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0014\u001am\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00030\u00152\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00042\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0002\u0010\u0018\u001am\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0005\"\b\b\u0001\u0010\u0003*\u00020\u0005*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00030\u00152\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00042\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, mo27512d2 = {"toLiveData", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Value", "Key", "", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "config", "Landroidx/paging/PagedList$Config;", "initialLoadKey", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "fetchDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lkotlin/jvm/functions/Function0;Landroidx/paging/PagedList$Config;Ljava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;)Landroidx/lifecycle/LiveData;", "pageSize", "", "(Lkotlin/jvm/functions/Function0;ILjava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;)Landroidx/lifecycle/LiveData;", "Landroidx/paging/DataSource$Factory;", "fetchExecutor", "Ljava/util/concurrent/Executor;", "(Landroidx/paging/DataSource$Factory;Landroidx/paging/PagedList$Config;Ljava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Ljava/util/concurrent/Executor;)Landroidx/lifecycle/LiveData;", "(Landroidx/paging/DataSource$Factory;ILjava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Ljava/util/concurrent/Executor;)Landroidx/lifecycle/LiveData;", "paging-runtime_release"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: LivePagedList.kt */
public final class LivePagedListKt {
    public static /* synthetic */ LiveData toLiveData$default(DataSource.Factory factory, PagedList.C0510Config config, Object obj, PagedList.BoundaryCallback boundaryCallback, Executor executor, int i, Object obj2) {
        if ((i & 2) != 0) {
            obj = null;
        }
        if ((i & 4) != 0) {
            boundaryCallback = null;
        }
        if ((i & 8) != 0) {
            executor = ArchTaskExecutor.getIOThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(executor, "ArchTaskExecutor.getIOThreadExecutor()");
        }
        return toLiveData(factory, config, obj, boundaryCallback, executor);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(\n                config.pageSize,\n                config.prefetchDistance,\n                config.enablePlaceholders,\n                config.initialLoadSizeHint,\n                config.maxSize\n            ),\n            initialLoadKey,\n            this.asPagingSourceFactory(fetchExecutor.asCoroutineDispatcher())\n        ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData", "kotlinx.coroutines.asCoroutineDispatcher"}))
    public static final <Key, Value> LiveData<PagedList<Value>> toLiveData(DataSource.Factory<Key, Value> factory, PagedList.C0510Config config, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Executor executor) {
        Intrinsics.checkNotNullParameter(factory, "$this$toLiveData");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(executor, "fetchExecutor");
        return new LivePagedListBuilder(factory, config).setInitialLoadKey(key).setBoundaryCallback(boundaryCallback).setFetchExecutor(executor).build();
    }

    public static /* synthetic */ LiveData toLiveData$default(DataSource.Factory factory, int i, Object obj, PagedList.BoundaryCallback boundaryCallback, Executor executor, int i2, Object obj2) {
        if ((i2 & 2) != 0) {
            obj = null;
        }
        if ((i2 & 4) != 0) {
            boundaryCallback = null;
        }
        if ((i2 & 8) != 0) {
            executor = ArchTaskExecutor.getIOThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(executor, "ArchTaskExecutor.getIOThreadExecutor()");
        }
        return toLiveData(factory, i, obj, boundaryCallback, executor);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(pageSize),\n            initialLoadKey,\n            this.asPagingSourceFactory(fetchExecutor.asCoroutineDispatcher())\n        ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData", "kotlinx.coroutines.asCoroutineDispatcher"}))
    public static final <Key, Value> LiveData<PagedList<Value>> toLiveData(DataSource.Factory<Key, Value> factory, int i, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Executor executor) {
        Intrinsics.checkNotNullParameter(factory, "$this$toLiveData");
        Intrinsics.checkNotNullParameter(executor, "fetchExecutor");
        return new LivePagedListBuilder(factory, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, (Object) null)).setInitialLoadKey(key).setBoundaryCallback(boundaryCallback).setFetchExecutor(executor).build();
    }

    public static /* synthetic */ LiveData toLiveData$default(Function0 function0, PagedList.C0510Config config, Object obj, PagedList.BoundaryCallback boundaryCallback, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, int i, Object obj2) {
        Object obj3 = (i & 2) != 0 ? null : obj;
        if ((i & 4) != 0) {
            boundaryCallback = null;
        }
        PagedList.BoundaryCallback boundaryCallback2 = boundaryCallback;
        if ((i & 8) != 0) {
            coroutineScope = GlobalScope.INSTANCE;
        }
        CoroutineScope coroutineScope2 = coroutineScope;
        if ((i & 16) != 0) {
            Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(iOThreadExecutor, "ArchTaskExecutor.getIOThreadExecutor()");
            coroutineDispatcher = ExecutorsKt.from(iOThreadExecutor);
        }
        return toLiveData(function0, config, obj3, boundaryCallback2, coroutineScope2, coroutineDispatcher);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(\n                config.pageSize,\n                config.prefetchDistance,\n                config.enablePlaceholders,\n                config.initialLoadSizeHint,\n                config.maxSize\n            ),\n            initialLoadKey,\n            this\n        ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData"}))
    public static final <Key, Value> LiveData<PagedList<Value>> toLiveData(Function0<? extends PagingSource<Key, Value>> function0, PagedList.C0510Config config, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkNotNullParameter(function0, "$this$toLiveData");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "fetchDispatcher");
        Executor mainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "ArchTaskExecutor.getMainThreadExecutor()");
        return new LivePagedList<>(coroutineScope, key, config, boundaryCallback, function0, ExecutorsKt.from(mainThreadExecutor), coroutineDispatcher);
    }

    public static /* synthetic */ LiveData toLiveData$default(Function0 function0, int i, Object obj, PagedList.BoundaryCallback boundaryCallback, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, int i2, Object obj2) {
        Object obj3 = (i2 & 2) != 0 ? null : obj;
        if ((i2 & 4) != 0) {
            boundaryCallback = null;
        }
        PagedList.BoundaryCallback boundaryCallback2 = boundaryCallback;
        if ((i2 & 8) != 0) {
            coroutineScope = GlobalScope.INSTANCE;
        }
        CoroutineScope coroutineScope2 = coroutineScope;
        if ((i2 & 16) != 0) {
            Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(iOThreadExecutor, "ArchTaskExecutor.getIOThreadExecutor()");
            coroutineDispatcher = ExecutorsKt.from(iOThreadExecutor);
        }
        return toLiveData(function0, i, obj3, boundaryCallback2, coroutineScope2, coroutineDispatcher);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(pageSize),\n            initialLoadKey,\n            this\n        ).liveData", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.liveData"}))
    public static final <Key, Value> LiveData<PagedList<Value>> toLiveData(Function0<? extends PagingSource<Key, Value>> function0, int i, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkNotNullParameter(function0, "$this$toLiveData");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "fetchDispatcher");
        PagedList.C0510Config build = new PagedList.C0510Config.Builder().setPageSize(i).build();
        Executor mainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
        Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "ArchTaskExecutor.getMainThreadExecutor()");
        return new LivePagedList<>(coroutineScope, key, build, boundaryCallback, function0, ExecutorsKt.from(mainThreadExecutor), coroutineDispatcher);
    }
}
