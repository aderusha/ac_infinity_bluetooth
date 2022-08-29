package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.SupportModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivitySupportBinding extends ViewDataBinding {
    @Bindable
    protected SupportModel mSupportModel;
    public final Toolbar toolBar;

    public abstract void setSupportModel(SupportModel supportModel);

    protected ActivitySupportBinding(Object obj, View view, int i, Toolbar toolbar) {
        super(obj, view, i);
        this.toolBar = toolbar;
    }

    public SupportModel getSupportModel() {
        return this.mSupportModel;
    }

    public static ActivitySupportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySupportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivitySupportBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_support, viewGroup, z, obj);
    }

    public static ActivitySupportBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySupportBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivitySupportBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_support, (ViewGroup) null, false, obj);
    }

    public static ActivitySupportBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySupportBinding bind(View view, Object obj) {
        return (ActivitySupportBinding) bind(obj, view, C0997R.layout.activity_support);
    }
}
