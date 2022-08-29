package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.CommonObserver;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.RegexUtils;
import com.eternal.framework.utils.Utils;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.schedulers.Schedulers;

public class CreateModel extends BaseViewModel {
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    public MutableLiveData<String> deviceName = new MutableLiveData<>();
    public MutableLiveData<Integer> emailColor = new MutableLiveData<>();
    public MutableLiveData<String> emailErrText = new MutableLiveData<>();
    public MutableLiveData<Boolean> emailErrVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> emailLineColor = new MutableLiveData<>();
    public MutableLiveData<String> emailText = new MutableLiveData<>();
    public BindingCommand<String> emailTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            CreateModel createModel = CreateModel.this;
            createModel.onTextChanged(str, createModel.passwordText.getValue());
        }
    });
    public MutableLiveData<Integer> nextBackground = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            CreateModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCreate = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            CreateModel.this.register();
        }
    });
    public BindingCommand<Void> onPwdShow = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            CreateModel createModel = CreateModel.this;
            createModel.showPwd(createModel.textVisiblePassword.getValue() != null && !CreateModel.this.textVisiblePassword.getValue().booleanValue());
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
            CreateModel createModel = CreateModel.this;
            createModel.onTextChanged(createModel.emailText.getValue(), str);
        }
    });
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> textVisiblePassword = new MutableLiveData<>();

    public CreateModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void onTextChanged(String str, String str2) {
        boolean isEmail = RegexUtils.isEmail(str);
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(str2) && RegexUtils.isPassword(str2);
        if (isEmail && this.emailErrVisible.getValue() == Boolean.TRUE) {
            this.emailErrVisible.setValue(Boolean.FALSE);
            this.emailLineColor.setValue(this.emailColor.getValue());
        }
        if (z2 && this.pwdErrVisible.getValue() == Boolean.TRUE) {
            this.pwdErrVisible.setValue(Boolean.FALSE);
            this.pwdLineColor.setValue(this.pwdColor.getValue());
        }
        MutableLiveData<Boolean> mutableLiveData = this.confirmAble;
        if (!isEmail || TextUtils.isEmpty(str2) || str2.length() < 8) {
            z = false;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public void showPwd(boolean z) {
        this.textVisiblePassword.setValue(Boolean.valueOf(z));
        this.pwdShowText.setValue(z ? "HIDE" : "SHOW");
    }

    public void register() {
        String value = this.emailText.getValue();
        if (!RegexUtils.isPassword(this.passwordText.getValue())) {
            this.pwdErrText.setValue(Utils.getString(C0997R.string.password_format_error));
            this.pwdErrVisible.setValue(true);
            this.pwdLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_FF6A6A)));
            this.pwdColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
            return;
        }
        this.showLoading.setValue(true);
        ApiHelper.getAccountApi().getByUserEmail(value).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<BaseData<Void>>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                CreateModel.this.showLoading.setValue(false);
                CreateModel.this.pwdErrText.setValue(str);
                CreateModel.this.pwdErrVisible.setValue(true);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(BaseData<Void> baseData) {
                CreateModel.this.showLoading.setValue(false);
                if (baseData.getCode() == 200) {
                    CreateModel.this.pushToExistPage();
                } else if (baseData.getCode() == 201) {
                    CreateModel.this.pushToVerifyPage();
                } else {
                    CreateModel.this.pwdErrText.setValue(baseData.getMsg());
                    CreateModel.this.pwdErrVisible.setValue(true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToExistPage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_EXIST).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    /* access modifiers changed from: private */
    public void pushToVerifyPage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_VERIFY).withString("email", this.emailText.getValue()).withString(ActivityEvent.PASSWORD, this.passwordText.getValue()).withByte(ActivityEvent.FROM_PAGE, (byte) 32).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void init() {
        showPwd(false);
        this.emailLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.emailColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.pwdLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.pwdColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.confirmAble.setValue(Boolean.FALSE);
        this.showLoading.setValue(Boolean.FALSE);
    }
}
