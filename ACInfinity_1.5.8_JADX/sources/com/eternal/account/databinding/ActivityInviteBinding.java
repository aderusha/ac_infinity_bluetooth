package com.eternal.account.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.eternal.account.C0997R;
import com.eternal.account.model.InviteModel;
import com.eternal.widget.guqiang.ProgressToolbar;

public abstract class ActivityInviteBinding extends ViewDataBinding {
    public final RelativeLayout createEmail;
    public final TextView createEmailTitle;
    public final EditText etEmail;
    public final Button ibNext;
    @Bindable
    protected InviteModel mInviteModel;
    public final ProgressBar pbLoading;
    public final LinearLayout root;
    public final ProgressToolbar toolBar;

    public abstract void setInviteModel(InviteModel inviteModel);

    protected ActivityInviteBinding(Object obj, View view, int i, RelativeLayout relativeLayout, TextView textView, EditText editText, Button button, ProgressBar progressBar, LinearLayout linearLayout, ProgressToolbar progressToolbar) {
        super(obj, view, i);
        this.createEmail = relativeLayout;
        this.createEmailTitle = textView;
        this.etEmail = editText;
        this.ibNext = button;
        this.pbLoading = progressBar;
        this.root = linearLayout;
        this.toolBar = progressToolbar;
    }

    public InviteModel getInviteModel() {
        return this.mInviteModel;
    }

    public static ActivityInviteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInviteBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (ActivityInviteBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_invite, viewGroup, z, obj);
    }

    public static ActivityInviteBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInviteBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (ActivityInviteBinding) ViewDataBinding.inflateInternal(layoutInflater, C0997R.layout.activity_invite, (ViewGroup) null, false, obj);
    }

    public static ActivityInviteBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static ActivityInviteBinding bind(View view, Object obj) {
        return (ActivityInviteBinding) bind(obj, view, C0997R.layout.activity_invite);
    }
}
