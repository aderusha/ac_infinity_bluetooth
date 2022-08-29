package com.eternal.about;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.Utils;

public class AboutModel extends BaseViewModel {
    private static final String PURCHASE_URL = "https://www.acinfinity.com/";
    private static final String SUPPORT_URL = "https://www.acinfinity.com/pages/support/contact-us.html";
    private static final String TEAMS_URL = "https://www.acinfinity.com/pages/terms-of-use.html";
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C0969R.anim.right_in, C0969R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
                public void onArrival(Postcard postcard) {
                    AboutModel.this.finish();
                }
            });
        }
    });
    public BindingCommand<Void> onPurchase = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AboutModel.this.startUrl(AboutModel.PURCHASE_URL);
        }
    });
    public BindingCommand<Void> onSupport = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AboutModel.this.startUrl(AboutModel.SUPPORT_URL);
        }
    });
    public BindingCommand<Void> onTeams = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AboutModel.this.startUrl(AboutModel.TEAMS_URL);
        }
    });

    public AboutModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void startUrl(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        intent.setData(Uri.parse(str));
        Utils.getContext().startActivity(intent);
    }
}
