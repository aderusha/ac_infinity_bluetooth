package com.eternal.base.api;

import com.eternal.base.concat.NetHistory;
import com.eternal.base.concat.NetHistoryData;
import com.eternal.base.concat.NetLog;
import com.eternal.base.concat.NetLogData;
import com.eternal.framework.http.bean.BaseData;
import java.util.List;
import p014io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LogApi {
    @POST("log/data")
    Observable<BaseData<List<NetHistory>>> getDataList(@Query("devId") String str, @Query("time") long j);

    @POST("log/dataPage")
    Observable<BaseData<NetHistoryData>> getDataListPage(@Query("appId") String str, @Query("devId") String str2, @Query("time") long j, @Query("pageSize") int i);

    @POST("log/logdata")
    Observable<BaseData<List<NetLog>>> getLogList(@Query("devId") String str, @Query("port") byte b, @Query("time") long j);

    @POST("log/logdataByAll")
    Observable<BaseData<NetLogData>> getLogList(@Query("appId") String str, @Query("devId") String str2, @Query("id") int i, @Query("time") long j, @Query("pageSize") int i2);
}
