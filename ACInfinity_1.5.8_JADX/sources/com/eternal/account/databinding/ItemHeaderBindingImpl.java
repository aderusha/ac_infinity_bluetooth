package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.C0977BR;
import com.eternal.account.model.ShareItemModel;

public class ItemHeaderBindingImpl extends ItemHeaderBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private final RelativeLayout mboundView0;
    private final TextView mboundView1;

    public ItemHeaderBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 2, sIncludes, sViewsWithIds));
    }

    private ItemHeaderBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1);
        this.mDirtyFlags = -1;
        RelativeLayout relativeLayout = objArr[0];
        this.mboundView0 = relativeLayout;
        relativeLayout.setTag((Object) null);
        TextView textView = objArr[1];
        this.mboundView1 = textView;
        textView.setTag((Object) null);
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
        if (C0977BR.itemShare != i) {
            return false;
        }
        setItemShare((ShareItemModel) obj);
        return true;
    }

    public void setItemShare(ShareItemModel shareItemModel) {
        this.mItemShare = shareItemModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(C0977BR.itemShare);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeItemShareHeadTxt((MutableLiveData) obj, i2);
    }

    private boolean onChangeItemShareHeadTxt(MutableLiveData<String> mutableLiveData, int i) {
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
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ShareItemModel shareItemModel = this.mItemShare;
        String str = null;
        int i = ((j & 7) > 0 ? 1 : ((j & 7) == 0 ? 0 : -1));
        if (i != 0) {
            MutableLiveData<String> mutableLiveData = shareItemModel != null ? shareItemModel.headTxt : null;
            updateLiveDataRegistration(0, mutableLiveData);
            if (mutableLiveData != null) {
                str = mutableLiveData.getValue();
            }
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.mboundView1, str);
        }
    }
}
