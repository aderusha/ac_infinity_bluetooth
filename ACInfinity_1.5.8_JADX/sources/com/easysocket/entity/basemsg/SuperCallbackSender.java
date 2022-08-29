package com.easysocket.entity.basemsg;

import com.easysocket.utils.Utils;

public abstract class SuperCallbackSender extends SuperSender {
    private String callbackId;

    public abstract byte[] pack();

    public SuperCallbackSender() {
        generateCallbackId();
    }

    public String getCallbackId() {
        return this.callbackId;
    }

    public void generateCallbackId() {
        this.callbackId = Utils.getRandomChar(20);
    }
}
