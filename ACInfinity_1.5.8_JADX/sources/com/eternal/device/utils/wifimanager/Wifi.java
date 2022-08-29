package com.eternal.device.utils.wifimanager;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class Wifi implements IWifi {
    protected String SSID;
    protected String capabilities;
    protected String description;
    protected String encryption;
    protected int frequency;

    /* renamed from: ip */
    protected String f174ip;
    protected boolean is5G;
    protected boolean isConnected;
    protected boolean isEncrypt;
    protected boolean isSaved;
    protected int level;
    protected String name;
    protected String state;

    public static IWifi create(ScanResult scanResult, List<WifiConfiguration> list, String str, int i) {
        if (TextUtils.isEmpty(scanResult.SSID)) {
            return null;
        }
        Wifi wifi = new Wifi();
        wifi.isConnected = false;
        wifi.isSaved = false;
        wifi.name = scanResult.SSID;
        String str2 = "\"" + scanResult.SSID + "\"";
        wifi.SSID = str2;
        wifi.isConnected = str2.equals(str);
        wifi.capabilities = scanResult.capabilities;
        wifi.isEncrypt = true;
        String str3 = "";
        wifi.encryption = str3;
        wifi.level = scanResult.level;
        wifi.frequency = scanResult.frequency;
        if (wifi.isConnected) {
            str3 = String.format("%d.%d.%d.%d", new Object[]{Integer.valueOf(i & 255), Integer.valueOf((i >> 8) & 255), Integer.valueOf((i >> 16) & 255), Integer.valueOf((i >> 24) & 255)});
        }
        wifi.f174ip = str3;
        if (wifi.capabilities.toUpperCase().contains("WPA2-PSK") && wifi.capabilities.toUpperCase().contains("WPA-PSK")) {
            wifi.encryption = "WPA/WPA2";
        } else if (wifi.capabilities.toUpperCase().contains("WPA-PSK")) {
            wifi.encryption = WifiHelper.WPA;
        } else if (wifi.capabilities.toUpperCase().contains("WPA2-PSK")) {
            wifi.encryption = "WPA2";
        } else {
            wifi.isEncrypt = false;
        }
        wifi.description = wifi.encryption;
        Iterator<WifiConfiguration> it = list.iterator();
        while (true) {
            if (it.hasNext()) {
                if (it.next().SSID.equals(wifi.SSID)) {
                    wifi.isSaved = true;
                    break;
                }
            } else {
                break;
            }
        }
        if (wifi.isSaved) {
            wifi.description = "已保存";
        }
        if (wifi.isConnected) {
            wifi.description = "已连接";
        }
        return wifi;
    }

    public String name() {
        return this.name;
    }

    public boolean isEncrypt() {
        return this.isEncrypt;
    }

    public boolean isSaved() {
        return this.isSaved;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public String encryption() {
        return this.encryption;
    }

    public int level() {
        return this.level;
    }

    public int frequency() {
        return this.frequency;
    }

    public boolean is5G() {
        return this.is5G;
    }

    public String description() {
        String str = this.state;
        return str == null ? this.description : str;
    }

    /* renamed from: ip */
    public String mo14931ip() {
        return this.f174ip;
    }

    public String description2() {
        if (!this.isConnected) {
            return description();
        }
        return String.format("%s(%s)", new Object[]{description(), this.f174ip});
    }

    public void state(String str) {
        this.state = str;
    }

    public String SSID() {
        return this.SSID;
    }

    public String capabilities() {
        return this.capabilities;
    }

    public IWifi merge(IWifi iWifi) {
        this.isSaved = iWifi.isSaved();
        this.isConnected = iWifi.isConnected();
        this.f174ip = iWifi.mo14931ip();
        this.state = iWifi.state();
        this.level = iWifi.level();
        this.description = ((Wifi) iWifi).description;
        return this;
    }

    public String state() {
        return this.state;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Wifi)) {
            return false;
        }
        return ((Wifi) obj).SSID.equals(this.SSID);
    }

    public String toString() {
        return "{\"name\":'" + this.name + "', \"SSID\":'" + this.SSID + "', \"isEncrypt\":" + this.isEncrypt + ", \"isSaved\":" + this.isSaved + ", \"isConnected\":" + this.isConnected + ", \"encryption\":'" + this.encryption + "', \"description\":'" + this.description + "', \"capabilities\":'" + this.capabilities + "', \"ip\":'" + this.f174ip + "', \"state\":'" + this.state + "', \"level\":" + this.level + '}';
    }
}
