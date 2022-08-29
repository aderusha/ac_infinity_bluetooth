package com.eternal.account.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
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
import com.eternal.account.model.ChangePasswordModel;

public class ActivityChangePasswordBindingImpl extends ActivityChangePasswordBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etNewPwdandroidTextAttrChanged;
    private InverseBindingListener etPwdandroidTextAttrChanged;
    private long mDirtyFlags;
    private final View mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView4;
    private final View mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.tv_tip, 14);
        sparseIntArray.put(C0997R.C1000id.rl_new_password, 15);
        sparseIntArray.put(C0997R.C1000id.rl_confrim_password, 16);
    }

    public ActivityChangePasswordBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityChangePasswordBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 14, objArr[7], objArr[3], objArr[8], objArr[12], objArr[13], objArr[16], objArr[15], objArr[0], objArr[1], objArr[2], objArr[14]);
        this.etNewPwdandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityChangePasswordBindingImpl.this.etNewPwd);
                ChangePasswordModel changePasswordModel = ActivityChangePasswordBindingImpl.this.mChangeModel;
                boolean z = true;
                if (changePasswordModel != null) {
                    MutableLiveData<String> mutableLiveData = changePasswordModel.newPwdText;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.etPwdandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityChangePasswordBindingImpl.this.etPwd);
                ChangePasswordModel changePasswordModel = ActivityChangePasswordBindingImpl.this.mChangeModel;
                boolean z = true;
                if (changePasswordModel != null) {
                    MutableLiveData<String> mutableLiveData = changePasswordModel.passwordText;
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
        this.createPasswordTitle.setTag((Object) null);
        this.etNewPwd.setTag((Object) null);
        this.etPwd.setTag((Object) null);
        this.ibNext.setTag((Object) null);
        View view2 = objArr[10];
        this.mboundView10 = view2;
        view2.setTag((Object) null);
        TextView textView = objArr[11];
        this.mboundView11 = textView;
        textView.setTag((Object) null);
        TextView textView2 = objArr[4];
        this.mboundView4 = textView2;
        textView2.setTag((Object) null);
        View view3 = objArr[5];
        this.mboundView5 = view3;
        view3.setTag((Object) null);
        TextView textView3 = objArr[6];
        this.mboundView6 = textView3;
        textView3.setTag((Object) null);
        TextView textView4 = objArr[9];
        this.mboundView9 = textView4;
        textView4.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.root.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvNewPassword.setTag((Object) null);
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
        if (C0977BR.changeModel != i) {
            return false;
        }
        setChangeModel((ChangePasswordModel) obj);
        return true;
    }

    public void setChangeModel(ChangePasswordModel changePasswordModel) {
        this.mChangeModel = changePasswordModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        notifyPropertyChanged(C0977BR.changeModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeChangeModelPasswordText((MutableLiveData) obj, i2);
            case 1:
                return onChangeChangeModelPwdShowText((MutableLiveData) obj, i2);
            case 2:
                return onChangeChangeModelPwdErrText((MutableLiveData) obj, i2);
            case 3:
                return onChangeChangeModelPwdErrVisible((MutableLiveData) obj, i2);
            case 4:
                return onChangeChangeModelTextVisiblePassword((MutableLiveData) obj, i2);
            case 5:
                return onChangeChangeModelNewPwdLineColor((MutableLiveData) obj, i2);
            case 6:
                return onChangeChangeModelPwdLineColor((MutableLiveData) obj, i2);
            case 7:
                return onChangeChangeModelShowLoading((MutableLiveData) obj, i2);
            case 8:
                return onChangeChangeModelNewPwdText((MutableLiveData) obj, i2);
            case 9:
                return onChangeChangeModelConfirmAble((MutableLiveData) obj, i2);
            case 10:
                return onChangeChangeModelNewPwdErrVisible((MutableLiveData) obj, i2);
            case 11:
                return onChangeChangeModelNewPwdColor((MutableLiveData) obj, i2);
            case 12:
                return onChangeChangeModelNewPwdErrText((MutableLiveData) obj, i2);
            case 13:
                return onChangeChangeModelPwdColor((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeChangeModelPasswordText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeChangeModelPwdShowText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeChangeModelPwdErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeChangeModelPwdErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeChangeModelTextVisiblePassword(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeChangeModelNewPwdLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeChangeModelPwdLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeChangeModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeChangeModelNewPwdText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeChangeModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeChangeModelNewPwdErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeChangeModelNewPwdColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeChangeModelNewPwdErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeChangeModelPwdColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x016e  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01e9  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x023c  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x0280  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x028c  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x02a8  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x02b4  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x02cd  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x02f9  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x00ff A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x013f  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x015e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r65 = this;
            r1 = r65
            monitor-enter(r65)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0465 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0465 }
            monitor-exit(r65)     // Catch:{ all -> 0x0465 }
            com.eternal.account.model.ChangePasswordModel r0 = r1.mChangeModel
            r6 = 65535(0xffff, double:3.23786E-319)
            long r6 = r6 & r2
            r16 = 49664(0xc200, double:2.45373E-319)
            r18 = 49184(0xc020, double:2.43E-319)
            r20 = 49168(0xc010, double:2.4292E-319)
            r22 = 49160(0xc008, double:2.42883E-319)
            r24 = 51200(0xc800, double:2.5296E-319)
            r26 = 49156(0xc004, double:2.42863E-319)
            r28 = 49408(0xc100, double:2.4411E-319)
            r30 = 49154(0xc002, double:2.42853E-319)
            r32 = 49152(0xc000, double:2.42843E-319)
            r34 = 49153(0xc001, double:2.4285E-319)
            r36 = 57344(0xe000, double:2.83317E-319)
            r8 = 1
            r9 = 0
            r40 = 0
            int r41 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r41 == 0) goto L_0x0322
            long r6 = r2 & r34
            int r41 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r41 == 0) goto L_0x0052
            if (r0 == 0) goto L_0x0044
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.passwordText
            goto L_0x0046
        L_0x0044:
            r6 = r40
        L_0x0046:
            r1.updateLiveDataRegistration(r9, r6)
            if (r6 == 0) goto L_0x0052
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0054
        L_0x0052:
            r6 = r40
        L_0x0054:
            long r41 = r2 & r30
            int r7 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x006d
            if (r0 == 0) goto L_0x005f
            androidx.lifecycle.MutableLiveData<java.lang.String> r7 = r0.pwdShowText
            goto L_0x0061
        L_0x005f:
            r7 = r40
        L_0x0061:
            r1.updateLiveDataRegistration(r8, r7)
            if (r7 == 0) goto L_0x006d
            java.lang.Object r7 = r7.getValue()
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x006f
        L_0x006d:
            r7 = r40
        L_0x006f:
            long r41 = r2 & r26
            int r43 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r43 == 0) goto L_0x0089
            if (r0 == 0) goto L_0x007a
            androidx.lifecycle.MutableLiveData<java.lang.String> r8 = r0.pwdErrText
            goto L_0x007c
        L_0x007a:
            r8 = r40
        L_0x007c:
            r9 = 2
            r1.updateLiveDataRegistration(r9, r8)
            if (r8 == 0) goto L_0x0089
            java.lang.Object r8 = r8.getValue()
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x008b
        L_0x0089:
            r8 = r40
        L_0x008b:
            long r43 = r2 & r22
            int r45 = (r43 > r4 ? 1 : (r43 == r4 ? 0 : -1))
            if (r45 == 0) goto L_0x00be
            if (r0 == 0) goto L_0x0096
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.pwdErrVisible
            goto L_0x0098
        L_0x0096:
            r10 = r40
        L_0x0098:
            r11 = 3
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x00a5
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x00a7
        L_0x00a5:
            r10 = r40
        L_0x00a7:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r45 == 0) goto L_0x00b8
            if (r10 == 0) goto L_0x00b3
            r45 = 131072(0x20000, double:6.47582E-319)
            goto L_0x00b6
        L_0x00b3:
            r45 = 65536(0x10000, double:3.2379E-319)
        L_0x00b6:
            long r2 = r2 | r45
        L_0x00b8:
            if (r10 == 0) goto L_0x00bb
            goto L_0x00be
        L_0x00bb:
            r10 = 8
            goto L_0x00bf
        L_0x00be:
            r10 = 0
        L_0x00bf:
            long r45 = r2 & r20
            int r11 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00f7
            if (r0 == 0) goto L_0x00ca
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.textVisiblePassword
            goto L_0x00cc
        L_0x00ca:
            r9 = r40
        L_0x00cc:
            r12 = 4
            r1.updateLiveDataRegistration(r12, r9)
            if (r9 == 0) goto L_0x00d9
            java.lang.Object r9 = r9.getValue()
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            goto L_0x00db
        L_0x00d9:
            r9 = r40
        L_0x00db:
            boolean r9 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r9)
            if (r11 == 0) goto L_0x00eb
            if (r9 == 0) goto L_0x00e7
            r11 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x00ea
        L_0x00e7:
            r11 = 16777216(0x1000000, double:8.289046E-317)
        L_0x00ea:
            long r2 = r2 | r11
        L_0x00eb:
            if (r9 == 0) goto L_0x00f2
            android.text.method.HideReturnsTransformationMethod r9 = android.text.method.HideReturnsTransformationMethod.getInstance()
            goto L_0x00f9
        L_0x00f2:
            android.text.method.PasswordTransformationMethod r9 = android.text.method.PasswordTransformationMethod.getInstance()
            goto L_0x00f9
        L_0x00f7:
            r9 = r40
        L_0x00f9:
            long r11 = r2 & r32
            int r13 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x010c
            if (r0 == 0) goto L_0x010c
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r11 = r0.onChange
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r13 = r0.pwdTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r14 = r0.newPwdTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onPwdShow
            goto L_0x0112
        L_0x010c:
            r11 = r40
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
        L_0x0112:
            long r50 = r2 & r18
            int r52 = (r50 > r4 ? 1 : (r50 == r4 ? 0 : -1))
            if (r52 == 0) goto L_0x0133
            if (r0 == 0) goto L_0x011d
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r4 = r0.newPwdLineColor
            goto L_0x011f
        L_0x011d:
            r4 = r40
        L_0x011f:
            r5 = 5
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x012c
            java.lang.Object r4 = r4.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x012e
        L_0x012c:
            r4 = r40
        L_0x012e:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x0134
        L_0x0133:
            r4 = 0
        L_0x0134:
            r48 = 49216(0xc040, double:2.4316E-319)
            long r52 = r2 & r48
            r50 = 0
            int r5 = (r52 > r50 ? 1 : (r52 == r50 ? 0 : -1))
            if (r5 == 0) goto L_0x015e
            if (r0 == 0) goto L_0x0146
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r5 = r0.pwdLineColor
            r52 = r4
            goto L_0x014a
        L_0x0146:
            r52 = r4
            r5 = r40
        L_0x014a:
            r4 = 6
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x0157
            java.lang.Object r4 = r5.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x0159
        L_0x0157:
            r4 = r40
        L_0x0159:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x0161
        L_0x015e:
            r52 = r4
            r4 = 0
        L_0x0161:
            r46 = 49280(0xc080, double:2.43476E-319)
            long r53 = r2 & r46
            r50 = 0
            int r5 = (r53 > r50 ? 1 : (r53 == r50 ? 0 : -1))
            r53 = r4
            if (r5 == 0) goto L_0x01b6
            if (r0 == 0) goto L_0x0175
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.showLoading
            r54 = r6
            goto L_0x0179
        L_0x0175:
            r54 = r6
            r4 = r40
        L_0x0179:
            r6 = 7
            r1.updateLiveDataRegistration(r6, r4)
            if (r4 == 0) goto L_0x0186
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0188
        L_0x0186:
            r4 = r40
        L_0x0188:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x01a0
            if (r4 == 0) goto L_0x0198
            r5 = 8388608(0x800000, double:4.144523E-317)
            long r2 = r2 | r5
            r5 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x019f
        L_0x0198:
            r5 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r5
            r5 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x019f:
            long r2 = r2 | r5
        L_0x01a0:
            if (r4 == 0) goto L_0x01a4
            r5 = 0
            goto L_0x01a6
        L_0x01a4:
            r5 = 8
        L_0x01a6:
            if (r4 == 0) goto L_0x01ad
            android.widget.Button r4 = r1.ibNext
            int r6 = com.eternal.account.C0997R.C0998color.color_transparency
            goto L_0x01b1
        L_0x01ad:
            android.widget.Button r4 = r1.ibNext
            int r6 = com.eternal.account.C0997R.C0998color.white
        L_0x01b1:
            int r4 = getColorFromResource(r4, r6)
            goto L_0x01ba
        L_0x01b6:
            r54 = r6
            r4 = 0
            r5 = 0
        L_0x01ba:
            long r55 = r2 & r28
            r50 = 0
            int r6 = (r55 > r50 ? 1 : (r55 == r50 ? 0 : -1))
            if (r6 == 0) goto L_0x01db
            if (r0 == 0) goto L_0x01c9
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.newPwdText
            r45 = r4
            goto L_0x01cd
        L_0x01c9:
            r45 = r4
            r6 = r40
        L_0x01cd:
            r4 = 8
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x01df
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x01e1
        L_0x01db:
            r45 = r4
            r4 = 8
        L_0x01df:
            r6 = r40
        L_0x01e1:
            long r55 = r2 & r16
            r50 = 0
            int r57 = (r55 > r50 ? 1 : (r55 == r50 ? 0 : -1))
            if (r57 == 0) goto L_0x023c
            if (r0 == 0) goto L_0x01f0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.confirmAble
            r56 = r5
            goto L_0x01f4
        L_0x01f0:
            r56 = r5
            r4 = r40
        L_0x01f4:
            r5 = 9
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0202
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0204
        L_0x0202:
            r4 = r40
        L_0x0204:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r57 == 0) goto L_0x0215
            if (r4 == 0) goto L_0x0210
            r57 = 524288(0x80000, double:2.590327E-318)
            goto L_0x0213
        L_0x0210:
            r57 = 262144(0x40000, double:1.295163E-318)
        L_0x0213:
            long r2 = r2 | r57
        L_0x0215:
            if (r4 == 0) goto L_0x0226
            android.widget.Button r5 = r1.ibNext
            android.content.Context r5 = r5.getContext()
            r57 = r2
            int r2 = com.eternal.account.C0997R.C0999drawable.create_account_gradient
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r5, r2)
            goto L_0x0234
        L_0x0226:
            r57 = r2
            android.widget.Button r2 = r1.ibNext
            android.content.Context r2 = r2.getContext()
            int r3 = com.eternal.account.C0997R.C0999drawable.create_account_disable
            android.graphics.drawable.Drawable r2 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r2, r3)
        L_0x0234:
            r5 = r4
            r43 = 50176(0xc400, double:2.479E-319)
            r4 = r2
            r2 = r57
            goto L_0x0244
        L_0x023c:
            r56 = r5
            r4 = r40
            r5 = 0
            r43 = 50176(0xc400, double:2.479E-319)
        L_0x0244:
            long r57 = r2 & r43
            r50 = 0
            int r59 = (r57 > r50 ? 1 : (r57 == r50 ? 0 : -1))
            r57 = r4
            if (r59 == 0) goto L_0x0280
            if (r0 == 0) goto L_0x0255
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.newPwdErrVisible
            r58 = r5
            goto L_0x0259
        L_0x0255:
            r58 = r5
            r4 = r40
        L_0x0259:
            r5 = 10
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0267
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0269
        L_0x0267:
            r4 = r40
        L_0x0269:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r59 == 0) goto L_0x027a
            if (r4 == 0) goto L_0x0275
            r59 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x0278
        L_0x0275:
            r59 = 1048576(0x100000, double:5.180654E-318)
        L_0x0278:
            long r2 = r2 | r59
        L_0x027a:
            if (r4 == 0) goto L_0x027d
            goto L_0x0282
        L_0x027d:
            r55 = 8
            goto L_0x0284
        L_0x0280:
            r58 = r5
        L_0x0282:
            r55 = 0
        L_0x0284:
            long r4 = r2 & r24
            r50 = 0
            int r59 = (r4 > r50 ? 1 : (r4 == r50 ? 0 : -1))
            if (r59 == 0) goto L_0x02a8
            if (r0 == 0) goto L_0x0291
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r4 = r0.newPwdColor
            goto L_0x0293
        L_0x0291:
            r4 = r40
        L_0x0293:
            r5 = 11
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x02a1
            java.lang.Object r4 = r4.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x02a3
        L_0x02a1:
            r4 = r40
        L_0x02a3:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x02a9
        L_0x02a8:
            r4 = 0
        L_0x02a9:
            r38 = 53248(0xd000, double:2.6308E-319)
            long r59 = r2 & r38
            r50 = 0
            int r5 = (r59 > r50 ? 1 : (r59 == r50 ? 0 : -1))
            if (r5 == 0) goto L_0x02cd
            if (r0 == 0) goto L_0x02bb
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.newPwdErrText
            r59 = r4
            goto L_0x02bf
        L_0x02bb:
            r59 = r4
            r5 = r40
        L_0x02bf:
            r4 = 12
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x02cf
            java.lang.Object r4 = r5.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x02d1
        L_0x02cd:
            r59 = r4
        L_0x02cf:
            r4 = r40
        L_0x02d1:
            long r60 = r2 & r36
            r50 = 0
            int r5 = (r60 > r50 ? 1 : (r60 == r50 ? 0 : -1))
            if (r5 == 0) goto L_0x02f9
            if (r0 == 0) goto L_0x02de
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r0.pwdColor
            goto L_0x02e0
        L_0x02de:
            r0 = r40
        L_0x02e0:
            r5 = 13
            r1.updateLiveDataRegistration(r5, r0)
            if (r0 == 0) goto L_0x02ee
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L_0x02f0
        L_0x02ee:
            r0 = r40
        L_0x02f0:
            int r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r0)
            r62 = r55
            r5 = r57
            goto L_0x02fe
        L_0x02f9:
            r62 = r55
            r5 = r57
            r0 = 0
        L_0x02fe:
            r55 = r10
            r57 = r53
            r10 = r58
            r53 = r52
            r52 = r4
            r4 = r59
            r63 = r12
            r12 = r6
            r6 = r45
            r45 = r9
            r9 = r13
            r13 = r54
            r54 = r7
            r7 = r15
            r15 = r14
            r14 = r63
            r64 = r56
            r56 = r8
            r8 = r11
            r11 = r64
            goto L_0x0340
        L_0x0322:
            r5 = r40
            r7 = r5
            r8 = r7
            r9 = r8
            r12 = r9
            r13 = r12
            r14 = r13
            r15 = r14
            r45 = r15
            r52 = r45
            r54 = r52
            r56 = r54
            r0 = 0
            r4 = 0
            r6 = 0
            r10 = 0
            r11 = 0
            r53 = 0
            r55 = 0
            r57 = 0
            r62 = 0
        L_0x0340:
            long r36 = r2 & r36
            r50 = 0
            int r58 = (r36 > r50 ? 1 : (r36 == r50 ? 0 : -1))
            r36 = r11
            if (r58 == 0) goto L_0x0354
            android.widget.TextView r11 = r1.createPasswordTitle
            r11.setTextColor(r0)
            android.widget.EditText r11 = r1.etPwd
            r11.setTextColor(r0)
        L_0x0354:
            long r32 = r2 & r32
            int r0 = (r32 > r50 ? 1 : (r32 == r50 ? 0 : -1))
            if (r0 == 0) goto L_0x037a
            android.widget.EditText r0 = r1.etNewPwd
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r0, r15)
            android.widget.EditText r0 = r1.etPwd
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r0, r9)
            android.widget.Button r0 = r1.ibNext
            r9 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r8, r9)
            android.widget.TextView r0 = r1.mboundView4
            r8 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r7, r8)
            android.widget.TextView r0 = r1.mboundView9
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r7, r8)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r0, r14)
        L_0x037a:
            long r7 = r2 & r28
            r14 = 0
            int r0 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x0387
            android.widget.EditText r0 = r1.etNewPwd
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x0387:
            long r7 = r2 & r24
            int r0 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x0397
            android.widget.EditText r0 = r1.etNewPwd
            r0.setTextColor(r4)
            android.widget.TextView r0 = r1.tvNewPassword
            r0.setTextColor(r4)
        L_0x0397:
            long r7 = r2 & r20
            int r0 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x03ab
            android.widget.EditText r0 = r1.etNewPwd
            r4 = r45
            android.text.method.TransformationMethod r4 = (android.text.method.TransformationMethod) r4
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.setTransform(r0, r4)
            android.widget.EditText r0 = r1.etPwd
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.setTransform(r0, r4)
        L_0x03ab:
            r7 = 32768(0x8000, double:1.61895E-319)
            long r7 = r7 & r2
            int r0 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x03cd
            android.widget.EditText r0 = r1.etNewPwd
            r4 = r40
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r4 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r4
            r7 = r40
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r7 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r7
            r8 = r40
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r8 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r8
            androidx.databinding.InverseBindingListener r9 = r1.etNewPwdandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r4, r7, r8, r9)
            android.widget.EditText r0 = r1.etPwd
            androidx.databinding.InverseBindingListener r9 = r1.etPwdandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r4, r7, r8, r9)
        L_0x03cd:
            long r7 = r2 & r34
            r11 = 0
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x03da
            android.widget.EditText r0 = r1.etPwd
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r13)
        L_0x03da:
            long r7 = r2 & r16
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x03ea
            android.widget.Button r0 = r1.ibNext
            r0.setClickable(r10)
            android.widget.Button r0 = r1.ibNext
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundDrawable(r0, r5)
        L_0x03ea:
            r4 = 49280(0xc080, double:2.43476E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x03fe
            android.widget.Button r0 = r1.ibNext
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r6)
            android.widget.ProgressBar r0 = r1.pbLoading
            r4 = r36
            r0.setVisibility(r4)
        L_0x03fe:
            r4 = 49216(0xc040, double:2.4316E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x040d
            android.view.View r0 = r1.mboundView10
            r4 = r57
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r4)
        L_0x040d:
            long r4 = r2 & r26
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x041a
            android.widget.TextView r0 = r1.mboundView11
            r8 = r56
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
        L_0x041a:
            long r4 = r2 & r22
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0427
            android.widget.TextView r0 = r1.mboundView11
            r10 = r55
            r0.setVisibility(r10)
        L_0x0427:
            long r4 = r2 & r30
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0439
            android.widget.TextView r0 = r1.mboundView4
            r7 = r54
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
            android.widget.TextView r0 = r1.mboundView9
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x0439:
            long r4 = r2 & r18
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0446
            android.view.View r0 = r1.mboundView5
            r4 = r53
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r4)
        L_0x0446:
            r4 = 53248(0xd000, double:2.6308E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0455
            android.widget.TextView r0 = r1.mboundView6
            r4 = r52
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0455:
            r4 = 50176(0xc400, double:2.479E-319)
            long r2 = r2 & r4
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0464
            android.widget.TextView r0 = r1.mboundView6
            r2 = r62
            r0.setVisibility(r2)
        L_0x0464:
            return
        L_0x0465:
            r0 = move-exception
            monitor-exit(r65)     // Catch:{ all -> 0x0465 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityChangePasswordBindingImpl.executeBindings():void");
    }
}
