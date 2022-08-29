package com.eternal.account;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class VersionActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        VersionActivity versionActivity = (VersionActivity) obj;
        versionActivity.isAppUpdate = versionActivity.getIntent().getBooleanExtra(ActivityEvent.IS_APP_UPDATE, versionActivity.isAppUpdate);
        versionActivity.updateVersion = versionActivity.getIntent().getStringExtra(ActivityEvent.UPDATE_VERSION);
        versionActivity.softwareVersion = versionActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_SOFTWARE_VERSION);
        versionActivity.hardwareVersion = versionActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_HARDWARE_VERSION);
        versionActivity.firmwareVersion = versionActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_FIRMWARE_VERSION);
        versionActivity.type = versionActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, versionActivity.type);
        versionActivity.port = versionActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_PORT, versionActivity.port);
        versionActivity.deviceType = versionActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, versionActivity.deviceType);
        versionActivity.mac = versionActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        versionActivity.firmwareUrl = versionActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_FIRMWARE_URL);
    }
}
