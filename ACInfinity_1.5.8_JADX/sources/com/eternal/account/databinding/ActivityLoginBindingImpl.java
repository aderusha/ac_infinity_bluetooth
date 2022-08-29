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
import com.eternal.account.model.LoginModel;

public class ActivityLoginBindingImpl extends ActivityLoginBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etEmailandroidTextAttrChanged;
    private InverseBindingListener etPwdandroidTextAttrChanged;
    private long mDirtyFlags;
    private final View mboundView10;
    private final TextView mboundView11;
    private final TextView mboundView14;
    private final TextView mboundView2;
    private final View mboundView5;
    private final TextView mboundView6;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.create_email, 17);
        sparseIntArray.put(C0997R.C1000id.create_password, 18);
    }

    public ActivityLoginBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 19, sIncludes, sViewsWithIds));
    }

    private ActivityLoginBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 15, objArr[17], objArr[3], objArr[18], objArr[7], objArr[4], objArr[8], objArr[15], objArr[16], objArr[0], objArr[1], objArr[12], objArr[13]);
        this.etEmailandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityLoginBindingImpl.this.etEmail);
                LoginModel loginModel = ActivityLoginBindingImpl.this.mLoginModel;
                boolean z = true;
                if (loginModel != null) {
                    MutableLiveData<String> mutableLiveData = loginModel.emailText;
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
                String textString = TextViewBindingAdapter.getTextString(ActivityLoginBindingImpl.this.etPwd);
                LoginModel loginModel = ActivityLoginBindingImpl.this.mLoginModel;
                boolean z = true;
                if (loginModel != null) {
                    MutableLiveData<String> mutableLiveData = loginModel.passwordText;
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
        this.createPasswordTitle.setTag((Object) null);
        this.etEmail.setTag((Object) null);
        this.etPwd.setTag((Object) null);
        this.ibNext.setTag((Object) null);
        View view2 = objArr[10];
        this.mboundView10 = view2;
        view2.setTag((Object) null);
        TextView textView = objArr[11];
        this.mboundView11 = textView;
        textView.setTag((Object) null);
        TextView textView2 = objArr[14];
        this.mboundView14 = textView2;
        textView2.setTag((Object) null);
        TextView textView3 = objArr[2];
        this.mboundView2 = textView3;
        textView3.setTag((Object) null);
        View view3 = objArr[5];
        this.mboundView5 = view3;
        view3.setTag((Object) null);
        TextView textView4 = objArr[6];
        this.mboundView6 = textView4;
        textView4.setTag((Object) null);
        TextView textView5 = objArr[9];
        this.mboundView9 = textView5;
        textView5.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.root.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvForgot.setTag((Object) null);
        this.tvNew.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
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
        if (C0977BR.loginModel != i) {
            return false;
        }
        setLoginModel((LoginModel) obj);
        return true;
    }

    public void setLoginModel(LoginModel loginModel) {
        this.mLoginModel = loginModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(C0977BR.loginModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeLoginModelPasswordText((MutableLiveData) obj, i2);
            case 1:
                return onChangeLoginModelPwdColor((MutableLiveData) obj, i2);
            case 2:
                return onChangeLoginModelAfterLogout((MutableLiveData) obj, i2);
            case 3:
                return onChangeLoginModelEmailText((MutableLiveData) obj, i2);
            case 4:
                return onChangeLoginModelPwdErrText((MutableLiveData) obj, i2);
            case 5:
                return onChangeLoginModelEmailErrVisible((MutableLiveData) obj, i2);
            case 6:
                return onChangeLoginModelTextVisiblePassword((MutableLiveData) obj, i2);
            case 7:
                return onChangeLoginModelEmailErrText((MutableLiveData) obj, i2);
            case 8:
                return onChangeLoginModelEmailLineColor((MutableLiveData) obj, i2);
            case 9:
                return onChangeLoginModelPwdShowText((MutableLiveData) obj, i2);
            case 10:
                return onChangeLoginModelConfirmAble((MutableLiveData) obj, i2);
            case 11:
                return onChangeLoginModelShowLoading((MutableLiveData) obj, i2);
            case 12:
                return onChangeLoginModelPwdErrVisible((MutableLiveData) obj, i2);
            case 13:
                return onChangeLoginModelEmailColor((MutableLiveData) obj, i2);
            case 14:
                return onChangeLoginModelPwdLineColor((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeLoginModelPasswordText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeLoginModelPwdColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeLoginModelAfterLogout(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeLoginModelEmailText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeLoginModelPwdErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeLoginModelEmailErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeLoginModelTextVisiblePassword(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeLoginModelEmailErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeLoginModelEmailLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeLoginModelPwdShowText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeLoginModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeLoginModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeLoginModelPwdErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeLoginModelEmailColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeLoginModelPwdLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01d2  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0205  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x0227  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x0236  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0256  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0266  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x027f  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x028d  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x02ca  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0324  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x0335  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x0366  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x0372  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x039a  */
    /* JADX WARNING: Removed duplicated region for block: B:235:0x03d6  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x014d  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0189  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:98:0x01c7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r75 = this;
            r1 = r75
            monitor-enter(r75)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x058c }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x058c }
            monitor-exit(r75)     // Catch:{ all -> 0x058c }
            com.eternal.account.model.LoginModel r0 = r1.mLoginModel
            r6 = 131071(0x1ffff, double:6.47577E-319)
            long r6 = r6 & r2
            r16 = 98432(0x18080, double:4.8632E-319)
            r18 = 100352(0x18800, double:4.95805E-319)
            r20 = 99328(0x18400, double:4.90746E-319)
            r22 = 98336(0x18020, double:4.85844E-319)
            r24 = 98368(0x18040, double:4.86002E-319)
            r26 = 98320(0x18010, double:4.85765E-319)
            r14 = 1
            r28 = 98312(0x18008, double:4.85726E-319)
            r30 = 98308(0x18004, double:4.85706E-319)
            r32 = 98304(0x18000, double:4.85686E-319)
            r34 = 98306(0x18002, double:4.85696E-319)
            r36 = 98305(0x18001, double:4.8569E-319)
            r38 = 106496(0x1a000, double:5.2616E-319)
            r15 = 0
            r40 = 0
            int r41 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r41 == 0) goto L_0x0407
            long r6 = r2 & r36
            int r41 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r41 == 0) goto L_0x0055
            if (r0 == 0) goto L_0x0047
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.passwordText
            goto L_0x0049
        L_0x0047:
            r6 = r40
        L_0x0049:
            r1.updateLiveDataRegistration(r15, r6)
            if (r6 == 0) goto L_0x0055
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0057
        L_0x0055:
            r6 = r40
        L_0x0057:
            long r41 = r2 & r34
            int r7 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0077
            if (r0 == 0) goto L_0x0062
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r7 = r0.pwdColor
            goto L_0x0064
        L_0x0062:
            r7 = r40
        L_0x0064:
            r1.updateLiveDataRegistration(r14, r7)
            if (r7 == 0) goto L_0x0070
            java.lang.Object r7 = r7.getValue()
            java.lang.Integer r7 = (java.lang.Integer) r7
            goto L_0x0072
        L_0x0070:
            r7 = r40
        L_0x0072:
            int r7 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r7)
            goto L_0x0078
        L_0x0077:
            r7 = 0
        L_0x0078:
            long r41 = r2 & r30
            int r43 = (r41 > r4 ? 1 : (r41 == r4 ? 0 : -1))
            if (r43 == 0) goto L_0x010b
            if (r0 == 0) goto L_0x0083
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r15 = r0.afterLogout
            goto L_0x0085
        L_0x0083:
            r15 = r40
        L_0x0085:
            r8 = 2
            r1.updateLiveDataRegistration(r8, r15)
            if (r15 == 0) goto L_0x0092
            java.lang.Object r8 = r15.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x0094
        L_0x0092:
            r8 = r40
        L_0x0094:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r43 == 0) goto L_0x00cb
            if (r8 == 0) goto L_0x00b3
            r46 = 4194304(0x400000, double:2.0722615E-317)
            long r2 = r2 | r46
            r46 = 268435456(0x10000000, double:1.32624737E-315)
            long r2 = r2 | r46
            r46 = 4294967296(0x100000000, double:2.121995791E-314)
            long r2 = r2 | r46
            r46 = 68719476736(0x1000000000, double:3.39519326554E-313)
            goto L_0x00c9
        L_0x00b3:
            r46 = 2097152(0x200000, double:1.0361308E-317)
            long r2 = r2 | r46
            r46 = 134217728(0x8000000, double:6.63123685E-316)
            long r2 = r2 | r46
            r46 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r2 = r2 | r46
            r46 = 34359738368(0x800000000, double:1.69759663277E-313)
        L_0x00c9:
            long r2 = r2 | r46
        L_0x00cb:
            if (r8 == 0) goto L_0x00da
            com.eternal.widget.guqiang.Toolbar r9 = r1.toolBar
            android.content.res.Resources r9 = r9.getResources()
            int r15 = com.eternal.account.C0997R.string.skip_as_guest
            java.lang.String r9 = r9.getString(r15)
            goto L_0x00dc
        L_0x00da:
            java.lang.String r9 = ""
        L_0x00dc:
            if (r8 == 0) goto L_0x00e0
            r15 = 0
            goto L_0x00e2
        L_0x00e0:
            r15 = 8
        L_0x00e2:
            if (r8 == 0) goto L_0x00e7
            java.lang.String r42 = ""
            goto L_0x00f3
        L_0x00e7:
            com.eternal.widget.guqiang.Toolbar r10 = r1.toolBar
            android.content.res.Resources r10 = r10.getResources()
            int r11 = com.eternal.account.C0997R.string.log_in
            java.lang.String r42 = r10.getString(r11)
        L_0x00f3:
            if (r8 == 0) goto L_0x00fe
            com.eternal.widget.guqiang.Toolbar r8 = r1.toolBar
            android.content.Context r8 = r8.getContext()
            int r10 = com.eternal.account.C0997R.C0999drawable.transparent
            goto L_0x0106
        L_0x00fe:
            com.eternal.widget.guqiang.Toolbar r8 = r1.toolBar
            android.content.Context r8 = r8.getContext()
            int r10 = com.eternal.account.C0997R.C0999drawable.add_device_back_selector
        L_0x0106:
            android.graphics.drawable.Drawable r8 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r8, r10)
            goto L_0x0111
        L_0x010b:
            r8 = r40
            r9 = r8
            r42 = r9
            r15 = 0
        L_0x0111:
            long r10 = r2 & r32
            int r43 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r43 == 0) goto L_0x0139
            if (r0 == 0) goto L_0x0139
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r10 = r0.pwdTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r11 = r0.onForgotPassword
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r14 = r0.onPwdShow
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onCreate
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r13 = r0.emailTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onLogin
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r5 = r0.onSkip
            r52 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onBack
            r74 = r5
            r5 = r4
            r4 = r52
            r52 = r14
            r14 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r74
            goto L_0x0143
        L_0x0139:
            r4 = r40
            r5 = r4
            r10 = r5
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r52 = r14
        L_0x0143:
            long r53 = r2 & r28
            r50 = 0
            int r55 = (r53 > r50 ? 1 : (r53 == r50 ? 0 : -1))
            r53 = r4
            if (r55 == 0) goto L_0x0165
            if (r0 == 0) goto L_0x0154
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.emailText
            r54 = r5
            goto L_0x0158
        L_0x0154:
            r54 = r5
            r4 = r40
        L_0x0158:
            r5 = 3
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0167
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0169
        L_0x0165:
            r54 = r5
        L_0x0167:
            r4 = r40
        L_0x0169:
            long r55 = r2 & r26
            r50 = 0
            int r5 = (r55 > r50 ? 1 : (r55 == r50 ? 0 : -1))
            if (r5 == 0) goto L_0x0189
            if (r0 == 0) goto L_0x0178
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.pwdErrText
            r55 = r4
            goto L_0x017c
        L_0x0178:
            r55 = r4
            r5 = r40
        L_0x017c:
            r4 = 4
            r1.updateLiveDataRegistration(r4, r5)
            if (r5 == 0) goto L_0x018b
            java.lang.Object r4 = r5.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x018d
        L_0x0189:
            r55 = r4
        L_0x018b:
            r4 = r40
        L_0x018d:
            long r56 = r2 & r22
            r50 = 0
            int r5 = (r56 > r50 ? 1 : (r56 == r50 ? 0 : -1))
            r56 = r4
            if (r5 == 0) goto L_0x01c7
            if (r0 == 0) goto L_0x019e
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.emailErrVisible
            r57 = r6
            goto L_0x01a2
        L_0x019e:
            r57 = r6
            r4 = r40
        L_0x01a2:
            r6 = 5
            r1.updateLiveDataRegistration(r6, r4)
            if (r4 == 0) goto L_0x01af
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01b1
        L_0x01af:
            r4 = r40
        L_0x01b1:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x01c1
            if (r4 == 0) goto L_0x01bd
            r5 = 16777216(0x1000000, double:8.289046E-317)
            goto L_0x01c0
        L_0x01bd:
            r5 = 8388608(0x800000, double:4.144523E-317)
        L_0x01c0:
            long r2 = r2 | r5
        L_0x01c1:
            if (r4 == 0) goto L_0x01c4
            goto L_0x01c9
        L_0x01c4:
            r4 = 8
            goto L_0x01ca
        L_0x01c7:
            r57 = r6
        L_0x01c9:
            r4 = 0
        L_0x01ca:
            long r5 = r2 & r24
            r50 = 0
            int r58 = (r5 > r50 ? 1 : (r5 == r50 ? 0 : -1))
            if (r58 == 0) goto L_0x0205
            if (r0 == 0) goto L_0x01d7
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.textVisiblePassword
            goto L_0x01d9
        L_0x01d7:
            r5 = r40
        L_0x01d9:
            r6 = 6
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x01e6
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x01e8
        L_0x01e6:
            r5 = r40
        L_0x01e8:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            if (r58 == 0) goto L_0x01f9
            if (r5 == 0) goto L_0x01f4
            r58 = 67108864(0x4000000, double:3.31561842E-316)
            goto L_0x01f7
        L_0x01f4:
            r58 = 33554432(0x2000000, double:1.6578092E-316)
        L_0x01f7:
            long r2 = r2 | r58
        L_0x01f9:
            if (r5 == 0) goto L_0x0200
            android.text.method.HideReturnsTransformationMethod r5 = android.text.method.HideReturnsTransformationMethod.getInstance()
            goto L_0x0207
        L_0x0200:
            android.text.method.PasswordTransformationMethod r5 = android.text.method.PasswordTransformationMethod.getInstance()
            goto L_0x0207
        L_0x0205:
            r5 = r40
        L_0x0207:
            long r58 = r2 & r16
            r50 = 0
            int r6 = (r58 > r50 ? 1 : (r58 == r50 ? 0 : -1))
            if (r6 == 0) goto L_0x0227
            if (r0 == 0) goto L_0x0216
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.emailErrText
            r58 = r4
            goto L_0x021a
        L_0x0216:
            r58 = r4
            r6 = r40
        L_0x021a:
            r4 = 7
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x0229
            java.lang.Object r4 = r6.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x022b
        L_0x0227:
            r58 = r4
        L_0x0229:
            r4 = r40
        L_0x022b:
            r48 = 98560(0x18100, double:4.8695E-319)
            long r59 = r2 & r48
            r50 = 0
            int r6 = (r59 > r50 ? 1 : (r59 == r50 ? 0 : -1))
            if (r6 == 0) goto L_0x0256
            if (r0 == 0) goto L_0x023d
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r6 = r0.emailLineColor
            r43 = r4
            goto L_0x0241
        L_0x023d:
            r43 = r4
            r6 = r40
        L_0x0241:
            r4 = 8
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x024f
            java.lang.Object r6 = r6.getValue()
            java.lang.Integer r6 = (java.lang.Integer) r6
            goto L_0x0251
        L_0x024f:
            r6 = r40
        L_0x0251:
            int r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r6)
            goto L_0x025b
        L_0x0256:
            r43 = r4
            r4 = 8
            r6 = 0
        L_0x025b:
            r46 = 98816(0x18200, double:4.88216E-319)
            long r59 = r2 & r46
            r50 = 0
            int r61 = (r59 > r50 ? 1 : (r59 == r50 ? 0 : -1))
            if (r61 == 0) goto L_0x027f
            if (r0 == 0) goto L_0x026d
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.pwdShowText
            r60 = r5
            goto L_0x0271
        L_0x026d:
            r60 = r5
            r4 = r40
        L_0x0271:
            r5 = 9
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0281
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0283
        L_0x027f:
            r60 = r5
        L_0x0281:
            r4 = r40
        L_0x0283:
            long r61 = r2 & r20
            r50 = 0
            int r5 = (r61 > r50 ? 1 : (r61 == r50 ? 0 : -1))
            r61 = r4
            if (r5 == 0) goto L_0x02ca
            if (r0 == 0) goto L_0x0294
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.confirmAble
            r62 = r6
            goto L_0x0298
        L_0x0294:
            r62 = r6
            r4 = r40
        L_0x0298:
            r6 = 10
            r1.updateLiveDataRegistration(r6, r4)
            if (r4 == 0) goto L_0x02a6
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x02a8
        L_0x02a6:
            r4 = r40
        L_0x02a8:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x02b8
            if (r4 == 0) goto L_0x02b4
            r5 = 262144(0x40000, double:1.295163E-318)
            goto L_0x02b7
        L_0x02b4:
            r5 = 131072(0x20000, double:6.47582E-319)
        L_0x02b7:
            long r2 = r2 | r5
        L_0x02b8:
            android.widget.Button r5 = r1.ibNext
            android.content.Context r5 = r5.getContext()
            if (r4 == 0) goto L_0x02c3
            int r6 = com.eternal.account.C0997R.C0999drawable.create_account_gradient
            goto L_0x02c5
        L_0x02c3:
            int r6 = com.eternal.account.C0997R.C0999drawable.create_account_disable
        L_0x02c5:
            android.graphics.drawable.Drawable r5 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r5, r6)
            goto L_0x02cf
        L_0x02ca:
            r62 = r6
            r5 = r40
            r4 = 0
        L_0x02cf:
            long r63 = r2 & r18
            r50 = 0
            int r6 = (r63 > r50 ? 1 : (r63 == r50 ? 0 : -1))
            r63 = r4
            if (r6 == 0) goto L_0x0324
            if (r0 == 0) goto L_0x02e0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.showLoading
            r64 = r5
            goto L_0x02e4
        L_0x02e0:
            r64 = r5
            r4 = r40
        L_0x02e4:
            r5 = 11
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x02f2
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x02f4
        L_0x02f2:
            r4 = r40
        L_0x02f4:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x0310
            if (r4 == 0) goto L_0x0306
            r5 = 1073741824(0x40000000, double:5.304989477E-315)
            long r2 = r2 | r5
            r5 = 17179869184(0x400000000, double:8.4879831639E-314)
            goto L_0x030f
        L_0x0306:
            r5 = 536870912(0x20000000, double:2.652494739E-315)
            long r2 = r2 | r5
            r5 = 8589934592(0x200000000, double:4.243991582E-314)
        L_0x030f:
            long r2 = r2 | r5
        L_0x0310:
            android.widget.Button r5 = r1.ibNext
            if (r4 == 0) goto L_0x0317
            int r6 = com.eternal.account.C0997R.C0998color.color_transparency
            goto L_0x0319
        L_0x0317:
            int r6 = com.eternal.account.C0997R.C0998color.white
        L_0x0319:
            int r5 = getColorFromResource(r5, r6)
            if (r4 == 0) goto L_0x0321
            r4 = 0
            goto L_0x0328
        L_0x0321:
            r4 = 8
            goto L_0x0328
        L_0x0324:
            r64 = r5
            r4 = 0
            r5 = 0
        L_0x0328:
            r44 = 102400(0x19000, double:5.05923E-319)
            long r65 = r2 & r44
            r50 = 0
            int r6 = (r65 > r50 ? 1 : (r65 == r50 ? 0 : -1))
            r65 = r4
            if (r6 == 0) goto L_0x0366
            if (r0 == 0) goto L_0x033c
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.pwdErrVisible
            r66 = r5
            goto L_0x0340
        L_0x033c:
            r66 = r5
            r4 = r40
        L_0x0340:
            r5 = 12
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x034e
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0350
        L_0x034e:
            r4 = r40
        L_0x0350:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x0360
            if (r4 == 0) goto L_0x035c
            r5 = 1048576(0x100000, double:5.180654E-318)
            goto L_0x035f
        L_0x035c:
            r5 = 524288(0x80000, double:2.590327E-318)
        L_0x035f:
            long r2 = r2 | r5
        L_0x0360:
            if (r4 == 0) goto L_0x0363
            goto L_0x0368
        L_0x0363:
            r59 = 8
            goto L_0x036a
        L_0x0366:
            r66 = r5
        L_0x0368:
            r59 = 0
        L_0x036a:
            long r4 = r2 & r38
            r50 = 0
            int r6 = (r4 > r50 ? 1 : (r4 == r50 ? 0 : -1))
            if (r6 == 0) goto L_0x038e
            if (r0 == 0) goto L_0x0377
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r4 = r0.emailColor
            goto L_0x0379
        L_0x0377:
            r4 = r40
        L_0x0379:
            r5 = 13
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0387
            java.lang.Object r4 = r4.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x0389
        L_0x0387:
            r4 = r40
        L_0x0389:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x038f
        L_0x038e:
            r4 = 0
        L_0x038f:
            r5 = 114688(0x1c000, double:5.66634E-319)
            long r67 = r2 & r5
            r5 = 0
            int r69 = (r67 > r5 ? 1 : (r67 == r5 ? 0 : -1))
            if (r69 == 0) goto L_0x03d6
            if (r0 == 0) goto L_0x039f
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r0.pwdLineColor
            goto L_0x03a1
        L_0x039f:
            r0 = r40
        L_0x03a1:
            r5 = 14
            r1.updateLiveDataRegistration(r5, r0)
            if (r0 == 0) goto L_0x03af
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L_0x03b1
        L_0x03af:
            r0 = r40
        L_0x03b1:
            int r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r0)
            r70 = r9
            r9 = r11
            r71 = r15
            r72 = r42
            r5 = r53
            r6 = r55
            r55 = r56
            r73 = r59
            r42 = r61
            r53 = r62
            r59 = r64
            r56 = r0
            r11 = r10
            r0 = r12
            r15 = r14
            r14 = r52
            r12 = r57
            r57 = r65
            goto L_0x03f6
        L_0x03d6:
            r70 = r9
            r9 = r11
            r0 = r12
            r71 = r15
            r72 = r42
            r5 = r53
            r6 = r55
            r55 = r56
            r12 = r57
            r73 = r59
            r42 = r61
            r53 = r62
            r59 = r64
            r57 = r65
            r56 = 0
            r11 = r10
            r15 = r14
            r14 = r52
        L_0x03f6:
            r10 = r7
            r52 = r43
            r43 = r58
            r7 = r63
            r58 = r66
            r74 = r54
            r54 = r8
            r8 = r13
            r13 = r74
            goto L_0x0433
        L_0x0407:
            r0 = r40
            r5 = r0
            r6 = r5
            r8 = r6
            r9 = r8
            r11 = r9
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r42 = r15
            r52 = r42
            r54 = r52
            r55 = r54
            r59 = r55
            r60 = r59
            r70 = r60
            r72 = r70
            r4 = 0
            r7 = 0
            r10 = 0
            r43 = 0
            r53 = 0
            r56 = 0
            r57 = 0
            r58 = 0
            r71 = 0
            r73 = 0
        L_0x0433:
            long r38 = r2 & r38
            r50 = 0
            int r61 = (r38 > r50 ? 1 : (r38 == r50 ? 0 : -1))
            r38 = r7
            if (r61 == 0) goto L_0x0447
            android.widget.TextView r7 = r1.createEmailTitle
            r7.setTextColor(r4)
            android.widget.EditText r7 = r1.etEmail
            r7.setTextColor(r4)
        L_0x0447:
            long r34 = r2 & r34
            int r4 = (r34 > r50 ? 1 : (r34 == r50 ? 0 : -1))
            if (r4 == 0) goto L_0x0457
            android.widget.TextView r4 = r1.createPasswordTitle
            r4.setTextColor(r10)
            android.widget.EditText r4 = r1.etPwd
            r4.setTextColor(r10)
        L_0x0457:
            long r32 = r2 & r32
            int r4 = (r32 > r50 ? 1 : (r32 == r50 ? 0 : -1))
            if (r4 == 0) goto L_0x0487
            android.widget.EditText r4 = r1.etEmail
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r4, r15)
            android.widget.EditText r4 = r1.etPwd
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r4, r9)
            android.widget.Button r4 = r1.ibNext
            r7 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r5, r7)
            android.widget.TextView r4 = r1.mboundView14
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r8, r7)
            android.widget.TextView r4 = r1.mboundView9
            r5 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r14, r5)
            com.eternal.widget.guqiang.Toolbar r4 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r4, r13)
            com.eternal.widget.guqiang.Toolbar r4 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onNext(r4, r11)
            android.widget.TextView r4 = r1.tvForgot
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r0, r7)
        L_0x0487:
            long r4 = r2 & r28
            r7 = 0
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0494
            android.widget.EditText r0 = r1.etEmail
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x0494:
            r4 = 65536(0x10000, double:3.2379E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x04b6
            android.widget.EditText r0 = r1.etEmail
            r4 = r40
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r4 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r4
            r5 = r40
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r5 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r5
            r6 = r40
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r6 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r6
            androidx.databinding.InverseBindingListener r7 = r1.etEmailandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r4, r5, r6, r7)
            android.widget.EditText r0 = r1.etPwd
            androidx.databinding.InverseBindingListener r7 = r1.etPwdandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r4, r5, r6, r7)
        L_0x04b6:
            long r4 = r2 & r36
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04c3
            android.widget.EditText r0 = r1.etPwd
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x04c3:
            long r4 = r2 & r24
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04d2
            android.widget.EditText r0 = r1.etPwd
            r4 = r60
            android.text.method.TransformationMethod r4 = (android.text.method.TransformationMethod) r4
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.setTransform(r0, r4)
        L_0x04d2:
            long r4 = r2 & r20
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04e6
            android.widget.Button r0 = r1.ibNext
            r4 = r38
            r0.setClickable(r4)
            android.widget.Button r0 = r1.ibNext
            r4 = r59
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundDrawable(r0, r4)
        L_0x04e6:
            long r4 = r2 & r18
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04fa
            android.widget.Button r0 = r1.ibNext
            r4 = r58
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r4)
            android.widget.ProgressBar r0 = r1.pbLoading
            r4 = r57
            r0.setVisibility(r4)
        L_0x04fa:
            r4 = 114688(0x1c000, double:5.66634E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0509
            android.view.View r0 = r1.mboundView10
            r4 = r56
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r4)
        L_0x0509:
            long r4 = r2 & r26
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0516
            android.widget.TextView r0 = r1.mboundView11
            r4 = r55
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0516:
            r4 = 102400(0x19000, double:5.05923E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0525
            android.widget.TextView r0 = r1.mboundView11
            r4 = r73
            r0.setVisibility(r4)
        L_0x0525:
            long r4 = r2 & r30
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0551
            android.widget.TextView r0 = r1.mboundView14
            r15 = r71
            r0.setVisibility(r15)
            android.widget.TextView r0 = r1.mboundView2
            r0.setVisibility(r15)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            r8 = r54
            com.eternal.widget.guqiang.ToolbarAdapter.backRes(r0, r8)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            r9 = r70
            com.eternal.widget.guqiang.ToolbarAdapter.nextTitle(r0, r9)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            r4 = r72
            com.eternal.widget.guqiang.ToolbarAdapter.setTitle(r0, r4)
            android.widget.TextView r0 = r1.tvNew
            r0.setVisibility(r15)
        L_0x0551:
            r4 = 98560(0x18100, double:4.8695E-319)
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0562
            android.view.View r0 = r1.mboundView5
            r4 = r53
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r4)
        L_0x0562:
            long r4 = r2 & r16
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x056f
            android.widget.TextView r0 = r1.mboundView6
            r4 = r52
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x056f:
            long r4 = r2 & r22
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x057c
            android.widget.TextView r0 = r1.mboundView6
            r4 = r43
            r0.setVisibility(r4)
        L_0x057c:
            r4 = 98816(0x18200, double:4.88216E-319)
            long r2 = r2 & r4
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x058b
            android.widget.TextView r0 = r1.mboundView9
            r2 = r42
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x058b:
            return
        L_0x058c:
            r0 = move-exception
            monitor-exit(r75)     // Catch:{ all -> 0x058c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityLoginBindingImpl.executeBindings():void");
    }
}
