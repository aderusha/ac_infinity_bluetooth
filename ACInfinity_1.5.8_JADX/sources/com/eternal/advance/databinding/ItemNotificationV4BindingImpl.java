package com.eternal.advance.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import com.eternal.advance.C1200BR;
import com.eternal.advance.model.ItemChildModelV4;
import com.eternal.advance.model.ItemModelV4;
import com.eternal.widget.ExpandableLayout;

public class ItemNotificationV4BindingImpl extends ItemNotificationV4Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final View mboundView10;
    private final ExpandableLayout mboundView7;
    private final ImageView mboundView9;
    private InverseBindingListener sbandroidCheckedAttrChanged;

    public ItemNotificationV4BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private ItemNotificationV4BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 5, objArr[2], objArr[4], objArr[8], objArr[3], objArr[6], objArr[1], objArr[5]);
        this.sbandroidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = ItemNotificationV4BindingImpl.this.f127sb.isChecked();
                ItemModelV4 itemModelV4 = ItemNotificationV4BindingImpl.this.mItemV4;
                boolean z = true;
                if (itemModelV4 != null) {
                    ObservableBoolean observableBoolean = itemModelV4.open;
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
        this.clContent.setTag((Object) null);
        this.ivType.setTag((Object) null);
        this.listChild.setTag("list");
        this.llHead.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        View view2 = objArr[10];
        this.mboundView10 = view2;
        view2.setTag((Object) null);
        ExpandableLayout expandableLayout = objArr[7];
        this.mboundView7 = expandableLayout;
        expandableLayout.setTag((Object) null);
        ImageView imageView = objArr[9];
        this.mboundView9 = imageView;
        imageView.setTag((Object) null);
        this.f127sb.setTag("switch");
        this.tvDel.setTag((Object) null);
        this.tvTypeName.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
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
        if (C1200BR.itemV4 != i) {
            return false;
        }
        setItemV4((ItemModelV4) obj);
        return true;
    }

    public void setItemV4(ItemModelV4 itemModelV4) {
        this.mItemV4 = itemModelV4;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(C1200BR.itemV4);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeItemV4AdvActive((ObservableBoolean) obj, i2);
        }
        if (i == 1) {
            return onChangeItemV4Title((ObservableField) obj, i2);
        }
        if (i == 2) {
            return onChangeItemV4ChildList((ObservableList) obj, i2);
        }
        if (i == 3) {
            return onChangeItemV4Open((ObservableBoolean) obj, i2);
        }
        if (i != 4) {
            return false;
        }
        return onChangeItemV4TypeRes((ObservableInt) obj, i2);
    }

    private boolean onChangeItemV4AdvActive(ObservableBoolean observableBoolean, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeItemV4Title(ObservableField<String> observableField, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeItemV4ChildList(ObservableList<ItemChildModelV4> observableList, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeItemV4Open(ObservableBoolean observableBoolean, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeItemV4TypeRes(ObservableInt observableInt, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ac  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r33 = this;
            r1 = r33
            monitor-enter(r33)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x016a }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x016a }
            monitor-exit(r33)     // Catch:{ all -> 0x016a }
            com.eternal.advance.model.ItemModelV4 r0 = r1.mItemV4
            r6 = 127(0x7f, double:6.27E-322)
            long r6 = r6 & r2
            r8 = 98
            r12 = 97
            r16 = 100
            r18 = 96
            r14 = 0
            int r22 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x00c7
            long r6 = r2 & r16
            int r22 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x0030
            if (r0 == 0) goto L_0x0029
            me.tatarka.bindingcollectionadapter2.ItemBinding<com.eternal.advance.model.ItemChildModelV4> r6 = r0.itemBinding
            androidx.databinding.ObservableList<com.eternal.advance.model.ItemChildModelV4> r7 = r0.childList
            goto L_0x002b
        L_0x0029:
            r6 = 0
            r7 = 0
        L_0x002b:
            r15 = 2
            r1.updateRegistration((int) r15, (androidx.databinding.ObservableList) r7)
            goto L_0x0032
        L_0x0030:
            r6 = 0
            r7 = 0
        L_0x0032:
            long r23 = r2 & r12
            int r15 = (r23 > r4 ? 1 : (r23 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x005a
            if (r0 == 0) goto L_0x003d
            androidx.databinding.ObservableBoolean r12 = r0.advActive
            goto L_0x003e
        L_0x003d:
            r12 = 0
        L_0x003e:
            r1.updateRegistration((int) r14, (androidx.databinding.Observable) r12)
            if (r12 == 0) goto L_0x0048
            boolean r12 = r12.get()
            goto L_0x0049
        L_0x0048:
            r12 = 0
        L_0x0049:
            if (r15 == 0) goto L_0x0054
            if (r12 == 0) goto L_0x0050
            r25 = 256(0x100, double:1.265E-321)
            goto L_0x0052
        L_0x0050:
            r25 = 128(0x80, double:6.32E-322)
        L_0x0052:
            long r2 = r2 | r25
        L_0x0054:
            if (r12 == 0) goto L_0x0057
            goto L_0x005a
        L_0x0057:
            r12 = 8
            goto L_0x005b
        L_0x005a:
            r12 = 0
        L_0x005b:
            long r25 = r2 & r18
            int r13 = (r25 > r4 ? 1 : (r25 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x006c
            if (r0 == 0) goto L_0x006c
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r13 = r0.onAdd
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onDelete
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r14 = r0.onEdit
            androidx.recyclerview.widget.ItemTouchHelper r10 = r0.itemTouchHelper
            goto L_0x0070
        L_0x006c:
            r10 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x0070:
            long r28 = r2 & r8
            int r11 = (r28 > r4 ? 1 : (r28 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x0089
            if (r0 == 0) goto L_0x007b
            androidx.databinding.ObservableField<java.lang.String> r11 = r0.title
            goto L_0x007c
        L_0x007b:
            r11 = 0
        L_0x007c:
            r8 = 1
            r1.updateRegistration((int) r8, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x0089
            java.lang.Object r8 = r11.get()
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x008a
        L_0x0089:
            r8 = 0
        L_0x008a:
            r26 = 104(0x68, double:5.14E-322)
            long r30 = r2 & r26
            int r9 = (r30 > r4 ? 1 : (r30 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00a3
            if (r0 == 0) goto L_0x0097
            androidx.databinding.ObservableBoolean r9 = r0.open
            goto L_0x0098
        L_0x0097:
            r9 = 0
        L_0x0098:
            r11 = 3
            r1.updateRegistration((int) r11, (androidx.databinding.Observable) r9)
            if (r9 == 0) goto L_0x00a3
            boolean r9 = r9.get()
            goto L_0x00a4
        L_0x00a3:
            r9 = 0
        L_0x00a4:
            r20 = 112(0x70, double:5.53E-322)
            long r30 = r2 & r20
            int r11 = (r30 > r4 ? 1 : (r30 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00bd
            if (r0 == 0) goto L_0x00b1
            androidx.databinding.ObservableInt r0 = r0.typeRes
            goto L_0x00b2
        L_0x00b1:
            r0 = 0
        L_0x00b2:
            r11 = 4
            r1.updateRegistration((int) r11, (androidx.databinding.Observable) r0)
            if (r0 == 0) goto L_0x00bd
            int r0 = r0.get()
            goto L_0x00be
        L_0x00bd:
            r0 = 0
        L_0x00be:
            r32 = r7
            r7 = r6
            r6 = r13
            r13 = r12
            r12 = r8
            r8 = r32
            goto L_0x00d1
        L_0x00c7:
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
        L_0x00d1:
            long r18 = r2 & r18
            int r11 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00ec
            androidx.constraintlayout.widget.ConstraintLayout r11 = r1.clContent
            r4 = 0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r11, r14, r4)
            com.eternal.base.SlideRecyclerView r5 = r1.listChild
            com.eternal.framework.binding.viewadapter.recyclerview.ViewAdapter.setItemTouchHelper(r5, r10)
            android.widget.ImageView r5 = r1.mboundView9
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r5, r6, r4)
            android.widget.TextView r5 = r1.tvDel
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r5, r15, r4)
        L_0x00ec:
            r4 = 112(0x70, double:5.53E-322)
            long r4 = r4 & r2
            r10 = 0
            int r6 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r6 == 0) goto L_0x00fa
            android.widget.ImageView r4 = r1.ivType
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r4, r0)
        L_0x00fa:
            r4 = 104(0x68, double:5.14E-322)
            long r4 = r4 & r2
            int r0 = (r4 > r10 ? 1 : (r4 == r10 ? 0 : -1))
            if (r0 == 0) goto L_0x011a
            android.widget.ImageView r0 = r1.ivType
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r9)
            android.widget.LinearLayout r0 = r1.llHead
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r9)
            com.eternal.widget.ExpandableLayout r0 = r1.mboundView7
            com.eternal.widget.ExpandableLayout.setExpanded((com.eternal.widget.ExpandableLayout) r0, (boolean) r9)
            com.eternal.base.StatueSwitch r0 = r1.f127sb
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r0, r9)
            android.widget.TextView r0 = r1.tvTypeName
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r0, r9)
        L_0x011a:
            r4 = 64
            long r4 = r4 & r2
            r9 = 0
            int r0 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x0138
            com.eternal.base.SlideRecyclerView r0 = r1.listChild
            com.eternal.advance.CustomLayoutManagers r4 = com.eternal.advance.CustomLayoutManagers.linear()
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setLayoutManager(r0, r4)
            com.eternal.base.StatueSwitch r0 = r1.f127sb
            r4 = 0
            r15 = r4
            android.widget.CompoundButton$OnCheckedChangeListener r15 = (android.widget.CompoundButton.OnCheckedChangeListener) r15
            androidx.databinding.InverseBindingListener r5 = r1.sbandroidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r0, r15, r5)
            goto L_0x0139
        L_0x0138:
            r4 = 0
        L_0x0139:
            long r5 = r2 & r16
            r9 = 0
            int r0 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x014f
            com.eternal.base.SlideRecyclerView r6 = r1.listChild
            r9 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter r9 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter) r9
            r10 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ItemIds r10 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ItemIds) r10
            r11 = r4
            me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter$ViewHolderFactory r11 = (p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter.ViewHolderFactory) r11
            p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters.setAdapter(r6, r7, r8, r9, r10, r11)
        L_0x014f:
            r4 = 97
            long r4 = r4 & r2
            r6 = 0
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x015d
            android.view.View r0 = r1.mboundView10
            r0.setVisibility(r13)
        L_0x015d:
            r4 = 98
            long r2 = r2 & r4
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 == 0) goto L_0x0169
            android.widget.TextView r0 = r1.tvTypeName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x0169:
            return
        L_0x016a:
            r0 = move-exception
            monitor-exit(r33)     // Catch:{ all -> 0x016a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.advance.databinding.ItemNotificationV4BindingImpl.executeBindings():void");
    }
}
