package com.eternal.base.global;

public class ActivityEvent {
    public static final String ACTION_ADD_DEVICE = "action add device";
    public static final String ACTION_ADD_LOG_REFRESH = "action add log refresh";
    public static final String ACTION_APP_UPDATE = "action app update";
    public static final String ACTION_CLEAR_LOG_REFRESH = "action_clear_log_refresh";
    public static final String ACTION_DELETE_DEVICE = "action delete device";
    public static final String ACTION_DELETE_LOG_REFRESH = "action delete log refresh";
    public static final String ACTION_LOG = "action into log";
    public static final String AFTER_LOGOUT = "afterLogout";
    public static final String BACK_WIFI_PAGE = "back wifi page";
    public static final String DELETE_ADVANCE_ALL = "delete advance all";
    public static final String DEVICE_CONNECT_TYPE = "device connect type";
    public static final String DEVICE_DEGREE = "device degree";
    public static final String DEVICE_FIRMWARE_URL = "device firmware url";
    public static final String DEVICE_FIRMWARE_VERSION = "device firmware version";
    public static final String DEVICE_HARDWARE_VERSION = "device hardware version";
    public static final String DEVICE_ID = "device id";
    public static final String DEVICE_INDEX = "device index";
    public static final String DEVICE_MAC = "device mac";
    public static final String DEVICE_MODEL = "device model";
    public static final String DEVICE_MODEL_OFF_LEVEL = "device model off level";
    public static final String DEVICE_MODEL_ON_LEVEL = "device model on level";
    public static final String DEVICE_PORT = "device port";
    public static final String DEVICE_SOFTWARE_VERSION = "device software version";
    public static final String DEVICE_SUPPORT_APP_VERSION = "device support app version";
    public static final String DEVICE_TIME_ZONE = "device time zone";
    public static final String DEVICE_TYPE = "device type";
    public static final String DEVICE_TYPE_NAME = "device type name";
    public static final String DEVICE_VERSION = "device version";
    public static final String EMAIL = "email";
    public static final int FINISH_PAGE = 12;
    public static final byte FROM_ACCOUNT = 31;
    public static final byte FROM_CREATE = 32;
    public static final byte FROM_FORGOT_PASSWORD = 30;
    public static final String FROM_PAGE = "from page";
    public static final String IS_APP_UPDATE = "is app update";
    public static final int IS_OPEN_AUTOMATION = 4;
    public static final String IS_PRIVACY = "is_privacy";
    public static final int LOGIN = 20;
    public static final int LOGOUT = 21;
    public static final String LOG_INIT = "log init";
    public static final String NOTIFICATION = "notification";
    public static final String NOTIFICATION_END = "notification end";
    public static final String NOTIFICATION_GROUP_ID = "notification id";
    public static final String NOTIFICATION_MODE = "notification mode";
    public static final String NOTIFICATION_NAME = "notification name";
    public static final byte NOTIFICATION_REFRESH = 0;
    public static final String NOTIFICATION_START = "notification start";
    public static final String NOTIFICATION_TYPE = "notification type";
    public static final String PAGE_TYPE = "page type";
    public static final String PASSWORD = "password";
    public static final byte REFRESH_SHARE_DOT = 40;
    public static final int SET_DEVICE_LEAF_TEMP_C = 3;
    public static final byte SET_DEVICE_MODEL_INFO = 1;
    public static final int SET_DEVICE_MODEL_INFO_DEGREE = 2;
    public static final String SHARED_PREFERENCES_KEY_DATA_CHECKED_TYPES = "sp_data_checked_types";
    public static final String SHARED_PREFERENCES_KEY_LOG_CHECKED_MODELS = "log_checked_models";
    public static final String SHARED_PREFERENCES_KEY_LOG_CHECKED_PORT_MODELS = "log_checked_port_models";
    public static final String SHARED_PREFERENCES_KEY_LOG_CHECKED_TYPES = "log_checked_types";
    public static final String SHOW_SHARE_DOT = "show share dot";
    public static final String SOCKET_IP = "socket ip";
    public static final String SOCKET_PORT = "socket Port";
    public static final String SSID = "ssid";
    public static final int STATE_BACKGROUND = 11;
    public static final int STATE_FOREGROUND = 10;
    public static final String UPDATE_VERSION = "update version";
    public Object obj;
    public int what;

    public ActivityEvent(int i) {
        this.what = i;
    }

    public ActivityEvent(int i, Object obj2) {
        this.what = i;
        this.obj = obj2;
    }
}
