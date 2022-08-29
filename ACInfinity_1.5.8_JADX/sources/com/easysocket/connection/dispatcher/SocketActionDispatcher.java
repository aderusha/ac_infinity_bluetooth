package com.easysocket.connection.dispatcher;

import com.easysocket.EasySocket;
import com.easysocket.connection.action.IOAction;
import com.easysocket.connection.action.SocketAction;
import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.ISocketActionDispatch;
import com.easysocket.interfaces.conn.ISocketActionListener;
import com.easysocket.utils.Utils;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class SocketActionDispatcher implements ISocketActionDispatch {
    /* access modifiers changed from: private */
    public List<ISocketActionListener> actionListeners = new ArrayList();
    private Thread actionThread;
    private IConnectionManager connectionManager;
    /* access modifiers changed from: private */
    public boolean isStop;
    private MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();
    /* access modifiers changed from: private */
    public final LinkedBlockingQueue<ActionBean> socketActions = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */
    public SocketAddress socketAddress;

    public SocketActionDispatcher(IConnectionManager iConnectionManager, SocketAddress socketAddress2) {
        this.socketAddress = socketAddress2;
        this.connectionManager = iConnectionManager;
    }

    public void setSocketAddress(SocketAddress socketAddress2) {
        this.socketAddress = socketAddress2;
    }

    public void dispatchAction(String str) {
        dispatchAction(str, (Serializable) null);
    }

    public void dispatchAction(String str, Serializable serializable) {
        this.socketActions.offer(new ActionBean(str, serializable, this));
    }

    public void subscribe(ISocketActionListener iSocketActionListener) {
        if (iSocketActionListener != null && !this.actionListeners.contains(iSocketActionListener)) {
            this.actionListeners.add(iSocketActionListener);
        }
    }

    public void unsubscribe(ISocketActionListener iSocketActionListener) {
        if (iSocketActionListener != null && this.actionListeners.contains(iSocketActionListener)) {
            this.actionListeners.remove(iSocketActionListener);
        }
    }

    private class DispatchThread extends Thread {
        public DispatchThread() {
            super("dispatch thread");
        }

        public void run() {
            while (!SocketActionDispatcher.this.isStop) {
                try {
                    ActionBean actionBean = (ActionBean) SocketActionDispatcher.this.socketActions.take();
                    if (!(actionBean == null || actionBean.mDispatcher == null)) {
                        SocketActionDispatcher socketActionDispatcher = actionBean.mDispatcher;
                        for (ISocketActionListener access$300 : new ArrayList(socketActionDispatcher.actionListeners)) {
                            socketActionDispatcher.dispatchActionToListener(actionBean.mAction, actionBean.arg, access$300);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected static class ActionBean {
        Serializable arg;
        String mAction = "";
        SocketActionDispatcher mDispatcher;

        public ActionBean(String str, Serializable serializable, SocketActionDispatcher socketActionDispatcher) {
            this.mAction = str;
            this.arg = serializable;
            this.mDispatcher = socketActionDispatcher;
        }
    }

    /* access modifiers changed from: private */
    public void dispatchActionToListener(String str, final Serializable serializable, final ISocketActionListener iSocketActionListener) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1455248519:
                if (str.equals(IOAction.ACTION_READ_COMPLETE)) {
                    c = 0;
                    break;
                }
                break;
            case -1201839197:
                if (str.equals(SocketAction.ACTION_DISCONNECTION)) {
                    c = 1;
                    break;
                }
                break;
            case -588456615:
                if (str.equals(SocketAction.ACTION_CONN_SUCCESS)) {
                    c = 2;
                    break;
                }
                break;
            case 957294984:
                if (str.equals(SocketAction.ACTION_CONN_FAIL)) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mainThreadExecutor.execute(new Runnable() {
                    public void run() {
                        iSocketActionListener.onSocketResponse(SocketActionDispatcher.this.socketAddress, (OriginReadData) serializable);
                        byte[] concatBytes = Utils.concatBytes(((OriginReadData) serializable).getHeaderData(), ((OriginReadData) serializable).getBodyBytes());
                        iSocketActionListener.onSocketResponse(SocketActionDispatcher.this.socketAddress, new String(concatBytes, Charset.forName(EasySocket.getInstance().getDefOptions().getCharsetName())));
                        iSocketActionListener.onSocketResponse(SocketActionDispatcher.this.socketAddress, concatBytes);
                    }
                });
                return;
            case 1:
                this.mainThreadExecutor.execute(new Runnable() {
                    public void run() {
                        iSocketActionListener.onSocketDisconnect(SocketActionDispatcher.this.socketAddress, ((Boolean) serializable).booleanValue());
                        if (!((Boolean) serializable).booleanValue()) {
                            SocketActionDispatcher.this.stopDispatchThread();
                        }
                    }
                });
                return;
            case 2:
                this.mainThreadExecutor.execute(new Runnable() {
                    public void run() {
                        iSocketActionListener.onSocketConnSuccess(SocketActionDispatcher.this.socketAddress);
                    }
                });
                return;
            case 3:
                this.mainThreadExecutor.execute(new Runnable() {
                    public void run() {
                        iSocketActionListener.onSocketConnFail(SocketActionDispatcher.this.socketAddress, ((Boolean) serializable).booleanValue());
                    }
                });
                return;
            default:
                return;
        }
    }

    public void startDispatchThread() {
        this.isStop = false;
        if (this.actionThread == null) {
            DispatchThread dispatchThread = new DispatchThread();
            this.actionThread = dispatchThread;
            dispatchThread.start();
        }
    }

    public void stopDispatchThread() {
        Thread thread = this.actionThread;
        if (thread != null && thread.isAlive() && !this.actionThread.isInterrupted()) {
            this.socketActions.clear();
            this.isStop = true;
            this.actionThread.interrupt();
            this.actionThread = null;
        }
    }
}
