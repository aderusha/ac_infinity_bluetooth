package com.eternal.account;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.URLSpan;
import android.view.View;
import com.eternal.account.databinding.ActivityCreateBinding;
import com.eternal.account.model.CreateModel;
import com.eternal.base.BaseActivity;
import com.eternal.base.config.AppUrlConfig;
import com.eternal.base.utils.SoftKeyBroadManager;
import com.eternal.framework.utils.RegexUtils;
import com.eternal.framework.utils.Utils;

public class CreateActivity extends BaseActivity<ActivityCreateBinding, CreateModel> {
    SoftKeyBroadManager.SoftKeyboardStateListener softKeyboardStateListener = new SoftKeyBroadManager.SoftKeyboardStateListener() {
        public void onSoftKeyboardOpened(int i) {
            ((ActivityCreateBinding) CreateActivity.this.binding).ibNext.requestLayout();
        }

        public void onSoftKeyboardClosed() {
            ((ActivityCreateBinding) CreateActivity.this.binding).ibNext.requestLayout();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((CreateModel) this.viewModel).init();
        initView();
    }

    public int initContentView(Bundle bundle) {
        return C0997R.layout.activity_create;
    }

    public int initVariableId() {
        return C0977BR.createModel;
    }

    private void initView() {
        new SoftKeyBroadManager(((ActivityCreateBinding) this.binding).root).addSoftKeyboardStateListener(this.softKeyboardStateListener);
        SpannableString spannableString = new SpannableString("By creating an account, you are agree to AC Infinity’s Terms of Use and Privacy Policy.");
        spannableString.setSpan(new StyleSpan(1), 0, 39, 18);
        spannableString.setSpan(new URLSpan(AppUrlConfig.terms_of_use), 55, 67, 33);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C0997R.C0998color.color_15BAFF)), 55, 67, 33);
        spannableString.setSpan(new URLSpan(AppUrlConfig.privacy_policy), 72, 86, 33);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(C0997R.C0998color.color_15BAFF)), 72, 86, 33);
        ((ActivityCreateBinding) this.binding).createTipsPrivacy.setText(spannableString);
        ((ActivityCreateBinding) this.binding).createTipsPrivacy.setMovementMethod(LinkMovementMethod.getInstance());
        ((ActivityCreateBinding) this.binding).etEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityCreateBinding) CreateActivity.this.binding).etEmail.getId()) {
                    return;
                }
                if (z) {
                    ((CreateModel) CreateActivity.this.viewModel).emailColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    if (((CreateModel) CreateActivity.this.viewModel).emailErrVisible.getValue() != Boolean.TRUE) {
                        ((CreateModel) CreateActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.white)));
                        return;
                    }
                    return;
                }
                String value = ((CreateModel) CreateActivity.this.viewModel).emailText.getValue();
                if (TextUtils.isEmpty(value)) {
                    ((CreateModel) CreateActivity.this.viewModel).emailErrVisible.setValue(false);
                    ((CreateModel) CreateActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                    ((CreateModel) CreateActivity.this.viewModel).emailColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                } else if (RegexUtils.isEmail(value)) {
                    ((CreateModel) CreateActivity.this.viewModel).emailErrVisible.setValue(false);
                    ((CreateModel) CreateActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    ((CreateModel) CreateActivity.this.viewModel).emailColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.white)));
                } else {
                    ((CreateModel) CreateActivity.this.viewModel).emailErrText.setValue("Please enter a valid email address.");
                    ((CreateModel) CreateActivity.this.viewModel).emailErrVisible.setValue(true);
                    ((CreateModel) CreateActivity.this.viewModel).emailLineColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.color_FF6A6A)));
                    ((CreateModel) CreateActivity.this.viewModel).emailColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                }
            }
        });
        ((ActivityCreateBinding) this.binding).etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (view.getId() != ((ActivityCreateBinding) CreateActivity.this.binding).etPwd.getId()) {
                    return;
                }
                if (z) {
                    ((CreateModel) CreateActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    if (((CreateModel) CreateActivity.this.viewModel).pwdErrVisible.getValue() != Boolean.TRUE) {
                        ((CreateModel) CreateActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.white)));
                        return;
                    }
                    return;
                }
                String value = ((CreateModel) CreateActivity.this.viewModel).passwordText.getValue();
                if (TextUtils.isEmpty(value)) {
                    ((CreateModel) CreateActivity.this.viewModel).pwdErrVisible.setValue(false);
                    ((CreateModel) CreateActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                    ((CreateModel) CreateActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                } else if (value.length() >= 8) {
                    ((CreateModel) CreateActivity.this.viewModel).pwdErrVisible.setValue(false);
                    ((CreateModel) CreateActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.white)));
                    ((CreateModel) CreateActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.white)));
                } else {
                    ((CreateModel) CreateActivity.this.viewModel).pwdErrText.setValue(Utils.getString(C0997R.string.password_format_error));
                    ((CreateModel) CreateActivity.this.viewModel).pwdErrVisible.setValue(true);
                    ((CreateModel) CreateActivity.this.viewModel).pwdLineColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.color_FF6A6A)));
                    ((CreateModel) CreateActivity.this.viewModel).pwdColor.setValue(Integer.valueOf(CreateActivity.this.getResources().getColor(C0997R.C0998color.color_838383)));
                }
            }
        });
    }

    public void onBackPressed() {
        ((CreateModel) this.viewModel).onBack.execute();
    }
}
