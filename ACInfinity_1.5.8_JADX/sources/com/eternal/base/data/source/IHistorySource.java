package com.eternal.base.data.source;

import androidx.paging.DataSource;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.database.entity.History;
import java.util.List;
import p014io.reactivex.Flowable;

public interface IHistorySource {
    void add(History history);

    void deleteAll(String str);

    void deleteOldData(String str, long j);

    long getFirstTime(String str);

    DataSource.Factory<Integer, TmpHum> getHistory(String str);

    List<TmpHum> getHistory(String str, long j, long j2);

    List<TmpHum> getHistoryHour(String str, List<String> list, String str2, long j, long j2);

    List<TmpHum> getHistoryMin(String str, List<String> list, long j, long j2);

    Flowable<DeviceInfo> getInfo(String str);

    long getTime(String str);

    boolean isDegree(String str);
}
