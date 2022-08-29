package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b*\u0001'\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0004/012B\u0005¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001H&¢\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\n\u0010\bJ'\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H@ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J$\u0010\u0011\u001a\u00020\u00152\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0017H&J'\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012H@ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u0014J$\u0010\u0018\u001a\u00020\u00152\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u00122\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0017H&J'\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH@ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ$\u0010\u001a\u001a\u00020\u00152\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u001eH&J0\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0\u0000\"\b\b\u0002\u0010 *\u00020\u00022\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H 0\"J0\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0\u0000\"\b\b\u0002\u0010 *\u00020\u00022\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H 0#J<\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0\u0000\"\b\b\u0002\u0010 *\u00020\u00022\u001e\u0010!\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0%0\"J<\u0010$\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0\u0000\"\b\b\u0002\u0010 *\u00020\u00022\u001e\u0010!\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H 0%0#J)\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010'*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\f0(H\u0002¢\u0006\u0002\u0010)J\u001b\u0010*\u001a\u0004\u0018\u00018\u0000*\b\u0012\u0004\u0012\u00028\u00010%H\u0000¢\u0006\u0004\b+\u0010,J\u001b\u0010-\u001a\u0004\u0018\u00018\u0000*\b\u0012\u0004\u0012\u00028\u00010%H\u0000¢\u0006\u0004\b.\u0010,\u0002\u0004\n\u0002\b\u0019¨\u00063"}, mo27512d2 = {"Landroidx/paging/ItemKeyedDataSource;", "Key", "", "Value", "Landroidx/paging/DataSource;", "()V", "getKey", "item", "(Ljava/lang/Object;)Ljava/lang/Object;", "getKeyInternal", "getKeyInternal$paging_common", "load", "Landroidx/paging/DataSource$BaseResult;", "params", "Landroidx/paging/DataSource$Params;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadAfter", "Landroidx/paging/ItemKeyedDataSource$LoadParams;", "loadAfter$paging_common", "(Landroidx/paging/ItemKeyedDataSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "callback", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "loadBefore", "loadBefore$paging_common", "loadInitial", "Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "loadInitial$paging_common", "(Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "map", "ToValue", "function", "Lkotlin/Function1;", "Landroidx/arch/core/util/Function;", "mapByPage", "", "asCallback", "androidx/paging/ItemKeyedDataSource$asCallback$1", "Lkotlinx/coroutines/CancellableContinuation;", "(Lkotlinx/coroutines/CancellableContinuation;)Landroidx/paging/ItemKeyedDataSource$asCallback$1;", "getNextKey", "getNextKey$paging_common", "(Ljava/util/List;)Ljava/lang/Object;", "getPrevKey", "getPrevKey$paging_common", "LoadCallback", "LoadInitialCallback", "LoadInitialParams", "LoadParams", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
@Deprecated(message = "ItemKeyedDataSource is deprecated and has been replaced by PagingSource", replaceWith = @ReplaceWith(expression = "PagingSource<Key, Value>", imports = {"androidx.paging.PagingSource"}))
/* compiled from: ItemKeyedDataSource.kt */
public abstract class ItemKeyedDataSource<Key, Value> extends DataSource<Key, Value> {

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\b&\u0018\u0000*\u0004\b\u0002\u0010\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007H&¨\u0006\b"}, mo27512d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "Value", "", "()V", "onResult", "", "data", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: ItemKeyedDataSource.kt */
    public static abstract class LoadCallback<Value> {
        public abstract void onResult(List<? extends Value> list);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0005¢\u0006\u0002\u0010\u0003J&\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&¨\u0006\u000b"}, mo27512d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "Value", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "()V", "onResult", "", "data", "", "position", "", "totalCount", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: ItemKeyedDataSource.kt */
    public static abstract class LoadInitialCallback<Value> extends LoadCallback<Value> {
        public abstract void onResult(List<? extends Value> list, int i, int i2);
    }

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

    public abstract Key getKey(Value value);

    public abstract void loadAfter(LoadParams<Key> loadParams, LoadCallback<Value> loadCallback);

    public abstract void loadBefore(LoadParams<Key> loadParams, LoadCallback<Value> loadCallback);

    public abstract void loadInitial(LoadInitialParams<Key> loadInitialParams, LoadInitialCallback<Value> loadInitialCallback);

    public ItemKeyedDataSource() {
        super(DataSource.KeyType.ITEM_KEYED);
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002B\u001f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u0004\u0018\u00018\u00028\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, mo27512d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "Key", "", "requestedInitialKey", "requestedLoadSize", "", "placeholdersEnabled", "", "(Ljava/lang/Object;IZ)V", "Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: ItemKeyedDataSource.kt */
    public static class LoadInitialParams<Key> {
        public final boolean placeholdersEnabled;
        public final Key requestedInitialKey;
        public final int requestedLoadSize;

        public LoadInitialParams(Key key, int i, boolean z) {
            this.requestedInitialKey = key;
            this.requestedLoadSize = i;
            this.placeholdersEnabled = z;
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0003\u001a\u00028\u00028\u0006X\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, mo27512d2 = {"Landroidx/paging/ItemKeyedDataSource$LoadParams;", "Key", "", "key", "requestedLoadSize", "", "(Ljava/lang/Object;I)V", "Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: ItemKeyedDataSource.kt */
    public static class LoadParams<Key> {
        public final Key key;
        public final int requestedLoadSize;

        public LoadParams(Key key2, int i) {
            Intrinsics.checkNotNullParameter(key2, "key");
            this.key = key2;
            this.requestedLoadSize = i;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object load$paging_common(androidx.paging.DataSource.Params<Key> r7, kotlin.coroutines.Continuation<? super androidx.paging.DataSource.BaseResult<Value>> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof androidx.paging.ItemKeyedDataSource$load$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            androidx.paging.ItemKeyedDataSource$load$1 r0 = (androidx.paging.ItemKeyedDataSource$load$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            androidx.paging.ItemKeyedDataSource$load$1 r0 = new androidx.paging.ItemKeyedDataSource$load$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0040
            if (r2 == r5) goto L_0x003c
            if (r2 == r4) goto L_0x0038
            if (r2 != r3) goto L_0x0030
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x006e
        L_0x0030:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0038:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0090
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x00ad
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.paging.LoadType r8 = r7.getType$paging_common()
            int[] r2 = androidx.paging.ItemKeyedDataSource.WhenMappings.$EnumSwitchMapping$0
            int r8 = r8.ordinal()
            r8 = r2[r8]
            if (r8 == r5) goto L_0x0093
            if (r8 == r4) goto L_0x0077
            if (r8 != r3) goto L_0x0071
            androidx.paging.ItemKeyedDataSource$LoadParams r8 = new androidx.paging.ItemKeyedDataSource$LoadParams
            java.lang.Object r2 = r7.getKey()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r7 = r7.getPageSize()
            r8.<init>(r2, r7)
            r0.label = r3
            java.lang.Object r8 = r6.loadAfter$paging_common(r8, r0)
            if (r8 != r1) goto L_0x006e
            return r1
        L_0x006e:
            androidx.paging.DataSource$BaseResult r8 = (androidx.paging.DataSource.BaseResult) r8
            goto L_0x00af
        L_0x0071:
            kotlin.NoWhenBranchMatchedException r7 = new kotlin.NoWhenBranchMatchedException
            r7.<init>()
            throw r7
        L_0x0077:
            androidx.paging.ItemKeyedDataSource$LoadParams r8 = new androidx.paging.ItemKeyedDataSource$LoadParams
            java.lang.Object r2 = r7.getKey()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r7 = r7.getPageSize()
            r8.<init>(r2, r7)
            r0.label = r4
            java.lang.Object r8 = r6.loadBefore$paging_common(r8, r0)
            if (r8 != r1) goto L_0x0090
            return r1
        L_0x0090:
            androidx.paging.DataSource$BaseResult r8 = (androidx.paging.DataSource.BaseResult) r8
            goto L_0x00af
        L_0x0093:
            androidx.paging.ItemKeyedDataSource$LoadInitialParams r8 = new androidx.paging.ItemKeyedDataSource$LoadInitialParams
            java.lang.Object r2 = r7.getKey()
            int r3 = r7.getInitialLoadSize()
            boolean r7 = r7.getPlaceholdersEnabled()
            r8.<init>(r2, r3, r7)
            r0.label = r5
            java.lang.Object r8 = r6.loadInitial$paging_common(r8, r0)
            if (r8 != r1) goto L_0x00ad
            return r1
        L_0x00ad:
            androidx.paging.DataSource$BaseResult r8 = (androidx.paging.DataSource.BaseResult) r8
        L_0x00af:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.ItemKeyedDataSource.load$paging_common(androidx.paging.DataSource$Params, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Key getPrevKey$paging_common(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "$this$getPrevKey");
        Object firstOrNull = CollectionsKt.firstOrNull(list);
        if (firstOrNull != null) {
            return getKey(firstOrNull);
        }
        return null;
    }

    public final Key getNextKey$paging_common(List<? extends Value> list) {
        Intrinsics.checkNotNullParameter(list, "$this$getNextKey");
        Object lastOrNull = CollectionsKt.lastOrNull(list);
        if (lastOrNull != null) {
            return getKey(lastOrNull);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public final ItemKeyedDataSource$asCallback$1 asCallback(CancellableContinuation<? super DataSource.BaseResult<Value>> cancellableContinuation) {
        return new ItemKeyedDataSource$asCallback$1(this, cancellableContinuation);
    }

    public Key getKeyInternal$paging_common(Value value) {
        Intrinsics.checkNotNullParameter(value, "item");
        return getKey(value);
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperItemKeyedDataSource<>(this, function);
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mapByPage(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage((Function) new ItemKeyedDataSource$mapByPage$1(function1));
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> map(Function<Value, ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage((Function) new ItemKeyedDataSource$map$1(function));
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> map(Function1<? super Value, ? extends ToValue> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage((Function) new ItemKeyedDataSource$map$2(function1));
    }

    public final Object loadInitial$paging_common(LoadInitialParams<Key> loadInitialParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadInitial(loadInitialParams, new C0475xe515933(cancellableContinuationImpl, this, loadInitialParams));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object loadBefore$paging_common(LoadParams<Key> loadParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadBefore(loadParams, asCallback(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final Object loadAfter$paging_common(LoadParams<Key> loadParams, Continuation<? super DataSource.BaseResult<Value>> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        loadAfter(loadParams, asCallback(cancellableContinuationImpl));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
