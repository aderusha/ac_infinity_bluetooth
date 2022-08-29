package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.PreferencesModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityPreferencesBinding extends ViewDataBinding {
    public final Button ibNext;
    @Bindable
    protected PreferencesModel mPreferenceModel;
    public final RelativeLayout root;
    public final Switch swAnalytics;
    public final Switch swReports;
    public final Switch swSubscription;
    public final Toolbar toolBar;
    public final TextView tvAnalytics;
    public final TextView tvReports;
    public final TextView tvSubscription;

    public abstract void setPreferenceModel(PreferencesModel preferencesModel);

    protected ActivityPreferencesBinding(Object obj, View view, int i, Button button, RelativeLayout relativeLayout, Switch switchR, Switch switchR2, Switch switchR3, Toolbar toolbar, TextView textView, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.ibNext = button;
        this.root = relativeLayout;
        this.swAnalytics = switchR;
        this.swReports = switchR2;
        this.swSubscription = switchR3;
        this.toolBar = toolbar;
        this.tvAnalytics = textView;
        this.tvReports = textView2;
        this.tvSubscription = textView3;
    }

    public PreferencesModel getPreferenceModel() {
        return this.mPreferenceModel;
    }

    public static ActivityPreferencesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPreferencesBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityPreferencesBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_preferences, viewGroup, z, obj);
    }

    public static ActivityPreferencesBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPreferencesBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityPreferencesBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_preferences, (ViewGroup) null, false, obj);
    }

    public static ActivityPreferencesBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityPreferencesBinding bind(View view, Object obj) {
        return (ActivityPreferencesBinding) bind(obj, view, C0997R.layout.activity_preferences);
    }
}
