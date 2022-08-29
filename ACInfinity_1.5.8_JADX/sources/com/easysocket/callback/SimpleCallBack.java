package com.easysocket.callback;

public abstract class SimpleCallBack extends SuperCallBack {
    public void onCompleted() {
    }

    public void onError(Exception exc) {
    }

    public void onStart() {
    }

    public SimpleCallBack(String str) {
        super(str);
    }
}
