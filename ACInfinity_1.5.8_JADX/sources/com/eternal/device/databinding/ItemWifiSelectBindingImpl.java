package com.eternal.device.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.Observable;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import com.eternal.device.C1909BR;
import com.eternal.device.model.ItemModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;

public class ItemWifiSelectBindingImpl extends ItemWifiSelectBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView1;

    public ItemWifiSelectBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    private ItemWifiSelectBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
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
        if (C1909BR.Item != i) {
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
        notifyPropertyChanged(C1909BR.Item);
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

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ItemModel itemModel = this.mItem;
        BindingCommand<Void> bindingCommand = null;
        int i = ((7 & j) > 0 ? 1 : ((7 & j) == 0 ? 0 : -1));
        if (i != 0) {
            ObservableField<String> observableField = itemModel != null ? itemModel.name : null;
            updateRegistration(0, (Observable) observableField);
            str = observableField != null ? observableField.get() : null;
            if (!((j & 6) == 0 || itemModel == null)) {
                bindingCommand = itemModel.onItemClick;
            }
        } else {
            str = null;
        }
        if ((j & 6) != 0) {
            ViewAdapter.onClickCommand(this.mboundView0, bindingCommand, false);
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
    }
}
