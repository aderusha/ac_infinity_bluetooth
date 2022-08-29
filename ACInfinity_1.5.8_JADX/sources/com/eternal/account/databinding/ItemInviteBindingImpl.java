package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.InviteItemModel;

public class ItemInviteBindingImpl extends ItemInviteBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView4;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.tv_type_name, 5);
    }

    public ItemInviteBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private ItemInviteBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, objArr[1], objArr[2], objArr[3], objArr[5]);
        this.mDirtyFlags = -1;
        this.clContent.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ImageView imageView = objArr[4];
        this.mboundView4 = imageView;
        imageView.setTag((Object) null);
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
        if (C0977BR.itemInvite != i) {
            return false;
        }
        setItemInvite((InviteItemModel) obj);
        return true;
    }

    public void setItemInvite(InviteItemModel inviteItemModel) {
        this.mItemInvite = inviteItemModel;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(C0977BR.itemInvite);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeItemInviteType((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangeItemInviteName((MutableLiveData) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeItemInviteIsSelect((ObservableBoolean) obj, i2);
    }

    private boolean onChangeItemInviteType(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeItemInviteName(MutableLiveData<String> mutableLiveData, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeItemInviteIsSelect(ObservableBoolean observableBoolean, int i) {
        if (i != C0977BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0092 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r21 = this;
            r1 = r21
            monitor-enter(r21)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x00ce }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x00ce }
            monitor-exit(r21)     // Catch:{ all -> 0x00ce }
            com.eternal.account.model.InviteItemModel r0 = r1.mItemInvite
            r6 = 31
            long r6 = r6 & r2
            r8 = 26
            r10 = 1
            r11 = 28
            r13 = 25
            r15 = 24
            r17 = 0
            int r18 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x009a
            long r6 = r2 & r13
            r13 = 0
            int r14 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x0038
            if (r0 == 0) goto L_0x002a
            androidx.lifecycle.MutableLiveData<java.lang.String> r6 = r0.type
            goto L_0x002c
        L_0x002a:
            r6 = r17
        L_0x002c:
            r1.updateLiveDataRegistration(r13, r6)
            if (r6 == 0) goto L_0x0038
            java.lang.Object r6 = r6.getValue()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x003a
        L_0x0038:
            r6 = r17
        L_0x003a:
            long r19 = r2 & r8
            int r7 = (r19 > r4 ? 1 : (r19 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0053
            if (r0 == 0) goto L_0x0045
            androidx.lifecycle.MutableLiveData<java.lang.String> r7 = r0.name
            goto L_0x0047
        L_0x0045:
            r7 = r17
        L_0x0047:
            r1.updateLiveDataRegistration(r10, r7)
            if (r7 == 0) goto L_0x0053
            java.lang.Object r7 = r7.getValue()
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x0055
        L_0x0053:
            r7 = r17
        L_0x0055:
            long r19 = r2 & r11
            int r14 = (r19 > r4 ? 1 : (r19 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x008a
            if (r0 == 0) goto L_0x0060
            androidx.databinding.ObservableBoolean r13 = r0.isSelect
            goto L_0x0062
        L_0x0060:
            r13 = r17
        L_0x0062:
            r8 = 2
            r1.updateRegistration((int) r8, (androidx.databinding.Observable) r13)
            if (r13 == 0) goto L_0x006d
            boolean r13 = r13.get()
            goto L_0x006e
        L_0x006d:
            r13 = 0
        L_0x006e:
            if (r14 == 0) goto L_0x0078
            if (r13 == 0) goto L_0x0075
            r8 = 64
            goto L_0x0077
        L_0x0075:
            r8 = 32
        L_0x0077:
            long r2 = r2 | r8
        L_0x0078:
            android.widget.ImageView r8 = r1.mboundView4
            android.content.Context r8 = r8.getContext()
            if (r13 == 0) goto L_0x0083
            int r9 = com.eternal.account.C0997R.C0999drawable.invite_checked
            goto L_0x0085
        L_0x0083:
            int r9 = com.eternal.account.C0997R.C0999drawable.invite_uncheck
        L_0x0085:
            android.graphics.drawable.Drawable r8 = androidx.appcompat.content.res.AppCompatResources.getDrawable(r8, r9)
            goto L_0x008c
        L_0x008a:
            r8 = r17
        L_0x008c:
            long r13 = r2 & r15
            int r9 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0097
            if (r0 == 0) goto L_0x0097
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r0 = r0.onSelect
            goto L_0x009f
        L_0x0097:
            r0 = r17
            goto L_0x009f
        L_0x009a:
            r0 = r17
            r6 = r0
            r7 = r6
            r8 = r7
        L_0x009f:
            long r13 = r2 & r15
            int r9 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00aa
            androidx.constraintlayout.widget.ConstraintLayout r9 = r1.clContent
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r9, r0, r10)
        L_0x00aa:
            long r9 = r2 & r11
            int r0 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00b5
            android.widget.ImageView r0 = r1.mboundView4
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r8)
        L_0x00b5:
            r8 = 26
            long r8 = r8 & r2
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00c1
            android.widget.TextView r0 = r1.tvName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r7)
        L_0x00c1:
            r7 = 25
            long r2 = r2 & r7
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x00cd
            android.widget.TextView r0 = r1.tvType
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x00cd:
            return
        L_0x00ce:
            r0 = move-exception
            monitor-exit(r21)     // Catch:{ all -> 0x00ce }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.databinding.ItemInviteBindingImpl.executeBindings():void");
    }
}
