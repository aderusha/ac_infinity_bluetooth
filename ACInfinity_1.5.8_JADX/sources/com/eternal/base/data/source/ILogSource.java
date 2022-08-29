package com.eternal.base.data.source;

import androidx.paging.DataSource;
import com.eternal.base.concat.DeviceModelInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.NotificationName;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.database.entity.Log;
import com.eternal.base.database.entity.Notification;
import java.util.List;

public interface ILogSource {
    void addLog(Log log);

    void deleteAll(String str);

    void deleteOldData(String str, long j);

    DeviceName getDeviceName(String str, byte b);

    Log getFirstCLog(String str);

    Log getFirstLog(String str);

    Log getFirstLog(String str, byte b);

    Log getFirstNetLog(String str);

    long getLogTime(String str);

    DataSource.Factory<Integer, Log> getLogs(String str, List<Byte> list);

    DataSource.Factory<Integer, Log> getLogs(String str, List<Byte> list, List<Byte> list2);

    DeviceModelInfo getModelInfo(String str);

    Notification getNotification(String str, byte b, int i, byte b2);

    List<NotificationName> getNotificationNames(String str);

    List<PortInfo> getPortInfo(String str);

    boolean isDegree(String str);

    void setLogTime(String str, long j);

    void setRead(String str, byte b, Integer[] numArr);
}
