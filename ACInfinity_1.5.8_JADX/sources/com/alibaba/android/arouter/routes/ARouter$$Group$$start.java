package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.start.StartActivity;
import java.util.Map;

public class ARouter$$Group$$start implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.Start.PAGE_START, RouteMeta.build(RouteType.ACTIVITY, StartActivity.class, "/start/start", "start", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
