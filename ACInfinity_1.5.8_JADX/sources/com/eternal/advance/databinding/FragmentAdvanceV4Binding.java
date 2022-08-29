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
import com.eternal.advance.model.AdvanceModelV4;
import com.eternal.base.SlideRecyclerView;
import com.google.android.material.tabs.TabLayout;

public abstract class FragmentAdvanceV4Binding extends ViewDataBinding {
    public final ConstraintLayout ctTab;
    public final ImageButton ivbAddProgram;
    public final SlideRecyclerView listAlarms;
    public final SlideRecyclerView listAutomations;
    public final SlideRecyclerView listNotification;
    @Bindable
    protected AdvanceModelV4 mModelV4;
    public final TabLayout tbPort;
    public final TextView tvAlarms;
    public final TextView tvAutomations;
    public final View vClickAlarms;
    public final View vClickAutomation;
    public final View vSlide;

    public abstract void setModelV4(AdvanceModelV4 advanceModelV4);

    protected FragmentAdvanceV4Binding(Object obj, View view, int i, ConstraintLayout constraintLayout, ImageButton imageButton, SlideRecyclerView slideRecyclerView, SlideRecyclerView slideRecyclerView2, SlideRecyclerView slideRecyclerView3, TabLayout tabLayout, TextView textView, TextView textView2, View view2, View view3, View view4) {
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

    public AdvanceModelV4 getModelV4() {
        return this.mModelV4;
    }

    public static FragmentAdvanceV4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAdvanceV4Binding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentAdvanceV4Binding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.fragment_advance_v4, viewGroup, z, obj);
    }

    public static FragmentAdvanceV4Binding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAdvanceV4Binding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentAdvanceV4Binding) ViewDataBinding.inflateInternal(layoutInflater, C1202R.layout.fragment_advance_v4, (ViewGroup) null, false, obj);
    }

    public static FragmentAdvanceV4Binding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentAdvanceV4Binding bind(View view, Object obj) {
        return (FragmentAdvanceV4Binding) bind(obj, view, C1202R.layout.fragment_advance_v4);
    }
}
