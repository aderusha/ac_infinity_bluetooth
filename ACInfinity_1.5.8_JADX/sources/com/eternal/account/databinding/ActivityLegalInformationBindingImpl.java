package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0977BR;
import com.eternal.account.model.LegalInformationModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;

public class ActivityLegalInformationBindingImpl extends ActivityLegalInformationBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final RelativeLayout mboundView2;
    private final RelativeLayout mboundView3;
    private final RelativeLayout mboundView4;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public ActivityLegalInformationBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private ActivityLegalInformationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1]);
        this.mDirtyFlags = -1;
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[2];
        this.mboundView2 = relativeLayout;
        relativeLayout.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[3];
        this.mboundView3 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        RelativeLayout relativeLayout3 = objArr[4];
        this.mboundView4 = relativeLayout3;
        relativeLayout3.setTag((Object) null);
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
        if (C0977BR.legalModel != i) {
            return false;
        }
        setLegalModel((LegalInformationModel) obj);
        return true;
    }

    public void setLegalModel(LegalInformationModel legalInformationModel) {
        this.mLegalModel = legalInformationModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(C0977BR.legalModel);
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
        LegalInformationModel legalInformationModel = this.mLegalModel;
        BindingCommand<Void> bindingCommand4 = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i == 0 || legalInformationModel == null) {
            bindingCommand3 = null;
            bindingCommand2 = null;
            bindingCommand = null;
        } else {
            BindingCommand<Void> bindingCommand5 = legalInformationModel.onPrivacy;
            BindingCommand<Void> bindingCommand6 = legalInformationModel.onBack;
            bindingCommand2 = legalInformationModel.onTerms;
            bindingCommand = bindingCommand6;
            bindingCommand3 = bindingCommand5;
            bindingCommand4 = legalInformationModel.onAgreement;
        }
        if (i != 0) {
            ViewAdapter.onClickCommand(this.mboundView2, bindingCommand4, false);
            ViewAdapter.onClickCommand(this.mboundView3, bindingCommand2, false);
            ViewAdapter.onClickCommand(this.mboundView4, bindingCommand3, false);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand);
        }
    }
}
