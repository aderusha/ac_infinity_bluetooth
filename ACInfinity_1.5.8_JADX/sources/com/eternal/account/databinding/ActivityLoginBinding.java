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
import com.eternal.account.model.LoginModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityLoginBinding extends ViewDataBinding {
    public final RelativeLayout createEmail;
    public final TextView createEmailTitle;
    public final RelativeLayout createPassword;
    public final TextView createPasswordTitle;
    public final EditText etEmail;
    public final EditText etPwd;
    public final Button ibNext;
    @Bindable
    protected LoginModel mLoginModel;
    public final ProgressBar pbLoading;
    public final RelativeLayout root;
    public final Toolbar toolBar;
    public final TextView tvForgot;
    public final TextView tvNew;

    public abstract void setLoginModel(LoginModel loginModel);

    protected ActivityLoginBinding(Object obj, View view, int i, RelativeLayout relativeLayout, TextView textView, RelativeLayout relativeLayout2, TextView textView2, EditText editText, EditText editText2, Button button, ProgressBar progressBar, RelativeLayout relativeLayout3, Toolbar toolbar, TextView textView3, TextView textView4) {
        super(obj, view, i);
        this.createEmail = relativeLayout;
        this.createEmailTitle = textView;
        this.createPassword = relativeLayout2;
        this.createPasswordTitle = textView2;
        this.etEmail = editText;
        this.etPwd = editText2;
        this.ibNext = button;
        this.pbLoading = progressBar;
        this.root = relativeLayout3;
        this.toolBar = toolbar;
        this.tvForgot = textView3;
        this.tvNew = textView4;
    }

    public LoginModel getLoginModel() {
        return this.mLoginModel;
    }

    public static ActivityLoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityLoginBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_login, viewGroup, z, obj);
    }

    public static ActivityLoginBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityLoginBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_login, (ViewGroup) null, false, obj);
    }

    public static ActivityLoginBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityLoginBinding bind(View view, Object obj) {
        return (ActivityLoginBinding) bind(obj, view, C0997R.layout.activity_login);
    }
}
