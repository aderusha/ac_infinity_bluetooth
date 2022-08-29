package com.eternal.framework.http.factory;

import com.eternal.framework.http.manage.RxUrlManager;
import com.eternal.framework.http.retrofit.RetrofitBuilder;
import java.util.HashMap;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

public class ApiFactory {
    private static HashMap<String, Object> apiServiceCache;
    private static volatile ApiFactory instance;
    private CallAdapter.Factory[] callAdapterFactory;
    private Converter.Factory[] converterFactory;
    private OkHttpClient okHttpClient;

    public static ApiFactory getInstance() {
        if (instance == null) {
            synchronized (ApiFactory.class) {
                if (instance == null) {
                    instance = new ApiFactory();
                }
            }
        }
        return instance;
    }

    private ApiFactory() {
        apiServiceCache = new HashMap<>();
    }

    public void clearAllApi() {
        apiServiceCache.clear();
    }

    public ApiFactory setCallAdapterFactory(CallAdapter.Factory... factoryArr) {
        this.callAdapterFactory = factoryArr;
        return this;
    }

    public ApiFactory setConverterFactory(Converter.Factory... factoryArr) {
        this.converterFactory = factoryArr;
        return this;
    }

    public ApiFactory setOkClient(OkHttpClient okHttpClient2) {
        this.okHttpClient = okHttpClient2;
        return this;
    }

    public ApiFactory setBaseUrl(String str) {
        RxUrlManager.getInstance().setUrl(str);
        return this;
    }

    public <A> A createApi(Class<A> cls) {
        return createApi(RxUrlManager.DEFAULT_URL_KEY, RxUrlManager.getInstance().getUrl(), cls);
    }

    public <A> A createApi(String str, String str2, Class<A> cls) {
        String apiKey = getApiKey(str, cls);
        A a = apiServiceCache.get(apiKey);
        if (a != null) {
            return a;
        }
        A create = new RetrofitBuilder().setBaseUrl(str2).setCallAdapterFactory(this.callAdapterFactory).setConverterFactory(this.converterFactory).setOkHttpClient(this.okHttpClient).build().create(cls);
        apiServiceCache.put(apiKey, create);
        return create;
    }

    private static <A> String getApiKey(String str, Class<A> cls) {
        return String.format("%s_%s", new Object[]{str, cls});
    }
}
