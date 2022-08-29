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
import com.eternal.account.model.ResetPasswordModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityResetPasswordBinding extends ViewDataBinding {
    public final RelativeLayout createEmail;
    public final TextView createEmailTitle;
    public final EditText etEmail;
    public final Button ibNext;
    @Bindable
    protected ResetPasswordModel mResetModel;
    public final ProgressBar pbLoading;
    public final RelativeLayout root;
    public final Toolbar toolBar;
    public final TextView tvTip;

    public abstract void setResetModel(ResetPasswordModel resetPasswordModel);

    protected ActivityResetPasswordBinding(Object obj, View view, int i, RelativeLayout relativeLayout, TextView textView, EditText editText, Button button, ProgressBar progressBar, RelativeLayout relativeLayout2, Toolbar toolbar, TextView textView2) {
        super(obj, view, i);
        this.createEmail = relativeLayout;
        this.createEmailTitle = textView;
        this.etEmail = editText;
        this.ibNext = button;
        this.pbLoading = progressBar;
        this.root = relativeLayout2;
        this.toolBar = toolbar;
        this.tvTip = textView2;
    }

    public ResetPasswordModel getResetModel() {
        return this.mResetModel;
    }

    public static ActivityResetPasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityResetPasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityResetPasswordBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_reset_password, viewGroup, z, obj);
    }

    public static ActivityResetPasswordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityResetPasswordBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityResetPasswordBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_reset_password, (ViewGroup) null, false, obj);
    }

    public static ActivityResetPasswordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityResetPasswordBinding bind(View view, Object obj) {
        return (ActivityResetPasswordBinding) bind(obj, view, C0997R.layout.activity_reset_password);
    }
}
