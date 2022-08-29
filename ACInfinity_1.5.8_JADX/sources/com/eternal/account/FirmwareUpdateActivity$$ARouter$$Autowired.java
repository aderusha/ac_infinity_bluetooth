package com.eternal.account;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class FirmwareUpdateActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        FirmwareUpdateActivity firmwareUpdateActivity = (FirmwareUpdateActivity) obj;
        firmwareUpdateActivity.firmwareUrl = firmwareUpdateActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_FIRMWARE_URL);
        firmwareUpdateActivity.updateVersion = firmwareUpdateActivity.getIntent().getStringExtra(ActivityEvent.UPDATE_VERSION);
        firmwareUpdateActivity.mac = firmwareUpdateActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        firmwareUpdateActivity.devId = firmwareUpdateActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_ID);
        firmwareUpdateActivity.port = firmwareUpdateActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_PORT, firmwareUpdateActivity.port);
        firmwareUpdateActivity.type = firmwareUpdateActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, firmwareUpdateActivity.type);
        firmwareUpdateActivity.connectType = firmwareUpdateActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, firmwareUpdateActivity.connectType);
    }
}
