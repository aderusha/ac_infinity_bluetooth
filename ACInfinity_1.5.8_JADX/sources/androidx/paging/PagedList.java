package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PagingSource;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0018\b'\u0018\u0000 d*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0006abcdefB?\b\u0000\u0012\u0010\u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u0010?\u001a\u00020(2\u0006\u0010@\u001a\u00020\u0012J \u0010?\u001a\u00020(2\u000e\u0010A\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010B2\u0006\u0010@\u001a\u00020\u0012H\u0007J \u0010C\u001a\u00020(2\u0018\u0010D\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0%J\b\u0010E\u001a\u00020(H&J\"\u0010F\u001a\u00020(2\u0018\u0010@\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0%H'J\u001d\u0010G\u001a\u00020(2\u0006\u0010H\u001a\u00020&2\u0006\u0010I\u001a\u00020'H\u0000¢\u0006\u0002\bJJ\u0018\u0010K\u001a\u0004\u0018\u00018\u00002\u0006\u0010L\u001a\u00020*H\u0002¢\u0006\u0002\u0010MJ\u000e\u0010N\u001a\b\u0012\u0004\u0012\u00028\u00000OH\u0007J\b\u0010P\u001a\u00020*H\u0007J\u000e\u0010Q\u001a\u00020(2\u0006\u0010L\u001a\u00020*J\u0010\u0010R\u001a\u00020(2\u0006\u0010L\u001a\u00020*H'J\u0018\u0010S\u001a\u00020(2\u0006\u0010T\u001a\u00020*2\u0006\u0010U\u001a\u00020*H\u0007J\u001d\u0010V\u001a\u00020(2\u0006\u0010T\u001a\u00020*2\u0006\u0010U\u001a\u00020*H\u0000¢\u0006\u0002\bWJ\u0018\u0010X\u001a\u00020(2\u0006\u0010T\u001a\u00020*2\u0006\u0010U\u001a\u00020*H\u0007J\u000e\u0010Y\u001a\u00020(2\u0006\u0010@\u001a\u00020\u0012J \u0010Z\u001a\u00020(2\u0018\u0010D\u001a\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0%J\b\u0010[\u001a\u00020(H\u0016J\u0018\u0010\\\u001a\u00020(2\u0006\u0010]\u001a\u00020&2\u0006\u0010^\u001a\u00020'H\u0017J\u0012\u0010_\u001a\u00020(2\b\u00103\u001a\u0004\u0018\u000104H\u0007J\f\u0010`\u001a\b\u0012\u0004\u0012\u00028\u00000BR\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u00188FX\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001fR\u0014\u0010 \u001a\u00020\u001e8VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u001fR\u0014\u0010!\u001a\u0004\u0018\u00010\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R,\u0010$\u001a \u0012\u001c\u0012\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0%0\u00110\u0010X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010)\u001a\u00020*8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R \u0010\u0004\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\u00058\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u00101\u001a\u00020*8F¢\u0006\u0006\u001a\u0004\b2\u0010,R\u001c\u00103\u001a\u0004\u0018\u000104X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0014\u00109\u001a\u00020*X\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010,R\u0014\u0010;\u001a\u00020*8VX\u0004¢\u0006\u0006\u001a\u0004\b<\u0010,R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>¨\u0006g"}, mo27512d2 = {"Landroidx/paging/PagedList;", "T", "", "Ljava/util/AbstractList;", "pagingSource", "Landroidx/paging/PagingSource;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "notifyDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "storage", "Landroidx/paging/PagedStorage;", "config", "Landroidx/paging/PagedList$Config;", "(Landroidx/paging/PagingSource;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/PagedStorage;Landroidx/paging/PagedList$Config;)V", "callbacks", "", "Ljava/lang/ref/WeakReference;", "Landroidx/paging/PagedList$Callback;", "getConfig", "()Landroidx/paging/PagedList$Config;", "getCoroutineScope$paging_common", "()Lkotlinx/coroutines/CoroutineScope;", "dataSource", "Landroidx/paging/DataSource;", "getDataSource$annotations", "()V", "getDataSource", "()Landroidx/paging/DataSource;", "isDetached", "", "()Z", "isImmutable", "lastKey", "getLastKey", "()Ljava/lang/Object;", "loadStateListeners", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "", "loadedCount", "", "getLoadedCount", "()I", "getNotifyDispatcher$paging_common", "()Lkotlinx/coroutines/CoroutineDispatcher;", "getPagingSource", "()Landroidx/paging/PagingSource;", "positionOffset", "getPositionOffset", "refreshRetryCallback", "Ljava/lang/Runnable;", "getRefreshRetryCallback$paging_common", "()Ljava/lang/Runnable;", "setRefreshRetryCallback$paging_common", "(Ljava/lang/Runnable;)V", "requiredRemainder", "getRequiredRemainder$paging_common", "size", "getSize", "getStorage$paging_common", "()Landroidx/paging/PagedStorage;", "addWeakCallback", "callback", "previousSnapshot", "", "addWeakLoadStateListener", "listener", "detach", "dispatchCurrentLoadState", "dispatchStateChangeAsync", "type", "state", "dispatchStateChangeAsync$paging_common", "get", "index", "(I)Ljava/lang/Object;", "getNullPaddedList", "Landroidx/paging/NullPaddedList;", "lastLoad", "loadAround", "loadAroundInternal", "notifyChanged", "position", "count", "notifyInserted", "notifyInserted$paging_common", "notifyRemoved", "removeWeakCallback", "removeWeakLoadStateListener", "retry", "setInitialLoadState", "loadType", "loadState", "setRetryCallback", "snapshot", "BoundaryCallback", "Builder", "Callback", "Companion", "Config", "LoadStateManager", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
@Deprecated(message = "PagedList is deprecated and has been replaced by PagingData")
/* compiled from: PagedList.kt */
public abstract class PagedList<T> extends AbstractList<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<WeakReference<Callback>> callbacks = new ArrayList();
    private final C0510Config config;
    private final CoroutineScope coroutineScope;
    /* access modifiers changed from: private */
    public final List<WeakReference<Function2<LoadType, LoadState, Unit>>> loadStateListeners = new ArrayList();
    private final CoroutineDispatcher notifyDispatcher;
    private final PagingSource<?, T> pagingSource;
    private Runnable refreshRetryCallback;
    private final int requiredRemainder;
    private final PagedStorage<T> storage;

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\b'\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0007J\u0015\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\u000b"}, mo27512d2 = {"Landroidx/paging/PagedList$BoundaryCallback;", "T", "", "()V", "onItemAtEndLoaded", "", "itemAtEnd", "(Ljava/lang/Object;)V", "onItemAtFrontLoaded", "itemAtFront", "onZeroItemsLoaded", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PagedList.kt */
    public static abstract class BoundaryCallback<T> {
        public void onItemAtEndLoaded(T t) {
            Intrinsics.checkNotNullParameter(t, "itemAtEnd");
        }

        public void onItemAtFrontLoaded(T t) {
            Intrinsics.checkNotNullParameter(t, "itemAtFront");
        }

        public void onZeroItemsLoaded() {
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&¨\u0006\n"}, mo27512d2 = {"Landroidx/paging/PagedList$Callback;", "", "()V", "onChanged", "", "position", "", "count", "onInserted", "onRemoved", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PagedList.kt */
    public static abstract class Callback {
        public abstract void onChanged(int i, int i2);

        public abstract void onInserted(int i, int i2);

        public abstract void onRemoved(int i, int i2);
    }

    @JvmStatic
    public static final <K, T> PagedList<T> create(PagingSource<K, T> pagingSource2, PagingSource.LoadResult.Page<K, T> page, CoroutineScope coroutineScope2, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, BoundaryCallback<T> boundaryCallback, C0510Config config2, K k) {
        return Companion.create(pagingSource2, page, coroutineScope2, coroutineDispatcher, coroutineDispatcher2, boundaryCallback, config2, k);
    }

    @Deprecated(message = "DataSource is deprecated and has been replaced by PagingSource. PagedList offers indirect ways of controlling fetch ('loadAround()', 'retry()') so that you should not need to access the DataSource/PagingSource.")
    public static /* synthetic */ void getDataSource$annotations() {
    }

    public abstract void detach();

    public abstract void dispatchCurrentLoadState(Function2<? super LoadType, ? super LoadState, Unit> function2);

    public abstract Object getLastKey();

    public abstract boolean isDetached();

    public abstract void loadAroundInternal(int i);

    public void retry() {
    }

    public void setInitialLoadState(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
    }

    public final /* bridge */ T remove(int i) {
        return removeAt(i);
    }

    public /* bridge */ Object removeAt(int i) {
        return super.remove(i);
    }

    public final /* bridge */ int size() {
        return getSize();
    }

    public PagingSource<?, T> getPagingSource() {
        return this.pagingSource;
    }

    public final CoroutineScope getCoroutineScope$paging_common() {
        return this.coroutineScope;
    }

    public final CoroutineDispatcher getNotifyDispatcher$paging_common() {
        return this.notifyDispatcher;
    }

    public final PagedStorage<T> getStorage$paging_common() {
        return this.storage;
    }

    public final C0510Config getConfig() {
        return this.config;
    }

    public PagedList(PagingSource<?, T> pagingSource2, CoroutineScope coroutineScope2, CoroutineDispatcher coroutineDispatcher, PagedStorage<T> pagedStorage, C0510Config config2) {
        Intrinsics.checkNotNullParameter(pagingSource2, "pagingSource");
        Intrinsics.checkNotNullParameter(coroutineScope2, "coroutineScope");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(pagedStorage, "storage");
        Intrinsics.checkNotNullParameter(config2, "config");
        this.pagingSource = pagingSource2;
        this.coroutineScope = coroutineScope2;
        this.notifyDispatcher = coroutineDispatcher;
        this.storage = pagedStorage;
        this.config = config2;
        this.requiredRemainder = (config2.prefetchDistance * 2) + config2.pageSize;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0001\u0010\u0006*\u00020\u0001\"\b\b\u0002\u0010\u0005*\u00020\u00012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u00050\b2\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u0002H\u0005\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u0001H\u0006H\u0007¢\u0006\u0002\u0010\u0015J%\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0000¢\u0006\u0002\b\u001d¨\u0006\u001e"}, mo27512d2 = {"Landroidx/paging/PagedList$Companion;", "", "()V", "create", "Landroidx/paging/PagedList;", "T", "K", "pagingSource", "Landroidx/paging/PagingSource;", "initialPage", "Landroidx/paging/PagingSource$LoadResult$Page;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "notifyDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "fetchDispatcher", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "config", "Landroidx/paging/PagedList$Config;", "key", "(Landroidx/paging/PagingSource;Landroidx/paging/PagingSource$LoadResult$Page;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/PagedList$BoundaryCallback;Landroidx/paging/PagedList$Config;Ljava/lang/Object;)Landroidx/paging/PagedList;", "dispatchNaiveUpdatesSinceSnapshot", "", "currentSize", "", "snapshotSize", "callback", "Landroidx/paging/PagedList$Callback;", "dispatchNaiveUpdatesSinceSnapshot$paging_common", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PagedList.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final <K, T> PagedList<T> create(PagingSource<K, T> pagingSource, PagingSource.LoadResult.Page<K, T> page, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, BoundaryCallback<T> boundaryCallback, C0510Config config, K k) {
            PagingSource.LoadResult.Page<K, T> page2;
            PagingSource<K, T> pagingSource2 = pagingSource;
            C0510Config config2 = config;
            Intrinsics.checkNotNullParameter(pagingSource, "pagingSource");
            CoroutineScope coroutineScope2 = coroutineScope;
            Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
            CoroutineDispatcher coroutineDispatcher3 = coroutineDispatcher;
            Intrinsics.checkNotNullParameter(coroutineDispatcher, "notifyDispatcher");
            Intrinsics.checkNotNullParameter(coroutineDispatcher2, "fetchDispatcher");
            Intrinsics.checkNotNullParameter(config2, "config");
            if (page == null) {
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = new PagingSource.LoadParams.Refresh(k, config2.initialLoadSizeHint, config2.enablePlaceholders);
                page2 = (PagingSource.LoadResult.Page) BuildersKt__BuildersKt.runBlocking$default((CoroutineContext) null, new PagedList$Companion$create$resolvedInitialPage$1(pagingSource, objectRef, (Continuation) null), 1, (Object) null);
            } else {
                K k2 = k;
                page2 = page;
            }
            return new ContiguousPagedList<>(pagingSource, coroutineScope, coroutineDispatcher, coroutineDispatcher2, boundaryCallback, config, page2, k);
        }

        public final void dispatchNaiveUpdatesSinceSnapshot$paging_common(int i, int i2, Callback callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
            if (i2 < i) {
                if (i2 > 0) {
                    callback.onChanged(0, i2);
                }
                int i3 = i - i2;
                if (i3 > 0) {
                    callback.onInserted(i2, i3);
                    return;
                }
                return;
            }
            if (i > 0) {
                callback.onChanged(0, i);
            }
            int i4 = i2 - i;
            if (i4 != 0) {
                callback.onRemoved(i, i4);
            }
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u0002*\b\b\u0002\u0010\u0003*\u00020\u00022\u00020\u0002B#\b\u0016\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB#\b\u0016\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB7\b\u0016\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000f\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u0010B7\b\u0016\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\r\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u000f\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u0011J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00020\u001cJ\"\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0013J\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u001a\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0017J\u001c\u0010 \u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010!\u001a\u00020\"H\u0007J!\u0010#\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\u0010\u0018\u001a\u0004\u0018\u00018\u0001¢\u0006\u0002\u0010$J\u001a\u0010%\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0017J\u001c\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\u0006\u0010'\u001a\u00020\"H\u0007R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0013X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u0004\u0018\u00018\u0001X\u000e¢\u0006\u0004\n\u0002\u0010\u0019R\u001c\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0018\u00010\rX\u0004¢\u0006\u0002\n\u0000¨\u0006("}, mo27512d2 = {"Landroidx/paging/PagedList$Builder;", "Key", "", "Value", "dataSource", "Landroidx/paging/DataSource;", "config", "Landroidx/paging/PagedList$Config;", "(Landroidx/paging/DataSource;Landroidx/paging/PagedList$Config;)V", "pageSize", "", "(Landroidx/paging/DataSource;I)V", "pagingSource", "Landroidx/paging/PagingSource;", "initialPage", "Landroidx/paging/PagingSource$LoadResult$Page;", "(Landroidx/paging/PagingSource;Landroidx/paging/PagingSource$LoadResult$Page;Landroidx/paging/PagedList$Config;)V", "(Landroidx/paging/PagingSource;Landroidx/paging/PagingSource$LoadResult$Page;I)V", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "fetchDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "initialKey", "Ljava/lang/Object;", "notifyDispatcher", "build", "Landroidx/paging/PagedList;", "setBoundaryCallback", "setCoroutineScope", "setFetchDispatcher", "setFetchExecutor", "fetchExecutor", "Ljava/util/concurrent/Executor;", "setInitialKey", "(Ljava/lang/Object;)Landroidx/paging/PagedList$Builder;", "setNotifyDispatcher", "setNotifyExecutor", "notifyExecutor", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    @Deprecated(message = "PagedList is deprecated and has been replaced by PagingData, which no longer supports constructing snapshots of loaded data manually.", replaceWith = @ReplaceWith(expression = "Pager.flow", imports = {"androidx.paging.Pager"}))
    /* compiled from: PagedList.kt */
    public static final class Builder<Key, Value> {
        private BoundaryCallback<Value> boundaryCallback;
        private final C0510Config config;
        private CoroutineScope coroutineScope;
        private DataSource<Key, Value> dataSource;
        private CoroutineDispatcher fetchDispatcher;
        private Key initialKey;
        private final PagingSource.LoadResult.Page<Key, Value> initialPage;
        private CoroutineDispatcher notifyDispatcher;
        private final PagingSource<Key, Value> pagingSource;

        public Builder(DataSource<Key, Value> dataSource2, C0510Config config2) {
            Intrinsics.checkNotNullParameter(dataSource2, "dataSource");
            Intrinsics.checkNotNullParameter(config2, "config");
            this.coroutineScope = GlobalScope.INSTANCE;
            this.pagingSource = null;
            this.dataSource = dataSource2;
            this.initialPage = null;
            this.config = config2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder(DataSource<Key, Value> dataSource2, int i) {
            this(dataSource2, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, (Object) null));
            Intrinsics.checkNotNullParameter(dataSource2, "dataSource");
        }

        public Builder(PagingSource<Key, Value> pagingSource2, PagingSource.LoadResult.Page<Key, Value> page, C0510Config config2) {
            Intrinsics.checkNotNullParameter(pagingSource2, "pagingSource");
            Intrinsics.checkNotNullParameter(page, "initialPage");
            Intrinsics.checkNotNullParameter(config2, "config");
            this.coroutineScope = GlobalScope.INSTANCE;
            this.pagingSource = pagingSource2;
            this.dataSource = null;
            this.initialPage = page;
            this.config = config2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public Builder(PagingSource<Key, Value> pagingSource2, PagingSource.LoadResult.Page<Key, Value> page, int i) {
            this(pagingSource2, page, PagedListConfigKt.Config$default(i, 0, false, 0, 0, 30, (Object) null));
            Intrinsics.checkNotNullParameter(pagingSource2, "pagingSource");
            Intrinsics.checkNotNullParameter(page, "initialPage");
        }

        public final Builder<Key, Value> setCoroutineScope(CoroutineScope coroutineScope2) {
            Intrinsics.checkNotNullParameter(coroutineScope2, "coroutineScope");
            Builder<Key, Value> builder = this;
            builder.coroutineScope = coroutineScope2;
            return builder;
        }

        @Deprecated(message = "Passing an executor will cause it get wrapped as a CoroutineDispatcher, consider passing a CoroutineDispatcher directly", replaceWith = @ReplaceWith(expression = "setNotifyDispatcher(fetchExecutor.asCoroutineDispatcher())", imports = {"kotlinx.coroutines.asCoroutineDispatcher"}))
        public final Builder<Key, Value> setNotifyExecutor(Executor executor) {
            Intrinsics.checkNotNullParameter(executor, "notifyExecutor");
            Builder<Key, Value> builder = this;
            builder.notifyDispatcher = ExecutorsKt.from(executor);
            return builder;
        }

        public final Builder<Key, Value> setNotifyDispatcher(CoroutineDispatcher coroutineDispatcher) {
            Intrinsics.checkNotNullParameter(coroutineDispatcher, "notifyDispatcher");
            Builder<Key, Value> builder = this;
            builder.notifyDispatcher = coroutineDispatcher;
            return builder;
        }

        @Deprecated(message = "Passing an executor will cause it get wrapped as a CoroutineDispatcher, consider passing a CoroutineDispatcher directly", replaceWith = @ReplaceWith(expression = "setFetchDispatcher(fetchExecutor.asCoroutineDispatcher())", imports = {"kotlinx.coroutines.asCoroutineDispatcher"}))
        public final Builder<Key, Value> setFetchExecutor(Executor executor) {
            Intrinsics.checkNotNullParameter(executor, "fetchExecutor");
            Builder<Key, Value> builder = this;
            builder.fetchDispatcher = ExecutorsKt.from(executor);
            return builder;
        }

        public final Builder<Key, Value> setFetchDispatcher(CoroutineDispatcher coroutineDispatcher) {
            Intrinsics.checkNotNullParameter(coroutineDispatcher, "fetchDispatcher");
            Builder<Key, Value> builder = this;
            builder.fetchDispatcher = coroutineDispatcher;
            return builder;
        }

        public final Builder<Key, Value> setBoundaryCallback(BoundaryCallback<Value> boundaryCallback2) {
            Builder<Key, Value> builder = this;
            builder.boundaryCallback = boundaryCallback2;
            return builder;
        }

        public final Builder<Key, Value> setInitialKey(Key key) {
            Builder<Key, Value> builder = this;
            builder.initialKey = key;
            return builder;
        }

        public final PagedList<Value> build() {
            PagingSource<Key, Value> pagingSource2;
            LegacyPagingSource legacyPagingSource;
            CoroutineDispatcher coroutineDispatcher = this.fetchDispatcher;
            if (coroutineDispatcher == null) {
                coroutineDispatcher = Dispatchers.getIO();
            }
            CoroutineDispatcher coroutineDispatcher2 = coroutineDispatcher;
            PagingSource<Key, Value> pagingSource3 = this.pagingSource;
            if (pagingSource3 != null) {
                pagingSource2 = pagingSource3;
            } else {
                DataSource<Key, Value> dataSource2 = this.dataSource;
                if (dataSource2 != null) {
                    legacyPagingSource = new LegacyPagingSource(coroutineDispatcher2, dataSource2);
                    legacyPagingSource.setPageSize(this.config.pageSize);
                } else {
                    legacyPagingSource = null;
                }
                pagingSource2 = legacyPagingSource;
            }
            if (pagingSource2 != null) {
                Companion companion = PagedList.Companion;
                PagingSource.LoadResult.Page<Key, Value> page = this.initialPage;
                CoroutineScope coroutineScope2 = this.coroutineScope;
                CoroutineDispatcher coroutineDispatcher3 = this.notifyDispatcher;
                if (coroutineDispatcher3 == null) {
                    coroutineDispatcher3 = Dispatchers.getMain().getImmediate();
                }
                return companion.create(pagingSource2, page, coroutineScope2, coroutineDispatcher3, coroutineDispatcher2, this.boundaryCallback, this.config, this.initialKey);
            }
            throw new IllegalStateException("PagedList cannot be built without a PagingSource or DataSource".toString());
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u000b2\u00020\u0001:\u0002\n\u000bB/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tR\u0010\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, mo27512d2 = {"Landroidx/paging/PagedList$Config;", "", "pageSize", "", "prefetchDistance", "enablePlaceholders", "", "initialLoadSizeHint", "maxSize", "(IIZII)V", "Builder", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* renamed from: androidx.paging.PagedList$Config */
    /* compiled from: PagedList.kt */
    public static final class C0510Config {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public static final int MAX_SIZE_UNBOUNDED = Integer.MAX_VALUE;
        public final boolean enablePlaceholders;
        public final int initialLoadSizeHint;
        public final int maxSize;
        public final int pageSize;
        public final int prefetchDistance;

        public C0510Config(int i, int i2, boolean z, int i3, int i4) {
            this.pageSize = i;
            this.prefetchDistance = i2;
            this.enablePlaceholders = z;
            this.initialLoadSizeHint = i3;
            this.maxSize = i4;
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0004J\u0010\u0010\r\u001a\u00020\u00002\b\b\u0001\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u00020\u00002\b\b\u0001\u0010\u0007\u001a\u00020\u0006J\u0010\u0010\u000f\u001a\u00020\u00002\b\b\u0001\u0010\b\u001a\u00020\u0006J\u0010\u0010\u0010\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo27512d2 = {"Landroidx/paging/PagedList$Config$Builder;", "", "()V", "enablePlaceholders", "", "initialLoadSizeHint", "", "maxSize", "pageSize", "prefetchDistance", "build", "Landroidx/paging/PagedList$Config;", "setEnablePlaceholders", "setInitialLoadSizeHint", "setMaxSize", "setPageSize", "setPrefetchDistance", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* renamed from: androidx.paging.PagedList$Config$Builder */
        /* compiled from: PagedList.kt */
        public static final class Builder {
            public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
            public static final int DEFAULT_INITIAL_PAGE_MULTIPLIER = 3;
            private boolean enablePlaceholders = true;
            private int initialLoadSizeHint = -1;
            private int maxSize = Integer.MAX_VALUE;
            private int pageSize = -1;
            private int prefetchDistance = -1;

            public final Builder setPageSize(int i) {
                Builder builder = this;
                if (i >= 1) {
                    builder.pageSize = i;
                    return builder;
                }
                throw new IllegalArgumentException("Page size must be a positive number");
            }

            public final Builder setPrefetchDistance(int i) {
                Builder builder = this;
                builder.prefetchDistance = i;
                return builder;
            }

            public final Builder setEnablePlaceholders(boolean z) {
                Builder builder = this;
                builder.enablePlaceholders = z;
                return builder;
            }

            public final Builder setInitialLoadSizeHint(int i) {
                Builder builder = this;
                builder.initialLoadSizeHint = i;
                return builder;
            }

            public final Builder setMaxSize(int i) {
                Builder builder = this;
                builder.maxSize = i;
                return builder;
            }

            public final C0510Config build() {
                if (this.prefetchDistance < 0) {
                    this.prefetchDistance = this.pageSize;
                }
                if (this.initialLoadSizeHint < 0) {
                    this.initialLoadSizeHint = this.pageSize * 3;
                }
                if (this.enablePlaceholders || this.prefetchDistance != 0) {
                    int i = this.maxSize;
                    if (i == Integer.MAX_VALUE || i >= this.pageSize + (this.prefetchDistance * 2)) {
                        return new C0510Config(this.pageSize, this.prefetchDistance, this.enablePlaceholders, this.initialLoadSizeHint, this.maxSize);
                    }
                    throw new IllegalArgumentException("Maximum size must be at least pageSize + 2*prefetchDist" + ", pageSize=" + this.pageSize + ", prefetchDist=" + this.prefetchDistance + ", maxSize=" + this.maxSize);
                }
                throw new IllegalArgumentException("Placeholders and prefetch are the only ways to trigger loading of more data in the PagedList, so either placeholders must be enabled, or prefetch distance must be > 0.");
            }

            @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo27512d2 = {"Landroidx/paging/PagedList$Config$Builder$Companion;", "", "()V", "DEFAULT_INITIAL_PAGE_MULTIPLIER", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
            /* renamed from: androidx.paging.PagedList$Config$Builder$Companion */
            /* compiled from: PagedList.kt */
            public static final class Companion {
                private Companion() {
                }

                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002¨\u0006\u0006"}, mo27512d2 = {"Landroidx/paging/PagedList$Config$Companion;", "", "()V", "MAX_SIZE_UNBOUNDED", "", "getMAX_SIZE_UNBOUNDED$annotations", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* renamed from: androidx.paging.PagedList$Config$Companion */
        /* compiled from: PagedList.kt */
        public static final class Companion {
            public static /* synthetic */ void getMAX_SIZE_UNBOUNDED$annotations() {
            }

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u000f\u001a\u00020\u00102\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u0012J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0004H'J\u0016\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u0018"}, mo27512d2 = {"Landroidx/paging/PagedList$LoadStateManager;", "", "()V", "endState", "Landroidx/paging/LoadState;", "getEndState", "()Landroidx/paging/LoadState;", "setEndState", "(Landroidx/paging/LoadState;)V", "refreshState", "getRefreshState", "setRefreshState", "startState", "getStartState", "setStartState", "dispatchCurrentLoadState", "", "callback", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "onStateChanged", "type", "state", "setState", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PagedList.kt */
    public static abstract class LoadStateManager {
        private LoadState endState = LoadState.NotLoading.Companion.getIncomplete$paging_common();
        private LoadState refreshState = LoadState.NotLoading.Companion.getIncomplete$paging_common();
        private LoadState startState = LoadState.NotLoading.Companion.getIncomplete$paging_common();

        @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[LoadType.values().length];
                $EnumSwitchMapping$0 = iArr;
                iArr[LoadType.REFRESH.ordinal()] = 1;
                iArr[LoadType.PREPEND.ordinal()] = 2;
                iArr[LoadType.APPEND.ordinal()] = 3;
            }
        }

        public abstract void onStateChanged(LoadType loadType, LoadState loadState);

        public final LoadState getRefreshState() {
            return this.refreshState;
        }

        public final void setRefreshState(LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadState, "<set-?>");
            this.refreshState = loadState;
        }

        public final LoadState getStartState() {
            return this.startState;
        }

        public final void setStartState(LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadState, "<set-?>");
            this.startState = loadState;
        }

        public final LoadState getEndState() {
            return this.endState;
        }

        public final void setEndState(LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadState, "<set-?>");
            this.endState = loadState;
        }

        public final void setState(LoadType loadType, LoadState loadState) {
            Intrinsics.checkNotNullParameter(loadType, "type");
            Intrinsics.checkNotNullParameter(loadState, "state");
            int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (!Intrinsics.areEqual((Object) this.endState, (Object) loadState)) {
                            this.endState = loadState;
                        } else {
                            return;
                        }
                    }
                } else if (!Intrinsics.areEqual((Object) this.startState, (Object) loadState)) {
                    this.startState = loadState;
                } else {
                    return;
                }
            } else if (!Intrinsics.areEqual((Object) this.refreshState, (Object) loadState)) {
                this.refreshState = loadState;
            } else {
                return;
            }
            onStateChanged(loadType, loadState);
        }

        public final void dispatchCurrentLoadState(Function2<? super LoadType, ? super LoadState, Unit> function2) {
            Intrinsics.checkNotNullParameter(function2, "callback");
            function2.invoke(LoadType.REFRESH, this.refreshState);
            function2.invoke(LoadType.PREPEND, this.startState);
            function2.invoke(LoadType.APPEND, this.endState);
        }
    }

    public final NullPaddedList<T> getNullPaddedList() {
        return this.storage;
    }

    public final Runnable getRefreshRetryCallback$paging_common() {
        return this.refreshRetryCallback;
    }

    public final void setRefreshRetryCallback$paging_common(Runnable runnable) {
        this.refreshRetryCallback = runnable;
    }

    public final int lastLoad() {
        return this.storage.getLastLoadAroundIndex();
    }

    public final int getRequiredRemainder$paging_common() {
        return this.requiredRemainder;
    }

    public int getSize() {
        return this.storage.size();
    }

    public final DataSource<?, T> getDataSource() {
        PagingSource pagingSource2 = getPagingSource();
        if (pagingSource2 instanceof LegacyPagingSource) {
            DataSource<?, T> dataSource$paging_common = ((LegacyPagingSource) pagingSource2).getDataSource$paging_common();
            Objects.requireNonNull(dataSource$paging_common, "null cannot be cast to non-null type androidx.paging.DataSource<*, T>");
            return dataSource$paging_common;
        }
        throw new IllegalStateException("Attempt to access dataSource on a PagedList that was instantiated with a " + pagingSource2.getClass().getSimpleName() + " instead of a DataSource");
    }

    public final int getLoadedCount() {
        return this.storage.getStorageCount();
    }

    public boolean isImmutable() {
        return isDetached();
    }

    public final int getPositionOffset() {
        return this.storage.getPositionOffset();
    }

    public final void setRetryCallback(Runnable runnable) {
        this.refreshRetryCallback = runnable;
    }

    public final void dispatchStateChangeAsync$paging_common(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "type");
        Intrinsics.checkNotNullParameter(loadState, "state");
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.notifyDispatcher, (CoroutineStart) null, new PagedList$dispatchStateChangeAsync$1(this, loadType, loadState, (Continuation) null), 2, (Object) null);
    }

    public T get(int i) {
        return this.storage.get(i);
    }

    public final void loadAround(int i) {
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size());
        }
        this.storage.setLastLoadAroundIndex(i);
        loadAroundInternal(i);
    }

    public final List<T> snapshot() {
        if (isImmutable()) {
            return this;
        }
        return new SnapshotPagedList<>(this);
    }

    public final void addWeakLoadStateListener(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "listener");
        CollectionsKt.removeAll(this.loadStateListeners, PagedList$addWeakLoadStateListener$1.INSTANCE);
        this.loadStateListeners.add(new WeakReference(function2));
        dispatchCurrentLoadState(function2);
    }

    public final void removeWeakLoadStateListener(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "listener");
        CollectionsKt.removeAll(this.loadStateListeners, new PagedList$removeWeakLoadStateListener$1(function2));
    }

    @Deprecated(message = "Dispatching a diff since snapshot created is behavior that can be instead tracked by attaching a Callback to the PagedList that is mutating, and tracking changes since calling PagedList.snapshot().")
    public final void addWeakCallback(List<? extends T> list, Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (!(list == null || list == this)) {
            Companion.dispatchNaiveUpdatesSinceSnapshot$paging_common(size(), list.size(), callback);
        }
        addWeakCallback(callback);
    }

    public final void addWeakCallback(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        CollectionsKt.removeAll(this.callbacks, PagedList$addWeakCallback$1.INSTANCE);
        this.callbacks.add(new WeakReference(callback));
    }

    public final void removeWeakCallback(Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        CollectionsKt.removeAll(this.callbacks, new PagedList$removeWeakCallback$1(callback));
    }

    public final void notifyInserted$paging_common(int i, int i2) {
        if (i2 != 0) {
            for (WeakReference weakReference : CollectionsKt.reversed(this.callbacks)) {
                Callback callback = (Callback) weakReference.get();
                if (callback != null) {
                    callback.onInserted(i, i2);
                }
            }
        }
    }

    public final void notifyChanged(int i, int i2) {
        if (i2 != 0) {
            for (WeakReference weakReference : CollectionsKt.reversed(this.callbacks)) {
                Callback callback = (Callback) weakReference.get();
                if (callback != null) {
                    callback.onChanged(i, i2);
                }
            }
        }
    }

    public final void notifyRemoved(int i, int i2) {
        if (i2 != 0) {
            for (WeakReference weakReference : CollectionsKt.reversed(this.callbacks)) {
                Callback callback = (Callback) weakReference.get();
                if (callback != null) {
                    callback.onRemoved(i, i2);
                }
            }
        }
    }
}
