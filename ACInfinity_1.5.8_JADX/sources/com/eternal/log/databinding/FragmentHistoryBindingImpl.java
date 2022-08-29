package com.eternal.log.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.log.C2267BR;
import com.eternal.log.C2303R;
import com.eternal.log.model.LogModel;

public class FragmentHistoryBindingImpl extends FragmentHistoryBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2303R.C2306id.rc_content, 2);
        sparseIntArray.put(C2303R.C2306id.ume_filter, 3);
        sparseIntArray.put(C2303R.C2306id.ll_row_1, 4);
        sparseIntArray.put(C2303R.C2306id.ll_row_2, 5);
    }

    public FragmentHistoryBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private FragmentHistoryBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[4], objArr[5], objArr[2], objArr[3]);
        this.mDirtyFlags = -1;
        this.btnDelete.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
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
        if (C2267BR.model != i) {
            return false;
        }
        setModel((LogModel) obj);
        return true;
    }

    public void setModel(LogModel logModel) {
        this.mModel = logModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(C2267BR.model);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LogModel logModel = this.mModel;
        BindingCommand<Void> bindingCommand = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (!(i == 0 || logModel == null)) {
            bindingCommand = logModel.onDelete;
        }
        if (i != 0) {
            ViewAdapter.onClickCommand(this.btnDelete, bindingCommand, false);
        }
    }
}
