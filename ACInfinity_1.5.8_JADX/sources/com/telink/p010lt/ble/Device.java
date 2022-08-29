package com.telink.p010lt.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.util.Log;
import com.telink.p010lt.ble.Command;
import com.telink.p010lt.util.Arrays;
import com.telink.p010lt.util.TelinkLog;
import java.util.List;
import java.util.UUID;

/* renamed from: com.telink.lt.ble.Device */
public class Device extends Peripheral {
    public static final UUID CHARACTERISTIC_UUID_WRITE = UUID.fromString("00010203-0405-0607-0809-0a0b0c0d2b12");
    private static final int DELAY_PERIOD = 20;
    public static final int OTA_END = 65282;
    public static final int OTA_PREPARE = 65280;
    public static final int OTA_START = 65281;
    public static final int STATE_FAILURE = 0;
    public static final int STATE_PROGRESS = 2;
    public static final int STATE_SUCCESS = 1;
    public static final String TAG = "Device";
    private static final int TAG_GENERAL_ENABLE_NOTIFICATION = 14;
    private static final int TAG_GENERAL_READ = 11;
    private static final int TAG_GENERAL_READ_DESCRIPTOR = 13;
    private static final int TAG_GENERAL_WRITE = 12;
    private static final int TAG_OTA_ENABLE_NOTIFICATION = 9;
    private static final int TAG_OTA_END = 8;
    private static final int TAG_OTA_LAST = 3;
    private static final int TAG_OTA_LAST_READ = 10;
    private static final int TAG_OTA_PREPARE = 5;
    private static final int TAG_OTA_PRE_READ = 4;
    private static final int TAG_OTA_READ = 2;
    private static final int TAG_OTA_START = 7;
    private static final int TAG_OTA_WRITE = 1;
    public UUID SERVICE_UUID = UUID.fromString("00010203-0405-0607-0809-0a0b0c0d1911");
    private final CharacteristicCommandCallback mCharacteristicCommandCallback = new CharacteristicCommandCallback(this, (C36081) null);
    /* access modifiers changed from: private */
    public DescriptorCallback mDescriptorCallback;
    private DeviceStateCallback mDeviceStateCallback;
    /* access modifiers changed from: private */
    public GattOperationCallback mGattOperationCallback;
    private final OtaCommandCallback mOtaCallback = new OtaCommandCallback(this, (C36081) null);
    private final OtaPacketParser mOtaParser = new OtaPacketParser();

    /* renamed from: com.telink.lt.ble.Device$DescriptorCallback */
    public interface DescriptorCallback {
        void onDescriptorRead(Command command, Object obj);
    }

    /* renamed from: com.telink.lt.ble.Device$DeviceStateCallback */
    public interface DeviceStateCallback {
        void onConnected(Device device);

        void onDisconnected(Device device);

        void onOtaStateChanged(Device device, int i);

        void onServicesDiscovered(Device device, List<BluetoothGattService> list);
    }

    /* renamed from: com.telink.lt.ble.Device$GattOperationCallback */
    public interface GattOperationCallback {
        void onDisableNotify();

        void onEnableNotify();

        void onNotify(byte[] bArr, UUID uuid, UUID uuid2, Object obj);

        void onRead(Command command, Object obj);

        void onWrite(Command command, Object obj);
    }

    public Device(BluetoothDevice bluetoothDevice, byte[] bArr, int i) {
        super(bluetoothDevice, bArr, i);
    }

    public void setDeviceStateCallback(DeviceStateCallback deviceStateCallback) {
        this.mDeviceStateCallback = deviceStateCallback;
    }

    public void setmGattOperationCallback(GattOperationCallback gattOperationCallback) {
        this.mGattOperationCallback = gattOperationCallback;
    }

    /* access modifiers changed from: protected */
    public void onConnect() {
        super.onConnect();
        DeviceStateCallback deviceStateCallback = this.mDeviceStateCallback;
        if (deviceStateCallback != null) {
            deviceStateCallback.onConnected(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onDisconnect() {
        super.onDisconnect();
        resetOta();
        DeviceStateCallback deviceStateCallback = this.mDeviceStateCallback;
        if (deviceStateCallback != null) {
            deviceStateCallback.onDisconnected(this);
        }
    }

    /* access modifiers changed from: protected */
    public void onServicesDiscovered(List<BluetoothGattService> list) {
        super.onServicesDiscovered(list);
        DeviceStateCallback deviceStateCallback = this.mDeviceStateCallback;
        if (deviceStateCallback != null) {
            deviceStateCallback.onServicesDiscovered(this, list);
        }
    }

    /* access modifiers changed from: protected */
    public void onNotify(byte[] bArr, UUID uuid, UUID uuid2, Object obj) {
        super.onNotify(bArr, uuid, uuid2, obj);
        String str = TAG;
        Log.d(str, " onNotify ==> " + Arrays.bytesToHexString(bArr, ":"));
        GattOperationCallback gattOperationCallback = this.mGattOperationCallback;
        if (gattOperationCallback != null) {
            gattOperationCallback.onNotify(bArr, uuid, uuid2, obj);
        }
    }

    /* access modifiers changed from: protected */
    public void onEnableNotify() {
        GattOperationCallback gattOperationCallback = this.mGattOperationCallback;
        if (gattOperationCallback != null) {
            gattOperationCallback.onEnableNotify();
        }
    }

    /* access modifiers changed from: protected */
    public void onDisableNotify() {
        GattOperationCallback gattOperationCallback = this.mGattOperationCallback;
        if (gattOperationCallback != null) {
            gattOperationCallback.onDisableNotify();
        }
    }

    /* access modifiers changed from: protected */
    public void onOtaSuccess() {
        DeviceStateCallback deviceStateCallback = this.mDeviceStateCallback;
        if (deviceStateCallback != null) {
            deviceStateCallback.onOtaStateChanged(this, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void onOtaFailure() {
        DeviceStateCallback deviceStateCallback = this.mDeviceStateCallback;
        if (deviceStateCallback != null) {
            deviceStateCallback.onOtaStateChanged(this, 0);
        }
    }

    /* access modifiers changed from: protected */
    public void onOtaProgress() {
        DeviceStateCallback deviceStateCallback = this.mDeviceStateCallback;
        if (deviceStateCallback != null) {
            deviceStateCallback.onOtaStateChanged(this, 2);
        }
    }

    public void startOta(byte[] bArr) {
        TelinkLog.m265d("Start OTA");
        resetOta();
        this.mOtaParser.set(bArr);
        sendOTAPrepareCommand();
    }

    public int getOtaProgress() {
        return this.mOtaParser.getProgress();
    }

    /* access modifiers changed from: private */
    public void resetOta() {
        this.mDelayHandler.removeCallbacksAndMessages((Object) null);
        this.mOtaParser.clear();
    }

    /* access modifiers changed from: private */
    public void setOtaProgressChanged() {
        if (this.mOtaParser.invalidateProgress()) {
            onOtaProgress();
        }
    }

    private void sendOTAPrepareCommand() {
        Command newInstance = Command.newInstance();
        newInstance.serviceUUID = this.SERVICE_UUID;
        newInstance.characteristicUUID = CHARACTERISTIC_UUID_WRITE;
        newInstance.type = Command.CommandType.WRITE_NO_RESPONSE;
        newInstance.tag = 5;
        newInstance.data = new byte[]{0, -1};
        sendCommand(this.mOtaCallback, newInstance);
    }

    /* access modifiers changed from: private */
    public void sendOtaStartCommand() {
        Command newInstance = Command.newInstance();
        newInstance.serviceUUID = this.SERVICE_UUID;
        newInstance.characteristicUUID = CHARACTERISTIC_UUID_WRITE;
        newInstance.type = Command.CommandType.WRITE_NO_RESPONSE;
        newInstance.tag = 7;
        newInstance.data = new byte[]{1, -1};
        sendCommand(this.mOtaCallback, newInstance);
    }

    /* access modifiers changed from: private */
    public void sendOtaEndCommand() {
        Command newInstance = Command.newInstance();
        newInstance.serviceUUID = this.SERVICE_UUID;
        newInstance.characteristicUUID = CHARACTERISTIC_UUID_WRITE;
        newInstance.type = Command.CommandType.WRITE_NO_RESPONSE;
        newInstance.tag = 8;
        int index = this.mOtaParser.getIndex();
        byte[] bArr = new byte[8];
        bArr[0] = 2;
        bArr[1] = -1;
        bArr[2] = (byte) (index & 255);
        bArr[3] = (byte) ((index >> 8) & 255);
        int i = ~index;
        bArr[4] = (byte) (i & 255);
        bArr[5] = (byte) ((i >> 8) & 255);
        this.mOtaParser.fillCrc(bArr, this.mOtaParser.crc16(bArr));
        newInstance.data = bArr;
        sendCommand(this.mOtaCallback, newInstance);
    }

    /* access modifiers changed from: private */
    public boolean sendNextOtaPacketCommand(int i) {
        boolean z = false;
        if (this.mOtaParser.hasNextPacket()) {
            Command newInstance = Command.newInstance();
            newInstance.serviceUUID = this.SERVICE_UUID;
            newInstance.characteristicUUID = CHARACTERISTIC_UUID_WRITE;
            newInstance.type = Command.CommandType.WRITE_NO_RESPONSE;
            newInstance.data = this.mOtaParser.getNextPacket();
            if (this.mOtaParser.isLast()) {
                newInstance.tag = 3;
                z = true;
            } else {
                newInstance.tag = 1;
            }
            newInstance.delay = i;
            sendCommand(this.mOtaCallback, newInstance);
        }
        return z;
    }

    /* access modifiers changed from: private */
    public boolean validateOta() {
        int nextPacketIndex = this.mOtaParser.getNextPacketIndex() * 16;
        TelinkLog.m269i("ota onCommandSampled byte length : " + nextPacketIndex);
        if (nextPacketIndex <= 0 || nextPacketIndex % 128 != 0) {
            return false;
        }
        TelinkLog.m269i("onCommandSampled ota read packet " + this.mOtaParser.getNextPacketIndex());
        Command newInstance = Command.newInstance();
        newInstance.serviceUUID = this.SERVICE_UUID;
        newInstance.characteristicUUID = CHARACTERISTIC_UUID_WRITE;
        newInstance.type = Command.CommandType.READ;
        newInstance.tag = 2;
        sendCommand(this.mOtaCallback, newInstance);
        return true;
    }

    public boolean isNotificationEnable(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return this.mNotificationCallbacks.containsKey(generateHashKey(bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic));
    }

    public void notificationToggle(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Command newInstance = Command.newInstance();
        newInstance.serviceUUID = bluetoothGattCharacteristic.getService().getUuid();
        newInstance.characteristicUUID = bluetoothGattCharacteristic.getUuid();
        newInstance.type = !isNotificationEnable(bluetoothGattCharacteristic) ? Command.CommandType.ENABLE_NOTIFY : Command.CommandType.DISABLE_NOTIFY;
        newInstance.tag = 14;
        sendCommand(this.mCharacteristicCommandCallback, newInstance);
    }

    public void sendGeneralReadCommand(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        Command newInstance = Command.newInstance();
        newInstance.serviceUUID = bluetoothGattCharacteristic.getService().getUuid();
        newInstance.characteristicUUID = bluetoothGattCharacteristic.getUuid();
        newInstance.type = Command.CommandType.READ;
        newInstance.tag = 11;
        sendCommand(this.mCharacteristicCommandCallback, newInstance);
    }

    public void sendGeneralWriteCommand(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) {
        Command newInstance = Command.newInstance();
        newInstance.serviceUUID = bluetoothGattCharacteristic.getService().getUuid();
        newInstance.characteristicUUID = bluetoothGattCharacteristic.getUuid();
        newInstance.type = Command.CommandType.WRITE_NO_RESPONSE;
        newInstance.tag = 12;
        newInstance.data = bArr;
        sendCommand(this.mCharacteristicCommandCallback, newInstance);
    }

    public void sendDescriptorReadCommand(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        for (BluetoothGattDescriptor uuid : bluetoothGattCharacteristic.getDescriptors()) {
            Command newInstance = Command.newInstance();
            newInstance.serviceUUID = bluetoothGattCharacteristic.getService().getUuid();
            newInstance.characteristicUUID = bluetoothGattCharacteristic.getUuid();
            newInstance.descriptorUUID = uuid.getUuid();
            newInstance.type = Command.CommandType.READ_DESCRIPTOR;
            newInstance.tag = 13;
            sendCommand(this.mCharacteristicCommandCallback, newInstance);
        }
    }

    /* renamed from: com.telink.lt.ble.Device$CharacteristicCommandCallback */
    private final class CharacteristicCommandCallback implements Command.Callback {
        private CharacteristicCommandCallback() {
        }

        /* synthetic */ CharacteristicCommandCallback(Device device, C36081 r2) {
            this();
        }

        public void success(Peripheral peripheral, Command command, Object obj) {
            TelinkLog.m269i("CharacteristicCommandCallback success");
            switch (C36081.$SwitchMap$com$telink$lt$ble$Command$CommandType[command.type.ordinal()]) {
                case 1:
                    if (Device.this.mGattOperationCallback != null) {
                        Device.this.mGattOperationCallback.onRead(command, obj);
                        return;
                    }
                    return;
                case 2:
                    if (Device.this.mDescriptorCallback != null) {
                        Device.this.mDescriptorCallback.onDescriptorRead(command, obj);
                        return;
                    }
                    return;
                case 3:
                    if (Device.this.mGattOperationCallback != null) {
                        Device.this.mGattOperationCallback.onWrite(command, obj);
                        return;
                    }
                    return;
                case 4:
                    if (Device.this.mGattOperationCallback != null) {
                        Device.this.mGattOperationCallback.onWrite(command, obj);
                        return;
                    }
                    return;
                case 5:
                    if (Device.this.mGattOperationCallback != null) {
                        Device.this.mGattOperationCallback.onEnableNotify();
                        return;
                    }
                    return;
                case 6:
                    if (Device.this.mGattOperationCallback != null) {
                        Device.this.mGattOperationCallback.onDisableNotify();
                        return;
                    }
                    return;
                default:
                    return;
            }
        }

        public void error(Peripheral peripheral, Command command, String str) {
            TelinkLog.m269i("CharacteristicCommandCallback success");
        }

        public boolean timeout(Peripheral peripheral, Command command) {
            TelinkLog.m269i("CharacteristicCommandCallback success");
            return false;
        }
    }

    /* renamed from: com.telink.lt.ble.Device$1 */
    static /* synthetic */ class C36081 {
        static final /* synthetic */ int[] $SwitchMap$com$telink$lt$ble$Command$CommandType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.telink.lt.ble.Command$CommandType[] r0 = com.telink.p010lt.ble.Command.CommandType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$telink$lt$ble$Command$CommandType = r0
                com.telink.lt.ble.Command$CommandType r1 = com.telink.p010lt.ble.Command.CommandType.READ     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$telink$lt$ble$Command$CommandType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.telink.lt.ble.Command$CommandType r1 = com.telink.p010lt.ble.Command.CommandType.READ_DESCRIPTOR     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$telink$lt$ble$Command$CommandType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.telink.lt.ble.Command$CommandType r1 = com.telink.p010lt.ble.Command.CommandType.WRITE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$telink$lt$ble$Command$CommandType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.telink.lt.ble.Command$CommandType r1 = com.telink.p010lt.ble.Command.CommandType.WRITE_NO_RESPONSE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$telink$lt$ble$Command$CommandType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.telink.lt.ble.Command$CommandType r1 = com.telink.p010lt.ble.Command.CommandType.ENABLE_NOTIFY     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$telink$lt$ble$Command$CommandType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.telink.lt.ble.Command$CommandType r1 = com.telink.p010lt.ble.Command.CommandType.DISABLE_NOTIFY     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.telink.p010lt.ble.Device.C36081.<clinit>():void");
        }
    }

    /* renamed from: com.telink.lt.ble.Device$OtaCommandCallback */
    private final class OtaCommandCallback implements Command.Callback {
        private OtaCommandCallback() {
        }

        /* synthetic */ OtaCommandCallback(Device device, C36081 r2) {
            this();
        }

        public void success(Peripheral peripheral, Command command, Object obj) {
            if (command.tag.equals(4)) {
                TelinkLog.m265d("read =========> " + Arrays.bytesToHexString((byte[]) obj, "-"));
            } else if (command.tag.equals(5)) {
                Device.this.sendOtaStartCommand();
            } else if (command.tag.equals(7)) {
                boolean unused = Device.this.sendNextOtaPacketCommand(0);
            } else if (command.tag.equals(8)) {
                Device.this.resetOta();
                Device.this.setOtaProgressChanged();
                Device.this.onOtaSuccess();
            } else if (command.tag.equals(3)) {
                Device.this.sendOtaEndCommand();
            } else if (command.tag.equals(1)) {
                if (!Device.this.validateOta()) {
                    boolean unused2 = Device.this.sendNextOtaPacketCommand(0);
                }
                Device.this.setOtaProgressChanged();
            } else if (command.tag.equals(2)) {
                boolean unused3 = Device.this.sendNextOtaPacketCommand(0);
            } else {
                command.tag.equals(10);
            }
        }

        public void error(Peripheral peripheral, Command command, String str) {
            TelinkLog.m265d("error packet : " + command.tag + " errorMsg : " + str);
            if (command.tag.equals(8)) {
                Device.this.resetOta();
                Device.this.setOtaProgressChanged();
                Device.this.onOtaSuccess();
                return;
            }
            Device.this.resetOta();
            Device.this.onOtaFailure();
        }

        public boolean timeout(Peripheral peripheral, Command command) {
            TelinkLog.m265d("timeout : " + Arrays.bytesToHexString(command.data, ":"));
            if (command.tag.equals(8)) {
                Device.this.resetOta();
                Device.this.setOtaProgressChanged();
                Device.this.onOtaSuccess();
                return false;
            }
            Device.this.resetOta();
            Device.this.onOtaFailure();
            return false;
        }
    }
}
