package androidx.paging;

import androidx.lifecycle.Lifecycle;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00030\u0005B)\b\u0007\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u001a\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00160\u0018J\u0019\u0010\u0019\u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010\u001a\u001a\u00020\u001bH\u0004¢\u0006\u0002\u0010\u001cJ\b\u0010\u001d\u001a\u00020\u001bH\u0016J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001bJ\u0017\u0010 \u001a\u0004\u0018\u00018\u00002\b\b\u0001\u0010!\u001a\u00020\u001b¢\u0006\u0002\u0010\u001cJ\u0006\u0010\"\u001a\u00020\u0016J\u001a\u0010#\u001a\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00160\u0018J\u0006\u0010$\u001a\u00020\u0016J\u000e\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u0014J\u0010\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020)H\u0016J\f\u0010*\u001a\b\u0012\u0004\u0012\u00028\u00000+J\u001c\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u000000J\u001f\u0010,\u001a\u00020\u00162\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u000000H@ø\u0001\u0000¢\u0006\u0002\u00101J\u0012\u00102\u001a\u0002032\n\u00104\u001a\u0006\u0012\u0002\b\u000305J\u0012\u00106\u001a\u0002032\n\u00107\u001a\u0006\u0012\u0002\b\u000305J\u001e\u00108\u001a\u0002032\n\u00107\u001a\u0006\u0012\u0002\b\u0003052\n\u00104\u001a\u0006\u0012\u0002\b\u000305R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00069"}, mo27512d2 = {"Landroidx/paging/PagingDataAdapter;", "T", "", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "mainDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "workerDispatcher", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlinx/coroutines/CoroutineDispatcher;)V", "differ", "Landroidx/paging/AsyncPagingDataDiffer;", "loadStateFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/CombinedLoadStates;", "getLoadStateFlow", "()Lkotlinx/coroutines/flow/Flow;", "userSetRestorationPolicy", "", "addLoadStateListener", "", "listener", "Lkotlin/Function1;", "getItem", "position", "", "(I)Ljava/lang/Object;", "getItemCount", "getItemId", "", "peek", "index", "refresh", "removeLoadStateListener", "retry", "setHasStableIds", "hasStableIds", "setStateRestorationPolicy", "strategy", "Landroidx/recyclerview/widget/RecyclerView$Adapter$StateRestorationPolicy;", "snapshot", "Landroidx/paging/ItemSnapshotList;", "submitData", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "pagingData", "Landroidx/paging/PagingData;", "(Landroidx/paging/PagingData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withLoadStateFooter", "Landroidx/recyclerview/widget/ConcatAdapter;", "footer", "Landroidx/paging/LoadStateAdapter;", "withLoadStateHeader", "header", "withLoadStateHeaderAndFooter", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: PagingDataAdapter.kt */
public abstract class PagingDataAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final AsyncPagingDataDiffer<T> differ;
    private final Flow<CombinedLoadStates> loadStateFlow;
    /* access modifiers changed from: private */
    public boolean userSetRestorationPolicy;

    public PagingDataAdapter(DiffUtil.ItemCallback<T> itemCallback) {
        this(itemCallback, (CoroutineDispatcher) null, (CoroutineDispatcher) null, 6, (DefaultConstructorMarker) null);
    }

    public PagingDataAdapter(DiffUtil.ItemCallback<T> itemCallback, CoroutineDispatcher coroutineDispatcher) {
        this(itemCallback, coroutineDispatcher, (CoroutineDispatcher) null, 4, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PagingDataAdapter(DiffUtil.ItemCallback itemCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(itemCallback, (i & 2) != 0 ? Dispatchers.getMain() : coroutineDispatcher, (i & 4) != 0 ? Dispatchers.getDefault() : coroutineDispatcher2);
    }

    public PagingDataAdapter(DiffUtil.ItemCallback<T> itemCallback, CoroutineDispatcher coroutineDispatcher, CoroutineDispatcher coroutineDispatcher2) {
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "mainDispatcher");
        Intrinsics.checkNotNullParameter(coroutineDispatcher2, "workerDispatcher");
        AsyncPagingDataDiffer<T> asyncPagingDataDiffer = new AsyncPagingDataDiffer<>(itemCallback, new AdapterListUpdateCallback(this), coroutineDispatcher, coroutineDispatcher2);
        this.differ = asyncPagingDataDiffer;
        super.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.PREVENT);
        final C05121 r4 = new Function0<Unit>(this) {
            final /* synthetic */ PagingDataAdapter this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                if (this.this$0.getStateRestorationPolicy() == RecyclerView.Adapter.StateRestorationPolicy.PREVENT && !this.this$0.userSetRestorationPolicy) {
                    this.this$0.setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy.ALLOW);
                }
            }
        };
        registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(this) {
            final /* synthetic */ PagingDataAdapter this$0;

            {
                this.this$0 = r1;
            }

            public void onItemRangeInserted(int i, int i2) {
                r4.invoke();
                this.this$0.unregisterAdapterDataObserver(this);
                super.onItemRangeInserted(i, i2);
            }
        });
        addLoadStateListener(new Function1<CombinedLoadStates, Unit>(this) {
            private boolean ignoreNextEvent = true;
            final /* synthetic */ PagingDataAdapter this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((CombinedLoadStates) obj);
                return Unit.INSTANCE;
            }

            public void invoke(CombinedLoadStates combinedLoadStates) {
                Intrinsics.checkNotNullParameter(combinedLoadStates, "loadStates");
                if (this.ignoreNextEvent) {
                    this.ignoreNextEvent = false;
                } else if (combinedLoadStates.getSource().getRefresh() instanceof LoadState.NotLoading) {
                    r4.invoke();
                    this.this$0.removeLoadStateListener(this);
                }
            }
        });
        this.loadStateFlow = asyncPagingDataDiffer.getLoadStateFlow();
    }

    public void setStateRestorationPolicy(RecyclerView.Adapter.StateRestorationPolicy stateRestorationPolicy) {
        Intrinsics.checkNotNullParameter(stateRestorationPolicy, "strategy");
        this.userSetRestorationPolicy = true;
        super.setStateRestorationPolicy(stateRestorationPolicy);
    }

    public final long getItemId(int i) {
        return super.getItemId(i);
    }

    public final void setHasStableIds(boolean z) {
        throw new UnsupportedOperationException("Stable ids are unsupported on PagingDataAdapter.");
    }

    public final Object submitData(PagingData<T> pagingData, Continuation<? super Unit> continuation) {
        Object submitData = this.differ.submitData(pagingData, continuation);
        if (submitData == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return submitData;
        }
        return Unit.INSTANCE;
    }

    public final void submitData(Lifecycle lifecycle, PagingData<T> pagingData) {
        Intrinsics.checkNotNullParameter(lifecycle, "lifecycle");
        Intrinsics.checkNotNullParameter(pagingData, "pagingData");
        this.differ.submitData(lifecycle, pagingData);
    }

    public final void retry() {
        this.differ.retry();
    }

    public final void refresh() {
        this.differ.refresh();
    }

    /* access modifiers changed from: protected */
    public final T getItem(int i) {
        return this.differ.getItem(i);
    }

    public final T peek(int i) {
        return this.differ.peek(i);
    }

    public final ItemSnapshotList<T> snapshot() {
        return this.differ.snapshot();
    }

    public int getItemCount() {
        return this.differ.getItemCount();
    }

    public final Flow<CombinedLoadStates> getLoadStateFlow() {
        return this.loadStateFlow;
    }

    public final void addLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.differ.addLoadStateListener(function1);
    }

    public final void removeLoadStateListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.differ.removeLoadStateListener(function1);
    }

    public final ConcatAdapter withLoadStateHeader(LoadStateAdapter<?> loadStateAdapter) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "header");
        addLoadStateListener(new PagingDataAdapter$withLoadStateHeader$1(loadStateAdapter));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{loadStateAdapter, this});
    }

    public final ConcatAdapter withLoadStateFooter(LoadStateAdapter<?> loadStateAdapter) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "footer");
        addLoadStateListener(new PagingDataAdapter$withLoadStateFooter$1(loadStateAdapter));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{this, loadStateAdapter});
    }

    public final ConcatAdapter withLoadStateHeaderAndFooter(LoadStateAdapter<?> loadStateAdapter, LoadStateAdapter<?> loadStateAdapter2) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "header");
        Intrinsics.checkNotNullParameter(loadStateAdapter2, "footer");
        addLoadStateListener(new PagingDataAdapter$withLoadStateHeaderAndFooter$1(loadStateAdapter, loadStateAdapter2));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{loadStateAdapter, this, loadStateAdapter2});
    }
}
