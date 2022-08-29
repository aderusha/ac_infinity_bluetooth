package com.eternal.base.data.ble;

import android.bluetooth.BluetoothGatt;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleMtuChangedCallback;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleScanState;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.exception.BleException;
import com.eternal.base.exception.NotCloseScanException;
import com.eternal.base.exception.NotEnableBluetoothException;
import com.eternal.base.global.BluetoothKey;
import com.eternal.base.protocol.CFamilialResolution;
import com.eternal.base.protocol.ProtocolResolution;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.protocol.StateMachine;
import com.eternal.base.utils.ByteUtils;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.time.DurationKt;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.schedulers.Schedulers;

public class BleServer {
    private static BleServer INSTANCE = null;
    public static final int MSG_CHA_READ_TIME = 16;
    /* access modifiers changed from: private */
    public MainHandler mainHandler = new MainHandler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final BleManager manager;

    public interface DisconnectListener {
        void onDisconnect(BleDevice bleDevice);
    }

    private BleServer() {
        BleManager instance = BleManager.getInstance();
        this.manager = instance;
        instance.setOperateTimeout(DurationKt.NANOS_IN_MILLIS);
    }

    private final class MainHandler extends Handler {
        MainHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 16 && message.obj != null) {
                BleStatue bleStatue = (BleStatue) message.obj;
                KLog.m65e("蓝牙 读取超时 " + ByteUtils.bytes2HexString(bleStatue.getPacket()));
                if ((bleStatue.getMessage() instanceof CustomMessage) || (bleStatue.getMessage() instanceof NoneAckMessage)) {
                    BleServer.this.onError(bleStatue, new BleException("Timeout!"));
                } else {
                    BleServer.this.write(bleStatue);
                }
            }
        }
    }

    public static BleServer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BleServer();
        }
        return INSTANCE;
    }

    public void enable() {
        if (!this.manager.isBlueEnable()) {
            this.manager.enableBluetooth();
        }
    }

    public void disable() {
        if (this.manager.isBlueEnable()) {
            this.manager.disableBluetooth();
        }
    }

    public boolean isEnable() {
        return this.manager.isBlueEnable();
    }

    public void reset() {
        this.manager.disconnectAllDevice();
        this.manager.destroy();
        this.manager.init(Utils.getApp());
    }

    public void clear(Collection<BleStatue> collection) {
        List<BleDevice> allConnectedDevice = this.manager.getAllConnectedDevice();
        Iterator<BleDevice> it = allConnectedDevice.iterator();
        while (it.hasNext()) {
            BleDevice next = it.next();
            Iterator<BleStatue> it2 = collection.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (it2.next().getDevice().getMac().equals(next.getMac())) {
                        it.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        for (BleDevice disconnect : allConnectedDevice) {
            this.manager.disconnect(disconnect);
        }
    }

    /* access modifiers changed from: package-private */
    public void closeScan() {
        KLog.m68i("cancel scan");
        if (this.manager.getScanSate() == BleScanState.STATE_SCANNING) {
            this.manager.cancelScan();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isScanning() {
        return this.manager.getScanSate() == BleScanState.STATE_SCANNING;
    }

    public Single<List<BleDevice>> scan(final BleScanRuleConfig bleScanRuleConfig) {
        return Single.create(new SingleOnSubscribe<List<BleDevice>>() {
            public void subscribe(final SingleEmitter<List<BleDevice>> singleEmitter) throws Exception {
                BleServer.this.manager.initScanRule(bleScanRuleConfig);
                BleServer.this.manager.scan(new BleScanCallback() {
                    public void onScanning(BleDevice bleDevice) {
                    }

                    public void onScanFinished(List<BleDevice> list) {
                        singleEmitter.onSuccess(list);
                    }

                    public void onScanStarted(boolean z) {
                        if (z) {
                            return;
                        }
                        if (BleServer.this.manager.isBlueEnable()) {
                            singleEmitter.onError(new NotCloseScanException());
                        } else {
                            singleEmitter.onError(new NotEnableBluetoothException());
                        }
                    }
                });
            }
        });
    }

    public Observable<List<BleDevice>> scanCycle(final BleScanRuleConfig bleScanRuleConfig) {
        KLog.m74w("BLE----BleServer---scanCycle");
        return Observable.create(new ObservableOnSubscribe<List<BleDevice>>() {
            public void subscribe(final ObservableEmitter<List<BleDevice>> observableEmitter) throws Exception {
                BleServer.this.manager.initScanRule(bleScanRuleConfig);
                BleServer.this.manager.scan(new BleScanCallback() {
                    public void onScanFinished(List<BleDevice> list) {
                        KLog.m74w("BLE----BleServer---onScanFinished");
                        observableEmitter.onNext(list);
                    }

                    public void onScanStarted(boolean z) {
                        KLog.m74w("BLE----BleServer---onScanStarted---" + z);
                        if (z) {
                            return;
                        }
                        if (BleServer.this.manager.isBlueEnable()) {
                            observableEmitter.onError(new NotCloseScanException());
                        } else {
                            observableEmitter.onError(new NotEnableBluetoothException());
                        }
                    }

                    public void onScanning(BleDevice bleDevice) {
                        observableEmitter.onNext(new ArrayList(Collections.singletonList(bleDevice)));
                    }
                });
            }
        });
    }

    public Single<BleDevice> connect(final BleDevice bleDevice, final DisconnectListener disconnectListener) {
        return Single.create(new SingleOnSubscribe<BleDevice>() {
            public void subscribe(final SingleEmitter<BleDevice> singleEmitter) throws Exception {
                KLog.m74w("BLE----连接蓝牙" + bleDevice.getMac());
                BleServer.this.manager.connect(bleDevice, (BleGattCallback) new BleGattCallback() {
                    public void onStartConnect() {
                    }

                    public void onConnectFail(BleDevice bleDevice, com.clj.fastble.exception.BleException bleException) {
                        KLog.m65e(bleException.toString());
                        if (!singleEmitter.isDisposed()) {
                            singleEmitter.onError(new BleException(bleException.toString()));
                        }
                    }

                    public void onConnectSuccess(BleDevice bleDevice, BluetoothGatt bluetoothGatt, int i) {
                        KLog.m68i(bleDevice + "连接成功！");
                        if (!singleEmitter.isDisposed()) {
                            singleEmitter.onSuccess(bleDevice);
                        }
                    }

                    public void onDisConnected(boolean z, BleDevice bleDevice, BluetoothGatt bluetoothGatt, int i) {
                        KLog.m68i(bleDevice + "断开连接！");
                        disconnectListener.onDisconnect(bleDevice);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: package-private */
    public Single<BleStatue> notify(final BleStatue bleStatue, final String str, final String str2) {
        return Single.create(new SingleOnSubscribe<BleStatue>() {
            public void subscribe(final SingleEmitter<BleStatue> singleEmitter) throws Exception {
                BleServer.this.manager.notify(bleStatue.getDevice(), str, str2, new NotifyCallback(bleStatue) {
                    public void onNotifySuccess() {
                        KLog.m65e("onNotifySuccess:" + ByteUtils.bytes2HexString(getMessages().getPacket()));
                        singleEmitter.onSuccess(getMessages());
                    }

                    public void onNotifyFailure(com.clj.fastble.exception.BleException bleException) {
                        KLog.m65e("onNotifyFailure:" + bleException);
                        singleEmitter.onError(new BleException(bleException.toString()));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: package-private */
    public Single<BleStatue> readDeviceInfo(final BleStatue bleStatue, final String str) {
        return Single.create(new SingleOnSubscribe<BleStatue>() {
            /* JADX WARNING: Removed duplicated region for block: B:22:0x0076  */
            /* JADX WARNING: Removed duplicated region for block: B:24:0x008b  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void subscribe(final p014io.reactivex.SingleEmitter<com.eternal.base.data.ble.BleStatue> r8) throws java.lang.Exception {
                /*
                    r7 = this;
                    com.eternal.base.data.ble.BleServer r0 = com.eternal.base.data.ble.BleServer.this
                    com.clj.fastble.BleManager r0 = r0.manager
                    com.eternal.base.data.ble.BleStatue r1 = r2
                    com.clj.fastble.data.BleDevice r1 = r1.getDevice()
                    android.bluetooth.BluetoothGatt r0 = r0.getBluetoothGatt(r1)
                    r1 = 0
                    if (r0 == 0) goto L_0x0068
                    java.util.List r0 = r0.getServices()
                    if (r0 == 0) goto L_0x0068
                    java.util.Iterator r0 = r0.iterator()
                L_0x001d:
                    boolean r2 = r0.hasNext()
                    if (r2 == 0) goto L_0x0068
                    java.lang.Object r2 = r0.next()
                    android.bluetooth.BluetoothGattService r2 = (android.bluetooth.BluetoothGattService) r2
                    java.util.List r3 = r2.getCharacteristics()
                    if (r3 == 0) goto L_0x001d
                    java.util.Iterator r3 = r3.iterator()
                L_0x0033:
                    boolean r4 = r3.hasNext()
                    if (r4 == 0) goto L_0x001d
                    java.lang.Object r4 = r3.next()
                    android.bluetooth.BluetoothGattCharacteristic r4 = (android.bluetooth.BluetoothGattCharacteristic) r4
                    java.util.UUID r4 = r4.getUuid()
                    java.lang.String r4 = r4.toString()
                    java.lang.String r5 = "-"
                    java.lang.String[] r5 = r4.split(r5)
                    int r6 = r5.length
                    if (r6 <= 0) goto L_0x0033
                    r6 = 0
                    r5 = r5[r6]
                    java.lang.String r5 = r5.toUpperCase()
                    java.lang.String r6 = r3
                    boolean r5 = r5.endsWith(r6)
                    if (r5 == 0) goto L_0x0033
                    java.util.UUID r0 = r2.getUuid()
                    java.lang.String r1 = r0.toString()
                    goto L_0x0069
                L_0x0068:
                    r4 = r1
                L_0x0069:
                    boolean r0 = android.text.TextUtils.isEmpty(r1)
                    if (r0 != 0) goto L_0x008b
                    boolean r0 = android.text.TextUtils.isEmpty(r4)
                    if (r0 == 0) goto L_0x0076
                    goto L_0x008b
                L_0x0076:
                    com.eternal.base.data.ble.BleServer r0 = com.eternal.base.data.ble.BleServer.this
                    com.clj.fastble.BleManager r0 = r0.manager
                    com.eternal.base.data.ble.BleStatue r2 = r2
                    com.clj.fastble.data.BleDevice r2 = r2.getDevice()
                    com.eternal.base.data.ble.BleServer$5$1 r3 = new com.eternal.base.data.ble.BleServer$5$1
                    r3.<init>(r8)
                    r0.read(r2, r1, r4, r3)
                    return
                L_0x008b:
                    java.lang.String r0 = r3
                    java.lang.String r1 = "00002A28"
                    boolean r0 = r0.equalsIgnoreCase(r1)
                    if (r0 == 0) goto L_0x009c
                    com.eternal.base.data.ble.BleStatue r0 = r2
                    java.lang.String r1 = "2.0.0"
                    r0.softwareVersion = r1
                    goto L_0x00bb
                L_0x009c:
                    java.lang.String r0 = r3
                    java.lang.String r1 = "00002A27"
                    boolean r0 = r0.equalsIgnoreCase(r1)
                    java.lang.String r1 = "1.0"
                    if (r0 == 0) goto L_0x00ad
                    com.eternal.base.data.ble.BleStatue r0 = r2
                    r0.hardwareVersion = r1
                    goto L_0x00bb
                L_0x00ad:
                    java.lang.String r0 = r3
                    java.lang.String r2 = "00002A26"
                    boolean r0 = r0.equalsIgnoreCase(r2)
                    if (r0 == 0) goto L_0x00bb
                    com.eternal.base.data.ble.BleStatue r0 = r2
                    r0.firmwareVersion = r1
                L_0x00bb:
                    com.eternal.base.data.ble.BleStatue r0 = r2
                    r8.onSuccess(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.eternal.base.data.ble.BleServer.C15915.subscribe(io.reactivex.SingleEmitter):void");
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void tryWrite(BleStatue bleStatue, Message message) {
        if (bleStatue.offer(message)) {
            write(bleStatue);
        }
    }

    /* access modifiers changed from: private */
    public void write(final BleStatue bleStatue) {
        if (RepositoryInjection.providerDeviceRepository().isConnect(bleStatue.getMac()) && bleStatue.isRetry() && bleStatue.getPacket().length != 0) {
            Message obtainMessage = this.mainHandler.obtainMessage();
            obtainMessage.what = 16;
            obtainMessage.obj = bleStatue;
            this.mainHandler.sendMessageDelayed(obtainMessage, 8000);
            bleStatue.addRetryCount();
            boolean isConfigNet = bleStatue.isConfigNet();
            this.manager.write(bleStatue.getDevice(), isConfigNet ? BluetoothKey.UUID_SERVICE_ESP : BluetoothKey.UUID_SERVICE, isConfigNet ? BluetoothKey.UUID_WRITE_ESP : BluetoothKey.UUID_WRITE, bleStatue.getPacket(), false, new BleWriteCallback() {
                public void onWriteSuccess(int i, int i2, byte[] bArr) {
                    if (bleStatue.getMessage() instanceof NoneAckMessage) {
                        BleServer.this.mainHandler.removeMessages(16, bleStatue);
                        if (bleStatue.onNext(new byte[0])) {
                            BleServer.this.write(bleStatue);
                        }
                    }
                    KLog.m68i("蓝牙 写入：" + ByteUtils.bytes2HexString(bArr));
                }

                public void onWriteFailure(com.clj.fastble.exception.BleException bleException) {
                    BleServer.this.mainHandler.removeMessages(16, bleStatue);
                    KLog.m65e(bleException.toString());
                    KLog.m65e("蓝牙 写入失败：" + ByteUtils.bytes2HexString(bleStatue.getPacket()));
                    if (bleException.getCode() == 102 && !"This device not connect!".equals(bleException.getDescription())) {
                        Completable.create(new CompletableOnSubscribe() {
                            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                                try {
                                    Thread.sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                BleServer.this.write(bleStatue);
                                completableEmitter.onComplete();
                            }
                        }).subscribeOn(Schedulers.m983io()).subscribe();
                    }
                }
            });
        } else if (bleStatue.isConfigNet()) {
            onError(bleStatue, new BleException("Timeout!"));
        } else {
            onComplement(bleStatue);
        }
    }

    /* access modifiers changed from: package-private */
    public void onComplement(BleStatue bleStatue) {
        if (bleStatue.onComplement()) {
            write(bleStatue);
        }
    }

    /* access modifiers changed from: package-private */
    public void onError(BleStatue bleStatue, Throwable th) {
        if (bleStatue.onError(th)) {
            write(bleStatue);
        }
    }

    /* access modifiers changed from: package-private */
    public void dispose(BleStatue bleStatue, ObservableMessage observableMessage) {
        if (bleStatue.remove(observableMessage)) {
            write(bleStatue);
        }
    }

    public void clear(BleDevice bleDevice) {
        this.manager.disconnect(bleDevice);
    }

    /* access modifiers changed from: package-private */
    public Single<BleStatue> setMtu(final BleDevice bleDevice, final int i) {
        final BleStatue bleStatue = new BleStatue(bleDevice);
        return Single.create(new SingleOnSubscribe<BleStatue>() {
            public void subscribe(final SingleEmitter<BleStatue> singleEmitter) throws Exception {
                BleServer.this.manager.setMtu(bleDevice, i, new BleMtuChangedCallback() {
                    public void onSetMTUFailure(com.clj.fastble.exception.BleException bleException) {
                        KLog.m65e("onSetMTUFailure");
                        singleEmitter.onError(new BleException(bleException.toString()));
                    }

                    public void onMtuChanged(int i) {
                        KLog.m65e("onMtuChanged: " + i);
                        singleEmitter.onSuccess(bleStatue);
                    }
                });
            }
        });
    }

    abstract class NotifyCallback extends BleNotifyCallback implements StateMachine.Callback {
        private final StateMachine machine;
        private final BleStatue messages;

        public NotifyCallback(BleStatue bleStatue) {
            this.messages = bleStatue;
            this.machine = new StateMachine(this, (byte) bleStatue.getType());
        }

        /* access modifiers changed from: protected */
        public BleStatue getMessages() {
            return this.messages;
        }

        public void onCharacteristicChanged(byte[] bArr) {
            if (!(bArr[0] == 30 && bArr[1] == -1) && (this.messages.getMessage() instanceof CustomMessage)) {
                BleServer.this.mainHandler.removeMessages(16, this.messages);
                KLog.m65e("配网 接收数据: " + ByteUtils.bytes2HexString(bArr));
                if (this.messages.onNext(bArr)) {
                    BleServer.this.write(this.messages);
                    return;
                }
                return;
            }
            this.machine.onNext(bArr);
        }

        public void onPacket(byte[] bArr) {
            DeviceModel deviceModel;
            if (bArr[0] == 30 && bArr[1] == -1) {
                if (ProtocolTransformer.isDeviceC(this.messages.getType())) {
                    deviceModel = CFamilialResolution.parseInfo(bArr);
                    RepositoryInjection.providerLogRepository().showNotify(deviceModel, this.messages);
                } else {
                    deviceModel = ProtocolResolution.parseInfo(bArr);
                }
                this.messages.onNext(deviceModel);
                RxBus.getDefault().post(new BluetoothEvent((byte) 5, this.messages.getMac(), deviceModel.isDegree ? 1 : 0));
            } else if (bArr.length >= 6 && ByteUtils.getShort(bArr, 4) == this.messages.getSequence()) {
                KLog.m65e("蓝牙 接收:" + ByteUtils.bytes2HexString(bArr));
                BleServer.this.mainHandler.removeMessages(16, this.messages);
                if (this.messages.onNext(bArr)) {
                    BleServer.this.write(this.messages);
                }
            }
        }
    }
}
