package com.eternal.device.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1909BR;
import com.eternal.device.model.ItemModel;

public class ItemWifiBindingImpl extends ItemWifiBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView1;

    public ItemWifiBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    private ItemWifiBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1);
        this.mDirtyFlags = -1;
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        TextView textView = objArr[1];
        this.mboundView1 = textView;
        textView.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
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
        if (C1909BR.item != i) {
            return false;
        }
        setItem((ItemModel) obj);
        return true;
    }

    public void setItem(ItemModel itemModel) {
        this.mItem = itemModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(C1909BR.item);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeItemName((ObservableField) obj, i2);
    }

    private boolean onChangeItemName(ObservableField<String> observableField, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [java.lang.CharSequence] */
    /* JADX WARNING: type inference failed for: r4v2 */
    /* JADX WARNING: type inference failed for: r4v4 */
    /* JADX WARNING: type inference failed for: r10v5, types: [java.lang.String] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r13 = this;
            monitor-enter(r13)
            long r0 = r13.mDirtyFlags     // Catch:{ all -> 0x0049 }
            r2 = 0
            r13.mDirtyFlags = r2     // Catch:{ all -> 0x0049 }
            monitor-exit(r13)     // Catch:{ all -> 0x0049 }
            com.eternal.device.model.ItemModel r4 = r13.mItem
            r5 = 7
            long r5 = r5 & r0
            r7 = 0
            r8 = 6
            r10 = 0
            int r11 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r11 == 0) goto L_0x0036
            long r5 = r0 & r8
            int r12 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r12 == 0) goto L_0x0020
            if (r4 == 0) goto L_0x0020
            com.eternal.framework.binding.command.BindingCommand<java.lang.Void> r5 = r4.onItemClick
            goto L_0x0021
        L_0x0020:
            r5 = r10
        L_0x0021:
            if (r4 == 0) goto L_0x0026
            androidx.databinding.ObservableField<java.lang.String> r4 = r4.name
            goto L_0x0027
        L_0x0026:
            r4 = r10
        L_0x0027:
            r13.updateRegistration((int) r7, (androidx.databinding.Observable) r4)
            if (r4 == 0) goto L_0x0033
            java.lang.Object r4 = r4.get()
            r10 = r4
            java.lang.String r10 = (java.lang.String) r10
        L_0x0033:
            r4 = r10
            r10 = r5
            goto L_0x0037
        L_0x0036:
            r4 = r10
        L_0x0037:
            long r0 = r0 & r8
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x0041
            android.widget.RelativeLayout r0 = r13.mboundView0
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(r0, r10, r7)
        L_0x0041:
            if (r11 == 0) goto L_0x0048
            android.widget.TextView r0 = r13.mboundView1
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0048:
            return
        L_0x0049:
            r0 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x0049 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.device.databinding.ItemWifiBindingImpl.executeBindings():void");
    }
}
