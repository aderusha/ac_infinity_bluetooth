package com.eternal.account;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import com.eternal.account.databinding.ActivityLoginBinding;
import com.eternal.account.model.LoginModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.utils.RegexUtils;
import com.eternal.framework.utils.Utils;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginModel> {
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((ActivityLoginBinding) LoginActivity.this.binding).ibNext.requestLayout();
        }

        public void onSoftKeyboardClosed() {
            ((ActivityLoginBinding) LoginActivity.this.binding).ibNext.requestLayout();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        boolean booleanExtra = getIntent().getBooleanExtra(ActivityEvent.AFTER_LOGOUT, false);
        ((LoginModel) this.viewModel).init(booleanExtra);
        initView(booleanExtra);
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_login;
    }

    public int initVariableId() {
        return C0977BR.loginModel;
    }

    private void initView(boolean z) {
        if (z) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ((ActivityLoginBinding) this.binding).createEmail.getLayoutParams();
            layoutParams.topMargin = getResources().getDimensionPixelSize(C0997R.dimen.dp_94);
            ((ActivityLoginBinding) this.binding).createEmail.setLayoutParams(layoutParams);
        }
        new SoftKeyBroadManager(((ActivityLoginBinding) this.binding).root).addSoftKeyboardStateListener(this.softKeyboardStateListener);
        ((ActivityLoginBinding) this.binding).etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityLoginBinding) LoginActivity.this.binding).etEmail.getId()) {
                    return;
                }
                if (z) {
                    ((LoginModel) LoginActivity.this.viewModel).emailColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    if (((LoginModel) LoginActivity.this.viewModel).emailErrVisible.getValue() != Boolean.TRUE) {
                        ((LoginModel) LoginActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.white)));
                        return;
                    }
                    return;
                }
                String value = ((LoginModel) LoginActivity.this.viewModel).emailText.getValue();
                if (TextUtils.isEmpty(value)) {
                    ((LoginModel) LoginActivity.this.viewModel).emailErrVisible.setValue(false);
                    ((LoginModel) LoginActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                    ((LoginModel) LoginActivity.this.viewModel).emailColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                } else if (RegexUtils.isEmail(value)) {
                    ((LoginModel) LoginActivity.this.viewModel).emailErrVisible.setValue(false);
                    ((LoginModel) LoginActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    ((LoginModel) LoginActivity.this.viewModel).emailColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.white)));
                } else {
                    ((LoginModel) LoginActivity.this.viewModel).emailErrText.setValue("Please enter a valid email address.");
                    ((LoginModel) LoginActivity.this.viewModel).emailErrVisible.setValue(true);
                    ((LoginModel) LoginActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.color_FF6A6A)));
                    ((LoginModel) LoginActivity.this.viewModel).emailColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                }
            }
        });
        ((ActivityLoginBinding) this.binding).etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityLoginBinding) LoginActivity.this.binding).etPwd.getId()) {
                    return;
                }
                if (z) {
                    ((LoginModel) LoginActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    if (((LoginModel) LoginActivity.this.viewModel).pwdErrVisible.getValue() != Boolean.TRUE) {
                        ((LoginModel) LoginActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.white)));
                        return;
                    }
                    return;
                }
                String value = ((LoginModel) LoginActivity.this.viewModel).passwordText.getValue();
                if (TextUtils.isEmpty(value)) {
                    ((LoginModel) LoginActivity.this.viewModel).pwdErrVisible.setValue(false);
                    ((LoginModel) LoginActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                    ((LoginModel) LoginActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                } else if (value.length() >= 6) {
                    ((LoginModel) LoginActivity.this.viewModel).pwdErrVisible.setValue(false);
                    ((LoginModel) LoginActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    ((LoginModel) LoginActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.white)));
                } else {
                    ((LoginModel) LoginActivity.this.viewModel).pwdErrText.setValue(Utils.getString(C0997R.string.password_incorrect));
                    ((LoginModel) LoginActivity.this.viewModel).pwdErrVisible.setValue(true);
                    ((LoginModel) LoginActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.color_FF6A6A)));
                    ((LoginModel) LoginActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(LoginActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                }
            }
        });
    }

    public void onBackPressed() {
        ((LoginModel) this.viewModel).onBack.execute();
    }
}
