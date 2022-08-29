package androidx.paging;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00050\u0004B\u001f\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004¢\u0006\u0002\u0010\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005H\u0002R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R.\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\t8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0011"}, mo27512d2 = {"Landroidx/paging/InvalidatingPagingSourceFactory;", "Key", "", "Value", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "pagingSourceFactory", "(Lkotlin/jvm/functions/Function0;)V", "pagingSources", "", "getPagingSources$paging_common$annotations", "()V", "getPagingSources$paging_common", "()Ljava/util/List;", "invalidate", "", "invoke", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: InvalidatingPagingSourceFactory.kt */
public final class InvalidatingPagingSourceFactory<Key, Value> implements Function0<PagingSource<Key, Value>> {
    private final Function0<PagingSource<Key, Value>> pagingSourceFactory;
    private final List<PagingSource<Key, Value>> pagingSources = new ArrayList();

    public static /* synthetic */ void getPagingSources$paging_common$annotations() {
    }

    public InvalidatingPagingSourceFactory(Function0<? extends PagingSource<Key, Value>> function0) {
        Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
        this.pagingSourceFactory = function0;
    }

    public final List<PagingSource<Key, Value>> getPagingSources$paging_common() {
        return this.pagingSources;
    }

    public PagingSource<Key, Value> invoke() {
        PagingSource<Key, Value> invoke = this.pagingSourceFactory.invoke();
        this.pagingSources.add(invoke);
        return invoke;
    }

    public final void invalidate() {
        while (!this.pagingSources.isEmpty()) {
            PagingSource pagingSource = (PagingSource) CollectionsKt.removeFirst(this.pagingSources);
            if (!pagingSource.getInvalid()) {
                pagingSource.invalidate();
            }
        }
    }
}
