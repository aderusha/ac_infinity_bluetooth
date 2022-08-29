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
import com.eternal.account.model.ExistModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityExistBinding extends ViewDataBinding {
    public final Button ibNext;
    @Bindable
    protected ExistModel mExistModel;
    public final RelativeLayout root;
    public final Toolbar toolBar;
    public final TextView tvTitle;

    public abstract void setExistModel(ExistModel existModel);

    protected ActivityExistBinding(Object obj, View view, int i, Button button, RelativeLayout relativeLayout, Toolbar toolbar, TextView textView) {
        super(obj, view, i);
        this.ibNext = button;
        this.root = relativeLayout;
        this.toolBar = toolbar;
        this.tvTitle = textView;
    }

    public ExistModel getExistModel() {
        return this.mExistModel;
    }

    public static ActivityExistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityExistBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityExistBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_exist, viewGroup, z, obj);
    }

    public static ActivityExistBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityExistBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityExistBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_exist, (ViewGroup) null, false, obj);
    }

    public static ActivityExistBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityExistBinding bind(View view, Object obj) {
        return (ActivityExistBinding) bind(obj, view, C0997R.layout.activity_exist);
    }
}
