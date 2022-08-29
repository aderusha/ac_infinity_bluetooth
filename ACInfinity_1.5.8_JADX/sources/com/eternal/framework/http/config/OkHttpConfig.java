package com.eternal.framework.http.config;

import android.content.Context;
import android.text.TextUtils;
import com.eternal.framework.http.cookie.CookieJarImpl;
import com.eternal.framework.http.cookie.store.CookieStore;
import com.eternal.framework.http.http.SSLUtils;
import com.eternal.framework.http.interceptor.HeaderInterceptor;
import com.eternal.framework.http.interceptor.NetCacheInterceptor;
import com.eternal.framework.http.interceptor.NoNetCacheInterceptor;
import com.eternal.framework.http.interceptor.RxHttpLogger;
import com.eternal.framework.http.interfaces.BuildHeadersListener;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpConfig {
    /* access modifiers changed from: private */
    public static String defaultCachePath = null;
    private static final long defaultCacheSize = 104857600;
    private static final long defaultTimeout = 10;
    private static OkHttpConfig instance;
    /* access modifiers changed from: private */
    public static OkHttpClient okHttpClient;
    /* access modifiers changed from: private */
    public static OkHttpClient.Builder okHttpClientBuilder;

    public OkHttpConfig() {
        okHttpClientBuilder = new OkHttpClient.Builder().retryOnConnectionFailure(true);
    }

    public static OkHttpConfig getInstance() {
        if (instance == null) {
            synchronized (OkHttpConfig.class) {
                if (instance == null) {
                    instance = new OkHttpConfig();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient2 = okHttpClient;
        return okHttpClient2 == null ? okHttpClientBuilder.build() : okHttpClient2;
    }

    public static class Builder {
        private InputStream bksFile;
        /* access modifiers changed from: private */
        public BuildHeadersListener buildHeadersListener;
        private long cacheMaxSize;
        private String cachePath;
        private int cacheTime = 60;
        private InputStream[] certificates;
        private long connectTimeout;
        public Context context;
        private CookieStore cookieStore;
        private HostnameVerifier hostnameVerifier;
        private Interceptor[] interceptors;
        private boolean isCache;
        private boolean isDebug;
        private int noNetCacheTime = 10;
        private String password;
        private long readTimeout;
        private long writeTimeout;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder setHeaders(BuildHeadersListener buildHeadersListener2) {
            this.buildHeadersListener = buildHeadersListener2;
            return this;
        }

        public Builder setDebug(boolean z) {
            this.isDebug = z;
            return this;
        }

        public Builder setCache(boolean z) {
            this.isCache = z;
            return this;
        }

        public Builder setHasNetCacheTime(int i) {
            this.cacheTime = i;
            return this;
        }

        public Builder setNoNetCacheTime(int i) {
            this.noNetCacheTime = i;
            return this;
        }

        public Builder setCachePath(String str) {
            this.cachePath = str;
            return this;
        }

        public Builder setCacheMaxSize(long j) {
            this.cacheMaxSize = j;
            return this;
        }

        public Builder setCookieType(CookieStore cookieStore2) {
            this.cookieStore = cookieStore2;
            return this;
        }

        public Builder setReadTimeout(long j) {
            this.readTimeout = j;
            return this;
        }

        public Builder setWriteTimeout(long j) {
            this.writeTimeout = j;
            return this;
        }

        public Builder setConnectTimeout(long j) {
            this.connectTimeout = j;
            return this;
        }

        public Builder setAddInterceptor(Interceptor... interceptorArr) {
            this.interceptors = interceptorArr;
            return this;
        }

        public Builder setSslSocketFactory(InputStream... inputStreamArr) {
            this.certificates = inputStreamArr;
            return this;
        }

        public Builder setSslSocketFactory(InputStream inputStream, String str, InputStream... inputStreamArr) {
            this.bksFile = inputStream;
            this.password = str;
            this.certificates = inputStreamArr;
            return this;
        }

        public Builder setHostnameVerifier(HostnameVerifier hostnameVerifier2) {
            this.hostnameVerifier = hostnameVerifier2;
            return this;
        }

        public OkHttpClient build() {
            OkHttpConfig.getInstance();
            setCookieConfig();
            setCacheConfig();
            setHeadersConfig();
            setSslConfig();
            setHostnameVerifier();
            addInterceptors();
            setTimeout();
            setDebugConfig();
            OkHttpClient unused = OkHttpConfig.okHttpClient = OkHttpConfig.okHttpClientBuilder.build();
            return OkHttpConfig.okHttpClient;
        }

        private void addInterceptors() {
            Interceptor[] interceptorArr = this.interceptors;
            if (interceptorArr != null) {
                for (Interceptor addInterceptor : interceptorArr) {
                    OkHttpConfig.okHttpClientBuilder.addInterceptor(addInterceptor);
                }
            }
        }

        private void setDebugConfig() {
            if (this.isDebug) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new RxHttpLogger());
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpConfig.okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
            }
        }

        private void setHeadersConfig() {
            if (this.buildHeadersListener != null) {
                OkHttpConfig.okHttpClientBuilder.addInterceptor(new HeaderInterceptor() {
                    public Map<String, String> buildHeaders() {
                        return Builder.this.buildHeadersListener.buildHeaders();
                    }
                });
            }
        }

        private void setCookieConfig() {
            if (this.cookieStore != null) {
                OkHttpConfig.okHttpClientBuilder.cookieJar(new CookieJarImpl(this.cookieStore));
            }
        }

        private void setCacheConfig() {
            Cache cache;
            File externalCacheDir = this.context.getExternalCacheDir();
            if (externalCacheDir != null) {
                String unused = OkHttpConfig.defaultCachePath = externalCacheDir.getPath() + "/RxHttpCacheData";
                if (this.isCache) {
                    if (TextUtils.isEmpty(this.cachePath) || this.cacheMaxSize <= 0) {
                        cache = new Cache(new File(OkHttpConfig.defaultCachePath), OkHttpConfig.defaultCacheSize);
                    } else {
                        cache = new Cache(new File(this.cachePath), this.cacheMaxSize);
                    }
                    OkHttpConfig.okHttpClientBuilder.cache(cache).addInterceptor(new NoNetCacheInterceptor(this.noNetCacheTime)).addNetworkInterceptor(new NetCacheInterceptor(this.cacheTime));
                }
            }
        }

        private void setTimeout() {
            OkHttpClient.Builder access$100 = OkHttpConfig.okHttpClientBuilder;
            long j = this.readTimeout;
            long j2 = OkHttpConfig.defaultTimeout;
            if (j == 0) {
                j = 10;
            }
            access$100.readTimeout(j, TimeUnit.SECONDS);
            OkHttpClient.Builder access$1002 = OkHttpConfig.okHttpClientBuilder;
            long j3 = this.writeTimeout;
            if (j3 == 0) {
                j3 = 10;
            }
            access$1002.writeTimeout(j3, TimeUnit.SECONDS);
            OkHttpClient.Builder access$1003 = OkHttpConfig.okHttpClientBuilder;
            long j4 = this.connectTimeout;
            if (j4 != 0) {
                j2 = j4;
            }
            access$1003.connectTimeout(j2, TimeUnit.SECONDS);
            OkHttpConfig.okHttpClientBuilder.retryOnConnectionFailure(true);
        }

        private void setSslConfig() {
            SSLUtils.SSLParams sSLParams;
            if (this.certificates == null) {
                sSLParams = SSLUtils.getSslSocketFactory();
            } else if (this.bksFile == null || TextUtils.isEmpty(this.password)) {
                sSLParams = SSLUtils.getSslSocketFactory(this.certificates);
            } else {
                sSLParams = SSLUtils.getSslSocketFactory(this.bksFile, this.password, this.certificates);
            }
            OkHttpConfig.okHttpClientBuilder.sslSocketFactory(sSLParams.sSLSocketFactory, sSLParams.trustManager);
        }

        private void setHostnameVerifier() {
            if (this.hostnameVerifier == null) {
                OkHttpConfig.okHttpClientBuilder.hostnameVerifier(SSLUtils.UnSafeHostnameVerifier);
            } else {
                OkHttpConfig.okHttpClientBuilder.hostnameVerifier(this.hostnameVerifier);
            }
        }
    }
}
