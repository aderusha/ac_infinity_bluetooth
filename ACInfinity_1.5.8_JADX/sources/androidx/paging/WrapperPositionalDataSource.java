package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PositionalDataSource;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00030\u0004B3\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u001e\u0010\u0006\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\u001e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u0018H\u0016J\u001e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u001a2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00010\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\n\u001a\u00020\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR)\u0010\u0006\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, mo27512d2 = {"Landroidx/paging/WrapperPositionalDataSource;", "A", "", "B", "Landroidx/paging/PositionalDataSource;", "source", "listFunction", "Landroidx/arch/core/util/Function;", "", "(Landroidx/paging/PositionalDataSource;Landroidx/arch/core/util/Function;)V", "isInvalid", "", "()Z", "getListFunction", "()Landroidx/arch/core/util/Function;", "addInvalidatedCallback", "", "onInvalidatedCallback", "Landroidx/paging/DataSource$InvalidatedCallback;", "invalidate", "loadInitial", "params", "Landroidx/paging/PositionalDataSource$LoadInitialParams;", "callback", "Landroidx/paging/PositionalDataSource$LoadInitialCallback;", "loadRange", "Landroidx/paging/PositionalDataSource$LoadRangeParams;", "Landroidx/paging/PositionalDataSource$LoadRangeCallback;", "removeInvalidatedCallback", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: WrapperPositionalDataSource.kt */
public final class WrapperPositionalDataSource<A, B> extends PositionalDataSource<B> {
    private final Function<List<A>, List<B>> listFunction;
    private final PositionalDataSource<A> source;

    public final Function<List<A>, List<B>> getListFunction() {
        return this.listFunction;
    }

    public WrapperPositionalDataSource(PositionalDataSource<A> positionalDataSource, Function<List<A>, List<B>> function) {
        Intrinsics.checkNotNullParameter(positionalDataSource, "source");
        Intrinsics.checkNotNullParameter(function, "listFunction");
        this.source = positionalDataSource;
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

    public void loadInitial(PositionalDataSource.LoadInitialParams loadInitialParams, PositionalDataSource.LoadInitialCallback<B> loadInitialCallback) {
        Intrinsics.checkNotNullParameter(loadInitialParams, "params");
        Intrinsics.checkNotNullParameter(loadInitialCallback, "callback");
        this.source.loadInitial(loadInitialParams, new WrapperPositionalDataSource$loadInitial$1(this, loadInitialCallback));
    }

    public void loadRange(PositionalDataSource.LoadRangeParams loadRangeParams, PositionalDataSource.LoadRangeCallback<B> loadRangeCallback) {
        Intrinsics.checkNotNullParameter(loadRangeParams, "params");
        Intrinsics.checkNotNullParameter(loadRangeCallback, "callback");
        this.source.loadRange(loadRangeParams, new WrapperPositionalDataSource$loadRange$1(this, loadRangeCallback));
    }
}
