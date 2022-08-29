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
import com.eternal.account.model.InviteItemModel;

public abstract class ItemInviteBinding extends ViewDataBinding {
    public final ConstraintLayout clContent;
    @Bindable
    protected InviteItemModel mItemInvite;
    public final TextView tvName;
    public final TextView tvType;
    public final TextView tvTypeName;

    public abstract void setItemInvite(InviteItemModel inviteItemModel);

    protected ItemInviteBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clContent = constraintLayout;
        this.tvName = textView;
        this.tvType = textView2;
        this.tvTypeName = textView3;
    }

    public InviteItemModel getItemInvite() {
        return this.mItemInvite;
    }

    public static ItemInviteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemInviteBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_invite, viewGroup, z, obj);
    }

    public static ItemInviteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemInviteBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_invite, (ViewGroup) null, false, obj);
    }

    public static ItemInviteBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemInviteBinding bind(View view, Object obj) {
        return (ItemInviteBinding) bind(obj, view, C0997R.layout.item_invite);
    }
}
