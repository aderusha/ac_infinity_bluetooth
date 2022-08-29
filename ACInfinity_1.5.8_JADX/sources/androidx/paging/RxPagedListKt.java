package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PagedList;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import p014io.reactivex.BackpressureStrategy;
import p014io.reactivex.Flowable;
import p014io.reactivex.Observable;
import p014io.reactivex.Scheduler;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a}\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u0001H\u00022\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010\u0010\u001aw\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00122\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u0001H\u00022\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010\u0013\u001a\u0001\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00160\u0015\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u0001\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00160\u0015\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u001c\u001a\u0001\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00160\u0015\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00122\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u001d\u001a\u0001\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00160\u0015\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u001e\u001a\u0001\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00160 \"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010!\u001a\u0001\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00160 \"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\u00062\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010\"\u001a{\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00160 \"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00122\u0006\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010#\u001a{\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00160 \"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u0001H\u00022\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0007¢\u0006\u0002\u0010$¨\u0006%"}, mo27512d2 = {"createRxPagedListBuilder", "Landroidx/paging/RxPagedListBuilder;", "Key", "Value", "", "pagingSourceFactory", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "config", "Landroidx/paging/PagedList$Config;", "initialLoadKey", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "fetchScheduler", "Lio/reactivex/Scheduler;", "notifyScheduler", "(Lkotlin/jvm/functions/Function0;Landroidx/paging/PagedList$Config;Ljava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;)Landroidx/paging/RxPagedListBuilder;", "dataSourceFactory", "Landroidx/paging/DataSource$Factory;", "(Landroidx/paging/DataSource$Factory;Landroidx/paging/PagedList$Config;Ljava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;)Landroidx/paging/RxPagedListBuilder;", "toFlowable", "Lio/reactivex/Flowable;", "Landroidx/paging/PagedList;", "backpressureStrategy", "Lio/reactivex/BackpressureStrategy;", "(Lkotlin/jvm/functions/Function0;Landroidx/paging/PagedList$Config;Ljava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;", "pageSize", "", "(Lkotlin/jvm/functions/Function0;ILjava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;", "(Landroidx/paging/DataSource$Factory;Landroidx/paging/PagedList$Config;Ljava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;", "(Landroidx/paging/DataSource$Factory;ILjava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;Lio/reactivex/BackpressureStrategy;)Lio/reactivex/Flowable;", "toObservable", "Lio/reactivex/Observable;", "(Lkotlin/jvm/functions/Function0;Landroidx/paging/PagedList$Config;Ljava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;)Lio/reactivex/Observable;", "(Lkotlin/jvm/functions/Function0;ILjava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;)Lio/reactivex/Observable;", "(Landroidx/paging/DataSource$Factory;Landroidx/paging/PagedList$Config;Ljava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;)Lio/reactivex/Observable;", "(Landroidx/paging/DataSource$Factory;ILjava/lang/Object;Landroidx/paging/PagedList$BoundaryCallback;Lio/reactivex/Scheduler;Lio/reactivex/Scheduler;)Lio/reactivex/Observable;", "paging-rxjava2_release"}, mo27513k = 2, mo27514mv = {1, 4, 2})
/* compiled from: RxPagedList.kt */
public final class RxPagedListKt {
    private static final <Key, Value> RxPagedListBuilder<Key, Value> createRxPagedListBuilder(DataSource.Factory<Key, Value> factory, PagedList.C0510Config config, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2) {
        RxPagedListBuilder<Key, Value> boundaryCallback2 = new RxPagedListBuilder(factory, config).setInitialLoadKey(key).setBoundaryCallback(boundaryCallback);
        if (scheduler != null) {
            boundaryCallback2.setFetchScheduler(scheduler);
        }
        if (scheduler2 != null) {
            boundaryCallback2.setNotifyScheduler(scheduler2);
        }
        return boundaryCallback2;
    }

    private static final <Key, Value> RxPagedListBuilder<Key, Value> createRxPagedListBuilder(Function0<? extends PagingSource<Key, Value>> function0, PagedList.C0510Config config, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2) {
        RxPagedListBuilder<Key, Value> boundaryCallback2 = new RxPagedListBuilder(function0, config).setInitialLoadKey(key).setBoundaryCallback(boundaryCallback);
        if (scheduler != null) {
            boundaryCallback2.setFetchScheduler(scheduler);
        }
        if (scheduler2 != null) {
            boundaryCallback2.setNotifyScheduler(scheduler2);
        }
        return boundaryCallback2;
    }

    public static /* synthetic */ Observable toObservable$default(DataSource.Factory factory, PagedList.C0510Config config, Object obj, PagedList.BoundaryCallback boundaryCallback, Scheduler scheduler, Scheduler scheduler2, int i, Object obj2) {
        Object obj3 = (i & 2) != 0 ? null : obj;
        if ((i & 4) != 0) {
            boundaryCallback = null;
        }
        PagedList.BoundaryCallback boundaryCallback2 = boundaryCallback;
        if ((i & 8) != 0) {
            scheduler = null;
        }
        Scheduler scheduler3 = scheduler;
        if ((i & 16) != 0) {
            scheduler2 = null;
        }
        return toObservable(factory, config, obj3, boundaryCallback2, scheduler3, scheduler2);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(\n                config.pageSize,\n                config.prefetchDistance,\n                config.enablePlaceholders,\n                config.initialLoadSizeHint,\n                config.maxSize\n            ),\n            initialLoadKey,\n            this.asPagingSourceFactory(fetchScheduler?.asCoroutineDispatcher() ?: Dispatchers.IO)\n        ).observable", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.rxjava2.getObservable", "kotlinx.coroutines.rx2.asCoroutineDispatcher", "kotlinx.coroutines.Dispatchers"}))
    public static final <Key, Value> Observable<PagedList<Value>> toObservable(DataSource.Factory<Key, Value> factory, PagedList.C0510Config config, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(factory, "$this$toObservable");
        Intrinsics.checkNotNullParameter(config, "config");
        return createRxPagedListBuilder(factory, config, key, boundaryCallback, scheduler, scheduler2).buildObservable();
    }

    public static /* synthetic */ Observable toObservable$default(DataSource.Factory factory, int i, Object obj, PagedList.BoundaryCallback boundaryCallback, Scheduler scheduler, Scheduler scheduler2, int i2, Object obj2) {
        Object obj3 = (i2 & 2) != 0 ? null : obj;
        if ((i2 & 4) != 0) {
            boundaryCallback = null;
        }
        PagedList.BoundaryCallback boundaryCallback2 = boundaryCallback;
        if ((i2 & 8) != 0) {
            scheduler = null;
        }
        Scheduler scheduler3 = scheduler;
        if ((i2 & 16) != 0) {
            scheduler2 = null;
        }
        return toObservable(factory, i, obj3, boundaryCallback2, scheduler3, scheduler2);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(pageSize),\n            initialLoadKey,\n            this.asPagingSourceFactory(fetchScheduler?.asCoroutineDispatcher() ?: Dispatchers.IO)\n        ).observable", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.rxjava2.getObservable", "kotlinx.coroutines.rx2.asCoroutineDispatcher", "kotlinx.coroutines.Dispatchers"}))
    public static final <Key, Value> Observable<PagedList<Value>> toObservable(DataSource.Factory<Key, Value> factory, int i, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(factory, "$this$toObservable");
        return createRxPagedListBuilder(factory, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, (Object) null), key, boundaryCallback, scheduler, scheduler2).buildObservable();
    }

    public static /* synthetic */ Flowable toFlowable$default(DataSource.Factory factory, PagedList.C0510Config config, Object obj, PagedList.BoundaryCallback boundaryCallback, Scheduler scheduler, Scheduler scheduler2, BackpressureStrategy backpressureStrategy, int i, Object obj2) {
        return toFlowable(factory, config, (i & 2) != 0 ? null : obj, (i & 4) != 0 ? null : boundaryCallback, (i & 8) != 0 ? null : scheduler, (i & 16) != 0 ? null : scheduler2, (i & 32) != 0 ? BackpressureStrategy.LATEST : backpressureStrategy);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(\n                config.pageSize,\n                config.prefetchDistance,\n                config.enablePlaceholders,\n                config.initialLoadSizeHint,\n                config.maxSize\n            ),\n            initialLoadKey,\n            this.asPagingSourceFactory(fetchScheduler?.asCoroutineDispatcher() ?: Dispatchers.IO)\n        ).flowable", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.rxjava2.getFlowable", "kotlinx.coroutines.rx2.asCoroutineDispatcher", "kotlinx.coroutines.Dispatchers"}))
    public static final <Key, Value> Flowable<PagedList<Value>> toFlowable(DataSource.Factory<Key, Value> factory, PagedList.C0510Config config, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2, BackpressureStrategy backpressureStrategy) {
        Intrinsics.checkNotNullParameter(factory, "$this$toFlowable");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(backpressureStrategy, "backpressureStrategy");
        return createRxPagedListBuilder(factory, config, key, boundaryCallback, scheduler, scheduler2).buildFlowable(backpressureStrategy);
    }

    public static /* synthetic */ Flowable toFlowable$default(DataSource.Factory factory, int i, Object obj, PagedList.BoundaryCallback boundaryCallback, Scheduler scheduler, Scheduler scheduler2, BackpressureStrategy backpressureStrategy, int i2, Object obj2) {
        return toFlowable(factory, i, (i2 & 2) != 0 ? null : obj, (i2 & 4) != 0 ? null : boundaryCallback, (i2 & 8) != 0 ? null : scheduler, (i2 & 16) != 0 ? null : scheduler2, (i2 & 32) != 0 ? BackpressureStrategy.LATEST : backpressureStrategy);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(pageSize),\n            initialLoadKey,\n            this.asPagingSourceFactory(fetchScheduler?.asCoroutineDispatcher() ?: Dispatchers.IO)\n        ).flowable", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.rxjava2.getFlowable", "kotlinx.coroutines.rx2.asCoroutineDispatcher", "kotlinx.coroutines.Dispatchers"}))
    public static final <Key, Value> Flowable<PagedList<Value>> toFlowable(DataSource.Factory<Key, Value> factory, int i, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2, BackpressureStrategy backpressureStrategy) {
        BackpressureStrategy backpressureStrategy2 = backpressureStrategy;
        Intrinsics.checkNotNullParameter(factory, "$this$toFlowable");
        Intrinsics.checkNotNullParameter(backpressureStrategy2, "backpressureStrategy");
        return createRxPagedListBuilder(factory, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, (Object) null), key, boundaryCallback, scheduler, scheduler2).buildFlowable(backpressureStrategy2);
    }

    public static /* synthetic */ Observable toObservable$default(Function0 function0, PagedList.C0510Config config, Object obj, PagedList.BoundaryCallback boundaryCallback, Scheduler scheduler, Scheduler scheduler2, int i, Object obj2) {
        Object obj3 = (i & 2) != 0 ? null : obj;
        if ((i & 4) != 0) {
            boundaryCallback = null;
        }
        PagedList.BoundaryCallback boundaryCallback2 = boundaryCallback;
        if ((i & 8) != 0) {
            scheduler = null;
        }
        Scheduler scheduler3 = scheduler;
        if ((i & 16) != 0) {
            scheduler2 = null;
        }
        return toObservable(function0, config, obj3, boundaryCallback2, scheduler3, scheduler2);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(\n                config.pageSize,\n                config.prefetchDistance,\n                config.enablePlaceholders,\n                config.initialLoadSizeHint,\n                config.maxSize\n            ),\n            initialLoadKey,\n            this.asPagingSourceFactory(fetchScheduler?.asCoroutineDispatcher() ?: Dispatchers.IO)\n        ).observable", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.rxjava2.getObservable", "kotlinx.coroutines.rx2.asCoroutineDispatcher", "kotlinx.coroutines.Dispatchers"}))
    public static final <Key, Value> Observable<PagedList<Value>> toObservable(Function0<? extends PagingSource<Key, Value>> function0, PagedList.C0510Config config, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(function0, "$this$toObservable");
        Intrinsics.checkNotNullParameter(config, "config");
        return createRxPagedListBuilder(function0, config, key, boundaryCallback, scheduler, scheduler2).buildObservable();
    }

    public static /* synthetic */ Observable toObservable$default(Function0 function0, int i, Object obj, PagedList.BoundaryCallback boundaryCallback, Scheduler scheduler, Scheduler scheduler2, int i2, Object obj2) {
        Object obj3 = (i2 & 2) != 0 ? null : obj;
        if ((i2 & 4) != 0) {
            boundaryCallback = null;
        }
        PagedList.BoundaryCallback boundaryCallback2 = boundaryCallback;
        if ((i2 & 8) != 0) {
            scheduler = null;
        }
        Scheduler scheduler3 = scheduler;
        if ((i2 & 16) != 0) {
            scheduler2 = null;
        }
        return toObservable(function0, i, obj3, boundaryCallback2, scheduler3, scheduler2);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(pageSize),\n            initialLoadKey,\n            this\n        ).observable", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.rxjava2.getObservable"}))
    public static final <Key, Value> Observable<PagedList<Value>> toObservable(Function0<? extends PagingSource<Key, Value>> function0, int i, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2) {
        Intrinsics.checkNotNullParameter(function0, "$this$toObservable");
        return createRxPagedListBuilder(function0, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, (Object) null), key, boundaryCallback, scheduler, scheduler2).buildObservable();
    }

    public static /* synthetic */ Flowable toFlowable$default(Function0 function0, PagedList.C0510Config config, Object obj, PagedList.BoundaryCallback boundaryCallback, Scheduler scheduler, Scheduler scheduler2, BackpressureStrategy backpressureStrategy, int i, Object obj2) {
        return toFlowable(function0, config, (i & 2) != 0 ? null : obj, (i & 4) != 0 ? null : boundaryCallback, (i & 8) != 0 ? null : scheduler, (i & 16) != 0 ? null : scheduler2, (i & 32) != 0 ? BackpressureStrategy.LATEST : backpressureStrategy);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(\n                config.pageSize,\n                config.prefetchDistance,\n                config.enablePlaceholders,\n                config.initialLoadSizeHint,\n                config.maxSize\n            ),\n            initialLoadKey,\n            this\n        ).flowable", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.rxjava2.getFlowable"}))
    public static final <Key, Value> Flowable<PagedList<Value>> toFlowable(Function0<? extends PagingSource<Key, Value>> function0, PagedList.C0510Config config, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2, BackpressureStrategy backpressureStrategy) {
        Intrinsics.checkNotNullParameter(function0, "$this$toFlowable");
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(backpressureStrategy, "backpressureStrategy");
        return createRxPagedListBuilder(function0, config, key, boundaryCallback, scheduler, scheduler2).buildFlowable(backpressureStrategy);
    }

    public static /* synthetic */ Flowable toFlowable$default(Function0 function0, int i, Object obj, PagedList.BoundaryCallback boundaryCallback, Scheduler scheduler, Scheduler scheduler2, BackpressureStrategy backpressureStrategy, int i2, Object obj2) {
        return toFlowable(function0, i, (i2 & 2) != 0 ? null : obj, (i2 & 4) != 0 ? null : boundaryCallback, (i2 & 8) != 0 ? null : scheduler, (i2 & 16) != 0 ? null : scheduler2, (i2 & 32) != 0 ? BackpressureStrategy.LATEST : backpressureStrategy);
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n            PagingConfig(pageSize),\n            initialLoadKey,\n            this\n        ).flowable", imports = {"androidx.paging.Pager", "androidx.paging.PagingConfig", "androidx.paging.rxjava2.getFlowable"}))
    public static final <Key, Value> Flowable<PagedList<Value>> toFlowable(Function0<? extends PagingSource<Key, Value>> function0, int i, Key key, PagedList.BoundaryCallback<Value> boundaryCallback, Scheduler scheduler, Scheduler scheduler2, BackpressureStrategy backpressureStrategy) {
        BackpressureStrategy backpressureStrategy2 = backpressureStrategy;
        Intrinsics.checkNotNullParameter(function0, "$this$toFlowable");
        Intrinsics.checkNotNullParameter(backpressureStrategy2, "backpressureStrategy");
        return createRxPagedListBuilder(function0, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, (Object) null), key, boundaryCallback, scheduler, scheduler2).buildFlowable(backpressureStrategy2);
    }
}
