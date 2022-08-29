package com.eternal.framework.http.retrofit;

import com.eternal.framework.http.gson.GsonAdapter;
import com.eternal.framework.http.http.SSLUtils;
import com.eternal.framework.http.interceptor.RxHttpLogger;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitBuilder {
    private String baseUrl;
    private CallAdapter.Factory[] callAdapterFactory;
    private Converter.Factory[] converterFactory;
    private OkHttpClient okHttpClient;

    public RetrofitBuilder setBaseUrl(String str) {
        this.baseUrl = str;
        return this;
    }

    public RetrofitBuilder setCallAdapterFactory(CallAdapter.Factory... factoryArr) {
        this.callAdapterFactory = factoryArr;
        return this;
    }

    public RetrofitBuilder setConverterFactory(Converter.Factory... factoryArr) {
        this.converterFactory = factoryArr;
        return this;
    }

    public RetrofitBuilder setOkHttpClient(OkHttpClient okHttpClient2) {
        this.okHttpClient = okHttpClient2;
        return this;
    }

    public Retrofit build() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(this.baseUrl);
        CallAdapter.Factory[] factoryArr = this.callAdapterFactory;
        if (factoryArr == null || factoryArr.length <= 0) {
            builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        } else {
            for (CallAdapter.Factory addCallAdapterFactory : factoryArr) {
                builder.addCallAdapterFactory(addCallAdapterFactory);
            }
        }
        Converter.Factory[] factoryArr2 = this.converterFactory;
        if (factoryArr2 == null || factoryArr2.length <= 0) {
            builder.addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(GsonAdapter.buildGson()));
        } else {
            for (Converter.Factory addConverterFactory : factoryArr2) {
                builder.addConverterFactory(addConverterFactory);
            }
        }
        OkHttpClient okHttpClient2 = this.okHttpClient;
        if (okHttpClient2 == null) {
            builder.client(createOkHttpClient());
        } else {
            builder.client(okHttpClient2);
        }
        return builder.build();
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        SSLUtils.SSLParams sslSocketFactory = SSLUtils.getSslSocketFactory();
        builder.sslSocketFactory(sslSocketFactory.sSLSocketFactory, sslSocketFactory.trustManager);
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new RxHttpLogger());
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(httpLoggingInterceptor);
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }
}
