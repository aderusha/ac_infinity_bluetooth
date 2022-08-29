package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.ShareItemModel;
import com.eternal.account.model.ShareModel;
import p018me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;

public class ActivityShareBindingImpl extends ActivityShareBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final TextView mboundView11;
    private final TextView mboundView4;
    private final View mboundView5;
    private final RelativeLayout mboundView6;
    private final TextView mboundView8;
    private final RelativeLayout mboundView9;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.ct_tab, 13);
        sparseIntArray.put(C0997R.C1000id.v_slide, 14);
    }

    public ActivityShareBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private ActivityShareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, objArr[13], objArr[12], objArr[3], objArr[0], objArr[7], objArr[10], objArr[1], objArr[2], objArr[14]);
        this.mDirtyFlags = -1;
        this.ibNext.setTag((Object) null);
        this.llWithYou.setTag((Object) null);
        TextView textView = objArr[11];
        this.mboundView11 = textView;
        textView.setTag((Object) null);
        TextView textView2 = objArr[4];
        this.mboundView4 = textView2;
        textView2.setTag((Object) null);
        View view2 = objArr[5];
        this.mboundView5 = view2;
        view2.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[6];
        this.mboundView6 = relativeLayout;
        relativeLayout.setTag((Object) null);
        TextView textView3 = objArr[8];
        this.mboundView8 = textView3;
        textView3.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[9];
        this.mboundView9 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        this.root.setTag((Object) null);
        this.srvWithOther.setTag((Object) null);
        this.srvWithYou.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvWithOthers.setTag((Object) null);
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
        if (C0977BR.shareModel != i) {
            return false;
        }
        setShareModel((ShareModel) obj);
        return true;
    }

    public void setShareModel(ShareModel shareModel) {
        this.mShareModel = shareModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(C0977BR.shareModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeShareModelItems2((DiffObservableList) obj, i2);
        }
        if (i == 1) {
            return onChangeShareModelShowEmpty((MutableLiveData) obj, i2);
        }
        if (i == 2) {
            return onChangeShareModelShowDot((MutableLiveData) obj, i2);
        }
        if (i == 3) {
            return onChangeShareModelItems((DiffObservableList) obj, i2);
        }
        if (i == 4) {
            return onChangeShareModelShowEmpty2((MutableLiveData) obj, i2);
        }
        if (i != 5) {
            return false;
        }
        return onChangeShareModelSelectWithYou((MutableLiveData) obj, i2);
    }

    private boolean onChangeShareModelItems2(DiffObservableList<ShareItemModel> diffObservableList, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeShareModelShowEmpty(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeShareModelShowDot(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeShareModelItems(DiffObservableList<ShareItemModel> diffObservableList, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeShareModelShowEmpty2(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeShareModelSelectWithYou(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x0102  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r40 = this;
            r1 = r40
            monitor-enter(r40)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0256 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0256 }
            monitor-exit(r40)     // Catch:{ all -> 0x0256 }
            com.eternal.account.model.ShareModel r0 = r1.mShareModel
            r6 = 255(0xff, double:1.26E-321)
            long r6 = r6 & r2
            r10 = 196(0xc4, double:9.7E-322)
            r14 = 194(0xc2, double:9.6E-322)
            r16 = 192(0xc0, double:9.5E-322)
            r18 = 193(0xc1, double:9.54E-322)
            r20 = 224(0xe0, double:1.107E-321)
            r12 = 0
            int r24 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x018b
            long r6 = r2 & r18
            int r24 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x002e
            if (r0 == 0) goto L_0x0029
            me.tatarka.bindingcollectionadapter2.collections.DiffObservableList<com.eternal.account.model.ShareItemModel> r6 = r0.items2
            goto L_0x002a
        L_0x0029:
            r6 = 0
        L_0x002a:
            r1.updateRegistration((int) r12, (androidx.databinding.ObservableList) r6)
            goto L_0x002f
        L_0x002e:
            r6 = 0
        L_0x002f:
            long r24 = r2 & r14
            int r26 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r26 == 0) goto L_0x0060
            if (r0 == 0) goto L_0x003a
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r7 = r0.showEmpty
            goto L_0x003b
        L_0x003a:
            r7 = 0
        L_0x003b:
            r13 = 1
            r1.updateLiveDataRegistration(r13, r7)
            if (r7 == 0) goto L_0x0048
            java.lang.Object r7 = r7.getValue()
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            goto L_0x0049
        L_0x0048:
            r7 = 0
        L_0x0049:
            boolean r7 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r7)
            if (r26 == 0) goto L_0x005a
            if (r7 == 0) goto L_0x0055
            r26 = 524288(0x80000, double:2.590327E-318)
            goto L_0x0058
        L_0x0055:
            r26 = 262144(0x40000, double:1.295163E-318)
        L_0x0058:
            long r2 = r2 | r26
        L_0x005a:
            if (r7 == 0) goto L_0x005d
            goto L_0x0060
        L_0x005d:
            r7 = 8
            goto L_0x0061
        L_0x0060:
            r7 = 0
        L_0x0061:
            long r26 = r2 & r16
            int r13 = (r26 > r4 ? 1 : (r26 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0072
            if (r0 == 0) goto L_0x0072
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onWithOthers
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r14 = r0.onWithYou
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onBack
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r12 = r0.onNext
            goto L_0x0076
        L_0x0072:
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x0076:
            r28 = 201(0xc9, double:9.93E-322)
            long r28 = r2 & r28
            int r30 = (r28 > r4 ? 1 : (r28 == r4 ? 0 : -1))
            if (r30 == 0) goto L_0x0083
            if (r0 == 0) goto L_0x0083
            me.tatarka.bindingcollectionadapter2.OnItemBind<com.eternal.account.model.ShareItemModel> r8 = r0.itemBinding
            goto L_0x0084
        L_0x0083:
            r8 = 0
        L_0x0084:
            long r30 = r2 & r10
            int r9 = (r30 > r4 ? 1 : (r30 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00b3
            if (r0 == 0) goto L_0x008f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.showDot
            goto L_0x0090
        L_0x008f:
            r10 = 0
        L_0x0090:
            r11 = 2
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x009d
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x009e
        L_0x009d:
            r10 = 0
        L_0x009e:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r9 == 0) goto L_0x00ad
            if (r10 == 0) goto L_0x00a9
            r32 = 2048(0x800, double:1.0118E-320)
            goto L_0x00ab
        L_0x00a9:
            r32 = 1024(0x400, double:5.06E-321)
        L_0x00ab:
            long r2 = r2 | r32
        L_0x00ad:
            if (r10 == 0) goto L_0x00b0
            goto L_0x00b3
        L_0x00b0:
            r9 = 8
            goto L_0x00b4
        L_0x00b3:
            r9 = 0
        L_0x00b4:
            r10 = 200(0xc8, double:9.9E-322)
            long r32 = r2 & r10
            int r10 = (r32 > r4 ? 1 : (r32 == r4 ? 0 : -1))
            if (r10 == 0) goto L_0x00c7
            if (r0 == 0) goto L_0x00c1
            me.tatarka.bindingcollectionadapter2.collections.DiffObservableList<com.eternal.account.model.ShareItemModel> r10 = r0.items
            goto L_0x00c2
        L_0x00c1:
            r10 = 0
        L_0x00c2:
            r11 = 3
            r1.updateRegistration((int) r11, (androidx.databinding.ObservableList) r10)
            goto L_0x00c8
        L_0x00c7:
            r10 = 0
        L_0x00c8:
            r22 = 208(0xd0, double:1.03E-321)
            long r32 = r2 & r22
            int r11 = (r32 > r4 ? 1 : (r32 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00f9
            if (r0 == 0) goto L_0x00d5
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r4 = r0.showEmpty2
            goto L_0x00d6
        L_0x00d5:
            r4 = 0
        L_0x00d6:
            r5 = 4
            r1.updateLiveDataRegistration(r5, r4)
            if (r4 == 0) goto L_0x00e3
            java.lang.Object r4 = r4.getValue()
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00e4
        L_0x00e3:
            r4 = 0
        L_0x00e4:
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            if (r11 == 0) goto L_0x00f3
            if (r4 == 0) goto L_0x00ef
            r34 = 512(0x200, double:2.53E-321)
            goto L_0x00f1
        L_0x00ef:
            r34 = 256(0x100, double:1.265E-321)
        L_0x00f1:
            long r2 = r2 | r34
        L_0x00f3:
            if (r4 == 0) goto L_0x00f6
            goto L_0x00f9
        L_0x00f6:
            r4 = 8
            goto L_0x00fa
        L_0x00f9:
            r4 = 0
        L_0x00fa:
            long r34 = r2 & r20
            r32 = 0
            int r5 = (r34 > r32 ? 1 : (r34 == r32 ? 0 : -1))
            if (r5 == 0) goto L_0x0181
            if (r0 == 0) goto L_0x0107
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r0.selectWithYou
            goto L_0x0108
        L_0x0107:
            r0 = 0
        L_0x0108:
            r11 = 5
            r1.updateLiveDataRegistration(r11, r0)
            if (r0 == 0) goto L_0x0115
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            goto L_0x0116
        L_0x0115:
            r0 = 0
        L_0x0116:
            boolean r0 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r0)
            if (r5 == 0) goto L_0x0142
            if (r0 == 0) goto L_0x0130
            r34 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 | r34
            r34 = 32768(0x8000, double:1.61895E-319)
            long r2 = r2 | r34
            r34 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r34
            r34 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x0140
        L_0x0130:
            r34 = 4096(0x1000, double:2.0237E-320)
            long r2 = r2 | r34
            r34 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r34
            r34 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 | r34
            r34 = 1048576(0x100000, double:5.180654E-318)
        L_0x0140:
            long r2 = r2 | r34
        L_0x0142:
            if (r0 == 0) goto L_0x0146
            r5 = 0
            goto L_0x0148
        L_0x0146:
            r5 = 8
        L_0x0148:
            if (r0 == 0) goto L_0x014d
            r24 = 8
            goto L_0x014f
        L_0x014d:
            r24 = 0
        L_0x014f:
            if (r0 == 0) goto L_0x015c
            android.widget.TextView r11 = r1.mboundView4
            r34 = r2
            int r2 = com.eternal.account.C0997R.C0998color.color_1DABF1
            int r2 = getColorFromResource(r11, r2)
            goto L_0x0166
        L_0x015c:
            r34 = r2
            android.widget.TextView r2 = r1.mboundView4
            int r3 = com.eternal.account.C0997R.C0998color.color_F1F1F1
            int r2 = getColorFromResource(r2, r3)
        L_0x0166:
            if (r0 == 0) goto L_0x016d
            android.widget.TextView r0 = r1.tvWithOthers
            int r3 = com.eternal.account.C0997R.C0998color.white
            goto L_0x0171
        L_0x016d:
            android.widget.TextView r0 = r1.tvWithOthers
            int r3 = com.eternal.account.C0997R.C0998color.color_1DABF1
        L_0x0171:
            int r0 = getColorFromResource(r0, r3)
            r36 = r10
            r11 = r24
            r10 = r9
            r9 = r6
            r6 = r5
            r5 = r4
            r4 = r2
            r2 = r34
            goto L_0x019a
        L_0x0181:
            r5 = r4
            r36 = r10
            r0 = 0
            r4 = 0
            r11 = 0
            r10 = r9
            r9 = r6
            r6 = 0
            goto L_0x019a
        L_0x018b:
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
            r36 = 0
        L_0x019a:
            long r20 = r2 & r20
            r32 = 0
            int r24 = (r20 > r32 ? 1 : (r20 == r32 ? 0 : -1))
            r20 = r9
            if (r24 == 0) goto L_0x01bd
            android.widget.Button r9 = r1.ibNext
            r9.setVisibility(r11)
            android.widget.TextView r9 = r1.mboundView4
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r9, r4)
            android.widget.RelativeLayout r4 = r1.mboundView6
            r4.setVisibility(r11)
            android.widget.RelativeLayout r4 = r1.mboundView9
            r4.setVisibility(r6)
            android.widget.TextView r4 = r1.tvWithOthers
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r4, r0)
        L_0x01bd:
            long r16 = r2 & r16
            r32 = 0
            int r0 = (r16 > r32 ? 1 : (r16 == r32 ? 0 : -1))
            if (r0 == 0) goto L_0x01da
            android.widget.Button r0 = r1.ibNext
            r4 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r12, r4)
            android.widget.LinearLayout r0 = r1.llWithYou
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r14, r4)
            com.eternal.widget.guqiang.ProgressToolbar r0 = r1.toolBar
            com.eternal.widget.guqiang.ProgressToolbarAdapter.onBack(r0, r15)
            android.widget.TextView r0 = r1.tvWithOthers
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r13, r4)
        L_0x01da:
            r11 = 208(0xd0, double:1.03E-321)
            long r11 = r11 & r2
            r13 = 0
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x01e8
            android.widget.TextView r0 = r1.mboundView11
            r0.setVisibility(r5)
        L_0x01e8:
            r4 = 196(0xc4, double:9.7E-322)
            long r4 = r4 & r2
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x01f4
            android.view.View r0 = r1.mboundView5
            r0.setVisibility(r10)
        L_0x01f4:
            r4 = 194(0xc2, double:9.6E-322)
            long r4 = r4 & r2
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0200
            android.widget.TextView r0 = r1.mboundView8
            r0.setVisibility(r7)
        L_0x0200:
            r4 = 128(0x80, double:6.32E-322)
            long r4 = r4 & r2
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0219
            com.eternal.base.SlideRecyclerView r0 = r1.srvWithOther
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r4 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r0, r4)
            com.eternal.base.SlideRecyclerView r0 = r1.srvWithYou
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r4 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r0, r4)
        L_0x0219:
            r4 = 200(0xc8, double:9.9E-322)
            long r4 = r4 & r2
            int r0 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x0238
            com.eternal.base.SlideRecyclerView r0 = r1.srvWithOther
            me.tatarka.bindingcollectionadapter2.ItemBinding r35 = p018me.tatarka.bindingcollectionadapter2.BindingCollectionAdapters.toItemBinding(r8)
            r4 = 0
            r37 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r37 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r37
            r38 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r38 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r38
            r39 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r39 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r39
            r34 = r0
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r34, r35, r36, r37, r38, r39)
        L_0x0238:
            long r2 = r2 & r18
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0255
            com.eternal.base.SlideRecyclerView r7 = r1.srvWithYou
            me.tatarka.bindingcollectionadapter2.ItemBinding r8 = p018me.tatarka.bindingcollectionadapter2.BindingCollectionAdapters.toItemBinding(r8)
            r0 = 0
            r10 = r0
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r10 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r10
            r11 = r0
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r11 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r11
            r12 = r0
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r12 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r12
            r9 = r20
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r7, r8, r9, r10, r11, r12)
        L_0x0255:
            return
        L_0x0256:
            r0 = move-exception
            monitor-exit(r40)     // Catch:{ all -> 0x0256 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ActivityShareBindingImpl.executeBindings():void");
    }
}
