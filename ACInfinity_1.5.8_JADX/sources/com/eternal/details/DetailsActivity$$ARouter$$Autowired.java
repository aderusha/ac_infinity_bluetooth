package com.eternal.details;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

public class DetailsActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        DetailsActivity detailsActivity = (DetailsActivity) obj;
        detailsActivity.mac = detailsActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        detailsActivity.devId = detailsActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_ID);
        detailsActivity.port = detailsActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_PORT, detailsActivity.port);
        detailsActivity.deviceVersion = detailsActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_VERSION, detailsActivity.deviceVersion);
        detailsActivity.connectType = detailsActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, detailsActivity.connectType);
        detailsActivity.deviceType = detailsActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, detailsActivity.deviceType);
        detailsActivity.softwareVersion = detailsActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_SOFTWARE_VERSION);
        detailsActivity.hardwareVersion = detailsActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_HARDWARE_VERSION);
        detailsActivity.firmwareVersion = detailsActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_FIRMWARE_VERSION);
    }
}
