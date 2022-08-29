package androidx.paging;

import android.view.ViewGroup;
import androidx.paging.LoadState;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27510bv = {1, 0, 3}, mo27511d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000eJ\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\u0015J\u001b\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u000e¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0006H&¢\u0006\u0002\u0010\u001aJ\u001b\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u000e¢\u0006\u0002\u0010\u001cR$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u001d"}, mo27512d2 = {"Landroidx/paging/LoadStateAdapter;", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "()V", "loadState", "Landroidx/paging/LoadState;", "getLoadState", "()Landroidx/paging/LoadState;", "setLoadState", "(Landroidx/paging/LoadState;)V", "displayLoadStateAsItem", "", "getItemCount", "", "getItemViewType", "position", "getStateViewType", "onBindViewHolder", "", "holder", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;Landroidx/paging/LoadState;)V", "(Landroidx/recyclerview/widget/RecyclerView$ViewHolder;I)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;Landroidx/paging/LoadState;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "viewType", "(Landroid/view/ViewGroup;I)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "paging-runtime_release"}, mo27513k = 1, mo27514mv = {1, 4, 2})
/* compiled from: LoadStateAdapter.kt */
public abstract class LoadStateAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private LoadState loadState = new LoadState.NotLoading(false);

    public int getStateViewType(LoadState loadState2) {
        Intrinsics.checkNotNullParameter(loadState2, "loadState");
        return 0;
    }

    public abstract void onBindViewHolder(VH vh, LoadState loadState2);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup, LoadState loadState2);

    public final LoadState getLoadState() {
        return this.loadState;
    }

    public final void setLoadState(LoadState loadState2) {
        Intrinsics.checkNotNullParameter(loadState2, "loadState");
        if (!Intrinsics.areEqual((Object) this.loadState, (Object) loadState2)) {
            boolean displayLoadStateAsItem = displayLoadStateAsItem(this.loadState);
            boolean displayLoadStateAsItem2 = displayLoadStateAsItem(loadState2);
            if (displayLoadStateAsItem && !displayLoadStateAsItem2) {
                notifyItemRemoved(0);
            } else if (displayLoadStateAsItem2 && !displayLoadStateAsItem) {
                notifyItemInserted(0);
            } else if (displayLoadStateAsItem && displayLoadStateAsItem2) {
                notifyItemChanged(0);
            }
            this.loadState = loadState2;
        }
    }

    public final VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        return onCreateViewHolder(viewGroup, this.loadState);
    }

    public final void onBindViewHolder(VH vh, int i) {
        Intrinsics.checkNotNullParameter(vh, "holder");
        onBindViewHolder(vh, this.loadState);
    }

    public final int getItemViewType(int i) {
        return getStateViewType(this.loadState);
    }

    public final int getItemCount() {
        return displayLoadStateAsItem(this.loadState) ? 1 : 0;
    }

    public boolean displayLoadStateAsItem(LoadState loadState2) {
        Intrinsics.checkNotNullParameter(loadState2, "loadState");
        return (loadState2 instanceof LoadState.Loading) || (loadState2 instanceof LoadState.Error);
    }
}
