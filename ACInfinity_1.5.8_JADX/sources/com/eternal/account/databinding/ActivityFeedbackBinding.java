package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.FeedbackModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityFeedbackBinding extends ViewDataBinding {
    public final TextView emailTitle;
    public final EditText etEmail;
    public final EditText etMessage;
    public final EditText etTitle;
    public final Button ibSend;
    @Bindable
    protected FeedbackModel mFeedbackModel;
    public final ProgressBar pbLoading;
    public final RelativeLayout rlAttach;
    public final RelativeLayout root;
    public final Toolbar toolBar;
    public final TextView tvMessage;
    public final TextView tvTitle;

    public abstract void setFeedbackModel(FeedbackModel feedbackModel);

    protected ActivityFeedbackBinding(Object obj, View view, int i, TextView textView, EditText editText, EditText editText2, EditText editText3, Button button, ProgressBar progressBar, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, Toolbar toolbar, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.emailTitle = textView;
        this.etEmail = editText;
        this.etMessage = editText2;
        this.etTitle = editText3;
        this.ibSend = button;
        this.pbLoading = progressBar;
        this.rlAttach = relativeLayout;
        this.root = relativeLayout2;
        this.toolBar = toolbar;
        this.tvMessage = textView2;
        this.tvTitle = textView3;
    }

    public FeedbackModel getFeedbackModel() {
        return this.mFeedbackModel;
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFeedbackBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityFeedbackBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_feedback, viewGroup, z, obj);
    }

    public static ActivityFeedbackBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFeedbackBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityFeedbackBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_feedback, (ViewGroup) null, false, obj);
    }

    public static ActivityFeedbackBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityFeedbackBinding bind(View view, Object obj) {
        return (ActivityFeedbackBinding) bind(obj, view, C0997R.layout.activity_feedback);
    }
}
