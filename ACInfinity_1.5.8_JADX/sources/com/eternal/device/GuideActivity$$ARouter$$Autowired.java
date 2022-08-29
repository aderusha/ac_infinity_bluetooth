package com.eternal.device;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class GuideActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        GuideActivity guideActivity = (GuideActivity) obj;
        guideActivity.socketIp = guideActivity.getIntent().getStringExtra(ActivityEvent.SOCKET_IP);
        guideActivity.socketPort = guideActivity.getIntent().getIntExtra(ActivityEvent.SOCKET_PORT, guideActivity.socketPort);
        guideActivity.pageType = guideActivity.getIntent().getByteExtra("page type", guideActivity.pageType);
        guideActivity.deviceType = guideActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, guideActivity.deviceType);
        guideActivity.deviceModel = guideActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_MODEL, guideActivity.deviceModel);
    }
}
