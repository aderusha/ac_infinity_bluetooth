package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.details.DetailsActivity;
import java.util.HashMap;
import java.util.Map;

public class ARouter$$Group$$detail implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.Detail.PAGE_DETAIL, RouteMeta.build(RouteType.ACTIVITY, DetailsActivity.class, "/detail/detail", "detail", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.DEVICE_PORT, 1);
                put(ActivityEvent.DEVICE_TYPE, 1);
                put(ActivityEvent.DEVICE_ID, 8);
                put(ActivityEvent.DEVICE_SOFTWARE_VERSION, 8);
                put(ActivityEvent.DEVICE_FIRMWARE_VERSION, 8);
                put(ActivityEvent.DEVICE_VERSION, 1);
                put(ActivityEvent.DEVICE_CONNECT_TYPE, 1);
                put(ActivityEvent.DEVICE_MAC, 8);
                put(ActivityEvent.DEVICE_HARDWARE_VERSION, 8);
            }
        }, -1, Integer.MIN_VALUE));
    }
}
