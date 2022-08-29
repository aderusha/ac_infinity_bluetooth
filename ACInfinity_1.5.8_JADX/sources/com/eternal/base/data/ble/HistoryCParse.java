package com.eternal.base.data.ble;

import com.eternal.base.database.entity.History;
import com.eternal.base.protocol.CFamilialResolution;

public class HistoryCParse extends BaseParse<History> {
    private int time;

    /* access modifiers changed from: package-private */
    public boolean checkLength() {
        return this.f141i + 4 < this.now.length;
    }

    /* access modifiers changed from: package-private */
    public History parse() {
        History parseHistory = CFamilialResolution.parseHistory(this.now, this.f141i, this.time);
        this.f141i += 4;
        this.time++;
        this.total--;
        return parseHistory;
    }

    public void setTime(int i) {
        this.time = i;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        this.total = this.now[13] & 255;
        this.f141i = 14;
    }
}
