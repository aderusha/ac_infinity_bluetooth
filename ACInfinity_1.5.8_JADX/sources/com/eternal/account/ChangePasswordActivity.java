package com.eternal.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.eternal.account.databinding.ActivityChangePasswordBinding;
import com.eternal.account.model.ChangePasswordModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.utils.RegexUtils;
import com.eternal.framework.utils.Utils;

public class ChangePasswordActivity extends BaseActivity<ActivityChangePasswordBinding, ChangePasswordModel> {
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((ActivityChangePasswordBinding) ChangePasswordActivity.this.binding).ibNext.requestLayout();
        }

        public void onSoftKeyboardClosed() {
            ((ActivityChangePasswordBinding) ChangePasswordActivity.this.binding).ibNext.requestLayout();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        byte byteExtra = intent.getByteExtra(ActivityEvent.FROM_PAGE, (byte) 30);
        ((ChangePasswordModel) this.viewModel).init(intent.getStringExtra("email"), byteExtra);
        initView();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_change_password;
    }

    public int initVariableId() {
        return C0977BR.changeModel;
    }

    private void initView() {
        new SoftKeyBroadManager(((ActivityChangePasswordBinding) this.binding).root).addSoftKeyboardStateListener(this.softKeyboardStateListener);
        ((ActivityChangePasswordBinding) this.binding).etNewPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityChangePasswordBinding) ChangePasswordActivity.this.binding).etNewPwd.getId()) {
                    return;
                }
                if (z) {
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    if (((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdErrVisible.getValue() != Boolean.TRUE) {
                        ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdLineColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                        return;
                    }
                    return;
                }
                String value = ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdText.getValue();
                if (TextUtils.isEmpty(value)) {
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdErrVisible.setValue(false);
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdLineColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                } else if (RegexUtils.isPassword(value)) {
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdErrVisible.setValue(false);
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdLineColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                } else {
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdErrText.setValue(Utils.getString(C0997R.string.password_format_error));
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdErrVisible.setValue(true);
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdLineColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.color_FF6A6A)));
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).newPwdColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                }
            }
        });
        ((ActivityChangePasswordBinding) this.binding).etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityChangePasswordBinding) ChangePasswordActivity.this.binding).etPwd.getId()) {
                    return;
                }
                if (z) {
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    if (((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdErrVisible.getValue() != Boolean.TRUE) {
                        ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                        return;
                    }
                    return;
                }
                String value = ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).passwordText.getValue();
                if (TextUtils.isEmpty(value)) {
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdErrVisible.setValue(false);
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                } else if (RegexUtils.isPassword(value)) {
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdErrVisible.setValue(false);
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.white)));
                } else {
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdErrText.setValue(Utils.getString(C0997R.string.password_format_error));
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdErrVisible.setValue(true);
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.color_FF6A6A)));
                    ((ChangePasswordModel) ChangePasswordActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(ChangePasswordActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                }
            }
        });
    }

    public void onBackPressed() {
        ((ChangePasswordModel) this.viewModel).onBack.execute();
    }
}
