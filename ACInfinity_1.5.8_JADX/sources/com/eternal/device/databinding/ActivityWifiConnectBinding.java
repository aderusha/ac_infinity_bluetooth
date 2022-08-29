package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.WiFiConnectModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityWifiConnectBinding extends ViewDataBinding {
    public final Button btSavePassword;
    public final RelativeLayout createPassword;
    public final TextView createPasswordTitle;
    public final EditText etPwd;
    public final Button ibNext;
    public final LinearLayout llSheetDialog;
    public final ConstraintLayout llWifiName;
    @Bindable
    protected WiFiConnectModel mWifiConnectModel;
    public final ProgressBar pbLoading;
    public final LinearLayout root;
    public final Toolbar toolBar;
    public final TextView tvContent;
    public final TextView tvHelp;
    public final TextView tvTitle;
    public final TextView tvWifiName;
    public final TextView tvWifiNameTitle;

    public abstract void setWifiConnectModel(WiFiConnectModel wiFiConnectModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityWifiConnectBinding(Object obj, View view, int i, Button button, RelativeLayout relativeLayout, TextView textView, EditText editText, Button button2, LinearLayout linearLayout, ConstraintLayout constraintLayout, ProgressBar progressBar, LinearLayout linearLayout2, Toolbar toolbar, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.btSavePassword = button;
        this.createPassword = relativeLayout;
        this.createPasswordTitle = textView;
        this.etPwd = editText;
        this.ibNext = button2;
        this.llSheetDialog = linearLayout;
        this.llWifiName = constraintLayout;
        this.pbLoading = progressBar;
        this.root = linearLayout2;
        this.toolBar = toolbar;
        this.tvContent = textView2;
        this.tvHelp = textView3;
        this.tvTitle = textView4;
        this.tvWifiName = textView5;
        this.tvWifiNameTitle = textView6;
    }

    public WiFiConnectModel getWifiConnectModel() {
        return this.mWifiConnectModel;
    }

    public static ActivityWifiConnectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiConnectBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityWifiConnectBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_wifi_connect, viewGroup, z, obj);
    }

    public static ActivityWifiConnectBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiConnectBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityWifiConnectBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_wifi_connect, (ViewGroup) null, false, obj);
    }

    public static ActivityWifiConnectBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiConnectBinding bind(View view, Object obj) {
        return (ActivityWifiConnectBinding) bind(obj, view, C1922R.layout.activity_wifi_connect);
    }
}
