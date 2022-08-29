package com.eternal.notification.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Space;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.notification.C2419BR;
import com.eternal.notification.C2420R;
import com.eternal.notification.model.AutomationModelV4;
import com.eternal.notification.view.TitleSelectView;
import com.eternal.notification.view.TitleSelectViewAdapter;
import com.eternal.widget.ExpandableLayout;

public class FragmentAutomationV4BindingImpl extends FragmentAutomationV4Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener cb1androidCheckedAttrChanged;
    private InverseBindingListener cb2androidCheckedAttrChanged;
    private InverseBindingListener cb3androidCheckedAttrChanged;
    private InverseBindingListener cb4androidCheckedAttrChanged;
    private InverseBindingListener cb5androidCheckedAttrChanged;
    private InverseBindingListener cb6androidCheckedAttrChanged;
    private InverseBindingListener cb7androidCheckedAttrChanged;
    private InverseBindingListener etEditNameandroidTextAttrChanged;
    private long mDirtyFlags;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView14;
    private InverseBindingListener mboundView14selectAttrChange;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView15;
    private InverseBindingListener mboundView15selectAttrChange;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView16;
    private InverseBindingListener mboundView16selectAttrChange;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView17;
    private InverseBindingListener mboundView17selectAttrChange;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView18;
    private InverseBindingListener mboundView18selectAttrChange;
    private final Space mboundView19;
    private final Space mboundView20;
    private final Space mboundView22;
    private final Space mboundView23;
    private final View mboundView25;
    private final Space mboundView27;
    private final View mboundView28;
    private final Space mboundView29;
    private final TextView mboundView3;
    private final Space mboundView31;
    private final Space mboundView32;
    private final View mboundView39;
    private final ExpandableLayout mboundView5;
    private final ConstraintLayout mboundView6;
    private InverseBindingListener modeCycleselectAttrChange;
    private InverseBindingListener modeOnselectAttrChange;
    private InverseBindingListener modeTempHumselectAttrChange;
    private InverseBindingListener modeVpdselectAttrChange;
    private InverseBindingListener sbandroidCheckedAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2420R.C2423id.tv_start_time_des, 44);
        sparseIntArray.put(C2420R.C2423id.ll_start_time, 45);
        sparseIntArray.put(C2420R.C2423id.wv_start_hour, 46);
        sparseIntArray.put(C2420R.C2423id.wv_start_min, 47);
        sparseIntArray.put(C2420R.C2423id.wv_start_am_or_pm, 48);
        sparseIntArray.put(C2420R.C2423id.v_time_line, 49);
        sparseIntArray.put(C2420R.C2423id.tv_end_time_des, 50);
        sparseIntArray.put(C2420R.C2423id.ll_end_time, 51);
        sparseIntArray.put(C2420R.C2423id.wv_end_hour, 52);
        sparseIntArray.put(C2420R.C2423id.wv_end_min, 53);
        sparseIntArray.put(C2420R.C2423id.wv_end_am_or_pm, 54);
        sparseIntArray.put(C2420R.C2423id.el_on, 55);
        sparseIntArray.put(C2420R.C2423id.ll_on_container, 56);
        sparseIntArray.put(C2420R.C2423id.el_tmp_hum, 57);
        sparseIntArray.put(C2420R.C2423id.ll_tmphum_container, 58);
        sparseIntArray.put(C2420R.C2423id.tv_temp_min, 59);
        sparseIntArray.put(C2420R.C2423id.rsb_temp, 60);
        sparseIntArray.put(C2420R.C2423id.tv_temp_max, 61);
        sparseIntArray.put(C2420R.C2423id.layout_tmp, 62);
        sparseIntArray.put(C2420R.C2423id.sb_high_tmp, 63);
        sparseIntArray.put(C2420R.C2423id.sb_low_tmp, 64);
        sparseIntArray.put(C2420R.C2423id.tv_hum_min, 65);
        sparseIntArray.put(C2420R.C2423id.rsb_hum, 66);
        sparseIntArray.put(C2420R.C2423id.layout_hum, 67);
        sparseIntArray.put(C2420R.C2423id.sb_high_hum, 68);
        sparseIntArray.put(C2420R.C2423id.sb_low_hum, 69);
        sparseIntArray.put(C2420R.C2423id.el_vpd, 70);
        sparseIntArray.put(C2420R.C2423id.ll_vpd_container, 71);
        sparseIntArray.put(C2420R.C2423id.tv_vpd_min, 72);
        sparseIntArray.put(C2420R.C2423id.rsb_vpd, 73);
        sparseIntArray.put(C2420R.C2423id.layout_vpd, 74);
        sparseIntArray.put(C2420R.C2423id.sb_high_vpd, 75);
        sparseIntArray.put(C2420R.C2423id.sb_low_vpd, 76);
        sparseIntArray.put(C2420R.C2423id.el_cycle, 77);
        sparseIntArray.put(C2420R.C2423id.ll_cycle_container, 78);
        sparseIntArray.put(C2420R.C2423id.tv_cycle_on_des, 79);
        sparseIntArray.put(C2420R.C2423id.ll_cycle_on, 80);
        sparseIntArray.put(C2420R.C2423id.wv_cycle_on_hour, 81);
        sparseIntArray.put(C2420R.C2423id.wv_cycle_on_min, 82);
        sparseIntArray.put(C2420R.C2423id.v_cycle_line, 83);
        sparseIntArray.put(C2420R.C2423id.tv_cycle_off_des, 84);
        sparseIntArray.put(C2420R.C2423id.ll_cycle_off_time, 85);
        sparseIntArray.put(C2420R.C2423id.wv_cycle_off_hour, 86);
        sparseIntArray.put(C2420R.C2423id.wv_cycle_off_min, 87);
        sparseIntArray.put(C2420R.C2423id.fpb_off, 88);
        sparseIntArray.put(C2420R.C2423id.ll_level_on, 89);
        sparseIntArray.put(C2420R.C2423id.fpb_on, 90);
    }

    public FragmentAutomationV4BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 91, sIncludes, sViewsWithIds));
    }

    private FragmentAutomationV4BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 31, objArr[43], objArr[8], objArr[9], objArr[10], objArr[11], objArr[12], objArr[13], objArr[7], objArr[77], objArr[55], objArr[57], objArr[70], objArr[2], objArr[88], objArr[90], objArr[38], objArr[42], objArr[67], objArr[62], objArr[74], objArr[0], objArr[78], objArr[85], objArr[80], objArr[51], objArr[26], objArr[34], objArr[35], objArr[89], objArr[56], objArr[45], objArr[58], objArr[71], objArr[33], objArr[21], objArr[24], objArr[30], objArr[66], objArr[60], objArr[73], objArr[4], objArr[68], objArr[63], objArr[75], objArr[69], objArr[64], objArr[76], objArr[84], objArr[79], objArr[50], objArr[65], objArr[37], objArr[36], objArr[41], objArr[40], objArr[1], objArr[44], objArr[61], objArr[59], objArr[72], objArr[83], objArr[49], objArr[86], objArr[87], objArr[81], objArr[82], objArr[54], objArr[52], objArr[53], objArr[48], objArr[46], objArr[47]);
        this.cb1androidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = FragmentAutomationV4BindingImpl.this.cb1.isChecked();
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.day1;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.cb2androidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = FragmentAutomationV4BindingImpl.this.cb2.isChecked();
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.day2;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.cb3androidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = FragmentAutomationV4BindingImpl.this.cb3.isChecked();
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.day3;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.cb4androidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = FragmentAutomationV4BindingImpl.this.cb4.isChecked();
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.day4;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.cb5androidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = FragmentAutomationV4BindingImpl.this.cb5.isChecked();
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.day5;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.cb6androidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = FragmentAutomationV4BindingImpl.this.cb6.isChecked();
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.day6;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.cb7androidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = FragmentAutomationV4BindingImpl.this.cb7.isChecked();
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.day7;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(isChecked));
                    }
                }
            }
        };
        this.etEditNameandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentAutomationV4BindingImpl.this.etEditName);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<String> mutableLiveData = automationModelV4.name;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.mboundView14selectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.mboundView14);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.port1;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.mboundView15selectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.mboundView15);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.port2;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.mboundView16selectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.mboundView16);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.port3;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.mboundView17selectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.mboundView17);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.port4;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.mboundView18selectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.mboundView18);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.off;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.modeCycle);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.cycle;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.modeOn);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.f216on;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.modeTempHum);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.tmphum;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentAutomationV4BindingImpl.this.modeVpd);
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = automationModelV4.vpd;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.sbandroidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = FragmentAutomationV4BindingImpl.this.f214sb.isChecked();
                AutomationModelV4 automationModelV4 = FragmentAutomationV4BindingImpl.this.mAutoModelV4;
                boolean z = true;
                if (automationModelV4 != null) {
                    ObservableBoolean observableBoolean = automationModelV4.allDay;
                    if (observableBoolean == null) {
                        z = false;
                    }
                    if (z) {
                        observableBoolean.set(isChecked);
                    }
                }
            }
        };
        this.mDirtyFlags = -1;
        this.btnDelete.setTag((Object) null);
        this.cb1.setTag((Object) null);
        this.cb2.setTag((Object) null);
        this.cb3.setTag((Object) null);
        this.cb4.setTag((Object) null);
        this.cb5.setTag((Object) null);
        this.cb6.setTag((Object) null);
        this.cb7.setTag((Object) null);
        this.etEditName.setTag((Object) null);
        this.ivLevelOff.setTag((Object) null);
        this.ivLevelOn.setTag((Object) null);
        this.llContent.setTag((Object) null);
        this.llHumContainer.setTag((Object) null);
        this.llLevel.setTag((Object) null);
        this.llLevelOff.setTag((Object) null);
        TitleSelectView titleSelectView = objArr[14];
        this.mboundView14 = titleSelectView;
        titleSelectView.setTag((Object) null);
        TitleSelectView titleSelectView2 = objArr[15];
        this.mboundView15 = titleSelectView2;
        titleSelectView2.setTag((Object) null);
        TitleSelectView titleSelectView3 = objArr[16];
        this.mboundView16 = titleSelectView3;
        titleSelectView3.setTag((Object) null);
        TitleSelectView titleSelectView4 = objArr[17];
        this.mboundView17 = titleSelectView4;
        titleSelectView4.setTag((Object) null);
        TitleSelectView titleSelectView5 = objArr[18];
        this.mboundView18 = titleSelectView5;
        titleSelectView5.setTag((Object) null);
        Space space = objArr[19];
        this.mboundView19 = space;
        space.setTag((Object) null);
        Space space2 = objArr[20];
        this.mboundView20 = space2;
        space2.setTag((Object) null);
        Space space3 = objArr[22];
        this.mboundView22 = space3;
        space3.setTag((Object) null);
        Space space4 = objArr[23];
        this.mboundView23 = space4;
        space4.setTag((Object) null);
        View view2 = objArr[25];
        this.mboundView25 = view2;
        view2.setTag((Object) null);
        Space space5 = objArr[27];
        this.mboundView27 = space5;
        space5.setTag((Object) null);
        View view3 = objArr[28];
        this.mboundView28 = view3;
        view3.setTag((Object) null);
        Space space6 = objArr[29];
        this.mboundView29 = space6;
        space6.setTag((Object) null);
        TextView textView = objArr[3];
        this.mboundView3 = textView;
        textView.setTag((Object) null);
        Space space7 = objArr[31];
        this.mboundView31 = space7;
        space7.setTag((Object) null);
        Space space8 = objArr[32];
        this.mboundView32 = space8;
        space8.setTag((Object) null);
        View view4 = objArr[39];
        this.mboundView39 = view4;
        view4.setTag((Object) null);
        ExpandableLayout expandableLayout = objArr[5];
        this.mboundView5 = expandableLayout;
        expandableLayout.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[6];
        this.mboundView6 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.modeCycle.setTag((Object) null);
        this.modeOn.setTag((Object) null);
        this.modeTempHum.setTag((Object) null);
        this.modeVpd.setTag((Object) null);
        this.f214sb.setTag("switch");
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
            this.mDirtyFlags = 4294967296L;
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
        if (C2419BR.autoModelV4 != i) {
            return false;
        }
        setAutoModelV4((AutomationModelV4) obj);
        return true;
    }

    public void setAutoModelV4(AutomationModelV4 automationModelV4) {
        this.mAutoModelV4 = automationModelV4;
        synchronized (this) {
            this.mDirtyFlags |= 2147483648L;
        }
        notifyPropertyChanged(C2419BR.autoModelV4);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeAutoModelV4Vpd((MutableLiveData) obj, i2);
            case 1:
                return onChangeAutoModelV4Day3((MutableLiveData) obj, i2);
            case 2:
                return onChangeAutoModelV4LevelOnRes((MutableLiveData) obj, i2);
            case 3:
                return onChangeAutoModelV4LevelOnTitle((MutableLiveData) obj, i2);
            case 4:
                return onChangeAutoModelV4Off((MutableLiveData) obj, i2);
            case 5:
                return onChangeAutoModelV4Day2((MutableLiveData) obj, i2);
            case 6:
                return onChangeAutoModelV4Tmphum((MutableLiveData) obj, i2);
            case 7:
                return onChangeAutoModelV4Day5((MutableLiveData) obj, i2);
            case 8:
                return onChangeAutoModelV4HumVisible((MutableLiveData) obj, i2);
            case 9:
                return onChangeAutoModelV4Day4((MutableLiveData) obj, i2);
            case 10:
                return onChangeAutoModelV4LevelVisible((MutableLiveData) obj, i2);
            case 11:
                return onChangeAutoModelV4NameSuggest((MutableLiveData) obj, i2);
            case 12:
                return onChangeAutoModelV4AllDay((ObservableBoolean) obj, i2);
            case 13:
                return onChangeAutoModelV4LevelOffVisible((MutableLiveData) obj, i2);
            case 14:
                return onChangeAutoModelV4Port4((MutableLiveData) obj, i2);
            case 15:
                return onChangeAutoModelV4On((MutableLiveData) obj, i2);
            case 16:
                return onChangeAutoModelV4LevelOffRes((MutableLiveData) obj, i2);
            case 17:
                return onChangeAutoModelV4TimeVisible((MutableLiveData) obj, i2);
            case 18:
                return onChangeAutoModelV4Day7((MutableLiveData) obj, i2);
            case 19:
                return onChangeAutoModelV4Day6((MutableLiveData) obj, i2);
            case 20:
                return onChangeAutoModelV4Cycle((MutableLiveData) obj, i2);
            case 21:
                return onChangeAutoModelV4LevelOffDes((MutableLiveData) obj, i2);
            case 22:
                return onChangeAutoModelV4Port1((MutableLiveData) obj, i2);
            case 23:
                return onChangeAutoModelV4LevelOnDes((MutableLiveData) obj, i2);
            case 24:
                return onChangeAutoModelV4Name((MutableLiveData) obj, i2);
            case 25:
                return onChangeAutoModelV4Day1((MutableLiveData) obj, i2);
            case 26:
                return onChangeAutoModelV4Port2((MutableLiveData) obj, i2);
            case 27:
                return onChangeAutoModelV4LevelOffTitle((MutableLiveData) obj, i2);
            case 28:
                return onChangeAutoModelV4VpdModeVisible((MutableLiveData) obj, i2);
            case 29:
                return onChangeAutoModelV4Port3((MutableLiveData) obj, i2);
            case 30:
                return onChangeAutoModelV4IsCreate((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeAutoModelV4Vpd(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Day3(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeAutoModelV4LevelOnRes(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeAutoModelV4LevelOnTitle(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Off(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Day2(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Tmphum(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Day5(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeAutoModelV4HumVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Day4(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeAutoModelV4LevelVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeAutoModelV4NameSuggest(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeAutoModelV4AllDay(ObservableBoolean observableBoolean, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeAutoModelV4LevelOffVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Port4(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    private boolean onChangeAutoModelV4On(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeAutoModelV4LevelOffRes(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeAutoModelV4TimeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Day7(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Day6(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Cycle(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        return true;
    }

    private boolean onChangeAutoModelV4LevelOffDes(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Port1(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4194304;
        }
        return true;
    }

    private boolean onChangeAutoModelV4LevelOnDes(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8388608;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Name(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16777216;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Day1(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 33554432;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Port2(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 67108864;
        }
        return true;
    }

    private boolean onChangeAutoModelV4LevelOffTitle(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 134217728;
        }
        return true;
    }

    private boolean onChangeAutoModelV4VpdModeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 268435456;
        }
        return true;
    }

    private boolean onChangeAutoModelV4Port3(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 536870912;
        }
        return true;
    }

    private boolean onChangeAutoModelV4IsCreate(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1073741824;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0187  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x022d  */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0271  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x0293  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x02ad  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x02c0  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x02ee  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x02fe  */
    /* JADX WARNING: Removed duplicated region for block: B:220:0x031c  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x032e  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0361  */
    /* JADX WARNING: Removed duplicated region for block: B:242:0x0372  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0390  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x03a2  */
    /* JADX WARNING: Removed duplicated region for block: B:268:0x03d5  */
    /* JADX WARNING: Removed duplicated region for block: B:272:0x03e5  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x03ff  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x0408  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x0426  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x0465  */
    /* JADX WARNING: Removed duplicated region for block: B:313:0x0476  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x048e  */
    /* JADX WARNING: Removed duplicated region for block: B:323:0x049e  */
    /* JADX WARNING: Removed duplicated region for block: B:331:0x04bc  */
    /* JADX WARNING: Removed duplicated region for block: B:334:0x04cc  */
    /* JADX WARNING: Removed duplicated region for block: B:340:0x04e4  */
    /* JADX WARNING: Removed duplicated region for block: B:344:0x04f4  */
    /* JADX WARNING: Removed duplicated region for block: B:350:0x050c  */
    /* JADX WARNING: Removed duplicated region for block: B:354:0x0517  */
    /* JADX WARNING: Removed duplicated region for block: B:362:0x0535  */
    /* JADX WARNING: Removed duplicated region for block: B:365:0x0545  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x0563  */
    /* JADX WARNING: Removed duplicated region for block: B:376:0x0573  */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x058b  */
    /* JADX WARNING: Removed duplicated region for block: B:386:0x059d  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:401:0x05ca  */
    /* JADX WARNING: Removed duplicated region for block: B:405:0x05d9  */
    /* JADX WARNING: Removed duplicated region for block: B:413:0x05f3  */
    /* JADX WARNING: Removed duplicated region for block: B:416:0x05fc  */
    /* JADX WARNING: Removed duplicated region for block: B:433:0x0673  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0123 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0154  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r104 = this;
            r1 = r104
            monitor-enter(r104)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x09d0 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x09d0 }
            monitor-exit(r104)     // Catch:{ all -> 0x09d0 }
            com.eternal.notification.model.AutomationModelV4 r0 = r1.mAutoModelV4
            r6 = 8589934591(0x1ffffffff, double:4.2439915814E-314)
            long r6 = r6 & r2
            r16 = 6442975232(0x180080000, double:3.183252719E-314)
            r18 = 6442451008(0x180000040, double:3.182993718E-314)
            r20 = 6442451072(0x180000080, double:3.1829937497E-314)
            r22 = 6442451456(0x180000200, double:3.1829939394E-314)
            r24 = 6442450960(0x180000010, double:3.1829936944E-314)
            r26 = 6442450976(0x180000020, double:3.1829937023E-314)
            r28 = 6442450952(0x180000008, double:3.1829936904E-314)
            r30 = 6442450948(0x180000004, double:3.1829936884E-314)
            r32 = 6476005376(0x182000000, double:3.1995717786E-314)
            r34 = 6442450946(0x180000002, double:3.1829936874E-314)
            r36 = 6442450944(0x180000000, double:3.1829936864E-314)
            r38 = 6442450945(0x180000001, double:3.182993687E-314)
            r40 = 7516192768(0x1c0000000, double:3.713492634E-314)
            r10 = 0
            int r44 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r44 == 0) goto L_0x06bf
            long r6 = r2 & r38
            int r45 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r45 == 0) goto L_0x008c
            if (r0 == 0) goto L_0x0063
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.vpd
            goto L_0x0064
        L_0x0063:
            r6 = 0
        L_0x0064:
            r1.updateLiveDataRegistration(r10, r6)
            if (r6 == 0) goto L_0x0070
            java.lang.Object r6 = r6.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            goto L_0x0071
        L_0x0070:
            r6 = 0
        L_0x0071:
            boolean r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r6)
            if (r45 == 0) goto L_0x0086
            if (r6 == 0) goto L_0x007f
            r45 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            goto L_0x0084
        L_0x007f:
            r45 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
        L_0x0084:
            long r2 = r2 | r45
        L_0x0086:
            if (r6 == 0) goto L_0x0089
            goto L_0x008d
        L_0x0089:
            r7 = 8
            goto L_0x008e
        L_0x008c:
            r6 = 0
        L_0x008d:
            r7 = 0
        L_0x008e:
            long r45 = r2 & r34
            int r47 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r47 == 0) goto L_0x00ad
            if (r0 == 0) goto L_0x0099
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.day3
            goto L_0x009a
        L_0x0099:
            r10 = 0
        L_0x009a:
            r14 = 1
            r1.updateLiveDataRegistration(r14, r10)
            if (r10 == 0) goto L_0x00a7
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x00a8
        L_0x00a7:
            r10 = 0
        L_0x00a8:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            goto L_0x00ae
        L_0x00ad:
            r10 = 0
        L_0x00ae:
            long r14 = r2 & r30
            int r48 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r48 == 0) goto L_0x00cd
            if (r0 == 0) goto L_0x00b9
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r14 = r0.levelOnRes
            goto L_0x00ba
        L_0x00b9:
            r14 = 0
        L_0x00ba:
            r15 = 2
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x00c7
            java.lang.Object r14 = r14.getValue()
            java.lang.Integer r14 = (java.lang.Integer) r14
            goto L_0x00c8
        L_0x00c7:
            r14 = 0
        L_0x00c8:
            int r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r14)
            goto L_0x00ce
        L_0x00cd:
            r14 = 0
        L_0x00ce:
            long r48 = r2 & r28
            int r15 = (r48 > r4 ? 1 : (r48 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00e7
            if (r0 == 0) goto L_0x00d9
            androidx.lifecycle.MutableLiveData<java.lang.String> r15 = r0.levelOnTitle
            goto L_0x00da
        L_0x00d9:
            r15 = 0
        L_0x00da:
            r8 = 3
            r1.updateLiveDataRegistration(r8, r15)
            if (r15 == 0) goto L_0x00e7
            java.lang.Object r8 = r15.getValue()
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x00e8
        L_0x00e7:
            r8 = 0
        L_0x00e8:
            long r50 = r2 & r24
            int r9 = (r50 > r4 ? 1 : (r50 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x011b
            if (r0 == 0) goto L_0x00f3
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.off
            goto L_0x00f4
        L_0x00f3:
            r15 = 0
        L_0x00f4:
            r11 = 4
            r1.updateLiveDataRegistration(r11, r15)
            if (r15 == 0) goto L_0x0101
            java.lang.Object r11 = r15.getValue()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            goto L_0x0102
        L_0x0101:
            r11 = 0
        L_0x0102:
            boolean r11 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r11)
            if (r9 == 0) goto L_0x0114
            if (r11 == 0) goto L_0x010d
            r51 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            goto L_0x0112
        L_0x010d:
            r51 = 140737488355328(0x800000000000, double:6.953355807835E-310)
        L_0x0112:
            long r2 = r2 | r51
        L_0x0114:
            if (r11 == 0) goto L_0x0118
            r9 = 0
            goto L_0x011d
        L_0x0118:
            r9 = 8
            goto L_0x011d
        L_0x011b:
            r9 = 0
            r11 = 0
        L_0x011d:
            long r51 = r2 & r36
            int r15 = (r51 > r4 ? 1 : (r51 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x012a
            if (r0 == 0) goto L_0x012a
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onDelete
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r12 = r0.nameChanged
            goto L_0x012c
        L_0x012a:
            r12 = 0
            r15 = 0
        L_0x012c:
            long r53 = r2 & r26
            int r13 = (r53 > r4 ? 1 : (r53 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x014b
            if (r0 == 0) goto L_0x0137
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.day2
            goto L_0x0138
        L_0x0137:
            r13 = 0
        L_0x0138:
            r4 = 5
            r1.updateLiveDataRegistration(r4, r13)
            if (r13 == 0) goto L_0x0145
            java.lang.Object r4 = r13.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0146
        L_0x0145:
            r4 = 0
        L_0x0146:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x014c
        L_0x014b:
            r4 = 0
        L_0x014c:
            long r55 = r2 & r18
            r53 = 0
            int r5 = (r55 > r53 ? 1 : (r55 == r53 ? 0 : -1))
            if (r5 == 0) goto L_0x0187
            if (r0 == 0) goto L_0x015b
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.tmphum
            r55 = r4
            goto L_0x015e
        L_0x015b:
            r55 = r4
            r13 = 0
        L_0x015e:
            r4 = 6
            r1.updateLiveDataRegistration(r4, r13)
            if (r13 == 0) goto L_0x016b
            java.lang.Object r4 = r13.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x016c
        L_0x016b:
            r4 = 0
        L_0x016c:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x0181
            if (r4 == 0) goto L_0x017a
            r56 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            goto L_0x017f
        L_0x017a:
            r56 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
        L_0x017f:
            long r2 = r2 | r56
        L_0x0181:
            if (r4 == 0) goto L_0x0184
            goto L_0x018a
        L_0x0184:
            r5 = 8
            goto L_0x018b
        L_0x0187:
            r55 = r4
            r4 = 0
        L_0x018a:
            r5 = 0
        L_0x018b:
            long r56 = r2 & r20
            r53 = 0
            int r13 = (r56 > r53 ? 1 : (r56 == r53 ? 0 : -1))
            if (r13 == 0) goto L_0x01b0
            if (r0 == 0) goto L_0x019a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.day5
            r56 = r4
            goto L_0x019d
        L_0x019a:
            r56 = r4
            r13 = 0
        L_0x019d:
            r4 = 7
            r1.updateLiveDataRegistration(r4, r13)
            if (r13 == 0) goto L_0x01aa
            java.lang.Object r4 = r13.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01ab
        L_0x01aa:
            r4 = 0
        L_0x01ab:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x01b3
        L_0x01b0:
            r56 = r4
            r4 = 0
        L_0x01b3:
            r51 = 6442451200(0x180000100, double:3.182993813E-314)
            long r57 = r2 & r51
            r53 = 0
            int r13 = (r57 > r53 ? 1 : (r57 == r53 ? 0 : -1))
            r57 = r4
            if (r13 == 0) goto L_0x01f6
            if (r0 == 0) goto L_0x01c9
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.humVisible
            r50 = r5
            goto L_0x01cc
        L_0x01c9:
            r50 = r5
            r4 = 0
        L_0x01cc:
            r5 = 8
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x01da
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01db
        L_0x01da:
            r4 = 0
        L_0x01db:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r13 == 0) goto L_0x01f0
            if (r4 == 0) goto L_0x01e9
            r58 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
            goto L_0x01ee
        L_0x01e9:
            r58 = 8796093022208(0x80000000000, double:4.345847379897E-311)
        L_0x01ee:
            long r2 = r2 | r58
        L_0x01f0:
            if (r4 == 0) goto L_0x01f3
            goto L_0x01fa
        L_0x01f3:
            r4 = 8
            goto L_0x01fb
        L_0x01f6:
            r50 = r5
            r5 = 8
        L_0x01fa:
            r4 = 0
        L_0x01fb:
            long r58 = r2 & r22
            r53 = 0
            int r13 = (r58 > r53 ? 1 : (r58 == r53 ? 0 : -1))
            if (r13 == 0) goto L_0x021d
            if (r0 == 0) goto L_0x0208
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.day4
            goto L_0x0209
        L_0x0208:
            r13 = 0
        L_0x0209:
            r5 = 9
            r1.updateLiveDataRegistration(r5, r13)
            if (r13 == 0) goto L_0x0217
            java.lang.Object r5 = r13.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x0218
        L_0x0217:
            r5 = 0
        L_0x0218:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            goto L_0x021e
        L_0x021d:
            r5 = 0
        L_0x021e:
            r59 = 6442451968(0x180000400, double:3.1829941924E-314)
            long r59 = r2 & r59
            r53 = 0
            int r13 = (r59 > r53 ? 1 : (r59 == r53 ? 0 : -1))
            r59 = r4
            if (r13 == 0) goto L_0x0261
            if (r0 == 0) goto L_0x0234
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.levelVisible
            r60 = r5
            goto L_0x0237
        L_0x0234:
            r60 = r5
            r4 = 0
        L_0x0237:
            r5 = 10
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0245
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0246
        L_0x0245:
            r4 = 0
        L_0x0246:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r13 == 0) goto L_0x025b
            if (r4 == 0) goto L_0x0254
            r61 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            goto L_0x0259
        L_0x0254:
            r61 = 549755813888(0x8000000000, double:2.716154612436E-312)
        L_0x0259:
            long r2 = r2 | r61
        L_0x025b:
            if (r4 == 0) goto L_0x025e
            goto L_0x0263
        L_0x025e:
            r4 = 8
            goto L_0x0264
        L_0x0261:
            r60 = r5
        L_0x0263:
            r4 = 0
        L_0x0264:
            r61 = 6442452992(0x180000800, double:3.1829946983E-314)
            long r61 = r2 & r61
            r53 = 0
            int r5 = (r61 > r53 ? 1 : (r61 == r53 ? 0 : -1))
            if (r5 == 0) goto L_0x0285
            if (r0 == 0) goto L_0x0276
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.nameSuggest
            goto L_0x0277
        L_0x0276:
            r5 = 0
        L_0x0277:
            r13 = 11
            r1.updateLiveDataRegistration(r13, r5)
            if (r5 == 0) goto L_0x0285
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0286
        L_0x0285:
            r5 = 0
        L_0x0286:
            r61 = 6442455040(0x180001000, double:3.18299571E-314)
            long r61 = r2 & r61
            r53 = 0
            int r13 = (r61 > r53 ? 1 : (r61 == r53 ? 0 : -1))
            if (r13 == 0) goto L_0x02ad
            if (r0 == 0) goto L_0x029a
            androidx.databinding.ObservableBoolean r13 = r0.allDay
            r61 = r4
            goto L_0x029d
        L_0x029a:
            r61 = r4
            r13 = 0
        L_0x029d:
            r4 = 12
            r1.updateRegistration((int) r4, (androidx.databinding.Observable) r13)
            if (r13 == 0) goto L_0x02a9
            boolean r4 = r13.get()
            goto L_0x02aa
        L_0x02a9:
            r4 = 0
        L_0x02aa:
            r13 = r4 ^ 1
            goto L_0x02b1
        L_0x02ad:
            r61 = r4
            r4 = 0
            r13 = 0
        L_0x02b1:
            r62 = 6442459136(0x180002000, double:3.182997734E-314)
            long r62 = r2 & r62
            r53 = 0
            int r64 = (r62 > r53 ? 1 : (r62 == r53 ? 0 : -1))
            r62 = r4
            if (r64 == 0) goto L_0x02ee
            if (r0 == 0) goto L_0x02c7
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.levelOffVisible
            r63 = r5
            goto L_0x02ca
        L_0x02c7:
            r63 = r5
            r4 = 0
        L_0x02ca:
            r5 = 13
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x02d8
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x02d9
        L_0x02d8:
            r4 = 0
        L_0x02d9:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r64 == 0) goto L_0x02e8
            if (r4 == 0) goto L_0x02e4
            r64 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
            goto L_0x02e6
        L_0x02e4:
            r64 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
        L_0x02e6:
            long r2 = r2 | r64
        L_0x02e8:
            if (r4 == 0) goto L_0x02eb
            goto L_0x02f0
        L_0x02eb:
            r4 = 8
            goto L_0x02f1
        L_0x02ee:
            r63 = r5
        L_0x02f0:
            r4 = 0
        L_0x02f1:
            r64 = 6442467328(0x180004000, double:3.183001781E-314)
            long r64 = r2 & r64
            r53 = 0
            int r5 = (r64 > r53 ? 1 : (r64 == r53 ? 0 : -1))
            if (r5 == 0) goto L_0x031c
            if (r0 == 0) goto L_0x0305
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.port4
            r64 = r4
            goto L_0x0308
        L_0x0305:
            r64 = r4
            r5 = 0
        L_0x0308:
            r4 = 14
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x0316
            java.lang.Object r4 = r5.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0317
        L_0x0316:
            r4 = 0
        L_0x0317:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x031f
        L_0x031c:
            r64 = r4
            r4 = 0
        L_0x031f:
            r65 = 6442483712(0x180008000, double:3.183009876E-314)
            long r65 = r2 & r65
            r53 = 0
            int r5 = (r65 > r53 ? 1 : (r65 == r53 ? 0 : -1))
            r65 = r4
            if (r5 == 0) goto L_0x0361
            if (r0 == 0) goto L_0x0335
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.f216on
            r66 = r6
            goto L_0x0338
        L_0x0335:
            r66 = r6
            r4 = 0
        L_0x0338:
            r6 = 15
            r1.updateLiveDataRegistration(r6, r4)
            if (r4 == 0) goto L_0x0346
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0347
        L_0x0346:
            r4 = 0
        L_0x0347:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x035b
            if (r4 == 0) goto L_0x0355
            r5 = 68719476736(0x1000000000, double:3.39519326554E-313)
            goto L_0x035a
        L_0x0355:
            r5 = 34359738368(0x800000000, double:1.69759663277E-313)
        L_0x035a:
            long r2 = r2 | r5
        L_0x035b:
            if (r4 == 0) goto L_0x035e
            goto L_0x0364
        L_0x035e:
            r5 = 8
            goto L_0x0365
        L_0x0361:
            r66 = r6
            r4 = 0
        L_0x0364:
            r5 = 0
        L_0x0365:
            r48 = 6442516480(0x180010000, double:3.1830260655E-314)
            long r67 = r2 & r48
            r53 = 0
            int r6 = (r67 > r53 ? 1 : (r67 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x0390
            if (r0 == 0) goto L_0x0379
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r6 = r0.levelOffRes
            r67 = r4
            goto L_0x037c
        L_0x0379:
            r67 = r4
            r6 = 0
        L_0x037c:
            r4 = 16
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x038a
            java.lang.Object r4 = r6.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x038b
        L_0x038a:
            r4 = 0
        L_0x038b:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x0393
        L_0x0390:
            r67 = r4
            r4 = 0
        L_0x0393:
            r68 = 6442582016(0x180020000, double:3.1830584446E-314)
            long r68 = r2 & r68
            r53 = 0
            int r6 = (r68 > r53 ? 1 : (r68 == r53 ? 0 : -1))
            r68 = r4
            if (r6 == 0) goto L_0x03d5
            if (r0 == 0) goto L_0x03a9
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.timeVisible
            r69 = r5
            goto L_0x03ac
        L_0x03a9:
            r69 = r5
            r4 = 0
        L_0x03ac:
            r5 = 17
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x03ba
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x03bb
        L_0x03ba:
            r4 = 0
        L_0x03bb:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x03cf
            if (r4 == 0) goto L_0x03c9
            r5 = 17179869184(0x400000000, double:8.4879831639E-314)
            goto L_0x03ce
        L_0x03c9:
            r5 = 8589934592(0x200000000, double:4.243991582E-314)
        L_0x03ce:
            long r2 = r2 | r5
        L_0x03cf:
            if (r4 == 0) goto L_0x03d2
            goto L_0x03d7
        L_0x03d2:
            r4 = 8
            goto L_0x03d8
        L_0x03d5:
            r69 = r5
        L_0x03d7:
            r4 = 0
        L_0x03d8:
            r5 = 6442713088(0x180040000, double:3.183123203E-314)
            long r70 = r2 & r5
            r5 = 0
            int r72 = (r70 > r5 ? 1 : (r70 == r5 ? 0 : -1))
            if (r72 == 0) goto L_0x03ff
            if (r0 == 0) goto L_0x03ea
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.day7
            goto L_0x03eb
        L_0x03ea:
            r5 = 0
        L_0x03eb:
            r6 = 18
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x03f9
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x03fa
        L_0x03f9:
            r5 = 0
        L_0x03fa:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            goto L_0x0400
        L_0x03ff:
            r5 = 0
        L_0x0400:
            long r70 = r2 & r16
            r53 = 0
            int r6 = (r70 > r53 ? 1 : (r70 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x0426
            if (r0 == 0) goto L_0x040f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.day6
            r70 = r4
            goto L_0x0412
        L_0x040f:
            r70 = r4
            r6 = 0
        L_0x0412:
            r4 = 19
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x0420
            java.lang.Object r4 = r6.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0421
        L_0x0420:
            r4 = 0
        L_0x0421:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x0429
        L_0x0426:
            r70 = r4
            r4 = 0
        L_0x0429:
            r71 = 6443499520(0x180100000, double:3.183511752E-314)
            long r71 = r2 & r71
            r53 = 0
            int r6 = (r71 > r53 ? 1 : (r71 == r53 ? 0 : -1))
            r71 = r4
            if (r6 == 0) goto L_0x0465
            if (r0 == 0) goto L_0x043f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.cycle
            r72 = r5
            goto L_0x0442
        L_0x043f:
            r72 = r5
            r4 = 0
        L_0x0442:
            r5 = 20
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0450
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0451
        L_0x0450:
            r4 = 0
        L_0x0451:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x045f
            if (r4 == 0) goto L_0x045c
            r5 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            goto L_0x045e
        L_0x045c:
            r5 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
        L_0x045e:
            long r2 = r2 | r5
        L_0x045f:
            if (r4 == 0) goto L_0x0462
            goto L_0x0468
        L_0x0462:
            r5 = 8
            goto L_0x0469
        L_0x0465:
            r72 = r5
            r4 = 0
        L_0x0468:
            r5 = 0
        L_0x0469:
            r73 = 6444548096(0x180200000, double:3.184029817E-314)
            long r73 = r2 & r73
            r53 = 0
            int r6 = (r73 > r53 ? 1 : (r73 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x048e
            if (r0 == 0) goto L_0x047d
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.levelOffDes
            r73 = r4
            goto L_0x0480
        L_0x047d:
            r73 = r4
            r6 = 0
        L_0x0480:
            r4 = 21
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x0490
            java.lang.Object r4 = r6.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0491
        L_0x048e:
            r73 = r4
        L_0x0490:
            r4 = 0
        L_0x0491:
            r74 = 6446645248(0x180400000, double:3.185065948E-314)
            long r74 = r2 & r74
            r53 = 0
            int r6 = (r74 > r53 ? 1 : (r74 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x04bc
            if (r0 == 0) goto L_0x04a5
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.port1
            r74 = r4
            goto L_0x04a8
        L_0x04a5:
            r74 = r4
            r6 = 0
        L_0x04a8:
            r4 = 22
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x04b6
            java.lang.Object r4 = r6.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x04b7
        L_0x04b6:
            r4 = 0
        L_0x04b7:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x04bf
        L_0x04bc:
            r74 = r4
            r4 = 0
        L_0x04bf:
            r75 = 6450839552(0x180800000, double:3.1871382095E-314)
            long r75 = r2 & r75
            r53 = 0
            int r6 = (r75 > r53 ? 1 : (r75 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x04e4
            if (r0 == 0) goto L_0x04d3
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.levelOnDes
            r75 = r4
            goto L_0x04d6
        L_0x04d3:
            r75 = r4
            r6 = 0
        L_0x04d6:
            r4 = 23
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x04e6
            java.lang.Object r4 = r6.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x04e7
        L_0x04e4:
            r75 = r4
        L_0x04e6:
            r4 = 0
        L_0x04e7:
            r42 = 6459228160(0x181000000, double:3.1912827325E-314)
            long r76 = r2 & r42
            r53 = 0
            int r6 = (r76 > r53 ? 1 : (r76 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x050c
            if (r0 == 0) goto L_0x04fb
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.name
            r76 = r4
            goto L_0x04fe
        L_0x04fb:
            r76 = r4
            r6 = 0
        L_0x04fe:
            r4 = 24
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x050e
            java.lang.Object r4 = r6.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x050f
        L_0x050c:
            r76 = r4
        L_0x050e:
            r4 = 0
        L_0x050f:
            long r77 = r2 & r32
            r53 = 0
            int r6 = (r77 > r53 ? 1 : (r77 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x0535
            if (r0 == 0) goto L_0x051e
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.day1
            r77 = r4
            goto L_0x0521
        L_0x051e:
            r77 = r4
            r6 = 0
        L_0x0521:
            r4 = 25
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x052f
            java.lang.Object r4 = r6.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0530
        L_0x052f:
            r4 = 0
        L_0x0530:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x0538
        L_0x0535:
            r77 = r4
            r4 = 0
        L_0x0538:
            r78 = 6509559808(0x184000000, double:3.2161498707E-314)
            long r78 = r2 & r78
            r53 = 0
            int r6 = (r78 > r53 ? 1 : (r78 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x0563
            if (r0 == 0) goto L_0x054c
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.port2
            r78 = r4
            goto L_0x054f
        L_0x054c:
            r78 = r4
            r6 = 0
        L_0x054f:
            r4 = 26
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x055d
            java.lang.Object r4 = r6.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x055e
        L_0x055d:
            r4 = 0
        L_0x055e:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x0566
        L_0x0563:
            r78 = r4
            r4 = 0
        L_0x0566:
            r79 = 6576668672(0x188000000, double:3.249306055E-314)
            long r79 = r2 & r79
            r53 = 0
            int r6 = (r79 > r53 ? 1 : (r79 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x058b
            if (r0 == 0) goto L_0x057a
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.levelOffTitle
            r79 = r4
            goto L_0x057d
        L_0x057a:
            r79 = r4
            r6 = 0
        L_0x057d:
            r4 = 27
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x058d
            java.lang.Object r4 = r6.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x058e
        L_0x058b:
            r79 = r4
        L_0x058d:
            r4 = 0
        L_0x058e:
            r80 = 6710886400(0x190000000, double:3.3156184234E-314)
            long r80 = r2 & r80
            r53 = 0
            int r6 = (r80 > r53 ? 1 : (r80 == r53 ? 0 : -1))
            r80 = r4
            if (r6 == 0) goto L_0x05ca
            if (r0 == 0) goto L_0x05a4
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.vpdModeVisible
            r81 = r5
            goto L_0x05a7
        L_0x05a4:
            r81 = r5
            r4 = 0
        L_0x05a7:
            r5 = 28
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x05b5
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x05b6
        L_0x05b5:
            r4 = 0
        L_0x05b6:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x05c4
            if (r4 == 0) goto L_0x05c1
            r5 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
            goto L_0x05c3
        L_0x05c1:
            r5 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
        L_0x05c3:
            long r2 = r2 | r5
        L_0x05c4:
            if (r4 == 0) goto L_0x05c7
            goto L_0x05cc
        L_0x05c7:
            r4 = 8
            goto L_0x05cd
        L_0x05ca:
            r81 = r5
        L_0x05cc:
            r4 = 0
        L_0x05cd:
            r5 = 6979321856(0x1a0000000, double:3.4482431603E-314)
            long r5 = r5 & r2
            r53 = 0
            int r82 = (r5 > r53 ? 1 : (r5 == r53 ? 0 : -1))
            if (r82 == 0) goto L_0x05f3
            if (r0 == 0) goto L_0x05de
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.port3
            goto L_0x05df
        L_0x05de:
            r5 = 0
        L_0x05df:
            r6 = 29
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x05ed
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x05ee
        L_0x05ed:
            r5 = 0
        L_0x05ee:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            goto L_0x05f4
        L_0x05f3:
            r5 = 0
        L_0x05f4:
            long r82 = r2 & r40
            r53 = 0
            int r6 = (r82 > r53 ? 1 : (r82 == r53 ? 0 : -1))
            if (r6 == 0) goto L_0x0673
            if (r0 == 0) goto L_0x0603
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.isCreate
            r82 = r4
            goto L_0x0606
        L_0x0603:
            r82 = r4
            r0 = 0
        L_0x0606:
            r4 = 30
            r1.updateLiveDataRegistration(r4, r0)
            if (r0 == 0) goto L_0x0614
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            goto L_0x0615
        L_0x0614:
            r0 = 0
        L_0x0615:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r0)
            if (r6 == 0) goto L_0x062a
            if (r0 == 0) goto L_0x0623
            r83 = 274877906944(0x4000000000, double:1.358077306218E-312)
            goto L_0x0628
        L_0x0623:
            r83 = 137438953472(0x2000000000, double:6.7903865311E-313)
        L_0x0628:
            long r2 = r2 | r83
        L_0x062a:
            if (r0 == 0) goto L_0x062f
            r58 = 8
            goto L_0x0631
        L_0x062f:
            r58 = 0
        L_0x0631:
            r93 = r5
            r99 = r7
            r100 = r8
            r101 = r9
            r102 = r11
            r103 = r13
            r11 = r15
            r95 = r50
            r0 = r55
            r85 = r56
            r5 = r57
            r9 = r58
            r15 = r60
            r89 = r62
            r97 = r63
            r58 = r64
            r55 = r65
            r98 = r66
            r50 = r67
            r8 = r68
            r94 = r69
            r87 = r70
            r6 = r71
            r88 = r73
            r90 = r74
            r57 = r75
            r92 = r76
            r7 = r77
            r4 = r78
            r56 = r79
            r91 = r80
            r96 = r81
            r86 = r82
            goto L_0x06b5
        L_0x0673:
            r82 = r4
            r93 = r5
            r99 = r7
            r100 = r8
            r101 = r9
            r102 = r11
            r103 = r13
            r11 = r15
            r95 = r50
            r0 = r55
            r85 = r56
            r5 = r57
            r15 = r60
            r89 = r62
            r97 = r63
            r58 = r64
            r55 = r65
            r98 = r66
            r50 = r67
            r8 = r68
            r94 = r69
            r87 = r70
            r6 = r71
            r88 = r73
            r90 = r74
            r57 = r75
            r92 = r76
            r7 = r77
            r4 = r78
            r56 = r79
            r91 = r80
            r96 = r81
            r86 = r82
            r9 = 0
        L_0x06b5:
            r13 = r10
            r10 = r12
            r12 = r14
            r60 = r59
            r59 = r61
            r14 = r72
            goto L_0x0700
        L_0x06bf:
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
            r50 = 0
            r55 = 0
            r56 = 0
            r57 = 0
            r58 = 0
            r59 = 0
            r60 = 0
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
        L_0x0700:
            long r40 = r2 & r40
            r53 = 0
            int r61 = (r40 > r53 ? 1 : (r40 == r53 ? 0 : -1))
            r40 = r12
            if (r61 == 0) goto L_0x070f
            android.widget.RelativeLayout r12 = r1.btnDelete
            r12.setVisibility(r9)
        L_0x070f:
            long r36 = r2 & r36
            int r9 = (r36 > r53 ? 1 : (r36 == r53 ? 0 : -1))
            if (r9 == 0) goto L_0x0720
            android.widget.RelativeLayout r9 = r1.btnDelete
            r12 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r9, r11, r12)
            android.widget.EditText r9 = r1.etEditName
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r9, r10)
        L_0x0720:
            long r9 = r2 & r32
            int r11 = (r9 > r53 ? 1 : (r9 == r53 ? 0 : -1))
            if (r11 == 0) goto L_0x072b
            android.widget.CheckBox r9 = r1.cb1
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r9, r4)
        L_0x072b:
            r9 = 4294967296(0x100000000, double:2.121995791E-314)
            long r9 = r9 & r2
            int r4 = (r9 > r53 ? 1 : (r9 == r53 ? 0 : -1))
            if (r4 == 0) goto L_0x07c3
            android.widget.CheckBox r4 = r1.cb1
            r9 = 0
            r11 = r9
            android.widget.CompoundButton$OnCheckedChangeListener r11 = (android.widget.CompoundButton.OnCheckedChangeListener) r11
            androidx.databinding.InverseBindingListener r9 = r1.cb1androidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r4, r11, r9)
            android.widget.CheckBox r4 = r1.cb2
            androidx.databinding.InverseBindingListener r9 = r1.cb2androidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r4, r11, r9)
            android.widget.CheckBox r4 = r1.cb3
            androidx.databinding.InverseBindingListener r9 = r1.cb3androidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r4, r11, r9)
            android.widget.CheckBox r4 = r1.cb4
            androidx.databinding.InverseBindingListener r9 = r1.cb4androidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r4, r11, r9)
            android.widget.CheckBox r4 = r1.cb5
            androidx.databinding.InverseBindingListener r9 = r1.cb5androidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r4, r11, r9)
            android.widget.CheckBox r4 = r1.cb6
            androidx.databinding.InverseBindingListener r9 = r1.cb6androidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r4, r11, r9)
            android.widget.CheckBox r4 = r1.cb7
            androidx.databinding.InverseBindingListener r9 = r1.cb7androidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r4, r11, r9)
            android.widget.EditText r4 = r1.etEditName
            r9 = 0
            r10 = r9
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r10
            r12 = r9
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r12 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r12
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r9 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r9
            r32 = r8
            androidx.databinding.InverseBindingListener r8 = r1.etEditNameandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r4, r10, r12, r9, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.mboundView14
            androidx.databinding.InverseBindingListener r8 = r1.mboundView14selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.mboundView15
            androidx.databinding.InverseBindingListener r8 = r1.mboundView15selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.mboundView16
            androidx.databinding.InverseBindingListener r8 = r1.mboundView16selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.mboundView17
            androidx.databinding.InverseBindingListener r8 = r1.mboundView17selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.mboundView18
            androidx.databinding.InverseBindingListener r8 = r1.mboundView18selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.modeCycle
            androidx.databinding.InverseBindingListener r8 = r1.modeCycleselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.modeOn
            androidx.databinding.InverseBindingListener r8 = r1.modeOnselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.modeTempHum
            androidx.databinding.InverseBindingListener r8 = r1.modeTempHumselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.notification.view.TitleSelectView r4 = r1.modeVpd
            androidx.databinding.InverseBindingListener r8 = r1.modeVpdselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r4, r8)
            com.eternal.base.StatueSwitch r4 = r1.f214sb
            androidx.databinding.InverseBindingListener r8 = r1.sbandroidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r4, r11, r8)
            goto L_0x07c5
        L_0x07c3:
            r32 = r8
        L_0x07c5:
            long r8 = r2 & r26
            r10 = 0
            int r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r4 == 0) goto L_0x07d2
            android.widget.CheckBox r4 = r1.cb2
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r4, r0)
        L_0x07d2:
            long r8 = r2 & r34
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x07dd
            android.widget.CheckBox r0 = r1.cb3
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r0, r13)
        L_0x07dd:
            long r8 = r2 & r22
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x07e8
            android.widget.CheckBox r0 = r1.cb4
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r0, r15)
        L_0x07e8:
            long r8 = r2 & r20
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x07f3
            android.widget.CheckBox r0 = r1.cb5
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r0, r5)
        L_0x07f3:
            long r4 = r2 & r16
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x07fe
            android.widget.CheckBox r0 = r1.cb6
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r0, r6)
        L_0x07fe:
            r4 = 6442713088(0x180040000, double:3.183123203E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x080d
            android.widget.CheckBox r0 = r1.cb7
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r0, r14)
        L_0x080d:
            r4 = 6459228160(0x181000000, double:3.1912827325E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x081c
            android.widget.EditText r0 = r1.etEditName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x081c:
            r4 = 6442516480(0x180010000, double:3.1830260655E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x082d
            android.widget.ImageView r0 = r1.ivLevelOff
            r4 = r32
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r4)
        L_0x082d:
            long r4 = r2 & r30
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x083a
            android.widget.ImageView r0 = r1.ivLevelOn
            r14 = r40
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r14)
        L_0x083a:
            r4 = 6442451200(0x180000100, double:3.182993813E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0850
            android.widget.LinearLayout r0 = r1.llHumContainer
            r4 = r60
            r0.setVisibility(r4)
            android.view.View r0 = r1.mboundView25
            r0.setVisibility(r4)
        L_0x0850:
            r4 = 6442451968(0x180000400, double:3.1829941924E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0861
            android.widget.LinearLayout r0 = r1.llLevel
            r4 = r59
            r0.setVisibility(r4)
        L_0x0861:
            r4 = 6442459136(0x180002000, double:3.182997734E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0877
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.llLevelOff
            r4 = r58
            r0.setVisibility(r4)
            android.view.View r0 = r1.mboundView39
            r0.setVisibility(r4)
        L_0x0877:
            r4 = 6446645248(0x180400000, double:3.185065948E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0888
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView14
            r4 = r57
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0888:
            r4 = 6509559808(0x184000000, double:3.2161498707E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0899
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView15
            r4 = r56
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0899:
            r4 = 6979321856(0x1a0000000, double:3.4482431603E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x08aa
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView16
            r5 = r93
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r5)
        L_0x08aa:
            r4 = 6442467328(0x180004000, double:3.183001781E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x08bb
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView17
            r4 = r55
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x08bb:
            long r4 = r2 & r24
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x08cf
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView18
            r4 = r102
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
            android.widget.Space r0 = r1.mboundView19
            r9 = r101
            r0.setVisibility(r9)
        L_0x08cf:
            r4 = 6442483712(0x180008000, double:3.183009876E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x08ec
            android.widget.Space r0 = r1.mboundView20
            r4 = r94
            r0.setVisibility(r4)
            android.widget.Space r0 = r1.mboundView22
            r0.setVisibility(r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeOn
            r4 = r50
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x08ec:
            long r4 = r2 & r18
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0905
            android.widget.Space r0 = r1.mboundView23
            r4 = r95
            r0.setVisibility(r4)
            android.widget.Space r0 = r1.mboundView27
            r0.setVisibility(r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeTempHum
            r4 = r85
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0905:
            r4 = 6710886400(0x190000000, double:3.3156184234E-314)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x091d
            android.view.View r0 = r1.mboundView28
            r4 = r86
            r0.setVisibility(r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            r0.setVisibility(r4)
        L_0x091d:
            long r4 = r2 & r38
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0936
            android.widget.Space r0 = r1.mboundView29
            r7 = r99
            r0.setVisibility(r7)
            android.widget.Space r0 = r1.mboundView31
            r0.setVisibility(r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            r4 = r98
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0936:
            r4 = 6442582016(0x180020000, double:3.1830584446E-314)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x094e
            android.widget.TextView r0 = r1.mboundView3
            r4 = r87
            r0.setVisibility(r4)
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView6
            r0.setVisibility(r4)
        L_0x094e:
            r4 = 6443499520(0x180100000, double:3.183511752E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0966
            android.widget.Space r0 = r1.mboundView32
            r4 = r96
            r0.setVisibility(r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeCycle
            r4 = r88
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0966:
            r4 = 6442455040(0x180001000, double:3.18299571E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x097e
            com.eternal.widget.ExpandableLayout r0 = r1.mboundView5
            r13 = r103
            com.eternal.widget.ExpandableLayout.setExpanded((com.eternal.widget.ExpandableLayout) r0, (boolean) r13)
            com.eternal.base.StatueSwitch r0 = r1.f214sb
            r4 = r89
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r0, r4)
        L_0x097e:
            r4 = 6444548096(0x180200000, double:3.184029817E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x098f
            android.widget.TextView r0 = r1.tvLevelOffDes
            r4 = r90
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x098f:
            r4 = 6576668672(0x188000000, double:3.249306055E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x09a0
            android.widget.TextView r0 = r1.tvLevelOffTitle
            r4 = r91
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x09a0:
            r4 = 6450839552(0x180800000, double:3.1871382095E-314)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x09b1
            android.widget.TextView r0 = r1.tvLevelOnDes
            r4 = r92
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x09b1:
            long r4 = r2 & r28
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x09be
            android.widget.TextView r0 = r1.tvLevelOnTitle
            r8 = r100
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
        L_0x09be:
            r4 = 6442452992(0x180000800, double:3.1829946983E-314)
            long r2 = r2 & r4
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x09cf
            android.widget.TextView r0 = r1.tvNameSuggest
            r2 = r97
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x09cf:
            return
        L_0x09d0:
            r0 = move-exception
            monitor-exit(r104)     // Catch:{ all -> 0x09d0 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.notification.databinding.FragmentAutomationV4BindingImpl.executeBindings():void");
    }
}
