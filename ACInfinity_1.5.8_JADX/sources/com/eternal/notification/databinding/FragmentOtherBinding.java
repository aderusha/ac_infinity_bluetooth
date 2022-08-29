package com.eternal.notification.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.notification.C2420R;
import com.eternal.notification.model.OtherModel;
import com.eternal.notification.view.TitleSelectView;
import com.eternal.widget.ExpandableLayout;
import com.eternal.widget.guqiang.DoubleAddLayout;
import com.eternal.widget.guqiang.RangeSeekBar;
import com.eternal.widget.guqiang.SingleAddView;

public abstract class FragmentOtherBinding extends ViewDataBinding {
    public final RelativeLayout btnDelete;
    public final ExpandableLayout elHum;
    public final ExpandableLayout elModes;
    public final ExpandableLayout elTmp;
    public final ExpandableLayout elVpd;
    public final EditText etEditName;
    public final DoubleAddLayout layoutHum;
    public final DoubleAddLayout layoutTmp;
    public final DoubleAddLayout layoutVpd;
    public final LinearLayout llBeep;
    public final LinearLayout llHumContainer;
    public final LinearLayout llModesContainer;
    public final LinearLayout llTmpContainer;
    public final LinearLayout llVpdContainer;
    @Bindable
    protected OtherModel mOtherModel;
    public final TitleSelectView modeHum;
    public final TitleSelectView modeVpd;
    public final RadioButton rbBeep;
    public final RadioButton rbNone;
    public final RadioGroup rgBeep;
    public final RangeSeekBar rsbHum;
    public final RangeSeekBar rsbTemp;
    public final RangeSeekBar rsbVpd;
    public final SingleAddView sbHighHum;
    public final SingleAddView sbHighTmp;
    public final SingleAddView sbHighVpd;
    public final SingleAddView sbLowHum;
    public final SingleAddView sbLowTmp;
    public final SingleAddView sbLowVpd;
    public final TitleSelectView tsvAllMode;
    public final TitleSelectView tsvTmp;
    public final TextView tvHumMin;
    public final TextView tvNameSuggest;
    public final TextView tvTempMax;
    public final TextView tvTempMin;
    public final TextView tvVpdMin;

    public abstract void setOtherModel(OtherModel otherModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentOtherBinding(Object obj, View view, int i, RelativeLayout relativeLayout, ExpandableLayout expandableLayout, ExpandableLayout expandableLayout2, ExpandableLayout expandableLayout3, ExpandableLayout expandableLayout4, EditText editText, DoubleAddLayout doubleAddLayout, DoubleAddLayout doubleAddLayout2, DoubleAddLayout doubleAddLayout3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, TitleSelectView titleSelectView, TitleSelectView titleSelectView2, RadioButton radioButton, RadioButton radioButton2, RadioGroup radioGroup, RangeSeekBar rangeSeekBar, RangeSeekBar rangeSeekBar2, RangeSeekBar rangeSeekBar3, SingleAddView singleAddView, SingleAddView singleAddView2, SingleAddView singleAddView3, SingleAddView singleAddView4, SingleAddView singleAddView5, SingleAddView singleAddView6, TitleSelectView titleSelectView3, TitleSelectView titleSelectView4, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5) {
        super(obj, view, i);
        this.btnDelete = relativeLayout;
        this.elHum = expandableLayout;
        this.elModes = expandableLayout2;
        this.elTmp = expandableLayout3;
        this.elVpd = expandableLayout4;
        this.etEditName = editText;
        this.layoutHum = doubleAddLayout;
        this.layoutTmp = doubleAddLayout2;
        this.layoutVpd = doubleAddLayout3;
        this.llBeep = linearLayout;
        this.llHumContainer = linearLayout2;
        this.llModesContainer = linearLayout3;
        this.llTmpContainer = linearLayout4;
        this.llVpdContainer = linearLayout5;
        this.modeHum = titleSelectView;
        this.modeVpd = titleSelectView2;
        this.rbBeep = radioButton;
        this.rbNone = radioButton2;
        this.rgBeep = radioGroup;
        this.rsbHum = rangeSeekBar;
        this.rsbTemp = rangeSeekBar2;
        this.rsbVpd = rangeSeekBar3;
        this.sbHighHum = singleAddView;
        this.sbHighTmp = singleAddView2;
        this.sbHighVpd = singleAddView3;
        this.sbLowHum = singleAddView4;
        this.sbLowTmp = singleAddView5;
        this.sbLowVpd = singleAddView6;
        this.tsvAllMode = titleSelectView3;
        this.tsvTmp = titleSelectView4;
        this.tvHumMin = textView;
        this.tvNameSuggest = textView2;
        this.tvTempMax = textView3;
        this.tvTempMin = textView4;
        this.tvVpdMin = textView5;
    }

    public OtherModel getOtherModel() {
        return this.mOtherModel;
    }

    public static FragmentOtherBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOtherBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentOtherBinding) ViewDataBinding.inflateInternal(layoutInflater, C2420R.layout.fragment_other, viewGroup, z, obj);
    }

    public static FragmentOtherBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOtherBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentOtherBinding) ViewDataBinding.inflateInternal(layoutInflater, C2420R.layout.fragment_other, (ViewGroup) null, false, obj);
    }

    public static FragmentOtherBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentOtherBinding bind(View view, Object obj) {
        return (FragmentOtherBinding) bind(obj, view, C2420R.layout.fragment_other);
    }
}
