package com.easysocket;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.connect.TcpConnection;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.config.IConnectionSwitchListener;
import com.easysocket.interfaces.conn.IConnectionManager;
import java.util.HashMap;
import java.util.Map;

public class ConnectionHolder {
    /* access modifiers changed from: private */
    public volatile Map<String, IConnectionManager> mConnectionManagerMap;

    private static class InstanceHolder {
        /* access modifiers changed from: private */
        public static final ConnectionHolder INSTANCE = new ConnectionHolder();

        private InstanceHolder() {
        }
    }

    public static ConnectionHolder getInstance() {
        return InstanceHolder.INSTANCE;
    }

    private ConnectionHolder() {
        this.mConnectionManagerMap = new HashMap();
        this.mConnectionManagerMap.clear();
    }

    public void removeConnection(SocketAddress socketAddress) {
        removeConnection(createKey(socketAddress));
    }

    public void removeConnection(String str) {
        this.mConnectionManagerMap.remove(str);
    }

    public IConnectionManager getConnection(SocketAddress socketAddress) {
        return getConnection(createKey(socketAddress));
    }

    public IConnectionManager getConnection(String str) {
        IConnectionManager iConnectionManager = this.mConnectionManagerMap.get(str);
        if (iConnectionManager == null) {
            return getConnection(str, EasySocketOptions.getDefaultOptions());
        }
        return getConnection(str, iConnectionManager.getOptions());
    }

    public IConnectionManager getConnection(SocketAddress socketAddress, EasySocketOptions easySocketOptions) {
        return getConnection(createKey(socketAddress), easySocketOptions);
    }

    public IConnectionManager getConnection(String str, EasySocketOptions easySocketOptions) {
        IConnectionManager iConnectionManager = this.mConnectionManagerMap.get(str);
        if (iConnectionManager == null) {
            return createNewManagerAndCache(str, easySocketOptions);
        }
        iConnectionManager.setOptions(easySocketOptions);
        return iConnectionManager;
    }

    private IConnectionManager createNewManagerAndCache(SocketAddress socketAddress, EasySocketOptions easySocketOptions) {
        TcpConnection tcpConnection = new TcpConnection(socketAddress);
        tcpConnection.setOptions(easySocketOptions);
        tcpConnection.setOnConnectionSwitchListener(new IConnectionSwitchListener() {
            public void onSwitchConnectionInfo(IConnectionManager iConnectionManager, SocketAddress socketAddress, SocketAddress socketAddress2) {
                synchronized (ConnectionHolder.this.mConnectionManagerMap) {
                    ((IConnectionManager) ConnectionHolder.this.mConnectionManagerMap.get(ConnectionHolder.this.createKey(socketAddress))).disconnect(false);
                    ConnectionHolder.this.mConnectionManagerMap.remove(ConnectionHolder.this.createKey(socketAddress));
                    ConnectionHolder.this.mConnectionManagerMap.put(ConnectionHolder.this.createKey(socketAddress2), iConnectionManager);
                }
            }
        });
        synchronized (this.mConnectionManagerMap) {
            this.mConnectionManagerMap.put(createKey(socketAddress), tcpConnection);
        }
        return tcpConnection;
    }

    private IConnectionManager createNewManagerAndCache(String str, EasySocketOptions easySocketOptions) {
        return createNewManagerAndCache(createSocketAddress(str), easySocketOptions);
    }

    /* access modifiers changed from: private */
    public String createKey(SocketAddress socketAddress) {
        return socketAddress.getIp() + ":" + socketAddress.getPort();
    }

    private SocketAddress createSocketAddress(String str) {
        String[] split = str.split(":");
        return new SocketAddress(split[0], Integer.parseInt(split[1]));
    }
}
