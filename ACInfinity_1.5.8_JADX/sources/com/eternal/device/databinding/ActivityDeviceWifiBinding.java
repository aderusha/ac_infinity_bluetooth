package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.AddWiFiModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityDeviceWifiBinding extends ViewDataBinding {
    public final Button ibOk;
    public final ImageView imgBluetooth;
    @Bindable
    protected AddWiFiModel mAddWifiModel;
    public final RelativeLayout rlOval;
    public final Toolbar toolBar;
    public final TextView tvContent;
    public final TextView tvHelp;
    public final TextView tvTitle;

    public abstract void setAddWifiModel(AddWiFiModel addWiFiModel);

    protected ActivityDeviceWifiBinding(Object obj, View view, int i, Button button, ImageView imageView, RelativeLayout relativeLayout, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ibOk = button;
        this.imgBluetooth = imageView;
        this.rlOval = relativeLayout;
        this.toolBar = toolbar;
        this.tvContent = textView;
        this.tvHelp = textView2;
        this.tvTitle = textView3;
    }

    public AddWiFiModel getAddWifiModel() {
        return this.mAddWifiModel;
    }

    public static ActivityDeviceWifiBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceWifiBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityDeviceWifiBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_device_wifi, viewGroup, z, obj);
    }

    public static ActivityDeviceWifiBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceWifiBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityDeviceWifiBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_device_wifi, (ViewGroup) null, false, obj);
    }

    public static ActivityDeviceWifiBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityDeviceWifiBinding bind(View view, Object obj) {
        return (ActivityDeviceWifiBinding) bind(obj, view, C1922R.layout.activity_device_wifi);
    }
}
