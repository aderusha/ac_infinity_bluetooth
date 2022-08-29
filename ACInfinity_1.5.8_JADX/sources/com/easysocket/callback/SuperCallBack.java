package com.easysocket.callback;

import com.easysocket.entity.OriginReadData;

public abstract class SuperCallBack {
    private String callbackId;

    public abstract void onCompleted();

    public abstract void onError(Exception exc);

    public abstract void onResponse(OriginReadData originReadData);

    public abstract void onStart();

    public SuperCallBack(String str) {
        this.callbackId = str;
    }

    public String getCallbackId() {
        return this.callbackId;
    }

    public void onSuccess(OriginReadData originReadData) {
        onCompleted();
        onResponse(originReadData);
    }
}
