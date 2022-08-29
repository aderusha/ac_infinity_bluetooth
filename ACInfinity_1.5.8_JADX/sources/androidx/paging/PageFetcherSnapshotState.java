package androidx.paging;

import androidx.core.app.NotificationCompat;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import androidx.paging.PagingSource;
import androidx.paging.ViewportHint;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0001JB\u000f\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000b0/J\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000b0/J#\u00101\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001022\b\u00103\u001a\u0004\u0018\u000104H\u0000¢\u0006\u0002\b5J\u0014\u00106\u001a\u0002072\f\u00108\u001a\b\u0012\u0004\u0012\u00028\u000109J\u001e\u0010:\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u0001092\u0006\u0010;\u001a\u00020\u00122\u0006\u0010<\u001a\u00020\u0013J\u0015\u0010=\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u0012H\u0000¢\u0006\u0002\b>J,\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u00122\u0012\u0010B\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\tH\u0007J\u0016\u0010C\u001a\u00020@2\u0006\u0010D\u001a\u00020\u00122\u0006\u0010E\u001a\u00020FJ+\u0010G\u001a\b\u0012\u0004\u0012\u00028\u00010H*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t2\u0006\u0010;\u001a\u00020\u0012H\u0000¢\u0006\u0002\bIR \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R&\u0010\u001a\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t0\u001bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR$\u0010\u001f\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000b8@@@X\u000e¢\u0006\f\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\"R$\u0010#\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000b8@@@X\u000e¢\u0006\f\u001a\u0004\b$\u0010\u0019\"\u0004\b%\u0010\"R\u000e\u0010&\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010)\u001a\u00020(2\u0006\u0010\u0016\u001a\u00020(@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020\u000b8@X\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\u0019¨\u0006K"}, mo27512d2 = {"Landroidx/paging/PageFetcherSnapshotState;", "Key", "", "Value", "config", "Landroidx/paging/PagingConfig;", "(Landroidx/paging/PagingConfig;)V", "_pages", "", "Landroidx/paging/PagingSource$LoadResult$Page;", "_placeholdersAfter", "", "_placeholdersBefore", "appendGenerationId", "appendGenerationIdCh", "Lkotlinx/coroutines/channels/Channel;", "failedHintsByLoadType", "", "Landroidx/paging/LoadType;", "Landroidx/paging/ViewportHint;", "getFailedHintsByLoadType$paging_common", "()Ljava/util/Map;", "<set-?>", "initialPageIndex", "getInitialPageIndex$paging_common", "()I", "pages", "", "getPages$paging_common", "()Ljava/util/List;", "value", "placeholdersAfter", "getPlaceholdersAfter$paging_common", "setPlaceholdersAfter$paging_common", "(I)V", "placeholdersBefore", "getPlaceholdersBefore$paging_common", "setPlaceholdersBefore$paging_common", "prependGenerationId", "prependGenerationIdCh", "Landroidx/paging/LoadStates;", "sourceLoadStates", "getSourceLoadStates$paging_common", "()Landroidx/paging/LoadStates;", "storageCount", "getStorageCount$paging_common", "consumeAppendGenerationIdAsFlow", "Lkotlinx/coroutines/flow/Flow;", "consumePrependGenerationIdAsFlow", "currentPagingState", "Landroidx/paging/PagingState;", "viewportHint", "Landroidx/paging/ViewportHint$Access;", "currentPagingState$paging_common", "drop", "", "event", "Landroidx/paging/PageEvent$Drop;", "dropEventOrNull", "loadType", "hint", "generationId", "generationId$paging_common", "insert", "", "loadId", "page", "setSourceLoadState", "type", "newState", "Landroidx/paging/LoadState;", "toPageEvent", "Landroidx/paging/PageEvent;", "toPageEvent$paging_common", "Holder", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PageFetcherSnapshotState.kt */
public final class PageFetcherSnapshotState<Key, Value> {
    private final List<PagingSource.LoadResult.Page<Key, Value>> _pages;
    private int _placeholdersAfter;
    private int _placeholdersBefore;
    /* access modifiers changed from: private */
    public int appendGenerationId;
    /* access modifiers changed from: private */
    public final Channel<Integer> appendGenerationIdCh;
    private final PagingConfig config;
    private final Map<LoadType, ViewportHint> failedHintsByLoadType;
    private int initialPageIndex;
    private final List<PagingSource.LoadResult.Page<Key, Value>> pages;
    /* access modifiers changed from: private */
    public int prependGenerationId;
    /* access modifiers changed from: private */
    public final Channel<Integer> prependGenerationIdCh;
    private LoadStates sourceLoadStates;

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;
        public static final /* synthetic */ int[] $EnumSwitchMapping$4;
        public static final /* synthetic */ int[] $EnumSwitchMapping$5;
        public static final /* synthetic */ int[] $EnumSwitchMapping$6;
        public static final /* synthetic */ int[] $EnumSwitchMapping$7;
        public static final /* synthetic */ int[] $EnumSwitchMapping$8;

        static {
            int[] iArr = new int[LoadType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[LoadType.REFRESH.ordinal()] = 1;
            iArr[LoadType.PREPEND.ordinal()] = 2;
            iArr[LoadType.APPEND.ordinal()] = 3;
            int[] iArr2 = new int[LoadType.values().length];
            $EnumSwitchMapping$1 = iArr2;
            iArr2[LoadType.REFRESH.ordinal()] = 1;
            iArr2[LoadType.PREPEND.ordinal()] = 2;
            iArr2[LoadType.APPEND.ordinal()] = 3;
            int[] iArr3 = new int[LoadType.values().length];
            $EnumSwitchMapping$2 = iArr3;
            iArr3[LoadType.REFRESH.ordinal()] = 1;
            iArr3[LoadType.PREPEND.ordinal()] = 2;
            iArr3[LoadType.APPEND.ordinal()] = 3;
            int[] iArr4 = new int[LoadType.values().length];
            $EnumSwitchMapping$3 = iArr4;
            iArr4[LoadType.REFRESH.ordinal()] = 1;
            iArr4[LoadType.PREPEND.ordinal()] = 2;
            iArr4[LoadType.APPEND.ordinal()] = 3;
            int[] iArr5 = new int[LoadType.values().length];
            $EnumSwitchMapping$4 = iArr5;
            iArr5[LoadType.PREPEND.ordinal()] = 1;
            iArr5[LoadType.APPEND.ordinal()] = 2;
            int[] iArr6 = new int[LoadType.values().length];
            $EnumSwitchMapping$5 = iArr6;
            iArr6[LoadType.PREPEND.ordinal()] = 1;
            int[] iArr7 = new int[LoadType.values().length];
            $EnumSwitchMapping$6 = iArr7;
            iArr7[LoadType.PREPEND.ordinal()] = 1;
            int[] iArr8 = new int[LoadType.values().length];
            $EnumSwitchMapping$7 = iArr8;
            iArr8[LoadType.PREPEND.ordinal()] = 1;
            int[] iArr9 = new int[LoadType.values().length];
            $EnumSwitchMapping$8 = iArr9;
            iArr9[LoadType.PREPEND.ordinal()] = 1;
        }
    }

    private PageFetcherSnapshotState(PagingConfig pagingConfig) {
        this.config = pagingConfig;
        List<PagingSource.LoadResult.Page<Key, Value>> arrayList = new ArrayList<>();
        this._pages = arrayList;
        this.pages = arrayList;
        this.prependGenerationIdCh = ChannelKt.Channel$default(-1, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        this.appendGenerationIdCh = ChannelKt.Channel$default(-1, (BufferOverflow) null, (Function1) null, 6, (Object) null);
        this.failedHintsByLoadType = new LinkedHashMap();
        this.sourceLoadStates = LoadStates.Companion.getIDLE();
    }

    public /* synthetic */ PageFetcherSnapshotState(PagingConfig pagingConfig, DefaultConstructorMarker defaultConstructorMarker) {
        this(pagingConfig);
    }

    public final List<PagingSource.LoadResult.Page<Key, Value>> getPages$paging_common() {
        return this.pages;
    }

    public final int getInitialPageIndex$paging_common() {
        return this.initialPageIndex;
    }

    public final int getStorageCount$paging_common() {
        int i = 0;
        for (PagingSource.LoadResult.Page data : this.pages) {
            i += data.getData().size();
        }
        return i;
    }

    public final int getPlaceholdersBefore$paging_common() {
        if (this.config.enablePlaceholders) {
            return this._placeholdersBefore;
        }
        return 0;
    }

    public final void setPlaceholdersBefore$paging_common(int i) {
        if (i == Integer.MIN_VALUE) {
            i = 0;
        }
        this._placeholdersBefore = i;
    }

    public final int getPlaceholdersAfter$paging_common() {
        if (this.config.enablePlaceholders) {
            return this._placeholdersAfter;
        }
        return 0;
    }

    public final void setPlaceholdersAfter$paging_common(int i) {
        if (i == Integer.MIN_VALUE) {
            i = 0;
        }
        this._placeholdersAfter = i;
    }

    public final int generationId$paging_common(LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException("Cannot get loadId for loadType: REFRESH");
        } else if (i == 2) {
            return this.prependGenerationId;
        } else {
            if (i == 3) {
                return this.appendGenerationId;
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public final Map<LoadType, ViewportHint> getFailedHintsByLoadType$paging_common() {
        return this.failedHintsByLoadType;
    }

    public final LoadStates getSourceLoadStates$paging_common() {
        return this.sourceLoadStates;
    }

    public final Flow<Integer> consumePrependGenerationIdAsFlow() {
        return FlowKt.onStart(FlowKt.consumeAsFlow(this.prependGenerationIdCh), new PageFetcherSnapshotState$consumePrependGenerationIdAsFlow$1(this, (Continuation) null));
    }

    public final Flow<Integer> consumeAppendGenerationIdAsFlow() {
        return FlowKt.onStart(FlowKt.consumeAsFlow(this.appendGenerationIdCh), new PageFetcherSnapshotState$consumeAppendGenerationIdAsFlow$1(this, (Continuation) null));
    }

    public final boolean setSourceLoadState(LoadType loadType, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "type");
        Intrinsics.checkNotNullParameter(loadState, "newState");
        if (Intrinsics.areEqual((Object) this.sourceLoadStates.get$paging_common(loadType), (Object) loadState)) {
            return false;
        }
        this.sourceLoadStates = this.sourceLoadStates.modifyState$paging_common(loadType, loadState);
        return true;
    }

    public final PageEvent<Value> toPageEvent$paging_common(PagingSource.LoadResult.Page<Key, Value> page, LoadType loadType) {
        Intrinsics.checkNotNullParameter(page, "$this$toPageEvent");
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$1[loadType.ordinal()];
        int i2 = 0;
        if (i != 1) {
            if (i == 2) {
                i2 = 0 - this.initialPageIndex;
            } else if (i == 3) {
                i2 = (this.pages.size() - this.initialPageIndex) - 1;
            } else {
                throw new NoWhenBranchMatchedException();
            }
        }
        List listOf = CollectionsKt.listOf(new TransformablePage(i2, page.getData()));
        int i3 = WhenMappings.$EnumSwitchMapping$2[loadType.ordinal()];
        if (i3 == 1) {
            return PageEvent.Insert.Companion.Refresh(listOf, getPlaceholdersBefore$paging_common(), getPlaceholdersAfter$paging_common(), new CombinedLoadStates(this.sourceLoadStates.getRefresh(), this.sourceLoadStates.getPrepend(), this.sourceLoadStates.getAppend(), this.sourceLoadStates, (LoadStates) null));
        }
        if (i3 == 2) {
            return PageEvent.Insert.Companion.Prepend(listOf, getPlaceholdersBefore$paging_common(), new CombinedLoadStates(this.sourceLoadStates.getRefresh(), this.sourceLoadStates.getPrepend(), this.sourceLoadStates.getAppend(), this.sourceLoadStates, (LoadStates) null));
        }
        if (i3 == 3) {
            return PageEvent.Insert.Companion.Append(listOf, getPlaceholdersAfter$paging_common(), new CombinedLoadStates(this.sourceLoadStates.getRefresh(), this.sourceLoadStates.getPrepend(), this.sourceLoadStates.getAppend(), this.sourceLoadStates, (LoadStates) null));
        }
        throw new NoWhenBranchMatchedException();
    }

    public final boolean insert(int i, LoadType loadType, PagingSource.LoadResult.Page<Key, Value> page) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(page, "page");
        int i4 = WhenMappings.$EnumSwitchMapping$3[loadType.ordinal()];
        if (i4 != 1) {
            if (i4 != 2) {
                if (i4 == 3) {
                    if (!(!this.pages.isEmpty())) {
                        throw new IllegalStateException("should've received an init before append".toString());
                    } else if (i != this.appendGenerationId) {
                        return false;
                    } else {
                        this._pages.add(page);
                        if (page.getItemsAfter() == Integer.MIN_VALUE) {
                            i3 = RangesKt.coerceAtLeast(getPlaceholdersAfter$paging_common() - page.getData().size(), 0);
                        } else {
                            i3 = page.getItemsAfter();
                        }
                        setPlaceholdersAfter$paging_common(i3);
                        this.failedHintsByLoadType.remove(LoadType.APPEND);
                    }
                }
            } else if (!(!this.pages.isEmpty())) {
                throw new IllegalStateException("should've received an init before prepend".toString());
            } else if (i != this.prependGenerationId) {
                return false;
            } else {
                this._pages.add(0, page);
                this.initialPageIndex++;
                if (page.getItemsBefore() == Integer.MIN_VALUE) {
                    i2 = RangesKt.coerceAtLeast(getPlaceholdersBefore$paging_common() - page.getData().size(), 0);
                } else {
                    i2 = page.getItemsBefore();
                }
                setPlaceholdersBefore$paging_common(i2);
                this.failedHintsByLoadType.remove(LoadType.PREPEND);
            }
        } else if (this.pages.isEmpty()) {
            if (i == 0) {
                this._pages.add(page);
                this.initialPageIndex = 0;
                setPlaceholdersAfter$paging_common(page.getItemsAfter());
                setPlaceholdersBefore$paging_common(page.getItemsBefore());
            } else {
                throw new IllegalStateException("init loadId must be the initial value, 0".toString());
            }
        } else {
            throw new IllegalStateException("cannot receive multiple init calls".toString());
        }
        return true;
    }

    public final void drop(PageEvent.Drop<Value> drop) {
        Intrinsics.checkNotNullParameter(drop, NotificationCompat.CATEGORY_EVENT);
        if (drop.getPageCount() <= this.pages.size()) {
            this.failedHintsByLoadType.remove(drop.getLoadType());
            this.sourceLoadStates = this.sourceLoadStates.modifyState$paging_common(drop.getLoadType(), LoadState.NotLoading.Companion.getIncomplete$paging_common());
            int i = WhenMappings.$EnumSwitchMapping$4[drop.getLoadType().ordinal()];
            if (i == 1) {
                int pageCount = drop.getPageCount();
                for (int i2 = 0; i2 < pageCount; i2++) {
                    this._pages.remove(0);
                }
                this.initialPageIndex -= drop.getPageCount();
                setPlaceholdersBefore$paging_common(drop.getPlaceholdersRemaining());
                int i3 = this.prependGenerationId + 1;
                this.prependGenerationId = i3;
                this.prependGenerationIdCh.offer(Integer.valueOf(i3));
            } else if (i == 2) {
                int pageCount2 = drop.getPageCount();
                for (int i4 = 0; i4 < pageCount2; i4++) {
                    this._pages.remove(this.pages.size() - 1);
                }
                setPlaceholdersAfter$paging_common(drop.getPlaceholdersRemaining());
                int i5 = this.appendGenerationId + 1;
                this.appendGenerationId = i5;
                this.appendGenerationIdCh.offer(Integer.valueOf(i5));
            } else {
                throw new IllegalArgumentException("cannot drop " + drop.getLoadType());
            }
        } else {
            throw new IllegalStateException(("invalid drop count. have " + this.pages.size() + " but wanted to drop " + drop.getPageCount()).toString());
        }
    }

    public final PageEvent.Drop<Value> dropEventOrNull(LoadType loadType, ViewportHint viewportHint) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(viewportHint, "hint");
        PageEvent.Drop<Value> drop = null;
        if (this.config.maxSize == Integer.MAX_VALUE || this.pages.size() <= 2 || getStorageCount$paging_common() <= this.config.maxSize) {
            return null;
        }
        int i7 = 0;
        if (loadType != LoadType.REFRESH) {
            int i8 = 0;
            int i9 = 0;
            while (i8 < this.pages.size() && getStorageCount$paging_common() - i9 > this.config.maxSize) {
                if (WhenMappings.$EnumSwitchMapping$5[loadType.ordinal()] != 1) {
                    List<PagingSource.LoadResult.Page<Key, Value>> list = this.pages;
                    i5 = list.get(CollectionsKt.getLastIndex(list) - i8).getData().size();
                } else {
                    i5 = this.pages.get(i8).getData().size();
                }
                if (WhenMappings.$EnumSwitchMapping$6[loadType.ordinal()] != 1) {
                    i6 = viewportHint.getPresentedItemsAfter();
                } else {
                    i6 = viewportHint.getPresentedItemsBefore();
                }
                if ((i6 - i9) - i5 < this.config.prefetchDistance) {
                    break;
                }
                i9 += i5;
                i8++;
            }
            if (i8 != 0) {
                if (WhenMappings.$EnumSwitchMapping$7[loadType.ordinal()] != 1) {
                    i = (CollectionsKt.getLastIndex(this.pages) - this.initialPageIndex) - (i8 - 1);
                } else {
                    i = -this.initialPageIndex;
                }
                if (WhenMappings.$EnumSwitchMapping$8[loadType.ordinal()] != 1) {
                    i3 = CollectionsKt.getLastIndex(this.pages);
                    i2 = this.initialPageIndex;
                } else {
                    i3 = i8 - 1;
                    i2 = this.initialPageIndex;
                }
                int i10 = i3 - i2;
                if (this.config.enablePlaceholders) {
                    if (loadType == LoadType.PREPEND) {
                        i4 = getPlaceholdersBefore$paging_common();
                    } else {
                        i4 = getPlaceholdersAfter$paging_common();
                    }
                    i7 = i4 + i9;
                }
                drop = new PageEvent.Drop<>(loadType, i, i10, i7);
            }
            return drop;
        }
        throw new IllegalArgumentException(("Drop LoadType must be PREPEND or APPEND, but got " + loadType).toString());
    }

    public final PagingState<Key, Value> currentPagingState$paging_common(ViewportHint.Access access) {
        Integer num;
        int i;
        List list = CollectionsKt.toList(this.pages);
        if (access != null) {
            int placeholdersBefore$paging_common = getPlaceholdersBefore$paging_common();
            int i2 = -this.initialPageIndex;
            int lastIndex = CollectionsKt.getLastIndex(this.pages) - this.initialPageIndex;
            int pageOffset = access.getPageOffset();
            for (int i3 = i2; i3 < pageOffset; i3++) {
                if (i3 > lastIndex) {
                    i = this.config.pageSize;
                } else {
                    i = this.pages.get(this.initialPageIndex + i3).getData().size();
                }
                placeholdersBefore$paging_common += i;
            }
            int indexInPage = placeholdersBefore$paging_common + access.getIndexInPage();
            if (access.getPageOffset() < i2) {
                indexInPage -= this.config.pageSize;
            }
            num = Integer.valueOf(indexInPage);
        } else {
            num = null;
        }
        return new PagingState<>(list, num, this.config, getPlaceholdersBefore$paging_common());
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0002B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006JF\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0004\u0010\f2-\u0010\r\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\n¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\t\u0012\u0004\u0012\u0002H\f0\u000eHHø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\nX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, mo27512d2 = {"Landroidx/paging/PageFetcherSnapshotState$Holder;", "Key", "", "Value", "config", "Landroidx/paging/PagingConfig;", "(Landroidx/paging/PagingConfig;)V", "lock", "Lkotlinx/coroutines/sync/Mutex;", "state", "Landroidx/paging/PageFetcherSnapshotState;", "withLock", "T", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PageFetcherSnapshotState.kt */
    public static final class Holder<Key, Value> {
        private final PagingConfig config;
        /* access modifiers changed from: private */
        public final Mutex lock = MutexKt.Mutex$default(false, 1, (Object) null);
        /* access modifiers changed from: private */
        public final PageFetcherSnapshotState<Key, Value> state;

        public Holder(PagingConfig pagingConfig) {
            Intrinsics.checkNotNullParameter(pagingConfig, "config");
            this.config = pagingConfig;
            this.state = new PageFetcherSnapshotState<>(pagingConfig, (DefaultConstructorMarker) null);
        }

        /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final <T> java.lang.Object withLock(kotlin.jvm.functions.Function1<? super androidx.paging.PageFetcherSnapshotState<Key, Value>, ? extends T> r6, kotlin.coroutines.Continuation<? super T> r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof androidx.paging.PageFetcherSnapshotState$Holder$withLock$1
                if (r0 == 0) goto L_0x0014
                r0 = r7
                androidx.paging.PageFetcherSnapshotState$Holder$withLock$1 r0 = (androidx.paging.PageFetcherSnapshotState$Holder$withLock$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r7 = r0.label
                int r7 = r7 - r2
                r0.label = r7
                goto L_0x0019
            L_0x0014:
                androidx.paging.PageFetcherSnapshotState$Holder$withLock$1 r0 = new androidx.paging.PageFetcherSnapshotState$Holder$withLock$1
                r0.<init>(r5, r7)
            L_0x0019:
                java.lang.Object r7 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 0
                r4 = 1
                if (r2 == 0) goto L_0x0041
                if (r2 != r4) goto L_0x0039
                java.lang.Object r6 = r0.L$2
                kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
                java.lang.Object r1 = r0.L$1
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                java.lang.Object r0 = r0.L$0
                androidx.paging.PageFetcherSnapshotState$Holder r0 = (androidx.paging.PageFetcherSnapshotState.Holder) r0
                kotlin.ResultKt.throwOnFailure(r7)
                r7 = r6
                r6 = r1
                goto L_0x0058
            L_0x0039:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L_0x0041:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.sync.Mutex r7 = r5.lock
                r0.L$0 = r5
                r0.L$1 = r6
                r0.L$2 = r7
                r0.label = r4
                java.lang.Object r0 = r7.lock(r3, r0)
                if (r0 != r1) goto L_0x0057
                return r1
            L_0x0057:
                r0 = r5
            L_0x0058:
                androidx.paging.PageFetcherSnapshotState r0 = r0.state     // Catch:{ all -> 0x006a }
                java.lang.Object r6 = r6.invoke(r0)     // Catch:{ all -> 0x006a }
                kotlin.jvm.internal.InlineMarker.finallyStart(r4)
                r7.unlock(r3)
                kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
                return r6
            L_0x006a:
                r6 = move-exception
                kotlin.jvm.internal.InlineMarker.finallyStart(r4)
                r7.unlock(r3)
                kotlin.jvm.internal.InlineMarker.finallyEnd(r4)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshotState.Holder.withLock(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
        }

        private final Object withLock$$forInline(Function1 function1, Continuation continuation) {
            Mutex access$getLock$p = this.lock;
            InlineMarker.mark(0);
            access$getLock$p.lock((Object) null, continuation);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
            try {
                return function1.invoke(this.state);
            } finally {
                InlineMarker.finallyStart(1);
                access$getLock$p.unlock((Object) null);
                InlineMarker.finallyEnd(1);
            }
        }
    }
}
