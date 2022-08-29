package com.easysocket.config;

import com.easysocket.entity.OriginReadData;

public abstract class CallbackIDFactory {
    public abstract String getCallbackID(OriginReadData originReadData);
}
