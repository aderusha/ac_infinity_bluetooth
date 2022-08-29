package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PagedList;
import androidx.paging.PagingSource;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u000278BU\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010¢\u0006\u0002\u0010\u0011J\u0006\u0010%\u001a\u00020&J\u0018\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J$\u0010,\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010.H\u0002J\u0006\u0010/\u001a\u00020&J\b\u00100\u001a\u00020&H\u0002J\u001e\u00101\u001a\u00020&2\u0006\u0010(\u001a\u00020)2\f\u00102\u001a\b\u0012\u0004\u0012\u00028\u000003H\u0002J\b\u00104\u001a\u00020&H\u0002J\u0006\u00105\u001a\u00020&J\u0006\u00106\u001a\u00020&R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0004¢\u0006\u0002\n\u0000R \u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$¨\u00069"}, mo27512d2 = {"Landroidx/paging/LegacyPageFetcher;", "K", "", "V", "pagedListScope", "Lkotlinx/coroutines/CoroutineScope;", "config", "Landroidx/paging/PagedList$Config;", "source", "Landroidx/paging/PagingSource;", "notifyDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "fetchDispatcher", "pageConsumer", "Landroidx/paging/LegacyPageFetcher$PageConsumer;", "keyProvider", "Landroidx/paging/LegacyPageFetcher$KeyProvider;", "(Lkotlinx/coroutines/CoroutineScope;Landroidx/paging/PagedList$Config;Landroidx/paging/PagingSource;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/LegacyPageFetcher$PageConsumer;Landroidx/paging/LegacyPageFetcher$KeyProvider;)V", "getConfig", "()Landroidx/paging/PagedList$Config;", "detached", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isDetached", "", "()Z", "loadStateManager", "Landroidx/paging/PagedList$LoadStateManager;", "getLoadStateManager$annotations", "()V", "getLoadStateManager", "()Landroidx/paging/PagedList$LoadStateManager;", "setLoadStateManager", "(Landroidx/paging/PagedList$LoadStateManager;)V", "getPageConsumer", "()Landroidx/paging/LegacyPageFetcher$PageConsumer;", "getSource", "()Landroidx/paging/PagingSource;", "detach", "", "onLoadError", "type", "Landroidx/paging/LoadType;", "throwable", "", "onLoadSuccess", "value", "Landroidx/paging/PagingSource$LoadResult$Page;", "retry", "scheduleAppend", "scheduleLoad", "params", "Landroidx/paging/PagingSource$LoadParams;", "schedulePrepend", "tryScheduleAppend", "trySchedulePrepend", "KeyProvider", "PageConsumer", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: LegacyPageFetcher.kt */
public final class LegacyPageFetcher<K, V> {
    private final PagedList.C0510Config config;
    private final AtomicBoolean detached = new AtomicBoolean(false);
    private final CoroutineDispatcher fetchDispatcher;
    private final KeyProvider<K> keyProvider;
    private PagedList.LoadStateManager loadStateManager = new LegacyPageFetcher$loadStateManager$1(this);
    /* access modifiers changed from: private */
    public final CoroutineDispatcher notifyDispatcher;
    private final PageConsumer<V> pageConsumer;
    private final CoroutineScope pagedListScope;
    private final PagingSource<K, V> source;

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\b`\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002R\u0014\u0010\u0003\u001a\u0004\u0018\u00018\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00018\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005¨\u0006\b"}, mo27512d2 = {"Landroidx/paging/LegacyPageFetcher$KeyProvider;", "K", "", "nextKey", "getNextKey", "()Ljava/lang/Object;", "prevKey", "getPrevKey", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: LegacyPageFetcher.kt */
    public interface KeyProvider<K> {
        K getNextKey();

        K getPrevKey();
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0010\u0010\u0007\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00020\bH&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/LegacyPageFetcher$PageConsumer;", "V", "", "onPageResult", "", "type", "Landroidx/paging/LoadType;", "page", "Landroidx/paging/PagingSource$LoadResult$Page;", "onStateChanged", "", "state", "Landroidx/paging/LoadState;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: LegacyPageFetcher.kt */
    public interface PageConsumer<V> {
        boolean onPageResult(LoadType loadType, PagingSource.LoadResult.Page<?, V> page);

        void onStateChanged(LoadType loadType, LoadState loadState);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LoadType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LoadType.PREPEND.ordinal()] = 1;
            iArr[LoadType.APPEND.ordinal()] = 2;
        }
    }

    public static /* synthetic */ void getLoadStateManager$annotations() {
    }

    public LegacyPageFetcher(CoroutineScope coroutineScope, PagedList.C0510Config config2, PagingSource<K, V> pagingSource, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, PageConsumer<V> pageConsumer2, KeyProvider<K> keyProvider2) {
        Intrinsics.checkNotNullParameter(coroutineScope, "pagedListScope");
        Intrinsics.checkNotNullParameter(config2, "config");
        Intrinsics.checkNotNullParameter(pagingSource, "source");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(coroutineDispatcher2, "fetchDispatcher");
        Intrinsics.checkNotNullParameter(pageConsumer2, "pageConsumer");
        Intrinsics.checkNotNullParameter(keyProvider2, "keyProvider");
        this.pagedListScope = coroutineScope;
        this.config = config2;
        this.source = pagingSource;
        this.notifyDispatcher = coroutineDispatcher;
        this.fetchDispatcher = coroutineDispatcher2;
        this.pageConsumer = pageConsumer2;
        this.keyProvider = keyProvider2;
    }

    public final PagedList.C0510Config getConfig() {
        return this.config;
    }

    public final PagingSource<K, V> getSource() {
        return this.source;
    }

    public final PageConsumer<V> getPageConsumer() {
        return this.pageConsumer;
    }

    public final PagedList.LoadStateManager getLoadStateManager() {
        return this.loadStateManager;
    }

    public final void setLoadStateManager(PagedList.LoadStateManager loadStateManager2) {
        Intrinsics.checkNotNullParameter(loadStateManager2, "<set-?>");
        this.loadStateManager = loadStateManager2;
    }

    public final boolean isDetached() {
        return this.detached.get();
    }

    private final void scheduleLoad(LoadType loadType, PagingSource.LoadParams<K> loadParams) {
        Job unused = BuildersKt__Builders_commonKt.launch$default(this.pagedListScope, this.fetchDispatcher, (CoroutineStart) null, new LegacyPageFetcher$scheduleLoad$1(this, loadParams, loadType, (Continuation) null), 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void onLoadSuccess(LoadType loadType, PagingSource.LoadResult.Page<K, V> page) {
        if (!isDetached()) {
            if (this.pageConsumer.onPageResult(loadType, page)) {
                int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
                if (i == 1) {
                    schedulePrepend();
                } else if (i == 2) {
                    scheduleAppend();
                } else {
                    throw new IllegalStateException("Can only fetch more during append/prepend");
                }
            } else {
                this.loadStateManager.setState(loadType, page.getData().isEmpty() ? LoadState.NotLoading.Companion.getComplete$paging_common() : LoadState.NotLoading.Companion.getIncomplete$paging_common());
            }
        }
    }

    /* access modifiers changed from: private */
    public final void onLoadError(LoadType loadType, Throwable th) {
        if (!isDetached()) {
            this.loadStateManager.setState(loadType, new LoadState.Error(th));
        }
    }

    public final void trySchedulePrepend() {
        LoadState startState = this.loadStateManager.getStartState();
        if ((startState instanceof LoadState.NotLoading) && !startState.getEndOfPaginationReached()) {
            schedulePrepend();
        }
    }

    public final void tryScheduleAppend() {
        LoadState endState = this.loadStateManager.getEndState();
        if ((endState instanceof LoadState.NotLoading) && !endState.getEndOfPaginationReached()) {
            scheduleAppend();
        }
    }

    private final void schedulePrepend() {
        K prevKey = this.keyProvider.getPrevKey();
        if (prevKey == null) {
            onLoadSuccess(LoadType.PREPEND, PagingSource.LoadResult.Page.Companion.empty$paging_common());
            return;
        }
        this.loadStateManager.setState(LoadType.PREPEND, LoadState.Loading.INSTANCE);
        scheduleLoad(LoadType.PREPEND, new PagingSource.LoadParams.Prepend(prevKey, this.config.pageSize, this.config.enablePlaceholders));
    }

    private final void scheduleAppend() {
        K nextKey = this.keyProvider.getNextKey();
        if (nextKey == null) {
            onLoadSuccess(LoadType.APPEND, PagingSource.LoadResult.Page.Companion.empty$paging_common());
            return;
        }
        this.loadStateManager.setState(LoadType.APPEND, LoadState.Loading.INSTANCE);
        scheduleLoad(LoadType.APPEND, new PagingSource.LoadParams.Append(nextKey, this.config.pageSize, this.config.enablePlaceholders));
    }

    public final void retry() {
        if (this.loadStateManager.getStartState() instanceof LoadState.Error) {
            schedulePrepend();
        }
        if (this.loadStateManager.getEndState() instanceof LoadState.Error) {
            scheduleAppend();
        }
    }

    public final void detach() {
        this.detached.set(true);
    }
}
