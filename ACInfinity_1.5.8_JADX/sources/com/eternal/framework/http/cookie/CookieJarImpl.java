package com.eternal.framework.http.cookie;

import com.eternal.framework.http.cookie.store.CookieStore;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class CookieJarImpl implements CookieJar {
    private CookieStore cookieStore;

    public CookieJarImpl(CookieStore cookieStore2) {
        if (cookieStore2 != null) {
            this.cookieStore = cookieStore2;
            return;
        }
        throw new IllegalArgumentException("cookieStore can not be null!");
    }

    public synchronized void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.cookieStore.saveCookie(httpUrl, list);
    }

    public synchronized List<Cookie> loadForRequest(HttpUrl httpUrl) {
        return this.cookieStore.loadCookie(httpUrl);
    }

    public CookieStore getCookieStore() {
        return this.cookieStore;
    }
}
