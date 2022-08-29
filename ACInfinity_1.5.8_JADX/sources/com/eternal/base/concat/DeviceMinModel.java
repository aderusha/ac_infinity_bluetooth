package com.eternal.base.concat;

import com.eternal.base.utils.ByteUtils;
import com.google.common.base.Ascii;
import kotlin.jvm.internal.CharCompanionObject;

public class DeviceMinModel extends DeviceTFP {
    public boolean automationActivated;
    public byte model;
    public DeviceModelTime time;
    public byte transitionHumidity;
    public byte transitionTemperature;
    public byte transitionTemperatureC;
    public byte typeOff;
    public byte typeOn;
    public byte[] values = new byte[8];

    public byte getFan() {
        return this.values[0];
    }

    public boolean hTmpSwitch() {
        return !ByteUtils.getBit(this.values[0], 4);
    }

    public boolean lTmpSwitch() {
        return !ByteUtils.getBit(this.values[0], 5);
    }

    public boolean hHumSwitch() {
        return !ByteUtils.getBit(this.values[0], 6);
    }

    public boolean lHumSwitch() {
        return !ByteUtils.getBit(this.values[0], 7);
    }

    public boolean hVpdSwitch() {
        return !ByteUtils.getBit(this.values[0], 6);
    }

    public boolean lVpdSwitch() {
        return !ByteUtils.getBit(this.values[0], 7);
    }

    public byte getHighTmp() {
        if (this.isDegree) {
            return this.values[2];
        }
        return this.values[1];
    }

    public byte getLowTmp() {
        if (this.isDegree) {
            return this.values[4];
        }
        return this.values[3];
    }

    public byte getHighHum() {
        return this.values[5];
    }

    public byte getLowHum() {
        return this.values[6];
    }

    public byte getHighVpd() {
        return this.values[1];
    }

    public byte getLowVpd() {
        return this.values[2];
    }

    public char getTime() {
        byte[] bArr = this.values;
        return (char) (((bArr[3] & 255) | (((bArr[1] & 255) << Ascii.DLE) | ((bArr[2] & 255) << 8))) / 60);
    }

    public char getOn() {
        byte[] bArr = this.values;
        return (char) (((bArr[3] & 255) | (((bArr[1] & 255) << Ascii.DLE) | ((bArr[2] & 255) << 8))) / 60);
    }

    public char getOff() {
        byte[] bArr = this.values;
        return (char) (((bArr[7] & 255) | (((bArr[5] & 255) << Ascii.DLE) | ((bArr[6] & 255) << 8))) / 60);
    }

    public char getStart() {
        byte[] bArr = this.values;
        if (bArr[0] == -1 && bArr[1] == -1) {
            return CharCompanionObject.MAX_VALUE;
        }
        return (char) Math.round((float) ((bArr[0] * 60) + bArr[1]));
    }

    public char getEnd() {
        byte[] bArr = this.values;
        if (bArr[2] == -1 && bArr[3] == -1) {
            return CharCompanionObject.MAX_VALUE;
        }
        return (char) Math.round((float) ((bArr[2] * 60) + bArr[3]));
    }
}
