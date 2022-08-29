package com.eternal.account.model;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.account.CreateActivity;
import com.eternal.account.LoginActivity;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.UserInfo;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.CommonObserver;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Observable;
import p014io.reactivex.Observer;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class VerifyModel extends BaseViewModel {
    public MutableLiveData<String> codeText = new MutableLiveData<>();
    public MutableLiveData<String> emailText = new MutableLiveData<>();
    public MutableLiveData<String> errText = new MutableLiveData<>();
    public MutableLiveData<Boolean> errVisible = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public byte from;
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            VerifyModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onSendCode = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            VerifyModel.this.sendCode();
        }
    });
    /* access modifiers changed from: private */
    public boolean onceFlag;
    private String password;
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    public BindingCommand<String> textChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            VerifyModel.this.onTextChanged(str);
        }
    });
    public MutableLiveData<String> timerText = new MutableLiveData<>();

    public VerifyModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void onTextChanged(String str) {
        if (str.length() == 6) {
            emailVerify(str);
        }
        if (this.errVisible.getValue() != Boolean.TRUE) {
            return;
        }
        if (this.onceFlag) {
            this.onceFlag = false;
        } else {
            this.errVisible.setValue(Boolean.FALSE);
        }
    }

    public void emailVerify(String str) {
        ApiHelper.getAccountApi().emailVerify(this.emailText.getValue(), str).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<BaseData<Void>>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                boolean unused = VerifyModel.this.onceFlag = true;
                VerifyModel.this.errText.setValue(str);
                VerifyModel.this.errVisible.setValue(Boolean.TRUE);
                VerifyModel.this.codeText.setValue("");
            }

            /* access modifiers changed from: protected */
            public void onSuccess(BaseData<Void> baseData) {
                if (baseData.getCode() != 200) {
                    boolean unused = VerifyModel.this.onceFlag = true;
                    VerifyModel.this.errText.setValue(baseData.getMsg());
                    VerifyModel.this.errVisible.setValue(Boolean.TRUE);
                    VerifyModel.this.codeText.setValue("");
                } else if (VerifyModel.this.from == 30) {
                    VerifyModel.this.pushToChangePWDPage();
                } else {
                    VerifyModel.this.register();
                }
            }
        });
    }

    public void sendCode() {
        startTime();
        ApiHelper.getAccountApi().sendEmail(this.emailText.getValue()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<BaseData<Void>>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                VerifyModel.this.errText.setValue(str);
                VerifyModel.this.errVisible.setValue(Boolean.TRUE);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(BaseData<Void> baseData) {
                if (baseData.getCode() != 200) {
                    VerifyModel.this.errText.setValue(baseData.getMsg());
                    VerifyModel.this.errVisible.setValue(Boolean.TRUE);
                }
            }
        });
    }

    public void register() {
        ApiHelper.getAccountApi().addAPPUser(this.emailText.getValue(), this.password).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<UserInfo>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                VerifyModel.this.errText.setValue(str);
                VerifyModel.this.errVisible.setValue(Boolean.TRUE);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(UserInfo userInfo) {
                UserCache.getInstance().setToken(userInfo.getToken());
                UserCache.getInstance().setEmail(userInfo.getEmail());
                VerifyModel.this.pushToPreferencesPage();
            }
        });
    }

    public void startTime() {
        Observable.interval(0, 1, TimeUnit.SECONDS).take(179).map(new Function<Long, Long>() {
            public Long apply(Long l) throws Exception {
                return Long.valueOf(180 - l.longValue());
            }
        }).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
                VerifyModel.this.showLoading.setValue(true);
            }
        }).subscribe(new Observer<Long>() {
            public void onError(Throwable th) {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onNext(Long l) {
                MutableLiveData<String> mutableLiveData = VerifyModel.this.timerText;
                mutableLiveData.setValue(l + "s");
            }

            public void onComplete() {
                VerifyModel.this.showLoading.setValue(false);
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToPreferencesPage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_PREFERENCE).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) LoginActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) CreateActivity.class);
                VerifyModel.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToChangePWDPage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_CHANGE_PASSWORD).withByte(ActivityEvent.FROM_PAGE, this.from).withString("email", this.emailText.getValue()).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
            }
        });
    }

    public void init(String str, String str2, byte b) {
        this.from = b;
        this.emailText.setValue(str);
        this.password = str2;
        sendCode();
    }
}
