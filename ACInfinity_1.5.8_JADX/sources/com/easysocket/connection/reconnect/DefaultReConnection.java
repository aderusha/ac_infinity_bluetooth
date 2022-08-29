package com.easysocket.connection.reconnect;

import android.os.Handler;
import android.os.HandlerThread;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.utils.LogUtil;

public class DefaultReConnection extends AbsReconnection {
    private static final int MAX_CONNECTION_FAILED_TIMES = 10;
    /* access modifiers changed from: private */
    public final Runnable RcConnTask = new Runnable() {
        public void run() {
            LogUtil.m46d("---> 执行重连");
            if (DefaultReConnection.this.isDetach) {
                DefaultReConnection.this.shutDown();
            } else if (!DefaultReConnection.this.connectionManager.isConnectViable()) {
                LogUtil.m46d("当前条件不允许连接");
                DefaultReConnection.this.handler.postDelayed(DefaultReConnection.this.RcConnTask, (long) (((double) DefaultReConnection.this.reconnectTimeDelay) * (Math.random() + 0.5d)));
            } else {
                DefaultReConnection.this.connectionManager.connect();
            }
        }
    };
    private int connectionFailedTimes = 0;
    /* access modifiers changed from: private */
    public Handler handler;
    private HandlerThread handlerThread;
    /* access modifiers changed from: private */
    public long reconnectTimeDelay = 10000;

    public synchronized void attach(IConnectionManager iConnectionManager) {
        super.attach(iConnectionManager);
        if (this.reconnectTimeDelay < ((long) this.connectionManager.getOptions().getConnectTimeout())) {
            this.reconnectTimeDelay = (long) this.connectionManager.getOptions().getConnectTimeout();
        }
    }

    private void reconnect() {
        if (this.handlerThread == null) {
            HandlerThread handlerThread2 = new HandlerThread("re_conn");
            this.handlerThread = handlerThread2;
            handlerThread2.start();
            this.handler = new Handler(this.handlerThread.getLooper());
        }
        LogUtil.m46d("重连间隔时间-->" + (((double) this.reconnectTimeDelay) * (Math.random() + 0.5d)));
        this.handler.postDelayed(this.RcConnTask, (long) (((double) this.reconnectTimeDelay) * (Math.random() + 0.5d)));
    }

    /* access modifiers changed from: private */
    public void shutDown() {
        HandlerThread handlerThread2 = this.handlerThread;
        if (handlerThread2 != null && handlerThread2.isAlive()) {
            this.handlerThread.quit();
            this.handlerThread = null;
            this.handler = null;
        }
    }

    public boolean equals(Object obj) {
        return obj != null && getClass() == obj.getClass();
    }

    public void onSocketConnSuccess(SocketAddress socketAddress) {
        shutDown();
    }

    public void onSocketConnFail(SocketAddress socketAddress, boolean z) {
        if (!z) {
            shutDown();
            return;
        }
        int i = this.connectionFailedTimes + 1;
        this.connectionFailedTimes = i;
        if (i <= 10 || socketAddress.getBackupAddress() == null) {
            reconnect();
            return;
        }
        this.connectionFailedTimes = 0;
        SocketAddress backupAddress = socketAddress.getBackupAddress();
        backupAddress.setBackupAddress(new SocketAddress(socketAddress.getIp(), socketAddress.getPort()));
        if (this.connectionManager.isConnectViable()) {
            this.connectionManager.switchHost(backupAddress);
            reconnect();
        }
    }

    public void onSocketDisconnect(SocketAddress socketAddress, boolean z) {
        if (!z) {
            shutDown();
        } else {
            reconnect();
        }
    }

    public boolean isReconning() {
        HandlerThread handlerThread2 = this.handlerThread;
        return handlerThread2 != null && handlerThread2.isAlive();
    }
}
