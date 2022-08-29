package androidx.paging;

import androidx.arch.core.util.Function;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\b&\u0018\u0000 0*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0006/01234B\u000f\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010H\u0017J\u0017\u0010\u001c\u001a\u00028\u00002\u0006\u0010\u001d\u001a\u00028\u0001H ¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010 \u001a\u00020\u001aH\u0017J'\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000$H @ø\u0001\u0000¢\u0006\u0004\b%\u0010&J2\u0010'\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H(0\u0000\"\b\b\u0002\u0010(*\u00020\u00022\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H(0*H\u0016J2\u0010'\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H(0\u0000\"\b\b\u0002\u0010(*\u00020\u00022\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H(0+H\u0016J>\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H(0\u0000\"\b\b\u0002\u0010(*\u00020\u00022\u001e\u0010)\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010-\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0-0*H\u0016J>\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H(0\u0000\"\b\b\u0002\u0010(*\u00020\u00022\u001e\u0010)\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010-\u0012\n\u0012\b\u0012\u0004\u0012\u0002H(0-0+H\u0016J\u0010\u0010.\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010H\u0017R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8WX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\fR\"\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u0002\u0004\n\u0002\b\u0019¨\u00065"}, mo27512d2 = {"Landroidx/paging/DataSource;", "Key", "", "Value", "type", "Landroidx/paging/DataSource$KeyType;", "(Landroidx/paging/DataSource$KeyType;)V", "_invalid", "Ljava/util/concurrent/atomic/AtomicBoolean;", "isContiguous", "", "isContiguous$paging_common", "()Z", "isInvalid", "onInvalidatedCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Landroidx/paging/DataSource$InvalidatedCallback;", "getOnInvalidatedCallbacks$paging_common$annotations", "()V", "getOnInvalidatedCallbacks$paging_common", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "supportsPageDropping", "getSupportsPageDropping$paging_common", "getType$paging_common", "()Landroidx/paging/DataSource$KeyType;", "addInvalidatedCallback", "", "onInvalidatedCallback", "getKeyInternal", "item", "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Object;", "invalidate", "load", "Landroidx/paging/DataSource$BaseResult;", "params", "Landroidx/paging/DataSource$Params;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "map", "ToValue", "function", "Lkotlin/Function1;", "Landroidx/arch/core/util/Function;", "mapByPage", "", "removeInvalidatedCallback", "BaseResult", "Companion", "Factory", "InvalidatedCallback", "KeyType", "Params", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: DataSource.kt */
public abstract class DataSource<Key, Value> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final AtomicBoolean _invalid = new AtomicBoolean(false);
    private final boolean isContiguous = true;
    private final CopyOnWriteArrayList<InvalidatedCallback> onInvalidatedCallbacks = new CopyOnWriteArrayList<>();
    private final boolean supportsPageDropping = true;
    private final KeyType type;

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'¨\u0006\u0004"}, mo27512d2 = {"Landroidx/paging/DataSource$InvalidatedCallback;", "", "onInvalidated", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: DataSource.kt */
    public interface InvalidatedCallback {
        void onInvalidated();
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo27512d2 = {"Landroidx/paging/DataSource$KeyType;", "", "(Ljava/lang/String;I)V", "POSITIONAL", "PAGE_KEYED", "ITEM_KEYED", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: DataSource.kt */
    public enum KeyType {
        POSITIONAL,
        PAGE_KEYED,
        ITEM_KEYED
    }

    public static /* synthetic */ void getOnInvalidatedCallbacks$paging_common$annotations() {
    }

    public abstract Key getKeyInternal$paging_common(Value value);

    public abstract Object load$paging_common(Params<Key> params, Continuation<? super BaseResult<Value>> continuation);

    public DataSource(KeyType keyType) {
        Intrinsics.checkNotNullParameter(keyType, "type");
        this.type = keyType;
    }

    public final KeyType getType$paging_common() {
        return this.type;
    }

    public final CopyOnWriteArrayList<InvalidatedCallback> getOnInvalidatedCallbacks$paging_common() {
        return this.onInvalidatedCallbacks;
    }

    public boolean isInvalid() {
        return this._invalid.get();
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\b&\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\tH\u0007J\u0014\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u000bH&J2\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u0002H\r0\u0000\"\b\b\u0004\u0010\r*\u00020\u00022\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u0002H\r0\u000fH\u0016J2\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u0002H\r0\u0000\"\b\b\u0004\u0010\r*\u00020\u00022\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u0002H\r0\u0010H\u0016J>\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u0002H\r0\u0000\"\b\b\u0004\u0010\r*\u00020\u00022\u001e\u0010\u000e\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00120\u000fH\u0016J>\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u0002H\r0\u0000\"\b\b\u0004\u0010\r*\u00020\u00022\u001e\u0010\u000e\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00030\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\r0\u00120\u0010H\u0016¨\u0006\u0013"}, mo27512d2 = {"Landroidx/paging/DataSource$Factory;", "Key", "", "Value", "()V", "asPagingSourceFactory", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "fetchDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "create", "Landroidx/paging/DataSource;", "map", "ToValue", "function", "Lkotlin/Function1;", "Landroidx/arch/core/util/Function;", "mapByPage", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: DataSource.kt */
    public static abstract class Factory<Key, Value> {
        public final Function0<PagingSource<Key, Value>> asPagingSourceFactory() {
            return asPagingSourceFactory$default(this, (CoroutineDispatcher) null, 1, (Object) null);
        }

        public abstract DataSource<Key, Value> create();

        public <ToValue> Factory<Key, ToValue> map(Function<Value, ToValue> function) {
            Intrinsics.checkNotNullParameter(function, "function");
            return mapByPage(new DataSource$Factory$map$1(function));
        }

        public /* synthetic */ <ToValue> Factory<Key, ToValue> map(Function1<? super Value, ? extends ToValue> function1) {
            Intrinsics.checkNotNullParameter(function1, "function");
            return mapByPage(new DataSource$Factory$map$2(function1));
        }

        public <ToValue> Factory<Key, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function) {
            Intrinsics.checkNotNullParameter(function, "function");
            return new DataSource$Factory$mapByPage$1(this, function);
        }

        public /* synthetic */ <ToValue> Factory<Key, ToValue> mapByPage(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
            Intrinsics.checkNotNullParameter(function1, "function");
            return mapByPage(new DataSource$Factory$mapByPage$2(function1));
        }

        public static /* synthetic */ Function0 asPagingSourceFactory$default(Factory factory, CoroutineDispatcher coroutineDispatcher, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    coroutineDispatcher = Dispatchers.getIO();
                }
                return factory.asPagingSourceFactory(coroutineDispatcher);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: asPagingSourceFactory");
        }

        public final Function0<PagingSource<Key, Value>> asPagingSourceFactory(CoroutineDispatcher coroutineDispatcher) {
            Intrinsics.checkNotNullParameter(coroutineDispatcher, "fetchDispatcher");
            return new SuspendingPagingSourceFactory<>(coroutineDispatcher, new DataSource$Factory$asPagingSourceFactory$1(this, coroutineDispatcher));
        }
    }

    public <ToValue> DataSource<Key, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return new WrapperDataSource<>(this, function);
    }

    public /* synthetic */ <ToValue> DataSource<Key, ToValue> mapByPage(Function1<? super List<? extends Value>, ? extends List<? extends ToValue>> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return mapByPage(new DataSource$mapByPage$1(function1));
    }

    public <ToValue> DataSource<Key, ToValue> map(Function<Value, ToValue> function) {
        Intrinsics.checkNotNullParameter(function, "function");
        return mapByPage(new DataSource$map$1(function));
    }

    public /* synthetic */ <ToValue> DataSource<Key, ToValue> map(Function1<? super Value, ? extends ToValue> function1) {
        Intrinsics.checkNotNullParameter(function1, "function");
        return map(new DataSource$map$2(function1));
    }

    public boolean isContiguous$paging_common() {
        return this.isContiguous;
    }

    public boolean getSupportsPageDropping$paging_common() {
        return this.supportsPageDropping;
    }

    public void addInvalidatedCallback(InvalidatedCallback invalidatedCallback) {
        Intrinsics.checkNotNullParameter(invalidatedCallback, "onInvalidatedCallback");
        this.onInvalidatedCallbacks.add(invalidatedCallback);
    }

    public void removeInvalidatedCallback(InvalidatedCallback invalidatedCallback) {
        Intrinsics.checkNotNullParameter(invalidatedCallback, "onInvalidatedCallback");
        this.onInvalidatedCallbacks.remove(invalidatedCallback);
    }

    public void invalidate() {
        if (this._invalid.compareAndSet(false, true)) {
            for (InvalidatedCallback onInvalidated : this.onInvalidatedCallbacks) {
                onInvalidated.onInvalidated();
            }
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0000\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002B1\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0007¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0002¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, mo27512d2 = {"Landroidx/paging/DataSource$Params;", "K", "", "type", "Landroidx/paging/LoadType;", "key", "initialLoadSize", "", "placeholdersEnabled", "", "pageSize", "(Landroidx/paging/LoadType;Ljava/lang/Object;IZI)V", "getInitialLoadSize", "()I", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getPageSize", "getPlaceholdersEnabled", "()Z", "getType$paging_common", "()Landroidx/paging/LoadType;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: DataSource.kt */
    public static final class Params<K> {
        private final int initialLoadSize;
        private final K key;
        private final int pageSize;
        private final boolean placeholdersEnabled;
        private final LoadType type;

        public Params(LoadType loadType, K k, int i, boolean z, int i2) {
            Intrinsics.checkNotNullParameter(loadType, "type");
            this.type = loadType;
            this.key = k;
            this.initialLoadSize = i;
            this.placeholdersEnabled = z;
            this.pageSize = i2;
            if (loadType != LoadType.REFRESH && k == null) {
                throw new IllegalArgumentException("Key must be non-null for prepend/append");
            }
        }

        public final LoadType getType$paging_common() {
            return this.type;
        }

        public final K getKey() {
            return this.key;
        }

        public final int getInitialLoadSize() {
            return this.initialLoadSize;
        }

        public final boolean getPlaceholdersEnabled() {
            return this.placeholdersEnabled;
        }

        public final int getPageSize() {
            return this.pageSize;
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u0018*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002:\u0001\u0018B=\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002H\u0002J\u0015\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0000¢\u0006\u0002\b\u0017R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u0019"}, mo27512d2 = {"Landroidx/paging/DataSource$BaseResult;", "Value", "", "data", "", "prevKey", "nextKey", "itemsBefore", "", "itemsAfter", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;II)V", "getItemsAfter", "()I", "getItemsBefore", "getNextKey", "()Ljava/lang/Object;", "getPrevKey", "equals", "", "other", "validateForInitialTiling", "", "pageSize", "validateForInitialTiling$paging_common", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: DataSource.kt */
    public static final class BaseResult<Value> {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        public final List<Value> data;
        private final int itemsAfter;
        private final int itemsBefore;
        private final Object nextKey;
        private final Object prevKey;

        public BaseResult(List<? extends Value> list, Object obj, Object obj2, int i, int i2) {
            Intrinsics.checkNotNullParameter(list, "data");
            this.data = list;
            this.prevKey = obj;
            this.nextKey = obj2;
            this.itemsBefore = i;
            this.itemsAfter = i2;
            if (i < 0 && i != Integer.MIN_VALUE) {
                throw new IllegalArgumentException("Position must be non-negative");
            } else if (list.isEmpty() && (i > 0 || i2 > 0)) {
                throw new IllegalArgumentException("Initial result cannot be empty if items are present in data set.");
            } else if (i2 < 0 && i2 != Integer.MIN_VALUE) {
                throw new IllegalArgumentException("List size + position too large, last item in list beyond totalCount.");
            }
        }

        public final Object getPrevKey() {
            return this.prevKey;
        }

        public final Object getNextKey() {
            return this.nextKey;
        }

        public final int getItemsBefore() {
            return this.itemsBefore;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ BaseResult(List list, Object obj, Object obj2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(list, obj, obj2, (i3 & 8) != 0 ? Integer.MIN_VALUE : i, (i3 & 16) != 0 ? Integer.MIN_VALUE : i2);
        }

        public final int getItemsAfter() {
            return this.itemsAfter;
        }

        public final void validateForInitialTiling$paging_common(int i) {
            int i2;
            if (this.itemsBefore == Integer.MIN_VALUE || (i2 = this.itemsAfter) == Integer.MIN_VALUE) {
                throw new IllegalStateException("Placeholders requested, but totalCount not provided. Please call the three-parameter onResult method, or disable placeholders in the PagedList.Config");
            } else if (i2 > 0 && this.data.size() % i != 0) {
                throw new IllegalArgumentException("PositionalDataSource requires initial load size to be a multiple of page" + " size to support internal tiling. loadSize " + this.data.size() + ", position" + ' ' + this.itemsBefore + ", totalCount " + (this.itemsBefore + this.data.size() + this.itemsAfter) + ", pageSize " + i);
            } else if (this.itemsBefore % i != 0) {
                throw new IllegalArgumentException("Initial load must be pageSize aligned.Position = " + this.itemsBefore + ", pageSize =" + ' ' + i);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof BaseResult)) {
                return false;
            }
            BaseResult baseResult = (BaseResult) obj;
            if (!Intrinsics.areEqual((Object) this.data, (Object) baseResult.data) || !Intrinsics.areEqual(this.prevKey, baseResult.prevKey) || !Intrinsics.areEqual(this.nextKey, baseResult.nextKey) || this.itemsBefore != baseResult.itemsBefore || this.itemsAfter != baseResult.itemsAfter) {
                return false;
            }
            return true;
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JU\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0003\u0010\u0006*\u00020\u0001\"\b\b\u0004\u0010\u0005*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\u001e\u0010\b\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\n0\tH\u0000¢\u0006\u0002\b\u000bJ\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0004\"\b\b\u0003\u0010\r*\u00020\u0001H\u0000¢\u0006\u0002\b\u000e¨\u0006\u000f"}, mo27512d2 = {"Landroidx/paging/DataSource$BaseResult$Companion;", "", "()V", "convert", "Landroidx/paging/DataSource$BaseResult;", "Value", "ToValue", "result", "function", "Landroidx/arch/core/util/Function;", "", "convert$paging_common", "empty", "T", "empty$paging_common", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: DataSource.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final <T> BaseResult<T> empty$paging_common() {
                return new BaseResult(CollectionsKt.emptyList(), (Object) null, (Object) null, 0, 0);
            }

            public final <ToValue, Value> BaseResult<Value> convert$paging_common(BaseResult<ToValue> baseResult, Function<List<ToValue>, List<Value>> function) {
                Intrinsics.checkNotNullParameter(baseResult, "result");
                Intrinsics.checkNotNullParameter(function, "function");
                return new BaseResult(DataSource.Companion.convert$paging_common(function, baseResult.data), baseResult.getPrevKey(), baseResult.getNextKey(), baseResult.getItemsBefore(), baseResult.getItemsAfter());
            }
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JM\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0002\u0010\u0006\"\u0004\b\u0003\u0010\u00052\u001e\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00040\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004H\u0000¢\u0006\u0002\b\n¨\u0006\u000b"}, mo27512d2 = {"Landroidx/paging/DataSource$Companion;", "", "()V", "convert", "", "B", "A", "function", "Landroidx/arch/core/util/Function;", "source", "convert$paging_common", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: DataSource.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final <A, B> List<B> convert$paging_common(Function<List<A>, List<B>> function, List<? extends A> list) {
            Intrinsics.checkNotNullParameter(function, "function");
            Intrinsics.checkNotNullParameter(list, "source");
            List<B> apply = function.apply(list);
            if (apply.size() == list.size()) {
                Intrinsics.checkNotNullExpressionValue(apply, "dest");
                return apply;
            }
            throw new IllegalStateException("Invalid Function " + function + " changed return size. This is not supported.");
        }
    }
}
