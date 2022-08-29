package com.eternal.framework.utils;

import android.content.SharedPreferences;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class SPUtils {
    private static Map<String, SPUtils> sSPMap = new HashMap();

    /* renamed from: sp */
    private SharedPreferences f191sp;

    public static SPUtils getInstance() {
        return getInstance("");
    }

    public static SPUtils getInstance(String str) {
        if (isSpace(str)) {
            str = "spUtils";
        }
        SPUtils sPUtils = sSPMap.get(str);
        if (sPUtils != null) {
            return sPUtils;
        }
        SPUtils sPUtils2 = new SPUtils(str);
        sSPMap.put(str, sPUtils2);
        return sPUtils2;
    }

    private SPUtils(String str) {
        this.f191sp = Utils.getContext().getSharedPreferences(str, 0);
    }

    public void put(String str, String str2) {
        this.f191sp.edit().putString(str, str2).apply();
    }

    public String getString(String str) {
        return getString(str, "");
    }

    public String getString(String str, String str2) {
        return this.f191sp.getString(str, str2);
    }

    public void put(String str, int i) {
        this.f191sp.edit().putInt(str, i).apply();
    }

    public int getInt(String str) {
        return getInt(str, -1);
    }

    public int getInt(String str, int i) {
        return this.f191sp.getInt(str, i);
    }

    public void put(String str, long j) {
        this.f191sp.edit().putLong(str, j).apply();
    }

    public long getLong(String str) {
        return getLong(str, -1);
    }

    public long getLong(String str, long j) {
        return this.f191sp.getLong(str, j);
    }

    public void put(String str, float f) {
        this.f191sp.edit().putFloat(str, f).apply();
    }

    public float getFloat(String str) {
        return getFloat(str, -1.0f);
    }

    public float getFloat(String str, float f) {
        return this.f191sp.getFloat(str, f);
    }

    public void put(String str, boolean z) {
        this.f191sp.edit().putBoolean(str, z).apply();
    }

    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.f191sp.getBoolean(str, z);
    }

    public void put(String str, Set<String> set) {
        this.f191sp.edit().putStringSet(str, set).apply();
    }

    public Set<String> getStringSet(String str) {
        return getStringSet(str, Collections.emptySet());
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return this.f191sp.getStringSet(str, set);
    }

    public Map<String, ?> getAll() {
        return this.f191sp.getAll();
    }

    public boolean contains(String str) {
        return this.f191sp.contains(str);
    }

    public void remove(String str) {
        this.f191sp.edit().remove(str).apply();
    }

    public void clear() {
        this.f191sp.edit().clear().apply();
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
