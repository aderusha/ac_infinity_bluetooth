package com.eternal.base.concat;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class NetDeviceInfo {
    @SerializedName("masterPort")
    public byte choosePort;
    public byte curMode;
    @SerializedName("speak")
    public int fan;
    @SerializedName("trend")
    public byte fanState;
    @SerializedName("humidity")
    public int hum;
    @SerializedName("hTrend")
    public byte humState;
    public int isOpenAutomation;
    public int master;
    @SerializedName("advTriggerInfo")
    public NetMessageInfo messageInfo;
    public byte modeTye;
    @SerializedName("ports")
    public List<NetPort> portInfo;
    public int remainTime;
    @SerializedName("temperature")
    public int tmp;
    @SerializedName("temperatureF")
    public int tmpF;
    @SerializedName("tTrend")
    public byte tmpState;
    public byte unit;
    public int vpdnums;
    public byte vpdstatus;

    public List<PortInfo> getPortList() {
        ArrayList arrayList = new ArrayList();
        for (NetPort next : this.portInfo) {
            PortInfo portInfo2 = new PortInfo();
            portInfo2.f138id = (byte) next.portIndex;
            boolean z = false;
            portInfo2.isPlug = next.isPlug == 1;
            portInfo2.workType = (byte) next.curMode;
            portInfo2.name = next.portName;
            portInfo2.fan = next.fan;
            portInfo2.fanType = next.portResistance;
            portInfo2.fanState = next.fanState;
            if (next.isOpenAutomation != 0) {
                z = true;
            }
            portInfo2.isAdv = z;
            arrayList.add(portInfo2);
        }
        return arrayList;
    }
}
