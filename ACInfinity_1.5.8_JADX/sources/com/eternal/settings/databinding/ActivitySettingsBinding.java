package com.eternal.settings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.settings.C2668R;
import com.eternal.settings.SettingModel;
import com.eternal.widget.ExpandableLayout;
import com.eternal.widget.guqiang.AddSubView;
import com.eternal.widget.guqiang.ProgressToolbar;
import com.google.android.material.tabs.TabLayout;

public abstract class ActivitySettingsBinding extends ViewDataBinding {
    public final RadioGroup brightRadioGroup;
    public final Button btnConfirm;
    public final RelativeLayout btnShare;
    public final ConstraintLayout clBuffer;
    public final ConstraintLayout clBufferHum;
    public final ConstraintLayout clBufferVpd;
    public final ConstraintLayout clVpdLeafTemperature;
    public final ConstraintLayout ctTab;
    public final ConstraintLayout ctTmp;
    public final EditText editName;
    public final EditText editPortName;
    public final ExpandableLayout elType;
    public final ImageView ivType;
    public final RelativeLayout layoutShow;
    public final LinearLayout llCalibration;
    public final LinearLayout llContainer;
    public final LinearLayout llMaxMinLevel;
    public final LinearLayout llTransition;
    @Bindable
    protected SettingModel mModel;
    public final RadioButton rbA2;
    public final RadioButton rbA3;
    public final RadioButton rbAc;
    public final RadioButton rbDehum;
    public final RadioButton rbFan;
    public final RadioButton rbHeater;
    public final RadioButton rbHeight;
    public final RadioButton rbHum;
    public final RadioButton rbLight;
    public final RadioButton rbLow;
    public final RadioButton rbMedium;
    public final RadioGroup rgType1;
    public final RadioGroup rgType2;
    public final ConstraintLayout rlCalibrationHum;
    public final ConstraintLayout rlCalibrationTemperature;
    public final RelativeLayout rlDeviceBrightness;
    public final ConstraintLayout rlMaxLevel;
    public final ConstraintLayout rlMinLevel;
    public final ConstraintLayout rlTransitionHum;
    public final ConstraintLayout rlTransitionTemperature;
    public final ConstraintLayout rlTransitionVpd;
    public final ConstraintLayout root;
    public final ScrollView scroll;
    public final AddSubView spbBufferHum;
    public final AddSubView spbBufferTemp;
    public final AddSubView spbBufferVpd;
    public final AddSubView spbCalibrationHum;
    public final AddSubView spbCalibrationTemperature;
    public final AddSubView spbLevelMax;
    public final AddSubView spbLevelMin;
    public final AddSubView spbTransitionHum;
    public final AddSubView spbTransitionTemp;
    public final AddSubView spbTransitionVpd;
    public final AddSubView spbVpdLeafTemperature;
    public final TabLayout tbPort;
    public final ProgressToolbar toolBar;
    public final TextView tvBuffer;
    public final TextView tvBufferHum;
    public final TextView tvBufferHumDes;
    public final TextView tvBufferHumSubtitle;
    public final TextView tvBufferTemp;
    public final TextView tvBufferTempDes;
    public final TextView tvBufferTempSubtitle;
    public final TextView tvBufferVpd;
    public final TextView tvBufferVpdDes;
    public final TextView tvBufferVpdSubtitle;
    public final TextView tvCalibration;
    public final TextView tvCalibrationHum;
    public final TextView tvCalibrationTemperatureSubtitle;
    public final RelativeLayout tvDelete;
    public final TextView tvDeviceBrightness;
    public final TextView tvDeviceBrightnessDes;
    public final TextView tvDeviceClock;
    public final RelativeLayout tvDeviceTime;
    public final TextView tvDeviceTimeZone;
    public final TextView tvMaxLevel;
    public final TextView tvMaxLevelDes;
    public final TextView tvMaxTitle;
    public final TextView tvMinLevel;
    public final TextView tvMinLevelDes;
    public final TextView tvMinTitle;
    public final TextView tvNameSuggest;
    public final TextView tvPortNameSuggest;
    public final RelativeLayout tvReset;
    public final TextView tvTabController;
    public final TextView tvTabPort;
    public final TextView tvTabSystem;
    public final TextView tvTransition;
    public final TextView tvTransitionDes;
    public final TextView tvTransitionHum;
    public final TextView tvTransitionTemperatureSubtitle;
    public final TextView tvTransitionVpd;
    public final TextView tvUnitC;
    public final TextView tvUnitF;
    public final TextView tvVpdLeafTemperature;
    public final TextView tvVpdLeafTemperatureDes;
    public final TextView tvVpdLeafTemperatureDes2;
    public final TextView tvVpdLeafTemperatureSubtitle;
    public final TextView txtConnectTime;
    public final View vBuffer;
    public final View vClickController;
    public final View vClickPort;
    public final View vClickSystem;
    public final View vDot;
    public final View vMinLevel;
    public final View vSlide;
    public final View vTabSlide;

    public abstract void setModel(SettingModel settingModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ActivitySettingsBinding(Object obj, View view, int i, RadioGroup radioGroup, Button button, RelativeLayout relativeLayout, ConstraintLayout constraintLayout, ConstraintLayout constraintLayout2, ConstraintLayout constraintLayout3, ConstraintLayout constraintLayout4, ConstraintLayout constraintLayout5, ConstraintLayout constraintLayout6, EditText editText, EditText editText2, ExpandableLayout expandableLayout, ImageView imageView, RelativeLayout relativeLayout2, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, RadioButton radioButton, RadioButton radioButton2, RadioButton radioButton3, RadioButton radioButton4, RadioButton radioButton5, RadioButton radioButton6, RadioButton radioButton7, RadioButton radioButton8, RadioButton radioButton9, RadioButton radioButton10, RadioButton radioButton11, RadioGroup radioGroup2, RadioGroup radioGroup3, ConstraintLayout constraintLayout7, ConstraintLayout constraintLayout8, RelativeLayout relativeLayout3, ConstraintLayout constraintLayout9, ConstraintLayout constraintLayout10, ConstraintLayout constraintLayout11, ConstraintLayout constraintLayout12, ConstraintLayout constraintLayout13, ConstraintLayout constraintLayout14, ScrollView scrollView, AddSubView addSubView, AddSubView addSubView2, AddSubView addSubView3, AddSubView addSubView4, AddSubView addSubView5, AddSubView addSubView6, AddSubView addSubView7, AddSubView addSubView8, AddSubView addSubView9, AddSubView addSubView10, AddSubView addSubView11, TabLayout tabLayout, ProgressToolbar progressToolbar, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, RelativeLayout relativeLayout4, TextView textView14, TextView textView15, TextView textView16, RelativeLayout relativeLayout5, TextView textView17, TextView textView18, TextView textView19, TextView textView20, TextView textView21, TextView textView22, TextView textView23, TextView textView24, TextView textView25, RelativeLayout relativeLayout6, TextView textView26, TextView textView27, TextView textView28, TextView textView29, TextView textView30, TextView textView31, TextView textView32, TextView textView33, TextView textView34, TextView textView35, TextView textView36, TextView textView37, TextView textView38, TextView textView39, TextView textView40, View view2, View view3, View view4, View view5, View view6, View view7, View view8, View view9) {
        super(obj, view, i);
        this.brightRadioGroup = radioGroup;
        this.btnConfirm = button;
        this.btnShare = relativeLayout;
        this.clBuffer = constraintLayout;
        this.clBufferHum = constraintLayout2;
        this.clBufferVpd = constraintLayout3;
        this.clVpdLeafTemperature = constraintLayout4;
        this.ctTab = constraintLayout5;
        this.ctTmp = constraintLayout6;
        this.editName = editText;
        this.editPortName = editText2;
        this.elType = expandableLayout;
        this.ivType = imageView;
        this.layoutShow = relativeLayout2;
        this.llCalibration = linearLayout;
        this.llContainer = linearLayout2;
        this.llMaxMinLevel = linearLayout3;
        this.llTransition = linearLayout4;
        this.rbA2 = radioButton;
        this.rbA3 = radioButton2;
        this.rbAc = radioButton3;
        this.rbDehum = radioButton4;
        this.rbFan = radioButton5;
        this.rbHeater = radioButton6;
        this.rbHeight = radioButton7;
        this.rbHum = radioButton8;
        this.rbLight = radioButton9;
        this.rbLow = radioButton10;
        this.rbMedium = radioButton11;
        this.rgType1 = radioGroup2;
        this.rgType2 = radioGroup3;
        this.rlCalibrationHum = constraintLayout7;
        this.rlCalibrationTemperature = constraintLayout8;
        this.rlDeviceBrightness = relativeLayout3;
        this.rlMaxLevel = constraintLayout9;
        this.rlMinLevel = constraintLayout10;
        this.rlTransitionHum = constraintLayout11;
        this.rlTransitionTemperature = constraintLayout12;
        this.rlTransitionVpd = constraintLayout13;
        this.root = constraintLayout14;
        this.scroll = scrollView;
        this.spbBufferHum = addSubView;
        this.spbBufferTemp = addSubView2;
        this.spbBufferVpd = addSubView3;
        this.spbCalibrationHum = addSubView4;
        this.spbCalibrationTemperature = addSubView5;
        this.spbLevelMax = addSubView6;
        this.spbLevelMin = addSubView7;
        this.spbTransitionHum = addSubView8;
        this.spbTransitionTemp = addSubView9;
        this.spbTransitionVpd = addSubView10;
        this.spbVpdLeafTemperature = addSubView11;
        this.tbPort = tabLayout;
        this.toolBar = progressToolbar;
        this.tvBuffer = textView;
        this.tvBufferHum = textView2;
        this.tvBufferHumDes = textView3;
        this.tvBufferHumSubtitle = textView4;
        this.tvBufferTemp = textView5;
        this.tvBufferTempDes = textView6;
        this.tvBufferTempSubtitle = textView7;
        this.tvBufferVpd = textView8;
        this.tvBufferVpdDes = textView9;
        this.tvBufferVpdSubtitle = textView10;
        this.tvCalibration = textView11;
        this.tvCalibrationHum = textView12;
        this.tvCalibrationTemperatureSubtitle = textView13;
        this.tvDelete = relativeLayout4;
        this.tvDeviceBrightness = textView14;
        this.tvDeviceBrightnessDes = textView15;
        this.tvDeviceClock = textView16;
        this.tvDeviceTime = relativeLayout5;
        this.tvDeviceTimeZone = textView17;
        this.tvMaxLevel = textView18;
        this.tvMaxLevelDes = textView19;
        this.tvMaxTitle = textView20;
        this.tvMinLevel = textView21;
        this.tvMinLevelDes = textView22;
        this.tvMinTitle = textView23;
        this.tvNameSuggest = textView24;
        this.tvPortNameSuggest = textView25;
        this.tvReset = relativeLayout6;
        this.tvTabController = textView26;
        this.tvTabPort = textView27;
        this.tvTabSystem = textView28;
        this.tvTransition = textView29;
        this.tvTransitionDes = textView30;
        this.tvTransitionHum = textView31;
        this.tvTransitionTemperatureSubtitle = textView32;
        this.tvTransitionVpd = textView33;
        this.tvUnitC = textView34;
        this.tvUnitF = textView35;
        this.tvVpdLeafTemperature = textView36;
        this.tvVpdLeafTemperatureDes = textView37;
        this.tvVpdLeafTemperatureDes2 = textView38;
        this.tvVpdLeafTemperatureSubtitle = textView39;
        this.txtConnectTime = textView40;
        this.vBuffer = view2;
        this.vClickController = view3;
        this.vClickPort = view4;
        this.vClickSystem = view5;
        this.vDot = view6;
        this.vMinLevel = view7;
        this.vSlide = view8;
        this.vTabSlide = view9;
    }

    public SettingModel getModel() {
        return this.mModel;
    }

    public static ActivitySettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivitySettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, C2668R.layout.activity_settings, viewGroup, z, obj);
    }

    public static ActivitySettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivitySettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, C2668R.layout.activity_settings, (ViewGroup) null, false, obj);
    }

    public static ActivitySettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySettingsBinding bind(View view, Object obj) {
        return (ActivitySettingsBinding) bind(obj, view, C2668R.layout.activity_settings);
    }
}
