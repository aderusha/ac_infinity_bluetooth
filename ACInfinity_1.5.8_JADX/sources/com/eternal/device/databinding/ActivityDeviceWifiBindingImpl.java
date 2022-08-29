package com.eternal.device.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.AddWiFiModel;

public class ActivityDeviceWifiBindingImpl extends ActivityDeviceWifiBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.rl_oval, 10);
    }

    public ActivityDeviceWifiBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private ActivityDeviceWifiBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 11, objArr[9], objArr[5], objArr[10], objArr[1], objArr[7], objArr[8], objArr[6]);
        this.mDirtyFlags = -1;
        this.ibOk.setTag((Object) null);
        this.imgBluetooth.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        ImageView imageView = objArr[2];
        this.mboundView2 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = objArr[3];
        this.mboundView3 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = objArr[4];
        this.mboundView4 = imageView3;
        imageView3.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvContent.setTag((Object) null);
        this.tvHelp.setTag((Object) null);
        this.tvTitle.setTag((Object) null);
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
        if (C1909BR.addWifiModel != i) {
            return false;
        }
        setAddWifiModel((AddWiFiModel) obj);
        return true;
    }

    public void setAddWifiModel(AddWiFiModel addWiFiModel) {
        this.mAddWifiModel = addWiFiModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(C1909BR.addWifiModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeAddWifiModelStateText((MutableLiveData) obj, i2);
            case 1:
                return onChangeAddWifiModelState((MutableLiveData) obj, i2);
            case 2:
                return onChangeAddWifiModelContentText((MutableLiveData) obj, i2);
            case 3:
                return onChangeAddWifiModelCancelVisible((MutableLiveData) obj, i2);
            case 4:
                return onChangeAddWifiModelContentVisible((MutableLiveData) obj, i2);
            case 5:
                return onChangeAddWifiModelMaxVisible((MutableLiveData) obj, i2);
            case 6:
                return onChangeAddWifiModelNormalVisible((MutableLiveData) obj, i2);
            case 7:
                return onChangeAddWifiModelMinVisible((MutableLiveData) obj, i2);
            case 8:
                return onChangeAddWifiModelReconnectVisible((MutableLiveData) obj, i2);
            case 9:
                return onChangeAddWifiModelBtnVisible((MutableLiveData) obj, i2);
            case 10:
                return onChangeAddWifiModelBtnText((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeAddWifiModelStateText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeAddWifiModelState(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeAddWifiModelContentText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeAddWifiModelCancelVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeAddWifiModelContentVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeAddWifiModelMaxVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeAddWifiModelNormalVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeAddWifiModelMinVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeAddWifiModelReconnectVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeAddWifiModelBtnVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeAddWifiModelBtnText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0200  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0118  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r49 = this;
            r1 = r49
            monitor-enter(r49)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x02dc }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x02dc }
            monitor-exit(r49)     // Catch:{ all -> 0x02dc }
            com.eternal.device.model.AddWiFiModel r0 = r1.mAddWifiModel
            r6 = 8191(0x1fff, double:4.047E-320)
            long r6 = r6 & r2
            r12 = 6160(0x1810, double:3.0434E-320)
            r16 = 6152(0x1808, double:3.0395E-320)
            r18 = 6176(0x1820, double:3.0513E-320)
            r20 = 6148(0x1804, double:3.0375E-320)
            r22 = 6146(0x1802, double:3.0365E-320)
            r24 = 6144(0x1800, double:3.0355E-320)
            r26 = 6656(0x1a00, double:3.2885E-320)
            r28 = 6145(0x1801, double:3.036E-320)
            r30 = 7168(0x1c00, double:3.5415E-320)
            r8 = 0
            int r34 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r34 == 0) goto L_0x0231
            long r6 = r2 & r28
            int r34 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r34 == 0) goto L_0x003e
            if (r0 == 0) goto L_0x0031
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.stateText
            goto L_0x0032
        L_0x0031:
            r6 = 0
        L_0x0032:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x003e
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x003f
        L_0x003e:
            r6 = 0
        L_0x003f:
            long r34 = r2 & r24
            int r7 = (r34 > r4 ? 1 : (r34 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x004e
            if (r0 == 0) goto L_0x004e
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onConfirm
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onCancel
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r8 = r0.onReconnect
            goto L_0x0051
        L_0x004e:
            r7 = 0
            r8 = 0
            r9 = 0
        L_0x0051:
            long r36 = r2 & r22
            int r38 = (r36 > r4 ? 1 : (r36 == r4 ? 0 : -1))
            if (r38 == 0) goto L_0x0070
            if (r0 == 0) goto L_0x005c
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r10 = r0.state
            goto L_0x005d
        L_0x005c:
            r10 = 0
        L_0x005d:
            r11 = 1
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x006a
            java.lang.Object r10 = r10.getValue()
            java.lang.Integer r10 = (java.lang.Integer) r10
            goto L_0x006b
        L_0x006a:
            r10 = 0
        L_0x006b:
            int r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r10)
            goto L_0x0071
        L_0x0070:
            r10 = 0
        L_0x0071:
            long r38 = r2 & r20
            int r11 = (r38 > r4 ? 1 : (r38 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x008a
            if (r0 == 0) goto L_0x007c
            androidx.lifecycle.MutableLiveData<java.lang.String> r11 = r0.contentText
            goto L_0x007d
        L_0x007c:
            r11 = 0
        L_0x007d:
            r14 = 2
            r1.updateLiveDataRegistration(r14, r11)
            if (r11 == 0) goto L_0x008a
            java.lang.Object r11 = r11.getValue()
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x008b
        L_0x008a:
            r11 = 0
        L_0x008b:
            long r14 = r2 & r16
            int r40 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r40 == 0) goto L_0x00aa
            if (r0 == 0) goto L_0x0096
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r14 = r0.cancelVisible
            goto L_0x0097
        L_0x0096:
            r14 = 0
        L_0x0097:
            r15 = 3
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x00a4
            java.lang.Object r14 = r14.getValue()
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L_0x00a5
        L_0x00a4:
            r14 = 0
        L_0x00a5:
            boolean r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r14)
            goto L_0x00ab
        L_0x00aa:
            r14 = 0
        L_0x00ab:
            long r40 = r2 & r12
            r12 = 4
            int r13 = (r40 > r4 ? 1 : (r40 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00dc
            if (r0 == 0) goto L_0x00b7
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.contentVisible
            goto L_0x00b8
        L_0x00b7:
            r15 = 0
        L_0x00b8:
            r1.updateLiveDataRegistration(r12, r15)
            if (r15 == 0) goto L_0x00c4
            java.lang.Object r15 = r15.getValue()
            java.lang.Boolean r15 = (java.lang.Boolean) r15
            goto L_0x00c5
        L_0x00c4:
            r15 = 0
        L_0x00c5:
            boolean r15 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r15)
            if (r13 == 0) goto L_0x00d6
            if (r15 == 0) goto L_0x00d1
            r42 = 65536(0x10000, double:3.2379E-319)
            goto L_0x00d4
        L_0x00d1:
            r42 = 32768(0x8000, double:1.61895E-319)
        L_0x00d4:
            long r2 = r2 | r42
        L_0x00d6:
            if (r15 == 0) goto L_0x00d9
            goto L_0x00dc
        L_0x00d9:
            r13 = 8
            goto L_0x00dd
        L_0x00dc:
            r13 = 0
        L_0x00dd:
            long r42 = r2 & r18
            int r15 = (r42 > r4 ? 1 : (r42 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x010d
            if (r0 == 0) goto L_0x00e8
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.maxVisible
            goto L_0x00e9
        L_0x00e8:
            r12 = 0
        L_0x00e9:
            r4 = 5
            r1.updateLiveDataRegistration(r4, r12)
            if (r12 == 0) goto L_0x00f6
            java.lang.Object r4 = r12.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00f7
        L_0x00f6:
            r4 = 0
        L_0x00f7:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x0108
            if (r4 == 0) goto L_0x0103
            r44 = 1048576(0x100000, double:5.180654E-318)
            goto L_0x0106
        L_0x0103:
            r44 = 524288(0x80000, double:2.590327E-318)
        L_0x0106:
            long r2 = r2 | r44
        L_0x0108:
            if (r4 == 0) goto L_0x010b
            goto L_0x010d
        L_0x010b:
            r4 = 4
            goto L_0x010e
        L_0x010d:
            r4 = 0
        L_0x010e:
            r38 = 6208(0x1840, double:3.067E-320)
            long r44 = r2 & r38
            r42 = 0
            int r5 = (r44 > r42 ? 1 : (r44 == r42 ? 0 : -1))
            if (r5 == 0) goto L_0x0140
            if (r0 == 0) goto L_0x011d
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.normalVisible
            goto L_0x011e
        L_0x011d:
            r12 = 0
        L_0x011e:
            r15 = 6
            r1.updateLiveDataRegistration(r15, r12)
            if (r12 == 0) goto L_0x012b
            java.lang.Object r12 = r12.getValue()
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            goto L_0x012c
        L_0x012b:
            r12 = 0
        L_0x012c:
            boolean r12 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r12)
            if (r5 == 0) goto L_0x013b
            if (r12 == 0) goto L_0x0137
            r44 = 16384(0x4000, double:8.0948E-320)
            goto L_0x0139
        L_0x0137:
            r44 = 8192(0x2000, double:4.0474E-320)
        L_0x0139:
            long r2 = r2 | r44
        L_0x013b:
            if (r12 == 0) goto L_0x013e
            goto L_0x0140
        L_0x013e:
            r5 = 4
            goto L_0x0141
        L_0x0140:
            r5 = 0
        L_0x0141:
            r36 = 6272(0x1880, double:3.099E-320)
            long r44 = r2 & r36
            r42 = 0
            int r12 = (r44 > r42 ? 1 : (r44 == r42 ? 0 : -1))
            if (r12 == 0) goto L_0x0179
            if (r0 == 0) goto L_0x0152
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.minVisible
            r44 = r4
            goto L_0x0155
        L_0x0152:
            r44 = r4
            r15 = 0
        L_0x0155:
            r4 = 7
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x0162
            java.lang.Object r4 = r15.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0163
        L_0x0162:
            r4 = 0
        L_0x0163:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r12 == 0) goto L_0x0174
            if (r4 == 0) goto L_0x016f
            r45 = 262144(0x40000, double:1.295163E-318)
            goto L_0x0172
        L_0x016f:
            r45 = 131072(0x20000, double:6.47582E-319)
        L_0x0172:
            long r2 = r2 | r45
        L_0x0174:
            if (r4 == 0) goto L_0x0177
            goto L_0x017b
        L_0x0177:
            r12 = 4
            goto L_0x017c
        L_0x0179:
            r44 = r4
        L_0x017b:
            r12 = 0
        L_0x017c:
            r32 = 6400(0x1900, double:3.162E-320)
            long r45 = r2 & r32
            r42 = 0
            int r4 = (r45 > r42 ? 1 : (r45 == r42 ? 0 : -1))
            if (r4 == 0) goto L_0x01b8
            if (r0 == 0) goto L_0x018f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.reconnectVisible
            r40 = r5
            r5 = 8
            goto L_0x0194
        L_0x018f:
            r40 = r5
            r5 = 8
            r15 = 0
        L_0x0194:
            r1.updateLiveDataRegistration(r5, r15)
            if (r15 == 0) goto L_0x01a0
            java.lang.Object r15 = r15.getValue()
            java.lang.Boolean r15 = (java.lang.Boolean) r15
            goto L_0x01a1
        L_0x01a0:
            r15 = 0
        L_0x01a1:
            boolean r15 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r15)
            if (r4 == 0) goto L_0x01b2
            if (r15 == 0) goto L_0x01ad
            r45 = 4194304(0x400000, double:2.0722615E-317)
            goto L_0x01b0
        L_0x01ad:
            r45 = 2097152(0x200000, double:1.0361308E-317)
        L_0x01b0:
            long r2 = r2 | r45
        L_0x01b2:
            if (r15 == 0) goto L_0x01b5
            goto L_0x01bc
        L_0x01b5:
            r4 = 8
            goto L_0x01bd
        L_0x01b8:
            r40 = r5
            r5 = 8
        L_0x01bc:
            r4 = 0
        L_0x01bd:
            long r45 = r2 & r26
            r42 = 0
            int r15 = (r45 > r42 ? 1 : (r45 == r42 ? 0 : -1))
            if (r15 == 0) goto L_0x01f5
            if (r0 == 0) goto L_0x01cc
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.btnVisible
            r45 = r4
            goto L_0x01cf
        L_0x01cc:
            r45 = r4
            r5 = 0
        L_0x01cf:
            r4 = 9
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x01dd
            java.lang.Object r4 = r5.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01de
        L_0x01dd:
            r4 = 0
        L_0x01de:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x01ef
            if (r4 == 0) goto L_0x01ea
            r46 = 16777216(0x1000000, double:8.289046E-317)
            goto L_0x01ed
        L_0x01ea:
            r46 = 8388608(0x800000, double:4.144523E-317)
        L_0x01ed:
            long r2 = r2 | r46
        L_0x01ef:
            if (r4 == 0) goto L_0x01f2
            goto L_0x01f7
        L_0x01f2:
            r15 = 8
            goto L_0x01f8
        L_0x01f5:
            r45 = r4
        L_0x01f7:
            r15 = 0
        L_0x01f8:
            long r4 = r2 & r30
            r42 = 0
            int r41 = (r4 > r42 ? 1 : (r4 == r42 ? 0 : -1))
            if (r41 == 0) goto L_0x021e
            if (r0 == 0) goto L_0x0205
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.btnText
            goto L_0x0206
        L_0x0205:
            r0 = 0
        L_0x0206:
            r4 = 10
            r1.updateLiveDataRegistration(r4, r0)
            if (r0 == 0) goto L_0x021e
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r34 = r6
            r6 = r9
            r5 = r40
            r4 = r44
            r9 = r0
            r0 = r45
            goto L_0x0228
        L_0x021e:
            r34 = r6
            r6 = r9
            r5 = r40
            r4 = r44
            r0 = r45
            r9 = 0
        L_0x0228:
            r48 = r12
            r12 = r10
            r10 = r15
            r15 = r14
            r14 = r13
            r13 = r48
            goto L_0x0240
        L_0x0231:
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
            r34 = 0
        L_0x0240:
            long r30 = r2 & r30
            r40 = 0
            int r42 = (r30 > r40 ? 1 : (r30 == r40 ? 0 : -1))
            r30 = r0
            if (r42 == 0) goto L_0x024f
            android.widget.Button r0 = r1.ibOk
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r9)
        L_0x024f:
            long r26 = r2 & r26
            int r0 = (r26 > r40 ? 1 : (r26 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x025a
            android.widget.Button r0 = r1.ibOk
            r0.setVisibility(r10)
        L_0x025a:
            long r9 = r2 & r24
            int r0 = (r9 > r40 ? 1 : (r9 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x0270
            android.widget.Button r0 = r1.ibOk
            r9 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r7, r9)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onNext(r0, r6)
            android.widget.TextView r0 = r1.tvHelp
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r8, r9)
        L_0x0270:
            long r6 = r2 & r22
            int r0 = (r6 > r40 ? 1 : (r6 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x027b
            android.widget.ImageView r0 = r1.imgBluetooth
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r0, r12)
        L_0x027b:
            long r6 = r2 & r18
            int r0 = (r6 > r40 ? 1 : (r6 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x0286
            android.widget.ImageView r0 = r1.mboundView2
            r0.setVisibility(r4)
        L_0x0286:
            r6 = 6208(0x1840, double:3.067E-320)
            long r6 = r6 & r2
            int r0 = (r6 > r40 ? 1 : (r6 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x0292
            android.widget.ImageView r0 = r1.mboundView3
            r0.setVisibility(r5)
        L_0x0292:
            r4 = 6272(0x1880, double:3.099E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r40 ? 1 : (r4 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x029e
            android.widget.ImageView r0 = r1.mboundView4
            r0.setVisibility(r13)
        L_0x029e:
            long r4 = r2 & r16
            int r0 = (r4 > r40 ? 1 : (r4 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x02a9
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.setNextVisible(r0, r15)
        L_0x02a9:
            long r4 = r2 & r20
            int r0 = (r4 > r40 ? 1 : (r4 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x02b4
            android.widget.TextView r0 = r1.tvContent
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r11)
        L_0x02b4:
            r4 = 6160(0x1810, double:3.0434E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r40 ? 1 : (r4 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x02c0
            android.widget.TextView r0 = r1.tvContent
            r0.setVisibility(r14)
        L_0x02c0:
            r4 = 6400(0x1900, double:3.162E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r40 ? 1 : (r4 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x02ce
            android.widget.TextView r0 = r1.tvHelp
            r4 = r30
            r0.setVisibility(r4)
        L_0x02ce:
            long r2 = r2 & r28
            int r0 = (r2 > r40 ? 1 : (r2 == r40 ? 0 : -1))
            if (r0 == 0) goto L_0x02db
            android.widget.TextView r0 = r1.tvTitle
            r6 = r34
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x02db:
            return
        L_0x02dc:
            r0 = move-exception
            monitor-exit(r49)     // Catch:{ all -> 0x02dc }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.device.databinding.ActivityDeviceWifiBindingImpl.executeBindings():void");
    }
}
