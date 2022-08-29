package com.eternal.account.model;

import android.app.Application;
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
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.schedulers.Schedulers;

public class ResetPasswordModel extends BaseViewModel {
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    public MutableLiveData<Integer> emailColor = new MutableLiveData<>();
    public MutableLiveData<String> emailErrText = new MutableLiveData<>();
    public MutableLiveData<Boolean> emailErrVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> emailLineColor = new MutableLiveData<>();
    public MutableLiveData<String> emailText = new MutableLiveData<>();
    public BindingCommand<String> emailTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            ResetPasswordModel.this.onTextChanged(str);
        }
    });
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ResetPasswordModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ResetPasswordModel.this.accountExist();
        }
    });
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();

    public ResetPasswordModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void onTextChanged(String str) {
        boolean isEmail = RegexUtils.isEmail(str);
        if (isEmail && this.emailErrVisible.getValue() == Boolean.TRUE) {
            this.emailErrVisible.setValue(Boolean.FALSE);
            this.emailLineColor.setValue(this.emailColor.getValue());
        }
        this.confirmAble.setValue(Boolean.valueOf(isEmail));
    }

    public void accountExist() {
        this.showLoading.setValue(true);
        ApiHelper.getAccountApi().getByUserEmail(this.emailText.getValue()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<BaseData<Void>>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                ResetPasswordModel.this.showLoading.setValue(false);
                ResetPasswordModel.this.emailErrText.setValue(str);
                ResetPasswordModel.this.emailErrVisible.setValue(true);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(BaseData<Void> baseData) {
                ResetPasswordModel.this.showLoading.setValue(false);
                if (baseData.getCode() == 200) {
                    ResetPasswordModel.this.pushToVerifyPage();
                } else if (baseData.getCode() == 201) {
                    ResetPasswordModel.this.emailErrText.setValue("No account exists for this email. Please create an account.");
                    ResetPasswordModel.this.emailErrVisible.setValue(true);
                } else {
                    ResetPasswordModel.this.emailErrText.setValue(baseData.getMsg());
                    ResetPasswordModel.this.emailErrVisible.setValue(true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToVerifyPage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_VERIFY).withByte(ActivityEvent.FROM_PAGE, (byte) 30).withString("email", this.emailText.getValue()).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void init() {
        this.emailLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.emailColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C0997R.C0998color.color_838383)));
        this.confirmAble.setValue(Boolean.FALSE);
        this.showLoading.setValue(Boolean.FALSE);
    }
}
