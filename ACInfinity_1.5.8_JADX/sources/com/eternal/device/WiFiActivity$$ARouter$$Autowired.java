package com.eternal.device;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class WiFiActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        WiFiActivity wiFiActivity = (WiFiActivity) obj;
        wiFiActivity.socketIp = wiFiActivity.getIntent().getStringExtra(ActivityEvent.SOCKET_IP);
        wiFiActivity.socketPort = wiFiActivity.getIntent().getIntExtra(ActivityEvent.SOCKET_PORT, wiFiActivity.socketPort);
    }
}
