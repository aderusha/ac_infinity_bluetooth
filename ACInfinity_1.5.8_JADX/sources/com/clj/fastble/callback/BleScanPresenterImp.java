package com.clj.fastble.callback;

import com.clj.fastble.data.BleDevice;

public interface BleScanPresenterImp {
    void onScanStarted(boolean z);

    void onScanning(BleDevice bleDevice);
}
