package p019no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import p019no.nordicsemi.android.dfu.BaseDfuImpl;

/* renamed from: no.nordicsemi.android.dfu.BaseButtonlessDfuImpl */
abstract class BaseButtonlessDfuImpl extends BaseDfuImpl {
    private final ButtonlessBluetoothCallback mBluetoothCallback = new ButtonlessBluetoothCallback();

    /* renamed from: no.nordicsemi.android.dfu.BaseButtonlessDfuImpl$ButtonlessBluetoothCallback */
    protected class ButtonlessBluetoothCallback extends BaseDfuImpl.BaseBluetoothGattCallback {
        protected ButtonlessBluetoothCallback() {
            super();
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            DfuBaseService dfuBaseService = BaseButtonlessDfuImpl.this.mService;
            dfuBaseService.sendLogBroadcast(5, "Notification received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
            BaseButtonlessDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
            BaseButtonlessDfuImpl.this.notifyLock();
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BaseButtonlessDfuImpl.this.mRequestCompleted = true;
            BaseButtonlessDfuImpl.this.notifyLock();
        }
    }

    BaseButtonlessDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
    }

    public BaseDfuImpl.BaseBluetoothGattCallback getGattCallback() {
        return this.mBluetoothCallback;
    }

    /* access modifiers changed from: package-private */
    public void finalize(Intent intent, boolean z, boolean z2) {
        boolean booleanExtra = intent.getBooleanExtra(DfuBaseService.EXTRA_KEEP_BOND, false);
        this.mService.refreshDeviceCache(this.mGatt, z || !booleanExtra);
        this.mService.close(this.mGatt);
        if (this.mGatt.getDevice().getBondState() == 12 && (intent.getBooleanExtra(DfuBaseService.EXTRA_RESTORE_BOND, false) || !booleanExtra)) {
            removeBond();
            this.mService.waitFor(2000);
        }
        logi("Restarting to bootloader mode");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        restartService(intent2, z2);
    }
}
