package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.WiFiListModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityWifiLlistBinding extends ViewDataBinding {
    @Bindable
    protected WiFiListModel mWifiListModel;
    public final Toolbar toolBar;
    public final TextView tvContent;
    public final TextView tvSetting;
    public final TextView tvTitle;

    public abstract void setWifiListModel(WiFiListModel wiFiListModel);

    protected ActivityWifiLlistBinding(Object obj, View view, int i, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.toolBar = toolbar;
        this.tvContent = textView;
        this.tvSetting = textView2;
        this.tvTitle = textView3;
    }

    public WiFiListModel getWifiListModel() {
        return this.mWifiListModel;
    }

    public static ActivityWifiLlistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiLlistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityWifiLlistBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_wifi_llist, viewGroup, z, obj);
    }

    public static ActivityWifiLlistBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiLlistBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityWifiLlistBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_wifi_llist, (ViewGroup) null, false, obj);
    }

    public static ActivityWifiLlistBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiLlistBinding bind(View view, Object obj) {
        return (ActivityWifiLlistBinding) bind(obj, view, C1922R.layout.activity_wifi_llist);
    }
}
