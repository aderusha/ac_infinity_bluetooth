package com.eternal.base.data.ble;

public interface Message {
    void addRetryCount();

    byte[] getPacket();

    int getRetryCount();

    Object getSingle();

    boolean isConfigNet();

    boolean isDisposed();

    void onErr(Throwable th);

    void onNext(byte[] bArr);
}
