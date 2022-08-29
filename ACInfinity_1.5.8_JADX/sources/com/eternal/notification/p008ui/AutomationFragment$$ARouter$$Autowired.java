package com.eternal.notification.p008ui;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

/* renamed from: com.eternal.notification.ui.AutomationFragment$$ARouter$$Autowired */
public class AutomationFragment$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        AutomationFragment automationFragment = (AutomationFragment) obj;
        automationFragment.deviceVersion = automationFragment.getArguments().getByte(ActivityEvent.DEVICE_VERSION);
        automationFragment.port = automationFragment.getArguments().getByte(ActivityEvent.DEVICE_PORT);
        automationFragment.deviceType = automationFragment.getArguments().getByte(ActivityEvent.DEVICE_TYPE);
        automationFragment.mac = automationFragment.getArguments().getString(ActivityEvent.DEVICE_MAC);
        automationFragment.isDegree = automationFragment.getArguments().getBoolean(ActivityEvent.DEVICE_DEGREE);
        automationFragment.onLevel = automationFragment.getArguments().getByte(ActivityEvent.DEVICE_MODEL_ON_LEVEL);
        automationFragment.offLevel = automationFragment.getArguments().getByte(ActivityEvent.DEVICE_MODEL_OFF_LEVEL);
    }
}
