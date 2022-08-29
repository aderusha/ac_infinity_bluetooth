package com.easysocket.entity.basemsg;

public abstract class SuperCallbackResponse implements IResponse {
    public abstract String getCallbackId();

    public abstract void setCallbackId(String str);
}
