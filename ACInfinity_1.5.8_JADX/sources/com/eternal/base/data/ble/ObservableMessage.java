package com.eternal.base.data.ble;

import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;

public class ObservableMessage implements Message, ObservableOnSubscribe<byte[]> {
    private ObservableEmitter<byte[]> emitter;
    private Observable<byte[]> observable = Observable.create(this);
    private byte[] packet;
    private int retryCount;

    public boolean isConfigNet() {
        return false;
    }

    ObservableMessage(byte[] bArr) {
        this.packet = bArr;
    }

    public void onNext(byte[] bArr) {
        if (!isDisposed()) {
            this.emitter.onNext(bArr);
        }
    }

    public void onErr(Throwable th) {
        if (!isDisposed()) {
            this.emitter.onError(th);
        }
    }

    public void onComplete() {
        if (!isDisposed()) {
            this.emitter.onComplete();
        }
    }

    public boolean isDisposed() {
        ObservableEmitter<byte[]> observableEmitter = this.emitter;
        return observableEmitter == null || observableEmitter.isDisposed();
    }

    public byte[] getPacket() {
        return this.packet;
    }

    public Observable<byte[]> getSingle() {
        return this.observable;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void addRetryCount() {
        this.retryCount++;
    }

    public void subscribe(ObservableEmitter<byte[]> observableEmitter) {
        this.emitter = observableEmitter;
    }

    public Observable<byte[]> reset() {
        Observable<byte[]> create = Observable.create(this);
        this.observable = create;
        return create;
    }
}
