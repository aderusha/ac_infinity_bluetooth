package com.eternal.notification.p008ui;

import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;

/* renamed from: com.eternal.notification.ui.NotificationActivity$$ARouter$$Autowired */
public class NotificationActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public void inject(Object obj) {
        this.serializationService = (SerializationService) ARouter.getInstance().navigation(SerializationService.class);
        NotificationActivity notificationActivity = (NotificationActivity) obj;
        notificationActivity.mac = notificationActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_MAC);
        notificationActivity.devId = notificationActivity.getIntent().getStringExtra(ActivityEvent.DEVICE_ID);
        notificationActivity.port = notificationActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_PORT, notificationActivity.port);
        notificationActivity.deviceType = notificationActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_TYPE, notificationActivity.deviceType);
        notificationActivity.deviceVersion = notificationActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_VERSION, notificationActivity.deviceVersion);
        notificationActivity.connectType = notificationActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_CONNECT_TYPE, notificationActivity.connectType);
        notificationActivity.notifyType = notificationActivity.getIntent().getByteExtra(ActivityEvent.NOTIFICATION_TYPE, notificationActivity.notifyType);
        notificationActivity.notificationStr = notificationActivity.getIntent().getStringExtra(ActivityEvent.NOTIFICATION);
        notificationActivity.groupId = notificationActivity.getIntent().getIntExtra(ActivityEvent.NOTIFICATION_GROUP_ID, notificationActivity.groupId);
        notificationActivity.isDegree = notificationActivity.getIntent().getBooleanExtra(ActivityEvent.DEVICE_DEGREE, notificationActivity.isDegree);
        notificationActivity.notifyName = notificationActivity.getIntent().getStringExtra(ActivityEvent.NOTIFICATION_NAME);
        notificationActivity.notifyStart = notificationActivity.getIntent().getCharExtra(ActivityEvent.NOTIFICATION_START, notificationActivity.notifyStart);
        notificationActivity.notifyEnd = notificationActivity.getIntent().getCharExtra(ActivityEvent.NOTIFICATION_END, notificationActivity.notifyEnd);
        notificationActivity.onLevel = notificationActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_MODEL_ON_LEVEL, notificationActivity.onLevel);
        notificationActivity.offLevel = notificationActivity.getIntent().getByteExtra(ActivityEvent.DEVICE_MODEL_OFF_LEVEL, notificationActivity.offLevel);
    }
}
