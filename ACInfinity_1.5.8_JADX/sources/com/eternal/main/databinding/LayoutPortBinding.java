package com.eternal.main.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.main.C2343R;
import com.eternal.main.model.PortModel;

public abstract class LayoutPortBinding extends ViewDataBinding {
    public final ImageView ivPortWindSpeed;
    public final ImageView ivPortWindStatus;
    public final ImageView ivTypeIcon;
    @Bindable
    protected Boolean mOpen;
    @Bindable
    protected PortModel mPort;
    public final RelativeLayout rlPortWind;
    public final RelativeLayout rlWindStatus;
    public final TextView tvPortWindSpeed;

    public abstract void setOpen(Boolean bool);

    public abstract void setPort(PortModel portModel);

    protected LayoutPortBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView) {
        super(obj, view, i);
        this.ivPortWindSpeed = imageView;
        this.ivPortWindStatus = imageView2;
        this.ivTypeIcon = imageView3;
        this.rlPortWind = relativeLayout;
        this.rlWindStatus = relativeLayout2;
        this.tvPortWindSpeed = textView;
    }

    public PortModel getPort() {
        return this.mPort;
    }

    public Boolean getOpen() {
        return this.mOpen;
    }

    public static LayoutPortBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutPortBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (LayoutPortBinding) ViewDataBinding.inflateInternal(layoutInflater, C2343R.layout.layout_port, viewGroup, z, obj);
    }

    public static LayoutPortBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutPortBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (LayoutPortBinding) ViewDataBinding.inflateInternal(layoutInflater, C2343R.layout.layout_port, (ViewGroup) null, false, obj);
    }

    public static LayoutPortBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static LayoutPortBinding bind(View view, Object obj) {
        return (LayoutPortBinding) bind(obj, view, C2343R.layout.layout_port);
    }
}
