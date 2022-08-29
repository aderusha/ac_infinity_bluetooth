package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.TimeZoneItemModel;

public class ItemTimeZoneBindingImpl extends ItemTimeZoneBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.tv_type_name, 5);
        sparseIntArray.put(C0997R.C1000id.iv_arrow, 6);
    }

    public ItemTimeZoneBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private ItemTimeZoneBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, objArr[1], objArr[6], objArr[2], objArr[3], objArr[5]);
        this.mDirtyFlags = -1;
        this.clContent.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        TextView textView = objArr[4];
        this.mboundView4 = textView;
        textView.setTag((Object) null);
        this.tvName.setTag((Object) null);
        this.tvType.setTag((Object) null);
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
        if (C0977BR.itemTimeZone != i) {
            return false;
        }
        setItemTimeZone((TimeZoneItemModel) obj);
        return true;
    }

    public void setItemTimeZone(TimeZoneItemModel timeZoneItemModel) {
        this.mItemTimeZone = timeZoneItemModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(C0977BR.itemTimeZone);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeItemTimeZoneName((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangeItemTimeZoneType((MutableLiveData) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeItemTimeZoneTimeZone((MutableLiveData) obj, i2);
    }

    private boolean onChangeItemTimeZoneName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeItemTimeZoneType(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeItemTimeZoneTimeZone(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0077 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r22 = this;
            r1 = r22
            monitor-enter(r22)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x00b1 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x00b1 }
            monitor-exit(r22)     // Catch:{ all -> 0x00b1 }
            com.eternal.account.model.TimeZoneItemModel r0 = r1.mItemTimeZone
            r6 = 31
            long r6 = r6 & r2
            r8 = 26
            r10 = 0
            r11 = 28
            r13 = 25
            r15 = 24
            r17 = 0
            int r18 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x007f
            long r6 = r2 & r13
            int r18 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0037
            if (r0 == 0) goto L_0x0029
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.name
            goto L_0x002b
        L_0x0029:
            r6 = r17
        L_0x002b:
            r1.updateLiveDataRegistration(r10, r6)
            if (r6 == 0) goto L_0x0037
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0039
        L_0x0037:
            r6 = r17
        L_0x0039:
            long r18 = r2 & r8
            int r7 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0053
            if (r0 == 0) goto L_0x0044
            androidx.lifecycle.MutableLiveData<java.lang.String> r7 = r0.type
            goto L_0x0046
        L_0x0044:
            r7 = r17
        L_0x0046:
            r8 = 1
            r1.updateLiveDataRegistration(r8, r7)
            if (r7 == 0) goto L_0x0053
            java.lang.Object r7 = r7.getValue()
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x0055
        L_0x0053:
            r7 = r17
        L_0x0055:
            long r8 = r2 & r11
            int r20 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x006f
            if (r0 == 0) goto L_0x0060
            androidx.lifecycle.MutableLiveData<java.lang.String> r8 = r0.timeZone
            goto L_0x0062
        L_0x0060:
            r8 = r17
        L_0x0062:
            r9 = 2
            r1.updateLiveDataRegistration(r9, r8)
            if (r8 == 0) goto L_0x006f
            java.lang.Object r8 = r8.getValue()
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x0071
        L_0x006f:
            r8 = r17
        L_0x0071:
            long r20 = r2 & r15
            int r9 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x007c
            if (r0 == 0) goto L_0x007c
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r0 = r0.onEdit
            goto L_0x0084
        L_0x007c:
            r0 = r17
            goto L_0x0084
        L_0x007f:
            r0 = r17
            r6 = r0
            r7 = r6
            r8 = r7
        L_0x0084:
            long r15 = r15 & r2
            int r9 = (r15 > r4 ? 1 : (r15 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x008e
            androidx.constraintlayout.widget.ConstraintLayout r9 = r1.clContent
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r9, r0, r10)
        L_0x008e:
            long r9 = r2 & r11
            int r0 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0099
            android.widget.TextView r0 = r1.mboundView4
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
        L_0x0099:
            long r8 = r2 & r13
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00a4
            android.widget.TextView r0 = r1.tvName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x00a4:
            r8 = 26
            long r2 = r2 & r8
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00b0
            android.widget.TextView r0 = r1.tvType
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x00b0:
            return
        L_0x00b1:
            r0 = move-exception
            monitor-exit(r22)     // Catch:{ all -> 0x00b1 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ItemTimeZoneBindingImpl.executeBindings():void");
    }
}
