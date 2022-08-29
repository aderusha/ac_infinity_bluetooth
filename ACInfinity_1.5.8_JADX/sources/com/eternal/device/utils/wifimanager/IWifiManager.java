package com.eternal.device.utils.wifimanager;

import android.net.wifi.WifiInfo;
import java.util.List;

public interface IWifiManager {
    void closeWifi();

    int connectEncryptWifi(String str, String str2, String str3);

    int connectOpenWifi(IWifi iWifi);

    boolean connectSavedWifi(IWifi iWifi);

    void destroy();

    boolean disConnectWifi();

    WifiInfo getConnectedInfo();

    String getConnectedSSID();

    List<IWifi> getWifi();

    boolean isOpened();

    void openWifi();

    boolean removeWifi(int i);

    boolean removeWifi(IWifi iWifi);

    void scanWifi();

    void setOnWifiChangeListener(OnWifiChangeListener onWifiChangeListener);

    void setOnWifiConnectListener(OnWifiConnectListener onWifiConnectListener);

    void setOnWifiStateChangeListener(OnWifiStateChangeListener onWifiStateChangeListener);
}
