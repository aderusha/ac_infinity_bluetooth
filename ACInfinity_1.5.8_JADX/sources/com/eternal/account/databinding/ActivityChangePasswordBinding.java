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
import com.eternal.account.model.ChangePasswordModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityChangePasswordBinding extends ViewDataBinding {
    public final TextView createPasswordTitle;
    public final EditText etNewPwd;
    public final EditText etPwd;
    public final Button ibNext;
    @Bindable
    protected ChangePasswordModel mChangeModel;
    public final ProgressBar pbLoading;
    public final RelativeLayout rlConfrimPassword;
    public final RelativeLayout rlNewPassword;
    public final RelativeLayout root;
    public final Toolbar toolBar;
    public final TextView tvNewPassword;
    public final TextView tvTip;

    public abstract void setChangeModel(ChangePasswordModel changePasswordModel);

    protected ActivityChangePasswordBinding(Object obj, View view, int i, TextView textView, EditText editText, EditText editText2, Button button, ProgressBar progressBar, RelativeLayout relativeLayout, RelativeLayout relativeLayout2, RelativeLayout relativeLayout3, Toolbar toolbar, TextView textView2, TextView textView3) {
        super(obj, view, i);
        this.createPasswordTitle = textView;
        this.etNewPwd = editText;
        this.etPwd = editText2;
        this.ibNext = button;
        this.pbLoading = progressBar;
        this.rlConfrimPassword = relativeLayout;
        this.rlNewPassword = relativeLayout2;
        this.root = relativeLayout3;
        this.toolBar = toolbar;
        this.tvNewPassword = textView2;
        this.tvTip = textView3;
    }

    public ChangePasswordModel getChangeModel() {
        return this.mChangeModel;
    }

    public static ActivityChangePasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChangePasswordBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityChangePasswordBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_change_password, viewGroup, z, obj);
    }

    public static ActivityChangePasswordBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChangePasswordBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityChangePasswordBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_change_password, (ViewGroup) null, false, obj);
    }

    public static ActivityChangePasswordBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityChangePasswordBinding bind(View view, Object obj) {
        return (ActivityChangePasswordBinding) bind(obj, view, C0997R.layout.activity_change_password);
    }
}
