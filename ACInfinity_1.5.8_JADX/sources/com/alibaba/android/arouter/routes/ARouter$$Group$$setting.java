package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.settings.SettingActivity;
import java.util.HashMap;
import java.util.Map;

public class ARouter$$Group$$setting implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.Setting.PAGE_SETTING, RouteMeta.build(RouteType.ACTIVITY, SettingActivity.class, "/setting/setting", "setting", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.DEVICE_MODEL_ON_LEVEL, 1);
                put(ActivityEvent.DEVICE_SOFTWARE_VERSION, 8);
                put(ActivityEvent.DEVICE_FIRMWARE_VERSION, 8);
                put(ActivityEvent.DEVICE_MAC, 8);
                put(ActivityEvent.DEVICE_HARDWARE_VERSION, 8);
                put(ActivityEvent.DEVICE_MODEL_OFF_LEVEL, 1);
            }
        }, -1, Integer.MIN_VALUE));
    }
}
