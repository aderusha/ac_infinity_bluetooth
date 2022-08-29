package com.eternal.advance;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class AdvanceFragmentV4$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        AdvanceFragmentV4 advanceFragmentV4 = (AdvanceFragmentV4) obj;
        advanceFragmentV4.mac = advanceFragmentV4.getArguments().getString(ActivityEvent.DEVICE_MAC);
        advanceFragmentV4.devId = advanceFragmentV4.getArguments().getString(ActivityEvent.DEVICE_ID);
        advanceFragmentV4.port = advanceFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_PORT);
        advanceFragmentV4.deviceType = advanceFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_TYPE);
        advanceFragmentV4.deviceVersion = advanceFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_VERSION);
        advanceFragmentV4.connectType = advanceFragmentV4.getArguments().getByte(ActivityEvent.DEVICE_CONNECT_TYPE);
    }
}
