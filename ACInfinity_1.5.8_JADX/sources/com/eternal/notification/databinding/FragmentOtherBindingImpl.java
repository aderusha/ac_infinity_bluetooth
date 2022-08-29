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
import com.eternal.notification.model.OtherModel;
import com.eternal.notification.view.TitleSelectViewAdapter;

public class FragmentOtherBindingImpl extends FragmentOtherBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etEditNameandroidTextAttrChanged;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final Space mboundView10;
    private final TextView mboundView15;
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
        sparseIntArray.put(C2420R.C2423id.el_tmp, 16);
        sparseIntArray.put(C2420R.C2423id.ll_tmp_container, 17);
        sparseIntArray.put(C2420R.C2423id.tv_temp_min, 18);
        sparseIntArray.put(C2420R.C2423id.rsb_temp, 19);
        sparseIntArray.put(C2420R.C2423id.tv_temp_max, 20);
        sparseIntArray.put(C2420R.C2423id.layout_tmp, 21);
        sparseIntArray.put(C2420R.C2423id.sb_high_tmp, 22);
        sparseIntArray.put(C2420R.C2423id.sb_low_tmp, 23);
        sparseIntArray.put(C2420R.C2423id.el_hum, 24);
        sparseIntArray.put(C2420R.C2423id.ll_hum_container, 25);
        sparseIntArray.put(C2420R.C2423id.tv_hum_min, 26);
        sparseIntArray.put(C2420R.C2423id.rsb_hum, 27);
        sparseIntArray.put(C2420R.C2423id.layout_hum, 28);
        sparseIntArray.put(C2420R.C2423id.sb_high_hum, 29);
        sparseIntArray.put(C2420R.C2423id.sb_low_hum, 30);
        sparseIntArray.put(C2420R.C2423id.el_vpd, 31);
        sparseIntArray.put(C2420R.C2423id.ll_vpd_container, 32);
        sparseIntArray.put(C2420R.C2423id.tv_vpd_min, 33);
        sparseIntArray.put(C2420R.C2423id.rsb_vpd, 34);
        sparseIntArray.put(C2420R.C2423id.layout_vpd, 35);
        sparseIntArray.put(C2420R.C2423id.sb_high_vpd, 36);
        sparseIntArray.put(C2420R.C2423id.sb_low_vpd, 37);
        sparseIntArray.put(C2420R.C2423id.el_modes, 38);
        sparseIntArray.put(C2420R.C2423id.ll_modes_container, 39);
        sparseIntArray.put(C2420R.C2423id.ll_beep, 40);
        sparseIntArray.put(C2420R.C2423id.rg_beep, 41);
        sparseIntArray.put(C2420R.C2423id.rb_none, 42);
    }

    public FragmentOtherBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 43, sIncludes, sViewsWithIds));
    }

    private FragmentOtherBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 11, objArr[14], objArr[24], objArr[38], objArr[16], objArr[31], objArr[2], objArr[28], objArr[21], objArr[35], objArr[40], objArr[25], objArr[39], objArr[17], objArr[32], objArr[7], objArr[11], objArr[13], objArr[42], objArr[41], objArr[27], objArr[19], objArr[34], objArr[29], objArr[22], objArr[36], objArr[30], objArr[23], objArr[37], objArr[12], objArr[3], objArr[26], objArr[1], objArr[20], objArr[18], objArr[33]);
        this.etEditNameandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(FragmentOtherBindingImpl.this.etEditName);
                OtherModel otherModel = FragmentOtherBindingImpl.this.mOtherModel;
                boolean z = true;
                if (otherModel != null) {
                    MutableLiveData<String> mutableLiveData = otherModel.name;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.modeHumselectAttrChange = new InverseBindingListener() {
            public void onChange() {
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherBindingImpl.this.modeHum);
                OtherModel otherModel = FragmentOtherBindingImpl.this.mOtherModel;
                boolean z = true;
                if (otherModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModel.hum;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherBindingImpl.this.modeVpd);
                OtherModel otherModel = FragmentOtherBindingImpl.this.mOtherModel;
                boolean z = true;
                if (otherModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModel.vpd;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherBindingImpl.this.tsvAllMode);
                OtherModel otherModel = FragmentOtherBindingImpl.this.mOtherModel;
                boolean z = true;
                if (otherModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModel.modes;
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
                boolean select = TitleSelectViewAdapter.getSelect(FragmentOtherBindingImpl.this.tsvTmp);
                OtherModel otherModel = FragmentOtherBindingImpl.this.mOtherModel;
                boolean z = true;
                if (otherModel != null) {
                    MutableLiveData<Boolean> mutableLiveData = otherModel.tmp;
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
        TextView textView = objArr[15];
        this.mboundView15 = textView;
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
        this.rbBeep.setTag((Object) null);
        this.tsvAllMode.setTag((Object) null);
        this.tsvTmp.setTag((Object) null);
        this.tvNameSuggest.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
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
        if (C2419BR.otherModel != i) {
            return false;
        }
        setOtherModel((OtherModel) obj);
        return true;
    }

    public void setOtherModel(OtherModel otherModel) {
        this.mOtherModel = otherModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(C2419BR.otherModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeOtherModelName((MutableLiveData) obj, i2);
            case 1:
                return onChangeOtherModelIsCreate((MutableLiveData) obj, i2);
            case 2:
                return onChangeOtherModelHum((MutableLiveData) obj, i2);
            case 3:
                return onChangeOtherModelVpdVisible((MutableLiveData) obj, i2);
            case 4:
                return onChangeOtherModelHumVisible((MutableLiveData) obj, i2);
            case 5:
                return onChangeOtherModelNameSuggest((MutableLiveData) obj, i2);
            case 6:
                return onChangeOtherModelTmp((MutableLiveData) obj, i2);
            case 7:
                return onChangeOtherModelVpd((MutableLiveData) obj, i2);
            case 8:
                return onChangeOtherModelBeepText((MutableLiveData) obj, i2);
            case 9:
                return onChangeOtherModelModes((MutableLiveData) obj, i2);
            case 10:
                return onChangeOtherModelDeleteText((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeOtherModelName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeOtherModelIsCreate(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeOtherModelHum(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeOtherModelVpdVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeOtherModelHumVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeOtherModelNameSuggest(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeOtherModelTmp(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeOtherModelVpd(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeOtherModelBeepText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeOtherModelModes(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeOtherModelDeleteText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2419BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0139  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x019f  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x01ad  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x01c7  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x01f2  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x00e7  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x011b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r50 = this;
            r1 = r50
            monitor-enter(r50)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0370 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0370 }
            monitor-exit(r50)     // Catch:{ all -> 0x0370 }
            com.eternal.notification.model.OtherModel r0 = r1.mOtherModel
            r6 = 8191(0x1fff, double:4.047E-320)
            long r6 = r6 & r2
            r12 = 6176(0x1820, double:3.0513E-320)
            r16 = 6160(0x1810, double:3.0434E-320)
            r18 = 7168(0x1c00, double:3.5415E-320)
            r20 = 6152(0x1808, double:3.0395E-320)
            r22 = 6272(0x1880, double:3.099E-320)
            r24 = 6148(0x1804, double:3.0375E-320)
            r26 = 6145(0x1801, double:3.036E-320)
            r28 = 6144(0x1800, double:3.0355E-320)
            r30 = 6146(0x1802, double:3.0365E-320)
            r8 = 0
            int r34 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r34 == 0) goto L_0x022b
            long r6 = r2 & r28
            int r34 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r34 == 0) goto L_0x0033
            if (r0 == 0) goto L_0x0033
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r6 = r0.nameChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onDelete
            goto L_0x0035
        L_0x0033:
            r6 = 0
            r7 = 0
        L_0x0035:
            long r34 = r2 & r26
            int r36 = (r34 > r4 ? 1 : (r34 == r4 ? 0 : -1))
            if (r36 == 0) goto L_0x004d
            if (r0 == 0) goto L_0x0040
            androidx.lifecycle.MutableLiveData<java.lang.String> r9 = r0.name
            goto L_0x0041
        L_0x0040:
            r9 = 0
        L_0x0041:
            r1.updateLiveDataRegistration(r8, r9)
            if (r9 == 0) goto L_0x004d
            java.lang.Object r9 = r9.getValue()
            java.lang.String r9 = (java.lang.String) r9
            goto L_0x004e
        L_0x004d:
            r9 = 0
        L_0x004e:
            long r35 = r2 & r30
            int r37 = (r35 > r4 ? 1 : (r35 == r4 ? 0 : -1))
            if (r37 == 0) goto L_0x007b
            if (r0 == 0) goto L_0x0059
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.isCreate
            goto L_0x005a
        L_0x0059:
            r8 = 0
        L_0x005a:
            r10 = 1
            r1.updateLiveDataRegistration(r10, r8)
            if (r8 == 0) goto L_0x0067
            java.lang.Object r8 = r8.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x0068
        L_0x0067:
            r8 = 0
        L_0x0068:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r37 == 0) goto L_0x0076
            if (r8 == 0) goto L_0x0073
            r10 = 16384(0x4000, double:8.0948E-320)
            goto L_0x0075
        L_0x0073:
            r10 = 8192(0x2000, double:4.0474E-320)
        L_0x0075:
            long r2 = r2 | r10
        L_0x0076:
            if (r8 == 0) goto L_0x007b
            r8 = 8
            goto L_0x007c
        L_0x007b:
            r8 = 0
        L_0x007c:
            long r10 = r2 & r24
            int r36 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r36 == 0) goto L_0x00ad
            if (r0 == 0) goto L_0x0087
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.hum
            goto L_0x0088
        L_0x0087:
            r10 = 0
        L_0x0088:
            r11 = 2
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x0095
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x0096
        L_0x0095:
            r10 = 0
        L_0x0096:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r36 == 0) goto L_0x00a7
            if (r10 == 0) goto L_0x00a2
            r40 = 16777216(0x1000000, double:8.289046E-317)
            goto L_0x00a5
        L_0x00a2:
            r40 = 8388608(0x800000, double:4.144523E-317)
        L_0x00a5:
            long r2 = r2 | r40
        L_0x00a7:
            if (r10 == 0) goto L_0x00aa
            goto L_0x00ae
        L_0x00aa:
            r11 = 8
            goto L_0x00af
        L_0x00ad:
            r10 = 0
        L_0x00ae:
            r11 = 0
        L_0x00af:
            long r40 = r2 & r20
            int r36 = (r40 > r4 ? 1 : (r40 == r4 ? 0 : -1))
            if (r36 == 0) goto L_0x00e0
            if (r0 == 0) goto L_0x00ba
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r14 = r0.vpdVisible
            goto L_0x00bb
        L_0x00ba:
            r14 = 0
        L_0x00bb:
            r15 = 3
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x00c8
            java.lang.Object r14 = r14.getValue()
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L_0x00c9
        L_0x00c8:
            r14 = 0
        L_0x00c9:
            boolean r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r14)
            if (r36 == 0) goto L_0x00da
            if (r14 == 0) goto L_0x00d5
            r42 = 65536(0x10000, double:3.2379E-319)
            goto L_0x00d8
        L_0x00d5:
            r42 = 32768(0x8000, double:1.61895E-319)
        L_0x00d8:
            long r2 = r2 | r42
        L_0x00da:
            if (r14 == 0) goto L_0x00dd
            goto L_0x00e0
        L_0x00dd:
            r14 = 8
            goto L_0x00e1
        L_0x00e0:
            r14 = 0
        L_0x00e1:
            long r42 = r2 & r16
            int r15 = (r42 > r4 ? 1 : (r42 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0112
            if (r0 == 0) goto L_0x00ec
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.humVisible
            goto L_0x00ed
        L_0x00ec:
            r4 = 0
        L_0x00ed:
            r5 = 4
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x00fa
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00fb
        L_0x00fa:
            r4 = 0
        L_0x00fb:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x010c
            if (r4 == 0) goto L_0x0107
            r44 = 262144(0x40000, double:1.295163E-318)
            goto L_0x010a
        L_0x0107:
            r44 = 131072(0x20000, double:6.47582E-319)
        L_0x010a:
            long r2 = r2 | r44
        L_0x010c:
            if (r4 == 0) goto L_0x010f
            goto L_0x0112
        L_0x010f:
            r4 = 8
            goto L_0x0113
        L_0x0112:
            r4 = 0
        L_0x0113:
            long r44 = r2 & r12
            r42 = 0
            int r5 = (r44 > r42 ? 1 : (r44 == r42 ? 0 : -1))
            if (r5 == 0) goto L_0x012e
            if (r0 == 0) goto L_0x0120
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.nameSuggest
            goto L_0x0121
        L_0x0120:
            r5 = 0
        L_0x0121:
            r15 = 5
            r1.updateLiveDataRegistration(r15, r5)
            if (r5 == 0) goto L_0x012e
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x012f
        L_0x012e:
            r5 = 0
        L_0x012f:
            r40 = 6208(0x1840, double:3.067E-320)
            long r44 = r2 & r40
            r42 = 0
            int r15 = (r44 > r42 ? 1 : (r44 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x0164
            if (r0 == 0) goto L_0x013e
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.tmp
            goto L_0x013f
        L_0x013e:
            r12 = 0
        L_0x013f:
            r13 = 6
            r1.updateLiveDataRegistration(r13, r12)
            if (r12 == 0) goto L_0x014c
            java.lang.Object r12 = r12.getValue()
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            goto L_0x014d
        L_0x014c:
            r12 = 0
        L_0x014d:
            boolean r12 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r12)
            if (r15 == 0) goto L_0x015e
            if (r12 == 0) goto L_0x0159
            r46 = 4194304(0x400000, double:2.0722615E-317)
            goto L_0x015c
        L_0x0159:
            r46 = 2097152(0x200000, double:1.0361308E-317)
        L_0x015c:
            long r2 = r2 | r46
        L_0x015e:
            if (r12 == 0) goto L_0x0161
            goto L_0x0165
        L_0x0161:
            r13 = 8
            goto L_0x0166
        L_0x0164:
            r12 = 0
        L_0x0165:
            r13 = 0
        L_0x0166:
            long r46 = r2 & r22
            r42 = 0
            int r15 = (r46 > r42 ? 1 : (r46 == r42 ? 0 : -1))
            r36 = r4
            if (r15 == 0) goto L_0x019f
            if (r0 == 0) goto L_0x0177
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.vpd
            r37 = r5
            goto L_0x017a
        L_0x0177:
            r37 = r5
            r4 = 0
        L_0x017a:
            r5 = 7
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0187
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0188
        L_0x0187:
            r4 = 0
        L_0x0188:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x0199
            if (r4 == 0) goto L_0x0194
            r46 = 1048576(0x100000, double:5.180654E-318)
            goto L_0x0197
        L_0x0194:
            r46 = 524288(0x80000, double:2.590327E-318)
        L_0x0197:
            long r2 = r2 | r46
        L_0x0199:
            if (r4 == 0) goto L_0x019c
            goto L_0x01a2
        L_0x019c:
            r5 = 8
            goto L_0x01a3
        L_0x019f:
            r37 = r5
            r4 = 0
        L_0x01a2:
            r5 = 0
        L_0x01a3:
            r38 = 6400(0x1900, double:3.162E-320)
            long r46 = r2 & r38
            r42 = 0
            int r15 = (r46 > r42 ? 1 : (r46 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x01c7
            if (r0 == 0) goto L_0x01b6
            androidx.lifecycle.MutableLiveData<java.lang.String> r15 = r0.beepText
            r35 = r4
            r4 = 8
            goto L_0x01bb
        L_0x01b6:
            r35 = r4
            r4 = 8
            r15 = 0
        L_0x01bb:
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x01c9
            java.lang.Object r4 = r15.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x01ca
        L_0x01c7:
            r35 = r4
        L_0x01c9:
            r4 = 0
        L_0x01ca:
            r32 = 6656(0x1a00, double:3.2885E-320)
            long r46 = r2 & r32
            r42 = 0
            int r15 = (r46 > r42 ? 1 : (r46 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x01f2
            if (r0 == 0) goto L_0x01db
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.modes
            r46 = r4
            goto L_0x01de
        L_0x01db:
            r46 = r4
            r15 = 0
        L_0x01de:
            r4 = 9
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x01ec
            java.lang.Object r4 = r15.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01ed
        L_0x01ec:
            r4 = 0
        L_0x01ed:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x01f5
        L_0x01f2:
            r46 = r4
            r4 = 0
        L_0x01f5:
            long r47 = r2 & r18
            r42 = 0
            int r15 = (r47 > r42 ? 1 : (r47 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x0212
            if (r0 == 0) goto L_0x0202
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.deleteText
            goto L_0x0203
        L_0x0202:
            r0 = 0
        L_0x0203:
            r15 = 10
            r1.updateLiveDataRegistration(r15, r0)
            if (r0 == 0) goto L_0x0212
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r15 = r11
            goto L_0x0214
        L_0x0212:
            r15 = r11
            r0 = 0
        L_0x0214:
            r11 = r7
            r7 = r13
            r13 = r9
            r9 = r12
            r12 = r8
            r8 = r5
            r5 = r36
            r36 = r4
            r4 = r35
            r35 = r37
            r37 = r46
            r49 = r10
            r10 = r6
            r6 = r14
            r14 = r49
            goto L_0x023e
        L_0x022b:
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
            r35 = 0
            r36 = 0
            r37 = 0
        L_0x023e:
            long r30 = r2 & r30
            r42 = 0
            int r46 = (r30 > r42 ? 1 : (r30 == r42 ? 0 : -1))
            r30 = r6
            if (r46 == 0) goto L_0x024d
            android.widget.RelativeLayout r6 = r1.btnDelete
            r6.setVisibility(r12)
        L_0x024d:
            long r28 = r2 & r28
            int r6 = (r28 > r42 ? 1 : (r28 == r42 ? 0 : -1))
            if (r6 == 0) goto L_0x025e
            android.widget.RelativeLayout r6 = r1.btnDelete
            r12 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r6, r11, r12)
            android.widget.EditText r6 = r1.etEditName
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r6, r10)
        L_0x025e:
            long r10 = r2 & r26
            int r6 = (r10 > r42 ? 1 : (r10 == r42 ? 0 : -1))
            if (r6 == 0) goto L_0x0269
            android.widget.EditText r6 = r1.etEditName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r13)
        L_0x0269:
            r10 = 4096(0x1000, double:2.0237E-320)
            long r10 = r10 & r2
            int r6 = (r10 > r42 ? 1 : (r10 == r42 ? 0 : -1))
            if (r6 == 0) goto L_0x02e0
            android.widget.EditText r6 = r1.etEditName
            r10 = 0
            r11 = r10
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r11 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r11
            r12 = r10
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r12 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r12
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r10
            androidx.databinding.InverseBindingListener r13 = r1.etEditNameandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r6, r11, r12, r10, r13)
            com.eternal.notification.view.TitleSelectView r6 = r1.modeHum
            com.eternal.notification.view.TitleSelectView r10 = r1.modeHum
            android.content.res.Resources r10 = r10.getResources()
            int r11 = com.eternal.notification.C2420R.string.title_hum_parameter
            java.lang.String r10 = r10.getString(r11)
            com.eternal.notification.view.TitleSelectViewAdapter.setTitle(r6, r10)
            com.eternal.notification.view.TitleSelectView r6 = r1.modeHum
            com.eternal.notification.view.TitleSelectView r10 = r1.modeHum
            android.content.res.Resources r10 = r10.getResources()
            int r11 = com.eternal.notification.C2420R.string.alarm_info_hum_parameter
            java.lang.String r10 = r10.getString(r11)
            com.eternal.notification.view.TitleSelectViewAdapter.setInfo(r6, r10)
            com.eternal.notification.view.TitleSelectView r6 = r1.modeHum
            androidx.databinding.InverseBindingListener r10 = r1.modeHumselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r6, r10)
            com.eternal.notification.view.TitleSelectView r6 = r1.modeVpd
            androidx.databinding.InverseBindingListener r10 = r1.modeVpdselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r6, r10)
            com.eternal.notification.view.TitleSelectView r6 = r1.tsvAllMode
            androidx.databinding.InverseBindingListener r10 = r1.tsvAllModeselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r6, r10)
            com.eternal.notification.view.TitleSelectView r6 = r1.tsvTmp
            com.eternal.notification.view.TitleSelectView r10 = r1.tsvTmp
            android.content.res.Resources r10 = r10.getResources()
            int r11 = com.eternal.notification.C2420R.string.title_temp_parameter
            java.lang.String r10 = r10.getString(r11)
            com.eternal.notification.view.TitleSelectViewAdapter.setTitle(r6, r10)
            com.eternal.notification.view.TitleSelectView r6 = r1.tsvTmp
            com.eternal.notification.view.TitleSelectView r10 = r1.tsvTmp
            android.content.res.Resources r10 = r10.getResources()
            int r11 = com.eternal.notification.C2420R.string.alarm_info_temp_parameter
            java.lang.String r10 = r10.getString(r11)
            com.eternal.notification.view.TitleSelectViewAdapter.setInfo(r6, r10)
            com.eternal.notification.view.TitleSelectView r6 = r1.tsvTmp
            androidx.databinding.InverseBindingListener r10 = r1.tsvTmpselectAttrChange
            com.eternal.notification.view.TitleSelectViewAdapter.selectAttrChange(r6, r10)
        L_0x02e0:
            long r10 = r2 & r22
            r12 = 0
            int r6 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r6 == 0) goto L_0x02f2
            android.widget.Space r6 = r1.mboundView10
            r6.setVisibility(r8)
            com.eternal.notification.view.TitleSelectView r6 = r1.modeVpd
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r6, r4)
        L_0x02f2:
            long r10 = r2 & r18
            int r4 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r4 == 0) goto L_0x02fd
            android.widget.TextView r4 = r1.mboundView15
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r0)
        L_0x02fd:
            r10 = 6208(0x1840, double:3.067E-320)
            long r10 = r10 & r2
            int r0 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x030e
            android.widget.Space r0 = r1.mboundView4
            r0.setVisibility(r7)
            com.eternal.notification.view.TitleSelectView r0 = r1.tsvTmp
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r9)
        L_0x030e:
            long r6 = r2 & r16
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x031e
            android.view.View r0 = r1.mboundView5
            r0.setVisibility(r5)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeHum
            r0.setVisibility(r5)
        L_0x031e:
            long r4 = r2 & r24
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0333
            android.widget.Space r0 = r1.mboundView6
            r0.setVisibility(r15)
            android.widget.Space r0 = r1.mboundView8
            r0.setVisibility(r15)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeHum
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r14)
        L_0x0333:
            long r4 = r2 & r20
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0345
            android.view.View r0 = r1.mboundView9
            r14 = r30
            r0.setVisibility(r14)
            com.eternal.notification.view.TitleSelectView r0 = r1.modeVpd
            r0.setVisibility(r14)
        L_0x0345:
            r4 = 6400(0x1900, double:3.162E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0353
            android.widget.RadioButton r0 = r1.rbBeep
            r4 = r37
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0353:
            r4 = 6656(0x1a00, double:3.2885E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0361
            com.eternal.notification.view.TitleSelectView r0 = r1.tsvAllMode
            r4 = r36
            com.eternal.notification.view.TitleSelectViewAdapter.setSelect(r0, r4)
        L_0x0361:
            r4 = 6176(0x1820, double:3.0513E-320)
            long r2 = r2 & r4
            int r0 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x036f
            android.widget.TextView r0 = r1.tvNameSuggest
            r2 = r35
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x036f:
            return
        L_0x0370:
            r0 = move-exception
            monitor-exit(r50)     // Catch:{ all -> 0x0370 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.notification.databinding.FragmentOtherBindingImpl.executeBindings():void");
    }
}
