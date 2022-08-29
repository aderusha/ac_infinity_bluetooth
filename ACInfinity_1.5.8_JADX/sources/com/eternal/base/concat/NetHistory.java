package com.eternal.base.concat;

import com.eternal.base.database.entity.History;

public class NetHistory {
    public int allSpead;
    public long createTime;
    public long createTimeBackup;
    public int dataStatus;
    public String devId;
    public int fTemperature;
    public int humidity;
    public long number;
    public long portSpead;
    public int portStatus;
    public String setId;
    public int temperature;
    public int vpdNums;

    public History toHistory(String str) {
        History history = new History();
        history.mac = str;
        boolean z = false;
        history.isDelete = false;
        history.time = this.createTime;
        history.tmp = this.temperature;
        history.hum = (char) this.humidity;
        history.vpd = this.vpdNums;
        if (this.dataStatus == 0) {
            z = true;
        }
        history.off = z;
        history.fan = (byte) this.allSpead;
        history.portState = this.portStatus;
        history.portFan = this.portSpead;
        return history;
    }
}
