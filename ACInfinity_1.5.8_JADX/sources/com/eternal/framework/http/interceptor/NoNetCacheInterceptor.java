package com.eternal.framework.http.interceptor;

import com.eternal.framework.http.utils.NetUtils;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NoNetCacheInterceptor implements Interceptor {
    private int noNetCacheTime;

    public NoNetCacheInterceptor(int i) {
        this.noNetCacheTime = i;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        if (NetUtils.isNetworkConnected()) {
            return chain.proceed(request);
        }
        Request build = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        Response proceed = chain.proceed(build);
        if (proceed.code() == 504) {
            return chain.proceed(build.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build());
        }
        Response.Builder newBuilder = proceed.newBuilder();
        return newBuilder.header(HttpHeaders.CACHE_CONTROL, "public, only-if-cached, max-stale=" + this.noNetCacheTime).removeHeader(HttpHeaders.PRAGMA).build();
    }
}
