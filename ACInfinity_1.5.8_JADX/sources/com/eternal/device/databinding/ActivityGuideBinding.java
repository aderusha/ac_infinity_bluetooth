package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.GuideModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityGuideBinding extends ViewDataBinding {
    public final Button ibNext;
    public final ImageView ivTip;
    @Bindable
    protected GuideModel mGuideModel;
    public final ProgressBar pbLoading;
    public final Toolbar toolBar;
    public final TextView tvContent;
    public final TextView tvHelp;
    public final TextView tvTitle;
    public final TextView tvTitlePermission;

    public abstract void setGuideModel(GuideModel guideModel);

    protected ActivityGuideBinding(Object obj, View view, int i, Button button, ImageView imageView, ProgressBar progressBar, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.ibNext = button;
        this.ivTip = imageView;
        this.pbLoading = progressBar;
        this.toolBar = toolbar;
        this.tvContent = textView;
        this.tvHelp = textView2;
        this.tvTitle = textView3;
        this.tvTitlePermission = textView4;
    }

    public GuideModel getGuideModel() {
        return this.mGuideModel;
    }

    public static ActivityGuideBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGuideBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityGuideBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_guide, viewGroup, z, obj);
    }

    public static ActivityGuideBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGuideBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityGuideBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_guide, (ViewGroup) null, false, obj);
    }

    public static ActivityGuideBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityGuideBinding bind(View view, Object obj) {
        return (ActivityGuideBinding) bind(obj, view, C1922R.layout.activity_guide);
    }
}
