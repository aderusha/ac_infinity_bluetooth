package com.easysocket.interfaces.conn;

public interface IReconnListener {
    void attach(IConnectionManager iConnectionManager);

    void detach();
}
