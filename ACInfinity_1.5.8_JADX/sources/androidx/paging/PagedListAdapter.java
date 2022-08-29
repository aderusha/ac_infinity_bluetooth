package androidx.paging;

import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\b\u0012\u0004\u0012\u0002H\u00030\u0005B\u0015\b\u0014\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bB\u0015\b\u0014\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0002\u0010\u000bJ\"\u0010\u001b\u001a\u00020\u00192\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\u0017\u0010\u001e\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001f\u001a\u00020 H\u0014¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020 H\u0016J\u0018\u0010#\u001a\u00020\u00192\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\rH\u0017J(\u0010#\u001a\u00020\u00192\u000e\u0010$\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\rH\u0016J\"\u0010%\u001a\u00020\u00192\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00190\u0018H\u0016J\u0018\u0010&\u001a\u00020\u00192\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\rH\u0016J\"\u0010&\u001a\u00020\u00192\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0012\u0010*\u001a\u00020+2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030-J\u0012\u0010.\u001a\u00020+2\n\u0010/\u001a\u0006\u0012\u0002\b\u00030-J\u001e\u00100\u001a\u00020+2\n\u0010/\u001a\u0006\u0012\u0002\b\u00030-2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030-R\"\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r8VX\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R \u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0016R6\u0010\u0017\u001a$\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\r\u0012\u0004\u0012\u00020\u00190\u0018X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u000f¨\u00061"}, mo27512d2 = {"Landroidx/paging/PagedListAdapter;", "T", "", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "config", "Landroidx/recyclerview/widget/AsyncDifferConfig;", "(Landroidx/recyclerview/widget/AsyncDifferConfig;)V", "currentList", "Landroidx/paging/PagedList;", "getCurrentList$annotations", "()V", "getCurrentList", "()Landroidx/paging/PagedList;", "differ", "Landroidx/paging/AsyncPagedListDiffer;", "getDiffer$paging_runtime_release$annotations", "getDiffer$paging_runtime_release", "()Landroidx/paging/AsyncPagedListDiffer;", "listener", "Lkotlin/Function2;", "", "getListener$annotations", "addLoadStateListener", "Landroidx/paging/LoadType;", "Landroidx/paging/LoadState;", "getItem", "position", "", "(I)Ljava/lang/Object;", "getItemCount", "onCurrentListChanged", "previousList", "removeLoadStateListener", "submitList", "pagedList", "commitCallback", "Ljava/lang/Runnable;", "withLoadStateFooter", "Landroidx/recyclerview/widget/ConcatAdapter;", "footer", "Landroidx/paging/LoadStateAdapter;", "withLoadStateHeader", "header", "withLoadStateHeaderAndFooter", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
@Deprecated(message = "PagedListAdapter is deprecated and has been replaced by PagingDataAdapter", replaceWith = @ReplaceWith(expression = "PagingDataAdapter<T, VH>", imports = {"androidx.paging.PagingDataAdapter"}))
/* compiled from: PagedListAdapter.kt */
public abstract class PagedListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final AsyncPagedListDiffer<T> differ;
    private final Function2<PagedList<T>, PagedList<T>, Unit> listener;

    public static /* synthetic */ void getCurrentList$annotations() {
    }

    public static /* synthetic */ void getDiffer$paging_runtime_release$annotations() {
    }

    private static /* synthetic */ void getListener$annotations() {
    }

    @Deprecated(message = "Use the two argument variant instead.", replaceWith = @ReplaceWith(expression = "onCurrentListChanged(previousList, currentList)", imports = {}))
    public void onCurrentListChanged(PagedList<T> pagedList) {
    }

    public void onCurrentListChanged(PagedList<T> pagedList, PagedList<T> pagedList2) {
    }

    public final AsyncPagedListDiffer<T> getDiffer$paging_runtime_release() {
        return this.differ;
    }

    public PagedList<T> getCurrentList() {
        return this.differ.getCurrentList();
    }

    protected PagedListAdapter(DiffUtil.ItemCallback<T> itemCallback) {
        Intrinsics.checkNotNullParameter(itemCallback, "diffCallback");
        Function2<PagedList<T>, PagedList<T>, Unit> pagedListAdapter$listener$1 = new PagedListAdapter$listener$1(this);
        this.listener = pagedListAdapter$listener$1;
        AsyncPagedListDiffer<T> asyncPagedListDiffer = new AsyncPagedListDiffer<>((RecyclerView.Adapter<?>) this, itemCallback);
        this.differ = asyncPagedListDiffer;
        asyncPagedListDiffer.addPagedListListener((Function2<? super PagedList<T>, ? super PagedList<T>, Unit>) pagedListAdapter$listener$1);
    }

    protected PagedListAdapter(AsyncDifferConfig<T> asyncDifferConfig) {
        Intrinsics.checkNotNullParameter(asyncDifferConfig, "config");
        Function2<PagedList<T>, PagedList<T>, Unit> pagedListAdapter$listener$1 = new PagedListAdapter$listener$1(this);
        this.listener = pagedListAdapter$listener$1;
        AsyncPagedListDiffer<T> asyncPagedListDiffer = new AsyncPagedListDiffer<>((ListUpdateCallback) new AdapterListUpdateCallback(this), asyncDifferConfig);
        this.differ = asyncPagedListDiffer;
        asyncPagedListDiffer.addPagedListListener((Function2<? super PagedList<T>, ? super PagedList<T>, Unit>) pagedListAdapter$listener$1);
    }

    public void submitList(PagedList<T> pagedList) {
        this.differ.submitList(pagedList);
    }

    public void submitList(PagedList<T> pagedList, Runnable runnable) {
        this.differ.submitList(pagedList, runnable);
    }

    /* access modifiers changed from: protected */
    public T getItem(int i) {
        return this.differ.getItem(i);
    }

    public int getItemCount() {
        return this.differ.getItemCount();
    }

    public void addLoadStateListener(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "listener");
        this.differ.addLoadStateListener(function2);
    }

    public void removeLoadStateListener(Function2<? super LoadType, ? super LoadState, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "listener");
        this.differ.removeLoadStateListener(function2);
    }

    public final ConcatAdapter withLoadStateHeader(LoadStateAdapter<?> loadStateAdapter) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "header");
        addLoadStateListener(new PagedListAdapter$withLoadStateHeader$1(loadStateAdapter));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{loadStateAdapter, this});
    }

    public final ConcatAdapter withLoadStateFooter(LoadStateAdapter<?> loadStateAdapter) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "footer");
        addLoadStateListener(new PagedListAdapter$withLoadStateFooter$1(loadStateAdapter));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{this, loadStateAdapter});
    }

    public final ConcatAdapter withLoadStateHeaderAndFooter(LoadStateAdapter<?> loadStateAdapter, LoadStateAdapter<?> loadStateAdapter2) {
        Intrinsics.checkNotNullParameter(loadStateAdapter, "header");
        Intrinsics.checkNotNullParameter(loadStateAdapter2, "footer");
        addLoadStateListener(new PagedListAdapter$withLoadStateHeaderAndFooter$1(loadStateAdapter, loadStateAdapter2));
        return new ConcatAdapter((RecyclerView.Adapter<? extends RecyclerView.ViewHolder>[]) new RecyclerView.Adapter[]{loadStateAdapter, this, loadStateAdapter2});
    }
}
