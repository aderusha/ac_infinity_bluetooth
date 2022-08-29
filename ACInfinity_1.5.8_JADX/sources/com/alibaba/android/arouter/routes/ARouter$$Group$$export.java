package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.export.ExportActivity;
import java.util.Map;

public class ARouter$$Group$$export implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.Export.PAGE_EXPORT, RouteMeta.build(RouteType.ACTIVITY, ExportActivity.class, "/export/export", "export", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
    }
}
