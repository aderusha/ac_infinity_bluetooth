package com.eternal.settings.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.settings.C2667BR;
import com.eternal.settings.C2668R;
import com.eternal.settings.SettingModel;

public class ActivitySettingsBindingImpl extends ActivitySettingsBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener editNameandroidTextAttrChanged;
    private InverseBindingListener editPortNameandroidTextAttrChanged;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private final RelativeLayout mboundView10;
    private final RelativeLayout mboundView11;
    private final View mboundView12;
    private final View mboundView15;
    private final TextView mboundView16;
    private final RelativeLayout mboundView25;
    private final TextView mboundView26;
    private final View mboundView27;
    private final TextView mboundView29;
    private final LinearLayout mboundView31;
    private final TextView mboundView33;
    private final TextView mboundView36;
    private final RelativeLayout mboundView37;
    private final TextView mboundView39;
    private final TextView mboundView40;
    private final TextView mboundView48;
    private final View mboundView50;
    private final View mboundView52;
    private final View mboundView58;
    private final TextView mboundView60;
    private final LinearLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2668R.C2671id.ct_tab, 64);
        sparseIntArray.put(C2668R.C2671id.v_tab_slide, 65);
        sparseIntArray.put(C2668R.C2671id.ll_container, 66);
        sparseIntArray.put(C2668R.C2671id.layout_show, 67);
        sparseIntArray.put(C2668R.C2671id.ct_tmp, 68);
        sparseIntArray.put(C2668R.C2671id.v_slide, 69);
        sparseIntArray.put(C2668R.C2671id.tv_device_clock, 70);
        sparseIntArray.put(C2668R.C2671id.rl_device_brightness, 71);
        sparseIntArray.put(C2668R.C2671id.tv_device_brightness, 72);
        sparseIntArray.put(C2668R.C2671id.bright_radio_group, 73);
        sparseIntArray.put(C2668R.C2671id.rb_low, 74);
        sparseIntArray.put(C2668R.C2671id.rb_medium, 75);
        sparseIntArray.put(C2668R.C2671id.rb_height, 76);
        sparseIntArray.put(C2668R.C2671id.rb_a2, 77);
        sparseIntArray.put(C2668R.C2671id.rb_a3, 78);
        sparseIntArray.put(C2668R.C2671id.tv_device_brightness_des, 79);
        sparseIntArray.put(C2668R.C2671id.ll_calibration, 80);
        sparseIntArray.put(C2668R.C2671id.tv_calibration, 81);
        sparseIntArray.put(C2668R.C2671id.rl_calibration_temperature, 82);
        sparseIntArray.put(C2668R.C2671id.tv_calibration_temperature_subtitle, 83);
        sparseIntArray.put(C2668R.C2671id.spb_calibration_temperature, 84);
        sparseIntArray.put(C2668R.C2671id.tv_calibration_hum, 85);
        sparseIntArray.put(C2668R.C2671id.spb_calibration_hum, 86);
        sparseIntArray.put(C2668R.C2671id.tv_vpd_leaf_temperature, 87);
        sparseIntArray.put(C2668R.C2671id.tv_vpd_leaf_temperature_subtitle, 88);
        sparseIntArray.put(C2668R.C2671id.spb_vpd_leaf_temperature, 89);
        sparseIntArray.put(C2668R.C2671id.tv_vpd_leaf_temperature_des, 90);
        sparseIntArray.put(C2668R.C2671id.tv_vpd_leaf_temperature_des2, 91);
        sparseIntArray.put(C2668R.C2671id.rg_type_1, 92);
        sparseIntArray.put(C2668R.C2671id.rg_type_2, 93);
        sparseIntArray.put(C2668R.C2671id.tv_transition, 94);
        sparseIntArray.put(C2668R.C2671id.tv_transition_des, 95);
        sparseIntArray.put(C2668R.C2671id.rl_transition_temperature, 96);
        sparseIntArray.put(C2668R.C2671id.tv_transition_temperature_subtitle, 97);
        sparseIntArray.put(C2668R.C2671id.spb_transition_temp, 98);
        sparseIntArray.put(C2668R.C2671id.tv_transition_hum, 99);
        sparseIntArray.put(C2668R.C2671id.spb_transition_hum, 100);
        sparseIntArray.put(C2668R.C2671id.tv_transition_vpd, 101);
        sparseIntArray.put(C2668R.C2671id.spb_transition_vpd, 102);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_temp, 103);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_temp_subtitle, 104);
        sparseIntArray.put(C2668R.C2671id.spb_buffer_temp, 105);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_temp_des, 106);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_hum, 107);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_hum_subtitle, 108);
        sparseIntArray.put(C2668R.C2671id.spb_buffer_hum, 109);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_hum_des, 110);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_vpd, 111);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_vpd_subtitle, 112);
        sparseIntArray.put(C2668R.C2671id.spb_buffer_vpd, 113);
        sparseIntArray.put(C2668R.C2671id.tv_buffer_vpd_des, 114);
        sparseIntArray.put(C2668R.C2671id.rl_min_level, 115);
        sparseIntArray.put(C2668R.C2671id.tv_min_title, 116);
        sparseIntArray.put(C2668R.C2671id.tv_min_level, 117);
        sparseIntArray.put(C2668R.C2671id.spb_level_min, 118);
        sparseIntArray.put(C2668R.C2671id.tv_min_level_des, 119);
        sparseIntArray.put(C2668R.C2671id.v_min_level, 120);
        sparseIntArray.put(C2668R.C2671id.rl_max_level, 121);
        sparseIntArray.put(C2668R.C2671id.tv_max_title, 122);
        sparseIntArray.put(C2668R.C2671id.tv_max_level, 123);
        sparseIntArray.put(C2668R.C2671id.spb_level_max, 124);
        sparseIntArray.put(C2668R.C2671id.tv_max_level_des, 125);
    }

    public ActivitySettingsBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 126, sIncludes, sViewsWithIds));
    }

    private ActivitySettingsBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 37, objArr[73], objArr[62], objArr[13], objArr[55], objArr[57], objArr[59], objArr[30], objArr[64], objArr[68], objArr[22], objArr[35], objArr[41], objArr[38], objArr[67], objArr[80], objArr[66], objArr[61], objArr[49], objArr[77], objArr[78], objArr[44], objArr[46], objArr[42], objArr[47], objArr[76], objArr[45], objArr[43], objArr[74], objArr[75], objArr[92], objArr[93], objArr[28], objArr[82], objArr[71], objArr[121], objArr[115], objArr[51], objArr[96], objArr[53], objArr[0], objArr[20], objArr[109], objArr[105], objArr[113], objArr[86], objArr[84], objArr[124], objArr[118], objArr[100], objArr[98], objArr[102], objArr[89], objArr[32], objArr[1], objArr[54], objArr[107], objArr[110], objArr[108], objArr[103], objArr[106], objArr[104], objArr[111], objArr[114], objArr[112], objArr[81], objArr[85], objArr[83], objArr[19], objArr[72], objArr[79], objArr[70], objArr[14], objArr[17], objArr[123], objArr[125], objArr[122], objArr[117], objArr[119], objArr[116], objArr[21], objArr[34], objArr[18], objArr[5], objArr[6], objArr[7], objArr[94], objArr[95], objArr[99], objArr[97], objArr[101], objArr[24], objArr[23], objArr[87], objArr[90], objArr[91], objArr[88], objArr[63], objArr[56], objArr[2], objArr[3], objArr[4], objArr[8], objArr[120], objArr[69], objArr[65]);
        this.editNameandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivitySettingsBindingImpl.this.editName);
                SettingModel settingModel = ActivitySettingsBindingImpl.this.mModel;
                boolean z = true;
                if (settingModel != null) {
                    MutableLiveData<String> mutableLiveData = settingModel.deviceName;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.editPortNameandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivitySettingsBindingImpl.this.editPortName);
                SettingModel settingModel = ActivitySettingsBindingImpl.this.mModel;
                boolean z = true;
                if (settingModel != null) {
                    MutableLiveData<String> mutableLiveData = settingModel.portName;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.mDirtyFlags = -1;
        this.mDirtyFlags_1 = -1;
        this.btnConfirm.setTag((Object) null);
        this.btnShare.setTag((Object) null);
        this.clBuffer.setTag((Object) null);
        this.clBufferHum.setTag((Object) null);
        this.clBufferVpd.setTag((Object) null);
        this.clVpdLeafTemperature.setTag((Object) null);
        this.editName.setTag((Object) null);
        this.editPortName.setTag((Object) null);
        this.elType.setTag((Object) null);
        this.ivType.setTag((Object) null);
        this.llMaxMinLevel.setTag((Object) null);
        this.llTransition.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[10];
        this.mboundView10 = relativeLayout;
        relativeLayout.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[11];
        this.mboundView11 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        View view2 = objArr[12];
        this.mboundView12 = view2;
        view2.setTag((Object) null);
        View view3 = objArr[15];
        this.mboundView15 = view3;
        view3.setTag((Object) null);
        TextView textView = objArr[16];
        this.mboundView16 = textView;
        textView.setTag((Object) null);
        RelativeLayout relativeLayout3 = objArr[25];
        this.mboundView25 = relativeLayout3;
        relativeLayout3.setTag((Object) null);
        TextView textView2 = objArr[26];
        this.mboundView26 = textView2;
        textView2.setTag((Object) null);
        View view4 = objArr[27];
        this.mboundView27 = view4;
        view4.setTag((Object) null);
        TextView textView3 = objArr[29];
        this.mboundView29 = textView3;
        textView3.setTag((Object) null);
        LinearLayout linearLayout = objArr[31];
        this.mboundView31 = linearLayout;
        linearLayout.setTag((Object) null);
        TextView textView4 = objArr[33];
        this.mboundView33 = textView4;
        textView4.setTag((Object) null);
        TextView textView5 = objArr[36];
        this.mboundView36 = textView5;
        textView5.setTag((Object) null);
        RelativeLayout relativeLayout4 = objArr[37];
        this.mboundView37 = relativeLayout4;
        relativeLayout4.setTag((Object) null);
        TextView textView6 = objArr[39];
        this.mboundView39 = textView6;
        textView6.setTag((Object) null);
        TextView textView7 = objArr[40];
        this.mboundView40 = textView7;
        textView7.setTag((Object) null);
        TextView textView8 = objArr[48];
        this.mboundView48 = textView8;
        textView8.setTag((Object) null);
        View view5 = objArr[50];
        this.mboundView50 = view5;
        view5.setTag((Object) null);
        View view6 = objArr[52];
        this.mboundView52 = view6;
        view6.setTag((Object) null);
        View view7 = objArr[58];
        this.mboundView58 = view7;
        view7.setTag((Object) null);
        TextView textView9 = objArr[60];
        this.mboundView60 = textView9;
        textView9.setTag((Object) null);
        LinearLayout linearLayout2 = objArr[9];
        this.mboundView9 = linearLayout2;
        linearLayout2.setTag((Object) null);
        this.rbAc.setTag((Object) null);
        this.rbDehum.setTag((Object) null);
        this.rbFan.setTag((Object) null);
        this.rbHeater.setTag((Object) null);
        this.rbHum.setTag((Object) null);
        this.rbLight.setTag((Object) null);
        this.rlCalibrationHum.setTag((Object) null);
        this.rlTransitionHum.setTag((Object) null);
        this.rlTransitionVpd.setTag((Object) null);
        this.root.setTag((Object) null);
        this.scroll.setTag((Object) null);
        this.tbPort.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvBuffer.setTag((Object) null);
        this.tvDelete.setTag((Object) null);
        this.tvDeviceTime.setTag((Object) null);
        this.tvDeviceTimeZone.setTag((Object) null);
        this.tvNameSuggest.setTag((Object) null);
        this.tvPortNameSuggest.setTag((Object) null);
        this.tvReset.setTag((Object) null);
        this.tvTabController.setTag((Object) null);
        this.tvTabPort.setTag((Object) null);
        this.tvTabSystem.setTag((Object) null);
        this.tvUnitC.setTag((Object) null);
        this.tvUnitF.setTag((Object) null);
        this.txtConnectTime.setTag((Object) null);
        this.vBuffer.setTag((Object) null);
        this.vClickController.setTag((Object) null);
        this.vClickPort.setTag((Object) null);
        this.vClickSystem.setTag((Object) null);
        this.vDot.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 274877906944L;
            this.mDirtyFlags_1 = 0;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags == 0) {
                if (this.mDirtyFlags_1 == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (C2667BR.model != i) {
            return false;
        }
        setModel((SettingModel) obj);
        return true;
    }

    public void setModel(SettingModel settingModel) {
        this.mModel = settingModel;
        synchronized (this) {
            this.mDirtyFlags |= 137438953472L;
        }
        notifyPropertyChanged(C2667BR.model);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeModelDeviceTimeAvailable((MutableLiveData) obj, i2);
            case 1:
                return onChangeModelPortNameVisible((MutableLiveData) obj, i2);
            case 2:
                return onChangeModelSelectTabDevice((MutableLiveData) obj, i2);
            case 3:
                return onChangeModelDeviceTime((MutableLiveData) obj, i2);
            case 4:
                return onChangeModelTransAndBufferVpdVisible((MutableLiveData) obj, i2);
            case 5:
                return onChangeModelTabLayoutVisible((MutableLiveData) obj, i2);
            case 6:
                return onChangeModelPortTypeVisible((MutableLiveData) obj, i2);
            case 7:
                return onChangeModelBufferVisible((MutableLiveData) obj, i2);
            case 8:
                return onChangeModelShareVisible((MutableLiveData) obj, i2);
            case 9:
                return onChangeModelDeviceTimeZone((MutableLiveData) obj, i2);
            case 10:
                return onChangeModelSubtitle((MutableLiveData) obj, i2);
            case 11:
                return onChangeModelPortTypeText((MutableLiveData) obj, i2);
            case 12:
                return onChangeModelIsPlugPortType((ObservableBoolean) obj, i2);
            case 13:
                return onChangeModelVpdLeafVisible((MutableLiveData) obj, i2);
            case 14:
                return onChangeModelTransitionVisible((MutableLiveData) obj, i2);
            case 15:
                return onChangeModelUpdateVisible((MutableLiveData) obj, i2);
            case 16:
                return onChangeModelPortTypeRes((MutableLiveData) obj, i2);
            case 17:
                return onChangeModelWifiSettingVisible((MutableLiveData) obj, i2);
            case 18:
                return onChangeModelCommitting((ObservableBoolean) obj, i2);
            case 19:
                return onChangeModelTimeText((MutableLiveData) obj, i2);
            case 20:
                return onChangeModelHumVisible((MutableLiveData) obj, i2);
            case 21:
                return onChangeModelUpdateAvailable((MutableLiveData) obj, i2);
            case 22:
                return onChangeModelResetVisible((MutableLiveData) obj, i2);
            case 23:
                return onChangeModelDeviceName((MutableLiveData) obj, i2);
            case 24:
                return onChangeModelLevelVisible((MutableLiveData) obj, i2);
            case 25:
                return onChangeModelPortName((MutableLiveData) obj, i2);
            case 26:
                return onChangeModelTimeVisible((MutableLiveData) obj, i2);
            case 27:
                return onChangeModelFirstTabText((MutableLiveData) obj, i2);
            case 28:
                return onChangeModelAllPortText((MutableLiveData) obj, i2);
            case 29:
                return onChangeModelPortTabText((MutableLiveData) obj, i2);
            case 30:
                return onChangeModelIsDegree((MutableLiveData) obj, i2);
            case 31:
                return onChangeModelIsShare((ObservableBoolean) obj, i2);
            case 32:
                return onChangeModelNameSuggest((MutableLiveData) obj, i2);
            case 33:
                return onChangeModelPortTypeExpand((ObservableBoolean) obj, i2);
            case 34:
                return onChangeModelDeviceTimeVisible((MutableLiveData) obj, i2);
            case 35:
                return onChangeModelProgress((MutableLiveData) obj, i2);
            case 36:
                return onChangeModelPortNameSuggest((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeModelDeviceTimeAvailable(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelPortNameVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModelSelectTabDevice(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModelDeviceTime(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeModelTransAndBufferVpdVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeModelTabLayoutVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeModelPortTypeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeModelBufferVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeModelShareVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeModelDeviceTimeZone(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeModelSubtitle(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeModelPortTypeText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeModelIsPlugPortType(ObservableBoolean observableBoolean, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeModelVpdLeafVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeModelTransitionVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeModelUpdateVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeModelPortTypeRes(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeModelWifiSettingVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeModelCommitting(ObservableBoolean observableBoolean, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeModelTimeText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeModelHumVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    private boolean onChangeModelUpdateAvailable(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        }
        return true;
    }

    private boolean onChangeModelResetVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4194304;
        }
        return true;
    }

    private boolean onChangeModelDeviceName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8388608;
        }
        return true;
    }

    private boolean onChangeModelLevelVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16777216;
        }
        return true;
    }

    private boolean onChangeModelPortName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 33554432;
        }
        return true;
    }

    private boolean onChangeModelTimeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        return true;
    }

    private boolean onChangeModelFirstTabText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        return true;
    }

    private boolean onChangeModelAllPortText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        return true;
    }

    private boolean onChangeModelPortTabText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 536870912;
        }
        return true;
    }

    private boolean onChangeModelIsDegree(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1073741824;
        }
        return true;
    }

    private boolean onChangeModelIsShare(ObservableBoolean observableBoolean, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2147483648L;
        }
        return true;
    }

    private boolean onChangeModelNameSuggest(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4294967296L;
        }
        return true;
    }

    private boolean onChangeModelPortTypeExpand(ObservableBoolean observableBoolean, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8589934592L;
        }
        return true;
    }

    private boolean onChangeModelDeviceTimeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 17179869184L;
        }
        return true;
    }

    private boolean onChangeModelProgress(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 34359738368L;
        }
        return true;
    }

    private boolean onChangeModelPortNameSuggest(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2667BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 68719476736L;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x029e  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x02ec  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x032d  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x035e  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x036d  */
    /* JADX WARNING: Removed duplicated region for block: B:213:0x03c1  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x03d3  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x03fe  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x0410  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0429  */
    /* JADX WARNING: Removed duplicated region for block: B:242:0x043a  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0453  */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x0464  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x047d  */
    /* JADX WARNING: Removed duplicated region for block: B:262:0x0490  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x058e  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x05aa  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x05da  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x05ea  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x0620  */
    /* JADX WARNING: Removed duplicated region for block: B:338:0x0630  */
    /* JADX WARNING: Removed duplicated region for block: B:353:0x0666  */
    /* JADX WARNING: Removed duplicated region for block: B:357:0x0676  */
    /* JADX WARNING: Removed duplicated region for block: B:365:0x0692  */
    /* JADX WARNING: Removed duplicated region for block: B:368:0x06a2  */
    /* JADX WARNING: Removed duplicated region for block: B:383:0x06d2  */
    /* JADX WARNING: Removed duplicated region for block: B:387:0x06e2  */
    /* JADX WARNING: Removed duplicated region for block: B:395:0x06f9  */
    /* JADX WARNING: Removed duplicated region for block: B:398:0x0707  */
    /* JADX WARNING: Removed duplicated region for block: B:404:0x0720  */
    /* JADX WARNING: Removed duplicated region for block: B:408:0x072c  */
    /* JADX WARNING: Removed duplicated region for block: B:435:0x0786  */
    /* JADX WARNING: Removed duplicated region for block: B:438:0x0794  */
    /* JADX WARNING: Removed duplicated region for block: B:458:0x07df  */
    /* JADX WARNING: Removed duplicated region for block: B:462:0x07f2  */
    /* JADX WARNING: Removed duplicated region for block: B:474:0x081b  */
    /* JADX WARNING: Removed duplicated region for block: B:477:0x082b  */
    /* JADX WARNING: Removed duplicated region for block: B:486:0x0851  */
    /* JADX WARNING: Removed duplicated region for block: B:501:0x0883  */
    /* JADX WARNING: Removed duplicated region for block: B:505:0x0893  */
    /* JADX WARNING: Removed duplicated region for block: B:514:0x08b9  */
    /* JADX WARNING: Removed duplicated region for block: B:529:0x08ef  */
    /* JADX WARNING: Removed duplicated region for block: B:533:0x08ff  */
    /* JADX WARNING: Removed duplicated region for block: B:542:0x0923  */
    /* JADX WARNING: Removed duplicated region for block: B:548:0x093c  */
    /* JADX WARNING: Removed duplicated region for block: B:552:0x094d  */
    /* JADX WARNING: Removed duplicated region for block: B:558:0x0966  */
    /* JADX WARNING: Removed duplicated region for block: B:562:0x0979  */
    /* JADX WARNING: Removed duplicated region for block: B:584:0x09c8  */
    /* JADX WARNING: Removed duplicated region for block: B:587:0x09d6  */
    /* JADX WARNING: Removed duplicated region for block: B:608:0x0a39  */
    /* JADX WARNING: Removed duplicated region for block: B:611:0x0a51  */
    /* JADX WARNING: Removed duplicated region for block: B:618:0x0a6c  */
    /* JADX WARNING: Removed duplicated region for block: B:622:0x0a81  */
    /* JADX WARNING: Removed duplicated region for block: B:639:0x0ab5  */
    /* JADX WARNING: Removed duplicated region for block: B:642:0x0ac9  */
    /* JADX WARNING: Removed duplicated region for block: B:656:0x0afd  */
    /* JADX WARNING: Removed duplicated region for block: B:659:0x0b11  */
    /* JADX WARNING: Removed duplicated region for block: B:667:0x0b31  */
    /* JADX WARNING: Removed duplicated region for block: B:670:0x0b40  */
    /* JADX WARNING: Removed duplicated region for block: B:717:0x0d10  */
    /* JADX WARNING: Removed duplicated region for block: B:724:0x0d2a  */
    /* JADX WARNING: Removed duplicated region for block: B:727:0x0d3a  */
    /* JADX WARNING: Removed duplicated region for block: B:734:0x0d4b  */
    /* JADX WARNING: Removed duplicated region for block: B:737:0x0d5a  */
    /* JADX WARNING: Removed duplicated region for block: B:744:0x0d6d  */
    /* JADX WARNING: Removed duplicated region for block: B:747:0x0d7d  */
    /* JADX WARNING: Removed duplicated region for block: B:748:0x0d85  */
    /* JADX WARNING: Removed duplicated region for block: B:751:0x0d8d  */
    /* JADX WARNING: Removed duplicated region for block: B:754:0x0db8  */
    /* JADX WARNING: Removed duplicated region for block: B:756:0x0e28  */
    /* JADX WARNING: Removed duplicated region for block: B:759:0x0e35  */
    /* JADX WARNING: Removed duplicated region for block: B:762:0x0e47  */
    /* JADX WARNING: Removed duplicated region for block: B:765:0x0e59  */
    /* JADX WARNING: Removed duplicated region for block: B:768:0x0e6f  */
    /* JADX WARNING: Removed duplicated region for block: B:771:0x0e85  */
    /* JADX WARNING: Removed duplicated region for block: B:774:0x0e92  */
    /* JADX WARNING: Removed duplicated region for block: B:777:0x0eb6  */
    /* JADX WARNING: Removed duplicated region for block: B:780:0x0edc  */
    /* JADX WARNING: Removed duplicated region for block: B:783:0x0eed  */
    /* JADX WARNING: Removed duplicated region for block: B:786:0x0f0c  */
    /* JADX WARNING: Removed duplicated region for block: B:789:0x0f24  */
    /* JADX WARNING: Removed duplicated region for block: B:792:0x0f35  */
    /* JADX WARNING: Removed duplicated region for block: B:795:0x0f4b  */
    /* JADX WARNING: Removed duplicated region for block: B:798:0x0f61  */
    /* JADX WARNING: Removed duplicated region for block: B:801:0x0f72  */
    /* JADX WARNING: Removed duplicated region for block: B:804:0x0f83  */
    /* JADX WARNING: Removed duplicated region for block: B:807:0x0f90  */
    /* JADX WARNING: Removed duplicated region for block: B:810:0x0fa1  */
    /* JADX WARNING: Removed duplicated region for block: B:812:0x0faf  */
    /* JADX WARNING: Removed duplicated region for block: B:815:0x0fc3  */
    /* JADX WARNING: Removed duplicated region for block: B:818:0x0fe5  */
    /* JADX WARNING: Removed duplicated region for block: B:821:0x0ff6  */
    /* JADX WARNING: Removed duplicated region for block: B:824:0x100c  */
    /* JADX WARNING: Removed duplicated region for block: B:827:0x1019  */
    /* JADX WARNING: Removed duplicated region for block: B:830:0x102f  */
    /* JADX WARNING: Removed duplicated region for block: B:833:0x1065  */
    /* JADX WARNING: Removed duplicated region for block: B:836:0x1076  */
    /* JADX WARNING: Removed duplicated region for block: B:839:0x1087  */
    /* JADX WARNING: Removed duplicated region for block: B:842:0x1098  */
    /* JADX WARNING: Removed duplicated region for block: B:845:0x10a9  */
    /* JADX WARNING: Removed duplicated region for block: B:848:0x10ba  */
    /* JADX WARNING: Removed duplicated region for block: B:850:0x10c3  */
    /* JADX WARNING: Removed duplicated region for block: B:853:0x10d6  */
    /* JADX WARNING: Removed duplicated region for block: B:856:0x10e7  */
    /* JADX WARNING: Removed duplicated region for block: B:859:0x10f8  */
    /* JADX WARNING: Removed duplicated region for block: B:862:0x1110  */
    /* JADX WARNING: Removed duplicated region for block: B:865:0x1121  */
    /* JADX WARNING: Removed duplicated region for block: B:868:0x112e  */
    /* JADX WARNING: Removed duplicated region for block: B:877:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r164 = this;
            r1 = r164
            monitor-enter(r164)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x1136 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x1136 }
            long r6 = r1.mDirtyFlags_1     // Catch:{ all -> 0x1136 }
            r1.mDirtyFlags_1 = r4     // Catch:{ all -> 0x1136 }
            monitor-exit(r164)     // Catch:{ all -> 0x1136 }
            com.eternal.settings.SettingModel r0 = r1.mModel
            r8 = 549755813887(0x7fffffffff, double:2.71615461243E-312)
            long r8 = r8 & r2
            r16 = 412316860416(0x6000000000, double:2.037115959327E-312)
            r18 = 536870912(0x20000000, double:2.652494739E-315)
            r20 = 412317908992(0x6000100000, double:2.03712113998E-312)
            r22 = 412317909120(0x6000100080, double:2.037121140613E-312)
            r24 = 412316860544(0x6000000080, double:2.03711595996E-312)
            r26 = 412316860432(0x6000000010, double:2.037115959406E-312)
            r28 = 412316860560(0x6000000090, double:2.03711596004E-312)
            r30 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            r32 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
            r34 = 414464344064(0x6080000000, double:2.04772593828E-312)
            r36 = 412318957569(0x6000200001, double:2.03712632064E-312)
            r38 = 412316860417(0x6000000001, double:2.03711595933E-312)
            r40 = 412316860420(0x6000000004, double:2.037115959346E-312)
            r45 = 0
            int r46 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r46 == 0) goto L_0x0bef
            long r8 = r2 & r16
            int r46 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r46 == 0) goto L_0x00a0
            if (r0 == 0) goto L_0x00a0
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r8 = r0.nameChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onDelete
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r10 = r0.onTmpC
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r11 = r0.onTabSystem
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r14 = r0.portNameChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onTabController
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onEditTimeZone
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onTabPort
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onWifiSetting
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r5 = r0.onReset
            r53 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onBack
            r54 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onUpdate
            r55 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onEditType
            r56 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onShare
            r57 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onConfirm
            r58 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onTmpF
            r162 = r5
            r5 = r4
            r4 = r55
            r55 = r15
            r15 = r14
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r162
            goto L_0x00b7
        L_0x00a0:
            r4 = r45
            r5 = r4
            r8 = r5
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r53 = r15
            r54 = r53
            r55 = r54
            r56 = r55
            r57 = r56
            r58 = r57
        L_0x00b7:
            long r59 = r2 & r38
            r51 = 0
            int r61 = (r59 > r51 ? 1 : (r59 == r51 ? 0 : -1))
            r59 = r4
            if (r61 == 0) goto L_0x00f5
            if (r0 == 0) goto L_0x00c8
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.deviceTimeAvailable
            r60 = r5
            goto L_0x00cc
        L_0x00c8:
            r60 = r5
            r4 = r45
        L_0x00cc:
            r5 = 0
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x00d9
            java.lang.Object r5 = r4.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x00db
        L_0x00d9:
            r5 = r45
        L_0x00db:
            boolean r62 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            if (r61 == 0) goto L_0x00e8
            if (r62 == 0) goto L_0x00e6
            long r2 = r2 | r30
            goto L_0x00e8
        L_0x00e6:
            long r2 = r2 | r32
        L_0x00e8:
            if (r62 == 0) goto L_0x00ed
            r61 = 0
            goto L_0x00ef
        L_0x00ed:
            r61 = 8
        L_0x00ef:
            r49 = 412316860418(0x6000000002, double:2.037115959337E-312)
            goto L_0x0103
        L_0x00f5:
            r60 = r5
            r4 = r45
            r5 = r4
            r49 = 412316860418(0x6000000002, double:2.037115959337E-312)
            r61 = 0
            r62 = 0
        L_0x0103:
            long r63 = r2 & r49
            r51 = 0
            int r65 = (r63 > r51 ? 1 : (r63 == r51 ? 0 : -1))
            r63 = r4
            if (r65 == 0) goto L_0x014d
            if (r0 == 0) goto L_0x0114
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.portNameVisible
            r64 = r5
            goto L_0x0118
        L_0x0114:
            r64 = r5
            r4 = r45
        L_0x0118:
            r5 = 1
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0125
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0127
        L_0x0125:
            r4 = r45
        L_0x0127:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r65 == 0) goto L_0x0140
            if (r4 == 0) goto L_0x0137
            r65 = 16384(0x4000, double:8.0948E-320)
            long r5 = r6 | r65
            r65 = 1048576(0x100000, double:5.180654E-318)
            goto L_0x013e
        L_0x0137:
            r65 = 8192(0x2000, double:4.0474E-320)
            long r5 = r6 | r65
            r65 = 524288(0x80000, double:2.590327E-318)
        L_0x013e:
            long r6 = r5 | r65
        L_0x0140:
            if (r4 == 0) goto L_0x0144
            r5 = 0
            goto L_0x0146
        L_0x0144:
            r5 = 8
        L_0x0146:
            if (r4 == 0) goto L_0x014b
            r4 = 8
            goto L_0x0151
        L_0x014b:
            r4 = 0
            goto L_0x0151
        L_0x014d:
            r64 = r5
            r4 = 0
            r5 = 0
        L_0x0151:
            long r65 = r2 & r40
            r51 = 0
            int r67 = (r65 > r51 ? 1 : (r65 == r51 ? 0 : -1))
            r65 = r4
            if (r67 == 0) goto L_0x025b
            if (r0 == 0) goto L_0x0173
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r4 = r0.selectTabDevice
            java.util.Objects.requireNonNull(r0)
            r66 = 2
            java.util.Objects.requireNonNull(r0)
            java.util.Objects.requireNonNull(r0)
            r66 = r5
            r68 = r8
            r69 = r9
            r5 = 2
            r8 = 1
            goto L_0x017d
        L_0x0173:
            r66 = r5
            r68 = r8
            r69 = r9
            r4 = r45
            r5 = 0
            r8 = 0
        L_0x017d:
            r9 = 2
            r1.updateLiveDataRegistration(r9, r4)
            if (r4 == 0) goto L_0x018a
            java.lang.Object r4 = r4.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x018c
        L_0x018a:
            r4 = r45
        L_0x018c:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            if (r4 != r8) goto L_0x0194
            r8 = 1
            goto L_0x0195
        L_0x0194:
            r8 = 0
        L_0x0195:
            if (r4 != 0) goto L_0x0199
            r9 = 1
            goto L_0x019a
        L_0x0199:
            r9 = 0
        L_0x019a:
            if (r4 != r5) goto L_0x019e
            r4 = 1
            goto L_0x019f
        L_0x019e:
            r4 = 0
        L_0x019f:
            if (r67 == 0) goto L_0x01b2
            if (r8 == 0) goto L_0x01aa
            r70 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            long r2 = r2 | r70
            r70 = 256(0x100, double:1.265E-321)
            goto L_0x01b0
        L_0x01aa:
            r70 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            long r2 = r2 | r70
            r70 = 128(0x80, double:6.32E-322)
        L_0x01b0:
            long r6 = r6 | r70
        L_0x01b2:
            long r70 = r2 & r40
            r51 = 0
            int r5 = (r70 > r51 ? 1 : (r70 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x01d1
            if (r9 == 0) goto L_0x01c6
            r70 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            long r2 = r2 | r70
            r70 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            goto L_0x01cf
        L_0x01c6:
            r70 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            long r2 = r2 | r70
            r70 = 549755813888(0x8000000000, double:2.716154612436E-312)
        L_0x01cf:
            long r6 = r6 | r70
        L_0x01d1:
            long r70 = r2 & r40
            r51 = 0
            int r5 = (r70 > r51 ? 1 : (r70 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x01f9
            if (r4 == 0) goto L_0x01e9
            r70 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            long r2 = r2 | r70
            r70 = 16
            long r5 = r6 | r70
            r70 = 4096(0x1000, double:2.0237E-320)
            goto L_0x01f6
        L_0x01e9:
            r70 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
            long r2 = r2 | r70
            r70 = 8
            long r5 = r6 | r70
            r70 = 2048(0x800, double:1.0118E-320)
        L_0x01f6:
            long r5 = r5 | r70
            r6 = r5
        L_0x01f9:
            if (r8 == 0) goto L_0x01fd
            r5 = 0
            goto L_0x01ff
        L_0x01fd:
            r5 = 8
        L_0x01ff:
            if (r8 == 0) goto L_0x020c
            android.widget.TextView r8 = r1.tvTabPort
            r70 = r2
            int r2 = com.eternal.settings.C2668R.C2669color.color_15BAFF
            int r2 = getColorFromResource(r8, r2)
            goto L_0x0216
        L_0x020c:
            r70 = r2
            android.widget.TextView r2 = r1.tvTabPort
            int r3 = com.eternal.settings.C2668R.C2669color.white
            int r2 = getColorFromResource(r2, r3)
        L_0x0216:
            if (r9 == 0) goto L_0x021a
            r3 = 0
            goto L_0x021c
        L_0x021a:
            r3 = 8
        L_0x021c:
            android.widget.TextView r8 = r1.tvTabController
            if (r9 == 0) goto L_0x0223
            int r9 = com.eternal.settings.C2668R.C2669color.color_15BAFF
            goto L_0x0225
        L_0x0223:
            int r9 = com.eternal.settings.C2668R.C2669color.white
        L_0x0225:
            int r8 = getColorFromResource(r8, r9)
            if (r4 == 0) goto L_0x0236
            android.widget.TextView r9 = r1.tvTabSystem
            r67 = r2
            int r2 = com.eternal.settings.C2668R.C2669color.color_15BAFF
            int r2 = getColorFromResource(r9, r2)
            goto L_0x0240
        L_0x0236:
            r67 = r2
            android.widget.TextView r2 = r1.tvTabSystem
            int r9 = com.eternal.settings.C2668R.C2669color.white
            int r2 = getColorFromResource(r2, r9)
        L_0x0240:
            if (r4 == 0) goto L_0x0245
            r9 = 8
            goto L_0x0246
        L_0x0245:
            r9 = 0
        L_0x0246:
            if (r4 == 0) goto L_0x024a
            r4 = 0
            goto L_0x024c
        L_0x024a:
            r4 = 8
        L_0x024c:
            r162 = r4
            r4 = r2
            r163 = r9
            r9 = r3
            r2 = r70
            r71 = r162
            r70 = r67
            r67 = r163
            goto L_0x026b
        L_0x025b:
            r66 = r5
            r68 = r8
            r69 = r9
            r4 = 0
            r5 = 0
            r8 = 0
            r9 = 0
            r67 = 0
            r70 = 0
            r71 = 0
        L_0x026b:
            r72 = 412316860424(0x6000000008, double:2.037115959366E-312)
            long r72 = r2 & r72
            r51 = 0
            int r74 = (r72 > r51 ? 1 : (r72 == r51 ? 0 : -1))
            r72 = r4
            if (r74 == 0) goto L_0x0292
            if (r0 == 0) goto L_0x0281
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.deviceTime
            r73 = r5
            goto L_0x0285
        L_0x0281:
            r73 = r5
            r4 = r45
        L_0x0285:
            r5 = 3
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0294
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0296
        L_0x0292:
            r73 = r5
        L_0x0294:
            r4 = r45
        L_0x0296:
            long r74 = r2 & r28
            r51 = 0
            int r5 = (r74 > r51 ? 1 : (r74 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x02d9
            if (r0 == 0) goto L_0x02a5
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.transAndBufferVpdVisible
            r74 = r4
            goto L_0x02a9
        L_0x02a5:
            r74 = r4
            r5 = r45
        L_0x02a9:
            r4 = 4
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x02b6
            java.lang.Object r4 = r5.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x02b8
        L_0x02b6:
            r4 = r45
        L_0x02b8:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            long r75 = r2 & r26
            r51 = 0
            int r5 = (r75 > r51 ? 1 : (r75 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x02cd
            if (r4 == 0) goto L_0x02c9
            r75 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            goto L_0x02cb
        L_0x02c9:
            r75 = 144115188075855872(0x200000000000000, double:4.7783097267364807E-299)
        L_0x02cb:
            long r2 = r2 | r75
        L_0x02cd:
            long r75 = r2 & r26
            int r5 = (r75 > r51 ? 1 : (r75 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x02de
            if (r4 == 0) goto L_0x02d6
            goto L_0x02de
        L_0x02d6:
            r5 = 8
            goto L_0x02df
        L_0x02d9:
            r74 = r4
            r51 = 0
            r4 = 0
        L_0x02de:
            r5 = 0
        L_0x02df:
            r75 = 412316860448(0x6000000020, double:2.037115959485E-312)
            long r75 = r2 & r75
            int r77 = (r75 > r51 ? 1 : (r75 == r51 ? 0 : -1))
            r75 = r5
            if (r77 == 0) goto L_0x031b
            if (r0 == 0) goto L_0x02f3
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.tabLayoutVisible
            r76 = r8
            goto L_0x02f7
        L_0x02f3:
            r76 = r8
            r5 = r45
        L_0x02f7:
            r8 = 5
            r1.updateLiveDataRegistration(r8, r5)
            if (r5 == 0) goto L_0x0304
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x0306
        L_0x0304:
            r5 = r45
        L_0x0306:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            if (r77 == 0) goto L_0x0315
            if (r5 == 0) goto L_0x0311
            r77 = 4611686018427387904(0x4000000000000000, double:2.0)
            goto L_0x0313
        L_0x0311:
            r77 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
        L_0x0313:
            long r2 = r2 | r77
        L_0x0315:
            if (r5 == 0) goto L_0x0318
            goto L_0x031d
        L_0x0318:
            r5 = 8
            goto L_0x031e
        L_0x031b:
            r76 = r8
        L_0x031d:
            r5 = 0
        L_0x031e:
            r77 = 412316860480(0x6000000040, double:2.037115959643E-312)
            long r77 = r2 & r77
            r51 = 0
            int r8 = (r77 > r51 ? 1 : (r77 == r51 ? 0 : -1))
            r77 = r5
            if (r8 == 0) goto L_0x035e
            if (r0 == 0) goto L_0x0334
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.portTypeVisible
            r78 = r9
            goto L_0x0338
        L_0x0334:
            r78 = r9
            r5 = r45
        L_0x0338:
            r9 = 6
            r1.updateLiveDataRegistration(r9, r5)
            if (r5 == 0) goto L_0x0345
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x0347
        L_0x0345:
            r5 = r45
        L_0x0347:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            if (r8 == 0) goto L_0x0358
            if (r5 == 0) goto L_0x0352
            r8 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            goto L_0x0357
        L_0x0352:
            r8 = 140737488355328(0x800000000000, double:6.953355807835E-310)
        L_0x0357:
            long r2 = r2 | r8
        L_0x0358:
            if (r5 == 0) goto L_0x035b
            goto L_0x0360
        L_0x035b:
            r5 = 8
            goto L_0x0361
        L_0x035e:
            r78 = r9
        L_0x0360:
            r5 = 0
        L_0x0361:
            r8 = 412317909136(0x6000100090, double:2.03712114069E-312)
            long r8 = r8 & r2
            r51 = 0
            int r79 = (r8 > r51 ? 1 : (r8 == r51 ? 0 : -1))
            if (r79 == 0) goto L_0x03c1
            if (r0 == 0) goto L_0x0372
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.bufferVisible
            goto L_0x0374
        L_0x0372:
            r8 = r45
        L_0x0374:
            r9 = 7
            r1.updateLiveDataRegistration(r9, r8)
            if (r8 == 0) goto L_0x0381
            java.lang.Object r8 = r8.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x0383
        L_0x0381:
            r8 = r45
        L_0x0383:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            long r79 = r2 & r24
            r51 = 0
            int r9 = (r79 > r51 ? 1 : (r79 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x039a
            if (r8 == 0) goto L_0x0396
            r79 = 1
            long r6 = r6 | r79
            goto L_0x039a
        L_0x0396:
            r79 = -9223372036854775808
            long r2 = r2 | r79
        L_0x039a:
            long r79 = r2 & r24
            int r9 = (r79 > r51 ? 1 : (r79 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x03a6
            if (r8 == 0) goto L_0x03a3
            goto L_0x03a6
        L_0x03a3:
            r9 = 8
            goto L_0x03a7
        L_0x03a6:
            r9 = 0
        L_0x03a7:
            long r79 = r2 & r28
            int r81 = (r79 > r51 ? 1 : (r79 == r51 ? 0 : -1))
            if (r81 == 0) goto L_0x03bf
            r4 = r4 & r8
            if (r81 == 0) goto L_0x03b9
            if (r4 == 0) goto L_0x03b5
            r79 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            goto L_0x03b7
        L_0x03b5:
            r79 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
        L_0x03b7:
            long r6 = r6 | r79
        L_0x03b9:
            if (r4 == 0) goto L_0x03bc
            goto L_0x03bf
        L_0x03bc:
            r4 = 8
            goto L_0x03c4
        L_0x03bf:
            r4 = 0
            goto L_0x03c4
        L_0x03c1:
            r4 = 0
            r8 = 0
            r9 = 0
        L_0x03c4:
            r79 = 414464344320(0x6080000100, double:2.047725939546E-312)
            long r79 = r2 & r79
            r51 = 0
            int r81 = (r79 > r51 ? 1 : (r79 == r51 ? 0 : -1))
            r79 = r4
            if (r81 == 0) goto L_0x03fe
            if (r0 == 0) goto L_0x03da
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.shareVisible
            r48 = r5
            goto L_0x03de
        L_0x03da:
            r48 = r5
            r4 = r45
        L_0x03de:
            r5 = 8
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x03ec
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x03ee
        L_0x03ec:
            r4 = r45
        L_0x03ee:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r81 == 0) goto L_0x0403
            if (r4 == 0) goto L_0x03f9
            r80 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
            goto L_0x03fb
        L_0x03f9:
            r80 = 144115188075855872(0x200000000000000, double:4.7783097267364807E-299)
        L_0x03fb:
            long r6 = r6 | r80
            goto L_0x0403
        L_0x03fe:
            r48 = r5
            r5 = 8
            r4 = 0
        L_0x0403:
            r80 = 412316860928(0x6000000200, double:2.037115961856E-312)
            long r80 = r2 & r80
            r51 = 0
            int r82 = (r80 > r51 ? 1 : (r80 == r51 ? 0 : -1))
            if (r82 == 0) goto L_0x0429
            if (r0 == 0) goto L_0x0417
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.deviceTimeZone
            r81 = r4
            goto L_0x041b
        L_0x0417:
            r81 = r4
            r5 = r45
        L_0x041b:
            r4 = 9
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x042b
            java.lang.Object r4 = r5.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x042d
        L_0x0429:
            r81 = r4
        L_0x042b:
            r4 = r45
        L_0x042d:
            r82 = 412316861440(0x6000000400, double:2.037115964386E-312)
            long r82 = r2 & r82
            r51 = 0
            int r5 = (r82 > r51 ? 1 : (r82 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0453
            if (r0 == 0) goto L_0x0441
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.subtitle
            r82 = r4
            goto L_0x0445
        L_0x0441:
            r82 = r4
            r5 = r45
        L_0x0445:
            r4 = 10
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x0455
            java.lang.Object r4 = r5.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0457
        L_0x0453:
            r82 = r4
        L_0x0455:
            r4 = r45
        L_0x0457:
            r83 = 412316862464(0x6000000800, double:2.037115969445E-312)
            long r83 = r2 & r83
            r51 = 0
            int r5 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x047d
            if (r0 == 0) goto L_0x046b
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.portTypeText
            r83 = r4
            goto L_0x046f
        L_0x046b:
            r83 = r4
            r5 = r45
        L_0x046f:
            r4 = 11
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x047f
            java.lang.Object r4 = r5.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0481
        L_0x047d:
            r83 = r4
        L_0x047f:
            r4 = r45
        L_0x0481:
            r84 = 412316864512(0x6000001000, double:2.037115979564E-312)
            long r84 = r2 & r84
            r51 = 0
            int r5 = (r84 > r51 ? 1 : (r84 == r51 ? 0 : -1))
            r84 = r4
            if (r5 == 0) goto L_0x058e
            if (r0 == 0) goto L_0x0497
            androidx.databinding.ObservableBoolean r4 = r0.isPlugPortType
            r85 = r9
            goto L_0x049b
        L_0x0497:
            r85 = r9
            r4 = r45
        L_0x049b:
            r9 = 12
            r1.updateRegistration((int) r9, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x04a7
            boolean r4 = r4.get()
            goto L_0x04a8
        L_0x04a7:
            r4 = 0
        L_0x04a8:
            if (r5 == 0) goto L_0x04e9
            if (r4 == 0) goto L_0x04ca
            r86 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            long r2 = r2 | r86
            r86 = 4194304(0x400000, double:2.0722615E-317)
            long r5 = r6 | r86
            r86 = 4294967296(0x100000000, double:2.121995791E-314)
            long r5 = r5 | r86
            r86 = 68719476736(0x1000000000, double:3.39519326554E-313)
            long r5 = r5 | r86
            r86 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r5 = r5 | r86
            r86 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
            goto L_0x04e7
        L_0x04ca:
            r86 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
            long r2 = r2 | r86
            r86 = 2097152(0x200000, double:1.0361308E-317)
            long r5 = r6 | r86
            r86 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r5 = r5 | r86
            r86 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r5 = r5 | r86
            r86 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            long r5 = r5 | r86
            r86 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
        L_0x04e7:
            long r6 = r5 | r86
        L_0x04e9:
            android.widget.RadioButton r5 = r1.rbAc
            android.content.Context r5 = r5.getContext()
            if (r4 == 0) goto L_0x04f4
            int r9 = com.eternal.settings.C2668R.C2670drawable.ac_selector
            goto L_0x04f6
        L_0x04f4:
            int r9 = com.eternal.settings.C2668R.C2670drawable.ac_circle_selector
        L_0x04f6:
            android.graphics.drawable.Drawable r5 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r5, r9)
            if (r4 == 0) goto L_0x050b
            android.widget.RadioButton r9 = r1.rbDehum
            android.content.Context r9 = r9.getContext()
            r86 = r2
            int r2 = com.eternal.settings.C2668R.C2670drawable.dehum_selector
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r9, r2)
            goto L_0x0519
        L_0x050b:
            r86 = r2
            android.widget.RadioButton r2 = r1.rbDehum
            android.content.Context r2 = r2.getContext()
            int r3 = com.eternal.settings.C2668R.C2670drawable.dehum_circle_selector
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r2, r3)
        L_0x0519:
            android.widget.RadioButton r3 = r1.rbFan
            android.content.Context r3 = r3.getContext()
            if (r4 == 0) goto L_0x0524
            int r9 = com.eternal.settings.C2668R.C2670drawable.fan_selector
            goto L_0x0526
        L_0x0524:
            int r9 = com.eternal.settings.C2668R.C2670drawable.fan_circle_selector
        L_0x0526:
            android.graphics.drawable.Drawable r3 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r3, r9)
            if (r4 == 0) goto L_0x053b
            android.widget.RadioButton r9 = r1.rbHeater
            android.content.Context r9 = r9.getContext()
            r88 = r2
            int r2 = com.eternal.settings.C2668R.C2670drawable.heater_selector
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r9, r2)
            goto L_0x0549
        L_0x053b:
            r88 = r2
            android.widget.RadioButton r2 = r1.rbHeater
            android.content.Context r2 = r2.getContext()
            int r9 = com.eternal.settings.C2668R.C2670drawable.heater_circle_selector
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r2, r9)
        L_0x0549:
            if (r4 == 0) goto L_0x055a
            android.widget.RadioButton r9 = r1.rbHum
            android.content.Context r9 = r9.getContext()
            r89 = r2
            int r2 = com.eternal.settings.C2668R.C2670drawable.hum_selector
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r9, r2)
            goto L_0x0568
        L_0x055a:
            r89 = r2
            android.widget.RadioButton r2 = r1.rbHum
            android.content.Context r2 = r2.getContext()
            int r9 = com.eternal.settings.C2668R.C2670drawable.hum_circle_selector
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r2, r9)
        L_0x0568:
            if (r4 == 0) goto L_0x0573
            android.widget.RadioButton r4 = r1.rbLight
            android.content.Context r4 = r4.getContext()
            int r9 = com.eternal.settings.C2668R.C2670drawable.light_selector
            goto L_0x057b
        L_0x0573:
            android.widget.RadioButton r4 = r1.rbLight
            android.content.Context r4 = r4.getContext()
            int r9 = com.eternal.settings.C2668R.C2670drawable.light_circle_selector
        L_0x057b:
            android.graphics.drawable.Drawable r4 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r4, r9)
            r162 = r4
            r4 = r2
            r163 = r5
            r5 = r3
            r2 = r86
            r86 = r6
            r6 = r162
            r7 = r163
            goto L_0x059b
        L_0x058e:
            r85 = r9
            r86 = r6
            r4 = r45
            r5 = r4
            r6 = r5
            r7 = r6
            r88 = r7
            r89 = r88
        L_0x059b:
            r90 = 412316868608(0x6000002000, double:2.0371159998E-312)
            long r90 = r2 & r90
            r51 = 0
            int r9 = (r90 > r51 ? 1 : (r90 == r51 ? 0 : -1))
            r90 = r4
            if (r9 == 0) goto L_0x05da
            if (r0 == 0) goto L_0x05b1
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.vpdLeafVisible
            r91 = r5
            goto L_0x05b5
        L_0x05b1:
            r91 = r5
            r4 = r45
        L_0x05b5:
            r5 = 13
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x05c3
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x05c5
        L_0x05c3:
            r4 = r45
        L_0x05c5:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r9 == 0) goto L_0x05d4
            if (r4 == 0) goto L_0x05d0
            r92 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            goto L_0x05d2
        L_0x05d0:
            r92 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
        L_0x05d2:
            long r86 = r86 | r92
        L_0x05d4:
            if (r4 == 0) goto L_0x05d7
            goto L_0x05dc
        L_0x05d7:
            r4 = 8
            goto L_0x05dd
        L_0x05da:
            r91 = r5
        L_0x05dc:
            r4 = 0
        L_0x05dd:
            r92 = 412316876800(0x6000004000, double:2.037116040274E-312)
            long r92 = r2 & r92
            r51 = 0
            int r5 = (r92 > r51 ? 1 : (r92 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0620
            if (r0 == 0) goto L_0x05f1
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.transitionVisible
            r92 = r4
            goto L_0x05f5
        L_0x05f1:
            r92 = r4
            r9 = r45
        L_0x05f5:
            r4 = 14
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0603
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0605
        L_0x0603:
            r4 = r45
        L_0x0605:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x061a
            if (r4 == 0) goto L_0x0613
            r93 = 17179869184(0x400000000, double:8.4879831639E-314)
            goto L_0x0618
        L_0x0613:
            r93 = 8589934592(0x200000000, double:4.243991582E-314)
        L_0x0618:
            long r86 = r86 | r93
        L_0x061a:
            if (r4 == 0) goto L_0x061d
            goto L_0x0622
        L_0x061d:
            r4 = 8
            goto L_0x0623
        L_0x0620:
            r92 = r4
        L_0x0622:
            r4 = 0
        L_0x0623:
            r93 = 412316893184(0x6000008000, double:2.03711612122E-312)
            long r93 = r2 & r93
            r51 = 0
            int r5 = (r93 > r51 ? 1 : (r93 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0666
            if (r0 == 0) goto L_0x0637
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.updateVisible
            r93 = r4
            goto L_0x063b
        L_0x0637:
            r93 = r4
            r9 = r45
        L_0x063b:
            r4 = 15
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0649
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x064b
        L_0x0649:
            r4 = r45
        L_0x064b:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x0660
            if (r4 == 0) goto L_0x0659
            r94 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
            goto L_0x065e
        L_0x0659:
            r94 = 8796093022208(0x80000000000, double:4.345847379897E-311)
        L_0x065e:
            long r2 = r2 | r94
        L_0x0660:
            if (r4 == 0) goto L_0x0663
            goto L_0x0668
        L_0x0663:
            r4 = 8
            goto L_0x0669
        L_0x0666:
            r93 = r4
        L_0x0668:
            r4 = 0
        L_0x0669:
            r94 = 412316925952(0x6000010000, double:2.037116283118E-312)
            long r94 = r2 & r94
            r51 = 0
            int r5 = (r94 > r51 ? 1 : (r94 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0692
            if (r0 == 0) goto L_0x067b
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r5 = r0.portTypeRes
            goto L_0x067d
        L_0x067b:
            r5 = r45
        L_0x067d:
            r9 = 16
            r1.updateLiveDataRegistration(r9, r5)
            if (r5 == 0) goto L_0x068b
            java.lang.Object r5 = r5.getValue()
            java.lang.Integer r5 = (java.lang.Integer) r5
            goto L_0x068d
        L_0x068b:
            r5 = r45
        L_0x068d:
            int r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r5)
            goto L_0x0693
        L_0x0692:
            r5 = 0
        L_0x0693:
            r94 = 412316991488(0x6000020000, double:2.03711660691E-312)
            long r94 = r2 & r94
            r51 = 0
            int r9 = (r94 > r51 ? 1 : (r94 == r51 ? 0 : -1))
            r94 = r4
            if (r9 == 0) goto L_0x06d2
            if (r0 == 0) goto L_0x06a9
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.wifiSettingVisible
            r95 = r5
            goto L_0x06ad
        L_0x06a9:
            r95 = r5
            r4 = r45
        L_0x06ad:
            r5 = 17
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x06bb
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x06bd
        L_0x06bb:
            r4 = r45
        L_0x06bd:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r9 == 0) goto L_0x06cc
            if (r4 == 0) goto L_0x06c8
            r96 = 4
            goto L_0x06ca
        L_0x06c8:
            r96 = 2
        L_0x06ca:
            long r86 = r86 | r96
        L_0x06cc:
            if (r4 == 0) goto L_0x06cf
            goto L_0x06d4
        L_0x06cf:
            r4 = 8
            goto L_0x06d5
        L_0x06d2:
            r95 = r5
        L_0x06d4:
            r4 = 0
        L_0x06d5:
            r96 = 412317122560(0x6000040000, double:2.03711725449E-312)
            long r96 = r2 & r96
            r51 = 0
            int r5 = (r96 > r51 ? 1 : (r96 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x06f9
            if (r0 == 0) goto L_0x06e7
            androidx.databinding.ObservableBoolean r5 = r0.committing
            goto L_0x06e9
        L_0x06e7:
            r5 = r45
        L_0x06e9:
            r9 = 18
            r1.updateRegistration((int) r9, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x06f5
            boolean r5 = r5.get()
            goto L_0x06f6
        L_0x06f5:
            r5 = 0
        L_0x06f6:
            r9 = 1
            r5 = r5 ^ r9
            goto L_0x06fa
        L_0x06f9:
            r5 = 0
        L_0x06fa:
            r96 = 412317384704(0x6000080000, double:2.037118549654E-312)
            long r96 = r2 & r96
            r51 = 0
            int r9 = (r96 > r51 ? 1 : (r96 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0720
            if (r0 == 0) goto L_0x070e
            androidx.lifecycle.MutableLiveData<java.lang.String> r9 = r0.timeText
            r96 = r4
            goto L_0x0712
        L_0x070e:
            r96 = r4
            r9 = r45
        L_0x0712:
            r4 = 19
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0722
            java.lang.Object r4 = r9.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0724
        L_0x0720:
            r96 = r4
        L_0x0722:
            r4 = r45
        L_0x0724:
            long r97 = r2 & r22
            r51 = 0
            int r9 = (r97 > r51 ? 1 : (r97 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0786
            if (r0 == 0) goto L_0x0733
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.humVisible
            r97 = r4
            goto L_0x0737
        L_0x0733:
            r97 = r4
            r9 = r45
        L_0x0737:
            r4 = 20
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0745
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0747
        L_0x0745:
            r4 = r45
        L_0x0747:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            long r98 = r2 & r20
            r51 = 0
            int r9 = (r98 > r51 ? 1 : (r98 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0762
            if (r4 == 0) goto L_0x075b
            r98 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            goto L_0x0760
        L_0x075b:
            r98 = 549755813888(0x8000000000, double:2.716154612436E-312)
        L_0x0760:
            long r2 = r2 | r98
        L_0x0762:
            long r98 = r2 & r20
            int r9 = (r98 > r51 ? 1 : (r98 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x076e
            if (r4 == 0) goto L_0x076b
            goto L_0x076e
        L_0x076b:
            r9 = 8
            goto L_0x076f
        L_0x076e:
            r9 = 0
        L_0x076f:
            r4 = r4 & r8
            long r98 = r2 & r22
            int r8 = (r98 > r51 ? 1 : (r98 == r51 ? 0 : -1))
            if (r8 == 0) goto L_0x077f
            if (r4 == 0) goto L_0x077b
            r98 = 64
            goto L_0x077d
        L_0x077b:
            r98 = 32
        L_0x077d:
            long r86 = r86 | r98
        L_0x077f:
            if (r4 == 0) goto L_0x0783
            r4 = 0
            goto L_0x078a
        L_0x0783:
            r4 = 8
            goto L_0x078a
        L_0x0786:
            r97 = r4
            r4 = 0
            r9 = 0
        L_0x078a:
            long r98 = r2 & r36
            r51 = 0
            int r8 = (r98 > r51 ? 1 : (r98 == r51 ? 0 : -1))
            r98 = r4
            if (r8 == 0) goto L_0x07df
            if (r0 == 0) goto L_0x079b
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.updateAvailable
            r99 = r5
            goto L_0x079f
        L_0x079b:
            r99 = r5
            r4 = r45
        L_0x079f:
            r5 = 21
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x07ad
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x07af
        L_0x07ad:
            r4 = r45
        L_0x07af:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            r100 = 412318957568(0x6000200000, double:2.037126320634E-312)
            long r100 = r2 & r100
            r51 = 0
            int r5 = (r100 > r51 ? 1 : (r100 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x07cb
            if (r4 == 0) goto L_0x07c6
            r100 = 262144(0x40000, double:1.295163E-318)
            goto L_0x07c9
        L_0x07c6:
            r100 = 131072(0x20000, double:6.47582E-319)
        L_0x07c9:
            long r86 = r86 | r100
        L_0x07cb:
            if (r8 == 0) goto L_0x07d7
            if (r4 == 0) goto L_0x07d5
            r100 = 1073741824(0x40000000, double:5.304989477E-315)
            long r86 = r86 | r100
            goto L_0x07d7
        L_0x07d5:
            long r86 = r86 | r18
        L_0x07d7:
            if (r5 == 0) goto L_0x07e2
            if (r4 == 0) goto L_0x07dc
            goto L_0x07e2
        L_0x07dc:
            r5 = 8
            goto L_0x07e3
        L_0x07df:
            r99 = r5
            r4 = 0
        L_0x07e2:
            r5 = 0
        L_0x07e3:
            r46 = 414468538368(0x6080400000, double:2.047746660897E-312)
            long r100 = r2 & r46
            r51 = 0
            int r8 = (r100 > r51 ? 1 : (r100 == r51 ? 0 : -1))
            r100 = r4
            if (r8 == 0) goto L_0x081b
            if (r0 == 0) goto L_0x07f9
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.resetVisible
            r101 = r5
            goto L_0x07fd
        L_0x07f9:
            r101 = r5
            r4 = r45
        L_0x07fd:
            r5 = 22
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x080b
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x080d
        L_0x080b:
            r4 = r45
        L_0x080d:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r8 == 0) goto L_0x081e
            if (r4 == 0) goto L_0x0818
            long r86 = r86 | r30
            goto L_0x081e
        L_0x0818:
            long r86 = r86 | r32
            goto L_0x081e
        L_0x081b:
            r101 = r5
            r4 = 0
        L_0x081e:
            r102 = 412325249024(0x6000800000, double:2.037157404557E-312)
            long r102 = r2 & r102
            r51 = 0
            int r5 = (r102 > r51 ? 1 : (r102 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0840
            if (r0 == 0) goto L_0x0830
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.deviceName
            goto L_0x0832
        L_0x0830:
            r5 = r45
        L_0x0832:
            r8 = 23
            r1.updateLiveDataRegistration(r8, r5)
            if (r5 == 0) goto L_0x0840
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0842
        L_0x0840:
            r5 = r45
        L_0x0842:
            r102 = 412333637632(0x6001000000, double:2.037198849787E-312)
            long r102 = r2 & r102
            r51 = 0
            int r8 = (r102 > r51 ? 1 : (r102 == r51 ? 0 : -1))
            r102 = r4
            if (r8 == 0) goto L_0x0883
            if (r0 == 0) goto L_0x0858
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.levelVisible
            r103 = r5
            goto L_0x085c
        L_0x0858:
            r103 = r5
            r4 = r45
        L_0x085c:
            r5 = 24
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x086a
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x086c
        L_0x086a:
            r4 = r45
        L_0x086c:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r8 == 0) goto L_0x087d
            if (r4 == 0) goto L_0x0878
            r104 = 268435456(0x10000000, double:1.32624737E-315)
            goto L_0x087b
        L_0x0878:
            r104 = 134217728(0x8000000, double:6.63123685E-316)
        L_0x087b:
            long r86 = r86 | r104
        L_0x087d:
            if (r4 == 0) goto L_0x0880
            goto L_0x0885
        L_0x0880:
            r4 = 8
            goto L_0x0886
        L_0x0883:
            r103 = r5
        L_0x0885:
            r4 = 0
        L_0x0886:
            r104 = 412350414848(0x6002000000, double:2.03728174025E-312)
            long r104 = r2 & r104
            r51 = 0
            int r5 = (r104 > r51 ? 1 : (r104 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x08a8
            if (r0 == 0) goto L_0x0898
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.portName
            goto L_0x089a
        L_0x0898:
            r5 = r45
        L_0x089a:
            r8 = 25
            r1.updateLiveDataRegistration(r8, r5)
            if (r5 == 0) goto L_0x08a8
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x08aa
        L_0x08a8:
            r5 = r45
        L_0x08aa:
            r104 = 412383969280(0x6004000000, double:2.03744752117E-312)
            long r104 = r2 & r104
            r51 = 0
            int r8 = (r104 > r51 ? 1 : (r104 == r51 ? 0 : -1))
            r104 = r4
            if (r8 == 0) goto L_0x08ef
            if (r0 == 0) goto L_0x08c0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.timeVisible
            r105 = r5
            goto L_0x08c4
        L_0x08c0:
            r105 = r5
            r4 = r45
        L_0x08c4:
            r5 = 26
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x08d2
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x08d4
        L_0x08d2:
            r4 = r45
        L_0x08d4:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r8 == 0) goto L_0x08e9
            if (r4 == 0) goto L_0x08e2
            r106 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
            goto L_0x08e7
        L_0x08e2:
            r106 = 8796093022208(0x80000000000, double:4.345847379897E-311)
        L_0x08e7:
            long r86 = r86 | r106
        L_0x08e9:
            if (r4 == 0) goto L_0x08ec
            goto L_0x08f1
        L_0x08ec:
            r4 = 8
            goto L_0x08f2
        L_0x08ef:
            r105 = r5
        L_0x08f1:
            r4 = 0
        L_0x08f2:
            r106 = 412451078144(0x6008000000, double:2.03777908301E-312)
            long r106 = r2 & r106
            r51 = 0
            int r5 = (r106 > r51 ? 1 : (r106 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x0914
            if (r0 == 0) goto L_0x0904
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.firstTabText
            goto L_0x0906
        L_0x0904:
            r5 = r45
        L_0x0906:
            r8 = 27
            r1.updateLiveDataRegistration(r8, r5)
            if (r5 == 0) goto L_0x0914
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0916
        L_0x0914:
            r5 = r45
        L_0x0916:
            r106 = 412585295872(0x6010000000, double:2.038442206696E-312)
            long r106 = r2 & r106
            r51 = 0
            int r8 = (r106 > r51 ? 1 : (r106 == r51 ? 0 : -1))
            if (r8 == 0) goto L_0x093c
            if (r0 == 0) goto L_0x092a
            androidx.lifecycle.MutableLiveData<java.lang.String> r8 = r0.allPortText
            r106 = r4
            goto L_0x092e
        L_0x092a:
            r106 = r4
            r8 = r45
        L_0x092e:
            r4 = 28
            r1.updateLiveDataRegistration(r4, r8)
            if (r8 == 0) goto L_0x093e
            java.lang.Object r4 = r8.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0940
        L_0x093c:
            r106 = r4
        L_0x093e:
            r4 = r45
        L_0x0940:
            r107 = 412853731328(0x6020000000, double:2.039768454065E-312)
            long r107 = r2 & r107
            r51 = 0
            int r8 = (r107 > r51 ? 1 : (r107 == r51 ? 0 : -1))
            if (r8 == 0) goto L_0x0966
            if (r0 == 0) goto L_0x0954
            androidx.lifecycle.MutableLiveData<java.lang.String> r8 = r0.portTabText
            r107 = r4
            goto L_0x0958
        L_0x0954:
            r107 = r4
            r8 = r45
        L_0x0958:
            r4 = 29
            r1.updateLiveDataRegistration(r4, r8)
            if (r8 == 0) goto L_0x0968
            java.lang.Object r4 = r8.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x096a
        L_0x0966:
            r107 = r4
        L_0x0968:
            r4 = r45
        L_0x096a:
            r108 = 413390602240(0x6040000000, double:2.042420948804E-312)
            long r108 = r2 & r108
            r51 = 0
            int r8 = (r108 > r51 ? 1 : (r108 == r51 ? 0 : -1))
            r108 = r4
            if (r8 == 0) goto L_0x09c8
            if (r0 == 0) goto L_0x0980
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.isDegree
            r109 = r5
            goto L_0x0984
        L_0x0980:
            r109 = r5
            r4 = r45
        L_0x0984:
            r5 = 30
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0992
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0994
        L_0x0992:
            r4 = r45
        L_0x0994:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r8 == 0) goto L_0x09ab
            if (r4 == 0) goto L_0x09a3
            r110 = 1024(0x400, double:5.06E-321)
            long r86 = r86 | r110
            r110 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
            goto L_0x09a9
        L_0x09a3:
            r110 = 512(0x200, double:2.53E-321)
            long r86 = r86 | r110
            r110 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
        L_0x09a9:
            long r86 = r86 | r110
        L_0x09ab:
            android.widget.TextView r5 = r1.tvUnitC
            if (r4 == 0) goto L_0x09b2
            int r8 = com.eternal.settings.C2668R.C2669color.white
            goto L_0x09b4
        L_0x09b2:
            int r8 = com.eternal.settings.C2668R.C2669color.color_BFBFBF
        L_0x09b4:
            int r5 = getColorFromResource(r5, r8)
            if (r4 == 0) goto L_0x09bf
            android.widget.TextView r4 = r1.tvUnitF
            int r8 = com.eternal.settings.C2668R.C2669color.color_BFBFBF
            goto L_0x09c3
        L_0x09bf:
            android.widget.TextView r4 = r1.tvUnitF
            int r8 = com.eternal.settings.C2668R.C2669color.white
        L_0x09c3:
            int r4 = getColorFromResource(r4, r8)
            goto L_0x09cc
        L_0x09c8:
            r109 = r5
            r4 = 0
            r5 = 0
        L_0x09cc:
            long r110 = r2 & r34
            r51 = 0
            int r8 = (r110 > r51 ? 1 : (r110 == r51 ? 0 : -1))
            r110 = r4
            if (r8 == 0) goto L_0x0a39
            if (r0 == 0) goto L_0x09dd
            androidx.databinding.ObservableBoolean r4 = r0.isShare
            r111 = r5
            goto L_0x09e1
        L_0x09dd:
            r111 = r5
            r4 = r45
        L_0x09e1:
            r5 = 31
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x09ed
            boolean r5 = r4.get()
            goto L_0x09ee
        L_0x09ed:
            r5 = 0
        L_0x09ee:
            if (r8 == 0) goto L_0x0a06
            if (r5 == 0) goto L_0x09fa
            r112 = 67108864(0x4000000, double:3.31561842E-316)
            long r86 = r86 | r112
            r112 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            goto L_0x0a04
        L_0x09fa:
            r112 = 33554432(0x2000000, double:1.6578092E-316)
            long r86 = r86 | r112
            r112 = 140737488355328(0x800000000000, double:6.953355807835E-310)
        L_0x0a04:
            long r86 = r86 | r112
        L_0x0a06:
            r8 = r5 ^ 1
            r112 = r4
            if (r5 == 0) goto L_0x0a13
            android.widget.EditText r4 = r1.editPortName
            r113 = r6
            int r6 = com.eternal.settings.C2668R.C2669color.color_FFFFFF
            goto L_0x0a19
        L_0x0a13:
            r113 = r6
            android.widget.EditText r4 = r1.editPortName
            int r6 = com.eternal.settings.C2668R.C2669color.color_15BAFF
        L_0x0a19:
            int r4 = getColorFromResource(r4, r6)
            if (r5 == 0) goto L_0x0a2a
            android.widget.EditText r6 = r1.editName
            r114 = r4
            int r4 = com.eternal.settings.C2668R.C2669color.color_FFFFFF
            int r4 = getColorFromResource(r6, r4)
            goto L_0x0a34
        L_0x0a2a:
            r114 = r4
            android.widget.EditText r4 = r1.editName
            int r6 = com.eternal.settings.C2668R.C2669color.color_15BAFF
            int r4 = getColorFromResource(r4, r6)
        L_0x0a34:
            r6 = r5
            r5 = r4
            r4 = r112
            goto L_0x0a44
        L_0x0a39:
            r111 = r5
            r113 = r6
            r4 = r45
            r5 = 0
            r6 = 0
            r8 = 0
            r114 = 0
        L_0x0a44:
            r115 = 416611827712(0x6100000000, double:2.058335917236E-312)
            long r115 = r2 & r115
            r51 = 0
            int r112 = (r115 > r51 ? 1 : (r115 == r51 ? 0 : -1))
            if (r112 == 0) goto L_0x0a6c
            r112 = r4
            if (r0 == 0) goto L_0x0a5a
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.nameSuggest
            r115 = r5
            goto L_0x0a5e
        L_0x0a5a:
            r115 = r5
            r4 = r45
        L_0x0a5e:
            r5 = 32
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0a70
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0a72
        L_0x0a6c:
            r112 = r4
            r115 = r5
        L_0x0a70:
            r4 = r45
        L_0x0a72:
            r116 = 420906795008(0x6200000000, double:2.079555875146E-312)
            long r116 = r2 & r116
            r51 = 0
            int r5 = (r116 > r51 ? 1 : (r116 == r51 ? 0 : -1))
            r116 = r4
            if (r5 == 0) goto L_0x0ab5
            if (r0 == 0) goto L_0x0a88
            androidx.databinding.ObservableBoolean r4 = r0.portTypeExpand
            r117 = r6
            goto L_0x0a8c
        L_0x0a88:
            r117 = r6
            r4 = r45
        L_0x0a8c:
            r6 = 33
            r1.updateRegistration((int) r6, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0a98
            boolean r4 = r4.get()
            goto L_0x0a99
        L_0x0a98:
            r4 = 0
        L_0x0a99:
            if (r5 == 0) goto L_0x0aa3
            if (r4 == 0) goto L_0x0aa0
            r5 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            goto L_0x0aa2
        L_0x0aa0:
            r5 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
        L_0x0aa2:
            long r2 = r2 | r5
        L_0x0aa3:
            android.widget.TextView r5 = r1.mboundView40
            android.content.res.Resources r5 = r5.getResources()
            if (r4 == 0) goto L_0x0aae
            int r6 = com.eternal.settings.C2668R.string.tip_done_lowercase
            goto L_0x0ab0
        L_0x0aae:
            int r6 = com.eternal.settings.C2668R.string.tip_edit_lowercase
        L_0x0ab0:
            java.lang.String r5 = r5.getString(r6)
            goto L_0x0aba
        L_0x0ab5:
            r117 = r6
            r5 = r45
            r4 = 0
        L_0x0aba:
            r42 = 431644213248(0x6480000000, double:2.13260576992E-312)
            long r118 = r2 & r42
            r51 = 0
            int r6 = (r118 > r51 ? 1 : (r118 == r51 ? 0 : -1))
            r118 = r4
            if (r6 == 0) goto L_0x0afd
            if (r0 == 0) goto L_0x0ad0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.deviceTimeVisible
            r119 = r5
            goto L_0x0ad4
        L_0x0ad0:
            r119 = r5
            r4 = r45
        L_0x0ad4:
            r5 = 34
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0ae2
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0ae4
        L_0x0ae2:
            r4 = r45
        L_0x0ae4:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x0afa
            if (r4 == 0) goto L_0x0af2
            r5 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            goto L_0x0af7
        L_0x0af2:
            r5 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
        L_0x0af7:
            long r5 = r86 | r5
            goto L_0x0b02
        L_0x0afa:
            r5 = r86
            goto L_0x0b02
        L_0x0afd:
            r119 = r5
            r5 = r86
            r4 = 0
        L_0x0b02:
            r86 = 446676598784(0x6800000000, double:2.206875622604E-312)
            long r86 = r2 & r86
            r51 = 0
            int r120 = (r86 > r51 ? 1 : (r86 == r51 ? 0 : -1))
            r86 = r4
            if (r120 == 0) goto L_0x0b31
            if (r0 == 0) goto L_0x0b18
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r4 = r0.progress
            r120 = r5
            goto L_0x0b1c
        L_0x0b18:
            r120 = r5
            r4 = r45
        L_0x0b1c:
            r5 = 35
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0b2a
            java.lang.Object r4 = r4.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x0b2c
        L_0x0b2a:
            r4 = r45
        L_0x0b2c:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x0b34
        L_0x0b31:
            r120 = r5
            r4 = 0
        L_0x0b34:
            r5 = 481036337152(0x7000000000, double:2.37663528588E-312)
            long r5 = r5 & r2
            r51 = 0
            int r87 = (r5 > r51 ? 1 : (r5 == r51 ? 0 : -1))
            if (r87 == 0) goto L_0x0b66
            if (r0 == 0) goto L_0x0b45
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.portNameSuggest
            goto L_0x0b47
        L_0x0b45:
            r5 = r45
        L_0x0b47:
            r6 = 36
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x0b66
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            r144 = r4
            r145 = r5
            r151 = r7
            r139 = r9
            r152 = r11
            r153 = r12
            r154 = r13
            r155 = r14
            r6 = r15
            goto L_0x0b77
        L_0x0b66:
            r144 = r4
            r151 = r7
            r139 = r9
            r152 = r11
            r153 = r12
            r154 = r13
            r155 = r14
            r6 = r15
            r145 = r45
        L_0x0b77:
            r140 = r48
            r156 = r54
            r157 = r55
            r158 = r56
            r5 = r57
            r138 = r61
            r48 = r63
            r129 = r65
            r128 = r66
            r9 = r69
            r7 = r70
            r15 = r71
            r14 = r72
            r13 = r73
            r63 = r74
            r141 = r75
            r4 = r76
            r143 = r77
            r12 = r78
            r122 = r79
            r57 = r82
            r66 = r85
            r159 = r88
            r160 = r89
            r142 = r91
            r123 = r92
            r134 = r93
            r136 = r94
            r132 = r95
            r135 = r96
            r54 = r97
            r65 = r98
            r11 = r99
            r137 = r101
            r124 = r103
            r133 = r104
            r127 = r105
            r149 = r106
            r61 = r107
            r55 = r108
            r146 = r109
            r148 = r110
            r147 = r111
            r150 = r113
            r126 = r114
            r125 = r115
            r56 = r116
            r130 = r118
            r131 = r119
            r69 = r10
            r71 = r53
            r10 = r58
            r70 = r59
            r58 = r83
            r59 = r90
            r53 = r8
            r8 = r67
            r67 = r60
            r60 = r84
            goto L_0x0c80
        L_0x0bef:
            r120 = r6
            r5 = r45
            r6 = r5
            r9 = r6
            r10 = r9
            r48 = r10
            r54 = r48
            r55 = r54
            r56 = r55
            r57 = r56
            r58 = r57
            r59 = r58
            r60 = r59
            r61 = r60
            r63 = r61
            r64 = r63
            r67 = r64
            r68 = r67
            r69 = r68
            r70 = r69
            r71 = r70
            r112 = r71
            r124 = r112
            r127 = r124
            r131 = r127
            r142 = r131
            r145 = r142
            r146 = r145
            r150 = r146
            r151 = r150
            r152 = r151
            r153 = r152
            r154 = r153
            r155 = r154
            r156 = r155
            r157 = r156
            r158 = r157
            r159 = r158
            r160 = r159
            r4 = 0
            r7 = 0
            r8 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r53 = 0
            r62 = 0
            r65 = 0
            r66 = 0
            r81 = 0
            r86 = 0
            r100 = 0
            r102 = 0
            r117 = 0
            r122 = 0
            r123 = 0
            r125 = 0
            r126 = 0
            r128 = 0
            r129 = 0
            r130 = 0
            r132 = 0
            r133 = 0
            r134 = 0
            r135 = 0
            r136 = 0
            r137 = 0
            r138 = 0
            r139 = 0
            r140 = 0
            r141 = 0
            r143 = 0
            r144 = 0
            r147 = 0
            r148 = 0
            r149 = 0
        L_0x0c80:
            long r18 = r120 & r18
            r51 = 0
            int r72 = (r18 > r51 ? 1 : (r18 == r51 ? 0 : -1))
            r18 = r6
            if (r72 == 0) goto L_0x0cb7
            if (r0 == 0) goto L_0x0c91
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.deviceTimeAvailable
            r19 = r9
            goto L_0x0c95
        L_0x0c91:
            r19 = r9
            r6 = r48
        L_0x0c95:
            r9 = 0
            r1.updateLiveDataRegistration(r9, r6)
            if (r6 == 0) goto L_0x0ca3
            java.lang.Object r6 = r6.getValue()
            r64 = r6
            java.lang.Boolean r64 = (java.lang.Boolean) r64
        L_0x0ca3:
            boolean r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r64)
            long r72 = r2 & r38
            r51 = 0
            int r9 = (r72 > r51 ? 1 : (r72 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0cbd
            if (r6 == 0) goto L_0x0cb4
            long r2 = r2 | r30
            goto L_0x0cbd
        L_0x0cb4:
            long r2 = r2 | r32
            goto L_0x0cbd
        L_0x0cb7:
            r19 = r9
            r51 = 0
            r6 = r62
        L_0x0cbd:
            r30 = 288305142942400512(0x400440000000000, double:2.0863392627693407E-289)
            long r30 = r120 & r30
            int r9 = (r30 > r51 ? 1 : (r30 == r51 ? 0 : -1))
            if (r9 == 0) goto L_0x0ce4
            if (r0 == 0) goto L_0x0ccd
            androidx.databinding.ObservableBoolean r0 = r0.isShare
            goto L_0x0ccf
        L_0x0ccd:
            r0 = r112
        L_0x0ccf:
            r9 = 31
            r1.updateRegistration((int) r9, (androidx.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0cda
            boolean r117 = r0.get()
        L_0x0cda:
            long r30 = r2 & r34
            r32 = 0
            int r0 = (r30 > r32 ? 1 : (r30 == r32 ? 0 : -1))
            r0 = 1
            r9 = r117 ^ 1
            goto L_0x0ce8
        L_0x0ce4:
            r32 = 0
            r9 = r53
        L_0x0ce8:
            long r30 = r2 & r36
            int r0 = (r30 > r32 ? 1 : (r30 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x0d02
            if (r100 == 0) goto L_0x0cf1
            r6 = 1
        L_0x0cf1:
            if (r0 == 0) goto L_0x0cfc
            if (r6 == 0) goto L_0x0cf8
            r30 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
            goto L_0x0cfa
        L_0x0cf8:
            r30 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
        L_0x0cfa:
            long r2 = r2 | r30
        L_0x0cfc:
            if (r6 == 0) goto L_0x0cff
            goto L_0x0d02
        L_0x0cff:
            r0 = 8
            goto L_0x0d03
        L_0x0d02:
            r0 = 0
        L_0x0d03:
            r30 = 431644213248(0x6480000000, double:2.13260576992E-312)
            long r30 = r2 & r30
            r32 = 0
            int r6 = (r30 > r32 ? 1 : (r30 == r32 ? 0 : -1))
            if (r6 == 0) goto L_0x0d2a
            if (r86 == 0) goto L_0x0d15
            r30 = r9
            goto L_0x0d17
        L_0x0d15:
            r30 = 0
        L_0x0d17:
            if (r30 == 0) goto L_0x0d1c
            r30 = 0
            goto L_0x0d1e
        L_0x0d1c:
            r30 = 8
        L_0x0d1e:
            r31 = 414468538368(0x6080400000, double:2.047746660897E-312)
            r162 = r30
            r30 = r0
            r0 = r162
            goto L_0x0d32
        L_0x0d2a:
            r30 = r0
            r0 = 0
            r31 = 414468538368(0x6080400000, double:2.047746660897E-312)
        L_0x0d32:
            long r31 = r2 & r31
            r42 = 0
            int r33 = (r31 > r42 ? 1 : (r31 == r42 ? 0 : -1))
            if (r33 == 0) goto L_0x0d4b
            if (r102 == 0) goto L_0x0d3f
            r31 = r9
            goto L_0x0d41
        L_0x0d3f:
            r31 = 0
        L_0x0d41:
            if (r31 == 0) goto L_0x0d46
            r31 = 0
            goto L_0x0d48
        L_0x0d46:
            r31 = 8
        L_0x0d48:
            r161 = r31
            goto L_0x0d4d
        L_0x0d4b:
            r161 = 0
        L_0x0d4d:
            r31 = 414464344320(0x6080000100, double:2.047725939546E-312)
            long r31 = r2 & r31
            r42 = 0
            int r44 = (r31 > r42 ? 1 : (r31 == r42 ? 0 : -1))
            if (r44 == 0) goto L_0x0d6d
            if (r81 == 0) goto L_0x0d5f
            r31 = r9
            goto L_0x0d61
        L_0x0d5f:
            r31 = 0
        L_0x0d61:
            if (r31 == 0) goto L_0x0d66
            r80 = 0
            goto L_0x0d68
        L_0x0d66:
            r80 = 8
        L_0x0d68:
            r31 = r0
            r0 = r80
            goto L_0x0d70
        L_0x0d6d:
            r31 = r0
            r0 = 0
        L_0x0d70:
            r42 = 412317122560(0x6000040000, double:2.03711725449E-312)
            long r42 = r2 & r42
            r46 = 0
            int r32 = (r42 > r46 ? 1 : (r42 == r46 ? 0 : -1))
            if (r32 == 0) goto L_0x0d85
            r32 = r6
            android.widget.Button r6 = r1.btnConfirm
            r6.setClickable(r11)
            goto L_0x0d87
        L_0x0d85:
            r32 = r6
        L_0x0d87:
            long r40 = r2 & r40
            int r6 = (r40 > r46 ? 1 : (r40 == r46 ? 0 : -1))
            if (r6 == 0) goto L_0x0db0
            android.widget.Button r6 = r1.btnConfirm
            r6.setVisibility(r8)
            android.widget.LinearLayout r6 = r1.mboundView31
            r6.setVisibility(r13)
            android.widget.LinearLayout r6 = r1.mboundView9
            r6.setVisibility(r15)
            android.widget.ScrollView r6 = r1.scroll
            r6.setVisibility(r12)
            android.widget.TextView r6 = r1.tvTabController
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r6, r4)
            android.widget.TextView r4 = r1.tvTabPort
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r4, r7)
            android.widget.TextView r4 = r1.tvTabSystem
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r4, r14)
        L_0x0db0:
            long r6 = r2 & r16
            r11 = 0
            int r4 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
            if (r4 == 0) goto L_0x0e26
            android.widget.Button r4 = r1.btnConfirm
            r6 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r10, r6)
            android.widget.RelativeLayout r4 = r1.btnShare
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r6)
            android.widget.EditText r4 = r1.editName
            r5 = r19
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r4, r5)
            android.widget.EditText r4 = r1.editPortName
            r15 = r18
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r4, r15)
            android.widget.RelativeLayout r4 = r1.mboundView10
            r5 = r71
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r6)
            android.widget.RelativeLayout r4 = r1.mboundView11
            r5 = r70
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r6)
            android.widget.TextView r4 = r1.mboundView40
            r5 = r158
            r7 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r7)
            com.eternal.widget.guqiang.ProgressToolbar r4 = r1.toolBar
            r5 = r156
            com.eternal.widget.guqiang.ProgressToolbarAdapter.onBack(r4, r5)
            android.widget.RelativeLayout r4 = r1.tvDelete
            r10 = r69
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r10, r6)
            android.widget.RelativeLayout r4 = r1.tvDeviceTime
            r5 = r157
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r6)
            android.widget.RelativeLayout r4 = r1.tvReset
            r5 = r68
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r6)
            android.widget.TextView r4 = r1.tvUnitC
            r11 = r152
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r11, r7)
            android.widget.TextView r4 = r1.tvUnitF
            r5 = r67
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r7)
            android.view.View r4 = r1.vClickController
            r14 = r155
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r14, r6)
            android.view.View r4 = r1.vClickPort
            r13 = r154
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r13, r6)
            android.view.View r4 = r1.vClickSystem
            r12 = r153
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r12, r6)
        L_0x0e26:
            if (r44 == 0) goto L_0x0e2d
            android.widget.RelativeLayout r4 = r1.btnShare
            r4.setVisibility(r0)
        L_0x0e2d:
            long r4 = r2 & r24
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e41
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.clBuffer
            r4 = r66
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.tvBuffer
            r0.setVisibility(r4)
        L_0x0e41:
            long r4 = r2 & r22
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e53
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.clBufferHum
            r4 = r65
            r0.setVisibility(r4)
            android.view.View r0 = r1.vBuffer
            r0.setVisibility(r4)
        L_0x0e53:
            long r4 = r2 & r28
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e65
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.clBufferVpd
            r4 = r122
            r0.setVisibility(r4)
            android.view.View r0 = r1.mboundView58
            r0.setVisibility(r4)
        L_0x0e65:
            r4 = 412316868608(0x6000002000, double:2.0371159998E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e7b
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.clVpdLeafTemperature
            r4 = r123
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.mboundView29
            r0.setVisibility(r4)
        L_0x0e7b:
            r4 = 412325249024(0x6000800000, double:2.037157404557E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0e8c
            android.widget.EditText r0 = r1.editName
            r4 = r124
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0e8c:
            long r4 = r2 & r34
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0eaa
            android.widget.EditText r0 = r1.editName
            r4 = r125
            r0.setTextColor(r4)
            android.widget.EditText r0 = r1.editName
            r0.setEnabled(r9)
            android.widget.EditText r0 = r1.editPortName
            r4 = r126
            r0.setTextColor(r4)
            android.widget.EditText r0 = r1.editPortName
            r0.setEnabled(r9)
        L_0x0eaa:
            r4 = 274877906944(0x4000000000, double:1.358077306218E-312)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0ed0
            android.widget.EditText r0 = r1.editName
            r4 = r45
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r4 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r4
            r5 = r45
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r5 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r5
            r6 = r45
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r6 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r6
            androidx.databinding.InverseBindingListener r7 = r1.editNameandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r4, r5, r6, r7)
            android.widget.EditText r0 = r1.editPortName
            androidx.databinding.InverseBindingListener r7 = r1.editPortNameandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r4, r5, r6, r7)
        L_0x0ed0:
            r4 = 412350414848(0x6002000000, double:2.03728174025E-312)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0ee3
            android.widget.EditText r0 = r1.editPortName
            r4 = r127
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0ee3:
            r4 = 412316860418(0x6000000002, double:2.037115959337E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f00
            android.widget.EditText r0 = r1.editPortName
            r4 = r128
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.mboundView33
            r5 = r129
            r0.setVisibility(r5)
            android.widget.TextView r0 = r1.tvPortNameSuggest
            r0.setVisibility(r4)
        L_0x0f00:
            r4 = 420906795008(0x6200000000, double:2.079555875146E-312)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f1a
            com.eternal.widget.ExpandableLayout r0 = r1.elType
            r4 = r130
            com.eternal.widget.ExpandableLayout.setExpanded((com.eternal.widget.ExpandableLayout) r0, (boolean) r4)
            android.widget.TextView r0 = r1.mboundView40
            r4 = r131
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0f1a:
            r4 = 412316925952(0x6000010000, double:2.037116283118E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f2b
            android.widget.ImageView r0 = r1.ivType
            r4 = r132
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r4)
        L_0x0f2b:
            r4 = 412333637632(0x6001000000, double:2.037198849787E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f41
            android.widget.LinearLayout r0 = r1.llMaxMinLevel
            r4 = r133
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.mboundView60
            r0.setVisibility(r4)
        L_0x0f41:
            r4 = 412316876800(0x6000004000, double:2.037116040274E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f57
            android.widget.LinearLayout r0 = r1.llTransition
            r4 = r134
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.mboundView48
            r0.setVisibility(r4)
        L_0x0f57:
            r4 = 412316991488(0x6000020000, double:2.03711660691E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f68
            android.widget.RelativeLayout r0 = r1.mboundView10
            r4 = r135
            r0.setVisibility(r4)
        L_0x0f68:
            r4 = 412316893184(0x6000008000, double:2.03711612122E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f79
            android.widget.RelativeLayout r0 = r1.mboundView11
            r4 = r136
            r0.setVisibility(r4)
        L_0x0f79:
            r4 = 412318957568(0x6000200000, double:2.037126320634E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f8a
            android.view.View r0 = r1.mboundView12
            r4 = r137
            r0.setVisibility(r4)
        L_0x0f8a:
            long r4 = r2 & r38
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0f97
            android.view.View r0 = r1.mboundView15
            r4 = r138
            r0.setVisibility(r4)
        L_0x0f97:
            r4 = 412316860424(0x6000000008, double:2.037115959366E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0fad
            android.widget.TextView r0 = r1.mboundView16
            r4 = r63
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
            android.widget.TextView r0 = r1.mboundView26
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0fad:
            if (r32 == 0) goto L_0x0fbb
            android.widget.RelativeLayout r0 = r1.mboundView25
            r4 = r31
            r0.setVisibility(r4)
            android.widget.RelativeLayout r0 = r1.tvDeviceTime
            r0.setVisibility(r4)
        L_0x0fbb:
            long r4 = r2 & r20
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0fd9
            android.view.View r0 = r1.mboundView27
            r9 = r139
            r0.setVisibility(r9)
            android.view.View r0 = r1.mboundView50
            r0.setVisibility(r9)
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.rlCalibrationHum
            r0.setVisibility(r9)
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.rlTransitionHum
            r0.setVisibility(r9)
        L_0x0fd9:
            r4 = 412585295872(0x6010000000, double:2.038442206696E-312)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0fec
            android.widget.TextView r0 = r1.mboundView33
            r4 = r61
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0fec:
            r4 = 412316860480(0x6000000040, double:2.037115959643E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1002
            android.widget.TextView r0 = r1.mboundView36
            r4 = r140
            r0.setVisibility(r4)
            android.widget.RelativeLayout r0 = r1.mboundView37
            r0.setVisibility(r4)
        L_0x1002:
            r4 = 412316862464(0x6000000800, double:2.037115969445E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1013
            android.widget.TextView r0 = r1.mboundView39
            r4 = r60
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x1013:
            long r4 = r2 & r26
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1025
            android.view.View r0 = r1.mboundView52
            r4 = r141
            r0.setVisibility(r4)
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.rlTransitionVpd
            r0.setVisibility(r4)
        L_0x1025:
            r4 = 412316864512(0x6000001000, double:2.037115979564E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1059
            android.widget.RadioButton r0 = r1.rbAc
            r7 = r151
            androidx.databinding.adapters.TextViewBindingAdapter.setDrawableTop(r0, r7)
            android.widget.RadioButton r0 = r1.rbDehum
            r4 = r159
            androidx.databinding.adapters.TextViewBindingAdapter.setDrawableTop(r0, r4)
            android.widget.RadioButton r0 = r1.rbFan
            r4 = r142
            androidx.databinding.adapters.TextViewBindingAdapter.setDrawableTop(r0, r4)
            android.widget.RadioButton r0 = r1.rbHeater
            r4 = r160
            androidx.databinding.adapters.TextViewBindingAdapter.setDrawableTop(r0, r4)
            android.widget.RadioButton r0 = r1.rbHum
            r4 = r59
            androidx.databinding.adapters.TextViewBindingAdapter.setDrawableTop(r0, r4)
            android.widget.RadioButton r0 = r1.rbLight
            r4 = r150
            androidx.databinding.adapters.TextViewBindingAdapter.setDrawableTop(r0, r4)
        L_0x1059:
            r4 = 412316860448(0x6000000020, double:2.037115959485E-312)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x106c
            com.google.android.material.tabs.TabLayout r0 = r1.tbPort
            r4 = r143
            r0.setVisibility(r4)
        L_0x106c:
            r4 = 412316861440(0x6000000400, double:2.037115964386E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x107d
            com.eternal.widget.guqiang.ProgressToolbar r0 = r1.toolBar
            r4 = r58
            com.eternal.widget.guqiang.ProgressToolbarAdapter.setSubtitle(r0, r4)
        L_0x107d:
            r4 = 446676598784(0x6800000000, double:2.206875622604E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x108e
            com.eternal.widget.guqiang.ProgressToolbar r0 = r1.toolBar
            r4 = r144
            com.eternal.widget.guqiang.ProgressToolbarAdapter.setProgress(r0, r4)
        L_0x108e:
            r4 = 412316860928(0x6000000200, double:2.037115961856E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x109f
            android.widget.TextView r0 = r1.tvDeviceTimeZone
            r4 = r57
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x109f:
            r4 = 416611827712(0x6100000000, double:2.058335917236E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x10b0
            android.widget.TextView r0 = r1.tvNameSuggest
            r4 = r56
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x10b0:
            r4 = 481036337152(0x7000000000, double:2.37663528588E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x10c1
            android.widget.TextView r0 = r1.tvPortNameSuggest
            r5 = r145
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r5)
        L_0x10c1:
            if (r33 == 0) goto L_0x10ca
            android.widget.RelativeLayout r0 = r1.tvReset
            r4 = r161
            r0.setVisibility(r4)
        L_0x10ca:
            r4 = 412451078144(0x6008000000, double:2.03777908301E-312)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x10dd
            android.widget.TextView r0 = r1.tvTabController
            r4 = r146
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x10dd:
            r4 = 412853731328(0x6020000000, double:2.039768454065E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x10ee
            android.widget.TextView r0 = r1.tvTabPort
            r4 = r55
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x10ee:
            r4 = 413390602240(0x6040000000, double:2.042420948804E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1106
            android.widget.TextView r0 = r1.tvUnitC
            r4 = r147
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r4)
            android.widget.TextView r0 = r1.tvUnitF
            r4 = r148
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r4)
        L_0x1106:
            r4 = 412317384704(0x6000080000, double:2.037118549654E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1117
            android.widget.TextView r0 = r1.txtConnectTime
            r4 = r54
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x1117:
            r4 = 412383969280(0x6004000000, double:2.03744752117E-312)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1128
            android.widget.TextView r0 = r1.txtConnectTime
            r4 = r149
            r0.setVisibility(r4)
        L_0x1128:
            long r2 = r2 & r36
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x1135
            android.view.View r0 = r1.vDot
            r2 = r30
            r0.setVisibility(r2)
        L_0x1135:
            return
        L_0x1136:
            r0 = move-exception
            monitor-exit(r164)     // Catch:{ all -> 0x1136 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.settings.databinding.ActivitySettingsBindingImpl.executeBindings():void");
    }
}
