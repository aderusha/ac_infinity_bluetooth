package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0977BR;
import com.eternal.account.model.TimeZoneItemModel;
import com.eternal.account.model.TimeZoneModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.widget.guqiang.ProgressToolbarAdapter;
import p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;
import p018me.tatarka.bindingcollectionadapter2.LayoutManagers;

public class ActivityTimeZoneBindingImpl extends ActivityTimeZoneBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public ActivityTimeZoneBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ActivityTimeZoneBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[0], objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        this.root.setTag((Object) null);
        this.srvWithYou.setTag((Object) null);
        this.toolBar.setTag((Object) null);
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
        if (C0977BR.timeZoneModel != i) {
            return false;
        }
        setTimeZoneModel((TimeZoneModel) obj);
        return true;
    }

    public void setTimeZoneModel(TimeZoneModel timeZoneModel) {
        this.mTimeZoneModel = timeZoneModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(C0977BR.timeZoneModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeTimeZoneModelItems((ObservableList) obj, i2);
    }

    private boolean onChangeTimeZoneModelItems(ObservableList<TimeZoneItemModel> observableList, int i) {
        if (i != C0977BR._all) {
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
        ObservableList<TimeZoneItemModel> observableList;
        ItemBinding<TimeZoneItemModel> itemBinding;
        BindingCommand<Void> bindingCommand;
        ItemBinding<TimeZoneItemModel> itemBinding2;
        ObservableList<TimeZoneItemModel> observableList2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        TimeZoneModel timeZoneModel = this.mTimeZoneModel;
        int i = ((7 & j) > 0 ? 1 : ((7 & j) == 0 ? 0 : -1));
        if (i != 0) {
            bindingCommand = ((j & 6) == 0 || timeZoneModel == null) ? null : timeZoneModel.onBack;
            if (timeZoneModel != null) {
                itemBinding2 = timeZoneModel.itemBinding;
                observableList2 = timeZoneModel.items;
            } else {
                observableList2 = null;
                itemBinding2 = null;
            }
            updateRegistration(0, (ObservableList) observableList2);
            observableList = observableList2;
            itemBinding = itemBinding2;
        } else {
            bindingCommand = null;
            itemBinding = null;
            observableList = null;
        }
        if ((4 & j) != 0) {
            BindingRecyclerViewAdapters.setLayoutManager(this.srvWithYou, LayoutManagers.linear());
        }
        if (i != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.srvWithYou, itemBinding, observableList, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null);
        }
        if ((j & 6) != 0) {
            ProgressToolbarAdapter.onBack(this.toolBar, bindingCommand);
        }
    }
}
