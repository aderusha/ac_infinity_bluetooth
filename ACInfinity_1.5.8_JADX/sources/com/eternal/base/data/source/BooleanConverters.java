package com.eternal.base.data.source;

import com.eternal.framework.utils.GsonUtils;
import java.util.List;

public class BooleanConverters {
    public static List<Boolean> stringToBooleanList(String str) {
        return GsonUtils.getList(str, Boolean.class);
    }

    public static String booleanListToString(List<Boolean> list) {
        return GsonUtils.toJson(list);
    }
}
