package com.clj.fastble.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.os.Build;
import com.clj.fastble.BleManager;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.utils.BleLruHashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleBluetoothController {
    private final BleLruHashMap<String, BleBluetooth> bleLruHashMap = new BleLruHashMap<>(BleManager.getInstance().getMaxConnectCount());
    private final HashMap<String, BleBluetooth> bleTempHashMap = new HashMap<>();

    public synchronized BleBluetooth buildConnectingBle(BleDevice bleDevice) {
        BleBluetooth bleBluetooth;
        bleBluetooth = new BleBluetooth(bleDevice);
        if (!this.bleTempHashMap.containsKey(bleBluetooth.getDeviceKey())) {
            this.bleTempHashMap.put(bleBluetooth.getDeviceKey(), bleBluetooth);
        }
        return bleBluetooth;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeConnectingBle(com.clj.fastble.bluetooth.BleBluetooth r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 != 0) goto L_0x0005
            monitor-exit(r2)
            return
        L_0x0005:
            java.util.HashMap<java.lang.String, com.clj.fastble.bluetooth.BleBluetooth> r0 = r2.bleTempHashMap     // Catch:{ all -> 0x001c }
            java.lang.String r1 = r3.getDeviceKey()     // Catch:{ all -> 0x001c }
            boolean r0 = r0.containsKey(r1)     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001a
            java.util.HashMap<java.lang.String, com.clj.fastble.bluetooth.BleBluetooth> r0 = r2.bleTempHashMap     // Catch:{ all -> 0x001c }
            java.lang.String r3 = r3.getDeviceKey()     // Catch:{ all -> 0x001c }
            r0.remove(r3)     // Catch:{ all -> 0x001c }
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clj.fastble.bluetooth.MultipleBluetoothController.removeConnectingBle(com.clj.fastble.bluetooth.BleBluetooth):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addBleBluetooth(com.clj.fastble.bluetooth.BleBluetooth r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 != 0) goto L_0x0005
            monitor-exit(r2)
            return
        L_0x0005:
            com.clj.fastble.utils.BleLruHashMap<java.lang.String, com.clj.fastble.bluetooth.BleBluetooth> r0 = r2.bleLruHashMap     // Catch:{ all -> 0x001c }
            java.lang.String r1 = r3.getDeviceKey()     // Catch:{ all -> 0x001c }
            boolean r0 = r0.containsKey(r1)     // Catch:{ all -> 0x001c }
            if (r0 != 0) goto L_0x001a
            com.clj.fastble.utils.BleLruHashMap<java.lang.String, com.clj.fastble.bluetooth.BleBluetooth> r0 = r2.bleLruHashMap     // Catch:{ all -> 0x001c }
            java.lang.String r1 = r3.getDeviceKey()     // Catch:{ all -> 0x001c }
            r0.put(r1, r3)     // Catch:{ all -> 0x001c }
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clj.fastble.bluetooth.MultipleBluetoothController.addBleBluetooth(com.clj.fastble.bluetooth.BleBluetooth):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeBleBluetooth(com.clj.fastble.bluetooth.BleBluetooth r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            if (r3 != 0) goto L_0x0005
            monitor-exit(r2)
            return
        L_0x0005:
            com.clj.fastble.utils.BleLruHashMap<java.lang.String, com.clj.fastble.bluetooth.BleBluetooth> r0 = r2.bleLruHashMap     // Catch:{ all -> 0x001c }
            java.lang.String r1 = r3.getDeviceKey()     // Catch:{ all -> 0x001c }
            boolean r0 = r0.containsKey(r1)     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001a
            com.clj.fastble.utils.BleLruHashMap<java.lang.String, com.clj.fastble.bluetooth.BleBluetooth> r0 = r2.bleLruHashMap     // Catch:{ all -> 0x001c }
            java.lang.String r3 = r3.getDeviceKey()     // Catch:{ all -> 0x001c }
            r0.remove(r3)     // Catch:{ all -> 0x001c }
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clj.fastble.bluetooth.MultipleBluetoothController.removeBleBluetooth(com.clj.fastble.bluetooth.BleBluetooth):void");
    }

    public synchronized boolean isContainDevice(BleDevice bleDevice) {
        boolean z;
        if (bleDevice != null) {
            if (this.bleLruHashMap.containsKey(bleDevice.getKey())) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized boolean isContainDevice(BluetoothDevice bluetoothDevice) {
        boolean z;
        if (bluetoothDevice != null) {
            BleLruHashMap<String, BleBluetooth> bleLruHashMap2 = this.bleLruHashMap;
            if (bleLruHashMap2.containsKey(bluetoothDevice.getName() + bluetoothDevice.getAddress())) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized BleBluetooth getBleBluetooth(BleDevice bleDevice) {
        if (bleDevice != null) {
            if (this.bleLruHashMap.containsKey(bleDevice.getKey())) {
                return (BleBluetooth) this.bleLruHashMap.get(bleDevice.getKey());
            }
        }
        return null;
    }

    public synchronized void disconnect(BleDevice bleDevice) {
        if (isContainDevice(bleDevice)) {
            getBleBluetooth(bleDevice).disconnect();
        }
    }

    public synchronized void disconnectAllDevice() {
        for (Map.Entry value : this.bleLruHashMap.entrySet()) {
            ((BleBluetooth) value.getValue()).disconnect();
        }
        this.bleLruHashMap.clear();
    }

    public synchronized void destroy() {
        for (Map.Entry value : this.bleLruHashMap.entrySet()) {
            ((BleBluetooth) value.getValue()).destroy();
        }
        this.bleLruHashMap.clear();
        for (Map.Entry<String, BleBluetooth> value2 : this.bleTempHashMap.entrySet()) {
            ((BleBluetooth) value2.getValue()).destroy();
        }
        this.bleTempHashMap.clear();
    }

    public synchronized List<BleBluetooth> getBleBluetoothList() {
        ArrayList arrayList;
        arrayList = new ArrayList(this.bleLruHashMap.values());
        Collections.sort(arrayList, new Comparator<BleBluetooth>() {
            public int compare(BleBluetooth bleBluetooth, BleBluetooth bleBluetooth2) {
                return bleBluetooth.getDeviceKey().compareToIgnoreCase(bleBluetooth2.getDeviceKey());
            }
        });
        return arrayList;
    }

    public synchronized List<BleDevice> getDeviceList() {
        ArrayList arrayList;
        refreshConnectedDevice();
        arrayList = new ArrayList();
        for (BleBluetooth next : getBleBluetoothList()) {
            if (next != null) {
                arrayList.add(next.getDevice());
            }
        }
        return arrayList;
    }

    public void refreshConnectedDevice() {
        if (Build.VERSION.SDK_INT >= 18) {
            List<BleBluetooth> bleBluetoothList = getBleBluetoothList();
            int i = 0;
            while (bleBluetoothList != null && i < bleBluetoothList.size()) {
                BleBluetooth bleBluetooth = bleBluetoothList.get(i);
                if (!BleManager.getInstance().isConnected(bleBluetooth.getDevice())) {
                    removeBleBluetooth(bleBluetooth);
                }
                i++;
            }
        }
    }
}
