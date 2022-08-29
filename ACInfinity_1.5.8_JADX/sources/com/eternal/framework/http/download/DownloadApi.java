package com.eternal.framework.http.download;

import okhttp3.ResponseBody;
import p014io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface DownloadApi {
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String str);
}
