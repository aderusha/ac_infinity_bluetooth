package androidx.paging;

import androidx.paging.DataSource;
import androidx.paging.PagingSource;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \u001e*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0001\u001eB!\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\b¢\u0006\u0002\u0010\tJ#\u0010\u0012\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H\u0002J+\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001a2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020\u0011R \u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, mo27512d2 = {"Landroidx/paging/LegacyPagingSource;", "Key", "", "Value", "Landroidx/paging/PagingSource;", "fetchDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "dataSource", "Landroidx/paging/DataSource;", "(Lkotlinx/coroutines/CoroutineDispatcher;Landroidx/paging/DataSource;)V", "getDataSource$paging_common", "()Landroidx/paging/DataSource;", "jumpingSupported", "", "getJumpingSupported", "()Z", "pageSize", "", "getRefreshKey", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/PagingState;)Ljava/lang/Object;", "guessPageSize", "params", "Landroidx/paging/PagingSource$LoadParams;", "load", "Landroidx/paging/PagingSource$LoadResult;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setPageSize", "", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: LegacyPagingSource.kt */
public final class LegacyPagingSource<Key, Value> extends PagingSource<Key, Value> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int PAGE_SIZE_NOT_SET = Integer.MIN_VALUE;
    private final DataSource<Key, Value> dataSource;
    private final CoroutineDispatcher fetchDispatcher;
    private int pageSize = Integer.MIN_VALUE;

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DataSource.KeyType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[DataSource.KeyType.POSITIONAL.ordinal()] = 1;
            iArr[DataSource.KeyType.PAGE_KEYED.ordinal()] = 2;
            iArr[DataSource.KeyType.ITEM_KEYED.ordinal()] = 3;
        }
    }

    public final DataSource<Key, Value> getDataSource$paging_common() {
        return this.dataSource;
    }

    public LegacyPagingSource(CoroutineDispatcher coroutineDispatcher, DataSource<Key, Value> dataSource2) {
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "fetchDispatcher");
        Intrinsics.checkNotNullParameter(dataSource2, "dataSource");
        this.fetchDispatcher = coroutineDispatcher;
        this.dataSource = dataSource2;
        dataSource2.addInvalidatedCallback(new C0481xc5136f96(new Function0<Unit>(this) {
            public final void invoke() {
                ((LegacyPagingSource) this.receiver).invalidate();
            }
        }));
        registerInvalidatedCallback(new Function0<Unit>(this) {
            final /* synthetic */ LegacyPagingSource this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                this.this$0.getDataSource$paging_common().removeInvalidatedCallback(new C0481xc5136f96(new Function0<Unit>(this.this$0) {
                    public final void invoke() {
                        ((LegacyPagingSource) this.receiver).invalidate();
                    }
                }));
                this.this$0.getDataSource$paging_common().invalidate();
            }
        });
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, coroutineDispatcher, (CoroutineStart) null, new C04803(this, (Continuation) null), 2, (Object) null);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@¢\u0006\u0004\b\u0006\u0010\u0007"}, mo27512d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, mo27513k = 3, mo27514mv = {1, 4, 2})
    @DebugMetadata(mo28221c = "androidx.paging.LegacyPagingSource$3", mo28222f = "LegacyPagingSource.kt", mo28223i = {}, mo28224l = {}, mo28225m = "invokeSuspend", mo28226n = {}, mo28227s = {})
    /* renamed from: androidx.paging.LegacyPagingSource$3 */
    /* compiled from: LegacyPagingSource.kt */
    static final class C04803 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ LegacyPagingSource this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, "completion");
            return new C04803(this.this$0, continuation);
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((C04803) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!this.this$0.getInvalid() && this.this$0.getDataSource$paging_common().isInvalid()) {
                    this.this$0.invalidate();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final void setPageSize(int i) {
        int i2 = this.pageSize;
        if (i2 == Integer.MIN_VALUE || i == i2) {
            this.pageSize = i;
            return;
        }
        throw new IllegalStateException(("Page size is already set to " + this.pageSize + '.').toString());
    }

    private final int guessPageSize(PagingSource.LoadParams<Key> loadParams) {
        if (!(loadParams instanceof PagingSource.LoadParams.Refresh) || loadParams.getLoadSize() % 3 != 0) {
            return loadParams.getLoadSize();
        }
        return loadParams.getLoadSize() / 3;
    }

    public Object load(PagingSource.LoadParams<Key> loadParams, Continuation<? super PagingSource.LoadResult<Key, Value>> continuation) {
        LoadType loadType;
        if (loadParams instanceof PagingSource.LoadParams.Refresh) {
            loadType = LoadType.REFRESH;
        } else if (loadParams instanceof PagingSource.LoadParams.Append) {
            loadType = LoadType.APPEND;
        } else if (loadParams instanceof PagingSource.LoadParams.Prepend) {
            loadType = LoadType.PREPEND;
        } else {
            throw new NoWhenBranchMatchedException();
        }
        LoadType loadType2 = loadType;
        if (this.pageSize == Integer.MIN_VALUE) {
            System.out.println("WARNING: pageSize on the LegacyPagingSource is not set.\nWhen using legacy DataSource / DataSourceFactory with Paging3, page size\nshould've been set by the paging library but it is not set yet.\n\nIf you are seeing this message in tests where you are testing DataSource\nin isolation (without a Pager), it is expected and page size will be estimated\nbased on parameters.\n\nIf you are seeing this message despite using a Pager, please file a bug:\nhttps://issuetracker.google.com/issues/new?component=413106");
            this.pageSize = guessPageSize(loadParams);
        }
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = new DataSource.Params(loadType2, loadParams.getKey(), loadParams.getLoadSize(), loadParams.getPlaceholdersEnabled(), this.pageSize);
        return BuildersKt.withContext(this.fetchDispatcher, new LegacyPagingSource$load$2(this, objectRef, loadParams, (Continuation) null), continuation);
    }

    public Key getRefreshKey(PagingState<Key, Value> pagingState) {
        int i;
        Value closestItemToPosition;
        Intrinsics.checkNotNullParameter(pagingState, "state");
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.dataSource.getType$paging_common().ordinal()];
        if (i2 == 1) {
            Integer anchorPosition = pagingState.getAnchorPosition();
            if (anchorPosition == null) {
                return null;
            }
            int intValue = anchorPosition.intValue();
            int access$getLeadingPlaceholderCount$p = intValue - pagingState.leadingPlaceholderCount;
            int i3 = 0;
            while (i3 < CollectionsKt.getLastIndex(pagingState.getPages()) && access$getLeadingPlaceholderCount$p > CollectionsKt.getLastIndex(pagingState.getPages().get(i3).getData())) {
                access$getLeadingPlaceholderCount$p -= pagingState.getPages().get(i3).getData().size();
                i3++;
            }
            PagingSource.LoadResult.Page<Key, Value> closestPageToPosition = pagingState.closestPageToPosition(intValue);
            if (closestPageToPosition == null || (i = closestPageToPosition.getPrevKey()) == null) {
                i = 0;
            }
            Objects.requireNonNull(i, "null cannot be cast to non-null type kotlin.Int");
            return Integer.valueOf(((Integer) i).intValue() + access$getLeadingPlaceholderCount$p);
        } else if (i2 == 2) {
            return null;
        } else {
            if (i2 == 3) {
                Integer anchorPosition2 = pagingState.getAnchorPosition();
                if (anchorPosition2 == null || (closestItemToPosition = pagingState.closestItemToPosition(anchorPosition2.intValue())) == null) {
                    return null;
                }
                return this.dataSource.getKeyInternal$paging_common(closestItemToPosition);
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public boolean getJumpingSupported() {
        return this.dataSource.getType$paging_common() == DataSource.KeyType.POSITIONAL;
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo27512d2 = {"Landroidx/paging/LegacyPagingSource$Companion;", "", "()V", "PAGE_SIZE_NOT_SET", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: LegacyPagingSource.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
