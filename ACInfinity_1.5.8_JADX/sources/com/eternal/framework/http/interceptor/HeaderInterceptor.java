package com.eternal.framework.http.interceptor;

import java.io.IOException;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public abstract class HeaderInterceptor implements Interceptor {
    public abstract Map<String, String> buildHeaders();

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Map<String, String> buildHeaders = buildHeaders();
        if (buildHeaders == null || buildHeaders.isEmpty()) {
            return chain.proceed(request);
        }
        return chain.proceed(request.newBuilder().headers(buildHeaders(request, buildHeaders)).build());
    }

    private Headers buildHeaders(Request request, Map<String, String> map) {
        Headers headers = request.headers();
        if (headers == null) {
            return headers;
        }
        Headers.Builder newBuilder = headers.newBuilder();
        for (String next : map.keySet()) {
            newBuilder.add(next, map.get(next));
        }
        return newBuilder.build();
    }
}
