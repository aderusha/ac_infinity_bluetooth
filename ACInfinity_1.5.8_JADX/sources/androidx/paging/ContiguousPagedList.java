package androidx.paging;

import androidx.paging.LegacyPageFetcher;
import androidx.paging.LoadState;
import androidx.paging.PagedList;
import androidx.paging.PagedStorage;
import androidx.paging.PagingSource;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010 \n\u0002\b\u0004\b\u0017\u0018\u0000 X*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00030\u00042\u00020\u00052\b\u0012\u0004\u0012\u0002H\u00030\u0006:\u0001XBg\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u000e\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0013\u0012\b\u0010\u0014\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\u0015J%\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\u001b2\u0006\u00103\u001a\u00020\u001bH\u0001¢\u0006\u0002\b4J\b\u00105\u001a\u000200H\u0016J\u0018\u00106\u001a\u0002002\u0006\u00107\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u001bH\u0002J\"\u00109\u001a\u0002002\u0018\u0010:\u001a\u0014\u0012\u0004\u0012\u00020<\u0012\u0004\u0012\u00020=\u0012\u0004\u0012\u0002000;H\u0016J\u0010\u0010>\u001a\u0002002\u0006\u0010?\u001a\u00020\u0017H\u0017J\u0010\u0010@\u001a\u0002002\u0006\u0010A\u001a\u00020\u0017H\u0017J \u0010B\u001a\u0002002\u0006\u0010C\u001a\u00020\u00172\u0006\u0010D\u001a\u00020\u00172\u0006\u0010E\u001a\u00020\u0017H\u0017J \u0010F\u001a\u0002002\u0006\u0010G\u001a\u00020\u00172\u0006\u0010D\u001a\u00020\u00172\u0006\u0010E\u001a\u00020\u0017H\u0017J\"\u0010H\u001a\u00020\u001b2\u0006\u0010I\u001a\u00020<2\u0010\u0010J\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00010\u0013H\u0016J\u0018\u0010K\u001a\u0002002\u0006\u0010L\u001a\u00020\u00172\u0006\u0010A\u001a\u00020\u0017H\u0016J\u0018\u0010M\u001a\u0002002\u0006\u0010L\u001a\u00020\u00172\u0006\u0010A\u001a\u00020\u0017H\u0016J\u0018\u0010N\u001a\u0002002\u0006\u0010I\u001a\u00020<2\u0006\u0010O\u001a\u00020=H\u0016J\b\u0010P\u001a\u000200H\u0016J\u0018\u0010Q\u001a\u0002002\u0006\u0010R\u001a\u00020<2\u0006\u0010S\u001a\u00020=H\u0016J\u001e\u0010T\u001a\u0002002\u0006\u0010I\u001a\u00020<2\f\u0010J\u001a\b\u0012\u0004\u0012\u00028\u00010UH\u0002J\u0010\u0010V\u001a\u0002002\u0006\u0010W\u001a\u00020\u001bH\u0002R\u000e\u0010\u0016\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000fX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00018\u0000X\u0004¢\u0006\u0004\n\u0002\u0010\u001eR\u0014\u0010\u001f\u001a\u00020\u001b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001c\u0010!\u001a\u0004\u0018\u00018\u00008VX\u0004¢\u0006\f\u0012\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R \u0010'\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010(X\u0004¢\u0006\b\n\u0000\u0012\u0004\b)\u0010#R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000¨\u0006Y"}, mo27512d2 = {"Landroidx/paging/ContiguousPagedList;", "K", "", "V", "Landroidx/paging/PagedList;", "Landroidx/paging/PagedStorage$Callback;", "Landroidx/paging/LegacyPageFetcher$PageConsumer;", "pagingSource", "Landroidx/paging/PagingSource;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "notifyDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "backgroundDispatcher", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "config", "Landroidx/paging/PagedList$Config;", "initialPage", "Landroidx/paging/PagingSource$LoadResult$Page;", "initialLastKey", "(Landroidx/paging/PagingSource;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/PagedList$BoundaryCallback;Landroidx/paging/PagedList$Config;Landroidx/paging/PagingSource$LoadResult$Page;Ljava/lang/Object;)V", "appendItemsRequested", "", "getBoundaryCallback$paging_common", "()Landroidx/paging/PagedList$BoundaryCallback;", "boundaryCallbackBeginDeferred", "", "boundaryCallbackEndDeferred", "highestIndexAccessed", "Ljava/lang/Object;", "isDetached", "()Z", "lastKey", "getLastKey$annotations", "()V", "getLastKey", "()Ljava/lang/Object;", "lowestIndexAccessed", "pager", "Landroidx/paging/LegacyPageFetcher;", "getPager$annotations", "getPagingSource", "()Landroidx/paging/PagingSource;", "prependItemsRequested", "replacePagesWithNulls", "shouldTrim", "deferBoundaryCallbacks", "", "deferEmpty", "deferBegin", "deferEnd", "deferBoundaryCallbacks$paging_common", "detach", "dispatchBoundaryCallbacks", "begin", "end", "dispatchCurrentLoadState", "callback", "Lkotlin/Function2;", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "loadAroundInternal", "index", "onInitialized", "count", "onPageAppended", "endPosition", "changed", "added", "onPagePrepended", "leadingNulls", "onPageResult", "type", "page", "onPagesRemoved", "startOfDrops", "onPagesSwappedToPlaceholder", "onStateChanged", "state", "retry", "setInitialLoadState", "loadType", "loadState", "triggerBoundaryCallback", "", "tryDispatchBoundaryCallbacks", "post", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: ContiguousPagedList.kt */
public class ContiguousPagedList<K, V> extends PagedList<V> implements PagedStorage.Callback, LegacyPageFetcher.PageConsumer<V> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private int appendItemsRequested;
    private final PagedList.BoundaryCallback<V> boundaryCallback;
    /* access modifiers changed from: private */
    public boolean boundaryCallbackBeginDeferred;
    /* access modifiers changed from: private */
    public boolean boundaryCallbackEndDeferred;
    private int highestIndexAccessed = Integer.MIN_VALUE;
    private final K initialLastKey;
    private int lowestIndexAccessed = Integer.MAX_VALUE;
    private final LegacyPageFetcher<K, V> pager;
    private final PagingSource<K, V> pagingSource;
    private int prependItemsRequested;
    private boolean replacePagesWithNulls;
    private final boolean shouldTrim;

    public static /* synthetic */ void getLastKey$annotations() {
    }

    private static /* synthetic */ void getPager$annotations() {
    }

    public final PagingSource<K, V> getPagingSource() {
        return this.pagingSource;
    }

    public final PagedList.BoundaryCallback<V> getBoundaryCallback$paging_common() {
        return this.boundaryCallback;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContiguousPagedList(PagingSource<K, V> pagingSource2, CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, PagedList.BoundaryCallback<V> boundaryCallback2, PagedList.C0510Config config, PagingSource.LoadResult.Page<K, V> page, K k) {
        super(pagingSource2, coroutineScope, coroutineDispatcher, new PagedStorage(), config);
        PagingSource<K, V> pagingSource3 = pagingSource2;
        PagedList.C0510Config config2 = config;
        Intrinsics.checkNotNullParameter(pagingSource3, "pagingSource");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(coroutineDispatcher2, "backgroundDispatcher");
        Intrinsics.checkNotNullParameter(config2, "config");
        Intrinsics.checkNotNullParameter(page, "initialPage");
        this.pagingSource = pagingSource3;
        this.boundaryCallback = boundaryCallback2;
        this.initialLastKey = k;
        this.shouldTrim = config2.maxSize != Integer.MAX_VALUE;
        PagedStorage storage$paging_common = getStorage$paging_common();
        Objects.requireNonNull(storage$paging_common, "null cannot be cast to non-null type androidx.paging.LegacyPageFetcher.KeyProvider<K>");
        this.pager = new LegacyPageFetcher(coroutineScope, config, pagingSource2, coroutineDispatcher, coroutineDispatcher2, this, storage$paging_common);
        if (config2.enablePlaceholders) {
            getStorage$paging_common().init(page.getItemsBefore() != Integer.MIN_VALUE ? page.getItemsBefore() : 0, page, page.getItemsAfter() != Integer.MIN_VALUE ? page.getItemsAfter() : 0, 0, this, (page.getItemsBefore() == Integer.MIN_VALUE || page.getItemsAfter() == Integer.MIN_VALUE) ? false : true);
        } else {
            getStorage$paging_common().init(0, page, 0, page.getItemsBefore() != Integer.MIN_VALUE ? page.getItemsBefore() : 0, this, false);
        }
        triggerBoundaryCallback(LoadType.REFRESH, page.getData());
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\bJ%\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0000¢\u0006\u0002\b\u000b¨\u0006\f"}, mo27512d2 = {"Landroidx/paging/ContiguousPagedList$Companion;", "", "()V", "getAppendItemsRequested", "", "prefetchDistance", "index", "itemsBeforeTrailingNulls", "getAppendItemsRequested$paging_common", "getPrependItemsRequested", "leadingNulls", "getPrependItemsRequested$paging_common", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: ContiguousPagedList.kt */
    public static final class Companion {
        public final int getAppendItemsRequested$paging_common(int i, int i2, int i3) {
            return ((i2 + i) + 1) - i3;
        }

        public final int getPrependItemsRequested$paging_common(int i, int i2, int i3) {
            return i - (i2 - i3);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000e, code lost:
        r0 = r2.pagingSource.getRefreshKey(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public K getLastKey() {
        /*
            r2 = this;
            androidx.paging.PagedStorage r0 = r2.getStorage$paging_common()
            androidx.paging.PagedList$Config r1 = r2.getConfig()
            androidx.paging.PagingState r0 = r0.getRefreshKeyInfo(r1)
            if (r0 == 0) goto L_0x0017
            androidx.paging.PagingSource<K, V> r1 = r2.pagingSource
            java.lang.Object r0 = r1.getRefreshKey(r0)
            if (r0 == 0) goto L_0x0017
            goto L_0x0019
        L_0x0017:
            K r0 = r2.initialLastKey
        L_0x0019:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.ContiguousPagedList.getLastKey():java.lang.Object");
    }

    public boolean isDetached() {
        return this.pager.isDetached();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        if ((!r0.isEmpty()) != false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0094, code lost:
        if ((!r0.isEmpty()) != false) goto L_0x006a;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPageResult(androidx.paging.LoadType r9, androidx.paging.PagingSource.LoadResult.Page<?, V> r10) {
        /*
            r8 = this;
            java.lang.String r0 = "type"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "page"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.util.List r0 = r10.getData()
            int r1 = r8.lastLoad()
            androidx.paging.PagedStorage r2 = r8.getStorage$paging_common()
            int r2 = r2.getMiddleOfLoadedRange()
            r3 = 0
            r4 = 1
            if (r1 <= r2) goto L_0x0020
            r1 = 1
            goto L_0x0021
        L_0x0020:
            r1 = 0
        L_0x0021:
            boolean r2 = r8.shouldTrim
            if (r2 == 0) goto L_0x003f
            androidx.paging.PagedStorage r2 = r8.getStorage$paging_common()
            androidx.paging.PagedList$Config r5 = r8.getConfig()
            int r5 = r5.maxSize
            int r6 = r8.getRequiredRemainder$paging_common()
            int r7 = r0.size()
            boolean r2 = r2.shouldPreTrimNewPage(r5, r6, r7)
            if (r2 == 0) goto L_0x003f
            r2 = 1
            goto L_0x0040
        L_0x003f:
            r2 = 0
        L_0x0040:
            androidx.paging.LoadType r5 = androidx.paging.LoadType.APPEND
            if (r9 != r5) goto L_0x006c
            if (r2 == 0) goto L_0x004b
            if (r1 != 0) goto L_0x004b
            r8.appendItemsRequested = r3
            goto L_0x0097
        L_0x004b:
            androidx.paging.PagedStorage r2 = r8.getStorage$paging_common()
            r5 = r8
            androidx.paging.PagedStorage$Callback r5 = (androidx.paging.PagedStorage.Callback) r5
            r2.appendPage$paging_common(r10, r5)
            int r10 = r8.appendItemsRequested
            int r2 = r0.size()
            int r10 = r10 - r2
            r8.appendItemsRequested = r10
            if (r10 <= 0) goto L_0x0097
            r10 = r0
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r4
            if (r10 == 0) goto L_0x0097
        L_0x006a:
            r3 = 1
            goto L_0x0097
        L_0x006c:
            androidx.paging.LoadType r5 = androidx.paging.LoadType.PREPEND
            if (r9 != r5) goto L_0x0116
            if (r2 == 0) goto L_0x0077
            if (r1 == 0) goto L_0x0077
            r8.prependItemsRequested = r3
            goto L_0x0097
        L_0x0077:
            androidx.paging.PagedStorage r2 = r8.getStorage$paging_common()
            r5 = r8
            androidx.paging.PagedStorage$Callback r5 = (androidx.paging.PagedStorage.Callback) r5
            r2.prependPage$paging_common(r10, r5)
            int r10 = r8.prependItemsRequested
            int r2 = r0.size()
            int r10 = r10 - r2
            r8.prependItemsRequested = r10
            if (r10 <= 0) goto L_0x0097
            r10 = r0
            java.util.Collection r10 = (java.util.Collection) r10
            boolean r10 = r10.isEmpty()
            r10 = r10 ^ r4
            if (r10 == 0) goto L_0x0097
            goto L_0x006a
        L_0x0097:
            boolean r10 = r8.shouldTrim
            if (r10 == 0) goto L_0x0112
            if (r1 == 0) goto L_0x00d8
            androidx.paging.LegacyPageFetcher<K, V> r10 = r8.pager
            androidx.paging.PagedList$LoadStateManager r10 = r10.getLoadStateManager()
            androidx.paging.LoadState r10 = r10.getStartState()
            boolean r10 = r10 instanceof androidx.paging.LoadState.Loading
            if (r10 != 0) goto L_0x0112
            androidx.paging.PagedStorage r10 = r8.getStorage$paging_common()
            boolean r1 = r8.replacePagesWithNulls
            androidx.paging.PagedList$Config r2 = r8.getConfig()
            int r2 = r2.maxSize
            int r4 = r8.getRequiredRemainder$paging_common()
            r5 = r8
            androidx.paging.PagedStorage$Callback r5 = (androidx.paging.PagedStorage.Callback) r5
            boolean r10 = r10.trimFromFront$paging_common(r1, r2, r4, r5)
            if (r10 == 0) goto L_0x0112
            androidx.paging.LegacyPageFetcher<K, V> r10 = r8.pager
            androidx.paging.PagedList$LoadStateManager r10 = r10.getLoadStateManager()
            androidx.paging.LoadType r1 = androidx.paging.LoadType.PREPEND
            androidx.paging.LoadState$NotLoading$Companion r2 = androidx.paging.LoadState.NotLoading.Companion
            androidx.paging.LoadState$NotLoading r2 = r2.getIncomplete$paging_common()
            androidx.paging.LoadState r2 = (androidx.paging.LoadState) r2
            r10.setState(r1, r2)
            goto L_0x0112
        L_0x00d8:
            androidx.paging.LegacyPageFetcher<K, V> r10 = r8.pager
            androidx.paging.PagedList$LoadStateManager r10 = r10.getLoadStateManager()
            androidx.paging.LoadState r10 = r10.getEndState()
            boolean r10 = r10 instanceof androidx.paging.LoadState.Loading
            if (r10 != 0) goto L_0x0112
            androidx.paging.PagedStorage r10 = r8.getStorage$paging_common()
            boolean r1 = r8.replacePagesWithNulls
            androidx.paging.PagedList$Config r2 = r8.getConfig()
            int r2 = r2.maxSize
            int r4 = r8.getRequiredRemainder$paging_common()
            r5 = r8
            androidx.paging.PagedStorage$Callback r5 = (androidx.paging.PagedStorage.Callback) r5
            boolean r10 = r10.trimFromEnd$paging_common(r1, r2, r4, r5)
            if (r10 == 0) goto L_0x0112
            androidx.paging.LegacyPageFetcher<K, V> r10 = r8.pager
            androidx.paging.PagedList$LoadStateManager r10 = r10.getLoadStateManager()
            androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND
            androidx.paging.LoadState$NotLoading$Companion r2 = androidx.paging.LoadState.NotLoading.Companion
            androidx.paging.LoadState$NotLoading r2 = r2.getIncomplete$paging_common()
            androidx.paging.LoadState r2 = (androidx.paging.LoadState) r2
            r10.setState(r1, r2)
        L_0x0112:
            r8.triggerBoundaryCallback(r9, r0)
            return r3
        L_0x0116:
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "unexpected result type "
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            r10.<init>(r9)
            java.lang.Throwable r10 = (java.lang.Throwable) r10
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.ContiguousPagedList.onPageResult(androidx.paging.LoadType, androidx.paging.PagingSource$LoadResult$Page):boolean");
    }

    public void onStateChanged(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "type");
        Intrinsics.checkNotNullParameter(loadState, "state");
        dispatchStateChangeAsync$paging_common(loadType, loadState);
    }

    private final void triggerBoundaryCallback(LoadType loadType, List<? extends V> list) {
        if (this.boundaryCallback != null) {
            boolean z = true;
            boolean z2 = getStorage$paging_common().size() == 0;
            boolean z3 = !z2 && loadType == LoadType.PREPEND && list.isEmpty();
            if (z2 || loadType != LoadType.APPEND || !list.isEmpty()) {
                z = false;
            }
            deferBoundaryCallbacks$paging_common(z2, z3, z);
        }
    }

    public final void deferBoundaryCallbacks$paging_common(boolean z, boolean z2, boolean z3) {
        if (this.boundaryCallback != null) {
            if (this.lowestIndexAccessed == Integer.MAX_VALUE) {
                this.lowestIndexAccessed = getStorage$paging_common().size();
            }
            if (this.highestIndexAccessed == Integer.MIN_VALUE) {
                this.highestIndexAccessed = 0;
            }
            if (z || z2 || z3) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope$paging_common(), getNotifyDispatcher$paging_common(), (CoroutineStart) null, new ContiguousPagedList$deferBoundaryCallbacks$1(this, z, z2, z3, (Continuation) null), 2, (Object) null);
                return;
            }
            return;
        }
        throw new IllegalStateException("Can't defer BoundaryCallback, no instance");
    }

    /* access modifiers changed from: private */
    public final void tryDispatchBoundaryCallbacks(boolean z) {
        boolean z2 = true;
        boolean z3 = this.boundaryCallbackBeginDeferred && this.lowestIndexAccessed <= getConfig().prefetchDistance;
        if (!this.boundaryCallbackEndDeferred || this.highestIndexAccessed < (size() - 1) - getConfig().prefetchDistance) {
            z2 = false;
        }
        if (z3 || z2) {
            if (z3) {
                this.boundaryCallbackBeginDeferred = false;
            }
            if (z2) {
                this.boundaryCallbackEndDeferred = false;
            }
            if (z) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope$paging_common(), getNotifyDispatcher$paging_common(), (CoroutineStart) null, new ContiguousPagedList$tryDispatchBoundaryCallbacks$1(this, z3, z2, (Continuation) null), 2, (Object) null);
            } else {
                dispatchBoundaryCallbacks(z3, z2);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void dispatchBoundaryCallbacks(boolean z, boolean z2) {
        if (z) {
            PagedList.BoundaryCallback<V> boundaryCallback2 = this.boundaryCallback;
            Intrinsics.checkNotNull(boundaryCallback2);
            boundaryCallback2.onItemAtFrontLoaded(getStorage$paging_common().getFirstLoadedItem$paging_common());
        }
        if (z2) {
            PagedList.BoundaryCallback<V> boundaryCallback3 = this.boundaryCallback;
            Intrinsics.checkNotNull(boundaryCallback3);
            boundaryCallback3.onItemAtEndLoaded(getStorage$paging_common().getLastLoadedItem$paging_common());
        }
    }

    public void retry() {
        Runnable refreshRetryCallback$paging_common;
        super.retry();
        this.pager.retry();
        if ((this.pager.getLoadStateManager().getRefreshState() instanceof LoadState.Error) && (refreshRetryCallback$paging_common = getRefreshRetryCallback$paging_common()) != null) {
            refreshRetryCallback$paging_common.run();
        }
    }

    public void dispatchCurrentLoadState(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "callback");
        this.pager.getLoadStateManager().dispatchCurrentLoadState(function2);
    }

    public void setInitialLoadState(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(loadState, "loadState");
        this.pager.getLoadStateManager().setState(loadType, loadState);
    }

    public void loadAroundInternal(int i) {
        Companion companion = Companion;
        int prependItemsRequested$paging_common = companion.getPrependItemsRequested$paging_common(getConfig().prefetchDistance, i, getStorage$paging_common().getPlaceholdersBefore());
        int appendItemsRequested$paging_common = companion.getAppendItemsRequested$paging_common(getConfig().prefetchDistance, i, getStorage$paging_common().getPlaceholdersBefore() + getStorage$paging_common().getStorageCount());
        int max = Math.max(prependItemsRequested$paging_common, this.prependItemsRequested);
        this.prependItemsRequested = max;
        if (max > 0) {
            this.pager.trySchedulePrepend();
        }
        int max2 = Math.max(appendItemsRequested$paging_common, this.appendItemsRequested);
        this.appendItemsRequested = max2;
        if (max2 > 0) {
            this.pager.tryScheduleAppend();
        }
        this.lowestIndexAccessed = Math.min(this.lowestIndexAccessed, i);
        this.highestIndexAccessed = Math.max(this.highestIndexAccessed, i);
        tryDispatchBoundaryCallbacks(true);
    }

    public void detach() {
        this.pager.detach();
    }

    public void onInitialized(int i) {
        boolean z = false;
        notifyInserted$paging_common(0, i);
        if (getStorage$paging_common().getPlaceholdersBefore() > 0 || getStorage$paging_common().getPlaceholdersAfter() > 0) {
            z = true;
        }
        this.replacePagesWithNulls = z;
    }

    public void onPagePrepended(int i, int i2, int i3) {
        notifyChanged(i, i2);
        notifyInserted$paging_common(0, i3);
        this.lowestIndexAccessed += i3;
        this.highestIndexAccessed += i3;
    }

    public void onPageAppended(int i, int i2, int i3) {
        notifyChanged(i, i2);
        notifyInserted$paging_common(i + i2, i3);
    }

    public void onPagesRemoved(int i, int i2) {
        notifyRemoved(i, i2);
    }

    public void onPagesSwappedToPlaceholder(int i, int i2) {
        notifyChanged(i, i2);
    }
}
