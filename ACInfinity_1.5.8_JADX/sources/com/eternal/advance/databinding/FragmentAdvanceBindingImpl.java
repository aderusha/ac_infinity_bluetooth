package com.eternal.advance.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.advance.C1200BR;
import com.eternal.advance.C1202R;
import com.eternal.advance.model.AdvanceModel;
import com.eternal.advance.model.ItemModel;

public class FragmentAdvanceBindingImpl extends FragmentAdvanceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1202R.C1205id.tb_port, 9);
        sparseIntArray.put(C1202R.C1205id.ct_tab, 10);
        sparseIntArray.put(C1202R.C1205id.v_slide, 11);
    }

    public FragmentAdvanceBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private FragmentAdvanceBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, objArr[10], objArr[8], objArr[6], objArr[5], objArr[7], objArr[9], objArr[4], objArr[3], objArr[2], objArr[1], objArr[11]);
        this.mDirtyFlags = -1;
        this.ivbAddProgram.setTag((Object) null);
        this.listAlarms.setTag((Object) null);
        this.listAutomations.setTag((Object) null);
        this.listNotification.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.tvAlarms.setTag((Object) null);
        this.tvAutomations.setTag((Object) null);
        this.vClickAlarms.setTag((Object) null);
        this.vClickAutomation.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
        if (C1200BR.model != i) {
            return false;
        }
        setModel((AdvanceModel) obj);
        return true;
    }

    public void setModel(AdvanceModel advanceModel) {
        this.mModel = advanceModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(C1200BR.model);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeModelSelectTab((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangeModelAutomations((ObservableList) obj, i2);
        }
        if (i == 2) {
            return onChangeModelNotifications((ObservableList) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeModelAlarms((ObservableList) obj, i2);
    }

    private boolean onChangeModelSelectTab(MutableLiveData<Integer> mutableLiveData, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelAutomations(ObservableList<ItemModel> observableList, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModelNotifications(ObservableList<ItemModel> observableList, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModelAlarms(ObservableList<ItemModel> observableList, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00c4, code lost:
        if (r8 != false) goto L_0x00cb;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r34 = this;
            r1 = r34
            monitor-enter(r34)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x01e6 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x01e6 }
            monitor-exit(r34)     // Catch:{ all -> 0x01e6 }
            com.eternal.advance.model.AdvanceModel r0 = r1.mModel
            r6 = 63
            long r6 = r6 & r2
            r12 = 50
            r14 = 48
            r16 = 49
            r10 = 0
            int r20 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x012a
            long r6 = r2 & r14
            int r20 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x0029
            if (r0 == 0) goto L_0x0029
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r6 = r0.onAdd
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onAutomations
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r11 = r0.onAlarms
            goto L_0x002c
        L_0x0029:
            r6 = 0
            r7 = 0
            r11 = 0
        L_0x002c:
            long r21 = r2 & r16
            r14 = 2
            r15 = 1
            int r25 = (r21 > r4 ? 1 : (r21 == r4 ? 0 : -1))
            if (r25 == 0) goto L_0x00c7
            if (r0 == 0) goto L_0x0039
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r8 = r0.selectTab
            goto L_0x003a
        L_0x0039:
            r8 = 0
        L_0x003a:
            r1.updateLiveDataRegistration(r10, r8)
            if (r8 == 0) goto L_0x0046
            java.lang.Object r8 = r8.getValue()
            java.lang.Integer r8 = (java.lang.Integer) r8
            goto L_0x0047
        L_0x0046:
            r8 = 0
        L_0x0047:
            int r8 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Integer) r8)
            if (r8 != r14) goto L_0x004f
            r9 = 1
            goto L_0x0050
        L_0x004f:
            r9 = 0
        L_0x0050:
            if (r8 != r15) goto L_0x0055
            r26 = 1
            goto L_0x0057
        L_0x0055:
            r26 = 0
        L_0x0057:
            if (r8 != 0) goto L_0x005b
            r8 = 1
            goto L_0x005c
        L_0x005b:
            r8 = 0
        L_0x005c:
            if (r25 == 0) goto L_0x0067
            if (r9 == 0) goto L_0x0063
            r27 = 2048(0x800, double:1.0118E-320)
            goto L_0x0065
        L_0x0063:
            r27 = 1024(0x400, double:5.06E-321)
        L_0x0065:
            long r2 = r2 | r27
        L_0x0067:
            long r27 = r2 & r16
            int r25 = (r27 > r4 ? 1 : (r27 == r4 ? 0 : -1))
            if (r25 == 0) goto L_0x007f
            if (r26 == 0) goto L_0x0077
            r27 = 512(0x200, double:2.53E-321)
            long r2 = r2 | r27
            r27 = 32768(0x8000, double:1.61895E-319)
            goto L_0x007d
        L_0x0077:
            r27 = 256(0x100, double:1.265E-321)
            long r2 = r2 | r27
            r27 = 16384(0x4000, double:8.0948E-320)
        L_0x007d:
            long r2 = r2 | r27
        L_0x007f:
            long r27 = r2 & r16
            int r25 = (r27 > r4 ? 1 : (r27 == r4 ? 0 : -1))
            if (r25 == 0) goto L_0x0096
            if (r8 == 0) goto L_0x008e
            r27 = 128(0x80, double:6.32E-322)
            long r2 = r2 | r27
            r27 = 8192(0x2000, double:4.0474E-320)
            goto L_0x0094
        L_0x008e:
            r27 = 64
            long r2 = r2 | r27
            r27 = 4096(0x1000, double:2.0237E-320)
        L_0x0094:
            long r2 = r2 | r27
        L_0x0096:
            r25 = 8
            if (r9 == 0) goto L_0x009c
            r9 = 0
            goto L_0x009e
        L_0x009c:
            r9 = 8
        L_0x009e:
            android.widget.TextView r10 = r1.tvAlarms
            if (r26 == 0) goto L_0x00a5
            int r14 = com.eternal.advance.C1202R.C1203color.color_15BAFF
            goto L_0x00a7
        L_0x00a5:
            int r14 = com.eternal.advance.C1202R.C1203color.white
        L_0x00a7:
            int r10 = getColorFromResource(r10, r14)
            if (r26 == 0) goto L_0x00af
            r14 = 0
            goto L_0x00b1
        L_0x00af:
            r14 = 8
        L_0x00b1:
            if (r8 == 0) goto L_0x00bc
            android.widget.TextView r15 = r1.tvAutomations
            int r4 = com.eternal.advance.C1202R.C1203color.color_15BAFF
            int r4 = getColorFromResource(r15, r4)
            goto L_0x00c4
        L_0x00bc:
            android.widget.TextView r4 = r1.tvAutomations
            int r5 = com.eternal.advance.C1202R.C1203color.white
            int r4 = getColorFromResource(r4, r5)
        L_0x00c4:
            if (r8 == 0) goto L_0x00cd
            goto L_0x00cb
        L_0x00c7:
            r4 = 0
            r9 = 0
            r10 = 0
            r14 = 0
        L_0x00cb:
            r25 = 0
        L_0x00cd:
            long r31 = r2 & r12
            r29 = 0
            int r5 = (r31 > r29 ? 1 : (r31 == r29 ? 0 : -1))
            if (r5 == 0) goto L_0x00e0
            if (r0 == 0) goto L_0x00da
            androidx.databinding.ObservableList<com.eternal.advance.model.ItemModel> r5 = r0.automations
            goto L_0x00db
        L_0x00da:
            r5 = 0
        L_0x00db:
            r8 = 1
            r1.updateRegistration((int) r8, (androidx.databinding.ObservableList) r5)
            goto L_0x00e1
        L_0x00e0:
            r5 = 0
        L_0x00e1:
            r21 = 52
            long r31 = r2 & r21
            int r8 = (r31 > r29 ? 1 : (r31 == r29 ? 0 : -1))
            if (r8 == 0) goto L_0x00f4
            if (r0 == 0) goto L_0x00ee
            androidx.databinding.ObservableList<com.eternal.advance.model.ItemModel> r8 = r0.notifications
            goto L_0x00ef
        L_0x00ee:
            r8 = 0
        L_0x00ef:
            r15 = 2
            r1.updateRegistration((int) r15, (androidx.databinding.ObservableList) r8)
            goto L_0x00f5
        L_0x00f4:
            r8 = 0
        L_0x00f5:
            r31 = 62
            long r31 = r2 & r31
            int r15 = (r31 > r29 ? 1 : (r31 == r29 ? 0 : -1))
            if (r15 == 0) goto L_0x0102
            if (r0 == 0) goto L_0x0102
            me.tatarka.bindingcollectionadapter2.ItemBinding<com.eternal.advance.model.ItemModel> r15 = r0.itemBinding
            goto L_0x0103
        L_0x0102:
            r15 = 0
        L_0x0103:
            r18 = 56
            long r31 = r2 & r18
            int r26 = (r31 > r29 ? 1 : (r31 == r29 ? 0 : -1))
            if (r26 == 0) goto L_0x011c
            if (r0 == 0) goto L_0x0110
            androidx.databinding.ObservableList<com.eternal.advance.model.ItemModel> r0 = r0.alarms
            goto L_0x0111
        L_0x0110:
            r0 = 0
        L_0x0111:
            r12 = 3
            r1.updateRegistration((int) r12, (androidx.databinding.ObservableList) r0)
            r12 = r0
            r0 = r8
            r23 = r15
            r8 = r25
            goto L_0x0122
        L_0x011c:
            r0 = r8
            r23 = r15
            r8 = r25
            r12 = 0
        L_0x0122:
            r24 = 48
            r33 = r7
            r7 = r5
            r5 = r33
            goto L_0x0139
        L_0x012a:
            r24 = r14
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
            r23 = 0
        L_0x0139:
            long r24 = r2 & r24
            r28 = 0
            int r13 = (r24 > r28 ? 1 : (r24 == r28 ? 0 : -1))
            if (r13 == 0) goto L_0x0151
            android.widget.ImageButton r13 = r1.ivbAddProgram
            r15 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r13, r6, r15)
            android.view.View r6 = r1.vClickAlarms
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r6, r11, r15)
            android.view.View r6 = r1.vClickAutomation
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r6, r5, r15)
        L_0x0151:
            r5 = 32
            long r5 = r5 & r2
            r24 = 0
            int r11 = (r5 > r24 ? 1 : (r5 == r24 ? 0 : -1))
            if (r11 == 0) goto L_0x0175
            com.eternal.base.SlideRecyclerView r5 = r1.listAlarms
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r6 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r5, r6)
            com.eternal.base.SlideRecyclerView r5 = r1.listAutomations
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r6 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r5, r6)
            com.eternal.base.SlideRecyclerView r5 = r1.listNotification
            me.tatarka.bindingcollectionadapter2.LayoutManagers$LayoutManagerFactory r6 = p018me.tatarka.bindingcollectionadapter2.LayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r5, r6)
        L_0x0175:
            long r5 = r2 & r16
            r15 = 0
            int r11 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r11 == 0) goto L_0x0196
            com.eternal.base.SlideRecyclerView r5 = r1.listAlarms
            r5.setVisibility(r14)
            com.eternal.base.SlideRecyclerView r5 = r1.listAutomations
            r5.setVisibility(r8)
            com.eternal.base.SlideRecyclerView r5 = r1.listNotification
            r5.setVisibility(r9)
            android.widget.TextView r5 = r1.tvAlarms
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r5, r10)
            android.widget.TextView r5 = r1.tvAutomations
            com.eternal.framework.binding.viewadapter.textview.viewAdapter.setTextColor(r5, r4)
        L_0x0196:
            r4 = 56
            long r4 = r4 & r2
            r8 = 0
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x01b0
            com.eternal.base.SlideRecyclerView r10 = r1.listAlarms
            r4 = 0
            r13 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r13 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r13
            r14 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r14 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r14
            r15 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r15 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r15
            r11 = r23
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r10, r11, r12, r13, r14, r15)
        L_0x01b0:
            r4 = 50
            long r4 = r4 & r2
            r8 = 0
            int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
            if (r6 == 0) goto L_0x01ca
            com.eternal.base.SlideRecyclerView r5 = r1.listAutomations
            r4 = 0
            r8 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r8 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r8
            r9 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r9 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r9
            r10 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r10 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r10
            r6 = r23
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r5, r6, r7, r8, r9, r10)
        L_0x01ca:
            r4 = 52
            long r2 = r2 & r4
            r4 = 0
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x01e5
            com.eternal.base.SlideRecyclerView r8 = r1.listNotification
            r2 = 0
            r11 = r2
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r11 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r11
            r12 = r2
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r12 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r12
            r13 = r2
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r13 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r13
            r9 = r23
            r10 = r0
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r8, r9, r10, r11, r12, r13)
        L_0x01e5:
            return
        L_0x01e6:
            r0 = move-exception
            monitor-exit(r34)     // Catch:{ all -> 0x01e6 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.advance.databinding.FragmentAdvanceBindingImpl.executeBindings():void");
    }
}
