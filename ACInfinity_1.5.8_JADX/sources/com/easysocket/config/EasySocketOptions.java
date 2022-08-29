package com.easysocket.config;

import com.bumptech.glide.load.Key;
import com.easysocket.connection.reconnect.AbsReconnection;
import com.easysocket.connection.reconnect.DefaultReConnection;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.config.IMessageProtocol;
import java.nio.ByteOrder;

public class EasySocketOptions {
    private static boolean isDebug = true;
    /* access modifiers changed from: private */
    public SocketAddress backupAddress;
    /* access modifiers changed from: private */
    public CallbackIDFactory callbackIDFactory;
    /* access modifiers changed from: private */
    public String charsetName;
    /* access modifiers changed from: private */
    public int connectTimeout;
    /* access modifiers changed from: private */
    public SocketSSLConfig easySSLConfig;
    /* access modifiers changed from: private */
    public long heartbeatFreq;
    /* access modifiers changed from: private */
    public boolean isOpenRequestTimeout;
    /* access modifiers changed from: private */
    public int maxHeartbeatLoseTimes;
    /* access modifiers changed from: private */
    public int maxReadBytes;
    /* access modifiers changed from: private */
    public int maxResponseDataMb;
    /* access modifiers changed from: private */
    public int maxWriteBytes;
    /* access modifiers changed from: private */
    public IMessageProtocol messageProtocol;
    /* access modifiers changed from: private */
    public ByteOrder readOrder;
    /* access modifiers changed from: private */
    public AbsReconnection reconnectionManager;
    /* access modifiers changed from: private */
    public long requestTimeout;
    /* access modifiers changed from: private */
    public SocketAddress socketAddress;
    /* access modifiers changed from: private */
    public SocketFactory socketFactory;
    /* access modifiers changed from: private */
    public ByteOrder writeOrder;

    public boolean isDebug() {
        return isDebug;
    }

    public static class Builder {
        EasySocketOptions socketOptions;

        public Builder() {
            this(EasySocketOptions.getDefaultOptions());
        }

        public Builder(EasySocketOptions easySocketOptions) {
            this.socketOptions = easySocketOptions;
        }

        public Builder setSocketAddress(SocketAddress socketAddress) {
            SocketAddress unused = this.socketOptions.socketAddress = socketAddress;
            return this;
        }

        public Builder setBackupAddress(SocketAddress socketAddress) {
            SocketAddress unused = this.socketOptions.backupAddress = socketAddress;
            return this;
        }

        public Builder setOpenRequestTimeout(boolean z) {
            boolean unused = this.socketOptions.isOpenRequestTimeout = z;
            return this;
        }

        public Builder setRequestTimeout(long j) {
            long unused = this.socketOptions.requestTimeout = j;
            return this;
        }

        public Builder setCallbackIDFactory(CallbackIDFactory callbackIDFactory) {
            CallbackIDFactory unused = this.socketOptions.callbackIDFactory = callbackIDFactory;
            return this;
        }

        public Builder setWriteOrder(ByteOrder byteOrder) {
            ByteOrder unused = this.socketOptions.writeOrder = byteOrder;
            return this;
        }

        public Builder setReadOrder(ByteOrder byteOrder) {
            ByteOrder unused = this.socketOptions.readOrder = byteOrder;
            return this;
        }

        public Builder setReaderProtocol(IMessageProtocol iMessageProtocol) {
            IMessageProtocol unused = this.socketOptions.messageProtocol = iMessageProtocol;
            return this;
        }

        public Builder setMaxWriteBytes(int i) {
            int unused = this.socketOptions.maxWriteBytes = i;
            return this;
        }

        public Builder setMaxReadBytes(int i) {
            int unused = this.socketOptions.maxReadBytes = i;
            return this;
        }

        public Builder setHeartbeatFreq(long j) {
            long unused = this.socketOptions.heartbeatFreq = j;
            return this;
        }

        public Builder setMaxHeartbeatLoseTimes(int i) {
            int unused = this.socketOptions.maxHeartbeatLoseTimes = i;
            return this;
        }

        public Builder setConnectTimeout(int i) {
            int unused = this.socketOptions.connectTimeout = i;
            return this;
        }

        public Builder setMaxResponseDataMb(int i) {
            int unused = this.socketOptions.maxResponseDataMb = i;
            return this;
        }

        public Builder setReconnectionManager(AbsReconnection absReconnection) {
            AbsReconnection unused = this.socketOptions.reconnectionManager = absReconnection;
            return this;
        }

        public Builder setEasySSLConfig(SocketSSLConfig socketSSLConfig) {
            SocketSSLConfig unused = this.socketOptions.easySSLConfig = socketSSLConfig;
            return this;
        }

        public Builder setSocketFactory(SocketFactory socketFactory) {
            SocketFactory unused = this.socketOptions.socketFactory = socketFactory;
            return this;
        }

        public Builder setCharsetName(String str) {
            String unused = this.socketOptions.charsetName = str;
            return this;
        }

        public EasySocketOptions build() {
            return this.socketOptions;
        }
    }

    public static EasySocketOptions getDefaultOptions() {
        EasySocketOptions easySocketOptions = new EasySocketOptions();
        easySocketOptions.socketAddress = null;
        easySocketOptions.backupAddress = null;
        easySocketOptions.heartbeatFreq = 5000;
        easySocketOptions.messageProtocol = null;
        easySocketOptions.maxResponseDataMb = 5;
        easySocketOptions.connectTimeout = 5000;
        easySocketOptions.maxWriteBytes = 100;
        easySocketOptions.maxReadBytes = 50;
        easySocketOptions.readOrder = ByteOrder.BIG_ENDIAN;
        easySocketOptions.writeOrder = ByteOrder.BIG_ENDIAN;
        easySocketOptions.maxHeartbeatLoseTimes = 5;
        easySocketOptions.reconnectionManager = new DefaultReConnection();
        easySocketOptions.easySSLConfig = null;
        easySocketOptions.socketFactory = null;
        easySocketOptions.callbackIDFactory = null;
        easySocketOptions.requestTimeout = 10000;
        easySocketOptions.isOpenRequestTimeout = true;
        easySocketOptions.charsetName = Key.STRING_CHARSET_NAME;
        return easySocketOptions;
    }

    public String getCharsetName() {
        return this.charsetName;
    }

    public ByteOrder getWriteOrder() {
        return this.writeOrder;
    }

    public ByteOrder getReadOrder() {
        return this.readOrder;
    }

    public IMessageProtocol getMessageProtocol() {
        return this.messageProtocol;
    }

    public int getMaxWriteBytes() {
        return this.maxWriteBytes;
    }

    public int getMaxReadBytes() {
        return this.maxReadBytes;
    }

    public long getHeartbeatFreq() {
        return this.heartbeatFreq;
    }

    public int getMaxHeartbeatLoseTimes() {
        return this.maxHeartbeatLoseTimes;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    public int getMaxResponseDataMb() {
        return this.maxResponseDataMb;
    }

    public AbsReconnection getReconnectionManager() {
        return this.reconnectionManager;
    }

    public SocketSSLConfig getEasySSLConfig() {
        return this.easySSLConfig;
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public long getRequestTimeout() {
        return this.requestTimeout;
    }

    public boolean isOpenRequestTimeout() {
        return this.isOpenRequestTimeout;
    }

    public CallbackIDFactory getCallbackIDFactory() {
        return this.callbackIDFactory;
    }

    public static void setIsDebug(boolean z) {
        isDebug = z;
    }

    public void setWriteOrder(ByteOrder byteOrder) {
        this.writeOrder = byteOrder;
    }

    public void setReadOrder(ByteOrder byteOrder) {
        this.readOrder = byteOrder;
    }

    public void setMessageProtocol(IMessageProtocol iMessageProtocol) {
        this.messageProtocol = iMessageProtocol;
    }

    public void setMaxWriteBytes(int i) {
        this.maxWriteBytes = i;
    }

    public void setMaxReadBytes(int i) {
        this.maxReadBytes = i;
    }

    public void setHeartbeatFreq(long j) {
        this.heartbeatFreq = j;
    }

    public void setMaxHeartbeatLoseTimes(int i) {
        this.maxHeartbeatLoseTimes = i;
    }

    public void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public void setMaxResponseDataMb(int i) {
        this.maxResponseDataMb = i;
    }

    public void setReconnectionManager(AbsReconnection absReconnection) {
        this.reconnectionManager = absReconnection;
    }

    public void setEasySSLConfig(SocketSSLConfig socketSSLConfig) {
        this.easySSLConfig = socketSSLConfig;
    }

    public void setSocketFactory(SocketFactory socketFactory2) {
        this.socketFactory = socketFactory2;
    }

    public void setCallbackIDFactory(CallbackIDFactory callbackIDFactory2) {
        this.callbackIDFactory = callbackIDFactory2;
    }

    public void setRequestTimeout(long j) {
        this.requestTimeout = j;
    }

    public void setOpenRequestTimeout(boolean z) {
        this.isOpenRequestTimeout = z;
    }

    public SocketAddress getSocketAddress() {
        return this.socketAddress;
    }

    public SocketAddress getBackupAddress() {
        return this.backupAddress;
    }
}
