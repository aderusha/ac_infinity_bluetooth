package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.HelpModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityHelpBinding extends ViewDataBinding {
    public final Button ibNext;
    @Bindable
    protected HelpModel mHelpModel;
    public final Toolbar toolBar;
    public final TextView tvContent1;
    public final TextView tvContent2;
    public final TextView tvContent3;
    public final TextView tvContent4;
    public final TextView tvNumber1;
    public final TextView tvNumber2;
    public final TextView tvNumber3;
    public final TextView tvNumber4;
    public final TextView tvTitle1;
    public final TextView tvTitle2;
    public final TextView tvTitle3;
    public final TextView tvTitle4;

    public abstract void setHelpModel(HelpModel helpModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityHelpBinding(Object obj, View view, int i, Button button, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12) {
        super(obj, view, i);
        this.ibNext = button;
        this.toolBar = toolbar;
        this.tvContent1 = textView;
        this.tvContent2 = textView2;
        this.tvContent3 = textView3;
        this.tvContent4 = textView4;
        this.tvNumber1 = textView5;
        this.tvNumber2 = textView6;
        this.tvNumber3 = textView7;
        this.tvNumber4 = textView8;
        this.tvTitle1 = textView9;
        this.tvTitle2 = textView10;
        this.tvTitle3 = textView11;
        this.tvTitle4 = textView12;
    }

    public HelpModel getHelpModel() {
        return this.mHelpModel;
    }

    public static ActivityHelpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHelpBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityHelpBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_help, viewGroup, z, obj);
    }

    public static ActivityHelpBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHelpBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityHelpBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_help, (ViewGroup) null, false, obj);
    }

    public static ActivityHelpBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityHelpBinding bind(View view, Object obj) {
        return (ActivityHelpBinding) bind(obj, view, C1922R.layout.activity_help);
    }
}
