package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.model.SearchItemModel;

public class ItemSearchBindingImpl extends ItemSearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    public ItemSearchBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ItemSearchBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.tvDesc.setTag((Object) null);
        this.tvName.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (C0977BR.itemSearch != i) {
            return false;
        }
        setItemSearch((SearchItemModel) obj);
        return true;
    }

    public void setItemSearch(SearchItemModel searchItemModel) {
        this.mItemSearch = searchItemModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(C0977BR.itemSearch);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeItemSearchIsSelect((ObservableBoolean) obj, i2);
        }
        if (i == 1) {
            return onChangeItemSearchDesc((MutableLiveData) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeItemSearchName((MutableLiveData) obj, i2);
    }

    private boolean onChangeItemSearchIsSelect(ObservableBoolean observableBoolean, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeItemSearchDesc(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeItemSearchName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x007b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r22 = this;
            r1 = r22
            monitor-enter(r22)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x00c7 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x00c7 }
            monitor-exit(r22)     // Catch:{ all -> 0x00c7 }
            com.eternal.account.model.SearchItemModel r0 = r1.mItemSearch
            r6 = 31
            long r6 = r6 & r2
            r10 = 26
            r12 = 24
            r14 = 25
            r8 = 0
            int r18 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0090
            long r6 = r2 & r14
            int r18 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x004c
            if (r0 == 0) goto L_0x0025
            androidx.databinding.ObservableBoolean r6 = r0.isSelect
            goto L_0x0026
        L_0x0025:
            r6 = 0
        L_0x0026:
            r1.updateRegistration((int) r8, (androidx.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0030
            boolean r6 = r6.get()
            goto L_0x0031
        L_0x0030:
            r6 = 0
        L_0x0031:
            if (r18 == 0) goto L_0x003c
            if (r6 == 0) goto L_0x0038
            r18 = 64
            goto L_0x003a
        L_0x0038:
            r18 = 32
        L_0x003a:
            long r2 = r2 | r18
        L_0x003c:
            if (r6 == 0) goto L_0x0043
            androidx.constraintlayout.widget.ConstraintLayout r6 = r1.mboundView0
            int r7 = com.eternal.account.C0997R.C0998color.color_4b4b4b
            goto L_0x0047
        L_0x0043:
            androidx.constraintlayout.widget.ConstraintLayout r6 = r1.mboundView0
            int r7 = com.eternal.account.C0997R.C0998color.row
        L_0x0047:
            int r6 = getColorFromResource(r6, r7)
            goto L_0x004d
        L_0x004c:
            r6 = 0
        L_0x004d:
            long r18 = r2 & r10
            int r7 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0066
            if (r0 == 0) goto L_0x0058
            androidx.lifecycle.MutableLiveData<java.lang.String> r7 = r0.desc
            goto L_0x0059
        L_0x0058:
            r7 = 0
        L_0x0059:
            r9 = 1
            r1.updateLiveDataRegistration(r9, r7)
            if (r7 == 0) goto L_0x0066
            java.lang.Object r7 = r7.getValue()
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x0067
        L_0x0066:
            r7 = 0
        L_0x0067:
            long r19 = r2 & r12
            int r9 = (r19 > r4 ? 1 : (r19 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0072
            if (r0 == 0) goto L_0x0072
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r9 = r0.onItem
            goto L_0x0073
        L_0x0072:
            r9 = 0
        L_0x0073:
            r16 = 28
            long r19 = r2 & r16
            int r21 = (r19 > r4 ? 1 : (r19 == r4 ? 0 : -1))
            if (r21 == 0) goto L_0x008e
            if (r0 == 0) goto L_0x0080
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.name
            goto L_0x0081
        L_0x0080:
            r0 = 0
        L_0x0081:
            r10 = 2
            r1.updateLiveDataRegistration(r10, r0)
            if (r0 == 0) goto L_0x008e
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0094
        L_0x008e:
            r0 = 0
            goto L_0x0094
        L_0x0090:
            r0 = 0
            r6 = 0
            r7 = 0
            r9 = 0
        L_0x0094:
            long r10 = r2 & r14
            int r14 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x00a3
            androidx.constraintlayout.widget.ConstraintLayout r10 = r1.mboundView0
            android.graphics.drawable.ColorDrawable r6 = androidx.databinding.adapters.Converters.convertColorToDrawable(r6)
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(r10, r6)
        L_0x00a3:
            long r10 = r2 & r12
            int r6 = (r10 > r4 ? 1 : (r10 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x00ae
            androidx.constraintlayout.widget.ConstraintLayout r6 = r1.mboundView0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r6, r9, r8)
        L_0x00ae:
            r8 = 26
            long r8 = r8 & r2
            int r6 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x00ba
            android.widget.TextView r6 = r1.tvDesc
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r7)
        L_0x00ba:
            r6 = 28
            long r2 = r2 & r6
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x00c6
            android.widget.TextView r2 = r1.tvName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r2, r0)
        L_0x00c6:
            return
        L_0x00c7:
            r0 = move-exception
            monitor-exit(r22)     // Catch:{ all -> 0x00c7 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ItemSearchBindingImpl.executeBindings():void");
    }
}
