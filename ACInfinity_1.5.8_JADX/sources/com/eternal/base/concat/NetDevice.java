package com.eternal.base.concat;

import com.eternal.base.database.entity.Device;
import com.eternal.framework.utils.StringUtils;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class NetDevice {
    public String appEmail;
    public String devAccesstime;
    public String devMacAddr;
    public String devTimeZone;
    public byte devVersion;
    @SerializedName("devCode")
    public String deviceCode;
    @SerializedName("devId")
    public String deviceId;
    public NetDeviceInfo deviceInfo;
    @SerializedName("devName")
    public String deviceName;
    @SerializedName("devType")
    public byte deviceType;
    public String firmwareVersion;
    public String hardwareVersion;
    @SerializedName("online")
    public byte isOnline;
    public byte isShare;
    @SerializedName("devOfftime")
    public long offTime;
    @SerializedName("devPortCount")
    public byte portCount;
    @SerializedName("id")
    public String shareId;
    public String timeGMT;
    public String timeZone;
    public int workMode;

    public Device toDevice() {
        Device device = new Device(StringUtils.appendSymbol(this.devMacAddr, ":"));
        device.deviceId = this.deviceId;
        device.name = this.deviceName;
        device.type = this.deviceType;
        device.version = this.devVersion;
        device.softwareVersion = this.firmwareVersion;
        device.hardwareVersion = this.hardwareVersion;
        device.typeName = this.deviceCode;
        device.isShare = this.isShare;
        device.shareEmail = this.appEmail;
        device.connectType = (byte) this.workMode;
        if (this.isOnline == 1) {
            device.connectTime = System.currentTimeMillis();
        } else {
            device.connectTime = this.offTime * 1000;
        }
        NetDeviceInfo netDeviceInfo = this.deviceInfo;
        if (netDeviceInfo == null) {
            return device;
        }
        device.isDegree = netDeviceInfo.unit == 1;
        device.fanState = this.deviceInfo.fanState;
        device.fan = this.deviceInfo.fan;
        device.tmpState = this.deviceInfo.tmpState;
        device.humState = this.deviceInfo.humState;
        device.choosePort = this.deviceInfo.choosePort;
        device.tmp = this.deviceInfo.tmp;
        device.workType = this.deviceInfo.curMode;
        device.hum = this.deviceInfo.hum;
        device.vpd = this.deviceInfo.vpdnums;
        device.vpdState = this.deviceInfo.vpdstatus;
        device.isAdv = this.deviceInfo.isOpenAutomation != 0;
        if (this.deviceInfo.portInfo != null) {
            ArrayList arrayList = new ArrayList();
            for (NetPort next : this.deviceInfo.portInfo) {
                PortInfo portInfo = new PortInfo();
                portInfo.f138id = (byte) next.portIndex;
                portInfo.isPlug = next.isPlug == 1;
                portInfo.workType = (byte) next.curMode;
                portInfo.name = next.portName;
                portInfo.fan = next.fan;
                portInfo.fanState = next.fanState;
                portInfo.isAdv = next.isOpenAutomation != 0;
                arrayList.add(portInfo);
            }
            device.portList = arrayList;
        }
        return device;
    }

    public DeviceTFP toTFP() {
        DeviceTFP deviceTFP = new DeviceTFP();
        deviceTFP.devId = this.deviceId;
        deviceTFP.name = this.deviceName;
        deviceTFP.isShare = this.isShare;
        deviceTFP.shareEmail = this.appEmail;
        boolean z = false;
        deviceTFP.isOnline = this.isOnline == 1;
        deviceTFP.connectType = (byte) this.workMode;
        if (deviceTFP.isOnline) {
            deviceTFP.connectTime = System.currentTimeMillis();
        } else {
            deviceTFP.connectTime = this.offTime * 1000;
        }
        NetDeviceInfo netDeviceInfo = this.deviceInfo;
        if (netDeviceInfo == null) {
            return deviceTFP;
        }
        deviceTFP.isDegree = netDeviceInfo.unit == 1;
        deviceTFP.tmp = this.deviceInfo.tmp;
        deviceTFP.workType = this.deviceInfo.curMode;
        deviceTFP.hum = this.deviceInfo.hum;
        deviceTFP.vpd = this.deviceInfo.vpdnums;
        deviceTFP.vpdState = this.deviceInfo.vpdstatus;
        deviceTFP.fan = this.deviceInfo.fan;
        deviceTFP.tmpState = this.deviceInfo.tmpState;
        deviceTFP.humState = this.deviceInfo.humState;
        deviceTFP.choosePort = this.deviceInfo.choosePort;
        if (this.deviceInfo.isOpenAutomation != 0) {
            z = true;
        }
        deviceTFP.isAdv = z;
        if (this.deviceInfo.portInfo != null) {
            deviceTFP.portList = this.deviceInfo.getPortList();
        }
        return deviceTFP;
    }

    public String getAppEmail() {
        return this.appEmail;
    }
}
