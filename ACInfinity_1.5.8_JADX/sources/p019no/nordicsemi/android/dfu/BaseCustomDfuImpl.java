package p019no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import java.io.IOException;
import java.util.UUID;
import java.util.zip.CRC32;
import p019no.nordicsemi.android.dfu.BaseDfuImpl;
import p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException;
import p019no.nordicsemi.android.dfu.internal.exception.DfuException;
import p019no.nordicsemi.android.dfu.internal.exception.HexFileValidationException;
import p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException;

/* renamed from: no.nordicsemi.android.dfu.BaseCustomDfuImpl */
abstract class BaseCustomDfuImpl extends BaseDfuImpl {
    boolean mFirmwareUploadInProgress;
    /* access modifiers changed from: private */
    public boolean mInitPacketInProgress;
    int mPacketsBeforeNotification;
    /* access modifiers changed from: private */
    public int mPacketsSentSinceNotification;
    boolean mRemoteErrorOccurred;

    /* access modifiers changed from: protected */
    public abstract UUID getControlPointCharacteristicUUID();

    /* access modifiers changed from: protected */
    public abstract UUID getDfuServiceUUID();

    /* access modifiers changed from: protected */
    public abstract UUID getPacketCharacteristicUUID();

    static /* synthetic */ int access$108(BaseCustomDfuImpl baseCustomDfuImpl) {
        int i = baseCustomDfuImpl.mPacketsSentSinceNotification;
        baseCustomDfuImpl.mPacketsSentSinceNotification = i + 1;
        return i;
    }

    /* renamed from: no.nordicsemi.android.dfu.BaseCustomDfuImpl$BaseCustomBluetoothCallback */
    class BaseCustomBluetoothCallback extends BaseDfuImpl.BaseBluetoothGattCallback {
        /* access modifiers changed from: protected */
        public void onPacketCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        }

        BaseCustomBluetoothCallback() {
            super();
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            boolean z = true;
            if (i == 0) {
                if (!bluetoothGattCharacteristic.getUuid().equals(BaseCustomDfuImpl.this.getPacketCharacteristicUUID())) {
                    DfuBaseService dfuBaseService = BaseCustomDfuImpl.this.mService;
                    dfuBaseService.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                    BaseCustomDfuImpl.this.mRequestCompleted = true;
                } else if (BaseCustomDfuImpl.this.mInitPacketInProgress) {
                    DfuBaseService dfuBaseService2 = BaseCustomDfuImpl.this.mService;
                    dfuBaseService2.sendLogBroadcast(5, "Data written to " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                    boolean unused = BaseCustomDfuImpl.this.mInitPacketInProgress = false;
                } else if (BaseCustomDfuImpl.this.mFirmwareUploadInProgress) {
                    BaseCustomDfuImpl.this.mProgressInfo.addBytesSent(bluetoothGattCharacteristic.getValue().length);
                    BaseCustomDfuImpl.access$108(BaseCustomDfuImpl.this);
                    if (BaseCustomDfuImpl.this.mPacketsBeforeNotification <= 0 || BaseCustomDfuImpl.this.mPacketsSentSinceNotification < BaseCustomDfuImpl.this.mPacketsBeforeNotification) {
                        z = false;
                    }
                    boolean isComplete = BaseCustomDfuImpl.this.mProgressInfo.isComplete();
                    boolean isObjectComplete = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
                    if (!z) {
                        if (isComplete || isObjectComplete) {
                            BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                            BaseCustomDfuImpl.this.notifyLock();
                            return;
                        }
                        try {
                            BaseCustomDfuImpl.this.waitIfPaused();
                            if (!BaseCustomDfuImpl.this.mAborted && BaseCustomDfuImpl.this.mError == 0 && !BaseCustomDfuImpl.this.mRemoteErrorOccurred) {
                                if (!BaseCustomDfuImpl.this.mResetRequestSent) {
                                    int availableObjectSizeIsBytes = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
                                    byte[] bArr = BaseCustomDfuImpl.this.mBuffer;
                                    if (availableObjectSizeIsBytes < bArr.length) {
                                        bArr = new byte[availableObjectSizeIsBytes];
                                    }
                                    BaseCustomDfuImpl.this.writePacket(bluetoothGatt, bluetoothGattCharacteristic, bArr, BaseCustomDfuImpl.this.mFirmwareStream.read(bArr));
                                    return;
                                }
                            }
                            BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                            BaseCustomDfuImpl.this.mService.sendLogBroadcast(15, "Upload terminated");
                            BaseCustomDfuImpl.this.notifyLock();
                            return;
                        } catch (HexFileValidationException unused2) {
                            BaseCustomDfuImpl.this.loge("Invalid HEX file");
                            BaseCustomDfuImpl.this.mError = 4099;
                        } catch (IOException e) {
                            BaseCustomDfuImpl.this.loge("Error while reading the input stream", e);
                            BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_IO_EXCEPTION;
                        }
                    } else {
                        return;
                    }
                } else {
                    onPacketCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
                }
            } else if (BaseCustomDfuImpl.this.mResetRequestSent) {
                BaseCustomDfuImpl.this.mRequestCompleted = true;
            } else {
                BaseCustomDfuImpl baseCustomDfuImpl = BaseCustomDfuImpl.this;
                baseCustomDfuImpl.loge("Characteristic write error: " + i);
                BaseCustomDfuImpl.this.mError = i | 16384;
            }
            BaseCustomDfuImpl.this.notifyLock();
        }

        /* access modifiers changed from: package-private */
        public void handlePacketReceiptNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            if (!BaseCustomDfuImpl.this.mFirmwareUploadInProgress) {
                handleNotification(bluetoothGatt, bluetoothGattCharacteristic);
                return;
            }
            BluetoothGattCharacteristic characteristic = bluetoothGatt.getService(BaseCustomDfuImpl.this.getDfuServiceUUID()).getCharacteristic(BaseCustomDfuImpl.this.getPacketCharacteristicUUID());
            try {
                int unused = BaseCustomDfuImpl.this.mPacketsSentSinceNotification = 0;
                BaseCustomDfuImpl.this.waitIfPaused();
                if (!BaseCustomDfuImpl.this.mAborted && BaseCustomDfuImpl.this.mError == 0 && !BaseCustomDfuImpl.this.mRemoteErrorOccurred) {
                    if (!BaseCustomDfuImpl.this.mResetRequestSent) {
                        boolean isComplete = BaseCustomDfuImpl.this.mProgressInfo.isComplete();
                        boolean isObjectComplete = BaseCustomDfuImpl.this.mProgressInfo.isObjectComplete();
                        if (!isComplete) {
                            if (!isObjectComplete) {
                                int availableObjectSizeIsBytes = BaseCustomDfuImpl.this.mProgressInfo.getAvailableObjectSizeIsBytes();
                                byte[] bArr = BaseCustomDfuImpl.this.mBuffer;
                                if (availableObjectSizeIsBytes < bArr.length) {
                                    bArr = new byte[availableObjectSizeIsBytes];
                                }
                                BaseCustomDfuImpl.this.writePacket(bluetoothGatt, characteristic, bArr, BaseCustomDfuImpl.this.mFirmwareStream.read(bArr));
                                return;
                            }
                        }
                        BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                        BaseCustomDfuImpl.this.notifyLock();
                        return;
                    }
                }
                BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
                BaseCustomDfuImpl.this.mService.sendLogBroadcast(15, "Upload terminated");
            } catch (HexFileValidationException unused2) {
                BaseCustomDfuImpl.this.loge("Invalid HEX file");
                BaseCustomDfuImpl.this.mError = 4099;
            } catch (IOException e) {
                BaseCustomDfuImpl.this.loge("Error while reading the input stream", e);
                BaseCustomDfuImpl.this.mError = DfuBaseService.ERROR_FILE_IO_EXCEPTION;
            }
        }

        /* access modifiers changed from: package-private */
        public void handleNotification(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            DfuBaseService dfuBaseService = BaseCustomDfuImpl.this.mService;
            dfuBaseService.sendLogBroadcast(5, "Notification received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
            BaseCustomDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
            BaseCustomDfuImpl.this.mFirmwareUploadInProgress = false;
        }
    }

    BaseCustomDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
        boolean z = true;
        int i = 0;
        int i2 = 12;
        if (intent.hasExtra(DfuBaseService.EXTRA_PACKET_RECEIPT_NOTIFICATIONS_ENABLED)) {
            boolean booleanExtra = intent.getBooleanExtra(DfuBaseService.EXTRA_PACKET_RECEIPT_NOTIFICATIONS_ENABLED, Build.VERSION.SDK_INT >= 23 ? false : z);
            int intExtra = intent.getIntExtra(DfuBaseService.EXTRA_PACKET_RECEIPT_NOTIFICATIONS_VALUE, 12);
            if (intExtra >= 0 && intExtra <= 65535) {
                i2 = intExtra;
            }
            this.mPacketsBeforeNotification = booleanExtra ? i2 : i;
            return;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(dfuBaseService);
        boolean z2 = defaultSharedPreferences.getBoolean(DfuSettingsConstants.SETTINGS_PACKET_RECEIPT_NOTIFICATION_ENABLED, Build.VERSION.SDK_INT >= 23 ? false : z);
        try {
            int parseInt = Integer.parseInt(defaultSharedPreferences.getString(DfuSettingsConstants.SETTINGS_NUMBER_OF_PACKETS, String.valueOf(12)));
            if (parseInt >= 0 && parseInt <= 65535) {
                i2 = parseInt;
            }
        } catch (NumberFormatException unused) {
        }
        this.mPacketsBeforeNotification = z2 ? i2 : i;
    }

    /* access modifiers changed from: package-private */
    public void writeInitData(BluetoothGattCharacteristic bluetoothGattCharacteristic, CRC32 crc32) throws DfuException, DeviceDisconnectedException, UploadAbortedException {
        try {
            byte[] bArr = this.mBuffer;
            while (true) {
                int read = this.mInitPacketStream.read(bArr, 0, bArr.length);
                if (read != -1) {
                    writeInitPacket(bluetoothGattCharacteristic, bArr, read);
                    if (crc32 != null) {
                        crc32.update(bArr, 0, read);
                    }
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            loge("Error while reading Init packet file", e);
            throw new DfuException("Error while reading Init packet file", 4098);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeInitPacket(android.bluetooth.BluetoothGattCharacteristic r4, byte[] r5, int r6) throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r3 = this;
            boolean r0 = r3.mAborted
            if (r0 != 0) goto L_0x00b6
            int r0 = r5.length
            r1 = 0
            if (r0 == r6) goto L_0x000e
            byte[] r0 = new byte[r6]
            java.lang.System.arraycopy(r5, r1, r0, r1, r6)
            r5 = r0
        L_0x000e:
            r6 = 0
            r3.mReceivedData = r6
            r3.mError = r1
            r6 = 1
            r3.mInitPacketInProgress = r6
            r4.setWriteType(r6)
            r4.setValue(r5)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Sending init packet (Value = "
            r0.append(r2)
            java.lang.String r5 = r3.parse(r5)
            r0.append(r5)
            java.lang.String r5 = ")"
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r3.logi(r5)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r3.mService
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "Writing to characteristic "
            r0.append(r2)
            java.util.UUID r2 = r4.getUuid()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r5.sendLogBroadcast(r6, r0)
            no.nordicsemi.android.dfu.DfuBaseService r5 = r3.mService
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r0 = "gatt.writeCharacteristic("
            r6.append(r0)
            java.util.UUID r0 = r4.getUuid()
            r6.append(r0)
            java.lang.String r0 = ")"
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.sendLogBroadcast(r1, r6)
            android.bluetooth.BluetoothGatt r5 = r3.mGatt
            r5.writeCharacteristic(r4)
            java.lang.Object r4 = r3.mLock     // Catch:{ InterruptedException -> 0x0095 }
            monitor-enter(r4)     // Catch:{ InterruptedException -> 0x0095 }
        L_0x007a:
            boolean r5 = r3.mInitPacketInProgress     // Catch:{ all -> 0x0092 }
            if (r5 == 0) goto L_0x0086
            boolean r5 = r3.mConnected     // Catch:{ all -> 0x0092 }
            if (r5 == 0) goto L_0x0086
            int r5 = r3.mError     // Catch:{ all -> 0x0092 }
            if (r5 == 0) goto L_0x008a
        L_0x0086:
            boolean r5 = r3.mPaused     // Catch:{ all -> 0x0092 }
            if (r5 == 0) goto L_0x0090
        L_0x008a:
            java.lang.Object r5 = r3.mLock     // Catch:{ all -> 0x0092 }
            r5.wait()     // Catch:{ all -> 0x0092 }
            goto L_0x007a
        L_0x0090:
            monitor-exit(r4)     // Catch:{ all -> 0x0092 }
            goto L_0x009b
        L_0x0092:
            r5 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0092 }
            throw r5     // Catch:{ InterruptedException -> 0x0095 }
        L_0x0095:
            r4 = move-exception
            java.lang.String r5 = "Sleeping interrupted"
            r3.loge(r5, r4)
        L_0x009b:
            boolean r4 = r3.mConnected
            if (r4 == 0) goto L_0x00ae
            int r4 = r3.mError
            if (r4 != 0) goto L_0x00a4
            return
        L_0x00a4:
            no.nordicsemi.android.dfu.internal.exception.DfuException r4 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.String r5 = "Unable to write Init DFU Parameters"
            int r6 = r3.mError
            r4.<init>(r5, r6)
            throw r4
        L_0x00ae:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r4 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r5 = "Unable to write Init DFU Parameters: device disconnected"
            r4.<init>(r5)
            throw r4
        L_0x00b6:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r4 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r4.<init>()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.BaseCustomDfuImpl.writeInitPacket(android.bluetooth.BluetoothGattCharacteristic, byte[], int):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0072  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uploadFirmwareImage(android.bluetooth.BluetoothGattCharacteristic r7) throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r6 = this;
            boolean r0 = r6.mAborted
            if (r0 != 0) goto L_0x009d
            r0 = 0
            r6.mReceivedData = r0
            r0 = 0
            r6.mError = r0
            r1 = 1
            r6.mFirmwareUploadInProgress = r1
            r6.mPacketsSentSinceNotification = r0
            no.nordicsemi.android.dfu.DfuProgressInfo r0 = r6.mProgressInfo     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            int r0 = r0.getAvailableObjectSizeIsBytes()     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            byte[] r2 = r6.mBuffer     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            int r3 = r2.length     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            if (r0 >= r3) goto L_0x001c
            byte[] r2 = new byte[r0]     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
        L_0x001c:
            java.io.InputStream r0 = r6.mFirmwareStream     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            int r0 = r0.read(r2)     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            no.nordicsemi.android.dfu.DfuBaseService r3 = r6.mService     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            r4.<init>()     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            java.lang.String r5 = "Sending firmware to characteristic "
            r4.append(r5)     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            java.util.UUID r5 = r7.getUuid()     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            r4.append(r5)     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            java.lang.String r5 = "..."
            r4.append(r5)     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            java.lang.String r4 = r4.toString()     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            r3.sendLogBroadcast(r1, r4)     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            android.bluetooth.BluetoothGatt r1 = r6.mGatt     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            r6.writePacket(r1, r7, r2, r0)     // Catch:{ HexFileValidationException -> 0x0093, IOException -> 0x0089 }
            java.lang.Object r7 = r6.mLock     // Catch:{ InterruptedException -> 0x0068 }
            monitor-enter(r7)     // Catch:{ InterruptedException -> 0x0068 }
        L_0x0049:
            boolean r0 = r6.mFirmwareUploadInProgress     // Catch:{ all -> 0x0065 }
            if (r0 == 0) goto L_0x0059
            byte[] r0 = r6.mReceivedData     // Catch:{ all -> 0x0065 }
            if (r0 != 0) goto L_0x0059
            boolean r0 = r6.mConnected     // Catch:{ all -> 0x0065 }
            if (r0 == 0) goto L_0x0059
            int r0 = r6.mError     // Catch:{ all -> 0x0065 }
            if (r0 == 0) goto L_0x005d
        L_0x0059:
            boolean r0 = r6.mPaused     // Catch:{ all -> 0x0065 }
            if (r0 == 0) goto L_0x0063
        L_0x005d:
            java.lang.Object r0 = r6.mLock     // Catch:{ all -> 0x0065 }
            r0.wait()     // Catch:{ all -> 0x0065 }
            goto L_0x0049
        L_0x0063:
            monitor-exit(r7)     // Catch:{ all -> 0x0065 }
            goto L_0x006e
        L_0x0065:
            r0 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0065 }
            throw r0     // Catch:{ InterruptedException -> 0x0068 }
        L_0x0068:
            r7 = move-exception
            java.lang.String r0 = "Sleeping interrupted"
            r6.loge(r0, r7)
        L_0x006e:
            boolean r7 = r6.mConnected
            if (r7 == 0) goto L_0x0081
            int r7 = r6.mError
            if (r7 != 0) goto L_0x0077
            return
        L_0x0077:
            no.nordicsemi.android.dfu.internal.exception.DfuException r7 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.String r0 = "Uploading Firmware Image failed"
            int r1 = r6.mError
            r7.<init>(r0, r1)
            throw r7
        L_0x0081:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r7 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r0 = "Uploading Firmware Image failed: device disconnected"
            r7.<init>(r0)
            throw r7
        L_0x0089:
            no.nordicsemi.android.dfu.internal.exception.DfuException r7 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.String r0 = "Error while reading file"
            r1 = 4100(0x1004, float:5.745E-42)
            r7.<init>(r0, r1)
            throw r7
        L_0x0093:
            no.nordicsemi.android.dfu.internal.exception.DfuException r7 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.String r0 = "HEX file not valid"
            r1 = 4099(0x1003, float:5.744E-42)
            r7.<init>(r0, r1)
            throw r7
        L_0x009d:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r7 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.BaseCustomDfuImpl.uploadFirmwareImage(android.bluetooth.BluetoothGattCharacteristic):void");
    }

    /* access modifiers changed from: private */
    public void writePacket(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, byte[] bArr, int i) {
        if (i > 0) {
            if (bArr.length != i) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr = bArr2;
            }
            bluetoothGattCharacteristic.setWriteType(1);
            bluetoothGattCharacteristic.setValue(bArr);
            bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        }
    }

    /* access modifiers changed from: package-private */
    public void finalize(Intent intent, boolean z) {
        boolean z2;
        boolean z3 = false;
        boolean booleanExtra = intent.getBooleanExtra(DfuBaseService.EXTRA_KEEP_BOND, false);
        this.mService.refreshDeviceCache(this.mGatt, z || !booleanExtra);
        this.mService.close(this.mGatt);
        if (this.mGatt.getDevice().getBondState() == 12) {
            boolean booleanExtra2 = intent.getBooleanExtra(DfuBaseService.EXTRA_RESTORE_BOND, false);
            if (booleanExtra2 || !booleanExtra) {
                removeBond();
                this.mService.waitFor(2000);
                z2 = true;
            } else {
                z2 = false;
            }
            if (!booleanExtra2 || (this.mFileType & 4) <= 0) {
                z3 = z2;
            } else if (!createBond()) {
                logw("Creating bond failed");
            }
        }
        if (this.mProgressInfo.isLastPart()) {
            if (!z3) {
                this.mService.waitFor(1400);
            }
            this.mProgressInfo.setProgress(-6);
            return;
        }
        logi("Starting service that will upload application");
        Intent intent2 = new Intent();
        intent2.fillIn(intent, 24);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_MIME_TYPE, DfuBaseService.MIME_TYPE_ZIP);
        intent2.putExtra(DfuBaseService.EXTRA_FILE_TYPE, 4);
        intent2.putExtra(DfuBaseService.EXTRA_PART_CURRENT, this.mProgressInfo.getCurrentPart() + 1);
        intent2.putExtra(DfuBaseService.EXTRA_PARTS_TOTAL, this.mProgressInfo.getTotalParts());
        restartService(intent2, true);
    }
}
