package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00040\u0005B9\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u001e\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0017\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0002H\u0010¢\u0006\u0004\b\u0016\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0011H\u0016J'\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u001cH@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\"\u0010 \u001a\u00020\u00112\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\t2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00020\tR\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u001c\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000fX\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, mo27512d2 = {"Landroidx/paging/WrapperDataSource;", "Key", "", "ValueFrom", "ValueTo", "Landroidx/paging/DataSource;", "source", "listFunction", "Landroidx/arch/core/util/Function;", "", "(Landroidx/paging/DataSource;Landroidx/arch/core/util/Function;)V", "isInvalid", "", "()Z", "keyMap", "Ljava/util/IdentityHashMap;", "addInvalidatedCallback", "", "onInvalidatedCallback", "Landroidx/paging/DataSource$InvalidatedCallback;", "getKeyInternal", "item", "getKeyInternal$paging_common", "(Ljava/lang/Object;)Ljava/lang/Object;", "invalidate", "load", "Landroidx/paging/DataSource$BaseResult;", "params", "Landroidx/paging/DataSource$Params;", "load$paging_common", "(Landroidx/paging/DataSource$Params;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeInvalidatedCallback", "stashKeysIfNeeded", "dest", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: WrapperDataSource.kt */
public class WrapperDataSource<Key, ValueFrom, ValueTo> extends DataSource<Key, ValueTo> {
    private final IdentityHashMap<ValueTo, Key> keyMap;
    private final Function<List<ValueFrom>, List<ValueTo>> listFunction;
    private final DataSource<Key, ValueFrom> source;

    @Metadata(mo27510bv = {1, 0, 3}, mo27513k = 3, mo27514mv = {1, 4, 2})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DataSource.KeyType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[DataSource.KeyType.ITEM_KEYED.ordinal()] = 1;
        }
    }

    public Object load$paging_common(DataSource.Params<Key> params, Continuation<? super DataSource.BaseResult<ValueTo>> continuation) {
        return load$paging_common$suspendImpl(this, params, continuation);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WrapperDataSource(DataSource<Key, ValueFrom> dataSource, Function<List<ValueFrom>, List<ValueTo>> function) {
        super(dataSource.getType$paging_common());
        IdentityHashMap<ValueTo, Key> identityHashMap;
        Intrinsics.checkNotNullParameter(dataSource, "source");
        Intrinsics.checkNotNullParameter(function, "listFunction");
        this.source = dataSource;
        this.listFunction = function;
        if (WhenMappings.$EnumSwitchMapping$0[dataSource.getType$paging_common().ordinal()] != 1) {
            identityHashMap = null;
        } else {
            identityHashMap = new IdentityHashMap<>();
        }
        this.keyMap = identityHashMap;
    }

    public void addInvalidatedCallback(DataSource.InvalidatedCallback invalidatedCallback) {
        Intrinsics.checkNotNullParameter(invalidatedCallback, "onInvalidatedCallback");
        this.source.addInvalidatedCallback(invalidatedCallback);
    }

    public void removeInvalidatedCallback(DataSource.InvalidatedCallback invalidatedCallback) {
        Intrinsics.checkNotNullParameter(invalidatedCallback, "onInvalidatedCallback");
        this.source.removeInvalidatedCallback(invalidatedCallback);
    }

    public void invalidate() {
        this.source.invalidate();
    }

    public boolean isInvalid() {
        return this.source.isInvalid();
    }

    public Key getKeyInternal$paging_common(ValueTo valueto) {
        Key key;
        Intrinsics.checkNotNullParameter(valueto, "item");
        IdentityHashMap<ValueTo, Key> identityHashMap = this.keyMap;
        if (identityHashMap != null) {
            synchronized (identityHashMap) {
                key = this.keyMap.get(valueto);
                Intrinsics.checkNotNull(key);
            }
            return key;
        }
        throw new IllegalStateException("Cannot get key by item in non-item keyed DataSource");
    }

    public final void stashKeysIfNeeded(List<? extends ValueFrom> list, List<? extends ValueTo> list2) {
        Intrinsics.checkNotNullParameter(list, "source");
        Intrinsics.checkNotNullParameter(list2, "dest");
        IdentityHashMap<ValueTo, Key> identityHashMap = this.keyMap;
        if (identityHashMap != null) {
            synchronized (identityHashMap) {
                int i = 0;
                int size = list2.size();
                while (i < size) {
                    Map map = this.keyMap;
                    Object obj = list2.get(i);
                    DataSource<Key, ValueFrom> dataSource = this.source;
                    if (dataSource != null) {
                        map.put(obj, ((ItemKeyedDataSource) dataSource).getKey(list.get(i)));
                        i++;
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type androidx.paging.ItemKeyedDataSource<Key, ValueFrom>");
                    }
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object load$paging_common$suspendImpl(androidx.paging.WrapperDataSource r4, androidx.paging.DataSource.Params r5, kotlin.coroutines.Continuation r6) {
        /*
            boolean r0 = r6 instanceof androidx.paging.WrapperDataSource$load$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            androidx.paging.WrapperDataSource$load$1 r0 = (androidx.paging.WrapperDataSource$load$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            androidx.paging.WrapperDataSource$load$1 r0 = new androidx.paging.WrapperDataSource$load$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r4 = r0.L$0
            androidx.paging.WrapperDataSource r4 = (androidx.paging.WrapperDataSource) r4
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0046
        L_0x002e:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.paging.DataSource<Key, ValueFrom> r6 = r4.source
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r6.load$paging_common(r5, r0)
            if (r6 != r1) goto L_0x0046
            return r1
        L_0x0046:
            androidx.paging.DataSource$BaseResult r6 = (androidx.paging.DataSource.BaseResult) r6
            androidx.paging.DataSource$BaseResult$Companion r5 = androidx.paging.DataSource.BaseResult.Companion
            androidx.arch.core.util.Function<java.util.List<ValueFrom>, java.util.List<ValueTo>> r0 = r4.listFunction
            androidx.paging.DataSource$BaseResult r5 = r5.convert$paging_common(r6, r0)
            java.util.List<Value> r6 = r6.data
            java.util.List<Value> r0 = r5.data
            r4.stashKeysIfNeeded(r6, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.WrapperDataSource.load$paging_common$suspendImpl(androidx.paging.WrapperDataSource, androidx.paging.DataSource$Params, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
