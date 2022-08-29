package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002B5\b\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00018\u0000\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t0\b¢\u0006\u0002\u0010\nBK\b\u0007\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00018\u0000\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\f\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\t0\b¢\u0006\u0002\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, mo27512d2 = {"Landroidx/paging/Pager;", "Key", "", "Value", "config", "Landroidx/paging/PagingConfig;", "initialKey", "pagingSourceFactory", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "(Landroidx/paging/PagingConfig;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)V", "remoteMediator", "Landroidx/paging/RemoteMediator;", "(Landroidx/paging/PagingConfig;Ljava/lang/Object;Landroidx/paging/RemoteMediator;Lkotlin/jvm/functions/Function0;)V", "flow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "paging-common"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: Pager.kt */
public final class Pager<Key, Value> {
    private final Flow<PagingData<Value>> flow;

    public Pager(PagingConfig pagingConfig, Function0<? extends PagingSource<Key, Value>> function0) {
        this(pagingConfig, (Object) null, function0, 2, (DefaultConstructorMarker) null);
    }

    @ExperimentalPagingApi
    public Pager(PagingConfig pagingConfig, Key key, RemoteMediator<Key, Value> remoteMediator, Function0<? extends PagingSource<Key, Value>> function0) {
        Function1 function1;
        Intrinsics.checkNotNullParameter(pagingConfig, "config");
        Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
        if (function0 instanceof SuspendingPagingSourceFactory) {
            function1 = new Pager$flow$1(function0);
        } else {
            function1 = new Pager$flow$2(function0, (Continuation) null);
        }
        this.flow = new PageFetcher(function1, key, pagingConfig, remoteMediator).getFlow();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Pager(PagingConfig pagingConfig, Object obj, RemoteMediator remoteMediator, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(pagingConfig, (i & 2) != 0 ? null : obj, remoteMediator, function0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Pager(PagingConfig pagingConfig, Object obj, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(pagingConfig, (i & 2) != 0 ? null : obj, function0);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Pager(PagingConfig pagingConfig, Key key, Function0<? extends PagingSource<Key, Value>> function0) {
        this(pagingConfig, key, (RemoteMediator) null, function0);
        Intrinsics.checkNotNullParameter(pagingConfig, "config");
        Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
    }

    public final Flow<PagingData<Value>> getFlow() {
        return this.flow;
    }
}
