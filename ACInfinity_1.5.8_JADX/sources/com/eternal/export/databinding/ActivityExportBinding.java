package com.eternal.export.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.export.C2164R;
import com.eternal.export.ExportModel;
import com.eternal.widget.guqiang.Toolbar;
import com.zyyoona7.wheel.WheelView;

public abstract class ActivityExportBinding extends ViewDataBinding {
    public final Button btnConfirm;
    public final RadioButton frequency1;
    public final RadioButton frequency1440;
    public final RadioButton frequency15;
    public final RadioButton frequency30;
    public final RadioButton frequency60;
    public final RadioButton frequency720;
    public final LinearLayout llEndTime;
    public final LinearLayout llStartTime;
    @Bindable
    protected ExportModel mModel;
    public final RadioGroup rgFrequency;
    public final Toolbar toolbar;
    public final TextView tvEndTime;
    public final TextView tvFrequency;
    public final TextView tvStartTime;
    public final TextView txtConnectTime;
    public final View vLine;
    public final WheelView wvEndAmPm;
    public final WheelView wvEndDay;
    public final WheelView wvEndHour;
    public final WheelView wvEndMin;
    public final WheelView wvEndMonth;
    public final WheelView wvEndYear;
    public final WheelView wvStartAmPm;
    public final WheelView wvStartDay;
    public final WheelView wvStartHour;
    public final WheelView wvStartMin;
    public final WheelView wvStartMonth;
    public final WheelView wvStartYear;

    public abstract void setModel(ExportModel exportModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivityExportBinding(Object obj, View view, int i, Button button, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioButton radioButton5, RadioButton radioButton6, LinearLayout linearLayout, LinearLayout linearLayout2, RadioGroup radioGroup, Toolbar toolbar2, TextView textView, TextView textView2, TextView textView3, TextView textView4, View view2, WheelView wheelView, WheelView wheelView2, WheelView wheelView3, WheelView wheelView4, WheelView wheelView5, WheelView wheelView6, WheelView wheelView7, WheelView wheelView8, WheelView wheelView9, WheelView wheelView10, WheelView wheelView11, WheelView wheelView12) {
        super(obj, view, i);
        this.btnConfirm = button;
        this.frequency1 = radioButton;
        this.frequency1440 = radioButton2;
        this.frequency15 = radioButton3;
        this.frequency30 = radioButton4;
        this.frequency60 = radioButton5;
        this.frequency720 = radioButton6;
        this.llEndTime = linearLayout;
        this.llStartTime = linearLayout2;
        this.rgFrequency = radioGroup;
        this.toolbar = toolbar2;
        this.tvEndTime = textView;
        this.tvFrequency = textView2;
        this.tvStartTime = textView3;
        this.txtConnectTime = textView4;
        this.vLine = view2;
        this.wvEndAmPm = wheelView;
        this.wvEndDay = wheelView2;
        this.wvEndHour = wheelView3;
        this.wvEndMin = wheelView4;
        this.wvEndMonth = wheelView5;
        this.wvEndYear = wheelView6;
        this.wvStartAmPm = wheelView7;
        this.wvStartDay = wheelView8;
        this.wvStartHour = wheelView9;
        this.wvStartMin = wheelView10;
        this.wvStartMonth = wheelView11;
        this.wvStartYear = wheelView12;
    }

    public ExportModel getModel() {
        return this.mModel;
    }

    public static ActivityExportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityExportBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityExportBinding) ViewDataBinding.inflateInternal(layoutInflater, C2164R.layout.activity_export, viewGroup, z, obj);
    }

    public static ActivityExportBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityExportBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityExportBinding) ViewDataBinding.inflateInternal(layoutInflater, C2164R.layout.activity_export, (ViewGroup) null, false, obj);
    }

    public static ActivityExportBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityExportBinding bind(View view, Object obj) {
        return (ActivityExportBinding) bind(obj, view, C2164R.layout.activity_export);
    }
}
