package com.eternal.base.concat;

import java.util.List;

public class DeviceSetting implements Cloneable {
    public byte brightness;
    public byte calibrationHumidity;
    public byte calibrationTemperature;
    public byte choosePort;
    public int hum;
    public boolean isDegree;
    public byte isShare;
    public byte leafTemperatureC;
    public byte leafTemperatureF;
    public String name;
    public byte otherHumidity;
    public byte otherTemperature;
    public byte otherTemperatureC;
    public byte otherVpd;
    public List<PortSetting> portList;
    public int portType;
    public String shareEmail;
    public int tmp;
    public byte transitionHumidity;
    public byte transitionTemperature;
    public byte transitionTemperatureC;
    public byte transitionVpd;
    public byte typeOff;
    public byte typeOn;

    public DeviceSetting clone() {
        try {
            return (DeviceSetting) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "DeviceSetting{name='" + this.name + '\'' + ", portType=" + this.portType + '}';
    }
}
