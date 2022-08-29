package com.eternal.notification.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Space;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.notification.C2419BR;
import com.eternal.notification.C2420R;
import com.eternal.notification.model.AutomationModel;
import com.eternal.notification.view.TitleSelectView;
import com.eternal.notification.view.TitleSelectViewAdapter;

public class FragmentAutomationBindingImpl extends FragmentAutomationBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etEditNameandroidTextAttrChanged;
    private long mDirtyFlags;
    private final Space mboundView10;
    private final View mboundView12;
    private final Space mboundView14;
    private final View mboundView15;
    private final Space mboundView16;
    private final Space mboundView18;
    private final Space mboundView19;
    private final View mboundView26;
    private final TextView mboundView3;
    private final ConstraintLayout mboundView4;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView5;
    private InverseBindingListener mboundView5selectAttrChange;
    private final Space mboundView6;
    private final Space mboundView7;
    private final Space mboundView9;
    private InverseBindingListener modeCycleselectAttrChange;
    private InverseBindingListener modeOnselectAttrChange;
    private InverseBindingListener modeTempHumselectAttrChange;
    private InverseBindingListener modeVpdselectAttrChange;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2420R.C2423id.tv_start_time_des, 31);
        sparseIntArray.put(C2420R.C2423id.ll_start_time, 32);
        sparseIntArray.put(C2420R.C2423id.wv_start_hour, 33);
        sparseIntArray.put(C2420R.C2423id.wv_start_min, 34);
        sparseIntArray.put(C2420R.C2423id.wv_start_am_or_pm, 35);
        sparseIntArray.put(C2420R.C2423id.v_time_line, 36);
        sparseIntArray.put(C2420R.C2423id.tv_end_time_des, 37);
        sparseIntArray.put(C2420R.C2423id.ll_end_time, 38);
        sparseIntArray.put(C2420R.C2423id.wv_end_hour, 39);
        sparseIntArray.put(C2420R.C2423id.wv_end_min, 40);
        sparseIntArray.put(C2420R.C2423id.wv_end_am_or_pm, 41);
        sparseIntArray.put(C2420R.C2423id.el_on, 42);
        sparseIntArray.put(C2420R.C2423id.ll_on_container, 43);
        sparseIntArray.put(C2420R.C2423id.el_tmp_hum, 44);
        sparseIntArray.put(C2420R.C2423id.ll_tmphum_container, 45);
        sparseIntArray.put(C2420R.C2423id.tv_temp_min, 46);
        sparseIntArray.put(C2420R.C2423id.rsb_temp, 47);
        sparseIntArray.put(C2420R.C2423id.tv_temp_max, 48);
        sparseIntArray.put(C2420R.C2423id.layout_tmp, 49);
        sparseIntArray.put(C2420R.C2423id.sb_high_tmp, 50);
        sparseIntArray.put(C2420R.C2423id.sb_low_tmp, 51);
        sparseIntArray.put(C2420R.C2423id.tv_hum_min, 52);
        sparseIntArray.put(C2420R.C2423id.rsb_hum, 53);
        sparseIntArray.put(C2420R.C2423id.layout_hum, 54);
        sparseIntArray.put(C2420R.C2423id.sb_high_hum, 55);
        sparseIntArray.put(C2420R.C2423id.sb_low_hum, 56);
        sparseIntArray.put(C2420R.C2423id.el_vpd, 57);
        sparseIntArray.put(C2420R.C2423id.ll_vpd_container, 58);
        sparseIntArray.put(C2420R.C2423id.tv_vpd_min, 59);
        sparseIntArray.put(C2420R.C2423id.rsb_vpd, 60);
        sparseIntArray.put(C2420R.C2423id.layout_vpd, 61);
        sparseIntArray.put(C2420R.C2423id.sb_high_vpd, 62);
        sparseIntArray.put(C2420R.C2423id.sb_low_vpd, 63);
        sparseIntArray.put(C2420R.C2423id.el_cycle, 64);
        sparseIntArray.put(C2420R.C2423id.ll_cycle_container, 65);
        sparseIntArray.put(C2420R.C2423id.tv_cycle_on_des, 66);
        sparseIntArray.put(C2420R.C2423id.ll_cycle_on, 67);
        sparseIntArray.put(C2420R.C2423id.wv_cycle_on_hour, 68);
        sparseIntArray.put(C2420R.C2423id.wv_cycle_on_min, 69);
        sparseIntArray.put(C2420R.C2423id.v_cycle_line, 70);
        sparseIntArray.put(C2420R.C2423id.tv_cycle_off_des, 71);
        sparseIntArray.put(C2420R.C2423id.ll_cycle_off_time, 72);
        sparseIntArray.put(C2420R.C2423id.wv_cycle_off_hour, 73);
        sparseIntArray.put(C2420R.C2423id.wv_cycle_off_min, 74);
        sparseIntArray.put(C2420R.C2423id.fpb_off, 75);
        sparseIntArray.put(C2420R.C2423id.ll_level_on, 76);
        sparseIntArray.put(C2420R.C2423id.fpb_on, 77);
    }

    public FragmentAutomationBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 78, sIncludes, sViewsWithIds));
    }

    private FragmentAutomationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 19, objArr[30], objArr[64], objArr[42], objArr[44], objArr[57], objArr[2], objArr[75], objArr[77], objArr[25], objArr[29], objArr[54], objArr[49], objArr[61], objArr[0], objArr[65], objArr[72], objArr[67], objArr[38], objArr[13], objArr[21], objArr[22], objArr[76], objArr[43], objArr[32], objArr[45], objArr[58], objArr[20], objArr[8], objArr[11], objArr[17], objArr[53], objArr[47], objArr[60], objArr[55], objArr[50], objArr[62], objArr[56], objArr[51], objArr[63], objArr[71], objArr[66], objArr[37], objArr[52], objArr[24], objArr[23], objArr[28], objArr[27], objArr[1], objArr[31], objArr[48], objArr[46], objArr[59], objArr[70], objArr[36], objArr[73], objArr[74], objArr[68], objArr[69], objArr[41], objArr[39], objArr[40], objArr[35], objArr[33], objArr[34]);
        this.etEditNameandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentAutomationBindingImpl.this.etEditName);
                AutomationModel automationModel = FragmentAutomationBindingImpl.this.mAutoModel;
                boolean z = true;
                if (automationModel != null) {
                    MutableLiveData<String> mutableLiveData = automationModel.name;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.mboundView5selectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationBindingImpl.this.mboundView5);
                AutomationModel automationModel = FragmentAutomationBindingImpl.this.mAutoModel;
                boolean z = true;
                if (automationModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModel.off;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.modeCycleselectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationBindingImpl.this.modeCycle);
                AutomationModel automationModel = FragmentAutomationBindingImpl.this.mAutoModel;
                boolean z = true;
                if (automationModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModel.cycle;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.modeOnselectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationBindingImpl.this.modeOn);
                AutomationModel automationModel = FragmentAutomationBindingImpl.this.mAutoModel;
                boolean z = true;
                if (automationModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModel.f215on;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.modeTempHumselectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationBindingImpl.this.modeTempHum);
                AutomationModel automationModel = FragmentAutomationBindingImpl.this.mAutoModel;
                boolean z = true;
                if (automationModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModel.tmphum;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.modeVpdselectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationBindingImpl.this.modeVpd);
                AutomationModel automationModel = FragmentAutomationBindingImpl.this.mAutoModel;
                boolean z = true;
                if (automationModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModel.vpd;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.mDirtyFlags = -1;
        this.btnDelete.setTag((Object) null);
        this.etEditName.setTag((Object) null);
        this.ivLevelOff.setTag((Object) null);
        this.ivLevelOn.setTag((Object) null);
        this.llContent.setTag((Object) null);
        this.llHumContainer.setTag((Object) null);
        this.llLevel.setTag((Object) null);
        this.llLevelOff.setTag((Object) null);
        Space space = objArr[10];
        this.mboundView10 = space;
        space.setTag((Object) null);
        View view2 = objArr[12];
        this.mboundView12 = view2;
        view2.setTag((Object) null);
        Space space2 = objArr[14];
        this.mboundView14 = space2;
        space2.setTag((Object) null);
        View view3 = objArr[15];
        this.mboundView15 = view3;
        view3.setTag((Object) null);
        Space space3 = objArr[16];
        this.mboundView16 = space3;
        space3.setTag((Object) null);
        Space space4 = objArr[18];
        this.mboundView18 = space4;
        space4.setTag((Object) null);
        Space space5 = objArr[19];
        this.mboundView19 = space5;
        space5.setTag((Object) null);
        View view4 = objArr[26];
        this.mboundView26 = view4;
        view4.setTag((Object) null);
        TextView textView = objArr[3];
        this.mboundView3 = textView;
        textView.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[4];
        this.mboundView4 = constraintLayout;
        constraintLayout.setTag((Object) null);
        TitleSelectView titleSelectView = objArr[5];
        this.mboundView5 = titleSelectView;
        titleSelectView.setTag((Object) null);
        Space space6 = objArr[6];
        this.mboundView6 = space6;
        space6.setTag((Object) null);
        Space space7 = objArr[7];
        this.mboundView7 = space7;
        space7.setTag((Object) null);
        Space space8 = objArr[9];
        this.mboundView9 = space8;
        space8.setTag((Object) null);
        this.modeCycle.setTag((Object) null);
        this.modeOn.setTag((Object) null);
        this.modeTempHum.setTag((Object) null);
        this.modeVpd.setTag((Object) null);
        this.tvLevelOffDes.setTag((Object) null);
        this.tvLevelOffTitle.setTag((Object) null);
        this.tvLevelOnDes.setTag((Object) null);
        this.tvLevelOnTitle.setTag((Object) null);
        this.tvNameSuggest.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (C2419BR.autoModel != i) {
            return false;
        }
        setAutoModel((AutomationModel) obj);
        return true;
    }

    public void setAutoModel(AutomationModel automationModel) {
        this.mAutoModel = automationModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        notifyPropertyChanged(C2419BR.autoModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeAutoModelCycle((MutableLiveData) obj, i2);
            case 1:
                return onChangeAutoModelLevelOffVisible((MutableLiveData) obj, i2);
            case 2:
                return onChangeAutoModelLevelVisible((MutableLiveData) obj, i2);
            case 3:
                return onChangeAutoModelLevelOnTitle((MutableLiveData) obj, i2);
            case 4:
                return onChangeAutoModelOff((MutableLiveData) obj, i2);
            case 5:
                return onChangeAutoModelTmphum((MutableLiveData) obj, i2);
            case 6:
                return onChangeAutoModelName((MutableLiveData) obj, i2);
            case 7:
                return onChangeAutoModelVpdModeVisible((MutableLiveData) obj, i2);
            case 8:
                return onChangeAutoModelLevelOnDes((MutableLiveData) obj, i2);
            case 9:
                return onChangeAutoModelLevelOnRes((MutableLiveData) obj, i2);
            case 10:
                return onChangeAutoModelLevelOffTitle((MutableLiveData) obj, i2);
            case 11:
                return onChangeAutoModelVpd((MutableLiveData) obj, i2);
            case 12:
                return onChangeAutoModelOn((MutableLiveData) obj, i2);
            case 13:
                return onChangeAutoModelLevelOffRes((MutableLiveData) obj, i2);
            case 14:
                return onChangeAutoModelLevelOffDes((MutableLiveData) obj, i2);
            case 15:
                return onChangeAutoModelIsCreate((MutableLiveData) obj, i2);
            case 16:
                return onChangeAutoModelHumVisible((MutableLiveData) obj, i2);
            case 17:
                return onChangeAutoModelTimeVisible((MutableLiveData) obj, i2);
            case 18:
                return onChangeAutoModelNameSuggest((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeAutoModelCycle(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeAutoModelLevelOffVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeAutoModelLevelVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeAutoModelLevelOnTitle(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeAutoModelOff(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeAutoModelTmphum(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeAutoModelName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeAutoModelVpdModeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeAutoModelLevelOnDes(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeAutoModelLevelOnRes(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeAutoModelLevelOffTitle(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeAutoModelVpd(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeAutoModelOn(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeAutoModelLevelOffRes(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeAutoModelLevelOffDes(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeAutoModelIsCreate(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeAutoModelHumVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeAutoModelTimeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeAutoModelNameSuggest(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0182  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x01bc  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0207  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x021f  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x022f  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0263  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x0274  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x02d2  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x02f8  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0305  */
    /* JADX WARNING: Removed duplicated region for block: B:244:0x0338  */
    /* JADX WARNING: Removed duplicated region for block: B:248:0x0343  */
    /* JADX WARNING: Removed duplicated region for block: B:263:0x0373  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0381  */
    /* JADX WARNING: Removed duplicated region for block: B:282:0x03b1  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x03bc A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x03ce  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x03fa  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r78 = this;
            r1 = r78
            monitor-enter(r78)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x05de }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x05de }
            monitor-exit(r78)     // Catch:{ all -> 0x05de }
            com.eternal.notification.model.AutomationModel r0 = r1.mAutoModel
            r6 = 2097151(0x1fffff, double:1.0361303E-317)
            long r6 = r6 & r2
            r16 = 1573120(0x180100, double:7.772245E-318)
            r18 = 1572992(0x180080, double:7.771613E-318)
            r20 = 1638400(0x190000, double:8.09477E-318)
            r22 = 1572896(0x180020, double:7.77114E-318)
            r24 = 1573376(0x180200, double:7.77351E-318)
            r26 = 1572880(0x180010, double:7.77106E-318)
            r28 = 1581056(0x182000, double:7.811455E-318)
            r30 = 1572872(0x180008, double:7.77102E-318)
            r32 = 1572868(0x180004, double:7.771E-318)
            r34 = 1572928(0x180040, double:7.771297E-318)
            r36 = 1572866(0x180002, double:7.77099E-318)
            r38 = 1572864(0x180000, double:7.77098E-318)
            r40 = 1572865(0x180001, double:7.770986E-318)
            r42 = 1605632(0x188000, double:7.932876E-318)
            r8 = 0
            int r46 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r46 == 0) goto L_0x042d
            long r6 = r2 & r40
            int r47 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r47 == 0) goto L_0x006f
            if (r0 == 0) goto L_0x004a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.cycle
            goto L_0x004b
        L_0x004a:
            r6 = 0
        L_0x004b:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x0057
            java.lang.Object r6 = r6.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            goto L_0x0058
        L_0x0057:
            r6 = 0
        L_0x0058:
            boolean r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r6)
            if (r47 == 0) goto L_0x0069
            if (r6 == 0) goto L_0x0064
            r47 = 4194304(0x400000, double:2.0722615E-317)
            goto L_0x0067
        L_0x0064:
            r47 = 2097152(0x200000, double:1.0361308E-317)
        L_0x0067:
            long r2 = r2 | r47
        L_0x0069:
            if (r6 == 0) goto L_0x006c
            goto L_0x0070
        L_0x006c:
            r7 = 8
            goto L_0x0071
        L_0x006f:
            r6 = 0
        L_0x0070:
            r7 = 0
        L_0x0071:
            long r47 = r2 & r36
            int r49 = (r47 > r4 ? 1 : (r47 == r4 ? 0 : -1))
            if (r49 == 0) goto L_0x00a5
            if (r0 == 0) goto L_0x007c
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.levelOffVisible
            goto L_0x007d
        L_0x007c:
            r8 = 0
        L_0x007d:
            r10 = 1
            r1.updateLiveDataRegistration(r10, r8)
            if (r8 == 0) goto L_0x008a
            java.lang.Object r8 = r8.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x008b
        L_0x008a:
            r8 = 0
        L_0x008b:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r49 == 0) goto L_0x009f
            if (r8 == 0) goto L_0x0099
            r10 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            goto L_0x009e
        L_0x0099:
            r10 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
        L_0x009e:
            long r2 = r2 | r10
        L_0x009f:
            if (r8 == 0) goto L_0x00a2
            goto L_0x00a5
        L_0x00a2:
            r8 = 8
            goto L_0x00a6
        L_0x00a5:
            r8 = 0
        L_0x00a6:
            long r10 = r2 & r32
            int r48 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r48 == 0) goto L_0x00db
            if (r0 == 0) goto L_0x00b1
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.levelVisible
            goto L_0x00b2
        L_0x00b1:
            r10 = 0
        L_0x00b2:
            r11 = 2
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x00bf
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x00c0
        L_0x00bf:
            r10 = 0
        L_0x00c0:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r48 == 0) goto L_0x00d5
            if (r10 == 0) goto L_0x00ce
            r48 = 68719476736(0x1000000000, double:3.39519326554E-313)
            goto L_0x00d3
        L_0x00ce:
            r48 = 34359738368(0x800000000, double:1.69759663277E-313)
        L_0x00d3:
            long r2 = r2 | r48
        L_0x00d5:
            if (r10 == 0) goto L_0x00d8
            goto L_0x00db
        L_0x00d8:
            r10 = 8
            goto L_0x00dc
        L_0x00db:
            r10 = 0
        L_0x00dc:
            long r48 = r2 & r30
            int r11 = (r48 > r4 ? 1 : (r48 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00f5
            if (r0 == 0) goto L_0x00e7
            androidx.lifecycle.MutableLiveData<java.lang.String> r11 = r0.levelOnTitle
            goto L_0x00e8
        L_0x00e7:
            r11 = 0
        L_0x00e8:
            r12 = 3
            r1.updateLiveDataRegistration(r12, r11)
            if (r11 == 0) goto L_0x00f5
            java.lang.Object r11 = r11.getValue()
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x00f6
        L_0x00f5:
            r11 = 0
        L_0x00f6:
            long r12 = r2 & r26
            int r52 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r52 == 0) goto L_0x0127
            if (r0 == 0) goto L_0x0101
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.off
            goto L_0x0102
        L_0x0101:
            r12 = 0
        L_0x0102:
            r13 = 4
            r1.updateLiveDataRegistration(r13, r12)
            if (r12 == 0) goto L_0x010f
            java.lang.Object r12 = r12.getValue()
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            goto L_0x0110
        L_0x010f:
            r12 = 0
        L_0x0110:
            boolean r12 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r12)
            if (r52 == 0) goto L_0x0121
            if (r12 == 0) goto L_0x011c
            r52 = 1073741824(0x40000000, double:5.304989477E-315)
            goto L_0x011f
        L_0x011c:
            r52 = 536870912(0x20000000, double:2.652494739E-315)
        L_0x011f:
            long r2 = r2 | r52
        L_0x0121:
            if (r12 == 0) goto L_0x0124
            goto L_0x0128
        L_0x0124:
            r13 = 8
            goto L_0x0129
        L_0x0127:
            r12 = 0
        L_0x0128:
            r13 = 0
        L_0x0129:
            long r52 = r2 & r22
            int r54 = (r52 > r4 ? 1 : (r52 == r4 ? 0 : -1))
            if (r54 == 0) goto L_0x015e
            if (r0 == 0) goto L_0x0134
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r14 = r0.tmphum
            goto L_0x0135
        L_0x0134:
            r14 = 0
        L_0x0135:
            r15 = 5
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x0142
            java.lang.Object r14 = r14.getValue()
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L_0x0143
        L_0x0142:
            r14 = 0
        L_0x0143:
            boolean r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r14)
            if (r54 == 0) goto L_0x0158
            if (r14 == 0) goto L_0x0151
            r54 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            goto L_0x0156
        L_0x0151:
            r54 = 549755813888(0x8000000000, double:2.716154612436E-312)
        L_0x0156:
            long r2 = r2 | r54
        L_0x0158:
            if (r14 == 0) goto L_0x015b
            goto L_0x015f
        L_0x015b:
            r15 = 8
            goto L_0x0160
        L_0x015e:
            r14 = 0
        L_0x015f:
            r15 = 0
        L_0x0160:
            long r54 = r2 & r34
            int r56 = (r54 > r4 ? 1 : (r54 == r4 ? 0 : -1))
            if (r56 == 0) goto L_0x0179
            if (r0 == 0) goto L_0x016b
            androidx.lifecycle.MutableLiveData<java.lang.String> r9 = r0.name
            goto L_0x016c
        L_0x016b:
            r9 = 0
        L_0x016c:
            r4 = 6
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0179
            java.lang.Object r4 = r9.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x017a
        L_0x0179:
            r4 = 0
        L_0x017a:
            long r57 = r2 & r18
            r55 = 0
            int r5 = (r57 > r55 ? 1 : (r57 == r55 ? 0 : -1))
            if (r5 == 0) goto L_0x01b1
            if (r0 == 0) goto L_0x0189
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.vpdModeVisible
            r57 = r4
            goto L_0x018c
        L_0x0189:
            r57 = r4
            r9 = 0
        L_0x018c:
            r4 = 7
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0199
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x019a
        L_0x0199:
            r4 = 0
        L_0x019a:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x01ab
            if (r4 == 0) goto L_0x01a6
            r58 = 67108864(0x4000000, double:3.31561842E-316)
            goto L_0x01a9
        L_0x01a6:
            r58 = 33554432(0x2000000, double:1.6578092E-316)
        L_0x01a9:
            long r2 = r2 | r58
        L_0x01ab:
            if (r4 == 0) goto L_0x01ae
            goto L_0x01b3
        L_0x01ae:
            r4 = 8
            goto L_0x01b4
        L_0x01b1:
            r57 = r4
        L_0x01b3:
            r4 = 0
        L_0x01b4:
            long r58 = r2 & r16
            r55 = 0
            int r5 = (r58 > r55 ? 1 : (r58 == r55 ? 0 : -1))
            if (r5 == 0) goto L_0x01d0
            if (r0 == 0) goto L_0x01c1
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.levelOnDes
            goto L_0x01c2
        L_0x01c1:
            r5 = 0
        L_0x01c2:
            r9 = 8
            r1.updateLiveDataRegistration(r9, r5)
            if (r5 == 0) goto L_0x01d2
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x01d3
        L_0x01d0:
            r9 = 8
        L_0x01d2:
            r5 = 0
        L_0x01d3:
            long r58 = r2 & r24
            r54 = 0
            int r60 = (r58 > r54 ? 1 : (r58 == r54 ? 0 : -1))
            if (r60 == 0) goto L_0x01f9
            if (r0 == 0) goto L_0x01e2
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r9 = r0.levelOnRes
            r58 = r4
            goto L_0x01e5
        L_0x01e2:
            r58 = r4
            r9 = 0
        L_0x01e5:
            r4 = 9
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x01f3
            java.lang.Object r4 = r9.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x01f4
        L_0x01f3:
            r4 = 0
        L_0x01f4:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x01fc
        L_0x01f9:
            r58 = r4
            r4 = 0
        L_0x01fc:
            r52 = 1573888(0x180400, double:7.77604E-318)
            long r59 = r2 & r52
            r55 = 0
            int r9 = (r59 > r55 ? 1 : (r59 == r55 ? 0 : -1))
            if (r9 == 0) goto L_0x021f
            if (r0 == 0) goto L_0x020e
            androidx.lifecycle.MutableLiveData<java.lang.String> r9 = r0.levelOffTitle
            r59 = r4
            goto L_0x0211
        L_0x020e:
            r59 = r4
            r9 = 0
        L_0x0211:
            r4 = 10
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0221
            java.lang.Object r4 = r9.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0222
        L_0x021f:
            r59 = r4
        L_0x0221:
            r4 = 0
        L_0x0222:
            r48 = 1574912(0x180800, double:7.7811E-318)
            long r60 = r2 & r48
            r55 = 0
            int r9 = (r60 > r55 ? 1 : (r60 == r55 ? 0 : -1))
            r60 = r4
            if (r9 == 0) goto L_0x0263
            if (r0 == 0) goto L_0x0236
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.vpd
            r61 = r5
            goto L_0x0239
        L_0x0236:
            r61 = r5
            r4 = 0
        L_0x0239:
            r5 = 11
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0247
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0248
        L_0x0247:
            r4 = 0
        L_0x0248:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r9 == 0) goto L_0x025d
            if (r4 == 0) goto L_0x0256
            r62 = 274877906944(0x4000000000, double:1.358077306218E-312)
            goto L_0x025b
        L_0x0256:
            r62 = 137438953472(0x2000000000, double:6.7903865311E-313)
        L_0x025b:
            long r2 = r2 | r62
        L_0x025d:
            if (r4 == 0) goto L_0x0260
            goto L_0x0266
        L_0x0260:
            r5 = 8
            goto L_0x0267
        L_0x0263:
            r61 = r5
            r4 = 0
        L_0x0266:
            r5 = 0
        L_0x0267:
            r50 = 1576960(0x181000, double:7.79122E-318)
            long r62 = r2 & r50
            r55 = 0
            int r9 = (r62 > r55 ? 1 : (r62 == r55 ? 0 : -1))
            r62 = r4
            if (r9 == 0) goto L_0x02a8
            if (r0 == 0) goto L_0x027b
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.f215on
            r63 = r5
            goto L_0x027e
        L_0x027b:
            r63 = r5
            r4 = 0
        L_0x027e:
            r5 = 12
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x028c
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x028d
        L_0x028c:
            r4 = 0
        L_0x028d:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r9 == 0) goto L_0x02a2
            if (r4 == 0) goto L_0x029b
            r64 = 17179869184(0x400000000, double:8.4879831639E-314)
            goto L_0x02a0
        L_0x029b:
            r64 = 8589934592(0x200000000, double:4.243991582E-314)
        L_0x02a0:
            long r2 = r2 | r64
        L_0x02a2:
            if (r4 == 0) goto L_0x02a5
            goto L_0x02ab
        L_0x02a5:
            r5 = 8
            goto L_0x02ac
        L_0x02a8:
            r63 = r5
            r4 = 0
        L_0x02ab:
            r5 = 0
        L_0x02ac:
            long r64 = r2 & r28
            r55 = 0
            int r9 = (r64 > r55 ? 1 : (r64 == r55 ? 0 : -1))
            if (r9 == 0) goto L_0x02d2
            if (r0 == 0) goto L_0x02bb
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r9 = r0.levelOffRes
            r64 = r4
            goto L_0x02be
        L_0x02bb:
            r64 = r4
            r9 = 0
        L_0x02be:
            r4 = 13
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x02cc
            java.lang.Object r4 = r9.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x02cd
        L_0x02cc:
            r4 = 0
        L_0x02cd:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x02d5
        L_0x02d2:
            r64 = r4
            r4 = 0
        L_0x02d5:
            r65 = 1589248(0x184000, double:7.85193E-318)
            long r65 = r2 & r65
            r55 = 0
            int r9 = (r65 > r55 ? 1 : (r65 == r55 ? 0 : -1))
            if (r9 == 0) goto L_0x02f8
            if (r0 == 0) goto L_0x02e7
            androidx.lifecycle.MutableLiveData<java.lang.String> r9 = r0.levelOffDes
            r65 = r4
            goto L_0x02ea
        L_0x02e7:
            r65 = r4
            r9 = 0
        L_0x02ea:
            r4 = 14
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x02fa
            java.lang.Object r4 = r9.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x02fb
        L_0x02f8:
            r65 = r4
        L_0x02fa:
            r4 = 0
        L_0x02fb:
            long r66 = r2 & r42
            r55 = 0
            int r9 = (r66 > r55 ? 1 : (r66 == r55 ? 0 : -1))
            r66 = r4
            if (r9 == 0) goto L_0x0338
            if (r0 == 0) goto L_0x030c
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.isCreate
            r67 = r5
            goto L_0x030f
        L_0x030c:
            r67 = r5
            r4 = 0
        L_0x030f:
            r5 = 15
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x031d
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x031e
        L_0x031d:
            r4 = 0
        L_0x031e:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r9 == 0) goto L_0x0333
            if (r4 == 0) goto L_0x032c
            r68 = 4294967296(0x100000000, double:2.121995791E-314)
            goto L_0x0331
        L_0x032c:
            r68 = 2147483648(0x80000000, double:1.0609978955E-314)
        L_0x0331:
            long r2 = r2 | r68
        L_0x0333:
            if (r4 == 0) goto L_0x033a
            r4 = 8
            goto L_0x033b
        L_0x0338:
            r67 = r5
        L_0x033a:
            r4 = 0
        L_0x033b:
            long r68 = r2 & r20
            r55 = 0
            int r5 = (r68 > r55 ? 1 : (r68 == r55 ? 0 : -1))
            if (r5 == 0) goto L_0x0373
            if (r0 == 0) goto L_0x034a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.humVisible
            r68 = r4
            goto L_0x034d
        L_0x034a:
            r68 = r4
            r9 = 0
        L_0x034d:
            r4 = 16
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x035b
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x035c
        L_0x035b:
            r4 = 0
        L_0x035c:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x036d
            if (r4 == 0) goto L_0x0368
            r69 = 268435456(0x10000000, double:1.32624737E-315)
            goto L_0x036b
        L_0x0368:
            r69 = 134217728(0x8000000, double:6.63123685E-316)
        L_0x036b:
            long r2 = r2 | r69
        L_0x036d:
            if (r4 == 0) goto L_0x0370
            goto L_0x0375
        L_0x0370:
            r4 = 8
            goto L_0x0376
        L_0x0373:
            r68 = r4
        L_0x0375:
            r4 = 0
        L_0x0376:
            r44 = 1703936(0x1a0000, double:8.41856E-318)
            long r69 = r2 & r44
            r55 = 0
            int r5 = (r69 > r55 ? 1 : (r69 == r55 ? 0 : -1))
            if (r5 == 0) goto L_0x03b1
            if (r0 == 0) goto L_0x0388
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.timeVisible
            r69 = r4
            goto L_0x038b
        L_0x0388:
            r69 = r4
            r9 = 0
        L_0x038b:
            r4 = 17
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x0399
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x039a
        L_0x0399:
            r4 = 0
        L_0x039a:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x03ab
            if (r4 == 0) goto L_0x03a6
            r70 = 16777216(0x1000000, double:8.289046E-317)
            goto L_0x03a9
        L_0x03a6:
            r70 = 8388608(0x800000, double:4.144523E-317)
        L_0x03a9:
            long r2 = r2 | r70
        L_0x03ab:
            if (r4 == 0) goto L_0x03ae
            goto L_0x03b3
        L_0x03ae:
            r9 = 8
            goto L_0x03b4
        L_0x03b1:
            r69 = r4
        L_0x03b3:
            r9 = 0
        L_0x03b4:
            long r4 = r2 & r38
            r54 = 0
            int r56 = (r4 > r54 ? 1 : (r4 == r54 ? 0 : -1))
            if (r56 == 0) goto L_0x03c3
            if (r0 == 0) goto L_0x03c3
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onDelete
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r5 = r0.nameChanged
            goto L_0x03c5
        L_0x03c3:
            r4 = 0
            r5 = 0
        L_0x03c5:
            r70 = 1835008(0x1c0000, double:9.066144E-318)
            long r70 = r2 & r70
            int r72 = (r70 > r54 ? 1 : (r70 == r54 ? 0 : -1))
            if (r72 == 0) goto L_0x03fa
            if (r0 == 0) goto L_0x03d5
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.nameSuggest
            r70 = r2
            goto L_0x03d8
        L_0x03d5:
            r70 = r2
            r0 = 0
        L_0x03d8:
            r2 = 18
            r1.updateLiveDataRegistration(r2, r0)
            if (r0 == 0) goto L_0x03fc
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r54 = r0
            r74 = r9
            r75 = r11
            r76 = r12
            r77 = r13
            r12 = r14
            r11 = r15
            r73 = r61
            r0 = r63
            r9 = r69
            r2 = r70
            goto L_0x0410
        L_0x03fa:
            r70 = r2
        L_0x03fc:
            r74 = r9
            r75 = r11
            r76 = r12
            r77 = r13
            r12 = r14
            r11 = r15
            r73 = r61
            r0 = r63
            r9 = r69
            r2 = r70
            r54 = 0
        L_0x0410:
            r15 = r5
            r61 = r6
            r14 = r8
            r13 = r10
            r6 = r57
            r10 = r58
            r8 = r59
            r57 = r60
            r63 = r62
            r59 = r64
            r58 = r66
            r60 = r67
            r5 = r4
            r62 = r7
            r7 = r65
            r4 = r68
            goto L_0x0454
        L_0x042d:
            r0 = 0
            r4 = 0
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
            r54 = 0
            r57 = 0
            r58 = 0
            r59 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r63 = 0
            r73 = 0
            r74 = 0
            r75 = 0
            r76 = 0
            r77 = 0
        L_0x0454:
            long r42 = r2 & r42
            r55 = 0
            int r64 = (r42 > r55 ? 1 : (r42 == r55 ? 0 : -1))
            r42 = r0
            if (r64 == 0) goto L_0x0463
            android.widget.RelativeLayout r0 = r1.btnDelete
            r0.setVisibility(r4)
        L_0x0463:
            long r38 = r2 & r38
            int r0 = (r38 > r55 ? 1 : (r38 == r55 ? 0 : -1))
            if (r0 == 0) goto L_0x0474
            android.widget.RelativeLayout r0 = r1.btnDelete
            r4 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r5, r4)
            android.widget.EditText r0 = r1.etEditName
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r0, r15)
        L_0x0474:
            long r4 = r2 & r34
            int r0 = (r4 > r55 ? 1 : (r4 == r55 ? 0 : -1))
            if (r0 == 0) goto L_0x047f
            android.widget.EditText r0 = r1.etEditName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x047f:
            r4 = 1048576(0x100000, double:5.180654E-318)
            long r4 = r4 & r2
            int r0 = (r4 > r55 ? 1 : (r4 == r55 ? 0 : -1))
            if (r0 == 0) goto L_0x04ba
            android.widget.EditText r0 = r1.etEditName
            r4 = 0
            r5 = r4
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r5 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r5
            r6 = r4
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r6 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r6
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r4 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r4
            androidx.databinding.InverseBindingListener r15 = r1.etEditNameandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r5, r6, r4, r15)
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView5
            androidx.databinding.InverseBindingListener r4 = r1.mboundView5selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeCycle
            androidx.databinding.InverseBindingListener r4 = r1.modeCycleselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeOn
            androidx.databinding.InverseBindingListener r4 = r1.modeOnselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeTempHum
            androidx.databinding.InverseBindingListener r4 = r1.modeTempHumselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            androidx.databinding.InverseBindingListener r4 = r1.modeVpdselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r4)
        L_0x04ba:
            long r4 = r2 & r28
            r28 = 0
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x04c7
            android.widget.ImageView r0 = r1.ivLevelOff
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r7)
        L_0x04c7:
            long r4 = r2 & r24
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x04d2
            android.widget.ImageView r0 = r1.ivLevelOn
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r8)
        L_0x04d2:
            long r4 = r2 & r20
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x04e2
            android.widget.LinearLayout r0 = r1.llHumContainer
            r0.setVisibility(r9)
            android.view.View r0 = r1.mboundView12
            r0.setVisibility(r9)
        L_0x04e2:
            long r4 = r2 & r32
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x04ed
            android.widget.LinearLayout r0 = r1.llLevel
            r0.setVisibility(r13)
        L_0x04ed:
            long r4 = r2 & r36
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x04fd
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.llLevelOff
            r0.setVisibility(r14)
            android.view.View r0 = r1.mboundView26
            r0.setVisibility(r14)
        L_0x04fd:
            long r4 = r2 & r22
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x0512
            android.widget.Space r0 = r1.mboundView10
            r0.setVisibility(r11)
            android.widget.Space r0 = r1.mboundView14
            r0.setVisibility(r11)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeTempHum
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r12)
        L_0x0512:
            long r4 = r2 & r18
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x0522
            android.view.View r0 = r1.mboundView15
            r0.setVisibility(r10)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            r0.setVisibility(r10)
        L_0x0522:
            r4 = 1574912(0x180800, double:7.7811E-318)
            long r4 = r4 & r2
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x053d
            android.widget.Space r0 = r1.mboundView16
            r4 = r42
            r0.setVisibility(r4)
            android.widget.Space r0 = r1.mboundView18
            r0.setVisibility(r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            r4 = r63
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x053d:
            long r4 = r2 & r40
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x0551
            android.widget.Space r0 = r1.mboundView19
            r7 = r62
            r0.setVisibility(r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeCycle
            r6 = r61
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r6)
        L_0x0551:
            r4 = 1703936(0x1a0000, double:8.41856E-318)
            long r4 = r4 & r2
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x0565
            android.widget.TextView r0 = r1.mboundView3
            r9 = r74
            r0.setVisibility(r9)
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView4
            r0.setVisibility(r9)
        L_0x0565:
            long r4 = r2 & r26
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x0579
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView5
            r12 = r76
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r12)
            android.widget.Space r0 = r1.mboundView6
            r13 = r77
            r0.setVisibility(r13)
        L_0x0579:
            r4 = 1576960(0x181000, double:7.79122E-318)
            long r4 = r4 & r2
            int r0 = (r4 > r28 ? 1 : (r4 == r28 ? 0 : -1))
            if (r0 == 0) goto L_0x0594
            android.widget.Space r0 = r1.mboundView7
            r4 = r60
            r0.setVisibility(r4)
            android.widget.Space r0 = r1.mboundView9
            r0.setVisibility(r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeOn
            r4 = r59
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0594:
            r4 = 1589248(0x184000, double:7.85193E-318)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x05a5
            android.widget.TextView r0 = r1.tvLevelOffDes
            r4 = r58
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x05a5:
            r4 = 1573888(0x180400, double:7.77604E-318)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x05b4
            android.widget.TextView r0 = r1.tvLevelOffTitle
            r4 = r57
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x05b4:
            long r4 = r2 & r16
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x05c1
            android.widget.TextView r0 = r1.tvLevelOnDes
            r4 = r73
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x05c1:
            long r4 = r2 & r30
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x05ce
            android.widget.TextView r0 = r1.tvLevelOnTitle
            r11 = r75
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r11)
        L_0x05ce:
            r4 = 1835008(0x1c0000, double:9.066144E-318)
            long r2 = r2 & r4
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x05dd
            android.widget.TextView r0 = r1.tvNameSuggest
            r2 = r54
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x05dd:
            return
        L_0x05de:
            r0 = move-exception
            monitor-exit(r78)     // Catch:{ all -> 0x05de }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.notification.databinding.FragmentAutomationBindingImpl.executeBindings():void");
    }
}
