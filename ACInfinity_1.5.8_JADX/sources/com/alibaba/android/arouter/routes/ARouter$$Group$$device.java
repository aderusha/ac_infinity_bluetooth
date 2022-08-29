package com.alibaba.android.arouter.routes;

import com.alibaba.android.arouter.facade.enums.RouteType;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.device.ChooseActivity;
import com.eternal.device.DeviceActivity;
import com.eternal.device.DeviceWiFiActivity;
import com.eternal.device.GuideActivity;
import com.eternal.device.HelpActivity;
import com.eternal.device.LocationPermissionActivity;
import com.eternal.device.PromptActivity;
import com.eternal.device.WiFiActivity;
import com.eternal.device.WiFiConnectActivity;
import com.eternal.device.WiFiListActivity;
import com.eternal.device.WiFiSettingActivity;
import java.util.HashMap;
import java.util.Map;

public class ARouter$$Group$$device implements IRouteGroup {
    public void loadInto(Map<String, RouteMeta> map) {
        map.put(RouterActivityPath.Device.PAGE_ADD, RouteMeta.build(RouteType.ACTIVITY, DeviceActivity.class, "/device/add", "device", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_ADD_WIFI, RouteMeta.build(RouteType.ACTIVITY, DeviceWiFiActivity.class, "/device/addwifi", "device", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.DEVICE_TYPE, 1);
                put(ActivityEvent.DEVICE_MAC, 8);
                put(ActivityEvent.DEVICE_TYPE_NAME, 8);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_CHOOSE, RouteMeta.build(RouteType.ACTIVITY, ChooseActivity.class, "/device/choose", "device", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_GUIDE, RouteMeta.build(RouteType.ACTIVITY, GuideActivity.class, "/device/guide", "device", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.SOCKET_IP, 8);
                put(ActivityEvent.DEVICE_TYPE, 1);
                put(ActivityEvent.SOCKET_PORT, 3);
                put("page type", 1);
                put(ActivityEvent.DEVICE_MODEL, 1);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_HELP, RouteMeta.build(RouteType.ACTIVITY, HelpActivity.class, "/device/help", "device", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_LOCATION_PERMISSION, RouteMeta.build(RouteType.ACTIVITY, LocationPermissionActivity.class, "/device/locationpermission", "device", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_PROMPT, RouteMeta.build(RouteType.ACTIVITY, PromptActivity.class, "/device/prompt", "device", (Map<String, Integer>) null, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_WIFI, RouteMeta.build(RouteType.ACTIVITY, WiFiActivity.class, "/device/wifi", "device", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.SOCKET_IP, 8);
                put(ActivityEvent.SOCKET_PORT, 3);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_WIFI_CONNECT, RouteMeta.build(RouteType.ACTIVITY, WiFiConnectActivity.class, "/device/wificonnect", "device", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.SOCKET_IP, 8);
                put(ActivityEvent.DEVICE_TYPE, 1);
                put(ActivityEvent.SOCKET_PORT, 3);
                put(ActivityEvent.SSID, 8);
                put(ActivityEvent.DEVICE_MAC, 8);
                put(ActivityEvent.DEVICE_TYPE_NAME, 8);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_WIFI_LIST, RouteMeta.build(RouteType.ACTIVITY, WiFiListActivity.class, "/device/wifilist", "device", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.SOCKET_IP, 8);
                put(ActivityEvent.SOCKET_PORT, 3);
            }
        }, -1, Integer.MIN_VALUE));
        map.put(RouterActivityPath.Device.PAGE_WIFI_SETTING, RouteMeta.build(RouteType.ACTIVITY, WiFiSettingActivity.class, "/device/wifisetting", "device", new HashMap<String, Integer>() {
            {
                put(ActivityEvent.SOCKET_IP, 8);
                put(ActivityEvent.DEVICE_TYPE, 1);
                put(ActivityEvent.SOCKET_PORT, 3);
                put(ActivityEvent.SSID, 8);
                put(ActivityEvent.DEVICE_MAC, 8);
                put(ActivityEvent.DEVICE_TYPE_NAME, 8);
            }
        }, -1, Integer.MIN_VALUE));
    }
}
