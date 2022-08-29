package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\b\b\u0002\u0010\u0004*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00040\u0005B9\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005\u0012\u001e\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J*\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0017H\u0016J*\u0010\u0018\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0017H\u0016J*\u0010\u0019\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u001a2\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR&\u0010\u0007\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\t\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo27512d2 = {"Landroidx/paging/WrapperPageKeyedDataSource;", "K", "", "A", "B", "Landroidx/paging/PageKeyedDataSource;", "source", "listFunction", "Landroidx/arch/core/util/Function;", "", "(Landroidx/paging/PageKeyedDataSource;Landroidx/arch/core/util/Function;)V", "isInvalid", "", "()Z", "addInvalidatedCallback", "", "onInvalidatedCallback", "Landroidx/paging/DataSource$InvalidatedCallback;", "invalidate", "loadAfter", "params", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "callback", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "removeInvalidatedCallback", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: WrapperPageKeyedDataSource.kt */
public final class WrapperPageKeyedDataSource<K, A, B> extends PageKeyedDataSource<K, B> {
    /* access modifiers changed from: private */
    public final Function<List<A>, List<B>> listFunction;
    private final PageKeyedDataSource<K, A> source;

    public WrapperPageKeyedDataSource(PageKeyedDataSource<K, A> pageKeyedDataSource, Function<List<A>, List<B>> function) {
        Intrinsics.checkNotNullParameter(pageKeyedDataSource, "source");
        Intrinsics.checkNotNullParameter(function, "listFunction");
        this.source = pageKeyedDataSource;
        this.listFunction = function;
    }

    public boolean isInvalid() {
        return this.source.isInvalid();
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

    public void loadInitial(PageKeyedDataSource.LoadInitialParams<K> loadInitialParams, PageKeyedDataSource.LoadInitialCallback<K, B> loadInitialCallback) {
        Intrinsics.checkNotNullParameter(loadInitialParams, "params");
        Intrinsics.checkNotNullParameter(loadInitialCallback, "callback");
        this.source.loadInitial(loadInitialParams, new WrapperPageKeyedDataSource$loadInitial$1(this, loadInitialCallback));
    }

    public void loadBefore(PageKeyedDataSource.LoadParams<K> loadParams, PageKeyedDataSource.LoadCallback<K, B> loadCallback) {
        Intrinsics.checkNotNullParameter(loadParams, "params");
        Intrinsics.checkNotNullParameter(loadCallback, "callback");
        this.source.loadBefore(loadParams, new WrapperPageKeyedDataSource$loadBefore$1(this, loadCallback));
    }

    public void loadAfter(PageKeyedDataSource.LoadParams<K> loadParams, PageKeyedDataSource.LoadCallback<K, B> loadCallback) {
        Intrinsics.checkNotNullParameter(loadParams, "params");
        Intrinsics.checkNotNullParameter(loadCallback, "callback");
        this.source.loadAfter(loadParams, new WrapperPageKeyedDataSource$loadAfter$1(this, loadCallback));
    }
}
