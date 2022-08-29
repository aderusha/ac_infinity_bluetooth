package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.notification.p008ui.NotificationActivity;
import java.util.HashMap;
import java.util.Map;

public class ARouter$$Group$$notification implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.Notification.PAGE_ADD, RouteMeta.build(RouteType.ACTIVITY, NotificationActivity.class, "/notification/add", ActivityEvent.NOTIFICATION, new HashMap<String, Integer>() {
            {
                put(ActivityEvent.DEVICE_PORT, 1);
                put(ActivityEvent.DEVICE_VERSION, 1);
                put(ActivityEvent.DEVICE_CONNECT_TYPE, 1);
                put(ActivityEvent.DEVICE_MODEL_OFF_LEVEL, 1);
                put(ActivityEvent.NOTIFICATION_END, 5);
                put(ActivityEvent.NOTIFICATION, 8);
                put(ActivityEvent.DEVICE_MODEL_ON_LEVEL, 1);
                put(ActivityEvent.NOTIFICATION_GROUP_ID, 3);
                put(ActivityEvent.NOTIFICATION_NAME, 8);
                put(ActivityEvent.DEVICE_TYPE, 1);
                put(ActivityEvent.NOTIFICATION_TYPE, 1);
                put(ActivityEvent.DEVICE_ID, 8);
                put(ActivityEvent.DEVICE_MAC, 8);
                put(ActivityEvent.DEVICE_DEGREE, 0);
                put(ActivityEvent.NOTIFICATION_START, 5);
            }
        }, -1, Integer.MIN_VALUE));
    }
}
