package com.eternal.account.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0977BR;
import com.eternal.account.C0997R;
import com.eternal.account.model.SearchItemModel;
import com.eternal.account.model.SearchModel;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.viewadapter.edittext.ViewAdapter;
import com.eternal.framework.binding.viewadapter.textview.viewAdapter;
import com.eternal.widget.guqiang.ProgressToolbarAdapter;
import p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import p018me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapters;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;
import p018me.tatarka.bindingcollectionadapter2.LayoutManagers;

public class ActivitySearchBindingImpl extends ActivitySearchBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    public ActivitySearchBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private ActivitySearchBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[2], objArr[0], objArr[3], objArr[1]);
        this.mDirtyFlags = -1;
        this.etKey.setTag((Object) null);
        this.root.setTag((Object) null);
        this.srvWithYou.setTag((Object) null);
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
        if (C0977BR.searchModel != i) {
            return false;
        }
        setSearchModel((SearchModel) obj);
        return true;
    }

    public void setSearchModel(SearchModel searchModel) {
        this.mSearchModel = searchModel;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(C0977BR.searchModel);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeSearchModelItems((ObservableList) obj, i2);
    }

    private boolean onChangeSearchModelItems(ObservableList<SearchItemModel> observableList, int i) {
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
        ObservableList<SearchItemModel> observableList;
        ItemBinding<SearchItemModel> itemBinding;
        BindingCommand<Void> bindingCommand;
        BindingCommand<String> bindingCommand2;
        ItemBinding<SearchItemModel> itemBinding2;
        ObservableList<SearchItemModel> observableList2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SearchModel searchModel = this.mSearchModel;
        int i = ((7 & j) > 0 ? 1 : ((7 & j) == 0 ? 0 : -1));
        if (i != 0) {
            if ((j & 6) == 0 || searchModel == null) {
                bindingCommand2 = null;
                bindingCommand = null;
            } else {
                bindingCommand2 = searchModel.textChanged;
                bindingCommand = searchModel.onBack;
            }
            if (searchModel != null) {
                itemBinding2 = searchModel.itemBinding;
                observableList2 = searchModel.items;
            } else {
                observableList2 = null;
                itemBinding2 = null;
            }
            updateRegistration(0, (ObservableList) observableList2);
            observableList = observableList2;
            itemBinding = itemBinding2;
        } else {
            bindingCommand2 = null;
            bindingCommand = null;
            itemBinding = null;
            observableList = null;
        }
        if ((6 & j) != 0) {
            ViewAdapter.addTextChangedListener(this.etKey, bindingCommand2);
            ProgressToolbarAdapter.onBack(this.toolBar, bindingCommand);
        }
        if ((j & 4) != 0) {
            viewAdapter.drawableStart(this.etKey, AppCompatResources.getDrawable(this.etKey.getContext(), C0997R.C0999drawable.search_layer));
            BindingRecyclerViewAdapters.setLayoutManager(this.srvWithYou, LayoutManagers.linear());
        }
        if (i != 0) {
            BindingRecyclerViewAdapters.setAdapter(this.srvWithYou, itemBinding, observableList, (BindingRecyclerViewAdapter) null, (BindingRecyclerViewAdapter.ItemIds) null, (BindingRecyclerViewAdapter.ViewHolderFactory) null);
        }
    }
}
