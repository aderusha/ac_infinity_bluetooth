package com.eternal.log.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.view.ViewAdapter;
import com.eternal.log.C2267BR;
import com.eternal.log.C2303R;
import com.eternal.log.model.LogCModel;

public class FragmentLogCBindingImpl extends FragmentLogCBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;
    private final ImageView mboundView2;
    private final ImageView mboundView4;
    private final ImageView mboundView6;
    private final ImageView mboundView8;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C2303R.C2306id.rc_content, 10);
        sparseIntArray.put(C2303R.C2306id.ume_filter, 11);
    }

    public FragmentLogCBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    private FragmentLogCBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 4, objArr[9], objArr[10], objArr[5], objArr[1], objArr[7], objArr[3], objArr[11]);
        this.mDirtyFlags = -1;
        this.btnDelete.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        ImageView imageView = objArr[2];
        this.mboundView2 = imageView;
        imageView.setTag((Object) null);
        ImageView imageView2 = objArr[4];
        this.mboundView4 = imageView2;
        imageView2.setTag((Object) null);
        ImageView imageView3 = objArr[6];
        this.mboundView6 = imageView3;
        imageView3.setTag((Object) null);
        ImageView imageView4 = objArr[8];
        this.mboundView8 = imageView4;
        imageView4.setTag((Object) null);
        this.rlHighHum.setTag((Object) null);
        this.rlHighTmp.setTag((Object) null);
        this.rlLowHum.setTag((Object) null);
        this.rlLowTmp.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
        if (C2267BR.modelc != i) {
            return false;
        }
        setModelc((LogCModel) obj);
        return true;
    }

    public void setModelc(LogCModel logCModel) {
        this.mModelc = logCModel;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(C2267BR.modelc);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeModelcHighTmpSwitch((MutableLiveData) obj, i2);
        }
        if (i == 1) {
            return onChangeModelcLowHumSwitch((MutableLiveData) obj, i2);
        }
        if (i == 2) {
            return onChangeModelcLowTmpSwitch((MutableLiveData) obj, i2);
        }
        if (i != 3) {
            return false;
        }
        return onChangeModelcHighHumSwitch((MutableLiveData) obj, i2);
    }

    private boolean onChangeModelcHighTmpSwitch(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2267BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeModelcLowHumSwitch(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2267BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeModelcLowTmpSwitch(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2267BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeModelcHighHumSwitch(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C2267BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        boolean z;
        View.OnClickListener onClickListener;
        BindingCommand<Void> bindingCommand;
        boolean z2;
        boolean z3;
        boolean z4;
        BindingCommand<Void> bindingCommand2;
        View.OnClickListener onClickListener2;
        boolean z5;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        LogCModel logCModel = this.mModelc;
        if ((63 & j) != 0) {
            if ((j & 49) != 0) {
                MutableLiveData<Boolean> mutableLiveData = logCModel != null ? logCModel.highTmpSwitch : null;
                updateLiveDataRegistration(0, mutableLiveData);
                z3 = ViewDataBinding.safeUnbox(mutableLiveData != null ? mutableLiveData.getValue() : null);
            } else {
                z3 = false;
            }
            if ((j & 50) != 0) {
                MutableLiveData<Boolean> mutableLiveData2 = logCModel != null ? logCModel.lowHumSwitch : null;
                updateLiveDataRegistration(1, mutableLiveData2);
                z2 = ViewDataBinding.safeUnbox(mutableLiveData2 != null ? mutableLiveData2.getValue() : null);
            } else {
                z2 = false;
            }
            if ((j & 48) == 0 || logCModel == null) {
                onClickListener2 = null;
                bindingCommand2 = null;
            } else {
                onClickListener2 = logCModel.onFilter;
                bindingCommand2 = logCModel.onDelete;
            }
            if ((j & 52) != 0) {
                MutableLiveData<Boolean> mutableLiveData3 = logCModel != null ? logCModel.lowTmpSwitch : null;
                updateLiveDataRegistration(2, mutableLiveData3);
                z5 = ViewDataBinding.safeUnbox(mutableLiveData3 != null ? mutableLiveData3.getValue() : null);
            } else {
                z5 = false;
            }
            if ((j & 56) != 0) {
                MutableLiveData<Boolean> mutableLiveData4 = logCModel != null ? logCModel.highHumSwitch : null;
                updateLiveDataRegistration(3, mutableLiveData4);
                z4 = ViewDataBinding.safeUnbox(mutableLiveData4 != null ? mutableLiveData4.getValue() : null);
                z = z5;
            } else {
                z = z5;
                z4 = false;
            }
            onClickListener = onClickListener2;
            bindingCommand = bindingCommand2;
        } else {
            z4 = false;
            z3 = false;
            z2 = false;
            bindingCommand = null;
            onClickListener = null;
            z = false;
        }
        if ((j & 48) != 0) {
            ViewAdapter.onClickCommand(this.btnDelete, bindingCommand, false);
            this.rlHighHum.setOnClickListener(onClickListener);
            this.rlHighTmp.setOnClickListener(onClickListener);
            this.rlLowHum.setOnClickListener(onClickListener);
            this.rlLowTmp.setOnClickListener(onClickListener);
        }
        if ((j & 49) != 0) {
            ViewAdapter.onSelected(this.mboundView2, z3);
        }
        if ((52 & j) != 0) {
            ViewAdapter.onSelected(this.mboundView4, z);
        }
        if ((56 & j) != 0) {
            ViewAdapter.onSelected(this.mboundView6, z4);
        }
        if ((j & 50) != 0) {
            ViewAdapter.onSelected(this.mboundView8, z2);
        }
    }
}
