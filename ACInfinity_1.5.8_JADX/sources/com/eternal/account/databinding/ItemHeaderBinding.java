package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.ShareItemModel;

public abstract class ItemHeaderBinding extends ViewDataBinding {
    @Bindable
    protected ShareItemModel mItemShare;

    public abstract void setItemShare(ShareItemModel shareItemModel);

    protected ItemHeaderBinding(Object obj, View view, int i) {
        super(obj, view, i);
    }

    public ShareItemModel getItemShare() {
        return this.mItemShare;
    }

    public static ItemHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHeaderBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_header, viewGroup, z, obj);
    }

    public static ItemHeaderBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHeaderBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemHeaderBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_header, (ViewGroup) null, false, obj);
    }

    public static ItemHeaderBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemHeaderBinding bind(View view, Object obj) {
        return (ItemHeaderBinding) bind(obj, view, C0997R.layout.item_header);
    }
}
