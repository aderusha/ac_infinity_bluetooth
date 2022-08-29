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
import com.eternal.account.model.ResetPasswordModel;

public class ActivityResetPasswordBindingImpl extends ActivityResetPasswordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etEmailandroidTextAttrChanged;
    private long mDirtyFlags;
    private final View mboundView4;
    private final TextView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.tv_tip, 8);
        sparseIntArray.put(C0997R.C1000id.create_email, 9);
    }

    public ActivityResetPasswordBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityResetPasswordBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 7, objArr[9], objArr[2], objArr[3], objArr[6], objArr[7], objArr[0], objArr[1], objArr[8]);
        this.etEmailandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityResetPasswordBindingImpl.this.etEmail);
                ResetPasswordModel resetPasswordModel = ActivityResetPasswordBindingImpl.this.mResetModel;
                boolean z = true;
                if (resetPasswordModel != null) {
                    MutableLiveData<String> mutableLiveData = resetPasswordModel.emailText;
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
        this.createEmailTitle.setTag((Object) null);
        this.etEmail.setTag((Object) null);
        this.ibNext.setTag((Object) null);
        View view2 = objArr[4];
        this.mboundView4 = view2;
        view2.setTag((Object) null);
        TextView textView = objArr[5];
        this.mboundView5 = textView;
        textView.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.root.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        View view3 = view;
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
        if (C0977BR.resetModel != i) {
            return false;
        }
        setResetModel((ResetPasswordModel) obj);
        return true;
    }

    public void setResetModel(ResetPasswordModel resetPasswordModel) {
        this.mResetModel = resetPasswordModel;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(C0977BR.resetModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeResetModelEmailLineColor((MutableLiveData) obj, i2);
            case 1:
                return onChangeResetModelEmailErrText((MutableLiveData) obj, i2);
            case 2:
                return onChangeResetModelConfirmAble((MutableLiveData) obj, i2);
            case 3:
                return onChangeResetModelShowLoading((MutableLiveData) obj, i2);
            case 4:
                return onChangeResetModelEmailText((MutableLiveData) obj, i2);
            case 5:
                return onChangeResetModelEmailErrVisible((MutableLiveData) obj, i2);
            case 6:
                return onChangeResetModelEmailColor((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeResetModelEmailLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeResetModelEmailErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeResetModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeResetModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeResetModelEmailText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeResetModelEmailErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeResetModelEmailColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0117  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r38 = this;
            r1 = r38
            monitor-enter(r38)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x020a }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x020a }
            monitor-exit(r38)     // Catch:{ all -> 0x020a }
            com.eternal.account.model.ResetPasswordModel r0 = r1.mResetModel
            r6 = 511(0x1ff, double:2.525E-321)
            long r6 = r6 & r2
            r10 = 392(0x188, double:1.937E-321)
            r12 = 388(0x184, double:1.917E-321)
            r14 = 386(0x182, double:1.907E-321)
            r16 = 400(0x190, double:1.976E-321)
            r18 = 384(0x180, double:1.897E-321)
            r20 = 385(0x181, double:1.9E-321)
            r22 = 448(0x1c0, double:2.213E-321)
            r8 = 0
            int r26 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r26 == 0) goto L_0x016e
            long r6 = r2 & r20
            int r26 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r26 == 0) goto L_0x0040
            if (r0 == 0) goto L_0x002d
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r6 = r0.emailLineColor
            goto L_0x002e
        L_0x002d:
            r6 = 0
        L_0x002e:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x003a
            java.lang.Object r6 = r6.getValue()
            java.lang.Integer r6 = (java.lang.Integer) r6
            goto L_0x003b
        L_0x003a:
            r6 = 0
        L_0x003b:
            int r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r6)
            goto L_0x0041
        L_0x0040:
            r6 = 0
        L_0x0041:
            long r26 = r2 & r18
            int r7 = (r26 > r4 ? 1 : (r26 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0050
            if (r0 == 0) goto L_0x0050
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r7 = r0.emailTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r8 = r0.onNext
            goto L_0x0053
        L_0x0050:
            r7 = 0
            r8 = 0
            r9 = 0
        L_0x0053:
            long r28 = r2 & r14
            int r30 = (r28 > r4 ? 1 : (r28 == r4 ? 0 : -1))
            if (r30 == 0) goto L_0x006c
            if (r0 == 0) goto L_0x005e
            androidx.lifecycle.MutableLiveData<java.lang.String> r14 = r0.emailErrText
            goto L_0x005f
        L_0x005e:
            r14 = 0
        L_0x005f:
            r15 = 1
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x006c
            java.lang.Object r14 = r14.getValue()
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x006d
        L_0x006c:
            r14 = 0
        L_0x006d:
            long r30 = r2 & r12
            int r15 = (r30 > r4 ? 1 : (r30 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00aa
            if (r0 == 0) goto L_0x0078
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.confirmAble
            goto L_0x0079
        L_0x0078:
            r12 = 0
        L_0x0079:
            r13 = 2
            r1.updateLiveDataRegistration(r13, r12)
            if (r12 == 0) goto L_0x0086
            java.lang.Object r12 = r12.getValue()
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            goto L_0x0087
        L_0x0086:
            r12 = 0
        L_0x0087:
            boolean r12 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r12)
            if (r15 == 0) goto L_0x0098
            if (r12 == 0) goto L_0x0093
            r32 = 65536(0x10000, double:3.2379E-319)
            goto L_0x0096
        L_0x0093:
            r32 = 32768(0x8000, double:1.61895E-319)
        L_0x0096:
            long r2 = r2 | r32
        L_0x0098:
            android.widget.Button r13 = r1.ibNext
            android.content.Context r13 = r13.getContext()
            if (r12 == 0) goto L_0x00a3
            int r15 = com.eternal.account.C0997R.C0999drawable.create_account_gradient
            goto L_0x00a5
        L_0x00a3:
            int r15 = com.eternal.account.C0997R.C0999drawable.create_account_disable
        L_0x00a5:
            android.graphics.drawable.Drawable r13 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r13, r15)
            goto L_0x00ac
        L_0x00aa:
            r12 = 0
            r13 = 0
        L_0x00ac:
            long r32 = r2 & r10
            int r34 = (r32 > r4 ? 1 : (r32 == r4 ? 0 : -1))
            if (r34 == 0) goto L_0x00f1
            if (r0 == 0) goto L_0x00b7
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.showLoading
            goto L_0x00b8
        L_0x00b7:
            r15 = 0
        L_0x00b8:
            r10 = 3
            r1.updateLiveDataRegistration(r10, r15)
            if (r15 == 0) goto L_0x00c5
            java.lang.Object r10 = r15.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x00c6
        L_0x00c5:
            r10 = 0
        L_0x00c6:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r34 == 0) goto L_0x00dd
            if (r10 == 0) goto L_0x00d5
            r33 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r33
            r33 = 16384(0x4000, double:8.0948E-320)
            goto L_0x00db
        L_0x00d5:
            r33 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r33
            r33 = 8192(0x2000, double:4.0474E-320)
        L_0x00db:
            long r2 = r2 | r33
        L_0x00dd:
            android.widget.Button r11 = r1.ibNext
            if (r10 == 0) goto L_0x00e4
            int r15 = com.eternal.account.C0997R.C0998color.color_transparency
            goto L_0x00e6
        L_0x00e4:
            int r15 = com.eternal.account.C0997R.C0998color.white
        L_0x00e6:
            int r11 = getColorFromResource(r11, r15)
            if (r10 == 0) goto L_0x00ee
            r10 = 0
            goto L_0x00f3
        L_0x00ee:
            r10 = 8
            goto L_0x00f3
        L_0x00f1:
            r10 = 0
            r11 = 0
        L_0x00f3:
            long r33 = r2 & r16
            int r15 = (r33 > r4 ? 1 : (r33 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x010c
            if (r0 == 0) goto L_0x00fe
            androidx.lifecycle.MutableLiveData<java.lang.String> r15 = r0.emailText
            goto L_0x00ff
        L_0x00fe:
            r15 = 0
        L_0x00ff:
            r4 = 4
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x010c
            java.lang.Object r4 = r15.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x010d
        L_0x010c:
            r4 = 0
        L_0x010d:
            r24 = 416(0x1a0, double:2.055E-321)
            long r35 = r2 & r24
            r33 = 0
            int r5 = (r35 > r33 ? 1 : (r35 == r33 ? 0 : -1))
            if (r5 == 0) goto L_0x0144
            if (r0 == 0) goto L_0x011e
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.emailErrVisible
            r35 = r4
            goto L_0x0121
        L_0x011e:
            r35 = r4
            r15 = 0
        L_0x0121:
            r4 = 5
            r1.updateLiveDataRegistration(r4, r15)
            if (r15 == 0) goto L_0x012e
            java.lang.Object r4 = r15.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x012f
        L_0x012e:
            r4 = 0
        L_0x012f:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x013e
            if (r4 == 0) goto L_0x013a
            r36 = 1024(0x400, double:5.06E-321)
            goto L_0x013c
        L_0x013a:
            r36 = 512(0x200, double:2.53E-321)
        L_0x013c:
            long r2 = r2 | r36
        L_0x013e:
            if (r4 == 0) goto L_0x0141
            goto L_0x0146
        L_0x0141:
            r15 = 8
            goto L_0x0147
        L_0x0144:
            r35 = r4
        L_0x0146:
            r15 = 0
        L_0x0147:
            long r4 = r2 & r22
            r32 = 0
            int r36 = (r4 > r32 ? 1 : (r4 == r32 ? 0 : -1))
            if (r36 == 0) goto L_0x016a
            if (r0 == 0) goto L_0x0154
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r0.emailColor
            goto L_0x0155
        L_0x0154:
            r0 = 0
        L_0x0155:
            r4 = 6
            r1.updateLiveDataRegistration(r4, r0)
            if (r0 == 0) goto L_0x0162
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L_0x0163
        L_0x0162:
            r0 = 0
        L_0x0163:
            int r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r0)
            r4 = r35
            goto L_0x017a
        L_0x016a:
            r4 = r35
            r0 = 0
            goto L_0x017a
        L_0x016e:
            r0 = 0
            r4 = 0
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
        L_0x017a:
            long r22 = r2 & r22
            r32 = 0
            int r5 = (r22 > r32 ? 1 : (r22 == r32 ? 0 : -1))
            if (r5 == 0) goto L_0x018c
            android.widget.TextView r5 = r1.createEmailTitle
            r5.setTextColor(r0)
            android.widget.EditText r5 = r1.etEmail
            r5.setTextColor(r0)
        L_0x018c:
            long r18 = r2 & r18
            int r0 = (r18 > r32 ? 1 : (r18 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x01a2
            android.widget.EditText r0 = r1.etEmail
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r0, r7)
            android.widget.Button r0 = r1.ibNext
            r5 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r8, r5)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r0, r9)
        L_0x01a2:
            long r7 = r2 & r16
            int r0 = (r7 > r32 ? 1 : (r7 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x01ad
            android.widget.EditText r0 = r1.etEmail
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x01ad:
            r4 = 256(0x100, double:1.265E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r32 ? 1 : (r4 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x01c4
            android.widget.EditText r0 = r1.etEmail
            r4 = 0
            r9 = r4
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r9 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r9
            r5 = r4
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r5 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r5
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r4 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r4
            androidx.databinding.InverseBindingListener r7 = r1.etEmailandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r9, r5, r4, r7)
        L_0x01c4:
            r4 = 388(0x184, double:1.917E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r32 ? 1 : (r4 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x01d5
            android.widget.Button r0 = r1.ibNext
            r0.setClickable(r12)
            android.widget.Button r0 = r1.ibNext
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundDrawable(r0, r13)
        L_0x01d5:
            r4 = 392(0x188, double:1.937E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r32 ? 1 : (r4 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x01e6
            android.widget.Button r0 = r1.ibNext
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r11)
            android.widget.ProgressBar r0 = r1.pbLoading
            r0.setVisibility(r10)
        L_0x01e6:
            long r4 = r2 & r20
            int r0 = (r4 > r32 ? 1 : (r4 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x01f1
            android.view.View r0 = r1.mboundView4
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r6)
        L_0x01f1:
            r4 = 386(0x182, double:1.907E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r32 ? 1 : (r4 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x01fd
            android.widget.TextView r0 = r1.mboundView5
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r14)
        L_0x01fd:
            r4 = 416(0x1a0, double:2.055E-321)
            long r2 = r2 & r4
            int r0 = (r2 > r32 ? 1 : (r2 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x0209
            android.widget.TextView r0 = r1.mboundView5
            r0.setVisibility(r15)
        L_0x0209:
            return
        L_0x020a:
            r0 = move-exception
            monitor-exit(r38)     // Catch:{ all -> 0x020a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityResetPasswordBindingImpl.executeBindings():void");
    }
}
