package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.ItemKeyedDataSource;
import java.util.IdentityHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00040\u0005B9\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u001e\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00020\t2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\tJ\u0015\u0010\u0015\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0011H\u0016J$\u0010\u0019\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00020\u001dH\u0016J$\u0010\u001e\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00020\u001dH\u0016J$\u0010\u001f\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000 2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00000\u000fX\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, mo27512d2 = {"Landroidx/paging/WrapperItemKeyedDataSource;", "K", "", "A", "B", "Landroidx/paging/ItemKeyedDataSource;", "source", "listFunction", "Landroidx/arch/core/util/Function;", "", "(Landroidx/paging/ItemKeyedDataSource;Landroidx/arch/core/util/Function;)V", "isInvalid", "", "()Z", "keyMap", "Ljava/util/IdentityHashMap;", "addInvalidatedCallback", "", "onInvalidatedCallback", "Landroidx/paging/DataSource$InvalidatedCallback;", "convertWithStashedKeys", "getKey", "item", "(Ljava/lang/Object;)Ljava/lang/Object;", "invalidate", "loadAfter", "params", "Landroidx/paging/ItemKeyedDataSource$LoadParams;", "callback", "Landroidx/paging/ItemKeyedDataSource$LoadCallback;", "loadBefore", "loadInitial", "Landroidx/paging/ItemKeyedDataSource$LoadInitialParams;", "Landroidx/paging/ItemKeyedDataSource$LoadInitialCallback;", "removeInvalidatedCallback", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: WrapperItemKeyedDataSource.kt */
public final class WrapperItemKeyedDataSource<K, A, B> extends ItemKeyedDataSource<K, B> {
    private final IdentityHashMap<B, K> keyMap = new IdentityHashMap<>();
    private final Function<List<A>, List<B>> listFunction;
    private final ItemKeyedDataSource<K, A> source;

    public WrapperItemKeyedDataSource(ItemKeyedDataSource<K, A> itemKeyedDataSource, Function<List<A>, List<B>> function) {
        Intrinsics.checkNotNullParameter(itemKeyedDataSource, "source");
        Intrinsics.checkNotNullParameter(function, "listFunction");
        this.source = itemKeyedDataSource;
        this.listFunction = function;
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

    public final List<B> convertWithStashedKeys(List<? extends A> list) {
        Intrinsics.checkNotNullParameter(list, "source");
        List<B> convert$paging_common = DataSource.Companion.convert$paging_common(this.listFunction, list);
        synchronized (this.keyMap) {
            int size = convert$paging_common.size();
            for (int i = 0; i < size; i++) {
                this.keyMap.put(convert$paging_common.get(i), this.source.getKey(list.get(i)));
            }
            Unit unit = Unit.INSTANCE;
        }
        return convert$paging_common;
    }

    public void loadInitial(ItemKeyedDataSource.LoadInitialParams<K> loadInitialParams, ItemKeyedDataSource.LoadInitialCallback<B> loadInitialCallback) {
        Intrinsics.checkNotNullParameter(loadInitialParams, "params");
        Intrinsics.checkNotNullParameter(loadInitialCallback, "callback");
        this.source.loadInitial(loadInitialParams, new WrapperItemKeyedDataSource$loadInitial$1(this, loadInitialCallback));
    }

    public void loadAfter(ItemKeyedDataSource.LoadParams<K> loadParams, ItemKeyedDataSource.LoadCallback<B> loadCallback) {
        Intrinsics.checkNotNullParameter(loadParams, "params");
        Intrinsics.checkNotNullParameter(loadCallback, "callback");
        this.source.loadAfter(loadParams, new WrapperItemKeyedDataSource$loadAfter$1(this, loadCallback));
    }

    public void loadBefore(ItemKeyedDataSource.LoadParams<K> loadParams, ItemKeyedDataSource.LoadCallback<B> loadCallback) {
        Intrinsics.checkNotNullParameter(loadParams, "params");
        Intrinsics.checkNotNullParameter(loadCallback, "callback");
        this.source.loadBefore(loadParams, new WrapperItemKeyedDataSource$loadBefore$1(this, loadCallback));
    }

    public K getKey(B b) {
        K k;
        Intrinsics.checkNotNullParameter(b, "item");
        synchronized (this.keyMap) {
            k = this.keyMap.get(b);
            Intrinsics.checkNotNull(k);
        }
        return k;
    }
}
