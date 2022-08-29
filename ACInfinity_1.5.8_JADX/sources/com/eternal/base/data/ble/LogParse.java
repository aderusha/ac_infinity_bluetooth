package com.eternal.base.data.ble;

import com.eternal.base.database.entity.Log;
import com.eternal.base.protocol.ProtocolResolution;

public class LogParse extends BaseParse<Log> {
    private byte logType;
    private long time;

    public LogParse(long j) {
        this.time = j;
    }

    /* access modifiers changed from: package-private */
    public boolean checkLength() {
        if (this.logType == 0) {
            if (this.f141i + 28 < this.now.length) {
                return true;
            }
            return false;
        } else if (this.f141i + 19 < this.now.length) {
            return true;
        } else {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public Log parse() {
        Log log;
        if (this.logType == 0) {
            log = ProtocolResolution.parseLog(this.now, this.f141i, this.time);
            this.f141i += 28;
        } else {
            log = ProtocolResolution.parseCLog(this.now, this.f141i, this.time);
            this.f141i += 19;
        }
        this.total--;
        return log;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.f141i = 20;
        this.total = this.now[18];
        this.logType = this.now[19];
        if (this.now[9] != 18) {
            this.total = 0;
            this.f141i = this.now.length;
        }
    }
}
