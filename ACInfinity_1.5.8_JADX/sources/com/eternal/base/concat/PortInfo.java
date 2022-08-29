package com.eternal.base.concat;

public class PortInfo {
    public int fan;
    public byte fanState;
    public int fanType;

    /* renamed from: id */
    public byte f138id;
    public boolean isAdv;
    public boolean isPlug;
    public String name;
    public int portType;
    public byte workType;

    public String toString() {
        return "PortInfo{id=" + this.f138id + ", name='" + this.name + '\'' + ", fanType=" + this.fanType + ", portType=" + this.portType + '}';
    }
}
