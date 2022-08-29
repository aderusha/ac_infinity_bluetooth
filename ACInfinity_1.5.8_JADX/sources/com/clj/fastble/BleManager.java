package com.clj.fastble;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Build;
import android.os.Looper;
import com.clj.fastble.bluetooth.BleBluetooth;
import com.clj.fastble.bluetooth.BleConnector;
import com.clj.fastble.bluetooth.MultipleBluetoothController;
import com.clj.fastble.bluetooth.SplitWriter;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleIndicateCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.callback.BleRssiCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleScanState;
import com.clj.fastble.exception.OtherException;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.clj.fastble.scan.BleScanner;
import com.clj.fastble.utils.BleLog;
import java.util.List;

public class BleManager {
    private static final int DEFAULT_CONNECT_OVER_TIME = 10000;
    private static final int DEFAULT_CONNECT_RETRY_COUNT = 0;
    private static final int DEFAULT_CONNECT_RETRY_INTERVAL = 5000;
    private static final int DEFAULT_MAX_MTU = 512;
    private static final int DEFAULT_MAX_MULTIPLE_DEVICE = 7;
    private static final int DEFAULT_MTU = 23;
    private static final int DEFAULT_OPERATE_TIME = 5000;
    public static final int DEFAULT_SCAN_TIME = 10000;
    private static final int DEFAULT_WRITE_DATA_SPLIT_COUNT = 20;
    private BleScanRuleConfig bleScanRuleConfig;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothManager bluetoothManager;
    private long connectOverTime = 10000;
    private Application context;
    private BluetoothLeScanner mBluetoothLeScanner;
    private int maxConnectCount = 7;
    private MultipleBluetoothController multipleBluetoothController;
    private int operateTimeout = 5000;
    private int reConnectCount = 0;
    private long reConnectInterval = 5000;
    private int splitWriteNum = 20;

    public static BleManager getInstance() {
        return BleManagerHolder.sBleManager;
    }

    private static class BleManagerHolder {
        /* access modifiers changed from: private */
        public static final BleManager sBleManager = new BleManager();

        private BleManagerHolder() {
        }
    }

    public void init(Application application) {
        if (this.context == null && application != null) {
            this.context = application;
            if (isSupportBle()) {
                this.bluetoothManager = (BluetoothManager) this.context.getSystemService("bluetooth");
            }
            this.bluetoothAdapter = this.bluetoothManager.getAdapter();
            this.multipleBluetoothController = new MultipleBluetoothController();
            this.bleScanRuleConfig = new BleScanRuleConfig();
            BluetoothAdapter bluetoothAdapter2 = this.bluetoothAdapter;
            if (bluetoothAdapter2 != null) {
                this.mBluetoothLeScanner = bluetoothAdapter2.getBluetoothLeScanner();
            }
        }
    }

    public Context getContext() {
        return this.context;
    }

    public BluetoothLeScanner getBluetoothLeScanner() {
        if (this.mBluetoothLeScanner == null) {
            this.mBluetoothLeScanner = getBluetoothAdapter().getBluetoothLeScanner();
        }
        return this.mBluetoothLeScanner;
    }

    public BluetoothManager getBluetoothManager() {
        if (this.bluetoothManager == null) {
            this.bluetoothManager = (BluetoothManager) this.context.getApplicationContext().getSystemService("bluetooth");
        }
        return this.bluetoothManager;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        if (this.bluetoothAdapter == null) {
            this.bluetoothAdapter = getBluetoothManager().getAdapter();
        }
        return this.bluetoothAdapter;
    }

    public BleScanRuleConfig getScanRuleConfig() {
        return this.bleScanRuleConfig;
    }

    public MultipleBluetoothController getMultipleBluetoothController() {
        return this.multipleBluetoothController;
    }

    public void initScanRule(BleScanRuleConfig bleScanRuleConfig2) {
        this.bleScanRuleConfig = bleScanRuleConfig2;
    }

    public int getMaxConnectCount() {
        return this.maxConnectCount;
    }

    public BleManager setMaxConnectCount(int i) {
        if (i > 7) {
            i = 7;
        }
        this.maxConnectCount = i;
        return this;
    }

    public int getOperateTimeout() {
        return this.operateTimeout;
    }

    public BleManager setOperateTimeout(int i) {
        this.operateTimeout = i;
        return this;
    }

    public int getReConnectCount() {
        return this.reConnectCount;
    }

    public long getReConnectInterval() {
        return this.reConnectInterval;
    }

    public BleManager setReConnectCount(int i) {
        return setReConnectCount(i, 5000);
    }

    public BleManager setReConnectCount(int i, long j) {
        if (i > 10) {
            i = 10;
        }
        if (j < 0) {
            j = 0;
        }
        this.reConnectCount = i;
        this.reConnectInterval = j;
        return this;
    }

    public int getSplitWriteNum() {
        return this.splitWriteNum;
    }

    public BleManager setSplitWriteNum(int i) {
        if (i > 0) {
            this.splitWriteNum = i;
        }
        return this;
    }

    public long getConnectOverTime() {
        return this.connectOverTime;
    }

    public BleManager setConnectOverTime(long j) {
        if (j <= 0) {
            j = 100;
        }
        this.connectOverTime = j;
        return this;
    }

    public BleManager enableLog(boolean z) {
        BleLog.isPrint = z;
        return this;
    }

    public void scan(BleScanCallback bleScanCallback) {
        BleLog.m42e("BLE----BleManager---scan");
        if (bleScanCallback != null) {
            BleLog.m42e("BLE----BleManager---scan-1" + isBlueEnable());
            if (!isBlueEnable()) {
                BleLog.m42e("Bluetooth not enable!");
                bleScanCallback.onScanStarted(false);
                return;
            }
            BleLog.m42e("BLE----BleManager---scan-2" + isBlueEnable());
            BleScanner.getInstance().scan(this.bleScanRuleConfig.getServiceUuids(), this.bleScanRuleConfig.getDeviceNames(), this.bleScanRuleConfig.getDeviceMac(), this.bleScanRuleConfig.isFuzzy(), this.bleScanRuleConfig.getScanTimeOut(), this.bleScanRuleConfig.isContinuous(), this.bleScanRuleConfig.isNeedConnect(), bleScanCallback);
            return;
        }
        throw new IllegalArgumentException("BleScanCallback can not be Null!");
    }

    public BluetoothGatt connect(BleDevice bleDevice, BleGattCallback bleGattCallback) {
        if (bleGattCallback == null) {
            throw new IllegalArgumentException("BleGattCallback can not be Null!");
        } else if (!isBlueEnable()) {
            BleLog.m42e("Bluetooth not enable!");
            bleGattCallback.onConnectFail(bleDevice, new OtherException("Bluetooth not enable!"));
            return null;
        } else {
            if (Looper.myLooper() == null || Looper.myLooper() != Looper.getMainLooper()) {
                BleLog.m44w("Be careful: currentThread is not MainThread!");
            }
            if (bleDevice != null && bleDevice.getDevice() != null) {
                return this.multipleBluetoothController.buildConnectingBle(bleDevice).connect(bleDevice, this.bleScanRuleConfig.isAutoConnect(), bleGattCallback);
            }
            bleGattCallback.onConnectFail(bleDevice, new OtherException("Not Found Device Exception Occurred!"));
            return null;
        }
    }

    public BluetoothGatt connect(String str, BleGattCallback bleGattCallback) {
        return connect(new BleDevice(getBluetoothAdapter().getRemoteDevice(str), 0, (byte[]) null, 0), bleGattCallback);
    }

    public void cancelScan() {
        BleScanner.getInstance().stopLeScan();
    }

    public void notify(BleDevice bleDevice, String str, String str2, BleNotifyCallback bleNotifyCallback) {
        notify(bleDevice, str, str2, false, bleNotifyCallback);
    }

    public void notify(BleDevice bleDevice, String str, String str2, boolean z, BleNotifyCallback bleNotifyCallback) {
        if (bleNotifyCallback != null) {
            BleBluetooth bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice);
            if (bleBluetooth == null) {
                bleNotifyCallback.onNotifyFailure(new OtherException("This device not connect!"));
            } else {
                bleBluetooth.newBleConnector().withUUIDString(str, str2).enableCharacteristicNotify(bleNotifyCallback, str2, z);
            }
        } else {
            throw new IllegalArgumentException("BleNotifyCallback can not be Null!");
        }
    }

    public void indicate(BleDevice bleDevice, String str, String str2, BleIndicateCallback bleIndicateCallback) {
        indicate(bleDevice, str, str2, false, bleIndicateCallback);
    }

    public void indicate(BleDevice bleDevice, String str, String str2, boolean z, BleIndicateCallback bleIndicateCallback) {
        if (bleIndicateCallback != null) {
            BleBluetooth bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice);
            if (bleBluetooth == null) {
                bleIndicateCallback.onIndicateFailure(new OtherException("This device not connect!"));
            } else {
                bleBluetooth.newBleConnector().withUUIDString(str, str2).enableCharacteristicIndicate(bleIndicateCallback, str2, z);
            }
        } else {
            throw new IllegalArgumentException("BleIndicateCallback can not be Null!");
        }
    }

    public boolean stopNotify(BleDevice bleDevice, String str, String str2) {
        return stopNotify(bleDevice, str, str2, false);
    }

    public boolean stopNotify(BleDevice bleDevice, String str, String str2, boolean z) {
        BleBluetooth bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice);
        if (bleBluetooth == null) {
            return false;
        }
        boolean disableCharacteristicNotify = bleBluetooth.newBleConnector().withUUIDString(str, str2).disableCharacteristicNotify(z);
        if (disableCharacteristicNotify) {
            bleBluetooth.removeNotifyCallback(str2);
        }
        return disableCharacteristicNotify;
    }

    public boolean stopIndicate(BleDevice bleDevice, String str, String str2) {
        return stopIndicate(bleDevice, str, str2, false);
    }

    public boolean stopIndicate(BleDevice bleDevice, String str, String str2, boolean z) {
        BleBluetooth bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice);
        if (bleBluetooth == null) {
            return false;
        }
        boolean disableCharacteristicIndicate = bleBluetooth.newBleConnector().withUUIDString(str, str2).disableCharacteristicIndicate(z);
        if (disableCharacteristicIndicate) {
            bleBluetooth.removeIndicateCallback(str2);
        }
        return disableCharacteristicIndicate;
    }

    public void write(BleDevice bleDevice, String str, String str2, byte[] bArr, BleWriteCallback bleWriteCallback) {
        write(bleDevice, str, str2, bArr, true, bleWriteCallback);
    }

    public void write(BleDevice bleDevice, String str, String str2, byte[] bArr, boolean z, BleWriteCallback bleWriteCallback) {
        write(bleDevice, str, str2, bArr, z, true, 0, bleWriteCallback);
    }

    public void write(BleDevice bleDevice, String str, String str2, byte[] bArr, boolean z, boolean z2, long j, BleWriteCallback bleWriteCallback) {
        String str3 = str2;
        byte[] bArr2 = bArr;
        BleWriteCallback bleWriteCallback2 = bleWriteCallback;
        if (bleWriteCallback2 == null) {
            throw new IllegalArgumentException("BleWriteCallback can not be Null!");
        } else if (bArr2 == null) {
            BleLog.m42e("data is Null!");
            bleWriteCallback2.onWriteFailure(new OtherException("data is Null!"));
        } else {
            if (bArr2.length > 20 && !z) {
                BleLog.m44w("Be careful: data's length beyond 20! Ensure MTU higher than 23, or use spilt write!");
            }
            BleDevice bleDevice2 = bleDevice;
            BleBluetooth bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice);
            if (bleBluetooth == null) {
                bleWriteCallback2.onWriteFailure(new OtherException("This device not connect!"));
            } else if (!z || bArr2.length <= getSplitWriteNum()) {
                BleConnector newBleConnector = bleBluetooth.newBleConnector();
                String str4 = str;
                newBleConnector.withUUIDString(str, str2).writeCharacteristic(bArr, bleWriteCallback2, str2);
            } else {
                new SplitWriter().splitWrite(bleBluetooth, str, str2, bArr, z2, j, bleWriteCallback);
            }
        }
    }

    public void read(BleDevice bleDevice, String str, String str2, BleReadCallback bleReadCallback) {
        if (bleReadCallback != null) {
            BleBluetooth bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice);
            if (bleBluetooth == null) {
                bleReadCallback.onReadFailure(new OtherException("This device is not connected!"));
            } else {
                bleBluetooth.newBleConnector().withUUIDString(str, str2).readCharacteristic(bleReadCallback, str2);
            }
        } else {
            throw new IllegalArgumentException("BleReadCallback can not be Null!");
        }
    }

    public void readRssi(BleDevice bleDevice, BleRssiCallback bleRssiCallback) {
        if (bleRssiCallback != null) {
            BleBluetooth bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice);
            if (bleBluetooth == null) {
                bleRssiCallback.onRssiFailure(new OtherException("This device is not connected!"));
            } else {
                bleBluetooth.newBleConnector().readRemoteRssi(bleRssiCallback);
            }
        } else {
            throw new IllegalArgumentException("BleRssiCallback can not be Null!");
        }
    }

    public void setMtu(BleDevice bleDevice, int i, BleMtuChangedCallback bleMtuChangedCallback) {
        if (bleMtuChangedCallback == null) {
            throw new IllegalArgumentException("BleMtuChangedCallback can not be Null!");
        } else if (i > 512) {
            BleLog.m42e("requiredMtu should lower than 512 !");
            bleMtuChangedCallback.onSetMTUFailure(new OtherException("requiredMtu should lower than 512 !"));
        } else if (i < 23) {
            BleLog.m42e("requiredMtu should higher than 23 !");
            bleMtuChangedCallback.onSetMTUFailure(new OtherException("requiredMtu should higher than 23 !"));
        } else {
            BleBluetooth bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice);
            if (bleBluetooth == null) {
                bleMtuChangedCallback.onSetMTUFailure(new OtherException("This device is not connected!"));
            } else {
                bleBluetooth.newBleConnector().setMtu(i, bleMtuChangedCallback);
            }
        }
    }

    public boolean requestConnectionPriority(BleDevice bleDevice, int i) {
        BleBluetooth bleBluetooth;
        if (Build.VERSION.SDK_INT < 21 || (bleBluetooth = this.multipleBluetoothController.getBleBluetooth(bleDevice)) == null) {
            return false;
        }
        return bleBluetooth.newBleConnector().requestConnectionPriority(i);
    }

    public boolean isSupportBle() {
        return Build.VERSION.SDK_INT >= 18 && this.context.getApplicationContext().getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public void enableBluetooth() {
        BluetoothAdapter bluetoothAdapter2 = this.bluetoothAdapter;
        if (bluetoothAdapter2 != null) {
            bluetoothAdapter2.enable();
        }
    }

    public void disableBluetooth() {
        BluetoothAdapter bluetoothAdapter2 = this.bluetoothAdapter;
        if (bluetoothAdapter2 != null && bluetoothAdapter2.isEnabled()) {
            this.bluetoothAdapter.disable();
        }
    }

    public boolean isBlueEnable() {
        BluetoothAdapter bluetoothAdapter2 = this.bluetoothAdapter;
        return bluetoothAdapter2 != null && bluetoothAdapter2.isEnabled();
    }

    public BleDevice convertBleDevice(BluetoothDevice bluetoothDevice) {
        return new BleDevice(bluetoothDevice);
    }

    public BleDevice convertBleDevice(ScanResult scanResult) {
        if (scanResult != null) {
            BluetoothDevice device = scanResult.getDevice();
            int rssi = scanResult.getRssi();
            ScanRecord scanRecord = scanResult.getScanRecord();
            byte[] bArr = null;
            if (scanRecord != null) {
                bArr = scanRecord.getBytes();
            }
            return new BleDevice(device, rssi, bArr, scanResult.getTimestampNanos());
        }
        throw new IllegalArgumentException("scanResult can not be Null!");
    }

    public BleBluetooth getBleBluetooth(BleDevice bleDevice) {
        MultipleBluetoothController multipleBluetoothController2 = this.multipleBluetoothController;
        if (multipleBluetoothController2 != null) {
            return multipleBluetoothController2.getBleBluetooth(bleDevice);
        }
        return null;
    }

    public BluetoothGatt getBluetoothGatt(BleDevice bleDevice) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            return bleBluetooth.getBluetoothGatt();
        }
        return null;
    }

    public List<BluetoothGattService> getBluetoothGattServices(BleDevice bleDevice) {
        BluetoothGatt bluetoothGatt = getBluetoothGatt(bleDevice);
        if (bluetoothGatt != null) {
            return bluetoothGatt.getServices();
        }
        return null;
    }

    public List<BluetoothGattCharacteristic> getBluetoothGattCharacteristics(BluetoothGattService bluetoothGattService) {
        return bluetoothGattService.getCharacteristics();
    }

    public void removeConnectGattCallback(BleDevice bleDevice) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            bleBluetooth.removeConnectGattCallback();
        }
    }

    public void removeRssiCallback(BleDevice bleDevice) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            bleBluetooth.removeRssiCallback();
        }
    }

    public void removeMtuChangedCallback(BleDevice bleDevice) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            bleBluetooth.removeMtuChangedCallback();
        }
    }

    public void removeNotifyCallback(BleDevice bleDevice, String str) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            bleBluetooth.removeNotifyCallback(str);
        }
    }

    public void removeIndicateCallback(BleDevice bleDevice, String str) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            bleBluetooth.removeIndicateCallback(str);
        }
    }

    public void removeWriteCallback(BleDevice bleDevice, String str) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            bleBluetooth.removeWriteCallback(str);
        }
    }

    public void removeReadCallback(BleDevice bleDevice, String str) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            bleBluetooth.removeReadCallback(str);
        }
    }

    public void clearCharacterCallback(BleDevice bleDevice) {
        BleBluetooth bleBluetooth = getBleBluetooth(bleDevice);
        if (bleBluetooth != null) {
            bleBluetooth.clearCharacterCallback();
        }
    }

    public BleScanState getScanSate() {
        return BleScanner.getInstance().getScanState();
    }

    public List<BleDevice> getAllConnectedDevice() {
        MultipleBluetoothController multipleBluetoothController2 = this.multipleBluetoothController;
        if (multipleBluetoothController2 == null) {
            return null;
        }
        return multipleBluetoothController2.getDeviceList();
    }

    public int getConnectState(BleDevice bleDevice) {
        if (bleDevice != null) {
            return getBluetoothManager().getConnectionState(bleDevice.getDevice(), 7);
        }
        return 0;
    }

    public boolean isConnected(BleDevice bleDevice) {
        return getConnectState(bleDevice) == 2;
    }

    public void disconnect(BleDevice bleDevice) {
        MultipleBluetoothController multipleBluetoothController2 = this.multipleBluetoothController;
        if (multipleBluetoothController2 != null) {
            multipleBluetoothController2.disconnect(bleDevice);
        }
    }

    public void disconnectAllDevice() {
        MultipleBluetoothController multipleBluetoothController2 = this.multipleBluetoothController;
        if (multipleBluetoothController2 != null) {
            multipleBluetoothController2.disconnectAllDevice();
        }
    }

    public void destroy() {
        MultipleBluetoothController multipleBluetoothController2 = this.multipleBluetoothController;
        if (multipleBluetoothController2 != null) {
            multipleBluetoothController2.destroy();
        }
    }
}
