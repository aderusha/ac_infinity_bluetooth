package com.eternal.account.model;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.base.config.AppUrlConfig;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;

public class LegalInformationModel extends BaseViewModel {
    public BindingCommand<Void> onAgreement = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LegalInformationModel.this.openBrowser(AppUrlConfig.user_agreement);
        }
    });
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LegalInformationModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onPrivacy = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LegalInformationModel.this.openBrowser(AppUrlConfig.privacy_policy);
        }
    });
    public BindingCommand<Void> onTerms = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LegalInformationModel.this.openBrowser(AppUrlConfig.terms_of_use);
        }
    });

    public void init() {
    }

    public LegalInformationModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void openBrowser(String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.addFlags(268435456);
        startActivity(intent);
    }

    private void pushToPage(String str) {
        ARouter.getInstance().build(str).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }
}
