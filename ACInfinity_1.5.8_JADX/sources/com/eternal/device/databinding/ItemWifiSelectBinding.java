package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.ItemModel;

public abstract class ItemWifiSelectBinding extends ViewDataBinding {
    @Bindable
    protected ItemModel mItem;

    public abstract void setItem(ItemModel itemModel);

    protected ItemWifiSelectBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public ItemModel getItem() {
        return this.mItem;
    }

    public static ItemWifiSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWifiSelectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemWifiSelectBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.item_wifi_select, viewGroup, z, obj);
    }

    public static ItemWifiSelectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWifiSelectBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemWifiSelectBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.item_wifi_select, (ViewGroup) null, false, obj);
    }

    public static ItemWifiSelectBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemWifiSelectBinding bind(View view, Object obj) {
        return (ItemWifiSelectBinding) bind(obj, view, C1922R.layout.item_wifi_select);
    }
}
