package com.eternal.base.database.entity;

public class Log {
    public static final byte MODEL_AI_V4 = 13;
    public static final byte MODEL_AUTO = 3;
    public static final byte MODEL_AUTOMATION_V4 = 12;
    public static final byte MODEL_CYCLE = 6;
    public static final byte MODEL_HUM_PARAM_V4 = 10;
    public static final byte MODEL_OFF = 1;
    public static final byte MODEL_ON = 2;
    public static final byte MODEL_PARAM = 8;
    public static final byte MODEL_SCHEDULE = 7;
    public static final byte MODEL_TEMP_PARAM_V4 = 9;
    public static final byte MODEL_TIME_OFF = 5;
    public static final byte MODEL_TIME_ON = 4;
    public static final byte MODEL_VPD = 9;
    public static final byte MODEL_VPD_PARAM = 10;
    public static final byte MODEL_VPD_PARAM_V4 = 11;
    public static final byte MODEL_VPD_V4 = 8;
    public static final byte TYPE_ALARM = 2;
    public static final byte TYPE_AUTOMATION = 1;
    public static final byte TYPE_LOG = 0;
    public static final byte TYPE_NOTIFY = 3;
    public byte day;
    public char end;
    public byte fan;
    public byte hHum;
    public byte hTmpC;
    public byte hTmpF;
    public byte hVpd;

    /* renamed from: id */
    public int f142id;
    public boolean isDelete;
    public boolean isRead;
    public boolean isStart;
    public byte lHum;
    public byte lTmpC;
    public byte lTmpF;
    public byte lVpd;
    public byte logType;
    public String mac;
    public byte model;
    public int netId;
    public byte notifyId;
    public char off;

    /* renamed from: on */
    public char f143on;
    public byte port;
    public char start;
    public long time;
    public short tmpHum;

    public String toString() {
        return "Log{mac='" + this.mac + '\'' + ", id=" + this.f142id + ", time=" + this.time + ", logType=" + this.logType + ", notifyId=" + this.notifyId + '}';
    }
}
