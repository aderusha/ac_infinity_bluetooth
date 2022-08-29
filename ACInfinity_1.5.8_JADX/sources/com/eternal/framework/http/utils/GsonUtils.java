package com.eternal.framework.http.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GsonUtils {
    public static <T> T getObject(String str, Class<T> cls) {
        try {
            return new Gson().fromJson(str, cls);
        } catch (Exception unused) {
            return null;
        }
    }

    public static <T> List<T> getArray(String str, Class<T> cls) {
        try {
            return (List) new Gson().fromJson(str, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static List<Map<String, Object>> listKeyMaps(String str) {
        try {
            return (List) new Gson().fromJson(str, new TypeToken<List<Map<String, Object>>>() {
            }.getType());
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    public static Map<String, Object> objKeyMaps(String str) {
        try {
            return (Map) new Gson().fromJson(str, new TypeToken<Map<String, Object>>() {
            }.getType());
        } catch (Exception unused) {
            return new HashMap();
        }
    }
}
