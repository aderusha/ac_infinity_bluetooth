package com.eternal.framework.http.utils;

import java.util.Map;
import java.util.TreeMap;

public class ParamUtils {
    private Map<String, Object> params;

    public ParamUtils addParams(String str, Object obj) {
        if (this.params == null) {
            this.params = new TreeMap();
        }
        this.params.put(str, obj);
        return this;
    }

    public Map getParams() {
        Map<String, Object> map = this.params;
        if (map == null) {
            return null;
        }
        return map;
    }

    public void clearParams() {
        Map<String, Object> map = this.params;
        if (map != null) {
            map.clear();
        }
    }
}
