package com.eternal.framework.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    public static Map<String, Object> transBean2Map(Object obj) {
        HashMap hashMap = new HashMap();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    Object obj2 = field.get(obj);
                    if (obj2 != null) {
                        hashMap.put(field.getName(), obj2);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }
}
