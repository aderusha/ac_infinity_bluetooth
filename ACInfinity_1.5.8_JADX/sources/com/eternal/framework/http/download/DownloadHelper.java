package com.eternal.framework.http.download;

import com.eternal.framework.http.factory.ApiFactory;
import com.eternal.framework.http.interceptor.Transformer;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import p014io.reactivex.Observable;

public class DownloadHelper {
    public static Observable<ResponseBody> downloadFile(String str) {
        return ((DownloadApi) ApiFactory.getInstance().setOkClient(new OkHttpClient.Builder().addInterceptor(new DownloadInterceptor()).build()).createApi(DownloadApi.class)).downloadFile(str).compose(Transformer.switchSchedulers());
    }
}
