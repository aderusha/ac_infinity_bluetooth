package com.eternal.device;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class WiFiListActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        WiFiListActivity wiFiListActivity = (WiFiListActivity) obj;
        wiFiListActivity.socketIp = wiFiListActivity.getIntent().getStringExtra(ActivityEvent.SOCKET_IP);
        wiFiListActivity.socketPort = wiFiListActivity.getIntent().getIntExtra(ActivityEvent.SOCKET_PORT, wiFiListActivity.socketPort);
    }
}
