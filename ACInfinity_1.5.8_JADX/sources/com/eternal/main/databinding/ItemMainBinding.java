package com.eternal.main.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.main.C2343R;
import com.eternal.main.model.ItemModel;
import com.eternal.widget.guqiang.ValueRangeSlider;

public abstract class ItemMainBinding extends ViewDataBinding {
    public final ConstraintLayout clContent;
    public final TextView deviceType;
    public final ImageView ivBlueIcon;
    public final ImageView ivHumStatus;
    public final ImageView ivTempStatus;
    public final ImageView ivTypeIcon;
    public final ImageView ivVpdstatus;
    public final ImageView ivWifiIcon;
    public final ImageView ivWindSpeed;
    public final ImageView ivWindStatus;
    public final LinearLayout llContent;
    public final LinearLayout llDeviceType;
    @Bindable
    protected ItemModel mItem;
    public final ProgressBar pbLoading;
    public final LayoutPortBinding rlPort0;
    public final LayoutPortBinding rlPort1;
    public final LayoutPortBinding rlPort2;
    public final LayoutPortBinding rlPort3;
    public final LayoutPortBinding rlPort4;
    public final LayoutPortBinding rlPort5;
    public final LayoutPortBinding rlPort6;
    public final LayoutPortBinding rlPort7;
    public final LayoutPortBinding rlPort8;
    public final LayoutPortBinding rlPort9;
    public final LayoutPortBinding rlPortAll;
    public final RelativeLayout rlPower;
    public final RelativeLayout rlTime;
    public final TextView tvDel;
    public final TextView tvDeviceName;
    public final TextView tvHum;
    public final TextView tvHum2;
    public final TextView tvHumUnit;
    public final TextView tvPower;
    public final TextView tvTempUnit;
    public final TextView tvTime;
    public final TextView tvTmp;
    public final TextView tvVpd;
    public final TextView tvVpdUnit;
    public final TextView tvWindSpeed;
    public final ValueRangeSlider vrsHum;
    public final ValueRangeSlider vrsTmp;

    public abstract void setItem(ItemModel itemModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ItemMainBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, TextView textView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, ImageView imageView6, ImageView imageView7, ImageView imageView8, LinearLayout linearLayout, LinearLayout linearLayout2, ProgressBar progressBar, LayoutPortBinding layoutPortBinding, LayoutPortBinding layoutPortBinding2, LayoutPortBinding layoutPortBinding3, LayoutPortBinding layoutPortBinding4, LayoutPortBinding layoutPortBinding5, LayoutPortBinding layoutPortBinding6, LayoutPortBinding layoutPortBinding7, LayoutPortBinding layoutPortBinding8, LayoutPortBinding layoutPortBinding9, LayoutPortBinding layoutPortBinding10, LayoutPortBinding layoutPortBinding11, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, ValueRangeSlider valueRangeSlider, ValueRangeSlider valueRangeSlider2) {
        super(obj, view, i);
        this.clContent = constraintLayout;
        this.deviceType = textView;
        this.ivBlueIcon = imageView;
        this.ivHumStatus = imageView2;
        this.ivTempStatus = imageView3;
        this.ivTypeIcon = imageView4;
        this.ivVpdstatus = imageView5;
        this.ivWifiIcon = imageView6;
        this.ivWindSpeed = imageView7;
        this.ivWindStatus = imageView8;
        this.llContent = linearLayout;
        this.llDeviceType = linearLayout2;
        this.pbLoading = progressBar;
        this.rlPort0 = layoutPortBinding;
        this.rlPort1 = layoutPortBinding2;
        this.rlPort2 = layoutPortBinding3;
        this.rlPort3 = layoutPortBinding4;
        this.rlPort4 = layoutPortBinding5;
        this.rlPort5 = layoutPortBinding6;
        this.rlPort6 = layoutPortBinding7;
        this.rlPort7 = layoutPortBinding8;
        this.rlPort8 = layoutPortBinding9;
        this.rlPort9 = layoutPortBinding10;
        this.rlPortAll = layoutPortBinding11;
        this.rlPower = relativeLayout;
        this.rlTime = relativeLayout2;
        this.tvDel = textView2;
        this.tvDeviceName = textView3;
        this.tvHum = textView4;
        this.tvHum2 = textView5;
        this.tvHumUnit = textView6;
        this.tvPower = textView7;
        this.tvTempUnit = textView8;
        this.tvTime = textView9;
        this.tvTmp = textView10;
        this.tvVpd = textView11;
        this.tvVpdUnit = textView12;
        this.tvWindSpeed = textView13;
        this.vrsHum = valueRangeSlider;
        this.vrsTmp = valueRangeSlider2;
    }

    public ItemModel getItem() {
        return this.mItem;
    }

    public static ItemMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ItemMainBinding) ViewDataBinding.inflateInternal(layoutInflater, C2343R.layout.item_main, viewGroup, z, obj);
    }

    public static ItemMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMainBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ItemMainBinding) ViewDataBinding.inflateInternal(layoutInflater, C2343R.layout.item_main, (ViewGroup) null, false, obj);
    }

    public static ItemMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ItemMainBinding bind(View view, Object obj) {
        return (ItemMainBinding) bind(obj, view, C2343R.layout.item_main);
    }
}
