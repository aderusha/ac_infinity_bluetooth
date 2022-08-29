package androidx.paging;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002:\u0002#$B\u0005¢\u0006\u0002\u0010\u0004J#\u0010\u0016\u001a\u0004\u0018\u00018\u00002\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0018H&¢\u0006\u0002\u0010\u0019J\u0006\u0010\u001a\u001a\u00020\u0012J+\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001eH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ\u0014\u0010 \u001a\u00020\u00122\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011J\u0014\u0010\"\u001a\u00020\u00122\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0014\u0010\r\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR(\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u00108\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0013\u0010\u0004\u001a\u0004\b\u0014\u0010\u0015\u0002\u0004\n\u0002\b\u0019¨\u0006%"}, mo27512d2 = {"Landroidx/paging/PagingSource;", "Key", "", "Value", "()V", "_invalid", "Ljava/util/concurrent/atomic/AtomicBoolean;", "invalid", "", "getInvalid", "()Z", "jumpingSupported", "getJumpingSupported", "keyReuseSupported", "getKeyReuseSupported", "onInvalidatedCallbacks", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function0;", "", "getOnInvalidatedCallbacks$paging_common$annotations", "getOnInvalidatedCallbacks$paging_common", "()Ljava/util/concurrent/CopyOnWriteArrayList;", "getRefreshKey", "state", "Landroidx/paging/PagingState;", "(Landroidx/paging/PagingState;)Ljava/lang/Object;", "invalidate", "load", "Landroidx/paging/PagingSource$LoadResult;", "params", "Landroidx/paging/PagingSource$LoadParams;", "(Landroidx/paging/PagingSource$LoadParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerInvalidatedCallback", "onInvalidatedCallback", "unregisterInvalidatedCallback", "LoadParams", "LoadResult", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PagingSource.kt */
public abstract class PagingSource<Key, Value> {
    private final AtomicBoolean _invalid = new AtomicBoolean(false);
    private final CopyOnWriteArrayList<Function0<Unit>> onInvalidatedCallbacks = new CopyOnWriteArrayList<>();

    public static /* synthetic */ void getOnInvalidatedCallbacks$paging_common$annotations() {
    }

    public boolean getJumpingSupported() {
        return false;
    }

    public boolean getKeyReuseSupported() {
        return false;
    }

    public abstract Key getRefreshKey(PagingState<Key, Value> pagingState);

    public abstract Object load(LoadParams<Key> loadParams, Continuation<? super LoadResult<Key, Value>> continuation);

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0010*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0002:\u0004\u000f\u0010\u0011\u0012B\u0017\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0014\u0010\b\u001a\u0004\u0018\u00018\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0001\u0003\u0013\u0014\u0015¨\u0006\u0016"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadParams;", "Key", "", "loadSize", "", "placeholdersEnabled", "", "(IZ)V", "key", "getKey", "()Ljava/lang/Object;", "getLoadSize", "()I", "getPlaceholdersEnabled", "()Z", "Append", "Companion", "Prepend", "Refresh", "Landroidx/paging/PagingSource$LoadParams$Refresh;", "Landroidx/paging/PagingSource$LoadParams$Append;", "Landroidx/paging/PagingSource$LoadParams$Prepend;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PagingSource.kt */
    public static abstract class LoadParams<Key> {
        public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
        private final int loadSize;
        private final boolean placeholdersEnabled;

        public abstract Key getKey();

        private LoadParams(int i, boolean z) {
            this.loadSize = i;
            this.placeholdersEnabled = z;
        }

        public /* synthetic */ LoadParams(int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, z);
        }

        public final int getLoadSize() {
            return this.loadSize;
        }

        public final boolean getPlaceholdersEnabled() {
            return this.placeholdersEnabled;
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001f\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0018\u0010\u0004\u001a\u0004\u0018\u00018\u0003X\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadParams$Refresh;", "Key", "", "Landroidx/paging/PagingSource$LoadParams;", "key", "loadSize", "", "placeholdersEnabled", "", "(Ljava/lang/Object;IZ)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: PagingSource.kt */
        public static final class Refresh<Key> extends LoadParams<Key> {
            private final Key key;

            public Key getKey() {
                return this.key;
            }

            public Refresh(Key key2, int i, boolean z) {
                super(i, z, (DefaultConstructorMarker) null);
                this.key = key2;
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00028\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0016\u0010\u0004\u001a\u00028\u0003X\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadParams$Append;", "Key", "", "Landroidx/paging/PagingSource$LoadParams;", "key", "loadSize", "", "placeholdersEnabled", "", "(Ljava/lang/Object;IZ)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: PagingSource.kt */
        public static final class Append<Key> extends LoadParams<Key> {
            private final Key key;

            public Key getKey() {
                return this.key;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Append(Key key2, int i, boolean z) {
                super(i, z, (DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(key2, "key");
                this.key = key2;
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000*\b\b\u0003\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00028\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0016\u0010\u0004\u001a\u00028\u0003X\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadParams$Prepend;", "Key", "", "Landroidx/paging/PagingSource$LoadParams;", "key", "loadSize", "", "placeholdersEnabled", "", "(Ljava/lang/Object;IZ)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: PagingSource.kt */
        public static final class Prepend<Key> extends LoadParams<Key> {
            private final Key key;

            public Key getKey() {
                return this.key;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Prepend(Key key2, int i, boolean z) {
                super(i, z, (DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(key2, "key");
                this.key = key2;
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J=\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0003\u0010\u0005*\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u0001H\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\r¨\u0006\u000e"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadParams$Companion;", "", "()V", "create", "Landroidx/paging/PagingSource$LoadParams;", "Key", "loadType", "Landroidx/paging/LoadType;", "key", "loadSize", "", "placeholdersEnabled", "", "(Landroidx/paging/LoadType;Ljava/lang/Object;IZ)Landroidx/paging/PagingSource$LoadParams;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: PagingSource.kt */
        public static final class Companion {

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

            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final <Key> LoadParams<Key> create(LoadType loadType, Key key, int i, boolean z) {
                Intrinsics.checkNotNullParameter(loadType, "loadType");
                int i2 = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
                if (i2 == 1) {
                    return new Refresh<>(key, i, z);
                }
                if (i2 != 2) {
                    if (i2 != 3) {
                        throw new NoWhenBranchMatchedException();
                    } else if (key != null) {
                        return new Append<>(key, i, z);
                    } else {
                        throw new IllegalArgumentException("key cannot be null for append".toString());
                    }
                } else if (key != null) {
                    return new Prepend<>(key, i, z);
                } else {
                    throw new IllegalArgumentException("key cannot be null for prepend".toString());
                }
            }
        }
    }

    @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u0002*\b\b\u0003\u0010\u0003*\u00020\u00022\u00020\u0002:\u0002\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0004\u0001\u0002\u0007\b¨\u0006\t"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadResult;", "Key", "", "Value", "()V", "Error", "Page", "Landroidx/paging/PagingSource$LoadResult$Error;", "Landroidx/paging/PagingSource$LoadResult$Page;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
    /* compiled from: PagingSource.kt */
    public static abstract class LoadResult<Key, Value> {
        private LoadResult() {
        }

        public /* synthetic */ LoadResult(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\b\b\u0004\u0010\u0001*\u00020\u0002*\b\b\u0005\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B\r\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u0006HÆ\u0003J\u001f\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0013"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadResult$Error;", "Key", "", "Value", "Landroidx/paging/PagingSource$LoadResult;", "throwable", "", "(Ljava/lang/Throwable;)V", "getThrowable", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: PagingSource.kt */
        public static final class Error<Key, Value> extends LoadResult<Key, Value> {
            private final Throwable throwable;

            public static /* synthetic */ Error copy$default(Error error, Throwable th, int i, Object obj) {
                if ((i & 1) != 0) {
                    th = error.throwable;
                }
                return error.copy(th);
            }

            public final Throwable component1() {
                return this.throwable;
            }

            public final Error<Key, Value> copy(Throwable th) {
                Intrinsics.checkNotNullParameter(th, "throwable");
                return new Error<>(th);
            }

            public boolean equals(Object obj) {
                if (this != obj) {
                    return (obj instanceof Error) && Intrinsics.areEqual((Object) this.throwable, (Object) ((Error) obj).throwable);
                }
                return true;
            }

            public int hashCode() {
                Throwable th = this.throwable;
                if (th != null) {
                    return th.hashCode();
                }
                return 0;
            }

            public String toString() {
                return "Error(throwable=" + this.throwable + ")";
            }

            public final Throwable getThrowable() {
                return this.throwable;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Error(Throwable th) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(th, "throwable");
                this.throwable = th;
            }
        }

        @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 $*\b\b\u0004\u0010\u0001*\u00020\u0002*\b\b\u0005\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0001$B)\b\u0016\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00050\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00018\u0004¢\u0006\u0002\u0010\tB;\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00050\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00018\u0004\u0012\b\b\u0003\u0010\n\u001a\u00020\u000b\u0012\b\b\u0003\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00050\u0006HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00018\u0004HÆ\u0003¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0019\u001a\u0004\u0018\u00018\u0004HÆ\u0003¢\u0006\u0002\u0010\u0014J\t\u0010\u001a\u001a\u00020\u000bHÆ\u0003J\t\u0010\u001b\u001a\u00020\u000bHÆ\u0003JV\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00028\u0004\u0012\u0004\u0012\u00028\u00050\u00002\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00050\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00018\u00042\b\b\u0003\u0010\n\u001a\u00020\u000b2\b\b\u0003\u0010\f\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010\u001dJ\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010!\u001a\u00020\u000bHÖ\u0001J\t\u0010\"\u001a\u00020#HÖ\u0001R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00050\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0015\u0010\b\u001a\u0004\u0018\u00018\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0004¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0016\u0010\u0014¨\u0006%"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadResult$Page;", "Key", "", "Value", "Landroidx/paging/PagingSource$LoadResult;", "data", "", "prevKey", "nextKey", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;)V", "itemsBefore", "", "itemsAfter", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;II)V", "getData", "()Ljava/util/List;", "getItemsAfter", "()I", "getItemsBefore", "getNextKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getPrevKey", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/util/List;Ljava/lang/Object;Ljava/lang/Object;II)Landroidx/paging/PagingSource$LoadResult$Page;", "equals", "", "other", "hashCode", "toString", "", "Companion", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
        /* compiled from: PagingSource.kt */
        public static final class Page<Key, Value> extends LoadResult<Key, Value> {
            public static final int COUNT_UNDEFINED = Integer.MIN_VALUE;
            public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
            /* access modifiers changed from: private */
            public static final Page EMPTY = new Page(CollectionsKt.emptyList(), (Object) null, (Object) null, 0, 0);
            private final List<Value> data;
            private final int itemsAfter;
            private final int itemsBefore;
            private final Key nextKey;
            private final Key prevKey;

            public static /* synthetic */ Page copy$default(Page page, List<Value> list, Key key, Key key2, int i, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    list = page.data;
                }
                if ((i3 & 2) != 0) {
                    key = page.prevKey;
                }
                Key key3 = key;
                if ((i3 & 4) != 0) {
                    key2 = page.nextKey;
                }
                Key key4 = key2;
                if ((i3 & 8) != 0) {
                    i = page.itemsBefore;
                }
                int i4 = i;
                if ((i3 & 16) != 0) {
                    i2 = page.itemsAfter;
                }
                return page.copy(list, key3, key4, i4, i2);
            }

            public final List<Value> component1() {
                return this.data;
            }

            public final Key component2() {
                return this.prevKey;
            }

            public final Key component3() {
                return this.nextKey;
            }

            public final int component4() {
                return this.itemsBefore;
            }

            public final int component5() {
                return this.itemsAfter;
            }

            public final Page<Key, Value> copy(List<? extends Value> list, Key key, Key key2, int i, int i2) {
                Intrinsics.checkNotNullParameter(list, "data");
                return new Page(list, key, key2, i, i2);
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof Page)) {
                    return false;
                }
                Page page = (Page) obj;
                return Intrinsics.areEqual((Object) this.data, (Object) page.data) && Intrinsics.areEqual((Object) this.prevKey, (Object) page.prevKey) && Intrinsics.areEqual((Object) this.nextKey, (Object) page.nextKey) && this.itemsBefore == page.itemsBefore && this.itemsAfter == page.itemsAfter;
            }

            public int hashCode() {
                List<Value> list = this.data;
                int i = 0;
                int hashCode = (list != null ? list.hashCode() : 0) * 31;
                Key key = this.prevKey;
                int hashCode2 = (hashCode + (key != null ? key.hashCode() : 0)) * 31;
                Key key2 = this.nextKey;
                if (key2 != null) {
                    i = key2.hashCode();
                }
                return ((((hashCode2 + i) * 31) + this.itemsBefore) * 31) + this.itemsAfter;
            }

            public String toString() {
                return "Page(data=" + this.data + ", prevKey=" + this.prevKey + ", nextKey=" + this.nextKey + ", itemsBefore=" + this.itemsBefore + ", itemsAfter=" + this.itemsAfter + ")";
            }

            public final List<Value> getData() {
                return this.data;
            }

            public final Key getPrevKey() {
                return this.prevKey;
            }

            public final Key getNextKey() {
                return this.nextKey;
            }

            public final int getItemsBefore() {
                return this.itemsBefore;
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public /* synthetic */ Page(List list, Object obj, Object obj2, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(list, obj, obj2, (i3 & 8) != 0 ? Integer.MIN_VALUE : i, (i3 & 16) != 0 ? Integer.MIN_VALUE : i2);
            }

            public final int getItemsAfter() {
                return this.itemsAfter;
            }

            /* JADX INFO: super call moved to the top of the method (can break code semantics) */
            public Page(List<? extends Value> list, Key key, Key key2, int i, int i2) {
                super((DefaultConstructorMarker) null);
                Intrinsics.checkNotNullParameter(list, "data");
                this.data = list;
                this.prevKey = key;
                this.nextKey = key2;
                this.itemsBefore = i;
                this.itemsAfter = i2;
                boolean z = false;
                if (i == Integer.MIN_VALUE || i >= 0) {
                    if (!((i2 == Integer.MIN_VALUE || i2 >= 0) ? true : z)) {
                        throw new IllegalArgumentException("itemsAfter cannot be negative".toString());
                    }
                    return;
                }
                throw new IllegalArgumentException("itemsBefore cannot be negative".toString());
            }

            /* JADX INFO: this call moved to the top of the method (can break code semantics) */
            public Page(List<? extends Value> list, Key key, Key key2) {
                this(list, key, key2, Integer.MIN_VALUE, Integer.MIN_VALUE);
                Intrinsics.checkNotNullParameter(list, "data");
            }

            @Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u0006\"\b\b\u0006\u0010\f*\u00020\u0001\"\b\b\u0007\u0010\r*\u00020\u0001H\u0000¢\u0006\u0002\b\u000eR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R&\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, mo27512d2 = {"Landroidx/paging/PagingSource$LoadResult$Page$Companion;", "", "()V", "COUNT_UNDEFINED", "", "EMPTY", "Landroidx/paging/PagingSource$LoadResult$Page;", "", "getEMPTY$paging_common$annotations", "getEMPTY$paging_common", "()Landroidx/paging/PagingSource$LoadResult$Page;", "empty", "Key", "Value", "empty$paging_common", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
            /* compiled from: PagingSource.kt */
            public static final class Companion {
                public static /* synthetic */ void getEMPTY$paging_common$annotations() {
                }

                private Companion() {
                }

                public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                    this();
                }

                public final Page getEMPTY$paging_common() {
                    return Page.EMPTY;
                }

                public final <Key, Value> Page<Key, Value> empty$paging_common() {
                    Page<Key, Value> eMPTY$paging_common = getEMPTY$paging_common();
                    Objects.requireNonNull(eMPTY$paging_common, "null cannot be cast to non-null type androidx.paging.PagingSource.LoadResult.Page<Key, Value>");
                    return eMPTY$paging_common;
                }
            }
        }
    }

    public final CopyOnWriteArrayList<Function0<Unit>> getOnInvalidatedCallbacks$paging_common() {
        return this.onInvalidatedCallbacks;
    }

    public final boolean getInvalid() {
        return this._invalid.get();
    }

    public final void invalidate() {
        if (this._invalid.compareAndSet(false, true)) {
            for (Function0 invoke : this.onInvalidatedCallbacks) {
                invoke.invoke();
            }
        }
    }

    public final void registerInvalidatedCallback(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "onInvalidatedCallback");
        this.onInvalidatedCallbacks.add(function0);
    }

    public final void unregisterInvalidatedCallback(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "onInvalidatedCallback");
        this.onInvalidatedCallbacks.remove(function0);
    }
}
