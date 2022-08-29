package androidx.paging;

import androidx.paging.PageKeyedDataSource;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004B\u0005¢\u0006\u0002\u0010\u0005J*\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000bH\u0016J*\u0010\f\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000bH\u0016J*\u0010\r\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\u0016¨\u0006\u0010"}, mo27512d2 = {"Landroidx/paging/InitialDataSource;", "K", "", "V", "Landroidx/paging/PageKeyedDataSource;", "()V", "loadAfter", "", "params", "Landroidx/paging/PageKeyedDataSource$LoadParams;", "callback", "Landroidx/paging/PageKeyedDataSource$LoadCallback;", "loadBefore", "loadInitial", "Landroidx/paging/PageKeyedDataSource$LoadInitialParams;", "Landroidx/paging/PageKeyedDataSource$LoadInitialCallback;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: InitialDataSource.kt */
public final class InitialDataSource<K, V> extends PageKeyedDataSource<K, V> {
    public void loadInitial(PageKeyedDataSource.LoadInitialParams<K> loadInitialParams, PageKeyedDataSource.LoadInitialCallback<K, V> loadInitialCallback) {
        Intrinsics.checkNotNullParameter(loadInitialParams, "params");
        Intrinsics.checkNotNullParameter(loadInitialCallback, "callback");
        loadInitialCallback.onResult(CollectionsKt.emptyList(), 0, 0, null, null);
    }

    public void loadBefore(PageKeyedDataSource.LoadParams<K> loadParams, PageKeyedDataSource.LoadCallback<K, V> loadCallback) {
        Intrinsics.checkNotNullParameter(loadParams, "params");
        Intrinsics.checkNotNullParameter(loadCallback, "callback");
        loadCallback.onResult(CollectionsKt.emptyList(), null);
    }

    public void loadAfter(PageKeyedDataSource.LoadParams<K> loadParams, PageKeyedDataSource.LoadCallback<K, V> loadCallback) {
        Intrinsics.checkNotNullParameter(loadParams, "params");
        Intrinsics.checkNotNullParameter(loadCallback, "callback");
        loadCallback.onResult(CollectionsKt.emptyList(), null);
    }
}
