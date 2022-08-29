package com.easysocket.connection.action;

public interface SocketStatus {
    public static final int SOCKET_CONNECTED = 2;
    public static final int SOCKET_CONNECTING = 1;
    public static final int SOCKET_DISCONNECTED = 0;
    public static final int SOCKET_DISCONNECTING = 3;
}
