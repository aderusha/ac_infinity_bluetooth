package com.eternal.control.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.control.C1669BR;
import com.eternal.control.C1760R;
import com.eternal.control.ControlCModel;

public class FragmentControlCBindingImpl extends FragmentControlCBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView7;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1760R.C1763id.tv_hum_unit, 8);
        sparseIntArray.put(C1760R.C1763id.tv_vpd_unit, 9);
        sparseIntArray.put(C1760R.C1763id.gq_dial, 10);
        sparseIntArray.put(C1760R.C1763id.space, 11);
        sparseIntArray.put(C1760R.C1763id.sc_content, 12);
        sparseIntArray.put(C1760R.C1763id.tv_tmp_title, 13);
        sparseIntArray.put(C1760R.C1763id.layout_tmp, 14);
        sparseIntArray.put(C1760R.C1763id.sb_high_tmp, 15);
        sparseIntArray.put(C1760R.C1763id.sb_low_tmp, 16);
        sparseIntArray.put(C1760R.C1763id.tv_hum_title, 17);
        sparseIntArray.put(C1760R.C1763id.layout_hum, 18);
        sparseIntArray.put(C1760R.C1763id.sb_high_hum, 19);
        sparseIntArray.put(C1760R.C1763id.sb_low_hum, 20);
    }

    public FragmentControlCBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 21, sIncludes, sViewsWithIds));
    }

    private FragmentControlCBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 7, objArr[10], objArr[18], objArr[14], objArr[19], objArr[15], objArr[20], objArr[16], objArr[12], objArr[6], objArr[11], objArr[3], objArr[17], objArr[8], objArr[2], objArr[1], objArr[13], objArr[4], objArr[9], objArr[5]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ImageView imageView = objArr[7];
        this.mboundView7 = imageView;
        imageView.setTag((Object) null);
        this.scOverlay.setTag((Object) null);
        this.tvHum.setTag((Object) null);
        this.tvTempUnit.setTag((Object) null);
        this.tvTmp.setTag((Object) null);
        this.tvVpd.setTag((Object) null);
        this.vMask.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 256;
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
        if (C1669BR.CModel != i) {
            return false;
        }
        setCModel((ControlCModel) obj);
        return true;
    }

    public void setCModel(ControlCModel controlCModel) {
        this.mCModel = controlCModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(C1669BR.CModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeCModelVpdSize((ObservableField) obj, i2);
            case 1:
                return onChangeCModelTmpFlag((ObservableField) obj, i2);
            case 2:
                return onChangeCModelPerSize((ObservableField) obj, i2);
            case 3:
                return onChangeCModelConnected((MutableLiveData) obj, i2);
            case 4:
                return onChangeCModelOverlayVisible((MutableLiveData) obj, i2);
            case 5:
                return onChangeCModelOverlayRes((MutableLiveData) obj, i2);
            case 6:
                return onChangeCModelTmpSize((ObservableField) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeCModelVpdSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeCModelTmpFlag(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeCModelPerSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeCModelConnected(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeCModelOverlayVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeCModelOverlayRes(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeCModelTmpSize(ObservableField<String> observableField, int i) {
        if (i != C1669BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r33 = this;
            r1 = r33
            monitor-enter(r33)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0186 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0186 }
            monitor-exit(r33)     // Catch:{ all -> 0x0186 }
            com.eternal.control.ControlCModel r0 = r1.mCModel
            r6 = 511(0x1ff, double:2.525E-321)
            long r6 = r6 & r2
            r10 = 392(0x188, double:1.937E-321)
            r12 = 388(0x184, double:1.917E-321)
            r14 = 400(0x190, double:1.976E-321)
            r16 = 386(0x182, double:1.907E-321)
            r18 = 384(0x180, double:1.897E-321)
            r20 = 385(0x181, double:1.9E-321)
            r22 = 416(0x1a0, double:2.055E-321)
            r8 = 0
            int r24 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x0121
            long r6 = r2 & r20
            int r24 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x003a
            if (r0 == 0) goto L_0x002d
            androidx.databinding.ObservableField<java.lang.String> r6 = r0.vpdSize
            goto L_0x002e
        L_0x002d:
            r6 = 0
        L_0x002e:
            r1.updateRegistration((int) r8, (androidx.databinding.Observable) r6)
            if (r6 == 0) goto L_0x003a
            java.lang.Object r6 = r6.get()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x003b
        L_0x003a:
            r6 = 0
        L_0x003b:
            long r24 = r2 & r16
            r7 = 1
            int r26 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r26 == 0) goto L_0x0054
            if (r0 == 0) goto L_0x0047
            androidx.databinding.ObservableField<java.lang.String> r9 = r0.tmpFlag
            goto L_0x0048
        L_0x0047:
            r9 = 0
        L_0x0048:
            r1.updateRegistration((int) r7, (androidx.databinding.Observable) r9)
            if (r9 == 0) goto L_0x0054
            java.lang.Object r9 = r9.get()
            java.lang.String r9 = (java.lang.String) r9
            goto L_0x0055
        L_0x0054:
            r9 = 0
        L_0x0055:
            long r25 = r2 & r12
            int r27 = (r25 > r4 ? 1 : (r25 == r4 ? 0 : -1))
            if (r27 == 0) goto L_0x006e
            if (r0 == 0) goto L_0x0060
            androidx.databinding.ObservableField<java.lang.String> r12 = r0.perSize
            goto L_0x0061
        L_0x0060:
            r12 = 0
        L_0x0061:
            r13 = 2
            r1.updateRegistration((int) r13, (androidx.databinding.Observable) r12)
            if (r12 == 0) goto L_0x006e
            java.lang.Object r12 = r12.get()
            java.lang.String r12 = (java.lang.String) r12
            goto L_0x006f
        L_0x006e:
            r12 = 0
        L_0x006f:
            long r27 = r2 & r10
            int r29 = (r27 > r4 ? 1 : (r27 == r4 ? 0 : -1))
            if (r29 == 0) goto L_0x009e
            if (r0 == 0) goto L_0x007a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.connected
            goto L_0x007b
        L_0x007a:
            r13 = 0
        L_0x007b:
            r10 = 3
            r1.updateLiveDataRegistration(r10, r13)
            if (r13 == 0) goto L_0x0088
            java.lang.Object r10 = r13.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x0089
        L_0x0088:
            r10 = 0
        L_0x0089:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            r7 = r7 ^ r10
            if (r29 == 0) goto L_0x0098
            if (r7 == 0) goto L_0x0095
            r10 = 1024(0x400, double:5.06E-321)
            goto L_0x0097
        L_0x0095:
            r10 = 512(0x200, double:2.53E-321)
        L_0x0097:
            long r2 = r2 | r10
        L_0x0098:
            if (r7 == 0) goto L_0x009b
            goto L_0x009e
        L_0x009b:
            r7 = 8
            goto L_0x009f
        L_0x009e:
            r7 = 0
        L_0x009f:
            long r10 = r2 & r18
            int r13 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00aa
            if (r0 == 0) goto L_0x00aa
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r10 = r0.onCloseOverlay
            goto L_0x00ab
        L_0x00aa:
            r10 = 0
        L_0x00ab:
            long r28 = r2 & r14
            int r11 = (r28 > r4 ? 1 : (r28 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00d9
            if (r0 == 0) goto L_0x00b6
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.overlayVisible
            goto L_0x00b7
        L_0x00b6:
            r13 = 0
        L_0x00b7:
            r14 = 4
            r1.updateLiveDataRegistration(r14, r13)
            if (r13 == 0) goto L_0x00c4
            java.lang.Object r13 = r13.getValue()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            goto L_0x00c5
        L_0x00c4:
            r13 = 0
        L_0x00c5:
            boolean r13 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r13)
            if (r11 == 0) goto L_0x00d3
            if (r13 == 0) goto L_0x00d0
            r14 = 4096(0x1000, double:2.0237E-320)
            goto L_0x00d2
        L_0x00d0:
            r14 = 2048(0x800, double:1.0118E-320)
        L_0x00d2:
            long r2 = r2 | r14
        L_0x00d3:
            if (r13 == 0) goto L_0x00d6
            goto L_0x00d9
        L_0x00d6:
            r13 = 8
            goto L_0x00da
        L_0x00d9:
            r13 = 0
        L_0x00da:
            long r14 = r2 & r22
            int r11 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00f9
            if (r0 == 0) goto L_0x00e5
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r11 = r0.overlayRes
            goto L_0x00e6
        L_0x00e5:
            r11 = 0
        L_0x00e6:
            r14 = 5
            r1.updateLiveDataRegistration(r14, r11)
            if (r11 == 0) goto L_0x00f3
            java.lang.Object r11 = r11.getValue()
            java.lang.Integer r11 = (java.lang.Integer) r11
            goto L_0x00f4
        L_0x00f3:
            r11 = 0
        L_0x00f4:
            int r11 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r11)
            goto L_0x00fa
        L_0x00f9:
            r11 = 0
        L_0x00fa:
            r14 = 448(0x1c0, double:2.213E-321)
            long r30 = r2 & r14
            int r14 = (r30 > r4 ? 1 : (r30 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x011c
            if (r0 == 0) goto L_0x0107
            androidx.databinding.ObservableField<java.lang.String> r0 = r0.tmpSize
            goto L_0x0108
        L_0x0107:
            r0 = 0
        L_0x0108:
            r14 = 6
            r1.updateRegistration((int) r14, (androidx.databinding.Observable) r0)
            if (r0 == 0) goto L_0x011c
            java.lang.Object r0 = r0.get()
            java.lang.String r0 = (java.lang.String) r0
            r32 = r6
            r6 = r0
            r0 = r9
            r9 = r10
            r10 = r32
            goto L_0x0129
        L_0x011c:
            r0 = r9
            r9 = r10
            r10 = r6
            r6 = 0
            goto L_0x0129
        L_0x0121:
            r0 = 0
            r6 = 0
            r7 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0129:
            long r14 = r2 & r22
            int r22 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x0134
            android.widget.ImageView r14 = r1.mboundView7
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.glideRes(r14, r11)
        L_0x0134:
            long r14 = r2 & r18
            int r11 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x013f
            android.widget.ImageView r11 = r1.mboundView7
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r11, r9, r8)
        L_0x013f:
            r8 = 400(0x190, double:1.976E-321)
            long r8 = r8 & r2
            int r11 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x014b
            androidx.core.widget.NestedScrollView r8 = r1.scOverlay
            r8.setVisibility(r13)
        L_0x014b:
            r8 = 388(0x184, double:1.917E-321)
            long r8 = r8 & r2
            int r11 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0157
            android.widget.TextView r8 = r1.tvHum
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r8, r12)
        L_0x0157:
            long r8 = r2 & r16
            int r11 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0162
            android.widget.TextView r8 = r1.tvTempUnit
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r8, r0)
        L_0x0162:
            r8 = 448(0x1c0, double:2.213E-321)
            long r8 = r8 & r2
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x016e
            android.widget.TextView r0 = r1.tvTmp
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x016e:
            long r8 = r2 & r20
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0179
            android.widget.TextView r0 = r1.tvVpd
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x0179:
            r8 = 392(0x188, double:1.937E-321)
            long r2 = r2 & r8
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0185
            android.view.View r0 = r1.vMask
            r0.setVisibility(r7)
        L_0x0185:
            return
        L_0x0186:
            r0 = move-exception
            monitor-exit(r33)     // Catch:{ all -> 0x0186 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.control.databinding.FragmentControlCBindingImpl.executeBindings():void");
    }
}
