package com.eternal.advance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.advance.C1202R;
import com.eternal.advance.model.ItemModel;
import com.eternal.base.StatueSwitch;

public abstract class ItemNotificationBinding extends ViewDataBinding {
    public final ConstraintLayout clContent;
    public final ImageView ivType;
    public final LinearLayout llHead;
    @Bindable
    protected ItemModel mItem;

    /* renamed from: sb */
    public final StatueSwitch f126sb;
    public final TextView tvDel;
    public final TextView tvTypeDetail;
    public final TextView tvTypeName;

    public abstract void setItem(ItemModel itemModel);

    protected ItemNotificationBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, LinearLayout linearLayout, StatueSwitch statueSwitch, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clContent = constraintLayout;
        this.ivType = imageView;
        this.llHead = linearLayout;
        this.f126sb = statueSwitch;
        this.tvDel = textView;
        this.tvTypeDetail = textView2;
        this.tvTypeName = textView3;
    }

    public ItemModel getItem() {
        return this.mItem;
    }

    public static ItemNotificationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNotificationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemNotificationBinding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.item_notification, viewGroup, z, obj);
    }

    public static ItemNotificationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNotificationBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemNotificationBinding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.item_notification, (ViewGroup) null, false, obj);
    }

    public static ItemNotificationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNotificationBinding bind(View view, Object obj) {
        return (ItemNotificationBinding) bind(obj, view, C1202R.layout.item_notification);
    }
}
