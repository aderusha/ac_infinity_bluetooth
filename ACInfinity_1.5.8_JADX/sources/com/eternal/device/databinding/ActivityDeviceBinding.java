package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.AddModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityDeviceBinding extends ViewDataBinding {
    public final Button ibOk;
    public final ImageView imgBluetooth;
    @Bindable
    protected AddModel mModel;
    public final ProgressBar pbLoading;
    public final RelativeLayout rlOval;
    public final Toolbar toolBar;
    public final TextView tvContent;
    public final TextView tvHelp;
    public final TextView tvTitle;

    public abstract void setModel(AddModel addModel);

    protected ActivityDeviceBinding(Object obj, View view, int i, Button button, ImageView imageView, ProgressBar progressBar, RelativeLayout relativeLayout, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ibOk = button;
        this.imgBluetooth = imageView;
        this.pbLoading = progressBar;
        this.rlOval = relativeLayout;
        this.toolBar = toolbar;
        this.tvContent = textView;
        this.tvHelp = textView2;
        this.tvTitle = textView3;
    }

    public AddModel getModel() {
        return this.mModel;
    }

    public static ActivityDeviceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityDeviceBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_device, viewGroup, z, obj);
    }

    public static ActivityDeviceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityDeviceBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_device, (ViewGroup) null, false, obj);
    }

    public static ActivityDeviceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceBinding bind(View view, Object obj) {
        return (ActivityDeviceBinding) bind(obj, view, C1922R.layout.activity_device);
    }
}
