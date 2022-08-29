package com.eternal.base.concat;

public class DeviceName {
    public String mac;
    public String name;
    public byte port;
    public int portType;

    public DeviceName(String str, String str2, byte b, int i) {
        this.mac = str;
        this.name = str2;
        this.port = b;
        this.portType = i;
    }

    public String toString() {
        return "DeviceName{mac='" + this.mac + '\'' + ", name='" + this.name + '\'' + ", portType=" + this.portType + ", port=" + this.port + '}';
    }
}
