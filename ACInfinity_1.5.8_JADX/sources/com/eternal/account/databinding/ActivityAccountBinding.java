package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.AccountModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityAccountBinding extends ViewDataBinding {
    @Bindable
    protected AccountModel mAccountModel;
    public final Toolbar toolBar;

    public abstract void setAccountModel(AccountModel accountModel);

    protected ActivityAccountBinding(Object obj, View view, int i, Toolbar toolbar) {
        super(obj, view, i);
        this.toolBar = toolbar;
    }

    public AccountModel getAccountModel() {
        return this.mAccountModel;
    }

    public static ActivityAccountBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityAccountBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_account, viewGroup, z, obj);
    }

    public static ActivityAccountBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityAccountBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_account, (ViewGroup) null, false, obj);
    }

    public static ActivityAccountBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAccountBinding bind(View view, Object obj) {
        return (ActivityAccountBinding) bind(obj, view, C0997R.layout.activity_account);
    }
}
