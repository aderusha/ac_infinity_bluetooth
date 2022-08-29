package com.eternal.notification.p008ui;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

/* renamed from: com.eternal.notification.ui.AutomationFragmentV4$$ARouter$$Autowired */
public class AutomationFragmentV4$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        AutomationFragmentV4 automationFragmentV4 = (AutomationFragmentV4) obj;
        automationFragmentV4.deviceVersion = automationFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_VERSION);
        automationFragmentV4.port = automationFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_PORT);
        automationFragmentV4.deviceType = automationFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_TYPE);
        automationFragmentV4.mac = automationFragmentV4.getArguments().getString(ActivityEvent.DEVICE_MAC);
        automationFragmentV4.isDegree = automationFragmentV4.getArguments().getBoolean(ActivityEvent.DEVICE_DEGREE);
        automationFragmentV4.groupId = automationFragmentV4.getArguments().getInt(ActivityEvent.NOTIFICATION_GROUP_ID);
        automationFragmentV4.onLevel = automationFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_MODEL_ON_LEVEL);
        automationFragmentV4.offLevel = automationFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_MODEL_OFF_LEVEL);
    }
}
