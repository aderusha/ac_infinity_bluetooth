package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.SearchItemModel;

public abstract class ItemSearchBinding extends ViewDataBinding {
    @Bindable
    protected SearchItemModel mItemSearch;
    public final TextView tvDesc;
    public final TextView tvName;

    public abstract void setItemSearch(SearchItemModel searchItemModel);

    protected ItemSearchBinding(Object obj, View view, int i, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.tvDesc = textView;
        this.tvName = textView2;
    }

    public SearchItemModel getItemSearch() {
        return this.mItemSearch;
    }

    public static ItemSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemSearchBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_search, viewGroup, z, obj);
    }

    public static ItemSearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemSearchBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_search, (ViewGroup) null, false, obj);
    }

    public static ItemSearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemSearchBinding bind(View view, Object obj) {
        return (ItemSearchBinding) bind(obj, view, C0997R.layout.item_search);
    }
}
