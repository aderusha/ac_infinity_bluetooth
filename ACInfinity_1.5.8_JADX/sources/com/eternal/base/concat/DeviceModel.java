package com.eternal.base.concat;

import java.util.List;

public class DeviceModel implements Cloneable {
    public byte alert;
    public byte autoHighHum;
    public boolean autoHighHumSwitch;
    public boolean autoHighHumTrigger;
    public byte autoHighTmp;
    public boolean autoHighTmpSwitch;
    public boolean autoHighTmpTrigger;
    public byte autoLowHum;
    public boolean autoLowHumSwitch;
    public boolean autoLowHumTrigger;
    public byte autoLowTmp;
    public boolean autoLowTmpSwitch;
    public boolean autoLowTmpTrigger;
    public byte choosePort;
    public boolean currentTypeResidueOn;
    public int currentTypeResidueTime;
    public char cycleOff;
    public char cycleOn;
    public int fan;
    public byte fanState;
    public int fanType;
    public int highVpd;
    public boolean highVpdSwitch;
    public int hum;
    public byte humState;
    public boolean isAdv;
    public boolean isControlTypeByHum;
    public boolean isDegree;
    public boolean isPlug;
    public byte leafTemperatureC;
    public int lowVpd;
    public boolean lowVpdSwitch;
    public String name;
    public byte port;
    public List<PortInfo> portList;
    public char schedOff;
    public char schedOn;
    public long time;
    public char timeOff;
    public char timeOn;
    public int tmp;
    public byte tmpState;
    public byte transitionHumidity;
    public byte transitionTemperature;
    public byte transitionTemperatureC;
    public byte typeOff;
    public byte typeOn;
    public int vpd;
    public byte vpdState;
    public byte workType;

    public DeviceModel clone() {
        try {
            return (DeviceModel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public DeviceTFP toTFP() {
        DeviceTFP deviceTFP = new DeviceTFP();
        deviceTFP.isDegree = this.isDegree;
        deviceTFP.tmp = this.tmp;
        deviceTFP.hum = this.hum;
        deviceTFP.vpd = this.vpd;
        deviceTFP.fan = this.fan;
        deviceTFP.tmpState = this.tmpState;
        deviceTFP.humState = this.humState;
        deviceTFP.vpdState = this.vpdState;
        deviceTFP.choosePort = this.choosePort;
        deviceTFP.portList = this.portList;
        deviceTFP.fanType = this.fanType;
        deviceTFP.workType = this.workType;
        deviceTFP.autoHighTmpSwitch = this.autoHighTmpSwitch;
        deviceTFP.autoLowTmpSwitch = this.autoLowTmpSwitch;
        deviceTFP.autoHighHumSwitch = this.autoHighHumSwitch;
        deviceTFP.autoLowHumSwitch = this.autoLowHumSwitch;
        deviceTFP.autoHighTmp = this.autoHighTmp;
        deviceTFP.autoLowTmp = this.autoLowTmp;
        deviceTFP.autoHighHum = this.autoHighHum;
        deviceTFP.autoLowHum = this.autoLowHum;
        deviceTFP.leafTemperatureC = this.leafTemperatureC;
        return deviceTFP;
    }

    public void update(NetDeviceMode netDeviceMode) {
        if (netDeviceMode != null) {
            this.port = netDeviceMode.externalPort;
            this.workType = netDeviceMode.atType;
            this.tmpState = netDeviceMode.tTrend;
            this.humState = netDeviceMode.hTrend;
            boolean z = true;
            this.isDegree = netDeviceMode.unit != 0;
            this.tmp = netDeviceMode.temperature;
            this.hum = netDeviceMode.humidity;
            this.fan = netDeviceMode.speak;
            this.fanState = netDeviceMode.trend;
            this.highVpdSwitch = netDeviceMode.activeHtVpd != 0;
            this.lowVpdSwitch = netDeviceMode.activeLtVpd != 0;
            this.highVpd = netDeviceMode.activeHtVpdNums;
            this.lowVpd = netDeviceMode.activeLtVpdNums;
            this.currentTypeResidueTime = Math.max(netDeviceMode.surplus, 0);
            this.currentTypeResidueOn = netDeviceMode.modeType != 0;
            this.typeOff = netDeviceMode.offSpead;
            this.typeOn = netDeviceMode.onSpead;
            this.cycleOn = (char) (netDeviceMode.activeCycleOn / 60);
            this.cycleOff = (char) (netDeviceMode.activeCycleOff / 60);
            this.schedOn = (char) netDeviceMode.schedStartTime;
            char c = (char) netDeviceMode.schedEndtTime;
            this.schedOff = c;
            if (this.schedOn >= 1440) {
                this.schedOn = 1439;
            }
            if (c >= 1440) {
                this.schedOff = 1439;
            }
            this.timeOn = (char) (netDeviceMode.acitveTimerOn / 60);
            this.timeOff = (char) (netDeviceMode.acitveTimerOff / 60);
            this.autoHighTmpSwitch = netDeviceMode.activeHt != 0;
            this.autoLowTmpSwitch = netDeviceMode.activeLt != 0;
            this.autoHighTmp = (byte) (this.isDegree ? netDeviceMode.devHt : netDeviceMode.devHtf);
            this.autoLowTmp = (byte) (this.isDegree ? netDeviceMode.devLt : netDeviceMode.devLtf);
            this.autoHighHumSwitch = netDeviceMode.activeHh != 0;
            if (netDeviceMode.activeLh == 0) {
                z = false;
            }
            this.autoLowHumSwitch = z;
            this.autoHighHum = (byte) netDeviceMode.devHh;
            this.autoLowHum = (byte) netDeviceMode.devLh;
        }
    }
}
