package com.eternal.notification.p008ui;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

/* renamed from: com.eternal.notification.ui.OtherFragmentV4$$ARouter$$Autowired */
public class OtherFragmentV4$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        OtherFragmentV4 otherFragmentV4 = (OtherFragmentV4) obj;
        otherFragmentV4.groupId = otherFragmentV4.getArguments().getInt(ActivityEvent.NOTIFICATION_GROUP_ID);
    }
}
