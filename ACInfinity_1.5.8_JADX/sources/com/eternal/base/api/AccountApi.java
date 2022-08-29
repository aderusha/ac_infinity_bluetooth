package com.eternal.base.api;

import com.eternal.base.concat.FirmwareVersion;
import com.eternal.base.concat.UserInfo;
import com.eternal.base.concat.Version;
import com.eternal.framework.http.bean.BaseData;
import java.util.List;
import okhttp3.MultipartBody;
import p014io.reactivex.Observable;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface AccountApi {
    @POST("user/addAPPUser")
    Observable<BaseData<UserInfo>> addAPPUser(@Query("appEmail") String str, @Query("appPasswordl") String str2);

    @Multipart
    @POST("user/addUserFeedBack")
    Observable<BaseData<Void>> addUserFeedBack(@Query("fbackEmail") String str, @Query("fbackTitle") String str2, @Query("fbackText") String str3, @Query("fbackInformation") String str4, @Part List<MultipartBody.Part> list);

    @POST("user/delUserInfo")
    Observable<BaseData<Void>> delUserInfo(@Query("appId") String str);

    @POST("email/sendEmailCodeIsOK")
    Observable<BaseData<Void>> emailVerify(@Query("appEmail") String str, @Query("emailCode") String str2);

    @POST("user/getByUser")
    Observable<BaseData<UserInfo>> getByUser(@Query("appId") String str);

    @POST("user/getByUserEmail")
    Observable<BaseData<Void>> getByUserEmail(@Query("appEmail") String str);

    @POST("upgrade/getUpgrade")
    Observable<BaseData<FirmwareVersion>> getFirmwareVersion(@Query("fFamily") byte b, @Query("firmwareVersion") String str, @Query("hardwareVersion") String str2);

    @POST("app/getApplogBy")
    Observable<Version> getVersion(@Query("app_system") int i, @Query("app_version") String str);

    @POST("email/sendEmail")
    Observable<BaseData<Void>> sendEmail(@Query("appEmail") String str);

    @POST("user/updateAPPUser")
    Observable<BaseData<String>> updateAPPUser(@Query("appId") String str, @Query("appIsanalytics") int i, @Query("appIsbugreport") int i2, @Query("appIsemailrepost") int i3);

    @POST("user/updateAPPUserPassword")
    Observable<BaseData<Void>> updatePassword(@Query("appEmail") String str, @Query("appPasswordl") String str2);

    @POST("user/appUserLogin")
    Observable<BaseData<UserInfo>> userLogin(@Query("appEmail") String str, @Query("appPasswordl") String str2);
}
