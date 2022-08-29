package com.easysocket.interfaces.conn;

import java.io.Serializable;

public interface ISocketActionDispatch {
    void dispatchAction(String str);

    void dispatchAction(String str, Serializable serializable);

    void startDispatchThread();

    void stopDispatchThread();

    void subscribe(ISocketActionListener iSocketActionListener);

    void unsubscribe(ISocketActionListener iSocketActionListener);
}
