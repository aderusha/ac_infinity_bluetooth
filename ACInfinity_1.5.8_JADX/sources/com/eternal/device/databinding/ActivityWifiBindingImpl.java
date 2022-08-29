package com.eternal.device.databinding;

import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.ItemModel;
import com.eternal.device.model.WiFiModel;

public class ActivityWifiBindingImpl extends ActivityWifiBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final SwipeRefreshLayout mboundView1;
    private final RecyclerView mboundView2;
    private final ImageView mboundView5;
    private final ImageView mboundView7;
    private final ImageView mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.ll_sheet_dialog, 12);
        sparseIntArray.put(C1922R.C1925id.ll_content, 13);
    }

    public ActivityWifiBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityWifiBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 11, objArr[11], objArr[6], objArr[13], objArr[12], objArr[0], objArr[4], objArr[3], objArr[10], objArr[8]);
        this.mDirtyFlags = -1;
        this.ibNext.setTag((Object) null);
        this.ivOval.setTag((Object) null);
        SwipeRefreshLayout swipeRefreshLayout = objArr[1];
        this.mboundView1 = swipeRefreshLayout;
        swipeRefreshLayout.setTag((Object) null);
        RecyclerView recyclerView = objArr[2];
        this.mboundView2 = recyclerView;
        recyclerView.setTag((Object) null);
        ImageView imageView = objArr[5];
        this.mboundView5 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = objArr[7];
        this.mboundView7 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = objArr[9];
        this.mboundView9 = imageView3;
        imageView3.setTag((Object) null);
        this.root.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvCancel.setTag((Object) null);
        this.tvContent.setTag((Object) null);
        this.tvTitle.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
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
        if (C1909BR.wifiModel != i) {
            return false;
        }
        setWifiModel((WiFiModel) obj);
        return true;
    }

    public void setWifiModel(WiFiModel wiFiModel) {
        this.mWifiModel = wiFiModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(C1909BR.wifiModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeWifiModelTitleText((MutableLiveData) obj, i2);
            case 1:
                return onChangeWifiModelIsLoading((MutableLiveData) obj, i2);
            case 2:
                return onChangeWifiModelConfirmAble((MutableLiveData) obj, i2);
            case 3:
                return onChangeWifiModelNextText((MutableLiveData) obj, i2);
            case 4:
                return onChangeWifiModelStateVisible((MutableLiveData) obj, i2);
            case 5:
                return onChangeWifiModelContentText((MutableLiveData) obj, i2);
            case 6:
                return onChangeWifiModelOvalVisible((MutableLiveData) obj, i2);
            case 7:
                return onChangeWifiModelNextVisible((MutableLiveData) obj, i2);
            case 8:
                return onChangeWifiModelItems((ObservableList) obj, i2);
            case 9:
                return onChangeWifiModelConnectIconVisible((MutableLiveData) obj, i2);
            case 10:
                return onChangeWifiModelState((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeWifiModelTitleText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeWifiModelIsLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeWifiModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeWifiModelNextText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeWifiModelStateVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeWifiModelContentText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeWifiModelOvalVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeWifiModelNextVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeWifiModelItems(ObservableList<ItemModel> observableList, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeWifiModelConnectIconVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeWifiModelState(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x017c  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x01b2  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x01e5  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x01f3  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00c6  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0136  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0143  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r52 = this;
            r1 = r52
            monitor-enter(r52)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0312 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0312 }
            monitor-exit(r52)     // Catch:{ all -> 0x0312 }
            com.eternal.device.model.WiFiModel r0 = r1.mWifiModel
            r6 = 8191(0x1fff, double:4.047E-320)
            long r6 = r6 & r2
            r16 = 6160(0x1810, double:3.0434E-320)
            r18 = 6148(0x1804, double:3.0375E-320)
            r20 = 6208(0x1840, double:3.067E-320)
            r22 = 6146(0x1802, double:3.0365E-320)
            r24 = 6144(0x1800, double:3.0355E-320)
            r26 = 6272(0x1880, double:3.099E-320)
            r28 = 6145(0x1801, double:3.036E-320)
            r30 = 6152(0x1808, double:3.0395E-320)
            r8 = 0
            int r32 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r32 == 0) goto L_0x022f
            long r6 = r2 & r28
            int r32 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r32 == 0) goto L_0x003c
            if (r0 == 0) goto L_0x002f
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.titleText
            goto L_0x0030
        L_0x002f:
            r6 = 0
        L_0x0030:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x003c
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x003d
        L_0x003c:
            r6 = 0
        L_0x003d:
            long r32 = r2 & r24
            int r7 = (r32 > r4 ? 1 : (r32 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0050
            if (r0 == 0) goto L_0x0050
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onNext
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onDismiss
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r8 = r0.onCancel
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onRefresh
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onBack
            goto L_0x0055
        L_0x0050:
            r7 = 0
            r8 = 0
            r9 = 0
            r12 = 0
            r13 = 0
        L_0x0055:
            long r36 = r2 & r22
            int r38 = (r36 > r4 ? 1 : (r36 == r4 ? 0 : -1))
            if (r38 == 0) goto L_0x0074
            if (r0 == 0) goto L_0x0060
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.isLoading
            goto L_0x0061
        L_0x0060:
            r10 = 0
        L_0x0061:
            r11 = 1
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x006e
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x006f
        L_0x006e:
            r10 = 0
        L_0x006f:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            goto L_0x0075
        L_0x0074:
            r10 = 0
        L_0x0075:
            long r38 = r2 & r18
            int r40 = (r38 > r4 ? 1 : (r38 == r4 ? 0 : -1))
            if (r40 == 0) goto L_0x00a5
            if (r0 == 0) goto L_0x0080
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r11 = r0.confirmAble
            goto L_0x0081
        L_0x0080:
            r11 = 0
        L_0x0081:
            r14 = 2
            r1.updateLiveDataRegistration(r14, r11)
            if (r11 == 0) goto L_0x008e
            java.lang.Object r11 = r11.getValue()
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            goto L_0x008f
        L_0x008e:
            r11 = 0
        L_0x008f:
            boolean r11 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r11)
            if (r40 == 0) goto L_0x009f
            if (r11 == 0) goto L_0x009b
            r14 = 262144(0x40000, double:1.295163E-318)
            goto L_0x009e
        L_0x009b:
            r14 = 131072(0x20000, double:6.47582E-319)
        L_0x009e:
            long r2 = r2 | r14
        L_0x009f:
            if (r11 == 0) goto L_0x00a2
            goto L_0x00a5
        L_0x00a2:
            r11 = 8
            goto L_0x00a6
        L_0x00a5:
            r11 = 0
        L_0x00a6:
            long r14 = r2 & r30
            int r39 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r39 == 0) goto L_0x00bf
            if (r0 == 0) goto L_0x00b1
            androidx.lifecycle.MutableLiveData<java.lang.String> r14 = r0.nextText
            goto L_0x00b2
        L_0x00b1:
            r14 = 0
        L_0x00b2:
            r15 = 3
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x00bf
            java.lang.Object r14 = r14.getValue()
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x00c0
        L_0x00bf:
            r14 = 0
        L_0x00c0:
            long r39 = r2 & r16
            int r15 = (r39 > r4 ? 1 : (r39 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x00f1
            if (r0 == 0) goto L_0x00cb
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.stateVisible
            goto L_0x00cc
        L_0x00cb:
            r4 = 0
        L_0x00cc:
            r5 = 4
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x00d9
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00da
        L_0x00d9:
            r4 = 0
        L_0x00da:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r15 == 0) goto L_0x00eb
            if (r4 == 0) goto L_0x00e6
            r43 = 4194304(0x400000, double:2.0722615E-317)
            goto L_0x00e9
        L_0x00e6:
            r43 = 2097152(0x200000, double:1.0361308E-317)
        L_0x00e9:
            long r2 = r2 | r43
        L_0x00eb:
            if (r4 == 0) goto L_0x00ee
            goto L_0x00f1
        L_0x00ee:
            r4 = 8
            goto L_0x00f2
        L_0x00f1:
            r4 = 0
        L_0x00f2:
            r41 = 6400(0x1900, double:3.162E-320)
            long r43 = r2 & r41
            r39 = 0
            int r5 = (r43 > r39 ? 1 : (r43 == r39 ? 0 : -1))
            if (r5 == 0) goto L_0x0111
            if (r0 == 0) goto L_0x0107
            me.tatarka.bindingcollectionadapter2.ItemBinding<com.eternal.device.model.ItemModel> r5 = r0.itemBinding
            androidx.databinding.ObservableList<com.eternal.device.model.ItemModel> r15 = r0.items
            r38 = r4
            r4 = 8
            goto L_0x010d
        L_0x0107:
            r38 = r4
            r4 = 8
            r5 = 0
            r15 = 0
        L_0x010d:
            r1.updateRegistration((int) r4, (androidx.databinding.ObservableList) r15)
            goto L_0x0117
        L_0x0111:
            r38 = r4
            r4 = 8
            r5 = 0
            r15 = 0
        L_0x0117:
            r36 = 6176(0x1820, double:3.0513E-320)
            long r43 = r2 & r36
            int r45 = (r43 > r39 ? 1 : (r43 == r39 ? 0 : -1))
            if (r45 == 0) goto L_0x0136
            if (r0 == 0) goto L_0x0126
            androidx.lifecycle.MutableLiveData<java.lang.String> r4 = r0.contentText
            r44 = r5
            goto L_0x0129
        L_0x0126:
            r44 = r5
            r4 = 0
        L_0x0129:
            r5 = 5
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x0138
            java.lang.Object r4 = r4.getValue()
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x0139
        L_0x0136:
            r44 = r5
        L_0x0138:
            r4 = 0
        L_0x0139:
            long r45 = r2 & r20
            r39 = 0
            int r5 = (r45 > r39 ? 1 : (r45 == r39 ? 0 : -1))
            r45 = r4
            if (r5 == 0) goto L_0x0171
            if (r0 == 0) goto L_0x014a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.ovalVisible
            r46 = r6
            goto L_0x014d
        L_0x014a:
            r46 = r6
            r4 = 0
        L_0x014d:
            r6 = 6
            r1.updateLiveDataRegistration(r6, r4)
            if (r4 == 0) goto L_0x015a
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x015b
        L_0x015a:
            r4 = 0
        L_0x015b:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x016b
            if (r4 == 0) goto L_0x0167
            r5 = 1048576(0x100000, double:5.180654E-318)
            goto L_0x016a
        L_0x0167:
            r5 = 524288(0x80000, double:2.590327E-318)
        L_0x016a:
            long r2 = r2 | r5
        L_0x016b:
            if (r4 == 0) goto L_0x016e
            goto L_0x0173
        L_0x016e:
            r4 = 8
            goto L_0x0174
        L_0x0171:
            r46 = r6
        L_0x0173:
            r4 = 0
        L_0x0174:
            long r5 = r2 & r26
            r39 = 0
            int r47 = (r5 > r39 ? 1 : (r5 == r39 ? 0 : -1))
            if (r47 == 0) goto L_0x01a5
            if (r0 == 0) goto L_0x0181
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.nextVisible
            goto L_0x0182
        L_0x0181:
            r5 = 0
        L_0x0182:
            r6 = 7
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x018f
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x0190
        L_0x018f:
            r5 = 0
        L_0x0190:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            if (r47 == 0) goto L_0x019f
            if (r5 == 0) goto L_0x019b
            r47 = 16384(0x4000, double:8.0948E-320)
            goto L_0x019d
        L_0x019b:
            r47 = 8192(0x2000, double:4.0474E-320)
        L_0x019d:
            long r2 = r2 | r47
        L_0x019f:
            if (r5 == 0) goto L_0x01a2
            goto L_0x01a5
        L_0x01a2:
            r5 = 8
            goto L_0x01a6
        L_0x01a5:
            r5 = 0
        L_0x01a6:
            r34 = 6656(0x1a00, double:3.2885E-320)
            long r47 = r2 & r34
            r39 = 0
            int r6 = (r47 > r39 ? 1 : (r47 == r39 ? 0 : -1))
            r47 = r4
            if (r6 == 0) goto L_0x01e5
            if (r0 == 0) goto L_0x01b9
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.connectIconVisible
            r48 = r5
            goto L_0x01bc
        L_0x01b9:
            r48 = r5
            r4 = 0
        L_0x01bc:
            r5 = 9
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x01ca
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01cb
        L_0x01ca:
            r4 = 0
        L_0x01cb:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r6 == 0) goto L_0x01db
            if (r4 == 0) goto L_0x01d7
            r5 = 65536(0x10000, double:3.2379E-319)
            goto L_0x01da
        L_0x01d7:
            r5 = 32768(0x8000, double:1.61895E-319)
        L_0x01da:
            long r2 = r2 | r5
        L_0x01db:
            if (r4 == 0) goto L_0x01e0
            r43 = 0
            goto L_0x01e2
        L_0x01e0:
            r43 = 8
        L_0x01e2:
            r4 = 7168(0x1c00, double:3.5415E-320)
            goto L_0x01eb
        L_0x01e5:
            r48 = r5
            r4 = 7168(0x1c00, double:3.5415E-320)
            r43 = 0
        L_0x01eb:
            long r49 = r2 & r4
            r4 = 0
            int r6 = (r49 > r4 ? 1 : (r49 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x0214
            if (r0 == 0) goto L_0x01f8
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r0.state
            goto L_0x01f9
        L_0x01f8:
            r0 = 0
        L_0x01f9:
            r4 = 10
            r1.updateLiveDataRegistration(r4, r0)
            if (r0 == 0) goto L_0x0207
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            goto L_0x0208
        L_0x0207:
            r0 = 0
        L_0x0208:
            int r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r0)
            r6 = r14
            r5 = r43
            r43 = r46
            r4 = r47
            goto L_0x021c
        L_0x0214:
            r6 = r14
            r5 = r43
            r43 = r46
            r4 = r47
            r0 = 0
        L_0x021c:
            r14 = r13
            r13 = r12
            r12 = r10
            r10 = r8
            r8 = r7
            r7 = r48
            r51 = r11
            r11 = r9
            r9 = r44
            r44 = r45
            r45 = r38
            r38 = r51
            goto L_0x0244
        L_0x022f:
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
            r43 = 0
            r44 = 0
            r45 = 0
        L_0x0244:
            long r30 = r2 & r30
            r39 = 0
            int r46 = (r30 > r39 ? 1 : (r30 == r39 ? 0 : -1))
            r30 = r0
            if (r46 == 0) goto L_0x0253
            android.widget.Button r0 = r1.ibNext
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x0253:
            long r26 = r2 & r26
            int r0 = (r26 > r39 ? 1 : (r26 == r39 ? 0 : -1))
            if (r0 == 0) goto L_0x025e
            android.widget.Button r0 = r1.ibNext
            r0.setVisibility(r7)
        L_0x025e:
            long r6 = r2 & r24
            int r0 = (r6 > r39 ? 1 : (r6 == r39 ? 0 : -1))
            if (r0 == 0) goto L_0x027e
            android.widget.Button r0 = r1.ibNext
            r6 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r8, r6)
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r0 = r1.mboundView1
            com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter.onRefreshCommand(r0, r13)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r0, r14)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ToolbarAdapter.onNext(r0, r10)
            android.widget.TextView r0 = r1.tvCancel
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r11, r6)
        L_0x027e:
            long r6 = r2 & r20
            r10 = 0
            int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x028b
            android.widget.ImageView r0 = r1.ivOval
            r0.setVisibility(r4)
        L_0x028b:
            long r6 = r2 & r22
            int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x0296
            androidx.swiperefreshlayout.widget.SwipeRefreshLayout r0 = r1.mboundView1
            com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter.setRefreshing(r0, r12)
        L_0x0296:
            r6 = 4096(0x1000, double:2.0237E-320)
            long r6 = r6 & r2
            int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x02a6
            androidx.recyclerview.widget.RecyclerView r0 = r1.mboundView2
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r4 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r0, r4)
        L_0x02a6:
            r6 = 6400(0x1900, double:3.162E-320)
            long r6 = r6 & r2
            int r0 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x02c0
            androidx.recyclerview.widget.RecyclerView r8 = r1.mboundView2
            r0 = 0
            r11 = r0
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r11 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r11
            r12 = r0
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r12 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r12
            r13 = r0
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r13 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r13
            r10 = r15
            r0 = r38
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r8, r9, r10, r11, r12, r13)
            goto L_0x02c2
        L_0x02c0:
            r0 = r38
        L_0x02c2:
            r6 = 6656(0x1a00, double:3.2885E-320)
            long r6 = r6 & r2
            r8 = 0
            int r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r4 == 0) goto L_0x02d0
            android.widget.ImageView r4 = r1.mboundView5
            r4.setVisibility(r5)
        L_0x02d0:
            r4 = 7168(0x1c00, double:3.5415E-320)
            long r4 = r4 & r2
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x02de
            android.widget.ImageView r4 = r1.mboundView7
            r5 = r30
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r4, r5)
        L_0x02de:
            long r4 = r2 & r16
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x02eb
            android.widget.ImageView r4 = r1.mboundView7
            r5 = r45
            r4.setVisibility(r5)
        L_0x02eb:
            long r4 = r2 & r18
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x02f6
            android.widget.ImageView r4 = r1.mboundView9
            r4.setVisibility(r0)
        L_0x02f6:
            r4 = 6176(0x1820, double:3.0513E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0304
            android.widget.TextView r0 = r1.tvContent
            r4 = r44
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0304:
            long r2 = r2 & r28
            int r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1))
            if (r0 == 0) goto L_0x0311
            android.widget.TextView r0 = r1.tvTitle
            r2 = r43
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x0311:
            return
        L_0x0312:
            r0 = move-exception
            monitor-exit(r52)     // Catch:{ all -> 0x0312 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.device.databinding.ActivityWifiBindingImpl.executeBindings():void");
    }
}
