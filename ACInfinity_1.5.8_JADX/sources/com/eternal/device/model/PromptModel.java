package com.eternal.device.model;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.device.C1922R;
import com.eternal.device.ChooseActivity;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import p014io.reactivex.functions.Consumer;

public class PromptModel extends BaseViewModel {
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            PromptModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(PromptModel.this, "show dialog");
        }
    });
    public BindingCommand<Void> onCreate = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            PromptModel.this.pushToCreatePage();
        }
    });
    public BindingCommand<Void> onLogin = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            PromptModel.this.pushToLogin();
        }
    });

    public PromptModel(Application application) {
        super(application);
    }

    public void init() {
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) {
                if (activityEvent.what == 12 && activityEvent.obj != null && RouterActivityPath.Device.PAGE_PROMPT.equals((String) activityEvent.obj)) {
                    PromptModel.this.finish();
                }
            }
        }));
    }

    /* access modifiers changed from: private */
    public void pushToCreatePage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_CREATE).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToLogin() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_LOGIN).withBoolean(ActivityEvent.AFTER_LOGOUT, false).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
            }
        });
    }

    public void pushToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1922R.anim.left_in, C1922R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) ChooseActivity.class);
                PromptModel.this.finish();
            }
        });
    }
}
