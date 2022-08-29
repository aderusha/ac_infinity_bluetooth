package com.eternal.base.concat;

import com.eternal.base.database.entity.Notification;
import com.eternal.base.protocol.ProtocolTransformer;
import com.google.common.base.Ascii;
import com.google.common.primitives.UnsignedBytes;
import kotlin.text.Typography;

public class NetAdvance {
    private int activeHtVpdNums;
    private int activeHtVpdParam;
    private int activeLtVpdNums;
    private int activeLtVpdParam;
    public int activeVpdMode;
    private int activeVpdParamMode;
    public int acviteHh;
    public int acviteHt;
    public int acviteLh;
    public int acviteLt;
    public int advActiveAutoMode;
    public int advActiveCycleMode;
    public int advActiveOff;
    public int advActiveOn;
    public int advActiveSchedule;
    public int advActiveTh;
    public int advActiveTimerOff;
    public int advActiveTimerOn;
    public int advCode;
    public int advCycleOffTime;
    public int advCycleOnTime;
    public String advId;
    public String advName;
    public int advStatus;
    public int advType;
    public int codeAddress;
    public String devCode;
    public String devId;
    public int endTime;
    public byte externalPort;
    public int hhParameter;
    public int htParameter;
    public int htParameterF;
    public boolean isError;
    public int lhParameter;
    public int ltParameter;
    public int ltParameterF;
    public int modeType;
    public int nameCode;
    public int offSpeed;
    public int onSpeed;
    public int startTime;

    public NetAdvance(Notification notification) {
        if (notification != null) {
            this.advId = notification.advId;
            this.devId = notification.devId;
            this.externalPort = notification.port;
            this.advCode = notification.f144id;
            this.devCode = notification.devCode;
            this.nameCode = notification.nameCode;
            this.codeAddress = notification.codeAddress;
            this.advName = notification.name;
            this.advStatus = notification.open ? 1 : 0;
            this.htParameterF = UnsignedBytes.toInt(notification.hTmpF);
            this.htParameter = UnsignedBytes.toInt(notification.hTmpC);
            this.ltParameterF = UnsignedBytes.toInt(notification.lTmpF);
            this.ltParameter = UnsignedBytes.toInt(notification.lTmpC);
            this.hhParameter = UnsignedBytes.toInt(notification.hHum);
            this.lhParameter = UnsignedBytes.toInt(notification.lHum);
            this.activeHtVpdNums = UnsignedBytes.toInt(notification.hVpd);
            this.activeLtVpdNums = UnsignedBytes.toInt(notification.lVpd);
            this.advType = notification.type;
            int i = 0;
            if (notification.type == 1) {
                this.startTime = notification.start;
                this.endTime = notification.end;
                this.modeType = notification.model;
                this.offSpeed = notification.levelOff;
                this.onSpeed = notification.levelOn;
                this.advCycleOnTime = notification.cycleOn * Typography.less;
                this.advCycleOffTime = notification.cycleOff * Typography.less;
                if (this.modeType == 5) {
                    this.activeHtVpdParam = (notification.tmpHum & 2) != 0 ? 1 : 0;
                    this.activeLtVpdParam = (notification.tmpHum & 1) != 0 ? 1 : i;
                    this.activeVpdParamMode = 1;
                    return;
                }
                this.acviteHt = (notification.tmpHum & 8) != 0 ? 1 : 0;
                this.acviteLt = (notification.tmpHum & 4) != 0 ? 1 : 0;
                this.acviteHh = (notification.tmpHum & 2) != 0 ? 1 : 0;
                this.acviteLh = (notification.tmpHum & 1) != 0 ? 1 : i;
                return;
            }
            this.advActiveSchedule = ProtocolTransformer.scheduleModel(notification.model) ? 1 : 0;
            this.advActiveCycleMode = ProtocolTransformer.cycleModel(notification.model) ? 1 : 0;
            this.advActiveTimerOn = ProtocolTransformer.onModel(notification.model) ? 1 : 0;
            this.advActiveTimerOff = ProtocolTransformer.offModel(notification.model) ? 1 : 0;
            this.advActiveAutoMode = ProtocolTransformer.autoModel(notification.model) ? 1 : 0;
            this.advActiveTh = ProtocolTransformer.paramModel(notification.model) ? 1 : 0;
            this.activeVpdParamMode = ProtocolTransformer.vpdParam(notification.model) ? 1 : 0;
            this.activeVpdMode = ProtocolTransformer.vpdModel(notification.model) ? 1 : 0;
            this.activeHtVpdParam = (notification.tmpHum & 32) != 0 ? 1 : 0;
            this.activeLtVpdParam = (notification.tmpHum & Ascii.DLE) != 0 ? 1 : 0;
            this.acviteHt = (notification.tmpHum & 8) != 0 ? 1 : 0;
            this.acviteLt = (notification.tmpHum & 4) != 0 ? 1 : 0;
            this.acviteHh = (notification.tmpHum & 2) != 0 ? 1 : 0;
            this.acviteLh = (notification.tmpHum & 1) != 0 ? 1 : i;
        }
    }

    public Notification toNotification(String str) {
        Notification notification = new Notification();
        notification.devId = this.devId;
        notification.advId = this.advId;
        notification.port = this.externalPort;
        notification.f144id = this.advCode;
        notification.groupIndex = (byte) notification.f144id;
        notification.devCode = this.devCode;
        notification.nameCode = this.nameCode;
        notification.codeAddress = this.codeAddress;
        notification.mac = str;
        notification.name = this.advName;
        boolean z = false;
        notification.open = this.advStatus != 0;
        notification.hTmpF = (byte) this.htParameterF;
        notification.hTmpC = (byte) this.htParameter;
        notification.lTmpF = (byte) this.ltParameterF;
        notification.lTmpC = (byte) this.ltParameter;
        notification.hHum = (byte) this.hhParameter;
        notification.lHum = (byte) this.lhParameter;
        notification.type = (byte) this.advType;
        notification.hVpd = (byte) this.activeHtVpdNums;
        notification.lVpd = (byte) this.activeLtVpdNums;
        if (notification.type == 1) {
            notification.start = (char) this.startTime;
            notification.end = (char) this.endTime;
            notification.model = (byte) this.modeType;
            notification.levelOff = this.offSpeed;
            notification.levelOn = this.onSpeed;
            notification.cycleOn = (char) (this.advCycleOnTime / 60);
            notification.cycleOff = (char) (this.advCycleOffTime / 60);
            if (notification.model == 5) {
                boolean z2 = this.activeHtVpdParam != 0;
                if (this.activeLtVpdParam != 0) {
                    z = true;
                }
                notification.tmpHum = ProtocolTransformer.setVpd(z2, z);
            } else {
                boolean z3 = this.acviteHt != 0;
                boolean z4 = this.acviteLt != 0;
                boolean z5 = this.acviteHh != 0;
                if (this.acviteLh != 0) {
                    z = true;
                }
                notification.tmpHum = ProtocolTransformer.setVpd(z3, z4, z5, z);
            }
        } else {
            notification.tmpHum = ProtocolTransformer.setVpd(this.acviteHt != 0, this.acviteLt != 0, this.acviteHh != 0, this.acviteLh != 0, this.activeHtVpdParam != 0, this.activeLtVpdParam != 0);
            notification.model = ProtocolTransformer.setModelType(this.advActiveSchedule != 0, this.advActiveCycleMode != 0, this.advActiveTimerOn != 0, this.advActiveTimerOff != 0, this.advActiveAutoMode != 0, this.advActiveTh != 0, this.activeVpdParamMode != 0, this.activeVpdMode != 0);
        }
        return notification;
    }

    public String toString() {
        return "NetAdvance{devId='" + this.devId + '\'' + ", externalPort=" + this.externalPort + ", advName='" + this.advName + '\'' + ", advType=" + this.advType + ", advCode=" + this.advCode + '}';
    }
}
