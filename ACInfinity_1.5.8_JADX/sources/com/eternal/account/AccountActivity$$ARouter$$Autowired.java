package com.eternal.account;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class AccountActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        AccountActivity accountActivity = (AccountActivity) obj;
        accountActivity.showShareDot = accountActivity.getIntent().getBooleanExtra(ActivityEvent.SHOW_SHARE_DOT, accountActivity.showShareDot);
    }
}
