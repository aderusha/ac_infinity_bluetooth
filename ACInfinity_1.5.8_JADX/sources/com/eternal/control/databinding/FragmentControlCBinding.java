package com.eternal.control.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.control.C1760R;
import com.eternal.control.ControlCModel;
import com.eternal.control.view.GuQiangCycleDialView;
import com.eternal.widget.guqiang.DoubleAddLayout;
import com.eternal.widget.guqiang.SingleAddView;

public abstract class FragmentControlCBinding extends ViewDataBinding {
    public final GuQiangCycleDialView gqDial;
    public final DoubleAddLayout layoutHum;
    public final DoubleAddLayout layoutTmp;
    @Bindable
    protected ControlCModel mCModel;
    public final SingleAddView sbHighHum;
    public final SingleAddView sbHighTmp;
    public final SingleAddView sbLowHum;
    public final SingleAddView sbLowTmp;
    public final ScrollView scContent;
    public final NestedScrollView scOverlay;
    public final View space;
    public final TextView tvHum;
    public final TextView tvHumTitle;
    public final TextView tvHumUnit;
    public final TextView tvTempUnit;
    public final TextView tvTmp;
    public final TextView tvTmpTitle;
    public final TextView tvVpd;
    public final TextView tvVpdUnit;
    public final View vMask;

    public abstract void setCModel(ControlCModel controlCModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentControlCBinding(Object obj, View view, int i, GuQiangCycleDialView guQiangCycleDialView, DoubleAddLayout doubleAddLayout, DoubleAddLayout doubleAddLayout2, SingleAddView singleAddView, SingleAddView singleAddView2, SingleAddView singleAddView3, SingleAddView singleAddView4, ScrollView scrollView, NestedScrollView nestedScrollView, View view2, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, View view3) {
        super(obj, view, i);
        this.gqDial = guQiangCycleDialView;
        this.layoutHum = doubleAddLayout;
        this.layoutTmp = doubleAddLayout2;
        this.sbHighHum = singleAddView;
        this.sbHighTmp = singleAddView2;
        this.sbLowHum = singleAddView3;
        this.sbLowTmp = singleAddView4;
        this.scContent = scrollView;
        this.scOverlay = nestedScrollView;
        this.space = view2;
        this.tvHum = textView;
        this.tvHumTitle = textView2;
        this.tvHumUnit = textView3;
        this.tvTempUnit = textView4;
        this.tvTmp = textView5;
        this.tvTmpTitle = textView6;
        this.tvVpd = textView7;
        this.tvVpdUnit = textView8;
        this.vMask = view3;
    }

    public ControlCModel getCModel() {
        return this.mCModel;
    }

    public static FragmentControlCBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentControlCBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentControlCBinding) ViewDataBinding.inflateInternal(layoutInflater, C1760R.layout.fragment_control_c, viewGroup, z, obj);
    }

    public static FragmentControlCBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentControlCBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentControlCBinding) ViewDataBinding.inflateInternal(layoutInflater, C1760R.layout.fragment_control_c, (ViewGroup) null, false, obj);
    }

    public static FragmentControlCBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentControlCBinding bind(View view, Object obj) {
        return (FragmentControlCBinding) bind(obj, view, C1760R.layout.fragment_control_c);
    }
}
