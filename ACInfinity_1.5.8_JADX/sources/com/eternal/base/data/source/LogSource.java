package com.eternal.base.data.source;

import androidx.paging.DataSource;
import com.eternal.base.concat.DeviceModelInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.NotificationName;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.database.BaseDatabase;
import com.eternal.base.database.dao.DeviceDao;
import com.eternal.base.database.dao.LogDao;
import com.eternal.base.database.dao.NotificationDao;
import com.eternal.base.database.entity.Log;
import com.eternal.base.database.entity.Notification;
import com.eternal.framework.utils.SPUtils;
import java.util.Calendar;
import java.util.List;

public class LogSource implements ILogSource {
    private DeviceDao dDao;
    private LogDao lDao;
    private NotificationDao nDao;

    public LogSource() {
        BaseDatabase instance = BaseDatabase.getInstance();
        this.lDao = instance.logDao();
        this.nDao = instance.notificationDao();
        this.dDao = instance.deviceDao();
    }

    public void addLog(Log log) {
        this.lDao.insertLog(log);
    }

    public DataSource.Factory<Integer, Log> getLogs(String str, List<Byte> list, List<Byte> list2) {
        if (list2 == null || list2.size() == 0) {
            return this.lDao.loadByType(str, list);
        }
        return this.lDao.loadByTypeAndModel(str, list, list2);
    }

    public DataSource.Factory<Integer, Log> getLogs(String str, List<Byte> list) {
        return this.lDao.loadCByType(str, list);
    }

    public Log getFirstLog(String str) {
        Log loadFirstLog = this.lDao.loadFirstLog(str);
        if (loadFirstLog != null) {
            return loadFirstLog;
        }
        Log log = new Log();
        Calendar instance = Calendar.getInstance();
        instance.set(2000, 1, 1);
        log.time = instance.getTimeInMillis();
        log.f142id = 0;
        return log;
    }

    public Log getFirstNetLog(String str) {
        Log loadFirstNetLog = this.lDao.loadFirstNetLog(str);
        if (loadFirstNetLog != null) {
            return loadFirstNetLog;
        }
        Log log = new Log();
        Calendar instance = Calendar.getInstance();
        instance.set(2000, 1, 1);
        log.time = instance.getTimeInMillis();
        log.f142id = 0;
        return log;
    }

    public Log getFirstLog(String str, byte b) {
        Log loadFirstLog = this.lDao.loadFirstLog(str, b);
        if (loadFirstLog != null) {
            return loadFirstLog;
        }
        Log log = new Log();
        Calendar instance = Calendar.getInstance();
        instance.set(2000, 1, 1);
        log.time = instance.getTimeInMillis();
        log.f142id = 0;
        return log;
    }

    public Log getFirstCLog(String str) {
        Log loadFirstLog = this.lDao.loadFirstLog(str);
        if (loadFirstLog != null) {
            return loadFirstLog;
        }
        Log log = new Log();
        log.mac = str;
        log.time = 0;
        log.f142id = -1;
        return log;
    }

    public void deleteAll(String str) {
        Log firstLog = getFirstLog(str);
        if (firstLog.f142id != -1) {
            Log firstNetLog = getFirstNetLog(str);
            this.lDao.setDelete(str, firstLog.time);
            this.lDao.setDelete(str, firstNetLog.time);
            this.lDao.removeOther(str, new Integer[]{Integer.valueOf(firstNetLog.f142id), Integer.valueOf(firstLog.f142id)});
        }
    }

    public void deleteOldData(String str, long j) {
        Log firstLog = getFirstLog(str);
        if (firstLog.f142id != -1) {
            Log firstNetLog = getFirstNetLog(str);
            this.lDao.removeOldData(str, new Integer[]{Integer.valueOf(firstNetLog.f142id), Integer.valueOf(firstLog.f142id)}, j);
            this.lDao.setDeleteLessTime(str, j);
        }
    }

    public long getLogTime(String str) {
        long loadLogTime = this.dDao.loadLogTime(str);
        if (loadLogTime != 0) {
            return loadLogTime;
        }
        SPUtils instance = SPUtils.getInstance();
        return instance.getLong(str + "0");
    }

    public void setLogTime(String str, long j) {
        if (getLogTime(str) != j) {
            this.dDao.updateDeviceLogTime(str, j);
            SPUtils instance = SPUtils.getInstance();
            instance.put(str + "0", j);
        }
    }

    public void setRead(String str, byte b, Integer[] numArr) {
        if (numArr == null) {
            this.lDao.setRead(str, b);
        } else {
            this.lDao.setRead(str, b, numArr);
        }
    }

    public Notification getNotification(String str, byte b, int i, byte b2) {
        return this.nDao.loadByMacAndId(str, b, i, b2);
    }

    public DeviceModelInfo getModelInfo(String str) {
        return this.dDao.loadModelInfo(str);
    }

    public boolean isDegree(String str) {
        return this.dDao.isDegree(str);
    }

    public List<NotificationName> getNotificationNames(String str) {
        return this.nDao.loadMacAllName(str);
    }

    public DeviceName getDeviceName(String str, byte b) {
        return this.dDao.getDeviceName(str, b);
    }

    public List<PortInfo> getPortInfo(String str) {
        return this.dDao.loadPortInfo(str);
    }
}
