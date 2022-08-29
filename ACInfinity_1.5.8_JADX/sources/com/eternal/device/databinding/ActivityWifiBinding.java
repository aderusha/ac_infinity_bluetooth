package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.WiFiModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityWifiBinding extends ViewDataBinding {
    public final Button ibNext;
    public final ImageView ivOval;
    public final LinearLayout llContent;
    public final LinearLayout llSheetDialog;
    @Bindable
    protected WiFiModel mWifiModel;
    public final ConstraintLayout root;
    public final Toolbar toolBar;
    public final TextView tvCancel;
    public final TextView tvContent;
    public final TextView tvTitle;

    public abstract void setWifiModel(WiFiModel wiFiModel);

    protected ActivityWifiBinding(Object obj, View view, int i, Button button, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, ConstraintLayout constraintLayout, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ibNext = button;
        this.ivOval = imageView;
        this.llContent = linearLayout;
        this.llSheetDialog = linearLayout2;
        this.root = constraintLayout;
        this.toolBar = toolbar;
        this.tvCancel = textView;
        this.tvContent = textView2;
        this.tvTitle = textView3;
    }

    public WiFiModel getWifiModel() {
        return this.mWifiModel;
    }

    public static ActivityWifiBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityWifiBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_wifi, viewGroup, z, obj);
    }

    public static ActivityWifiBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityWifiBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_wifi, (ViewGroup) null, false, obj);
    }

    public static ActivityWifiBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiBinding bind(View view, Object obj) {
        return (ActivityWifiBinding) bind(obj, view, C1922R.layout.activity_wifi);
    }
}
