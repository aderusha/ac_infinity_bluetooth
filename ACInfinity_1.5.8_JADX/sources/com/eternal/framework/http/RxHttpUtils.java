package com.eternal.framework.http;

import android.app.Application;
import android.content.Context;
import com.eternal.framework.http.config.OkHttpConfig;
import com.eternal.framework.http.cookie.CookieJarImpl;
import com.eternal.framework.http.cookie.store.CookieStore;
import com.eternal.framework.http.download.DownloadHelper;
import com.eternal.framework.http.factory.ApiFactory;
import com.eternal.framework.http.manage.RxHttpManager;
import com.eternal.framework.http.upload.UploadHelper;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;
import p014io.reactivex.Observable;

public class RxHttpUtils {
    private static Application context;
    private static RxHttpUtils instance;

    public static RxHttpUtils getInstance() {
        if (instance == null) {
            synchronized (RxHttpUtils.class) {
                if (instance == null) {
                    instance = new RxHttpUtils();
                }
            }
        }
        return instance;
    }

    public RxHttpUtils init(Application application) {
        context = application;
        return this;
    }

    public static Context getContext() {
        checkInitialize();
        return context;
    }

    private static void checkInitialize() {
        if (context == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 RxHttpUtils.getInstance().init(this) 初始化！");
        }
    }

    public ApiFactory config() {
        checkInitialize();
        return ApiFactory.getInstance();
    }

    public static <K> K createApi(Class<K> cls) {
        return ApiFactory.getInstance().createApi(cls);
    }

    public static <K> K createApi(String str, String str2, Class<K> cls) {
        return ApiFactory.getInstance().createApi(str, str2, cls);
    }

    public static Observable<ResponseBody> downloadFile(String str) {
        return DownloadHelper.downloadFile(str);
    }

    public static Observable<ResponseBody> uploadImg(String str, String str2) {
        return UploadHelper.uploadImage(str, str2);
    }

    public static Observable<ResponseBody> uploadImages(String str, List<String> list) {
        return UploadHelper.uploadImages(str, list);
    }

    public static Observable<ResponseBody> uploadImagesWithParams(String str, String str2, Map<String, Object> map, List<String> list) {
        return UploadHelper.uploadFilesWithParams(str, str2, map, list);
    }

    private static CookieJarImpl getCookieJar() {
        return (CookieJarImpl) OkHttpConfig.getInstance().getOkHttpClient().cookieJar();
    }

    private static CookieStore getCookieStore() {
        return getCookieJar().getCookieStore();
    }

    public static List<Cookie> getAllCookie() {
        return getCookieStore().getAllCookie();
    }

    public static List<Cookie> getCookieByUrl(String str) {
        return getCookieStore().getCookie(HttpUrl.parse(str));
    }

    public static void removeAllCookie() {
        getCookieStore().removeAllCookie();
    }

    public static void removeCookieByUrl(String str) {
        getCookieStore().removeCookie(HttpUrl.parse(str));
    }

    public static void cancelAll() {
        RxHttpManager.get().cancelAll();
    }

    public static void cancel(Object... objArr) {
        RxHttpManager.get().cancel(objArr);
    }
}
