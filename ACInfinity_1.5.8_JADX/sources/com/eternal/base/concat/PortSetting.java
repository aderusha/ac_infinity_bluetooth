package com.eternal.base.concat;

public class PortSetting extends PortInfo {
    public byte otherHumidity;
    public byte otherTemperature;
    public byte otherTemperatureC;
    public byte otherVpd;
    public byte transitionHumidity;
    public byte transitionTemperature;
    public byte transitionTemperatureC;
    public byte transitionVpd;
    public byte typeOff;
    public byte typeOn;

    public String toString() {
        return "PortSetting{id=" + this.f138id + ", portType=" + this.portType + '}';
    }
}
