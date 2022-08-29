package com.easysocket.connection.reconnect;

import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.interfaces.conn.IConnectionManager;
import com.easysocket.interfaces.conn.IReconnListener;
import com.easysocket.interfaces.conn.SocketActionListener;

public abstract class AbsReconnection extends SocketActionListener implements IReconnListener {
    protected IConnectionManager connectionManager;
    protected boolean isDetach;

    public abstract boolean isReconning();

    public void onSocketResponse(SocketAddress socketAddress, OriginReadData originReadData) {
    }

    public synchronized void attach(IConnectionManager iConnectionManager) {
        if (!this.isDetach) {
            detach();
        }
        this.isDetach = false;
        this.connectionManager = iConnectionManager;
        iConnectionManager.subscribeSocketAction(this);
    }

    public synchronized void detach() {
        this.isDetach = true;
        IConnectionManager iConnectionManager = this.connectionManager;
        if (iConnectionManager != null) {
            iConnectionManager.unSubscribeSocketAction(this);
        }
    }
}
