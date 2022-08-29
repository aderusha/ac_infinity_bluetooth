package com.clj.fastble.scan;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleScanPresenterImp;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleScanState;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BleScanner {
    /* access modifiers changed from: private */
    public BleScanPresenter mBleScanPresenter = new BleScanPresenter() {
        public void onScanStarted(boolean z) {
            BleScanPresenterImp bleScanPresenterImp = BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
            if (bleScanPresenterImp != null) {
                bleScanPresenterImp.onScanStarted(z);
            }
        }

        public void onLeScan(BleDevice bleDevice) {
            BleScanCallback bleScanCallback = (BleScanCallback) BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
            if (bleScanCallback != null) {
                bleScanCallback.onLeScan(bleDevice);
            }
        }

        public void onScanning(BleDevice bleDevice) {
            BleScanPresenterImp bleScanPresenterImp = BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
            if (bleScanPresenterImp != null) {
                bleScanPresenterImp.onScanning(bleDevice);
            }
        }

        public void onScanFinished(List<BleDevice> list) {
            BleScanCallback bleScanCallback = (BleScanCallback) BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
            if (bleScanCallback != null) {
                bleScanCallback.onScanFinished(new ArrayList(list));
            }
        }
    };
    /* access modifiers changed from: private */
    public BleScanState mBleScanState = BleScanState.STATE_IDLE;
    private final ScanCallback mScanCallback = new ScanCallback() {
        public void onScanResult(int i, ScanResult scanResult) {
            super.onScanResult(i, scanResult);
            BleScanner.this.mBleScanPresenter.onLeScan(scanResult.getDevice(), scanResult.getRssi(), scanResult.getScanRecord().getBytes());
        }

        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
        }

        public void onScanFailed(int i) {
            super.onScanFailed(i);
            BleScanState unused = BleScanner.this.mBleScanState = BleScanState.STATE_IDLE;
            BleScanner.this.mBleScanPresenter.onScanStarted(false);
        }
    };

    public static BleScanner getInstance() {
        return BleScannerHolder.sBleScanner;
    }

    private static class BleScannerHolder {
        /* access modifiers changed from: private */
        public static final BleScanner sBleScanner = new BleScanner();

        private BleScannerHolder() {
        }
    }

    public void scan(UUID[] uuidArr, String[] strArr, String str, boolean z, long j, boolean z2, boolean z3, BleScanCallback bleScanCallback) {
        startLeScan(uuidArr, strArr, str, z, z3, j, z2, bleScanCallback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void startLeScan(java.util.UUID[] r13, java.lang.String[] r14, java.lang.String r15, boolean r16, boolean r17, long r18, boolean r20, com.clj.fastble.callback.BleScanPresenterImp r21) {
        /*
            r12 = this;
            r1 = r12
            r0 = r21
            monitor-enter(r12)
            com.clj.fastble.data.BleScanState r2 = r1.mBleScanState     // Catch:{ all -> 0x006e }
            com.clj.fastble.data.BleScanState r3 = com.clj.fastble.data.BleScanState.STATE_IDLE     // Catch:{ all -> 0x006e }
            r11 = 0
            if (r2 == r3) goto L_0x0017
            java.lang.String r2 = "scan action already exists, complete the previous scan action first"
            com.clj.fastble.utils.BleLog.m44w(r2)     // Catch:{ all -> 0x006e }
            if (r0 == 0) goto L_0x0015
            r0.onScanStarted(r11)     // Catch:{ all -> 0x006e }
        L_0x0015:
            monitor-exit(r12)
            return
        L_0x0017:
            com.clj.fastble.scan.BleScanPresenter r2 = r1.mBleScanPresenter     // Catch:{ all -> 0x006e }
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r18
            r9 = r20
            r10 = r21
            r2.prepare(r3, r4, r5, r6, r7, r9, r10)     // Catch:{ all -> 0x006e }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x006e }
            r0.<init>()     // Catch:{ all -> 0x006e }
            android.bluetooth.le.ScanFilter$Builder r2 = new android.bluetooth.le.ScanFilter$Builder     // Catch:{ all -> 0x006e }
            r2.<init>()     // Catch:{ all -> 0x006e }
            r3 = 2306(0x902, float:3.231E-42)
            r4 = 1
            byte[] r5 = new byte[r4]     // Catch:{ all -> 0x006e }
            r5[r11] = r11     // Catch:{ all -> 0x006e }
            byte[] r6 = new byte[r4]     // Catch:{ all -> 0x006e }
            r6[r11] = r11     // Catch:{ all -> 0x006e }
            android.bluetooth.le.ScanFilter$Builder r2 = r2.setManufacturerData(r3, r5, r6)     // Catch:{ all -> 0x006e }
            android.bluetooth.le.ScanFilter r2 = r2.build()     // Catch:{ all -> 0x006e }
            r0.add(r2)     // Catch:{ all -> 0x006e }
            android.bluetooth.le.ScanSettings$Builder r2 = new android.bluetooth.le.ScanSettings$Builder     // Catch:{ all -> 0x006e }
            r2.<init>()     // Catch:{ all -> 0x006e }
            r3 = 2
            android.bluetooth.le.ScanSettings$Builder r2 = r2.setScanMode(r3)     // Catch:{ all -> 0x006e }
            android.bluetooth.le.ScanSettings r2 = r2.build()     // Catch:{ all -> 0x006e }
            com.clj.fastble.BleManager r3 = com.clj.fastble.BleManager.getInstance()     // Catch:{ all -> 0x006e }
            android.bluetooth.le.BluetoothLeScanner r3 = r3.getBluetoothLeScanner()     // Catch:{ all -> 0x006e }
            android.bluetooth.le.ScanCallback r5 = r1.mScanCallback     // Catch:{ all -> 0x006e }
            r3.startScan(r0, r2, r5)     // Catch:{ all -> 0x006e }
            com.clj.fastble.data.BleScanState r0 = com.clj.fastble.data.BleScanState.STATE_SCANNING     // Catch:{ all -> 0x006e }
            r1.mBleScanState = r0     // Catch:{ all -> 0x006e }
            com.clj.fastble.scan.BleScanPresenter r0 = r1.mBleScanPresenter     // Catch:{ all -> 0x006e }
            r0.notifyScanStarted(r4)     // Catch:{ all -> 0x006e }
            monitor-exit(r12)
            return
        L_0x006e:
            r0 = move-exception
            monitor-exit(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clj.fastble.scan.BleScanner.startLeScan(java.util.UUID[], java.lang.String[], java.lang.String, boolean, boolean, long, boolean, com.clj.fastble.callback.BleScanPresenterImp):void");
    }

    public synchronized void stopLeScan() {
        this.mBleScanState = BleScanState.STATE_IDLE;
        this.mBleScanPresenter.notifyScanStopped();
        if (BleManager.getInstance().isBlueEnable()) {
            BleManager.getInstance().getBluetoothLeScanner().stopScan(this.mScanCallback);
        }
    }

    public BleScanState getScanState() {
        return this.mBleScanState;
    }
}
