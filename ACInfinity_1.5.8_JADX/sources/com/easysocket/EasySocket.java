package com.easysocket;

import android.content.Context;
import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.heartbeat.HeartManager;
import com.easysocket.entity.SocketAddress;
import com.easysocket.entity.basemsg.SuperCallbackSender;
import com.easysocket.exception.InitialExeption;
import com.easysocket.exception.NotNullException;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionListener;

public class EasySocket {
    private static ConnectionHolder connectionHolder = ConnectionHolder.getInstance();
    private static volatile EasySocket singleton = null;
    private Context context;
    private IConnectionManager defConnection;
    private EasySocketOptions defOptions;

    public static EasySocket getInstance() {
        if (singleton == null) {
            synchronized (EasySocket.class) {
                if (singleton == null) {
                    singleton = new EasySocket();
                }
            }
        }
        return singleton;
    }

    public Context getContext() {
        return this.context;
    }

    public EasySocketOptions getDefOptions() {
        EasySocketOptions easySocketOptions = this.defOptions;
        return easySocketOptions == null ? EasySocketOptions.getDefaultOptions() : easySocketOptions;
    }

    public EasySocket createConnection(EasySocketOptions easySocketOptions, Context context2) {
        this.defOptions = easySocketOptions;
        this.context = context2;
        SocketAddress socketAddress = easySocketOptions.getSocketAddress();
        if (easySocketOptions.getSocketAddress() != null) {
            if (easySocketOptions.getBackupAddress() != null) {
                socketAddress.setBackupAddress(easySocketOptions.getBackupAddress());
            }
            if (this.defConnection == null) {
                ConnectionHolder connectionHolder2 = connectionHolder;
                if (easySocketOptions == null) {
                    easySocketOptions = EasySocketOptions.getDefaultOptions();
                }
                this.defConnection = connectionHolder2.getConnection(socketAddress, easySocketOptions);
            }
            this.defConnection.connect();
            return this;
        }
        throw new InitialExeption("请在初始化的时候设置SocketAddress");
    }

    public EasySocket connect() {
        getDefconnection().connect();
        return this;
    }

    public EasySocket connect(String str) {
        getConnection(str).connect();
        return this;
    }

    public EasySocket disconnect(boolean z) {
        getDefconnection().disconnect(z);
        return this;
    }

    public EasySocket disconnect(String str, boolean z) {
        getConnection(str).disconnect(z);
        return this;
    }

    public EasySocket destroyConnection() {
        getDefconnection().disconnect(false);
        connectionHolder.removeConnection(this.defOptions.getSocketAddress());
        this.defConnection = null;
        return this;
    }

    public EasySocket destroyConnection(String str) {
        getConnection(str).disconnect(false);
        connectionHolder.removeConnection(str);
        return this;
    }

    public IConnectionManager upCallbackMessage(SuperCallbackSender superCallbackSender) {
        getDefconnection().upCallbackMessage(superCallbackSender);
        return this.defConnection;
    }

    public IConnectionManager upCallbackMessage(SuperCallbackSender superCallbackSender, String str) {
        return getConnection(str).upCallbackMessage(superCallbackSender);
    }

    public IConnectionManager upMessage(byte[] bArr, String str) {
        return getConnection(str).upBytes(bArr);
    }

    public IConnectionManager upMessage(byte[] bArr) {
        return getDefconnection().upBytes(bArr);
    }

    public EasySocket subscribeSocketAction(ISocketActionListener iSocketActionListener) {
        getDefconnection().subscribeSocketAction(iSocketActionListener);
        return this;
    }

    public EasySocket unSubscribeSocketAction(ISocketActionListener iSocketActionListener) {
        getDefconnection().unSubscribeSocketAction(iSocketActionListener);
        return this;
    }

    public EasySocket subscribeSocketAction(ISocketActionListener iSocketActionListener, String str) {
        getConnection(str).subscribeSocketAction(iSocketActionListener);
        return this;
    }

    public EasySocket startHeartBeat(byte[] bArr, HeartManager.HeartbeatListener heartbeatListener) {
        getDefconnection().getHeartManager().startHeartbeat(bArr, heartbeatListener);
        return this;
    }

    public EasySocket startHeartBeat(byte[] bArr, String str, HeartManager.HeartbeatListener heartbeatListener) {
        getConnection(str).getHeartManager().startHeartbeat(bArr, heartbeatListener);
        return this;
    }

    public IConnectionManager getDefconnection() {
        IConnectionManager iConnectionManager = this.defConnection;
        if (iConnectionManager != null) {
            return iConnectionManager;
        }
        throw new NotNullException("你还没有创建：" + this.defOptions.getSocketAddress().getIp() + ":" + this.defOptions.getSocketAddress().getPort() + "的Socket的连接，请使用com.easysocket.EasySocket.connect()方法创建一个默认的连接");
    }

    public IConnectionManager getConnection(String str) {
        IConnectionManager connection = connectionHolder.getConnection(str);
        if (connection != null) {
            return connection;
        }
        throw new NotNullException("请先创建：" + str + "的Socket连接");
    }

    public IConnectionManager createSpecifyConnection(EasySocketOptions easySocketOptions, Context context2) {
        this.context = context2;
        ConnectionHolder connectionHolder2 = connectionHolder;
        SocketAddress socketAddress = easySocketOptions.getSocketAddress();
        if (easySocketOptions == null) {
            easySocketOptions = EasySocketOptions.getDefaultOptions();
        }
        IConnectionManager connection = connectionHolder2.getConnection(socketAddress, easySocketOptions);
        connection.connect();
        return connection;
    }

    public IConnectionManager getSpecifyConnection(String str) {
        return connectionHolder.getConnection(str);
    }

    public IConnectionManager upToSpecifyConnection(byte[] bArr, String str) {
        IConnectionManager specifyConnection = getSpecifyConnection(str);
        if (specifyConnection != null) {
            specifyConnection.upBytes(bArr);
        }
        return specifyConnection;
    }

    public void setDebug(boolean z) {
        EasySocketOptions.setIsDebug(z);
    }
}
