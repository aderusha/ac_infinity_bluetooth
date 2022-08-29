package p019no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import java.util.UUID;
import p019no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import p019no.nordicsemi.android.dfu.internal.exception.DfuException;
import p019no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

/* renamed from: no.nordicsemi.android.dfu.LegacyDfuImpl */
class LegacyDfuImpl extends BaseCustomDfuImpl {
    static final UUID DEFAULT_DFU_CONTROL_POINT_UUID;
    static final UUID DEFAULT_DFU_PACKET_UUID;
    static final UUID DEFAULT_DFU_SERVICE_UUID;
    static final UUID DEFAULT_DFU_VERSION_UUID;
    static UUID DFU_CONTROL_POINT_UUID = null;
    static UUID DFU_PACKET_UUID = null;
    static UUID DFU_SERVICE_UUID = null;
    private static final int DFU_STATUS_SUCCESS = 1;
    static UUID DFU_VERSION_UUID = null;
    private static final byte[] OP_CODE_ACTIVATE_AND_RESET = {5};
    private static final int OP_CODE_ACTIVATE_AND_RESET_KEY = 5;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS = {2};
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_COMPLETE = {2, 1};
    private static final int OP_CODE_INIT_DFU_PARAMS_KEY = 2;
    private static final byte[] OP_CODE_INIT_DFU_PARAMS_START = {2, 0};
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_KEY = 17;
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ = {8, 0, 0};
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 8;
    private static final byte[] OP_CODE_RECEIVE_FIRMWARE_IMAGE = {3};
    private static final int OP_CODE_RECEIVE_FIRMWARE_IMAGE_KEY = 3;
    private static final byte[] OP_CODE_RESET = {6};
    private static final int OP_CODE_RESET_KEY = 6;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 16;
    private static final byte[] OP_CODE_START_DFU = {1, 0};
    private static final int OP_CODE_START_DFU_KEY = 1;
    private static final byte[] OP_CODE_START_DFU_V1 = {1};
    private static final byte[] OP_CODE_VALIDATE = {4};
    private static final int OP_CODE_VALIDATE_KEY = 4;
    private final LegacyBluetoothCallback mBluetoothCallback = new LegacyBluetoothCallback();
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    /* access modifiers changed from: private */
    public boolean mImageSizeInProgress;
    private BluetoothGattCharacteristic mPacketCharacteristic;

    static {
        UUID uuid = new UUID(23296205844446L, 1523193452336828707L);
        DEFAULT_DFU_SERVICE_UUID = uuid;
        UUID uuid2 = new UUID(23300500811742L, 1523193452336828707L);
        DEFAULT_DFU_CONTROL_POINT_UUID = uuid2;
        UUID uuid3 = new UUID(23304795779038L, 1523193452336828707L);
        DEFAULT_DFU_PACKET_UUID = uuid3;
        UUID uuid4 = new UUID(23313385713630L, 1523193452336828707L);
        DEFAULT_DFU_VERSION_UUID = uuid4;
        DFU_SERVICE_UUID = uuid;
        DFU_CONTROL_POINT_UUID = uuid2;
        DFU_PACKET_UUID = uuid3;
        DFU_VERSION_UUID = uuid4;
    }

    /* renamed from: no.nordicsemi.android.dfu.LegacyDfuImpl$LegacyBluetoothCallback */
    protected class LegacyBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected LegacyBluetoothCallback() {
            super();
        }

        /* access modifiers changed from: protected */
        public void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (LegacyDfuImpl.this.mImageSizeInProgress) {
                DfuBaseService dfuBaseService = LegacyDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                boolean unused = LegacyDfuImpl.this.mImageSizeInProgress = false;
            }
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() == 17) {
                LegacyDfuImpl.this.mProgressInfo.setBytesReceived(bluetoothGattCharacteristic.getIntValue(20, 1).intValue());
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            } else if (!LegacyDfuImpl.this.mRemoteErrorOccurred) {
                if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                    LegacyDfuImpl.this.mRemoteErrorOccurred = true;
                }
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            LegacyDfuImpl.this.notifyLock();
        }
    }

    LegacyDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
    }

    public boolean isClientCompatible(Intent intent, BluetoothGatt bluetoothGatt) {
        BluetoothGattCharacteristic characteristic;
        BluetoothGattService service = bluetoothGatt.getService(DFU_SERVICE_UUID);
        if (service == null || (characteristic = service.getCharacteristic(DFU_CONTROL_POINT_UUID)) == null || characteristic.getDescriptor(CLIENT_CHARACTERISTIC_CONFIG) == null) {
            return false;
        }
        this.mControlPointCharacteristic = characteristic;
        BluetoothGattCharacteristic characteristic2 = service.getCharacteristic(DFU_PACKET_UUID);
        this.mPacketCharacteristic = characteristic2;
        if (characteristic2 != null) {
            return true;
        }
        return false;
    }

    public BaseCustomDfuImpl.BaseCustomBluetoothCallback getGattCallback() {
        return this.mBluetoothCallback;
    }

    /* access modifiers changed from: protected */
    public UUID getControlPointCharacteristicUUID() {
        return DFU_CONTROL_POINT_UUID;
    }

    /* access modifiers changed from: protected */
    public UUID getPacketCharacteristicUUID() {
        return DFU_PACKET_UUID;
    }

    /* access modifiers changed from: protected */
    public UUID getDfuServiceUUID() {
        return DFU_SERVICE_UUID;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x03d3, code lost:
        r4 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0664, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0665, code lost:
        r2 = r0;
        loge("Disconnected while sending data");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x066b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0671, code lost:
        throw new p019no.nordicsemi.android.dfu.internal.exception.RemoteDfuException("Starting DFU failed", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0672, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0673, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0674, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0676, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0677, code lost:
        r3 = r0;
        r4 = "Reset request sent";
        r2 = "Sending Reset command (Op Code = 6)";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x067c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x067d, code lost:
        r3 = r0;
        r4 = "Reset request sent";
        r2 = "Sending Reset command (Op Code = 6)";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x0682, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0683, code lost:
        r17 = "Reset request sent";
        r21 = "Sending Reset command (Op Code = 6)";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0687, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0201, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0205, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0206, code lost:
        r3 = r0;
        r4 = r17;
        r2 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x020d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x020e, code lost:
        r3 = r0;
        r4 = r17;
        r2 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0215, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0216, code lost:
        r22 = "DFU target does not support DFU v.2";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0224, code lost:
        if (r16 <= 0) goto L_0x0322;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x022a, code lost:
        r1.mRemoteErrorOccurred = false;
        r4 = r23;
        logw(r4);
        r1.mService.sendLogBroadcast(15, r4);
        r9 = r9 & -5;
        r1.mFileType = r9;
        r4 = OP_CODE_START_DFU;
        r4[1] = (byte) r9;
        r1.mProgressInfo.setTotalPart(2);
        ((p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r1.mFirmwareStream).setContentType(r9);
        r1.mService.sendLogBroadcast(1, "Sending only SD/BL");
        logi("Resending Start DFU command (Op Code = 1, Upload Mode = " + r9 + ")");
        writeOpCode(r1.mControlPointCharacteristic, r4);
        r4 = r1.mService;
        r4.sendLogBroadcast(10, "DFU Start sent (Op Code = 1, Upload Mode = " + r9 + ")");
        logi("Sending image size array to DFU Packet: [" + r14 + "b, " + r7 + "b, " + 0 + "b]");
        writeImageSize(r1.mPacketCharacteristic, r14, r7, 0);
        r3 = r1.mService;
        r3.sendLogBroadcast(10, "Firmware image size sent [" + r14 + "b, " + r7 + "b, " + 0 + "b]");
        r3 = readNotificationResponse();
        r5 = getStatusCode(r3, 1);
        r7 = r1.mService;
        r7.sendLogBroadcast(10, "Response received (Op Code = " + r3[1] + " Status = " + r5 + ")");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x030e, code lost:
        if (r5 == 2) goto L_0x0310;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0310, code lost:
        resetAndRestart(r13, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0313, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0315, code lost:
        if (r5 != 1) goto L_0x031c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0321, code lost:
        throw new p019no.nordicsemi.android.dfu.internal.exception.RemoteDfuException("Starting DFU failed", r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0322, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0323, code lost:
        throw r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0324, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0325, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x032b, code lost:
        if (r3.getErrorNumber() == 3) goto L_0x032d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x032e, code lost:
        if (r9 == 4) goto L_0x0330;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0330, code lost:
        r1.mRemoteErrorOccurred = false;
        r3 = r22;
        logw(r3);
        r1.mService.sendLogBroadcast(15, r3);
        r1.mService.sendLogBroadcast(1, "Switching to DFU v.1");
        logi("Resending Start DFU command (Op Code = 1)");
        writeOpCode(r1.mControlPointCharacteristic, OP_CODE_START_DFU_V1);
        r1.mService.sendLogBroadcast(10, "DFU Start sent (Op Code = 1)");
        logi("Sending application image size to DFU Packet: " + r1.mImageSizeInBytes + " bytes");
        writeImageSize(r1.mPacketCharacteristic, r1.mImageSizeInBytes);
        r3 = r1.mService;
        r3.sendLogBroadcast(10, "Firmware image size sent (" + r1.mImageSizeInBytes + " bytes)");
        r3 = readNotificationResponse();
        r5 = getStatusCode(r3, 1);
        r7 = r1.mService;
        r8 = new java.lang.StringBuilder();
        r8.append("Response received (Op Code = ");
        r8.append(r3[1]);
        r3 = r18;
        r8.append(r3);
        r8.append(r5);
        r8.append(")");
        r7.sendLogBroadcast(10, r8.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x03ca, code lost:
        if (r5 == 2) goto L_0x03cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x03cc, code lost:
        resetAndRestart(r13, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x03cf, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x03d1, code lost:
        if (r5 == 1) goto L_0x03d3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x03d8 A[Catch:{ DeviceDisconnectedException -> 0x0664, RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x048c A[Catch:{ DeviceDisconnectedException -> 0x0664, RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x049e A[Catch:{ DeviceDisconnectedException -> 0x0664, RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x056c A[Catch:{ DeviceDisconnectedException -> 0x0664, RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x065c A[Catch:{ DeviceDisconnectedException -> 0x0664, RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0676 A[ExcHandler: UnknownResponseException (r0v5 'e' no.nordicsemi.android.dfu.internal.exception.UnknownResponseException A[CUSTOM_DECLARE]), Splitter:B:14:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x067c A[ExcHandler: UploadAbortedException (r0v4 'e' no.nordicsemi.android.dfu.internal.exception.UploadAbortedException A[CUSTOM_DECLARE]), Splitter:B:14:0x0098] */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x0682 A[ExcHandler: RemoteDfuException (e no.nordicsemi.android.dfu.internal.exception.RemoteDfuException), Splitter:B:12:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0205 A[ExcHandler: UnknownResponseException (r0v13 'e' no.nordicsemi.android.dfu.internal.exception.UnknownResponseException A[CUSTOM_DECLARE]), Splitter:B:44:0x012e] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x020d A[ExcHandler: UploadAbortedException (r0v12 'e' no.nordicsemi.android.dfu.internal.exception.UploadAbortedException A[CUSTOM_DECLARE]), Splitter:B:44:0x012e] */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0224 A[Catch:{ DeviceDisconnectedException -> 0x0664, RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0323 A[Catch:{ DeviceDisconnectedException -> 0x0664, RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void performDfu(android.content.Intent r27) throws p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r26 = this;
            r1 = r26
            r2 = r27
            java.lang.String r3 = "DFU Start sent (Op Code = 1, Upload Mode = "
            java.lang.String r4 = "DFU target does not support DFU v.2"
            java.lang.String r5 = "DFU target does not support (SD/BL)+App update"
            java.lang.String r6 = "Starting DFU failed"
            java.lang.String r7 = "Reset request sent"
            java.lang.String r8 = "Sending Reset command (Op Code = 6)"
            java.lang.String r9 = ", Status = "
            java.lang.String r10 = "Response received (Op Code = "
            java.lang.String r11 = "b, "
            java.lang.String r12 = ")"
            java.lang.String r13 = "Legacy DFU bootloader found"
            r1.logw(r13)
            no.nordicsemi.android.dfu.DfuProgressInfo r13 = r1.mProgressInfo
            r14 = -2
            r13.setProgress(r14)
            java.lang.String r13 = "no.nordicsemi.android.dfu.extra.EXTRA_MTU"
            boolean r14 = r2.hasExtra(r13)
            if (r14 == 0) goto L_0x004e
            int r14 = android.os.Build.VERSION.SDK_INT
            r15 = 21
            if (r14 < r15) goto L_0x004e
            r14 = 517(0x205, float:7.24E-43)
            int r13 = r2.getIntExtra(r13, r14)
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "Requesting MTU = "
            r14.append(r15)
            r14.append(r13)
            java.lang.String r14 = r14.toString()
            r1.logi(r14)
            r1.requestMtu(r13)
        L_0x004e:
            no.nordicsemi.android.dfu.DfuBaseService r13 = r1.mService
            r14 = 1000(0x3e8, double:4.94E-321)
            r13.waitFor(r14)
            android.bluetooth.BluetoothGatt r13 = r1.mGatt
            java.util.UUID r14 = DFU_SERVICE_UUID
            android.bluetooth.BluetoothGattService r14 = r13.getService(r14)
            java.util.UUID r15 = DFU_VERSION_UUID
            android.bluetooth.BluetoothGattCharacteristic r14 = r14.getCharacteristic(r15)
            int r14 = r1.readVersion(r14)
            r15 = 5
            r18 = r9
            r9 = 20
            if (r14 < r15) goto L_0x0095
            java.io.InputStream r15 = r1.mInitPacketStream
            if (r15 != 0) goto L_0x0095
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Init packet not set for the DFU Bootloader version "
            r2.append(r3)
            r2.append(r14)
            java.lang.String r2 = r2.toString()
            r1.logw(r2)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            java.lang.String r3 = "The Init packet is required by this version DFU Bootloader"
            r2.sendLogBroadcast(r9, r3)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r3 = 4107(0x100b, float:5.755E-42)
            r2.terminateConnection(r13, r3)
            return
        L_0x0095:
            r15 = 1
            android.bluetooth.BluetoothGattCharacteristic r9 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0712, UnknownResponseException -> 0x06e3, RemoteDfuException -> 0x0682 }
            r1.enableCCCD(r9, r15)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x0682 }
            no.nordicsemi.android.dfu.DfuBaseService r9 = r1.mService     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x0682 }
            java.lang.String r15 = "Notifications enabled"
            r20 = r14
            r14 = 10
            r9.sendLogBroadcast(r14, r15)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x0682 }
            no.nordicsemi.android.dfu.DfuBaseService r9 = r1.mService     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x0682 }
            r14 = 1000(0x3e8, double:4.94E-321)
            r9.waitFor(r14)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x0682 }
            int r9 = r1.mFileType     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x0682 }
            r14 = r9 & 1
            if (r14 <= 0) goto L_0x00be
            int r14 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            goto L_0x00bf
        L_0x00b6:
            r0 = move-exception
            r2 = r0
            r17 = r7
            r21 = r8
            goto L_0x0688
        L_0x00be:
            r14 = 0
        L_0x00bf:
            r15 = r9 & 2
            if (r15 <= 0) goto L_0x00c6
            int r15 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            goto L_0x00c7
        L_0x00c6:
            r15 = 0
        L_0x00c7:
            r16 = r9 & 4
            if (r16 <= 0) goto L_0x00d2
            r17 = r14
            int r14 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            r21 = r14
            goto L_0x00d6
        L_0x00d2:
            r17 = r14
            r21 = 0
        L_0x00d6:
            java.io.InputStream r14 = r1.mFirmwareStream     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x0682 }
            boolean r14 = r14 instanceof p019no.nordicsemi.android.dfu.internal.ArchiveInputStream     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x0682 }
            if (r14 == 0) goto L_0x0125
            java.io.InputStream r14 = r1.mFirmwareStream     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r14 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r14     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            boolean r15 = r14.isSecureDfuRequired()     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            if (r15 == 0) goto L_0x010d
            java.lang.String r2 = "Secure DFU is required to upload selected firmware"
            r1.loge(r2)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            java.lang.String r3 = "The device does not support given firmware."
            r4 = 20
            r2.sendLogBroadcast(r4, r3)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            r1.logi(r8)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            android.bluetooth.BluetoothGattCharacteristic r2 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            byte[] r3 = OP_CODE_RESET     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            r1.writeOpCode(r2, r3)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            r3 = 10
            r2.sendLogBroadcast(r3, r7)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            r3 = 4099(0x1003, float:5.744E-42)
            r2.terminateConnection(r13, r3)     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            return
        L_0x010d:
            int r15 = r14.softDeviceImageSize()     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            int r17 = r14.bootloaderImageSize()     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            int r14 = r14.applicationImageSize()     // Catch:{ UploadAbortedException -> 0x067c, UnknownResponseException -> 0x0676, RemoteDfuException -> 0x00b6 }
            r24 = r17
            r17 = r7
            r7 = r24
            r25 = r15
            r15 = r14
            r14 = r25
            goto L_0x012c
        L_0x0125:
            r14 = r17
            r17 = r7
            r7 = r15
            r15 = r21
        L_0x012c:
            r21 = r8
            byte[] r8 = OP_CODE_START_DFU     // Catch:{ RemoteDfuException -> 0x0215, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r22 = r4
            byte r4 = (byte) r9
            r19 = 1
            r8[r19] = r4     // Catch:{ RemoteDfuException -> 0x0203, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0203, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.<init>()     // Catch:{ RemoteDfuException -> 0x0203, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r23 = r5
            java.lang.String r5 = "Sending Start DFU command (Op Code = 1, Upload Mode = "
            r4.append(r5)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r9)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r12)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r4 = r4.toString()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r1.logi(r4)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mControlPointCharacteristic     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r1.writeOpCode(r4, r8)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.<init>()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r3)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r9)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r12)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r5 = r5.toString()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8 = 10
            r4.sendLogBroadcast(r8, r5)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.<init>()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r5 = "Sending image size array to DFU Packet ("
            r4.append(r5)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r14)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r11)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r7)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r11)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r15)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r5 = "b)"
            r4.append(r5)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r4 = r4.toString()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r1.logi(r4)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mPacketCharacteristic     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r1.writeImageSize(r4, r14, r7, r15)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.<init>()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r8 = "Firmware image size sent ("
            r5.append(r8)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r14)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r11)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r7)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r11)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r15)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r8 = "b)"
            r5.append(r8)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r5 = r5.toString()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8 = 10
            r4.sendLogBroadcast(r8, r5)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            byte[] r4 = r26.readNotificationResponse()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5 = 1
            int r8 = r1.getStatusCode(r4, r5)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuBaseService r15 = r1.mService     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.<init>()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r10)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r19 = 1
            byte r4 = r4[r19]     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r4)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r4 = " Status = "
            r5.append(r4)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r8)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r12)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r4 = r5.toString()     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5 = 10
            r15.sendLogBroadcast(r5, r4)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4 = 2
            if (r8 != r4) goto L_0x01f6
            r1.resetAndRestart(r13, r2)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            return
        L_0x01f6:
            r4 = 1
            if (r8 != r4) goto L_0x01fb
            goto L_0x0317
        L_0x01fb:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r4 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.<init>(r6, r8)     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            throw r4     // Catch:{ RemoteDfuException -> 0x0201, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
        L_0x0201:
            r0 = move-exception
            goto L_0x021a
        L_0x0203:
            r0 = move-exception
            goto L_0x0218
        L_0x0205:
            r0 = move-exception
            r3 = r0
            r4 = r17
            r2 = r21
            goto L_0x06e7
        L_0x020d:
            r0 = move-exception
            r3 = r0
            r4 = r17
            r2 = r21
            goto L_0x0716
        L_0x0215:
            r0 = move-exception
            r22 = r4
        L_0x0218:
            r23 = r5
        L_0x021a:
            r4 = r0
            r5 = 15
            int r8 = r4.getErrorNumber()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r15 = 3
            if (r8 != r15) goto L_0x0323
            if (r16 <= 0) goto L_0x0322
            r8 = r9 & 3
            if (r8 <= 0) goto L_0x0322
            r8 = 0
            r1.mRemoteErrorOccurred = r8     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4 = r23
            r1.logw(r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8.sendLogBroadcast(r5, r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r9 = r9 & -5
            r1.mFileType = r9     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            byte[] r4 = OP_CODE_START_DFU     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            byte r8 = (byte) r9     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r15 = 1
            r4[r15] = r8     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r1.mProgressInfo     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r15 = 2
            r8.setTotalPart(r15)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.io.InputStream r8 = r1.mFirmwareStream     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r8 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8.setContentType(r9)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r15 = "Sending only SD/BL"
            r5 = 1
            r8.sendLogBroadcast(r5, r15)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.<init>()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r8 = "Resending Start DFU command (Op Code = 1, Upload Mode = "
            r5.append(r8)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r9)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r12)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r5 = r5.toString()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r1.logi(r5)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            android.bluetooth.BluetoothGattCharacteristic r5 = r1.mControlPointCharacteristic     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r1.writeOpCode(r5, r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.<init>()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r3)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r9)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5.append(r12)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r3 = r5.toString()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5 = 10
            r4.sendLogBroadcast(r5, r3)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r3.<init>()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r4 = "Sending image size array to DFU Packet: ["
            r3.append(r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r3.append(r14)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r3.append(r11)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r3.append(r7)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r3.append(r11)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4 = 0
            r3.append(r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r4 = "b]"
            r3.append(r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r3 = r3.toString()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r1.logi(r3)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            android.bluetooth.BluetoothGattCharacteristic r3 = r1.mPacketCharacteristic     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4 = 0
            r1.writeImageSize(r3, r14, r7, r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.<init>()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r5 = "Firmware image size sent ["
            r4.append(r5)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r14)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r11)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r7)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4.append(r11)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5 = 0
            r4.append(r5)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r5 = "b]"
            r4.append(r5)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r4 = r4.toString()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r5 = 10
            r3.sendLogBroadcast(r5, r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            byte[] r3 = r26.readNotificationResponse()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4 = 1
            int r5 = r1.getStatusCode(r3, r4)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8.<init>()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8.append(r10)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            byte r3 = r3[r4]     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8.append(r3)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r3 = " Status = "
            r8.append(r3)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8.append(r5)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r8.append(r12)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            java.lang.String r3 = r8.toString()     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r4 = 10
            r7.sendLogBroadcast(r4, r3)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r3 = 2
            if (r5 != r3) goto L_0x0314
            r1.resetAndRestart(r13, r2)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            return
        L_0x0314:
            r3 = 1
            if (r5 != r3) goto L_0x031c
        L_0x0317:
            r3 = r18
            r4 = 1
            goto L_0x03d4
        L_0x031c:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r3 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            r3.<init>(r6, r5)     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
            throw r3     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
        L_0x0322:
            throw r4     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
        L_0x0323:
            throw r4     // Catch:{ RemoteDfuException -> 0x0324, UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205 }
        L_0x0324:
            r0 = move-exception
            r3 = r0
            int r4 = r3.getErrorNumber()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5 = 3
            if (r4 != r5) goto L_0x0673
            r4 = 4
            if (r9 != r4) goto L_0x0672
            r4 = 0
            r1.mRemoteErrorOccurred = r4     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r3 = r22
            r1.logw(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5 = 15
            r4.sendLogBroadcast(r5, r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = "Switching to DFU v.1"
            r5 = 1
            r3.sendLogBroadcast(r5, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r3 = "Resending Start DFU command (Op Code = 1)"
            r1.logi(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r3 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r4 = OP_CODE_START_DFU_V1     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeOpCode(r3, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = "DFU Start sent (Op Code = 1)"
            r5 = 10
            r3.sendLogBroadcast(r5, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r3.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = "Sending application image size to DFU Packet: "
            r3.append(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            int r4 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r3.append(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = " bytes"
            r3.append(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r3 = r3.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.logi(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r3 = r1.mPacketCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            int r4 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeImageSize(r3, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r4.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = "Firmware image size sent ("
            r4.append(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            int r5 = r1.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r4.append(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = " bytes)"
            r4.append(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = r4.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5 = 10
            r3.sendLogBroadcast(r5, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r3 = r26.readNotificationResponse()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r4 = 1
            int r5 = r1.getStatusCode(r3, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r10)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte r3 = r3[r4]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r3 = r18
            r8.append(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r12)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = r8.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8 = 10
            r7.sendLogBroadcast(r8, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r4 = 2
            if (r5 != r4) goto L_0x03d0
            r1.resetAndRestart(r13, r2)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            return
        L_0x03d0:
            r4 = 1
            if (r5 != r4) goto L_0x066c
            r4 = 0
        L_0x03d4:
            java.io.InputStream r5 = r1.mInitPacketStream     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            if (r5 == 0) goto L_0x048a
            no.nordicsemi.android.dfu.DfuBaseService r5 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = "Writing Initialize DFU Parameters..."
            r7 = 10
            r5.sendLogBroadcast(r7, r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            if (r4 == 0) goto L_0x0426
            java.lang.String r5 = "Sending the Initialize DFU Parameters START (Op Code = 2, Value = 0)"
            r1.logi(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r5 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r6 = OP_CODE_INIT_DFU_PARAMS_START     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeOpCode(r5, r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = "Sending "
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            int r6 = r1.mInitPacketSizeInBytes     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = " bytes of init packet"
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = r5.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.logi(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r5 = r1.mPacketCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6 = 0
            r1.writeInitData(r5, r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = "Sending the Initialize DFU Parameters COMPLETE (Op Code = 2, Value = 1)"
            r1.logi(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r5 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r6 = OP_CODE_INIT_DFU_PARAMS_COMPLETE     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeOpCode(r5, r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r5 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = "Initialize DFU Parameters completed"
            r7 = 10
            r5.sendLogBroadcast(r7, r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            goto L_0x0453
        L_0x0426:
            java.lang.String r5 = "Sending the Initialize DFU Parameters (Op Code = 2)"
            r1.logi(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r5 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r6 = OP_CODE_INIT_DFU_PARAMS     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeOpCode(r5, r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = "Sending "
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            int r6 = r1.mInitPacketSizeInBytes     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = " bytes of init packet"
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = r5.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.logi(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r5 = r1.mPacketCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6 = 0
            r1.writeInitData(r5, r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x0453:
            byte[] r5 = r26.readNotificationResponse()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6 = 2
            int r7 = r1.getStatusCode(r5, r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r10)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r9 = 1
            byte r5 = r5[r9]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r7)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r12)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = r8.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8 = 10
            r6.sendLogBroadcast(r8, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5 = 1
            if (r7 != r5) goto L_0x0482
            goto L_0x048a
        L_0x0482:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r3 = "Device returned error after sending init packet"
            r2.<init>(r3, r7)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            throw r2     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x048a:
            if (r4 != 0) goto L_0x049a
            int r4 = r1.mPacketsBeforeNotification     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            if (r4 <= 0) goto L_0x0497
            int r4 = r1.mPacketsBeforeNotification     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5 = 10
            if (r4 > r5) goto L_0x0497
            goto L_0x049a
        L_0x0497:
            r4 = 10
            goto L_0x049c
        L_0x049a:
            int r4 = r1.mPacketsBeforeNotification     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x049c:
            if (r4 <= 0) goto L_0x04dc
            r1.mPacketsBeforeNotification = r4     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = "Sending the number of packets before notifications (Op Code = 8, Value = "
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.append(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.append(r12)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = r5.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.logi(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r5 = OP_CODE_PACKET_RECEIPT_NOTIF_REQ     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.setNumberOfPackets(r5, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r6 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeOpCode(r6, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r5 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r7 = "Packet Receipt Notif Req (Op Code = 8) sent (Value = "
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.append(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.append(r12)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = r6.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6 = 10
            r5.sendLogBroadcast(r6, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x04dc:
            java.lang.String r4 = "Sending Receive Firmware Image request (Op Code = 3)"
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r5 = OP_CODE_RECEIVE_FIRMWARE_IMAGE     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeOpCode(r4, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = "Receive Firmware Image request sent"
            r6 = 10
            r4.sendLogBroadcast(r6, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            long r4 = android.os.SystemClock.elapsedRealtime()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuProgressInfo r6 = r1.mProgressInfo     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7 = 0
            r6.setBytesSent(r7)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = "Uploading firmware..."
            r1.logi(r6)     // Catch:{ DeviceDisconnectedException -> 0x0664 }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ DeviceDisconnectedException -> 0x0664 }
            java.lang.String r7 = "Uploading firmware..."
            r8 = 10
            r6.sendLogBroadcast(r8, r7)     // Catch:{ DeviceDisconnectedException -> 0x0664 }
            android.bluetooth.BluetoothGattCharacteristic r6 = r1.mPacketCharacteristic     // Catch:{ DeviceDisconnectedException -> 0x0664 }
            r1.uploadFirmwareImage(r6)     // Catch:{ DeviceDisconnectedException -> 0x0664 }
            long r6 = android.os.SystemClock.elapsedRealtime()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r8 = r26.readNotificationResponse()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r9 = 3
            int r9 = r1.getStatusCode(r8, r9)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r11.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r11.append(r10)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14 = 0
            byte r15 = r8[r14]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r11.append(r15)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r14 = ", Req Op Code = "
            r11.append(r14)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14 = 1
            byte r15 = r8[r14]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r11.append(r15)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r11.append(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14 = 2
            byte r15 = r8[r14]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r11.append(r15)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r11.append(r12)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r11 = r11.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.logi(r11)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r11 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r14 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14.append(r10)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r15 = 1
            byte r8 = r8[r15]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14.append(r8)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14.append(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14.append(r9)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14.append(r12)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r8 = r14.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r14 = 10
            r11.sendLogBroadcast(r14, r8)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8 = 1
            if (r9 != r8) goto L_0x065c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r9 = "Transfer of "
            r8.append(r9)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuProgressInfo r9 = r1.mProgressInfo     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            int r9 = r9.getBytesSent()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8.append(r9)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r9 = " bytes has taken "
            r8.append(r9)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            long r6 = r6 - r4
            r8.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = " ms"
            r8.append(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = r8.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r8 = "Upload completed in "
            r5.append(r8)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = " ms"
            r5.append(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = r5.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6 = 10
            r4.sendLogBroadcast(r6, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = "Sending Validate request (Op Code = 4)"
            r1.logi(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r4 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r5 = OP_CODE_VALIDATE     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeOpCode(r4, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r5 = "Validate request sent"
            r6 = 10
            r4.sendLogBroadcast(r6, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r4 = r26.readNotificationResponse()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r5 = 4
            int r5 = r1.getStatusCode(r4, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.append(r10)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7 = 0
            byte r8 = r4[r7]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.append(r8)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r7 = ", Req Op Code = "
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7 = 1
            byte r8 = r4[r7]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.append(r8)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.append(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7 = 2
            byte r7 = r4[r7]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.append(r7)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r6.append(r12)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r6 = r6.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.logi(r6)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7.<init>()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7.append(r10)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r8 = 1
            byte r4 = r4[r8]     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7.append(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7.append(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7.append(r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r7.append(r12)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r3 = r7.toString()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r4 = 10
            r6.sendLogBroadcast(r4, r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r3 = 1
            if (r5 != r3) goto L_0x0654
            no.nordicsemi.android.dfu.DfuProgressInfo r3 = r1.mProgressInfo     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r4 = -5
            r3.setProgress(r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r3 = "Sending Activate and Reset request (Op Code = 5)"
            r1.logi(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            android.bluetooth.BluetoothGattCharacteristic r3 = r1.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            byte[] r4 = OP_CODE_ACTIVATE_AND_RESET     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r1.writeOpCode(r3, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = "Activate and Reset request sent"
            r5 = 10
            r3.sendLogBroadcast(r5, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r3.waitUntilDisconnected()     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r4 = "Disconnected by the remote device"
            r5 = 5
            r3.sendLogBroadcast(r5, r4)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r3 = r20
            if (r3 != r5) goto L_0x064e
            r3 = 1
            goto L_0x064f
        L_0x064e:
            r3 = 0
        L_0x064f:
            r1.finalize(r2, r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            goto L_0x0711
        L_0x0654:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r3 = "Device returned validation error"
            r2.<init>(r3, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            throw r2     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x065c:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            java.lang.String r3 = "Device returned error after sending file"
            r2.<init>(r3, r9)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            throw r2     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x0664:
            r0 = move-exception
            r2 = r0
            java.lang.String r3 = "Disconnected while sending data"
            r1.loge(r3)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            throw r2     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x066c:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r2 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            r2.<init>(r6, r5)     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
            throw r2     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x0672:
            throw r3     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x0673:
            throw r3     // Catch:{ UploadAbortedException -> 0x020d, UnknownResponseException -> 0x0205, RemoteDfuException -> 0x0674 }
        L_0x0674:
            r0 = move-exception
            goto L_0x0687
        L_0x0676:
            r0 = move-exception
            r3 = r0
            r4 = r7
            r2 = r8
            goto L_0x06e7
        L_0x067c:
            r0 = move-exception
            r3 = r0
            r4 = r7
            r2 = r8
            goto L_0x0716
        L_0x0682:
            r0 = move-exception
            r17 = r7
            r21 = r8
        L_0x0687:
            r2 = r0
        L_0x0688:
            int r3 = r2.getErrorNumber()
            r3 = r3 | 256(0x100, float:3.59E-43)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r2 = r2.getMessage()
            r4.append(r2)
            java.lang.String r2 = ": "
            r4.append(r2)
            java.lang.String r2 = p019no.nordicsemi.android.error.LegacyDfuError.parse(r3)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r1.loge(r2)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            java.util.Locale r4 = java.util.Locale.US
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r6 = p019no.nordicsemi.android.error.LegacyDfuError.parse(r3)
            r7 = 0
            r5[r7] = r6
            java.lang.String r6 = "Remote DFU error: %s"
            java.lang.String r4 = java.lang.String.format(r4, r6, r5)
            r5 = 20
            r2.sendLogBroadcast(r5, r4)
            r2 = r21
            r1.logi(r2)
            android.bluetooth.BluetoothGattCharacteristic r2 = r1.mControlPointCharacteristic
            byte[] r4 = OP_CODE_RESET
            r1.writeOpCode(r2, r4)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r4 = r17
            r5 = 10
            r2.sendLogBroadcast(r5, r4)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r3 = r3 | 8192(0x2000, float:1.14794E-41)
            r2.terminateConnection(r13, r3)
            goto L_0x0711
        L_0x06e3:
            r0 = move-exception
            r4 = r7
            r2 = r8
            r3 = r0
        L_0x06e7:
            java.lang.String r5 = r3.getMessage()
            r1.loge(r5)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r1.mService
            java.lang.String r3 = r3.getMessage()
            r6 = 20
            r5.sendLogBroadcast(r6, r3)
            r1.logi(r2)
            android.bluetooth.BluetoothGattCharacteristic r2 = r1.mControlPointCharacteristic
            byte[] r3 = OP_CODE_RESET
            r1.writeOpCode(r2, r3)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r3 = 10
            r2.sendLogBroadcast(r3, r4)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r3 = 4104(0x1008, float:5.751E-42)
            r2.terminateConnection(r13, r3)
        L_0x0711:
            return
        L_0x0712:
            r0 = move-exception
            r4 = r7
            r2 = r8
            r3 = r0
        L_0x0716:
            r1.logi(r2)
            r2 = 0
            r1.mAborted = r2
            android.bluetooth.BluetoothGattCharacteristic r2 = r1.mControlPointCharacteristic
            byte[] r5 = OP_CODE_RESET
            r1.writeOpCode(r2, r5)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r1.mService
            r5 = 10
            r2.sendLogBroadcast(r5, r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.LegacyDfuImpl.performDfu(android.content.Intent):void");
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        if (bArr != null && bArr.length == 3 && bArr[0] == 16 && bArr[1] == i && bArr[2] >= 1 && bArr[2] <= 6) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 16, i);
    }

    private int readVersion(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic.getIntValue(18, 0).intValue();
        }
        return 0;
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        boolean z = false;
        if (bArr[0] == 6 || bArr[0] == 5) {
            z = true;
        }
        writeOpCode(bluetoothGattCharacteristic, bArr, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeImageSize(android.bluetooth.BluetoothGattCharacteristic r5, int r6) throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r4 = this;
            r0 = 0
            r4.mReceivedData = r0
            r0 = 0
            r4.mError = r0
            r1 = 1
            r4.mImageSizeInProgress = r1
            r5.setWriteType(r1)
            r2 = 4
            byte[] r2 = new byte[r2]
            r5.setValue(r2)
            r2 = 20
            r5.setValue(r6, r2, r0)
            no.nordicsemi.android.dfu.DfuBaseService r6 = r4.mService
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Writing to characteristic "
            r2.append(r3)
            java.util.UUID r3 = r5.getUuid()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r6.sendLogBroadcast(r1, r2)
            no.nordicsemi.android.dfu.DfuBaseService r6 = r4.mService
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "gatt.writeCharacteristic("
            r1.append(r2)
            java.util.UUID r2 = r5.getUuid()
            r1.append(r2)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r6.sendLogBroadcast(r0, r1)
            android.bluetooth.BluetoothGatt r6 = r4.mGatt
            r6.writeCharacteristic(r5)
            java.lang.Object r5 = r4.mLock     // Catch:{ InterruptedException -> 0x0077 }
            monitor-enter(r5)     // Catch:{ InterruptedException -> 0x0077 }
        L_0x0058:
            boolean r6 = r4.mImageSizeInProgress     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x0068
            boolean r6 = r4.mConnected     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x0068
            int r6 = r4.mError     // Catch:{ all -> 0x0074 }
            if (r6 != 0) goto L_0x0068
            boolean r6 = r4.mAborted     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x006c
        L_0x0068:
            boolean r6 = r4.mPaused     // Catch:{ all -> 0x0074 }
            if (r6 == 0) goto L_0x0072
        L_0x006c:
            java.lang.Object r6 = r4.mLock     // Catch:{ all -> 0x0074 }
            r6.wait()     // Catch:{ all -> 0x0074 }
            goto L_0x0058
        L_0x0072:
            monitor-exit(r5)     // Catch:{ all -> 0x0074 }
            goto L_0x007d
        L_0x0074:
            r6 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0074 }
            throw r6     // Catch:{ InterruptedException -> 0x0077 }
        L_0x0077:
            r5 = move-exception
            java.lang.String r6 = "Sleeping interrupted"
            r4.loge(r6, r5)
        L_0x007d:
            boolean r5 = r4.mAborted
            if (r5 != 0) goto L_0x009c
            boolean r5 = r4.mConnected
            if (r5 == 0) goto L_0x0094
            int r5 = r4.mError
            if (r5 != 0) goto L_0x008a
            return
        L_0x008a:
            no.nordicsemi.android.dfu.internal.exception.DfuException r5 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.String r6 = "Unable to write Image Size"
            int r0 = r4.mError
            r5.<init>(r6, r0)
            throw r5
        L_0x0094:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r5 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r6 = "Unable to write Image Size: device disconnected"
            r5.<init>(r6)
            throw r5
        L_0x009c:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r5 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.LegacyDfuImpl.writeImageSize(android.bluetooth.BluetoothGattCharacteristic, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x008b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00a6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeImageSize(android.bluetooth.BluetoothGattCharacteristic r4, int r5, int r6, int r7) throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r3 = this;
            r0 = 0
            r3.mReceivedData = r0
            r0 = 0
            r3.mError = r0
            r1 = 1
            r3.mImageSizeInProgress = r1
            r4.setWriteType(r1)
            r2 = 12
            byte[] r2 = new byte[r2]
            r4.setValue(r2)
            r2 = 20
            r4.setValue(r5, r2, r0)
            r5 = 4
            r4.setValue(r6, r2, r5)
            r5 = 8
            r4.setValue(r7, r2, r5)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r3.mService
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Writing to characteristic "
            r6.append(r7)
            java.util.UUID r7 = r4.getUuid()
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.sendLogBroadcast(r1, r6)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r3.mService
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "gatt.writeCharacteristic("
            r6.append(r7)
            java.util.UUID r7 = r4.getUuid()
            r6.append(r7)
            java.lang.String r7 = ")"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.sendLogBroadcast(r0, r6)
            android.bluetooth.BluetoothGatt r5 = r3.mGatt
            r5.writeCharacteristic(r4)
            java.lang.Object r4 = r3.mLock     // Catch:{ InterruptedException -> 0x0081 }
            monitor-enter(r4)     // Catch:{ InterruptedException -> 0x0081 }
        L_0x0062:
            boolean r5 = r3.mImageSizeInProgress     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0072
            boolean r5 = r3.mConnected     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0072
            int r5 = r3.mError     // Catch:{ all -> 0x007e }
            if (r5 != 0) goto L_0x0072
            boolean r5 = r3.mAborted     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x0076
        L_0x0072:
            boolean r5 = r3.mPaused     // Catch:{ all -> 0x007e }
            if (r5 == 0) goto L_0x007c
        L_0x0076:
            java.lang.Object r5 = r3.mLock     // Catch:{ all -> 0x007e }
            r5.wait()     // Catch:{ all -> 0x007e }
            goto L_0x0062
        L_0x007c:
            monitor-exit(r4)     // Catch:{ all -> 0x007e }
            goto L_0x0087
        L_0x007e:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x007e }
            throw r5     // Catch:{ InterruptedException -> 0x0081 }
        L_0x0081:
            r4 = move-exception
            java.lang.String r5 = "Sleeping interrupted"
            r3.loge(r5, r4)
        L_0x0087:
            boolean r4 = r3.mAborted
            if (r4 != 0) goto L_0x00a6
            boolean r4 = r3.mConnected
            if (r4 == 0) goto L_0x009e
            int r4 = r3.mError
            if (r4 != 0) goto L_0x0094
            return
        L_0x0094:
            no.nordicsemi.android.dfu.internal.exception.DfuException r4 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.String r5 = "Unable to write Image Sizes"
            int r6 = r3.mError
            r4.<init>(r5, r6)
            throw r4
        L_0x009e:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r4 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r5 = "Unable to write Image Sizes: device disconnected"
            r4.<init>(r5)
            throw r4
        L_0x00a6:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r4 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.LegacyDfuImpl.writeImageSize(android.bluetooth.BluetoothGattCharacteristic, int, int, int):void");
    }

    private void resetAndRestart(BluetoothGatt bluetoothGatt, Intent intent) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        this.mService.sendLogBroadcast(15, "Last upload interrupted. Restarting device...");
        this.mProgressInfo.setProgress(-5);
        logi("Sending Reset command (Op Code = 6)");
        writeOpCode(this.mControlPointCharacteristic, OP_CODE_RESET);
        this.mService.sendLogBroadcast(10, "Reset request sent");
        this.mService.waitUntilDisconnected();
        this.mService.sendLogBroadcast(5, "Disconnected by the remote device");
        BluetoothGattService service = bluetoothGatt.getService(GENERIC_ATTRIBUTE_SERVICE_UUID);
        this.mService.refreshDeviceCache(bluetoothGatt, !((service == null || service.getCharacteristic(SERVICE_CHANGED_UUID) == null) ? false : true));
        this.mService.close(bluetoothGatt);
        logi("Restarting the service");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        restartService(intent2, false);
    }
}
