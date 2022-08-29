package androidx.paging;

import androidx.paging.DataSource;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004\"\b\b\u0002\u0010\u0002*\u00020\u0004\"\b\b\u0003\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, mo27512d2 = {"<anonymous>", "Landroidx/paging/PagingSource;", "Key", "Value", "", "invoke"}, mo27513k = 3, mo27514mv = {1, 4, 2})
/* compiled from: DataSource.kt */
final class DataSource$Factory$asPagingSourceFactory$1 extends Lambda implements Function0<PagingSource<Key, Value>> {
    final /* synthetic */ CoroutineDispatcher $fetchDispatcher;
    final /* synthetic */ DataSource.Factory this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DataSource$Factory$asPagingSourceFactory$1(DataSource.Factory factory, CoroutineDispatcher coroutineDispatcher) {
        super(0);
        this.this$0 = factory;
        this.$fetchDispatcher = coroutineDispatcher;
    }

    public final PagingSource<Key, Value> invoke() {
        return new LegacyPagingSource<>(this.$fetchDispatcher, this.this$0.create());
    }
}
