package com.eternal.framework.http.interceptor;

import com.eternal.framework.http.utils.NetUtils;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetCacheInterceptor implements Interceptor {
    private int cacheTime;

    public NetCacheInterceptor(int i) {
        this.cacheTime = i;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (!NetUtils.isNetworkConnected()) {
            return chain.proceed(request);
        }
        return chain.proceed(request).newBuilder().header(HttpHeaders.CACHE_CONTROL, new CacheControl.Builder().maxAge(this.cacheTime, TimeUnit.SECONDS).build().toString()).removeHeader(HttpHeaders.PRAGMA).build();
    }
}
