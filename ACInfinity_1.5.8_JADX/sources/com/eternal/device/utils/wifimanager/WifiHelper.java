package com.eternal.device.utils.wifimanager;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WifiHelper {
    public static final String EAP = "EAP";
    public static final String PSK = "PSK";
    public static final String WEP = "WEP";
    public static final String WPA = "WPA";

    public static int configOrCreateWifi(WifiManager wifiManager, String str, String str2, String str3) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks != null) {
            for (WifiConfiguration next : configuredNetworks) {
                if (next.SSID.equals(str)) {
                    return next.networkId;
                }
            }
        }
        return saveWifiConfiguration(wifiManager, createWifiConfiguration(str, str3, str2));
    }

    public static boolean deleteWifiConfiguration(WifiManager wifiManager, IWifi iWifi) {
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null) {
            return false;
        }
        for (WifiConfiguration next : configuredNetworks) {
            if (next.SSID.equals(iWifi.SSID())) {
                return wifiManager.saveConfiguration() & wifiManager.removeNetwork(next.networkId);
            }
        }
        return false;
    }

    public static boolean deleteWifiConfiguration(WifiManager wifiManager, int i) {
        return wifiManager.saveConfiguration() & wifiManager.removeNetwork(i);
    }

    private static WifiConfiguration createWifiConfiguration(String str, String str2, String str3) {
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        if (str2 == null) {
            wifiConfiguration.hiddenSSID = false;
            wifiConfiguration.status = 2;
            wifiConfiguration.SSID = str;
            if (str3.contains(WEP)) {
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                wifiConfiguration.wepTxKeyIndex = 0;
                wifiConfiguration.wepKeys[0] = "";
            } else if (str3.contains(PSK)) {
                wifiConfiguration.preSharedKey = "";
            } else if (str3.contains(EAP)) {
                wifiConfiguration.allowedKeyManagement.set(2);
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedProtocols.set(0);
                wifiConfiguration.preSharedKey = "";
            } else {
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.preSharedKey = null;
            }
        } else {
            wifiConfiguration.allowedAuthAlgorithms.clear();
            wifiConfiguration.allowedGroupCiphers.clear();
            wifiConfiguration.allowedKeyManagement.clear();
            wifiConfiguration.allowedPairwiseCiphers.clear();
            wifiConfiguration.allowedProtocols.clear();
            wifiConfiguration.SSID = "\"" + str + "\"";
            if (str3.contains(WEP)) {
                wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
                wifiConfiguration.hiddenSSID = false;
                wifiConfiguration.allowedAuthAlgorithms.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedGroupCiphers.set(0);
                wifiConfiguration.allowedGroupCiphers.set(1);
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.wepTxKeyIndex = 0;
            } else if (str3.contains(WPA)) {
                wifiConfiguration.hiddenSSID = false;
                wifiConfiguration.preSharedKey = "\"" + str2 + "\"";
                wifiConfiguration.allowedAuthAlgorithms.set(0);
                wifiConfiguration.allowedGroupCiphers.set(2);
                wifiConfiguration.allowedKeyManagement.set(1);
                wifiConfiguration.allowedPairwiseCiphers.set(1);
                wifiConfiguration.allowedGroupCiphers.set(3);
                wifiConfiguration.allowedPairwiseCiphers.set(2);
            } else {
                wifiConfiguration.wepKeys[0] = "";
                wifiConfiguration.allowedKeyManagement.set(0);
                wifiConfiguration.wepTxKeyIndex = 0;
            }
        }
        return wifiConfiguration;
    }

    private static int saveWifiConfiguration(WifiManager wifiManager, WifiConfiguration wifiConfiguration) {
        return wifiManager.addNetwork(wifiConfiguration);
    }

    public static List<IWifi> removeDuplicate(List<IWifi> list) {
        Collections.sort(list, new Comparator<IWifi>() {
            public int compare(IWifi iWifi, IWifi iWifi2) {
                return iWifi2.level() - iWifi.level();
            }
        });
        ArrayList arrayList = new ArrayList();
        for (IWifi next : list) {
            if (!arrayList.contains(next)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }
}
