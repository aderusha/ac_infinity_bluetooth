package com.eternal.base.data.ble;

import com.eternal.base.database.entity.History;
import com.eternal.base.protocol.ProtocolResolution;
import com.eternal.base.utils.ByteUtils;
import com.google.common.base.Ascii;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class HistoryDParse extends BaseParse<History> {
    private int historySize;
    private byte packetSize;
    private int portCount;
    private long start;
    private long time;
    private byte type;
    private byte version;

    public HistoryDParse(long j) {
        this.time = j;
    }

    /* access modifiers changed from: package-private */
    public boolean checkLength() {
        return this.f141i + this.packetSize < this.now.length;
    }

    /* access modifiers changed from: package-private */
    public History parse() {
        History parseDHistory = ProtocolResolution.parseDHistory(this.now, this.f141i, (this.start / 60) * 60, this.type, this.version);
        this.start += (long) this.historySize;
        this.total--;
        this.f141i += this.packetSize;
        return parseDHistory;
    }

    /* access modifiers changed from: package-private */
    public void init() {
        byte b = 4;
        this.version = (byte) ByteUtils.getBits(this.now[1], 4, 4);
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
        byte b2 = this.now[28];
        this.type = b2;
        if (b2 == 2) {
            if (this.version >= 3) {
                b = 6;
            }
            this.packetSize = b;
        } else if (b2 == 3) {
            this.packetSize = 3;
        } else {
            this.packetSize = this.version >= 3 ? (byte) 7 : 5;
        }
        this.f141i = 29;
    }
}
