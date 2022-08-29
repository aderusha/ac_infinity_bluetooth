package p018me.tatarka.bindingcollectionadapter2;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.ViewDataBinding;
import java.util.List;

/* renamed from: me.tatarka.bindingcollectionadapter2.BindingCollectionAdapter */
public interface BindingCollectionAdapter<T> {
    T getAdapterItem(int i);

    ItemBinding<T> getItemBinding();

    void onBindBinding(ViewDataBinding viewDataBinding, int i, int i2, int i3, T t);

    ViewDataBinding onCreateBinding(LayoutInflater layoutInflater, int i, ViewGroup viewGroup);

    void setItemBinding(ItemBinding<T> itemBinding);

    void setItems(List<T> list);
}
