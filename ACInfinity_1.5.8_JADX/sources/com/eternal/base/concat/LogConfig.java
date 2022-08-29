package com.eternal.base.concat;

import java.util.List;

public class LogConfig {
    public int portCount;
    public List<Byte> portSelects;
    public List<Byte> types;

    public LogConfig(List<Byte> list, int i, List<Byte> list2) {
        this.portCount = i;
        this.portSelects = list;
        this.types = list2;
    }
}
