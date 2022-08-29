package com.easysocket.interfaces.conn;

import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.callback.ICallBack;
import com.easysocket.interfaces.config.IOptions;
import java.io.InputStream;
import java.io.OutputStream;

public interface IConnectionManager extends ISubscribeSocketAction, IOptions<IConnectionManager>, ISend, ICallBack {
    void connect();

    void disconnect(boolean z);

    int getConnectionStatus();

    IHeartManager getHeartManager();

    InputStream getInputStream();

    OutputStream getOutStream();

    boolean isConnectViable();

    void switchHost(SocketAddress socketAddress);
}
