package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.VersionModel;
import com.eternal.widget.guqiang.ProgressToolbar;
import com.eternal.widget.guqiang.Toolbar;

public class ActivityVersionBindingImpl extends ActivityVersionBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final Toolbar mboundView1;
    private final ProgressToolbar mboundView2;
    private final TextView mboundView4;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.toolBar, 8);
    }

    public ActivityVersionBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 9, sIncludes, sViewsWithIds));
    }

    private ActivityVersionBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 8, objArr[6], objArr[0], objArr[8], objArr[3], objArr[7]);
        this.mDirtyFlags = -1;
        this.ibNext.setTag((Object) null);
        Toolbar toolbar = objArr[1];
        this.mboundView1 = toolbar;
        toolbar.setTag((Object) null);
        ProgressToolbar progressToolbar = objArr[2];
        this.mboundView2 = progressToolbar;
        progressToolbar.setTag((Object) null);
        TextView textView = objArr[4];
        this.mboundView4 = textView;
        textView.setTag((Object) null);
        TextView textView2 = objArr[5];
        this.mboundView5 = textView2;
        textView2.setTag((Object) null);
        this.root.setTag((Object) null);
        this.tvTip.setTag((Object) null);
        this.txtConnectTime.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 512;
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
        if (C0977BR.versionModel != i) {
            return false;
        }
        setVersionModel((VersionModel) obj);
        return true;
    }

    public void setVersionModel(VersionModel versionModel) {
        this.mVersionModel = versionModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(C0977BR.versionModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeVersionModelTimeVisible((MutableLiveData) obj, i2);
            case 1:
                return onChangeVersionModelTimeText((MutableLiveData) obj, i2);
            case 2:
                return onChangeVersionModelUpdateAvailable((MutableLiveData) obj, i2);
            case 3:
                return onChangeVersionModelSubtitle((MutableLiveData) obj, i2);
            case 4:
                return onChangeVersionModelCurrentVersionText((MutableLiveData) obj, i2);
            case 5:
                return onChangeVersionModelIsAppUpdate((MutableLiveData) obj, i2);
            case 6:
                return onChangeVersionModelTipText((MutableLiveData) obj, i2);
            case 7:
                return onChangeVersionModelVersionText((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeVersionModelTimeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVersionModelTimeText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVersionModelUpdateAvailable(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVersionModelSubtitle(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVersionModelCurrentVersionText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVersionModelIsAppUpdate(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeVersionModelTipText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeVersionModelVersionText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x0119, code lost:
        if (r10 != false) goto L_0x011d;
     */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00a5 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00ce  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x00e8  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x011c  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x0127  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r40 = this;
            r1 = r40
            monitor-enter(r40)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x01f0 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x01f0 }
            monitor-exit(r40)     // Catch:{ all -> 0x01f0 }
            com.eternal.account.model.VersionModel r0 = r1.mVersionModel
            r6 = 1023(0x3ff, double:5.054E-321)
            long r6 = r6 & r2
            r10 = 784(0x310, double:3.873E-321)
            r14 = 776(0x308, double:3.834E-321)
            r16 = 800(0x320, double:3.953E-321)
            r18 = 770(0x302, double:3.804E-321)
            r20 = 768(0x300, double:3.794E-321)
            r22 = 769(0x301, double:3.8E-321)
            r24 = 772(0x304, double:3.814E-321)
            r12 = 0
            int r28 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r28 == 0) goto L_0x0166
            long r6 = r2 & r22
            r28 = 8
            int r29 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r29 == 0) goto L_0x0053
            if (r0 == 0) goto L_0x002f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.timeVisible
            goto L_0x0030
        L_0x002f:
            r6 = 0
        L_0x0030:
            r1.updateLiveDataRegistration(r12, r6)
            if (r6 == 0) goto L_0x003c
            java.lang.Object r6 = r6.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            goto L_0x003d
        L_0x003c:
            r6 = 0
        L_0x003d:
            boolean r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r6)
            if (r29 == 0) goto L_0x004d
            if (r6 == 0) goto L_0x0049
            r29 = 32768(0x8000, double:1.61895E-319)
            goto L_0x004b
        L_0x0049:
            r29 = 16384(0x4000, double:8.0948E-320)
        L_0x004b:
            long r2 = r2 | r29
        L_0x004d:
            if (r6 == 0) goto L_0x0050
            goto L_0x0053
        L_0x0050:
            r6 = 8
            goto L_0x0054
        L_0x0053:
            r6 = 0
        L_0x0054:
            long r29 = r2 & r18
            int r7 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x006d
            if (r0 == 0) goto L_0x005f
            androidx.lifecycle.MutableLiveData<java.lang.String> r7 = r0.timeText
            goto L_0x0060
        L_0x005f:
            r7 = 0
        L_0x0060:
            r13 = 1
            r1.updateLiveDataRegistration(r13, r7)
            if (r7 == 0) goto L_0x006d
            java.lang.Object r7 = r7.getValue()
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x006e
        L_0x006d:
            r7 = 0
        L_0x006e:
            long r30 = r2 & r24
            int r13 = (r30 > r4 ? 1 : (r30 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x009e
            if (r0 == 0) goto L_0x0079
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.updateAvailable
            goto L_0x007a
        L_0x0079:
            r12 = 0
        L_0x007a:
            r8 = 2
            r1.updateLiveDataRegistration(r8, r12)
            if (r12 == 0) goto L_0x0087
            java.lang.Object r8 = r12.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x0088
        L_0x0087:
            r8 = 0
        L_0x0088:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r13 == 0) goto L_0x0098
            if (r8 == 0) goto L_0x0094
            r12 = 131072(0x20000, double:6.47582E-319)
            goto L_0x0097
        L_0x0094:
            r12 = 65536(0x10000, double:3.2379E-319)
        L_0x0097:
            long r2 = r2 | r12
        L_0x0098:
            if (r8 == 0) goto L_0x009b
            goto L_0x009e
        L_0x009b:
            r8 = 8
            goto L_0x009f
        L_0x009e:
            r8 = 0
        L_0x009f:
            long r12 = r2 & r20
            int r9 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00ac
            if (r0 == 0) goto L_0x00ac
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onNext
            goto L_0x00ae
        L_0x00ac:
            r9 = 0
            r12 = 0
        L_0x00ae:
            long r33 = r2 & r14
            int r13 = (r33 > r4 ? 1 : (r33 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00c7
            if (r0 == 0) goto L_0x00b9
            androidx.lifecycle.MutableLiveData<java.lang.String> r13 = r0.subtitle
            goto L_0x00ba
        L_0x00b9:
            r13 = 0
        L_0x00ba:
            r14 = 3
            r1.updateLiveDataRegistration(r14, r13)
            if (r13 == 0) goto L_0x00c7
            java.lang.Object r13 = r13.getValue()
            java.lang.String r13 = (java.lang.String) r13
            goto L_0x00c8
        L_0x00c7:
            r13 = 0
        L_0x00c8:
            long r14 = r2 & r10
            int r35 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r35 == 0) goto L_0x00e1
            if (r0 == 0) goto L_0x00d3
            androidx.lifecycle.MutableLiveData<java.lang.String> r14 = r0.currentVersionText
            goto L_0x00d4
        L_0x00d3:
            r14 = 0
        L_0x00d4:
            r15 = 4
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x00e1
            java.lang.Object r14 = r14.getValue()
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x00e2
        L_0x00e1:
            r14 = 0
        L_0x00e2:
            long r35 = r2 & r16
            int r15 = (r35 > r4 ? 1 : (r35 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x011c
            if (r0 == 0) goto L_0x00ed
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.isAppUpdate
            goto L_0x00ee
        L_0x00ed:
            r10 = 0
        L_0x00ee:
            r11 = 5
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x00fb
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x00fc
        L_0x00fb:
            r10 = 0
        L_0x00fc:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r15 == 0) goto L_0x0113
            if (r10 == 0) goto L_0x010b
            r37 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r37
            r37 = 8192(0x2000, double:4.0474E-320)
            goto L_0x0111
        L_0x010b:
            r37 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r37
            r37 = 4096(0x1000, double:2.0237E-320)
        L_0x0111:
            long r2 = r2 | r37
        L_0x0113:
            if (r10 == 0) goto L_0x0118
            r11 = 8
            goto L_0x0119
        L_0x0118:
            r11 = 0
        L_0x0119:
            if (r10 == 0) goto L_0x011f
            goto L_0x011d
        L_0x011c:
            r11 = 0
        L_0x011d:
            r28 = 0
        L_0x011f:
            r31 = 832(0x340, double:4.11E-321)
            long r37 = r2 & r31
            int r10 = (r37 > r4 ? 1 : (r37 == r4 ? 0 : -1))
            if (r10 == 0) goto L_0x013a
            if (r0 == 0) goto L_0x012c
            androidx.lifecycle.MutableLiveData<java.lang.String> r10 = r0.tipText
            goto L_0x012d
        L_0x012c:
            r10 = 0
        L_0x012d:
            r15 = 6
            r1.updateLiveDataRegistration(r15, r10)
            if (r10 == 0) goto L_0x013a
            java.lang.Object r10 = r10.getValue()
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x013b
        L_0x013a:
            r10 = 0
        L_0x013b:
            r26 = 896(0x380, double:4.427E-321)
            long r37 = r2 & r26
            int r15 = (r37 > r4 ? 1 : (r37 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x015f
            if (r0 == 0) goto L_0x0148
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.versionText
            goto L_0x0149
        L_0x0148:
            r0 = 0
        L_0x0149:
            r15 = 7
            r1.updateLiveDataRegistration(r15, r0)
            if (r0 == 0) goto L_0x015f
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r15 = r28
            r39 = r7
            r7 = r0
            r0 = r13
            r13 = r12
            r12 = r39
            goto L_0x0171
        L_0x015f:
            r0 = r13
            r15 = r28
            r13 = r12
            r12 = r7
            r7 = 0
            goto L_0x0171
        L_0x0166:
            r0 = 0
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
        L_0x0171:
            long r24 = r2 & r24
            int r28 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r28 == 0) goto L_0x0181
            android.widget.Button r4 = r1.ibNext
            r4.setVisibility(r8)
            android.widget.TextView r4 = r1.mboundView5
            r4.setVisibility(r8)
        L_0x0181:
            long r4 = r2 & r20
            r20 = 0
            int r8 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r8 == 0) goto L_0x0199
            android.widget.Button r4 = r1.ibNext
            r5 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r13, r5)
            com.eternal.widget.guqiang.Toolbar r4 = r1.mboundView1
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r4, r9)
            com.eternal.widget.guqiang.ProgressToolbar r4 = r1.mboundView2
            com.eternal.widget.guqiang.ProgressToolbarAdapter.onBack(r4, r9)
        L_0x0199:
            long r4 = r2 & r16
            int r8 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r8 == 0) goto L_0x01a9
            com.eternal.widget.guqiang.Toolbar r4 = r1.mboundView1
            r4.setVisibility(r15)
            com.eternal.widget.guqiang.ProgressToolbar r4 = r1.mboundView2
            r4.setVisibility(r11)
        L_0x01a9:
            r4 = 776(0x308, double:3.834E-321)
            long r4 = r4 & r2
            int r8 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r8 == 0) goto L_0x01b5
            com.eternal.widget.guqiang.ProgressToolbar r4 = r1.mboundView2
            com.eternal.widget.guqiang.ProgressToolbarAdapter.setSubtitle(r4, r0)
        L_0x01b5:
            r4 = 896(0x380, double:4.427E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r0 == 0) goto L_0x01c1
            android.widget.TextView r0 = r1.mboundView4
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x01c1:
            r4 = 784(0x310, double:3.873E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r0 == 0) goto L_0x01cd
            android.widget.TextView r0 = r1.mboundView5
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r14)
        L_0x01cd:
            r4 = 832(0x340, double:4.11E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r0 == 0) goto L_0x01d9
            android.widget.TextView r0 = r1.tvTip
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r10)
        L_0x01d9:
            long r4 = r2 & r18
            int r0 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r0 == 0) goto L_0x01e4
            android.widget.TextView r0 = r1.txtConnectTime
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x01e4:
            long r2 = r2 & r22
            int r0 = (r2 > r20 ? 1 : (r2 == r20 ? 0 : -1))
            if (r0 == 0) goto L_0x01ef
            android.widget.TextView r0 = r1.txtConnectTime
            r0.setVisibility(r6)
        L_0x01ef:
            return
        L_0x01f0:
            r0 = move-exception
            monitor-exit(r40)     // Catch:{ all -> 0x01f0 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityVersionBindingImpl.executeBindings():void");
    }
}
