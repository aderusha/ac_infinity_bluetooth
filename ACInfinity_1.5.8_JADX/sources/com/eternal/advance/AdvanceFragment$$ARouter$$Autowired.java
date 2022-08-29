package com.eternal.advance;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class AdvanceFragment$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        AdvanceFragment advanceFragment = (AdvanceFragment) obj;
        advanceFragment.mac = advanceFragment.getArguments().getString(ActivityEvent.DEVICE_MAC);
        advanceFragment.devId = advanceFragment.getArguments().getString(ActivityEvent.DEVICE_ID);
        advanceFragment.port = advanceFragment.getArguments().getByte(ActivityEvent.DEVICE_PORT);
        advanceFragment.deviceType = advanceFragment.getArguments().getByte(ActivityEvent.DEVICE_TYPE);
        advanceFragment.deviceVersion = advanceFragment.getArguments().getByte(ActivityEvent.DEVICE_VERSION);
        advanceFragment.connectType = advanceFragment.getArguments().getByte(ActivityEvent.DEVICE_CONNECT_TYPE);
    }
}
