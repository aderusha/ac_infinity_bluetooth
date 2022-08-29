package com.eternal.notification.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.base.StatueSwitch;
import com.eternal.notification.C2420R;
import com.eternal.notification.model.AutomationModelV4;
import com.eternal.notification.view.TitleSelectView;
import com.eternal.widget.ExpandableLayout;
import com.eternal.widget.guqiang.DoubleAddLayout;
import com.eternal.widget.guqiang.FanProgressBar;
import com.eternal.widget.guqiang.RangeSeekBar;
import com.eternal.widget.guqiang.SingleAddView;
import com.zyyoona7.wheel.WheelView;

public abstract class FragmentAutomationV4Binding extends ViewDataBinding {
    public final RelativeLayout btnDelete;
    public final CheckBox cb1;
    public final CheckBox cb2;
    public final CheckBox cb3;
    public final CheckBox cb4;
    public final CheckBox cb5;
    public final CheckBox cb6;
    public final CheckBox cb7;
    public final ExpandableLayout elCycle;
    public final ExpandableLayout elOn;
    public final ExpandableLayout elTmpHum;
    public final ExpandableLayout elVpd;
    public final EditText etEditName;
    public final FanProgressBar fpbOff;
    public final FanProgressBar fpbOn;
    public final ImageView ivLevelOff;
    public final ImageView ivLevelOn;
    public final DoubleAddLayout layoutHum;
    public final DoubleAddLayout layoutTmp;
    public final DoubleAddLayout layoutVpd;
    public final LinearLayout llContent;
    public final LinearLayout llCycleContainer;
    public final LinearLayout llCycleOffTime;
    public final LinearLayout llCycleOn;
    public final LinearLayout llEndTime;
    public final LinearLayout llHumContainer;
    public final LinearLayout llLevel;
    public final ConstraintLayout llLevelOff;
    public final ConstraintLayout llLevelOn;
    public final LinearLayout llOnContainer;
    public final LinearLayout llStartTime;
    public final LinearLayout llTmphumContainer;
    public final LinearLayout llVpdContainer;
    @Bindable
    protected AutomationModelV4 mAutoModelV4;
    public final TitleSelectView modeCycle;
    public final TitleSelectView modeOn;
    public final TitleSelectView modeTempHum;
    public final TitleSelectView modeVpd;
    public final RangeSeekBar rsbHum;
    public final RangeSeekBar rsbTemp;
    public final RangeSeekBar rsbVpd;

    /* renamed from: sb */
    public final StatueSwitch f214sb;
    public final SingleAddView sbHighHum;
    public final SingleAddView sbHighTmp;
    public final SingleAddView sbHighVpd;
    public final SingleAddView sbLowHum;
    public final SingleAddView sbLowTmp;
    public final SingleAddView sbLowVpd;
    public final TextView tvCycleOffDes;
    public final TextView tvCycleOnDes;
    public final TextView tvEndTimeDes;
    public final TextView tvHumMin;
    public final TextView tvLevelOffDes;
    public final TextView tvLevelOffTitle;
    public final TextView tvLevelOnDes;
    public final TextView tvLevelOnTitle;
    public final TextView tvNameSuggest;
    public final TextView tvStartTimeDes;
    public final TextView tvTempMax;
    public final TextView tvTempMin;
    public final TextView tvVpdMin;
    public final View vCycleLine;
    public final View vTimeLine;
    public final WheelView wvCycleOffHour;
    public final WheelView wvCycleOffMin;
    public final WheelView wvCycleOnHour;
    public final WheelView wvCycleOnMin;
    public final WheelView wvEndAmOrPm;
    public final WheelView wvEndHour;
    public final WheelView wvEndMin;
    public final WheelView wvStartAmOrPm;
    public final WheelView wvStartHour;
    public final WheelView wvStartMin;

    public abstract void setAutoModelV4(AutomationModelV4 automationModelV4);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentAutomationV4Binding(Object obj, View view, int i, RelativeLayout relativeLayout, CheckBox checkBox, CheckBox checkBox2, CheckBox checkBox3, CheckBox checkBox4, CheckBox checkBox5, CheckBox checkBox6, CheckBox checkBox7, ExpandableLayout expandableLayout, ExpandableLayout expandableLayout2, ExpandableLayout expandableLayout3, ExpandableLayout expandableLayout4, EditText editText, FanProgressBar fanProgressBar, FanProgressBar fanProgressBar2, ImageView imageView, ImageView imageView2, DoubleAddLayout doubleAddLayout, DoubleAddLayout doubleAddLayout2, DoubleAddLayout doubleAddLayout3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, LinearLayout linearLayout8, LinearLayout linearLayout9, LinearLayout linearLayout10, LinearLayout linearLayout11, TitleSelectView titleSelectView, TitleSelectView titleSelectView2, TitleSelectView titleSelectView3, TitleSelectView titleSelectView4, RangeSeekBar rangeSeekBar, RangeSeekBar rangeSeekBar2, RangeSeekBar rangeSeekBar3, StatueSwitch statueSwitch, SingleAddView singleAddView, SingleAddView singleAddView2, SingleAddView singleAddView3, SingleAddView singleAddView4, SingleAddView singleAddView5, SingleAddView singleAddView6, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, View view2, View view3, WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, WheelView wheelView5, WheelView wheelView6, WheelView wheelView7, WheelView wheelView8, WheelView wheelView9, WheelView wheelView10) {
        super(obj, view, i);
        this.btnDelete = relativeLayout;
        this.cb1 = checkBox;
        this.cb2 = checkBox2;
        this.cb3 = checkBox3;
        this.cb4 = checkBox4;
        this.cb5 = checkBox5;
        this.cb6 = checkBox6;
        this.cb7 = checkBox7;
        this.elCycle = expandableLayout;
        this.elOn = expandableLayout2;
        this.elTmpHum = expandableLayout3;
        this.elVpd = expandableLayout4;
        this.etEditName = editText;
        this.fpbOff = fanProgressBar;
        this.fpbOn = fanProgressBar2;
        this.ivLevelOff = imageView;
        this.ivLevelOn = imageView2;
        this.layoutHum = doubleAddLayout;
        this.layoutTmp = doubleAddLayout2;
        this.layoutVpd = doubleAddLayout3;
        this.llContent = linearLayout;
        this.llCycleContainer = linearLayout2;
        this.llCycleOffTime = linearLayout3;
        this.llCycleOn = linearLayout4;
        this.llEndTime = linearLayout5;
        this.llHumContainer = linearLayout6;
        this.llLevel = linearLayout7;
        this.llLevelOff = constraintLayout;
        this.llLevelOn = constraintLayout2;
        this.llOnContainer = linearLayout8;
        this.llStartTime = linearLayout9;
        this.llTmphumContainer = linearLayout10;
        this.llVpdContainer = linearLayout11;
        this.modeCycle = titleSelectView;
        this.modeOn = titleSelectView2;
        this.modeTempHum = titleSelectView3;
        this.modeVpd = titleSelectView4;
        this.rsbHum = rangeSeekBar;
        this.rsbTemp = rangeSeekBar2;
        this.rsbVpd = rangeSeekBar3;
        this.f214sb = statueSwitch;
        this.sbHighHum = singleAddView;
        this.sbHighTmp = singleAddView2;
        this.sbHighVpd = singleAddView3;
        this.sbLowHum = singleAddView4;
        this.sbLowTmp = singleAddView5;
        this.sbLowVpd = singleAddView6;
        this.tvCycleOffDes = textView;
        this.tvCycleOnDes = textView2;
        this.tvEndTimeDes = textView3;
        this.tvHumMin = textView4;
        this.tvLevelOffDes = textView5;
        this.tvLevelOffTitle = textView6;
        this.tvLevelOnDes = textView7;
        this.tvLevelOnTitle = textView8;
        this.tvNameSuggest = textView9;
        this.tvStartTimeDes = textView10;
        this.tvTempMax = textView11;
        this.tvTempMin = textView12;
        this.tvVpdMin = textView13;
        this.vCycleLine = view2;
        this.vTimeLine = view3;
        this.wvCycleOffHour = wheelView;
        this.wvCycleOffMin = wheelView2;
        this.wvCycleOnHour = wheelView3;
        this.wvCycleOnMin = wheelView4;
        this.wvEndAmOrPm = wheelView5;
        this.wvEndHour = wheelView6;
        this.wvEndMin = wheelView7;
        this.wvStartAmOrPm = wheelView8;
        this.wvStartHour = wheelView9;
        this.wvStartMin = wheelView10;
    }

    public AutomationModelV4 getAutoModelV4() {
        return this.mAutoModelV4;
    }

    public static FragmentAutomationV4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAutomationV4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentAutomationV4Binding) ViewDataBinding.inflateInternal(layoutInflater, C2420R.layout.fragment_automation_v4, viewGroup, z, obj);
    }

    public static FragmentAutomationV4Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAutomationV4Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentAutomationV4Binding) ViewDataBinding.inflateInternal(layoutInflater, C2420R.layout.fragment_automation_v4, (ViewGroup) null, false, obj);
    }

    public static FragmentAutomationV4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAutomationV4Binding bind(View view, Object obj) {
        return (FragmentAutomationV4Binding) bind(obj, view, C2420R.layout.fragment_automation_v4);
    }
}
