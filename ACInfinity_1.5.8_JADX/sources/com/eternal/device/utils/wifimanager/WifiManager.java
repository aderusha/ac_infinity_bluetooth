package com.eternal.device.utils.wifimanager;

import android.content.Context;
import android.net.wifi.WifiInfo;
import java.util.List;

public class WifiManager extends BaseWifiManager {
    private WifiManager(Context context) {
        super(context);
    }

    public static IWifiManager create(Context context) {
        return new WifiManager(context);
    }

    public boolean isOpened() {
        return this.manager.isWifiEnabled();
    }

    public void openWifi() {
        if (!this.manager.isWifiEnabled()) {
            this.manager.setWifiEnabled(true);
        }
    }

    public void closeWifi() {
        if (this.manager.isWifiEnabled()) {
            this.manager.setWifiEnabled(false);
        }
    }

    public void scanWifi() {
        this.manager.startScan();
    }

    public boolean disConnectWifi() {
        return this.manager.disconnect();
    }

    public int connectEncryptWifi(String str, String str2, String str3) {
        if (this.manager.getConnectionInfo() != null && str.equals(this.manager.getConnectionInfo().getSSID())) {
            return -1;
        }
        int configOrCreateWifi = WifiHelper.configOrCreateWifi(this.manager, str, str2, str3);
        this.manager.enableNetwork(configOrCreateWifi, true);
        modifyWifi(str, "开始连接...");
        return configOrCreateWifi;
    }

    public boolean connectSavedWifi(IWifi iWifi) {
        boolean enableNetwork = this.manager.enableNetwork(WifiHelper.configOrCreateWifi(this.manager, iWifi.name(), iWifi.capabilities(), (String) null), true);
        modifyWifi(iWifi.SSID(), "开始连接...");
        return enableNetwork;
    }

    public int connectOpenWifi(IWifi iWifi) {
        int connectEncryptWifi = connectEncryptWifi(iWifi.SSID(), iWifi.capabilities(), (String) null);
        modifyWifi(iWifi.SSID(), "开始连接...");
        return connectEncryptWifi;
    }

    public boolean removeWifi(IWifi iWifi) {
        boolean deleteWifiConfiguration = WifiHelper.deleteWifiConfiguration(this.manager, iWifi);
        modifyWifi();
        return deleteWifiConfiguration;
    }

    public boolean removeWifi(int i) {
        this.manager.disableNetwork(i);
        boolean deleteWifiConfiguration = WifiHelper.deleteWifiConfiguration(this.manager, i);
        modifyWifi();
        return deleteWifiConfiguration;
    }

    public List<IWifi> getWifi() {
        return this.wifis;
    }

    public String getConnectedSSID() {
        WifiInfo connectionInfo;
        if (this.manager == null || (connectionInfo = this.manager.getConnectionInfo()) == null) {
            return "";
        }
        String ssid = connectionInfo.getSSID();
        return (ssid.length() > 2 && ssid.charAt(0) == '\"' && ssid.charAt(ssid.length() - 1) == '\"') ? ssid.substring(1, ssid.length() - 1) : "";
    }

    public WifiInfo getConnectedInfo() {
        if (this.manager != null) {
            return this.manager.getConnectionInfo();
        }
        return null;
    }
}
