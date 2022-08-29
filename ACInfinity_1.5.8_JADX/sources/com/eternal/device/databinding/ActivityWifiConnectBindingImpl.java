package com.eternal.device.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.ItemModel;
import com.eternal.device.model.WiFiConnectModel;

public class ActivityWifiConnectBindingImpl extends ActivityWifiConnectBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etPwdandroidTextAttrChanged;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final SwipeRefreshLayout mboundView1;
    private final RecyclerView mboundView2;
    private final TextView mboundView7;
    private final View mboundView8;
    private final TextView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.ll_sheet_dialog, 14);
        sparseIntArray.put(C1922R.C1925id.root, 15);
        sparseIntArray.put(C1922R.C1925id.tv_title, 16);
        sparseIntArray.put(C1922R.C1925id.ll_wifi_name, 17);
        sparseIntArray.put(C1922R.C1925id.tv_wifi_name_title, 18);
        sparseIntArray.put(C1922R.C1925id.create_password, 19);
        sparseIntArray.put(C1922R.C1925id.tv_content, 20);
    }

    public ActivityWifiConnectBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 21, sIncludes, sViewsWithIds));
    }

    private ActivityWifiConnectBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 12, objArr[10], objArr[19], objArr[5], objArr[6], objArr[12], objArr[14], objArr[17], objArr[13], objArr[15], objArr[3], objArr[20], objArr[11], objArr[16], objArr[4], objArr[18]);
        this.etPwdandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityWifiConnectBindingImpl.this.etPwd);
                WiFiConnectModel wiFiConnectModel = ActivityWifiConnectBindingImpl.this.mWifiConnectModel;
                boolean z = true;
                if (wiFiConnectModel != null) {
                    MutableLiveData<String> mutableLiveData = wiFiConnectModel.passwordText;
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
        RecyclerView recyclerView = objArr[2];
        this.mboundView2 = recyclerView;
        recyclerView.setTag((Object) null);
        TextView textView = objArr[7];
        this.mboundView7 = textView;
        textView.setTag((Object) null);
        View view2 = objArr[8];
        this.mboundView8 = view2;
        view2.setTag((Object) null);
        TextView textView2 = objArr[9];
        this.mboundView9 = textView2;
        textView2.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvHelp.setTag((Object) null);
        this.tvWifiName.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
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
        if (C1909BR.wifiConnectModel != i) {
            return false;
        }
        setWifiConnectModel((WiFiConnectModel) obj);
        return true;
    }

    public void setWifiConnectModel(WiFiConnectModel wiFiConnectModel) {
        this.mWifiConnectModel = wiFiConnectModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        notifyPropertyChanged(C1909BR.wifiConnectModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeWifiConnectModelPwdErrVisible((MutableLiveData) obj, i2);
            case 1:
                return onChangeWifiConnectModelShowLoading((MutableLiveData) obj, i2);
            case 2:
                return onChangeWifiConnectModelPasswordText((MutableLiveData) obj, i2);
            case 3:
                return onChangeWifiConnectModelPwdColor((MutableLiveData) obj, i2);
            case 4:
                return onChangeWifiConnectModelPwdLineColor((MutableLiveData) obj, i2);
            case 5:
                return onChangeWifiConnectModelPwdErrText((MutableLiveData) obj, i2);
            case 6:
                return onChangeWifiConnectModelName((MutableLiveData) obj, i2);
            case 7:
                return onChangeWifiConnectModelConfirmAble((MutableLiveData) obj, i2);
            case 8:
                return onChangeWifiConnectModelIsSavePwd((MutableLiveData) obj, i2);
            case 9:
                return onChangeWifiConnectModelIsLoading((MutableLiveData) obj, i2);
            case 10:
                return onChangeWifiConnectModelItems((ObservableList) obj, i2);
            case 11:
                return onChangeWifiConnectModelTextVisiblePassword((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeWifiConnectModelPwdErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelPasswordText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelPwdColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelPwdLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelPwdErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelIsSavePwd(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelIsLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelItems(ObservableList<ItemModel> observableList, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeWifiConnectModelTextVisiblePassword(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01e6  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x025b  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x026a  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0295  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x0307  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0127  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0184  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r62 = this;
            r1 = r62
            monitor-enter(r62)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0481 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0481 }
            monitor-exit(r62)     // Catch:{ all -> 0x0481 }
            com.eternal.device.model.WiFiConnectModel r0 = r1.mWifiConnectModel
            r6 = 16383(0x3fff, double:8.0943E-320)
            long r6 = r6 & r2
            r16 = 12416(0x3080, double:6.1343E-320)
            r18 = 12304(0x3010, double:6.079E-320)
            r20 = 14336(0x3800, double:7.083E-320)
            r22 = 12292(0x3004, double:6.073E-320)
            r24 = 12290(0x3002, double:6.072E-320)
            r26 = 12296(0x3008, double:6.075E-320)
            r28 = 12289(0x3001, double:6.0716E-320)
            r30 = 12288(0x3000, double:6.071E-320)
            r32 = 12544(0x3100, double:6.1976E-320)
            r36 = 0
            int r37 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r37 == 0) goto L_0x0334
            long r6 = r2 & r30
            int r37 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r37 == 0) goto L_0x0042
            if (r0 == 0) goto L_0x0042
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r6 = r0.onCancel
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r7 = r0.pwdTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r8 = r0.onPwdShow
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onRefresh
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onNext
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r14 = r0.onSetting
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onHelp
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r10 = r0.onSavePwd
            goto L_0x004c
        L_0x0042:
            r6 = r36
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r12 = r10
            r13 = r12
            r14 = r13
            r15 = r14
        L_0x004c:
            long r43 = r2 & r28
            int r45 = (r43 > r4 ? 1 : (r43 == r4 ? 0 : -1))
            if (r45 == 0) goto L_0x007f
            if (r0 == 0) goto L_0x0057
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r11 = r0.pwdErrVisible
            goto L_0x0059
        L_0x0057:
            r11 = r36
        L_0x0059:
            r4 = 0
            r1.updateLiveDataRegistration(r4, r11)
            if (r11 == 0) goto L_0x0066
            java.lang.Object r4 = r11.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0068
        L_0x0066:
            r4 = r36
        L_0x0068:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r45 == 0) goto L_0x0079
            if (r4 == 0) goto L_0x0074
            r48 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x0077
        L_0x0074:
            r48 = 1048576(0x100000, double:5.180654E-318)
        L_0x0077:
            long r2 = r2 | r48
        L_0x0079:
            if (r4 == 0) goto L_0x007c
            goto L_0x007f
        L_0x007c:
            r4 = 8
            goto L_0x0080
        L_0x007f:
            r4 = 0
        L_0x0080:
            long r48 = r2 & r24
            r45 = 0
            int r5 = (r48 > r45 ? 1 : (r48 == r45 ? 0 : -1))
            if (r5 == 0) goto L_0x00d8
            if (r0 == 0) goto L_0x008f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r11 = r0.showLoading
            r44 = r4
            goto L_0x0093
        L_0x008f:
            r44 = r4
            r11 = r36
        L_0x0093:
            r4 = 1
            r1.updateLiveDataRegistration(r4, r11)
            if (r11 == 0) goto L_0x00a0
            java.lang.Object r4 = r11.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00a2
        L_0x00a0:
            r4 = r36
        L_0x00a2:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x00bd
            if (r4 == 0) goto L_0x00b3
            r48 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r48
            r48 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x00bb
        L_0x00b3:
            r48 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r48
            r48 = 4194304(0x400000, double:2.0722615E-317)
        L_0x00bb:
            long r2 = r2 | r48
        L_0x00bd:
            if (r4 == 0) goto L_0x00c1
            r5 = 0
            goto L_0x00c3
        L_0x00c1:
            r5 = 8
        L_0x00c3:
            if (r4 == 0) goto L_0x00ca
            android.widget.Button r4 = r1.ibNext
            int r11 = com.eternal.device.C1922R.C1923color.color_transparency
            goto L_0x00ce
        L_0x00ca:
            android.widget.Button r4 = r1.ibNext
            int r11 = com.eternal.device.C1922R.C1923color.white
        L_0x00ce:
            int r4 = getColorFromResource(r4, r11)
            r59 = r5
            r5 = r4
            r4 = r59
            goto L_0x00dc
        L_0x00d8:
            r44 = r4
            r4 = 0
            r5 = 0
        L_0x00dc:
            long r48 = r2 & r22
            r45 = 0
            int r11 = (r48 > r45 ? 1 : (r48 == r45 ? 0 : -1))
            if (r11 == 0) goto L_0x00fc
            if (r0 == 0) goto L_0x00eb
            androidx.lifecycle.MutableLiveData<java.lang.String> r11 = r0.passwordText
            r45 = r4
            goto L_0x00ef
        L_0x00eb:
            r45 = r4
            r11 = r36
        L_0x00ef:
            r4 = 2
            r1.updateLiveDataRegistration(r4, r11)
            if (r11 == 0) goto L_0x00fe
            java.lang.Object r4 = r11.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0100
        L_0x00fc:
            r45 = r4
        L_0x00fe:
            r4 = r36
        L_0x0100:
            long r48 = r2 & r26
            r46 = 0
            int r11 = (r48 > r46 ? 1 : (r48 == r46 ? 0 : -1))
            if (r11 == 0) goto L_0x0127
            if (r0 == 0) goto L_0x010f
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r11 = r0.pwdColor
            r48 = r4
            goto L_0x0113
        L_0x010f:
            r48 = r4
            r11 = r36
        L_0x0113:
            r4 = 3
            r1.updateLiveDataRegistration(r4, r11)
            if (r11 == 0) goto L_0x0120
            java.lang.Object r4 = r11.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x0122
        L_0x0120:
            r4 = r36
        L_0x0122:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x012a
        L_0x0127:
            r48 = r4
            r4 = 0
        L_0x012a:
            long r49 = r2 & r18
            r46 = 0
            int r11 = (r49 > r46 ? 1 : (r49 == r46 ? 0 : -1))
            if (r11 == 0) goto L_0x0151
            if (r0 == 0) goto L_0x0139
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r11 = r0.pwdLineColor
            r49 = r4
            goto L_0x013d
        L_0x0139:
            r49 = r4
            r11 = r36
        L_0x013d:
            r4 = 4
            r1.updateLiveDataRegistration(r4, r11)
            if (r11 == 0) goto L_0x014a
            java.lang.Object r4 = r11.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x014c
        L_0x014a:
            r4 = r36
        L_0x014c:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x0154
        L_0x0151:
            r49 = r4
            r4 = 0
        L_0x0154:
            r41 = 12320(0x3020, double:6.087E-320)
            long r50 = r2 & r41
            r46 = 0
            int r11 = (r50 > r46 ? 1 : (r50 == r46 ? 0 : -1))
            if (r11 == 0) goto L_0x0176
            if (r0 == 0) goto L_0x0165
            androidx.lifecycle.MutableLiveData<java.lang.String> r11 = r0.pwdErrText
            r50 = r4
            goto L_0x0169
        L_0x0165:
            r50 = r4
            r11 = r36
        L_0x0169:
            r4 = 5
            r1.updateLiveDataRegistration(r4, r11)
            if (r11 == 0) goto L_0x0178
            java.lang.Object r4 = r11.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x017a
        L_0x0176:
            r50 = r4
        L_0x0178:
            r4 = r36
        L_0x017a:
            r39 = 12352(0x3040, double:6.1027E-320)
            long r51 = r2 & r39
            r46 = 0
            int r11 = (r51 > r46 ? 1 : (r51 == r46 ? 0 : -1))
            if (r11 == 0) goto L_0x019c
            if (r0 == 0) goto L_0x018b
            androidx.lifecycle.MutableLiveData<java.lang.String> r11 = r0.name
            r51 = r4
            goto L_0x018f
        L_0x018b:
            r51 = r4
            r11 = r36
        L_0x018f:
            r4 = 6
            r1.updateLiveDataRegistration(r4, r11)
            if (r11 == 0) goto L_0x019e
            java.lang.Object r4 = r11.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x01a0
        L_0x019c:
            r51 = r4
        L_0x019e:
            r4 = r36
        L_0x01a0:
            long r52 = r2 & r16
            r46 = 0
            int r11 = (r52 > r46 ? 1 : (r52 == r46 ? 0 : -1))
            r52 = r4
            if (r11 == 0) goto L_0x01e6
            if (r0 == 0) goto L_0x01b1
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.confirmAble
            r53 = r5
            goto L_0x01b5
        L_0x01b1:
            r53 = r5
            r4 = r36
        L_0x01b5:
            r5 = 7
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x01c2
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01c4
        L_0x01c2:
            r4 = r36
        L_0x01c4:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r11 == 0) goto L_0x01d4
            if (r4 == 0) goto L_0x01d0
            r54 = 32768(0x8000, double:1.61895E-319)
            goto L_0x01d2
        L_0x01d0:
            r54 = 16384(0x4000, double:8.0948E-320)
        L_0x01d2:
            long r2 = r2 | r54
        L_0x01d4:
            android.widget.Button r5 = r1.ibNext
            android.content.Context r5 = r5.getContext()
            if (r4 == 0) goto L_0x01df
            int r11 = com.eternal.device.C1922R.C1924drawable.create_account_gradient
            goto L_0x01e1
        L_0x01df:
            int r11 = com.eternal.device.C1922R.C1924drawable.create_account_disable
        L_0x01e1:
            android.graphics.drawable.Drawable r5 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r5, r11)
            goto L_0x01eb
        L_0x01e6:
            r53 = r5
            r5 = r36
            r4 = 0
        L_0x01eb:
            long r54 = r2 & r32
            r46 = 0
            int r11 = (r54 > r46 ? 1 : (r54 == r46 ? 0 : -1))
            r54 = r4
            if (r11 == 0) goto L_0x0239
            if (r0 == 0) goto L_0x01fc
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.isSavePwd
            r43 = r5
            goto L_0x0200
        L_0x01fc:
            r43 = r5
            r4 = r36
        L_0x0200:
            r5 = 8
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x020e
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0210
        L_0x020e:
            r4 = r36
        L_0x0210:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r11 == 0) goto L_0x0221
            if (r4 == 0) goto L_0x021c
            r55 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x021f
        L_0x021c:
            r55 = 16777216(0x1000000, double:8.289046E-317)
        L_0x021f:
            long r2 = r2 | r55
        L_0x0221:
            if (r4 == 0) goto L_0x022c
            android.widget.Button r4 = r1.btSavePassword
            android.content.Context r4 = r4.getContext()
            int r5 = com.eternal.device.C1922R.C1924drawable.layer_save_password_checked
            goto L_0x0234
        L_0x022c:
            android.widget.Button r4 = r1.btSavePassword
            android.content.Context r4 = r4.getContext()
            int r5 = com.eternal.device.C1922R.C1924drawable.layer_save_password_uncheck
        L_0x0234:
            android.graphics.drawable.Drawable r4 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r4, r5)
            goto L_0x023d
        L_0x0239:
            r43 = r5
            r4 = r36
        L_0x023d:
            r37 = 13312(0x3400, double:6.577E-320)
            long r55 = r2 & r37
            r46 = 0
            int r5 = (r55 > r46 ? 1 : (r55 == r46 ? 0 : -1))
            if (r5 == 0) goto L_0x025b
            if (r0 == 0) goto L_0x0250
            me.tatarka.bindingcollectionadapter2.ItemBinding<com.eternal.device.model.ItemModel> r5 = r0.itemBinding
            androidx.databinding.ObservableList<com.eternal.device.model.ItemModel> r11 = r0.items
            r55 = r4
            goto L_0x0255
        L_0x0250:
            r55 = r4
            r5 = r36
            r11 = r5
        L_0x0255:
            r4 = 10
            r1.updateRegistration((int) r4, (androidx.databinding.ObservableList) r11)
            goto L_0x0260
        L_0x025b:
            r55 = r4
            r5 = r36
            r11 = r5
        L_0x0260:
            r34 = 12800(0x3200, double:6.324E-320)
            long r56 = r2 & r34
            r46 = 0
            int r4 = (r56 > r46 ? 1 : (r56 == r46 ? 0 : -1))
            if (r4 == 0) goto L_0x028a
            if (r0 == 0) goto L_0x0271
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.isLoading
            r56 = r5
            goto L_0x0275
        L_0x0271:
            r56 = r5
            r4 = r36
        L_0x0275:
            r5 = 9
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0283
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0285
        L_0x0283:
            r4 = r36
        L_0x0285:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x028d
        L_0x028a:
            r56 = r5
            r4 = 0
        L_0x028d:
            long r57 = r2 & r20
            r46 = 0
            int r5 = (r57 > r46 ? 1 : (r57 == r46 ? 0 : -1))
            if (r5 == 0) goto L_0x0307
            if (r0 == 0) goto L_0x029c
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.textVisiblePassword
            r57 = r4
            goto L_0x02a0
        L_0x029c:
            r57 = r4
            r0 = r36
        L_0x02a0:
            r4 = 11
            r1.updateLiveDataRegistration(r4, r0)
            if (r0 == 0) goto L_0x02ae
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            goto L_0x02b0
        L_0x02ae:
            r0 = r36
        L_0x02b0:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r0)
            if (r5 == 0) goto L_0x02c8
            if (r0 == 0) goto L_0x02c0
            r4 = 524288(0x80000, double:2.590327E-318)
            long r2 = r2 | r4
            r4 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x02c7
        L_0x02c0:
            r4 = 262144(0x40000, double:1.295163E-318)
            long r2 = r2 | r4
            r4 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x02c7:
            long r2 = r2 | r4
        L_0x02c8:
            if (r0 == 0) goto L_0x02cf
            android.text.method.HideReturnsTransformationMethod r4 = android.text.method.HideReturnsTransformationMethod.getInstance()
            goto L_0x02d3
        L_0x02cf:
            android.text.method.PasswordTransformationMethod r4 = android.text.method.PasswordTransformationMethod.getInstance()
        L_0x02d3:
            if (r0 == 0) goto L_0x02d8
            java.lang.String r0 = "HIDE"
            goto L_0x02da
        L_0x02d8:
            java.lang.String r0 = "SHOW"
        L_0x02da:
            r5 = r49
            r49 = r56
            r56 = r11
            r11 = r9
            r9 = r12
            r12 = r8
            r8 = r14
            r14 = r6
            r6 = r48
            r48 = r57
            r59 = r55
            r55 = r0
            r0 = r15
            r15 = r13
            r13 = r7
            r7 = r4
            r4 = r59
            r60 = r52
            r52 = r43
            r43 = r60
            r61 = r50
            r50 = r45
            r45 = r51
            r51 = r53
            r53 = r54
            r54 = r61
            goto L_0x035a
        L_0x0307:
            r57 = r4
            r0 = r15
            r5 = r49
            r4 = r55
            r49 = r56
            r56 = r11
            r15 = r13
            r55 = r36
            r13 = r7
            r11 = r9
            r9 = r12
            r7 = r55
            r12 = r8
            r8 = r14
            r14 = r6
            r6 = r48
            r48 = r57
            r59 = r52
            r52 = r43
            r43 = r59
            r60 = r50
            r50 = r45
            r45 = r51
            r51 = r53
            r53 = r54
            r54 = r60
            goto L_0x035a
        L_0x0334:
            r0 = r36
            r4 = r0
            r6 = r4
            r7 = r6
            r8 = r7
            r9 = r8
            r10 = r9
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r43 = r15
            r45 = r43
            r49 = r45
            r52 = r49
            r55 = r52
            r56 = r55
            r5 = 0
            r44 = 0
            r48 = 0
            r50 = 0
            r51 = 0
            r53 = 0
            r54 = 0
        L_0x035a:
            long r32 = r2 & r32
            r46 = 0
            int r57 = (r32 > r46 ? 1 : (r32 == r46 ? 0 : -1))
            r32 = r7
            if (r57 == 0) goto L_0x0369
            android.widget.Button r7 = r1.btSavePassword
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.drawableStart(r7, r4)
        L_0x0369:
            long r30 = r2 & r30
            int r4 = (r30 > r46 ? 1 : (r30 == r46 ? 0 : -1))
            if (r4 == 0) goto L_0x039e
            android.widget.Button r4 = r1.btSavePassword
            r7 = 1
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r10, r7)
            android.widget.EditText r4 = r1.etPwd
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r4, r13)
            android.widget.Button r4 = r1.ibNext
            r10 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r15, r10)
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r4 = r1.mboundView1
            com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter.onRefreshCommand(r4, r11)
            android.widget.TextView r4 = r1.mboundView7
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r12, r7)
            com.eternal.widget.guqiang.Toolbar r4 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r4, r9)
            com.eternal.widget.guqiang.Toolbar r4 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onNext(r4, r14)
            android.widget.TextView r4 = r1.tvHelp
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r0, r10)
            android.widget.TextView r0 = r1.tvWifiName
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r8, r10)
        L_0x039e:
            long r7 = r2 & r26
            r9 = 0
            int r0 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x03b0
            android.widget.TextView r0 = r1.createPasswordTitle
            r0.setTextColor(r5)
            android.widget.EditText r0 = r1.etPwd
            r0.setTextColor(r5)
        L_0x03b0:
            long r4 = r2 & r22
            int r0 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x03bb
            android.widget.EditText r0 = r1.etPwd
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x03bb:
            long r4 = r2 & r20
            int r0 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x03d1
            android.widget.EditText r0 = r1.etPwd
            r7 = r32
            android.text.method.TransformationMethod r7 = (android.text.method.TransformationMethod) r7
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.setTransform(r0, r7)
            android.widget.TextView r0 = r1.mboundView7
            r4 = r55
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x03d1:
            r4 = 8192(0x2000, double:4.0474E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x03f4
            android.widget.EditText r0 = r1.etPwd
            r4 = r36
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r4 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r4
            r5 = r36
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r5 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r5
            r6 = r36
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r6 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r6
            androidx.databinding.InverseBindingListener r7 = r1.etPwdandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r0, r4, r5, r6, r7)
            androidx.recyclerview.widget.RecyclerView r0 = r1.mboundView2
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r4 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r0, r4)
        L_0x03f4:
            long r4 = r2 & r16
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x040a
            android.widget.Button r0 = r1.ibNext
            r4 = r53
            r0.setClickable(r4)
            android.widget.Button r0 = r1.ibNext
            r4 = r52
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundDrawable(r0, r4)
        L_0x040a:
            long r4 = r2 & r24
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x041e
            android.widget.Button r0 = r1.ibNext
            r4 = r51
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r0, r4)
            android.widget.ProgressBar r0 = r1.pbLoading
            r4 = r50
            r0.setVisibility(r4)
        L_0x041e:
            r4 = 12800(0x3200, double:6.324E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x042c
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r0 = r1.mboundView1
            r4 = r48
            com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter.setRefreshing(r0, r4)
        L_0x042c:
            r4 = 13312(0x3400, double:6.577E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0448
            androidx.recyclerview.widget.RecyclerView r0 = r1.mboundView2
            r51 = r36
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r51 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r51
            r52 = r36
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r52 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r52
            r53 = r36
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r53 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r53
            r48 = r0
            r50 = r56
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r48, r49, r50, r51, r52, r53)
        L_0x0448:
            long r4 = r2 & r18
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0457
            android.view.View r0 = r1.mboundView8
            r4 = r54
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r4)
        L_0x0457:
            r4 = 12320(0x3020, double:6.087E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0465
            android.widget.TextView r0 = r1.mboundView9
            r4 = r45
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0465:
            long r4 = r2 & r28
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0472
            android.widget.TextView r0 = r1.mboundView9
            r4 = r44
            r0.setVisibility(r4)
        L_0x0472:
            r4 = 12352(0x3040, double:6.1027E-320)
            long r2 = r2 & r4
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0480
            android.widget.TextView r0 = r1.tvWifiName
            r2 = r43
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x0480:
            return
        L_0x0481:
            r0 = move-exception
            monitor-exit(r62)     // Catch:{ all -> 0x0481 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.device.databinding.ActivityWifiConnectBindingImpl.executeBindings():void");
    }
}
