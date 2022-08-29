package com.eternal.base.data;

import android.text.TextUtils;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.data.ble.IBleControl;
import com.eternal.base.database.entity.Device;
import com.eternal.framework.bus.RxBus;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BaseRepository {
    protected IBleControl control;
    private ConcurrentMap<String, Device> netConnects = new ConcurrentHashMap();

    public BaseRepository(IBleControl iBleControl) {
        this.control = iBleControl;
    }

    public BleStatue getConnect(String str) {
        return this.control.getConnect(str);
    }

    public Device getConnectNet(String str) {
        if (isConnectNet(str)) {
            return (Device) this.netConnects.get(str);
        }
        return null;
    }

    public boolean isConnect(String str) {
        if (this.control.isConnect(str)) {
            return true;
        }
        return isConnectNet(str);
    }

    public void disconnectNet(String str) {
        if (!TextUtils.isEmpty(str) && isConnectNet(str)) {
            this.netConnects.remove(str);
            RxBus.getDefault().post(new BluetoothEvent((byte) 0, str));
        }
    }

    public void connectNet(String str, Device device) {
        if (!TextUtils.isEmpty(str) && !isConnect(str) && device != null) {
            this.netConnects.put(str, device);
            RxBus.getDefault().post(new BluetoothEvent((byte) 2, str));
        }
    }

    public boolean isConnectNet(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.netConnects.containsKey(str);
    }

    public void disableNet() {
        this.netConnects.clear();
    }
}
