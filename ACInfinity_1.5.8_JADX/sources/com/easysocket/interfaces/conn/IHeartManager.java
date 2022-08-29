package com.easysocket.interfaces.conn;

import com.easysocket.connection.heartbeat.HeartManager;

public interface IHeartManager {
    void onReceiveHeartBeat();

    void startHeartbeat(byte[] bArr, HeartManager.HeartbeatListener heartbeatListener);

    void stopHeartbeat();
}
