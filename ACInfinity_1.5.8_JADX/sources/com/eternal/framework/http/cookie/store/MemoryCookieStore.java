package com.eternal.framework.http.cookie.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class MemoryCookieStore implements CookieStore {
    private final Map<String, List<Cookie>> memoryCookies = new HashMap();

    public synchronized void saveCookie(HttpUrl httpUrl, List<Cookie> list) {
        List<Cookie> list2 = this.memoryCookies.get(httpUrl.host());
        ArrayList arrayList = new ArrayList();
        for (Cookie next : list) {
            for (Cookie cookie : list2) {
                if (next.name().equals(cookie.name())) {
                    arrayList.add(cookie);
                }
            }
        }
        list2.removeAll(arrayList);
        list2.addAll(list);
    }

    public synchronized void saveCookie(HttpUrl httpUrl, Cookie cookie) {
        List<Cookie> list = this.memoryCookies.get(httpUrl.host());
        ArrayList arrayList = new ArrayList();
        for (Cookie cookie2 : list) {
            if (cookie.name().equals(cookie2.name())) {
                arrayList.add(cookie2);
            }
        }
        list.removeAll(arrayList);
        list.add(cookie);
    }

    public synchronized List<Cookie> loadCookie(HttpUrl httpUrl) {
        List<Cookie> list;
        list = this.memoryCookies.get(httpUrl.host());
        if (list == null) {
            list = new ArrayList<>();
            this.memoryCookies.put(httpUrl.host(), list);
        }
        return list;
    }

    public synchronized List<Cookie> getAllCookie() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (String str : this.memoryCookies.keySet()) {
            arrayList.addAll(this.memoryCookies.get(str));
        }
        return arrayList;
    }

    public List<Cookie> getCookie(HttpUrl httpUrl) {
        ArrayList arrayList = new ArrayList();
        List list = this.memoryCookies.get(httpUrl.host());
        if (list != null) {
            arrayList.addAll(list);
        }
        return arrayList;
    }

    public synchronized boolean removeCookie(HttpUrl httpUrl, Cookie cookie) {
        return cookie != null && this.memoryCookies.get(httpUrl.host()).remove(cookie);
    }

    public synchronized boolean removeCookie(HttpUrl httpUrl) {
        return this.memoryCookies.remove(httpUrl.host()) != null;
    }

    public synchronized boolean removeAllCookie() {
        this.memoryCookies.clear();
        return true;
    }
}
