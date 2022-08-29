package p019no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.UUID;
import p019no.nordicsemi.android.dfu.DfuCallback;
import p019no.nordicsemi.android.dfu.internal.scanner.BootloaderScannerFactory;

/* renamed from: no.nordicsemi.android.dfu.BaseDfuImpl */
abstract class BaseDfuImpl implements DfuService {
    static final UUID CLIENT_CHARACTERISTIC_CONFIG = new UUID(45088566677504L, -9223371485494954757L);
    static final UUID GENERIC_ATTRIBUTE_SERVICE_UUID = new UUID(26392574038016L, -9223371485494954757L);
    /* access modifiers changed from: private */
    public static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    static final int INDICATIONS = 2;
    private static final int MAX_PACKET_SIZE_DEFAULT = 20;
    static final int NOTIFICATIONS = 1;
    static final UUID SERVICE_CHANGED_UUID = new UUID(46200963207168L, -9223371485494954757L);
    private static final String TAG = "DfuImpl";
    boolean mAborted;
    byte[] mBuffer = new byte[20];
    boolean mConnected;
    /* access modifiers changed from: private */
    public int mCurrentMtu;
    int mError;
    int mFileType;
    InputStream mFirmwareStream;
    BluetoothGatt mGatt;
    int mImageSizeInBytes;
    int mInitPacketSizeInBytes;
    InputStream mInitPacketStream;
    final Object mLock = new Object();
    boolean mPaused;
    DfuProgressInfo mProgressInfo;
    byte[] mReceivedData = null;
    boolean mRequestCompleted;
    boolean mResetRequestSent;
    DfuBaseService mService;

    /* renamed from: no.nordicsemi.android.dfu.BaseDfuImpl$BaseBluetoothGattCallback */
    protected class BaseBluetoothGattCallback extends DfuCallback.DfuGattCallback {
        protected BaseBluetoothGattCallback() {
        }

        public void onDisconnected() {
            BaseDfuImpl.this.mConnected = false;
            BaseDfuImpl.this.notifyLock();
        }

        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            if (i == 0) {
                DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "Read Response received from " + bluetoothGattCharacteristic.getUuid() + ", value (0x): " + parse(bluetoothGattCharacteristic));
                BaseDfuImpl.this.mReceivedData = bluetoothGattCharacteristic.getValue();
                BaseDfuImpl.this.mRequestCompleted = true;
            } else {
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.loge("Characteristic read error: " + i);
                BaseDfuImpl.this.mError = i | 16384;
            }
            BaseDfuImpl.this.notifyLock();
        }

        public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i != 0) {
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.loge("Descriptor read error: " + i);
                BaseDfuImpl.this.mError = i | 16384;
            } else if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "Read Response received from descr." + bluetoothGattDescriptor.getCharacteristic().getUuid() + ", value (0x): " + parse(bluetoothGattDescriptor));
                if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                    BaseDfuImpl.this.mRequestCompleted = true;
                } else {
                    BaseDfuImpl.this.loge("Unknown descriptor read");
                }
            }
            BaseDfuImpl.this.notifyLock();
        }

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            if (i != 0) {
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.loge("Descriptor write error: " + i);
                BaseDfuImpl.this.mError = i | 16384;
            } else if (BaseDfuImpl.CLIENT_CHARACTERISTIC_CONFIG.equals(bluetoothGattDescriptor.getUuid())) {
                DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "Data written to descr." + bluetoothGattDescriptor.getCharacteristic().getUuid() + ", value (0x): " + parse(bluetoothGattDescriptor));
                if (BaseDfuImpl.SERVICE_CHANGED_UUID.equals(bluetoothGattDescriptor.getCharacteristic().getUuid())) {
                    DfuBaseService dfuBaseService2 = BaseDfuImpl.this.mService;
                    dfuBaseService2.sendLogBroadcast(1, "Indications enabled for " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                } else {
                    DfuBaseService dfuBaseService3 = BaseDfuImpl.this.mService;
                    dfuBaseService3.sendLogBroadcast(1, "Notifications enabled for " + bluetoothGattDescriptor.getCharacteristic().getUuid());
                }
            }
            BaseDfuImpl.this.notifyLock();
        }

        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            if (i2 == 0) {
                DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "MTU changed to: " + i);
                int i3 = i + -3;
                if (i3 > BaseDfuImpl.this.mBuffer.length) {
                    BaseDfuImpl.this.mBuffer = new byte[i3];
                }
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.logi("MTU changed to: " + i);
            } else {
                BaseDfuImpl baseDfuImpl2 = BaseDfuImpl.this;
                baseDfuImpl2.logw("Changing MTU failed: " + i2 + " (mtu: " + i + ")");
                if (i2 == 4 && BaseDfuImpl.this.mCurrentMtu > 23 && BaseDfuImpl.this.mCurrentMtu - 3 > BaseDfuImpl.this.mBuffer.length) {
                    BaseDfuImpl baseDfuImpl3 = BaseDfuImpl.this;
                    baseDfuImpl3.mBuffer = new byte[(baseDfuImpl3.mCurrentMtu - 3)];
                    BaseDfuImpl baseDfuImpl4 = BaseDfuImpl.this;
                    baseDfuImpl4.logi("MTU restored to: " + BaseDfuImpl.this.mCurrentMtu);
                }
            }
            BaseDfuImpl.this.mRequestCompleted = true;
            BaseDfuImpl.this.notifyLock();
        }

        public void onPhyUpdate(BluetoothGatt bluetoothGatt, int i, int i2, int i3) {
            if (i3 == 0) {
                DfuBaseService dfuBaseService = BaseDfuImpl.this.mService;
                dfuBaseService.sendLogBroadcast(5, "PHY updated (TX: " + phyToString(i) + ", RX: " + phyToString(i2) + ")");
                BaseDfuImpl baseDfuImpl = BaseDfuImpl.this;
                baseDfuImpl.logi("PHY updated (TX: " + phyToString(i) + ", RX: " + phyToString(i2) + ")");
                return;
            }
            BaseDfuImpl baseDfuImpl2 = BaseDfuImpl.this;
            baseDfuImpl2.logw("Updating PHY failed: " + i3 + " (txPhy: " + i + ", rxPhy: " + i2 + ")");
        }

        /* access modifiers changed from: protected */
        public String parse(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            return parse(bluetoothGattCharacteristic.getValue());
        }

        /* access modifiers changed from: protected */
        public String parse(BluetoothGattDescriptor bluetoothGattDescriptor) {
            return parse(bluetoothGattDescriptor.getValue());
        }

        private String parse(byte[] bArr) {
            int length;
            if (bArr == null || (length = bArr.length) == 0) {
                return "";
            }
            char[] cArr = new char[((length * 3) - 1)];
            for (int i = 0; i < length; i++) {
                byte b = bArr[i] & 255;
                int i2 = i * 3;
                cArr[i2] = BaseDfuImpl.HEX_ARRAY[b >>> 4];
                cArr[i2 + 1] = BaseDfuImpl.HEX_ARRAY[b & 15];
                if (i != length - 1) {
                    cArr[i2 + 2] = '-';
                }
            }
            return new String(cArr);
        }

        private String phyToString(int i) {
            if (i == 1) {
                return "LE 1M";
            }
            if (i == 2) {
                return "LE 2M";
            }
            if (i == 3) {
                return "LE Coded";
            }
            return "UNKNOWN (" + i + ")";
        }
    }

    BaseDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        this.mService = dfuBaseService;
        this.mProgressInfo = dfuBaseService.mProgressInfo;
        this.mConnected = true;
    }

    public void release() {
        this.mService = null;
    }

    public void pause() {
        this.mPaused = true;
    }

    public void resume() {
        this.mPaused = false;
        notifyLock();
    }

    public void abort() {
        this.mPaused = false;
        this.mAborted = true;
        notifyLock();
    }

    public void onBondStateChanged(int i) {
        this.mRequestCompleted = true;
        notifyLock();
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062 A[Catch:{ Exception -> 0x0075 }] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean initialize(android.content.Intent r6, android.bluetooth.BluetoothGatt r7, int r8, java.io.InputStream r9, java.io.InputStream r10) throws p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r5 = this;
            r5.mGatt = r7
            r5.mFileType = r8
            r5.mFirmwareStream = r9
            r5.mInitPacketStream = r10
            java.lang.String r0 = "no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT"
            r1 = 1
            int r0 = r6.getIntExtra(r0, r1)
            java.lang.String r2 = "no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL"
            int r2 = r6.getIntExtra(r2, r1)
            java.lang.String r3 = "no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU"
            r4 = 23
            int r6 = r6.getIntExtra(r3, r4)
            r5.mCurrentMtu = r6
            r6 = 15
            r3 = 2
            r4 = 4
            if (r8 <= r4) goto L_0x003f
            java.lang.String r8 = "DFU target does not support (SD/BL)+App update, splitting into 2 parts"
            r5.logw(r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r5.mService
            java.lang.String r2 = "Sending system components"
            r8.sendLogBroadcast(r6, r2)
            int r8 = r5.mFileType
            r8 = r8 & -5
            r5.mFileType = r8
            java.io.InputStream r2 = r5.mFirmwareStream
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r2 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r2
            r2.setContentType(r8)
            r2 = 2
        L_0x003f:
            if (r0 != r3) goto L_0x0048
            no.nordicsemi.android.dfu.DfuBaseService r8 = r5.mService
            java.lang.String r4 = "Sending application"
            r8.sendLogBroadcast(r6, r4)
        L_0x0048:
            r6 = 0
            if (r10 == 0) goto L_0x0059
            boolean r8 = r10.markSupported()     // Catch:{ Exception -> 0x0059 }
            if (r8 == 0) goto L_0x0054
            r10.reset()     // Catch:{ Exception -> 0x0059 }
        L_0x0054:
            int r8 = r10.available()     // Catch:{ Exception -> 0x0059 }
            goto L_0x005a
        L_0x0059:
            r8 = 0
        L_0x005a:
            r5.mInitPacketSizeInBytes = r8
            boolean r8 = r9.markSupported()     // Catch:{ Exception -> 0x0075 }
            if (r8 == 0) goto L_0x0070
            boolean r8 = r9 instanceof p019no.nordicsemi.android.dfu.internal.ArchiveInputStream     // Catch:{ Exception -> 0x0075 }
            if (r8 == 0) goto L_0x006d
            r8 = r9
            no.nordicsemi.android.dfu.internal.ArchiveInputStream r8 = (p019no.nordicsemi.android.dfu.internal.ArchiveInputStream) r8     // Catch:{ Exception -> 0x0075 }
            r8.fullReset()     // Catch:{ Exception -> 0x0075 }
            goto L_0x0070
        L_0x006d:
            r9.reset()     // Catch:{ Exception -> 0x0075 }
        L_0x0070:
            int r6 = r9.available()     // Catch:{ Exception -> 0x0075 }
            goto L_0x0076
        L_0x0075:
        L_0x0076:
            r5.mImageSizeInBytes = r6
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r5.mProgressInfo
            r8.init(r6, r0, r2)
            android.bluetooth.BluetoothDevice r6 = r7.getDevice()
            int r6 = r6.getBondState()
            r8 = 12
            if (r6 != r8) goto L_0x00ae
            java.util.UUID r6 = GENERIC_ATTRIBUTE_SERVICE_UUID
            android.bluetooth.BluetoothGattService r6 = r7.getService(r6)
            if (r6 == 0) goto L_0x00ae
            java.util.UUID r7 = SERVICE_CHANGED_UUID
            android.bluetooth.BluetoothGattCharacteristic r6 = r6.getCharacteristic(r7)
            if (r6 == 0) goto L_0x00ae
            boolean r7 = r5.isServiceChangedCCCDEnabled()
            if (r7 != 0) goto L_0x00a2
            r5.enableCCCD(r6, r3)
        L_0x00a2:
            java.lang.String r6 = "Service Changed indications enabled"
            r5.logi(r6)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r5.mService
            r8 = 10
            r7.sendLogBroadcast(r8, r6)
        L_0x00ae:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.BaseDfuImpl.initialize(android.content.Intent, android.bluetooth.BluetoothGatt, int, java.io.InputStream, java.io.InputStream):boolean");
    }

    /* access modifiers changed from: package-private */
    public void notifyLock() {
        synchronized (this.mLock) {
            this.mLock.notifyAll();
        }
    }

    /* access modifiers changed from: package-private */
    public void waitIfPaused() {
        try {
            synchronized (this.mLock) {
                while (this.mPaused) {
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00db, code lost:
        if (r10.mError == 0) goto L_0x00e1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0139  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enableCCCD(android.bluetooth.BluetoothGattCharacteristic r11, int r12) throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r10 = this;
            android.bluetooth.BluetoothGatt r0 = r10.mGatt
            r1 = 1
            if (r12 != r1) goto L_0x0008
            java.lang.String r2 = "notifications"
            goto L_0x000a
        L_0x0008:
            java.lang.String r2 = "indications"
        L_0x000a:
            boolean r3 = r10.mConnected
            if (r3 == 0) goto L_0x015b
            boolean r3 = r10.mAborted
            if (r3 != 0) goto L_0x0155
            r3 = 0
            r10.mReceivedData = r3
            r3 = 0
            r10.mError = r3
            java.util.UUID r4 = CLIENT_CHARACTERISTIC_CONFIG
            android.bluetooth.BluetoothGattDescriptor r4 = r11.getDescriptor(r4)
            byte[] r5 = r4.getValue()
            r6 = 2
            if (r5 == 0) goto L_0x003e
            byte[] r5 = r4.getValue()
            int r5 = r5.length
            if (r5 != r6) goto L_0x003e
            byte[] r5 = r4.getValue()
            byte r5 = r5[r3]
            if (r5 <= 0) goto L_0x003e
            byte[] r5 = r4.getValue()
            byte r5 = r5[r1]
            if (r5 != 0) goto L_0x003e
            r5 = 1
            goto L_0x003f
        L_0x003e:
            r5 = 0
        L_0x003f:
            if (r5 == 0) goto L_0x0042
            return
        L_0x0042:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "Enabling "
            r7.append(r8)
            r7.append(r2)
            java.lang.String r8 = "..."
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            r10.logi(r7)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r10.mService
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "Enabling "
            r8.append(r9)
            r8.append(r2)
            java.lang.String r9 = " for "
            r8.append(r9)
            java.util.UUID r9 = r11.getUuid()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.sendLogBroadcast(r1, r8)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r10.mService
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "gatt.setCharacteristicNotification("
            r8.append(r9)
            java.util.UUID r9 = r11.getUuid()
            r8.append(r9)
            java.lang.String r9 = ", true)"
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r7.sendLogBroadcast(r3, r8)
            r0.setCharacteristicNotification(r11, r1)
            if (r12 != r1) goto L_0x00a4
            byte[] r11 = android.bluetooth.BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
            goto L_0x00a6
        L_0x00a4:
            byte[] r11 = android.bluetooth.BluetoothGattDescriptor.ENABLE_INDICATION_VALUE
        L_0x00a6:
            r4.setValue(r11)
            no.nordicsemi.android.dfu.DfuBaseService r11 = r10.mService
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r8 = "gatt.writeDescriptor("
            r7.append(r8)
            java.util.UUID r8 = r4.getUuid()
            r7.append(r8)
            if (r12 != r1) goto L_0x00c1
            java.lang.String r12 = ", value=0x01-00)"
            goto L_0x00c3
        L_0x00c1:
            java.lang.String r12 = ", value=0x02-00)"
        L_0x00c3:
            r7.append(r12)
            java.lang.String r12 = r7.toString()
            r11.sendLogBroadcast(r3, r12)
            r0.writeDescriptor(r4)
            java.lang.Object r11 = r10.mLock     // Catch:{ InterruptedException -> 0x010c }
            monitor-enter(r11)     // Catch:{ InterruptedException -> 0x010c }
        L_0x00d3:
            if (r5 != 0) goto L_0x00dd
            boolean r12 = r10.mConnected     // Catch:{ all -> 0x0109 }
            if (r12 == 0) goto L_0x00dd
            int r12 = r10.mError     // Catch:{ all -> 0x0109 }
            if (r12 == 0) goto L_0x00e1
        L_0x00dd:
            boolean r12 = r10.mPaused     // Catch:{ all -> 0x0109 }
            if (r12 == 0) goto L_0x0107
        L_0x00e1:
            java.lang.Object r12 = r10.mLock     // Catch:{ all -> 0x0109 }
            r12.wait()     // Catch:{ all -> 0x0109 }
            byte[] r12 = r4.getValue()     // Catch:{ all -> 0x0109 }
            if (r12 == 0) goto L_0x0105
            byte[] r12 = r4.getValue()     // Catch:{ all -> 0x0109 }
            int r12 = r12.length     // Catch:{ all -> 0x0109 }
            if (r12 != r6) goto L_0x0105
            byte[] r12 = r4.getValue()     // Catch:{ all -> 0x0109 }
            byte r12 = r12[r3]     // Catch:{ all -> 0x0109 }
            if (r12 <= 0) goto L_0x0105
            byte[] r12 = r4.getValue()     // Catch:{ all -> 0x0109 }
            byte r12 = r12[r1]     // Catch:{ all -> 0x0109 }
            if (r12 != 0) goto L_0x0105
            r5 = 1
            goto L_0x00d3
        L_0x0105:
            r5 = 0
            goto L_0x00d3
        L_0x0107:
            monitor-exit(r11)     // Catch:{ all -> 0x0109 }
            goto L_0x0112
        L_0x0109:
            r12 = move-exception
            monitor-exit(r11)     // Catch:{ all -> 0x0109 }
            throw r12     // Catch:{ InterruptedException -> 0x010c }
        L_0x010c:
            r11 = move-exception
            java.lang.String r12 = "Sleeping interrupted"
            r10.loge(r12, r11)
        L_0x0112:
            boolean r11 = r10.mConnected
            if (r11 == 0) goto L_0x0139
            int r11 = r10.mError
            if (r11 != 0) goto L_0x011b
            return
        L_0x011b:
            no.nordicsemi.android.dfu.internal.exception.DfuException r11 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Unable to set "
            r12.append(r0)
            r12.append(r2)
            java.lang.String r0 = " state"
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            int r0 = r10.mError
            r11.<init>(r12, r0)
            throw r11
        L_0x0139:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r11 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Unable to set "
            r12.append(r0)
            r12.append(r2)
            java.lang.String r0 = " state: device disconnected"
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        L_0x0155:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r11 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r11.<init>()
            throw r11
        L_0x015b:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r11 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            r12.<init>()
            java.lang.String r0 = "Unable to set "
            r12.append(r0)
            r12.append(r2)
            java.lang.String r0 = " state: device disconnected"
            r12.append(r0)
            java.lang.String r12 = r12.toString()
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.BaseDfuImpl.enableCCCD(android.bluetooth.BluetoothGattCharacteristic, int):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b7  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isServiceChangedCCCDEnabled() throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r7 = this;
            boolean r0 = r7.mConnected
            if (r0 == 0) goto L_0x00c5
            boolean r0 = r7.mAborted
            if (r0 != 0) goto L_0x00bf
            android.bluetooth.BluetoothGatt r0 = r7.mGatt
            java.util.UUID r1 = GENERIC_ATTRIBUTE_SERVICE_UUID
            android.bluetooth.BluetoothGattService r1 = r0.getService(r1)
            r2 = 0
            if (r1 != 0) goto L_0x0014
            return r2
        L_0x0014:
            java.util.UUID r3 = SERVICE_CHANGED_UUID
            android.bluetooth.BluetoothGattCharacteristic r1 = r1.getCharacteristic(r3)
            if (r1 != 0) goto L_0x001d
            return r2
        L_0x001d:
            java.util.UUID r3 = CLIENT_CHARACTERISTIC_CONFIG
            android.bluetooth.BluetoothGattDescriptor r1 = r1.getDescriptor(r3)
            if (r1 != 0) goto L_0x0026
            return r2
        L_0x0026:
            r7.mRequestCompleted = r2
            r7.mError = r2
            java.lang.String r3 = "Reading Service Changed CCCD value..."
            r7.logi(r3)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r7.mService
            java.lang.String r4 = "Reading Service Changed CCCD value..."
            r5 = 1
            r3.sendLogBroadcast(r5, r4)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r7.mService
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r6 = "gatt.readDescriptor("
            r4.append(r6)
            java.util.UUID r6 = r1.getUuid()
            r4.append(r6)
            java.lang.String r6 = ")"
            r4.append(r6)
            java.lang.String r4 = r4.toString()
            r3.sendLogBroadcast(r2, r4)
            r0.readDescriptor(r1)
            java.lang.Object r0 = r7.mLock     // Catch:{ InterruptedException -> 0x0077 }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0077 }
        L_0x005c:
            boolean r3 = r7.mRequestCompleted     // Catch:{ all -> 0x0074 }
            if (r3 != 0) goto L_0x0068
            boolean r3 = r7.mConnected     // Catch:{ all -> 0x0074 }
            if (r3 == 0) goto L_0x0068
            int r3 = r7.mError     // Catch:{ all -> 0x0074 }
            if (r3 == 0) goto L_0x006c
        L_0x0068:
            boolean r3 = r7.mPaused     // Catch:{ all -> 0x0074 }
            if (r3 == 0) goto L_0x0072
        L_0x006c:
            java.lang.Object r3 = r7.mLock     // Catch:{ all -> 0x0074 }
            r3.wait()     // Catch:{ all -> 0x0074 }
            goto L_0x005c
        L_0x0072:
            monitor-exit(r0)     // Catch:{ all -> 0x0074 }
            goto L_0x007d
        L_0x0074:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0074 }
            throw r3     // Catch:{ InterruptedException -> 0x0077 }
        L_0x0077:
            r0 = move-exception
            java.lang.String r3 = "Sleeping interrupted"
            r7.loge(r3, r0)
        L_0x007d:
            boolean r0 = r7.mConnected
            if (r0 == 0) goto L_0x00b7
            int r0 = r7.mError
            if (r0 != 0) goto L_0x00ad
            byte[] r0 = r1.getValue()
            if (r0 == 0) goto L_0x00ac
            byte[] r0 = r1.getValue()
            int r0 = r0.length
            r3 = 2
            if (r0 != r3) goto L_0x00ac
            byte[] r0 = r1.getValue()
            byte r0 = r0[r2]
            byte[] r3 = android.bluetooth.BluetoothGattDescriptor.ENABLE_INDICATION_VALUE
            byte r3 = r3[r2]
            if (r0 != r3) goto L_0x00ac
            byte[] r0 = r1.getValue()
            byte r0 = r0[r5]
            byte[] r1 = android.bluetooth.BluetoothGattDescriptor.ENABLE_INDICATION_VALUE
            byte r1 = r1[r5]
            if (r0 != r1) goto L_0x00ac
            r2 = 1
        L_0x00ac:
            return r2
        L_0x00ad:
            no.nordicsemi.android.dfu.internal.exception.DfuException r0 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.String r1 = "Unable to read Service Changed CCCD"
            int r2 = r7.mError
            r0.<init>(r1, r2)
            throw r0
        L_0x00b7:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r0 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r1 = "Unable to read Service Changed CCCD: device disconnected"
            r0.<init>(r1)
            throw r0
        L_0x00bf:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r0 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r0.<init>()
            throw r0
        L_0x00c5:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r0 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r1 = "Unable to read Service Changed CCCD: device disconnected"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.BaseDfuImpl.isServiceChangedCCCDEnabled():boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void writeOpCode(android.bluetooth.BluetoothGattCharacteristic r5, byte[] r6, boolean r7) throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r4 = this;
            boolean r0 = r4.mAborted
            if (r0 != 0) goto L_0x00c2
            r0 = 0
            r4.mReceivedData = r0
            r0 = 0
            r4.mError = r0
            r4.mRequestCompleted = r0
            r4.mResetRequestSent = r7
            r7 = 2
            r5.setWriteType(r7)
            r5.setValue(r6)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r4.mService
            r1 = 1
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Writing to characteristic "
            r2.append(r3)
            java.util.UUID r3 = r5.getUuid()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r7.sendLogBroadcast(r1, r2)
            no.nordicsemi.android.dfu.DfuBaseService r7 = r4.mService
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "gatt.writeCharacteristic("
            r1.append(r2)
            java.util.UUID r2 = r5.getUuid()
            r1.append(r2)
            java.lang.String r2 = ")"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r7.sendLogBroadcast(r0, r1)
            android.bluetooth.BluetoothGatt r7 = r4.mGatt
            r7.writeCharacteristic(r5)
            java.lang.Object r5 = r4.mLock     // Catch:{ InterruptedException -> 0x0072 }
            monitor-enter(r5)     // Catch:{ InterruptedException -> 0x0072 }
        L_0x0057:
            boolean r7 = r4.mRequestCompleted     // Catch:{ all -> 0x006f }
            if (r7 != 0) goto L_0x0063
            boolean r7 = r4.mConnected     // Catch:{ all -> 0x006f }
            if (r7 == 0) goto L_0x0063
            int r7 = r4.mError     // Catch:{ all -> 0x006f }
            if (r7 == 0) goto L_0x0067
        L_0x0063:
            boolean r7 = r4.mPaused     // Catch:{ all -> 0x006f }
            if (r7 == 0) goto L_0x006d
        L_0x0067:
            java.lang.Object r7 = r4.mLock     // Catch:{ all -> 0x006f }
            r7.wait()     // Catch:{ all -> 0x006f }
            goto L_0x0057
        L_0x006d:
            monitor-exit(r5)     // Catch:{ all -> 0x006f }
            goto L_0x0078
        L_0x006f:
            r7 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x006f }
            throw r7     // Catch:{ InterruptedException -> 0x0072 }
        L_0x0072:
            r5 = move-exception
            java.lang.String r7 = "Sleeping interrupted"
            r4.loge(r7, r5)
        L_0x0078:
            boolean r5 = r4.mResetRequestSent
            if (r5 != 0) goto L_0x009f
            boolean r7 = r4.mConnected
            if (r7 == 0) goto L_0x0081
            goto L_0x009f
        L_0x0081:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r5 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r1 = "Unable to write Op Code "
            r7.append(r1)
            byte r6 = r6[r0]
            r7.append(r6)
            java.lang.String r6 = ": device disconnected"
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r5.<init>(r6)
            throw r5
        L_0x009f:
            if (r5 != 0) goto L_0x00c1
            int r5 = r4.mError
            if (r5 != 0) goto L_0x00a6
            goto L_0x00c1
        L_0x00a6:
            no.nordicsemi.android.dfu.internal.exception.DfuException r5 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.String r1 = "Unable to write Op Code "
            r7.append(r1)
            byte r6 = r6[r0]
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            int r7 = r4.mError
            r5.<init>(r6, r7)
            throw r5
        L_0x00c1:
            return
        L_0x00c2:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r5 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.BaseDfuImpl.writeOpCode(android.bluetooth.BluetoothGattCharacteristic, byte[], boolean):void");
    }

    /* access modifiers changed from: package-private */
    public boolean createBond() {
        boolean z;
        BluetoothDevice device = this.mGatt.getDevice();
        this.mRequestCompleted = false;
        this.mService.sendLogBroadcast(1, "Starting pairing...");
        if (Build.VERSION.SDK_INT >= 19) {
            this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond()");
            z = device.createBond();
        } else {
            z = createBondApi18(device);
        }
        try {
            synchronized (this.mLock) {
                while (z) {
                    if (this.mRequestCompleted || this.mAborted) {
                        break;
                    }
                    this.mLock.wait();
                }
            }
        } catch (InterruptedException e) {
            loge("Sleeping interrupted", e);
        }
        return z;
    }

    private boolean createBondApi18(BluetoothDevice bluetoothDevice) {
        try {
            Method method = bluetoothDevice.getClass().getMethod("createBond", new Class[0]);
            this.mService.sendLogBroadcast(0, "gatt.getDevice().createBond() (hidden)");
            return ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
        } catch (Exception e) {
            Log.w(TAG, "An exception occurred while creating bond", e);
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean removeBond() {
        BluetoothDevice device = this.mGatt.getDevice();
        if (device.getBondState() == 10) {
            return true;
        }
        this.mService.sendLogBroadcast(1, "Removing bond information...");
        boolean z = false;
        try {
            Method method = device.getClass().getMethod("removeBond", new Class[0]);
            this.mRequestCompleted = false;
            this.mService.sendLogBroadcast(0, "gatt.getDevice().removeBond() (hidden)");
            z = ((Boolean) method.invoke(device, new Object[0])).booleanValue();
            try {
                synchronized (this.mLock) {
                    while (!this.mRequestCompleted && !this.mAborted) {
                        this.mLock.wait();
                    }
                }
            } catch (InterruptedException e) {
                loge("Sleeping interrupted", e);
            }
        } catch (Exception e2) {
            Log.w(TAG, "An exception occurred while removing bond information", e2);
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public boolean isBonded() {
        return this.mGatt.getDevice().getBondState() == 12;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x005b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x005c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void requestMtu(int r5) throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r4 = this;
            boolean r0 = r4.mAborted
            if (r0 != 0) goto L_0x0064
            r0 = 0
            r4.mRequestCompleted = r0
            no.nordicsemi.android.dfu.DfuBaseService r1 = r4.mService
            r2 = 1
            java.lang.String r3 = "Requesting new MTU..."
            r1.sendLogBroadcast(r2, r3)
            no.nordicsemi.android.dfu.DfuBaseService r1 = r4.mService
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "gatt.requestMtu("
            r2.append(r3)
            r2.append(r5)
            java.lang.String r3 = ")"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.sendLogBroadcast(r0, r2)
            android.bluetooth.BluetoothGatt r0 = r4.mGatt
            boolean r5 = r0.requestMtu(r5)
            if (r5 != 0) goto L_0x0033
            return
        L_0x0033:
            java.lang.Object r5 = r4.mLock     // Catch:{ InterruptedException -> 0x0051 }
            monitor-enter(r5)     // Catch:{ InterruptedException -> 0x0051 }
        L_0x0036:
            boolean r0 = r4.mRequestCompleted     // Catch:{ all -> 0x004e }
            if (r0 != 0) goto L_0x0042
            boolean r0 = r4.mConnected     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x0042
            int r0 = r4.mError     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x0046
        L_0x0042:
            boolean r0 = r4.mPaused     // Catch:{ all -> 0x004e }
            if (r0 == 0) goto L_0x004c
        L_0x0046:
            java.lang.Object r0 = r4.mLock     // Catch:{ all -> 0x004e }
            r0.wait()     // Catch:{ all -> 0x004e }
            goto L_0x0036
        L_0x004c:
            monitor-exit(r5)     // Catch:{ all -> 0x004e }
            goto L_0x0057
        L_0x004e:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x004e }
            throw r0     // Catch:{ InterruptedException -> 0x0051 }
        L_0x0051:
            r5 = move-exception
            java.lang.String r0 = "Sleeping interrupted"
            r4.loge(r0, r5)
        L_0x0057:
            boolean r5 = r4.mConnected
            if (r5 == 0) goto L_0x005c
            return
        L_0x005c:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r5 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r0 = "Unable to read Service Changed CCCD: device disconnected"
            r5.<init>(r0)
            throw r5
        L_0x0064:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r5 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.BaseDfuImpl.requestMtu(int):void");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] readNotificationResponse() throws p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock     // Catch:{ InterruptedException -> 0x0022 }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x0022 }
        L_0x0003:
            byte[] r1 = r3.mReceivedData     // Catch:{ all -> 0x001f }
            if (r1 != 0) goto L_0x0013
            boolean r1 = r3.mConnected     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0013
            int r1 = r3.mError     // Catch:{ all -> 0x001f }
            if (r1 != 0) goto L_0x0013
            boolean r1 = r3.mAborted     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x0017
        L_0x0013:
            boolean r1 = r3.mPaused     // Catch:{ all -> 0x001f }
            if (r1 == 0) goto L_0x001d
        L_0x0017:
            java.lang.Object r1 = r3.mLock     // Catch:{ all -> 0x001f }
            r1.wait()     // Catch:{ all -> 0x001f }
            goto L_0x0003
        L_0x001d:
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            goto L_0x0028
        L_0x001f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001f }
            throw r1     // Catch:{ InterruptedException -> 0x0022 }
        L_0x0022:
            r0 = move-exception
            java.lang.String r1 = "Sleeping interrupted"
            r3.loge(r1, r0)
        L_0x0028:
            boolean r0 = r3.mAborted
            if (r0 != 0) goto L_0x0049
            boolean r0 = r3.mConnected
            if (r0 == 0) goto L_0x0041
            int r0 = r3.mError
            if (r0 != 0) goto L_0x0037
            byte[] r0 = r3.mReceivedData
            return r0
        L_0x0037:
            no.nordicsemi.android.dfu.internal.exception.DfuException r0 = new no.nordicsemi.android.dfu.internal.exception.DfuException
            java.lang.String r1 = "Unable to write Op Code"
            int r2 = r3.mError
            r0.<init>(r1, r2)
            throw r0
        L_0x0041:
            no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException r0 = new no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException
            java.lang.String r1 = "Unable to write Op Code: device disconnected"
            r0.<init>(r1)
            throw r0
        L_0x0049:
            no.nordicsemi.android.dfu.internal.exception.UploadAbortedException r0 = new no.nordicsemi.android.dfu.internal.exception.UploadAbortedException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.BaseDfuImpl.readNotificationResponse():byte[]");
    }

    /* access modifiers changed from: package-private */
    public void restartService(Intent intent, boolean z) {
        String str;
        if (z) {
            this.mService.sendLogBroadcast(1, "Scanning for the DFU Bootloader...");
            str = BootloaderScannerFactory.getScanner().searchFor(this.mGatt.getDevice().getAddress());
            logi("Scanning for new address finished with: " + str);
            if (str != null) {
                DfuBaseService dfuBaseService = this.mService;
                dfuBaseService.sendLogBroadcast(5, "DFU Bootloader found with address " + str);
            } else {
                this.mService.sendLogBroadcast(5, "DFU Bootloader not found. Trying the same address...");
            }
        } else {
            str = null;
        }
        if (str != null) {
            intent.putExtra(DfuBaseService.EXTRA_DEVICE_ADDRESS, str);
        }
        intent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DFU_ATTEMPT", 0);
        this.mService.startService(intent);
    }

    /* access modifiers changed from: protected */
    public String parse(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) == 0) {
            return "";
        }
        char[] cArr = new char[((length * 3) - 1)];
        for (int i = 0; i < length; i++) {
            byte b = bArr[i] & 255;
            int i2 = i * 3;
            char[] cArr2 = HEX_ARRAY;
            cArr[i2] = cArr2[b >>> 4];
            cArr[i2 + 1] = cArr2[b & 15];
            if (i != length - 1) {
                cArr[i2 + 2] = '-';
            }
        }
        return new String(cArr);
    }

    /* access modifiers changed from: package-private */
    public void loge(String str) {
        Log.e(TAG, str);
    }

    /* access modifiers changed from: package-private */
    public void loge(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    /* access modifiers changed from: package-private */
    public void logw(String str) {
        if (DfuBaseService.DEBUG) {
            Log.w(TAG, str);
        }
    }

    /* access modifiers changed from: package-private */
    public void logi(String str) {
        if (DfuBaseService.DEBUG) {
            Log.i(TAG, str);
        }
    }
}
