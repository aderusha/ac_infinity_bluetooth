package com.eternal.control;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class ControlFragment$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        ControlFragment controlFragment = (ControlFragment) obj;
        controlFragment.mac = controlFragment.getArguments().getString(ActivityEvent.DEVICE_MAC);
        controlFragment.devId = controlFragment.getArguments().getString(ActivityEvent.DEVICE_ID);
        controlFragment.connectType = controlFragment.getArguments().getByte(ActivityEvent.DEVICE_CONNECT_TYPE);
    }
}
