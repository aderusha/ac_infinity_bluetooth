package com.eternal.framework.http.download;

import com.google.common.net.HttpHeaders;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

public class DownloadInterceptor implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().addHeader(HttpHeaders.ACCEPT_ENCODING, "identity").build());
    }
}
