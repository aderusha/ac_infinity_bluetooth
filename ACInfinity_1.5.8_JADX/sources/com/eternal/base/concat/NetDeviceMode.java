package com.eternal.base.concat;

import com.eternal.base.protocol.ProtocolTransformer;
import com.google.common.primitives.UnsignedBytes;
import kotlin.text.Typography;

public class NetDeviceMode implements Cloneable {
    public int acitveTimerOff;
    public int acitveTimerOn;
    public int activeCycleOff;
    public int activeCycleOn;
    public int activeHh;
    public int activeHt;
    public int activeHtVpd;
    public int activeHtVpdNums;
    public int activeLh;
    public int activeLt;
    public int activeLtVpd;
    public int activeLtVpdNums;
    public byte atType;
    public int devHh;
    public int devHt;
    public int devHtf;
    public String devId;
    public int devLh;
    public int devLt;
    public int devLtf;
    public byte externalPort;
    public byte hTrend;
    public int humidity;
    public int isOpenAutomation;
    public String modeSetid;
    public int modeType;
    public byte offSpead;
    public byte onSpead;
    public int schedEndtTime;
    public int schedStartTime;
    public int speak;
    public int surplus;
    public byte tTrend;
    public int temperature;
    public byte trend;
    public int unit;
    public int vpdnums;
    public byte vpdstatus;

    public NetDeviceMode(DeviceModel deviceModel) {
        if (deviceModel != null) {
            this.externalPort = deviceModel.port;
            this.atType = deviceModel.workType;
            this.tTrend = deviceModel.tmpState;
            this.hTrend = deviceModel.humState;
            this.unit = deviceModel.isDegree ? 1 : 0;
            this.temperature = deviceModel.tmp;
            this.humidity = deviceModel.hum;
            int i = deviceModel.fan;
            this.speak = i;
            deviceModel.fanType = i;
            this.trend = deviceModel.fanState;
            update(deviceModel);
        }
    }

    public void update(DeviceModel deviceModel) {
        if (deviceModel != null) {
            switch (deviceModel.workType) {
                case 1:
                    this.offSpead = deviceModel.typeOff;
                    return;
                case 2:
                    this.onSpead = deviceModel.typeOn;
                    return;
                case 3:
                    this.activeHt = deviceModel.autoHighTmpSwitch ? 1 : 0;
                    this.activeLt = deviceModel.autoLowTmpSwitch ? 1 : 0;
                    if (deviceModel.isDegree) {
                        this.devHt = UnsignedBytes.toInt(deviceModel.autoHighTmp);
                        this.devLt = UnsignedBytes.toInt(deviceModel.autoLowTmp);
                        this.devHtf = Math.round(ProtocolTransformer.getFah((float) this.devHt));
                        this.devLtf = Math.round(ProtocolTransformer.getFah((float) this.devLt));
                    } else {
                        this.devHtf = UnsignedBytes.toInt(deviceModel.autoHighTmp);
                        this.devLtf = UnsignedBytes.toInt(deviceModel.autoLowTmp);
                        this.devHt = Math.round(ProtocolTransformer.getDegree((float) this.devHtf));
                        this.devLt = Math.round(ProtocolTransformer.getDegree((float) this.devLtf));
                    }
                    this.activeHh = deviceModel.autoHighHumSwitch ? 1 : 0;
                    this.activeLh = deviceModel.autoLowHumSwitch ? 1 : 0;
                    this.devHh = deviceModel.autoHighHum;
                    this.devLh = deviceModel.autoLowHum;
                    return;
                case 4:
                    this.acitveTimerOn = deviceModel.timeOn * Typography.less;
                    this.surplus = Math.max(deviceModel.currentTypeResidueTime, 0);
                    this.modeType = deviceModel.currentTypeResidueOn ^ true ? 1 : 0;
                    return;
                case 5:
                    this.acitveTimerOff = deviceModel.timeOff * Typography.less;
                    this.surplus = Math.max(deviceModel.currentTypeResidueTime, 0);
                    this.modeType = deviceModel.currentTypeResidueOn ^ true ? 1 : 0;
                    return;
                case 6:
                    this.activeCycleOn = deviceModel.cycleOn * Typography.less;
                    this.activeCycleOff = deviceModel.cycleOff * Typography.less;
                    this.surplus = Math.max(deviceModel.currentTypeResidueTime, 0);
                    this.modeType = deviceModel.currentTypeResidueOn ^ true ? 1 : 0;
                    return;
                case 8:
                    this.activeHtVpd = deviceModel.highVpdSwitch ? 1 : 0;
                    this.activeLtVpd = deviceModel.lowVpdSwitch ? 1 : 0;
                    this.activeHtVpdNums = deviceModel.highVpd;
                    this.activeLtVpdNums = deviceModel.lowVpd;
                    return;
                default:
                    this.schedStartTime = deviceModel.schedOn;
                    char c = deviceModel.schedOff;
                    this.schedEndtTime = c;
                    if (this.schedStartTime >= 1440) {
                        this.schedStartTime = 1439;
                    }
                    if (c >= 1440) {
                        this.schedEndtTime = 1439;
                    }
                    this.surplus = Math.max(deviceModel.currentTypeResidueTime, 0);
                    this.modeType = deviceModel.currentTypeResidueOn ^ true ? 1 : 0;
                    return;
            }
        }
    }

    public NetDeviceMode clone() {
        try {
            return (NetDeviceMode) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
