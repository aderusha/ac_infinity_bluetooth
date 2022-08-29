package com.clj.fastble.scan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.clj.fastble.callback.BleScanPresenterImp;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.utils.BleLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BleScanPresenter implements BluetoothAdapter.LeScanCallback {
    /* access modifiers changed from: private */
    public static final List<BleDevice> mBleDeviceList = Collections.synchronizedList(new ArrayList());
    private BleScanPresenterImp mBleScanPresenterImp;
    private boolean mContinuous;
    private String mDeviceMac;
    private String[] mDeviceNames;
    private boolean mFuzzy;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mHandling;
    private Handler mMainHandler = new Handler(Looper.getMainLooper());
    private boolean mNeedConnect;
    private long mScanTimeout;

    public abstract void onLeScan(BleDevice bleDevice);

    public abstract void onScanFinished(List<BleDevice> list);

    public abstract void onScanStarted(boolean z);

    public abstract void onScanning(BleDevice bleDevice);

    private static final class ScanHandler extends Handler {
        private final WeakReference<BleScanPresenter> mBleScanPresenter;

        ScanHandler(Looper looper, BleScanPresenter bleScanPresenter) {
            super(looper);
            this.mBleScanPresenter = new WeakReference<>(bleScanPresenter);
        }

        public void handleMessage(Message message) {
            BleDevice bleDevice;
            BleScanPresenter bleScanPresenter = (BleScanPresenter) this.mBleScanPresenter.get();
            if (bleScanPresenter != null && message.what == 0 && (bleDevice = (BleDevice) message.obj) != null) {
                bleScanPresenter.handleResult(bleDevice);
            }
        }
    }

    /* access modifiers changed from: private */
    public void handleResult(final BleDevice bleDevice) {
        this.mMainHandler.post(new Runnable() {
            public void run() {
                BleScanPresenter.this.onLeScan(bleDevice);
            }
        });
        checkDevice(bleDevice);
    }

    public void prepare(String[] strArr, String str, boolean z, boolean z2, long j, boolean z3, BleScanPresenterImp bleScanPresenterImp) {
        this.mDeviceNames = strArr;
        this.mDeviceMac = str;
        this.mFuzzy = z;
        this.mNeedConnect = z2;
        this.mScanTimeout = j;
        this.mContinuous = z3;
        this.mBleScanPresenterImp = bleScanPresenterImp;
        HandlerThread handlerThread = new HandlerThread("BleScanPresenter");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new ScanHandler(this.mHandlerThread.getLooper(), this);
        this.mHandling = true;
    }

    public boolean isNeedConnect() {
        return this.mNeedConnect;
    }

    public BleScanPresenterImp getBleScanPresenterImp() {
        return this.mBleScanPresenterImp;
    }

    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        if (bluetoothDevice != null && this.mHandling) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 0;
            obtainMessage.obj = new BleDevice(bluetoothDevice, i, bArr, System.currentTimeMillis());
            this.mHandler.sendMessage(obtainMessage);
        }
    }

    private void checkDevice(BleDevice bleDevice) {
        String[] strArr;
        if (TextUtils.isEmpty(this.mDeviceMac) && ((strArr = this.mDeviceNames) == null || strArr.length < 1)) {
            correctDeviceAndNextStep(bleDevice);
        } else if (TextUtils.isEmpty(this.mDeviceMac) || this.mDeviceMac.equalsIgnoreCase(bleDevice.getMac())) {
            String[] strArr2 = this.mDeviceNames;
            if (strArr2 != null && strArr2.length > 0) {
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                for (String str : this.mDeviceNames) {
                    String name = bleDevice.getName();
                    if (name == null) {
                        name = "";
                    }
                    if (this.mFuzzy) {
                        if (!name.contains(str)) {
                        }
                    } else if (!name.equals(str)) {
                    }
                    atomicBoolean.set(true);
                }
                if (!atomicBoolean.get()) {
                    return;
                }
            }
            correctDeviceAndNextStep(bleDevice);
        }
    }

    private void correctDeviceAndNextStep(final BleDevice bleDevice) {
        if (this.mNeedConnect) {
            mBleDeviceList.add(bleDevice);
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    BleScanner.getInstance().stopLeScan();
                }
            });
        } else if (!this.mContinuous) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            for (BleDevice device : mBleDeviceList) {
                if (device.getDevice().equals(bleDevice.getDevice())) {
                    atomicBoolean.set(true);
                }
            }
            if (!atomicBoolean.get()) {
                mBleDeviceList.add(bleDevice);
                this.mMainHandler.post(new Runnable() {
                    public void run() {
                        BleScanPresenter.this.onScanFinished(new ArrayList(BleScanPresenter.mBleDeviceList));
                    }
                });
            }
        } else {
            this.mMainHandler.post(new Runnable() {
                public void run() {
                    BleScanPresenter.this.onScanning(bleDevice);
                }
            });
        }
    }

    public final void notifyScanStarted(final boolean z) {
        mBleDeviceList.clear();
        removeHandlerMsg();
        if (z && this.mScanTimeout > 0) {
            this.mMainHandler.postDelayed(new Runnable() {
                public void run() {
                    BleScanner.getInstance().stopLeScan();
                }
            }, this.mScanTimeout);
        }
        this.mMainHandler.post(new Runnable() {
            public void run() {
                BleLog.m42e("BLE----fastBle---blescanpresenter" + z);
                BleScanPresenter.this.onScanStarted(z);
            }
        });
    }

    public final void notifyScanStopped() {
        this.mHandling = false;
        this.mHandlerThread.quit();
        removeHandlerMsg();
        this.mMainHandler.post(new Runnable() {
            public void run() {
                BleScanPresenter.this.onScanFinished(BleScanPresenter.mBleDeviceList);
            }
        });
    }

    public final void removeHandlerMsg() {
        this.mMainHandler.removeCallbacksAndMessages((Object) null);
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }
}
