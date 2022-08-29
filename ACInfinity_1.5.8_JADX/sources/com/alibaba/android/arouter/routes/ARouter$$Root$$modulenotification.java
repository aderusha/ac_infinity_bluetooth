package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import com.eternal.base.global.ActivityEvent;
import java.util.Map;

public class ARouter$$Root$$modulenotification implements IRouteRoot {
    public void loadInto(Map<String, Class<? extends IRouteGroup>> map) {
        map.put(ActivityEvent.NOTIFICATION, ARouter$$Group$$notification.class);
    }
}
