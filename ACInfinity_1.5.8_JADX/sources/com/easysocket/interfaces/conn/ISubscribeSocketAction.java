package com.easysocket.interfaces.conn;

public interface ISubscribeSocketAction {
    void subscribeSocketAction(ISocketActionListener iSocketActionListener);

    void unSubscribeSocketAction(ISocketActionListener iSocketActionListener);
}
