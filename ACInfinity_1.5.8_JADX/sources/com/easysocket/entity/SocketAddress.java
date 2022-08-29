package com.easysocket.entity;

public class SocketAddress {
    private SocketAddress backupAddress;

    /* renamed from: ip */
    private String f95ip;
    private int port;

    public SocketAddress getBackupAddress() {
        return this.backupAddress;
    }

    public void setBackupAddress(SocketAddress socketAddress) {
        this.backupAddress = socketAddress;
    }

    public SocketAddress(String str, int i) {
        this.f95ip = str;
        this.port = i;
    }

    public String getIp() {
        return this.f95ip;
    }

    public int getPort() {
        return this.port;
    }
}
