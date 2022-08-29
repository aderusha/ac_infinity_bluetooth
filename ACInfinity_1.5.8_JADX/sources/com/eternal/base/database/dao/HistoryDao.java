package com.eternal.base.database.dao;

import androidx.paging.DataSource;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.database.entity.History;
import java.util.List;

public interface HistoryDao {
    void clear(String str);

    void insert(History history);

    DataSource.Factory<Integer, TmpHum> loadAllHistoryFromMac(String str);

    List<TmpHum> loadAllHistoryFromMac(String str, long j, long j2);

    List<TmpHum> loadAllHistoryFromMacHour(String str, List<String> list, String str2, long j, long j2);

    List<TmpHum> loadAllHistoryFromMacMin(String str, List<String> list, long j, long j2);

    List<History> loadAllTest(String str);

    int loadFirstTime(String str);

    int loadLastTime(String str);

    void removeOldData(String str, int i, long j);

    void removeOther(String str, int i);

    void setDelete(String str, int i);
}
