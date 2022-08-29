package com.eternal.base.concat;

import android.text.TextUtils;
import com.eternal.base.data.RepositoryInjection;
import java.util.ArrayList;
import java.util.List;

public class DeviceInfo {
    public static final byte DECREASE_TREND = -1;
    public static final byte INCREASE_TREND = 1;
    public static final byte NORMAL_TREND = 0;
    public byte autoHighHum;
    public boolean autoHighHumSwitch;
    public byte autoHighTmp;
    public boolean autoHighTmpSwitch;
    public byte autoLowHum;
    public boolean autoLowHumSwitch;
    public byte autoLowTmp;
    public boolean autoLowTmpSwitch;
    public byte choosePort;
    public long connectTime = System.currentTimeMillis();
    public byte connectType;
    public String deviceId;
    public int fan = 60;
    public byte fanState = 1;
    public int fanType;
    public String firmwareVersion;
    public String hardwareVersion;
    public int hum = 50;
    public byte humState = -1;
    public boolean isAdv;
    public boolean isDegree = true;
    public boolean isPlug;
    public byte isShare;
    public byte leafTemperatureC = 0;
    public byte leafTemperatureF = 0;
    public String mac = "asdfsad";
    public String name = "New Device";
    public byte port;
    public List<PortInfo> portList = new ArrayList();
    public int portType;
    public String shareEmail;
    public String softwareVersion;
    public int tmp = 40;
    public byte tmpState = 1;
    public long triggerNotifyTime;
    public byte type = 1;
    public String typeName = "NEM";
    public byte version;
    public int vpd = 0;
    public byte vpdState;
    public byte workType;

    public void update(DeviceTFP deviceTFP, byte b) {
        this.isDegree = deviceTFP.isDegree;
        this.connectType = deviceTFP.connectType;
        this.tmp = deviceTFP.tmp;
        this.hum = deviceTFP.hum;
        this.vpd = deviceTFP.vpd;
        this.fan = deviceTFP.fan;
        this.tmpState = deviceTFP.tmpState;
        this.humState = deviceTFP.humState;
        this.vpdState = deviceTFP.vpdState;
        this.choosePort = deviceTFP.choosePort;
        this.isShare = deviceTFP.isShare;
        this.shareEmail = deviceTFP.shareEmail;
        this.workType = deviceTFP.workType;
        this.isAdv = deviceTFP.isAdv;
        byte b2 = this.type;
        if (b2 == 7 || ((b2 == 11 && this.connectType == 0) || b2 == 9 || b2 == 12)) {
            this.name = deviceTFP.name;
        } else if (b2 == 8 || b2 == 11) {
            String str = this.name;
            if (str == null || !str.equals(deviceTFP.name)) {
                RepositoryInjection.providerDeviceRepository().setDeviceName(this.mac, this.port, deviceTFP.name);
            }
            this.deviceId = deviceTFP.devId;
        }
        if (!(this.portList == null || deviceTFP.portList == null)) {
            for (PortInfo next : deviceTFP.portList) {
                for (PortInfo next2 : this.portList) {
                    if (next.f138id == next2.f138id) {
                        byte b3 = this.type;
                        if (b3 == 7 || ((b3 == 11 && this.connectType == 0) || b3 == 9 || b3 == 12)) {
                            next.name = next2.name;
                        } else if ((b3 == 8 || b3 == 11) && (next2.name == null || !next2.name.equals(next.name))) {
                            RepositoryInjection.providerDeviceRepository().setDeviceName(this.mac, next.f138id, next.name);
                        }
                        next.portType = next2.portType;
                    }
                }
            }
        }
        this.portList = deviceTFP.portList;
        this.fanType = deviceTFP.fanType;
        this.fanState = deviceTFP.fanState;
        this.autoHighTmpSwitch = deviceTFP.autoHighTmpSwitch;
        this.autoLowTmpSwitch = deviceTFP.autoLowTmpSwitch;
        this.autoHighHumSwitch = deviceTFP.autoHighHumSwitch;
        this.autoLowHumSwitch = deviceTFP.autoLowHumSwitch;
        this.autoHighTmp = deviceTFP.autoHighTmp;
        this.autoLowTmp = deviceTFP.autoLowTmp;
        this.autoHighHum = deviceTFP.autoHighHum;
        this.autoLowHum = deviceTFP.autoLowHum;
    }

    public DeviceModel toCModel() {
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.isDegree = this.isDegree;
        deviceModel.autoHighTmpSwitch = this.autoHighTmpSwitch;
        deviceModel.autoLowTmpSwitch = this.autoLowTmpSwitch;
        deviceModel.autoHighHumSwitch = this.autoHighHumSwitch;
        deviceModel.autoLowHumSwitch = this.autoLowHumSwitch;
        deviceModel.autoHighTmp = this.autoHighTmp;
        deviceModel.autoLowTmp = this.autoLowTmp;
        deviceModel.autoHighHum = this.autoHighHum;
        deviceModel.autoLowHum = this.autoLowHum;
        return deviceModel;
    }

    public PortInfo toPortInfo(String str, boolean z) {
        PortInfo portInfo = new PortInfo();
        portInfo.f138id = this.port;
        if (!TextUtils.isEmpty(str)) {
            portInfo.name = str;
        } else {
            portInfo.name = this.name;
        }
        portInfo.fanType = this.fanType;
        portInfo.portType = this.portType;
        portInfo.fanState = this.fanState;
        portInfo.isPlug = z;
        portInfo.fan = this.fan;
        portInfo.workType = this.workType;
        portInfo.isAdv = this.isAdv;
        return portInfo;
    }
}
