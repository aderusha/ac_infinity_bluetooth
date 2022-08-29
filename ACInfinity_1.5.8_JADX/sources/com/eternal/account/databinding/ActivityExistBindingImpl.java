package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.ExistModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;

public class ActivityExistBindingImpl extends ActivityExistBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C0997R.C1000id.tv_title, 3);
    }

    public ActivityExistBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private ActivityExistBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[0], objArr[1], objArr[3]);
        this.mDirtyFlags = -1;
        this.ibNext.setTag((Object) null);
        this.root.setTag((Object) null);
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
        if (C0977BR.existModel != i) {
            return false;
        }
        setExistModel((ExistModel) obj);
        return true;
    }

    public void setExistModel(ExistModel existModel) {
        this.mExistModel = existModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(C0977BR.existModel);
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
        ExistModel existModel = this.mExistModel;
        BindingCommand<Void> bindingCommand2 = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i == 0 || existModel == null) {
            bindingCommand = null;
        } else {
            BindingCommand<Void> bindingCommand3 = existModel.onBack;
            bindingCommand2 = existModel.onNext;
            bindingCommand = bindingCommand3;
        }
        if (i != 0) {
            ViewAdapter.onClickCommand(this.ibNext, bindingCommand2, false);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand);
        }
    }
}
