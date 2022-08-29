package androidx.paging;

import androidx.paging.LegacyPageFetcher;
import androidx.paging.PagedList;
import androidx.paging.PagingSource;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010!\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005:\u0001YB\u0007\b\u0016¢\u0006\u0002\u0010\u0006B)\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fB\u0015\b\u0012\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000¢\u0006\u0002\u0010\u000eJ+\u00100\u001a\u0002012\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n2\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0000¢\u0006\u0002\b4J\u0018\u00105\u001a\u0004\u0018\u00018\u00002\u0006\u00106\u001a\u00020\bH\u0002¢\u0006\u0002\u00107J\u0015\u00108\u001a\u00028\u00002\u0006\u00109\u001a\u00020\bH\u0016¢\u0006\u0002\u00107J\u001a\u0010:\u001a\u000e\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u0000\u0018\u00010;2\u0006\u0010<\u001a\u00020=JD\u0010>\u001a\u0002012\u0006\u0010\u0007\u001a\u00020\b2\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u00102\u001a\u0002032\b\b\u0002\u0010\u000f\u001a\u00020\u0010H\u0007J:\u0010>\u001a\u0002012\u0006\u0010\u0007\u001a\u00020\b2\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J \u0010?\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u0010B\u001a\u00020\bH\u0002J\u0016\u0010C\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bJ\u0016\u0010D\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\bJ+\u0010E\u001a\u0002012\u0010\u0010\t\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n2\n\b\u0002\u00102\u001a\u0004\u0018\u000103H\u0000¢\u0006\u0002\bFJ\u001e\u0010G\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u0010H\u001a\u00020\bJ\f\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000J\b\u0010J\u001a\u00020KH\u0016J`\u0010L\u001a\u0002HM\"\u0004\b\u0001\u0010M2\u0006\u00109\u001a\u00020\b2B\b\u0004\u0010N\u001a<\u0012\u001d\u0012\u001b\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n¢\u0006\f\bP\u0012\b\bQ\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\b¢\u0006\f\bP\u0012\b\bQ\u0012\u0004\b\b(R\u0012\u0004\u0012\u0002HM0OH\b¢\u0006\u0002\u0010SJ-\u0010T\u001a\u00020\u00102\u0006\u0010U\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\bVJ-\u0010W\u001a\u00020\u00102\u0006\u0010U\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\b2\u0006\u0010A\u001a\u00020\b2\u0006\u00102\u001a\u000203H\u0000¢\u0006\u0002\bXR\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00028\u00008@X\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\b8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\u00028\u00008@X\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u0011\u0010\u001d\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0017R\u0016\u0010\u001f\u001a\u0004\u0018\u00010\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0013R\u001e\u0010!\u001a\u0012\u0012\u000e\u0012\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u00028\u00000\n0\"X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010$\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b@RX\u000e¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u001e\u0010&\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b@RX\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0017R\u001e\u0010(\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0017R\u0016\u0010*\u001a\u0004\u0018\u00010\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u0013R\u0014\u0010,\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\u0017R\u001e\u0010.\u001a\u00020\b2\u0006\u0010#\u001a\u00020\b@RX\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u0017¨\u0006Z"}, mo27512d2 = {"Landroidx/paging/PagedStorage;", "T", "", "Ljava/util/AbstractList;", "Landroidx/paging/LegacyPageFetcher$KeyProvider;", "Landroidx/paging/NullPaddedList;", "()V", "leadingNulls", "", "page", "Landroidx/paging/PagingSource$LoadResult$Page;", "trailingNulls", "(ILandroidx/paging/PagingSource$LoadResult$Page;I)V", "other", "(Landroidx/paging/PagedStorage;)V", "counted", "", "firstLoadedItem", "getFirstLoadedItem$paging_common", "()Ljava/lang/Object;", "value", "lastLoadAroundIndex", "getLastLoadAroundIndex", "()I", "setLastLoadAroundIndex", "(I)V", "lastLoadAroundLocalIndex", "lastLoadedItem", "getLastLoadedItem$paging_common", "middleOfLoadedRange", "getMiddleOfLoadedRange", "nextKey", "getNextKey", "pages", "", "<set-?>", "placeholdersAfter", "getPlaceholdersAfter", "placeholdersBefore", "getPlaceholdersBefore", "positionOffset", "getPositionOffset", "prevKey", "getPrevKey", "size", "getSize", "storageCount", "getStorageCount", "appendPage", "", "callback", "Landroidx/paging/PagedStorage$Callback;", "appendPage$paging_common", "get", "index", "(I)Ljava/lang/Object;", "getFromStorage", "localIndex", "getRefreshKeyInfo", "Landroidx/paging/PagingState;", "config", "Landroidx/paging/PagedList$Config;", "init", "needsTrim", "maxSize", "requiredRemaining", "localPageIndex", "needsTrimFromEnd", "needsTrimFromFront", "prependPage", "prependPage$paging_common", "shouldPreTrimNewPage", "countToBeAdded", "snapshot", "toString", "", "traversePages", "V", "onLastPage", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "pageInternalIndex", "(ILkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "trimFromEnd", "insertNulls", "trimFromEnd$paging_common", "trimFromFront", "trimFromFront$paging_common", "Callback", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PagedStorage.kt */
public final class PagedStorage<T> extends AbstractList<T> implements LegacyPageFetcher.KeyProvider<Object>, NullPaddedList<T> {
    private boolean counted;
    private int lastLoadAroundLocalIndex;
    /* access modifiers changed from: private */
    public final List<PagingSource.LoadResult.Page<?, T>> pages;
    private int placeholdersAfter;
    private int placeholdersBefore;
    private int positionOffset;
    private int storageCount;

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J \u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000f"}, mo27512d2 = {"Landroidx/paging/PagedStorage$Callback;", "", "onInitialized", "", "count", "", "onPageAppended", "endPosition", "changed", "added", "onPagePrepended", "leadingNulls", "onPagesRemoved", "startOfDrops", "onPagesSwappedToPlaceholder", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PagedStorage.kt */
    public interface Callback {
        void onInitialized(int i);

        void onPageAppended(int i, int i2, int i3);

        void onPagePrepended(int i, int i2, int i3);

        void onPagesRemoved(int i, int i2);

        void onPagesSwappedToPlaceholder(int i, int i2);
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

    public final T getFirstLoadedItem$paging_common() {
        return CollectionsKt.first(((PagingSource.LoadResult.Page) CollectionsKt.first(this.pages)).getData());
    }

    public final T getLastLoadedItem$paging_common() {
        return CollectionsKt.last(((PagingSource.LoadResult.Page) CollectionsKt.last(this.pages)).getData());
    }

    public int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    public int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    public final int getPositionOffset() {
        return this.positionOffset;
    }

    public int getStorageCount() {
        return this.storageCount;
    }

    public final int getLastLoadAroundIndex() {
        return getPlaceholdersBefore() + this.lastLoadAroundLocalIndex;
    }

    public final void setLastLoadAroundIndex(int i) {
        this.lastLoadAroundLocalIndex = RangesKt.coerceIn(i - getPlaceholdersBefore(), 0, getStorageCount() - 1);
    }

    public final int getMiddleOfLoadedRange() {
        return getPlaceholdersBefore() + (getStorageCount() / 2);
    }

    public PagedStorage() {
        this.pages = new ArrayList();
        this.counted = true;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PagedStorage(int i, PagingSource.LoadResult.Page<?, T> page, int i2) {
        this();
        Intrinsics.checkNotNullParameter(page, "page");
        init(i, page, i2, 0, true);
    }

    private PagedStorage(PagedStorage<T> pagedStorage) {
        List<PagingSource.LoadResult.Page<?, T>> arrayList = new ArrayList<>();
        this.pages = arrayList;
        this.counted = true;
        arrayList.addAll(pagedStorage.pages);
        this.placeholdersBefore = pagedStorage.getPlaceholdersBefore();
        this.placeholdersAfter = pagedStorage.getPlaceholdersAfter();
        this.positionOffset = pagedStorage.positionOffset;
        this.counted = pagedStorage.counted;
        this.storageCount = pagedStorage.getStorageCount();
        this.lastLoadAroundLocalIndex = pagedStorage.lastLoadAroundLocalIndex;
    }

    public final PagedStorage<T> snapshot() {
        return new PagedStorage<>(this);
    }

    private final void init(int i, PagingSource.LoadResult.Page<?, T> page, int i2, int i3, boolean z) {
        this.placeholdersBefore = i;
        this.pages.clear();
        this.pages.add(page);
        this.placeholdersAfter = i2;
        this.positionOffset = i3;
        this.storageCount = page.getData().size();
        this.counted = z;
        this.lastLoadAroundLocalIndex = page.getData().size() / 2;
    }

    public static /* synthetic */ void init$default(PagedStorage pagedStorage, int i, PagingSource.LoadResult.Page page, int i2, int i3, Callback callback, boolean z, int i4, Object obj) {
        pagedStorage.init(i, page, i2, i3, callback, (i4 & 32) != 0 ? true : z);
    }

    public final void init(int i, PagingSource.LoadResult.Page<?, T> page, int i2, int i3, Callback callback, boolean z) {
        Intrinsics.checkNotNullParameter(page, "page");
        Intrinsics.checkNotNullParameter(callback, "callback");
        init(i, page, i2, i3, z);
        callback.onInitialized(size());
    }

    public Object getPrevKey() {
        if (!this.counted || getPlaceholdersBefore() + this.positionOffset > 0) {
            return ((PagingSource.LoadResult.Page) CollectionsKt.first(this.pages)).getPrevKey();
        }
        return null;
    }

    public Object getNextKey() {
        if (!this.counted || getPlaceholdersAfter() > 0) {
            return ((PagingSource.LoadResult.Page) CollectionsKt.last(this.pages)).getNextKey();
        }
        return null;
    }

    private final <V> V traversePages(int i, Function2<? super PagingSource.LoadResult.Page<?, T>, ? super Integer, ? extends V> function2) {
        int size = this.pages.size();
        int i2 = 0;
        while (i2 < size) {
            int size2 = ((PagingSource.LoadResult.Page) this.pages.get(i2)).getData().size();
            if (size2 > i) {
                break;
            }
            i -= size2;
            i2++;
        }
        return function2.invoke(this.pages.get(i2), Integer.valueOf(i));
    }

    public final PagingState<?, T> getRefreshKeyInfo(PagedList.C0510Config config) {
        Intrinsics.checkNotNullParameter(config, "config");
        if (this.pages.isEmpty()) {
            return null;
        }
        List list = CollectionsKt.toList(this.pages);
        Objects.requireNonNull(list, "null cannot be cast to non-null type kotlin.collections.List<androidx.paging.PagingSource.LoadResult.Page<kotlin.Any, T>>");
        return new PagingState<>(list, Integer.valueOf(getLastLoadAroundIndex()), new PagingConfig(config.pageSize, config.prefetchDistance, config.enablePlaceholders, config.initialLoadSizeHint, config.maxSize, 0, 32, (DefaultConstructorMarker) null), getPlaceholdersBefore());
    }

    public T get(int i) {
        int placeholdersBefore2 = i - getPlaceholdersBefore();
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size());
        } else if (placeholdersBefore2 < 0 || placeholdersBefore2 >= getStorageCount()) {
            return null;
        } else {
            return getFromStorage(placeholdersBefore2);
        }
    }

    public int getSize() {
        return getPlaceholdersBefore() + getStorageCount() + getPlaceholdersAfter();
    }

    private final boolean needsTrim(int i, int i2, int i3) {
        return getStorageCount() > i && this.pages.size() > 2 && getStorageCount() - this.pages.get(i3).getData().size() >= i2;
    }

    public final boolean needsTrimFromFront(int i, int i2) {
        return needsTrim(i, i2, 0);
    }

    public final boolean needsTrimFromEnd(int i, int i2) {
        return needsTrim(i, i2, this.pages.size() - 1);
    }

    public final boolean shouldPreTrimNewPage(int i, int i2, int i3) {
        return getStorageCount() + i3 > i && this.pages.size() > 1 && getStorageCount() >= i2;
    }

    public final boolean trimFromFront$paging_common(boolean z, int i, int i2, Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        int i3 = 0;
        while (needsTrimFromFront(i, i2)) {
            int size = this.pages.remove(0).getData().size();
            i3 += size;
            this.storageCount = getStorageCount() - size;
        }
        this.lastLoadAroundLocalIndex = RangesKt.coerceAtLeast(this.lastLoadAroundLocalIndex - i3, 0);
        if (i3 > 0) {
            if (z) {
                int placeholdersBefore2 = getPlaceholdersBefore();
                this.placeholdersBefore = getPlaceholdersBefore() + i3;
                callback.onPagesSwappedToPlaceholder(placeholdersBefore2, i3);
            } else {
                this.positionOffset += i3;
                callback.onPagesRemoved(getPlaceholdersBefore(), i3);
            }
        }
        if (i3 > 0) {
            return true;
        }
        return false;
    }

    public final boolean trimFromEnd$paging_common(boolean z, int i, int i2, Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        int i3 = 0;
        while (needsTrimFromEnd(i, i2)) {
            List<PagingSource.LoadResult.Page<?, T>> list = this.pages;
            int size = list.remove(list.size() - 1).getData().size();
            i3 += size;
            this.storageCount = getStorageCount() - size;
        }
        this.lastLoadAroundLocalIndex = RangesKt.coerceAtMost(this.lastLoadAroundLocalIndex, getStorageCount() - 1);
        if (i3 > 0) {
            int placeholdersBefore2 = getPlaceholdersBefore() + getStorageCount();
            if (z) {
                this.placeholdersAfter = getPlaceholdersAfter() + i3;
                callback.onPagesSwappedToPlaceholder(placeholdersBefore2, i3);
            } else {
                callback.onPagesRemoved(placeholdersBefore2, i3);
            }
        }
        if (i3 > 0) {
            return true;
        }
        return false;
    }

    public static /* synthetic */ void prependPage$paging_common$default(PagedStorage pagedStorage, PagingSource.LoadResult.Page page, Callback callback, int i, Object obj) {
        if ((i & 2) != 0) {
            callback = null;
        }
        pagedStorage.prependPage$paging_common(page, callback);
    }

    public final void prependPage$paging_common(PagingSource.LoadResult.Page<?, T> page, Callback callback) {
        Intrinsics.checkNotNullParameter(page, "page");
        int size = page.getData().size();
        if (size != 0) {
            this.pages.add(0, page);
            this.storageCount = getStorageCount() + size;
            int min = Math.min(getPlaceholdersBefore(), size);
            int i = size - min;
            if (min != 0) {
                this.placeholdersBefore = getPlaceholdersBefore() - min;
            }
            this.positionOffset -= i;
            if (callback != null) {
                callback.onPagePrepended(getPlaceholdersBefore(), min, i);
            }
        }
    }

    public static /* synthetic */ void appendPage$paging_common$default(PagedStorage pagedStorage, PagingSource.LoadResult.Page page, Callback callback, int i, Object obj) {
        if ((i & 2) != 0) {
            callback = null;
        }
        pagedStorage.appendPage$paging_common(page, callback);
    }

    public final void appendPage$paging_common(PagingSource.LoadResult.Page<?, T> page, Callback callback) {
        Intrinsics.checkNotNullParameter(page, "page");
        int size = page.getData().size();
        if (size != 0) {
            this.pages.add(page);
            this.storageCount = getStorageCount() + size;
            int min = Math.min(getPlaceholdersAfter(), size);
            int i = size - min;
            if (min != 0) {
                this.placeholdersAfter = getPlaceholdersAfter() - min;
            }
            if (callback != null) {
                callback.onPageAppended((getPlaceholdersBefore() + getStorageCount()) - size, min, i);
            }
        }
    }

    public String toString() {
        return "leading " + getPlaceholdersBefore() + ", storage " + getStorageCount() + ", trailing " + getPlaceholdersAfter() + ' ' + CollectionsKt.joinToString$default(this.pages, " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public T getFromStorage(int i) {
        int size = this.pages.size();
        int i2 = 0;
        while (i2 < size) {
            int size2 = ((PagingSource.LoadResult.Page) this.pages.get(i2)).getData().size();
            if (size2 > i) {
                break;
            }
            i -= size2;
            i2++;
        }
        return ((PagingSource.LoadResult.Page) this.pages.get(i2)).getData().get(i);
    }
}
