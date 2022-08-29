package com.eternal.base.data.ble;

import com.eternal.base.database.entity.Log;
import com.eternal.base.protocol.EFamilialResolution;
import com.eternal.base.protocol.EFamilialResolutionV4;

public class LogEParse extends BaseParse<Log> {
    private byte logType;
    private long time;

    public LogEParse(long j) {
        this.time = j;
    }

    /* access modifiers changed from: package-private */
    public boolean checkLength() {
        byte b = this.logType;
        if (b == 0) {
            if (this.f141i + 28 < this.now.length) {
                return true;
            }
            return false;
        } else if (b == 2) {
            if (this.f141i + 30 < this.now.length) {
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
        if (this.logType == 2) {
            log = EFamilialResolutionV4.parseLog(this.now, this.f141i, this.time);
            this.f141i += 30;
        } else {
            log = EFamilialResolution.parseLog(this.now, this.f141i, this.time);
            this.f141i += 28;
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
