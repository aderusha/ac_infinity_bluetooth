package com.eternal.device.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1922R;
import com.eternal.device.model.PromptModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityPromptBinding extends ViewDataBinding {
    public final Button ibCreate;
    public final Button ibLogin;
    @Bindable
    protected PromptModel mPromptModel;
    public final Toolbar toolBar;
    public final TextView tvContent1;
    public final TextView tvContent2;
    public final TextView tvContent3;
    public final ImageView tvNumber1;
    public final ImageView tvNumber2;
    public final ImageView tvNumber3;
    public final TextView tvTitle1;
    public final TextView tvTitle2;
    public final TextView tvTitle3;

    public abstract void setPromptModel(PromptModel promptModel);

    protected ActivityPromptBinding(Object obj, View view, int i, Button button, Button button2, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView4, TextView textView5, TextView textView6) {
        super(obj, view, i);
        this.ibCreate = button;
        this.ibLogin = button2;
        this.toolBar = toolbar;
        this.tvContent1 = textView;
        this.tvContent2 = textView2;
        this.tvContent3 = textView3;
        this.tvNumber1 = imageView;
        this.tvNumber2 = imageView2;
        this.tvNumber3 = imageView3;
        this.tvTitle1 = textView4;
        this.tvTitle2 = textView5;
        this.tvTitle3 = textView6;
    }

    public PromptModel getPromptModel() {
        return this.mPromptModel;
    }

    public static ActivityPromptBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPromptBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityPromptBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_prompt, viewGroup, z, obj);
    }

    public static ActivityPromptBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPromptBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityPromptBinding) ViewDataBinding.inflateInternal(layoutInflater, C1922R.layout.activity_prompt, (ViewGroup) null, false, obj);
    }

    public static ActivityPromptBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPromptBinding bind(View view, Object obj) {
        return (ActivityPromptBinding) bind(obj, view, C1922R.layout.activity_prompt);
    }
}
