package com.eternal.log.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.log.C2303R;
import com.eternal.log.model.LogModel;
import com.eternal.widget.ExpandableLayout;

public abstract class FragmentHistoryBinding extends ViewDataBinding {
    public final RelativeLayout btnDelete;
    public final LinearLayout llRow1;
    public final LinearLayout llRow2;
    @Bindable
    protected LogModel mModel;
    public final RecyclerView rcContent;
    public final ExpandableLayout umeFilter;

    public abstract void setModel(LogModel logModel);

    protected FragmentHistoryBinding(Object obj, View view, int i, RelativeLayout relativeLayout, LinearLayout linearLayout, LinearLayout linearLayout2, RecyclerView recyclerView, ExpandableLayout expandableLayout) {
        super(obj, view, i);
        this.btnDelete = relativeLayout;
        this.llRow1 = linearLayout;
        this.llRow2 = linearLayout2;
        this.rcContent = recyclerView;
        this.umeFilter = expandableLayout;
    }

    public LogModel getModel() {
        return this.mModel;
    }

    public static FragmentHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHistoryBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, C2303R.layout.fragment_history, viewGroup, z, obj);
    }

    public static FragmentHistoryBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHistoryBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentHistoryBinding) ViewDataBinding.inflateInternal(layoutInflater, C2303R.layout.fragment_history, (ViewGroup) null, false, obj);
    }

    public static FragmentHistoryBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentHistoryBinding bind(View view, Object obj) {
        return (FragmentHistoryBinding) bind(obj, view, C2303R.layout.fragment_history);
    }
}
