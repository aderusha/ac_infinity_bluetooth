package com.eternal.framework.http.upload;

import java.util.List;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import p014io.reactivex.Observable;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface UploadFileApi {
    @POST
    @Multipart
    Observable<ResponseBody> uploadFiles(@Url String str, @Part List<MultipartBody.Part> list);
}
