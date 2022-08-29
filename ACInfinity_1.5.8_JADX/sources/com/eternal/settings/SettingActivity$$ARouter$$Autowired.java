package com.eternal.settings;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class SettingActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        SettingActivity settingActivity = (SettingActivity) obj;
        settingActivity.softwareVersion = settingActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_SOFTWARE_VERSION);
        settingActivity.hardwareVersion = settingActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_HARDWARE_VERSION);
        settingActivity.firmwareVersion = settingActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_FIRMWARE_VERSION);
        settingActivity.mac = settingActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        settingActivity.typeOn = settingActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_MODEL_ON_LEVEL, settingActivity.typeOn);
        settingActivity.typeOff = settingActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_MODEL_OFF_LEVEL, settingActivity.typeOff);
    }
}
