package com.eternal.framework.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class NetworkUtil {
    public static final int NETWORK_2G = 5;
    public static final int NETWORK_3G = 4;
    public static final int NETWORK_4G = 3;
    public static final int NETWORK_ETHERNET = 1;
    public static final int NETWORK_NO = 0;
    public static final int NETWORK_UNKNOWN = -1;
    public static final int NETWORK_WIFI = 2;
    public static int NET_CNNT_BAIDU_OK = 1;
    public static int NET_CNNT_BAIDU_TIMEOUT = 2;
    public static int NET_ERROR = 4;
    public static int NET_NOT_PREPARE = 3;
    private static int TIMEOUT = 3000;

    public static boolean is24GHz(int i) {
        return i > 2400 && i < 2500;
    }

    public static boolean is5GHz(int i) {
        return i > 4900 && i < 5900;
    }

    public static boolean isNetworkAvailable(Context context) {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable() && isAvailableByDns((String) null)) {
            return true;
        }
        return false;
    }

    public static boolean isAvailableByDns(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "www.baidu.com";
        }
        try {
            if (InetAddress.getByName(str) != null) {
                return true;
            }
            return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getLocalIpAddress() {
        String str = "";
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress nextElement = inetAddresses.nextElement();
                    if (!nextElement.isLoopbackAddress()) {
                        str = nextElement.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean getMobileDataEnabled() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
            if (telephonyManager == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                return telephonyManager.isDataEnabled();
            }
            return ((Boolean) telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]).invoke(telephonyManager, new Object[0])).booleanValue();
        } catch (Exception e) {
            Log.e("NetworkUtils", "getMobileDataEnabled: ", e);
            return false;
        }
    }

    public static boolean is3G() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) Utils.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 0;
    }

    public static boolean isWifi() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) Utils.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static boolean is2G(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        if (activeNetworkInfo.getSubtype() == 2 || activeNetworkInfo.getSubtype() == 1 || activeNetworkInfo.getSubtype() == 4) {
            return true;
        }
        return false;
    }

    public static boolean isMobileData() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0;
    }

    private static boolean isEthernet() {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getContext().getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return true;
        }
        return false;
    }

    public static boolean is4G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    public static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getContext().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getContext().getSystemService("wifi");
        if (wifiManager != null && z != wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(z);
        }
    }

    private static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getContext().getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return telephonyManager.getNetworkOperatorName();
    }

    public static int getNetworkType() {
        if (isEthernet()) {
            return 1;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return 0;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 2;
        }
        if (activeNetworkInfo.getType() != 0) {
            return -1;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return 5;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return 4;
            case 13:
            case 18:
                return 3;
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                    return 4;
                }
                return -1;
        }
    }

    public static String getBroadcastIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    if (!nextElement.isLoopback()) {
                        List<InterfaceAddress> interfaceAddresses = nextElement.getInterfaceAddresses();
                        int size = interfaceAddresses.size();
                        for (int i = 0; i < size; i++) {
                            InetAddress broadcast = interfaceAddresses.get(i).getBroadcast();
                            if (broadcast != null) {
                                return broadcast.getHostAddress();
                            }
                        }
                        continue;
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getDomainAddress(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getIpAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getContext().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress);
    }

    public static String getGatewayByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getContext().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
    }

    public static String getNetMaskByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getContext().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().netmask);
    }

    public static String getServerAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getContext().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().serverAddress);
    }
}
