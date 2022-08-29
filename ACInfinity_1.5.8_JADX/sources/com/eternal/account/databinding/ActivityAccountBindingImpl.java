package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.model.AccountModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;

public class ActivityAccountBindingImpl extends ActivityAccountBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final RelativeLayout mboundView2;
    private final View mboundView3;
    private final RelativeLayout mboundView4;
    private final RelativeLayout mboundView5;
    private final RelativeLayout mboundView6;
    private final RelativeLayout mboundView7;

    public ActivityAccountBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private ActivityAccountBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[1]);
        this.mDirtyFlags = -1;
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        RelativeLayout relativeLayout = objArr[2];
        this.mboundView2 = relativeLayout;
        relativeLayout.setTag((Object) null);
        View view2 = objArr[3];
        this.mboundView3 = view2;
        view2.setTag((Object) null);
        RelativeLayout relativeLayout2 = objArr[4];
        this.mboundView4 = relativeLayout2;
        relativeLayout2.setTag((Object) null);
        RelativeLayout relativeLayout3 = objArr[5];
        this.mboundView5 = relativeLayout3;
        relativeLayout3.setTag((Object) null);
        RelativeLayout relativeLayout4 = objArr[6];
        this.mboundView6 = relativeLayout4;
        relativeLayout4.setTag((Object) null);
        RelativeLayout relativeLayout5 = objArr[7];
        this.mboundView7 = relativeLayout5;
        relativeLayout5.setTag((Object) null);
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
        if (C0977BR.accountModel != i) {
            return false;
        }
        setAccountModel((AccountModel) obj);
        return true;
    }

    public void setAccountModel(AccountModel accountModel) {
        this.mAccountModel = accountModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(C0977BR.accountModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeAccountModelShowShareDot((MutableLiveData) obj, i2);
    }

    private boolean onChangeAccountModelShowShareDot(MutableLiveData<Boolean> mutableLiveData, int i) {
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
        BindingCommand<Void> bindingCommand;
        BindingCommand<Void> bindingCommand2;
        BindingCommand<Void> bindingCommand3;
        BindingCommand<Void> bindingCommand4;
        BindingCommand<Void> bindingCommand5;
        BindingCommand<Void> bindingCommand6;
        int i;
        BindingCommand<Void> bindingCommand7;
        BindingCommand<Void> bindingCommand8;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        AccountModel accountModel = this.mAccountModel;
        int i2 = ((j & 7) > 0 ? 1 : ((j & 7) == 0 ? 0 : -1));
        if (i2 != 0) {
            if ((j & 6) == 0 || accountModel == null) {
                bindingCommand8 = null;
                bindingCommand5 = null;
                bindingCommand4 = null;
                bindingCommand3 = null;
                bindingCommand7 = null;
                bindingCommand = null;
            } else {
                bindingCommand4 = accountModel.onChangePassword;
                bindingCommand3 = accountModel.onDeleteAccount;
                bindingCommand = accountModel.onPrivacy;
                bindingCommand7 = accountModel.onTimeZone;
                bindingCommand8 = accountModel.onShare;
                bindingCommand5 = accountModel.onBack;
            }
            MutableLiveData<Boolean> mutableLiveData = accountModel != null ? accountModel.showShareDot : null;
            updateLiveDataRegistration(0, mutableLiveData);
            boolean safeUnbox = ViewDataBinding.safeUnbox(mutableLiveData != null ? mutableLiveData.getValue() : null);
            if (i2 != 0) {
                j |= safeUnbox ? 16 : 8;
            }
            i = safeUnbox ? 0 : 8;
            BindingCommand<Void> bindingCommand9 = bindingCommand7;
            bindingCommand2 = bindingCommand8;
            bindingCommand6 = bindingCommand9;
        } else {
            i = 0;
            bindingCommand6 = null;
            bindingCommand5 = null;
            bindingCommand4 = null;
            bindingCommand3 = null;
            bindingCommand2 = null;
            bindingCommand = null;
        }
        if ((6 & j) != 0) {
            ViewAdapter.onClickCommand(this.mboundView2, bindingCommand2, false);
            ViewAdapter.onClickCommand(this.mboundView4, bindingCommand4, false);
            ViewAdapter.onClickCommand(this.mboundView5, bindingCommand, false);
            ViewAdapter.onClickCommand(this.mboundView6, bindingCommand3, false);
            ViewAdapter.onClickCommand(this.mboundView7, bindingCommand6, false);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand5);
        }
        if ((j & 7) != 0) {
            this.mboundView3.setVisibility(i);
        }
    }
}
