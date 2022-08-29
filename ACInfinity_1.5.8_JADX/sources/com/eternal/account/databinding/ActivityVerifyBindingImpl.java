package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.VerifyModel;

public class ActivityVerifyBindingImpl extends ActivityVerifyBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final TextView mboundView5;
    private final TextView mboundView7;
    private InverseBindingListener pvCodeandroidTextAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.ll_tip, 8);
        sparseIntArray.put(C0997R.C1000id.tv_tip, 9);
        sparseIntArray.put(C0997R.C1000id.tv_title, 10);
    }

    public ActivityVerifyBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityVerifyBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, objArr[8], objArr[6], objArr[3], objArr[0], objArr[1], objArr[2], objArr[4], objArr[9], objArr[10]);
        this.pvCodeandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityVerifyBindingImpl.this.pvCode);
                VerifyModel verifyModel = ActivityVerifyBindingImpl.this.mVerifyModel;
                boolean z = true;
                if (verifyModel != null) {
                    MutableLiveData<String> mutableLiveData = verifyModel.codeText;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.mDirtyFlags = -1;
        TextView textView = objArr[5];
        this.mboundView5 = textView;
        textView.setTag((Object) null);
        TextView textView2 = objArr[7];
        this.mboundView7 = textView2;
        textView2.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.pvCode.setTag((Object) null);
        this.root.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvEmail.setTag((Object) null);
        this.tvErr.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        if (C0977BR.verifyModel != i) {
            return false;
        }
        setVerifyModel((VerifyModel) obj);
        return true;
    }

    public void setVerifyModel(VerifyModel verifyModel) {
        this.mVerifyModel = verifyModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(C0977BR.verifyModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeVerifyModelErrText((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangeVerifyModelEmailText((MutableLiveData) obj, i2);
        }
        if (i == 2) {
            return onChangeVerifyModelErrVisible((MutableLiveData) obj, i2);
        }
        if (i == 3) {
            return onChangeVerifyModelShowLoading((MutableLiveData) obj, i2);
        }
        if (i == 4) {
            return onChangeVerifyModelTimerText((MutableLiveData) obj, i2);
        }
        if (i != 5) {
            return false;
        }
        return onChangeVerifyModelCodeText((MutableLiveData) obj, i2);
    }

    private boolean onChangeVerifyModelErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeVerifyModelEmailText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVerifyModelErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeVerifyModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeVerifyModelTimerText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeVerifyModelCodeText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0089 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00ef  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x010d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r36 = this;
            r1 = r36
            monitor-enter(r36)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x01ba }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x01ba }
            monitor-exit(r36)     // Catch:{ all -> 0x01ba }
            com.eternal.account.model.VerifyModel r0 = r1.mVerifyModel
            r6 = 255(0xff, double:1.26E-321)
            long r6 = r6 & r2
            r10 = 196(0xc4, double:9.7E-322)
            r12 = 208(0xd0, double:1.03E-321)
            r14 = 194(0xc2, double:9.6E-322)
            r16 = 192(0xc0, double:9.5E-322)
            r18 = 193(0xc1, double:9.54E-322)
            r20 = 200(0xc8, double:9.9E-322)
            r8 = 0
            int r24 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x0122
            long r6 = r2 & r18
            int r24 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x0038
            if (r0 == 0) goto L_0x002b
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.errText
            goto L_0x002c
        L_0x002b:
            r6 = 0
        L_0x002c:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x0038
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0039
        L_0x0038:
            r6 = 0
        L_0x0039:
            long r24 = r2 & r14
            int r7 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0052
            if (r0 == 0) goto L_0x0044
            androidx.lifecycle.MutableLiveData<java.lang.String> r7 = r0.emailText
            goto L_0x0045
        L_0x0044:
            r7 = 0
        L_0x0045:
            r14 = 1
            r1.updateLiveDataRegistration(r14, r7)
            if (r7 == 0) goto L_0x0052
            java.lang.Object r7 = r7.getValue()
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x0053
        L_0x0052:
            r7 = 0
        L_0x0053:
            long r14 = r2 & r10
            int r26 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r26 == 0) goto L_0x0081
            if (r0 == 0) goto L_0x005e
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r14 = r0.errVisible
            goto L_0x005f
        L_0x005e:
            r14 = 0
        L_0x005f:
            r15 = 2
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x006c
            java.lang.Object r14 = r14.getValue()
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L_0x006d
        L_0x006c:
            r14 = 0
        L_0x006d:
            boolean r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r14)
            if (r26 == 0) goto L_0x007c
            if (r14 == 0) goto L_0x0078
            r26 = 512(0x200, double:2.53E-321)
            goto L_0x007a
        L_0x0078:
            r26 = 256(0x100, double:1.265E-321)
        L_0x007a:
            long r2 = r2 | r26
        L_0x007c:
            if (r14 == 0) goto L_0x007f
            goto L_0x0082
        L_0x007f:
            r15 = 4
            goto L_0x0083
        L_0x0081:
            r14 = 0
        L_0x0082:
            r15 = 0
        L_0x0083:
            long r26 = r2 & r16
            int r28 = (r26 > r4 ? 1 : (r26 == r4 ? 0 : -1))
            if (r28 == 0) goto L_0x0092
            if (r0 == 0) goto L_0x0092
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r10 = r0.onSendCode
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r11 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r8 = r0.textChanged
            goto L_0x0095
        L_0x0092:
            r8 = 0
            r10 = 0
            r11 = 0
        L_0x0095:
            long r29 = r2 & r20
            int r31 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r31 == 0) goto L_0x00e4
            if (r0 == 0) goto L_0x00a0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.showLoading
            goto L_0x00a1
        L_0x00a0:
            r9 = 0
        L_0x00a1:
            r4 = 3
            r1.updateLiveDataRegistration(r4, r9)
            if (r9 == 0) goto L_0x00ae
            java.lang.Object r4 = r9.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00af
        L_0x00ae:
            r4 = 0
        L_0x00af:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r31 == 0) goto L_0x00c6
            if (r4 == 0) goto L_0x00be
            r30 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r30
            r30 = 8192(0x2000, double:4.0474E-320)
            goto L_0x00c4
        L_0x00be:
            r30 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r30
            r30 = 4096(0x1000, double:2.0237E-320)
        L_0x00c4:
            long r2 = r2 | r30
        L_0x00c6:
            android.widget.TextView r5 = r1.mboundView5
            if (r4 == 0) goto L_0x00cd
            int r9 = com.eternal.account.C0997R.C0998color.color_838383
            goto L_0x00cf
        L_0x00cd:
            int r9 = com.eternal.account.C0997R.C0998color.color_15BAFF
        L_0x00cf:
            int r5 = getColorFromResource(r5, r9)
            r9 = r4 ^ 1
            if (r4 == 0) goto L_0x00d9
            r4 = 0
            goto L_0x00db
        L_0x00d9:
            r4 = 8
        L_0x00db:
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            boolean r9 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r9)
            goto L_0x00e7
        L_0x00e4:
            r4 = 0
            r5 = 0
            r9 = 0
        L_0x00e7:
            long r30 = r2 & r12
            r32 = 0
            int r34 = (r30 > r32 ? 1 : (r30 == r32 ? 0 : -1))
            if (r34 == 0) goto L_0x0102
            if (r0 == 0) goto L_0x00f4
            androidx.lifecycle.MutableLiveData<java.lang.String> r12 = r0.timerText
            goto L_0x00f5
        L_0x00f4:
            r12 = 0
        L_0x00f5:
            r13 = 4
            r1.updateLiveDataRegistration(r13, r12)
            if (r12 == 0) goto L_0x0102
            java.lang.Object r12 = r12.getValue()
            java.lang.String r12 = (java.lang.String) r12
            goto L_0x0103
        L_0x0102:
            r12 = 0
        L_0x0103:
            r22 = 224(0xe0, double:1.107E-321)
            long r34 = r2 & r22
            r32 = 0
            int r13 = (r34 > r32 ? 1 : (r34 == r32 ? 0 : -1))
            if (r13 == 0) goto L_0x0120
            if (r0 == 0) goto L_0x0112
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.codeText
            goto L_0x0113
        L_0x0112:
            r0 = 0
        L_0x0113:
            r13 = 5
            r1.updateLiveDataRegistration(r13, r0)
            if (r0 == 0) goto L_0x0120
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x012e
        L_0x0120:
            r0 = 0
            goto L_0x012e
        L_0x0122:
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
            r14 = 0
            r15 = 0
        L_0x012e:
            long r20 = r2 & r20
            r32 = 0
            int r13 = (r20 > r32 ? 1 : (r20 == r32 ? 0 : -1))
            if (r13 == 0) goto L_0x014a
            android.widget.TextView r13 = r1.mboundView5
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r13, r5)
            android.widget.TextView r5 = r1.mboundView5
            r5.setEnabled(r9)
            android.widget.TextView r5 = r1.mboundView7
            r5.setVisibility(r4)
            android.widget.ProgressBar r5 = r1.pbLoading
            r5.setVisibility(r4)
        L_0x014a:
            long r4 = r2 & r16
            r16 = 0
            int r9 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r9 == 0) goto L_0x0162
            android.widget.TextView r4 = r1.mboundView5
            r5 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r10, r5)
            com.eternal.widget.pinview.PinView r4 = r1.pvCode
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r4, r8)
            com.eternal.widget.guqiang.Toolbar r4 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r4, r11)
        L_0x0162:
            r4 = 208(0xd0, double:1.03E-321)
            long r4 = r4 & r2
            int r8 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r8 == 0) goto L_0x016e
            android.widget.TextView r4 = r1.mboundView7
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r12)
        L_0x016e:
            r4 = 224(0xe0, double:1.107E-321)
            long r4 = r4 & r2
            int r8 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r8 == 0) goto L_0x017a
            com.eternal.widget.pinview.PinView r4 = r1.pvCode
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r0)
        L_0x017a:
            r4 = 196(0xc4, double:9.7E-322)
            long r4 = r4 & r2
            int r0 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x018b
            com.eternal.widget.pinview.PinView r0 = r1.pvCode
            com.eternal.widget.pinview.PinView.errorColor(r0, r14)
            android.widget.TextView r0 = r1.tvErr
            r0.setVisibility(r15)
        L_0x018b:
            r4 = 128(0x80, double:6.32E-322)
            long r4 = r4 & r2
            int r0 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x01a2
            com.eternal.widget.pinview.PinView r0 = r1.pvCode
            r4 = 0
            r9 = r4
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r9 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r9
            r5 = r4
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r5 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r5
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r4 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r4
            androidx.databinding.InverseBindingListener r8 = r1.pvCodeandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r9, r5, r4, r8)
        L_0x01a2:
            r4 = 194(0xc2, double:9.6E-322)
            long r4 = r4 & r2
            int r0 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x01ae
            android.widget.TextView r0 = r1.tvEmail
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x01ae:
            long r2 = r2 & r18
            int r0 = (r2 > r16 ? 1 : (r2 == r16 ? 0 : -1))
            if (r0 == 0) goto L_0x01b9
            android.widget.TextView r0 = r1.tvErr
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x01b9:
            return
        L_0x01ba:
            r0 = move-exception
            monitor-exit(r36)     // Catch:{ all -> 0x01ba }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityVerifyBindingImpl.executeBindings():void");
    }
}
