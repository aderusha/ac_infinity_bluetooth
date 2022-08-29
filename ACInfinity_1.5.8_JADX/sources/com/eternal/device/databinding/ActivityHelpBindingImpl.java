package com.eternal.device.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.HelpModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;

public class ActivityHelpBindingImpl extends ActivityHelpBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.toolBar, 2);
        sparseIntArray.put(C1922R.C1925id.tv_number1, 3);
        sparseIntArray.put(C1922R.C1925id.tv_title1, 4);
        sparseIntArray.put(C1922R.C1925id.tv_content1, 5);
        sparseIntArray.put(C1922R.C1925id.tv_number2, 6);
        sparseIntArray.put(C1922R.C1925id.tv_title2, 7);
        sparseIntArray.put(C1922R.C1925id.tv_content2, 8);
        sparseIntArray.put(C1922R.C1925id.tv_number3, 9);
        sparseIntArray.put(C1922R.C1925id.tv_title3, 10);
        sparseIntArray.put(C1922R.C1925id.tv_content3, 11);
        sparseIntArray.put(C1922R.C1925id.tv_number4, 12);
        sparseIntArray.put(C1922R.C1925id.tv_title4, 13);
        sparseIntArray.put(C1922R.C1925id.tv_content4, 14);
    }

    public ActivityHelpBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 15, sIncludes, sViewsWithIds));
    }

    private ActivityHelpBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[2], objArr[5], objArr[8], objArr[11], objArr[14], objArr[3], objArr[6], objArr[9], objArr[12], objArr[4], objArr[7], objArr[10], objArr[13]);
        this.mDirtyFlags = -1;
        this.ibNext.setTag((Object) null);
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
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
        if (C1909BR.helpModel != i) {
            return false;
        }
        setHelpModel((HelpModel) obj);
        return true;
    }

    public void setHelpModel(HelpModel helpModel) {
        this.mHelpModel = helpModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(C1909BR.helpModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        HelpModel helpModel = this.mHelpModel;
        BindingCommand<Void> bindingCommand = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (!(i == 0 || helpModel == null)) {
            bindingCommand = helpModel.onBack;
        }
        if (i != 0) {
            ViewAdapter.onClickCommand(this.ibNext, bindingCommand, false);
        }
    }
}
