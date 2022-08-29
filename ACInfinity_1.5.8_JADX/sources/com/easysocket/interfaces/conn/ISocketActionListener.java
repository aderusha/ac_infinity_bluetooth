package com.easysocket.interfaces.conn;

import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;

public interface ISocketActionListener {
    void onSocketConnFail(SocketAddress socketAddress, boolean z);

    void onSocketConnSuccess(SocketAddress socketAddress);

    void onSocketDisconnect(SocketAddress socketAddress, boolean z);

    void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData);

    void onSocketResponse(SocketAddress socketAddress, String str);

    void onSocketResponse(SocketAddress socketAddress, byte[] bArr);
}
