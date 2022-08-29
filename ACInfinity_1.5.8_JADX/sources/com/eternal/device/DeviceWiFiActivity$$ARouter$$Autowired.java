package com.eternal.device;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class DeviceWiFiActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        DeviceWiFiActivity deviceWiFiActivity = (DeviceWiFiActivity) obj;
        deviceWiFiActivity.mac = deviceWiFiActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        deviceWiFiActivity.deviceType = deviceWiFiActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, deviceWiFiActivity.deviceType);
        deviceWiFiActivity.typeName = deviceWiFiActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_TYPE_NAME);
    }
}
