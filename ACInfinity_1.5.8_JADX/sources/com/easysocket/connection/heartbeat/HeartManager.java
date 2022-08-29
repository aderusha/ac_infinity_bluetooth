package com.easysocket.connection.heartbeat;

import com.easysocket.config.EasySocketOptions;
import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.config.IOptions;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.IHeartManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.conn.SocketActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HeartManager extends SocketActionListener implements IOptions, IHeartManager {
    private final Runnable beatTask = new Runnable() {
        public void run() {
            if (HeartManager.this.socketOptions.getMaxHeartbeatLoseTimes() == -1 || HeartManager.this.loseTimes.incrementAndGet() < HeartManager.this.socketOptions.getMaxHeartbeatLoseTimes()) {
                HeartManager.this.connectionManager.upBytes(HeartManager.this.clientHeart);
                return;
            }
            HeartManager.this.connectionManager.disconnect(true);
            HeartManager.this.resetLoseTimes();
        }
    };
    /* access modifiers changed from: private */
    public byte[] clientHeart;
    /* access modifiers changed from: private */
    public IConnectionManager connectionManager;
    private long freq;
    private ScheduledExecutorService heartExecutor;
    private HeartbeatListener heartbeatListener;
    private boolean isActivate;
    /* access modifiers changed from: private */
    public AtomicInteger loseTimes = new AtomicInteger(-1);
    /* access modifiers changed from: private */
    public EasySocketOptions socketOptions;

    public interface HeartbeatListener {
        boolean isServerHeartbeat(OriginReadData originReadData);
    }

    public HeartManager(IConnectionManager iConnectionManager, ISocketActionDispatch iSocketActionDispatch) {
        this.connectionManager = iConnectionManager;
        this.socketOptions = iConnectionManager.getOptions();
        iSocketActionDispatch.subscribe(this);
    }

    public void startHeartbeat(byte[] bArr, HeartbeatListener heartbeatListener2) {
        this.clientHeart = bArr;
        this.heartbeatListener = heartbeatListener2;
        this.isActivate = true;
        openThread();
    }

    private void openThread() {
        this.freq = this.socketOptions.getHeartbeatFreq();
        ScheduledExecutorService scheduledExecutorService = this.heartExecutor;
        if (scheduledExecutorService == null || scheduledExecutorService.isShutdown()) {
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            this.heartExecutor = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleWithFixedDelay(this.beatTask, 0, this.freq, TimeUnit.MILLISECONDS);
        }
    }

    public void stopHeartbeat() {
        this.isActivate = false;
        closeThread();
    }

    private void closeThread() {
        ScheduledExecutorService scheduledExecutorService = this.heartExecutor;
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            this.heartExecutor.shutdownNow();
            this.heartExecutor = null;
            resetLoseTimes();
        }
    }

    public void onReceiveHeartBeat() {
        resetLoseTimes();
    }

    /* access modifiers changed from: private */
    public void resetLoseTimes() {
        this.loseTimes.set(-1);
    }

    public void onSocketConnSuccess(SocketAddress socketAddress) {
        if (this.isActivate) {
            openThread();
        }
    }

    public void onSocketConnFail(SocketAddress socketAddress, boolean z) {
        if (!z) {
            closeThread();
        }
    }

    public void onSocketDisconnect(SocketAddress socketAddress, boolean z) {
        if (!z) {
            closeThread();
        }
    }

    public void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData) {
        HeartbeatListener heartbeatListener2 = this.heartbeatListener;
        if (heartbeatListener2 != null && heartbeatListener2.isServerHeartbeat(originReadData)) {
            onReceiveHeartBeat();
        }
    }

    public Object setOptions(EasySocketOptions easySocketOptions) {
        this.socketOptions = easySocketOptions;
        long heartbeatFreq = easySocketOptions.getHeartbeatFreq();
        this.freq = heartbeatFreq;
        if (heartbeatFreq < 1000) {
            heartbeatFreq = 1000;
        }
        this.freq = heartbeatFreq;
        return this;
    }

    public EasySocketOptions getOptions() {
        return this.socketOptions;
    }
}
