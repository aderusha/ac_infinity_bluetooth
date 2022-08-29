package com.eternal.base;

public interface IConnectAction {
    void connected();

    void disconnected();

    void setConnectType(String str, byte b);
}
