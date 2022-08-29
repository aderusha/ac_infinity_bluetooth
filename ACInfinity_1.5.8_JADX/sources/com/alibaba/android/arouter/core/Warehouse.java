package com.alibaba.android.arouter.core;

import com.alibaba.android.arouter.base.UniqueKeyTreeMap;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Warehouse {
    static Map<String, Class<? extends IRouteGroup>> groupsIndex = new HashMap();
    static List<IInterceptor> interceptors = new ArrayList();
    static Map<Integer, Class<? extends IInterceptor>> interceptorsIndex = new UniqueKeyTreeMap("More than one interceptors use same priority [%s]");
    static Map<Class, IProvider> providers = new HashMap();
    static Map<String, RouteMeta> providersIndex = new HashMap();
    static Map<String, RouteMeta> routes = new HashMap();

    Warehouse() {
    }

    static void clear() {
        routes.clear();
        groupsIndex.clear();
        providers.clear();
        providersIndex.clear();
        interceptors.clear();
        interceptorsIndex.clear();
    }
}
