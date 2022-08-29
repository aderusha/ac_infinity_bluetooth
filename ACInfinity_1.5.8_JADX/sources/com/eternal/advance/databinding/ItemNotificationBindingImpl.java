package com.eternal.advance.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.databinding.ViewDataBinding;
import com.eternal.advance.C1200BR;
import com.eternal.advance.model.ItemModel;
import com.eternal.widget.ExpandableLayout;

public class ItemNotificationBindingImpl extends ItemNotificationBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final View mboundView10;
    private final ExpandableLayout mboundView7;
    private final TextView mboundView9;
    private InverseBindingListener sbandroidCheckedAttrChanged;

    public ItemNotificationBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    private ItemNotificationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, objArr[2], objArr[4], objArr[3], objArr[6], objArr[1], objArr[8], objArr[5]);
        this.sbandroidCheckedAttrChanged = new InverseBindingListener() {
            public void onChange() {
                boolean isChecked = ItemNotificationBindingImpl.this.f126sb.isChecked();
                ItemModel itemModel = ItemNotificationBindingImpl.this.mItem;
                boolean z = true;
                if (itemModel != null) {
                    ObservableBoolean observableBoolean = itemModel.open;
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
        TextView textView = objArr[9];
        this.mboundView9 = textView;
        textView.setTag((Object) null);
        this.f126sb.setTag("switch");
        this.tvDel.setTag((Object) null);
        this.tvTypeDetail.setTag((Object) null);
        this.tvTypeName.setTag((Object) null);
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
        if (C1200BR.item != i) {
            return false;
        }
        setItem((ItemModel) obj);
        return true;
    }

    public void setItem(ItemModel itemModel) {
        this.mItem = itemModel;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(C1200BR.item);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeItemTitle((ObservableField) obj, i2);
        }
        if (i == 1) {
            return onChangeItemInfo((ObservableField) obj, i2);
        }
        if (i == 2) {
            return onChangeItemAdvActive((ObservableBoolean) obj, i2);
        }
        if (i == 3) {
            return onChangeItemDesc((ObservableField) obj, i2);
        }
        if (i == 4) {
            return onChangeItemTypeRes((ObservableInt) obj, i2);
        }
        if (i != 5) {
            return false;
        }
        return onChangeItemOpen((ObservableBoolean) obj, i2);
    }

    private boolean onChangeItemTitle(ObservableField<String> observableField, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeItemInfo(ObservableField<String> observableField, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeItemAdvActive(ObservableBoolean observableBoolean, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeItemDesc(ObservableField<String> observableField, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeItemTypeRes(ObservableInt observableInt, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeItemOpen(ObservableBoolean observableBoolean, int i) {
        if (i != C1200BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r33 = this;
            r1 = r33
            monitor-enter(r33)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0163 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0163 }
            monitor-exit(r33)     // Catch:{ all -> 0x0163 }
            com.eternal.advance.model.ItemModel r0 = r1.mItem
            r6 = 255(0xff, double:1.26E-321)
            long r6 = r6 & r2
            r8 = 200(0xc8, double:9.9E-322)
            r10 = 196(0xc4, double:9.7E-322)
            r12 = 194(0xc2, double:9.6E-322)
            r16 = 208(0xd0, double:1.03E-321)
            r18 = 193(0xc1, double:9.54E-322)
            r20 = 192(0xc0, double:9.5E-322)
            r14 = 0
            int r24 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x00d9
            long r6 = r2 & r18
            int r24 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r24 == 0) goto L_0x0038
            if (r0 == 0) goto L_0x002b
            androidx.databinding.ObservableField<java.lang.String> r6 = r0.title
            goto L_0x002c
        L_0x002b:
            r6 = 0
        L_0x002c:
            r1.updateRegistration((int) r14, (androidx.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0038
            java.lang.Object r6 = r6.get()
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0039
        L_0x0038:
            r6 = 0
        L_0x0039:
            long r24 = r2 & r20
            int r7 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0046
            if (r0 == 0) goto L_0x0046
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r7 = r0.onDelete
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r15 = r0.onEdit
            goto L_0x0048
        L_0x0046:
            r7 = 0
            r15 = 0
        L_0x0048:
            long r25 = r2 & r12
            int r27 = (r25 > r4 ? 1 : (r25 == r4 ? 0 : -1))
            if (r27 == 0) goto L_0x0061
            if (r0 == 0) goto L_0x0053
            androidx.databinding.ObservableField<java.lang.String> r12 = r0.info
            goto L_0x0054
        L_0x0053:
            r12 = 0
        L_0x0054:
            r13 = 1
            r1.updateRegistration((int) r13, (androidx.databinding.Observable) r12)
            if (r12 == 0) goto L_0x0061
            java.lang.Object r12 = r12.get()
            java.lang.String r12 = (java.lang.String) r12
            goto L_0x0062
        L_0x0061:
            r12 = 0
        L_0x0062:
            long r27 = r2 & r10
            int r13 = (r27 > r4 ? 1 : (r27 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x008b
            if (r0 == 0) goto L_0x006d
            androidx.databinding.ObservableBoolean r10 = r0.advActive
            goto L_0x006e
        L_0x006d:
            r10 = 0
        L_0x006e:
            r11 = 2
            r1.updateRegistration((int) r11, (androidx.databinding.Observable) r10)
            if (r10 == 0) goto L_0x0079
            boolean r10 = r10.get()
            goto L_0x007a
        L_0x0079:
            r10 = 0
        L_0x007a:
            if (r13 == 0) goto L_0x0085
            if (r10 == 0) goto L_0x0081
            r29 = 512(0x200, double:2.53E-321)
            goto L_0x0083
        L_0x0081:
            r29 = 256(0x100, double:1.265E-321)
        L_0x0083:
            long r2 = r2 | r29
        L_0x0085:
            if (r10 == 0) goto L_0x0088
            goto L_0x008b
        L_0x0088:
            r10 = 8
            goto L_0x008c
        L_0x008b:
            r10 = 0
        L_0x008c:
            long r29 = r2 & r8
            int r11 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r11 == 0) goto L_0x00a5
            if (r0 == 0) goto L_0x0097
            androidx.databinding.ObservableField<java.lang.String> r11 = r0.desc
            goto L_0x0098
        L_0x0097:
            r11 = 0
        L_0x0098:
            r13 = 3
            r1.updateRegistration((int) r13, (androidx.databinding.Observable) r11)
            if (r11 == 0) goto L_0x00a5
            java.lang.Object r11 = r11.get()
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x00a6
        L_0x00a5:
            r11 = 0
        L_0x00a6:
            long r29 = r2 & r16
            int r13 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00bd
            if (r0 == 0) goto L_0x00b1
            androidx.databinding.ObservableInt r13 = r0.typeRes
            goto L_0x00b2
        L_0x00b1:
            r13 = 0
        L_0x00b2:
            r8 = 4
            r1.updateRegistration((int) r8, (androidx.databinding.Observable) r13)
            if (r13 == 0) goto L_0x00bd
            int r8 = r13.get()
            goto L_0x00be
        L_0x00bd:
            r8 = 0
        L_0x00be:
            r22 = 224(0xe0, double:1.107E-321)
            long r31 = r2 & r22
            int r9 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00d7
            if (r0 == 0) goto L_0x00cb
            androidx.databinding.ObservableBoolean r0 = r0.open
            goto L_0x00cc
        L_0x00cb:
            r0 = 0
        L_0x00cc:
            r9 = 5
            r1.updateRegistration((int) r9, (androidx.databinding.Observable) r0)
            if (r0 == 0) goto L_0x00d7
            boolean r0 = r0.get()
            goto L_0x00e1
        L_0x00d7:
            r0 = 0
            goto L_0x00e1
        L_0x00d9:
            r0 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r15 = 0
        L_0x00e1:
            long r20 = r2 & r20
            int r9 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x00f1
            androidx.constraintlayout.widget.ConstraintLayout r9 = r1.clContent
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r9, r15, r14)
            android.widget.TextView r9 = r1.tvDel
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r9, r7, r14)
        L_0x00f1:
            long r13 = r2 & r16
            int r7 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x00fc
            android.widget.ImageView r7 = r1.ivType
            com.eternal.framework.binding.viewadapter.image.ViewAdapter.setRes(r7, r8)
        L_0x00fc:
            r7 = 224(0xe0, double:1.107E-321)
            long r7 = r7 & r2
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0121
            android.widget.ImageView r7 = r1.ivType
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r7, r0)
            android.widget.LinearLayout r7 = r1.llHead
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r7, r0)
            com.eternal.widget.ExpandableLayout r7 = r1.mboundView7
            com.eternal.widget.ExpandableLayout.setExpanded((com.eternal.widget.ExpandableLayout) r7, (boolean) r0)
            com.eternal.base.StatueSwitch r7 = r1.f126sb
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setChecked(r7, r0)
            android.widget.TextView r7 = r1.tvTypeDetail
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r7, r0)
            android.widget.TextView r7 = r1.tvTypeName
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onSelected(r7, r0)
        L_0x0121:
            r7 = 196(0xc4, double:9.7E-322)
            long r7 = r7 & r2
            int r0 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x012d
            android.view.View r0 = r1.mboundView10
            r0.setVisibility(r10)
        L_0x012d:
            r7 = 200(0xc8, double:9.9E-322)
            long r7 = r7 & r2
            int r0 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0139
            android.widget.TextView r0 = r1.mboundView9
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r11)
        L_0x0139:
            r7 = 128(0x80, double:6.32E-322)
            long r7 = r7 & r2
            int r0 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x014b
            com.eternal.base.StatueSwitch r0 = r1.f126sb
            r7 = 0
            r15 = r7
            android.widget.CompoundButton$OnCheckedChangeListener r15 = (android.widget.CompoundButton.OnCheckedChangeListener) r15
            androidx.databinding.InverseBindingListener r7 = r1.sbandroidCheckedAttrChanged
            androidx.databinding.adapters.CompoundButtonBindingAdapter.setListeners(r0, r15, r7)
        L_0x014b:
            r7 = 194(0xc2, double:9.6E-322)
            long r7 = r7 & r2
            int r0 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0157
            android.widget.TextView r0 = r1.tvTypeDetail
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
        L_0x0157:
            long r2 = r2 & r18
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0162
            android.widget.TextView r0 = r1.tvTypeName
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r6)
        L_0x0162:
            return
        L_0x0163:
            r0 = move-exception
            monitor-exit(r33)     // Catch:{ all -> 0x0163 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.advance.databinding.ItemNotificationBindingImpl.executeBindings():void");
    }
}
