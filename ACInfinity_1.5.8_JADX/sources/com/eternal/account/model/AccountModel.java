package com.eternal.account.model;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.AccountActivity;
import com.eternal.account.C0997R;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class AccountModel extends BaseViewModel {
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AccountModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onChangePassword = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AccountModel.this.pushToChangePWDPage();
        }
    });
    public BindingCommand<Void> onDeleteAccount = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().sendNoMsg(AccountActivity.SHOW_DELETE_ACCOUNT_DIALOG);
        }
    });
    public BindingCommand<Void> onPrivacy = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_PREFERENCE).withBoolean(ActivityEvent.IS_PRIVACY, true).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onShare = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_SHARE).withBoolean(ActivityEvent.SHOW_SHARE_DOT, AccountModel.this.showShareDot.getValue() == Boolean.TRUE).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onTimeZone = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_TIME_ZONE).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public MutableLiveData<Boolean> showShareDot = new MutableLiveData<>();

    public AccountModel(Application application) {
        super(application);
    }

    private void pushToPage(String str) {
        ARouter.getInstance().build(str).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void deleteAccount() {
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            ApiHelper.getAccountApi().delUserInfo(token).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                public void doOnSubscribe(Disposable disposable) {
                    AccountModel.this.showDialog((String) null, disposable);
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    AccountModel.this.showFailDialog(str);
                }

                /* access modifiers changed from: protected */
                public void onSuccess(Void voidR) {
                    AccountModel.this.dismissDialog();
                    RxBus.getDefault().post(new ActivityEvent(21, true));
                    AccountModel.this.onBack.execute();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void pushToChangePWDPage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_CHANGE_PASSWORD).withByte(ActivityEvent.FROM_PAGE, (byte) 31).withString("email", UserCache.getInstance().getEmail()).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
            }
        });
    }

    public void registerEventBus() {
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) {
                if (activityEvent.what == 40) {
                    AccountModel.this.showShareDot.setValue(Boolean.valueOf(((Boolean) activityEvent.obj).booleanValue()));
                }
            }
        }));
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        Messenger.getDefault().unregister(this);
    }

    public void init(boolean z) {
        this.showShareDot.setValue(Boolean.valueOf(z));
        registerEventBus();
    }
}
