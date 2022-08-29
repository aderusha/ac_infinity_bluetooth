package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.router.RouterFragmentPath;
import com.eternal.data.DataFragment;
import java.util.Map;

public class ARouter$$Group$$data implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterFragmentPath.Data.PAGE_DATA, RouteMeta.build(RouteType.FRAGMENT, DataFragment.class, "/data/data", "data", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
