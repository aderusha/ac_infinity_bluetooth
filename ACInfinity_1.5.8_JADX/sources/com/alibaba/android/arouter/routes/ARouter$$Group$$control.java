package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.router.RouterFragmentPath;
import com.eternal.control.ControlCFragment;
import com.eternal.control.ControlFragment;
import java.util.Map;

public class ARouter$$Group$$control implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterFragmentPath.Control.PAGE_CONTROL, RouteMeta.build(RouteType.FRAGMENT, ControlFragment.class, "/control/control", "control", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterFragmentPath.Control.PAGE_CONTROL_C, RouteMeta.build(RouteType.FRAGMENT, ControlCFragment.class, "/control/control_c", "control", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
