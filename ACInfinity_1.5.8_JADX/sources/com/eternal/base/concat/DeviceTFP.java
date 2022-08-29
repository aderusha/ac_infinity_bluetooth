package com.eternal.base.concat;

import java.util.ArrayList;
import java.util.List;

public class DeviceTFP {
    public byte autoHighHum;
    public boolean autoHighHumSwitch;
    public byte autoHighTmp;
    public boolean autoHighTmpSwitch;
    public byte autoLowHum;
    public boolean autoLowHumSwitch;
    public byte autoLowTmp;
    public boolean autoLowTmpSwitch;
    public byte choosePort;
    public long connectTime;
    public byte connectType;
    public String devId;
    public int fan;
    public byte fanState;
    public int fanType;
    public int hum;
    public byte humState;
    public boolean isAdv;
    public boolean isDegree;
    public boolean isOnline;
    public byte isShare;
    public byte leafTemperatureC;
    public String name;
    public byte port;
    public List<PortInfo> portList = new ArrayList();
    public String shareEmail;
    public int tmp;
    public byte tmpState;
    public int vpd;
    public byte vpdState;
    public byte workType;
}
