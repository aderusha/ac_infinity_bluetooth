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
import com.eternal.account.model.CreateModel;
import com.eternal.widget.guqiang.Toolbar;

public abstract class ActivityCreateBinding extends ViewDataBinding {
    public final RelativeLayout createEmail;
    public final TextView createEmailTitle;
    public final RelativeLayout createPassword;
    public final TextView createPasswordTitle;
    public final TextView createTipsPrivacy;
    public final EditText etEmail;
    public final EditText etPwd;
    public final Button ibNext;
    @Bindable
    protected CreateModel mCreateModel;
    public final ProgressBar pbLoading;
    public final RelativeLayout root;
    public final Toolbar toolBar;

    public abstract void setCreateModel(CreateModel createModel);

    protected ActivityCreateBinding(Object obj, View view, int i, RelativeLayout relativeLayout, TextView textView, RelativeLayout relativeLayout2, TextView textView2, TextView textView3, EditText editText, EditText editText2, Button button, ProgressBar progressBar, RelativeLayout relativeLayout3, Toolbar toolbar) {
        super(obj, view, i);
        this.createEmail = relativeLayout;
        this.createEmailTitle = textView;
        this.createPassword = relativeLayout2;
        this.createPasswordTitle = textView2;
        this.createTipsPrivacy = textView3;
        this.etEmail = editText;
        this.etPwd = editText2;
        this.ibNext = button;
        this.pbLoading = progressBar;
        this.root = relativeLayout3;
        this.toolBar = toolbar;
    }

    public CreateModel getCreateModel() {
        return this.mCreateModel;
    }

    public static ActivityCreateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCreateBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityCreateBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_create, viewGroup, z, obj);
    }

    public static ActivityCreateBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCreateBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityCreateBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_create, (ViewGroup) null, false, obj);
    }

    public static ActivityCreateBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityCreateBinding bind(View view, Object obj) {
        return (ActivityCreateBinding) bind(obj, view, C0997R.layout.activity_create);
    }
}
