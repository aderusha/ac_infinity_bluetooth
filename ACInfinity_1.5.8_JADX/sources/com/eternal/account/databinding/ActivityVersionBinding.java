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
import com.eternal.account.model.VersionModel;

public abstract class ActivityVersionBinding extends ViewDataBinding {
    public final Button ibNext;
    @Bindable
    protected VersionModel mVersionModel;
    public final RelativeLayout root;
    public final RelativeLayout toolBar;
    public final TextView tvTip;
    public final TextView txtConnectTime;

    public abstract void setVersionModel(VersionModel versionModel);

    protected ActivityVersionBinding(Object obj, View view, int i, Button button, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView, TextView textView2) {
        super(obj, view, i);
        this.ibNext = button;
        this.root = relativeLayout;
        this.toolBar = relativeLayout2;
        this.tvTip = textView;
        this.txtConnectTime = textView2;
    }

    public VersionModel getVersionModel() {
        return this.mVersionModel;
    }

    public static ActivityVersionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVersionBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityVersionBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_version, viewGroup, z, obj);
    }

    public static ActivityVersionBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVersionBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityVersionBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_version, (ViewGroup) null, false, obj);
    }

    public static ActivityVersionBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVersionBinding bind(View view, Object obj) {
        return (ActivityVersionBinding) bind(obj, view, C0997R.layout.activity_version);
    }
}
