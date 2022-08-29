package com.eternal.base.api;

import com.eternal.base.concat.NetAdvance;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.concat.NetDeviceMode;
import com.eternal.base.concat.NetDeviceSetting;
import com.eternal.base.concat.NetServe;
import com.eternal.base.concat.TimeZone;
import com.eternal.framework.http.bean.BaseData;
import java.util.List;
import java.util.Map;
import p014io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DeviceApi {
    @POST("user/devIsShareOk")
    Observable<BaseData<Void>> acceptShare(@Query("id") String str);

    @POST("dev/addAdvAlert")
    Observable<BaseData<Void>> addAdvInfo(@QueryMap Map<String, Object> map);

    @POST("user/addAppDevInfo")
    Observable<BaseData<Void>> bindDev(@Query("appUserId") String str, @Query("devMacAddr") String str2, @Query("timeGmt") String str3);

    @POST("user/delShareDev")
    Observable<BaseData<Void>> cancelShareDev(@Query("devId") String str, @Query("email") String str2);

    @POST("dev/delADVInfo")
    @Headers({"timeout:5"})
    Observable<BaseData<Void>> delADVInfo(@Query("devId") String str, @Query("advId") String str2);

    @POST("user/delDevNetWorkInfo")
    Observable<BaseData<Void>> delDevNetWorkInfo(@Query("appUserId") String str, @Query("devMacAddr") String str2);

    @POST("user/delAppShareDev")
    Observable<BaseData<Void>> delShareDev(@Query("devId") String str, @Query("email") String str2);

    @POST("user/devInfoListAll")
    Observable<BaseData<List<NetDevice>>> devInfoListAll(@Query("userId") String str);

    @POST("dev/function")
    Observable<BaseData<Void>> function(@Query("devId") String str, @Query("typeId") int i, @Query("fUrl") String str2);

    @POST("dev/getdevADVinfoBydevId")
    Observable<BaseData<List<NetAdvance>>> getADVInfoList(@Query("devId") String str, @Query("port") byte b);

    @POST("dev/getDevSetting")
    Observable<BaseData<NetDeviceSetting>> getDevSetting(@Query("devId") String str, @Query("port") byte b);

    @POST("dev/getDevTimeZone")
    @Headers({"cache:true"})
    Observable<BaseData<List<TimeZone>>> getDevTimeZone();

    @POST("dev/getDeviceInfo")
    Observable<BaseData<NetDevice>> getDeviceInfo(@Query("devId") String str);

    @POST("dev/getdevModeSettingList")
    Observable<BaseData<NetDeviceMode>> getModeSettingList(@Query("devId") String str, @Query("port") byte b);

    @POST("dev/getSockerIp")
    Observable<BaseData<NetServe>> getSockerIp();

    @POST("dev/netWorkSetting")
    Observable<BaseData<Void>> netWorkSetting(@Query("devId") String str, @Query("wifiName") String str2, @Query("wifiPwd") String str3);

    @POST("dev/putDevTimeZone")
    Observable<BaseData<Void>> putDevTimeZone(@Query("devId") String str, @Query("timeZone") String str2);

    @POST("dev/addDevMode")
    @Headers({"timeout:5"})
    Observable<BaseData<Void>> setModel(@QueryMap Map<String, Object> map);

    @POST("user/devShaerUserEmail")
    Observable<BaseData<Void>> shareDev(@Query("userId") String str, @Query("appEmail") String str2, @Query("devId") String[] strArr);

    @POST("user/devShareUser")
    Observable<BaseData<List<NetDevice>>> shareWithOtherDevList(@Query("userId") String str);

    @POST("user/shareDevList")
    Observable<BaseData<List<NetDevice>>> shareWithYouDevList(@Query("userId") String str);

    @POST("dev/initClockSetting")
    Observable<BaseData<Void>> syncTime(@Query("appId") String str);

    @POST("user/delAppDev")
    Observable<BaseData<Void>> unbindDev(@Query("appUserId") String str, @Query("devId") String str2, @Query("isDelAdvAll") boolean z);

    @POST("dev/updateADVStatus")
    @Headers({"timeout:5"})
    Observable<BaseData<Void>> updateADVStatus(@Query("devId") String str, @Query("advId") String str2);

    @POST("version=2.0/dev/updateAdvInfo")
    Observable<BaseData<Void>> updateAdvInfo(@QueryMap Map<String, Object> map);

    @POST("dev/updateMster")
    Observable<BaseData<Void>> updateMster(@Query("devId") String str);

    @POST("dev/updateMsterByNums")
    Observable<BaseData<Void>> updateMsterPort(@Query("devId") String str, @Query("nums") byte b);

    @POST("dev/updateAdvSetting")
    Observable<BaseData<Void>> updateSetting(@QueryMap Map<String, Object> map);
}
