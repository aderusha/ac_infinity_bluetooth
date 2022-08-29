package com.eternal.base.concat;

import com.google.gson.annotations.SerializedName;

public class NetMessageInfo {
    public int activeHtVpd;
    public int activeHtVpdParam;
    public int activeLtVpd;
    public int activeLtVpdParam;
    public int currentMode;
    public int currentStatus;
    public int cycleOff;
    public int cycleOn;
    public int highHumi;
    public boolean highHumiSwitch = true;
    public int highTempC;
    public int highTempF;
    public boolean highTempSwitch = true;
    public int htVpdNums;
    public boolean isCycleOn = true;
    public boolean isHighHumi = true;
    public boolean isHighTemp = true;
    public boolean isLowHumi = true;
    public boolean isLowTemp = true;
    public boolean isSchedOn = true;
    public int lowHumi;
    public boolean lowHumiSwitch = true;
    public int lowTempC;
    public int lowTempF;
    public boolean lowTempSwitch = true;
    public int ltVpdNums;
    public int notificationId;
    public String notificationName;
    @SerializedName("port")
    public int portIndex;
    public int schedOff;
    public int schedOn;
    public int timerToOff;
    public int timerToOn;
    public long triggerTimeSecond = 0;
    public int type;
    public int vptStatus;
}
