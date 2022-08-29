package p018me.tatarka.bindingcollectionadapter2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: me.tatarka.bindingcollectionadapter2.BindingListViewAdapter */
public class BindingListViewAdapter<T> extends BaseAdapter implements BindingCollectionAdapter<T> {
    private final WeakReferenceOnListChangedCallback<T> callback = new WeakReferenceOnListChangedCallback<>(this);
    private int dropDownItemLayout;
    private LayoutInflater inflater;
    private ItemBinding<T> itemBinding;
    private ItemIds<? super T> itemIds;
    private ItemIsEnabled<? super T> itemIsEnabled;
    private final int itemTypeCount;
    private List<T> items;
    private int[] layouts;

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingListViewAdapter$ItemIds */
    public interface ItemIds<T> {
        long getItemId(int i, T t);
    }

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingListViewAdapter$ItemIsEnabled */
    public interface ItemIsEnabled<T> {
        boolean isEnabled(int i, T t);
    }

    public BindingListViewAdapter(int i) {
        this.itemTypeCount = i;
    }

    public void setItemBinding(ItemBinding<T> itemBinding2) {
        this.itemBinding = itemBinding2;
    }

    public ItemBinding<T> getItemBinding() {
        return this.itemBinding;
    }

    public void setDropDownItemLayout(int i) {
        this.dropDownItemLayout = i;
    }

    public void setItems(List<T> list) {
        List<T> list2 = this.items;
        if (list2 != list) {
            if (list2 instanceof ObservableList) {
                ((ObservableList) list2).removeOnListChangedCallback(this.callback);
            }
            if (list instanceof ObservableList) {
                ((ObservableList) list).addOnListChangedCallback(this.callback);
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

    public void setItemIds(ItemIds<? super T> itemIds2) {
        this.itemIds = itemIds2;
    }

    public void setItemIsEnabled(ItemIsEnabled<? super T> itemIsEnabled2) {
        this.itemIsEnabled = itemIsEnabled2;
    }

    public int getCount() {
        List<T> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public T getItem(int i) {
        return this.items.get(i);
    }

    public long getItemId(int i) {
        ItemIds<? super T> itemIds2 = this.itemIds;
        return itemIds2 == null ? (long) i : itemIds2.getItemId(i, this.items.get(i));
    }

    public boolean isEnabled(int i) {
        ItemIsEnabled<? super T> itemIsEnabled2 = this.itemIsEnabled;
        return itemIsEnabled2 == null || itemIsEnabled2.isEnabled(i, this.items.get(i));
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        ViewDataBinding viewDataBinding;
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        int i2 = this.layouts[getItemViewType(i)];
        if (view == null) {
            viewDataBinding = onCreateBinding(this.inflater, i2, viewGroup);
        } else {
            viewDataBinding = DataBindingUtil.getBinding(view);
        }
        ViewDataBinding viewDataBinding2 = viewDataBinding;
        onBindBinding(viewDataBinding2, this.itemBinding.variableId(), i2, i, this.items.get(i));
        return viewDataBinding.getRoot();
    }

    public final View getDropDownView(int i, View view, ViewGroup viewGroup) {
        ViewDataBinding viewDataBinding;
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        int i2 = this.dropDownItemLayout;
        if (i2 == 0) {
            return super.getDropDownView(i, view, viewGroup);
        }
        if (view == null) {
            viewDataBinding = onCreateBinding(this.inflater, i2, viewGroup);
        } else {
            viewDataBinding = DataBindingUtil.getBinding(view);
        }
        ViewDataBinding viewDataBinding2 = viewDataBinding;
        onBindBinding(viewDataBinding2, this.itemBinding.variableId(), i2, i, this.items.get(i));
        return viewDataBinding.getRoot();
    }

    public int getItemViewType(int i) {
        ensureLayoutsInit();
        this.itemBinding.onItemBind(i, this.items.get(i));
        int i2 = 0;
        int i3 = 0;
        while (true) {
            int[] iArr = this.layouts;
            if (i2 < iArr.length) {
                int layoutRes = this.itemBinding.layoutRes();
                int[] iArr2 = this.layouts;
                if (layoutRes == iArr2[i2]) {
                    return i2;
                }
                if (iArr2[i2] == 0) {
                    i3 = i2;
                }
                i2++;
            } else {
                iArr[i3] = this.itemBinding.layoutRes();
                return i3;
            }
        }
    }

    public boolean hasStableIds() {
        return this.itemIds != null;
    }

    public int getViewTypeCount() {
        return ensureLayoutsInit();
    }

    private int ensureLayoutsInit() {
        int i = this.itemTypeCount;
        if (this.layouts == null) {
            this.layouts = new int[i];
        }
        return i;
    }

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingListViewAdapter$WeakReferenceOnListChangedCallback */
    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<BindingListViewAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BindingListViewAdapter<T> bindingListViewAdapter) {
            this.adapterRef = new WeakReference<>(bindingListViewAdapter);
        }

        public void onChanged(ObservableList observableList) {
            BindingListViewAdapter bindingListViewAdapter = (BindingListViewAdapter) this.adapterRef.get();
            if (bindingListViewAdapter != null) {
                Utils.ensureChangeOnMainThread();
                bindingListViewAdapter.notifyDataSetChanged();
            }
        }

        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }

        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            onChanged(observableList);
        }

        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            onChanged(observableList);
        }
    }
}
