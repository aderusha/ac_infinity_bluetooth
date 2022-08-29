package com.eternal.base.database.dao;

import androidx.paging.DataSource;
import com.eternal.base.database.entity.Log;
import java.util.List;

public interface LogDao {
    void clear(String str);

    void insertLog(Log log);

    DataSource.Factory<Integer, Log> loadAllMacByModel(String str);

    DataSource.Factory<Integer, Log> loadByType(String str, List<Byte> list);

    DataSource.Factory<Integer, Log> loadByTypeAndModel(String str, List<Byte> list, List<Byte> list2);

    DataSource.Factory<Integer, Log> loadCByType(String str, List<Byte> list);

    Log loadFirstLog(String str);

    Log loadFirstLog(String str, byte b);

    Log loadFirstNetLog(String str);

    List<Log> loadLogByTime(String str, long j, long j2);

    int numByMacAndTime(String str, long j, List<Byte> list, List<Byte> list2);

    int numByMacAndTimeC(String str, long j, List<Byte> list);

    void removeOldData(String str, Integer[] numArr, long j);

    void removeOther(String str, long j);

    void removeOther(String str, Integer[] numArr);

    void setDelete(String str, long j);

    void setDeleteLessTime(String str, long j);

    void setRead(String str, byte b);

    void setRead(String str, byte b, Integer[] numArr);
}
