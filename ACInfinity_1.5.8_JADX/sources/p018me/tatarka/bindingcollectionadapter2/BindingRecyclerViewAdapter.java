package p018me.tatarka.bindingcollectionadapter2;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.OnRebindCallback;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter */
public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements BindingCollectionAdapter<T> {
    /* access modifiers changed from: private */
    public static final Object DATA_INVALIDATION = new Object();
    private final WeakReferenceOnListChangedCallback<T> callback = new WeakReferenceOnListChangedCallback<>(this);
    private LayoutInflater inflater;
    private ItemBinding<T> itemBinding;
    private ItemIds<? super T> itemIds;
    private List<T> items;
    /* access modifiers changed from: private */
    public RecyclerView recyclerView;
    private ViewHolderFactory viewHolderFactory;

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds */
    public interface ItemIds<T> {
        long getItemId(int i, T t);
    }

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory */
    public interface ViewHolderFactory {
        RecyclerView.ViewHolder createViewHolder(ViewDataBinding viewDataBinding);
    }

    public void setItemBinding(ItemBinding<T> itemBinding2) {
        this.itemBinding = itemBinding2;
    }

    public ItemBinding<T> getItemBinding() {
        return this.itemBinding;
    }

    public void setItems(List<T> list) {
        List<T> list2 = this.items;
        if (list2 != list) {
            if (this.recyclerView != null) {
                if (list2 instanceof ObservableList) {
                    ((ObservableList) list2).removeOnListChangedCallback(this.callback);
                }
                if (list instanceof ObservableList) {
                    ((ObservableList) list).addOnListChangedCallback(this.callback);
                }
            }
            this.items = list;
            notifyDataSetChanged();
        }
    }

    public T getAdapterItem(int i) {
        return this.items.get(i);
    }

    public ViewDataBinding onCreateBinding(LayoutInflater layoutInflater, int i, ViewGroup viewGroup) {
        return DataBindingUtil.inflate(layoutInflater, i, viewGroup, false);
    }

    public void onBindBinding(ViewDataBinding viewDataBinding, int i, int i2, int i3, T t) {
        if (this.itemBinding.bind(viewDataBinding, t)) {
            viewDataBinding.executePendingBindings();
        }
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView2) {
        List<T> list;
        if (this.recyclerView == null && (list = this.items) != null && (list instanceof ObservableList)) {
            ((ObservableList) list).addOnListChangedCallback(this.callback);
        }
        this.recyclerView = recyclerView2;
    }

    public void onDetachedFromRecyclerView(RecyclerView recyclerView2) {
        List<T> list;
        if (!(this.recyclerView == null || (list = this.items) == null || !(list instanceof ObservableList))) {
            ((ObservableList) list).removeOnListChangedCallback(this.callback);
        }
        this.recyclerView = null;
    }

    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        ViewDataBinding onCreateBinding = onCreateBinding(this.inflater, i, viewGroup);
        final RecyclerView.ViewHolder onCreateViewHolder = onCreateViewHolder(onCreateBinding);
        onCreateBinding.addOnRebindCallback(new OnRebindCallback() {
            public boolean onPreBind(ViewDataBinding viewDataBinding) {
                return BindingRecyclerViewAdapter.this.recyclerView != null && BindingRecyclerViewAdapter.this.recyclerView.isComputingLayout();
            }

            public void onCanceled(ViewDataBinding viewDataBinding) {
                int adapterPosition;
                if (BindingRecyclerViewAdapter.this.recyclerView != null && !BindingRecyclerViewAdapter.this.recyclerView.isComputingLayout() && (adapterPosition = onCreateViewHolder.getAdapterPosition()) != -1) {
                    BindingRecyclerViewAdapter.this.notifyItemChanged(adapterPosition, BindingRecyclerViewAdapter.DATA_INVALIDATION);
                }
            }
        });
        return onCreateViewHolder;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewDataBinding viewDataBinding) {
        ViewHolderFactory viewHolderFactory2 = this.viewHolderFactory;
        if (viewHolderFactory2 != null) {
            return viewHolderFactory2.createViewHolder(viewDataBinding);
        }
        return new BindingViewHolder(viewDataBinding);
    }

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$BindingViewHolder */
    private static class BindingViewHolder extends RecyclerView.ViewHolder {
        public BindingViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
        }
    }

    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindBinding(DataBindingUtil.getBinding(viewHolder.itemView), this.itemBinding.variableId(), this.itemBinding.layoutRes(), i, this.items.get(i));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List<Object> list) {
        if (isForDataBinding(list)) {
            DataBindingUtil.getBinding(viewHolder.itemView).executePendingBindings();
        } else {
            super.onBindViewHolder(viewHolder, i, list);
        }
    }

    private boolean isForDataBinding(List<Object> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != DATA_INVALIDATION) {
                return false;
            }
        }
        return true;
    }

    public int getItemViewType(int i) {
        this.itemBinding.onItemBind(i, this.items.get(i));
        return this.itemBinding.layoutRes();
    }

    public void setItemIds(ItemIds<? super T> itemIds2) {
        if (this.itemIds != itemIds2) {
            this.itemIds = itemIds2;
            setHasStableIds(itemIds2 != null);
        }
    }

    public void setViewHolderFactory(ViewHolderFactory viewHolderFactory2) {
        this.viewHolderFactory = viewHolderFactory2;
    }

    public int getItemCount() {
        List<T> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public long getItemId(int i) {
        ItemIds<? super T> itemIds2 = this.itemIds;
        return itemIds2 == null ? (long) i : itemIds2.getItemId(i, this.items.get(i));
    }

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$WeakReferenceOnListChangedCallback */
    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<BindingRecyclerViewAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter) {
            this.adapterRef = new WeakReference<>(bindingRecyclerViewAdapter);
        }

        public void onChanged(ObservableList observableList) {
            BindingRecyclerViewAdapter bindingRecyclerViewAdapter = (BindingRecyclerViewAdapter) this.adapterRef.get();
            if (bindingRecyclerViewAdapter != null) {
                Utils.ensureChangeOnMainThread();
                bindingRecyclerViewAdapter.notifyDataSetChanged();
            }
        }

        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            BindingRecyclerViewAdapter bindingRecyclerViewAdapter = (BindingRecyclerViewAdapter) this.adapterRef.get();
            if (bindingRecyclerViewAdapter != null) {
                Utils.ensureChangeOnMainThread();
                bindingRecyclerViewAdapter.notifyItemRangeChanged(i, i2);
            }
        }

        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            BindingRecyclerViewAdapter bindingRecyclerViewAdapter = (BindingRecyclerViewAdapter) this.adapterRef.get();
            if (bindingRecyclerViewAdapter != null) {
                Utils.ensureChangeOnMainThread();
                bindingRecyclerViewAdapter.notifyItemRangeInserted(i, i2);
            }
        }

        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            BindingRecyclerViewAdapter bindingRecyclerViewAdapter = (BindingRecyclerViewAdapter) this.adapterRef.get();
            if (bindingRecyclerViewAdapter != null) {
                Utils.ensureChangeOnMainThread();
                for (int i4 = 0; i4 < i3; i4++) {
                    bindingRecyclerViewAdapter.notifyItemMoved(i + i4, i2 + i4);
                }
            }
        }

        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            BindingRecyclerViewAdapter bindingRecyclerViewAdapter = (BindingRecyclerViewAdapter) this.adapterRef.get();
            if (bindingRecyclerViewAdapter != null) {
                Utils.ensureChangeOnMainThread();
                bindingRecyclerViewAdapter.notifyItemRangeRemoved(i, i2);
            }
        }
    }
}
