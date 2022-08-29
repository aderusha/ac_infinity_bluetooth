package com.eternal.data.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.data.C1835R;
import com.eternal.data.TargetModel;

public abstract class LayoutTargetBinding extends ViewDataBinding {
    public final EditText editTarget;
    public final ImageView ivClose;
    @Bindable
    protected TargetModel mTargetModel;
    public final TextView tvAnalysis;
    public final TextView tvScore;
    public final TextView tvTargetTitle;

    public abstract void setTargetModel(TargetModel targetModel);

    protected LayoutTargetBinding(Object obj, View view, int i, EditText editText, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.editTarget = editText;
        this.ivClose = imageView;
        this.tvAnalysis = textView;
        this.tvScore = textView2;
        this.tvTargetTitle = textView3;
    }

    public TargetModel getTargetModel() {
        return this.mTargetModel;
    }

    public static LayoutTargetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTargetBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutTargetBinding) ViewDataBinding.inflateInternal(layoutInflater, C1835R.layout.layout_target, viewGroup, z, obj);
    }

    public static LayoutTargetBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTargetBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutTargetBinding) ViewDataBinding.inflateInternal(layoutInflater, C1835R.layout.layout_target, (ViewGroup) null, false, obj);
    }

    public static LayoutTargetBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutTargetBinding bind(View view, Object obj) {
        return (LayoutTargetBinding) bind(obj, view, C1835R.layout.layout_target);
    }
}
