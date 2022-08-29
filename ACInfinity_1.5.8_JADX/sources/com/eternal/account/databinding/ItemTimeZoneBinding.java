package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.TimeZoneItemModel;

public abstract class ItemTimeZoneBinding extends ViewDataBinding {
    public final ConstraintLayout clContent;
    public final ImageView ivArrow;
    @Bindable
    protected TimeZoneItemModel mItemTimeZone;
    public final TextView tvName;
    public final TextView tvType;
    public final TextView tvTypeName;

    public abstract void setItemTimeZone(TimeZoneItemModel timeZoneItemModel);

    protected ItemTimeZoneBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.clContent = constraintLayout;
        this.ivArrow = imageView;
        this.tvName = textView;
        this.tvType = textView2;
        this.tvTypeName = textView3;
    }

    public TimeZoneItemModel getItemTimeZone() {
        return this.mItemTimeZone;
    }

    public static ItemTimeZoneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTimeZoneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemTimeZoneBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_time_zone, viewGroup, z, obj);
    }

    public static ItemTimeZoneBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTimeZoneBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemTimeZoneBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.item_time_zone, (ViewGroup) null, false, obj);
    }

    public static ItemTimeZoneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemTimeZoneBinding bind(View view, Object obj) {
        return (ItemTimeZoneBinding) bind(obj, view, C0997R.layout.item_time_zone);
    }
}
