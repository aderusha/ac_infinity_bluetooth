package com.eternal.base.concat;

import com.eternal.base.protocol.ProtocolTransformer;

public class NetDeviceSetting {
    public int devBh;
    public int devBt;
    public int devBth;
    public int devBvpd;
    public int devCh;
    public int devCompany;
    public int devCt;
    public int devCth;
    public String devId;
    public int devLight;
    public String devName;
    public int devTh;
    public int devTt;
    public int devTth;
    public int externalPort;
    public byte isShare;
    public int offSpead;
    public int onSpead;
    public int port;
    public int portType;
    public int vpdCt;
    public int vpdCth;
    public int vpdTransition;

    public NetDeviceSetting(String str, byte b, DeviceSetting deviceSetting, boolean z) {
        this.port = b;
        this.externalPort = b;
        this.devId = str;
        this.devName = deviceSetting.name;
        this.devCompany = deviceSetting.isDegree ? 1 : 0;
        this.devLight = deviceSetting.brightness;
        if (z) {
            this.devTt = deviceSetting.otherTemperatureC;
            this.devTth = deviceSetting.otherTemperature;
            this.devTh = deviceSetting.otherHumidity;
            this.vpdTransition = deviceSetting.otherVpd;
            this.devBt = deviceSetting.transitionTemperatureC;
            this.devBth = deviceSetting.transitionTemperature;
            this.devBh = deviceSetting.transitionHumidity;
            this.devBvpd = deviceSetting.transitionVpd;
        } else {
            this.devTt = deviceSetting.transitionTemperatureC;
            this.devTth = deviceSetting.transitionTemperature;
            this.devTh = deviceSetting.transitionHumidity;
            this.vpdTransition = deviceSetting.transitionVpd;
            this.devBt = deviceSetting.otherTemperatureC;
            this.devBth = deviceSetting.otherTemperature;
            this.devBh = deviceSetting.otherHumidity;
            this.devBvpd = deviceSetting.otherVpd;
        }
        if (deviceSetting.isDegree) {
            this.devCt = deviceSetting.calibrationTemperature;
        } else {
            this.devCth = deviceSetting.calibrationTemperature;
        }
        this.devCh = deviceSetting.calibrationHumidity;
        this.vpdCt = deviceSetting.leafTemperatureC;
        this.vpdCth = deviceSetting.leafTemperatureF;
        this.offSpead = deviceSetting.typeOff;
        this.onSpead = deviceSetting.typeOn;
    }

    public DeviceSetting toDeviceSetting(String str, boolean z) {
        DeviceSetting deviceSetting = new DeviceSetting();
        deviceSetting.name = str;
        boolean z2 = true;
        if (this.devCompany != 1) {
            z2 = false;
        }
        deviceSetting.isDegree = z2;
        deviceSetting.brightness = (byte) this.devLight;
        if (z) {
            deviceSetting.transitionTemperature = (byte) this.devBth;
            deviceSetting.transitionTemperatureC = (byte) this.devBt;
            deviceSetting.transitionHumidity = (byte) this.devBh;
            deviceSetting.transitionVpd = (byte) this.devBvpd;
            deviceSetting.otherTemperature = (byte) this.devTth;
            deviceSetting.otherTemperatureC = (byte) this.devTt;
            deviceSetting.otherHumidity = (byte) this.devTh;
            deviceSetting.otherVpd = (byte) this.vpdTransition;
        } else {
            deviceSetting.transitionTemperature = (byte) this.devTth;
            deviceSetting.transitionTemperatureC = (byte) this.devTt;
            deviceSetting.transitionHumidity = (byte) this.devTh;
            deviceSetting.transitionVpd = (byte) this.vpdTransition;
            deviceSetting.otherTemperature = (byte) this.devBth;
            deviceSetting.otherTemperatureC = (byte) this.devBt;
            deviceSetting.otherHumidity = (byte) this.devBh;
            deviceSetting.otherVpd = (byte) this.devBvpd;
        }
        deviceSetting.calibrationTemperature = (byte) (deviceSetting.isDegree ? this.devCt : this.devCth);
        deviceSetting.calibrationHumidity = (byte) this.devCh;
        deviceSetting.leafTemperatureC = (byte) this.vpdCt;
        deviceSetting.leafTemperatureF = (byte) this.vpdCth;
        deviceSetting.choosePort = (byte) this.externalPort;
        deviceSetting.typeOff = (byte) this.offSpead;
        deviceSetting.typeOn = (byte) this.onSpead;
        deviceSetting.portType = this.portType;
        return deviceSetting;
    }

    public PortSetting toPortSetting(byte b, byte b2, String str, boolean z, int i) {
        PortSetting portSetting = new PortSetting();
        portSetting.name = str;
        portSetting.f138id = b;
        portSetting.isPlug = z;
        if (ProtocolTransformer.isOutletDevice(b2, i)) {
            portSetting.transitionTemperature = (byte) this.devBth;
            portSetting.transitionTemperatureC = (byte) this.devBt;
            portSetting.transitionHumidity = (byte) this.devBh;
            portSetting.transitionVpd = (byte) this.devBvpd;
            portSetting.otherTemperature = (byte) this.devTth;
            portSetting.otherTemperatureC = (byte) this.devTt;
            portSetting.otherHumidity = (byte) this.devTh;
            portSetting.otherVpd = (byte) this.vpdTransition;
        } else {
            portSetting.transitionTemperature = (byte) this.devTth;
            portSetting.transitionTemperatureC = (byte) this.devTt;
            portSetting.transitionHumidity = (byte) this.devTh;
            portSetting.transitionVpd = (byte) this.vpdTransition;
            portSetting.otherTemperature = (byte) this.devBth;
            portSetting.otherTemperatureC = (byte) this.devBt;
            portSetting.otherHumidity = (byte) this.devBh;
            portSetting.otherVpd = (byte) this.devBvpd;
        }
        portSetting.typeOn = (byte) this.onSpead;
        portSetting.typeOff = (byte) this.offSpead;
        portSetting.fanType = i;
        portSetting.portType = this.portType;
        return portSetting;
    }
}
