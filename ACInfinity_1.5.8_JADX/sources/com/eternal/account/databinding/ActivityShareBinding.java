package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.ShareModel;
import com.eternal.base.SlideRecyclerView;
import com.eternal.widget.guqiang.ProgressToolbar;

public abstract class ActivityShareBinding extends ViewDataBinding {
    public final ConstraintLayout ctTab;
    public final Button ibNext;
    public final LinearLayout llWithYou;
    @Bindable
    protected ShareModel mShareModel;
    public final LinearLayout root;
    public final SlideRecyclerView srvWithOther;
    public final SlideRecyclerView srvWithYou;
    public final ProgressToolbar toolBar;
    public final TextView tvWithOthers;
    public final View vSlide;

    public abstract void setShareModel(ShareModel shareModel);

    protected ActivityShareBinding(Object obj, View view, int i, ConstraintLayout constraintLayout, Button button, LinearLayout linearLayout, LinearLayout linearLayout2, SlideRecyclerView slideRecyclerView, SlideRecyclerView slideRecyclerView2, ProgressToolbar progressToolbar, TextView textView, View view2) {
        super(obj, view, i);
        this.ctTab = constraintLayout;
        this.ibNext = button;
        this.llWithYou = linearLayout;
        this.root = linearLayout2;
        this.srvWithOther = slideRecyclerView;
        this.srvWithYou = slideRecyclerView2;
        this.toolBar = progressToolbar;
        this.tvWithOthers = textView;
        this.vSlide = view2;
    }

    public ShareModel getShareModel() {
        return this.mShareModel;
    }

    public static ActivityShareBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityShareBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityShareBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_share, viewGroup, z, obj);
    }

    public static ActivityShareBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityShareBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityShareBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_share, (ViewGroup) null, false, obj);
    }

    public static ActivityShareBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityShareBinding bind(View view, Object obj) {
        return (ActivityShareBinding) bind(obj, view, C0997R.layout.activity_share);
    }
}
