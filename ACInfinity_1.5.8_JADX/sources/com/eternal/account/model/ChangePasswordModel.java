package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.eternal.account.AccountActivity;
import com.eternal.account.C0997R;
import com.eternal.account.ResetPasswordActivity;
import com.eternal.account.VerifyActivity;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.RegexUtils;
import com.eternal.framework.utils.Utils;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.schedulers.Schedulers;

public class ChangePasswordModel extends BaseViewModel {
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    public MutableLiveData<String> deviceName = new MutableLiveData<>();
    private String email;
    /* access modifiers changed from: private */
    public int from;
    public MutableLiveData<Integer> newPwdColor = new MutableLiveData<>();
    public MutableLiveData<String> newPwdErrText = new MutableLiveData<>();
    public MutableLiveData<Boolean> newPwdErrVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> newPwdLineColor = new MutableLiveData<>();
    public MutableLiveData<String> newPwdText = new MutableLiveData<>();
    public BindingCommand<String> newPwdTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            ChangePasswordModel changePasswordModel = ChangePasswordModel.this;
            changePasswordModel.onTextChanged(str, changePasswordModel.passwordText.getValue());
        }
    });
    public MutableLiveData<Integer> nextBackground = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChangePasswordModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onChange = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChangePasswordModel.this.change();
        }
    });
    public BindingCommand<Void> onPwdShow = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ChangePasswordModel changePasswordModel = ChangePasswordModel.this;
            changePasswordModel.showPwd(changePasswordModel.textVisiblePassword.getValue() != null && !ChangePasswordModel.this.textVisiblePassword.getValue().booleanValue());
        }
    });
    public MutableLiveData<String> passwordText = new MutableLiveData<>();
    public MutableLiveData<Integer> pwdColor = new MutableLiveData<>();
    public MutableLiveData<String> pwdErrText = new MutableLiveData<>();
    public MutableLiveData<Boolean> pwdErrVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> pwdLineColor = new MutableLiveData<>();
    public MutableLiveData<String> pwdShowText = new MutableLiveData<>();
    public BindingCommand<String> pwdTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            ChangePasswordModel changePasswordModel = ChangePasswordModel.this;
            changePasswordModel.onTextChanged(changePasswordModel.newPwdText.getValue(), str);
        }
    });
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> textVisiblePassword = new MutableLiveData<>();

    public ChangePasswordModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void onTextChanged(String str, String str2) {
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(str) && RegexUtils.isPassword(str);
        if (z2 && this.newPwdErrVisible.getValue() == Boolean.TRUE) {
            this.newPwdErrVisible.setValue(Boolean.FALSE);
            this.newPwdLineColor.setValue(this.newPwdColor.getValue());
        }
        boolean z3 = !TextUtils.isEmpty(str2) && RegexUtils.isPassword(str2);
        if (z3 && this.pwdErrVisible.getValue() == Boolean.TRUE) {
            this.pwdErrVisible.setValue(Boolean.FALSE);
            this.pwdLineColor.setValue(this.pwdColor.getValue());
        }
        MutableLiveData<Boolean> mutableLiveData = this.confirmAble;
        if (!z2 || !z3) {
            z = false;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public void showPwd(boolean z) {
        this.textVisiblePassword.setValue(Boolean.valueOf(z));
        this.pwdShowText.setValue(z ? "HIDE" : "SHOW");
    }

    public void change() {
        String value = this.passwordText.getValue();
        if (!this.newPwdText.getValue().equals(value)) {
            this.pwdErrText.setValue("The two passwords you entered were inconsistent");
            this.pwdErrVisible.setValue(true);
        } else if (!RegexUtils.isPassword(value)) {
            this.pwdErrText.setValue(Utils.getString(C0997R.string.password_format_error));
            this.pwdErrVisible.setValue(true);
            this.pwdLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_FF6A6A)));
            this.pwdColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        } else {
            this.showLoading.setValue(true);
            ApiHelper.getAccountApi().updatePassword(this.email, value).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                /* access modifiers changed from: protected */
                public void onError(String str) {
                    ChangePasswordModel.this.showLoading.setValue(false);
                    ChangePasswordModel.this.pwdErrText.setValue(str);
                    ChangePasswordModel.this.pwdErrVisible.setValue(true);
                }

                /* access modifiers changed from: protected */
                public void onSuccess(Void voidR) {
                    ChangePasswordModel.this.showLoading.setValue(false);
                    if (ChangePasswordModel.this.from == 30) {
                        AppManager.getAppManager().finishActivity((Class<?>) ResetPasswordActivity.class);
                        AppManager.getAppManager().finishActivity((Class<?>) VerifyActivity.class);
                        ChangePasswordModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
                        return;
                    }
                    AppManager.getAppManager().finishActivity((Class<?>) AccountActivity.class);
                    ChangePasswordModel.this.logout();
                }
            });
        }
    }

    public void logout() {
        RxBus.getDefault().post(new ActivityEvent(21, false));
        finish();
    }

    public void init(String str, byte b) {
        this.email = str;
        this.from = b;
        showPwd(true);
        this.newPwdLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.newPwdColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.pwdLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.pwdColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.confirmAble.setValue(Boolean.FALSE);
        this.showLoading.setValue(Boolean.FALSE);
    }
}
