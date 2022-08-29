package com.eternal.about.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.about.AboutModel;
import com.eternal.about.C0968BR;
import com.eternal.about.C0969R;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;

public class ActivityAboutBindingImpl extends ActivityAboutBinding {
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
        sparseIntArray.put(C0969R.C0972id.tv_company_info, 5);
        sparseIntArray.put(C0969R.C0972id.tv_version, 6);
    }

    public ActivityAboutBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private ActivityAboutBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[2], objArr[4], objArr[1], objArr[5], objArr[6]);
        this.mDirtyFlags = -1;
        this.btPurchase.setTag((Object) null);
        this.btSupport.setTag((Object) null);
        this.btTeams.setTag((Object) null);
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
        if (C0968BR.model != i) {
            return false;
        }
        setModel((AboutModel) obj);
        return true;
    }

    public void setModel(AboutModel aboutModel) {
        this.mModel = aboutModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(C0968BR.model);
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
        AboutModel aboutModel = this.mModel;
        BindingCommand<Void> bindingCommand4 = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i == 0 || aboutModel == null) {
            bindingCommand3 = null;
            bindingCommand2 = null;
            bindingCommand = null;
        } else {
            BindingCommand<Void> bindingCommand5 = aboutModel.onTeams;
            BindingCommand<Void> bindingCommand6 = aboutModel.onBack;
            BindingCommand<Void> bindingCommand7 = aboutModel.onPurchase;
            bindingCommand = aboutModel.onSupport;
            BindingCommand<Void> bindingCommand8 = bindingCommand7;
            bindingCommand2 = bindingCommand6;
            bindingCommand3 = bindingCommand5;
            bindingCommand4 = bindingCommand8;
        }
        if (i != 0) {
            ViewAdapter.onClickCommand(this.btPurchase, bindingCommand4, false);
            ViewAdapter.onClickCommand(this.btSupport, bindingCommand, false);
            ViewAdapter.onClickCommand(this.btTeams, bindingCommand3, false);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand2);
        }
    }
}
