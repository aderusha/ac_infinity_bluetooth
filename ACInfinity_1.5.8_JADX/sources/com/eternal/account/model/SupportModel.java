package com.eternal.account.model;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.base.config.AppUrlConfig;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;

public class SupportModel extends BaseViewModel {
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SupportModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onFAQ = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SupportModel.this.openBrowser(AppUrlConfig.FAQ);
        }
    });
    public BindingCommand<Void> onFeedback = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SupportModel.this.pushToPage(RouterActivityPath.Account.PAGE_FEEDBACK);
        }
    });
    public BindingCommand<Void> onGrowing = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SupportModel.this.openBrowser(AppUrlConfig.growing_guide);
        }
    });
    public BindingCommand<Void> onPurchase = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SupportModel.this.openBrowser(AppUrlConfig.purchase_products);
        }
    });
    public BindingCommand<Void> onTechnicalGuides = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SupportModel.this.pushToPage(RouterActivityPath.Account.PAGE_TECHNICAL_GUIDES);
        }
    });
    public BindingCommand<Void> onUsing = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SupportModel.this.openBrowser(AppUrlConfig.using_the_c_infinity_app);
        }
    });

    public void init() {
    }

    public SupportModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void openBrowser(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void pushToPage(String str) {
        ARouter.getInstance().build(str).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }
}
