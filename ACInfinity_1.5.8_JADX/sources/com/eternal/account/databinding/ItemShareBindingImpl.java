package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.ShareItemModel;

public class ItemShareBindingImpl extends ItemShareBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final TextView mboundView5;
    private final ImageView mboundView6;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.tv_type_name, 7);
    }

    public ItemShareBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private ItemShareBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, objArr[2], objArr[1], objArr[3], objArr[4], objArr[7]);
        this.mDirtyFlags = -1;
        this.clContent.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        TextView textView = objArr[5];
        this.mboundView5 = textView;
        textView.setTag((Object) null);
        ImageView imageView = objArr[6];
        this.mboundView6 = imageView;
        imageView.setTag((Object) null);
        this.tvDel.setTag((Object) null);
        this.tvName.setTag((Object) null);
        this.tvType.setTag((Object) null);
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
        if (C0977BR.itemShare != i) {
            return false;
        }
        setItemShare((ShareItemModel) obj);
        return true;
    }

    public void setItemShare(ShareItemModel shareItemModel) {
        this.mItemShare = shareItemModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(C0977BR.itemShare);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeItemShareType((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangeItemShareIsShowShareIcon((MutableLiveData) obj, i2);
        }
        if (i == 2) {
            return onChangeItemShareIsShowPending((MutableLiveData) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeItemShareName((MutableLiveData) obj, i2);
    }

    private boolean onChangeItemShareType(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeItemShareIsShowShareIcon(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeItemShareIsShowPending(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeItemShareName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00a6  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r27 = this;
            r1 = r27
            monitor-enter(r27)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0114 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0114 }
            monitor-exit(r27)     // Catch:{ all -> 0x0114 }
            com.eternal.account.model.ShareItemModel r0 = r1.mItemShare
            r6 = 63
            long r6 = r6 & r2
            r10 = 50
            r12 = 49
            r14 = 52
            r16 = 48
            r8 = 0
            int r18 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x00ce
            long r6 = r2 & r16
            int r18 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0029
            if (r0 == 0) goto L_0x0029
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r6 = r0.onEdit
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onDelete
            goto L_0x002b
        L_0x0029:
            r6 = 0
            r7 = 0
        L_0x002b:
            long r18 = r2 & r12
            int r20 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x0043
            if (r0 == 0) goto L_0x0036
            androidx.lifecycle.MutableLiveData<java.lang.String> r9 = r0.type
            goto L_0x0037
        L_0x0036:
            r9 = 0
        L_0x0037:
            r1.updateLiveDataRegistration(r8, r9)
            if (r9 == 0) goto L_0x0043
            java.lang.Object r9 = r9.getValue()
            java.lang.String r9 = (java.lang.String) r9
            goto L_0x0044
        L_0x0043:
            r9 = 0
        L_0x0044:
            long r19 = r2 & r10
            r21 = 8
            int r22 = (r19 > r4 ? 1 : (r19 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x0075
            if (r0 == 0) goto L_0x0051
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r12 = r0.isShowShareIcon
            goto L_0x0052
        L_0x0051:
            r12 = 0
        L_0x0052:
            r13 = 1
            r1.updateLiveDataRegistration(r13, r12)
            if (r12 == 0) goto L_0x005f
            java.lang.Object r12 = r12.getValue()
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            goto L_0x0060
        L_0x005f:
            r12 = 0
        L_0x0060:
            boolean r12 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r12)
            if (r22 == 0) goto L_0x006f
            if (r12 == 0) goto L_0x006b
            r22 = 512(0x200, double:2.53E-321)
            goto L_0x006d
        L_0x006b:
            r22 = 256(0x100, double:1.265E-321)
        L_0x006d:
            long r2 = r2 | r22
        L_0x006f:
            if (r12 == 0) goto L_0x0072
            goto L_0x0075
        L_0x0072:
            r12 = 8
            goto L_0x0076
        L_0x0075:
            r12 = 0
        L_0x0076:
            long r22 = r2 & r14
            int r13 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00a6
            if (r0 == 0) goto L_0x0081
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r10 = r0.isShowPending
            goto L_0x0082
        L_0x0081:
            r10 = 0
        L_0x0082:
            r11 = 2
            r1.updateLiveDataRegistration(r11, r10)
            if (r10 == 0) goto L_0x008f
            java.lang.Object r10 = r10.getValue()
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            goto L_0x0090
        L_0x008f:
            r10 = 0
        L_0x0090:
            boolean r10 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r10)
            if (r13 == 0) goto L_0x009f
            if (r10 == 0) goto L_0x009b
            r24 = 128(0x80, double:6.32E-322)
            goto L_0x009d
        L_0x009b:
            r24 = 64
        L_0x009d:
            long r2 = r2 | r24
        L_0x009f:
            if (r10 == 0) goto L_0x00a3
            r21 = 0
        L_0x00a3:
            r10 = 56
            goto L_0x00aa
        L_0x00a6:
            r10 = 56
            r21 = 0
        L_0x00aa:
            long r24 = r2 & r10
            int r10 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r10 == 0) goto L_0x00c5
            if (r0 == 0) goto L_0x00b5
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.name
            goto L_0x00b6
        L_0x00b5:
            r0 = 0
        L_0x00b6:
            r10 = 3
            r1.updateLiveDataRegistration(r10, r0)
            if (r0 == 0) goto L_0x00c5
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r10 = r21
            goto L_0x00c8
        L_0x00c5:
            r10 = r21
            r0 = 0
        L_0x00c8:
            r26 = r9
            r9 = r6
            r6 = r26
            goto L_0x00d4
        L_0x00ce:
            r0 = 0
            r6 = 0
            r7 = 0
            r9 = 0
            r10 = 0
            r12 = 0
        L_0x00d4:
            long r16 = r2 & r16
            int r11 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00e4
            androidx.constraintlayout.widget.ConstraintLayout r11 = r1.clContent
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r11, r9, r8)
            android.widget.TextView r9 = r1.tvDel
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r9, r7, r8)
        L_0x00e4:
            long r7 = r2 & r14
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00ef
            android.widget.TextView r7 = r1.mboundView5
            r7.setVisibility(r10)
        L_0x00ef:
            r7 = 50
            long r7 = r7 & r2
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00fb
            android.widget.ImageView r7 = r1.mboundView6
            r7.setVisibility(r12)
        L_0x00fb:
            r7 = 56
            long r7 = r7 & r2
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0107
            android.widget.TextView r7 = r1.tvName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r0)
        L_0x0107:
            r7 = 49
            long r2 = r2 & r7
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0113
            android.widget.TextView r0 = r1.tvType
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x0113:
            return
        L_0x0114:
            r0 = move-exception
            monitor-exit(r27)     // Catch:{ all -> 0x0114 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ItemShareBindingImpl.executeBindings():void");
    }
}
