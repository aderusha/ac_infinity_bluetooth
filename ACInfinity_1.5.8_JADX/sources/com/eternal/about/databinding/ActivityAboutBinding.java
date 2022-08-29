package com.eternal.about.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.about.AboutModel;
import com.eternal.about.C0969R;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityAboutBinding extends ViewDataBinding {
    public final TextView btPurchase;
    public final TextView btSupport;
    public final TextView btTeams;
    @Bindable
    protected AboutModel mModel;
    public final Toolbar toolBar;
    public final TextView tvCompanyInfo;
    public final TextView tvVersion;

    public abstract void setModel(AboutModel aboutModel);

    protected ActivityAboutBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, Toolbar toolbar, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.btPurchase = textView;
        this.btSupport = textView2;
        this.btTeams = textView3;
        this.toolBar = toolbar;
        this.tvCompanyInfo = textView4;
        this.tvVersion = textView5;
    }

    public AboutModel getModel() {
        return this.mModel;
    }

    public static ActivityAboutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAboutBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityAboutBinding) ViewDataBinding.inflateInternal(layoutInflater, C0969R.layout.activity_about, viewGroup, z, obj);
    }

    public static ActivityAboutBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAboutBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityAboutBinding) ViewDataBinding.inflateInternal(layoutInflater, C0969R.layout.activity_about, (ViewGroup) null, false, obj);
    }

    public static ActivityAboutBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityAboutBinding bind(View view, Object obj) {
        return (ActivityAboutBinding) bind(obj, view, C0969R.layout.activity_about);
    }
}
