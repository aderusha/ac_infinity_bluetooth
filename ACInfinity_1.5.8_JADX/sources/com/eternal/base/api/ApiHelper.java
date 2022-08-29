package com.eternal.base.api;

import com.eternal.framework.http.RxHttpUtils;

public class ApiHelper {
    public static AccountApi getAccountApi() {
        return (AccountApi) RxHttpUtils.createApi(AccountApi.class);
    }

    public static DeviceApi getDeviceApi() {
        return (DeviceApi) RxHttpUtils.createApi(DeviceApi.class);
    }

    public static LogApi getLogApi() {
        return (LogApi) RxHttpUtils.createApi(LogApi.class);
    }
}
