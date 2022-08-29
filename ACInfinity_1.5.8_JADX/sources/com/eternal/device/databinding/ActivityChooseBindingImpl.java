package com.eternal.device.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.ChooseModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;

public class ActivityChooseBindingImpl extends ActivityChooseBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final RelativeLayout mboundView2;
    private final RelativeLayout mboundView3;
    private final RelativeLayout mboundView4;
    private final RelativeLayout mboundView5;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.ll_layout_choose1, 18);
        sparseIntArray.put(C1922R.C1925id.iv_icon1, 19);
        sparseIntArray.put(C1922R.C1925id.iv_icon2, 20);
        sparseIntArray.put(C1922R.C1925id.iv_icon3, 21);
        sparseIntArray.put(C1922R.C1925id.iv_icon4, 22);
    }

    public ActivityChooseBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 23, sIncludes, sViewsWithIds));
    }

    private ActivityChooseBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[19], objArr[20], objArr[21], objArr[22], objArr[6], objArr[7], objArr[8], objArr[9], objArr[12], objArr[10], objArr[11], objArr[17], objArr[15], objArr[16], objArr[13], objArr[14], objArr[18], objArr[1]);
        this.mDirtyFlags = -1;
        this.ll67.setTag((Object) null);
        this.ll69.setTag((Object) null);
        this.ll69Wifi.setTag((Object) null);
        this.ll70.setTag((Object) null);
        this.ll75IndependentPort.setTag((Object) null);
        this.ll76.setTag((Object) null);
        this.ll76IndependentPort.setTag((Object) null);
        this.llAirtap.setTag((Object) null);
        this.llCa1.setTag((Object) null);
        this.llCa2.setTag((Object) null);
        this.llCb1.setTag((Object) null);
        this.llCb2.setTag((Object) null);
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
        RelativeLayout relativeLayout4 = objArr[5];
        this.mboundView5 = relativeLayout4;
        relativeLayout4.setTag((Object) null);
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
        if (C1909BR.chooseModel != i) {
            return false;
        }
        setChooseModel((ChooseModel) obj);
        return true;
    }

    public void setChooseModel(ChooseModel chooseModel) {
        this.mChooseModel = chooseModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(C1909BR.chooseModel);
        super.requestRebind();
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
        BindingCommand<Void> bindingCommand7;
        BindingCommand<Void> bindingCommand8;
        BindingCommand<Void> bindingCommand9;
        BindingCommand<Void> bindingCommand10;
        BindingCommand<Void> bindingCommand11;
        BindingCommand<Void> bindingCommand12;
        BindingCommand<Void> bindingCommand13;
        BindingCommand<Void> bindingCommand14;
        BindingCommand<Void> bindingCommand15;
        BindingCommand<Void> bindingCommand16;
        BindingCommand<Void> bindingCommand17;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ChooseModel chooseModel = this.mChooseModel;
        BindingCommand<Void> bindingCommand18 = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i == 0 || chooseModel == null) {
            bindingCommand17 = null;
            bindingCommand16 = null;
            bindingCommand15 = null;
            bindingCommand14 = null;
            bindingCommand13 = null;
            bindingCommand12 = null;
            bindingCommand11 = null;
            bindingCommand10 = null;
            bindingCommand9 = null;
            bindingCommand8 = null;
            bindingCommand7 = null;
            bindingCommand6 = null;
            bindingCommand5 = null;
            bindingCommand4 = null;
            bindingCommand3 = null;
            bindingCommand2 = null;
            bindingCommand = null;
        } else {
            BindingCommand<Void> bindingCommand19 = chooseModel.onCB1Click;
            BindingCommand<Void> bindingCommand20 = chooseModel.onCB2Click;
            BindingCommand<Void> bindingCommand21 = chooseModel.on67Click;
            bindingCommand14 = chooseModel.on69Click;
            BindingCommand<Void> bindingCommand22 = chooseModel.onBack;
            BindingCommand<Void> bindingCommand23 = chooseModel.onHAVCClick;
            bindingCommand11 = chooseModel.on75IndependentPortClick;
            bindingCommand10 = chooseModel.onCA1Click;
            bindingCommand9 = chooseModel.onCA2Click;
            bindingCommand8 = chooseModel.onAirtapClick;
            bindingCommand7 = chooseModel.on76Click;
            bindingCommand6 = chooseModel.on70Click;
            bindingCommand5 = chooseModel.on69WiFiClick;
            BindingCommand<Void> bindingCommand24 = bindingCommand20;
            BindingCommand<Void> bindingCommand25 = chooseModel.onControllersClick;
            BindingCommand<Void> bindingCommand26 = chooseModel.on76IndependentPortClick;
            BindingCommand<Void> bindingCommand27 = chooseModel.onOutletsClick;
            BindingCommand<Void> bindingCommand28 = chooseModel.onCancel;
            bindingCommand4 = chooseModel.onHygrometersClick;
            bindingCommand = bindingCommand28;
            bindingCommand2 = bindingCommand22;
            bindingCommand16 = bindingCommand19;
            bindingCommand3 = bindingCommand23;
            bindingCommand13 = bindingCommand25;
            bindingCommand17 = bindingCommand26;
            bindingCommand12 = bindingCommand27;
            bindingCommand18 = bindingCommand21;
            bindingCommand15 = bindingCommand24;
        }
        if (i != 0) {
            ViewAdapter.onClickCommand(this.ll67, bindingCommand18, false);
            ViewAdapter.onClickCommand(this.ll69, bindingCommand14, false);
            ViewAdapter.onClickCommand(this.ll69Wifi, bindingCommand5, false);
            ViewAdapter.onClickCommand(this.ll70, bindingCommand6, false);
            ViewAdapter.onClickCommand(this.ll75IndependentPort, bindingCommand11, false);
            ViewAdapter.onClickCommand(this.ll76, bindingCommand7, false);
            ViewAdapter.onClickCommand(this.ll76IndependentPort, bindingCommand17, false);
            ViewAdapter.onClickCommand(this.llAirtap, bindingCommand8, false);
            ViewAdapter.onClickCommand(this.llCa1, bindingCommand10, false);
            ViewAdapter.onClickCommand(this.llCa2, bindingCommand9, false);
            ViewAdapter.onClickCommand(this.llCb1, bindingCommand16, false);
            ViewAdapter.onClickCommand(this.llCb2, bindingCommand15, false);
            ViewAdapter.onClickCommand(this.mboundView2, bindingCommand13, false);
            ViewAdapter.onClickCommand(this.mboundView3, bindingCommand12, false);
            ViewAdapter.onClickCommand(this.mboundView4, bindingCommand4, false);
            ViewAdapter.onClickCommand(this.mboundView5, bindingCommand3, false);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand2);
            ToolbarAdapter.onNext(this.toolBar, bindingCommand);
        }
    }
}
