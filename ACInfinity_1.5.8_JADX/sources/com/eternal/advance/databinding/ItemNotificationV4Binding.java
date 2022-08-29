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
import com.eternal.advance.model.ItemModelV4;
import com.eternal.base.SlideRecyclerView;
import com.eternal.base.StatueSwitch;

public abstract class ItemNotificationV4Binding extends ViewDataBinding {
    public final ConstraintLayout clContent;
    public final ImageView ivType;
    public final SlideRecyclerView listChild;
    public final LinearLayout llHead;
    @Bindable
    protected ItemModelV4 mItemV4;

    /* renamed from: sb */
    public final StatueSwitch f127sb;
    public final TextView tvDel;
    public final TextView tvTypeName;

    public abstract void setItemV4(ItemModelV4 itemModelV4);

    protected ItemNotificationV4Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, SlideRecyclerView slideRecyclerView, LinearLayout linearLayout, StatueSwitch statueSwitch, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.clContent = constraintLayout;
        this.ivType = imageView;
        this.listChild = slideRecyclerView;
        this.llHead = linearLayout;
        this.f127sb = statueSwitch;
        this.tvDel = textView;
        this.tvTypeName = textView2;
    }

    public ItemModelV4 getItemV4() {
        return this.mItemV4;
    }

    public static ItemNotificationV4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNotificationV4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemNotificationV4Binding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.item_notification_v4, viewGroup, z, obj);
    }

    public static ItemNotificationV4Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNotificationV4Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemNotificationV4Binding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.item_notification_v4, (ViewGroup) null, false, obj);
    }

    public static ItemNotificationV4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemNotificationV4Binding bind(View view, Object obj) {
        return (ItemNotificationV4Binding) bind(obj, view, C1202R.layout.item_notification_v4);
    }
}
