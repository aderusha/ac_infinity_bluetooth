package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.ShareItemModel;

public abstract class ItemShareBinding extends ViewDataBinding {
    public final ConstraintLayout clContent;
    @Bindable
    protected ShareItemModel mItemShare;
    public final TextView tvDel;
    public final TextView tvName;
    public final TextView tvType;
    public final TextView tvTypeName;

    public abstract void setItemShare(ShareItemModel shareItemModel);

    protected ItemShareBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.clContent = constraintLayout;
        this.tvDel = textView;
        this.tvName = textView2;
        this.tvType = textView3;
        this.tvTypeName = textView4;
    }

    public ShareItemModel getItemShare() {
        return this.mItemShare;
    }

    public static ItemShareBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemShareBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemShareBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_share, viewGroup, z, obj);
    }

    public static ItemShareBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemShareBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemShareBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_share, (ViewGroup) null, false, obj);
    }

    public static ItemShareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemShareBinding bind(View view, Object obj) {
        return (ItemShareBinding) bind(obj, view, C0997R.layout.item_share);
    }
}
