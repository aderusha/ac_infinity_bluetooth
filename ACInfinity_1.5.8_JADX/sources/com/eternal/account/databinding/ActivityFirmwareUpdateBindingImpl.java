package com.eternal.account.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.FirmwareUpdateModel;

public class ActivityFirmwareUpdateBindingImpl extends ActivityFirmwareUpdateBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final ImageView mboundView2;
    private final ImageView mboundView3;
    private final ImageView mboundView4;
    private final ImageView mboundView5;
    private final TextView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.rl_oval, 10);
    }

    public ActivityFirmwareUpdateBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private ActivityFirmwareUpdateBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 13, objArr[9], objArr[10], objArr[1], objArr[8], objArr[7]);
        this.mDirtyFlags = -1;
        this.ibOk.setTag((Object) null);
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
        ImageView imageView4 = objArr[5];
        this.mboundView5 = imageView4;
        imageView4.setTag((Object) null);
        TextView textView = objArr[6];
        this.mboundView6 = textView;
        textView.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvContent.setTag((Object) null);
        this.tvTitle.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE;
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
        if (C0977BR.firmwareUpdateModel != i) {
            return false;
        }
        setFirmwareUpdateModel((FirmwareUpdateModel) obj);
        return true;
    }

    public void setFirmwareUpdateModel(FirmwareUpdateModel firmwareUpdateModel) {
        this.mFirmwareUpdateModel = firmwareUpdateModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        notifyPropertyChanged(C0977BR.firmwareUpdateModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeFirmwareUpdateModelProgressText((MutableLiveData) obj, i2);
            case 1:
                return onChangeFirmwareUpdateModelContentVisible((MutableLiveData) obj, i2);
            case 2:
                return onChangeFirmwareUpdateModelNormalVisible((MutableLiveData) obj, i2);
            case 3:
                return onChangeFirmwareUpdateModelStateText((MutableLiveData) obj, i2);
            case 4:
                return onChangeFirmwareUpdateModelProgressVisible((MutableLiveData) obj, i2);
            case 5:
                return onChangeFirmwareUpdateModelContentText((MutableLiveData) obj, i2);
            case 6:
                return onChangeFirmwareUpdateModelBtnText((MutableLiveData) obj, i2);
            case 7:
                return onChangeFirmwareUpdateModelCancelVisible((MutableLiveData) obj, i2);
            case 8:
                return onChangeFirmwareUpdateModelMinVisible((MutableLiveData) obj, i2);
            case 9:
                return onChangeFirmwareUpdateModelMaxVisible((MutableLiveData) obj, i2);
            case 10:
                return onChangeFirmwareUpdateModelBtnVisible((MutableLiveData) obj, i2);
            case 11:
                return onChangeFirmwareUpdateModelState((MutableLiveData) obj, i2);
            case 12:
                return onChangeFirmwareUpdateModelSubtitle((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeFirmwareUpdateModelProgressText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelContentVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelNormalVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelStateText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelProgressVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelContentText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelBtnText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelCancelVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelMinVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelMaxVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelBtnVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelState(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeFirmwareUpdateModelSubtitle(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01a4  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x021d  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0229  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0243  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0102  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x010a A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0119  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0133  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r56 = this;
            r1 = r56
            monitor-enter(r56)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0357 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0357 }
            monitor-exit(r56)     // Catch:{ all -> 0x0357 }
            com.eternal.account.model.FirmwareUpdateModel r0 = r1.mFirmwareUpdateModel
            r6 = 32767(0x7fff, double:1.6189E-319)
            long r6 = r6 & r2
            r12 = 24608(0x6020, double:1.2158E-319)
            r16 = 24832(0x6100, double:1.22686E-319)
            r18 = 24592(0x6010, double:1.215E-319)
            r20 = 24584(0x6008, double:1.2146E-319)
            r22 = 25088(0x6200, double:1.2395E-319)
            r24 = 24580(0x6004, double:1.2144E-319)
            r26 = 24576(0x6000, double:1.2142E-319)
            r28 = 24578(0x6002, double:1.2143E-319)
            r30 = 25600(0x6400, double:1.2648E-319)
            r32 = 24577(0x6001, double:1.21427E-319)
            r34 = 24640(0x6040, double:1.2174E-319)
            r8 = 0
            int r38 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r38 == 0) goto L_0x028e
            long r6 = r2 & r32
            int r38 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r38 == 0) goto L_0x0042
            if (r0 == 0) goto L_0x0035
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.progressText
            goto L_0x0036
        L_0x0035:
            r6 = 0
        L_0x0036:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x0042
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0043
        L_0x0042:
            r6 = 0
        L_0x0043:
            long r38 = r2 & r28
            int r40 = (r38 > r4 ? 1 : (r38 == r4 ? 0 : -1))
            if (r40 == 0) goto L_0x0074
            if (r0 == 0) goto L_0x004e
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.contentVisible
            goto L_0x004f
        L_0x004e:
            r9 = 0
        L_0x004f:
            r8 = 1
            r1.updateLiveDataRegistration(r8, r9)
            if (r9 == 0) goto L_0x005c
            java.lang.Object r8 = r9.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x005d
        L_0x005c:
            r8 = 0
        L_0x005d:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r40 == 0) goto L_0x006e
            if (r8 == 0) goto L_0x0069
            r40 = 268435456(0x10000000, double:1.32624737E-315)
            goto L_0x006c
        L_0x0069:
            r40 = 134217728(0x8000000, double:6.63123685E-316)
        L_0x006c:
            long r2 = r2 | r40
        L_0x006e:
            if (r8 == 0) goto L_0x0071
            goto L_0x0074
        L_0x0071:
            r8 = 8
            goto L_0x0075
        L_0x0074:
            r8 = 0
        L_0x0075:
            long r40 = r2 & r24
            r9 = 4
            int r42 = (r40 > r4 ? 1 : (r40 == r4 ? 0 : -1))
            if (r42 == 0) goto L_0x00a6
            if (r0 == 0) goto L_0x0081
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r14 = r0.normalVisible
            goto L_0x0082
        L_0x0081:
            r14 = 0
        L_0x0082:
            r15 = 2
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x008f
            java.lang.Object r14 = r14.getValue()
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L_0x0090
        L_0x008f:
            r14 = 0
        L_0x0090:
            boolean r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r14)
            if (r42 == 0) goto L_0x00a1
            if (r14 == 0) goto L_0x009c
            r42 = 262144(0x40000, double:1.295163E-318)
            goto L_0x009f
        L_0x009c:
            r42 = 131072(0x20000, double:6.47582E-319)
        L_0x009f:
            long r2 = r2 | r42
        L_0x00a1:
            if (r14 == 0) goto L_0x00a4
            goto L_0x00a6
        L_0x00a4:
            r14 = 4
            goto L_0x00a7
        L_0x00a6:
            r14 = 0
        L_0x00a7:
            long r42 = r2 & r20
            int r15 = (r42 > r4 ? 1 : (r42 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00c0
            if (r0 == 0) goto L_0x00b2
            androidx.lifecycle.MutableLiveData<java.lang.String> r15 = r0.stateText
            goto L_0x00b3
        L_0x00b2:
            r15 = 0
        L_0x00b3:
            r7 = 3
            r1.updateLiveDataRegistration(r7, r15)
            if (r15 == 0) goto L_0x00c0
            java.lang.Object r7 = r15.getValue()
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x00c1
        L_0x00c0:
            r7 = 0
        L_0x00c1:
            long r43 = r2 & r18
            int r15 = (r43 > r4 ? 1 : (r43 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0102
            if (r0 == 0) goto L_0x00cc
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.progressVisible
            goto L_0x00cd
        L_0x00cc:
            r10 = 0
        L_0x00cd:
            r1.updateLiveDataRegistration(r9, r10)
            if (r10 == 0) goto L_0x00d9
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x00da
        L_0x00d9:
            r10 = 0
        L_0x00da:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r15 == 0) goto L_0x00f5
            if (r10 == 0) goto L_0x00eb
            r45 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r45
            r45 = 16777216(0x1000000, double:8.289046E-317)
            goto L_0x00f3
        L_0x00eb:
            r45 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r45
            r45 = 8388608(0x800000, double:4.144523E-317)
        L_0x00f3:
            long r2 = r2 | r45
        L_0x00f5:
            if (r10 == 0) goto L_0x00fa
            r11 = 8
            goto L_0x00fb
        L_0x00fa:
            r11 = 0
        L_0x00fb:
            if (r10 == 0) goto L_0x00ff
            r10 = 0
            goto L_0x0104
        L_0x00ff:
            r10 = 8
            goto L_0x0104
        L_0x0102:
            r10 = 0
            r11 = 0
        L_0x0104:
            long r45 = r2 & r26
            int r15 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0111
            if (r0 == 0) goto L_0x0111
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onConfirm
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onCancel
            goto L_0x0113
        L_0x0111:
            r9 = 0
            r15 = 0
        L_0x0113:
            long r46 = r2 & r12
            int r48 = (r46 > r4 ? 1 : (r46 == r4 ? 0 : -1))
            if (r48 == 0) goto L_0x012c
            if (r0 == 0) goto L_0x011e
            androidx.lifecycle.MutableLiveData<java.lang.String> r12 = r0.contentText
            goto L_0x011f
        L_0x011e:
            r12 = 0
        L_0x011f:
            r13 = 5
            r1.updateLiveDataRegistration(r13, r12)
            if (r12 == 0) goto L_0x012c
            java.lang.Object r12 = r12.getValue()
            java.lang.String r12 = (java.lang.String) r12
            goto L_0x012d
        L_0x012c:
            r12 = 0
        L_0x012d:
            long r48 = r2 & r34
            int r13 = (r48 > r4 ? 1 : (r48 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0146
            if (r0 == 0) goto L_0x0138
            androidx.lifecycle.MutableLiveData<java.lang.String> r13 = r0.btnText
            goto L_0x0139
        L_0x0138:
            r13 = 0
        L_0x0139:
            r4 = 6
            r1.updateLiveDataRegistration(r4, r13)
            if (r13 == 0) goto L_0x0146
            java.lang.Object r4 = r13.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0147
        L_0x0146:
            r4 = 0
        L_0x0147:
            r43 = 24704(0x6080, double:1.22054E-319)
            long r50 = r2 & r43
            r48 = 0
            int r5 = (r50 > r48 ? 1 : (r50 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x016a
            if (r0 == 0) goto L_0x0156
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.cancelVisible
            goto L_0x0157
        L_0x0156:
            r5 = 0
        L_0x0157:
            r13 = 7
            r1.updateLiveDataRegistration(r13, r5)
            if (r5 == 0) goto L_0x0164
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x0165
        L_0x0164:
            r5 = 0
        L_0x0165:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            goto L_0x016b
        L_0x016a:
            r5 = 0
        L_0x016b:
            long r50 = r2 & r16
            r48 = 0
            int r13 = (r50 > r48 ? 1 : (r50 == r48 ? 0 : -1))
            r50 = r4
            if (r13 == 0) goto L_0x01a4
            if (r0 == 0) goto L_0x017c
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.minVisible
            r42 = r5
            goto L_0x017f
        L_0x017c:
            r42 = r5
            r4 = 0
        L_0x017f:
            r5 = 8
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x018d
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x018e
        L_0x018d:
            r4 = 0
        L_0x018e:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r13 == 0) goto L_0x019f
            if (r4 == 0) goto L_0x019a
            r51 = 67108864(0x4000000, double:3.31561842E-316)
            goto L_0x019d
        L_0x019a:
            r51 = 33554432(0x2000000, double:1.6578092E-316)
        L_0x019d:
            long r2 = r2 | r51
        L_0x019f:
            if (r4 == 0) goto L_0x01a2
            goto L_0x01a8
        L_0x01a2:
            r4 = 4
            goto L_0x01a9
        L_0x01a4:
            r42 = r5
            r5 = 8
        L_0x01a8:
            r4 = 0
        L_0x01a9:
            long r51 = r2 & r22
            r48 = 0
            int r13 = (r51 > r48 ? 1 : (r51 == r48 ? 0 : -1))
            if (r13 == 0) goto L_0x01e1
            if (r0 == 0) goto L_0x01b8
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.maxVisible
            r52 = r4
            goto L_0x01bb
        L_0x01b8:
            r52 = r4
            r5 = 0
        L_0x01bb:
            r4 = 9
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x01c9
            java.lang.Object r4 = r5.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01ca
        L_0x01c9:
            r4 = 0
        L_0x01ca:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r13 == 0) goto L_0x01db
            if (r4 == 0) goto L_0x01d6
            r53 = 4194304(0x400000, double:2.0722615E-317)
            goto L_0x01d9
        L_0x01d6:
            r53 = 2097152(0x200000, double:1.0361308E-317)
        L_0x01d9:
            long r2 = r2 | r53
        L_0x01db:
            if (r4 == 0) goto L_0x01de
            goto L_0x01e3
        L_0x01de:
            r45 = 4
            goto L_0x01e5
        L_0x01e1:
            r52 = r4
        L_0x01e3:
            r45 = 0
        L_0x01e5:
            long r4 = r2 & r30
            r48 = 0
            int r13 = (r4 > r48 ? 1 : (r4 == r48 ? 0 : -1))
            if (r13 == 0) goto L_0x021d
            if (r0 == 0) goto L_0x01f2
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.btnVisible
            goto L_0x01f3
        L_0x01f2:
            r4 = 0
        L_0x01f3:
            r5 = 10
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0201
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0202
        L_0x0201:
            r4 = 0
        L_0x0202:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r13 == 0) goto L_0x0213
            if (r4 == 0) goto L_0x020e
            r53 = 1048576(0x100000, double:5.180654E-318)
            goto L_0x0211
        L_0x020e:
            r53 = 524288(0x80000, double:2.590327E-318)
        L_0x0211:
            long r2 = r2 | r53
        L_0x0213:
            if (r4 == 0) goto L_0x0218
            r51 = 0
            goto L_0x021a
        L_0x0218:
            r51 = 8
        L_0x021a:
            r4 = 26624(0x6800, double:1.3154E-319)
            goto L_0x0221
        L_0x021d:
            r4 = 26624(0x6800, double:1.3154E-319)
            r51 = 0
        L_0x0221:
            long r53 = r2 & r4
            r4 = 0
            int r13 = (r53 > r4 ? 1 : (r53 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0243
            if (r0 == 0) goto L_0x022e
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r4 = r0.state
            goto L_0x022f
        L_0x022e:
            r4 = 0
        L_0x022f:
            r5 = 11
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x023d
            java.lang.Object r4 = r4.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x023e
        L_0x023d:
            r4 = 0
        L_0x023e:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x0244
        L_0x0243:
            r4 = 0
        L_0x0244:
            r36 = 28672(0x7000, double:1.4166E-319)
            long r53 = r2 & r36
            r48 = 0
            int r5 = (r53 > r48 ? 1 : (r53 == r48 ? 0 : -1))
            if (r5 == 0) goto L_0x0273
            if (r0 == 0) goto L_0x0253
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.subtitle
            goto L_0x0254
        L_0x0253:
            r0 = 0
        L_0x0254:
            r5 = 12
            r1.updateLiveDataRegistration(r5, r0)
            if (r0 == 0) goto L_0x0273
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r38 = r7
            r13 = r10
            r5 = r42
            r10 = r0
            r0 = r4
            r7 = r6
            r42 = r8
            r6 = r9
            r8 = r45
            r9 = r50
            r4 = r52
            goto L_0x0284
        L_0x0273:
            r0 = r4
            r38 = r7
            r13 = r10
            r5 = r42
            r4 = r52
            r10 = 0
            r7 = r6
            r42 = r8
            r6 = r9
            r8 = r45
            r9 = r50
        L_0x0284:
            r45 = r12
            r12 = r51
            r55 = r14
            r14 = r11
            r11 = r55
            goto L_0x02a1
        L_0x028e:
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
            r42 = 0
            r45 = 0
        L_0x02a1:
            long r34 = r2 & r34
            r48 = 0
            int r50 = (r34 > r48 ? 1 : (r34 == r48 ? 0 : -1))
            r34 = r10
            if (r50 == 0) goto L_0x02b0
            android.widget.Button r10 = r1.ibOk
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r10, r9)
        L_0x02b0:
            long r9 = r2 & r30
            int r30 = (r9 > r48 ? 1 : (r9 == r48 ? 0 : -1))
            if (r30 == 0) goto L_0x02bb
            android.widget.Button r9 = r1.ibOk
            r9.setVisibility(r12)
        L_0x02bb:
            long r9 = r2 & r26
            int r12 = (r9 > r48 ? 1 : (r9 == r48 ? 0 : -1))
            if (r12 == 0) goto L_0x02cc
            android.widget.Button r9 = r1.ibOk
            r10 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r9, r15, r10)
            com.eternal.widget.guqiang.ProgressToolbar r9 = r1.toolBar
            com.eternal.widget.guqiang.ProgressToolbarAdapter.onNext(r9, r6)
        L_0x02cc:
            long r9 = r2 & r22
            int r6 = (r9 > r48 ? 1 : (r9 == r48 ? 0 : -1))
            if (r6 == 0) goto L_0x02d7
            android.widget.ImageView r6 = r1.mboundView2
            r6.setVisibility(r8)
        L_0x02d7:
            long r8 = r2 & r24
            int r6 = (r8 > r48 ? 1 : (r8 == r48 ? 0 : -1))
            if (r6 == 0) goto L_0x02e2
            android.widget.ImageView r6 = r1.mboundView3
            r6.setVisibility(r11)
        L_0x02e2:
            long r8 = r2 & r16
            int r6 = (r8 > r48 ? 1 : (r8 == r48 ? 0 : -1))
            if (r6 == 0) goto L_0x02ed
            android.widget.ImageView r6 = r1.mboundView4
            r6.setVisibility(r4)
        L_0x02ed:
            r8 = 26624(0x6800, double:1.3154E-319)
            long r8 = r8 & r2
            int r4 = (r8 > r48 ? 1 : (r8 == r48 ? 0 : -1))
            if (r4 == 0) goto L_0x02f9
            android.widget.ImageView r4 = r1.mboundView5
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r4, r0)
        L_0x02f9:
            long r8 = r2 & r18
            int r0 = (r8 > r48 ? 1 : (r8 == r48 ? 0 : -1))
            if (r0 == 0) goto L_0x0309
            android.widget.ImageView r0 = r1.mboundView5
            r0.setVisibility(r14)
            android.widget.TextView r0 = r1.mboundView6
            r0.setVisibility(r13)
        L_0x0309:
            long r8 = r2 & r32
            int r0 = (r8 > r48 ? 1 : (r8 == r48 ? 0 : -1))
            if (r0 == 0) goto L_0x0314
            android.widget.TextView r0 = r1.mboundView6
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x0314:
            r6 = 24704(0x6080, double:1.22054E-319)
            long r6 = r6 & r2
            int r0 = (r6 > r48 ? 1 : (r6 == r48 ? 0 : -1))
            if (r0 == 0) goto L_0x0320
            com.eternal.widget.guqiang.ProgressToolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ProgressToolbarAdapter.setNextVisible(r0, r5)
        L_0x0320:
            r4 = 28672(0x7000, double:1.4166E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r48 ? 1 : (r4 == r48 ? 0 : -1))
            if (r0 == 0) goto L_0x032e
            com.eternal.widget.guqiang.ProgressToolbar r0 = r1.toolBar
            r4 = r34
            com.eternal.widget.guqiang.ProgressToolbarAdapter.setSubtitle(r0, r4)
        L_0x032e:
            r4 = 24608(0x6020, double:1.2158E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r48 ? 1 : (r4 == r48 ? 0 : -1))
            if (r0 == 0) goto L_0x033c
            android.widget.TextView r0 = r1.tvContent
            r12 = r45
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x033c:
            long r4 = r2 & r28
            int r0 = (r4 > r48 ? 1 : (r4 == r48 ? 0 : -1))
            if (r0 == 0) goto L_0x0349
            android.widget.TextView r0 = r1.tvContent
            r8 = r42
            r0.setVisibility(r8)
        L_0x0349:
            long r2 = r2 & r20
            int r0 = (r2 > r48 ? 1 : (r2 == r48 ? 0 : -1))
            if (r0 == 0) goto L_0x0356
            android.widget.TextView r0 = r1.tvTitle
            r7 = r38
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x0356:
            return
        L_0x0357:
            r0 = move-exception
            monitor-exit(r56)     // Catch:{ all -> 0x0357 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityFirmwareUpdateBindingImpl.executeBindings():void");
    }
}
