package com.easysocket.interfaces.config;

import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.conn.IConnectionManager;

public interface IConnectionSwitchListener {
    void onSwitchConnectionInfo(IConnectionManager iConnectionManager, SocketAddress socketAddress, SocketAddress socketAddress2);
}
