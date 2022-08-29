package com.eternal.framework.http.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.eternal.framework.http.RxHttpUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class SPUtils {
    public static final String FILE_NAME = "share_data";

    public SPUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static String get(String str, String str2) {
        return obtainPref().getString(str, str2);
    }

    public static int get(String str, int i) {
        return obtainPref().getInt(str, i);
    }

    public static boolean get(String str, boolean z) {
        return obtainPref().getBoolean(str, z);
    }

    public static float get(String str, float f) {
        return obtainPref().getFloat(str, f);
    }

    public static long get(String str, long j) {
        return obtainPref().getLong(str, j);
    }

    public static Set<String> get(String str, Set<String> set) {
        return obtainPref().getStringSet(str, set);
    }

    public static void put(String str, String str2) {
        SharedPreferences.Editor obtainPrefEditor = obtainPrefEditor();
        obtainPrefEditor.putString(str, str2);
        SharedPreferencesCompat.apply(obtainPrefEditor);
    }

    public static void put(String str, int i) {
        SharedPreferences.Editor obtainPrefEditor = obtainPrefEditor();
        obtainPrefEditor.putInt(str, i);
        SharedPreferencesCompat.apply(obtainPrefEditor);
    }

    public static void put(String str, boolean z) {
        SharedPreferences.Editor obtainPrefEditor = obtainPrefEditor();
        obtainPrefEditor.putBoolean(str, z);
        SharedPreferencesCompat.apply(obtainPrefEditor);
    }

    public static void put(String str, float f) {
        SharedPreferences.Editor obtainPrefEditor = obtainPrefEditor();
        obtainPrefEditor.putFloat(str, f);
        SharedPreferencesCompat.apply(obtainPrefEditor);
    }

    public static void put(String str, long j) {
        SharedPreferences.Editor obtainPrefEditor = obtainPrefEditor();
        obtainPrefEditor.putLong(str, j);
        SharedPreferencesCompat.apply(obtainPrefEditor);
    }

    public static void put(String str, Set set) {
        SharedPreferences.Editor obtainPrefEditor = obtainPrefEditor();
        obtainPrefEditor.putStringSet(str, set);
        SharedPreferencesCompat.apply(obtainPrefEditor);
    }

    public static void remove(String str) {
        SharedPreferences.Editor obtainPrefEditor = obtainPrefEditor();
        obtainPrefEditor.remove(str);
        SharedPreferencesCompat.apply(obtainPrefEditor);
    }

    public static void clearAll() {
        SharedPreferences.Editor obtainPrefEditor = obtainPrefEditor();
        obtainPrefEditor.clear();
        SharedPreferencesCompat.apply(obtainPrefEditor);
    }

    public static boolean contains(String str) {
        return obtainPref().contains(str);
    }

    public static Map<String, ?> getAll() {
        return obtainPref().getAll();
    }

    private static SharedPreferences obtainPref() {
        return RxHttpUtils.getContext().getSharedPreferences(FILE_NAME, 0);
    }

    private static SharedPreferences.Editor obtainPrefEditor() {
        return obtainPref().edit();
    }

    public static void put(Context context, String str, Object obj) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        if (obj instanceof String) {
            edit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else {
            edit.putString(str, obj.toString());
        }
        SharedPreferencesCompat.apply(edit);
    }

    public static Object get(Context context, String str, Object obj) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, 0);
        if (obj instanceof String) {
            return sharedPreferences.getString(str, (String) obj);
        }
        if (obj instanceof Integer) {
            return Integer.valueOf(sharedPreferences.getInt(str, ((Integer) obj).intValue()));
        }
        if (obj instanceof Boolean) {
            return Boolean.valueOf(sharedPreferences.getBoolean(str, ((Boolean) obj).booleanValue()));
        }
        if (obj instanceof Float) {
            return Float.valueOf(sharedPreferences.getFloat(str, ((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return Long.valueOf(sharedPreferences.getLong(str, ((Long) obj).longValue()));
        }
        return null;
    }

    public static void remove(Context context, String str) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.remove(str);
        SharedPreferencesCompat.apply(edit);
    }

    public static void clearAll(Context context) {
        SharedPreferences.Editor edit = context.getSharedPreferences(FILE_NAME, 0).edit();
        edit.clear();
        SharedPreferencesCompat.apply(edit);
    }

    public static boolean contains(Context context, String str) {
        return context.getSharedPreferences(FILE_NAME, 0).contains(str);
    }

    public static Map<String, ?> getAll(Context context) {
        return context.getSharedPreferences(FILE_NAME, 0).getAll();
    }

    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        private SharedPreferencesCompat() {
        }

        private static Method findApplyMethod() {
            try {
                return SharedPreferences.Editor.class.getMethod("apply", new Class[0]);
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }

        public static void apply(SharedPreferences.Editor editor) {
            try {
                Method method = sApplyMethod;
                if (method != null) {
                    method.invoke(editor, new Object[0]);
                    return;
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
            editor.commit();
        }
    }
}
