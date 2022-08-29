package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.account.LoginActivity;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.UserInfo;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.RegexUtils;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.schedulers.Schedulers;

public class LoginModel extends BaseViewModel {
    public MutableLiveData<Boolean> afterLogout = new MutableLiveData<>();
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    public MutableLiveData<String> deviceName = new MutableLiveData<>();
    public MutableLiveData<Integer> emailColor = new MutableLiveData<>();
    public MutableLiveData<String> emailErrText = new MutableLiveData<>();
    public MutableLiveData<Boolean> emailErrVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> emailLineColor = new MutableLiveData<>();
    public MutableLiveData<String> emailText = new MutableLiveData<>();
    public BindingCommand<String> emailTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            LoginModel loginModel = LoginModel.this;
            loginModel.onTextChanged(str, loginModel.passwordText.getValue());
        }
    });
    public MutableLiveData<Integer> nextBackground = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (LoginModel.this.afterLogout.getValue() == Boolean.FALSE) {
                LoginModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
            }
        }
    });
    public BindingCommand<Void> onCreate = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LoginModel.this.pushToForgotPasswordPage(RouterActivityPath.Account.PAGE_CREATE);
        }
    });
    public BindingCommand<Void> onForgotPassword = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LoginModel.this.pushToForgotPasswordPage(RouterActivityPath.Account.PAGE_FORGOT_PASSWORD);
        }
    });
    public BindingCommand<Void> onLogin = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LoginModel.this.login();
        }
    });
    public BindingCommand<Void> onPwdShow = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LoginModel loginModel = LoginModel.this;
            loginModel.showPwd(loginModel.textVisiblePassword.getValue() != null && !LoginModel.this.textVisiblePassword.getValue().booleanValue());
        }
    });
    public BindingCommand<Void> onSkip = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (LoginModel.this.afterLogout.getValue() == Boolean.TRUE) {
                LoginModel.this.finishAnimate(C0997R.anim.right_in, C0997R.anim.left_out);
            }
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
            LoginModel loginModel = LoginModel.this;
            loginModel.onTextChanged(loginModel.emailText.getValue(), str);
        }
    });
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> textVisiblePassword = new MutableLiveData<>();

    public LoginModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void onTextChanged(String str, String str2) {
        boolean isEmail = RegexUtils.isEmail(str);
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(str2) && str2.length() >= 6;
        if (isEmail && this.emailErrVisible.getValue() == Boolean.TRUE) {
            this.emailErrVisible.setValue(Boolean.FALSE);
            this.emailLineColor.setValue(this.emailColor.getValue());
        }
        if (z2 && this.pwdErrVisible.getValue() == Boolean.TRUE) {
            this.pwdErrVisible.setValue(Boolean.FALSE);
            this.pwdLineColor.setValue(this.pwdColor.getValue());
        }
        MutableLiveData<Boolean> mutableLiveData = this.confirmAble;
        if (!isEmail || !z2) {
            z = false;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public void showPwd(boolean z) {
        this.textVisiblePassword.setValue(Boolean.valueOf(z));
        this.pwdShowText.setValue(z ? "HIDE" : "SHOW");
    }

    public void login() {
        this.showLoading.setValue(true);
        ApiHelper.getAccountApi().userLogin(this.emailText.getValue(), this.passwordText.getValue()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<UserInfo>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                LoginModel.this.showLoading.setValue(false);
                LoginModel.this.pwdErrText.setValue(str);
                LoginModel.this.pwdErrVisible.setValue(true);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(UserInfo userInfo) {
                LoginModel.this.showLoading.setValue(false);
                UserCache.getInstance().setToken(userInfo.getToken());
                UserCache.getInstance().setEmail(userInfo.getEmail());
                RxBus.getDefault().post(new ActivityEvent(20));
                LoginModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
                AppManager.getAppManager().finishActivity((Class<?>) LoginActivity.class);
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToForgotPasswordPage(String str) {
        ARouter.getInstance().build(str).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void init(boolean z) {
        showPwd(false);
        this.emailLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.emailColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.pwdLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.pwdColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.confirmAble.setValue(Boolean.FALSE);
        this.showLoading.setValue(Boolean.FALSE);
        this.afterLogout.setValue(Boolean.valueOf(z));
    }
}
