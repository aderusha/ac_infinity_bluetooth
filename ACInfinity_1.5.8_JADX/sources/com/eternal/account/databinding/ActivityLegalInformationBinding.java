package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.LegalInformationModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityLegalInformationBinding extends ViewDataBinding {
    @Bindable
    protected LegalInformationModel mLegalModel;
    public final Toolbar toolBar;

    public abstract void setLegalModel(LegalInformationModel legalInformationModel);

    protected ActivityLegalInformationBinding(Object obj, View view, int i, Toolbar toolbar) {
        super(obj, view, i);
        this.toolBar = toolbar;
    }

    public LegalInformationModel getLegalModel() {
        return this.mLegalModel;
    }

    public static ActivityLegalInformationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLegalInformationBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityLegalInformationBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_legal_information, viewGroup, z, obj);
    }

    public static ActivityLegalInformationBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLegalInformationBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityLegalInformationBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_legal_information, (ViewGroup) null, false, obj);
    }

    public static ActivityLegalInformationBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLegalInformationBinding bind(View view, Object obj) {
        return (ActivityLegalInformationBinding) bind(obj, view, C0997R.layout.activity_legal_information);
    }
}
