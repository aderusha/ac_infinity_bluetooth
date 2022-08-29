package com.eternal.device.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.GuideModel;
import com.eternal.widget.guqiang.Toolbar;

public class ActivityGuideBindingImpl extends ActivityGuideBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final RelativeLayout mboundView1;
    private final Toolbar mboundView2;
    private final Button mboundView3;
    private final LinearLayout mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.tv_title_permission, 9);
        sparseIntArray.put(C1922R.C1925id.iv_tip, 10);
        sparseIntArray.put(C1922R.C1925id.tv_title, 11);
        sparseIntArray.put(C1922R.C1925id.tv_content, 12);
    }

    public ActivityGuideBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityGuideBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, objArr[7], objArr[10], objArr[8], objArr[5], objArr[12], objArr[6], objArr[11], objArr[9]);
        this.mDirtyFlags = -1;
        this.ibNext.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[1];
        this.mboundView1 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        Toolbar toolbar = objArr[2];
        this.mboundView2 = toolbar;
        toolbar.setTag((Object) null);
        Button button = objArr[3];
        this.mboundView3 = button;
        button.setTag((Object) null);
        LinearLayout linearLayout = objArr[4];
        this.mboundView4 = linearLayout;
        linearLayout.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvHelp.setTag((Object) null);
        View view2 = view;
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (C1909BR.guideModel != i) {
            return false;
        }
        setGuideModel((GuideModel) obj);
        return true;
    }

    public void setGuideModel(GuideModel guideModel) {
        this.mGuideModel = guideModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(C1909BR.guideModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeGuideModelHelpVisible((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangeGuideModelShowLoading((MutableLiveData) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeGuideModelPermissionVisible((MutableLiveData) obj, i2);
    }

    private boolean onChangeGuideModelHelpVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeGuideModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeGuideModelPermissionVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00ed  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r24 = this;
            r1 = r24
            monitor-enter(r24)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0151 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0151 }
            monitor-exit(r24)     // Catch:{ all -> 0x0151 }
            com.eternal.device.model.GuideModel r0 = r1.mGuideModel
            r6 = 31
            long r6 = r6 & r2
            r10 = 25
            r12 = 24
            r14 = 26
            r16 = 0
            r8 = 0
            int r9 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00f2
            long r6 = r2 & r12
            int r9 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x002f
            if (r0 == 0) goto L_0x002f
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r6 = r0.onCancel
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onSetting
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onNext
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onHelp
            goto L_0x0035
        L_0x002f:
            r6 = r16
            r7 = r6
            r9 = r7
            r12 = r9
            r13 = r12
        L_0x0035:
            long r17 = r2 & r10
            r19 = 8
            int r20 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x0067
            if (r0 == 0) goto L_0x0042
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.helpVisible
            goto L_0x0044
        L_0x0042:
            r10 = r16
        L_0x0044:
            r1.updateLiveDataRegistration(r8, r10)
            if (r10 == 0) goto L_0x0050
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x0052
        L_0x0050:
            r10 = r16
        L_0x0052:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r20 == 0) goto L_0x0061
            if (r10 == 0) goto L_0x005d
            r20 = 64
            goto L_0x005f
        L_0x005d:
            r20 = 32
        L_0x005f:
            long r2 = r2 | r20
        L_0x0061:
            if (r10 == 0) goto L_0x0064
            goto L_0x0067
        L_0x0064:
            r10 = 8
            goto L_0x0068
        L_0x0067:
            r10 = 0
        L_0x0068:
            long r20 = r2 & r14
            int r11 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00ac
            if (r0 == 0) goto L_0x0073
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.showLoading
            goto L_0x0075
        L_0x0073:
            r8 = r16
        L_0x0075:
            r14 = 1
            r1.updateLiveDataRegistration(r14, r8)
            if (r8 == 0) goto L_0x0082
            java.lang.Object r8 = r8.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x0084
        L_0x0082:
            r8 = r16
        L_0x0084:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r11 == 0) goto L_0x0098
            if (r8 == 0) goto L_0x0092
            r14 = 256(0x100, double:1.265E-321)
            long r2 = r2 | r14
            r14 = 1024(0x400, double:5.06E-321)
            goto L_0x0097
        L_0x0092:
            r14 = 128(0x80, double:6.32E-322)
            long r2 = r2 | r14
            r14 = 512(0x200, double:2.53E-321)
        L_0x0097:
            long r2 = r2 | r14
        L_0x0098:
            android.widget.Button r11 = r1.ibNext
            if (r8 == 0) goto L_0x009f
            int r14 = com.eternal.device.C1922R.C1923color.color_transparency
            goto L_0x00a1
        L_0x009f:
            int r14 = com.eternal.device.C1922R.C1923color.white
        L_0x00a1:
            int r11 = getColorFromResource(r11, r14)
            if (r8 == 0) goto L_0x00a9
            r8 = 0
            goto L_0x00ae
        L_0x00a9:
            r8 = 8
            goto L_0x00ae
        L_0x00ac:
            r8 = 0
            r11 = 0
        L_0x00ae:
            r14 = 28
            long r22 = r2 & r14
            int r14 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x00ed
            if (r0 == 0) goto L_0x00bb
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.permissionVisible
            goto L_0x00bd
        L_0x00bb:
            r0 = r16
        L_0x00bd:
            r15 = 2
            r1.updateLiveDataRegistration(r15, r0)
            if (r0 == 0) goto L_0x00cb
            java.lang.Object r0 = r0.getValue()
            r16 = r0
            java.lang.Boolean r16 = (java.lang.Boolean) r16
        L_0x00cb:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r16)
            if (r14 == 0) goto L_0x00df
            if (r0 == 0) goto L_0x00d9
            r14 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r14
            r14 = 16384(0x4000, double:8.0948E-320)
            goto L_0x00de
        L_0x00d9:
            r14 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r14
            r14 = 8192(0x2000, double:4.0474E-320)
        L_0x00de:
            long r2 = r2 | r14
        L_0x00df:
            if (r0 == 0) goto L_0x00e3
            r14 = 0
            goto L_0x00e5
        L_0x00e3:
            r14 = 8
        L_0x00e5:
            if (r0 == 0) goto L_0x00e8
            goto L_0x00ea
        L_0x00e8:
            r19 = 0
        L_0x00ea:
            r0 = r19
            goto L_0x00ef
        L_0x00ed:
            r0 = 0
            r14 = 0
        L_0x00ef:
            r15 = 26
            goto L_0x00fe
        L_0x00f2:
            r6 = r16
            r7 = r6
            r9 = r7
            r12 = r9
            r13 = r12
            r0 = 0
            r8 = 0
            r10 = 0
            r11 = 0
            r15 = r14
            r14 = 0
        L_0x00fe:
            long r15 = r15 & r2
            int r19 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r19 == 0) goto L_0x010d
            android.widget.Button r15 = r1.ibNext
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r15, r11)
            android.widget.ProgressBar r11 = r1.pbLoading
            r11.setVisibility(r8)
        L_0x010d:
            r15 = 24
            long r15 = r15 & r2
            int r8 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x0133
            android.widget.Button r8 = r1.ibNext
            r11 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r8, r9, r11)
            com.eternal.widget.guqiang.Toolbar r8 = r1.mboundView2
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r8, r12)
            android.widget.Button r8 = r1.mboundView3
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r8, r7, r11)
            com.eternal.widget.guqiang.Toolbar r7 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r7, r12)
            com.eternal.widget.guqiang.Toolbar r7 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onNext(r7, r6)
            android.widget.TextView r6 = r1.tvHelp
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r6, r13, r11)
        L_0x0133:
            r6 = 28
            long r6 = r6 & r2
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x0144
            android.widget.RelativeLayout r6 = r1.mboundView1
            r6.setVisibility(r14)
            android.widget.LinearLayout r6 = r1.mboundView4
            r6.setVisibility(r0)
        L_0x0144:
            r6 = 25
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0150
            android.widget.TextView r0 = r1.tvHelp
            r0.setVisibility(r10)
        L_0x0150:
            return
        L_0x0151:
            r0 = move-exception
            monitor-exit(r24)     // Catch:{ all -> 0x0151 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.device.databinding.ActivityGuideBindingImpl.executeBindings():void");
    }
}
