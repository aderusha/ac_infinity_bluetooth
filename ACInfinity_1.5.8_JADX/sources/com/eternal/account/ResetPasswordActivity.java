package com.eternal.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.eternal.account.databinding.ActivityResetPasswordBinding;
import com.eternal.account.model.ResetPasswordModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.utils.RegexUtils;

public class ResetPasswordActivity extends BaseActivity<ActivityResetPasswordBinding, ResetPasswordModel> {
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((ActivityResetPasswordBinding) ResetPasswordActivity.this.binding).ibNext.requestLayout();
        }

        public void onSoftKeyboardClosed() {
            ((ActivityResetPasswordBinding) ResetPasswordActivity.this.binding).ibNext.requestLayout();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((ResetPasswordModel) this.viewModel).init();
        initView();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_reset_password;
    }

    public int initVariableId() {
        return C0977BR.resetModel;
    }

    private void initView() {
        new SoftKeyBroadManager(((ActivityResetPasswordBinding) this.binding).root).addSoftKeyboardStateListener(this.softKeyboardStateListener);
        ((ActivityResetPasswordBinding) this.binding).etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityResetPasswordBinding) ResetPasswordActivity.this.binding).etEmail.getId()) {
                    return;
                }
                if (z) {
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailColor.setValue(Integer.valueOf(ResetPasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    if (((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailErrVisible.getValue() != Boolean.TRUE) {
                        ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(ResetPasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                        return;
                    }
                    return;
                }
                String value = ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailText.getValue();
                if (TextUtils.isEmpty(value)) {
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailErrVisible.setValue(false);
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(ResetPasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailColor.setValue(Integer.valueOf(ResetPasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                } else if (RegexUtils.isEmail(value)) {
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailErrVisible.setValue(false);
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(ResetPasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailColor.setValue(Integer.valueOf(ResetPasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                } else {
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailErrText.setValue("Please enter a valid email address.");
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailErrVisible.setValue(true);
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(ResetPasswordActivity.this.getResources().getColor(C0997R.C0998color.color_FF6A6A)));
                    ((ResetPasswordModel) ResetPasswordActivity.this.viewModel).emailColor.setValue(Integer.valueOf(ResetPasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                }
            }
        });
    }

    public void onBackPressed() {
        ((ResetPasswordModel) this.viewModel).onBack.execute();
    }
}
