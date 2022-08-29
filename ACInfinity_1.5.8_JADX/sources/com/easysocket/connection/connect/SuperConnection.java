package com.easysocket.connection.connect;

import com.easysocket.EasySocket;
import com.easysocket.callback.SuperCallBack;
import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.action.SocketAction;
import com.easysocket.connection.dispatcher.CallbackResponseDispatcher;
import com.easysocket.connection.dispatcher.SocketActionDispatcher;
import com.easysocket.connection.heartbeat.HeartManager;
import com.easysocket.connection.iowork.IOManager;
import com.easysocket.connection.reconnect.AbsReconnection;
import com.easysocket.entity.SocketAddress;
import com.easysocket.entity.basemsg.SuperCallbackSender;
import com.easysocket.exception.NotNullException;
import com.easysocket.interfaces.config.IConnectionSwitchListener;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionListener;
import com.easysocket.utils.LogUtil;
import com.easysocket.utils.Utils;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class SuperConnection implements IConnectionManager {
    /* access modifiers changed from: private */
    public SocketActionDispatcher actionDispatcher;
    /* access modifiers changed from: private */
    public CallbackResponseDispatcher callbackResponseDispatcher;
    /* access modifiers changed from: private */
    public ExecutorService connExecutor;
    private Runnable connTask = new Runnable() {
        public void run() {
            try {
                SuperConnection.this.openConnection();
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.m46d("---> socket连接失败");
                SuperConnection.this.connectionStatus.set(0);
                SuperConnection.this.actionDispatcher.dispatchAction(SocketAction.ACTION_CONN_FAIL, new Boolean(true));
            }
        }
    };
    protected final AtomicInteger connectionStatus = new AtomicInteger(0);
    private IConnectionSwitchListener connectionSwitchListener;
    private HeartManager heartManager;
    /* access modifiers changed from: private */
    public IOManager ioManager;
    private AbsReconnection reconnection;
    protected SocketAddress socketAddress;
    protected EasySocketOptions socketOptions;

    /* access modifiers changed from: protected */
    public abstract void closeConnection() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void openConnection() throws Exception;

    public SuperConnection(SocketAddress socketAddress2) {
        this.socketAddress = socketAddress2;
        this.actionDispatcher = new SocketActionDispatcher(this, socketAddress2);
    }

    public void subscribeSocketAction(ISocketActionListener iSocketActionListener) {
        this.actionDispatcher.subscribe(iSocketActionListener);
    }

    public void unSubscribeSocketAction(ISocketActionListener iSocketActionListener) {
        this.actionDispatcher.unsubscribe(iSocketActionListener);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0039, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.easysocket.interfaces.conn.IConnectionManager setOptions(com.easysocket.config.EasySocketOptions r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 != 0) goto L_0x0005
            monitor-exit(r2)
            return r2
        L_0x0005:
            r2.socketOptions = r3     // Catch:{ all -> 0x003a }
            com.easysocket.connection.iowork.IOManager r0 = r2.ioManager     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x000e
            r0.setOptions(r3)     // Catch:{ all -> 0x003a }
        L_0x000e:
            com.easysocket.connection.heartbeat.HeartManager r0 = r2.heartManager     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0015
            r0.setOptions(r3)     // Catch:{ all -> 0x003a }
        L_0x0015:
            com.easysocket.connection.dispatcher.CallbackResponseDispatcher r0 = r2.callbackResponseDispatcher     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x001c
            r0.setSocketOptions(r3)     // Catch:{ all -> 0x003a }
        L_0x001c:
            com.easysocket.connection.reconnect.AbsReconnection r0 = r2.reconnection     // Catch:{ all -> 0x003a }
            if (r0 == 0) goto L_0x0038
            com.easysocket.connection.reconnect.AbsReconnection r1 = r3.getReconnectionManager()     // Catch:{ all -> 0x003a }
            boolean r0 = r0.equals(r1)     // Catch:{ all -> 0x003a }
            if (r0 != 0) goto L_0x0038
            com.easysocket.connection.reconnect.AbsReconnection r0 = r2.reconnection     // Catch:{ all -> 0x003a }
            r0.detach()     // Catch:{ all -> 0x003a }
            com.easysocket.connection.reconnect.AbsReconnection r3 = r3.getReconnectionManager()     // Catch:{ all -> 0x003a }
            r2.reconnection = r3     // Catch:{ all -> 0x003a }
            r3.attach(r2)     // Catch:{ all -> 0x003a }
        L_0x0038:
            monitor-exit(r2)
            return r2
        L_0x003a:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.easysocket.connection.connect.SuperConnection.setOptions(com.easysocket.config.EasySocketOptions):com.easysocket.interfaces.conn.IConnectionManager");
    }

    public EasySocketOptions getOptions() {
        return this.socketOptions;
    }

    public synchronized void connect() {
        LogUtil.m46d("---> socket开始连接");
        if (this.socketAddress.getIp() != null) {
            this.connectionStatus.set(1);
            if (this.heartManager == null) {
                this.heartManager = new HeartManager(this, this.actionDispatcher);
            }
            AbsReconnection absReconnection = this.reconnection;
            if (absReconnection != null) {
                absReconnection.detach();
            }
            AbsReconnection reconnectionManager = this.socketOptions.getReconnectionManager();
            this.reconnection = reconnectionManager;
            if (reconnectionManager != null) {
                reconnectionManager.attach(this);
            }
            SocketActionDispatcher socketActionDispatcher = this.actionDispatcher;
            if (socketActionDispatcher != null) {
                socketActionDispatcher.startDispatchThread();
            }
            ExecutorService executorService = this.connExecutor;
            if (executorService == null || executorService.isShutdown()) {
                this.connExecutor = Executors.newCachedThreadPool();
            }
            this.connExecutor.execute(this.connTask);
        } else {
            throw new NotNullException("请检查是否设置了IP地址");
        }
    }

    public synchronized void disconnect(boolean z) {
        if (this.connectionStatus.get() != 0) {
            if (z) {
                if (this.reconnection.isReconning()) {
                    return;
                }
            }
            this.connectionStatus.set(3);
            DisconnectThread disconnectThread = new DisconnectThread(z, "disconn thread：" + (this.socketAddress.getIp() + " : " + this.socketAddress.getPort()));
            disconnectThread.setDaemon(true);
            disconnectThread.start();
        }
    }

    private class DisconnectThread extends Thread {
        boolean isNeedReconnect;

        public DisconnectThread(boolean z, String str) {
            super(str);
            this.isNeedReconnect = z;
        }

        public void run() {
            try {
                if (SuperConnection.this.ioManager != null) {
                    SuperConnection.this.ioManager.closeIO();
                }
                if (SuperConnection.this.callbackResponseDispatcher != null) {
                    SuperConnection.this.callbackResponseDispatcher.shutdownThread();
                }
                if (SuperConnection.this.connExecutor != null && !SuperConnection.this.connExecutor.isShutdown()) {
                    SuperConnection.this.connExecutor.shutdown();
                    ExecutorService unused = SuperConnection.this.connExecutor = null;
                }
                SuperConnection.this.closeConnection();
                LogUtil.m46d("---> 关闭socket连接");
                SuperConnection.this.connectionStatus.set(0);
                SuperConnection.this.actionDispatcher.dispatchAction(SocketAction.ACTION_DISCONNECTION, new Boolean(this.isNeedReconnect));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onConnectionOpened() {
        LogUtil.m46d("---> socket连接成功");
        this.actionDispatcher.dispatchAction(SocketAction.ACTION_CONN_SUCCESS);
        this.connectionStatus.set(2);
        openSocketManager();
    }

    private void openSocketManager() {
        if (this.callbackResponseDispatcher == null) {
            this.callbackResponseDispatcher = new CallbackResponseDispatcher(this);
        }
        if (this.ioManager == null) {
            this.ioManager = new IOManager(this, this.actionDispatcher);
        }
        this.ioManager.startIO();
        this.callbackResponseDispatcher.engineThread();
        this.ioManager.startIO();
    }

    public synchronized void switchHost(SocketAddress socketAddress2) {
        if (socketAddress2 != null) {
            SocketAddress socketAddress3 = this.socketAddress;
            this.socketAddress = socketAddress2;
            SocketActionDispatcher socketActionDispatcher = this.actionDispatcher;
            if (socketActionDispatcher != null) {
                socketActionDispatcher.setSocketAddress(socketAddress2);
            }
            IConnectionSwitchListener iConnectionSwitchListener = this.connectionSwitchListener;
            if (iConnectionSwitchListener != null) {
                iConnectionSwitchListener.onSwitchConnectionInfo(this, socketAddress3, socketAddress2);
            }
        }
    }

    public void setOnConnectionSwitchListener(IConnectionSwitchListener iConnectionSwitchListener) {
        this.connectionSwitchListener = iConnectionSwitchListener;
    }

    public boolean isConnectViable() {
        return Utils.isNetConnected(EasySocket.getInstance().getContext()) && this.connectionStatus.get() == 0;
    }

    public int getConnectionStatus() {
        return this.connectionStatus.get();
    }

    private IConnectionManager sendBytes(byte[] bArr) {
        if (this.ioManager != null && this.connectionStatus.get() == 2) {
            this.ioManager.sendBytes(bArr);
        }
        return this;
    }

    public void onCallBack(SuperCallBack superCallBack) {
        this.callbackResponseDispatcher.addSocketCallback(superCallBack);
    }

    public synchronized IConnectionManager upBytes(byte[] bArr) {
        sendBytes(bArr);
        return this;
    }

    public synchronized IConnectionManager upCallbackMessage(SuperCallbackSender superCallbackSender) {
        this.callbackResponseDispatcher.checkCallbackSender(superCallbackSender);
        sendBytes(superCallbackSender.pack());
        return this;
    }

    public HeartManager getHeartManager() {
        return this.heartManager;
    }
}
