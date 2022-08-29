package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.SearchModel;
import com.eternal.base.SlideRecyclerView;
import com.eternal.widget.guqiang.ProgressToolbar;

public abstract class ActivitySearchBinding extends ViewDataBinding {
    public final EditText etKey;
    @Bindable
    protected SearchModel mSearchModel;
    public final LinearLayout root;
    public final SlideRecyclerView srvWithYou;
    public final ProgressToolbar toolBar;

    public abstract void setSearchModel(SearchModel searchModel);

    protected ActivitySearchBinding(Object obj, View view, int i, EditText editText, LinearLayout linearLayout, SlideRecyclerView slideRecyclerView, ProgressToolbar progressToolbar) {
        super(obj, view, i);
        this.etKey = editText;
        this.root = linearLayout;
        this.srvWithYou = slideRecyclerView;
        this.toolBar = progressToolbar;
    }

    public SearchModel getSearchModel() {
        return this.mSearchModel;
    }

    public static ActivitySearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivitySearchBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_search, viewGroup, z, obj);
    }

    public static ActivitySearchBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivitySearchBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_search, (ViewGroup) null, false, obj);
    }

    public static ActivitySearchBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivitySearchBinding bind(View view, Object obj) {
        return (ActivitySearchBinding) bind(obj, view, C0997R.layout.activity_search);
    }
}
