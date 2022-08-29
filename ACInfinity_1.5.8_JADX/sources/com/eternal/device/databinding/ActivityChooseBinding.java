package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.ItemDeviceView;
import com.eternal.device.model.ChooseModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityChooseBinding extends ViewDataBinding {
    public final ImageView ivIcon1;
    public final ImageView ivIcon2;
    public final ImageView ivIcon3;
    public final ImageView ivIcon4;
    public final ItemDeviceView ll67;
    public final ItemDeviceView ll69;
    public final ItemDeviceView ll69Wifi;
    public final ItemDeviceView ll70;
    public final ItemDeviceView ll75IndependentPort;
    public final ItemDeviceView ll76;
    public final ItemDeviceView ll76IndependentPort;
    public final ItemDeviceView llAirtap;
    public final ItemDeviceView llCa1;
    public final ItemDeviceView llCa2;
    public final ItemDeviceView llCb1;
    public final ItemDeviceView llCb2;
    public final LinearLayout llLayoutChoose1;
    @Bindable
    protected ChooseModel mChooseModel;
    public final Toolbar toolBar;

    public abstract void setChooseModel(ChooseModel chooseModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityChooseBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ItemDeviceView itemDeviceView, ItemDeviceView itemDeviceView2, ItemDeviceView itemDeviceView3, ItemDeviceView itemDeviceView4, ItemDeviceView itemDeviceView5, ItemDeviceView itemDeviceView6, ItemDeviceView itemDeviceView7, ItemDeviceView itemDeviceView8, ItemDeviceView itemDeviceView9, ItemDeviceView itemDeviceView10, ItemDeviceView itemDeviceView11, ItemDeviceView itemDeviceView12, LinearLayout linearLayout, Toolbar toolbar) {
        super(obj, view, i);
        this.ivIcon1 = imageView;
        this.ivIcon2 = imageView2;
        this.ivIcon3 = imageView3;
        this.ivIcon4 = imageView4;
        this.ll67 = itemDeviceView;
        this.ll69 = itemDeviceView2;
        this.ll69Wifi = itemDeviceView3;
        this.ll70 = itemDeviceView4;
        this.ll75IndependentPort = itemDeviceView5;
        this.ll76 = itemDeviceView6;
        this.ll76IndependentPort = itemDeviceView7;
        this.llAirtap = itemDeviceView8;
        this.llCa1 = itemDeviceView9;
        this.llCa2 = itemDeviceView10;
        this.llCb1 = itemDeviceView11;
        this.llCb2 = itemDeviceView12;
        this.llLayoutChoose1 = linearLayout;
        this.toolBar = toolbar;
    }

    public ChooseModel getChooseModel() {
        return this.mChooseModel;
    }

    public static ActivityChooseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChooseBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityChooseBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_choose, viewGroup, z, obj);
    }

    public static ActivityChooseBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChooseBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityChooseBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_choose, (ViewGroup) null, false, obj);
    }

    public static ActivityChooseBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChooseBinding bind(View view, Object obj) {
        return (ActivityChooseBinding) bind(obj, view, C1922R.layout.activity_choose);
    }
}
