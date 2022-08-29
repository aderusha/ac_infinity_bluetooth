package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\b'\u0018\u0000 '*\b\b\u0000\u0010\u0001*\u00020\u00022\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0003:\u0005'()*+B\u0005¢\u0006\u0002\u0010\u0005J\u0017\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\r\u0010\u000eJ'\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0012H@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\u0011\u001a\u00020\u0016H@ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001e\u0010\u0015\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u00162\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH'J\u001f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\u0011\u001a\u00020\u001dH@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ\u001e\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0011\u001a\u00020\u001d2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001fH'J*\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\u0000\"\b\b\u0001\u0010!*\u00020\u00022\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H!0#J*\u0010 \u001a\b\u0012\u0004\u0012\u0002H!0\u0000\"\b\b\u0001\u0010!*\u00020\u00022\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H!0$J6\u0010%\u001a\b\u0012\u0004\u0012\u0002H!0\u0000\"\b\b\u0001\u0010!*\u00020\u00022\u001e\u0010\"\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000&\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0&0#J6\u0010%\u001a\b\u0012\u0004\u0012\u0002H!0\u0000\"\b\b\u0001\u0010!*\u00020\u00022\u001e\u0010\"\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000&\u0012\n\u0012\b\u0012\u0004\u0012\u0002H!0&0$R\u001a\u0010\u0006\u001a\u00020\u0007XD¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0005\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, mo27512d2 = {"Landroidx/paging/PositionalDataSource;", "T", "", "Landroidx/paging/DataSource;", "", "()V", "isContiguous", "", "isContiguous$paging_common$annotations", "isContiguous$paging_common", "()Z", "getKeyInternal", "item", "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Integer;", "load", "Landroidx/paging/DataSource$BaseResult;", "params", "Landroidx/paging/DataSource$Params;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadInitial", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "loadInitial$paging_common", "(Landroidx/paging/PositionalDataSource$LoadInitialParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "callback", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "loadRange", "Landroidx/paging/PositionalDataSource$LoadRangeParams;", "(Landroidx/paging/PositionalDataSource$LoadRangeParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "map", "V", "function", "Lkotlin/Function1;", "Landroidx/arch/core/util/Function;", "mapByPage", "", "Companion", "LoadInitialCallback", "LoadInitialParams", "LoadRangeCallback", "LoadRangeParams", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
@Deprecated(message = "PositionalDataSource is deprecated and has been replaced by PagingSource", replaceWith = @ReplaceWith(expression = "PagingSource<Int, T>", imports = {"androidx.paging.PagingSource"}))
/* compiled from: PositionalDataSource.kt */
public abstract class PositionalDataSource<T> extends DataSource<Integer, T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean isContiguous;

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J&\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&¨\u0006\u000b"}, mo27512d2 = {"Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "T", "", "()V", "onResult", "", "data", "", "position", "", "totalCount", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PositionalDataSource.kt */
    public static abstract class LoadInitialCallback<T> {
        public abstract void onResult(List<? extends T> list, int i);

        public abstract void onResult(List<? extends T> list, int i, int i2);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\b&\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007H&¨\u0006\b"}, mo27512d2 = {"Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "T", "", "()V", "onResult", "", "data", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PositionalDataSource.kt */
    public static abstract class LoadRangeCallback<T> {
        public abstract void onResult(List<? extends T> list);
    }

    @JvmStatic
    public static final int computeInitialLoadPosition(LoadInitialParams loadInitialParams, int i) {
        return Companion.computeInitialLoadPosition(loadInitialParams, i);
    }

    @JvmStatic
    public static final int computeInitialLoadSize(LoadInitialParams loadInitialParams, int i, int i2) {
        return Companion.computeInitialLoadSize(loadInitialParams, i, i2);
    }

    public static /* synthetic */ void isContiguous$paging_common$annotations() {
    }

    public abstract void loadInitial(LoadInitialParams loadInitialParams, LoadInitialCallback<T> loadInitialCallback);

    public abstract void loadRange(LoadRangeParams loadRangeParams, LoadRangeCallback<T> loadRangeCallback);

    public PositionalDataSource() {
        super(DataSource.KeyType.POSITIONAL);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0010\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, mo27512d2 = {"Landroidx/paging/PositionalDataSource$LoadInitialParams;", "", "requestedStartPosition", "", "requestedLoadSize", "pageSize", "placeholdersEnabled", "", "(IIIZ)V", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PositionalDataSource.kt */
    public static class LoadInitialParams {
        public final int pageSize;
        public final boolean placeholdersEnabled;
        public final int requestedLoadSize;
        public final int requestedStartPosition;

        public LoadInitialParams(int i, int i2, int i3, boolean z) {
            this.requestedStartPosition = i;
            this.requestedLoadSize = i2;
            this.pageSize = i3;
            this.placeholdersEnabled = z;
            boolean z2 = true;
            if (i >= 0) {
                if (i2 >= 0) {
                    if (!(i3 < 0 ? false : z2)) {
                        throw new IllegalStateException(("invalid page size: " + i3).toString());
                    }
                    return;
                }
                throw new IllegalStateException(("invalid load size: " + i2).toString());
            }
            throw new IllegalStateException(("invalid start position: " + i).toString());
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo27512d2 = {"Landroidx/paging/PositionalDataSource$LoadRangeParams;", "", "startPosition", "", "loadSize", "(II)V", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PositionalDataSource.kt */
    public static class LoadRangeParams {
        public final int loadSize;
        public final int startPosition;

        public LoadRangeParams(int i, int i2) {
            this.startPosition = i;
            this.loadSize = i2;
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0007J \u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¨\u0006\n"}, mo27512d2 = {"Landroidx/paging/PositionalDataSource$Companion;", "", "()V", "computeInitialLoadPosition", "", "params", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "totalCount", "computeInitialLoadSize", "initialLoadPosition", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PositionalDataSource.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final int computeInitialLoadPosition(LoadInitialParams loadInitialParams, int i) {
            Intrinsics.checkNotNullParameter(loadInitialParams, "params");
            int i2 = loadInitialParams.requestedStartPosition;
            int i3 = loadInitialParams.requestedLoadSize;
            int i4 = loadInitialParams.pageSize;
            return Math.max(0, Math.min(((((i - i3) + i4) - 1) / i4) * i4, (i2 / i4) * i4));
        }

        @JvmStatic
        public final int computeInitialLoadSize(LoadInitialParams loadInitialParams, int i, int i2) {
            Intrinsics.checkNotNullParameter(loadInitialParams, "params");
            return Math.min(i2 - i, loadInitialParams.requestedLoadSize);
        }
    }

    public final Object load$paging_common(DataSource.Params<Integer> params, Continuation<? super DataSource.BaseResult<T>> continuation) {
        if (params.getType$paging_common() == LoadType.REFRESH) {
            int initialLoadSize = params.getInitialLoadSize();
            int i = 0;
            if (params.getKey() != null) {
                int intValue = params.getKey().intValue();
                if (params.getPlaceholdersEnabled()) {
                    initialLoadSize = Math.max(initialLoadSize / params.getPageSize(), 2) * params.getPageSize();
                    i = Math.max(0, ((intValue - (initialLoadSize / 2)) / params.getPageSize()) * params.getPageSize());
                } else {
                    i = Math.max(0, intValue - (initialLoadSize / 2));
                }
            }
            return loadInitial$paging_common(new LoadInitialParams(i, initialLoadSize, params.getPageSize(), params.getPlaceholdersEnabled()), continuation);
        }
        Integer key = params.getKey();
        Intrinsics.checkNotNull(key);
        int intValue2 = key.intValue();
        int pageSize = params.getPageSize();
        if (params.getType$paging_common() == LoadType.PREPEND) {
            pageSize = Math.min(pageSize, intValue2);
            intValue2 -= pageSize;
        }
        return loadRange(new LoadRangeParams(intValue2, pageSize), continuation);
    }

    public boolean isContiguous$paging_common() {
        return this.isContiguous;
    }

    public final Integer getKeyInternal$paging_common(T t) {
        Intrinsics.checkNotNullParameter(t, "item");
        throw new IllegalStateException("Cannot get key by item in positionalDataSource");
    }

    public final <V> PositionalDataSource<V> mapByPage(Function<List<T>, List<V>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperPositionalDataSource<>(this, function);
    }

    public final <V> PositionalDataSource<V> mapByPage(Function1<? super List<? extends T>, ? extends List<? extends V>> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage((Function) new PositionalDataSource$mapByPage$1(function1));
    }

    public final <V> PositionalDataSource<V> map(Function<T, V> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new PositionalDataSource$map$1(function));
    }

    public final <V> PositionalDataSource<V> map(Function1<? super T, ? extends V> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage((Function) new PositionalDataSource$map$2(function1));
    }

    public final Object loadInitial$paging_common(LoadInitialParams loadInitialParams, Continuation<? super DataSource.BaseResult<T>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadInitial(loadInitialParams, new C0555x9194581c(cancellableContinuationImpl, this, loadInitialParams));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ Object loadRange(LoadRangeParams loadRangeParams, Continuation<? super DataSource.BaseResult<T>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadRange(loadRangeParams, new C0556xfa338615(cancellableContinuationImpl, this, loadRangeParams));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
