package com.eternal.base.data.ble;

import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;

public class NoneAckMessage implements Message, SingleOnSubscribe<byte[]> {
    private SingleEmitter<byte[]> emitter;
    private byte[] packet;
    private int retryCount;
    private Single<byte[]> single = Single.create(this);

    public boolean isConfigNet() {
        return true;
    }

    NoneAckMessage(byte[] bArr) {
        this.packet = bArr;
    }

    public void onNext(byte[] bArr) {
        if (!isDisposed()) {
            this.emitter.onSuccess(bArr);
        }
    }

    public void onErr(Throwable th) {
        if (!isDisposed()) {
            this.emitter.onError(th);
        }
    }

    public boolean isDisposed() {
        SingleEmitter<byte[]> singleEmitter = this.emitter;
        return singleEmitter == null || singleEmitter.isDisposed();
    }

    public byte[] getPacket() {
        return this.packet;
    }

    public Single<byte[]> getSingle() {
        return this.single;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void addRetryCount() {
        this.retryCount++;
    }

    public void subscribe(SingleEmitter<byte[]> singleEmitter) throws Exception {
        this.emitter = singleEmitter;
    }
}
