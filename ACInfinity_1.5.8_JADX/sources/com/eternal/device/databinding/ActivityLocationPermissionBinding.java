package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.LocationPermissionModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityLocationPermissionBinding extends ViewDataBinding {
    public final Button ibNext;
    @Bindable
    protected LocationPermissionModel mPermissionModel;
    public final Toolbar toolBar;
    public final TextView tvContent;
    public final TextView tvTitle;

    public abstract void setPermissionModel(LocationPermissionModel locationPermissionModel);

    protected ActivityLocationPermissionBinding(Object obj, View view, int i, Button button, Toolbar toolbar, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ibNext = button;
        this.toolBar = toolbar;
        this.tvContent = textView;
        this.tvTitle = textView2;
    }

    public LocationPermissionModel getPermissionModel() {
        return this.mPermissionModel;
    }

    public static ActivityLocationPermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocationPermissionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityLocationPermissionBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_location_permission, viewGroup, z, obj);
    }

    public static ActivityLocationPermissionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocationPermissionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityLocationPermissionBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_location_permission, (ViewGroup) null, false, obj);
    }

    public static ActivityLocationPermissionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLocationPermissionBinding bind(View view, Object obj) {
        return (ActivityLocationPermissionBinding) bind(obj, view, C1922R.layout.activity_location_permission);
    }
}
