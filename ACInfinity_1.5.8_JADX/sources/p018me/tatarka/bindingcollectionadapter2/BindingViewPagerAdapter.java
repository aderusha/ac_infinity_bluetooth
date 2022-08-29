package p018me.tatarka.bindingcollectionadapter2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.PagerAdapter;
import java.lang.ref.WeakReference;
import java.util.List;

/* renamed from: me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter */
public class BindingViewPagerAdapter<T> extends PagerAdapter implements BindingCollectionAdapter<T> {
    private final WeakReferenceOnListChangedCallback<T> callback = new WeakReferenceOnListChangedCallback<>(this);
    private LayoutInflater inflater;
    private ItemBinding<T> itemBinding;
    private List<T> items;
    private PageTitles<T> pageTitles;

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter$PageTitles */
    public interface PageTitles<T> {
        CharSequence getPageTitle(int i, T t);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
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

    public void setPageTitles(PageTitles<T> pageTitles2) {
        this.pageTitles = pageTitles2;
    }

    public int getCount() {
        List<T> list = this.items;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public CharSequence getPageTitle(int i) {
        PageTitles<T> pageTitles2 = this.pageTitles;
        if (pageTitles2 == null) {
            return null;
        }
        return pageTitles2.getPageTitle(i, this.items.get(i));
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.inflater == null) {
            this.inflater = LayoutInflater.from(viewGroup.getContext());
        }
        T t = this.items.get(i);
        this.itemBinding.onItemBind(i, t);
        ViewDataBinding onCreateBinding = onCreateBinding(this.inflater, this.itemBinding.layoutRes(), viewGroup);
        onBindBinding(onCreateBinding, this.itemBinding.variableId(), this.itemBinding.layoutRes(), i, t);
        viewGroup.addView(onCreateBinding.getRoot());
        onCreateBinding.getRoot().setTag(t);
        return onCreateBinding.getRoot();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getItemPosition(Object obj) {
        T tag = ((View) obj).getTag();
        if (this.items == null) {
            return -2;
        }
        for (int i = 0; i < this.items.size(); i++) {
            if (tag == this.items.get(i)) {
                return i;
            }
        }
        return -2;
    }

    /* renamed from: me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter$WeakReferenceOnListChangedCallback */
    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {
        final WeakReference<BindingViewPagerAdapter<T>> adapterRef;

        WeakReferenceOnListChangedCallback(BindingViewPagerAdapter<T> bindingViewPagerAdapter) {
            this.adapterRef = new WeakReference<>(bindingViewPagerAdapter);
        }

        public void onChanged(ObservableList observableList) {
            BindingViewPagerAdapter bindingViewPagerAdapter = (BindingViewPagerAdapter) this.adapterRef.get();
            if (bindingViewPagerAdapter != null) {
                Utils.ensureChangeOnMainThread();
                bindingViewPagerAdapter.notifyDataSetChanged();
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
