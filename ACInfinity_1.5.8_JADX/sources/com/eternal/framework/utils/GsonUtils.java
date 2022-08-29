package com.eternal.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class GsonUtils {
    private static final Gson GSON = createGson(true);
    private static final Gson GSON_NO_NULLS = createGson(false);

    private GsonUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Gson getGson() {
        return getGson(true);
    }

    public static Gson getGson(boolean z) {
        return z ? GSON_NO_NULLS : GSON;
    }

    public static String toJson(Object obj) {
        return toJson(obj, true);
    }

    public static String toJson(Object obj, boolean z) {
        return (z ? GSON : GSON_NO_NULLS).toJson(obj);
    }

    public static String toJson(Object obj, Type type) {
        return toJson(obj, type, true);
    }

    public static String toJson(Object obj, Type type, boolean z) {
        return (z ? GSON : GSON_NO_NULLS).toJson(obj, type);
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        return GSON.fromJson(str, cls);
    }

    public static <T> T fromJson(String str, Type type) {
        return GSON.fromJson(str, type);
    }

    public static <T> T fromJson(Reader reader, Class<T> cls) {
        return GSON.fromJson(reader, cls);
    }

    public static <T> T fromJson(Reader reader, Type type) {
        return GSON.fromJson(reader, type);
    }

    public static Type getListType(Type type) {
        return TypeToken.getParameterized(List.class, type).getType();
    }

    public static Type getSetType(Type type) {
        return TypeToken.getParameterized(Set.class, type).getType();
    }

    public static Type getMapType(Type type, Type type2) {
        return TypeToken.getParameterized(Map.class, type, type2).getType();
    }

    public static Type getArrayType(Type type) {
        return TypeToken.getArray(type).getType();
    }

    public static Type getType(Type type, Type... typeArr) {
        return TypeToken.getParameterized(type, typeArr).getType();
    }

    private static Gson createGson(boolean z) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        if (z) {
            gsonBuilder.serializeNulls();
        }
        return gsonBuilder.create();
    }

    public static <T> List<T> getList(String str, Class<T> cls) {
        return (List) new Gson().fromJson(str, TypeToken.getParameterized(List.class, cls).getType());
    }
}
