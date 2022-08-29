package com.eternal.account.model;

import android.app.Application;
import android.content.Context;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.account.CreateActivity;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;

public class ExistModel extends BaseViewModel {
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ExistModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_LOGIN).withBoolean(ActivityEvent.AFTER_LOGOUT, false).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
                public void onArrival(Postcard postcard) {
                    ExistModel.this.finish();
                    AppManager.getAppManager().finishActivity((Class<?>) CreateActivity.class);
                }
            });
        }
    });

    public void init() {
    }

    public ExistModel(Application application) {
        super(application);
    }
}
