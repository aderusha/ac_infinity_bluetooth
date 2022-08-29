package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.InviteItemModel;
import com.eternal.account.model.InviteModel;
import com.eternal.base.SlideRecyclerView;

public class ActivityInviteBindingImpl extends ActivityInviteBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private InverseBindingListener etEmailandroidTextAttrChanged;
    private long mDirtyFlags;
    private final View mboundView3;
    private final TextView mboundView4;
    private final SlideRecyclerView mboundView5;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.create_email, 8);
        sparseIntArray.put(C0997R.C1000id.create_email_title, 9);
    }

    public ActivityInviteBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    private ActivityInviteBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 8, objArr[8], objArr[9], objArr[2], objArr[6], objArr[7], objArr[0], objArr[1]);
        this.etEmailandroidTextAttrChanged = new InverseBindingListener() {
            public void onChange() {
                String textString = TextViewBindingAdapter.getTextString(ActivityInviteBindingImpl.this.etEmail);
                InviteModel inviteModel = ActivityInviteBindingImpl.this.mInviteModel;
                boolean z = true;
                if (inviteModel != null) {
                    MutableLiveData<String> mutableLiveData = inviteModel.emailText;
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
        this.etEmail.setTag((Object) null);
        this.ibNext.setTag((Object) null);
        View view2 = objArr[3];
        this.mboundView3 = view2;
        view2.setTag((Object) null);
        TextView textView = objArr[4];
        this.mboundView4 = textView;
        textView.setTag((Object) null);
        SlideRecyclerView slideRecyclerView = objArr[5];
        this.mboundView5 = slideRecyclerView;
        slideRecyclerView.setTag((Object) null);
        this.pbLoading.setTag((Object) null);
        this.root.setTag((Object) null);
        this.toolBar.setTag((Object) null);
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
        if (C0977BR.inviteModel != i) {
            return false;
        }
        setInviteModel((InviteModel) obj);
        return true;
    }

    public void setInviteModel(InviteModel inviteModel) {
        this.mInviteModel = inviteModel;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(C0977BR.inviteModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeInviteModelEmailText((MutableLiveData) obj, i2);
            case 1:
                return onChangeInviteModelEmailLineColor((MutableLiveData) obj, i2);
            case 2:
                return onChangeInviteModelEmailErrVisible((MutableLiveData) obj, i2);
            case 3:
                return onChangeInviteModelEmailErrText((MutableLiveData) obj, i2);
            case 4:
                return onChangeInviteModelEmailColor((MutableLiveData) obj, i2);
            case 5:
                return onChangeInviteModelConfirmAble((MutableLiveData) obj, i2);
            case 6:
                return onChangeInviteModelShowLoading((MutableLiveData) obj, i2);
            case 7:
                return onChangeInviteModelItems((ObservableList) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeInviteModelEmailText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeInviteModelEmailLineColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeInviteModelEmailErrVisible(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeInviteModelEmailErrText(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeInviteModelEmailColor(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeInviteModelConfirmAble(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeInviteModelShowLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeInviteModelItems(ObservableList<InviteItemModel> observableList, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x01aa  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01cb  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01fa  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x020b  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x021b  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0227  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x0232  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x023e  */
    /* JADX WARNING: Removed duplicated region for block: B:161:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0091 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00df  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x011f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r47 = this;
            r1 = r47
            monitor-enter(r47)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0253 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0253 }
            monitor-exit(r47)     // Catch:{ all -> 0x0253 }
            com.eternal.account.model.InviteModel r0 = r1.mInviteModel
            r6 = 1023(0x3ff, double:5.054E-321)
            long r6 = r6 & r2
            r12 = 776(0x308, double:3.834E-321)
            r14 = 800(0x320, double:3.953E-321)
            r16 = 772(0x304, double:3.814E-321)
            r18 = 784(0x310, double:3.873E-321)
            r20 = 770(0x302, double:3.804E-321)
            r22 = 769(0x301, double:3.8E-321)
            r24 = 768(0x300, double:3.794E-321)
            r8 = 0
            int r28 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r28 == 0) goto L_0x0192
            long r6 = r2 & r22
            int r28 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r28 == 0) goto L_0x003a
            if (r0 == 0) goto L_0x002d
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.emailText
            goto L_0x002e
        L_0x002d:
            r6 = 0
        L_0x002e:
            r1.updateLiveDataRegistration(r8, r6)
            if (r6 == 0) goto L_0x003a
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x003b
        L_0x003a:
            r6 = 0
        L_0x003b:
            long r28 = r2 & r20
            int r7 = (r28 > r4 ? 1 : (r28 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x005a
            if (r0 == 0) goto L_0x0046
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r7 = r0.emailLineColor
            goto L_0x0047
        L_0x0046:
            r7 = 0
        L_0x0047:
            r9 = 1
            r1.updateLiveDataRegistration(r9, r7)
            if (r7 == 0) goto L_0x0054
            java.lang.Object r7 = r7.getValue()
            java.lang.Integer r7 = (java.lang.Integer) r7
            goto L_0x0055
        L_0x0054:
            r7 = 0
        L_0x0055:
            int r7 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r7)
            goto L_0x005b
        L_0x005a:
            r7 = 0
        L_0x005b:
            long r29 = r2 & r16
            int r31 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r31 == 0) goto L_0x008a
            if (r0 == 0) goto L_0x0066
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r0.emailErrVisible
            goto L_0x0067
        L_0x0066:
            r9 = 0
        L_0x0067:
            r8 = 2
            r1.updateLiveDataRegistration(r8, r9)
            if (r9 == 0) goto L_0x0074
            java.lang.Object r8 = r9.getValue()
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            goto L_0x0075
        L_0x0074:
            r8 = 0
        L_0x0075:
            boolean r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r8)
            if (r31 == 0) goto L_0x0084
            if (r8 == 0) goto L_0x0080
            r31 = 8192(0x2000, double:4.0474E-320)
            goto L_0x0082
        L_0x0080:
            r31 = 4096(0x1000, double:2.0237E-320)
        L_0x0082:
            long r2 = r2 | r31
        L_0x0084:
            if (r8 == 0) goto L_0x0087
            goto L_0x008a
        L_0x0087:
            r8 = 8
            goto L_0x008b
        L_0x008a:
            r8 = 0
        L_0x008b:
            long r31 = r2 & r24
            int r9 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x009a
            if (r0 == 0) goto L_0x009a
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.String> r10 = r0.emailTextChanged
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r11 = r0.onNext
            goto L_0x009d
        L_0x009a:
            r9 = 0
            r10 = 0
            r11 = 0
        L_0x009d:
            long r33 = r2 & r12
            int r35 = (r33 > r4 ? 1 : (r33 == r4 ? 0 : -1))
            if (r35 == 0) goto L_0x00b6
            if (r0 == 0) goto L_0x00a8
            androidx.lifecycle.MutableLiveData<java.lang.String> r12 = r0.emailErrText
            goto L_0x00a9
        L_0x00a8:
            r12 = 0
        L_0x00a9:
            r13 = 3
            r1.updateLiveDataRegistration(r13, r12)
            if (r12 == 0) goto L_0x00b6
            java.lang.Object r12 = r12.getValue()
            java.lang.String r12 = (java.lang.String) r12
            goto L_0x00b7
        L_0x00b6:
            r12 = 0
        L_0x00b7:
            long r35 = r2 & r18
            int r13 = (r35 > r4 ? 1 : (r35 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00d6
            if (r0 == 0) goto L_0x00c2
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r13 = r0.emailColor
            goto L_0x00c3
        L_0x00c2:
            r13 = 0
        L_0x00c3:
            r4 = 4
            r1.updateLiveDataRegistration(r4, r13)
            if (r13 == 0) goto L_0x00d0
            java.lang.Object r4 = r13.getValue()
            java.lang.Integer r4 = (java.lang.Integer) r4
            goto L_0x00d1
        L_0x00d0:
            r4 = 0
        L_0x00d1:
            int r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r4)
            goto L_0x00d7
        L_0x00d6:
            r4 = 0
        L_0x00d7:
            long r37 = r2 & r14
            r35 = 0
            int r5 = (r37 > r35 ? 1 : (r37 == r35 ? 0 : -1))
            if (r5 == 0) goto L_0x0113
            if (r0 == 0) goto L_0x00e4
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r13 = r0.confirmAble
            goto L_0x00e5
        L_0x00e4:
            r13 = 0
        L_0x00e5:
            r14 = 5
            r1.updateLiveDataRegistration(r14, r13)
            if (r13 == 0) goto L_0x00f2
            java.lang.Object r13 = r13.getValue()
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            goto L_0x00f3
        L_0x00f2:
            r13 = 0
        L_0x00f3:
            boolean r13 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r13)
            if (r5 == 0) goto L_0x0101
            if (r13 == 0) goto L_0x00fe
            r14 = 2048(0x800, double:1.0118E-320)
            goto L_0x0100
        L_0x00fe:
            r14 = 1024(0x400, double:5.06E-321)
        L_0x0100:
            long r2 = r2 | r14
        L_0x0101:
            android.widget.Button r5 = r1.ibNext
            android.content.Context r5 = r5.getContext()
            if (r13 == 0) goto L_0x010c
            int r14 = com.eternal.account.C0997R.C0999drawable.create_account_gradient
            goto L_0x010e
        L_0x010c:
            int r14 = com.eternal.account.C0997R.C0999drawable.create_account_disable
        L_0x010e:
            android.graphics.drawable.Drawable r5 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r5, r14)
            goto L_0x0115
        L_0x0113:
            r5 = 0
            r13 = 0
        L_0x0115:
            r14 = 832(0x340, double:4.11E-321)
            long r39 = r2 & r14
            r14 = 0
            int r41 = (r39 > r14 ? 1 : (r39 == r14 ? 0 : -1))
            if (r41 == 0) goto L_0x0166
            if (r0 == 0) goto L_0x0124
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r14 = r0.showLoading
            goto L_0x0125
        L_0x0124:
            r14 = 0
        L_0x0125:
            r15 = 6
            r1.updateLiveDataRegistration(r15, r14)
            if (r14 == 0) goto L_0x0132
            java.lang.Object r14 = r14.getValue()
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            goto L_0x0133
        L_0x0132:
            r14 = 0
        L_0x0133:
            boolean r14 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r14)
            if (r41 == 0) goto L_0x014d
            if (r14 == 0) goto L_0x0144
            r39 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r39
            r39 = 131072(0x20000, double:6.47582E-319)
            goto L_0x014b
        L_0x0144:
            r39 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r39
            r39 = 65536(0x10000, double:3.2379E-319)
        L_0x014b:
            long r2 = r2 | r39
        L_0x014d:
            if (r14 == 0) goto L_0x0152
            r29 = 0
            goto L_0x0154
        L_0x0152:
            r29 = 8
        L_0x0154:
            if (r14 == 0) goto L_0x015b
            android.widget.Button r14 = r1.ibNext
            int r15 = com.eternal.account.C0997R.C0998color.color_transparency
            goto L_0x015f
        L_0x015b:
            android.widget.Button r14 = r1.ibNext
            int r15 = com.eternal.account.C0997R.C0998color.white
        L_0x015f:
            int r14 = getColorFromResource(r14, r15)
            r26 = 896(0x380, double:4.427E-321)
            goto L_0x016b
        L_0x0166:
            r14 = 0
            r26 = 896(0x380, double:4.427E-321)
            r29 = 0
        L_0x016b:
            long r39 = r2 & r26
            r35 = 0
            int r15 = (r39 > r35 ? 1 : (r39 == r35 ? 0 : -1))
            if (r15 == 0) goto L_0x018d
            if (r0 == 0) goto L_0x017c
            me.tatarka.bindingcollectionadapter2.ItemBinding<com.eternal.account.model.InviteItemModel> r15 = r0.itemBinding
            androidx.databinding.ObservableList<com.eternal.account.model.InviteItemModel> r0 = r0.items
            r39 = r2
            goto L_0x0180
        L_0x017c:
            r39 = r2
            r0 = 0
            r15 = 0
        L_0x0180:
            r2 = 7
            r1.updateRegistration((int) r2, (androidx.databinding.ObservableList) r0)
            r43 = r0
            r42 = r15
            r0 = r29
            r2 = r39
            goto L_0x01a2
        L_0x018d:
            r39 = r2
            r0 = r29
            goto L_0x019e
        L_0x0192:
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
        L_0x019e:
            r42 = 0
            r43 = 0
        L_0x01a2:
            long r24 = r2 & r24
            r35 = 0
            int r15 = (r24 > r35 ? 1 : (r24 == r35 ? 0 : -1))
            if (r15 == 0) goto L_0x01ba
            android.widget.EditText r15 = r1.etEmail
            com.eternal.framework.binding.viewadapter.edittext.ViewAdapter.addTextChangedListener(r15, r10)
            android.widget.Button r10 = r1.ibNext
            r15 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r10, r11, r15)
            com.eternal.widget.guqiang.ProgressToolbar r10 = r1.toolBar
            com.eternal.widget.guqiang.ProgressToolbarAdapter.onBack(r10, r9)
        L_0x01ba:
            long r9 = r2 & r22
            int r11 = (r9 > r35 ? 1 : (r9 == r35 ? 0 : -1))
            if (r11 == 0) goto L_0x01c5
            android.widget.EditText r9 = r1.etEmail
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r9, r6)
        L_0x01c5:
            long r9 = r2 & r18
            int r6 = (r9 > r35 ? 1 : (r9 == r35 ? 0 : -1))
            if (r6 == 0) goto L_0x01d0
            android.widget.EditText r6 = r1.etEmail
            r6.setTextColor(r4)
        L_0x01d0:
            r9 = 512(0x200, double:2.53E-321)
            long r9 = r9 & r2
            int r4 = (r9 > r35 ? 1 : (r9 == r35 ? 0 : -1))
            if (r4 == 0) goto L_0x01f1
            android.widget.EditText r4 = r1.etEmail
            r6 = 0
            r9 = r6
            androidx.databinding.adapters.TextViewBindingAdapter$BeforeTextChanged r9 = (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged) r9
            r10 = r6
            androidx.databinding.adapters.TextViewBindingAdapter$OnTextChanged r10 = (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged) r10
            r11 = r6
            androidx.databinding.adapters.TextViewBindingAdapter$AfterTextChanged r11 = (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged) r11
            androidx.databinding.InverseBindingListener r6 = r1.etEmailandroidTextAttrChanged
            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(r4, r9, r10, r11, r6)
            com.eternal.base.SlideRecyclerView r4 = r1.mboundView5
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r6 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r4, r6)
        L_0x01f1:
            r9 = 800(0x320, double:3.953E-321)
            long r9 = r9 & r2
            r18 = 0
            int r4 = (r9 > r18 ? 1 : (r9 == r18 ? 0 : -1))
            if (r4 == 0) goto L_0x0204
            android.widget.Button r4 = r1.ibNext
            r4.setClickable(r13)
            android.widget.Button r4 = r1.ibNext
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundDrawable(r4, r5)
        L_0x0204:
            r4 = 832(0x340, double:4.11E-321)
            long r4 = r4 & r2
            int r6 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r6 == 0) goto L_0x0215
            android.widget.Button r4 = r1.ibNext
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r4, r14)
            android.widget.ProgressBar r4 = r1.pbLoading
            r4.setVisibility(r0)
        L_0x0215:
            long r4 = r2 & r20
            int r0 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x0220
            android.view.View r0 = r1.mboundView3
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.backgroundColor(r0, r7)
        L_0x0220:
            r4 = 776(0x308, double:3.834E-321)
            long r4 = r4 & r2
            int r0 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x022c
            android.widget.TextView r0 = r1.mboundView4
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x022c:
            long r4 = r2 & r16
            int r0 = (r4 > r18 ? 1 : (r4 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x0237
            android.widget.TextView r0 = r1.mboundView4
            r0.setVisibility(r8)
        L_0x0237:
            r4 = 896(0x380, double:4.427E-321)
            long r2 = r2 & r4
            int r0 = (r2 > r18 ? 1 : (r2 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x0252
            com.eternal.base.SlideRecyclerView r0 = r1.mboundView5
            r2 = 0
            r44 = r2
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r44 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r44
            r45 = r2
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r45 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r45
            r46 = r2
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r46 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r46
            r41 = r0
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r41, r42, r43, r44, r45, r46)
        L_0x0252:
            return
        L_0x0253:
            r0 = move-exception
            monitor-exit(r47)     // Catch:{ all -> 0x0253 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityInviteBindingImpl.executeBindings():void");
    }
}
