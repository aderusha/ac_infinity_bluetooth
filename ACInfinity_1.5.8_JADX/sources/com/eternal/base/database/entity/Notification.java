package com.eternal.base.database.entity;

public class Notification implements Cloneable {
    public static final byte AUTOMATION_MODEL_AUTO_PARAM = 4;
    public static final byte AUTOMATION_MODEL_CYCLE = 3;
    public static final byte AUTOMATION_MODEL_OFF = 2;
    public static final byte AUTOMATION_MODEL_ON = 1;
    public static final byte AUTOMATION_MODEL_VPD_PARAM = 5;
    public boolean advActive;
    public String advId;
    public byte beeps;
    public byte childId;
    public byte childIndex;
    public int codeAddress;
    public char cycleOff;
    public char cycleOn;
    public byte day;
    public String devCode;
    public String devId;
    public char end;
    public byte groupIndex;
    public byte hHum;
    public byte hTmpC;
    public byte hTmpF;
    public byte hVpd;

    /* renamed from: id */
    public int f144id;
    public byte lHum;
    public byte lTmpC;
    public byte lTmpF;
    public byte lVpd;
    public int levelOff;
    public int levelOn;
    public String mac;
    public byte model;
    public String name;
    public int nameCode;
    public boolean open;
    public byte port;
    public char start;
    public byte tmpHum;
    public byte type;

    public boolean equals(Object obj) {
        if (!(obj instanceof Notification)) {
            return false;
        }
        Notification notification = (Notification) obj;
        if (this.mac.equals(notification.mac) && this.f144id == notification.f144id && this.type == notification.type) {
            return true;
        }
        return false;
    }

    public boolean equal(Notification notification) {
        return notification != null && this.f144id == notification.f144id && this.type == notification.type && this.port == notification.port;
    }

    public Notification clone() {
        try {
            return (Notification) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return "{id=" + this.f144id + ", open=" + this.open + ", port=" + this.port + ", groupIndex=" + this.groupIndex + ", childId=" + this.childId + ", childIndex=" + this.childIndex + ", day=" + this.day + '}';
    }
}
