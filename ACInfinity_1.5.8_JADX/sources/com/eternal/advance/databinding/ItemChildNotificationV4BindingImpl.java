package com.eternal.advance.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import com.eternal.advance.C1200BR;
import com.eternal.advance.model.ItemChildModelV4;

public class ItemChildNotificationV4BindingImpl extends ItemChildNotificationV4Binding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView4;

    public ItemChildNotificationV4BindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private ItemChildNotificationV4BindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, objArr[2], objArr[1], objArr[3]);
        this.mDirtyFlags = -1;
        this.llContent.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        TextView textView = objArr[4];
        this.mboundView4 = textView;
        textView.setTag((Object) null);
        this.tvDel.setTag((Object) null);
        this.tvTypeDetail.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (C1200BR.childV4 != i) {
            return false;
        }
        setChildV4((ItemChildModelV4) obj);
        return true;
    }

    public void setChildV4(ItemChildModelV4 itemChildModelV4) {
        this.mChildV4 = itemChildModelV4;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(C1200BR.childV4);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeChildV4Desc((ObservableField) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeChildV4Info((ObservableField) obj, i2);
    }

    private boolean onChangeChildV4Desc(ObservableField<String> observableField, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeChildV4Info(ObservableField<String> observableField, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0048  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r21 = this;
            r1 = r21
            monitor-enter(r21)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0091 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0091 }
            monitor-exit(r21)     // Catch:{ all -> 0x0091 }
            com.eternal.advance.model.ItemChildModelV4 r0 = r1.mChildV4
            r6 = 15
            long r6 = r6 & r2
            r8 = 14
            r10 = 13
            r12 = 12
            r14 = 0
            int r16 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x0065
            long r6 = r2 & r12
            int r16 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x0027
            if (r0 == 0) goto L_0x0027
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r6 = r0.onDelete
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onEdit
            goto L_0x0029
        L_0x0027:
            r6 = 0
            r7 = 0
        L_0x0029:
            long r16 = r2 & r10
            int r18 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0041
            if (r0 == 0) goto L_0x0034
            androidx.databinding.ObservableField<java.lang.String> r15 = r0.desc
            goto L_0x0035
        L_0x0034:
            r15 = 0
        L_0x0035:
            r1.updateRegistration((int) r14, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x0041
            java.lang.Object r15 = r15.get()
            java.lang.String r15 = (java.lang.String) r15
            goto L_0x0042
        L_0x0041:
            r15 = 0
        L_0x0042:
            long r17 = r2 & r8
            int r19 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r19 == 0) goto L_0x0061
            if (r0 == 0) goto L_0x004d
            androidx.databinding.ObservableField<java.lang.String> r0 = r0.info
            goto L_0x004e
        L_0x004d:
            r0 = 0
        L_0x004e:
            r8 = 1
            r1.updateRegistration((int) r8, (androidx.databinding.Observable) r0)
            if (r0 == 0) goto L_0x0061
            java.lang.Object r0 = r0.get()
            java.lang.String r0 = (java.lang.String) r0
            r20 = r7
            r7 = r0
            r0 = r15
            r15 = r20
            goto L_0x0069
        L_0x0061:
            r0 = r15
            r15 = r7
            r7 = 0
            goto L_0x0069
        L_0x0065:
            r0 = 0
            r6 = 0
            r7 = 0
            r15 = 0
        L_0x0069:
            long r8 = r2 & r12
            int r12 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x0079
            android.widget.LinearLayout r8 = r1.llContent
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r8, r15, r14)
            android.widget.TextView r8 = r1.tvDel
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r8, r6, r14)
        L_0x0079:
            long r8 = r2 & r10
            int r6 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r6 == 0) goto L_0x0084
            android.widget.TextView r6 = r1.mboundView4
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r6, r0)
        L_0x0084:
            r8 = 14
            long r2 = r2 & r8
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0090
            android.widget.TextView r0 = r1.tvTypeDetail
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x0090:
            return
        L_0x0091:
            r0 = move-exception
            monitor-exit(r21)     // Catch:{ all -> 0x0091 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.advance.databinding.ItemChildNotificationV4BindingImpl.executeBindings():void");
    }
}
