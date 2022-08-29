package com.eternal.base.concat;

import java.util.List;

public class PortList {
    public byte choosePort;
    public List<PortInfo> portList;

    public PortList() {
    }

    public PortList(List<PortInfo> list, byte b) {
        this.portList = list;
        this.choosePort = b;
    }
}
