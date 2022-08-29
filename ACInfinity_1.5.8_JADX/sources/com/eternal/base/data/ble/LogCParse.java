package com.eternal.base.data.ble;

import com.eternal.base.concat.HistoryInfo;
import com.eternal.base.database.entity.Log;
import com.eternal.base.protocol.CFamilialResolution;
import java.util.List;

public class LogCParse extends BaseParse<Log> {
    private long end;
    private List<HistoryInfo> list;
    private long time;

    public void setList(List<HistoryInfo> list2) {
        this.list = list2;
        HistoryInfo historyInfo = list2.get(list2.size() - 1);
        this.end = (long) (historyInfo.calibrated + historyInfo.length);
    }

    /* access modifiers changed from: package-private */
    public boolean checkLength() {
        return this.f141i + 6 < this.now.length;
    }

    /* access modifiers changed from: package-private */
    public Log parse() {
        Log parseLog = CFamilialResolution.parseLog(this.now, this.f141i);
        long j = parseLog.time;
        this.time = j;
        if ((j >> 16) == 255) {
            parseLog.time = getTime(parseLog.f142id);
        }
        if (parseLog.time > this.end) {
            parseLog.f142id = -1;
        } else {
            parseLog.f142id = (int) parseLog.time;
        }
        this.f141i += 6;
        this.total--;
        return parseLog;
    }

    private long getTime(int i) {
        int size = this.list.size() - 1;
        int i2 = 0;
        while (i2 + 1 != size) {
            int i3 = (i2 + size) / 2;
            HistoryInfo historyInfo = this.list.get(i3);
            if (historyInfo.startId > i) {
                size = i3;
            } else if (historyInfo.startId >= i) {
                return (long) historyInfo.startId;
            } else {
                i2 = i3;
            }
        }
        HistoryInfo historyInfo2 = this.list.get(size);
        if (historyInfo2.startId > i) {
            historyInfo2 = this.list.get(i2);
        }
        return (long) ((historyInfo2.calibrated + i) - historyInfo2.startId);
    }

    public long getLastTime() {
        return this.time;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.total = this.now[10] & 255;
        this.f141i = 11;
    }
}
