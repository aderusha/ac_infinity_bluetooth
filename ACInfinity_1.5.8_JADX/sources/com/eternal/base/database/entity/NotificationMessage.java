package com.eternal.base.database.entity;

public class NotificationMessage {
    public static final byte MODEL_AUTO = 2;
    public static final byte MODEL_CYCLE = 5;
    public static final byte MODEL_PARAM = 1;
    public static final byte MODEL_SCHEDULE = 6;
    public static final byte MODEL_TIME_OFF = 4;
    public static final byte MODEL_TIME_ON = 3;
    public static final byte MODEL_VPD = 7;
    public static final byte MODEL_VPD_PARAM = 8;
    public boolean isDegree;
    public String mac;
    public byte notifyId;
    public byte notifyId2;
    public byte notifyType;
    public byte notifyType2;
    public byte port;
    public long time;
    public byte type;
    public byte[] values = new byte[10];
}
