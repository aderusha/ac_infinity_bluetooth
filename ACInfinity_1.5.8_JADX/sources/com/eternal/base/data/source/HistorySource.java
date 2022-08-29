package com.eternal.base.data.source;

import androidx.paging.DataSource;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.database.BaseDatabase;
import com.eternal.base.database.dao.DeviceDao;
import com.eternal.base.database.dao.HistoryDao;
import com.eternal.base.database.entity.History;
import java.util.List;
import p014io.reactivex.Flowable;

public class HistorySource implements IHistorySource {
    private DeviceDao dDao;
    private HistoryDao dao;

    public HistorySource() {
        BaseDatabase instance = BaseDatabase.getInstance();
        this.dao = instance.historyDao();
        this.dDao = instance.deviceDao();
    }

    public DataSource.Factory<Integer, TmpHum> getHistory(String str) {
        return this.dao.loadAllHistoryFromMac(str);
    }

    public List<TmpHum> getHistoryMin(String str, List<String> list, long j, long j2) {
        return this.dao.loadAllHistoryFromMacMin(str, list, j, j2);
    }

    public List<TmpHum> getHistoryHour(String str, List<String> list, String str2, long j, long j2) {
        return this.dao.loadAllHistoryFromMacHour(str, list, str2, j, j2);
    }

    public List<TmpHum> getHistory(String str, long j, long j2) {
        return this.dao.loadAllHistoryFromMac(str, j, j2);
    }

    public void add(History history) {
        this.dao.insert(history);
    }

    public long getTime(String str) {
        return ((long) this.dao.loadLastTime(str)) * 1000;
    }

    public long getFirstTime(String str) {
        return ((long) this.dao.loadFirstTime(str)) * 1000;
    }

    public boolean isDegree(String str) {
        return BaseDatabase.getInstance().deviceDao().isDegree(str);
    }

    public Flowable<DeviceInfo> getInfo(String str) {
        return BaseDatabase.getInstance().deviceDao().loadDeviceInfo(str);
    }

    public void deleteAll(String str) {
        int loadLastTime = this.dao.loadLastTime(str);
        if (loadLastTime != 0) {
            this.dao.setDelete(str, loadLastTime);
            this.dao.removeOther(str, loadLastTime);
        }
    }

    public void deleteOldData(String str, long j) {
        int loadLastTime = this.dao.loadLastTime(str);
        if (loadLastTime == 0) {
            return;
        }
        if (((long) loadLastTime) < j) {
            this.dao.setDelete(str, loadLastTime);
            this.dao.removeOldData(str, loadLastTime, j);
            return;
        }
        this.dao.removeOldData(str, -1, j);
    }
}
