package com.eternal.device.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.LocationPermissionModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;

public class ActivityLocationPermissionBindingImpl extends ActivityLocationPermissionBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.tv_title, 3);
        sparseIntArray.put(C1922R.C1925id.tv_content, 4);
    }

    public ActivityLocationPermissionBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private ActivityLocationPermissionBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[1], objArr[4], objArr[3]);
        this.mDirtyFlags = -1;
        this.ibNext.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (C1909BR.permissionModel != i) {
            return false;
        }
        setPermissionModel((LocationPermissionModel) obj);
        return true;
    }

    public void setPermissionModel(LocationPermissionModel locationPermissionModel) {
        this.mPermissionModel = locationPermissionModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(C1909BR.permissionModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        BindingCommand<Void> bindingCommand;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LocationPermissionModel locationPermissionModel = this.mPermissionModel;
        BindingCommand<Void> bindingCommand2 = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i == 0 || locationPermissionModel == null) {
            bindingCommand = null;
        } else {
            BindingCommand<Void> bindingCommand3 = locationPermissionModel.onBack;
            bindingCommand2 = locationPermissionModel.onSetting;
            bindingCommand = bindingCommand3;
        }
        if (i != 0) {
            ViewAdapter.onClickCommand(this.ibNext, bindingCommand2, false);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand);
        }
    }
}
