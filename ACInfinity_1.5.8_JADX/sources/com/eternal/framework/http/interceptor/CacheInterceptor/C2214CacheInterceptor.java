package com.eternal.framework.http.interceptor.CacheInterceptor;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.eternal.export.CSVUtil;
import com.eternal.framework.http.interceptor.CacheInterceptor.Catch.CacheManager;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* renamed from: com.eternal.framework.http.interceptor.CacheInterceptor.CacheInterceptor */
public class C2214CacheInterceptor implements Interceptor {
    private Context context;

    public void setContext(Context context2) {
        this.context = context2;
    }

    public C2214CacheInterceptor(Context context2) {
        this.context = context2;
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        String header = request.header("cache");
        String header2 = request.header(HttpHeaders.CACHE_CONTROL);
        String header3 = request.header("timeout");
        if ("true".equals(header) || (header2 != null && !header2.isEmpty())) {
            long currentTimeMillis = System.currentTimeMillis();
            String url = request.url().url().toString();
            String str = null;
            String postParams = getPostParams(request);
            try {
                Response proceed = chain.proceed(request);
                if (!proceed.isSuccessful()) {
                    return chain.proceed(request);
                }
                ResponseBody body = proceed.body();
                if (body != null) {
                    str = body.string();
                    if (str == null) {
                        str = "";
                    }
                    CacheManager instance = CacheManager.getInstance(this.context);
                    instance.setCache(CacheManager.encryptMD5(url + postParams), str);
                    Log.i("HttpRetrofit", "--> Push Cache:" + url + " :Success");
                }
                return getOnlineResponse(proceed, str);
            } catch (Exception e) {
                e.printStackTrace();
                Response cacheResponse = getCacheResponse(request, currentTimeMillis);
                return cacheResponse == null ? chain.proceed(request) : cacheResponse;
            }
        } else if (TextUtils.isEmpty(header3)) {
            return chain.proceed(request);
        } else {
            int i = 15;
            try {
                i = Integer.parseInt(header3);
            } catch (NullPointerException | NumberFormatException unused) {
            }
            return chain.withConnectTimeout(i, TimeUnit.SECONDS).withReadTimeout(i, TimeUnit.SECONDS).withWriteTimeout(i, TimeUnit.SECONDS).proceed(request);
        }
    }

    private Response getCacheResponse(Request request, long j) {
        Log.i("HttpRetrofit", "--> Try to Get Cache   --------");
        String url = request.url().url().toString();
        String postParams = getPostParams(request);
        CacheManager instance = CacheManager.getInstance(this.context);
        String cache = instance.getCache(CacheManager.encryptMD5(url + postParams));
        if (cache == null) {
            Log.i("HttpRetrofit", "<-- Get Cache Failure ---------");
            return null;
        }
        Response build = new Response.Builder().code(200).body(ResponseBody.create((MediaType) null, cache)).request(request).message(CacheType.DISK_CACHE).protocol(Protocol.HTTP_1_0).build();
        long currentTimeMillis = System.currentTimeMillis() - j;
        Log.i("HttpRetrofit", "<-- Get Cache: " + build.code() + " " + build.message() + " " + url + " (" + currentTimeMillis + "ms)");
        StringBuilder sb = new StringBuilder();
        sb.append(cache);
        sb.append("");
        Log.i("HttpRetrofit", sb.toString());
        return build;
    }

    private Response getOnlineResponse(Response response, String str) {
        MediaType mediaType;
        ResponseBody body = response.body();
        Response.Builder code = new Response.Builder().code(response.code());
        if (body == null) {
            mediaType = null;
        } else {
            mediaType = body.contentType();
        }
        return code.body(ResponseBody.create(mediaType, str)).request(response.request()).message(response.message()).protocol(response.protocol()).build();
    }

    private String getPostParams(Request request) {
        if ("POST".equals(request.method())) {
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody formBody = (FormBody) request.body();
                if (formBody != null) {
                    for (int i = 0; i < formBody.size(); i++) {
                        sb.append(formBody.encodedName(i));
                        sb.append("=");
                        sb.append(formBody.encodedValue(i));
                        sb.append(CSVUtil.COLUMN_SEPARATOR);
                    }
                    sb.delete(sb.length() - 1, sb.length());
                }
                String sb2 = sb.toString();
                sb.delete(0, sb.length());
                return sb2;
            }
        }
        return "";
    }
}
