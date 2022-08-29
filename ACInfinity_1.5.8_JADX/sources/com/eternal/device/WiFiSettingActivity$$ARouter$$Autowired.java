package com.eternal.device;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class WiFiSettingActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        WiFiSettingActivity wiFiSettingActivity = (WiFiSettingActivity) obj;
        wiFiSettingActivity.socketIp = wiFiSettingActivity.getIntent().getStringExtra(ActivityEvent.SOCKET_IP);
        wiFiSettingActivity.socketPort = wiFiSettingActivity.getIntent().getIntExtra(ActivityEvent.SOCKET_PORT, wiFiSettingActivity.socketPort);
        wiFiSettingActivity.ssid = wiFiSettingActivity.getIntent().getStringExtra(ActivityEvent.SSID);
        wiFiSettingActivity.deviceType = wiFiSettingActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, wiFiSettingActivity.deviceType);
        wiFiSettingActivity.mac = wiFiSettingActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        wiFiSettingActivity.typeName = wiFiSettingActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_TYPE_NAME);
    }
}
