package com.eternal.main.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.main.C2323BR;
import com.eternal.main.C2343R;
import com.eternal.main.model.ItemModel;
import com.eternal.main.model.MainModel;

public class ActivityMainBindingImpl extends ActivityMainBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView10;
    private final RelativeLayout mboundView11;
    private final TextView mboundView13;
    private final RelativeLayout mboundView2;
    private final ImageView mboundView3;
    private final TextView mboundView5;
    private final RelativeLayout mboundView6;
    private final View mboundView7;
    private final RelativeLayout mboundView8;
    private final View mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2343R.C2346id.ct_root, 14);
        sparseIntArray.put(C2343R.C2346id.layout_main, 15);
        sparseIntArray.put(C2343R.C2346id.tv_permission, 16);
        sparseIntArray.put(C2343R.C2346id.tv_title, 17);
        sparseIntArray.put(C2343R.C2346id.ll_layout, 18);
    }

    public ActivityMainBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 19, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityMainBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 8, objArr[14], objArr[4], objArr[0], objArr[15], objArr[18], objArr[1], objArr[12], objArr[16], objArr[17]);
        this.mDirtyFlags = -1;
        this.layoutDevice.setTag((Object) null);
        this.layoutDrawer.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[10];
        this.mboundView10 = relativeLayout;
        relativeLayout.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[11];
        this.mboundView11 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        TextView textView = objArr[13];
        this.mboundView13 = textView;
        textView.setTag((Object) null);
        RelativeLayout relativeLayout3 = objArr[2];
        this.mboundView2 = relativeLayout3;
        relativeLayout3.setTag((Object) null);
        ImageView imageView = objArr[3];
        this.mboundView3 = imageView;
        imageView.setTag((Object) null);
        TextView textView2 = objArr[5];
        this.mboundView5 = textView2;
        textView2.setTag((Object) null);
        RelativeLayout relativeLayout4 = objArr[6];
        this.mboundView6 = relativeLayout4;
        relativeLayout4.setTag((Object) null);
        View view2 = objArr[7];
        this.mboundView7 = view2;
        view2.setTag((Object) null);
        RelativeLayout relativeLayout5 = objArr[8];
        this.mboundView8 = relativeLayout5;
        relativeLayout5.setTag((Object) null);
        View view3 = objArr[9];
        this.mboundView9 = view3;
        view3.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvLogin.setTag((Object) null);
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
        if (C2323BR.model != i) {
            return false;
        }
        setModel((MainModel) obj);
        return true;
    }

    public void setModel(MainModel mainModel) {
        this.mModel = mainModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(C2323BR.model);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeModelIsLogin((MutableLiveData) obj, i2);
            case 1:
                return onChangeModelShowPromptLogin((MutableLiveData) obj, i2);
            case 2:
                return onChangeModelShowShareDot((MutableLiveData) obj, i2);
            case 3:
                return onChangeModelUpdateAvailable((MutableLiveData) obj, i2);
            case 4:
                return onChangeModelTitle((MutableLiveData) obj, i2);
            case 5:
                return onChangeModelOpenDrawer((MutableLiveData) obj, i2);
            case 6:
                return onChangeModelEmailText((MutableLiveData) obj, i2);
            case 7:
                return onChangeModelItems((ObservableList) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeModelIsLogin(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelShowPromptLogin(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModelShowShareDot(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModelUpdateAvailable(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeModelTitle(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeModelOpenDrawer(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeModelEmailText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeModelItems(ObservableList<ItemModel> observableList, int i) {
        if (i != C2323BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x017d  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01c1  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0138  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0171  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r50 = this;
            r1 = r50
            monitor-enter(r50)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0356 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0356 }
            monitor-exit(r50)     // Catch:{ all -> 0x0356 }
            com.eternal.main.model.MainModel r0 = r1.mModel
            r6 = 1023(0x3ff, double:5.054E-321)
            long r6 = r6 & r2
            r16 = 770(0x302, double:3.804E-321)
            r18 = 800(0x320, double:3.953E-321)
            r20 = 769(0x301, double:3.8E-321)
            r22 = 896(0x380, double:4.427E-321)
            r24 = 768(0x300, double:3.794E-321)
            int r28 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r28 == 0) goto L_0x0236
            long r6 = r2 & r24
            int r28 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r28 == 0) goto L_0x0038
            if (r0 == 0) goto L_0x0038
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r6 = r0.onPromptLogin
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onCreateAccount
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onAbout
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r10 = r0.onLogin
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r11 = r0.onUpdate
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onAdd
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onLegal
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r14 = r0.onAccount
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onSupport
            goto L_0x0041
        L_0x0038:
            r6 = 0
            r7 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x0041:
            long r31 = r2 & r20
            r33 = 8
            int r34 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r34 == 0) goto L_0x00be
            if (r0 == 0) goto L_0x004f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.isLogin
            r4 = 0
            goto L_0x0051
        L_0x004f:
            r4 = 0
            r8 = 0
        L_0x0051:
            r1.updateLiveDataRegistration(r4, r8)
            if (r8 == 0) goto L_0x005d
            java.lang.Object r4 = r8.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x005e
        L_0x005d:
            r4 = 0
        L_0x005e:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r34 == 0) goto L_0x0089
            if (r4 == 0) goto L_0x0077
            r37 = 2048(0x800, double:1.0118E-320)
            long r2 = r2 | r37
            r37 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 | r37
            r37 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r37
            r37 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x0087
        L_0x0077:
            r37 = 1024(0x400, double:5.06E-321)
            long r2 = r2 | r37
            r37 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r37
            r37 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r37
            r37 = 4194304(0x400000, double:2.0722615E-317)
        L_0x0087:
            long r2 = r2 | r37
        L_0x0089:
            if (r4 == 0) goto L_0x008d
            r5 = 0
            goto L_0x008f
        L_0x008d:
            r5 = 8
        L_0x008f:
            if (r4 == 0) goto L_0x0093
            r8 = 0
            goto L_0x0094
        L_0x0093:
            r8 = 4
        L_0x0094:
            if (r4 == 0) goto L_0x0099
            r32 = 8
            goto L_0x009b
        L_0x0099:
            r32 = 0
        L_0x009b:
            if (r4 == 0) goto L_0x00ac
            android.widget.TextView r4 = r1.tvLogin
            android.content.res.Resources r4 = r4.getResources()
            r37 = r2
            int r2 = com.eternal.main.C2343R.string.log_out
            java.lang.String r2 = r4.getString(r2)
            goto L_0x00ba
        L_0x00ac:
            r37 = r2
            android.widget.TextView r2 = r1.tvLogin
            android.content.res.Resources r2 = r2.getResources()
            int r3 = com.eternal.main.C2343R.string.log_in
            java.lang.String r2 = r2.getString(r3)
        L_0x00ba:
            r4 = r2
            r2 = r37
            goto L_0x00c3
        L_0x00be:
            r4 = 0
            r5 = 0
            r8 = 0
            r32 = 0
        L_0x00c3:
            long r37 = r2 & r16
            r34 = 0
            int r39 = (r37 > r34 ? 1 : (r37 == r34 ? 0 : -1))
            r34 = r4
            if (r39 == 0) goto L_0x00fb
            if (r0 == 0) goto L_0x00d4
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.showPromptLogin
            r37 = r5
            goto L_0x00d7
        L_0x00d4:
            r37 = r5
            r4 = 0
        L_0x00d7:
            r5 = 1
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x00e4
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00e5
        L_0x00e4:
            r4 = 0
        L_0x00e5:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r39 == 0) goto L_0x00f5
            if (r4 == 0) goto L_0x00f1
            r38 = 32768(0x8000, double:1.61895E-319)
            goto L_0x00f3
        L_0x00f1:
            r38 = 16384(0x4000, double:8.0948E-320)
        L_0x00f3:
            long r2 = r2 | r38
        L_0x00f5:
            if (r4 == 0) goto L_0x00f8
            goto L_0x00fd
        L_0x00f8:
            r4 = 8
            goto L_0x00fe
        L_0x00fb:
            r37 = r5
        L_0x00fd:
            r4 = 0
        L_0x00fe:
            r29 = 772(0x304, double:3.814E-321)
            long r38 = r2 & r29
            r35 = 0
            int r5 = (r38 > r35 ? 1 : (r38 == r35 ? 0 : -1))
            r38 = r4
            if (r5 == 0) goto L_0x0138
            if (r0 == 0) goto L_0x0111
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.showShareDot
            r39 = r6
            goto L_0x0114
        L_0x0111:
            r39 = r6
            r4 = 0
        L_0x0114:
            r6 = 2
            r1.updateLiveDataRegistration(r6, r4)
            if (r4 == 0) goto L_0x0121
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x0122
        L_0x0121:
            r4 = 0
        L_0x0122:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r5 == 0) goto L_0x0132
            if (r4 == 0) goto L_0x012e
            r5 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x0131
        L_0x012e:
            r5 = 1048576(0x100000, double:5.180654E-318)
        L_0x0131:
            long r2 = r2 | r5
        L_0x0132:
            if (r4 == 0) goto L_0x0135
            goto L_0x013a
        L_0x0135:
            r4 = 8
            goto L_0x013b
        L_0x0138:
            r39 = r6
        L_0x013a:
            r4 = 0
        L_0x013b:
            r5 = 776(0x308, double:3.834E-321)
            long r40 = r2 & r5
            r5 = 0
            int r42 = (r40 > r5 ? 1 : (r40 == r5 ? 0 : -1))
            if (r42 == 0) goto L_0x0171
            if (r0 == 0) goto L_0x014a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r0.updateAvailable
            goto L_0x014b
        L_0x014a:
            r5 = 0
        L_0x014b:
            r6 = 3
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x0158
            java.lang.Object r5 = r5.getValue()
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto L_0x0159
        L_0x0158:
            r5 = 0
        L_0x0159:
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            if (r42 == 0) goto L_0x016a
            if (r5 == 0) goto L_0x0165
            r40 = 524288(0x80000, double:2.590327E-318)
            goto L_0x0168
        L_0x0165:
            r40 = 262144(0x40000, double:1.295163E-318)
        L_0x0168:
            long r2 = r2 | r40
        L_0x016a:
            if (r5 == 0) goto L_0x016e
            r33 = 0
        L_0x016e:
            r5 = 784(0x310, double:3.873E-321)
            goto L_0x0175
        L_0x0171:
            r5 = 784(0x310, double:3.873E-321)
            r33 = 0
        L_0x0175:
            long r40 = r2 & r5
            r5 = 0
            int r42 = (r40 > r5 ? 1 : (r40 == r5 ? 0 : -1))
            if (r42 == 0) goto L_0x0190
            if (r0 == 0) goto L_0x0182
            androidx.lifecycle.MutableLiveData<java.lang.String> r5 = r0.title
            goto L_0x0183
        L_0x0182:
            r5 = 0
        L_0x0183:
            r6 = 4
            r1.updateLiveDataRegistration(r6, r5)
            if (r5 == 0) goto L_0x0190
            java.lang.Object r5 = r5.getValue()
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x0191
        L_0x0190:
            r5 = 0
        L_0x0191:
            long r40 = r2 & r18
            r35 = 0
            int r6 = (r40 > r35 ? 1 : (r40 == r35 ? 0 : -1))
            if (r6 == 0) goto L_0x01b6
            if (r0 == 0) goto L_0x01a0
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r6 = r0.openDrawer
            r31 = r4
            goto L_0x01a3
        L_0x01a0:
            r31 = r4
            r6 = 0
        L_0x01a3:
            r4 = 5
            r1.updateLiveDataRegistration(r4, r6)
            if (r6 == 0) goto L_0x01b0
            java.lang.Object r4 = r6.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x01b1
        L_0x01b0:
            r4 = 0
        L_0x01b1:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x01b9
        L_0x01b6:
            r31 = r4
            r4 = 0
        L_0x01b9:
            long r40 = r2 & r22
            r35 = 0
            int r6 = (r40 > r35 ? 1 : (r40 == r35 ? 0 : -1))
            if (r6 == 0) goto L_0x01d7
            if (r0 == 0) goto L_0x01cc
            me.tatarka.bindingcollectionadapter2.ItemBinding<com.eternal.main.model.ItemModel> r6 = r0.itemBinding
            r40 = r4
            androidx.databinding.ObservableList<com.eternal.main.model.ItemModel> r4 = r0.items
            r41 = r5
            goto L_0x01d2
        L_0x01cc:
            r40 = r4
            r41 = r5
            r4 = 0
            r6 = 0
        L_0x01d2:
            r5 = 7
            r1.updateRegistration((int) r5, (androidx.databinding.ObservableList) r4)
            goto L_0x01dd
        L_0x01d7:
            r40 = r4
            r41 = r5
            r4 = 0
            r6 = 0
        L_0x01dd:
            r26 = 832(0x340, double:4.11E-321)
            long r42 = r2 & r26
            r35 = 0
            int r5 = (r42 > r35 ? 1 : (r42 == r35 ? 0 : -1))
            if (r5 == 0) goto L_0x020d
            if (r0 == 0) goto L_0x01ec
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.emailText
            goto L_0x01ed
        L_0x01ec:
            r0 = 0
        L_0x01ed:
            r5 = 6
            r1.updateLiveDataRegistration(r5, r0)
            if (r0 == 0) goto L_0x020d
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r5 = r6
            r6 = r4
            r4 = r39
            r39 = r10
            r10 = r40
            r40 = r12
            r12 = r15
            r15 = r9
            r9 = r31
            r31 = r0
            r0 = r7
            r7 = r41
            goto L_0x0220
        L_0x020d:
            r5 = r6
            r0 = r7
            r7 = r41
            r6 = r4
            r4 = r39
            r39 = r10
            r10 = r40
            r40 = r12
            r12 = r15
            r15 = r9
            r9 = r31
            r31 = 0
        L_0x0220:
            r47 = r37
            r37 = r8
            r8 = r33
            r33 = r34
            r34 = r47
            r48 = r13
            r13 = r11
            r11 = r48
            r49 = r38
            r38 = r32
            r32 = r49
            goto L_0x0253
        L_0x0236:
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
            r31 = 0
            r32 = 0
            r33 = 0
            r34 = 0
            r37 = 0
            r38 = 0
            r39 = 0
            r40 = 0
        L_0x0253:
            r41 = 512(0x200, double:2.53E-321)
            long r41 = r2 & r41
            r35 = 0
            int r43 = (r41 > r35 ? 1 : (r41 == r35 ? 0 : -1))
            r41 = r4
            if (r43 == 0) goto L_0x026b
            com.eternal.base.SlideRecyclerView r4 = r1.layoutDevice
            r42 = r7
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r7 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r4, r7)
            goto L_0x026d
        L_0x026b:
            r42 = r7
        L_0x026d:
            long r22 = r2 & r22
            int r4 = (r22 > r35 ? 1 : (r22 == r35 ? 0 : -1))
            if (r4 == 0) goto L_0x0297
            com.eternal.base.SlideRecyclerView r4 = r1.layoutDevice
            r7 = 0
            r22 = r7
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r22 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r22
            r23 = r7
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r23 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r23
            r28 = r7
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r28 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r28
            r7 = r41
            r41 = r15
            r44 = r42
            r15 = r7
            r7 = r22
            r45 = r8
            r8 = r23
            r46 = r9
            r9 = r28
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r4, r5, r6, r7, r8, r9)
            goto L_0x02a3
        L_0x0297:
            r45 = r8
            r46 = r9
            r44 = r42
            r47 = r41
            r41 = r15
            r15 = r47
        L_0x02a3:
            long r4 = r2 & r18
            r6 = 0
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x02b0
            androidx.drawerlayout.widget.DrawerLayout r4 = r1.layoutDrawer
            com.eternal.framework.binding.viewadapter.drawerlayout.ViewAdapter.openDrawer(r4, r10)
        L_0x02b0:
            long r4 = r2 & r24
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 == 0) goto L_0x02ea
            android.widget.RelativeLayout r4 = r1.mboundView10
            r5 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r12, r5)
            android.widget.RelativeLayout r4 = r1.mboundView11
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r11, r5)
            android.widget.TextView r4 = r1.mboundView13
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r4, r0, r5)
            android.widget.ImageView r0 = r1.mboundView3
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r15, r5)
            android.widget.RelativeLayout r0 = r1.mboundView6
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r14, r5)
            android.widget.RelativeLayout r0 = r1.mboundView8
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r13, r5)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            r9 = r41
            com.eternal.widget.guqiang.ToolbarAdapter.onBack(r0, r9)
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            r12 = r40
            com.eternal.widget.guqiang.ToolbarAdapter.onNext(r0, r12)
            android.widget.TextView r0 = r1.tvLogin
            r10 = r39
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r10, r5)
        L_0x02ea:
            long r4 = r2 & r20
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x030e
            android.widget.TextView r0 = r1.mboundView13
            r4 = r38
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.mboundView5
            r8 = r37
            r0.setVisibility(r8)
            android.widget.RelativeLayout r0 = r1.mboundView6
            r4 = r34
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.tvLogin
            r4 = r33
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x030e:
            long r4 = r2 & r16
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x031d
            android.widget.RelativeLayout r0 = r1.mboundView2
            r4 = r32
            r0.setVisibility(r4)
        L_0x031d:
            r4 = 832(0x340, double:4.11E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x032b
            android.widget.TextView r0 = r1.mboundView5
            r4 = r31
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x032b:
            r4 = 772(0x304, double:3.814E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0339
            android.view.View r0 = r1.mboundView7
            r4 = r46
            r0.setVisibility(r4)
        L_0x0339:
            r4 = 776(0x308, double:3.834E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0347
            android.view.View r0 = r1.mboundView9
            r4 = r45
            r0.setVisibility(r4)
        L_0x0347:
            r4 = 784(0x310, double:3.873E-321)
            long r2 = r2 & r4
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0355
            com.eternal.widget.guqiang.Toolbar r0 = r1.toolBar
            r2 = r44
            com.eternal.widget.guqiang.ToolbarAdapter.setTitle(r0, r2)
        L_0x0355:
            return
        L_0x0356:
            r0 = move-exception
            monitor-exit(r50)     // Catch:{ all -> 0x0356 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.main.databinding.ActivityMainBindingImpl.executeBindings():void");
    }
}
