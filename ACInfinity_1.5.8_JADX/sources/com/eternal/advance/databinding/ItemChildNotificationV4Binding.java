package com.eternal.advance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.advance.C1202R;
import com.eternal.advance.model.ItemChildModelV4;

public abstract class ItemChildNotificationV4Binding extends ViewDataBinding {
    public final LinearLayout llContent;
    @Bindable
    protected ItemChildModelV4 mChildV4;
    public final TextView tvDel;
    public final TextView tvTypeDetail;

    public abstract void setChildV4(ItemChildModelV4 itemChildModelV4);

    protected ItemChildNotificationV4Binding(Object obj, View view, int i, LinearLayout linearLayout, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.llContent = linearLayout;
        this.tvDel = textView;
        this.tvTypeDetail = textView2;
    }

    public ItemChildModelV4 getChildV4() {
        return this.mChildV4;
    }

    public static ItemChildNotificationV4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChildNotificationV4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemChildNotificationV4Binding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.item_child_notification_v4, viewGroup, z, obj);
    }

    public static ItemChildNotificationV4Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChildNotificationV4Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemChildNotificationV4Binding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.item_child_notification_v4, (ViewGroup) null, false, obj);
    }

    public static ItemChildNotificationV4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemChildNotificationV4Binding bind(View view, Object obj) {
        return (ItemChildNotificationV4Binding) bind(obj, view, C1202R.layout.item_child_notification_v4);
    }
}
