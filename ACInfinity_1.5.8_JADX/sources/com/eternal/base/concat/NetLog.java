package com.eternal.base.concat;

import com.eternal.base.database.entity.Log;
import com.eternal.base.protocol.ProtocolTransformer;

public class NetLog {
    public int activeHtVpd;
    public int activeHtVpdNums;
    public int activeHtVpdParam;
    public int activeLtVpd;
    public int activeLtVpdNums;
    public int activeLtVpdParam;
    public long advanceId;
    public int alarmHighHumi;
    public int alarmHighHumiSwitch;
    public int alarmHighTempC;
    public int alarmHighTempF;
    public int alarmHighTempSwitch;
    public int alarmLowHumi;
    public int alarmLowHumiSwitch;
    public int alarmLowTempC;
    public int alarmLowTempF;
    public int alarmLowTempSwitch;
    public int autoEndTime;
    public int autoHighHumi;
    public int autoHighHumiSwitch;
    public int autoHighTempC;
    public int autoHighTempF;
    public int autoHighTempSwitch;
    public int autoLowHumi;
    public int autoLowHumiSwitch;
    public int autoLowTempC;
    public int autoLowTempF;
    public int autoLowTempSwitch;
    public int autoStartTime;
    public int currentMode;
    public int currentStatus;
    public int cycleOff;
    public int cycleOn;
    public String devId;
    public int fanSpeedOff;
    public int fanSpeedOn;

    /* renamed from: id */
    public int f136id;
    public int isActivateAlarmHighHumi;
    public int isActivateAlarmHighTemp;
    public int isActivateAlarmLowHumi;
    public int isActivateAlarmLowTemp;
    public int isAutoHighHumi;
    public int isAutoHighTemp;
    public int isAutoLowHumi;
    public int isAutoLowTemp;
    public int isCycleOn;
    public int isDeactivateAlarmHighHumi;
    public int isDeactivateAlarmHighTemp;
    public int isDeactivateAlarmLowHumi;
    public int isDeactivateAlarmLowTemp;
    public int isSchedStart;
    public int logFormat;
    public long logId;
    public long logTime;
    public int logType;
    public int portIndex;
    public int remainTime;
    public int schedEnd;
    public int schedStart;
    public int timerOff;
    public int timerOn;

    public Log toLog(String str) {
        Log log = new Log();
        log.mac = str;
        log.f142id = (int) this.logId;
        log.netId = this.f136id;
        log.time = this.logTime * 1000;
        log.logType = (byte) this.logType;
        log.notifyId = (byte) ((int) this.advanceId);
        log.start = (char) this.autoStartTime;
        log.end = (char) this.autoEndTime;
        log.model = (byte) this.currentMode;
        log.fan = (byte) this.currentStatus;
        boolean z = true;
        if (log.model == 9 || log.model == 10) {
            log.tmpHum = (short) ProtocolTransformer.setLogVpd(this.activeHtVpd != 0, this.activeLtVpd != 0, this.activeHtVpdParam != 0, this.activeLtVpdParam != 0);
        } else {
            log.tmpHum = (short) ProtocolTransformer.setTmpHum(this.isAutoHighTemp != 0, this.isAutoLowTemp != 0, this.isAutoHighHumi != 0, this.isAutoLowHumi != 0, this.autoHighTempSwitch != 0, this.autoLowTempSwitch != 0, this.autoHighHumiSwitch != 0, this.autoLowHumiSwitch != 0);
        }
        log.hTmpF = (byte) this.autoHighTempF;
        log.lTmpF = (byte) this.autoLowTempF;
        log.hTmpC = (byte) this.autoHighTempC;
        log.lTmpC = (byte) this.autoLowTempC;
        log.hHum = (byte) this.autoHighHumi;
        log.lHum = (byte) this.autoLowHumi;
        log.hVpd = (byte) this.activeHtVpdNums;
        log.lVpd = (byte) this.activeLtVpdNums;
        if (log.model == 4) {
            log.f143on = (char) (this.timerOn / 60);
        } else if (log.model == 5) {
            log.off = (char) (this.timerOff / 60);
        } else if (log.model == 6) {
            if (this.isCycleOn != 1) {
                z = false;
            }
            log.isStart = z;
            log.f143on = (char) (this.cycleOn / 60);
            log.off = (char) (this.cycleOff / 60);
        } else if (log.model == 7) {
            if (this.isSchedStart != 0) {
                z = false;
            }
            log.isStart = z;
            log.f143on = (char) this.schedStart;
            log.off = (char) this.schedEnd;
        }
        log.isDelete = false;
        log.isRead = false;
        log.port = (byte) this.portIndex;
        return log;
    }
}
