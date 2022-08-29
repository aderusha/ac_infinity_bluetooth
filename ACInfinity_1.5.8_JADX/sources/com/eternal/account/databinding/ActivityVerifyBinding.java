package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.VerifyModel;
import com.eternal.widget.guqiang.Toolbar;
import com.eternal.widget.pinview.PinView;

public abstract class ActivityVerifyBinding extends ViewDataBinding {
    public final RelativeLayout llTip;
    @Bindable
    protected VerifyModel mVerifyModel;
    public final ProgressBar pbLoading;
    public final PinView pvCode;
    public final RelativeLayout root;
    public final Toolbar toolBar;
    public final TextView tvEmail;
    public final TextView tvErr;
    public final TextView tvTip;
    public final TextView tvTitle;

    public abstract void setVerifyModel(VerifyModel verifyModel);

    protected ActivityVerifyBinding(Object obj, View view, int i, RelativeLayout relativeLayout, ProgressBar progressBar, PinView pinView, RelativeLayout relativeLayout2, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.llTip = relativeLayout;
        this.pbLoading = progressBar;
        this.pvCode = pinView;
        this.root = relativeLayout2;
        this.toolBar = toolbar;
        this.tvEmail = textView;
        this.tvErr = textView2;
        this.tvTip = textView3;
        this.tvTitle = textView4;
    }

    public VerifyModel getVerifyModel() {
        return this.mVerifyModel;
    }

    public static ActivityVerifyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVerifyBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityVerifyBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_verify, viewGroup, z, obj);
    }

    public static ActivityVerifyBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVerifyBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityVerifyBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_verify, (ViewGroup) null, false, obj);
    }

    public static ActivityVerifyBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityVerifyBinding bind(View view, Object obj) {
        return (ActivityVerifyBinding) bind(obj, view, C0997R.layout.activity_verify);
    }
}
