package com.eternal.control.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.control.C1760R;
import com.eternal.control.ControlModel;
import com.eternal.control.view.GuQiangCycleDialView;
import com.eternal.widget.guqiang.DoubleAddLayout;
import com.eternal.widget.guqiang.DoubleCloseAddLayout;
import com.eternal.widget.guqiang.FanProgressBar;
import com.eternal.widget.guqiang.SingleAddView;
import com.eternal.widget.guqiang.TimeAmPmWheel;
import com.eternal.widget.guqiang.TimeWheel;
import com.google.android.material.tabs.TabLayout;

public abstract class FragmentControlBinding extends ViewDataBinding {
    public final ConstraintLayout ctMaxMin;
    public final FanProgressBar fpb;
    public final GuQiangCycleDialView gqDial;
    public final ImageView ivFan;
    public final ImageView ivMaxDot;
    public final ImageView ivMinDot;
    public final ImageView ivWindSpeed;
    public final DoubleCloseAddLayout layoutCs;
    public final LinearLayout layoutCycle;
    public final DoubleAddLayout layoutHum;
    public final ConstraintLayout layoutModelType;
    public final LinearLayout layoutTh;
    public final LinearLayout layoutTime;
    public final DoubleAddLayout layoutTmp;
    public final DoubleAddLayout layoutVpd;
    public final View lineCycle;
    public final View lineSched;
    public final RelativeLayout llFan;
    public final LinearLayout llTfp;
    public final LinearLayout llVpd;
    @Bindable
    protected ControlModel mModel;
    public final View maxMinLine;
    public final RelativeLayout rlPower;
    public final TimeWheel sbCycleOff;
    public final TimeWheel sbCycleOn;
    public final TimeAmPmWheel sbFirst;
    public final SingleAddView sbHighHum;
    public final SingleAddView sbHighTmp;
    public final SingleAddView sbHighVpd;
    public final TimeAmPmWheel sbLast;
    public final SingleAddView sbLowHum;
    public final SingleAddView sbLowTmp;
    public final SingleAddView sbLowVpd;
    public final TimeWheel sbTime;
    public final ScrollView scContent;
    public final NestedScrollView scOverlay;
    public final View space;
    public final ScrollView svModelType;
    public final TabLayout tbPort;
    public final TextView tvAutoHumHighDesc;
    public final TextView tvAutoHumLowDesc;
    public final TextView tvAutoTempHighDesc;
    public final TextView tvAutoTempLowDesc;
    public final TextView tvBufferHum;
    public final TextView tvBufferTemp;
    public final TextView tvHum;
    public final TextView tvHumTitle;
    public final TextView tvHumUnit;
    public final TextView tvMaxFan;
    public final ImageView tvMaxFanType;
    public final TextView tvMinFan;
    public final ImageView tvMinFanType;
    public final TextView tvPower;
    public final TextView tvTempUnit;
    public final TextView tvTmp;
    public final TextView tvTmpTitle;
    public final TextView tvVpd;
    public final TextView tvVpdHighDesc;
    public final TextView tvVpdLowDesc;
    public final TextView tvVpdTitle;
    public final TextView tvVpdUnit;
    public final TextView tvWindSpeed;
    public final TextView txtAutomationActivated;
    public final TextView txtMode;
    public final TextView txtModeAuto;
    public final TextView txtModeCycle;
    public final TextView txtModeOff;
    public final TextView txtModeOn;
    public final TextView txtModeSched;
    public final TextView txtModeTimeToOff;
    public final TextView txtModeTimeToOn;
    public final TextView txtModeVpd;
    public final View vBufferLine;
    public final View vMask;
    public final View vMaskAutomation;
    public final View vTouchOutside;

    public abstract void setModel(ControlModel controlModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentControlBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, FanProgressBar fanProgressBar, GuQiangCycleDialView guQiangCycleDialView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, DoubleCloseAddLayout doubleCloseAddLayout, LinearLayout linearLayout, DoubleAddLayout doubleAddLayout, ConstraintLayout constraintLayout2, LinearLayout linearLayout2, LinearLayout linearLayout3, DoubleAddLayout doubleAddLayout2, DoubleAddLayout doubleAddLayout3, View view2, View view3, RelativeLayout relativeLayout, LinearLayout linearLayout4, LinearLayout linearLayout5, View view4, RelativeLayout relativeLayout2, TimeWheel timeWheel, TimeWheel timeWheel2, TimeAmPmWheel timeAmPmWheel, SingleAddView singleAddView, SingleAddView singleAddView2, SingleAddView singleAddView3, TimeAmPmWheel timeAmPmWheel2, SingleAddView singleAddView4, SingleAddView singleAddView5, SingleAddView singleAddView6, TimeWheel timeWheel3, ScrollView scrollView, NestedScrollView nestedScrollView, View view5, ScrollView scrollView2, TabLayout tabLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, ImageView imageView5, TextView textView11, ImageView imageView6, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, TextView textView26, TextView textView27, TextView textView28, TextView textView29, TextView textView30, TextView textView31, View view6, View view7, View view8, View view9) {
        super(obj, view, i);
        this.ctMaxMin = constraintLayout;
        this.fpb = fanProgressBar;
        this.gqDial = guQiangCycleDialView;
        this.ivFan = imageView;
        this.ivMaxDot = imageView2;
        this.ivMinDot = imageView3;
        this.ivWindSpeed = imageView4;
        this.layoutCs = doubleCloseAddLayout;
        this.layoutCycle = linearLayout;
        this.layoutHum = doubleAddLayout;
        this.layoutModelType = constraintLayout2;
        this.layoutTh = linearLayout2;
        this.layoutTime = linearLayout3;
        this.layoutTmp = doubleAddLayout2;
        this.layoutVpd = doubleAddLayout3;
        this.lineCycle = view2;
        this.lineSched = view3;
        this.llFan = relativeLayout;
        this.llTfp = linearLayout4;
        this.llVpd = linearLayout5;
        this.maxMinLine = view4;
        this.rlPower = relativeLayout2;
        this.sbCycleOff = timeWheel;
        this.sbCycleOn = timeWheel2;
        this.sbFirst = timeAmPmWheel;
        this.sbHighHum = singleAddView;
        this.sbHighTmp = singleAddView2;
        this.sbHighVpd = singleAddView3;
        this.sbLast = timeAmPmWheel2;
        this.sbLowHum = singleAddView4;
        this.sbLowTmp = singleAddView5;
        this.sbLowVpd = singleAddView6;
        this.sbTime = timeWheel3;
        this.scContent = scrollView;
        this.scOverlay = nestedScrollView;
        this.space = view5;
        this.svModelType = scrollView2;
        this.tbPort = tabLayout;
        this.tvAutoHumHighDesc = textView;
        this.tvAutoHumLowDesc = textView2;
        this.tvAutoTempHighDesc = textView3;
        this.tvAutoTempLowDesc = textView4;
        this.tvBufferHum = textView5;
        this.tvBufferTemp = textView6;
        this.tvHum = textView7;
        this.tvHumTitle = textView8;
        this.tvHumUnit = textView9;
        this.tvMaxFan = textView10;
        this.tvMaxFanType = imageView5;
        this.tvMinFan = textView11;
        this.tvMinFanType = imageView6;
        this.tvPower = textView12;
        this.tvTempUnit = textView13;
        this.tvTmp = textView14;
        this.tvTmpTitle = textView15;
        this.tvVpd = textView16;
        this.tvVpdHighDesc = textView17;
        this.tvVpdLowDesc = textView18;
        this.tvVpdTitle = textView19;
        this.tvVpdUnit = textView20;
        this.tvWindSpeed = textView21;
        this.txtAutomationActivated = textView22;
        this.txtMode = textView23;
        this.txtModeAuto = textView24;
        this.txtModeCycle = textView25;
        this.txtModeOff = textView26;
        this.txtModeOn = textView27;
        this.txtModeSched = textView28;
        this.txtModeTimeToOff = textView29;
        this.txtModeTimeToOn = textView30;
        this.txtModeVpd = textView31;
        this.vBufferLine = view6;
        this.vMask = view7;
        this.vMaskAutomation = view8;
        this.vTouchOutside = view9;
    }

    public ControlModel getModel() {
        return this.mModel;
    }

    public static FragmentControlBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentControlBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentControlBinding) ViewDataBinding.inflateInternal(layoutInflater, C1760R.layout.fragment_control, viewGroup, z, obj);
    }

    public static FragmentControlBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentControlBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentControlBinding) ViewDataBinding.inflateInternal(layoutInflater, C1760R.layout.fragment_control, (ViewGroup) null, false, obj);
    }

    public static FragmentControlBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentControlBinding bind(View view, Object obj) {
        return (FragmentControlBinding) bind(obj, view, C1760R.layout.fragment_control);
    }
}
