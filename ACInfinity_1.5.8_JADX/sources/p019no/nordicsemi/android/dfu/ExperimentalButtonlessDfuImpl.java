package p019no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.util.UUID;
import p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import p019no.nordicsemi.android.dfu.internal.exception.DfuException;
import p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

/* renamed from: no.nordicsemi.android.dfu.ExperimentalButtonlessDfuImpl */
class ExperimentalButtonlessDfuImpl extends ButtonlessDfuImpl {
    static final UUID DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID;
    static final UUID DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_UUID;
    static UUID EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID;
    static UUID EXPERIMENTAL_BUTTONLESS_DFU_UUID;
    private BluetoothGattCharacteristic mButtonlessDfuCharacteristic;

    /* access modifiers changed from: protected */
    public int getResponseType() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public boolean shouldScanForBootloader() {
        return true;
    }

    static {
        UUID uuid = new UUID(-8196551313441075360L, -6937650605005804976L);
        DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID = uuid;
        UUID uuid2 = new UUID(-8196551313441075360L, -6937650605005804976L);
        DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_UUID = uuid2;
        EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID = uuid;
        EXPERIMENTAL_BUTTONLESS_DFU_UUID = uuid2;
    }

    ExperimentalButtonlessDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
    }

    public boolean isClientCompatible(Intent intent, BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService service = bluetoothGatt.getService(EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID);
        if (service == null || (characteristic = service.getCharacteristic(EXPERIMENTAL_BUTTONLESS_DFU_UUID)) == null || characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG) == null) {
            return false;
        }
        this.mButtonlessDfuCharacteristic = characteristic;
        return true;
    }

    /* access modifiers changed from: protected */
    public BluetoothGattCharacteristic getButtonlessDfuCharacteristic() {
        return this.mButtonlessDfuCharacteristic;
    }

    public void performDfu(Intent intent) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        logi("Experimental buttonless service found -> SDK 12.x");
        super.performDfu(intent);
    }
}
