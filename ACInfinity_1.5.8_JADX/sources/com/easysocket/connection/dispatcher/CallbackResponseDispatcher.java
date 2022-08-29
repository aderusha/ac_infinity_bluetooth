package com.easysocket.connection.dispatcher;

import com.easysocket.callback.SuperCallBack;
import com.easysocket.config.EasySocketOptions;
import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.entity.basemsg.SuperCallbackSender;
import com.easysocket.exception.RequestTimeOutException;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.SocketActionListener;
import com.easysocket.utils.LogUtil;
import com.easysocket.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CallbackResponseDispatcher {
    /* access modifiers changed from: private */
    public Map<String, SuperCallBack> callbacks = new HashMap();
    IConnectionManager connectionManager;
    private SocketActionListener socketActionListener = new SocketActionListener() {
        public void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData) {
            String callbackID;
            SuperCallBack superCallBack;
            if (CallbackResponseDispatcher.this.callbacks.size() != 0 && CallbackResponseDispatcher.this.socketOptions.getCallbackIDFactory() != null && (callbackID = CallbackResponseDispatcher.this.socketOptions.getCallbackIDFactory().getCallbackID(originReadData)) != null && (superCallBack = (SuperCallBack) CallbackResponseDispatcher.this.callbacks.get(callbackID)) != null) {
                superCallBack.onSuccess(originReadData);
                CallbackResponseDispatcher.this.callbacks.remove(callbackID);
                LogUtil.m46d("移除的callbackId-->" + callbackID);
            }
        }
    };
    /* access modifiers changed from: private */
    public EasySocketOptions socketOptions;
    /* access modifiers changed from: private */
    public ExecutorService timeoutExecutor;
    /* access modifiers changed from: private */
    public DelayQueue<timeoutItem> timeoutQueue = new DelayQueue<>();

    public CallbackResponseDispatcher(IConnectionManager iConnectionManager) {
        this.connectionManager = iConnectionManager;
        this.socketOptions = iConnectionManager.getOptions();
        iConnectionManager.subscribeSocketAction(this.socketActionListener);
        engineThread();
    }

    public void setSocketOptions(EasySocketOptions easySocketOptions) {
        this.socketOptions = easySocketOptions;
    }

    public void engineThread() {
        ExecutorService executorService = this.timeoutExecutor;
        if (executorService == null || executorService.isShutdown()) {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            this.timeoutExecutor = newSingleThreadExecutor;
            newSingleThreadExecutor.execute(new Runnable() {
                public void run() {
                    SuperCallBack superCallBack;
                    try {
                        timeoutItem timeoutitem = (timeoutItem) CallbackResponseDispatcher.this.timeoutQueue.take();
                        if (!(timeoutitem == null || (superCallBack = (SuperCallBack) CallbackResponseDispatcher.this.callbacks.remove(timeoutitem.callbackId)) == null)) {
                            superCallBack.onError(new RequestTimeOutException("request timeout"));
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (CallbackResponseDispatcher.this.timeoutExecutor != null && !CallbackResponseDispatcher.this.timeoutExecutor.isShutdown()) {
                        run();
                    }
                }
            });
        }
    }

    public void shutdownThread() {
        ExecutorService executorService = this.timeoutExecutor;
        if (executorService != null && !executorService.isShutdown()) {
            this.timeoutExecutor.shutdownNow();
            this.timeoutExecutor = null;
        }
    }

    public void addSocketCallback(SuperCallBack superCallBack) {
        this.callbacks.put(superCallBack.getCallbackId(), superCallBack);
        EasySocketOptions easySocketOptions = this.socketOptions;
        if (easySocketOptions == null) {
            easySocketOptions = EasySocketOptions.getDefaultOptions();
        }
        this.timeoutQueue.add(new timeoutItem(superCallBack.getCallbackId(), easySocketOptions.getRequestTimeout(), TimeUnit.MILLISECONDS));
    }

    class timeoutItem implements Delayed {
        String callbackId;
        long executeTime;

        public timeoutItem(String str, long j, TimeUnit timeUnit) {
            this.callbackId = str;
            this.executeTime = System.currentTimeMillis() + (j > 0 ? timeUnit.toMillis(j) : 0);
        }

        public long getDelay(TimeUnit timeUnit) {
            return this.executeTime - System.currentTimeMillis();
        }

        public int compareTo(Delayed delayed) {
            return (int) (getDelay(TimeUnit.MILLISECONDS) - delayed.getDelay(TimeUnit.MILLISECONDS));
        }
    }

    public void checkCallbackSender(SuperCallbackSender superCallbackSender) {
        Utils.checkNotNull(this.socketOptions.getCallbackIDFactory(), "要想实现EasySocket的回调功能，CallbackIdFactory不能为null，请实现一个CallbackIdFactory并在初始化的时候通过EasySocketOptions的setCallbackIdFactory进行配置");
        if (this.callbacks.containsKey(superCallbackSender.getCallbackId())) {
            superCallbackSender.generateCallbackId();
        }
    }
}
