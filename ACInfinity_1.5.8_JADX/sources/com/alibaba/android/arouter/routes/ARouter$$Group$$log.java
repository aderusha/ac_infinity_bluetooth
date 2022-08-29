package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.router.RouterFragmentPath;
import com.eternal.log.LogCFragment;
import com.eternal.log.LogFragment;
import java.util.Map;

public class ARouter$$Group$$log implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterFragmentPath.Log.PAGE_LOG, RouteMeta.build(RouteType.FRAGMENT, LogFragment.class, "/log/log", "log", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterFragmentPath.Log.PAGE_LOG_C, RouteMeta.build(RouteType.FRAGMENT, LogCFragment.class, "/log/log_c", "log", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
