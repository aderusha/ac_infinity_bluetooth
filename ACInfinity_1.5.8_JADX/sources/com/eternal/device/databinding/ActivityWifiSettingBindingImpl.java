package com.eternal.device.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.ItemModel;
import com.eternal.device.model.WiFiSettingModel;

public class ActivityWifiSettingBindingImpl extends ActivityWifiSettingBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etPwdandroidTextAttrChanged;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final SwipeRefreshLayout mboundView1;
    private final View mboundView10;
    private final TextView mboundView11;
    private final RecyclerView mboundView2;
    private final RelativeLayout mboundView5;
    private final TextView mboundView9;
    private InverseBindingListener sbandroidCheckedAttrChanged;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.ll_sheet_dialog, 17);
        sparseIntArray.put(C1922R.C1925id.root, 18);
        sparseIntArray.put(C1922R.C1925id.rl_item, 19);
        sparseIntArray.put(C1922R.C1925id.ll_wifi_name, 20);
        sparseIntArray.put(C1922R.C1925id.tv_wifi_name_title, 21);
        sparseIntArray.put(C1922R.C1925id.create_password, 22);
        sparseIntArray.put(C1922R.C1925id.tv_content, 23);
    }

    public ActivityWifiSettingBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    private ActivityWifiSettingBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 15, objArr[12], objArr[22], objArr[7], objArr[8], objArr[14], objArr[17], objArr[20], objArr[15], objArr[19], objArr[18], objArr[4], objArr[3], objArr[23], objArr[13], objArr[6], objArr[21], objArr[16]);
        this.etPwdandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityWifiSettingBindingImpl.this.etPwd);
                WiFiSettingModel wiFiSettingModel = ActivityWifiSettingBindingImpl.this.mWifiSettingModel;
                boolean z = true;
                if (wiFiSettingModel != null) {
                    MutableLiveData<String> mutableLiveData = wiFiSettingModel.passwordText;
                    if (mutableLiveData == null) {
                        z = false;
                    }
                    if (z) {
                        mutableLiveData.setValue(textString);
                    }
                }
            }
        };
        this.sbandroidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = ActivityWifiSettingBindingImpl.this.f171sb.isChecked();
                WiFiSettingModel wiFiSettingModel = ActivityWifiSettingBindingImpl.this.mWifiSettingModel;
                boolean z = true;
                if (wiFiSettingModel != null) {
                    ObservableBoolean observableBoolean = wiFiSettingModel.open;
                    if (observableBoolean == null) {
                        z = false;
                    }
                    if (z) {
                        observableBoolean.set(isChecked);
                    }
                }
            }
        };
        this.mDirtyFlags = -1;
        this.btSavePassword.setTag((Object) null);
        this.createPasswordTitle.setTag((Object) null);
        this.etPwd.setTag((Object) null);
        this.ibNext.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        SwipeRefreshLayout swipeRefreshLayout = objArr[1];
        this.mboundView1 = swipeRefreshLayout;
        swipeRefreshLayout.setTag((Object) null);
        View view2 = objArr[10];
        this.mboundView10 = view2;
        view2.setTag((Object) null);
        TextView textView = objArr[11];
        this.mboundView11 = textView;
        textView.setTag((Object) null);
        RecyclerView recyclerView = objArr[2];
        this.mboundView2 = recyclerView;
        recyclerView.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[5];
        this.mboundView5 = relativeLayout;
        relativeLayout.setTag((Object) null);
        TextView textView2 = objArr[9];
        this.mboundView9 = textView2;
        textView2.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.f171sb.setTag("switch");
        this.toolBar.setTag((Object) null);
        this.tvHelp.setTag((Object) null);
        this.tvWifiName.setTag((Object) null);
        this.txtConnectTime.setTag((Object) null);
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
        if (C1909BR.wifiSettingModel != i) {
            return false;
        }
        setWifiSettingModel((WiFiSettingModel) obj);
        return true;
    }

    public void setWifiSettingModel(WiFiSettingModel wiFiSettingModel) {
        this.mWifiSettingModel = wiFiSettingModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(C1909BR.wifiSettingModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeWifiSettingModelTimeVisible((MutableLiveData) obj, i2);
            case 1:
                return onChangeWifiSettingModelTextVisiblePassword((MutableLiveData) obj, i2);
            case 2:
                return onChangeWifiSettingModelItems((ObservableList) obj, i2);
            case 3:
                return onChangeWifiSettingModelConfirmAble((MutableLiveData) obj, i2);
            case 4:
                return onChangeWifiSettingModelShowLoading((MutableLiveData) obj, i2);
            case 5:
                return onChangeWifiSettingModelPwdLineColor((MutableLiveData) obj, i2);
            case 6:
                return onChangeWifiSettingModelOpen((ObservableBoolean) obj, i2);
            case 7:
                return onChangeWifiSettingModelPwdErrVisible((MutableLiveData) obj, i2);
            case 8:
                return onChangeWifiSettingModelPasswordText((MutableLiveData) obj, i2);
            case 9:
                return onChangeWifiSettingModelIsLoading((MutableLiveData) obj, i2);
            case 10:
                return onChangeWifiSettingModelPwdColor((MutableLiveData) obj, i2);
            case 11:
                return onChangeWifiSettingModelTimeText((MutableLiveData) obj, i2);
            case 12:
                return onChangeWifiSettingModelPwdErrText((MutableLiveData) obj, i2);
            case 13:
                return onChangeWifiSettingModelName((MutableLiveData) obj, i2);
            case 14:
                return onChangeWifiSettingModelIsSavePwd((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeWifiSettingModelTimeVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelTextVisiblePassword(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelItems(ObservableList<ItemModel> observableList, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelPwdLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelOpen(ObservableBoolean observableBoolean, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelPwdErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelPasswordText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelIsLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelPwdColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelTimeText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelPwdErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        return true;
    }

    private boolean onChangeWifiSettingModelIsSavePwd(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x01b4  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01e1  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01ee  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x0217  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x0228  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0258  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x0263  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x02a2  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x02ad  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x02cd  */
    /* JADX WARNING: Removed duplicated region for block: B:184:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x02f4  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0303  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x031c  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x032b  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x0344  */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x0350  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x0394  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x015d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r74 = this;
            r1 = r74
            monitor-enter(r74)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0553 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0553 }
            monitor-exit(r74)     // Catch:{ all -> 0x0553 }
            com.eternal.device.model.WiFiSettingModel r0 = r1.mWifiSettingModel
            r6 = 131071(0x1ffff, double:6.47577E-319)
            long r6 = r6 & r2
            r16 = 98816(0x18200, double:4.88216E-319)
            r18 = 98368(0x18040, double:4.86002E-319)
            r20 = 98336(0x18020, double:4.85844E-319)
            r22 = 98320(0x18010, double:4.85765E-319)
            r24 = 98312(0x18008, double:4.85726E-319)
            r26 = 98560(0x18100, double:4.8695E-319)
            r28 = 98308(0x18004, double:4.85706E-319)
            r30 = 99328(0x18400, double:4.90746E-319)
            r32 = 98306(0x18002, double:4.85696E-319)
            r34 = 98304(0x18000, double:4.85686E-319)
            r36 = 98305(0x18001, double:4.8569E-319)
            r38 = 114688(0x1c000, double:5.66634E-319)
            r8 = 1
            r9 = 0
            r42 = 0
            int r43 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r43 == 0) goto L_0x03c9
            long r6 = r2 & r36
            int r13 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x006e
            if (r0 == 0) goto L_0x0047
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.timeVisible
            goto L_0x0049
        L_0x0047:
            r6 = r42
        L_0x0049:
            r1.updateLiveDataRegistration(r9, r6)
            if (r6 == 0) goto L_0x0055
            java.lang.Object r6 = r6.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            goto L_0x0057
        L_0x0055:
            r6 = r42
        L_0x0057:
            boolean r6 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r6)
            if (r13 == 0) goto L_0x0068
            if (r6 == 0) goto L_0x0063
            r45 = 268435456(0x10000000, double:1.32624737E-315)
            goto L_0x0066
        L_0x0063:
            r45 = 134217728(0x8000000, double:6.63123685E-316)
        L_0x0066:
            long r2 = r2 | r45
        L_0x0068:
            if (r6 == 0) goto L_0x006b
            goto L_0x006e
        L_0x006b:
            r6 = 8
            goto L_0x006f
        L_0x006e:
            r6 = 0
        L_0x006f:
            long r45 = r2 & r32
            int r7 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x00c0
            if (r0 == 0) goto L_0x007a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.textVisiblePassword
            goto L_0x007c
        L_0x007a:
            r13 = r42
        L_0x007c:
            r1.updateLiveDataRegistration(r8, r13)
            if (r13 == 0) goto L_0x0088
            java.lang.Object r13 = r13.getValue()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            goto L_0x008a
        L_0x0088:
            r13 = r42
        L_0x008a:
            boolean r13 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r13)
            if (r7 == 0) goto L_0x00ad
            if (r13 == 0) goto L_0x009f
            r45 = 4294967296(0x100000000, double:2.121995791E-314)
            long r2 = r2 | r45
            r45 = 17179869184(0x400000000, double:8.4879831639E-314)
            goto L_0x00ab
        L_0x009f:
            r45 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r2 = r2 | r45
            r45 = 8589934592(0x200000000, double:4.243991582E-314)
        L_0x00ab:
            long r2 = r2 | r45
        L_0x00ad:
            if (r13 == 0) goto L_0x00b2
            java.lang.String r7 = "HIDE"
            goto L_0x00b4
        L_0x00b2:
            java.lang.String r7 = "SHOW"
        L_0x00b4:
            if (r13 == 0) goto L_0x00bb
            android.text.method.HideReturnsTransformationMethod r13 = android.text.method.HideReturnsTransformationMethod.getInstance()
            goto L_0x00c3
        L_0x00bb:
            android.text.method.PasswordTransformationMethod r13 = android.text.method.PasswordTransformationMethod.getInstance()
            goto L_0x00c3
        L_0x00c0:
            r7 = r42
            r13 = r7
        L_0x00c3:
            long r45 = r2 & r28
            int r47 = (r45 > r4 ? 1 : (r45 == r4 ? 0 : -1))
            if (r47 == 0) goto L_0x00d8
            if (r0 == 0) goto L_0x00d0
            androidx.databinding.ObservableList<com.eternal.device.model.ItemModel> r9 = r0.items
            me.tatarka.bindingcollectionadapter2.ItemBinding<com.eternal.device.model.ItemModel> r8 = r0.itemBinding
            goto L_0x00d3
        L_0x00d0:
            r8 = r42
            r9 = r8
        L_0x00d3:
            r10 = 2
            r1.updateRegistration((int) r10, (androidx.databinding.ObservableList) r9)
            goto L_0x00db
        L_0x00d8:
            r8 = r42
            r9 = r8
        L_0x00db:
            long r10 = r2 & r24
            int r49 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r49 == 0) goto L_0x011a
            if (r0 == 0) goto L_0x00e6
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.confirmAble
            goto L_0x00e8
        L_0x00e6:
            r10 = r42
        L_0x00e8:
            r11 = 3
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x00f5
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x00f7
        L_0x00f5:
            r10 = r42
        L_0x00f7:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r49 == 0) goto L_0x0108
            if (r10 == 0) goto L_0x0103
            r49 = 67108864(0x4000000, double:3.31561842E-316)
            goto L_0x0106
        L_0x0103:
            r49 = 33554432(0x2000000, double:1.6578092E-316)
        L_0x0106:
            long r2 = r2 | r49
        L_0x0108:
            android.widget.Button r11 = r1.ibNext
            android.content.Context r11 = r11.getContext()
            if (r10 == 0) goto L_0x0113
            int r12 = com.eternal.device.C1922R.C1924drawable.create_account_gradient
            goto L_0x0115
        L_0x0113:
            int r12 = com.eternal.device.C1922R.C1924drawable.create_account_disable
        L_0x0115:
            android.graphics.drawable.Drawable r11 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r11, r12)
            goto L_0x011d
        L_0x011a:
            r11 = r42
            r10 = 0
        L_0x011d:
            long r50 = r2 & r34
            int r12 = (r50 > r4 ? 1 : (r50 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x0147
            if (r0 == 0) goto L_0x0147
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onPwdShow
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r14 = r0.onSetting
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onHelp
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onRefresh
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r5 = r0.onBack
            r54 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onSavePwd
            r55 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r4 = r0.pwdTextChanged
            r56 = r4
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r4 = r0.onNext
            r73 = r5
            r5 = r4
            r4 = r54
            r54 = r15
            r15 = r14
            r14 = r12
            r12 = r73
            goto L_0x0153
        L_0x0147:
            r4 = r42
            r5 = r4
            r12 = r5
            r14 = r12
            r15 = r14
            r54 = r15
            r55 = r54
            r56 = r55
        L_0x0153:
            long r57 = r2 & r22
            r52 = 0
            int r59 = (r57 > r52 ? 1 : (r57 == r52 ? 0 : -1))
            r57 = r4
            if (r59 == 0) goto L_0x01b4
            if (r0 == 0) goto L_0x0164
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.showLoading
            r58 = r5
            goto L_0x0168
        L_0x0164:
            r58 = r5
            r4 = r42
        L_0x0168:
            r5 = 4
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0175
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0177
        L_0x0175:
            r4 = r42
        L_0x0177:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r59 == 0) goto L_0x0192
            if (r4 == 0) goto L_0x0188
            r59 = 1048576(0x100000, double:5.180654E-318)
            long r2 = r2 | r59
            r59 = 4194304(0x400000, double:2.0722615E-317)
            goto L_0x0190
        L_0x0188:
            r59 = 524288(0x80000, double:2.590327E-318)
            long r2 = r2 | r59
            r59 = 2097152(0x200000, double:1.0361308E-317)
        L_0x0190:
            long r2 = r2 | r59
        L_0x0192:
            if (r4 == 0) goto L_0x019f
            android.widget.Button r5 = r1.ibNext
            r59 = r2
            int r2 = com.eternal.device.C1922R.C1923color.color_transparency
            int r2 = getColorFromResource(r5, r2)
            goto L_0x01a9
        L_0x019f:
            r59 = r2
            android.widget.Button r2 = r1.ibNext
            int r3 = com.eternal.device.C1922R.C1923color.white
            int r2 = getColorFromResource(r2, r3)
        L_0x01a9:
            if (r4 == 0) goto L_0x01ad
            r3 = 0
            goto L_0x01af
        L_0x01ad:
            r3 = 8
        L_0x01af:
            r4 = r2
            r5 = r3
            r2 = r59
            goto L_0x01b8
        L_0x01b4:
            r58 = r5
            r4 = 0
            r5 = 0
        L_0x01b8:
            long r59 = r2 & r20
            r52 = 0
            int r61 = (r59 > r52 ? 1 : (r59 == r52 ? 0 : -1))
            r59 = r4
            if (r61 == 0) goto L_0x01e1
            if (r0 == 0) goto L_0x01c9
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r4 = r0.pwdLineColor
            r60 = r5
            goto L_0x01cd
        L_0x01c9:
            r60 = r5
            r4 = r42
        L_0x01cd:
            r5 = 5
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x01da
            java.lang.Object r4 = r4.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x01dc
        L_0x01da:
            r4 = r42
        L_0x01dc:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x01e4
        L_0x01e1:
            r60 = r5
            r4 = 0
        L_0x01e4:
            long r61 = r2 & r18
            r52 = 0
            int r5 = (r61 > r52 ? 1 : (r61 == r52 ? 0 : -1))
            r61 = r4
            if (r5 == 0) goto L_0x0217
            if (r0 == 0) goto L_0x01f5
            androidx.databinding.ObservableBoolean r4 = r0.open
            r62 = r6
            goto L_0x01f9
        L_0x01f5:
            r62 = r6
            r4 = r42
        L_0x01f9:
            r6 = 6
            r1.updateRegistration((int) r6, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0204
            boolean r4 = r4.get()
            goto L_0x0205
        L_0x0204:
            r4 = 0
        L_0x0205:
            if (r5 == 0) goto L_0x0211
            if (r4 == 0) goto L_0x020d
            r5 = 262144(0x40000, double:1.295163E-318)
            goto L_0x0210
        L_0x020d:
            r5 = 131072(0x20000, double:6.47582E-319)
        L_0x0210:
            long r2 = r2 | r5
        L_0x0211:
            if (r4 == 0) goto L_0x0214
            goto L_0x021a
        L_0x0214:
            r5 = 8
            goto L_0x021b
        L_0x0217:
            r62 = r6
            r4 = 0
        L_0x021a:
            r5 = 0
        L_0x021b:
            r50 = 98432(0x18080, double:4.8632E-319)
            long r63 = r2 & r50
            r52 = 0
            int r6 = (r63 > r52 ? 1 : (r63 == r52 ? 0 : -1))
            r63 = r4
            if (r6 == 0) goto L_0x0258
            if (r0 == 0) goto L_0x022f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.pwdErrVisible
            r64 = r5
            goto L_0x0233
        L_0x022f:
            r64 = r5
            r4 = r42
        L_0x0233:
            r5 = 7
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0240
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0242
        L_0x0240:
            r4 = r42
        L_0x0242:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x0252
            if (r4 == 0) goto L_0x024e
            r5 = 16777216(0x1000000, double:8.289046E-317)
            goto L_0x0251
        L_0x024e:
            r5 = 8388608(0x800000, double:4.144523E-317)
        L_0x0251:
            long r2 = r2 | r5
        L_0x0252:
            if (r4 == 0) goto L_0x0255
            goto L_0x025a
        L_0x0255:
            r4 = 8
            goto L_0x025b
        L_0x0258:
            r64 = r5
        L_0x025a:
            r4 = 0
        L_0x025b:
            long r5 = r2 & r26
            r52 = 0
            int r65 = (r5 > r52 ? 1 : (r5 == r52 ? 0 : -1))
            if (r65 == 0) goto L_0x0278
            if (r0 == 0) goto L_0x0268
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.passwordText
            goto L_0x026a
        L_0x0268:
            r5 = r42
        L_0x026a:
            r6 = 8
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x0278
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x027a
        L_0x0278:
            r5 = r42
        L_0x027a:
            long r65 = r2 & r16
            r52 = 0
            int r6 = (r65 > r52 ? 1 : (r65 == r52 ? 0 : -1))
            if (r6 == 0) goto L_0x02a2
            if (r0 == 0) goto L_0x0289
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.isLoading
            r49 = r4
            goto L_0x028d
        L_0x0289:
            r49 = r4
            r6 = r42
        L_0x028d:
            r4 = 9
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x029b
            java.lang.Object r4 = r6.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x029d
        L_0x029b:
            r4 = r42
        L_0x029d:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x02a5
        L_0x02a2:
            r49 = r4
            r4 = 0
        L_0x02a5:
            long r65 = r2 & r30
            r52 = 0
            int r6 = (r65 > r52 ? 1 : (r65 == r52 ? 0 : -1))
            if (r6 == 0) goto L_0x02cd
            if (r0 == 0) goto L_0x02b4
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r6 = r0.pwdColor
            r65 = r4
            goto L_0x02b8
        L_0x02b4:
            r65 = r4
            r6 = r42
        L_0x02b8:
            r4 = 10
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x02c6
            java.lang.Object r4 = r6.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x02c8
        L_0x02c6:
            r4 = r42
        L_0x02c8:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x02d0
        L_0x02cd:
            r65 = r4
            r4 = 0
        L_0x02d0:
            r47 = 100352(0x18800, double:4.95805E-319)
            long r66 = r2 & r47
            r52 = 0
            int r6 = (r66 > r52 ? 1 : (r66 == r52 ? 0 : -1))
            if (r6 == 0) goto L_0x02f4
            if (r0 == 0) goto L_0x02e2
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.timeText
            r66 = r4
            goto L_0x02e6
        L_0x02e2:
            r66 = r4
            r6 = r42
        L_0x02e6:
            r4 = 11
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x02f6
            java.lang.Object r4 = r6.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x02f8
        L_0x02f4:
            r66 = r4
        L_0x02f6:
            r4 = r42
        L_0x02f8:
            r43 = 102400(0x19000, double:5.05923E-319)
            long r67 = r2 & r43
            r52 = 0
            int r6 = (r67 > r52 ? 1 : (r67 == r52 ? 0 : -1))
            if (r6 == 0) goto L_0x031c
            if (r0 == 0) goto L_0x030a
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.pwdErrText
            r67 = r4
            goto L_0x030e
        L_0x030a:
            r67 = r4
            r6 = r42
        L_0x030e:
            r4 = 12
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x031e
            java.lang.Object r4 = r6.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0320
        L_0x031c:
            r67 = r4
        L_0x031e:
            r4 = r42
        L_0x0320:
            r40 = 106496(0x1a000, double:5.2616E-319)
            long r68 = r2 & r40
            r52 = 0
            int r6 = (r68 > r52 ? 1 : (r68 == r52 ? 0 : -1))
            if (r6 == 0) goto L_0x0344
            if (r0 == 0) goto L_0x0332
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.name
            r68 = r4
            goto L_0x0336
        L_0x0332:
            r68 = r4
            r6 = r42
        L_0x0336:
            r4 = 13
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x0346
            java.lang.Object r4 = r6.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0348
        L_0x0344:
            r68 = r4
        L_0x0346:
            r4 = r42
        L_0x0348:
            long r69 = r2 & r38
            r52 = 0
            int r6 = (r69 > r52 ? 1 : (r69 == r52 ? 0 : -1))
            if (r6 == 0) goto L_0x0394
            if (r0 == 0) goto L_0x0357
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.isSavePwd
            r69 = r4
            goto L_0x035b
        L_0x0357:
            r69 = r4
            r0 = r42
        L_0x035b:
            r4 = 14
            r1.updateLiveDataRegistration(r4, r0)
            if (r0 == 0) goto L_0x0369
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            goto L_0x036b
        L_0x0369:
            r0 = r42
        L_0x036b:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r0)
            if (r6 == 0) goto L_0x037c
            if (r0 == 0) goto L_0x0377
            r70 = 1073741824(0x40000000, double:5.304989477E-315)
            goto L_0x037a
        L_0x0377:
            r70 = 536870912(0x20000000, double:2.652494739E-315)
        L_0x037a:
            long r2 = r2 | r70
        L_0x037c:
            if (r0 == 0) goto L_0x0387
            android.widget.Button r0 = r1.btSavePassword
            android.content.Context r0 = r0.getContext()
            int r4 = com.eternal.device.C1922R.C1924drawable.layer_save_password_checked
            goto L_0x038f
        L_0x0387:
            android.widget.Button r0 = r1.btSavePassword
            android.content.Context r0 = r0.getContext()
            int r4 = com.eternal.device.C1922R.C1924drawable.layer_save_password_uncheck
        L_0x038f:
            android.graphics.drawable.Drawable r0 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r0, r4)
            goto L_0x0398
        L_0x0394:
            r69 = r4
            r0 = r42
        L_0x0398:
            r6 = r55
            r4 = r57
            r72 = r62
            r55 = r63
            r62 = r64
            r57 = r8
            r8 = r14
            r14 = r58
            r64 = r59
            r63 = r60
            r60 = r61
            r61 = r65
            r58 = r68
            r65 = r11
            r59 = r13
            r13 = r54
            r54 = r69
            r11 = r7
            r7 = r15
            r15 = r5
            r5 = r66
            r66 = r9
            r9 = r12
            r12 = r10
            r10 = r56
            r56 = r49
            r49 = r67
            goto L_0x03f5
        L_0x03c9:
            r0 = r42
            r4 = r0
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r13 = r11
            r14 = r13
            r15 = r14
            r49 = r15
            r54 = r49
            r57 = r54
            r58 = r57
            r59 = r58
            r65 = r59
            r66 = r65
            r5 = 0
            r12 = 0
            r55 = 0
            r56 = 0
            r60 = 0
            r61 = 0
            r62 = 0
            r63 = 0
            r64 = 0
            r72 = 0
        L_0x03f5:
            long r38 = r2 & r38
            r52 = 0
            int r67 = (r38 > r52 ? 1 : (r38 == r52 ? 0 : -1))
            r38 = r12
            if (r67 == 0) goto L_0x0404
            android.widget.Button r12 = r1.btSavePassword
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.drawableStart(r12, r0)
        L_0x0404:
            long r34 = r2 & r34
            int r0 = (r34 > r52 ? 1 : (r34 == r52 ? 0 : -1))
            if (r0 == 0) goto L_0x0434
            android.widget.Button r0 = r1.btSavePassword
            r12 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r6, r12)
            android.widget.EditText r0 = r1.etPwd
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r0, r10)
            android.widget.Button r0 = r1.ibNext
            r6 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r14, r6)
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r0 = r1.mboundView1
            com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter.onRefreshCommand(r0, r4)
            android.widget.TextView r0 = r1.mboundView9
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r8, r12)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r0, r9)
            android.widget.TextView r0 = r1.tvHelp
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r13, r6)
            android.widget.TextView r0 = r1.tvWifiName
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r7, r6)
        L_0x0434:
            long r6 = r2 & r30
            r8 = 0
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0446
            android.widget.TextView r0 = r1.createPasswordTitle
            r0.setTextColor(r5)
            android.widget.EditText r0 = r1.etPwd
            r0.setTextColor(r5)
        L_0x0446:
            long r4 = r2 & r26
            int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0451
            android.widget.EditText r0 = r1.etPwd
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r15)
        L_0x0451:
            long r4 = r2 & r32
            int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0465
            android.widget.EditText r0 = r1.etPwd
            r4 = r59
            android.text.method.TransformationMethod r4 = (android.text.method.TransformationMethod) r4
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.setTransform(r0, r4)
            android.widget.TextView r0 = r1.mboundView9
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r11)
        L_0x0465:
            r4 = 65536(0x10000, double:3.2379E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0494
            android.widget.EditText r0 = r1.etPwd
            r4 = r42
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r4 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r4
            r5 = r42
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r5 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r5
            r6 = r42
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r6 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r6
            androidx.databinding.InverseBindingListener r7 = r1.etPwdandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r4, r5, r6, r7)
            androidx.recyclerview.widget.RecyclerView r0 = r1.mboundView2
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r4 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r0, r4)
            com.eternal.base.StatueSwitch r0 = r1.f171sb
            r4 = r42
            android.widget.CompoundButton$OnCheckedChangeListener r4 = (android.widget.CompoundButton.OnCheckedChangeListener) r4
            androidx.databinding.InverseBindingListener r5 = r1.sbandroidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r0, r4, r5)
        L_0x0494:
            long r4 = r2 & r24
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04aa
            android.widget.Button r0 = r1.ibNext
            r10 = r38
            r0.setClickable(r10)
            android.widget.Button r0 = r1.ibNext
            r11 = r65
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundDrawable(r0, r11)
        L_0x04aa:
            long r4 = r2 & r22
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04be
            android.widget.Button r0 = r1.ibNext
            r4 = r64
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r4)
            android.widget.ProgressBar r0 = r1.pbLoading
            r4 = r63
            r0.setVisibility(r4)
        L_0x04be:
            long r4 = r2 & r16
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04cb
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r0 = r1.mboundView1
            r4 = r61
            com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter.setRefreshing(r0, r4)
        L_0x04cb:
            long r4 = r2 & r20
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04d8
            android.view.View r0 = r1.mboundView10
            r4 = r60
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r4)
        L_0x04d8:
            r4 = 102400(0x19000, double:5.05923E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04e7
            android.widget.TextView r0 = r1.mboundView11
            r4 = r58
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x04e7:
            r4 = 98432(0x18080, double:4.8632E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x04f6
            android.widget.TextView r0 = r1.mboundView11
            r4 = r56
            r0.setVisibility(r4)
        L_0x04f6:
            long r4 = r2 & r28
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0511
            androidx.recyclerview.widget.RecyclerView r0 = r1.mboundView2
            r59 = r42
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r59 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r59
            r60 = r42
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r60 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r60
            r61 = r42
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r61 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r61
            r56 = r0
            r58 = r66
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r56, r57, r58, r59, r60, r61)
        L_0x0511:
            long r4 = r2 & r18
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0527
            android.widget.RelativeLayout r0 = r1.mboundView5
            r4 = r62
            r0.setVisibility(r4)
            com.eternal.base.StatueSwitch r0 = r1.f171sb
            r4 = r55
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r0, r4)
        L_0x0527:
            r4 = 106496(0x1a000, double:5.2616E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0536
            android.widget.TextView r0 = r1.tvWifiName
            r4 = r54
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0536:
            r4 = 100352(0x18800, double:4.95805E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0545
            android.widget.TextView r0 = r1.txtConnectTime
            r4 = r49
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0545:
            long r2 = r2 & r36
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0552
            android.widget.TextView r0 = r1.txtConnectTime
            r2 = r72
            r0.setVisibility(r2)
        L_0x0552:
            return
        L_0x0553:
            r0 = move-exception
            monitor-exit(r74)     // Catch:{ all -> 0x0553 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.device.databinding.ActivityWifiSettingBindingImpl.executeBindings():void");
    }
}
