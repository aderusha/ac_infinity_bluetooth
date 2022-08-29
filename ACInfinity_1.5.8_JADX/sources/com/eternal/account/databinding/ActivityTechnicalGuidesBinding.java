package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.TechnicalGuidesModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityTechnicalGuidesBinding extends ViewDataBinding {
    @Bindable
    protected TechnicalGuidesModel mTechnicalGuides;
    public final Toolbar toolBar;

    public abstract void setTechnicalGuides(TechnicalGuidesModel technicalGuidesModel);

    protected ActivityTechnicalGuidesBinding(Object obj, View view, int i, Toolbar toolbar) {
        super(obj, view, i);
        this.toolBar = toolbar;
    }

    public TechnicalGuidesModel getTechnicalGuides() {
        return this.mTechnicalGuides;
    }

    public static ActivityTechnicalGuidesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTechnicalGuidesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityTechnicalGuidesBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_technical_guides, viewGroup, z, obj);
    }

    public static ActivityTechnicalGuidesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTechnicalGuidesBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityTechnicalGuidesBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_technical_guides, (ViewGroup) null, false, obj);
    }

    public static ActivityTechnicalGuidesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityTechnicalGuidesBinding bind(View view, Object obj) {
        return (ActivityTechnicalGuidesBinding) bind(obj, view, C0997R.layout.activity_technical_guides);
    }
}
