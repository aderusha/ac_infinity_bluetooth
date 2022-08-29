package com.eternal.advance.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.advance.C1202R;
import com.eternal.advance.model.AdvanceModel;
import com.eternal.base.SlideRecyclerView;
import com.google.android.material.tabs.TabLayout;

public abstract class FragmentAdvanceBinding extends ViewDataBinding {
    public final ConstraintLayout ctTab;
    public final ImageButton ivbAddProgram;
    public final SlideRecyclerView listAlarms;
    public final SlideRecyclerView listAutomations;
    public final SlideRecyclerView listNotification;
    @Bindable
    protected AdvanceModel mModel;
    public final TabLayout tbPort;
    public final TextView tvAlarms;
    public final TextView tvAutomations;
    public final View vClickAlarms;
    public final View vClickAutomation;
    public final View vSlide;

    public abstract void setModel(AdvanceModel advanceModel);

    protected FragmentAdvanceBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageButton imageButton, SlideRecyclerView slideRecyclerView, SlideRecyclerView slideRecyclerView2, SlideRecyclerView slideRecyclerView3, TabLayout tabLayout, TextView textView, TextView textView2, View view2, View view3, View view4) {
        super(obj, view, i);
        this.ctTab = constraintLayout;
        this.ivbAddProgram = imageButton;
        this.listAlarms = slideRecyclerView;
        this.listAutomations = slideRecyclerView2;
        this.listNotification = slideRecyclerView3;
        this.tbPort = tabLayout;
        this.tvAlarms = textView;
        this.tvAutomations = textView2;
        this.vClickAlarms = view2;
        this.vClickAutomation = view3;
        this.vSlide = view4;
    }

    public AdvanceModel getModel() {
        return this.mModel;
    }

    public static FragmentAdvanceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAdvanceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentAdvanceBinding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.fragment_advance, viewGroup, z, obj);
    }

    public static FragmentAdvanceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAdvanceBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentAdvanceBinding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.fragment_advance, (ViewGroup) null, false, obj);
    }

    public static FragmentAdvanceBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAdvanceBinding bind(View view, Object obj) {
        return (FragmentAdvanceBinding) bind(obj, view, C1202R.layout.fragment_advance);
    }
}
