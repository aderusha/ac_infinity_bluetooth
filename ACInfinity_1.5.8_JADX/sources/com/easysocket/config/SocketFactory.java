package com.easysocket.config;

import com.easysocket.entity.SocketAddress;
import java.net.Socket;

public abstract class SocketFactory {
    public abstract Socket createSocket(SocketAddress socketAddress, EasySocketOptions easySocketOptions) throws Exception;
}
