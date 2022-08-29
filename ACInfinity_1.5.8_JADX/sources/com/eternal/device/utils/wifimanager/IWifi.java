package com.eternal.device.utils.wifimanager;

public interface IWifi {
    @Deprecated
    String SSID();

    @Deprecated
    String capabilities();

    String description();

    String description2();

    String encryption();

    int frequency();

    /* renamed from: ip */
    String mo14931ip();

    boolean is5G();

    boolean isConnected();

    boolean isEncrypt();

    boolean isSaved();

    int level();

    @Deprecated
    IWifi merge(IWifi iWifi);

    String name();

    String state();

    void state(String str);
}
