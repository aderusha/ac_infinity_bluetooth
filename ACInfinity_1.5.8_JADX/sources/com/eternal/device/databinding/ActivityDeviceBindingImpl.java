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
import com.eternal.device.model.AddModel;

public class ActivityDeviceBindingImpl extends ActivityDeviceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final ImageView mboundView7;
    private final ImageView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.rl_oval, 13);
    }

    public ActivityDeviceBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityDeviceBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 14, objArr[11], objArr[5], objArr[12], objArr[13], objArr[1], objArr[9], objArr[10], objArr[6]);
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
        ImageView imageView4 = objArr[7];
        this.mboundView7 = imageView4;
        imageView4.setTag((Object) null);
        ImageView imageView5 = objArr[8];
        this.mboundView8 = imageView5;
        imageView5.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvContent.setTag((Object) null);
        this.tvHelp.setTag((Object) null);
        this.tvTitle.setTag((Object) null);
        View view2 = view;
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
        if (C1909BR.model != i) {
            return false;
        }
        setModel((AddModel) obj);
        return true;
    }

    public void setModel(AddModel addModel) {
        this.mModel = addModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        notifyPropertyChanged(C1909BR.model);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeModelContentText((MutableLiveData) obj, i2);
            case 1:
                return onChangeModelTitleText((MutableLiveData) obj, i2);
            case 2:
                return onChangeModelContentVisible((MutableLiveData) obj, i2);
            case 3:
                return onChangeModelCancelVisible((MutableLiveData) obj, i2);
            case 4:
                return onChangeModelBackVisible((MutableLiveData) obj, i2);
            case 5:
                return onChangeModelMinVisible((MutableLiveData) obj, i2);
            case 6:
                return onChangeModelShowLoading((MutableLiveData) obj, i2);
            case 7:
                return onChangeModelMaxVisible((MutableLiveData) obj, i2);
            case 8:
                return onChangeModelBtnVisible((MutableLiveData) obj, i2);
            case 9:
                return onChangeModelContentIconVisible((MutableLiveData) obj, i2);
            case 10:
                return onChangeModelNormalVisible((MutableLiveData) obj, i2);
            case 11:
                return onChangeModelConnectBleVisible((MutableLiveData) obj, i2);
            case 12:
                return onChangeModelStateIcon((MutableLiveData) obj, i2);
            case 13:
                return onChangeModelBtnText((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeModelContentText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelTitleText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModelContentVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModelCancelVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeModelBackVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeModelMinVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeModelMaxVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeModelBtnVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeModelContentIconVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeModelNormalVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeModelConnectBleVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeModelStateIcon(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeModelBtnText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0163  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0191  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x020e  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x0219  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:211:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x02b8  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x012f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r60 = this;
            r1 = r60
            monitor-enter(r60)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x03f0 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x03f0 }
            monitor-exit(r60)     // Catch:{ all -> 0x03f0 }
            com.eternal.device.model.AddModel r0 = r1.mModel
            r6 = 65535(0xffff, double:3.23786E-319)
            long r6 = r6 & r2
            r16 = 49168(0xc010, double:2.4292E-319)
            r18 = 50176(0xc400, double:2.479E-319)
            r20 = 49160(0xc008, double:2.42883E-319)
            r22 = 49280(0xc080, double:2.43476E-319)
            r24 = 49156(0xc004, double:2.42863E-319)
            r26 = 53248(0xd000, double:2.6308E-319)
            r28 = 49154(0xc002, double:2.42853E-319)
            r30 = 49152(0xc000, double:2.42843E-319)
            r32 = 49408(0xc100, double:2.4411E-319)
            r34 = 49153(0xc001, double:2.4285E-319)
            r36 = 57344(0xe000, double:2.83317E-319)
            r8 = 0
            int r38 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r38 == 0) goto L_0x0304
            long r6 = r2 & r34
            int r38 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r38 == 0) goto L_0x004e
            if (r0 == 0) goto L_0x0041
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.contentText
            goto L_0x0042
        L_0x0041:
            r6 = 0
        L_0x0042:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x004e
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x004f
        L_0x004e:
            r6 = 0
        L_0x004f:
            long r38 = r2 & r30
            int r7 = (r38 > r4 ? 1 : (r38 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0060
            if (r0 == 0) goto L_0x0060
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onConnectBle
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r8 = r0.onConfirm
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onCancel
            goto L_0x0064
        L_0x0060:
            r7 = 0
            r8 = 0
            r9 = 0
            r12 = 0
        L_0x0064:
            long r42 = r2 & r28
            int r13 = (r42 > r4 ? 1 : (r42 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x007d
            if (r0 == 0) goto L_0x006f
            androidx.lifecycle.MutableLiveData<java.lang.String> r13 = r0.titleText
            goto L_0x0070
        L_0x006f:
            r13 = 0
        L_0x0070:
            r10 = 1
            r1.updateLiveDataRegistration(r10, r13)
            if (r13 == 0) goto L_0x007d
            java.lang.Object r10 = r13.getValue()
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x007e
        L_0x007d:
            r10 = 0
        L_0x007e:
            long r44 = r2 & r24
            int r13 = (r44 > r4 ? 1 : (r44 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00ae
            if (r0 == 0) goto L_0x0089
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r11 = r0.contentVisible
            goto L_0x008a
        L_0x0089:
            r11 = 0
        L_0x008a:
            r14 = 2
            r1.updateLiveDataRegistration(r14, r11)
            if (r11 == 0) goto L_0x0097
            java.lang.Object r11 = r11.getValue()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            goto L_0x0098
        L_0x0097:
            r11 = 0
        L_0x0098:
            boolean r11 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r11)
            if (r13 == 0) goto L_0x00a8
            if (r11 == 0) goto L_0x00a4
            r13 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x00a7
        L_0x00a4:
            r13 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x00a7:
            long r2 = r2 | r13
        L_0x00a8:
            if (r11 == 0) goto L_0x00ab
            goto L_0x00ae
        L_0x00ab:
            r11 = 8
            goto L_0x00af
        L_0x00ae:
            r11 = 0
        L_0x00af:
            long r13 = r2 & r20
            int r15 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00ce
            if (r0 == 0) goto L_0x00ba
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.cancelVisible
            goto L_0x00bb
        L_0x00ba:
            r13 = 0
        L_0x00bb:
            r14 = 3
            r1.updateLiveDataRegistration(r14, r13)
            if (r13 == 0) goto L_0x00c8
            java.lang.Object r13 = r13.getValue()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            goto L_0x00c9
        L_0x00c8:
            r13 = 0
        L_0x00c9:
            boolean r13 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r13)
            goto L_0x00cf
        L_0x00ce:
            r13 = 0
        L_0x00cf:
            long r14 = r2 & r16
            r47 = r6
            r6 = 4
            int r48 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r48 == 0) goto L_0x00f0
            if (r0 == 0) goto L_0x00dd
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r14 = r0.backVisible
            goto L_0x00de
        L_0x00dd:
            r14 = 0
        L_0x00de:
            r1.updateLiveDataRegistration(r6, r14)
            if (r14 == 0) goto L_0x00ea
            java.lang.Object r14 = r14.getValue()
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L_0x00eb
        L_0x00ea:
            r14 = 0
        L_0x00eb:
            boolean r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r14)
            goto L_0x00f1
        L_0x00f0:
            r14 = 0
        L_0x00f1:
            r45 = 49184(0xc020, double:2.43E-319)
            long r48 = r2 & r45
            int r15 = (r48 > r4 ? 1 : (r48 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0123
            if (r0 == 0) goto L_0x00ff
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.minVisible
            goto L_0x0100
        L_0x00ff:
            r6 = 0
        L_0x0100:
            r4 = 5
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x010d
            java.lang.Object r4 = r6.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x010e
        L_0x010d:
            r4 = 0
        L_0x010e:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x011e
            if (r4 == 0) goto L_0x011a
            r5 = 131072(0x20000, double:6.47582E-319)
            goto L_0x011d
        L_0x011a:
            r5 = 65536(0x10000, double:3.2379E-319)
        L_0x011d:
            long r2 = r2 | r5
        L_0x011e:
            if (r4 == 0) goto L_0x0121
            goto L_0x0123
        L_0x0121:
            r4 = 4
            goto L_0x0124
        L_0x0123:
            r4 = 0
        L_0x0124:
            r5 = 49216(0xc040, double:2.4316E-319)
            long r51 = r2 & r5
            r5 = 0
            int r15 = (r51 > r5 ? 1 : (r51 == r5 ? 0 : -1))
            if (r15 == 0) goto L_0x015a
            if (r0 == 0) goto L_0x0134
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.showLoading
            goto L_0x0135
        L_0x0134:
            r5 = 0
        L_0x0135:
            r6 = 6
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x0142
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x0143
        L_0x0142:
            r5 = 0
        L_0x0143:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            if (r15 == 0) goto L_0x0154
            if (r5 == 0) goto L_0x014f
            r51 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x0152
        L_0x014f:
            r51 = 16777216(0x1000000, double:8.289046E-317)
        L_0x0152:
            long r2 = r2 | r51
        L_0x0154:
            if (r5 == 0) goto L_0x0157
            goto L_0x015a
        L_0x0157:
            r5 = 8
            goto L_0x015b
        L_0x015a:
            r5 = 0
        L_0x015b:
            long r51 = r2 & r22
            r49 = 0
            int r6 = (r51 > r49 ? 1 : (r51 == r49 ? 0 : -1))
            if (r6 == 0) goto L_0x0191
            if (r0 == 0) goto L_0x016a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.maxVisible
            r51 = r4
            goto L_0x016d
        L_0x016a:
            r51 = r4
            r15 = 0
        L_0x016d:
            r4 = 7
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x017a
            java.lang.Object r4 = r15.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x017b
        L_0x017a:
            r4 = 0
        L_0x017b:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x018c
            if (r4 == 0) goto L_0x0187
            r52 = 536870912(0x20000000, double:2.652494739E-315)
            goto L_0x018a
        L_0x0187:
            r52 = 268435456(0x10000000, double:1.32624737E-315)
        L_0x018a:
            long r2 = r2 | r52
        L_0x018c:
            if (r4 == 0) goto L_0x018f
            goto L_0x0193
        L_0x018f:
            r4 = 4
            goto L_0x0194
        L_0x0191:
            r51 = r4
        L_0x0193:
            r4 = 0
        L_0x0194:
            long r52 = r2 & r32
            r49 = 0
            int r6 = (r52 > r49 ? 1 : (r52 == r49 ? 0 : -1))
            if (r6 == 0) goto L_0x01ce
            if (r0 == 0) goto L_0x01a5
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.btnVisible
            r44 = r4
            r4 = 8
            goto L_0x01aa
        L_0x01a5:
            r44 = r4
            r4 = 8
            r15 = 0
        L_0x01aa:
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x01b6
            java.lang.Object r15 = r15.getValue()
            java.lang.Boolean r15 = (java.lang.Boolean) r15
            goto L_0x01b7
        L_0x01b6:
            r15 = 0
        L_0x01b7:
            boolean r15 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r15)
            if (r6 == 0) goto L_0x01c8
            if (r15 == 0) goto L_0x01c3
            r52 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x01c6
        L_0x01c3:
            r52 = 4194304(0x400000, double:2.0722615E-317)
        L_0x01c6:
            long r2 = r2 | r52
        L_0x01c8:
            if (r15 == 0) goto L_0x01cb
            goto L_0x01d2
        L_0x01cb:
            r6 = 8
            goto L_0x01d3
        L_0x01ce:
            r44 = r4
            r4 = 8
        L_0x01d2:
            r6 = 0
        L_0x01d3:
            r40 = 49664(0xc200, double:2.45373E-319)
            long r52 = r2 & r40
            r49 = 0
            int r15 = (r52 > r49 ? 1 : (r52 == r49 ? 0 : -1))
            if (r15 == 0) goto L_0x020e
            if (r0 == 0) goto L_0x01e5
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.contentIconVisible
            r53 = r5
            goto L_0x01e8
        L_0x01e5:
            r53 = r5
            r4 = 0
        L_0x01e8:
            r5 = 9
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x01f6
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01f7
        L_0x01f6:
            r4 = 0
        L_0x01f7:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x0208
            if (r4 == 0) goto L_0x0203
            r54 = 524288(0x80000, double:2.590327E-318)
            goto L_0x0206
        L_0x0203:
            r54 = 262144(0x40000, double:1.295163E-318)
        L_0x0206:
            long r2 = r2 | r54
        L_0x0208:
            if (r4 == 0) goto L_0x020b
            goto L_0x0210
        L_0x020b:
            r4 = 8
            goto L_0x0211
        L_0x020e:
            r53 = r5
        L_0x0210:
            r4 = 0
        L_0x0211:
            long r54 = r2 & r18
            r49 = 0
            int r5 = (r54 > r49 ? 1 : (r54 == r49 ? 0 : -1))
            if (r5 == 0) goto L_0x024e
            if (r0 == 0) goto L_0x0220
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.normalVisible
            r54 = r4
            goto L_0x0223
        L_0x0220:
            r54 = r4
            r15 = 0
        L_0x0223:
            r4 = 10
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x0231
            java.lang.Object r4 = r15.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0232
        L_0x0231:
            r4 = 0
        L_0x0232:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x0243
            if (r4 == 0) goto L_0x023e
            r55 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x0241
        L_0x023e:
            r55 = 1048576(0x100000, double:5.180654E-318)
        L_0x0241:
            long r2 = r2 | r55
        L_0x0243:
            if (r4 == 0) goto L_0x0248
            r48 = 0
            goto L_0x024a
        L_0x0248:
            r48 = 4
        L_0x024a:
            r4 = 51200(0xc800, double:2.5296E-319)
            goto L_0x0255
        L_0x024e:
            r54 = r4
            r4 = 51200(0xc800, double:2.5296E-319)
            r48 = 0
        L_0x0255:
            long r55 = r2 & r4
            r4 = 0
            int r15 = (r55 > r4 ? 1 : (r55 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x028b
            if (r0 == 0) goto L_0x0262
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.connectBleVisible
            goto L_0x0263
        L_0x0262:
            r4 = 0
        L_0x0263:
            r5 = 11
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0271
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0272
        L_0x0271:
            r4 = 0
        L_0x0272:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x0285
            if (r4 == 0) goto L_0x0280
            r55 = 2147483648(0x80000000, double:1.0609978955E-314)
            goto L_0x0283
        L_0x0280:
            r55 = 1073741824(0x40000000, double:5.304989477E-315)
        L_0x0283:
            long r2 = r2 | r55
        L_0x0285:
            if (r4 == 0) goto L_0x0288
            goto L_0x028b
        L_0x0288:
            r52 = 8
            goto L_0x028d
        L_0x028b:
            r52 = 0
        L_0x028d:
            long r4 = r2 & r26
            r49 = 0
            int r15 = (r4 > r49 ? 1 : (r4 == r49 ? 0 : -1))
            if (r15 == 0) goto L_0x02af
            if (r0 == 0) goto L_0x029a
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r4 = r0.stateIcon
            goto L_0x029b
        L_0x029a:
            r4 = 0
        L_0x029b:
            r5 = 12
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x02a9
            java.lang.Object r4 = r4.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x02aa
        L_0x02a9:
            r4 = 0
        L_0x02aa:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x02b0
        L_0x02af:
            r4 = 0
        L_0x02b0:
            long r55 = r2 & r36
            r49 = 0
            int r5 = (r55 > r49 ? 1 : (r55 == r49 ? 0 : -1))
            if (r5 == 0) goto L_0x02e9
            if (r0 == 0) goto L_0x02bd
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.btnText
            goto L_0x02be
        L_0x02bd:
            r0 = 0
        L_0x02be:
            r5 = 13
            r1.updateLiveDataRegistration(r5, r0)
            if (r0 == 0) goto L_0x02e9
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r38 = r10
            r15 = r13
            r5 = r51
            r57 = r52
            r13 = r7
            r10 = r8
            r7 = r53
            r8 = r6
            r6 = r54
            r58 = r9
            r9 = r0
            r0 = r44
            r44 = r11
            r11 = r58
            r59 = r48
            r48 = r14
            r14 = r59
            goto L_0x031b
        L_0x02e9:
            r38 = r10
            r15 = r13
            r0 = r44
            r5 = r51
            r57 = r52
            r13 = r7
            r10 = r8
            r44 = r11
            r7 = r53
            r8 = r6
            r11 = r9
            r6 = r54
            r9 = 0
            r58 = r48
            r48 = r14
            r14 = r58
            goto L_0x031b
        L_0x0304:
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
            r38 = 0
            r44 = 0
            r47 = 0
            r48 = 0
            r57 = 0
        L_0x031b:
            long r36 = r2 & r36
            r49 = 0
            int r51 = (r36 > r49 ? 1 : (r36 == r49 ? 0 : -1))
            r36 = r15
            if (r51 == 0) goto L_0x032a
            android.widget.Button r15 = r1.ibOk
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r15, r9)
        L_0x032a:
            long r32 = r2 & r32
            int r9 = (r32 > r49 ? 1 : (r32 == r49 ? 0 : -1))
            if (r9 == 0) goto L_0x0335
            android.widget.Button r9 = r1.ibOk
            r9.setVisibility(r8)
        L_0x0335:
            long r8 = r2 & r30
            int r15 = (r8 > r49 ? 1 : (r8 == r49 ? 0 : -1))
            if (r15 == 0) goto L_0x0350
            android.widget.Button r8 = r1.ibOk
            r9 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r8, r10, r9)
            com.eternal.widget.guqiang.Toolbar r8 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onNext(r8, r12)
            com.eternal.widget.guqiang.Toolbar r8 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r8, r11)
            android.widget.TextView r8 = r1.tvHelp
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r8, r13, r9)
        L_0x0350:
            long r8 = r2 & r26
            r10 = 0
            int r12 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r12 == 0) goto L_0x035d
            android.widget.ImageView r8 = r1.imgBluetooth
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r8, r4)
        L_0x035d:
            long r8 = r2 & r22
            int r4 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r4 == 0) goto L_0x0368
            android.widget.ImageView r4 = r1.mboundView2
            r4.setVisibility(r0)
        L_0x0368:
            long r8 = r2 & r18
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0373
            android.widget.ImageView r0 = r1.mboundView3
            r0.setVisibility(r14)
        L_0x0373:
            r8 = 49184(0xc020, double:2.43E-319)
            long r8 = r8 & r2
            int r0 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0380
            android.widget.ImageView r0 = r1.mboundView4
            r0.setVisibility(r5)
        L_0x0380:
            r4 = 49664(0xc200, double:2.45373E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0392
            android.widget.ImageView r0 = r1.mboundView7
            r0.setVisibility(r6)
            android.widget.ImageView r0 = r1.mboundView8
            r0.setVisibility(r6)
        L_0x0392:
            r4 = 49216(0xc040, double:2.4316E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x039f
            android.widget.ProgressBar r0 = r1.pbLoading
            r0.setVisibility(r7)
        L_0x039f:
            long r4 = r2 & r20
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x03ac
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            r13 = r36
            com.eternal.widget.guqiang.ToolbarAdapter.setNextVisible(r0, r13)
        L_0x03ac:
            long r4 = r2 & r16
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x03b9
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            r14 = r48
            com.eternal.widget.guqiang.ToolbarAdapter.setBackVisible(r0, r14)
        L_0x03b9:
            long r4 = r2 & r34
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x03c6
            android.widget.TextView r0 = r1.tvContent
            r4 = r47
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x03c6:
            long r4 = r2 & r24
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x03d3
            android.widget.TextView r0 = r1.tvContent
            r4 = r44
            r0.setVisibility(r4)
        L_0x03d3:
            r4 = 51200(0xc800, double:2.5296E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x03e2
            android.widget.TextView r0 = r1.tvHelp
            r4 = r57
            r0.setVisibility(r4)
        L_0x03e2:
            long r2 = r2 & r28
            int r0 = (r2 > r10 ? 1 : (r2 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x03ef
            android.widget.TextView r0 = r1.tvTitle
            r10 = r38
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x03ef:
            return
        L_0x03f0:
            r0 = move-exception
            monitor-exit(r60)     // Catch:{ all -> 0x03f0 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.device.databinding.ActivityDeviceBindingImpl.executeBindings():void");
    }
}
