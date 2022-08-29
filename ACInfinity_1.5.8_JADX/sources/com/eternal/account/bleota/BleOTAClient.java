package com.eternal.account.bleota;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.util.Log;
import com.eternal.account.bleota.message.BleOTAMessage;
import com.eternal.account.bleota.message.EndCommandAckMessage;
import com.eternal.account.bleota.message.StartCommandAckMessage;
import com.eternal.data.DataFragment;
import java.io.Closeable;
import java.io.File;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo27511d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0014\u0018\u0000 G2\u00020\u0001:\u0002GHB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u00104\u001a\u000205H\u0016J\u0018\u00106\u001a\u00020#2\u0006\u00107\u001a\u00020\u001c2\u0006\u00108\u001a\u00020#H\u0002J\b\u00109\u001a\u000205H\u0002J\n\u0010:\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010;\u001a\u000205H\u0002J\u0010\u0010<\u001a\u0002052\u0006\u0010=\u001a\u00020#H\u0002J\b\u0010>\u001a\u000205H\u0002J\b\u0010?\u001a\u000205H\u0002J\b\u0010@\u001a\u000205H\u0002J\b\u0010A\u001a\u000205H\u0002J\u0010\u0010B\u001a\u0002052\u0006\u0010C\u001a\u00020\u001cH\u0002J\u0010\u0010D\u001a\u0002052\u0006\u0010C\u001a\u00020\u001cH\u0002J\u000e\u0010E\u001a\u0002052\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010F\u001a\u000205R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010$\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u000e\"\u0004\b&\u0010\u0010R\u001c\u0010'\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u000e\"\u0004\b)\u0010\u0010R\u000e\u0010*\u001a\u00020+X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020#X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u0006I"}, mo27512d2 = {"Lcom/eternal/account/bleota/BleOTAClient;", "Ljava/io/Closeable;", "context", "Landroid/content/Context;", "device", "Landroid/bluetooth/BluetoothDevice;", "bin", "Ljava/io/File;", "(Landroid/content/Context;Landroid/bluetooth/BluetoothDevice;Ljava/io/File;)V", "callback", "Lcom/eternal/account/bleota/BleOTAClient$GattCallback;", "commandChar", "Landroid/bluetooth/BluetoothGattCharacteristic;", "getCommandChar", "()Landroid/bluetooth/BluetoothGattCharacteristic;", "setCommandChar", "(Landroid/bluetooth/BluetoothGattCharacteristic;)V", "customerChar", "getCustomerChar", "setCustomerChar", "gatt", "Landroid/bluetooth/BluetoothGatt;", "getGatt", "()Landroid/bluetooth/BluetoothGatt;", "setGatt", "(Landroid/bluetooth/BluetoothGatt;)V", "nextNotifyChar", "packetSize", "", "getPacketSize", "()I", "setPacketSize", "(I)V", "packets", "Ljava/util/LinkedList;", "", "progressChar", "getProgressChar", "setProgressChar", "recvFwChar", "getRecvFwChar", "setRecvFwChar", "sectorAckIndex", "Ljava/util/concurrent/atomic/AtomicInteger;", "sectorAckMark", "sectorSize", "service", "Landroid/bluetooth/BluetoothGattService;", "getService", "()Landroid/bluetooth/BluetoothGattService;", "setService", "(Landroid/bluetooth/BluetoothGattService;)V", "close", "", "genCommandPacket", "id", "payload", "initPackets", "notifyNextDescWrite", "parseCommandPacket", "parseSectorAck", "data", "postBinData", "postCommandEnd", "postCommandStart", "postNextPacket", "receiveCommandEndAck", "status", "receiveCommandStartAck", "start", "stop", "Companion", "GattCallback", "module-account_release"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
/* compiled from: BleOTAClient.kt */
public final class BleOTAClient implements Closeable {
    private static final int BIN_ACK_CRC_ERROR = 1;
    private static final int BIN_ACK_PAYLOAD_LENGTH_ERROR = 3;
    private static final int BIN_ACK_SECTOR_INDEX_ERROR = 2;
    private static final int BIN_ACK_SUCCESS = 0;
    /* access modifiers changed from: private */
    public static final UUID CHAR_COMMAND_UUID = ExtKt.bleUUID("8022");
    /* access modifiers changed from: private */
    public static final UUID CHAR_CUSTOMER_UUID = ExtKt.bleUUID("8023");
    /* access modifiers changed from: private */
    public static final UUID CHAR_PROGRESS_UUID = ExtKt.bleUUID("8021");
    /* access modifiers changed from: private */
    public static final UUID CHAR_RECV_FW_UUID = ExtKt.bleUUID("8020");
    public static final int COMMAND_ACK_ACCEPT = 0;
    public static final int COMMAND_ACK_REFUSE = 1;
    private static final int COMMAND_ID_ACK = 3;
    private static final int COMMAND_ID_END = 2;
    private static final int COMMAND_ID_START = 1;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final boolean DEBUG = false;
    private static final int EXPECT_PACKET_SIZE = 463;
    private static final int MTU_SIZE = 517;
    private static final int MTU_STATUS_FAILED = 20000;
    private static final boolean REQUIRE_CHECKSUM = false;
    /* access modifiers changed from: private */
    public static final UUID SERVICE_UUID = ExtKt.bleUUID("8018");
    private static final String TAG = "BleOTAClient";
    private final File bin;
    private GattCallback callback;
    private BluetoothGattCharacteristic commandChar;
    private final Context context;
    private BluetoothGattCharacteristic customerChar;
    private final BluetoothDevice device;
    private BluetoothGatt gatt;
    private BluetoothGattCharacteristic nextNotifyChar;
    private int packetSize = 20;
    private final LinkedList<byte[]> packets = new LinkedList<>();
    private BluetoothGattCharacteristic progressChar;
    private BluetoothGattCharacteristic recvFwChar;
    private final AtomicInteger sectorAckIndex = new AtomicInteger(0);
    private final byte[] sectorAckMark = new byte[0];
    private int sectorSize;
    private BluetoothGattService service;

    public BleOTAClient(Context context2, BluetoothDevice bluetoothDevice, File file) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(bluetoothDevice, "device");
        Intrinsics.checkNotNullParameter(file, "bin");
        this.context = context2;
        this.device = bluetoothDevice;
        this.bin = file;
    }

    @Metadata(mo27511d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0013XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000¨\u0006\u001b"}, mo27512d2 = {"Lcom/eternal/account/bleota/BleOTAClient$Companion;", "", "()V", "BIN_ACK_CRC_ERROR", "", "BIN_ACK_PAYLOAD_LENGTH_ERROR", "BIN_ACK_SECTOR_INDEX_ERROR", "BIN_ACK_SUCCESS", "CHAR_COMMAND_UUID", "Ljava/util/UUID;", "CHAR_CUSTOMER_UUID", "CHAR_PROGRESS_UUID", "CHAR_RECV_FW_UUID", "COMMAND_ACK_ACCEPT", "COMMAND_ACK_REFUSE", "COMMAND_ID_ACK", "COMMAND_ID_END", "COMMAND_ID_START", "DEBUG", "", "EXPECT_PACKET_SIZE", "MTU_SIZE", "MTU_STATUS_FAILED", "REQUIRE_CHECKSUM", "SERVICE_UUID", "TAG", "", "module-account_release"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
    /* compiled from: BleOTAClient.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final int getPacketSize() {
        return this.packetSize;
    }

    public final void setPacketSize(int i) {
        this.packetSize = i;
    }

    public final BluetoothGatt getGatt() {
        return this.gatt;
    }

    public final void setGatt(BluetoothGatt bluetoothGatt) {
        this.gatt = bluetoothGatt;
    }

    public final BluetoothGattService getService() {
        return this.service;
    }

    public final void setService(BluetoothGattService bluetoothGattService) {
        this.service = bluetoothGattService;
    }

    public final BluetoothGattCharacteristic getRecvFwChar() {
        return this.recvFwChar;
    }

    public final void setRecvFwChar(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.recvFwChar = bluetoothGattCharacteristic;
    }

    public final BluetoothGattCharacteristic getProgressChar() {
        return this.progressChar;
    }

    public final void setProgressChar(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.progressChar = bluetoothGattCharacteristic;
    }

    public final BluetoothGattCharacteristic getCommandChar() {
        return this.commandChar;
    }

    public final void setCommandChar(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.commandChar = bluetoothGattCharacteristic;
    }

    public final BluetoothGattCharacteristic getCustomerChar() {
        return this.customerChar;
    }

    public final void setCustomerChar(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        this.customerChar = bluetoothGattCharacteristic;
    }

    public final void start(GattCallback gattCallback) {
        Intrinsics.checkNotNullParameter(gattCallback, "callback");
        Log.i(TAG, "start OTA");
        stop();
        this.callback = gattCallback;
        gattCallback.setClient(this);
        this.gatt = this.device.connectGatt(this.context, false, gattCallback);
    }

    public final void stop() {
        try {
            BluetoothGatt bluetoothGatt = this.gatt;
            if (bluetoothGatt != null) {
                bluetoothGatt.disconnect();
            }
            BluetoothGatt bluetoothGatt2 = this.gatt;
            if (bluetoothGatt2 != null) {
                bluetoothGatt2.close();
            }
        } catch (Throwable unused) {
        }
        this.gatt = null;
        this.callback = null;
        this.packets.clear();
    }

    public void close() {
        stop();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0069, code lost:
        r15.packets.add(r15.sectorAckMark);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00c6, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00c7, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r13, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ca, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00dc, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00dd, code lost:
        kotlin.p016io.CloseableKt.closeFinally(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e0, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void initPackets() {
        /*
            r15 = this;
            java.util.concurrent.atomic.AtomicInteger r0 = r15.sectorAckIndex
            r1 = 0
            r0.set(r1)
            java.util.LinkedList<byte[]> r0 = r15.packets
            r0.clear()
            r15.sectorSize = r1
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream
            java.io.File r3 = r15.bin
            r2.<init>(r3)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = 0
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            r4 = r2
            java.io.FileInputStream r4 = (java.io.FileInputStream) r4     // Catch:{ all -> 0x00da }
            r5 = 4096(0x1000, float:5.74E-42)
            byte[] r5 = new byte[r5]     // Catch:{ all -> 0x00da }
        L_0x0025:
            int r6 = r4.read(r5)     // Catch:{ all -> 0x00da }
            r7 = -1
            if (r6 != r7) goto L_0x00cc
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00da }
            kotlin.p016io.CloseableKt.closeFinally(r2, r3)
            int r2 = r0.size()
            r15.sectorSize = r2
            int r2 = r15.packetSize
            int r2 = r2 + -3
            byte[] r2 = new byte[r2]
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.lang.Iterable r0 = kotlin.collections.CollectionsKt.withIndex(r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x0047:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x00cb
            java.lang.Object r4 = r0.next()
            kotlin.collections.IndexedValue r4 = (kotlin.collections.IndexedValue) r4
            java.lang.Object r5 = r4.getValue()
            byte[] r5 = (byte[]) r5
            int r4 = r4.getIndex()
            java.io.ByteArrayInputStream r6 = new java.io.ByteArrayInputStream
            r6.<init>(r5)
            r8 = 0
        L_0x0063:
            int r9 = r6.read(r2)
            if (r9 != r7) goto L_0x0071
            java.util.LinkedList<byte[]> r4 = r15.packets
            byte[] r5 = r15.sectorAckMark
            r4.add(r5)
            goto L_0x0047
        L_0x0071:
            int r10 = r6.available()
            if (r10 != 0) goto L_0x0079
            r10 = 1
            goto L_0x007a
        L_0x0079:
            r10 = 0
        L_0x007a:
            if (r10 == 0) goto L_0x0083
            int r8 = com.eternal.account.bleota.EspCRC16.crc(r5)
            r11 = r8
            r8 = -1
            goto L_0x0084
        L_0x0083:
            r11 = 0
        L_0x0084:
            if (r10 == 0) goto L_0x0089
            int r12 = r9 + 5
            goto L_0x008b
        L_0x0089:
            int r12 = r9 + 3
        L_0x008b:
            java.io.ByteArrayOutputStream r13 = new java.io.ByteArrayOutputStream
            r13.<init>(r12)
            java.io.Closeable r13 = (java.io.Closeable) r13
            r12 = r13
            java.io.ByteArrayOutputStream r12 = (java.io.ByteArrayOutputStream) r12     // Catch:{ all -> 0x00c4 }
            r14 = r4 & 255(0xff, float:3.57E-43)
            r12.write(r14)     // Catch:{ all -> 0x00c4 }
            int r14 = r4 >> 8
            r14 = r14 & 255(0xff, float:3.57E-43)
            r12.write(r14)     // Catch:{ all -> 0x00c4 }
            r12.write(r8)     // Catch:{ all -> 0x00c4 }
            r12.write(r2, r1, r9)     // Catch:{ all -> 0x00c4 }
            if (r10 == 0) goto L_0x00b5
            r9 = r11 & 255(0xff, float:3.57E-43)
            r12.write(r9)     // Catch:{ all -> 0x00c4 }
            int r9 = r11 >> 8
            r9 = r9 & 255(0xff, float:3.57E-43)
            r12.write(r9)     // Catch:{ all -> 0x00c4 }
        L_0x00b5:
            byte[] r9 = r12.toByteArray()     // Catch:{ all -> 0x00c4 }
            kotlin.p016io.CloseableKt.closeFinally(r13, r3)
            int r8 = r8 + 1
            java.util.LinkedList<byte[]> r10 = r15.packets
            r10.add(r9)
            goto L_0x0063
        L_0x00c4:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00c6 }
        L_0x00c6:
            r1 = move-exception
            kotlin.p016io.CloseableKt.closeFinally(r13, r0)
            throw r1
        L_0x00cb:
            return
        L_0x00cc:
            byte[] r6 = java.util.Arrays.copyOf(r5, r6)     // Catch:{ all -> 0x00da }
            java.lang.String r7 = "java.util.Arrays.copyOf(this, newSize)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)     // Catch:{ all -> 0x00da }
            r0.add(r6)     // Catch:{ all -> 0x00da }
            goto L_0x0025
        L_0x00da:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00dc }
        L_0x00dc:
            r1 = move-exception
            kotlin.p016io.CloseableKt.closeFinally(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.account.bleota.BleOTAClient.initPackets():void");
    }

    /* access modifiers changed from: private */
    public final BluetoothGattCharacteristic notifyNextDescWrite() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        BluetoothGattCharacteristic bluetoothGattCharacteristic2 = this.nextNotifyChar;
        if (bluetoothGattCharacteristic2 == null) {
            bluetoothGattCharacteristic = this.recvFwChar;
            this.nextNotifyChar = bluetoothGattCharacteristic;
        } else if (Intrinsics.areEqual((Object) bluetoothGattCharacteristic2, (Object) this.recvFwChar)) {
            bluetoothGattCharacteristic = this.progressChar;
            this.nextNotifyChar = bluetoothGattCharacteristic;
        } else if (Intrinsics.areEqual((Object) bluetoothGattCharacteristic2, (Object) this.progressChar)) {
            bluetoothGattCharacteristic = this.commandChar;
            this.nextNotifyChar = bluetoothGattCharacteristic;
        } else if (Intrinsics.areEqual((Object) bluetoothGattCharacteristic2, (Object) this.commandChar)) {
            bluetoothGattCharacteristic = this.customerChar;
            this.nextNotifyChar = bluetoothGattCharacteristic;
        } else if (Intrinsics.areEqual((Object) bluetoothGattCharacteristic2, (Object) this.customerChar)) {
            bluetoothGattCharacteristic = null;
        } else {
            bluetoothGattCharacteristic = null;
        }
        if (bluetoothGattCharacteristic == null) {
            return null;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(ExtKt.getUUID_NOTIFY_DESCRIPTOR());
        if (descriptor != null) {
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            BluetoothGatt gatt2 = getGatt();
            if (gatt2 != null) {
                gatt2.writeDescriptor(descriptor);
            }
        }
        return bluetoothGattCharacteristic;
    }

    private final byte[] genCommandPacket(int i, byte[] bArr) {
        byte[] bArr2 = new byte[20];
        bArr2[0] = (byte) (i & 255);
        bArr2[1] = (byte) ((i >> 8) & 255);
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        int crc = EspCRC16.crc(bArr2, 0, 18);
        bArr2[18] = (byte) (crc & 255);
        bArr2[19] = (byte) ((crc >> 8) & 255);
        return bArr2;
    }

    /* access modifiers changed from: private */
    public final void postCommandStart() {
        Log.i(TAG, "postCommandStart");
        long length = this.bin.length();
        byte[] genCommandPacket = genCommandPacket(1, new byte[]{(byte) ((int) (length & 255)), (byte) ((int) ((length >> 8) & 255)), (byte) ((int) ((length >> 16) & 255)), (byte) ((int) ((length >> 24) & 255))});
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.commandChar;
        if (bluetoothGattCharacteristic != null) {
            bluetoothGattCharacteristic.setValue(genCommandPacket);
        }
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt != null) {
            bluetoothGatt.writeCharacteristic(this.commandChar);
        }
    }

    private final void receiveCommandStartAck(int i) {
        Log.i(TAG, "receiveCommandStartAck: status=" + i);
        if (i == 0) {
            postBinData();
        }
        StartCommandAckMessage startCommandAckMessage = new StartCommandAckMessage(i);
        GattCallback gattCallback = this.callback;
        if (gattCallback != null) {
            gattCallback.onOTA(startCommandAckMessage);
        }
    }

    private final void postCommandEnd() {
        Log.i(TAG, "postCommandEnd");
        byte[] genCommandPacket = genCommandPacket(2, new byte[0]);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.commandChar;
        if (bluetoothGattCharacteristic != null) {
            bluetoothGattCharacteristic.setValue(genCommandPacket);
        }
        BluetoothGatt bluetoothGatt = this.gatt;
        if (bluetoothGatt != null) {
            bluetoothGatt.writeCharacteristic(this.commandChar);
        }
    }

    private final void receiveCommandEndAck(int i) {
        Log.i(TAG, "receiveCommandEndAck: status=" + i);
        EndCommandAckMessage endCommandAckMessage = new EndCommandAckMessage(i);
        GattCallback gattCallback = this.callback;
        if (gattCallback != null) {
            gattCallback.onOTA(endCommandAckMessage);
        }
    }

    private final void postBinData() {
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new BleOTAClient$postBinData$1(this), 31, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void postNextPacket() {
        byte[] pollFirst = this.packets.pollFirst();
        if (pollFirst == null) {
            postCommandEnd();
        } else if (pollFirst != this.sectorAckMark) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.recvFwChar;
            if (bluetoothGattCharacteristic != null) {
                bluetoothGattCharacteristic.setValue(pollFirst);
            }
            BluetoothGatt bluetoothGatt = this.gatt;
            if (bluetoothGatt != null) {
                bluetoothGatt.writeCharacteristic(this.recvFwChar);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void parseSectorAck(byte[] bArr) {
        try {
            int andIncrement = this.sectorAckIndex.getAndIncrement();
            byte b = (bArr[0] & 255) | ((bArr[1] << 8) & 65280);
            if (b != andIncrement) {
                Log.w(TAG, "takeSectorAck: Receive error index " + b + ", expect " + andIncrement);
                GattCallback gattCallback = this.callback;
                if (gattCallback != null) {
                    gattCallback.onError(1);
                    return;
                }
                return;
            }
            byte b2 = (bArr[2] & 255) | (65280 & (bArr[3] << 8));
            Log.d(TAG, "takeSectorAck: index=" + b + ",  , status=" + b2);
            GattCallback gattCallback2 = this.callback;
            if (gattCallback2 != null) {
                gattCallback2.onProgressChanged((b * DataFragment.TAG_FILTER_TEMPERATURE) / this.sectorSize);
            }
            if (b2 == 0) {
                postNextPacket();
            } else if (b2 == 1) {
                GattCallback gattCallback3 = this.callback;
                if (gattCallback3 != null) {
                    gattCallback3.onError(2);
                }
            } else if (b2 == 2) {
                byte b3 = bArr[4];
                byte b4 = bArr[5];
                GattCallback gattCallback4 = this.callback;
                if (gattCallback4 != null) {
                    gattCallback4.onError(3);
                }
            } else if (b2 != 3) {
                GattCallback gattCallback5 = this.callback;
                if (gattCallback5 != null) {
                    gattCallback5.onError(5);
                }
            } else {
                GattCallback gattCallback6 = this.callback;
                if (gattCallback6 != null) {
                    gattCallback6.onError(4);
                }
            }
        } catch (Exception unused) {
            Log.w(TAG, "parseSectorAck error");
            GattCallback gattCallback7 = this.callback;
            if (gattCallback7 != null) {
                gattCallback7.onError(-1);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void parseCommandPacket() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic = this.commandChar;
        Intrinsics.checkNotNull(bluetoothGattCharacteristic);
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (((value[0] & 255) | ((value[1] << 8) & 65280)) == 3) {
            byte b = ((value[3] << 8) & 65280) | (value[2] & 255);
            byte b2 = ((value[5] << 8) & 65280) | (value[4] & 255);
            if (b == 1) {
                receiveCommandStartAck(b2);
            } else if (b == 2) {
                receiveCommandEndAck(b2);
            }
        }
    }

    @Metadata(mo27511d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J \u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0011H\u0016J \u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0011H\u0016J \u0010\u0019\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0011H\u0016J\u0018\u0010 \u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\f\u0010!\u001a\u00020\"*\u00020\u0011H\u0004J\f\u0010#\u001a\u00020\"*\u00020\u0011H\u0004R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006$"}, mo27512d2 = {"Lcom/eternal/account/bleota/BleOTAClient$GattCallback;", "Landroid/bluetooth/BluetoothGattCallback;", "()V", "client", "Lcom/eternal/account/bleota/BleOTAClient;", "getClient", "()Lcom/eternal/account/bleota/BleOTAClient;", "setClient", "(Lcom/eternal/account/bleota/BleOTAClient;)V", "onCharacteristicChanged", "", "gatt", "Landroid/bluetooth/BluetoothGatt;", "characteristic", "Landroid/bluetooth/BluetoothGattCharacteristic;", "onCharacteristicWrite", "status", "", "onConnectionStateChange", "newState", "onDescriptorWrite", "descriptor", "Landroid/bluetooth/BluetoothGattDescriptor;", "onError", "code", "onMtuChanged", "mtu", "onOTA", "message", "Lcom/eternal/account/bleota/message/BleOTAMessage;", "onProgressChanged", "percent", "onServicesDiscovered", "isGattFailed", "", "isGattSuccess", "module-account_release"}, mo27513k = 1, mo27514mv = {1, 5, 1}, mo27516xi = 48)
    /* compiled from: BleOTAClient.kt */
    public static class GattCallback extends BluetoothGattCallback {
        private BleOTAClient client;

        /* access modifiers changed from: protected */
        public final boolean isGattFailed(int i) {
            return i != 0;
        }

        /* access modifiers changed from: protected */
        public final boolean isGattSuccess(int i) {
            return i == 0;
        }

        public void onError(int i) {
        }

        public void onOTA(BleOTAMessage bleOTAMessage) {
            Intrinsics.checkNotNullParameter(bleOTAMessage, "message");
        }

        public void onProgressChanged(int i) {
        }

        public final BleOTAClient getClient() {
            return this.client;
        }

        public final void setClient(BleOTAClient bleOTAClient) {
            this.client = bleOTAClient;
        }

        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            Intrinsics.checkNotNullParameter(bluetoothGatt, "gatt");
            if (i2 == 2) {
                bluetoothGatt.requestConnectionPriority(1);
                if (!bluetoothGatt.requestMtu(BleOTAClient.MTU_SIZE)) {
                    onMtuChanged(bluetoothGatt, BleOTAClient.MTU_SIZE, BleOTAClient.MTU_STATUS_FAILED);
                }
            }
        }

        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            Intrinsics.checkNotNullParameter(bluetoothGatt, "gatt");
            BleOTAClient bleOTAClient = this.client;
            Intrinsics.checkNotNull(bleOTAClient);
            bleOTAClient.setPacketSize(isGattSuccess(i2) ? BleOTAClient.EXPECT_PACKET_SIZE : 20);
            bluetoothGatt.discoverServices();
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            BluetoothGattCharacteristic bluetoothGattCharacteristic;
            BluetoothGattCharacteristic bluetoothGattCharacteristic2;
            BluetoothGattCharacteristic characteristic;
            Intrinsics.checkNotNullParameter(bluetoothGatt, "gatt");
            if (!isGattFailed(i)) {
                BluetoothGattService service = bluetoothGatt.getService(BleOTAClient.SERVICE_UUID);
                BluetoothGattCharacteristic bluetoothGattCharacteristic3 = null;
                if (service == null || (bluetoothGattCharacteristic = service.getCharacteristic(BleOTAClient.CHAR_RECV_FW_UUID)) == null) {
                    bluetoothGattCharacteristic = null;
                } else {
                    bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true);
                }
                if (service == null || (bluetoothGattCharacteristic2 = service.getCharacteristic(BleOTAClient.CHAR_PROGRESS_UUID)) == null) {
                    bluetoothGattCharacteristic2 = null;
                } else {
                    bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic2, true);
                }
                BluetoothGattCharacteristic characteristic2 = service.getCharacteristic(BleOTAClient.CHAR_COMMAND_UUID);
                if (characteristic2 == null) {
                    characteristic2 = null;
                } else {
                    bluetoothGatt.setCharacteristicNotification(characteristic2, true);
                }
                if (!(service == null || (characteristic = service.getCharacteristic(BleOTAClient.CHAR_CUSTOMER_UUID)) == null)) {
                    bluetoothGatt.setCharacteristicNotification(characteristic, true);
                    bluetoothGattCharacteristic3 = characteristic;
                }
                BleOTAClient bleOTAClient = this.client;
                if (bleOTAClient != null) {
                    bleOTAClient.setService(service);
                    bleOTAClient.setRecvFwChar(bluetoothGattCharacteristic);
                    bleOTAClient.setProgressChar(bluetoothGattCharacteristic2);
                    bleOTAClient.setCommandChar(characteristic2);
                    bleOTAClient.setCustomerChar(bluetoothGattCharacteristic3);
                    if (service != null && bluetoothGattCharacteristic != null && bluetoothGattCharacteristic2 != null && characteristic2 != null && bluetoothGattCharacteristic3 != null) {
                        BluetoothGattCharacteristic unused = bleOTAClient.notifyNextDescWrite();
                    }
                }
            }
        }

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            Intrinsics.checkNotNullParameter(bluetoothGatt, "gatt");
            Intrinsics.checkNotNullParameter(bluetoothGattDescriptor, "descriptor");
            if (!isGattFailed(i)) {
                BleOTAClient bleOTAClient = this.client;
                if ((bleOTAClient == null ? null : bleOTAClient.notifyNextDescWrite()) == null) {
                    Log.d(BleOTAClient.TAG, "onDescriptorWrite: Set notification enabled completed");
                    BleOTAClient bleOTAClient2 = this.client;
                    if (bleOTAClient2 != null) {
                        bleOTAClient2.postCommandStart();
                    }
                }
            }
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            BleOTAClient bleOTAClient;
            Intrinsics.checkNotNullParameter(bluetoothGatt, "gatt");
            Intrinsics.checkNotNullParameter(bluetoothGattCharacteristic, "characteristic");
            BleOTAClient bleOTAClient2 = this.client;
            if (Intrinsics.areEqual((Object) bluetoothGattCharacteristic, (Object) bleOTAClient2 == null ? null : bleOTAClient2.getRecvFwChar()) && (bleOTAClient = this.client) != null) {
                bleOTAClient.postNextPacket();
            }
            if (isGattFailed(i)) {
                Log.w(BleOTAClient.TAG, "onCharacteristicWrite: status=" + i);
            }
        }

        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            Intrinsics.checkNotNullParameter(bluetoothGatt, "gatt");
            Intrinsics.checkNotNullParameter(bluetoothGattCharacteristic, "characteristic");
            BleOTAClient bleOTAClient = this.client;
            BluetoothGattCharacteristic bluetoothGattCharacteristic2 = null;
            if (Intrinsics.areEqual((Object) bluetoothGattCharacteristic, (Object) bleOTAClient == null ? null : bleOTAClient.getRecvFwChar())) {
                BleOTAClient bleOTAClient2 = this.client;
                if (bleOTAClient2 != null) {
                    byte[] value = bluetoothGattCharacteristic.getValue();
                    Intrinsics.checkNotNullExpressionValue(value, "characteristic.value");
                    bleOTAClient2.parseSectorAck(value);
                    return;
                }
                return;
            }
            BleOTAClient bleOTAClient3 = this.client;
            if (!Intrinsics.areEqual((Object) bluetoothGattCharacteristic, (Object) bleOTAClient3 == null ? null : bleOTAClient3.getProgressChar())) {
                BleOTAClient bleOTAClient4 = this.client;
                if (Intrinsics.areEqual((Object) bluetoothGattCharacteristic, (Object) bleOTAClient4 == null ? null : bleOTAClient4.getCommandChar())) {
                    BleOTAClient bleOTAClient5 = this.client;
                    if (bleOTAClient5 != null) {
                        bleOTAClient5.parseCommandPacket();
                        return;
                    }
                    return;
                }
                BleOTAClient bleOTAClient6 = this.client;
                if (bleOTAClient6 != null) {
                    bluetoothGattCharacteristic2 = bleOTAClient6.getCustomerChar();
                }
                Intrinsics.areEqual((Object) bluetoothGattCharacteristic, (Object) bluetoothGattCharacteristic2);
            }
        }
    }
}
