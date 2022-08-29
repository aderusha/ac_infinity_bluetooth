package com.eternal.base.data.ble;

import com.eternal.base.database.entity.History;
import com.eternal.base.protocol.ProtocolResolution;
import com.google.common.base.Ascii;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class HistoryParse extends BaseParse<History> {
    private int historySize;
    private byte packetSize;
    private long start;
    private long time;

    public HistoryParse(long j) {
        this.time = j;
    }

    /* access modifiers changed from: package-private */
    public boolean checkLength() {
        return this.f141i + this.packetSize < this.now.length;
    }

    /* access modifiers changed from: package-private */
    public History parse() {
        History parseHistory = ProtocolResolution.parseHistory(this.now, this.f141i, (this.start / 60) * 60, this.packetSize == 4);
        this.start += (long) this.historySize;
        this.total--;
        this.f141i += this.packetSize;
        return parseHistory;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        if (this.now[18] == 0) {
            Calendar instance = Calendar.getInstance();
            instance.set(this.now[19] + 2000, this.now[20] - 1, this.now[21], this.now[22], this.now[23], this.now[24]);
            this.start = instance.getTimeInMillis() / 1000;
        } else {
            this.start = (long) (((this.now[19] & 255) << Ascii.CAN) | ((this.now[20] & 255) << Ascii.DLE) | ((this.now[21] & 255) << 8) | (this.now[22] & 255));
            this.start = TimeUnit.MILLISECONDS.toSeconds(this.time) - this.start;
        }
        this.historySize = ((this.now[25] & 255) << 8) | (this.now[26] & 255);
        this.total = this.now[27];
        if (this.now[28] == 2) {
            this.packetSize = 4;
        } else {
            this.packetSize = 5;
        }
        this.f141i = 29;
    }
}
