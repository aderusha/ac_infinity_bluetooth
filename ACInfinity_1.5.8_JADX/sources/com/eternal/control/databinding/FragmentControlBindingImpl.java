package com.eternal.control.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.control.C1669BR;
import com.eternal.control.C1760R;
import com.eternal.control.ControlModel;

public class FragmentControlBindingImpl extends FragmentControlBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private long mDirtyFlags_1;
    private final ConstraintLayout mboundView0;
    private final RelativeLayout mboundView19;
    private final RelativeLayout mboundView21;
    private final RelativeLayout mboundView23;
    private final TextView mboundView24;
    private final TextView mboundView28;
    private final ConstraintLayout mboundView30;
    private final ImageView mboundView44;
    private final View mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView7;
    private final TextView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1760R.C1763id.sc_content, 45);
        sparseIntArray.put(C1760R.C1763id.layout_th, 46);
        sparseIntArray.put(C1760R.C1763id.tv_tmp_title, 47);
        sparseIntArray.put(C1760R.C1763id.layout_tmp, 48);
        sparseIntArray.put(C1760R.C1763id.sb_high_tmp, 49);
        sparseIntArray.put(C1760R.C1763id.sb_low_tmp, 50);
        sparseIntArray.put(C1760R.C1763id.tv_auto_temp_high_desc, 51);
        sparseIntArray.put(C1760R.C1763id.tv_auto_temp_low_desc, 52);
        sparseIntArray.put(C1760R.C1763id.sb_high_hum, 53);
        sparseIntArray.put(C1760R.C1763id.sb_low_hum, 54);
        sparseIntArray.put(C1760R.C1763id.ll_vpd, 55);
        sparseIntArray.put(C1760R.C1763id.tv_vpd_title, 56);
        sparseIntArray.put(C1760R.C1763id.layout_vpd, 57);
        sparseIntArray.put(C1760R.C1763id.sb_high_vpd, 58);
        sparseIntArray.put(C1760R.C1763id.sb_low_vpd, 59);
        sparseIntArray.put(C1760R.C1763id.tv_vpd_high_desc, 60);
        sparseIntArray.put(C1760R.C1763id.tv_vpd_low_desc, 61);
        sparseIntArray.put(C1760R.C1763id.ll_fan, 62);
        sparseIntArray.put(C1760R.C1763id.iv_fan, 63);
        sparseIntArray.put(C1760R.C1763id.fpb, 64);
        sparseIntArray.put(C1760R.C1763id.layout_cs, 65);
        sparseIntArray.put(C1760R.C1763id.sb_first, 66);
        sparseIntArray.put(C1760R.C1763id.line_sched, 67);
        sparseIntArray.put(C1760R.C1763id.sb_last, 68);
        sparseIntArray.put(C1760R.C1763id.layout_time, 69);
        sparseIntArray.put(C1760R.C1763id.sb_time, 70);
        sparseIntArray.put(C1760R.C1763id.layout_cycle, 71);
        sparseIntArray.put(C1760R.C1763id.sb_cycle_on, 72);
        sparseIntArray.put(C1760R.C1763id.line_cycle, 73);
        sparseIntArray.put(C1760R.C1763id.sb_cycle_off, 74);
        sparseIntArray.put(C1760R.C1763id.sv_model_type, 75);
        sparseIntArray.put(C1760R.C1763id.layout_model_type, 76);
        sparseIntArray.put(C1760R.C1763id.ll_tfp, 77);
        sparseIntArray.put(C1760R.C1763id.tv_hum_unit, 78);
        sparseIntArray.put(C1760R.C1763id.tv_vpd_unit, 79);
        sparseIntArray.put(C1760R.C1763id.tv_power, 80);
        sparseIntArray.put(C1760R.C1763id.gq_dial, 81);
        sparseIntArray.put(C1760R.C1763id.v_buffer_line, 82);
        sparseIntArray.put(C1760R.C1763id.max_min_line, 83);
        sparseIntArray.put(C1760R.C1763id.v_touch_outside, 84);
        sparseIntArray.put(C1760R.C1763id.space, 85);
        sparseIntArray.put(C1760R.C1763id.tb_port, 86);
    }

    public FragmentControlBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 87, sIncludes, sViewsWithIds));
    }

    private FragmentControlBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 32, objArr[33], objArr[64], objArr[81], objArr[63], objArr[36], objArr[39], objArr[26], objArr[65], objArr[71], objArr[2], objArr[76], objArr[46], objArr[69], objArr[48], objArr[57], objArr[73], objArr[67], objArr[62], objArr[77], objArr[55], objArr[83], objArr[27], objArr[74], objArr[72], objArr[66], objArr[53], objArr[49], objArr[58], objArr[68], objArr[54], objArr[50], objArr[59], objArr[70], objArr[45], objArr[43], objArr[85], objArr[75], objArr[86], objArr[3], objArr[4], objArr[51], objArr[52], objArr[32], objArr[31], objArr[20], objArr[1], objArr[78], objArr[35], objArr[34], objArr[38], objArr[37], objArr[80], objArr[18], objArr[17], objArr[47], objArr[22], objArr[60], objArr[61], objArr[56], objArr[79], objArr[25], objArr[41], objArr[29], objArr[11], objArr[12], objArr[9], objArr[10], objArr[15], objArr[14], objArr[13], objArr[16], objArr[82], objArr[42], objArr[40], objArr[84]);
        this.mDirtyFlags = -1;
        this.mDirtyFlags_1 = -1;
        this.ctMaxMin.setTag((Object) null);
        this.ivMaxDot.setTag((Object) null);
        this.ivMinDot.setTag((Object) null);
        this.ivWindSpeed.setTag((Object) null);
        this.layoutHum.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[19];
        this.mboundView19 = relativeLayout;
        relativeLayout.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[21];
        this.mboundView21 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        RelativeLayout relativeLayout3 = objArr[23];
        this.mboundView23 = relativeLayout3;
        relativeLayout3.setTag((Object) null);
        TextView textView = objArr[24];
        this.mboundView24 = textView;
        textView.setTag((Object) null);
        TextView textView2 = objArr[28];
        this.mboundView28 = textView2;
        textView2.setTag((Object) null);
        ConstraintLayout constraintLayout2 = objArr[30];
        this.mboundView30 = constraintLayout2;
        constraintLayout2.setTag((Object) null);
        ImageView imageView = objArr[44];
        this.mboundView44 = imageView;
        imageView.setTag((Object) null);
        View view2 = objArr[5];
        this.mboundView5 = view2;
        view2.setTag((Object) null);
        TextView textView3 = objArr[6];
        this.mboundView6 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = objArr[7];
        this.mboundView7 = textView4;
        textView4.setTag((Object) null);
        TextView textView5 = objArr[8];
        this.mboundView8 = textView5;
        textView5.setTag((Object) null);
        this.rlPower.setTag((Object) null);
        this.scOverlay.setTag((Object) null);
        this.tvAutoHumHighDesc.setTag((Object) null);
        this.tvAutoHumLowDesc.setTag((Object) null);
        this.tvBufferHum.setTag((Object) null);
        this.tvBufferTemp.setTag((Object) null);
        this.tvHum.setTag((Object) null);
        this.tvHumTitle.setTag((Object) null);
        this.tvMaxFan.setTag((Object) null);
        this.tvMaxFanType.setTag((Object) null);
        this.tvMinFan.setTag((Object) null);
        this.tvMinFanType.setTag((Object) null);
        this.tvTempUnit.setTag((Object) null);
        this.tvTmp.setTag((Object) null);
        this.tvVpd.setTag((Object) null);
        this.tvWindSpeed.setTag((Object) null);
        this.txtAutomationActivated.setTag((Object) null);
        this.txtMode.setTag((Object) null);
        this.txtModeAuto.setTag((Object) null);
        this.txtModeCycle.setTag((Object) null);
        this.txtModeOff.setTag((Object) null);
        this.txtModeOn.setTag((Object) null);
        this.txtModeSched.setTag((Object) null);
        this.txtModeTimeToOff.setTag((Object) null);
        this.txtModeTimeToOn.setTag((Object) null);
        this.txtModeVpd.setTag((Object) null);
        this.vMask.setTag((Object) null);
        this.vMaskAutomation.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8589934592L;
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
        if (C1669BR.model != i) {
            return false;
        }
        setModel((ControlModel) obj);
        return true;
    }

    public void setModel(ControlModel controlModel) {
        this.mModel = controlModel;
        synchronized (this) {
            this.mDirtyFlags |= 4294967296L;
        }
        notifyPropertyChanged(C1669BR.model);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeModelSchedModeVisible((MutableLiveData) obj, i2);
            case 1:
                return onChangeModelVpdModeVisible((MutableLiveData) obj, i2);
            case 2:
                return onChangeModelModel((MutableLiveData) obj, i2);
            case 3:
                return onChangeModelFanSize((ObservableField) obj, i2);
            case 4:
                return onChangeModelBufferVisible((MutableLiveData) obj, i2);
            case 5:
                return onChangeModelTmpFlag((ObservableField) obj, i2);
            case 6:
                return onChangeModelBufferTempSize((ObservableField) obj, i2);
            case 7:
                return onChangeModelHumVisibility((ObservableBoolean) obj, i2);
            case 8:
                return onChangeModelOverlayRes((MutableLiveData) obj, i2);
            case 9:
                return onChangeModelFanType((ObservableInt) obj, i2);
            case 10:
                return onChangeModelPowerVisibility((ObservableBoolean) obj, i2);
            case 11:
                return onChangeModelCycleModel((MutableLiveData) obj, i2);
            case 12:
                return onChangeModelMaxFanType((ObservableInt) obj, i2);
            case 13:
                return onChangeModelAutomationActivated((MutableLiveData) obj, i2);
            case 14:
                return onChangeModelTmpSize((ObservableField) obj, i2);
            case 15:
                return onChangeModelCurrentResidueOnVisible((MutableLiveData) obj, i2);
            case 16:
                return onChangeModelVpdVisibility((ObservableBoolean) obj, i2);
            case 17:
                return onChangeModelBufferHumSize((ObservableField) obj, i2);
            case 18:
                return onChangeModelFanVisibility((ObservableBoolean) obj, i2);
            case 19:
                return onChangeModelOnFanSize((ObservableField) obj, i2);
            case 20:
                return onChangeModelOffFanSize((ObservableField) obj, i2);
            case 21:
                return onChangeModelPowerOff((ObservableBoolean) obj, i2);
            case 22:
                return onChangeModelVpdSize((ObservableField) obj, i2);
            case 23:
                return onChangeModelHintText((ObservableField) obj, i2);
            case 24:
                return onChangeModelConnected((MutableLiveData) obj, i2);
            case 25:
                return onChangeModelAutomationVisible((MutableLiveData) obj, i2);
            case 26:
                return onChangeModelHumModeVisible((MutableLiveData) obj, i2);
            case 27:
                return onChangeModelMinFanType((ObservableInt) obj, i2);
            case 28:
                return onChangeModelFanTypeTitle((ObservableField) obj, i2);
            case 29:
                return onChangeModelPerSize((ObservableField) obj, i2);
            case 30:
                return onChangeModelOverlayVisible((MutableLiveData) obj, i2);
            case 31:
                return onChangeModelResidueFlag((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeModelSchedModeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelVpdModeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModelModel(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModelFanSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeModelBufferVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeModelTmpFlag(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeModelBufferTempSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeModelHumVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeModelOverlayRes(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeModelFanType(ObservableInt observableInt, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeModelPowerVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeModelCycleModel(MutableLiveData<Byte> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeModelMaxFanType(ObservableInt observableInt, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeModelAutomationActivated(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeModelTmpSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeModelCurrentResidueOnVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeModelVpdVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeModelBufferHumSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeModelFanVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeModelOnFanSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeModelOffFanSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    private boolean onChangeModelPowerOff(ObservableBoolean observableBoolean, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        }
        return true;
    }

    private boolean onChangeModelVpdSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4194304;
        }
        return true;
    }

    private boolean onChangeModelHintText(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8388608;
        }
        return true;
    }

    private boolean onChangeModelConnected(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16777216;
        }
        return true;
    }

    private boolean onChangeModelAutomationVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 33554432;
        }
        return true;
    }

    private boolean onChangeModelHumModeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        return true;
    }

    private boolean onChangeModelMinFanType(ObservableInt observableInt, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        return true;
    }

    private boolean onChangeModelFanTypeTitle(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        return true;
    }

    private boolean onChangeModelPerSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 536870912;
        }
        return true;
    }

    private boolean onChangeModelOverlayVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1073741824;
        }
        return true;
    }

    private boolean onChangeModelResidueFlag(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2147483648L;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0176 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x018c  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01c3  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01dd  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01fc  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0236  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x02a6  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x02c1  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x02ed  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0310  */
    /* JADX WARNING: Removed duplicated region for block: B:228:0x031f  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x033e  */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x036c  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x0377  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x039e  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x03b6  */
    /* JADX WARNING: Removed duplicated region for block: B:278:0x03c8  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x03f6  */
    /* JADX WARNING: Removed duplicated region for block: B:296:0x0405  */
    /* JADX WARNING: Removed duplicated region for block: B:305:0x0427  */
    /* JADX WARNING: Removed duplicated region for block: B:311:0x043f  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0451  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x048b  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x049b  */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x04bd  */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x04d5  */
    /* JADX WARNING: Removed duplicated region for block: B:353:0x04e7  */
    /* JADX WARNING: Removed duplicated region for block: B:368:0x051d  */
    /* JADX WARNING: Removed duplicated region for block: B:372:0x052c  */
    /* JADX WARNING: Removed duplicated region for block: B:390:0x0561  */
    /* JADX WARNING: Removed duplicated region for block: B:400:0x0584  */
    /* JADX WARNING: Removed duplicated region for block: B:404:0x0593  */
    /* JADX WARNING: Removed duplicated region for block: B:413:0x05b3  */
    /* JADX WARNING: Removed duplicated region for block: B:419:0x05cb  */
    /* JADX WARNING: Removed duplicated region for block: B:423:0x05db  */
    /* JADX WARNING: Removed duplicated region for block: B:429:0x05f3  */
    /* JADX WARNING: Removed duplicated region for block: B:433:0x0605  */
    /* JADX WARNING: Removed duplicated region for block: B:448:0x0639  */
    /* JADX WARNING: Removed duplicated region for block: B:452:0x0644  */
    /* JADX WARNING: Removed duplicated region for block: B:492:0x0733  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0157  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r111 = this;
            r1 = r111
            monitor-enter(r111)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0a85 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0a85 }
            r1.mDirtyFlags_1 = r4     // Catch:{ all -> 0x0a85 }
            monitor-exit(r111)     // Catch:{ all -> 0x0a85 }
            com.eternal.control.ControlModel r0 = r1.mModel
            r6 = 12885033024(0x300020040, double:6.3660521627E-314)
            long r6 = r6 & r2
            r8 = 0
            int r9 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x001c
            int r6 = com.eternal.control.C1760R.font.avenir_book
            goto L_0x001d
        L_0x001c:
            r6 = 0
        L_0x001d:
            r9 = 17179869183(0x3ffffffff, double:8.4879831634E-314)
            long r9 = r9 & r2
            r11 = 12884901952(0x300000040, double:6.3659874045E-314)
            r15 = 12884901920(0x300000020, double:6.3659873887E-314)
            r17 = 12884967424(0x300010000, double:6.366019752E-314)
            r19 = 12884901904(0x300000010, double:6.365987381E-314)
            r21 = 12884902016(0x300000080, double:6.365987436E-314)
            r23 = 12884901896(0x300000008, double:6.365987377E-314)
            r25 = 12952010752(0x304000000, double:6.399143557E-314)
            r27 = 12884901892(0x300000004, double:6.365987375E-314)
            r29 = 12884902400(0x300000200, double:6.365987626E-314)
            r31 = 12884901890(0x300000002, double:6.365987374E-314)
            r33 = 12884901889(0x300000001, double:6.3659873734E-314)
            r35 = 12884934656(0x300008000, double:6.3660035624E-314)
            r37 = 15032385536(0x380000000, double:7.4269852684E-314)
            int r39 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r39 == 0) goto L_0x078d
            long r9 = r2 & r33
            int r40 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r40 == 0) goto L_0x0096
            if (r0 == 0) goto L_0x0073
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.schedModeVisible
            goto L_0x0074
        L_0x0073:
            r9 = 0
        L_0x0074:
            r1.updateLiveDataRegistration(r8, r9)
            if (r9 == 0) goto L_0x0080
            java.lang.Object r9 = r9.getValue()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            goto L_0x0081
        L_0x0080:
            r9 = 0
        L_0x0081:
            boolean r9 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r9)
            if (r40 == 0) goto L_0x0090
            if (r9 == 0) goto L_0x008c
            r40 = -9223372036854775808
            goto L_0x008e
        L_0x008c:
            r40 = 4611686018427387904(0x4000000000000000, double:2.0)
        L_0x008e:
            long r2 = r2 | r40
        L_0x0090:
            if (r9 == 0) goto L_0x0093
            goto L_0x0096
        L_0x0093:
            r9 = 8
            goto L_0x0097
        L_0x0096:
            r9 = 0
        L_0x0097:
            long r40 = r2 & r31
            r10 = 1
            int r42 = (r40 > r4 ? 1 : (r40 == r4 ? 0 : -1))
            if (r42 == 0) goto L_0x00cc
            if (r0 == 0) goto L_0x00a3
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.vpdModeVisible
            goto L_0x00a4
        L_0x00a3:
            r8 = 0
        L_0x00a4:
            r1.updateLiveDataRegistration(r10, r8)
            if (r8 == 0) goto L_0x00b0
            java.lang.Object r8 = r8.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x00b1
        L_0x00b0:
            r8 = 0
        L_0x00b1:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r42 == 0) goto L_0x00c6
            if (r8 == 0) goto L_0x00bf
            r41 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
            goto L_0x00c4
        L_0x00bf:
            r41 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
        L_0x00c4:
            long r2 = r2 | r41
        L_0x00c6:
            if (r8 == 0) goto L_0x00c9
            goto L_0x00cc
        L_0x00c9:
            r8 = 8
            goto L_0x00cd
        L_0x00cc:
            r8 = 0
        L_0x00cd:
            long r41 = r2 & r27
            r13 = 2
            int r14 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x00e6
            if (r0 == 0) goto L_0x00d9
            androidx.lifecycle.MutableLiveData<java.lang.String> r14 = r0.model
            goto L_0x00da
        L_0x00d9:
            r14 = 0
        L_0x00da:
            r1.updateLiveDataRegistration(r13, r14)
            if (r14 == 0) goto L_0x00e6
            java.lang.Object r14 = r14.getValue()
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x00e7
        L_0x00e6:
            r14 = 0
        L_0x00e7:
            long r41 = r2 & r23
            r10 = 3
            int r46 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r46 == 0) goto L_0x0100
            if (r0 == 0) goto L_0x00f3
            androidx.databinding.ObservableField<java.lang.String> r13 = r0.fanSize
            goto L_0x00f4
        L_0x00f3:
            r13 = 0
        L_0x00f4:
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r13)
            if (r13 == 0) goto L_0x0100
            java.lang.Object r13 = r13.get()
            java.lang.String r13 = (java.lang.String) r13
            goto L_0x0101
        L_0x0100:
            r13 = 0
        L_0x0101:
            long r46 = r2 & r19
            r10 = 4
            int r48 = (r46 > r4 ? 1 : (r46 == r4 ? 0 : -1))
            if (r48 == 0) goto L_0x0136
            if (r0 == 0) goto L_0x010d
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r7 = r0.bufferVisible
            goto L_0x010e
        L_0x010d:
            r7 = 0
        L_0x010e:
            r1.updateLiveDataRegistration(r10, r7)
            if (r7 == 0) goto L_0x011a
            java.lang.Object r7 = r7.getValue()
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            goto L_0x011b
        L_0x011a:
            r7 = 0
        L_0x011b:
            boolean r7 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r7)
            if (r48 == 0) goto L_0x0130
            if (r7 == 0) goto L_0x0129
            r47 = 8796093022208(0x80000000000, double:4.345847379897E-311)
            goto L_0x012e
        L_0x0129:
            r47 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
        L_0x012e:
            long r2 = r2 | r47
        L_0x0130:
            if (r7 == 0) goto L_0x0133
            goto L_0x0136
        L_0x0133:
            r7 = 8
            goto L_0x0137
        L_0x0136:
            r7 = 0
        L_0x0137:
            long r47 = r2 & r15
            int r49 = (r47 > r4 ? 1 : (r47 == r4 ? 0 : -1))
            if (r49 == 0) goto L_0x0150
            if (r0 == 0) goto L_0x0142
            androidx.databinding.ObservableField<java.lang.String> r15 = r0.tmpFlag
            goto L_0x0143
        L_0x0142:
            r15 = 0
        L_0x0143:
            r10 = 5
            r1.updateRegistration((int) r10, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x0150
            java.lang.Object r10 = r15.get()
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x0151
        L_0x0150:
            r10 = 0
        L_0x0151:
            long r49 = r2 & r11
            int r15 = (r49 > r4 ? 1 : (r49 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x016a
            if (r0 == 0) goto L_0x015c
            androidx.databinding.ObservableField<java.lang.String> r15 = r0.bufferTempSize
            goto L_0x015d
        L_0x015c:
            r15 = 0
        L_0x015d:
            r11 = 6
            r1.updateRegistration((int) r11, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x016a
            java.lang.Object r11 = r15.get()
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x016b
        L_0x016a:
            r11 = 0
        L_0x016b:
            r51 = 12884901888(0x300000000, double:6.365987373E-314)
            long r51 = r2 & r51
            int r12 = (r51 > r4 ? 1 : (r51 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x017f
            if (r0 == 0) goto L_0x017f
            android.view.View$OnClickListener r12 = r0.onReModel
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onCloseOverlay
            android.view.View$OnClickListener r4 = r0.onModel
            goto L_0x0182
        L_0x017f:
            r4 = 0
            r12 = 0
            r15 = 0
        L_0x0182:
            long r53 = r2 & r21
            r51 = 0
            int r5 = (r53 > r51 ? 1 : (r53 == r51 ? 0 : -1))
            r53 = r4
            if (r5 == 0) goto L_0x01b3
            if (r0 == 0) goto L_0x0193
            androidx.databinding.ObservableBoolean r4 = r0.humVisibility
            r54 = r7
            goto L_0x0196
        L_0x0193:
            r54 = r7
            r4 = 0
        L_0x0196:
            r7 = 7
            r1.updateRegistration((int) r7, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x01a1
            boolean r4 = r4.get()
            goto L_0x01a2
        L_0x01a1:
            r4 = 0
        L_0x01a2:
            if (r5 == 0) goto L_0x01ad
            if (r4 == 0) goto L_0x01a9
            r55 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            goto L_0x01ab
        L_0x01a9:
            r55 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
        L_0x01ab:
            long r2 = r2 | r55
        L_0x01ad:
            if (r4 == 0) goto L_0x01b0
            goto L_0x01b5
        L_0x01b0:
            r4 = 8
            goto L_0x01b6
        L_0x01b3:
            r54 = r7
        L_0x01b5:
            r4 = 0
        L_0x01b6:
            r55 = 12884902144(0x300000100, double:6.3659874994E-314)
            long r55 = r2 & r55
            r51 = 0
            int r5 = (r55 > r51 ? 1 : (r55 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x01dd
            if (r0 == 0) goto L_0x01c8
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r5 = r0.overlayRes
            goto L_0x01c9
        L_0x01c8:
            r5 = 0
        L_0x01c9:
            r7 = 8
            r1.updateLiveDataRegistration(r7, r5)
            if (r5 == 0) goto L_0x01d7
            java.lang.Object r5 = r5.getValue()
            java.lang.Integer r5 = (java.lang.Integer) r5
            goto L_0x01d8
        L_0x01d7:
            r5 = 0
        L_0x01d8:
            int r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r5)
            goto L_0x01de
        L_0x01dd:
            r5 = 0
        L_0x01de:
            long r55 = r2 & r29
            r51 = 0
            int r7 = (r55 > r51 ? 1 : (r55 == r51 ? 0 : -1))
            if (r7 == 0) goto L_0x01fc
            if (r0 == 0) goto L_0x01ed
            androidx.databinding.ObservableInt r7 = r0.fanType
            r55 = r4
            goto L_0x01f0
        L_0x01ed:
            r55 = r4
            r7 = 0
        L_0x01f0:
            r4 = 9
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r7)
            if (r7 == 0) goto L_0x01fe
            int r4 = r7.get()
            goto L_0x01ff
        L_0x01fc:
            r55 = r4
        L_0x01fe:
            r4 = 0
        L_0x01ff:
            r56 = 12884902912(0x300000400, double:6.365987879E-314)
            long r56 = r2 & r56
            r51 = 0
            int r7 = (r56 > r51 ? 1 : (r56 == r51 ? 0 : -1))
            r56 = r4
            if (r7 == 0) goto L_0x0236
            if (r0 == 0) goto L_0x0215
            androidx.databinding.ObservableBoolean r4 = r0.powerVisibility
            r57 = r5
            goto L_0x0218
        L_0x0215:
            r57 = r5
            r4 = 0
        L_0x0218:
            r5 = 10
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0224
            boolean r4 = r4.get()
            goto L_0x0225
        L_0x0224:
            r4 = 0
        L_0x0225:
            if (r7 == 0) goto L_0x0230
            if (r4 == 0) goto L_0x022c
            r58 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
            goto L_0x022e
        L_0x022c:
            r58 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
        L_0x022e:
            long r2 = r2 | r58
        L_0x0230:
            if (r4 == 0) goto L_0x0233
            goto L_0x0238
        L_0x0233:
            r7 = 8
            goto L_0x0239
        L_0x0236:
            r57 = r5
        L_0x0238:
            r7 = 0
        L_0x0239:
            r4 = 12884903936(0x300000800, double:6.3659883847E-314)
            long r4 = r4 & r2
            r51 = 0
            int r58 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r58 == 0) goto L_0x02a6
            if (r0 == 0) goto L_0x024a
            androidx.lifecycle.MutableLiveData<java.lang.Byte> r4 = r0.cycleModel
            goto L_0x024b
        L_0x024a:
            r4 = 0
        L_0x024b:
            r5 = 11
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0259
            java.lang.Object r4 = r4.getValue()
            java.lang.Byte r4 = (java.lang.Byte) r4
            goto L_0x025a
        L_0x0259:
            r4 = 0
        L_0x025a:
            byte r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Byte) r4)
            r5 = 6
            if (r4 != r5) goto L_0x0264
            r58 = 1
            goto L_0x0266
        L_0x0264:
            r58 = 0
        L_0x0266:
            r5 = 5
            if (r4 != r5) goto L_0x026d
            r5 = 2
            r59 = 1
            goto L_0x0270
        L_0x026d:
            r5 = 2
            r59 = 0
        L_0x0270:
            if (r4 != r5) goto L_0x0275
            r60 = 1
            goto L_0x0277
        L_0x0275:
            r60 = 0
        L_0x0277:
            r5 = 7
            if (r4 != r5) goto L_0x027e
            r5 = 1
            r61 = 1
            goto L_0x0281
        L_0x027e:
            r5 = 1
            r61 = 0
        L_0x0281:
            if (r4 != r5) goto L_0x0287
            r5 = 3
            r42 = 1
            goto L_0x028a
        L_0x0287:
            r5 = 3
            r42 = 0
        L_0x028a:
            if (r4 != r5) goto L_0x0291
            r5 = 8
            r46 = 1
            goto L_0x0295
        L_0x0291:
            r5 = 8
            r46 = 0
        L_0x0295:
            if (r4 != r5) goto L_0x029b
            r5 = 4
            r16 = 1
            goto L_0x029e
        L_0x029b:
            r5 = 4
            r16 = 0
        L_0x029e:
            if (r4 != r5) goto L_0x02a2
            r4 = 1
            goto L_0x02a3
        L_0x02a2:
            r4 = 0
        L_0x02a3:
            r5 = r58
            goto L_0x02b4
        L_0x02a6:
            r4 = 0
            r5 = 0
            r16 = 0
            r42 = 0
            r46 = 0
            r59 = 0
            r60 = 0
            r61 = 0
        L_0x02b4:
            r63 = 12884905984(0x300001000, double:6.3659893966E-314)
            long r63 = r2 & r63
            r51 = 0
            int r58 = (r63 > r51 ? 1 : (r63 == r51 ? 0 : -1))
            if (r58 == 0) goto L_0x02d9
            r58 = r4
            if (r0 == 0) goto L_0x02ca
            androidx.databinding.ObservableInt r4 = r0.maxFanType
            r63 = r5
            goto L_0x02cd
        L_0x02ca:
            r63 = r5
            r4 = 0
        L_0x02cd:
            r5 = 12
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x02dd
            int r4 = r4.get()
            goto L_0x02de
        L_0x02d9:
            r58 = r4
            r63 = r5
        L_0x02dd:
            r4 = 0
        L_0x02de:
            r64 = 12884910080(0x300002000, double:6.3659914203E-314)
            long r64 = r2 & r64
            r51 = 0
            int r5 = (r64 > r51 ? 1 : (r64 == r51 ? 0 : -1))
            r64 = r4
            if (r5 == 0) goto L_0x0310
            if (r0 == 0) goto L_0x02f4
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.automationActivated
            r65 = r7
            goto L_0x02f7
        L_0x02f4:
            r65 = r7
            r4 = 0
        L_0x02f7:
            r7 = 13
            r1.updateLiveDataRegistration(r7, r4)
            if (r4 == 0) goto L_0x0305
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0306
        L_0x0305:
            r4 = 0
        L_0x0306:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r4 == 0) goto L_0x030d
            goto L_0x0312
        L_0x030d:
            r7 = 8
            goto L_0x0313
        L_0x0310:
            r65 = r7
        L_0x0312:
            r7 = 0
        L_0x0313:
            r4 = 12884918272(0x300004000, double:6.3659954677E-314)
            long r4 = r4 & r2
            r51 = 0
            int r66 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r66 == 0) goto L_0x0333
            if (r0 == 0) goto L_0x0324
            androidx.databinding.ObservableField<java.lang.String> r4 = r0.tmpSize
            goto L_0x0325
        L_0x0324:
            r4 = 0
        L_0x0325:
            r5 = 14
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0333
            java.lang.Object r4 = r4.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0334
        L_0x0333:
            r4 = 0
        L_0x0334:
            long r66 = r2 & r35
            r51 = 0
            int r5 = (r66 > r51 ? 1 : (r66 == r51 ? 0 : -1))
            r66 = r4
            if (r5 == 0) goto L_0x036c
            if (r0 == 0) goto L_0x0345
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.currentResidueOnVisible
            r67 = r7
            goto L_0x0348
        L_0x0345:
            r67 = r7
            r4 = 0
        L_0x0348:
            r7 = 15
            r1.updateLiveDataRegistration(r7, r4)
            if (r4 == 0) goto L_0x0356
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0357
        L_0x0356:
            r4 = 0
        L_0x0357:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x0366
            if (r4 == 0) goto L_0x0362
            r68 = 2305843009213693952(0x2000000000000000, double:1.4916681462400413E-154)
            goto L_0x0364
        L_0x0362:
            r68 = 1152921504606846976(0x1000000000000000, double:1.2882297539194267E-231)
        L_0x0364:
            long r2 = r2 | r68
        L_0x0366:
            if (r4 == 0) goto L_0x0369
            goto L_0x036e
        L_0x0369:
            r7 = 8
            goto L_0x036f
        L_0x036c:
            r67 = r7
        L_0x036e:
            r7 = 0
        L_0x036f:
            long r4 = r2 & r17
            r51 = 0
            int r68 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r68 == 0) goto L_0x0390
            if (r0 == 0) goto L_0x037c
            androidx.databinding.ObservableBoolean r4 = r0.vpdVisibility
            goto L_0x037d
        L_0x037c:
            r4 = 0
        L_0x037d:
            r5 = 16
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0389
            boolean r4 = r4.get()
            goto L_0x038a
        L_0x0389:
            r4 = 0
        L_0x038a:
            if (r4 == 0) goto L_0x038d
            goto L_0x0390
        L_0x038d:
            r4 = 8
            goto L_0x0391
        L_0x0390:
            r4 = 0
        L_0x0391:
            r68 = 12885032960(0x300020000, double:6.366052131E-314)
            long r68 = r2 & r68
            r51 = 0
            int r5 = (r68 > r51 ? 1 : (r68 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x03b6
            if (r0 == 0) goto L_0x03a5
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.bufferHumSize
            r68 = r4
            goto L_0x03a8
        L_0x03a5:
            r68 = r4
            r5 = 0
        L_0x03a8:
            r4 = 17
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x03b8
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x03b9
        L_0x03b6:
            r68 = r4
        L_0x03b8:
            r4 = 0
        L_0x03b9:
            r43 = 12885164032(0x300040000, double:6.366116889E-314)
            long r69 = r2 & r43
            r51 = 0
            int r5 = (r69 > r51 ? 1 : (r69 == r51 ? 0 : -1))
            r69 = r4
            if (r5 == 0) goto L_0x03f6
            if (r0 == 0) goto L_0x03cf
            androidx.databinding.ObservableBoolean r4 = r0.fanVisibility
            r70 = r7
            goto L_0x03d2
        L_0x03cf:
            r70 = r7
            r4 = 0
        L_0x03d2:
            r7 = 18
            r1.updateRegistration((int) r7, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x03de
            boolean r4 = r4.get()
            goto L_0x03df
        L_0x03de:
            r4 = 0
        L_0x03df:
            if (r5 == 0) goto L_0x03f0
            if (r4 == 0) goto L_0x03e9
            r71 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
            goto L_0x03ee
        L_0x03e9:
            r71 = 1099511627776(0x10000000000, double:5.43230922487E-312)
        L_0x03ee:
            long r2 = r2 | r71
        L_0x03f0:
            if (r4 == 0) goto L_0x03f3
            goto L_0x03f8
        L_0x03f3:
            r7 = 8
            goto L_0x03f9
        L_0x03f6:
            r70 = r7
        L_0x03f8:
            r7 = 0
        L_0x03f9:
            r4 = 12885426176(0x300080000, double:6.3662464056E-314)
            long r4 = r4 & r2
            r51 = 0
            int r71 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r71 == 0) goto L_0x0419
            if (r0 == 0) goto L_0x040a
            androidx.databinding.ObservableField<java.lang.String> r4 = r0.onFanSize
            goto L_0x040b
        L_0x040a:
            r4 = 0
        L_0x040b:
            r5 = 19
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0419
            java.lang.Object r4 = r4.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x041a
        L_0x0419:
            r4 = 0
        L_0x041a:
            r71 = 12885950464(0x300100000, double:6.3665054383E-314)
            long r71 = r2 & r71
            r51 = 0
            int r5 = (r71 > r51 ? 1 : (r71 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x043f
            if (r0 == 0) goto L_0x042e
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.offFanSize
            r71 = r4
            goto L_0x0431
        L_0x042e:
            r71 = r4
            r5 = 0
        L_0x0431:
            r4 = 20
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x0441
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0442
        L_0x043f:
            r71 = r4
        L_0x0441:
            r4 = 0
        L_0x0442:
            r72 = 12886999040(0x300200000, double:6.3670235037E-314)
            long r72 = r2 & r72
            r51 = 0
            int r5 = (r72 > r51 ? 1 : (r72 == r51 ? 0 : -1))
            r72 = r4
            if (r5 == 0) goto L_0x048b
            if (r0 == 0) goto L_0x0458
            androidx.databinding.ObservableBoolean r4 = r0.powerOff
            r73 = r7
            goto L_0x045b
        L_0x0458:
            r73 = r7
            r4 = 0
        L_0x045b:
            r7 = 21
            r1.updateRegistration((int) r7, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0467
            boolean r4 = r4.get()
            goto L_0x0468
        L_0x0467:
            r4 = 0
        L_0x0468:
            if (r5 == 0) goto L_0x0473
            if (r4 == 0) goto L_0x046f
            r74 = 144115188075855872(0x200000000000000, double:4.7783097267364807E-299)
            goto L_0x0471
        L_0x046f:
            r74 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
        L_0x0471:
            long r2 = r2 | r74
        L_0x0473:
            if (r4 == 0) goto L_0x047e
            android.widget.TextView r4 = r1.mboundView28
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.eternal.control.C1760R.string.power_off
            goto L_0x0486
        L_0x047e:
            android.widget.TextView r4 = r1.mboundView28
            android.content.res.Resources r4 = r4.getResources()
            int r5 = com.eternal.control.C1760R.string.power_on
        L_0x0486:
            java.lang.String r4 = r4.getString(r5)
            goto L_0x048e
        L_0x048b:
            r73 = r7
            r4 = 0
        L_0x048e:
            r74 = 12889096192(0x300400000, double:6.3680596344E-314)
            long r74 = r2 & r74
            r51 = 0
            int r5 = (r74 > r51 ? 1 : (r74 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x04af
            if (r0 == 0) goto L_0x04a0
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.vpdSize
            goto L_0x04a1
        L_0x04a0:
            r5 = 0
        L_0x04a1:
            r7 = 22
            r1.updateRegistration((int) r7, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x04af
            java.lang.Object r5 = r5.get()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x04b0
        L_0x04af:
            r5 = 0
        L_0x04b0:
            r74 = 12893290496(0x300800000, double:6.370131896E-314)
            long r74 = r2 & r74
            r51 = 0
            int r7 = (r74 > r51 ? 1 : (r74 == r51 ? 0 : -1))
            if (r7 == 0) goto L_0x04d5
            if (r0 == 0) goto L_0x04c4
            androidx.databinding.ObservableField<java.lang.String> r7 = r0.hintText
            r74 = r4
            goto L_0x04c7
        L_0x04c4:
            r74 = r4
            r7 = 0
        L_0x04c7:
            r4 = 23
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r7)
            if (r7 == 0) goto L_0x04d7
            java.lang.Object r4 = r7.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x04d8
        L_0x04d5:
            r74 = r4
        L_0x04d7:
            r4 = 0
        L_0x04d8:
            r75 = 12901679104(0x301000000, double:6.374276419E-314)
            long r75 = r2 & r75
            r51 = 0
            int r7 = (r75 > r51 ? 1 : (r75 == r51 ? 0 : -1))
            r75 = r4
            if (r7 == 0) goto L_0x051d
            if (r0 == 0) goto L_0x04ee
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.connected
            r76 = r5
            goto L_0x04f1
        L_0x04ee:
            r76 = r5
            r4 = 0
        L_0x04f1:
            r5 = 24
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x04ff
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0500
        L_0x04ff:
            r4 = 0
        L_0x0500:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            r5 = 1
            r4 = r4 ^ r5
            if (r7 == 0) goto L_0x0517
            if (r4 == 0) goto L_0x0510
            r77 = 34359738368(0x800000000, double:1.69759663277E-313)
            goto L_0x0515
        L_0x0510:
            r77 = 17179869184(0x400000000, double:8.4879831639E-314)
        L_0x0515:
            long r2 = r2 | r77
        L_0x0517:
            if (r4 == 0) goto L_0x051a
            goto L_0x051f
        L_0x051a:
            r7 = 8
            goto L_0x0520
        L_0x051d:
            r76 = r5
        L_0x051f:
            r7 = 0
        L_0x0520:
            r4 = 12918456320(0x302000000, double:6.382565465E-314)
            long r4 = r4 & r2
            r51 = 0
            int r77 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r77 == 0) goto L_0x0556
            if (r0 == 0) goto L_0x0531
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.automationVisible
            goto L_0x0532
        L_0x0531:
            r4 = 0
        L_0x0532:
            r5 = 25
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0540
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0541
        L_0x0540:
            r4 = 0
        L_0x0541:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r77 == 0) goto L_0x0550
            if (r4 == 0) goto L_0x054c
            r77 = 576460752303423488(0x800000000000000, double:3.785766995733679E-270)
            goto L_0x054e
        L_0x054c:
            r77 = 288230376151711744(0x400000000000000, double:2.0522684006491881E-289)
        L_0x054e:
            long r2 = r2 | r77
        L_0x0550:
            if (r4 == 0) goto L_0x0553
            goto L_0x0556
        L_0x0553:
            r4 = 8
            goto L_0x0557
        L_0x0556:
            r4 = 0
        L_0x0557:
            long r77 = r2 & r25
            r51 = 0
            int r5 = (r77 > r51 ? 1 : (r77 == r51 ? 0 : -1))
            r77 = r4
            if (r5 == 0) goto L_0x0584
            if (r0 == 0) goto L_0x0568
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.humModeVisible
            r78 = r7
            goto L_0x056b
        L_0x0568:
            r78 = r7
            r4 = 0
        L_0x056b:
            r7 = 26
            r1.updateLiveDataRegistration(r7, r4)
            if (r4 == 0) goto L_0x0579
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x057a
        L_0x0579:
            r4 = 0
        L_0x057a:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r4 == 0) goto L_0x0581
            goto L_0x0586
        L_0x0581:
            r7 = 8
            goto L_0x0587
        L_0x0584:
            r78 = r7
        L_0x0586:
            r7 = 0
        L_0x0587:
            r4 = 13019119616(0x308000000, double:6.4322997414E-314)
            long r4 = r4 & r2
            r51 = 0
            int r79 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r79 == 0) goto L_0x05a5
            if (r0 == 0) goto L_0x0598
            androidx.databinding.ObservableInt r4 = r0.minFanType
            goto L_0x0599
        L_0x0598:
            r4 = 0
        L_0x0599:
            r5 = 27
            r1.updateRegistration((int) r5, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x05a5
            int r4 = r4.get()
            goto L_0x05a6
        L_0x05a5:
            r4 = 0
        L_0x05a6:
            r79 = 13153337344(0x310000000, double:6.49861211E-314)
            long r79 = r2 & r79
            r51 = 0
            int r5 = (r79 > r51 ? 1 : (r79 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x05cb
            if (r0 == 0) goto L_0x05ba
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.fanTypeTitle
            r79 = r4
            goto L_0x05bd
        L_0x05ba:
            r79 = r4
            r5 = 0
        L_0x05bd:
            r4 = 28
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x05cd
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x05ce
        L_0x05cb:
            r79 = r4
        L_0x05cd:
            r4 = 0
        L_0x05ce:
            r80 = 13421772800(0x320000000, double:6.631236847E-314)
            long r80 = r2 & r80
            r51 = 0
            int r5 = (r80 > r51 ? 1 : (r80 == r51 ? 0 : -1))
            if (r5 == 0) goto L_0x05f3
            if (r0 == 0) goto L_0x05e2
            androidx.databinding.ObservableField<java.lang.String> r5 = r0.perSize
            r80 = r4
            goto L_0x05e5
        L_0x05e2:
            r80 = r4
            r5 = 0
        L_0x05e5:
            r4 = 29
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r5)
            if (r5 == 0) goto L_0x05f5
            java.lang.Object r4 = r5.get()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x05f6
        L_0x05f3:
            r80 = r4
        L_0x05f5:
            r4 = 0
        L_0x05f6:
            r81 = 13958643712(0x340000000, double:6.8964863206E-314)
            long r81 = r2 & r81
            r51 = 0
            int r5 = (r81 > r51 ? 1 : (r81 == r51 ? 0 : -1))
            r81 = r4
            if (r5 == 0) goto L_0x0639
            if (r0 == 0) goto L_0x060c
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.overlayVisible
            r82 = r7
            goto L_0x060f
        L_0x060c:
            r82 = r7
            r4 = 0
        L_0x060f:
            r7 = 30
            r1.updateLiveDataRegistration(r7, r4)
            if (r4 == 0) goto L_0x061d
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x061e
        L_0x061d:
            r4 = 0
        L_0x061e:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x0633
            if (r4 == 0) goto L_0x062c
            r83 = 549755813888(0x8000000000, double:2.716154612436E-312)
            goto L_0x0631
        L_0x062c:
            r83 = 274877906944(0x4000000000, double:1.358077306218E-312)
        L_0x0631:
            long r2 = r2 | r83
        L_0x0633:
            if (r4 == 0) goto L_0x0636
            goto L_0x063b
        L_0x0636:
            r7 = 8
            goto L_0x063c
        L_0x0639:
            r82 = r7
        L_0x063b:
            r7 = 0
        L_0x063c:
            long r4 = r2 & r37
            r51 = 0
            int r83 = (r4 > r51 ? 1 : (r4 == r51 ? 0 : -1))
            if (r83 == 0) goto L_0x0733
            if (r0 == 0) goto L_0x0649
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r0.residueFlag
            goto L_0x064a
        L_0x0649:
            r0 = 0
        L_0x064a:
            r4 = 31
            r1.updateLiveDataRegistration(r4, r0)
            if (r0 == 0) goto L_0x0658
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L_0x0659
        L_0x0658:
            r0 = 0
        L_0x0659:
            int r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r0)
            r5 = 1
            if (r0 != r5) goto L_0x0662
            r4 = 1
            goto L_0x0663
        L_0x0662:
            r4 = 0
        L_0x0663:
            r5 = 2
            if (r0 != r5) goto L_0x0669
            r45 = 1
            goto L_0x066b
        L_0x0669:
            r45 = 0
        L_0x066b:
            if (r83 == 0) goto L_0x0684
            if (r4 == 0) goto L_0x0679
            r83 = 137438953472(0x2000000000, double:6.7903865311E-313)
            long r2 = r2 | r83
            r83 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            goto L_0x0682
        L_0x0679:
            r83 = 68719476736(0x1000000000, double:3.39519326554E-313)
            long r2 = r2 | r83
            r83 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
        L_0x0682:
            long r2 = r2 | r83
        L_0x0684:
            long r83 = r2 & r37
            r51 = 0
            int r0 = (r83 > r51 ? 1 : (r83 == r51 ? 0 : -1))
            if (r0 == 0) goto L_0x06a3
            if (r45 == 0) goto L_0x0698
            r83 = 140737488355328(0x800000000000, double:6.953355807835E-310)
            long r2 = r2 | r83
            r83 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            goto L_0x06a1
        L_0x0698:
            r83 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            long r2 = r2 | r83
            r83 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
        L_0x06a1:
            long r2 = r2 | r83
        L_0x06a3:
            if (r4 == 0) goto L_0x06a7
            r0 = 0
            goto L_0x06a9
        L_0x06a7:
            r0 = 8
        L_0x06a9:
            if (r4 == 0) goto L_0x06b0
            android.widget.TextView r4 = r1.tvMaxFan
            int r5 = com.eternal.control.C1760R.C1761color.white
            goto L_0x06b4
        L_0x06b0:
            android.widget.TextView r4 = r1.tvMaxFan
            int r5 = com.eternal.control.C1760R.C1761color.color_BFBFBF
        L_0x06b4:
            int r4 = getColorFromResource(r4, r5)
            if (r45 == 0) goto L_0x06bd
            r62 = 0
            goto L_0x06bf
        L_0x06bd:
            r62 = 8
        L_0x06bf:
            if (r45 == 0) goto L_0x06cc
            android.widget.TextView r5 = r1.tvMinFan
            r39 = r0
            int r0 = com.eternal.control.C1760R.C1761color.white
            int r0 = getColorFromResource(r5, r0)
            goto L_0x06d6
        L_0x06cc:
            r39 = r0
            android.widget.TextView r0 = r1.tvMinFan
            int r5 = com.eternal.control.C1760R.C1761color.color_BFBFBF
            int r0 = getColorFromResource(r0, r5)
        L_0x06d6:
            r5 = r4
            r90 = r7
            r103 = r8
            r104 = r9
            r96 = r10
            r92 = r11
            r87 = r12
            r99 = r13
            r100 = r14
            r85 = r15
            r105 = r16
            r106 = r42
            r107 = r46
            r86 = r53
            r8 = r55
            r7 = r56
            r53 = r57
            r16 = r58
            r108 = r59
            r109 = r60
            r110 = r61
            r14 = r62
            r45 = r64
            r89 = r65
            r97 = r66
            r102 = r67
            r9 = r68
            r91 = r69
            r13 = r70
            r94 = r71
            r95 = r72
            r12 = r73
            r10 = r74
            r88 = r75
            r98 = r76
            r41 = r77
            r101 = r78
            r42 = r79
            r11 = r80
            r93 = r81
            r15 = r82
            r3 = r2
            r46 = r6
            r6 = r54
            r2 = r0
            r0 = r39
            r39 = r63
            goto L_0x07dd
        L_0x0733:
            r3 = r2
            r90 = r7
            r103 = r8
            r104 = r9
            r96 = r10
            r92 = r11
            r87 = r12
            r99 = r13
            r100 = r14
            r85 = r15
            r105 = r16
            r106 = r42
            r107 = r46
            r86 = r53
            r8 = r55
            r7 = r56
            r53 = r57
            r16 = r58
            r108 = r59
            r109 = r60
            r110 = r61
            r39 = r63
            r45 = r64
            r89 = r65
            r97 = r66
            r102 = r67
            r9 = r68
            r91 = r69
            r13 = r70
            r94 = r71
            r95 = r72
            r12 = r73
            r10 = r74
            r88 = r75
            r98 = r76
            r41 = r77
            r101 = r78
            r42 = r79
            r11 = r80
            r93 = r81
            r15 = r82
            r0 = 0
            r2 = 0
            r5 = 0
            r14 = 0
            r46 = r6
            r6 = r54
            goto L_0x07dd
        L_0x078d:
            r3 = r2
            r46 = r6
            r0 = 0
            r2 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r39 = 0
            r41 = 0
            r42 = 0
            r45 = 0
            r53 = 0
            r85 = 0
            r86 = 0
            r87 = 0
            r88 = 0
            r89 = 0
            r90 = 0
            r91 = 0
            r92 = 0
            r93 = 0
            r94 = 0
            r95 = 0
            r96 = 0
            r97 = 0
            r98 = 0
            r99 = 0
            r100 = 0
            r101 = 0
            r102 = 0
            r103 = 0
            r104 = 0
            r105 = 0
            r106 = 0
            r107 = 0
            r108 = 0
            r109 = 0
            r110 = 0
        L_0x07dd:
            long r35 = r3 & r35
            r51 = 0
            int r54 = (r35 > r51 ? 1 : (r35 == r51 ? 0 : -1))
            r35 = r6
            if (r54 == 0) goto L_0x07ec
            androidx.constraintlayout.widget.ConstraintLayout r6 = r1.ctMaxMin
            r6.setVisibility(r13)
        L_0x07ec:
            long r36 = r3 & r37
            int r6 = (r36 > r51 ? 1 : (r36 == r51 ? 0 : -1))
            if (r6 == 0) goto L_0x0806
            android.widget.ImageView r6 = r1.ivMaxDot
            r6.setVisibility(r0)
            android.widget.ImageView r0 = r1.ivMinDot
            r0.setVisibility(r14)
            android.widget.TextView r0 = r1.tvMaxFan
            r0.setTextColor(r5)
            android.widget.TextView r0 = r1.tvMinFan
            r0.setTextColor(r2)
        L_0x0806:
            long r5 = r3 & r29
            r13 = 0
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0813
            android.widget.ImageView r0 = r1.ivWindSpeed
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r7)
        L_0x0813:
            long r5 = r3 & r25
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0832
            com.eternal.widget.guqiang.DoubleAddLayout r0 = r1.layoutHum
            r0.setVisibility(r15)
            android.view.View r0 = r1.mboundView5
            r0.setVisibility(r15)
            android.widget.TextView r0 = r1.tvAutoHumHighDesc
            r0.setVisibility(r15)
            android.widget.TextView r0 = r1.tvAutoHumLowDesc
            r0.setVisibility(r15)
            android.widget.TextView r0 = r1.tvHumTitle
            r0.setVisibility(r15)
        L_0x0832:
            long r5 = r3 & r21
            r13 = 0
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x083f
            android.widget.RelativeLayout r0 = r1.mboundView19
            r0.setVisibility(r8)
        L_0x083f:
            long r5 = r3 & r17
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x084a
            android.widget.RelativeLayout r0 = r1.mboundView21
            r0.setVisibility(r9)
        L_0x084a:
            r5 = 12885164032(0x300040000, double:6.366116889E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0859
            android.widget.RelativeLayout r0 = r1.mboundView23
            r0.setVisibility(r12)
        L_0x0859:
            r5 = 13153337344(0x310000000, double:6.49861211E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0868
            android.widget.TextView r0 = r1.mboundView24
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r11)
        L_0x0868:
            r5 = 12886999040(0x300200000, double:6.3670235037E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0877
            android.widget.TextView r0 = r1.mboundView28
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x0877:
            long r5 = r3 & r19
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0884
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView30
            r2 = r35
            r0.setVisibility(r2)
        L_0x0884:
            r5 = 12884902144(0x300000100, double:6.3659874994E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0895
            android.widget.ImageView r0 = r1.mboundView44
            r2 = r53
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.glideRes(r0, r2)
        L_0x0895:
            r5 = 12884901888(0x300000000, double:6.365987373E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x08d8
            android.widget.ImageView r0 = r1.mboundView44
            r15 = r85
            r2 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r15, r2)
            android.widget.TextView r0 = r1.txtMode
            r2 = r86
            r0.setOnClickListener(r2)
            android.widget.TextView r0 = r1.txtModeAuto
            r12 = r87
            r0.setOnClickListener(r12)
            android.widget.TextView r0 = r1.txtModeCycle
            r0.setOnClickListener(r12)
            android.widget.TextView r0 = r1.txtModeOff
            r0.setOnClickListener(r12)
            android.widget.TextView r0 = r1.txtModeOn
            r0.setOnClickListener(r12)
            android.widget.TextView r0 = r1.txtModeSched
            r0.setOnClickListener(r12)
            android.widget.TextView r0 = r1.txtModeTimeToOff
            r0.setOnClickListener(r12)
            android.widget.TextView r0 = r1.txtModeTimeToOn
            r0.setOnClickListener(r12)
            android.widget.TextView r0 = r1.txtModeVpd
            r0.setOnClickListener(r12)
        L_0x08d8:
            r5 = 12893290496(0x300800000, double:6.370131896E-314)
            long r5 = r5 & r3
            r7 = 0
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x08f5
            android.widget.TextView r0 = r1.mboundView6
            r2 = r88
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
            android.widget.TextView r0 = r1.mboundView7
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
            android.widget.TextView r0 = r1.mboundView8
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x08f5:
            r5 = 12884902912(0x300000400, double:6.365987879E-314)
            long r5 = r5 & r3
            r7 = 0
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0908
            android.widget.RelativeLayout r0 = r1.rlPower
            r2 = r89
            r0.setVisibility(r2)
        L_0x0908:
            r5 = 13958643712(0x340000000, double:6.8964863206E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0919
            androidx.core.widget.NestedScrollView r0 = r1.scOverlay
            r2 = r90
            r0.setVisibility(r2)
        L_0x0919:
            r5 = 12885032960(0x300020000, double:6.366052131E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0935
            android.widget.TextView r0 = r1.tvBufferHum
            android.widget.TextView r2 = r1.tvBufferHum
            int r5 = com.eternal.control.C1760R.C1761color.color_BFBFBF
            int r2 = getColorFromResource(r2, r5)
            r6 = r46
            r5 = r91
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r5, r2, r6)
            goto L_0x0937
        L_0x0935:
            r6 = r46
        L_0x0937:
            r9 = 12884901952(0x300000040, double:6.3659874045E-314)
            long r9 = r9 & r3
            int r0 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0950
            android.widget.TextView r0 = r1.tvBufferTemp
            android.widget.TextView r2 = r1.tvBufferTemp
            int r5 = com.eternal.control.C1760R.C1761color.color_BFBFBF
            int r2 = getColorFromResource(r2, r5)
            r11 = r92
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.textDecoration(r0, r11, r2, r6)
        L_0x0950:
            r5 = 13421772800(0x320000000, double:6.631236847E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0961
            android.widget.TextView r0 = r1.tvHum
            r2 = r93
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x0961:
            r5 = 12885426176(0x300080000, double:6.3662464056E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0972
            android.widget.TextView r0 = r1.tvMaxFan
            r2 = r94
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x0972:
            r5 = 12884905984(0x300001000, double:6.3659893966E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0983
            android.widget.ImageView r0 = r1.tvMaxFanType
            r2 = r45
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r2)
        L_0x0983:
            r5 = 12885950464(0x300100000, double:6.3665054383E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0994
            android.widget.TextView r0 = r1.tvMinFan
            r2 = r95
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x0994:
            r5 = 13019119616(0x308000000, double:6.4322997414E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x09a5
            android.widget.ImageView r0 = r1.tvMinFanType
            r2 = r42
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r2)
        L_0x09a5:
            r5 = 12884901920(0x300000020, double:6.3659873887E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x09b6
            android.widget.TextView r0 = r1.tvTempUnit
            r10 = r96
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x09b6:
            r5 = 12884918272(0x300004000, double:6.3659954677E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x09c7
            android.widget.TextView r0 = r1.tvTmp
            r2 = r97
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x09c7:
            r5 = 12889096192(0x300400000, double:6.3680596344E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x09d8
            android.widget.TextView r0 = r1.tvVpd
            r2 = r98
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x09d8:
            long r5 = r3 & r23
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x09e5
            android.widget.TextView r0 = r1.tvWindSpeed
            r13 = r99
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r13)
        L_0x09e5:
            r5 = 12918456320(0x302000000, double:6.382565465E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x09f6
            android.widget.TextView r0 = r1.txtAutomationActivated
            r2 = r41
            r0.setVisibility(r2)
        L_0x09f6:
            long r5 = r3 & r27
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0a03
            android.widget.TextView r0 = r1.txtMode
            r14 = r100
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r14)
        L_0x0a03:
            r5 = 12884903936(0x300000800, double:6.3659883847E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0a45
            android.widget.TextView r0 = r1.txtModeAuto
            r2 = r107
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r2)
            android.widget.TextView r0 = r1.txtModeCycle
            r2 = r39
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r2)
            android.widget.TextView r0 = r1.txtModeOff
            r2 = r106
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r2)
            android.widget.TextView r0 = r1.txtModeOn
            r2 = r109
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r2)
            android.widget.TextView r0 = r1.txtModeSched
            r2 = r110
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r2)
            android.widget.TextView r0 = r1.txtModeTimeToOff
            r2 = r108
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r2)
            android.widget.TextView r0 = r1.txtModeTimeToOn
            r2 = r16
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r2)
            android.widget.TextView r0 = r1.txtModeVpd
            r2 = r105
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r2)
        L_0x0a45:
            long r5 = r3 & r33
            r7 = 0
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0a54
            android.widget.TextView r0 = r1.txtModeSched
            r9 = r104
            r0.setVisibility(r9)
        L_0x0a54:
            long r5 = r3 & r31
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0a61
            android.widget.TextView r0 = r1.txtModeVpd
            r2 = r103
            r0.setVisibility(r2)
        L_0x0a61:
            r5 = 12901679104(0x301000000, double:6.374276419E-314)
            long r5 = r5 & r3
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0a72
            android.view.View r0 = r1.vMask
            r2 = r101
            r0.setVisibility(r2)
        L_0x0a72:
            r5 = 12884910080(0x300002000, double:6.3659914203E-314)
            long r2 = r3 & r5
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0a84
            android.view.View r0 = r1.vMaskAutomation
            r2 = r102
            r0.setVisibility(r2)
        L_0x0a84:
            return
        L_0x0a85:
            r0 = move-exception
            monitor-exit(r111)     // Catch:{ all -> 0x0a85 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.control.databinding.FragmentControlBindingImpl.executeBindings():void");
    }
}
