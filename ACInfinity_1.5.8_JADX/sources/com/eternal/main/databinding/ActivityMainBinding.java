package com.eternal.main.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;
import com.eternal.base.SlideRecyclerView;
import com.eternal.main.C2343R;
import com.eternal.main.model.MainModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityMainBinding extends ViewDataBinding {
    public final ConstraintLayout ctRoot;
    public final SlideRecyclerView layoutDevice;
    public final DrawerLayout layoutDrawer;
    public final LinearLayout layoutMain;
    public final LinearLayout llLayout;
    @Bindable
    protected MainModel mModel;
    public final Toolbar toolBar;
    public final TextView tvLogin;
    public final View tvPermission;
    public final TextView tvTitle;

    public abstract void setModel(MainModel mainModel);

    protected ActivityMainBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, SlideRecyclerView slideRecyclerView, DrawerLayout drawerLayout, LinearLayout linearLayout, LinearLayout linearLayout2, Toolbar toolbar, TextView textView, View view2, TextView textView2) {
        super(obj, view, i);
        this.ctRoot = constraintLayout;
        this.layoutDevice = slideRecyclerView;
        this.layoutDrawer = drawerLayout;
        this.layoutMain = linearLayout;
        this.llLayout = linearLayout2;
        this.toolBar = toolbar;
        this.tvLogin = textView;
        this.tvPermission = view2;
        this.tvTitle = textView2;
    }

    public MainModel getModel() {
        return this.mModel;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityMainBinding) ViewDataBinding.inflateInternal(layoutInflater, C2343R.layout.activity_main, viewGroup, z, obj);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityMainBinding) ViewDataBinding.inflateInternal(layoutInflater, C2343R.layout.activity_main, (ViewGroup) null, false, obj);
    }

    public static ActivityMainBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityMainBinding bind(View view, Object obj) {
        return (ActivityMainBinding) bind(obj, view, C2343R.layout.activity_main);
    }
}
