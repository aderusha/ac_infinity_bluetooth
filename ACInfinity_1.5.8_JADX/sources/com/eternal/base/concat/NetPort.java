package com.eternal.base.concat;

import com.google.gson.annotations.SerializedName;

public class NetPort {
    public int curMode;
    @SerializedName("speak")
    public byte fan;
    @SerializedName("trend")
    public byte fanState;
    public int isOpenAutomation;
    @SerializedName("online")
    public byte isPlug;
    @SerializedName("port")
    public int portIndex;
    public String portName;
    public int portResistance;
}
