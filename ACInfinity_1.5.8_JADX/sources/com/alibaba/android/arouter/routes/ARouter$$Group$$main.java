package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.main.MainActivity;
import java.util.Map;

public class ARouter$$Group$$main implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.Main.PAGE_MAIN, RouteMeta.build(RouteType.ACTIVITY, MainActivity.class, "/main/main", "main", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
