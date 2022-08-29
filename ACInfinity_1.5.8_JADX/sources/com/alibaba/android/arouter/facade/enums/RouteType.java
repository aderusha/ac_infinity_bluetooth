package com.alibaba.android.arouter.facade.enums;

public enum RouteType {
    ACTIVITY(0, "android.app.Activity"),
    SERVICE(1, "android.app.Service"),
    PROVIDER(2, "com.alibaba.android.arouter.facade.template.IProvider"),
    CONTENT_PROVIDER(-1, "android.app.ContentProvider"),
    BOARDCAST(-1, ""),
    METHOD(-1, ""),
    FRAGMENT(-1, "android.app.Fragment"),
    UNKNOWN(-1, "Unknown route type");
    
    String className;

    /* renamed from: id */
    int f76id;

    public int getId() {
        return this.f76id;
    }

    public RouteType setId(int i) {
        this.f76id = i;
        return this;
    }

    public String getClassName() {
        return this.className;
    }

    public RouteType setClassName(String str) {
        this.className = str;
        return this;
    }

    private RouteType(int i, String str) {
        this.f76id = i;
        this.className = str;
    }

    public static RouteType parse(String str) {
        for (RouteType routeType : values()) {
            if (routeType.getClassName().equals(str)) {
                return routeType;
            }
        }
        return UNKNOWN;
    }
}
