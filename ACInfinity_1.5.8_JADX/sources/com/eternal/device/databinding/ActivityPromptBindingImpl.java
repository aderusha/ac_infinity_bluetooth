package com.eternal.device.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.PromptModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;

public class ActivityPromptBindingImpl extends ActivityPromptBinding {
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
        sparseIntArray.put(C1922R.C1925id.tv_number1, 4);
        sparseIntArray.put(C1922R.C1925id.tv_title1, 5);
        sparseIntArray.put(C1922R.C1925id.tv_content1, 6);
        sparseIntArray.put(C1922R.C1925id.tv_number2, 7);
        sparseIntArray.put(C1922R.C1925id.tv_title2, 8);
        sparseIntArray.put(C1922R.C1925id.tv_content2, 9);
        sparseIntArray.put(C1922R.C1925id.tv_number3, 10);
        sparseIntArray.put(C1922R.C1925id.tv_title3, 11);
        sparseIntArray.put(C1922R.C1925id.tv_content3, 12);
    }

    public ActivityPromptBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private ActivityPromptBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[2], objArr[1], objArr[6], objArr[9], objArr[12], objArr[4], objArr[7], objArr[10], objArr[5], objArr[8], objArr[11]);
        this.mDirtyFlags = -1;
        this.ibCreate.setTag((Object) null);
        this.ibLogin.setTag((Object) null);
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
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
        if (C1909BR.promptModel != i) {
            return false;
        }
        setPromptModel((PromptModel) obj);
        return true;
    }

    public void setPromptModel(PromptModel promptModel) {
        this.mPromptModel = promptModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(C1909BR.promptModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        BindingCommand<Void> bindingCommand;
        BindingCommand<Void> bindingCommand2;
        BindingCommand<Void> bindingCommand3;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PromptModel promptModel = this.mPromptModel;
        BindingCommand<Void> bindingCommand4 = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i == 0 || promptModel == null) {
            bindingCommand3 = null;
            bindingCommand2 = null;
            bindingCommand = null;
        } else {
            BindingCommand<Void> bindingCommand5 = promptModel.onCancel;
            BindingCommand<Void> bindingCommand6 = promptModel.onCreate;
            bindingCommand2 = promptModel.onBack;
            bindingCommand = promptModel.onLogin;
            BindingCommand<Void> bindingCommand7 = bindingCommand5;
            bindingCommand4 = bindingCommand6;
            bindingCommand3 = bindingCommand7;
        }
        if (i != 0) {
            ViewAdapter.onClickCommand(this.ibCreate, bindingCommand4, false);
            ViewAdapter.onClickCommand(this.ibLogin, bindingCommand, false);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand2);
            ToolbarAdapter.onNext(this.toolBar, bindingCommand3);
        }
    }
}
