package androidx.paging;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.rx2.RxSchedulerKt;
import kotlinx.coroutines.rx2.SchedulerCoroutineDispatcher;
import p014io.reactivex.BackpressureStrategy;
import p014io.reactivex.Flowable;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.Scheduler;
import p014io.reactivex.functions.Cancellable;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0001,B)\b\u0017\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tB)\b\u0017\u0012\u0018\u0010\u0004\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00060\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fB#\b\u0017\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\u000fB#\b\u0017\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000e\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\u0010J\u001a\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010 0\u001f2\u0006\u0010!\u001a\u00020\"J\u0012\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010 0$J\"\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0012J\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010'\u001a\u00020\u0019J!\u0010(\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\b\u0010)\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010*J\u001a\u0010+\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00002\u0006\u0010'\u001a\u00020\u0019R\u001c\u0010\u0011\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u0012X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0015\u0010\u0014R\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000eX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001a\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0004\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, mo27512d2 = {"Landroidx/paging/RxPagedListBuilder;", "Key", "", "Value", "pagingSourceFactory", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "config", "Landroidx/paging/PagedList$Config;", "(Lkotlin/jvm/functions/Function0;Landroidx/paging/PagedList$Config;)V", "pageSize", "", "(Lkotlin/jvm/functions/Function0;I)V", "dataSourceFactory", "Landroidx/paging/DataSource$Factory;", "(Landroidx/paging/DataSource$Factory;Landroidx/paging/PagedList$Config;)V", "(Landroidx/paging/DataSource$Factory;I)V", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "getBoundaryCallback$annotations", "()V", "getConfig$annotations", "fetchDispatcher", "Lkotlinx/coroutines/rx2/SchedulerCoroutineDispatcher;", "fetchScheduler", "Lio/reactivex/Scheduler;", "initialLoadKey", "Ljava/lang/Object;", "notifyDispatcher", "notifyScheduler", "buildFlowable", "Lio/reactivex/Flowable;", "Landroidx/paging/PagedList;", "backpressureStrategy", "Lio/reactivex/BackpressureStrategy;", "buildObservable", "Lio/reactivex/Observable;", "setBoundaryCallback", "setFetchScheduler", "scheduler", "setInitialLoadKey", "key", "(Ljava/lang/Object;)Landroidx/paging/RxPagedListBuilder;", "setNotifyScheduler", "PagingObservableOnSubscribe", "paging-rxjava2_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
@Deprecated(message = "PagedList is deprecated and has been replaced by PagingData")
/* compiled from: RxPagedListBuilder.kt */
public final class RxPagedListBuilder<Key, Value> {
    private PagedList.BoundaryCallback<Value> boundaryCallback;
    private final PagedList.C0510Config config;
    private final DataSource.Factory<Key, Value> dataSourceFactory;
    private SchedulerCoroutineDispatcher fetchDispatcher;
    private Scheduler fetchScheduler;
    private Key initialLoadKey;
    private SchedulerCoroutineDispatcher notifyDispatcher;
    private Scheduler notifyScheduler;
    private final Function0<PagingSource<Key, Value>> pagingSourceFactory;

    private static /* synthetic */ void getBoundaryCallback$annotations() {
    }

    private static /* synthetic */ void getConfig$annotations() {
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                config = PagingConfig(\n                    config.pageSize,\n                    config.prefetchDistance,\n                    config.enablePlaceholders,\n                    config.initialLoadSizeHint,\n                    config.maxSize\n                ),\n                initialKey = null,\n                pagingSourceFactory = pagingSourceFactory\n            ).flowable", imports = {"androidx.paging.PagingConfig", "androidx.paging.Pager", "androidx.paging.rxjava2.getFlowable"}))
    public RxPagedListBuilder(Function0<? extends PagingSource<Key, Value>> function0, PagedList.C0510Config config2) {
        Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
        Intrinsics.checkNotNullParameter(config2, "config");
        this.pagingSourceFactory = function0;
        this.dataSourceFactory = null;
        this.config = config2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                config = PagingConfig(pageSize),\n                initialKey = null,\n                pagingSourceFactory = pagingSourceFactory\n            ).flowable", imports = {"androidx.paging.PagingConfig", "androidx.paging.Pager", "androidx.paging.rxjava2.getFlowable"}))
    public RxPagedListBuilder(Function0<? extends PagingSource<Key, Value>> function0, int i) {
        this(function0, new PagedList.C0510Config.Builder().setPageSize(i).build());
        Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
    }

    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                config = PagingConfig(\n                    config.pageSize,\n                    config.prefetchDistance,\n                    config.enablePlaceholders,\n                    config.initialLoadSizeHint,\n                    config.maxSize\n                ),\n                initialKey = null,\n                pagingSourceFactory = dataSourceFactory.asPagingSourceFactory(Dispatchers.IO)\n            ).flowable", imports = {"androidx.paging.PagingConfig", "androidx.paging.Pager", "androidx.paging.rxjava2.getFlowable", "kotlinx.coroutines.Dispatchers"}))
    public RxPagedListBuilder(DataSource.Factory<Key, Value> factory, PagedList.C0510Config config2) {
        Intrinsics.checkNotNullParameter(factory, "dataSourceFactory");
        Intrinsics.checkNotNullParameter(config2, "config");
        this.pagingSourceFactory = null;
        this.dataSourceFactory = factory;
        this.config = config2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData", replaceWith = @ReplaceWith(expression = "Pager(\n                config = PagingConfig(pageSize),\n                initialKey = null,\n                pagingSourceFactory = dataSourceFactory.asPagingSourceFactory(Dispatchers.IO)\n            ).flowable", imports = {"androidx.paging.PagingConfig", "androidx.paging.Pager", "androidx.paging.rxjava2.getFlowable", "kotlinx.coroutines.Dispatchers"}))
    public RxPagedListBuilder(DataSource.Factory<Key, Value> factory, int i) {
        this(factory, new PagedList.C0510Config.Builder().setPageSize(i).build());
        Intrinsics.checkNotNullParameter(factory, "dataSourceFactory");
    }

    public final RxPagedListBuilder<Key, Value> setInitialLoadKey(Key key) {
        RxPagedListBuilder<Key, Value> rxPagedListBuilder = this;
        rxPagedListBuilder.initialLoadKey = key;
        return rxPagedListBuilder;
    }

    public final RxPagedListBuilder<Key, Value> setBoundaryCallback(PagedList.BoundaryCallback<Value> boundaryCallback2) {
        RxPagedListBuilder<Key, Value> rxPagedListBuilder = this;
        rxPagedListBuilder.boundaryCallback = boundaryCallback2;
        return rxPagedListBuilder;
    }

    public final RxPagedListBuilder<Key, Value> setNotifyScheduler(Scheduler scheduler) {
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        RxPagedListBuilder<Key, Value> rxPagedListBuilder = this;
        rxPagedListBuilder.notifyScheduler = scheduler;
        rxPagedListBuilder.notifyDispatcher = RxSchedulerKt.asCoroutineDispatcher(scheduler);
        return rxPagedListBuilder;
    }

    public final RxPagedListBuilder<Key, Value> setFetchScheduler(Scheduler scheduler) {
        Intrinsics.checkNotNullParameter(scheduler, "scheduler");
        RxPagedListBuilder<Key, Value> rxPagedListBuilder = this;
        rxPagedListBuilder.fetchScheduler = scheduler;
        rxPagedListBuilder.fetchDispatcher = RxSchedulerKt.asCoroutineDispatcher(scheduler);
        return rxPagedListBuilder;
    }

    public final Observable<PagedList<Value>> buildObservable() {
        Scheduler scheduler = this.notifyScheduler;
        if (scheduler == null) {
            Executor mainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(mainThreadExecutor, "ArchTaskExecutor.getMainThreadExecutor()");
            scheduler = new ScheduledExecutor(mainThreadExecutor);
        }
        SchedulerCoroutineDispatcher schedulerCoroutineDispatcher = this.notifyDispatcher;
        if (schedulerCoroutineDispatcher == null) {
            schedulerCoroutineDispatcher = RxSchedulerKt.asCoroutineDispatcher(scheduler);
        }
        Scheduler scheduler2 = this.fetchScheduler;
        if (scheduler2 == null) {
            Executor iOThreadExecutor = ArchTaskExecutor.getIOThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(iOThreadExecutor, "ArchTaskExecutor.getIOThreadExecutor()");
            scheduler2 = new ScheduledExecutor(iOThreadExecutor);
        }
        SchedulerCoroutineDispatcher schedulerCoroutineDispatcher2 = this.fetchDispatcher;
        if (schedulerCoroutineDispatcher2 == null) {
            schedulerCoroutineDispatcher2 = RxSchedulerKt.asCoroutineDispatcher(scheduler2);
        }
        Function0<PagingSource<Key, Value>> function0 = this.pagingSourceFactory;
        if (function0 == null) {
            DataSource.Factory<Key, Value> factory = this.dataSourceFactory;
            function0 = factory != null ? factory.asPagingSourceFactory(schedulerCoroutineDispatcher2) : null;
        }
        Function0<PagingSource<Key, Value>> function02 = function0;
        if (function02 != null) {
            Observable<PagedList<Value>> subscribeOn = Observable.create(new PagingObservableOnSubscribe(this.initialLoadKey, this.config, this.boundaryCallback, function02, schedulerCoroutineDispatcher, schedulerCoroutineDispatcher2)).observeOn(scheduler).subscribeOn(scheduler2);
            Intrinsics.checkNotNullExpressionValue(subscribeOn, "Observable\n            .…bscribeOn(fetchScheduler)");
            return subscribeOn;
        }
        throw new IllegalStateException("RxPagedList cannot be built without a PagingSourceFactory or DataSource.Factory".toString());
    }

    public final Flowable<PagedList<Value>> buildFlowable(BackpressureStrategy backpressureStrategy) {
        Intrinsics.checkNotNullParameter(backpressureStrategy, "backpressureStrategy");
        Flowable<PagedList<Value>> flowable = buildObservable().toFlowable(backpressureStrategy);
        Intrinsics.checkNotNullExpressionValue(flowable, "buildObservable().toFlowable(backpressureStrategy)");
        return flowable;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00050\u00042\u00020\u0006BQ\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0002\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u000b\u0012\u0018\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u000e0\r\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0010¢\u0006\u0002\u0010\u0012J\b\u0010\u001e\u001a\u00020\u0014H\u0016J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020\u001bH\u0002J$\u0010!\u001a\u00020\u00142\f\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00030\u00052\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00030\u0005H\u0002J\u001c\u0010$\u001a\u00020\u00142\u0012\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u00050\u0019H\u0016R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0003\u0018\u00010\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00030\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u00050\u0019X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u000e0\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, mo27512d2 = {"Landroidx/paging/RxPagedListBuilder$PagingObservableOnSubscribe;", "Key", "", "Value", "Lio/reactivex/ObservableOnSubscribe;", "Landroidx/paging/PagedList;", "Lio/reactivex/functions/Cancellable;", "initialLoadKey", "config", "Landroidx/paging/PagedList$Config;", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "pagingSourceFactory", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "notifyDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "fetchDispatcher", "(Ljava/lang/Object;Landroidx/paging/PagedList$Config;Landroidx/paging/PagedList$BoundaryCallback;Lkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "callback", "", "currentData", "currentJob", "Lkotlinx/coroutines/Job;", "emitter", "Lio/reactivex/ObservableEmitter;", "firstSubscribe", "", "refreshRetryCallback", "Ljava/lang/Runnable;", "cancel", "invalidate", "force", "onItemUpdate", "previous", "next", "subscribe", "paging-rxjava2_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: RxPagedListBuilder.kt */
    public static final class PagingObservableOnSubscribe<Key, Value> implements ObservableOnSubscribe<PagedList<Value>>, Cancellable {
        /* access modifiers changed from: private */
        public final PagedList.BoundaryCallback<Value> boundaryCallback;
        /* access modifiers changed from: private */
        public final Function0<Unit> callback = new RxPagedListBuilder$PagingObservableOnSubscribe$callback$1(this);
        /* access modifiers changed from: private */
        public final PagedList.C0510Config config;
        /* access modifiers changed from: private */
        public PagedList<Value> currentData;
        private Job currentJob;
        /* access modifiers changed from: private */
        public ObservableEmitter<PagedList<Value>> emitter;
        /* access modifiers changed from: private */
        public final CoroutineDispatcher fetchDispatcher;
        private boolean firstSubscribe = true;
        /* access modifiers changed from: private */
        public final CoroutineDispatcher notifyDispatcher;
        /* access modifiers changed from: private */
        public final Function0<PagingSource<Key, Value>> pagingSourceFactory;
        private final Runnable refreshRetryCallback;

        public PagingObservableOnSubscribe(Key key, PagedList.C0510Config config2, PagedList.BoundaryCallback<Value> boundaryCallback2, Function0<? extends PagingSource<Key, Value>> function0, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2) {
            Intrinsics.checkNotNullParameter(config2, "config");
            Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
            Intrinsics.checkNotNullParameter(coroutineDispatcher, "notifyDispatcher");
            Intrinsics.checkNotNullParameter(coroutineDispatcher2, "fetchDispatcher");
            this.config = config2;
            this.boundaryCallback = boundaryCallback2;
            this.pagingSourceFactory = function0;
            this.notifyDispatcher = coroutineDispatcher;
            this.fetchDispatcher = coroutineDispatcher2;
            Runnable rxPagedListBuilder$PagingObservableOnSubscribe$refreshRetryCallback$1 = new C0563xed62ccbd(this);
            this.refreshRetryCallback = rxPagedListBuilder$PagingObservableOnSubscribe$refreshRetryCallback$1;
            PagedList<Value> initialPagedList = new InitialPagedList<>(GlobalScope.INSTANCE, coroutineDispatcher, coroutineDispatcher2, config2, key);
            this.currentData = initialPagedList;
            initialPagedList.setRetryCallback(rxPagedListBuilder$PagingObservableOnSubscribe$refreshRetryCallback$1);
        }

        public static final /* synthetic */ ObservableEmitter access$getEmitter$p(PagingObservableOnSubscribe pagingObservableOnSubscribe) {
            ObservableEmitter<PagedList<Value>> observableEmitter = pagingObservableOnSubscribe.emitter;
            if (observableEmitter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("emitter");
            }
            return observableEmitter;
        }

        public void subscribe(ObservableEmitter<PagedList<Value>> observableEmitter) {
            Intrinsics.checkNotNullParameter(observableEmitter, "emitter");
            this.emitter = observableEmitter;
            observableEmitter.setCancellable(this);
            if (this.firstSubscribe) {
                observableEmitter.onNext(this.currentData);
                this.firstSubscribe = false;
            }
            invalidate(false);
        }

        public void cancel() {
            this.currentData.getPagingSource().unregisterInvalidatedCallback(this.callback);
        }

        /* access modifiers changed from: private */
        public final void invalidate(boolean z) {
            Job job = this.currentJob;
            if (job == null || z) {
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                this.currentJob = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, this.fetchDispatcher, (CoroutineStart) null, new RxPagedListBuilder$PagingObservableOnSubscribe$invalidate$1(this, (Continuation) null), 2, (Object) null);
            }
        }

        /* access modifiers changed from: private */
        public final void onItemUpdate(PagedList<Value> pagedList, PagedList<Value> pagedList2) {
            pagedList.setRetryCallback((Runnable) null);
            pagedList2.setRetryCallback(this.refreshRetryCallback);
        }
    }
}
