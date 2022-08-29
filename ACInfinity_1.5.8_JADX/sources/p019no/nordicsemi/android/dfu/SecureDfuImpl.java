package p019no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.Intent;
import com.google.common.base.Ascii;
import java.io.InputStream;
import java.util.Locale;
import java.util.UUID;
import p019no.nordicsemi.android.dfu.BaseCustomDfuImpl;
import p019no.nordicsemi.android.dfu.BaseDfuImpl;
import p019no.nordicsemi.android.dfu.internal.ArchiveInputStream;
import p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import p019no.nordicsemi.android.dfu.internal.exception.DfuException;
import p019no.nordicsemi.android.dfu.internal.exception.RemoteDfuException;
import p019no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException;
import p019no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;
import p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;
import p019no.nordicsemi.android.error.SecureDfuError;

/* renamed from: no.nordicsemi.android.dfu.SecureDfuImpl */
class SecureDfuImpl extends BaseCustomDfuImpl {
    static final UUID DEFAULT_DFU_CONTROL_POINT_UUID;
    static final UUID DEFAULT_DFU_PACKET_UUID;
    static final UUID DEFAULT_DFU_SERVICE_UUID;
    static UUID DFU_CONTROL_POINT_UUID = null;
    static UUID DFU_PACKET_UUID = null;
    static UUID DFU_SERVICE_UUID = null;
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final int MAX_ATTEMPTS = 3;
    private static final int OBJECT_COMMAND = 1;
    private static final int OBJECT_DATA = 2;
    private static final byte[] OP_CODE_CALCULATE_CHECKSUM = {3};
    private static final int OP_CODE_CALCULATE_CHECKSUM_KEY = 3;
    private static final byte[] OP_CODE_CREATE_COMMAND = {1, 1, 0, 0, 0, 0};
    private static final byte[] OP_CODE_CREATE_DATA = {1, 2, 0, 0, 0, 0};
    private static final int OP_CODE_CREATE_KEY = 1;
    private static final byte[] OP_CODE_EXECUTE = {4};
    private static final int OP_CODE_EXECUTE_KEY = 4;
    private static final byte[] OP_CODE_PACKET_RECEIPT_NOTIF_REQ = {2, 0, 0};
    private static final int OP_CODE_PACKET_RECEIPT_NOTIF_REQ_KEY = 2;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 96;
    private static final byte[] OP_CODE_SELECT_OBJECT = {6, 0};
    private static final int OP_CODE_SELECT_OBJECT_KEY = 6;
    private final SecureBluetoothCallback mBluetoothCallback = new SecureBluetoothCallback();
    private BluetoothGattCharacteristic mControlPointCharacteristic;
    private BluetoothGattCharacteristic mPacketCharacteristic;
    private long prepareObjectDelay;

    static {
        UUID uuid = new UUID(279658205548544L, -9223371485494954757L);
        DEFAULT_DFU_SERVICE_UUID = uuid;
        UUID uuid2 = new UUID(-8157989241631715488L, -6937650605005804976L);
        DEFAULT_DFU_CONTROL_POINT_UUID = uuid2;
        UUID uuid3 = new UUID(-8157989237336748192L, -6937650605005804976L);
        DEFAULT_DFU_PACKET_UUID = uuid3;
        DFU_SERVICE_UUID = uuid;
        DFU_CONTROL_POINT_UUID = uuid2;
        DFU_PACKET_UUID = uuid3;
    }

    /* renamed from: no.nordicsemi.android.dfu.SecureDfuImpl$SecureBluetoothCallback */
    protected class SecureBluetoothCallback extends BaseCustomDfuImpl.BaseCustomBluetoothCallback {
        protected SecureBluetoothCallback() {
            super();
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (bluetoothGattCharacteristic.getValue() == null || bluetoothGattCharacteristic.getValue().length < 3) {
                SecureDfuImpl secureDfuImpl = SecureDfuImpl.this;
                secureDfuImpl.loge("Empty response: " + parse(bluetoothGattCharacteristic));
                SecureDfuImpl.this.mError = DfuBaseService.ERROR_INVALID_RESPONSE;
                SecureDfuImpl.this.notifyLock();
                return;
            }
            if (bluetoothGattCharacteristic.getIntValue(17, 0).intValue() != 96) {
                SecureDfuImpl secureDfuImpl2 = SecureDfuImpl.this;
                secureDfuImpl2.loge("Invalid response: " + parse(bluetoothGattCharacteristic));
                SecureDfuImpl.this.mError = DfuBaseService.ERROR_INVALID_RESPONSE;
            } else if (bluetoothGattCharacteristic.getIntValue(17, 1).intValue() == 3) {
                int intValue = bluetoothGattCharacteristic.getIntValue(20, 3).intValue();
                if (((int) (((ArchiveInputStream) SecureDfuImpl.this.mFirmwareStream).getCrc32() & 4294967295L)) == bluetoothGattCharacteristic.getIntValue(20, 7).intValue()) {
                    SecureDfuImpl.this.mProgressInfo.setBytesReceived(intValue);
                } else if (SecureDfuImpl.this.mFirmwareUploadInProgress) {
                    SecureDfuImpl.this.mFirmwareUploadInProgress = false;
                    SecureDfuImpl.this.notifyLock();
                    return;
                }
                handlePacketReceiptNotification(bluetoothGatt, bluetoothGattCharacteristic);
            } else if (!SecureDfuImpl.this.mRemoteErrorOccurred) {
                if (bluetoothGattCharacteristic.getIntValue(17, 2).intValue() != 1) {
                    SecureDfuImpl.this.mRemoteErrorOccurred = true;
                }
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
            }
            SecureDfuImpl.this.notifyLock();
        }
    }

    SecureDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
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

    public boolean initialize(Intent intent, BluetoothGatt bluetoothGatt, int i, InputStream inputStream, InputStream inputStream2) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        if (inputStream2 != null) {
            return super.initialize(intent, bluetoothGatt, i, inputStream, inputStream2);
        }
        this.mService.sendLogBroadcast(20, "The Init packet is required by this version DFU Bootloader");
        this.mService.terminateConnection(bluetoothGatt, DfuBaseService.ERROR_INIT_PACKET_REQUIRED);
        return false;
    }

    public BaseDfuImpl.BaseBluetoothGattCallback getGattCallback() {
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

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0177, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0178, code lost:
        loge(r11.getMessage());
        r10.mService.sendLogBroadcast(20, r11.getMessage());
        r10.mService.terminateConnection(r1, p019no.nordicsemi.android.dfu.DfuBaseService.ERROR_INVALID_RESPONSE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0190, code lost:
        r11 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0191, code lost:
        throw r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0074 A[Catch:{ RemoteDfuException -> 0x007d, UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0177 A[ExcHandler: UnknownResponseException (r11v2 'e' no.nordicsemi.android.dfu.internal.exception.UnknownResponseException A[CUSTOM_DECLARE]), Splitter:B:6:0x004f] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0190 A[ExcHandler: UploadAbortedException (r11v1 'e' no.nordicsemi.android.dfu.internal.exception.UploadAbortedException A[CUSTOM_DECLARE]), Splitter:B:6:0x004f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void performDfu(android.content.Intent r11) throws p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r10 = this;
            java.lang.String r0 = "no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME"
            java.lang.String r1 = "Secure DFU bootloader found"
            r10.logw(r1)
            no.nordicsemi.android.dfu.DfuProgressInfo r1 = r10.mProgressInfo
            r2 = -2
            r1.setProgress(r2)
            no.nordicsemi.android.dfu.DfuBaseService r1 = r10.mService
            r2 = 1000(0x3e8, double:4.94E-321)
            r1.waitFor(r2)
            android.bluetooth.BluetoothGatt r1 = r10.mGatt
            java.lang.String r4 = "no.nordicsemi.android.dfu.extra.EXTRA_MTU"
            boolean r5 = r11.hasExtra(r4)
            if (r5 == 0) goto L_0x0041
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 21
            if (r5 < r6) goto L_0x0041
            r5 = 517(0x205, float:7.24E-43)
            int r4 = r11.getIntExtra(r4, r5)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Requesting MTU = "
            r5.append(r6)
            r5.append(r4)
            java.lang.String r5 = r5.toString()
            r10.logi(r5)
            r10.requestMtu(r4)
        L_0x0041:
            r4 = 0
            java.lang.String r6 = "no.nordicsemi.android.dfu.extra.EXTRA_DATA_OBJECT_DELAY"
            long r4 = r11.getLongExtra(r6, r4)
            r10.prepareObjectDelay = r4
            r4 = 20
            r5 = 1
            r6 = 0
            android.bluetooth.BluetoothGattCharacteristic r7 = r10.mControlPointCharacteristic     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r10.enableCCCD(r7, r5)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r10.mService     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r8 = 10
            java.lang.String r9 = "Notifications enabled"
            r7.sendLogBroadcast(r8, r9)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            no.nordicsemi.android.dfu.DfuBaseService r7 = r10.mService     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r7.waitFor(r2)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            boolean r2 = r11.hasExtra(r0)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            if (r2 == 0) goto L_0x0071
            boolean r0 = r11.getBooleanExtra(r0, r6)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            if (r0 != 0) goto L_0x006f
            goto L_0x0071
        L_0x006f:
            r0 = 0
            goto L_0x0072
        L_0x0071:
            r0 = 1
        L_0x0072:
            if (r0 != 0) goto L_0x0079
            java.lang.String r2 = "Resume feature disabled. Performing fresh DFU"
            r10.logi(r2)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
        L_0x0079:
            r10.sendInitPacket(r1, r0)     // Catch:{ RemoteDfuException -> 0x007d, UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177 }
            goto L_0x00c1
        L_0x007d:
            r0 = move-exception
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = r10.mProgressInfo     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            boolean r2 = r2.isLastPart()     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            if (r2 != 0) goto L_0x00dc
            r10.mRemoteErrorOccurred = r6     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            java.lang.String r0 = "Sending SD+BL failed. Trying to send App only"
            r10.logw(r0)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r10.mService     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r2 = 15
            java.lang.String r3 = "Invalid system components. Trying to send application"
            r0.sendLogBroadcast(r2, r3)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r0 = 4
            r10.mFileType = r0     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            java.io.InputStream r0 = r10.mFirmwareStream     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r0 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r0     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            int r2 = r10.mFileType     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r0.setContentType(r2)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            byte[] r2 = r0.getApplicationInit()     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r3.<init>(r2)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r10.mInitPacketStream = r3     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            int r2 = r2.length     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r10.mInitPacketSizeInBytes = r2     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            int r0 = r0.applicationImageSize()     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r10.mImageSizeInBytes = r0     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r10.mProgressInfo     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            int r2 = r10.mImageSizeInBytes     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r3 = 2
            r0.init(r2, r3, r3)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r10.sendInitPacket(r1, r6)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
        L_0x00c1:
            r10.sendFirmware(r1)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r10.mProgressInfo     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r2 = -5
            r0.setProgress(r2)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r10.mService     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r0.waitUntilDisconnected()     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r10.mService     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r2 = 5
            java.lang.String r3 = "Disconnected by the remote device"
            r0.sendLogBroadcast(r2, r3)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            r10.finalize(r11, r6)     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
            goto L_0x018f
        L_0x00dc:
            throw r0     // Catch:{ UploadAbortedException -> 0x0190, UnknownResponseException -> 0x0177, RemoteDfuException -> 0x00dd }
        L_0x00dd:
            r11 = move-exception
            int r0 = r11.getErrorNumber()
            r0 = r0 | 512(0x200, float:7.175E-43)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r11.getMessage()
            r2.append(r3)
            java.lang.String r3 = ": "
            r2.append(r3)
            java.lang.String r3 = p019no.nordicsemi.android.error.SecureDfuError.parse(r0)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r10.loge(r2)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r10.mService
            java.util.Locale r3 = java.util.Locale.US
            java.lang.Object[] r5 = new java.lang.Object[r5]
            java.lang.String r7 = p019no.nordicsemi.android.error.SecureDfuError.parse(r0)
            r5[r6] = r7
            java.lang.String r6 = "Remote DFU error: %s"
            java.lang.String r3 = java.lang.String.format(r3, r6, r5)
            r2.sendLogBroadcast(r4, r3)
            boolean r2 = r11 instanceof p019no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException
            if (r2 == 0) goto L_0x016f
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException r11 = (p019no.nordicsemi.android.dfu.internal.exception.RemoteDfuExtendedErrorException) r11
            int r0 = r11.getExtendedErrorNumber()
            r0 = r0 | 1024(0x400, float:1.435E-42)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Extended Error details: "
            r2.append(r3)
            java.lang.String r3 = p019no.nordicsemi.android.error.SecureDfuError.parseExtendedError(r0)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r10.loge(r2)
            no.nordicsemi.android.dfu.DfuBaseService r2 = r10.mService
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r5 = "Details: "
            r3.append(r5)
            java.lang.String r5 = p019no.nordicsemi.android.error.SecureDfuError.parseExtendedError(r0)
            r3.append(r5)
            java.lang.String r5 = " (Code = "
            r3.append(r5)
            int r11 = r11.getExtendedErrorNumber()
            r3.append(r11)
            java.lang.String r11 = ")"
            r3.append(r11)
            java.lang.String r11 = r3.toString()
            r2.sendLogBroadcast(r4, r11)
            no.nordicsemi.android.dfu.DfuBaseService r11 = r10.mService
            r0 = r0 | 8192(0x2000, float:1.14794E-41)
            r11.terminateConnection(r1, r0)
            goto L_0x018f
        L_0x016f:
            no.nordicsemi.android.dfu.DfuBaseService r11 = r10.mService
            r0 = r0 | 8192(0x2000, float:1.14794E-41)
            r11.terminateConnection(r1, r0)
            goto L_0x018f
        L_0x0177:
            r11 = move-exception
            java.lang.String r0 = r11.getMessage()
            r10.loge(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r10.mService
            java.lang.String r11 = r11.getMessage()
            r0.sendLogBroadcast(r4, r11)
            no.nordicsemi.android.dfu.DfuBaseService r11 = r10.mService
            r0 = 4104(0x1008, float:5.751E-42)
            r11.terminateConnection(r1, r0)
        L_0x018f:
            return
        L_0x0190:
            r11 = move-exception
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.SecureDfuImpl.performDfu(android.content.Intent):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:38:0x011c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sendInitPacket(android.bluetooth.BluetoothGatt r18, boolean r19) throws p019no.nordicsemi.android.dfu.internal.exception.RemoteDfuException, p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException, p019no.nordicsemi.android.dfu.internal.exception.UnknownResponseException {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            java.util.zip.CRC32 r3 = new java.util.zip.CRC32
            r3.<init>()
            java.lang.String r0 = "Setting object to Command (Op Code = 6, Type = 1)"
            r1.logi(r0)
            r4 = 1
            no.nordicsemi.android.dfu.SecureDfuImpl$ObjectInfo r5 = r1.selectObject(r4)
            java.util.Locale r0 = java.util.Locale.US
            r6 = 3
            java.lang.Object[] r7 = new java.lang.Object[r6]
            int r8 = r5.maxSize
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r9 = 0
            r7[r9] = r8
            int r8 = r5.offset
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r7[r4] = r8
            int r8 = r5.CRC32
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r10 = 2
            r7[r10] = r8
            java.lang.String r8 = "Command object info received (Max size = %d, Offset = %d, CRC = %08X)"
            java.lang.String r0 = java.lang.String.format(r0, r8, r7)
            r1.logi(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.util.Locale r7 = java.util.Locale.US
            java.lang.Object[] r11 = new java.lang.Object[r6]
            int r12 = r5.maxSize
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r11[r9] = r12
            int r12 = r5.offset
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r11[r4] = r12
            int r12 = r5.CRC32
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r11[r10] = r12
            java.lang.String r7 = java.lang.String.format(r7, r8, r11)
            r8 = 10
            r0.sendLogBroadcast(r8, r7)
            int r0 = r1.mInitPacketSizeInBytes
            int r0 = r5.maxSize
            r11 = 4294967295(0xffffffff, double:2.1219957905E-314)
            r7 = 4100(0x1004, float:5.745E-42)
            java.lang.String r13 = "Error while resetting the init packet stream"
            if (r19 == 0) goto L_0x0118
            int r0 = r5.offset
            if (r0 <= 0) goto L_0x0118
            int r0 = r5.offset
            int r14 = r1.mInitPacketSizeInBytes
            if (r0 > r14) goto L_0x0118
            int r0 = r5.offset     // Catch:{ IOException -> 0x00e4 }
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x00e4 }
            java.io.InputStream r14 = r1.mInitPacketStream     // Catch:{ IOException -> 0x00e4 }
            r14.read(r0)     // Catch:{ IOException -> 0x00e4 }
            r3.update(r0)     // Catch:{ IOException -> 0x00e4 }
            long r14 = r3.getValue()     // Catch:{ IOException -> 0x00e4 }
            long r14 = r14 & r11
            int r0 = (int) r14     // Catch:{ IOException -> 0x00e4 }
            int r14 = r5.CRC32     // Catch:{ IOException -> 0x00e4 }
            if (r14 != r0) goto L_0x00d9
            java.lang.String r0 = "Init packet CRC is the same"
            r1.logi(r0)     // Catch:{ IOException -> 0x00e4 }
            int r0 = r5.offset     // Catch:{ IOException -> 0x00e4 }
            int r14 = r1.mInitPacketSizeInBytes     // Catch:{ IOException -> 0x00e4 }
            if (r0 != r14) goto L_0x00af
            java.lang.String r0 = "-> Whole Init packet was sent before"
            r1.logi(r0)     // Catch:{ IOException -> 0x00e4 }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService     // Catch:{ IOException -> 0x00ab }
            java.lang.String r14 = "Received CRC match Init packet"
            r0.sendLogBroadcast(r8, r14)     // Catch:{ IOException -> 0x00ab }
            r0 = 1
            goto L_0x0119
        L_0x00ab:
            r0 = move-exception
            r15 = r0
            r0 = 1
            goto L_0x00e7
        L_0x00af:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00e4 }
            r0.<init>()     // Catch:{ IOException -> 0x00e4 }
            java.lang.String r14 = "-> "
            r0.append(r14)     // Catch:{ IOException -> 0x00e4 }
            int r14 = r5.offset     // Catch:{ IOException -> 0x00e4 }
            r0.append(r14)     // Catch:{ IOException -> 0x00e4 }
            java.lang.String r14 = " bytes of Init packet were sent before"
            r0.append(r14)     // Catch:{ IOException -> 0x00e4 }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x00e4 }
            r1.logi(r0)     // Catch:{ IOException -> 0x00e4 }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService     // Catch:{ IOException -> 0x00d4 }
            java.lang.String r14 = "Resuming sending Init packet..."
            r0.sendLogBroadcast(r8, r14)     // Catch:{ IOException -> 0x00d4 }
            r0 = 0
            r14 = 1
            goto L_0x011a
        L_0x00d4:
            r0 = move-exception
            r15 = r0
            r0 = 0
            r14 = 1
            goto L_0x00e8
        L_0x00d9:
            java.io.InputStream r0 = r1.mInitPacketStream     // Catch:{ IOException -> 0x00e4 }
            r0.reset()     // Catch:{ IOException -> 0x00e4 }
            r3.reset()     // Catch:{ IOException -> 0x00e4 }
            r5.offset = r9     // Catch:{ IOException -> 0x00e4 }
            goto L_0x0118
        L_0x00e4:
            r0 = move-exception
            r15 = r0
            r0 = 0
        L_0x00e7:
            r14 = 0
        L_0x00e8:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Error while reading "
            r10.append(r11)
            int r11 = r5.offset
            r10.append(r11)
            java.lang.String r11 = " bytes from the init packet stream"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r1.loge(r10, r15)
            java.io.InputStream r10 = r1.mInitPacketStream     // Catch:{ IOException -> 0x010e }
            r10.reset()     // Catch:{ IOException -> 0x010e }
            r3.reset()     // Catch:{ IOException -> 0x010e }
            r5.offset = r9     // Catch:{ IOException -> 0x010e }
            goto L_0x011a
        L_0x010e:
            r0 = move-exception
            r1.loge(r13, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r0.terminateConnection(r2, r7)
            return
        L_0x0118:
            r0 = 0
        L_0x0119:
            r14 = 0
        L_0x011a:
            if (r0 != 0) goto L_0x025b
            r1.setPacketReceiptNotifications(r9)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.String r10 = "Packet Receipt Notif disabled (Op Code = 2, Value = 0)"
            r0.sendLogBroadcast(r8, r10)
            r0 = 1
        L_0x0127:
            if (r0 > r6) goto L_0x025b
            java.lang.String r10 = ")"
            if (r14 != 0) goto L_0x0152
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "Creating Init packet object (Op Code = 1, Type = 1, Size = "
            r11.append(r12)
            int r12 = r1.mInitPacketSizeInBytes
            r11.append(r12)
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            r1.logi(r11)
            int r11 = r1.mInitPacketSizeInBytes
            r1.writeCreateRequest(r4, r11)
            no.nordicsemi.android.dfu.DfuBaseService r11 = r1.mService
            java.lang.String r12 = "Command object created"
            r11.sendLogBroadcast(r8, r12)
        L_0x0152:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            r11.<init>()     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            java.lang.String r12 = "Sending "
            r11.append(r12)     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            int r12 = r1.mInitPacketSizeInBytes     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            int r14 = r5.offset     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            int r12 = r12 - r14
            r11.append(r12)     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            java.lang.String r12 = " bytes of init packet..."
            r11.append(r12)     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            java.lang.String r11 = r11.toString()     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            r1.logi(r11)     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            android.bluetooth.BluetoothGattCharacteristic r11 = r1.mPacketCharacteristic     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            r1.writeInitData(r11, r3)     // Catch:{ DeviceDisconnectedException -> 0x0254 }
            long r11 = r3.getValue()
            r14 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r11 = r11 & r14
            int r12 = (int) r11
            no.nordicsemi.android.dfu.DfuBaseService r11 = r1.mService
            java.util.Locale r14 = java.util.Locale.US
            java.lang.Object[] r15 = new java.lang.Object[r4]
            java.lang.Integer r16 = java.lang.Integer.valueOf(r12)
            r15[r9] = r16
            java.lang.String r7 = "Command object sent (CRC = %08X)"
            java.lang.String r7 = java.lang.String.format(r14, r7, r15)
            r11.sendLogBroadcast(r8, r7)
            java.lang.String r7 = "Sending Calculate Checksum command (Op Code = 3)"
            r1.logi(r7)
            no.nordicsemi.android.dfu.SecureDfuImpl$ObjectChecksum r7 = r17.readChecksum()
            no.nordicsemi.android.dfu.DfuBaseService r11 = r1.mService
            java.util.Locale r14 = java.util.Locale.US
            r15 = 2
            java.lang.Object[] r6 = new java.lang.Object[r15]
            int r15 = r7.offset
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            r6[r9] = r15
            int r15 = r7.CRC32
            java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
            r6[r4] = r15
            java.lang.String r15 = "Checksum received (Offset = %d, CRC = %08X)"
            java.lang.String r6 = java.lang.String.format(r14, r15, r6)
            r11.sendLogBroadcast(r8, r6)
            java.util.Locale r6 = java.util.Locale.US
            r11 = 2
            java.lang.Object[] r14 = new java.lang.Object[r11]
            int r11 = r7.offset
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r14[r9] = r11
            int r11 = r7.CRC32
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r14[r4] = r11
            java.lang.String r6 = java.lang.String.format(r6, r15, r14)
            r1.logi(r6)
            int r6 = r7.CRC32
            if (r12 != r6) goto L_0x01e0
            goto L_0x025b
        L_0x01e0:
            r6 = 3
            if (r0 >= r6) goto L_0x0240
            int r0 = r0 + 1
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r11 = "CRC does not match! Retrying...("
            r7.append(r11)
            r7.append(r0)
            java.lang.String r12 = "/"
            r7.append(r12)
            r7.append(r6)
            r7.append(r10)
            java.lang.String r7 = r7.toString()
            r1.logi(r7)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r1.mService
            r14 = 15
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>()
            r15.append(r11)
            r15.append(r0)
            r15.append(r12)
            r15.append(r6)
            r15.append(r10)
            java.lang.String r10 = r15.toString()
            r7.sendLogBroadcast(r14, r10)
            r5.offset = r9     // Catch:{ IOException -> 0x0234 }
            r5.CRC32 = r9     // Catch:{ IOException -> 0x0234 }
            java.io.InputStream r7 = r1.mInitPacketStream     // Catch:{ IOException -> 0x0234 }
            r7.reset()     // Catch:{ IOException -> 0x0234 }
            r3.reset()     // Catch:{ IOException -> 0x0234 }
            r7 = 4100(0x1004, float:5.745E-42)
            r14 = 0
            goto L_0x0127
        L_0x0234:
            r0 = move-exception
            r1.loge(r13, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4100(0x1004, float:5.745E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x0240:
            java.lang.String r0 = "CRC does not match!"
            r1.loge(r0)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService
            r4 = 20
            r3.sendLogBroadcast(r4, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4109(0x100d, float:5.758E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x0254:
            r0 = move-exception
            java.lang.String r2 = "Disconnected while sending init packet"
            r1.loge(r2)
            throw r0
        L_0x025b:
            java.lang.String r0 = "Executing init packet (Op Code = 4)"
            r1.logi(r0)
            r17.writeExecute()
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.String r2 = "Command object executed"
            r0.sendLogBroadcast(r8, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.SecureDfuImpl.sendInitPacket(android.bluetooth.BluetoothGatt, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:60:0x032d, code lost:
        if (r1.mPacketsBeforeNotification > 1) goto L_0x0331;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void sendFirmware(android.bluetooth.BluetoothGatt r27) throws p019no.nordicsemi.android.dfu.internal.exception.RemoteDfuException, p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException, p019no.nordicsemi.android.dfu.internal.exception.UnknownResponseException {
        /*
            r26 = this;
            r1 = r26
            r2 = r27
            java.lang.String r0 = "..."
            java.lang.String r3 = "Resuming from byte "
            java.lang.String r4 = " bytes of data sent before, CRC match"
            java.lang.String r5 = " bytes sent before, CRC does not match"
            int r6 = r1.mPacketsBeforeNotification
            java.lang.String r7 = ")"
            r8 = 10
            if (r6 <= 0) goto L_0x0030
            r1.setPacketReceiptNotifications(r6)
            no.nordicsemi.android.dfu.DfuBaseService r9 = r1.mService
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "Packet Receipt Notif Req (Op Code = 2) sent (Value = "
            r10.append(r11)
            r10.append(r6)
            r10.append(r7)
            java.lang.String r6 = r10.toString()
            r9.sendLogBroadcast(r8, r6)
        L_0x0030:
            java.lang.String r6 = "Setting object to Data (Op Code = 6, Type = 2)"
            r1.logi(r6)
            r6 = 2
            no.nordicsemi.android.dfu.SecureDfuImpl$ObjectInfo r9 = r1.selectObject(r6)
            java.util.Locale r10 = java.util.Locale.US
            r11 = 3
            java.lang.Object[] r12 = new java.lang.Object[r11]
            int r13 = r9.maxSize
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r14 = 0
            r12[r14] = r13
            int r13 = r9.offset
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r15 = 1
            r12[r15] = r13
            int r13 = r9.CRC32
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r12[r6] = r13
            java.lang.String r13 = "Data object info received (Max size = %d, Offset = %d, CRC = %08X)"
            java.lang.String r10 = java.lang.String.format(r10, r13, r12)
            r1.logi(r10)
            no.nordicsemi.android.dfu.DfuBaseService r10 = r1.mService
            java.util.Locale r12 = java.util.Locale.US
            java.lang.Object[] r8 = new java.lang.Object[r11]
            int r11 = r9.maxSize
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r8[r14] = r11
            int r11 = r9.offset
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r8[r15] = r11
            int r11 = r9.CRC32
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r8[r6] = r11
            java.lang.String r8 = java.lang.String.format(r12, r13, r8)
            r11 = 10
            r10.sendLogBroadcast(r11, r8)
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r1.mProgressInfo
            int r10 = r9.maxSize
            r8.setMaxObjectSizeInBytes(r10)
            int r8 = r1.mImageSizeInBytes
            int r10 = r9.maxSize
            int r8 = r8 + r10
            int r8 = r8 - r15
            int r10 = r9.maxSize
            int r8 = r8 / r10
            int r10 = r9.offset
            java.lang.String r13 = "Error while reading firmware stream"
            java.lang.String r6 = "Data object executed"
            java.lang.String r15 = "Executing data object (Op Code = 4)"
            if (r10 <= 0) goto L_0x01d8
            int r10 = r9.offset     // Catch:{ IOException -> 0x01cc }
            int r14 = r9.maxSize     // Catch:{ IOException -> 0x01cc }
            int r10 = r10 / r14
            int r14 = r9.maxSize     // Catch:{ IOException -> 0x01cc }
            int r14 = r14 * r10
            int r11 = r9.offset     // Catch:{ IOException -> 0x01cc }
            int r11 = r11 - r14
            if (r11 != 0) goto L_0x00b6
            int r11 = r9.maxSize     // Catch:{ IOException -> 0x01cc }
            int r14 = r14 - r11
            int r11 = r9.maxSize     // Catch:{ IOException -> 0x01cc }
        L_0x00b6:
            if (r14 <= 0) goto L_0x00c9
            java.io.InputStream r12 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01cc }
            r21 = r10
            byte[] r10 = new byte[r14]     // Catch:{ IOException -> 0x01cc }
            r12.read(r10)     // Catch:{ IOException -> 0x01cc }
            java.io.InputStream r10 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01cc }
            int r12 = r9.maxSize     // Catch:{ IOException -> 0x01cc }
            r10.mark(r12)     // Catch:{ IOException -> 0x01cc }
            goto L_0x00cb
        L_0x00c9:
            r21 = r10
        L_0x00cb:
            java.io.InputStream r10 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01cc }
            byte[] r12 = new byte[r11]     // Catch:{ IOException -> 0x01cc }
            r10.read(r12)     // Catch:{ IOException -> 0x01cc }
            java.io.InputStream r10 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01cc }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r10 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r10     // Catch:{ IOException -> 0x01cc }
            long r22 = r10.getCrc32()     // Catch:{ IOException -> 0x01cc }
            r10 = r7
            r12 = r8
            r19 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r7 = r22 & r19
            int r8 = (int) r7     // Catch:{ IOException -> 0x01cc }
            int r7 = r9.CRC32     // Catch:{ IOException -> 0x01cc }
            if (r8 != r7) goto L_0x0155
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01cc }
            r0.<init>()     // Catch:{ IOException -> 0x01cc }
            int r3 = r9.offset     // Catch:{ IOException -> 0x01cc }
            r0.append(r3)     // Catch:{ IOException -> 0x01cc }
            r0.append(r4)     // Catch:{ IOException -> 0x01cc }
            java.lang.String r0 = r0.toString()     // Catch:{ IOException -> 0x01cc }
            r1.logi(r0)     // Catch:{ IOException -> 0x01cc }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService     // Catch:{ IOException -> 0x01cc }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01cc }
            r3.<init>()     // Catch:{ IOException -> 0x01cc }
            int r5 = r9.offset     // Catch:{ IOException -> 0x01cc }
            r3.append(r5)     // Catch:{ IOException -> 0x01cc }
            r3.append(r4)     // Catch:{ IOException -> 0x01cc }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x01cc }
            r4 = 10
            r0.sendLogBroadcast(r4, r3)     // Catch:{ IOException -> 0x01cc }
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo     // Catch:{ IOException -> 0x01cc }
            int r3 = r9.offset     // Catch:{ IOException -> 0x01cc }
            r0.setBytesSent(r3)     // Catch:{ IOException -> 0x01cc }
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo     // Catch:{ IOException -> 0x01cc }
            int r3 = r9.offset     // Catch:{ IOException -> 0x01cc }
            r0.setBytesReceived(r3)     // Catch:{ IOException -> 0x01cc }
            int r0 = r9.maxSize     // Catch:{ IOException -> 0x01cc }
            if (r11 != r0) goto L_0x0152
            int r0 = r9.offset     // Catch:{ IOException -> 0x01cc }
            int r3 = r1.mImageSizeInBytes     // Catch:{ IOException -> 0x01cc }
            if (r0 >= r3) goto L_0x0152
            r1.logi(r15)     // Catch:{ IOException -> 0x01cc }
            r26.writeExecute()     // Catch:{ RemoteDfuException -> 0x013b }
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService     // Catch:{ RemoteDfuException -> 0x013b }
            r3 = 10
            r0.sendLogBroadcast(r3, r6)     // Catch:{ RemoteDfuException -> 0x013b }
            goto L_0x01ca
        L_0x013b:
            r0 = move-exception
            int r3 = r0.getErrorNumber()     // Catch:{ IOException -> 0x01cc }
            r4 = 8
            if (r3 != r4) goto L_0x0151
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService     // Catch:{ IOException -> 0x01cc }
            java.lang.String r3 = "Data object already executed"
            r4 = 10
            r0.sendLogBroadcast(r4, r3)     // Catch:{ IOException -> 0x01cc }
            r3 = 0
            r1.mRemoteErrorOccurred = r3     // Catch:{ IOException -> 0x01cc }
            goto L_0x01ca
        L_0x0151:
            throw r0     // Catch:{ IOException -> 0x01cc }
        L_0x0152:
            r0 = 1
            goto L_0x01e3
        L_0x0155:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01cc }
            r4.<init>()     // Catch:{ IOException -> 0x01cc }
            int r7 = r9.offset     // Catch:{ IOException -> 0x01cc }
            r4.append(r7)     // Catch:{ IOException -> 0x01cc }
            r4.append(r5)     // Catch:{ IOException -> 0x01cc }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x01cc }
            r1.logi(r4)     // Catch:{ IOException -> 0x01cc }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ IOException -> 0x01cc }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01cc }
            r7.<init>()     // Catch:{ IOException -> 0x01cc }
            int r8 = r9.offset     // Catch:{ IOException -> 0x01cc }
            r7.append(r8)     // Catch:{ IOException -> 0x01cc }
            r7.append(r5)     // Catch:{ IOException -> 0x01cc }
            java.lang.String r5 = r7.toString()     // Catch:{ IOException -> 0x01cc }
            r7 = 15
            r4.sendLogBroadcast(r7, r5)     // Catch:{ IOException -> 0x01cc }
            no.nordicsemi.android.dfu.DfuProgressInfo r4 = r1.mProgressInfo     // Catch:{ IOException -> 0x01cc }
            r4.setBytesSent(r14)     // Catch:{ IOException -> 0x01cc }
            no.nordicsemi.android.dfu.DfuProgressInfo r4 = r1.mProgressInfo     // Catch:{ IOException -> 0x01cc }
            r4.setBytesReceived(r14)     // Catch:{ IOException -> 0x01cc }
            int r4 = r9.offset     // Catch:{ IOException -> 0x01cc }
            int r4 = r4 - r11
            r9.offset = r4     // Catch:{ IOException -> 0x01cc }
            r4 = 0
            r9.CRC32 = r4     // Catch:{ IOException -> 0x01cc }
            java.io.InputStream r4 = r1.mFirmwareStream     // Catch:{ IOException -> 0x01cc }
            r4.reset()     // Catch:{ IOException -> 0x01cc }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01cc }
            r4.<init>()     // Catch:{ IOException -> 0x01cc }
            r4.append(r3)     // Catch:{ IOException -> 0x01cc }
            int r5 = r9.offset     // Catch:{ IOException -> 0x01cc }
            r4.append(r5)     // Catch:{ IOException -> 0x01cc }
            r4.append(r0)     // Catch:{ IOException -> 0x01cc }
            java.lang.String r4 = r4.toString()     // Catch:{ IOException -> 0x01cc }
            r1.logi(r4)     // Catch:{ IOException -> 0x01cc }
            no.nordicsemi.android.dfu.DfuBaseService r4 = r1.mService     // Catch:{ IOException -> 0x01cc }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x01cc }
            r5.<init>()     // Catch:{ IOException -> 0x01cc }
            r5.append(r3)     // Catch:{ IOException -> 0x01cc }
            int r3 = r9.offset     // Catch:{ IOException -> 0x01cc }
            r5.append(r3)     // Catch:{ IOException -> 0x01cc }
            r5.append(r0)     // Catch:{ IOException -> 0x01cc }
            java.lang.String r0 = r5.toString()     // Catch:{ IOException -> 0x01cc }
            r3 = 10
            r4.sendLogBroadcast(r3, r0)     // Catch:{ IOException -> 0x01cc }
        L_0x01ca:
            r0 = 0
            goto L_0x01e3
        L_0x01cc:
            r0 = move-exception
            r1.loge(r13, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4100(0x1004, float:5.745E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x01d8:
            r10 = r7
            r12 = r8
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo
            r3 = 0
            r0.setBytesSent(r3)
            r0 = 0
            r21 = 0
        L_0x01e3:
            long r3 = android.os.SystemClock.elapsedRealtime()
            int r5 = r9.offset
            int r7 = r1.mImageSizeInBytes
            if (r5 >= r7) goto L_0x0440
            r5 = 1
        L_0x01ee:
            no.nordicsemi.android.dfu.DfuProgressInfo r7 = r1.mProgressInfo
            int r7 = r7.getAvailableObjectSizeIsBytes()
            if (r7 <= 0) goto L_0x044e
            java.lang.String r7 = "Uploading firmware..."
            if (r0 != 0) goto L_0x0276
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r1.mProgressInfo
            int r8 = r8.getAvailableObjectSizeIsBytes()
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r14 = "Creating Data object (Op Code = 1, Type = 2, Size = "
            r11.append(r14)
            r11.append(r8)
            java.lang.String r14 = ") ("
            r11.append(r14)
            int r14 = r21 + 1
            r11.append(r14)
            r22 = r0
            java.lang.String r0 = "/"
            r11.append(r0)
            r11.append(r12)
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            r1.logi(r11)
            r11 = 2
            r1.writeCreateRequest(r11, r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r23 = r10
            java.lang.String r10 = "Data object ("
            r11.append(r10)
            r11.append(r14)
            r11.append(r0)
            r11.append(r12)
            java.lang.String r0 = ") created"
            r11.append(r0)
            java.lang.String r0 = r11.toString()
            r10 = 10
            r8.sendLogBroadcast(r10, r0)
            long r10 = r1.prepareObjectDelay
            r24 = 0
            int r0 = (r10 > r24 ? 1 : (r10 == r24 ? 0 : -1))
            if (r0 > 0) goto L_0x025e
            if (r12 != 0) goto L_0x026c
        L_0x025e:
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            long r10 = r1.prepareObjectDelay
            int r8 = (r10 > r24 ? 1 : (r10 == r24 ? 0 : -1))
            if (r8 <= 0) goto L_0x0267
            goto L_0x0269
        L_0x0267:
            r10 = 400(0x190, double:1.976E-321)
        L_0x0269:
            r0.waitFor(r10)
        L_0x026c:
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r8 = 10
            r0.sendLogBroadcast(r8, r7)
            r0 = r22
            goto L_0x0282
        L_0x0276:
            r23 = r10
            r8 = 10
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.String r10 = "Resuming uploading firmware..."
            r0.sendLogBroadcast(r8, r10)
            r0 = 0
        L_0x0282:
            r1.logi(r7)     // Catch:{ DeviceDisconnectedException -> 0x0439 }
            android.bluetooth.BluetoothGattCharacteristic r7 = r1.mPacketCharacteristic     // Catch:{ DeviceDisconnectedException -> 0x0439 }
            r1.uploadFirmwareImage(r7)     // Catch:{ DeviceDisconnectedException -> 0x0439 }
            java.lang.String r7 = "Sending Calculate Checksum command (Op Code = 3)"
            r1.logi(r7)
            no.nordicsemi.android.dfu.SecureDfuImpl$ObjectChecksum r7 = r26.readChecksum()
            java.util.Locale r8 = java.util.Locale.US
            r10 = 2
            java.lang.Object[] r11 = new java.lang.Object[r10]
            int r10 = r7.offset
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r14 = 0
            r11[r14] = r10
            int r10 = r7.CRC32
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r14 = 1
            r11[r14] = r10
            java.lang.String r10 = "Checksum received (Offset = %d, CRC = %08X)"
            java.lang.String r8 = java.lang.String.format(r8, r10, r11)
            r1.logi(r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService
            java.util.Locale r11 = java.util.Locale.US
            r22 = r0
            r14 = 2
            java.lang.Object[] r0 = new java.lang.Object[r14]
            int r14 = r7.offset
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            r18 = 0
            r0[r18] = r14
            int r14 = r7.CRC32
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            r17 = 1
            r0[r17] = r14
            java.lang.String r0 = java.lang.String.format(r11, r10, r0)
            r10 = 10
            r8.sendLogBroadcast(r10, r0)
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo
            int r0 = r0.getBytesSent()
            int r8 = r7.offset
            int r0 = r0 - r8
            if (r0 <= 0) goto L_0x036f
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.lang.String r10 = " bytes were lost!"
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            r1.logw(r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r10.append(r0)
            java.lang.String r11 = " bytes were lost"
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            r11 = 15
            r8.sendLogBroadcast(r11, r10)
            java.io.InputStream r8 = r1.mFirmwareStream     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            r8.reset()     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            java.io.InputStream r8 = r1.mFirmwareStream     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            int r10 = r9.maxSize     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            int r10 = r10 - r0
            byte[] r10 = new byte[r10]     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            r8.read(r10)     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r1.mProgressInfo     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            int r10 = r7.offset     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            r8.setBytesSent(r10)     // Catch:{ IOException -> 0x0363, all -> 0x0340 }
            int r8 = r1.mPacketsBeforeNotification
            if (r8 == 0) goto L_0x0330
            int r8 = r1.mPacketsBeforeNotification
            r10 = 1
            if (r8 <= r10) goto L_0x036f
            goto L_0x0331
        L_0x0330:
            r10 = 1
        L_0x0331:
            r1.mPacketsBeforeNotification = r10
            r1.setPacketReceiptNotifications(r10)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService
            java.lang.String r10 = "Packet Receipt Notif Req (Op Code = 2) sent (Value = 1)"
            r11 = 10
            r8.sendLogBroadcast(r11, r10)
            goto L_0x036f
        L_0x0340:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Progress lost. Bytes sent: "
            r3.append(r4)
            no.nordicsemi.android.dfu.DfuProgressInfo r4 = r1.mProgressInfo
            int r4 = r4.getBytesSent()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r1.loge(r3, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4111(0x100f, float:5.761E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x0363:
            r0 = move-exception
            r1.loge(r13, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4100(0x1004, float:5.745E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x036f:
            java.io.InputStream r8 = r1.mFirmwareStream
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r8 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8
            long r10 = r8.getCrc32()
            r19 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r10 = r10 & r19
            int r8 = (int) r10
            int r10 = r7.CRC32
            if (r8 != r10) goto L_0x03ad
            if (r0 <= 0) goto L_0x038a
            r10 = r23
            r0 = 1
            goto L_0x01ee
        L_0x038a:
            r1.logi(r15)
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo
            boolean r0 = r0.isComplete()
            r1.writeExecute(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r5 = 10
            r0.sendLogBroadcast(r5, r6)
            int r21 = r21 + 1
            java.io.InputStream r0 = r1.mFirmwareStream
            r10 = 0
            r0.mark(r10)
            r5 = 1
            r7 = 3
            r10 = 2
            r11 = 15
            r16 = 0
            goto L_0x0413
        L_0x03ad:
            r10 = 0
            java.util.Locale r0 = java.util.Locale.US
            r11 = 2
            java.lang.Object[] r14 = new java.lang.Object[r11]
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r14[r10] = r8
            int r7 = r7.CRC32
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r8 = 1
            r14[r8] = r7
            java.lang.String r7 = "CRC does not match! Expected %08X but found %08X."
            java.lang.String r0 = java.lang.String.format(r0, r7, r14)
            r7 = 3
            if (r5 >= r7) goto L_0x0427
            int r5 = r5 + 1
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            r8.append(r0)
            java.util.Locale r0 = java.util.Locale.US
            r10 = 2
            java.lang.Object[] r11 = new java.lang.Object[r10]
            java.lang.Integer r14 = java.lang.Integer.valueOf(r5)
            r16 = 0
            r11[r16] = r14
            java.lang.Integer r14 = java.lang.Integer.valueOf(r7)
            r17 = 1
            r11[r17] = r14
            java.lang.String r14 = " Retrying...(%d/%d)"
            java.lang.String r0 = java.lang.String.format(r0, r14, r11)
            r8.append(r0)
            java.lang.String r0 = r8.toString()
            r1.logi(r0)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r1.mService
            r11 = 15
            r8.sendLogBroadcast(r11, r0)
            java.io.InputStream r0 = r1.mFirmwareStream     // Catch:{ IOException -> 0x0419 }
            r0.reset()     // Catch:{ IOException -> 0x0419 }
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r1.mProgressInfo     // Catch:{ IOException -> 0x0419 }
            java.io.InputStream r8 = r1.mFirmwareStream     // Catch:{ IOException -> 0x0419 }
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r8 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8     // Catch:{ IOException -> 0x0419 }
            int r8 = r8.getBytesRead()     // Catch:{ IOException -> 0x0419 }
            r0.setBytesSent(r8)     // Catch:{ IOException -> 0x0419 }
        L_0x0413:
            r0 = r22
            r10 = r23
            goto L_0x01ee
        L_0x0419:
            r0 = move-exception
            java.lang.String r3 = "Error while resetting the firmware stream"
            r1.loge(r3, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4100(0x1004, float:5.745E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x0427:
            r1.loge(r0)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r1.mService
            r4 = 20
            r3.sendLogBroadcast(r4, r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r3 = 4109(0x100d, float:5.758E-42)
            r0.terminateConnection(r2, r3)
            return
        L_0x0439:
            r0 = move-exception
            java.lang.String r2 = "Disconnected while sending data"
            r1.loge(r2)
            throw r0
        L_0x0440:
            r1.logi(r15)
            r2 = 1
            r1.writeExecute(r2)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            r2 = 10
            r0.sendLogBroadcast(r2, r6)
        L_0x044e:
            long r5 = android.os.SystemClock.elapsedRealtime()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Transfer of "
            r0.append(r2)
            no.nordicsemi.android.dfu.DfuProgressInfo r2 = r1.mProgressInfo
            int r2 = r2.getBytesSent()
            int r7 = r9.offset
            int r2 = r2 - r7
            r0.append(r2)
            java.lang.String r2 = " bytes has taken "
            r0.append(r2)
            long r5 = r5 - r3
            r0.append(r5)
            java.lang.String r2 = " ms"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.logi(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r1.mService
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Upload completed in "
            r3.append(r4)
            r3.append(r5)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r3 = 10
            r0.sendLogBroadcast(r3, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.SecureDfuImpl.sendFirmware(android.bluetooth.BluetoothGatt):void");
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        if (bArr != null && bArr.length >= 3 && bArr[0] == 96 && bArr[1] == i && (bArr[2] == 1 || bArr[2] == 2 || bArr[2] == 3 || bArr[2] == 4 || bArr[2] == 5 || bArr[2] == 7 || bArr[2] == 8 || bArr[2] == 10 || bArr[2] == 11)) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 96, i);
    }

    private void setNumberOfPackets(byte[] bArr, int i) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
    }

    private void setObjectSize(byte[] bArr, int i) {
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        bArr[4] = (byte) ((i >> 16) & 255);
        bArr[5] = (byte) ((i >> 24) & 255);
    }

    private void setPacketReceiptNotifications(int i) throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        if (this.mConnected) {
            logi("Sending the number of packets before notifications (Op Code = 2, Value = " + i + ")");
            byte[] bArr = OP_CODE_PACKET_RECEIPT_NOTIF_REQ;
            setNumberOfPackets(bArr, i);
            writeOpCode(this.mControlPointCharacteristic, bArr);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 2);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Sending the number of packets failed", readNotificationResponse[3]);
            } else if (statusCode != 1) {
                throw new RemoteDfuException("Sending the number of packets failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
    }

    private void writeOpCode(BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr) throws DeviceDisconnectedException, DfuException, UploadAbortedException {
        writeOpCode(bluetoothGattCharacteristic, bArr, false);
    }

    private void writeCreateRequest(int i, int i2) throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (this.mConnected) {
            byte[] bArr = i == 1 ? OP_CODE_CREATE_COMMAND : OP_CODE_CREATE_DATA;
            setObjectSize(bArr, i2);
            writeOpCode(this.mControlPointCharacteristic, bArr);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 1);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Creating Command object failed", readNotificationResponse[3]);
            } else if (statusCode != 1) {
                throw new RemoteDfuException("Creating Command object failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to create object: device disconnected");
        }
    }

    private ObjectInfo selectObject(int i) throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (this.mConnected) {
            byte[] bArr = OP_CODE_SELECT_OBJECT;
            bArr[1] = (byte) i;
            writeOpCode(this.mControlPointCharacteristic, bArr);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 6);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Selecting object failed", readNotificationResponse[3]);
            } else if (statusCode == 1) {
                ObjectInfo objectInfo = new ObjectInfo();
                objectInfo.maxSize = unsignedBytesToInt(readNotificationResponse, 3);
                objectInfo.offset = unsignedBytesToInt(readNotificationResponse, 7);
                objectInfo.CRC32 = unsignedBytesToInt(readNotificationResponse, 11);
                return objectInfo;
            } else {
                throw new RemoteDfuException("Selecting object failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to read object info: device disconnected");
        }
    }

    private ObjectChecksum readChecksum() throws DeviceDisconnectedException, DfuException, UploadAbortedException, RemoteDfuException, UnknownResponseException {
        if (this.mConnected) {
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_CALCULATE_CHECKSUM);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 3);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Receiving Checksum failed", readNotificationResponse[3]);
            } else if (statusCode == 1) {
                ObjectChecksum objectChecksum = new ObjectChecksum();
                objectChecksum.offset = unsignedBytesToInt(readNotificationResponse, 3);
                objectChecksum.CRC32 = unsignedBytesToInt(readNotificationResponse, 7);
                return objectChecksum;
            } else {
                throw new RemoteDfuException("Receiving Checksum failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
    }

    private int unsignedBytesToInt(byte[] bArr, int i) {
        return (bArr[i] & 255) + ((bArr[i + 1] & 255) << 8) + ((bArr[i + 2] & 255) << Ascii.DLE) + ((bArr[i + 3] & 255) << Ascii.CAN);
    }

    private void writeExecute() throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        if (this.mConnected) {
            writeOpCode(this.mControlPointCharacteristic, OP_CODE_EXECUTE);
            byte[] readNotificationResponse = readNotificationResponse();
            int statusCode = getStatusCode(readNotificationResponse, 4);
            if (statusCode == 11) {
                throw new RemoteDfuExtendedErrorException("Executing object failed", readNotificationResponse[3]);
            } else if (statusCode != 1) {
                throw new RemoteDfuException("Executing object failed", statusCode);
            }
        } else {
            throw new DeviceDisconnectedException("Unable to read Checksum: device disconnected");
        }
    }

    private void writeExecute(boolean z) throws DfuException, DeviceDisconnectedException, UploadAbortedException, UnknownResponseException, RemoteDfuException {
        try {
            writeExecute();
        } catch (RemoteDfuException e) {
            if (!z || e.getErrorNumber() != 5) {
                throw e;
            }
            logw(e.getMessage() + ": " + SecureDfuError.parse(517));
            if (this.mFileType == 1) {
                logw("Are you sure your new SoftDevice is API compatible with the updated one? If not, update the bootloader as well");
            }
            this.mService.sendLogBroadcast(15, String.format(Locale.US, "Remote DFU error: %s. SD busy? Retrying...", new Object[]{SecureDfuError.parse(517)}));
            logi("SD busy? Retrying...");
            logi("Executing data object (Op Code = 4)");
            writeExecute();
        }
    }

    /* renamed from: no.nordicsemi.android.dfu.SecureDfuImpl$ObjectInfo */
    private static class ObjectInfo extends ObjectChecksum {
        int maxSize;

        private ObjectInfo() {
            super();
        }
    }

    /* renamed from: no.nordicsemi.android.dfu.SecureDfuImpl$ObjectChecksum */
    private static class ObjectChecksum {
        int CRC32;
        int offset;

        private ObjectChecksum() {
        }
    }
}
