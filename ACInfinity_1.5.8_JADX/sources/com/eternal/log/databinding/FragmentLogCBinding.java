package com.eternal.log.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.eternal.log.C2303R;
import com.eternal.log.model.LogCModel;
import com.eternal.widget.ExpandableLayout;

public abstract class FragmentLogCBinding extends ViewDataBinding {
    public final RelativeLayout btnDelete;
    @Bindable
    protected LogCModel mModelc;
    public final RecyclerView rcContent;
    public final RelativeLayout rlHighHum;
    public final RelativeLayout rlHighTmp;
    public final RelativeLayout rlLowHum;
    public final RelativeLayout rlLowTmp;
    public final ExpandableLayout umeFilter;

    public abstract void setModelc(LogCModel logCModel);

    protected FragmentLogCBinding(Object obj, View view, int i, RelativeLayout relativeLayout, RecyclerView recyclerView, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, RelativeLayout relativeLayout4, RelativeLayout relativeLayout5, ExpandableLayout expandableLayout) {
        super(obj, view, i);
        this.btnDelete = relativeLayout;
        this.rcContent = recyclerView;
        this.rlHighHum = relativeLayout2;
        this.rlHighTmp = relativeLayout3;
        this.rlLowHum = relativeLayout4;
        this.rlLowTmp = relativeLayout5;
        this.umeFilter = expandableLayout;
    }

    public LogCModel getModelc() {
        return this.mModelc;
    }

    public static FragmentLogCBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLogCBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentLogCBinding) ViewDataBinding.inflateInternal(layoutInflater, C2303R.layout.fragment_log_c, viewGroup, z, obj);
    }

    public static FragmentLogCBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLogCBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentLogCBinding) ViewDataBinding.inflateInternal(layoutInflater, C2303R.layout.fragment_log_c, (ViewGroup) null, false, obj);
    }

    public static FragmentLogCBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentLogCBinding bind(View view, Object obj) {
        return (FragmentLogCBinding) bind(obj, view, C2303R.layout.fragment_log_c);
    }
}
