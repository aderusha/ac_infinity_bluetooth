package p019no.nordicsemi.android.dfu;

import android.bluetooth.BluetoothGattCharacteristic;
import android.content.Intent;
import p019no.nordicsemi.android.dfu.internal.exception.UnknownResponseException;

/* renamed from: no.nordicsemi.android.dfu.ButtonlessDfuImpl */
abstract class ButtonlessDfuImpl extends BaseButtonlessDfuImpl {
    private static final int DFU_STATUS_SUCCESS = 1;
    private static final byte[] OP_CODE_ENTER_BOOTLOADER = {1};
    private static final int OP_CODE_ENTER_BOOTLOADER_KEY = 1;
    private static final int OP_CODE_RESPONSE_CODE_KEY = 32;

    /* access modifiers changed from: protected */
    public abstract BluetoothGattCharacteristic getButtonlessDfuCharacteristic();

    /* access modifiers changed from: protected */
    public abstract int getResponseType();

    /* access modifiers changed from: protected */
    public abstract boolean shouldScanForBootloader();

    ButtonlessDfuImpl(Intent intent, DfuBaseService dfuBaseService) {
        super(intent, dfuBaseService);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(2:9|10) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r6 = r12.mReceivedData;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0079 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void performDfu(android.content.Intent r13) throws p019no.nordicsemi.android.dfu.internal.exception.DfuException, p019no.nordicsemi.android.dfu.internal.exception.DeviceDisconnectedException, p019no.nordicsemi.android.dfu.internal.exception.UploadAbortedException {
        /*
            r12 = this;
            java.lang.String r0 = ")"
            java.lang.String r1 = ", Status = "
            java.lang.String r2 = "Response received (Op Code = "
            no.nordicsemi.android.dfu.DfuProgressInfo r3 = r12.mProgressInfo
            r4 = -2
            r3.setProgress(r4)
            no.nordicsemi.android.dfu.DfuBaseService r3 = r12.mService
            r4 = 1000(0x3e8, double:4.94E-321)
            r3.waitFor(r4)
            android.bluetooth.BluetoothGatt r3 = r12.mGatt
            no.nordicsemi.android.dfu.DfuBaseService r6 = r12.mService
            r7 = 15
            java.lang.String r8 = "Application with buttonless update found"
            r6.sendLogBroadcast(r7, r8)
            no.nordicsemi.android.dfu.DfuBaseService r6 = r12.mService
            r7 = 1
            java.lang.String r8 = "Jumping to the DFU Bootloader..."
            r6.sendLogBroadcast(r7, r8)
            android.bluetooth.BluetoothGattCharacteristic r6 = r12.getButtonlessDfuCharacteristic()
            int r8 = r12.getResponseType()
            int r9 = r12.getResponseType()
            r12.enableCCCD(r6, r9)
            no.nordicsemi.android.dfu.DfuBaseService r9 = r12.mService
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            r11 = 2
            if (r8 != r11) goto L_0x0042
            java.lang.String r8 = "Indications"
            goto L_0x0044
        L_0x0042:
            java.lang.String r8 = "Notifications"
        L_0x0044:
            r10.append(r8)
            java.lang.String r8 = " enabled"
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            r10 = 10
            r9.sendLogBroadcast(r10, r8)
            no.nordicsemi.android.dfu.DfuBaseService r8 = r12.mService
            r8.waitFor(r4)
            r4 = 0
            r5 = 20
            no.nordicsemi.android.dfu.DfuProgressInfo r8 = r12.mProgressInfo     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r9 = -3
            r8.setProgress(r9)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            java.lang.String r8 = "Sending Enter Bootloader (Op Code = 1)"
            r12.logi(r8)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            byte[] r8 = OP_CODE_ENTER_BOOTLOADER     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r12.writeOpCode(r6, r8, r7)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            no.nordicsemi.android.dfu.DfuBaseService r6 = r12.mService     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            java.lang.String r8 = "Enter bootloader sent (Op Code = 1)"
            r6.sendLogBroadcast(r10, r8)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            byte[] r6 = r12.readNotificationResponse()     // Catch:{ DeviceDisconnectedException -> 0x0079 }
            goto L_0x007b
        L_0x0079:
            byte[] r6 = r12.mReceivedData     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
        L_0x007b:
            if (r6 == 0) goto L_0x00c8
            int r8 = r12.getStatusCode(r6, r7)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r9.<init>()     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r9.append(r2)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            byte r11 = r6[r7]     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r9.append(r11)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r9.append(r1)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r9.append(r8)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r9.append(r0)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            java.lang.String r9 = r9.toString()     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r12.logi(r9)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            no.nordicsemi.android.dfu.DfuBaseService r9 = r12.mService     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r11.<init>()     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r11.append(r2)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            byte r2 = r6[r7]     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r11.append(r2)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r11.append(r1)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r11.append(r8)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r11.append(r0)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            java.lang.String r0 = r11.toString()     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r9.sendLogBroadcast(r10, r0)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            if (r8 != r7) goto L_0x00c0
            goto L_0x00cd
        L_0x00c0:
            no.nordicsemi.android.dfu.internal.exception.RemoteDfuException r13 = new no.nordicsemi.android.dfu.internal.exception.RemoteDfuException     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            java.lang.String r0 = "Device returned error after sending Enter Bootloader"
            r13.<init>(r0, r8)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            throw r13     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
        L_0x00c8:
            java.lang.String r0 = "Device disconnected before receiving notification"
            r12.logi(r0)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
        L_0x00cd:
            boolean r0 = r12.shouldScanForBootloader()     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            r12.finalize(r13, r4, r0)     // Catch:{ UnknownResponseException -> 0x0100, RemoteDfuException -> 0x00d5 }
            goto L_0x0118
        L_0x00d5:
            r13 = move-exception
            int r0 = r13.getErrorNumber()
            r0 = r0 | 2048(0x800, float:2.87E-42)
            java.lang.String r13 = r13.getMessage()
            r12.loge(r13)
            no.nordicsemi.android.dfu.DfuBaseService r13 = r12.mService
            java.util.Locale r1 = java.util.Locale.US
            java.lang.Object[] r2 = new java.lang.Object[r7]
            java.lang.String r6 = p019no.nordicsemi.android.error.SecureDfuError.parseButtonlessError(r0)
            r2[r4] = r6
            java.lang.String r4 = "Remote DFU error: %s"
            java.lang.String r1 = java.lang.String.format(r1, r4, r2)
            r13.sendLogBroadcast(r5, r1)
            no.nordicsemi.android.dfu.DfuBaseService r13 = r12.mService
            r0 = r0 | 8192(0x2000, float:1.14794E-41)
            r13.terminateConnection(r3, r0)
            goto L_0x0118
        L_0x0100:
            r13 = move-exception
            java.lang.String r0 = r13.getMessage()
            r12.loge(r0)
            no.nordicsemi.android.dfu.DfuBaseService r0 = r12.mService
            java.lang.String r13 = r13.getMessage()
            r0.sendLogBroadcast(r5, r13)
            no.nordicsemi.android.dfu.DfuBaseService r13 = r12.mService
            r0 = 4104(0x1008, float:5.751E-42)
            r13.terminateConnection(r3, r0)
        L_0x0118:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p019no.nordicsemi.android.dfu.ButtonlessDfuImpl.performDfu(android.content.Intent):void");
    }

    private int getStatusCode(byte[] bArr, int i) throws UnknownResponseException {
        if (bArr != null && bArr.length >= 3 && bArr[0] == 32 && bArr[1] == i && (bArr[2] == 1 || bArr[2] == 2 || bArr[2] == 4)) {
            return bArr[2];
        }
        throw new UnknownResponseException("Invalid response received", bArr, 32, i);
    }
}
