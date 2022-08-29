package com.eternal.framework.http.cookie.store;

import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public interface CookieStore {
    List<Cookie> getAllCookie();

    List<Cookie> getCookie(HttpUrl httpUrl);

    List<Cookie> loadCookie(HttpUrl httpUrl);

    boolean removeAllCookie();

    boolean removeCookie(HttpUrl httpUrl);

    boolean removeCookie(HttpUrl httpUrl, Cookie cookie);

    void saveCookie(HttpUrl httpUrl, List<Cookie> list);

    void saveCookie(HttpUrl httpUrl, Cookie cookie);
}
