package com.eternal.account;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class SearchActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        SearchActivity searchActivity = (SearchActivity) obj;
        searchActivity.devId = searchActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_ID);
        searchActivity.timeZone = searchActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_TIME_ZONE);
    }
}
