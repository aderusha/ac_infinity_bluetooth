package com.easysocket.connection.connect;

import com.easysocket.config.DefaultX509ProtocolTrustManager;
import com.easysocket.config.SocketSSLConfig;
import com.easysocket.entity.SocketAddress;
import com.easysocket.utils.LogUtil;
import com.easysocket.utils.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.SecureRandom;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class TcpConnection extends SuperConnection {
    private Socket socket;

    public TcpConnection(SocketAddress socketAddress) {
        super(socketAddress);
    }

    /* access modifiers changed from: protected */
    public void openConnection() throws Exception {
        try {
            Socket socket2 = getSocket();
            this.socket = socket2;
            socket2.connect(new InetSocketAddress(this.socketAddress.getIp(), this.socketAddress.getPort()), this.socketOptions.getConnectTimeout());
            this.socket.setTcpNoDelay(true);
            if (this.socket.isConnected() && !this.socket.isClosed()) {
                onConnectionOpened();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.connectionStatus.set(0);
            throw new RuntimeException("创建socket失败");
        }
    }

    /* access modifiers changed from: protected */
    public void closeConnection() throws IOException {
        Socket socket2 = this.socket;
        if (socket2 != null) {
            socket2.close();
        }
    }

    private synchronized Socket getSocket() throws Exception {
        if (this.socketOptions.getSocketFactory() != null) {
            return this.socketOptions.getSocketFactory().createSocket(this.socketAddress, this.socketOptions);
        }
        SocketSSLConfig easySSLConfig = this.socketOptions.getEasySSLConfig();
        if (easySSLConfig == null) {
            return new Socket();
        }
        SSLSocketFactory customSSLFactory = easySSLConfig.getCustomSSLFactory();
        if (customSSLFactory == null) {
            String str = "SSL";
            if (!Utils.isStringEmpty(easySSLConfig.getProtocol())) {
                str = easySSLConfig.getProtocol();
            }
            TrustManager[] trustManagers = easySSLConfig.getTrustManagers();
            if (trustManagers == null || trustManagers.length == 0) {
                trustManagers = new TrustManager[]{new DefaultX509ProtocolTrustManager()};
            }
            try {
                SSLContext instance = SSLContext.getInstance(str);
                instance.init(easySSLConfig.getKeyManagers(), trustManagers, new SecureRandom());
                return instance.getSocketFactory().createSocket();
            } catch (Exception e) {
                if (this.socketOptions.isDebug()) {
                    e.printStackTrace();
                }
                LogUtil.m49e(e.getMessage());
                return new Socket();
            }
        } else {
            try {
                return customSSLFactory.createSocket();
            } catch (IOException e2) {
                if (this.socketOptions.isDebug()) {
                    e2.printStackTrace();
                }
                LogUtil.m49e(e2.getMessage());
                return new Socket();
            }
        }
    }

    public InputStream getInputStream() {
        Socket socket2 = this.socket;
        if (socket2 == null || !socket2.isConnected() || this.socket.isClosed()) {
            return null;
        }
        try {
            return this.socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public OutputStream getOutStream() {
        Socket socket2 = this.socket;
        if (socket2 == null || !socket2.isConnected() || this.socket.isClosed()) {
            return null;
        }
        try {
            return this.socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
