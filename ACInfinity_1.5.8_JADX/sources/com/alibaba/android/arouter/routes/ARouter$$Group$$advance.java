package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.advance.AdvanceFragment;
import com.eternal.advance.AdvanceFragmentV4;
import com.eternal.base.router.RouterFragmentPath;
import java.util.Map;

public class ARouter$$Group$$advance implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterFragmentPath.Advance.PAGE_ADVANCE, RouteMeta.build(RouteType.FRAGMENT, AdvanceFragment.class, "/advance/advance", "advance", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterFragmentPath.Advance.PAGE_ADVANCE_V4, RouteMeta.build(RouteType.FRAGMENT, AdvanceFragmentV4.class, "/advance/advancev4", "advance", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
