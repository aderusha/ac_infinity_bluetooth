package com.telink.p010lt.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import com.telink.p010lt.ble.Command;
import com.telink.p010lt.util.Arrays;
import com.telink.p010lt.util.TelinkLog;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.telink.lt.ble.Peripheral */
public class Peripheral extends BluetoothGattCallback {
    public static final int CONNECTION_PRIORITY_BALANCED = 0;
    public static final int CONNECTION_PRIORITY_HIGH = 1;
    public static final int CONNECTION_PRIORITY_LOW_POWER = 2;
    private static final int CONN_STATE_CLOSED = 16;
    private static final int CONN_STATE_CONNECTED = 4;
    private static final int CONN_STATE_CONNECTING = 2;
    private static final int CONN_STATE_DISCONNECTING = 8;
    private static final int CONN_STATE_IDLE = 1;
    private static final int RSSI_UPDATE_TIME_INTERVAL = 2000;
    protected int commandTimeoutMill = 10000;
    protected BluetoothDevice device;
    protected BluetoothGatt gatt;
    protected final Runnable mCommandDelayRunnable = new CommandDelayRunnable(this, (C36091) null);
    protected final Runnable mCommandTimeoutRunnable = new CommandTimeoutRunnable(this, (C36091) null);
    private int mConnState = 1;
    protected final Handler mDelayHandler = new Handler(Looper.getMainLooper());
    protected final Queue<CommandContext> mInputCommandQueue = new ConcurrentLinkedQueue();
    protected final Map<String, CommandContext> mNotificationCallbacks = new ConcurrentHashMap();
    protected final Queue<CommandContext> mOutputCommandQueue = new ConcurrentLinkedQueue();
    private final Object mProcessLock = new Object();
    protected final Handler mRssiUpdateHandler = new Handler(Looper.getMainLooper());
    protected final Runnable mRssiUpdateRunnable = new RssiUpdateRunnable(this, (C36091) null);
    protected List<BluetoothGattService> mServices;
    private final Object mStateLock = new Object();
    protected final Handler mTimeoutHandler = new Handler(Looper.getMainLooper());
    protected String mac;
    protected byte[] macBytes;
    protected boolean monitorRssi;
    protected String name;
    protected Boolean processing = false;
    protected int rssi;
    protected byte[] scanRecord;
    protected int type;
    protected int updateIntervalMill = 5000;

    /* access modifiers changed from: protected */
    public void onDisableNotify() {
    }

    /* access modifiers changed from: protected */
    public void onEnableNotify() {
    }

    /* access modifiers changed from: protected */
    public void onNotify(byte[] bArr, UUID uuid, UUID uuid2, Object obj) {
    }

    /* access modifiers changed from: protected */
    public void onRssiChanged() {
    }

    /* access modifiers changed from: protected */
    public void onServicesDiscovered(List<BluetoothGattService> list) {
    }

    public Peripheral(BluetoothDevice bluetoothDevice, byte[] bArr, int i) {
        this.device = bluetoothDevice;
        this.scanRecord = bArr;
        this.rssi = i;
        this.name = bluetoothDevice.getName();
        this.mac = bluetoothDevice.getAddress();
        this.type = bluetoothDevice.getType();
    }

    public BluetoothDevice getDevice() {
        return this.device;
    }

    public String getDeviceName() {
        return this.name;
    }

    public String getMacAddress() {
        return this.mac;
    }

    public List<BluetoothGattService> getServices() {
        return this.mServices;
    }

    public byte[] getMacBytes() {
        if (this.macBytes == null) {
            String[] split = getMacAddress().split(":");
            int length = split.length;
            this.macBytes = new byte[length];
            for (int i = 0; i < length; i++) {
                this.macBytes[i] = (byte) (Integer.parseInt(split[i], 16) & 255);
            }
            Arrays.reverse(this.macBytes, 0, length - 1);
        }
        return this.macBytes;
    }

    public int getType() {
        return this.type;
    }

    public int getRssi() {
        return this.rssi;
    }

    public boolean isConnected() {
        boolean z;
        synchronized (this.mStateLock) {
            z = this.mConnState == 4;
        }
        return z;
    }

    public void connect(Context context) {
        synchronized (this.mStateLock) {
            if (this.mConnState == 1) {
                TelinkLog.m265d("connect " + getDeviceName() + " -- " + getMacAddress());
                this.mConnState = 2;
                BluetoothGatt connectGatt = this.device.connectGatt(context, false, this);
                this.gatt = connectGatt;
                if (connectGatt == null) {
                    disconnect();
                    this.mConnState = 1;
                    onDisconnect();
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r0 = r4.gatt;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        if (r0 == null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003e, code lost:
        if (r4.mConnState != 4) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        r0.disconnect();
        r4.mConnState = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r0.disconnect();
        r4.gatt.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        r4.mConnState = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        com.telink.p010lt.util.TelinkLog.m265d("disconnect " + getDeviceName() + " -- " + getMacAddress());
        clear();
        r1 = r4.mStateLock;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void disconnect() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mStateLock
            monitor-enter(r0)
            int r1 = r4.mConnState     // Catch:{ all -> 0x005d }
            r2 = 2
            r3 = 4
            if (r1 == r2) goto L_0x000d
            if (r1 == r3) goto L_0x000d
            monitor-exit(r0)     // Catch:{ all -> 0x005d }
            return
        L_0x000d:
            monitor-exit(r0)     // Catch:{ all -> 0x005d }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "disconnect "
            r0.append(r1)
            java.lang.String r1 = r4.getDeviceName()
            r0.append(r1)
            java.lang.String r1 = " -- "
            r0.append(r1)
            java.lang.String r1 = r4.getMacAddress()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.telink.p010lt.util.TelinkLog.m265d(r0)
            r4.clear()
            java.lang.Object r1 = r4.mStateLock
            monitor-enter(r1)
            android.bluetooth.BluetoothGatt r0 = r4.gatt     // Catch:{ all -> 0x005a }
            if (r0 == 0) goto L_0x0055
            int r2 = r4.mConnState     // Catch:{ all -> 0x005a }
            if (r2 != r3) goto L_0x0048
            r0.disconnect()     // Catch:{ all -> 0x005a }
            r0 = 8
            r4.mConnState = r0     // Catch:{ all -> 0x005a }
            goto L_0x0058
        L_0x0048:
            r0.disconnect()     // Catch:{ all -> 0x0050 }
            android.bluetooth.BluetoothGatt r0 = r4.gatt     // Catch:{ all -> 0x0050 }
            r0.close()     // Catch:{ all -> 0x0050 }
        L_0x0050:
            r0 = 16
            r4.mConnState = r0     // Catch:{ all -> 0x005a }
            goto L_0x0058
        L_0x0055:
            r0 = 1
            r4.mConnState = r0     // Catch:{ all -> 0x005a }
        L_0x0058:
            monitor-exit(r1)     // Catch:{ all -> 0x005a }
            return
        L_0x005a:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x005a }
            throw r0
        L_0x005d:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005d }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.telink.p010lt.ble.Peripheral.disconnect():void");
    }

    private void clear() {
        this.processing = false;
        stopMonitoringRssi();
        cancelCommandTimeoutTask();
        this.mInputCommandQueue.clear();
        this.mOutputCommandQueue.clear();
        this.mNotificationCallbacks.clear();
        this.mDelayHandler.removeCallbacksAndMessages((Object) null);
    }

    public boolean sendCommand(Command.Callback callback, Command command) {
        synchronized (this.mStateLock) {
            if (this.mConnState != 4) {
                return false;
            }
            postCommand(new CommandContext(callback, command));
            return true;
        }
    }

    public final void startMonitoringRssi(int i) {
        this.monitorRssi = true;
        if (i <= 0) {
            this.updateIntervalMill = RSSI_UPDATE_TIME_INTERVAL;
        } else {
            this.updateIntervalMill = i;
        }
    }

    public final void stopMonitoringRssi() {
        this.monitorRssi = false;
        this.mRssiUpdateHandler.removeCallbacks(this.mRssiUpdateRunnable);
        this.mRssiUpdateHandler.removeCallbacksAndMessages((Object) null);
    }

    public final boolean requestConnectionPriority(int i) {
        return Build.VERSION.SDK_INT >= 21 && this.gatt.requestConnectionPriority(i);
    }

    /* access modifiers changed from: protected */
    public void onConnect() {
        enableMonitorRssi(this.monitorRssi);
    }

    /* access modifiers changed from: protected */
    public void onDisconnect() {
        enableMonitorRssi(false);
    }

    /* access modifiers changed from: protected */
    public void enableMonitorRssi(boolean z) {
        if (z) {
            this.mRssiUpdateHandler.removeCallbacks(this.mRssiUpdateRunnable);
            this.mRssiUpdateHandler.postDelayed(this.mRssiUpdateRunnable, (long) this.updateIntervalMill);
            return;
        }
        this.mRssiUpdateHandler.removeCallbacks(this.mRssiUpdateRunnable);
        this.mRssiUpdateHandler.removeCallbacksAndMessages((Object) null);
    }

    private void postCommand(CommandContext commandContext) {
        TelinkLog.m265d("postCommand");
        this.mInputCommandQueue.add(commandContext);
        synchronized (this.mProcessLock) {
            if (!this.processing.booleanValue()) {
                processCommand();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002f, code lost:
        r0 = r1.command.type;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0035, code lost:
        if (r0 == com.telink.p010lt.ble.Command.CommandType.ENABLE_NOTIFY) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        if (r0 == com.telink.p010lt.ble.Command.CommandType.DISABLE_NOTIFY) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        r5.mOutputCommandQueue.add(r1);
        r0 = r5.mProcessLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0042, code lost:
        monitor-enter(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0049, code lost:
        if (r5.processing.booleanValue() != false) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004b, code lost:
        r5.processing = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        monitor-exit(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0057, code lost:
        r0 = r1.command.delay;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x005b, code lost:
        if (r0 <= 0) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005d, code lost:
        r5.mDelayHandler.postDelayed(r5.mCommandDelayRunnable, (long) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0066, code lost:
        processCommand(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002c, code lost:
        if (r1 != null) goto L_0x002f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void processCommand() {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "processing : "
            r0.append(r1)
            java.lang.Boolean r1 = r5.processing
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.telink.p010lt.util.TelinkLog.m265d(r0)
            java.util.Queue<com.telink.lt.ble.Peripheral$CommandContext> r0 = r5.mInputCommandQueue
            monitor-enter(r0)
            java.util.Queue<com.telink.lt.ble.Peripheral$CommandContext> r1 = r5.mInputCommandQueue     // Catch:{ all -> 0x006a }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x006a }
            if (r1 == 0) goto L_0x0023
            monitor-exit(r0)     // Catch:{ all -> 0x006a }
            return
        L_0x0023:
            java.util.Queue<com.telink.lt.ble.Peripheral$CommandContext> r1 = r5.mInputCommandQueue     // Catch:{ all -> 0x006a }
            java.lang.Object r1 = r1.poll()     // Catch:{ all -> 0x006a }
            com.telink.lt.ble.Peripheral$CommandContext r1 = (com.telink.p010lt.ble.Peripheral.CommandContext) r1     // Catch:{ all -> 0x006a }
            monitor-exit(r0)     // Catch:{ all -> 0x006a }
            if (r1 != 0) goto L_0x002f
            return
        L_0x002f:
            com.telink.lt.ble.Command r0 = r1.command
            com.telink.lt.ble.Command$CommandType r0 = r0.type
            com.telink.lt.ble.Command$CommandType r2 = com.telink.p010lt.ble.Command.CommandType.ENABLE_NOTIFY
            if (r0 == r2) goto L_0x0057
            com.telink.lt.ble.Command$CommandType r2 = com.telink.p010lt.ble.Command.CommandType.DISABLE_NOTIFY
            if (r0 == r2) goto L_0x0057
            java.util.Queue<com.telink.lt.ble.Peripheral$CommandContext> r0 = r5.mOutputCommandQueue
            r0.add(r1)
            java.lang.Object r0 = r5.mProcessLock
            monitor-enter(r0)
            java.lang.Boolean r2 = r5.processing     // Catch:{ all -> 0x0054 }
            boolean r2 = r2.booleanValue()     // Catch:{ all -> 0x0054 }
            if (r2 != 0) goto L_0x0052
            r2 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x0054 }
            r5.processing = r2     // Catch:{ all -> 0x0054 }
        L_0x0052:
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            goto L_0x0057
        L_0x0054:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0054 }
            throw r1
        L_0x0057:
            com.telink.lt.ble.Command r0 = r1.command
            int r0 = r0.delay
            if (r0 <= 0) goto L_0x0066
            android.os.Handler r1 = r5.mDelayHandler
            java.lang.Runnable r2 = r5.mCommandDelayRunnable
            long r3 = (long) r0
            r1.postDelayed(r2, r3)
            goto L_0x0069
        L_0x0066:
            r5.processCommand(r1)
        L_0x0069:
            return
        L_0x006a:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x006a }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.telink.p010lt.ble.Peripheral.processCommand():void");
    }

    /* access modifiers changed from: private */
    public synchronized void processCommand(CommandContext commandContext) {
        Command command = commandContext.command;
        Command.CommandType commandType = command.type;
        TelinkLog.m265d("processCommand : " + command.toString());
        switch (C36091.$SwitchMap$com$telink$lt$ble$Command$CommandType[commandType.ordinal()]) {
            case 1:
                postCommandTimeoutTask();
                readCharacteristic(commandContext, command.serviceUUID, command.characteristicUUID);
                break;
            case 2:
                postCommandTimeoutTask();
                writeCharacteristic(commandContext, command.serviceUUID, command.characteristicUUID, 2, command.data);
                break;
            case 3:
                postCommandTimeoutTask();
                readDescriptor(commandContext, command.serviceUUID, command.characteristicUUID, command.descriptorUUID);
                break;
            case 4:
                postCommandTimeoutTask();
                writeCharacteristic(commandContext, command.serviceUUID, command.characteristicUUID, 1, command.data);
                break;
            case 5:
                enableNotification(commandContext, command.serviceUUID, command.characteristicUUID);
                break;
            case 6:
                disableNotification(commandContext, command.serviceUUID, command.characteristicUUID);
                break;
        }
    }

    /* renamed from: com.telink.lt.ble.Peripheral$1 */
    static /* synthetic */ class C36091 {
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
                com.telink.lt.ble.Command$CommandType r1 = com.telink.p010lt.ble.Command.CommandType.WRITE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$telink$lt$ble$Command$CommandType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.telink.lt.ble.Command$CommandType r1 = com.telink.p010lt.ble.Command.CommandType.READ_DESCRIPTOR     // Catch:{ NoSuchFieldError -> 0x0028 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.telink.p010lt.ble.Peripheral.C36091.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public void commandCompleted() {
        TelinkLog.m265d("commandCompleted");
        synchronized (this.mProcessLock) {
            if (this.processing.booleanValue()) {
                this.processing = false;
            }
        }
        processCommand();
    }

    private void commandSuccess(CommandContext commandContext, Object obj) {
        TelinkLog.m265d("commandSuccess");
        if (commandContext != null) {
            Command command = commandContext.command;
            Command.Callback callback = commandContext.callback;
            commandContext.clear();
            if (callback != null) {
                callback.success(this, command, obj);
            }
        }
    }

    private void commandSuccess(Object obj) {
        commandSuccess(this.mOutputCommandQueue.poll(), obj);
    }

    private void commandError(CommandContext commandContext, String str) {
        TelinkLog.m265d("commandError");
        if (commandContext != null) {
            Command command = commandContext.command;
            Command.Callback callback = commandContext.callback;
            commandContext.clear();
            if (callback != null) {
                callback.error(this, command, str);
            }
        }
    }

    private void commandError(String str) {
        commandError(this.mOutputCommandQueue.poll(), str);
    }

    /* access modifiers changed from: private */
    public boolean commandTimeout(CommandContext commandContext) {
        TelinkLog.m265d("commandTimeout");
        if (commandContext == null) {
            return false;
        }
        Command command = commandContext.command;
        Command.Callback callback = commandContext.callback;
        commandContext.clear();
        if (callback != null) {
            return callback.timeout(this, command);
        }
        return false;
    }

    private void postCommandTimeoutTask() {
        if (this.commandTimeoutMill > 0) {
            this.mTimeoutHandler.removeCallbacksAndMessages((Object) null);
            this.mTimeoutHandler.postDelayed(this.mCommandTimeoutRunnable, (long) this.commandTimeoutMill);
        }
    }

    private void cancelCommandTimeoutTask() {
        this.mTimeoutHandler.removeCallbacksAndMessages((Object) null);
    }

    private void readDescriptor(CommandContext commandContext, UUID uuid, UUID uuid2, UUID uuid3) {
        String str;
        BluetoothGattService service = this.gatt.getService(uuid);
        boolean z = false;
        if (service != null) {
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
            if (characteristic != null) {
                BluetoothGattDescriptor descriptor = characteristic.getDescriptor(uuid3);
                str = "read descriptor error";
                if (descriptor != null && this.gatt.readDescriptor(descriptor)) {
                    z = true;
                    str = "";
                }
            } else {
                str = "read characteristic error";
            }
        } else {
            str = "service is not offered by the remote device";
        }
        if (!z) {
            commandError(str);
            commandCompleted();
        }
    }

    private void readCharacteristic(CommandContext commandContext, UUID uuid, UUID uuid2) {
        String str;
        BluetoothGattService service = this.gatt.getService(uuid);
        boolean z = false;
        if (service != null) {
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
            str = "read characteristic error";
            if (characteristic != null && this.gatt.readCharacteristic(characteristic)) {
                str = "";
                z = true;
            }
        } else {
            str = "service is not offered by the remote device";
        }
        if (!z) {
            commandError(str);
            commandCompleted();
        }
    }

    private void writeCharacteristic(CommandContext commandContext, UUID uuid, UUID uuid2, int i, byte[] bArr) {
        String str;
        BluetoothGattService service = this.gatt.getService(uuid);
        boolean z = false;
        if (service != null) {
            BluetoothGattCharacteristic findWritableCharacteristic = findWritableCharacteristic(service, uuid2, i);
            if (findWritableCharacteristic != null) {
                findWritableCharacteristic.setValue(bArr);
                findWritableCharacteristic.setWriteType(i);
                if (!this.gatt.writeCharacteristic(findWritableCharacteristic)) {
                    str = "write characteristic error";
                } else {
                    str = "";
                    z = true;
                }
            } else {
                str = "no characteristic";
            }
        } else {
            str = "service is not offered by the remote device";
        }
        if (!z) {
            commandError(str);
            commandCompleted();
        }
    }

    private void enableNotification(CommandContext commandContext, UUID uuid, UUID uuid2) {
        String str;
        BluetoothGattService service = this.gatt.getService(uuid);
        boolean z = false;
        if (service != null) {
            BluetoothGattCharacteristic findNotifyCharacteristic = findNotifyCharacteristic(service, uuid2);
            if (findNotifyCharacteristic == null) {
                str = "no characteristic";
            } else if (!this.gatt.setCharacteristicNotification(findNotifyCharacteristic, true)) {
                str = "enable notification error";
            } else {
                this.mNotificationCallbacks.put(generateHashKey(uuid, findNotifyCharacteristic), commandContext);
                str = "";
                z = true;
            }
        } else {
            str = "service is not offered by the remote device";
        }
        if (!z) {
            commandError(commandContext, str);
        } else {
            onEnableNotify();
        }
        commandCompleted();
    }

    private void disableNotification(CommandContext commandContext, UUID uuid, UUID uuid2) {
        String str;
        BluetoothGattService service = this.gatt.getService(uuid);
        boolean z = false;
        if (service != null) {
            BluetoothGattCharacteristic findNotifyCharacteristic = findNotifyCharacteristic(service, uuid2);
            if (findNotifyCharacteristic != null) {
                this.mNotificationCallbacks.remove(generateHashKey(uuid, findNotifyCharacteristic));
                if (!this.gatt.setCharacteristicNotification(findNotifyCharacteristic, false)) {
                    str = "disable notification error";
                } else {
                    z = true;
                    str = "";
                }
            } else {
                str = "no characteristic";
            }
        } else {
            str = "service is not offered by the remote device";
        }
        if (!z) {
            commandError(commandContext, str);
        } else {
            onDisableNotify();
        }
        commandCompleted();
    }

    private BluetoothGattCharacteristic findWritableCharacteristic(BluetoothGattService bluetoothGattService, UUID uuid, int i) {
        int i2 = i == 1 ? 4 : 8;
        for (BluetoothGattCharacteristic next : bluetoothGattService.getCharacteristics()) {
            if ((next.getProperties() & i2) != 0 && uuid.equals(next.getUuid())) {
                return next;
            }
        }
        return null;
    }

    private BluetoothGattCharacteristic findNotifyCharacteristic(BluetoothGattService bluetoothGattService, UUID uuid) {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
        Iterator<BluetoothGattCharacteristic> it = characteristics.iterator();
        while (true) {
            if (!it.hasNext()) {
                bluetoothGattCharacteristic = null;
                break;
            }
            bluetoothGattCharacteristic = it.next();
            if ((bluetoothGattCharacteristic.getProperties() & 16) != 0 && uuid.equals(bluetoothGattCharacteristic.getUuid())) {
                break;
            }
        }
        if (bluetoothGattCharacteristic != null) {
            return bluetoothGattCharacteristic;
        }
        for (BluetoothGattCharacteristic next : characteristics) {
            if ((next.getProperties() & 32) != 0 && uuid.equals(next.getUuid())) {
                return next;
            }
        }
        return bluetoothGattCharacteristic;
    }

    private String generateHashKey(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return generateHashKey(bluetoothGattCharacteristic.getService().getUuid(), bluetoothGattCharacteristic);
    }

    /* access modifiers changed from: protected */
    public String generateHashKey(UUID uuid, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        return String.valueOf(uuid) + "|" + bluetoothGattCharacteristic.getUuid() + "|" + bluetoothGattCharacteristic.getInstanceId();
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        TelinkLog.m265d("onConnectionStateChange  status :" + i + " state : " + i2);
        if (i2 == 2) {
            synchronized (this.mStateLock) {
                this.mConnState = 4;
            }
            BluetoothGatt bluetoothGatt2 = this.gatt;
            if (bluetoothGatt2 == null || !bluetoothGatt2.discoverServices()) {
                TelinkLog.m265d("remote service discovery has been stopped status = " + i2);
                disconnect();
                return;
            }
            onConnect();
            return;
        }
        synchronized (this.mStateLock) {
            TelinkLog.m265d("Close");
            BluetoothGatt bluetoothGatt3 = this.gatt;
            if (bluetoothGatt3 != null) {
                try {
                    bluetoothGatt3.close();
                } catch (Throwable unused) {
                }
                this.mConnState = 16;
            }
            clear();
            this.mConnState = 1;
            onDisconnect();
        }
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        super.onCharacteristicChanged(bluetoothGatt, bluetoothGattCharacteristic);
        CommandContext commandContext = this.mNotificationCallbacks.get(generateHashKey(bluetoothGattCharacteristic));
        if (commandContext != null) {
            onNotify(bluetoothGattCharacteristic.getValue(), commandContext.command.serviceUUID, commandContext.command.characteristicUUID, commandContext.command.tag);
        }
    }

    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicRead(bluetoothGatt, bluetoothGattCharacteristic, i);
        cancelCommandTimeoutTask();
        if (i == 0) {
            commandSuccess(bluetoothGattCharacteristic.getValue());
        } else {
            commandError("read characteristic failed");
        }
        commandCompleted();
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
        cancelCommandTimeoutTask();
        if (i == 0) {
            commandSuccess((Object) null);
        } else {
            commandError("write characteristic fail");
        }
        TelinkLog.m265d("onCharacteristicWrite newStatus : " + i);
        commandCompleted();
    }

    public void onDescriptorRead(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        super.onDescriptorRead(bluetoothGatt, bluetoothGattDescriptor, i);
        cancelCommandTimeoutTask();
        if (i == 0) {
            commandSuccess(bluetoothGattDescriptor.getValue());
        } else {
            commandError("read description failed");
        }
        commandCompleted();
    }

    public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
        super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
        cancelCommandTimeoutTask();
        if (i == 0) {
            commandSuccess((Object) null);
        } else {
            commandError("write description failed");
        }
        commandCompleted();
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        super.onServicesDiscovered(bluetoothGatt, i);
        if (i == 0) {
            List<BluetoothGattService> services = bluetoothGatt.getServices();
            this.mServices = services;
            onServicesDiscovered(services);
            TelinkLog.m265d("Service discovery success:" + services.size());
            return;
        }
        TelinkLog.m265d("Service discovery failed");
        disconnect();
    }

    public void onReadRemoteRssi(BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onReadRemoteRssi(bluetoothGatt, i, i2);
        if (i2 == 0 && i != this.rssi) {
            this.rssi = i;
            onRssiChanged();
        }
    }

    public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
        super.onMtuChanged(bluetoothGatt, i, i2);
        TelinkLog.m265d("mtu changed : " + i);
    }

    public void onReliableWriteCompleted(BluetoothGatt bluetoothGatt, int i) {
        super.onReliableWriteCompleted(bluetoothGatt, i);
    }

    /* renamed from: com.telink.lt.ble.Peripheral$CommandContext */
    private final class CommandContext {
        public Command.Callback callback;
        public Command command;

        public CommandContext(Command.Callback callback2, Command command2) {
            this.callback = callback2;
            this.command = command2;
        }

        public void clear() {
            this.command = null;
            this.callback = null;
        }
    }

    /* renamed from: com.telink.lt.ble.Peripheral$RssiUpdateRunnable */
    private final class RssiUpdateRunnable implements Runnable {
        private RssiUpdateRunnable() {
        }

        /* synthetic */ RssiUpdateRunnable(Peripheral peripheral, C36091 r2) {
            this();
        }

        public void run() {
            if (Peripheral.this.monitorRssi && Peripheral.this.isConnected()) {
                if (Peripheral.this.gatt != null) {
                    Peripheral.this.gatt.readRemoteRssi();
                }
                Peripheral.this.mRssiUpdateHandler.postDelayed(Peripheral.this.mRssiUpdateRunnable, (long) Peripheral.this.updateIntervalMill);
            }
        }
    }

    /* renamed from: com.telink.lt.ble.Peripheral$CommandTimeoutRunnable */
    private final class CommandTimeoutRunnable implements Runnable {
        private CommandTimeoutRunnable() {
        }

        /* synthetic */ CommandTimeoutRunnable(Peripheral peripheral, C36091 r2) {
            this();
        }

        public void run() {
            synchronized (Peripheral.this.mOutputCommandQueue) {
                CommandContext peek = Peripheral.this.mOutputCommandQueue.peek();
                if (peek != null) {
                    Command command = peek.command;
                    Command.Callback callback = peek.callback;
                    if (Peripheral.this.commandTimeout(peek)) {
                        peek.command = command;
                        peek.callback = callback;
                        Peripheral.this.processCommand(peek);
                    } else {
                        Peripheral.this.mOutputCommandQueue.poll();
                        Peripheral.this.commandCompleted();
                    }
                }
            }
        }
    }

    /* renamed from: com.telink.lt.ble.Peripheral$CommandDelayRunnable */
    private final class CommandDelayRunnable implements Runnable {
        private CommandDelayRunnable() {
        }

        /* synthetic */ CommandDelayRunnable(Peripheral peripheral, C36091 r2) {
            this();
        }

        public void run() {
            synchronized (Peripheral.this.mOutputCommandQueue) {
                Peripheral.this.processCommand(Peripheral.this.mOutputCommandQueue.peek());
            }
        }
    }
}
