package com.eternal.device;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class WiFiConnectActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        WiFiConnectActivity wiFiConnectActivity = (WiFiConnectActivity) obj;
        wiFiConnectActivity.socketIp = wiFiConnectActivity.getIntent().getStringExtra(ActivityEvent.SOCKET_IP);
        wiFiConnectActivity.socketPort = wiFiConnectActivity.getIntent().getIntExtra(ActivityEvent.SOCKET_PORT, wiFiConnectActivity.socketPort);
        wiFiConnectActivity.ssid = wiFiConnectActivity.getIntent().getStringExtra(ActivityEvent.SSID);
        wiFiConnectActivity.deviceType = wiFiConnectActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, wiFiConnectActivity.deviceType);
        wiFiConnectActivity.mac = wiFiConnectActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        wiFiConnectActivity.typeName = wiFiConnectActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_TYPE_NAME);
    }
}
