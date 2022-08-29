package com.eternal.data.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.eternal.data.C1835R;
import com.eternal.data.DataModel;
import com.eternal.data.p007ui.GraphHumBarView;
import com.eternal.data.p007ui.GraphTmpBarView;
import com.eternal.data.p007ui.GraphView;
import com.eternal.data.p007ui.GraphVpdBarView;

public abstract class FragmentDataBinding extends ViewDataBinding {
    public final GraphHumBarView barHum;
    public final GraphTmpBarView barTmp;
    public final GraphVpdBarView barVpd;
    public final ConstraintLayout clDistance;
    public final GraphView graphTmp;
    public final ImageView ivEnd;
    public final ImageView ivShow;
    public final ImageView ivShowFilter;
    public final ImageView ivStart;
    public final ImageView ivWindSpeed;
    public final LayoutTargetBinding layoutTargetHum;
    public final LayoutTargetBinding layoutTargetTmp;
    public final LayoutTargetBinding layoutTargetVpd;
    public final LinearLayout llContent;
    public final LinearLayout llHumTitle;
    public final LinearLayout llRoot;
    public final LinearLayout llTimeTool;
    public final LinearLayout llTmpTitle;
    public final LinearLayout llVpdTitle;
    @Bindable
    protected DataModel mModel;
    public final RelativeLayout rlPower;
    public final NestedScrollView scOverlay;
    public final ScrollView scroll;
    public final SwipeRefreshLayout srl;
    public final TextView tvAvg;
    public final TextView tvDistance;
    public final TextView tvEnd;
    public final TextView tvHum;
    public final TextView tvHumRow;
    public final TextView tvHumUnit;
    public final TextView tvMax;
    public final TextView tvMin;
    public final TextView tvPower;
    public final TextView tvStart;
    public final TextView tvTempUnit;
    public final TextView tvTmp;
    public final TextView tvTmpRow;
    public final TextView tvVpd;
    public final TextView tvVpdRow;
    public final TextView tvVpdUnit;
    public final TextView tvWindSpeed;
    public final View vLineHum;
    public final View vLineTemp;

    public abstract void setModel(DataModel dataModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected FragmentDataBinding(Object obj, View view, int i, GraphHumBarView graphHumBarView, GraphTmpBarView graphTmpBarView, GraphVpdBarView graphVpdBarView, ConstraintLayout constraintLayout, GraphView graphView, ImageView imageView, ImageView imageView2, ImageView imageView3, ImageView imageView4, ImageView imageView5, LayoutTargetBinding layoutTargetBinding, LayoutTargetBinding layoutTargetBinding2, LayoutTargetBinding layoutTargetBinding3, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, RelativeLayout relativeLayout, NestedScrollView nestedScrollView, ScrollView scrollView, SwipeRefreshLayout swipeRefreshLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, View view2, View view3) {
        super(obj, view, i);
        this.barHum = graphHumBarView;
        this.barTmp = graphTmpBarView;
        this.barVpd = graphVpdBarView;
        this.clDistance = constraintLayout;
        this.graphTmp = graphView;
        this.ivEnd = imageView;
        this.ivShow = imageView2;
        this.ivShowFilter = imageView3;
        this.ivStart = imageView4;
        this.ivWindSpeed = imageView5;
        this.layoutTargetHum = layoutTargetBinding;
        this.layoutTargetTmp = layoutTargetBinding2;
        this.layoutTargetVpd = layoutTargetBinding3;
        this.llContent = linearLayout;
        this.llHumTitle = linearLayout2;
        this.llRoot = linearLayout3;
        this.llTimeTool = linearLayout4;
        this.llTmpTitle = linearLayout5;
        this.llVpdTitle = linearLayout6;
        this.rlPower = relativeLayout;
        this.scOverlay = nestedScrollView;
        this.scroll = scrollView;
        this.srl = swipeRefreshLayout;
        this.tvAvg = textView;
        this.tvDistance = textView2;
        this.tvEnd = textView3;
        this.tvHum = textView4;
        this.tvHumRow = textView5;
        this.tvHumUnit = textView6;
        this.tvMax = textView7;
        this.tvMin = textView8;
        this.tvPower = textView9;
        this.tvStart = textView10;
        this.tvTempUnit = textView11;
        this.tvTmp = textView12;
        this.tvTmpRow = textView13;
        this.tvVpd = textView14;
        this.tvVpdRow = textView15;
        this.tvVpdUnit = textView16;
        this.tvWindSpeed = textView17;
        this.vLineHum = view2;
        this.vLineTemp = view3;
    }

    public DataModel getModel() {
        return this.mModel;
    }

    public static FragmentDataBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDataBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (FragmentDataBinding) ViewDataBinding.inflateInternal(layoutInflater, C1835R.layout.fragment_data, viewGroup, z, obj);
    }

    public static FragmentDataBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDataBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (FragmentDataBinding) ViewDataBinding.inflateInternal(layoutInflater, C1835R.layout.fragment_data, (ViewGroup) null, false, obj);
    }

    public static FragmentDataBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static FragmentDataBinding bind(View view, Object obj) {
        return (FragmentDataBinding) bind(obj, view, C1835R.layout.fragment_data);
    }
}
