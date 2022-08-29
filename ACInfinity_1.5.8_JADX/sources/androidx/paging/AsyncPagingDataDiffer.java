package androidx.paging;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleKt;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000u\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\f\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B1\b\u0007\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\nJ\u001a\u0010&\u001a\u00020'2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020'0)J\u0017\u0010*\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010+\u001a\u00020\u001c¢\u0006\u0002\u0010,J\u0017\u0010-\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010+\u001a\u00020\u001c¢\u0006\u0002\u0010,J\u0006\u0010.\u001a\u00020'J\u001a\u0010/\u001a\u00020'2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020'0)J\u0006\u00100\u001a\u00020'J\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u000002J\u001c\u00103\u001a\u00020'2\u0006\u00104\u001a\u0002052\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u000007J\u001f\u00103\u001a\u00020'2\f\u00106\u001a\b\u0012\u0004\u0012\u00028\u000007H@ø\u0001\u0000¢\u0006\u0002\u00108R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R \u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 ¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00069"}, mo27512d2 = {"Landroidx/paging/AsyncPagingDataDiffer;", "T", "", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "updateCallback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "workerDispatcher", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Landroidx/recyclerview/widget/ListUpdateCallback;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "differBase", "androidx/paging/AsyncPagingDataDiffer$differBase$1", "Landroidx/paging/AsyncPagingDataDiffer$differBase$1;", "differCallback", "Landroidx/paging/DifferCallback;", "getDifferCallback$paging_runtime_release$annotations", "()V", "getDifferCallback$paging_runtime_release", "()Landroidx/paging/DifferCallback;", "inGetItem", "", "getInGetItem$paging_runtime_release$annotations", "getInGetItem$paging_runtime_release", "()Z", "setInGetItem$paging_runtime_release", "(Z)V", "itemCount", "", "getItemCount", "()I", "loadStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/CombinedLoadStates;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "submitDataId", "Ljava/util/concurrent/atomic/AtomicInteger;", "addLoadStateListener", "", "listener", "Lkotlin/Function1;", "getItem", "index", "(I)Ljava/lang/Object;", "peek", "refresh", "removeLoadStateListener", "retry", "snapshot", "Landroidx/paging/ItemSnapshotList;", "submitData", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "pagingData", "Landroidx/paging/PagingData;", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: AsyncPagingDataDiffer.kt */
public final class AsyncPagingDataDiffer<T> {
    /* access modifiers changed from: private */
    public final DiffUtil.ItemCallback<T> diffCallback;
    /* access modifiers changed from: private */
    public final AsyncPagingDataDiffer$differBase$1 differBase;
    private final DifferCallback differCallback;
    private boolean inGetItem;
    private final Flow<CombinedLoadStates> loadStateFlow;
    private final CoroutineDispatcher mainDispatcher;
    /* access modifiers changed from: private */
    public final AtomicInteger submitDataId;
    /* access modifiers changed from: private */
    public final ListUpdateCallback updateCallback;
    /* access modifiers changed from: private */
    public final CoroutineDispatcher workerDispatcher;

    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> itemCallback, ListUpdateCallback listUpdateCallback) {
        this(itemCallback, listUpdateCallback, (CoroutineDispatcher) null, (CoroutineDispatcher) null, 12, (DefaultConstructorMarker) null);
    }

    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> itemCallback, ListUpdateCallback listUpdateCallback, CoroutineDispatcher coroutineDispatcher) {
        this(itemCallback, listUpdateCallback, coroutineDispatcher, (CoroutineDispatcher) null, 8, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ void getDifferCallback$paging_runtime_release$annotations() {
    }

    public static /* synthetic */ void getInGetItem$paging_runtime_release$annotations() {
    }

    public AsyncPagingDataDiffer(DiffUtil.ItemCallback<T> itemCallback, ListUpdateCallback listUpdateCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2) {
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(listUpdateCallback, "updateCallback");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "mainDispatcher");
        Intrinsics.checkNotNullParameter(coroutineDispatcher2, "workerDispatcher");
        this.diffCallback = itemCallback;
        this.updateCallback = listUpdateCallback;
        this.mainDispatcher = coroutineDispatcher;
        this.workerDispatcher = coroutineDispatcher2;
        DifferCallback asyncPagingDataDiffer$differCallback$1 = new AsyncPagingDataDiffer$differCallback$1(this);
        this.differCallback = asyncPagingDataDiffer$differCallback$1;
        AsyncPagingDataDiffer$differBase$1 asyncPagingDataDiffer$differBase$1 = new AsyncPagingDataDiffer$differBase$1(this, asyncPagingDataDiffer$differCallback$1, coroutineDispatcher);
        this.differBase = asyncPagingDataDiffer$differBase$1;
        this.submitDataId = new AtomicInteger(0);
        this.loadStateFlow = asyncPagingDataDiffer$differBase$1.getLoadStateFlow();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AsyncPagingDataDiffer(DiffUtil.ItemCallback itemCallback, ListUpdateCallback listUpdateCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, listUpdateCallback, (i & 4) != 0 ? Dispatchers.getMain() : coroutineDispatcher, (i & 8) != 0 ? Dispatchers.getDefault() : coroutineDispatcher2);
    }

    public final DifferCallback getDifferCallback$paging_runtime_release() {
        return this.differCallback;
    }

    public final boolean getInGetItem$paging_runtime_release() {
        return this.inGetItem;
    }

    public final void setInGetItem$paging_runtime_release(boolean z) {
        this.inGetItem = z;
    }

    public final Object submitData(PagingData<T> pagingData, Continuation<? super Unit> continuation) {
        this.submitDataId.incrementAndGet();
        Object collectFrom = this.differBase.collectFrom(pagingData, continuation);
        if (collectFrom == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return collectFrom;
        }
        return Unit.INSTANCE;
    }

    public final void submitData(Lifecycle lifecycle, PagingData<T> pagingData) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(pagingData, "pagingData");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleKt.getCoroutineScope(lifecycle), (CoroutineContext) null, (CoroutineStart) null, new AsyncPagingDataDiffer$submitData$2(this, this.submitDataId.incrementAndGet(), pagingData, (Continuation) null), 3, (Object) null);
    }

    public final void retry() {
        this.differBase.retry();
    }

    public final void refresh() {
        this.differBase.refresh();
    }

    /* JADX INFO: finally extract failed */
    public final T getItem(int i) {
        try {
            this.inGetItem = true;
            T t = this.differBase.get(i);
            this.inGetItem = false;
            return t;
        } catch (Throwable th) {
            this.inGetItem = false;
            throw th;
        }
    }

    public final T peek(int i) {
        return this.differBase.peek(i);
    }

    public final ItemSnapshotList<T> snapshot() {
        return this.differBase.snapshot();
    }

    public final int getItemCount() {
        return this.differBase.getSize();
    }

    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.loadStateFlow;
    }

    public final void addLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.differBase.addLoadStateListener(function1);
    }

    public final void removeLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.differBase.removeLoadStateListener(function1);
    }
}
