package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.about.AboutActivity;
import com.eternal.base.router.RouterActivityPath;
import java.util.Map;

public class ARouter$$Group$$about implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.About.PAGE_ABOUT, RouteMeta.build(RouteType.ACTIVITY, AboutActivity.class, "/about/about", "about", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
