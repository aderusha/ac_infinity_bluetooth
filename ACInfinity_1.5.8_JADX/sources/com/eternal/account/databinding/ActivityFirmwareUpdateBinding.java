package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.FirmwareUpdateModel;
import com.eternal.widget.guqiang.ProgressToolbar;

public abstract class ActivityFirmwareUpdateBinding extends ViewDataBinding {
    public final Button ibOk;
    @Bindable
    protected FirmwareUpdateModel mFirmwareUpdateModel;
    public final RelativeLayout rlOval;
    public final ProgressToolbar toolBar;
    public final TextView tvContent;
    public final TextView tvTitle;

    public abstract void setFirmwareUpdateModel(FirmwareUpdateModel firmwareUpdateModel);

    protected ActivityFirmwareUpdateBinding(Object obj, View view, int i, Button button, RelativeLayout relativeLayout, ProgressToolbar progressToolbar, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ibOk = button;
        this.rlOval = relativeLayout;
        this.toolBar = progressToolbar;
        this.tvContent = textView;
        this.tvTitle = textView2;
    }

    public FirmwareUpdateModel getFirmwareUpdateModel() {
        return this.mFirmwareUpdateModel;
    }

    public static ActivityFirmwareUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFirmwareUpdateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityFirmwareUpdateBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_firmware_update, viewGroup, z, obj);
    }

    public static ActivityFirmwareUpdateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFirmwareUpdateBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityFirmwareUpdateBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_firmware_update, (ViewGroup) null, false, obj);
    }

    public static ActivityFirmwareUpdateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFirmwareUpdateBinding bind(View view, Object obj) {
        return (ActivityFirmwareUpdateBinding) bind(obj, view, C0997R.layout.activity_firmware_update);
    }
}
