package com.easysocket.interfaces.conn;

import com.easysocket.entity.basemsg.SuperCallbackSender;

public interface ISend {
    IConnectionManager upBytes(byte[] bArr);

    IConnectionManager upCallbackMessage(SuperCallbackSender superCallbackSender);
}
