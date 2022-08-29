package com.eternal.base.concat;

public class NotificationName {
    public byte childId;
    public byte childIndex;
    public byte groupIndex;

    /* renamed from: id */
    public int f137id;
    public String name;
    public byte port;
    public byte type;

    public NotificationName(int i, String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.f137id = i;
        this.name = str;
        this.type = b;
        this.port = b2;
        this.groupIndex = b3;
        this.childId = b4;
        this.childIndex = b5;
    }
}
