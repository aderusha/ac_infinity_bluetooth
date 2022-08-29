package com.easysocket.interfaces.conn;

import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;

public abstract class SocketActionListener implements ISocketActionListener {
    public void onSocketConnFail(SocketAddress socketAddress, boolean z) {
    }

    public void onSocketConnSuccess(SocketAddress socketAddress) {
    }

    public void onSocketDisconnect(SocketAddress socketAddress, boolean z) {
    }

    public void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData) {
    }

    public void onSocketResponse(SocketAddress socketAddress, String str) {
    }

    public void onSocketResponse(SocketAddress socketAddress, byte[] bArr) {
    }
}
