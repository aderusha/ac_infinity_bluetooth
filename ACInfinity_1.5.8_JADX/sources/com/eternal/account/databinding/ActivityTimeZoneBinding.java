package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.TimeZoneModel;
import com.eternal.base.SlideRecyclerView;
import com.eternal.widget.guqiang.ProgressToolbar;

public abstract class ActivityTimeZoneBinding extends ViewDataBinding {
    @Bindable
    protected TimeZoneModel mTimeZoneModel;
    public final LinearLayout root;
    public final SlideRecyclerView srvWithYou;
    public final ProgressToolbar toolBar;

    public abstract void setTimeZoneModel(TimeZoneModel timeZoneModel);

    protected ActivityTimeZoneBinding(Object obj, View view, int i, LinearLayout linearLayout, SlideRecyclerView slideRecyclerView, ProgressToolbar progressToolbar) {
        super(obj, view, i);
        this.root = linearLayout;
        this.srvWithYou = slideRecyclerView;
        this.toolBar = progressToolbar;
    }

    public TimeZoneModel getTimeZoneModel() {
        return this.mTimeZoneModel;
    }

    public static ActivityTimeZoneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTimeZoneBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityTimeZoneBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_time_zone, viewGroup, z, obj);
    }

    public static ActivityTimeZoneBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTimeZoneBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityTimeZoneBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_time_zone, (ViewGroup) null, false, obj);
    }

    public static ActivityTimeZoneBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTimeZoneBinding bind(View view, Object obj) {
        return (ActivityTimeZoneBinding) bind(obj, view, C0997R.layout.activity_time_zone);
    }
}
