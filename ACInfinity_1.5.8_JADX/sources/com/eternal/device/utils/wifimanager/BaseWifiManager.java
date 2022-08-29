package com.eternal.device.utils.wifimanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.eternal.framework.utils.NetworkUtil;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseWifiManager implements IWifiManager {
    static final int WIFI_STATE_CONNECTED = 7;
    static final int WIFI_STATE_DISABLED = 1;
    static final int WIFI_STATE_DISABLING = 2;
    static final int WIFI_STATE_ENABLED = 4;
    static final int WIFI_STATE_ENABLING = 3;
    static final int WIFI_STATE_MODIFY = 6;
    static final int WIFI_STATE_UNCONNECTED = 8;
    static final int WIFI_STATE_UNKNOWN = 5;
    Context context;
    Handler handler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (BaseWifiManager.this.onWifiStateChangeListener != null) {
                        BaseWifiManager.this.onWifiStateChangeListener.onStateChanged(State.DISABLED);
                        return;
                    }
                    return;
                case 2:
                    if (BaseWifiManager.this.onWifiStateChangeListener != null) {
                        BaseWifiManager.this.onWifiStateChangeListener.onStateChanged(State.DISABLING);
                        return;
                    }
                    return;
                case 3:
                    if (BaseWifiManager.this.onWifiStateChangeListener != null) {
                        BaseWifiManager.this.onWifiStateChangeListener.onStateChanged(State.ENABLING);
                        return;
                    }
                    return;
                case 4:
                    if (BaseWifiManager.this.onWifiStateChangeListener != null) {
                        BaseWifiManager.this.onWifiStateChangeListener.onStateChanged(State.ENABLED);
                        return;
                    }
                    return;
                case 5:
                    if (BaseWifiManager.this.onWifiStateChangeListener != null) {
                        BaseWifiManager.this.onWifiStateChangeListener.onStateChanged(State.UNKNOWN);
                        return;
                    }
                    return;
                case 6:
                    if (BaseWifiManager.this.onWifiChangeListener != null) {
                        BaseWifiManager.this.onWifiChangeListener.onWifiChanged(BaseWifiManager.this.wifis);
                        return;
                    }
                    return;
                case 7:
                    if (BaseWifiManager.this.onWifiConnectListener != null) {
                        BaseWifiManager.this.onWifiConnectListener.onConnectChanged(true);
                        return;
                    }
                    return;
                case 8:
                    if (BaseWifiManager.this.onWifiConnectListener != null) {
                        BaseWifiManager.this.onWifiConnectListener.onConnectChanged(false);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    WifiManager manager;
    OnWifiChangeListener onWifiChangeListener;
    OnWifiConnectListener onWifiConnectListener;
    OnWifiStateChangeListener onWifiStateChangeListener;
    WifiReceiver wifiReceiver;
    List<IWifi> wifis;

    BaseWifiManager(Context context2) {
        this.context = context2;
        this.manager = (WifiManager) context2.getApplicationContext().getSystemService("wifi");
        this.wifis = new ArrayList();
        this.wifiReceiver = new WifiReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        context2.registerReceiver(this.wifiReceiver, intentFilter);
    }

    public void destroy() {
        this.context.unregisterReceiver(this.wifiReceiver);
        this.handler.removeCallbacksAndMessages((Object) null);
        this.manager = null;
        this.wifis = null;
        this.context = null;
    }

    public void setOnWifiChangeListener(OnWifiChangeListener onWifiChangeListener2) {
        this.onWifiChangeListener = onWifiChangeListener2;
    }

    public void setOnWifiConnectListener(OnWifiConnectListener onWifiConnectListener2) {
        this.onWifiConnectListener = onWifiConnectListener2;
    }

    public void setOnWifiStateChangeListener(OnWifiStateChangeListener onWifiStateChangeListener2) {
        this.onWifiStateChangeListener = onWifiStateChangeListener2;
    }

    public class WifiReceiver extends BroadcastReceiver {
        public WifiReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            NetworkInfo networkInfo;
            NetworkInfo.DetailedState detailedState;
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action)) {
                int i = 0;
                if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                    int intExtra = intent.getIntExtra("wifi_state", 4);
                    if (intExtra == 0) {
                        i = 2;
                    } else if (intExtra == 1) {
                        i = 1;
                    } else if (intExtra == 2) {
                        i = 3;
                    } else if (intExtra == 3) {
                        BaseWifiManager.this.scanWifi();
                        i = 4;
                    } else if (intExtra == 4) {
                        i = 5;
                    }
                    BaseWifiManager.this.handler.sendEmptyMessage(i);
                } else if (action.equals("android.net.wifi.SCAN_RESULTS")) {
                    if (Build.VERSION.SDK_INT < 23) {
                        BaseWifiManager.this.modifyWifi();
                    } else if (intent.getBooleanExtra("resultsUpdated", false)) {
                        BaseWifiManager.this.modifyWifi();
                    }
                } else if (action.equals("android.net.wifi.STATE_CHANGE")) {
                    NetworkInfo networkInfo2 = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                    if (networkInfo2 != null && (detailedState = networkInfo2.getDetailedState()) != null) {
                        String extraInfo = networkInfo2.getExtraInfo();
                        if (detailedState == NetworkInfo.DetailedState.CONNECTED) {
                            BaseWifiManager.this.modifyWifi();
                            BaseWifiManager.this.handler.sendEmptyMessage(7);
                        } else if (detailedState == NetworkInfo.DetailedState.DISCONNECTED) {
                            BaseWifiManager.this.modifyWifi();
                            BaseWifiManager.this.handler.sendEmptyMessage(8);
                        } else if (!TextUtils.isEmpty(extraInfo) && detailedState != NetworkInfo.DetailedState.IDLE && detailedState != NetworkInfo.DetailedState.SCANNING) {
                            if (detailedState == NetworkInfo.DetailedState.AUTHENTICATING) {
                                BaseWifiManager.this.modifyWifi(extraInfo, "身份验证中...");
                            } else if (detailedState == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
                                BaseWifiManager.this.modifyWifi(extraInfo, "获取地址信息...");
                            } else if (detailedState == NetworkInfo.DetailedState.SUSPENDED) {
                                BaseWifiManager.this.modifyWifi(extraInfo, "连接中断");
                            } else if (detailedState == NetworkInfo.DetailedState.DISCONNECTING) {
                                BaseWifiManager.this.modifyWifi(extraInfo, "断开中...");
                            } else if (detailedState == NetworkInfo.DetailedState.FAILED) {
                                BaseWifiManager.this.modifyWifi(extraInfo, "连接失败");
                            } else if (detailedState == NetworkInfo.DetailedState.BLOCKED) {
                                BaseWifiManager.this.modifyWifi(extraInfo, "wifi无效");
                            } else if (detailedState == NetworkInfo.DetailedState.VERIFYING_POOR_LINK) {
                                BaseWifiManager.this.modifyWifi(extraInfo, "信号差");
                            } else if (detailedState == NetworkInfo.DetailedState.CAPTIVE_PORTAL_CHECK) {
                                BaseWifiManager.this.modifyWifi(extraInfo, "强制登陆门户");
                            }
                        }
                    }
                } else if (action.equals("android.net.wifi.supplicant.STATE_CHANGE") && (networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo")) != null && networkInfo.getDetailedState() != null) {
                    String extraInfo2 = networkInfo.getExtraInfo();
                    if (!TextUtils.isEmpty(extraInfo2)) {
                        if (intent.getIntExtra("supplicantError", -1) == 1) {
                            BaseWifiManager.this.modifyWifi(extraInfo2, "密码错误");
                        } else {
                            BaseWifiManager.this.modifyWifi(extraInfo2, "身份验证出现问题");
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void modifyWifi() {
        synchronized (this.wifis) {
            List<ScanResult> scanResults = this.manager.getScanResults();
            LinkedList linkedList = new LinkedList();
            ArrayList arrayList = new ArrayList();
            List configuredNetworks = this.manager.getConfiguredNetworks();
            if (configuredNetworks == null) {
                configuredNetworks = new ArrayList();
            }
            WifiInfo connectionInfo = this.manager.getConnectionInfo();
            String ssid = connectionInfo.getSSID();
            int ipAddress = connectionInfo.getIpAddress();
            for (ScanResult create : scanResults) {
                IWifi create2 = Wifi.create(create, configuredNetworks, ssid, ipAddress);
                if (create2 != null) {
                    if (NetworkUtil.is24GHz(create2.frequency())) {
                        arrayList.add(create2);
                    }
                }
            }
            for (IWifi next : WifiHelper.removeDuplicate(arrayList)) {
                boolean z = false;
                for (IWifi next2 : this.wifis) {
                    if (next2.equals(next)) {
                        linkedList.add(next2.merge(next));
                        z = true;
                    }
                }
                if (!z) {
                    linkedList.add(next);
                }
            }
            this.wifis.clear();
            this.wifis.addAll(linkedList);
            this.handler.sendEmptyMessage(6);
        }
    }

    /* access modifiers changed from: protected */
    public void modifyWifi(String str, String str2) {
        synchronized (this.wifis) {
            ArrayList arrayList = new ArrayList();
            for (IWifi next : this.wifis) {
                if (str.equals(next.SSID())) {
                    next.state(str2);
                    arrayList.add(0, next);
                } else {
                    next.state((String) null);
                    arrayList.add(next);
                }
            }
            this.wifis.clear();
            this.wifis.addAll(arrayList);
            this.handler.sendEmptyMessage(6);
        }
    }
}
