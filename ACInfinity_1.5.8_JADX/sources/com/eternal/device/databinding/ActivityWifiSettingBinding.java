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
import com.eternal.base.StatueSwitch;
import com.eternal.device.C1922R;
import com.eternal.device.model.WiFiSettingModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityWifiSettingBinding extends ViewDataBinding {
    public final Button btSavePassword;
    public final RelativeLayout createPassword;
    public final TextView createPasswordTitle;
    public final EditText etPwd;
    public final Button ibNext;
    public final LinearLayout llSheetDialog;
    public final ConstraintLayout llWifiName;
    @Bindable
    protected WiFiSettingModel mWifiSettingModel;
    public final ProgressBar pbLoading;
    public final RelativeLayout rlItem;
    public final LinearLayout root;

    /* renamed from: sb */
    public final StatueSwitch f171sb;
    public final Toolbar toolBar;
    public final TextView tvContent;
    public final TextView tvHelp;
    public final TextView tvWifiName;
    public final TextView tvWifiNameTitle;
    public final TextView txtConnectTime;

    public abstract void setWifiSettingModel(WiFiSettingModel wiFiSettingModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityWifiSettingBinding(Object obj, View view, int i, Button button, RelativeLayout relativeLayout, TextView textView, EditText editText, Button button2, LinearLayout linearLayout, ConstraintLayout constraintLayout, ProgressBar progressBar, RelativeLayout relativeLayout2, LinearLayout linearLayout2, StatueSwitch statueSwitch, Toolbar toolbar, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.btSavePassword = button;
        this.createPassword = relativeLayout;
        this.createPasswordTitle = textView;
        this.etPwd = editText;
        this.ibNext = button2;
        this.llSheetDialog = linearLayout;
        this.llWifiName = constraintLayout;
        this.pbLoading = progressBar;
        this.rlItem = relativeLayout2;
        this.root = linearLayout2;
        this.f171sb = statueSwitch;
        this.toolBar = toolbar;
        this.tvContent = textView2;
        this.tvHelp = textView3;
        this.tvWifiName = textView4;
        this.tvWifiNameTitle = textView5;
        this.txtConnectTime = textView6;
    }

    public WiFiSettingModel getWifiSettingModel() {
        return this.mWifiSettingModel;
    }

    public static ActivityWifiSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiSettingBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityWifiSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_wifi_setting, viewGroup, z, obj);
    }

    public static ActivityWifiSettingBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiSettingBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityWifiSettingBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_wifi_setting, (ViewGroup) null, false, obj);
    }

    public static ActivityWifiSettingBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityWifiSettingBinding bind(View view, Object obj) {
        return (ActivityWifiSettingBinding) bind(obj, view, C1922R.layout.activity_wifi_setting);
    }
}
