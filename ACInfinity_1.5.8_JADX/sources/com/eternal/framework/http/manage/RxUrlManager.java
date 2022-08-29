package com.eternal.framework.http.manage;

import com.eternal.framework.http.RxHttpUtils;
import com.eternal.framework.http.factory.ApiFactory;
import java.util.HashMap;
import java.util.Map;

public class RxUrlManager {
    public static String DEFAULT_URL_KEY = "rx_default_url_key";
    private static volatile RxUrlManager instance;
    private Map<String, String> urlMap = new HashMap();

    public static RxUrlManager getInstance() {
        if (instance == null) {
            synchronized (RxUrlManager.class) {
                if (instance == null) {
                    instance = new RxUrlManager();
                }
            }
        }
        return instance;
    }

    private RxUrlManager() {
    }

    public RxUrlManager setMultipleUrl(Map<String, String> map) {
        this.urlMap = map;
        return this;
    }

    public RxUrlManager addUrl(String str, String str2) {
        this.urlMap.put(str, str2);
        return this;
    }

    public RxUrlManager removeUrlByKey(String str) {
        this.urlMap.remove(str);
        return this;
    }

    public RxUrlManager setUrl(String str) {
        this.urlMap.put(DEFAULT_URL_KEY, str);
        return this;
    }

    public String getUrl() {
        return getUrlByKey(DEFAULT_URL_KEY);
    }

    public String getUrlByKey(String str) {
        return this.urlMap.get(str);
    }

    public RxUrlManager clear() {
        this.urlMap.clear();
        ApiFactory.getInstance().clearAllApi();
        RxHttpUtils.removeAllCookie();
        return this;
    }
}
