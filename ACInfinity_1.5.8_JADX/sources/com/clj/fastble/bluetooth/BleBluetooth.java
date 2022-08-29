package com.clj.fastble.bluetooth;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleIndicateCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleReadCallback;
import com.clj.fastble.callback.BleRssiCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleConnectStateParameter;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleMsg;
import com.clj.fastble.exception.ConnectException;
import com.clj.fastble.exception.OtherException;
import com.clj.fastble.exception.TimeoutException;
import com.clj.fastble.utils.BleLog;
import com.eternal.control.ControlCFragment;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BleBluetooth {
    /* access modifiers changed from: private */
    public BleDevice bleDevice;
    /* access modifiers changed from: private */
    public BleGattCallback bleGattCallback;
    /* access modifiers changed from: private */
    public HashMap<String, BleIndicateCallback> bleIndicateCallbackHashMap = new HashMap<>();
    /* access modifiers changed from: private */
    public BleMtuChangedCallback bleMtuChangedCallback;
    /* access modifiers changed from: private */
    public HashMap<String, BleNotifyCallback> bleNotifyCallbackHashMap = new HashMap<>();
    /* access modifiers changed from: private */
    public HashMap<String, BleReadCallback> bleReadCallbackHashMap = new HashMap<>();
    /* access modifiers changed from: private */
    public BleRssiCallback bleRssiCallback;
    /* access modifiers changed from: private */
    public HashMap<String, BleWriteCallback> bleWriteCallbackHashMap = new HashMap<>();
    /* access modifiers changed from: private */
    public BluetoothGatt bluetoothGatt;
    /* access modifiers changed from: private */
    public int connectRetryCount = 0;
    private BluetoothGattCallback coreGattCallback = new BluetoothGattCallback() {
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            BleLog.m43i("BluetoothGattCallback：onConnectionStateChange \nstatus: " + i + 10 + "newState: " + i2 + 10 + "currentThread: " + Thread.currentThread().getName());
            BluetoothGatt unused = BleBluetooth.this.bluetoothGatt = bluetoothGatt;
            BleBluetooth.this.mainHandler.removeMessages(7);
            if (i2 == 2) {
                Message obtainMessage = BleBluetooth.this.mainHandler.obtainMessage();
                obtainMessage.what = 4;
                BleBluetooth.this.mainHandler.sendMessageDelayed(obtainMessage, 500);
            } else if (i2 != 0) {
            } else {
                if (BleBluetooth.this.lastState == LastState.CONNECT_CONNECTING) {
                    Message obtainMessage2 = BleBluetooth.this.mainHandler.obtainMessage();
                    obtainMessage2.what = 1;
                    obtainMessage2.obj = new BleConnectStateParameter(i);
                    BleBluetooth.this.mainHandler.sendMessage(obtainMessage2);
                } else if (BleBluetooth.this.lastState == LastState.CONNECT_CONNECTED) {
                    Message obtainMessage3 = BleBluetooth.this.mainHandler.obtainMessage();
                    obtainMessage3.what = 2;
                    BleConnectStateParameter bleConnectStateParameter = new BleConnectStateParameter(i);
                    bleConnectStateParameter.setActive(BleBluetooth.this.isActiveDisconnect);
                    obtainMessage3.obj = bleConnectStateParameter;
                    BleBluetooth.this.mainHandler.sendMessage(obtainMessage3);
                }
            }
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            BleLog.m43i("BluetoothGattCallback：onServicesDiscovered \nstatus: " + i + 10 + "currentThread: " + Thread.currentThread().getName());
            BluetoothGatt unused = BleBluetooth.this.bluetoothGatt = bluetoothGatt;
            if (i == 0) {
                Message obtainMessage = BleBluetooth.this.mainHandler.obtainMessage();
                obtainMessage.what = 6;
                obtainMessage.obj = new BleConnectStateParameter(i);
                BleBluetooth.this.mainHandler.sendMessage(obtainMessage);
                return;
            }
            Message obtainMessage2 = BleBluetooth.this.mainHandler.obtainMessage();
            obtainMessage2.what = 5;
            BleBluetooth.this.mainHandler.sendMessage(obtainMessage2);
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            Handler handler;
            Handler handler2;
            super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
            for (Map.Entry value : BleBluetooth.this.bleNotifyCallbackHashMap.entrySet()) {
                Object value2 = value.getValue();
                if (value2 instanceof BleNotifyCallback) {
                    BleNotifyCallback bleNotifyCallback = (BleNotifyCallback) value2;
                    if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(bleNotifyCallback.getKey()) && (handler2 = bleNotifyCallback.getHandler()) != null) {
                        Message obtainMessage = handler2.obtainMessage();
                        obtainMessage.what = 19;
                        obtainMessage.obj = bleNotifyCallback;
                        Bundle bundle = new Bundle();
                        bundle.putByteArray(BleMsg.KEY_NOTIFY_BUNDLE_VALUE, bluetoothGattCharacteristic.getValue());
                        obtainMessage.setData(bundle);
                        handler2.sendMessage(obtainMessage);
                    }
                }
            }
            for (Map.Entry value3 : BleBluetooth.this.bleIndicateCallbackHashMap.entrySet()) {
                Object value4 = value3.getValue();
                if (value4 instanceof BleIndicateCallback) {
                    BleIndicateCallback bleIndicateCallback = (BleIndicateCallback) value4;
                    if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(bleIndicateCallback.getKey()) && (handler = bleIndicateCallback.getHandler()) != null) {
                        Message obtainMessage2 = handler.obtainMessage();
                        obtainMessage2.what = 35;
                        obtainMessage2.obj = bleIndicateCallback;
                        Bundle bundle2 = new Bundle();
                        bundle2.putByteArray(BleMsg.KEY_INDICATE_BUNDLE_VALUE, bluetoothGattCharacteristic.getValue());
                        obtainMessage2.setData(bundle2);
                        handler.sendMessage(obtainMessage2);
                    }
                }
            }
        }

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            Handler handler;
            Handler handler2;
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            for (Map.Entry value : BleBluetooth.this.bleNotifyCallbackHashMap.entrySet()) {
                Object value2 = value.getValue();
                if (value2 instanceof BleNotifyCallback) {
                    BleNotifyCallback bleNotifyCallback = (BleNotifyCallback) value2;
                    if (bluetoothGattDescriptor.getCharacteristic().getUuid().toString().equalsIgnoreCase(bleNotifyCallback.getKey()) && (handler2 = bleNotifyCallback.getHandler()) != null) {
                        Message obtainMessage = handler2.obtainMessage();
                        obtainMessage.what = 18;
                        obtainMessage.obj = bleNotifyCallback;
                        Bundle bundle = new Bundle();
                        bundle.putInt(BleMsg.KEY_NOTIFY_BUNDLE_STATUS, i);
                        obtainMessage.setData(bundle);
                        handler2.sendMessage(obtainMessage);
                    }
                }
            }
            for (Map.Entry value3 : BleBluetooth.this.bleIndicateCallbackHashMap.entrySet()) {
                Object value4 = value3.getValue();
                if (value4 instanceof BleIndicateCallback) {
                    BleIndicateCallback bleIndicateCallback = (BleIndicateCallback) value4;
                    if (bluetoothGattDescriptor.getCharacteristic().getUuid().toString().equalsIgnoreCase(bleIndicateCallback.getKey()) && (handler = bleIndicateCallback.getHandler()) != null) {
                        Message obtainMessage2 = handler.obtainMessage();
                        obtainMessage2.what = 34;
                        obtainMessage2.obj = bleIndicateCallback;
                        Bundle bundle2 = new Bundle();
                        bundle2.putInt(BleMsg.KEY_INDICATE_BUNDLE_STATUS, i);
                        obtainMessage2.setData(bundle2);
                        handler.sendMessage(obtainMessage2);
                    }
                }
            }
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            Handler handler;
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            for (Map.Entry value : BleBluetooth.this.bleWriteCallbackHashMap.entrySet()) {
                Object value2 = value.getValue();
                if (value2 instanceof BleWriteCallback) {
                    BleWriteCallback bleWriteCallback = (BleWriteCallback) value2;
                    if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(bleWriteCallback.getKey()) && (handler = bleWriteCallback.getHandler()) != null) {
                        Message obtainMessage = handler.obtainMessage();
                        obtainMessage.what = 50;
                        obtainMessage.obj = bleWriteCallback;
                        Bundle bundle = new Bundle();
                        bundle.putInt(BleMsg.KEY_WRITE_BUNDLE_STATUS, i);
                        bundle.putByteArray(BleMsg.KEY_WRITE_BUNDLE_VALUE, bluetoothGattCharacteristic.getValue());
                        obtainMessage.setData(bundle);
                        handler.sendMessage(obtainMessage);
                    }
                }
            }
        }

        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            Handler handler;
            super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
            for (Map.Entry value : BleBluetooth.this.bleReadCallbackHashMap.entrySet()) {
                Object value2 = value.getValue();
                if (value2 instanceof BleReadCallback) {
                    BleReadCallback bleReadCallback = (BleReadCallback) value2;
                    if (bluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(bleReadCallback.getKey()) && (handler = bleReadCallback.getHandler()) != null) {
                        Message obtainMessage = handler.obtainMessage();
                        obtainMessage.what = 66;
                        obtainMessage.obj = bleReadCallback;
                        Bundle bundle = new Bundle();
                        bundle.putInt(BleMsg.KEY_READ_BUNDLE_STATUS, i);
                        bundle.putByteArray(BleMsg.KEY_READ_BUNDLE_VALUE, bluetoothGattCharacteristic.getValue());
                        obtainMessage.setData(bundle);
                        handler.sendMessage(obtainMessage);
                    }
                }
            }
        }

        public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
            Handler handler;
            super.onReadRemoteRssi(bluetoothGatt, i, i2);
            if (BleBluetooth.this.bleRssiCallback != null && (handler = BleBluetooth.this.bleRssiCallback.getHandler()) != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 82;
                obtainMessage.obj = BleBluetooth.this.bleRssiCallback;
                Bundle bundle = new Bundle();
                bundle.putInt(BleMsg.KEY_READ_RSSI_BUNDLE_STATUS, i2);
                bundle.putInt(BleMsg.KEY_READ_RSSI_BUNDLE_VALUE, i);
                obtainMessage.setData(bundle);
                handler.sendMessage(obtainMessage);
            }
        }

        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            Handler handler;
            super.onMtuChanged(bluetoothGatt, i, i2);
            if (BleBluetooth.this.bleMtuChangedCallback != null && (handler = BleBluetooth.this.bleMtuChangedCallback.getHandler()) != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 98;
                obtainMessage.obj = BleBluetooth.this.bleMtuChangedCallback;
                Bundle bundle = new Bundle();
                bundle.putInt(BleMsg.KEY_SET_MTU_BUNDLE_STATUS, i2);
                bundle.putInt(BleMsg.KEY_SET_MTU_BUNDLE_VALUE, i);
                obtainMessage.setData(bundle);
                handler.sendMessage(obtainMessage);
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean isActiveDisconnect = false;
    /* access modifiers changed from: private */
    public LastState lastState;
    /* access modifiers changed from: private */
    public MainHandler mainHandler = new MainHandler(Looper.getMainLooper());

    enum LastState {
        CONNECT_IDLE,
        CONNECT_CONNECTING,
        CONNECT_CONNECTED,
        CONNECT_FAILURE,
        CONNECT_DISCONNECT
    }

    public BleBluetooth(BleDevice bleDevice2) {
        this.bleDevice = bleDevice2;
    }

    public BleConnector newBleConnector() {
        return new BleConnector(this);
    }

    public synchronized void addConnectGattCallback(BleGattCallback bleGattCallback2) {
        this.bleGattCallback = bleGattCallback2;
    }

    public synchronized void removeConnectGattCallback() {
        this.bleGattCallback = null;
    }

    public synchronized void addNotifyCallback(String str, BleNotifyCallback bleNotifyCallback) {
        this.bleNotifyCallbackHashMap.put(str, bleNotifyCallback);
    }

    public synchronized void addIndicateCallback(String str, BleIndicateCallback bleIndicateCallback) {
        this.bleIndicateCallbackHashMap.put(str, bleIndicateCallback);
    }

    public synchronized void addWriteCallback(String str, BleWriteCallback bleWriteCallback) {
        this.bleWriteCallbackHashMap.put(str, bleWriteCallback);
    }

    public synchronized void addReadCallback(String str, BleReadCallback bleReadCallback) {
        this.bleReadCallbackHashMap.put(str, bleReadCallback);
    }

    public synchronized void removeNotifyCallback(String str) {
        if (this.bleNotifyCallbackHashMap.containsKey(str)) {
            this.bleNotifyCallbackHashMap.remove(str);
        }
    }

    public synchronized void removeIndicateCallback(String str) {
        if (this.bleIndicateCallbackHashMap.containsKey(str)) {
            this.bleIndicateCallbackHashMap.remove(str);
        }
    }

    public synchronized void removeWriteCallback(String str) {
        if (this.bleWriteCallbackHashMap.containsKey(str)) {
            this.bleWriteCallbackHashMap.remove(str);
        }
    }

    public synchronized void removeReadCallback(String str) {
        if (this.bleReadCallbackHashMap.containsKey(str)) {
            this.bleReadCallbackHashMap.remove(str);
        }
    }

    public synchronized void clearCharacterCallback() {
        HashMap<String, BleNotifyCallback> hashMap = this.bleNotifyCallbackHashMap;
        if (hashMap != null) {
            hashMap.clear();
        }
        HashMap<String, BleIndicateCallback> hashMap2 = this.bleIndicateCallbackHashMap;
        if (hashMap2 != null) {
            hashMap2.clear();
        }
        HashMap<String, BleWriteCallback> hashMap3 = this.bleWriteCallbackHashMap;
        if (hashMap3 != null) {
            hashMap3.clear();
        }
        HashMap<String, BleReadCallback> hashMap4 = this.bleReadCallbackHashMap;
        if (hashMap4 != null) {
            hashMap4.clear();
        }
    }

    public synchronized void addRssiCallback(BleRssiCallback bleRssiCallback2) {
        this.bleRssiCallback = bleRssiCallback2;
    }

    public synchronized void removeRssiCallback() {
        this.bleRssiCallback = null;
    }

    public synchronized void addMtuChangedCallback(BleMtuChangedCallback bleMtuChangedCallback2) {
        this.bleMtuChangedCallback = bleMtuChangedCallback2;
    }

    public synchronized void removeMtuChangedCallback() {
        this.bleMtuChangedCallback = null;
    }

    public String getDeviceKey() {
        return this.bleDevice.getKey();
    }

    public BleDevice getDevice() {
        return this.bleDevice;
    }

    public BluetoothGatt getBluetoothGatt() {
        return this.bluetoothGatt;
    }

    public synchronized BluetoothGatt connect(BleDevice bleDevice2, boolean z, BleGattCallback bleGattCallback2) {
        return connect(bleDevice2, z, bleGattCallback2, 0);
    }

    public synchronized BluetoothGatt connect(BleDevice bleDevice2, boolean z, BleGattCallback bleGattCallback2, int i) {
        BleLog.m43i("connect device: " + bleDevice2.getName() + "\nmac: " + bleDevice2.getMac() + "\nautoConnect: " + z + "\ncurrentThread: " + Thread.currentThread().getName() + "\nconnectCount:" + (i + 1));
        this.connectRetryCount = i;
        addConnectGattCallback(bleGattCallback2);
        this.lastState = LastState.CONNECT_CONNECTING;
        if (Build.VERSION.SDK_INT >= 23) {
            this.bluetoothGatt = bleDevice2.getDevice().connectGatt(BleManager.getInstance().getContext(), z, this.coreGattCallback, 2);
        } else {
            this.bluetoothGatt = bleDevice2.getDevice().connectGatt(BleManager.getInstance().getContext(), z, this.coreGattCallback);
        }
        if (this.bluetoothGatt != null) {
            BleGattCallback bleGattCallback3 = this.bleGattCallback;
            if (bleGattCallback3 != null) {
                bleGattCallback3.onStartConnect();
            }
            Message obtainMessage = this.mainHandler.obtainMessage();
            obtainMessage.what = 7;
            this.mainHandler.sendMessageDelayed(obtainMessage, BleManager.getInstance().getConnectOverTime());
        } else {
            disconnectGatt();
            refreshDeviceCache();
            closeBluetoothGatt();
            this.lastState = LastState.CONNECT_FAILURE;
            BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(this);
            BleGattCallback bleGattCallback4 = this.bleGattCallback;
            if (bleGattCallback4 != null) {
                bleGattCallback4.onConnectFail(bleDevice2, new OtherException("GATT connect exception occurred!"));
            }
        }
        return this.bluetoothGatt;
    }

    public synchronized void disconnect() {
        this.isActiveDisconnect = true;
        disconnectGatt();
    }

    public synchronized void destroy() {
        this.lastState = LastState.CONNECT_IDLE;
        disconnectGatt();
        refreshDeviceCache();
        closeBluetoothGatt();
        removeConnectGattCallback();
        removeRssiCallback();
        removeMtuChangedCallback();
        clearCharacterCallback();
        this.mainHandler.removeCallbacksAndMessages((Object) null);
    }

    /* access modifiers changed from: private */
    public synchronized void disconnectGatt() {
        BluetoothGatt bluetoothGatt2 = this.bluetoothGatt;
        if (bluetoothGatt2 != null) {
            try {
                bluetoothGatt2.disconnect();
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void refreshDeviceCache() {
        BluetoothGatt bluetoothGatt2;
        try {
            Method method = BluetoothGatt.class.getMethod(ControlCFragment.REFRESH, new Class[0]);
            if (!(method == null || (bluetoothGatt2 = this.bluetoothGatt) == null)) {
                boolean booleanValue = ((Boolean) method.invoke(bluetoothGatt2, new Object[0])).booleanValue();
                BleLog.m43i("refreshDeviceCache, is success:  " + booleanValue);
            }
        } catch (Exception e) {
            BleLog.m43i("exception occur while refreshing device: " + e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    /* access modifiers changed from: private */
    public synchronized void closeBluetoothGatt() {
        BluetoothGatt bluetoothGatt2 = this.bluetoothGatt;
        if (bluetoothGatt2 != null) {
            try {
                bluetoothGatt2.close();
            } catch (Throwable unused) {
            }
        }
    }

    private final class MainHandler extends Handler {
        MainHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    BleBluetooth.this.disconnectGatt();
                    BleBluetooth.this.refreshDeviceCache();
                    BleBluetooth.this.closeBluetoothGatt();
                    LastState unused = BleBluetooth.this.lastState = LastState.CONNECT_FAILURE;
                    BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(BleBluetooth.this);
                    int status = ((BleConnectStateParameter) message.obj).getStatus();
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onConnectFail(BleBluetooth.this.bleDevice, new ConnectException(BleBluetooth.this.bluetoothGatt, status));
                        return;
                    }
                    return;
                case 2:
                    LastState unused2 = BleBluetooth.this.lastState = LastState.CONNECT_DISCONNECT;
                    BleManager.getInstance().getMultipleBluetoothController().removeBleBluetooth(BleBluetooth.this);
                    BleBluetooth.this.disconnect();
                    BleBluetooth.this.refreshDeviceCache();
                    BleBluetooth.this.closeBluetoothGatt();
                    BleBluetooth.this.removeRssiCallback();
                    BleBluetooth.this.removeMtuChangedCallback();
                    BleBluetooth.this.clearCharacterCallback();
                    BleBluetooth.this.mainHandler.removeCallbacksAndMessages((Object) null);
                    BleConnectStateParameter bleConnectStateParameter = (BleConnectStateParameter) message.obj;
                    boolean isActive = bleConnectStateParameter.isActive();
                    int status2 = bleConnectStateParameter.getStatus();
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onDisConnected(isActive, BleBluetooth.this.bleDevice, BleBluetooth.this.bluetoothGatt, status2);
                        return;
                    }
                    return;
                case 3:
                    BleBluetooth bleBluetooth = BleBluetooth.this;
                    bleBluetooth.connect(bleBluetooth.bleDevice, false, BleBluetooth.this.bleGattCallback, BleBluetooth.this.connectRetryCount);
                    return;
                case 4:
                    if (BleBluetooth.this.bluetoothGatt == null) {
                        Message obtainMessage = BleBluetooth.this.mainHandler.obtainMessage();
                        obtainMessage.what = 5;
                        BleBluetooth.this.mainHandler.sendMessage(obtainMessage);
                        return;
                    } else if (!BleBluetooth.this.bluetoothGatt.discoverServices()) {
                        Message obtainMessage2 = BleBluetooth.this.mainHandler.obtainMessage();
                        obtainMessage2.what = 5;
                        BleBluetooth.this.mainHandler.sendMessage(obtainMessage2);
                        return;
                    } else {
                        return;
                    }
                case 5:
                    BleBluetooth.this.disconnectGatt();
                    BleBluetooth.this.refreshDeviceCache();
                    BleBluetooth.this.closeBluetoothGatt();
                    LastState unused3 = BleBluetooth.this.lastState = LastState.CONNECT_FAILURE;
                    BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(BleBluetooth.this);
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onConnectFail(BleBluetooth.this.bleDevice, new OtherException("GATT discover services exception occurred!"));
                        return;
                    }
                    return;
                case 6:
                    LastState unused4 = BleBluetooth.this.lastState = LastState.CONNECT_CONNECTED;
                    boolean unused5 = BleBluetooth.this.isActiveDisconnect = false;
                    BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(BleBluetooth.this);
                    BleManager.getInstance().getMultipleBluetoothController().addBleBluetooth(BleBluetooth.this);
                    int status3 = ((BleConnectStateParameter) message.obj).getStatus();
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onConnectSuccess(BleBluetooth.this.bleDevice, BleBluetooth.this.bluetoothGatt, status3);
                        return;
                    }
                    return;
                case 7:
                    BleBluetooth.this.disconnectGatt();
                    BleBluetooth.this.refreshDeviceCache();
                    BleBluetooth.this.closeBluetoothGatt();
                    LastState unused6 = BleBluetooth.this.lastState = LastState.CONNECT_FAILURE;
                    BleManager.getInstance().getMultipleBluetoothController().removeConnectingBle(BleBluetooth.this);
                    if (BleBluetooth.this.bleGattCallback != null) {
                        BleBluetooth.this.bleGattCallback.onConnectFail(BleBluetooth.this.bleDevice, new TimeoutException());
                        return;
                    }
                    return;
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    }
}
