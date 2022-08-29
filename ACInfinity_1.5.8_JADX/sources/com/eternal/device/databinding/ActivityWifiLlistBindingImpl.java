package com.eternal.device.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.model.ItemModel;
import com.eternal.device.model.WiFiListModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.swiperefresh.ViewAdapter;
import com.eternal.widget.guqiang.ToolbarAdapter;
import p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;
import p018me.tatarka.bindingcollectionadapter2.LayoutManagers;

public class ActivityWifiLlistBindingImpl extends ActivityWifiLlistBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;
    private final SwipeRefreshLayout mboundView2;
    private final RecyclerView mboundView3;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(C1922R.C1925id.tv_title, 5);
        sparseIntArray.put(C1922R.C1925id.tv_content, 6);
    }

    public ActivityWifiLlistBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private ActivityWifiLlistBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, objArr[1], objArr[6], objArr[4], objArr[5]);
        this.mDirtyFlags = -1;
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        SwipeRefreshLayout swipeRefreshLayout = objArr[2];
        this.mboundView2 = swipeRefreshLayout;
        swipeRefreshLayout.setTag((Object) null);
        RecyclerView recyclerView = objArr[3];
        this.mboundView3 = recyclerView;
        recyclerView.setTag((Object) null);
        this.toolBar.setTag((Object) null);
        this.tvSetting.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (C1909BR.wifiListModel != i) {
            return false;
        }
        setWifiListModel((WiFiListModel) obj);
        return true;
    }

    public void setWifiListModel(WiFiListModel wiFiListModel) {
        this.mWifiListModel = wiFiListModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(C1909BR.wifiListModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeWifiListModelItems((ObservableList) obj, i2);
        }
        if (i != 1) {
            return false;
        }
        return onChangeWifiListModelIsLoading((MutableLiveData) obj, i2);
    }

    private boolean onChangeWifiListModelItems(ObservableList<ItemModel> observableList, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeWifiListModelIsLoading(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != C1909BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        ObservableList<ItemModel> observableList;
        ItemBinding<ItemModel> itemBinding;
        BindingCommand<Void> bindingCommand;
        BindingCommand<Void> bindingCommand2;
        BindingCommand<Void> bindingCommand3;
        BindingCommand<Void> bindingCommand4;
        boolean z;
        ItemBinding<ItemModel> itemBinding2;
        ObservableList<ItemModel> observableList2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        WiFiListModel wiFiListModel = this.mWifiListModel;
        if ((15 & j) != 0) {
            if ((j & 12) == 0 || wiFiListModel == null) {
                bindingCommand4 = null;
                bindingCommand3 = null;
                bindingCommand2 = null;
                bindingCommand = null;
            } else {
                bindingCommand4 = wiFiListModel.onHelp;
                bindingCommand3 = wiFiListModel.onRefresh;
                bindingCommand = wiFiListModel.onBack;
                bindingCommand2 = wiFiListModel.onCancel;
            }
            if ((j & 13) != 0) {
                if (wiFiListModel != null) {
                    itemBinding2 = wiFiListModel.itemBinding;
                    observableList2 = wiFiListModel.items;
                } else {
                    observableList2 = null;
                    itemBinding2 = null;
                }
                updateRegistration(0, (ObservableList) observableList2);
            } else {
                observableList2 = null;
                itemBinding2 = null;
            }
            if ((j & 14) != 0) {
                MutableLiveData<Boolean> mutableLiveData = wiFiListModel != null ? wiFiListModel.isLoading : null;
                updateLiveDataRegistration(1, mutableLiveData);
                z = ViewDataBinding.safeUnbox(mutableLiveData != null ? mutableLiveData.getValue() : null);
                observableList = observableList2;
                itemBinding = itemBinding2;
            } else {
                observableList = observableList2;
                itemBinding = itemBinding2;
                z = false;
            }
        } else {
            z = false;
            bindingCommand4 = null;
            bindingCommand3 = null;
            bindingCommand2 = null;
            bindingCommand = null;
            itemBinding = null;
            observableList = null;
        }
        if ((j & 14) != 0) {
            ViewAdapter.setRefreshing(this.mboundView2, z);
        }
        if ((12 & j) != 0) {
            ViewAdapter.onRefreshCommand(this.mboundView2, bindingCommand3);
            ToolbarAdapter.onBack(this.toolBar, bindingCommand);
            ToolbarAdapter.onNext(this.toolBar, bindingCommand2);
            com.eternal.framework.binding.viewadapter.view.ViewAdapter.onClickCommand(this.tvSetting, bindingCommand4, false);
        }
        if ((8 & j) != 0) {
            BindingRecyclerViewAdapters.setLayoutManager(this.mboundView3, LayoutManagers.linear());
        }
        if ((j & 13) != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.mboundView3, itemBinding, observableList, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null);
        }
    }
}
