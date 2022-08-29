package androidx.paging;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00050\u0004BY\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\f\u0012\u0018\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f0\u000e\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0002\u0010\u0013J\u0010\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0014J$\u0010\u001f\u001a\u00020\u00152\f\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005H\u0002R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000f0\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, mo27512d2 = {"Landroidx/paging/LivePagedList;", "Key", "", "Value", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "initialKey", "config", "Landroidx/paging/PagedList$Config;", "boundaryCallback", "Landroidx/paging/PagedList$BoundaryCallback;", "pagingSourceFactory", "Lkotlin/Function0;", "Landroidx/paging/PagingSource;", "notifyDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "fetchDispatcher", "(Lkotlinx/coroutines/CoroutineScope;Ljava/lang/Object;Landroidx/paging/PagedList$Config;Landroidx/paging/PagedList$BoundaryCallback;Lkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "callback", "", "currentData", "currentJob", "Lkotlinx/coroutines/Job;", "refreshRetryCallback", "Ljava/lang/Runnable;", "invalidate", "force", "", "onActive", "onItemUpdate", "previous", "next", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: LivePagedList.kt */
public final class LivePagedList<Key, Value> extends LiveData<PagedList<Value>> {
    /* access modifiers changed from: private */
    public final PagedList.BoundaryCallback<Value> boundaryCallback;
    /* access modifiers changed from: private */
    public final Function0<Unit> callback = new LivePagedList$callback$1(this);
    /* access modifiers changed from: private */
    public final PagedList.C0510Config config;
    /* access modifiers changed from: private */
    public final CoroutineScope coroutineScope;
    /* access modifiers changed from: private */
    public PagedList<Value> currentData;
    private Job currentJob;
    /* access modifiers changed from: private */
    public final CoroutineDispatcher fetchDispatcher;
    /* access modifiers changed from: private */
    public final CoroutineDispatcher notifyDispatcher;
    /* access modifiers changed from: private */
    public final Function0<PagingSource<Key, Value>> pagingSourceFactory;
    private final Runnable refreshRetryCallback;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LivePagedList(CoroutineScope coroutineScope2, Key key, PagedList.C0510Config config2, PagedList.BoundaryCallback<Value> boundaryCallback2, Function0<? extends PagingSource<Key, Value>> function0, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2) {
        super(new InitialPagedList(coroutineScope2, coroutineDispatcher, coroutineDispatcher2, config2, key));
        Intrinsics.checkNotNullParameter(coroutineScope2, "coroutineScope");
        Intrinsics.checkNotNullParameter(config2, "config");
        Intrinsics.checkNotNullParameter(function0, "pagingSourceFactory");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "notifyDispatcher");
        Intrinsics.checkNotNullParameter(coroutineDispatcher2, "fetchDispatcher");
        this.coroutineScope = coroutineScope2;
        this.config = config2;
        this.boundaryCallback = boundaryCallback2;
        this.pagingSourceFactory = function0;
        this.notifyDispatcher = coroutineDispatcher;
        this.fetchDispatcher = coroutineDispatcher2;
        Runnable livePagedList$refreshRetryCallback$1 = new LivePagedList$refreshRetryCallback$1(this);
        this.refreshRetryCallback = livePagedList$refreshRetryCallback$1;
        PagedList<Value> pagedList = (PagedList) getValue();
        Intrinsics.checkNotNull(pagedList);
        this.currentData = pagedList;
        pagedList.setRetryCallback(livePagedList$refreshRetryCallback$1);
    }

    /* access modifiers changed from: protected */
    public void onActive() {
        super.onActive();
        invalidate(false);
    }

    /* access modifiers changed from: private */
    public final void invalidate(boolean z) {
        Job job = this.currentJob;
        if (job == null || z) {
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.currentJob = BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, this.fetchDispatcher, (CoroutineStart) null, new LivePagedList$invalidate$1(this, (Continuation) null), 2, (Object) null);
        }
    }

    /* access modifiers changed from: private */
    public final void onItemUpdate(PagedList<Value> pagedList, PagedList<Value> pagedList2) {
        pagedList.setRetryCallback((Runnable) null);
        pagedList2.setRetryCallback(this.refreshRetryCallback);
    }
}
