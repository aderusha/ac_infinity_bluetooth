package com.eternal.framework.http.cookie.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.eternal.export.CSVUtil;
import com.eternal.framework.http.cookie.SerializableCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class SPCookieStore implements CookieStore {
    private static final String COOKIE_NAME_PREFIX = "cookie_";
    private static final String COOKIE_PREFS = "rx_http_utils_cookie";
    private final SharedPreferences cookiePrefs;
    private final Map<String, ConcurrentHashMap<String, Cookie>> cookies = new HashMap();

    public SPCookieStore(Context context) {
        Cookie decodeCookie;
        SharedPreferences sharedPreferences = context.getSharedPreferences(COOKIE_PREFS, 0);
        this.cookiePrefs = sharedPreferences;
        for (Map.Entry next : sharedPreferences.getAll().entrySet()) {
            if (next.getValue() != null && !((String) next.getKey()).startsWith(COOKIE_NAME_PREFIX)) {
                for (String str : TextUtils.split((String) next.getValue(), CSVUtil.COLUMN_SEPARATOR)) {
                    String string = this.cookiePrefs.getString(COOKIE_NAME_PREFIX + str, (String) null);
                    if (!(string == null || (decodeCookie = SerializableCookie.decodeCookie(string)) == null)) {
                        if (!this.cookies.containsKey(next.getKey())) {
                            this.cookies.put((String) next.getKey(), new ConcurrentHashMap());
                        }
                        this.cookies.get(next.getKey()).put(str, decodeCookie);
                    }
                }
            }
        }
    }

    private String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    private static boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    public synchronized void saveCookie(HttpUrl httpUrl, List<Cookie> list) {
        for (Cookie saveCookie : list) {
            saveCookie(httpUrl, saveCookie);
        }
    }

    public synchronized void saveCookie(HttpUrl httpUrl, Cookie cookie) {
        if (!this.cookies.containsKey(httpUrl.host())) {
            this.cookies.put(httpUrl.host(), new ConcurrentHashMap());
        }
        if (isCookieExpired(cookie)) {
            removeCookie(httpUrl, cookie);
        } else {
            saveCookie(httpUrl, cookie, getCookieToken(cookie));
        }
    }

    private void saveCookie(HttpUrl httpUrl, Cookie cookie, String str) {
        this.cookies.get(httpUrl.host()).put(str, cookie);
        SharedPreferences.Editor edit = this.cookiePrefs.edit();
        edit.putString(httpUrl.host(), TextUtils.join(CSVUtil.COLUMN_SEPARATOR, this.cookies.get(httpUrl.host()).keySet()));
        edit.putString(COOKIE_NAME_PREFIX + str, SerializableCookie.encodeCookie(httpUrl.host(), cookie));
        edit.apply();
    }

    public synchronized List<Cookie> loadCookie(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        if (!this.cookies.containsKey(httpUrl.host())) {
            return arrayList;
        }
        for (Cookie cookie : this.cookies.get(httpUrl.host()).values()) {
            if (isCookieExpired(cookie)) {
                removeCookie(httpUrl, cookie);
            } else {
                arrayList.add(cookie);
            }
        }
        return arrayList;
    }

    public synchronized boolean removeCookie(HttpUrl httpUrl, Cookie cookie) {
        if (!this.cookies.containsKey(httpUrl.host())) {
            return false;
        }
        String cookieToken = getCookieToken(cookie);
        if (!this.cookies.get(httpUrl.host()).containsKey(cookieToken)) {
            return false;
        }
        this.cookies.get(httpUrl.host()).remove(cookieToken);
        SharedPreferences.Editor edit = this.cookiePrefs.edit();
        SharedPreferences sharedPreferences = this.cookiePrefs;
        if (sharedPreferences.contains(COOKIE_NAME_PREFIX + cookieToken)) {
            edit.remove(COOKIE_NAME_PREFIX + cookieToken);
        }
        edit.putString(httpUrl.host(), TextUtils.join(CSVUtil.COLUMN_SEPARATOR, this.cookies.get(httpUrl.host()).keySet()));
        edit.apply();
        return true;
    }

    public synchronized boolean removeCookie(HttpUrl httpUrl) {
        if (!this.cookies.containsKey(httpUrl.host())) {
            return false;
        }
        Set<String> keySet = this.cookies.remove(httpUrl.host()).keySet();
        SharedPreferences.Editor edit = this.cookiePrefs.edit();
        for (String str : keySet) {
            SharedPreferences sharedPreferences = this.cookiePrefs;
            if (sharedPreferences.contains(COOKIE_NAME_PREFIX + str)) {
                edit.remove(COOKIE_NAME_PREFIX + str);
            }
        }
        edit.remove(httpUrl.host());
        edit.apply();
        return true;
    }

    public synchronized boolean removeAllCookie() {
        this.cookies.clear();
        SharedPreferences.Editor edit = this.cookiePrefs.edit();
        edit.clear();
        edit.apply();
        return true;
    }

    public synchronized List<Cookie> getAllCookie() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (String str : this.cookies.keySet()) {
            arrayList.addAll(this.cookies.get(str).values());
        }
        return arrayList;
    }

    public synchronized List<Cookie> getCookie(HttpUrl httpUrl) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Map map = this.cookies.get(httpUrl.host());
        if (map != null) {
            arrayList.addAll(map.values());
        }
        return arrayList;
    }
}
