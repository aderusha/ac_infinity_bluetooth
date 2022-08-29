package com.eternal.notification.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.notification.C2419BR;
import com.eternal.notification.C2420R;
import com.eternal.notification.model.OtherModelV4;
import com.eternal.notification.view.TitleSelectView;
import com.eternal.notification.view.TitleSelectViewAdapter;

public class FragmentOtherV4BindingImpl extends FragmentOtherV4Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etEditNameandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final Space mboundView10;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView13;
    private InverseBindingListener mboundView13selectAttrChange;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView14;
    private InverseBindingListener mboundView14selectAttrChange;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView15;
    private InverseBindingListener mboundView15selectAttrChange;
    /* access modifiers changed from: private */
    public final TitleSelectView mboundView16;
    private InverseBindingListener mboundView16selectAttrChange;
    private final TextView mboundView18;
    private final Space mboundView4;
    private final View mboundView5;
    private final Space mboundView6;
    private final Space mboundView8;
    private final View mboundView9;
    private InverseBindingListener modeHumselectAttrChange;
    private InverseBindingListener modeVpdselectAttrChange;
    private InverseBindingListener tsvAllModeselectAttrChange;
    private InverseBindingListener tsvTmpselectAttrChange;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2420R.C2423id.el_tmp, 19);
        sparseIntArray.put(C2420R.C2423id.ll_tmp_container, 20);
        sparseIntArray.put(C2420R.C2423id.tv_temp_min, 21);
        sparseIntArray.put(C2420R.C2423id.rsb_temp, 22);
        sparseIntArray.put(C2420R.C2423id.tv_temp_max, 23);
        sparseIntArray.put(C2420R.C2423id.layout_tmp, 24);
        sparseIntArray.put(C2420R.C2423id.sb_high_tmp, 25);
        sparseIntArray.put(C2420R.C2423id.sb_low_tmp, 26);
        sparseIntArray.put(C2420R.C2423id.el_hum, 27);
        sparseIntArray.put(C2420R.C2423id.ll_hum_container, 28);
        sparseIntArray.put(C2420R.C2423id.tv_hum_min, 29);
        sparseIntArray.put(C2420R.C2423id.rsb_hum, 30);
        sparseIntArray.put(C2420R.C2423id.layout_hum, 31);
        sparseIntArray.put(C2420R.C2423id.sb_high_hum, 32);
        sparseIntArray.put(C2420R.C2423id.sb_low_hum, 33);
        sparseIntArray.put(C2420R.C2423id.el_vpd, 34);
        sparseIntArray.put(C2420R.C2423id.ll_vpd_container, 35);
        sparseIntArray.put(C2420R.C2423id.tv_vpd_min, 36);
        sparseIntArray.put(C2420R.C2423id.rsb_vpd, 37);
        sparseIntArray.put(C2420R.C2423id.layout_vpd, 38);
        sparseIntArray.put(C2420R.C2423id.sb_high_vpd, 39);
        sparseIntArray.put(C2420R.C2423id.sb_low_vpd, 40);
        sparseIntArray.put(C2420R.C2423id.el_modes, 41);
        sparseIntArray.put(C2420R.C2423id.ll_modes_container, 42);
        sparseIntArray.put(C2420R.C2423id.ll_beep, 43);
        sparseIntArray.put(C2420R.C2423id.rg_beep, 44);
        sparseIntArray.put(C2420R.C2423id.rb_none, 45);
        sparseIntArray.put(C2420R.C2423id.rb_1, 46);
        sparseIntArray.put(C2420R.C2423id.rb_3, 47);
        sparseIntArray.put(C2420R.C2423id.rb_continuous, 48);
    }

    public FragmentOtherV4BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 49, sIncludes, sViewsWithIds));
    }

    private FragmentOtherV4BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 14, objArr[17], objArr[27], objArr[41], objArr[19], objArr[34], objArr[2], objArr[31], objArr[24], objArr[38], objArr[43], objArr[28], objArr[42], objArr[20], objArr[35], objArr[7], objArr[11], objArr[46], objArr[47], objArr[48], objArr[45], objArr[44], objArr[30], objArr[22], objArr[37], objArr[32], objArr[25], objArr[39], objArr[33], objArr[26], objArr[40], objArr[12], objArr[3], objArr[29], objArr[1], objArr[23], objArr[21], objArr[36]);
        this.etEditNameandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentOtherV4BindingImpl.this.etEditName);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<String> mutableLiveData = otherModelV4.name;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.mboundView13selectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherV4BindingImpl.this.mboundView13);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModelV4.port1;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.mboundView14selectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherV4BindingImpl.this.mboundView14);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModelV4.port2;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherV4BindingImpl.this.mboundView15);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModelV4.port3;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherV4BindingImpl.this.mboundView16);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModelV4.port4;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.modeHumselectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherV4BindingImpl.this.modeHum);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModelV4.hum;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherV4BindingImpl.this.modeVpd);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModelV4.vpd;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.tsvAllModeselectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherV4BindingImpl.this.tsvAllMode);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModelV4.modes;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(Boolean.valueOf(select));
                    }
                }
            }
        };
        this.tsvTmpselectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherV4BindingImpl.this.tsvTmp);
                OtherModelV4 otherModelV4 = FragmentOtherV4BindingImpl.this.mOtherModelV4;
                boolean z = true;
                if (otherModelV4 != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModelV4.tmp;
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
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        Space space = objArr[10];
        this.mboundView10 = space;
        space.setTag((Object) null);
        TitleSelectView titleSelectView = objArr[13];
        this.mboundView13 = titleSelectView;
        titleSelectView.setTag((Object) null);
        TitleSelectView titleSelectView2 = objArr[14];
        this.mboundView14 = titleSelectView2;
        titleSelectView2.setTag((Object) null);
        TitleSelectView titleSelectView3 = objArr[15];
        this.mboundView15 = titleSelectView3;
        titleSelectView3.setTag((Object) null);
        TitleSelectView titleSelectView4 = objArr[16];
        this.mboundView16 = titleSelectView4;
        titleSelectView4.setTag((Object) null);
        TextView textView = objArr[18];
        this.mboundView18 = textView;
        textView.setTag((Object) null);
        Space space2 = objArr[4];
        this.mboundView4 = space2;
        space2.setTag((Object) null);
        View view2 = objArr[5];
        this.mboundView5 = view2;
        view2.setTag((Object) null);
        Space space3 = objArr[6];
        this.mboundView6 = space3;
        space3.setTag((Object) null);
        Space space4 = objArr[8];
        this.mboundView8 = space4;
        space4.setTag((Object) null);
        View view3 = objArr[9];
        this.mboundView9 = view3;
        view3.setTag((Object) null);
        this.modeHum.setTag((Object) null);
        this.modeVpd.setTag((Object) null);
        this.tsvAllMode.setTag((Object) null);
        this.tsvTmp.setTag((Object) null);
        this.tvNameSuggest.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
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
        if (C2419BR.otherModelV4 != i) {
            return false;
        }
        setOtherModelV4((OtherModelV4) obj);
        return true;
    }

    public void setOtherModelV4(OtherModelV4 otherModelV4) {
        this.mOtherModelV4 = otherModelV4;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        notifyPropertyChanged(C2419BR.otherModelV4);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeOtherModelV4Hum((MutableLiveData) obj, i2);
            case 1:
                return onChangeOtherModelV4Port2((MutableLiveData) obj, i2);
            case 2:
                return onChangeOtherModelV4HumVisible((MutableLiveData) obj, i2);
            case 3:
                return onChangeOtherModelV4Name((MutableLiveData) obj, i2);
            case 4:
                return onChangeOtherModelV4Port1((MutableLiveData) obj, i2);
            case 5:
                return onChangeOtherModelV4Modes((MutableLiveData) obj, i2);
            case 6:
                return onChangeOtherModelV4Vpd((MutableLiveData) obj, i2);
            case 7:
                return onChangeOtherModelV4Port4((MutableLiveData) obj, i2);
            case 8:
                return onChangeOtherModelV4DeleteText((MutableLiveData) obj, i2);
            case 9:
                return onChangeOtherModelV4VpdVisible((MutableLiveData) obj, i2);
            case 10:
                return onChangeOtherModelV4Tmp((MutableLiveData) obj, i2);
            case 11:
                return onChangeOtherModelV4IsCreate((MutableLiveData) obj, i2);
            case 12:
                return onChangeOtherModelV4Port3((MutableLiveData) obj, i2);
            case 13:
                return onChangeOtherModelV4NameSuggest((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeOtherModelV4Hum(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeOtherModelV4Port2(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeOtherModelV4HumVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeOtherModelV4Name(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeOtherModelV4Port1(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeOtherModelV4Modes(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeOtherModelV4Vpd(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeOtherModelV4Port4(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeOtherModelV4DeleteText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeOtherModelV4VpdVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeOtherModelV4Tmp(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeOtherModelV4IsCreate(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeOtherModelV4Port3(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeOtherModelV4NameSuggest(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x017b  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x01e3  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x01f3  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0222  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0230  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x026a  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x009d  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0109  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0129  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r60 = this;
            r1 = r60
            monitor-enter(r60)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0474 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0474 }
            monitor-exit(r60)     // Catch:{ all -> 0x0474 }
            com.eternal.notification.model.OtherModelV4 r0 = r1.mOtherModelV4
            r6 = 65535(0xffff, double:3.23786E-319)
            long r6 = r6 & r2
            r16 = 49280(0xc080, double:2.43476E-319)
            r18 = 53248(0xd000, double:2.6308E-319)
            r20 = 49184(0xc020, double:2.43E-319)
            r22 = 49168(0xc010, double:2.4292E-319)
            r24 = 49216(0xc040, double:2.4316E-319)
            r26 = 49156(0xc004, double:2.42863E-319)
            r28 = 49154(0xc002, double:2.42853E-319)
            r30 = 49160(0xc008, double:2.42883E-319)
            r32 = 49152(0xc000, double:2.42843E-319)
            r34 = 49153(0xc001, double:2.4285E-319)
            r36 = 51200(0xc800, double:2.5296E-319)
            r8 = 0
            int r38 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r38 == 0) goto L_0x02e1
            long r6 = r2 & r34
            int r39 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r39 == 0) goto L_0x0066
            if (r0 == 0) goto L_0x0041
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.hum
            goto L_0x0042
        L_0x0041:
            r6 = 0
        L_0x0042:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x004e
            java.lang.Object r6 = r6.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            goto L_0x004f
        L_0x004e:
            r6 = 0
        L_0x004f:
            boolean r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r6)
            if (r39 == 0) goto L_0x0060
            if (r6 == 0) goto L_0x005b
            r39 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x005e
        L_0x005b:
            r39 = 4194304(0x400000, double:2.0722615E-317)
        L_0x005e:
            long r2 = r2 | r39
        L_0x0060:
            if (r6 == 0) goto L_0x0063
            goto L_0x0067
        L_0x0063:
            r7 = 8
            goto L_0x0068
        L_0x0066:
            r6 = 0
        L_0x0067:
            r7 = 0
        L_0x0068:
            long r39 = r2 & r32
            int r41 = (r39 > r4 ? 1 : (r39 == r4 ? 0 : -1))
            if (r41 == 0) goto L_0x0075
            if (r0 == 0) goto L_0x0075
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r8 = r0.nameChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onDelete
            goto L_0x0077
        L_0x0075:
            r8 = 0
            r12 = 0
        L_0x0077:
            long r42 = r2 & r28
            int r13 = (r42 > r4 ? 1 : (r42 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0096
            if (r0 == 0) goto L_0x0082
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.port2
            goto L_0x0083
        L_0x0082:
            r13 = 0
        L_0x0083:
            r10 = 1
            r1.updateLiveDataRegistration(r10, r13)
            if (r13 == 0) goto L_0x0090
            java.lang.Object r10 = r13.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x0091
        L_0x0090:
            r10 = 0
        L_0x0091:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            goto L_0x0097
        L_0x0096:
            r10 = 0
        L_0x0097:
            long r44 = r2 & r26
            int r11 = (r44 > r4 ? 1 : (r44 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00c8
            if (r0 == 0) goto L_0x00a2
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.humVisible
            goto L_0x00a3
        L_0x00a2:
            r13 = 0
        L_0x00a3:
            r9 = 2
            r1.updateLiveDataRegistration(r9, r13)
            if (r13 == 0) goto L_0x00b0
            java.lang.Object r9 = r13.getValue()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            goto L_0x00b1
        L_0x00b0:
            r9 = 0
        L_0x00b1:
            boolean r9 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r9)
            if (r11 == 0) goto L_0x00c2
            if (r9 == 0) goto L_0x00bd
            r45 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x00c0
        L_0x00bd:
            r45 = 1048576(0x100000, double:5.180654E-318)
        L_0x00c0:
            long r2 = r2 | r45
        L_0x00c2:
            if (r9 == 0) goto L_0x00c5
            goto L_0x00c8
        L_0x00c5:
            r9 = 8
            goto L_0x00c9
        L_0x00c8:
            r9 = 0
        L_0x00c9:
            long r45 = r2 & r30
            int r11 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00e2
            if (r0 == 0) goto L_0x00d4
            androidx.lifecycle.MutableLiveData<java.lang.String> r11 = r0.name
            goto L_0x00d5
        L_0x00d4:
            r11 = 0
        L_0x00d5:
            r13 = 3
            r1.updateLiveDataRegistration(r13, r11)
            if (r11 == 0) goto L_0x00e2
            java.lang.Object r11 = r11.getValue()
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x00e3
        L_0x00e2:
            r11 = 0
        L_0x00e3:
            long r45 = r2 & r22
            int r13 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0102
            if (r0 == 0) goto L_0x00ee
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.port1
            goto L_0x00ef
        L_0x00ee:
            r13 = 0
        L_0x00ef:
            r14 = 4
            r1.updateLiveDataRegistration(r14, r13)
            if (r13 == 0) goto L_0x00fc
            java.lang.Object r13 = r13.getValue()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            goto L_0x00fd
        L_0x00fc:
            r13 = 0
        L_0x00fd:
            boolean r13 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r13)
            goto L_0x0103
        L_0x0102:
            r13 = 0
        L_0x0103:
            long r14 = r2 & r20
            int r47 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r47 == 0) goto L_0x0122
            if (r0 == 0) goto L_0x010e
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r14 = r0.modes
            goto L_0x010f
        L_0x010e:
            r14 = 0
        L_0x010f:
            r15 = 5
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x011c
            java.lang.Object r14 = r14.getValue()
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L_0x011d
        L_0x011c:
            r14 = 0
        L_0x011d:
            boolean r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r14)
            goto L_0x0123
        L_0x0122:
            r14 = 0
        L_0x0123:
            long r47 = r2 & r24
            int r15 = (r47 > r4 ? 1 : (r47 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0154
            if (r0 == 0) goto L_0x012e
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.vpd
            goto L_0x012f
        L_0x012e:
            r4 = 0
        L_0x012f:
            r5 = 6
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x013c
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x013d
        L_0x013c:
            r4 = 0
        L_0x013d:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x014e
            if (r4 == 0) goto L_0x0149
            r49 = 524288(0x80000, double:2.590327E-318)
            goto L_0x014c
        L_0x0149:
            r49 = 262144(0x40000, double:1.295163E-318)
        L_0x014c:
            long r2 = r2 | r49
        L_0x014e:
            if (r4 == 0) goto L_0x0151
            goto L_0x0155
        L_0x0151:
            r5 = 8
            goto L_0x0156
        L_0x0154:
            r4 = 0
        L_0x0155:
            r5 = 0
        L_0x0156:
            long r49 = r2 & r16
            r47 = 0
            int r15 = (r49 > r47 ? 1 : (r49 == r47 ? 0 : -1))
            if (r15 == 0) goto L_0x017b
            if (r0 == 0) goto L_0x0165
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.port4
            r49 = r4
            goto L_0x0168
        L_0x0165:
            r49 = r4
            r15 = 0
        L_0x0168:
            r4 = 7
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x0175
            java.lang.Object r4 = r15.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0176
        L_0x0175:
            r4 = 0
        L_0x0176:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x017e
        L_0x017b:
            r49 = r4
            r4 = 0
        L_0x017e:
            r45 = 49408(0xc100, double:2.4411E-319)
            long r50 = r2 & r45
            r47 = 0
            int r15 = (r50 > r47 ? 1 : (r50 == r47 ? 0 : -1))
            if (r15 == 0) goto L_0x01a3
            if (r0 == 0) goto L_0x0192
            androidx.lifecycle.MutableLiveData<java.lang.String> r15 = r0.deleteText
            r44 = r4
            r4 = 8
            goto L_0x0197
        L_0x0192:
            r44 = r4
            r4 = 8
            r15 = 0
        L_0x0197:
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x01a7
            java.lang.Object r15 = r15.getValue()
            java.lang.String r15 = (java.lang.String) r15
            goto L_0x01a8
        L_0x01a3:
            r44 = r4
            r4 = 8
        L_0x01a7:
            r15 = 0
        L_0x01a8:
            r42 = 49664(0xc200, double:2.45373E-319)
            long r50 = r2 & r42
            r47 = 0
            int r52 = (r50 > r47 ? 1 : (r50 == r47 ? 0 : -1))
            if (r52 == 0) goto L_0x01e3
            if (r0 == 0) goto L_0x01ba
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.vpdVisible
            r51 = r5
            goto L_0x01bd
        L_0x01ba:
            r51 = r5
            r4 = 0
        L_0x01bd:
            r5 = 9
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x01cb
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01cc
        L_0x01cb:
            r4 = 0
        L_0x01cc:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r52 == 0) goto L_0x01dd
            if (r4 == 0) goto L_0x01d8
            r52 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x01db
        L_0x01d8:
            r52 = 16777216(0x1000000, double:8.289046E-317)
        L_0x01db:
            long r2 = r2 | r52
        L_0x01dd:
            if (r4 == 0) goto L_0x01e0
            goto L_0x01e5
        L_0x01e0:
            r4 = 8
            goto L_0x01e6
        L_0x01e3:
            r51 = r5
        L_0x01e5:
            r4 = 0
        L_0x01e6:
            r40 = 50176(0xc400, double:2.479E-319)
            long r52 = r2 & r40
            r47 = 0
            int r5 = (r52 > r47 ? 1 : (r52 == r47 ? 0 : -1))
            r52 = r4
            if (r5 == 0) goto L_0x0222
            if (r0 == 0) goto L_0x01fa
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.tmp
            r53 = r6
            goto L_0x01fd
        L_0x01fa:
            r53 = r6
            r4 = 0
        L_0x01fd:
            r6 = 10
            r1.updateLiveDataRegistration(r6, r4)
            if (r4 == 0) goto L_0x020b
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x020c
        L_0x020b:
            r4 = 0
        L_0x020c:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x021c
            if (r4 == 0) goto L_0x0218
            r5 = 131072(0x20000, double:6.47582E-319)
            goto L_0x021b
        L_0x0218:
            r5 = 65536(0x10000, double:3.2379E-319)
        L_0x021b:
            long r2 = r2 | r5
        L_0x021c:
            if (r4 == 0) goto L_0x021f
            goto L_0x0225
        L_0x021f:
            r5 = 8
            goto L_0x0226
        L_0x0222:
            r53 = r6
            r4 = 0
        L_0x0225:
            r5 = 0
        L_0x0226:
            long r54 = r2 & r36
            r47 = 0
            int r6 = (r54 > r47 ? 1 : (r54 == r47 ? 0 : -1))
            r54 = r4
            if (r6 == 0) goto L_0x025e
            if (r0 == 0) goto L_0x0237
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.isCreate
            r55 = r5
            goto L_0x023a
        L_0x0237:
            r55 = r5
            r4 = 0
        L_0x023a:
            r5 = 11
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0248
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0249
        L_0x0248:
            r4 = 0
        L_0x0249:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x0259
            if (r4 == 0) goto L_0x0255
            r5 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x0258
        L_0x0255:
            r5 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x0258:
            long r2 = r2 | r5
        L_0x0259:
            if (r4 == 0) goto L_0x0260
            r50 = 8
            goto L_0x0262
        L_0x025e:
            r55 = r5
        L_0x0260:
            r50 = 0
        L_0x0262:
            long r4 = r2 & r18
            r47 = 0
            int r6 = (r4 > r47 ? 1 : (r4 == r47 ? 0 : -1))
            if (r6 == 0) goto L_0x0284
            if (r0 == 0) goto L_0x026f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.port3
            goto L_0x0270
        L_0x026f:
            r4 = 0
        L_0x0270:
            r5 = 12
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x027e
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x027f
        L_0x027e:
            r4 = 0
        L_0x027f:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x0285
        L_0x0284:
            r4 = 0
        L_0x0285:
            r5 = 57344(0xe000, double:2.83317E-319)
            long r56 = r2 & r5
            r5 = 0
            int r58 = (r56 > r5 ? 1 : (r56 == r5 ? 0 : -1))
            if (r58 == 0) goto L_0x02c1
            if (r0 == 0) goto L_0x0295
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.nameSuggest
            goto L_0x0296
        L_0x0295:
            r0 = 0
        L_0x0296:
            r5 = 13
            r1.updateLiveDataRegistration(r5, r0)
            if (r0 == 0) goto L_0x02c1
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r5 = r4
            r59 = r14
            r6 = r44
            r4 = r49
            r14 = r50
            r49 = r52
            r50 = r53
            r44 = r0
            r52 = r9
            r0 = r11
            r11 = r13
            r9 = r51
            r51 = r7
            r13 = r8
            r8 = r12
            r12 = r15
            r7 = r54
            r15 = r10
            goto L_0x02de
        L_0x02c1:
            r5 = r4
            r0 = r11
            r11 = r13
            r59 = r14
            r6 = r44
            r4 = r49
            r14 = r50
            r49 = r52
            r50 = r53
            r44 = 0
            r13 = r8
            r52 = r9
            r8 = r12
            r12 = r15
            r9 = r51
            r51 = r7
            r15 = r10
            r7 = r54
        L_0x02de:
            r10 = r55
            goto L_0x02fa
        L_0x02e1:
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
            r44 = 0
            r49 = 0
            r50 = 0
            r51 = 0
            r52 = 0
            r59 = 0
        L_0x02fa:
            long r36 = r2 & r36
            r47 = 0
            int r53 = (r36 > r47 ? 1 : (r36 == r47 ? 0 : -1))
            r36 = r7
            if (r53 == 0) goto L_0x0309
            android.widget.RelativeLayout r7 = r1.btnDelete
            r7.setVisibility(r14)
        L_0x0309:
            long r32 = r2 & r32
            int r7 = (r32 > r47 ? 1 : (r32 == r47 ? 0 : -1))
            if (r7 == 0) goto L_0x031a
            android.widget.RelativeLayout r7 = r1.btnDelete
            r14 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r7, r8, r14)
            android.widget.EditText r7 = r1.etEditName
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r7, r13)
        L_0x031a:
            long r7 = r2 & r30
            int r13 = (r7 > r47 ? 1 : (r7 == r47 ? 0 : -1))
            if (r13 == 0) goto L_0x0325
            android.widget.EditText r7 = r1.etEditName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r0)
        L_0x0325:
            r7 = 32768(0x8000, double:1.61895E-319)
            long r7 = r7 & r2
            int r0 = (r7 > r47 ? 1 : (r7 == r47 ? 0 : -1))
            if (r0 == 0) goto L_0x03b9
            android.widget.EditText r0 = r1.etEditName
            r7 = 0
            r8 = r7
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r8 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r8
            r13 = r7
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r13 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r13
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r7 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r7
            androidx.databinding.InverseBindingListener r14 = r1.etEditNameandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r8, r13, r7, r14)
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView13
            androidx.databinding.InverseBindingListener r7 = r1.mboundView13selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView14
            androidx.databinding.InverseBindingListener r7 = r1.mboundView14selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView15
            androidx.databinding.InverseBindingListener r7 = r1.mboundView15selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView16
            androidx.databinding.InverseBindingListener r7 = r1.mboundView16selectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeHum
            com.eternal.notification.view.TitleSelectView r7 = r1.modeHum
            android.content.res.Resources r7 = r7.getResources()
            int r8 = com.eternal.notification.C2420R.string.title_hum_parameter
            java.lang.String r7 = r7.getString(r8)
            com.eternal.notification.view.TitleSelectViewAdapter.setTitle(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeHum
            com.eternal.notification.view.TitleSelectView r7 = r1.modeHum
            android.content.res.Resources r7 = r7.getResources()
            int r8 = com.eternal.notification.C2420R.string.alarm_info_hum_parameter
            java.lang.String r7 = r7.getString(r8)
            com.eternal.notification.view.TitleSelectViewAdapter.setInfo(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeHum
            androidx.databinding.InverseBindingListener r7 = r1.modeHumselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            androidx.databinding.InverseBindingListener r7 = r1.modeVpdselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.tsvAllMode
            androidx.databinding.InverseBindingListener r7 = r1.tsvAllModeselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.tsvTmp
            com.eternal.notification.view.TitleSelectView r7 = r1.tsvTmp
            android.content.res.Resources r7 = r7.getResources()
            int r8 = com.eternal.notification.C2420R.string.title_temp_parameter
            java.lang.String r7 = r7.getString(r8)
            com.eternal.notification.view.TitleSelectViewAdapter.setTitle(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.tsvTmp
            com.eternal.notification.view.TitleSelectView r7 = r1.tsvTmp
            android.content.res.Resources r7 = r7.getResources()
            int r8 = com.eternal.notification.C2420R.string.alarm_info_temp_parameter
            java.lang.String r7 = r7.getString(r8)
            com.eternal.notification.view.TitleSelectViewAdapter.setInfo(r0, r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.tsvTmp
            androidx.databinding.InverseBindingListener r7 = r1.tsvTmpselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r0, r7)
        L_0x03b9:
            long r7 = r2 & r24
            r13 = 0
            int r0 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x03cb
            android.widget.Space r0 = r1.mboundView10
            r0.setVisibility(r9)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x03cb:
            long r7 = r2 & r22
            int r0 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x03d6
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView13
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r11)
        L_0x03d6:
            long r7 = r2 & r28
            int r0 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x03e1
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView14
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r15)
        L_0x03e1:
            long r7 = r2 & r18
            int r0 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x03ec
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView15
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r5)
        L_0x03ec:
            long r4 = r2 & r16
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x03f7
            com.eternal.notification.view.TitleSelectView r0 = r1.mboundView16
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r6)
        L_0x03f7:
            r4 = 49408(0xc100, double:2.4411E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0404
            android.widget.TextView r0 = r1.mboundView18
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x0404:
            r4 = 50176(0xc400, double:2.479E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0418
            android.widget.Space r0 = r1.mboundView4
            r0.setVisibility(r10)
            com.eternal.notification.view.TitleSelectView r0 = r1.tsvTmp
            r4 = r36
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0418:
            long r4 = r2 & r26
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x042a
            android.view.View r0 = r1.mboundView5
            r9 = r52
            r0.setVisibility(r9)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeHum
            r0.setVisibility(r9)
        L_0x042a:
            long r4 = r2 & r34
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0443
            android.widget.Space r0 = r1.mboundView6
            r7 = r51
            r0.setVisibility(r7)
            android.widget.Space r0 = r1.mboundView8
            r0.setVisibility(r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeHum
            r4 = r50
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0443:
            r4 = 49664(0xc200, double:2.45373E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0457
            android.view.View r0 = r1.mboundView9
            r4 = r49
            r0.setVisibility(r4)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            r0.setVisibility(r4)
        L_0x0457:
            long r4 = r2 & r20
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0464
            com.eternal.notification.view.TitleSelectView r0 = r1.tsvAllMode
            r4 = r59
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0464:
            r4 = 57344(0xe000, double:2.83317E-319)
            long r2 = r2 & r4
            int r0 = (r2 > r13 ? 1 : (r2 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0473
            android.widget.TextView r0 = r1.tvNameSuggest
            r2 = r44
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x0473:
            return
        L_0x0474:
            r0 = move-exception
            monitor-exit(r60)     // Catch:{ all -> 0x0474 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.notification.databinding.FragmentOtherV4BindingImpl.executeBindings():void");
    }
}
