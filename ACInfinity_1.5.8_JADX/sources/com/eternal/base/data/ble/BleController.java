package com.eternal.base.data.ble;

import android.text.TextUtils;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.eternal.base.concat.DeviceMinModel;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.HistoryInfo;
import com.eternal.base.concat.PortList;
import com.eternal.base.concat.PortSetting;
import com.eternal.base.data.ble.BleServer;
import com.eternal.base.database.entity.History;
import com.eternal.base.database.entity.Log;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.database.entity.NotificationMessage;
import com.eternal.base.exception.BleException;
import com.eternal.base.global.BluetoothKey;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.protocol.BaseProtocol;
import com.eternal.base.protocol.CFamilialResolution;
import com.eternal.base.protocol.EFamilialResolution;
import com.eternal.base.protocol.EFamilialResolutionV4;
import com.eternal.base.protocol.ModelProtocolResolution;
import com.eternal.base.protocol.ProtocolResolution;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.utils.ByteUtils;
import com.eternal.base.utils.ScanRecord;
import com.eternal.base.utils.TimeReceiver;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.utils.KLog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import p014io.reactivex.Completable;
import p014io.reactivex.Flowable;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeEmitter;
import p014io.reactivex.MaybeOnSubscribe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.SingleSource;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.BooleanSupplier;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class BleController implements IBleControl, Comparator<BleDevice> {
    /* access modifiers changed from: private */
    public ConcurrentHashMap<String, BleStatue> connects = new ConcurrentHashMap<>();
    /* access modifiers changed from: private */
    public BleServer server;

    public BleController(BleServer bleServer) {
        this.server = bleServer;
    }

    public void clean() {
        this.server.clear(this.connects.values());
    }

    public void closeScan() {
        this.server.closeScan();
    }

    public boolean isScanning() {
        return this.server.isScanning();
    }

    public Single<List<BleDevice>> scan(BleScanRuleConfig bleScanRuleConfig) {
        KLog.m74w("BLE----scan");
        return this.server.scan(bleScanRuleConfig).map(new Function<List<BleDevice>, List<BleDevice>>() {
            public List<BleDevice> apply(List<BleDevice> list) {
                Iterator<BleDevice> it = list.iterator();
                while (it.hasNext()) {
                    byte[] manufacturerSpecificData = ScanRecord.parseFromBytes(it.next().getScanRecord()).getManufacturerSpecificData(BluetoothKey.FEATURES);
                    if (manufacturerSpecificData == null || !ByteUtils.getBit(manufacturerSpecificData[13], 0)) {
                        it.remove();
                    }
                }
                Collections.sort(list, BleController.this);
                return list;
            }
        });
    }

    public Observable<List<BleDevice>> scanCycle(BleScanRuleConfig bleScanRuleConfig, final boolean z) {
        KLog.m74w("BLE----scanCycle");
        return this.server.scanCycle(bleScanRuleConfig).map(new Function<List<BleDevice>, List<BleDevice>>() {
            public List<BleDevice> apply(List<BleDevice> list) {
                Iterator<BleDevice> it = list.iterator();
                while (it.hasNext()) {
                    byte[] manufacturerSpecificData = ScanRecord.parseFromBytes(it.next().getScanRecord()).getManufacturerSpecificData(BluetoothKey.FEATURES);
                    if (manufacturerSpecificData == null || (z && (manufacturerSpecificData.length <= 13 || !ByteUtils.getBit(manufacturerSpecificData[13], 0)))) {
                        it.remove();
                    }
                }
                Collections.sort(list, BleController.this);
                return list;
            }
        });
    }

    public List<BleDevice> disableBle() {
        KLog.m74w("BLE----disableBle");
        this.server.reset();
        ArrayList arrayList = new ArrayList(this.connects.size());
        for (BleStatue next : this.connects.values()) {
            if (next != null) {
                next.clear();
                arrayList.add(next.getDevice());
            }
        }
        this.connects.clear();
        TimeReceiver.unregister();
        return arrayList;
    }

    public Single<BleStatue> connect(final BleDevice bleDevice, final BleServer.DisconnectListener disconnectListener) {
        KLog.m74w("BLE----connect");
        return this.server.connect(bleDevice, new BleServer.DisconnectListener() {
            public void onDisconnect(BleDevice bleDevice) {
                BleStatue bleStatue = (BleStatue) BleController.this.connects.remove(bleDevice.getMac());
                if (BleController.this.connects.isEmpty()) {
                    TimeReceiver.unregister();
                }
                if (bleStatue != null) {
                    bleStatue.clear();
                }
                disconnectListener.onDisconnect(bleDevice);
            }
        }).flatMap(new Function<BleDevice, SingleSource<BleStatue>>() {
            public SingleSource<BleStatue> apply(BleDevice bleDevice) {
                KLog.m65e("connect setMtu");
                return BleController.this.server.setMtu(bleDevice, BluetoothKey.DEFAULT_MTU);
            }
        }).flatMap(new Function<BleStatue, SingleSource<BleStatue>>() {
            public SingleSource<BleStatue> apply(BleStatue bleStatue) throws Exception {
                KLog.m65e("connect notify");
                return BleController.this.server.notify(bleStatue, BluetoothKey.UUID_SERVICE, BluetoothKey.UUID_NOTIFY);
            }
        }).flatMap(new Function<BleStatue, SingleSource<BleStatue>>() {
            public SingleSource<BleStatue> apply(BleStatue bleStatue) throws Exception {
                if (bleStatue.getType() != 11) {
                    return Single.just(bleStatue);
                }
                return BleController.this.server.notify(bleStatue, BluetoothKey.UUID_SERVICE_ESP, BluetoothKey.UUID_NOTIFY_ESP);
            }
        }).flatMap(new Function<BleStatue, SingleSource<BleStatue>>() {
            public SingleSource<BleStatue> apply(BleStatue bleStatue) throws Exception {
                KLog.m62d("读取软件版本号");
                return BleController.this.server.readDeviceInfo(bleStatue, BluetoothKey.SOFTWAREVERSION_UUID);
            }
        }).flatMap(new Function<BleStatue, SingleSource<BleStatue>>() {
            public SingleSource<BleStatue> apply(BleStatue bleStatue) throws Exception {
                KLog.m62d("读取硬件版本号");
                return BleController.this.server.readDeviceInfo(bleStatue, BluetoothKey.HARDWAREVERSION_UUID);
            }
        }).doOnSuccess(new Consumer<BleStatue>() {
            public void accept(BleStatue bleStatue) {
                if (BleController.this.connects.isEmpty()) {
                    TimeReceiver.register();
                }
                KLog.m65e("connect put getMac");
                BleController.this.connects.put(bleDevice.getMac(), bleStatue);
            }
        }).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) {
                KLog.m74w("connect error disconnect");
                BleController.this.server.clear(bleDevice);
            }
        }).doOnDispose(new Action() {
            public void run() {
                KLog.m74w("dispose connect disconnecting");
                BleController.this.server.clear(bleDevice);
            }
        });
    }

    public void disConnect(BleStatue bleStatue) {
        KLog.m74w("BLE----disConnect");
        this.server.clear(bleStatue.getDevice());
        bleStatue.clear();
        this.connects.remove(bleStatue.getDevice().getMac());
    }

    public BleStatue getConnect(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.connects.get(str);
    }

    public boolean isConnect(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.connects.containsKey(str);
    }

    public Single<Boolean> syncTime(BleStatue bleStatue) {
        KLog.m74w("BLE----syncTime-1");
        SingleMessage singleMessage = new SingleMessage(ProtocolResolution.setTimeData(bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                return Boolean.valueOf(BaseProtocol.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> syncCTime(BleStatue bleStatue) {
        KLog.m74w("BLE----syncTime-2");
        SingleMessage singleMessage = new SingleMessage(CFamilialResolution.setTime(bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) {
                KLog.m65e("syncCTime success!");
                return Boolean.valueOf(BaseProtocol.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> syncETime(BleStatue bleStatue) {
        KLog.m74w("BLE----syncETime");
        SingleMessage singleMessage = new SingleMessage(EFamilialResolution.setTimeData(bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                return Boolean.valueOf(EFamilialResolution.checkResult(bArr));
            }
        });
    }

    public Completable syncConnectedTime() {
        KLog.m74w("BLE----syncConnectedTime");
        ArrayList arrayList = new ArrayList(this.connects.size());
        for (BleStatue next : this.connects.values()) {
            if (next.getType() == 1 || next.getType() == 2 || next.getType() == 6) {
                arrayList.add(syncTime(next));
            } else if (next.getType() == 7 || next.getType() == 11 || next.getType() == 9 || next.getType() == 12) {
                arrayList.add(syncETime(next));
            } else {
                arrayList.add(syncCTime(next));
            }
        }
        return Single.merge(arrayList).ignoreElements();
    }

    public Single<DeviceTFP> getTFP(String str, final byte b) {
        KLog.m74w("BLE----getTFP");
        final BleStatue bleStatue = this.connects.get(str);
        if (bleStatue == null) {
            return Single.error((Throwable) new BleException("disconnect"));
        }
        return Single.create(new SingleOnSubscribe<SingleMessage>() {
            public void subscribe(SingleEmitter<SingleMessage> singleEmitter) {
                byte[] bArr;
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11) {
                    bArr = EFamilialResolution.getTFPData(b, bleStatue.getLastSequence());
                } else if (bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    bArr = EFamilialResolution.getF_TFPData(b, bleStatue.getLastSequence());
                } else {
                    bArr = ProtocolResolution.getTFPData(b, bleStatue.getLastSequence());
                }
                SingleMessage singleMessage = new SingleMessage(bArr);
                BleController.this.server.tryWrite(bleStatue, singleMessage);
                singleEmitter.onSuccess(singleMessage);
            }
        }).flatMap(new Function<SingleMessage, SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(SingleMessage singleMessage) throws Exception {
                return singleMessage.getSingle();
            }
        }).map(new Function<byte[], DeviceTFP>() {
            public DeviceTFP apply(byte[] bArr) throws Exception {
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11) {
                    return EFamilialResolution.parseTFP(bArr, bleStatue.getVersion());
                }
                if (bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    return EFamilialResolution.parseF_TFP(bArr, bleStatue.getVersion());
                }
                return ProtocolResolution.parseTFP(bArr);
            }
        });
    }

    public Single<List<NotificationMessage>> getNotificationMessage(final BleStatue bleStatue, final byte b) {
        return Single.create(new SingleOnSubscribe<SingleMessage>() {
            public void subscribe(SingleEmitter<SingleMessage> singleEmitter) {
                byte[] bArr;
                if (bleStatue.getType() != 7 && bleStatue.getType() != 11 && bleStatue.getType() != 9 && bleStatue.getType() != 12) {
                    bArr = ProtocolResolution.getNotificationMessage(bleStatue.getLastSequence());
                } else if (bleStatue.getVersion() >= 5) {
                    bArr = EFamilialResolutionV4.getNotificationMessage(b, bleStatue.getLastSequence());
                } else {
                    bArr = EFamilialResolution.getNotificationMessage(b, bleStatue.getLastSequence());
                }
                SingleMessage singleMessage = new SingleMessage(bArr);
                BleController.this.server.tryWrite(bleStatue, singleMessage);
                singleEmitter.onSuccess(singleMessage);
            }
        }).flatMap(new Function<SingleMessage, SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(SingleMessage singleMessage) throws Exception {
                return singleMessage.getSingle();
            }
        }).map(new Function<byte[], List<NotificationMessage>>() {
            public List<NotificationMessage> apply(byte[] bArr) throws Exception {
                KLog.m65e("通知：" + ByteUtils.bytes2HexString(bArr));
                if (bleStatue.getType() != 7 && bleStatue.getType() != 11 && bleStatue.getType() != 9 && bleStatue.getType() != 12) {
                    List<NotificationMessage> parseNotificationMessage = ProtocolResolution.parseNotificationMessage(bleStatue.getMac(), b, bArr);
                    if (bleStatue.getType() != 6) {
                        return parseNotificationMessage;
                    }
                    for (NotificationMessage next : parseNotificationMessage) {
                        if (next.type == 2 || next.type == 1) {
                            next.values[0] = (byte) (next.values[0] & -52);
                        }
                    }
                    return parseNotificationMessage;
                } else if (bleStatue.getVersion() >= 5) {
                    return EFamilialResolutionV4.parseNotificationMessage(bleStatue.getMac(), bArr);
                } else {
                    return EFamilialResolution.parseNotificationMessage(bleStatue.getMac(), b, bArr);
                }
            }
        });
    }

    public Flowable<DeviceModel> getBroadcast(BleStatue bleStatue) {
        KLog.m74w("BLE----getBroadcast");
        if (bleStatue == null) {
            return null;
        }
        return bleStatue.getBroadcast();
    }

    public Single<DeviceMinModel> getCModel(final BleStatue bleStatue) {
        return Single.create(new SingleOnSubscribe<SingleMessage>() {
            public void subscribe(SingleEmitter<SingleMessage> singleEmitter) throws Exception {
                SingleMessage singleMessage = new SingleMessage(ModelProtocolResolution.getCModelAlarmData(bleStatue.getLastSequence()));
                BleController.this.server.tryWrite(bleStatue, singleMessage);
                singleEmitter.onSuccess(singleMessage);
            }
        }).flatMap(new Function<SingleMessage, SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(SingleMessage singleMessage) throws Exception {
                return singleMessage.getSingle();
            }
        }).map(new Function<byte[], DeviceMinModel>() {
            public DeviceMinModel apply(byte[] bArr) throws Exception {
                return ModelProtocolResolution.parseCMinModel(bArr);
            }
        });
    }

    public Single<Boolean> setCModel(BleStatue bleStatue, DeviceModel deviceModel) {
        SingleMessage singleMessage = new SingleMessage(CFamilialResolution.setModelData(deviceModel, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) {
                return Boolean.valueOf(CFamilialResolution.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> setSetting(String str, DeviceSetting deviceSetting) {
        BleStatue bleStatue = this.connects.get(str);
        if (bleStatue == null) {
            return Single.error((Throwable) new BleException("disconnect"));
        }
        SingleMessage singleMessage = new SingleMessage(ProtocolResolution.setSettingData(deviceSetting, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) {
                return Boolean.valueOf(ProtocolResolution.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> setBSetting(String str, DeviceSetting deviceSetting) {
        BleStatue bleStatue = this.connects.get(str);
        if (bleStatue == null) {
            return Single.error((Throwable) new BleException("disconnect"));
        }
        SingleMessage singleMessage = new SingleMessage(ProtocolResolution.setBSettingData(deviceSetting, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                return Boolean.valueOf(ProtocolResolution.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> setCSetting(String str, DeviceSetting deviceSetting) {
        BleStatue bleStatue = this.connects.get(str);
        if (bleStatue == null) {
            return Single.error((Throwable) new BleException("disconnect"));
        }
        SingleMessage singleMessage = new SingleMessage(CFamilialResolution.setSettingData(deviceSetting, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                return Boolean.valueOf(BaseProtocol.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> resetDeviceToFactory(String str) {
        BleStatue bleStatue = this.connects.get(str);
        if (bleStatue == null) {
            return Single.error((Throwable) new BleException("disconnect"));
        }
        SingleMessage singleMessage = new SingleMessage(CFamilialResolution.setResetDeviceToFactory(bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                return Boolean.valueOf(BaseProtocol.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> setESetting(String str, byte b, DeviceSetting deviceSetting) {
        BleStatue bleStatue = this.connects.get(str);
        if (bleStatue == null) {
            return Single.error((Throwable) new BleException("disconnect"));
        }
        SingleMessage singleMessage = new SingleMessage(EFamilialResolution.setSettingData(b, deviceSetting, bleStatue.getVersion(), bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                return Boolean.valueOf(EFamilialResolution.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> setFSetting(String str, byte b, DeviceSetting deviceSetting) {
        BleStatue bleStatue = this.connects.get(str);
        if (bleStatue == null) {
            return Single.error((Throwable) new BleException("disconnect"));
        }
        SingleMessage singleMessage = new SingleMessage(EFamilialResolution.setFSettingData(b, deviceSetting, bleStatue.getVersion(), bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                return Boolean.valueOf(EFamilialResolution.checkResult(bArr));
            }
        });
    }

    public Single<DeviceSetting> getSetting(BleStatue bleStatue) {
        SingleMessage singleMessage = new SingleMessage(ProtocolResolution.getSettingData(bleStatue.getLastSequence(), bleStatue.getVersion()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], DeviceSetting>() {
            public DeviceSetting apply(byte[] bArr) throws Exception {
                return ProtocolResolution.parseSetting(bArr);
            }
        });
    }

    public Single<DeviceSetting> getBSetting(BleStatue bleStatue) {
        SingleMessage singleMessage = new SingleMessage(ProtocolResolution.getBSettingData(bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], DeviceSetting>() {
            public DeviceSetting apply(byte[] bArr) throws Exception {
                return ProtocolResolution.parseSetting(bArr);
            }
        });
    }

    public Single<DeviceSetting> getCSetting(BleStatue bleStatue) {
        SingleMessage singleMessage = new SingleMessage(CFamilialResolution.getSettingData(bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], DeviceSetting>() {
            public DeviceSetting apply(byte[] bArr) {
                return CFamilialResolution.parseSetting(bArr);
            }
        });
    }

    public Single<DeviceSetting> getESetting(final BleStatue bleStatue, byte b) {
        SingleMessage singleMessage = new SingleMessage(EFamilialResolution.getSettingData((byte) 0, bleStatue.getVersion(), bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], DeviceSetting>() {
            public DeviceSetting apply(byte[] bArr) throws Exception {
                return EFamilialResolution.parseSetting(bArr, bleStatue.getVersion());
            }
        }).flatMap(new Function<DeviceSetting, SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(DeviceSetting deviceSetting) throws Exception {
                boolean z;
                byte[] bArr;
                Iterator<PortSetting> it = deviceSetting.portList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    PortSetting next = it.next();
                    if (next.isPlug) {
                        z = ProtocolTransformer.isOutletDevice(bleStatue.getType(), next.fanType);
                        break;
                    }
                }
                if (z) {
                    bArr = EFamilialResolution.getFSettingData((byte) 0, bleStatue.getVersion(), bleStatue.getLastSequence());
                } else {
                    bArr = EFamilialResolution.getSettingData((byte) 0, bleStatue.getVersion(), bleStatue.getLastSequence());
                }
                SingleMessage singleMessage = new SingleMessage(bArr);
                BleController.this.server.tryWrite(bleStatue, singleMessage);
                return singleMessage.getSingle();
            }
        }).map(new Function<byte[], DeviceSetting>() {
            public DeviceSetting apply(byte[] bArr) throws Exception {
                return EFamilialResolution.parseSetting(bArr, bleStatue.getVersion());
            }
        }).flatMap(new Function<DeviceSetting, SingleSource<DeviceSetting>>() {
            public SingleSource<DeviceSetting> apply(final DeviceSetting deviceSetting) throws Exception {
                byte[] bArr;
                ArrayList arrayList = new ArrayList();
                for (final PortSetting next : deviceSetting.portList) {
                    if (ProtocolTransformer.isOutletDevice(bleStatue.getType(), next.fanType)) {
                        bArr = EFamilialResolution.getFSettingData(next.f138id, bleStatue.getVersion(), bleStatue.getLastSequence());
                    } else {
                        bArr = EFamilialResolution.getSettingData(next.f138id, bleStatue.getVersion(), bleStatue.getLastSequence());
                    }
                    SingleMessage singleMessage = new SingleMessage(bArr);
                    BleController.this.server.tryWrite(bleStatue, singleMessage);
                    arrayList.add(singleMessage.getSingle().map(new Function<byte[], DeviceSetting>() {
                        public DeviceSetting apply(byte[] bArr) throws Exception {
                            DeviceSetting parseSetting = EFamilialResolution.parseSetting(bArr, bleStatue.getVersion());
                            if (parseSetting != null) {
                                next.transitionTemperature = parseSetting.transitionTemperature;
                                next.transitionTemperatureC = parseSetting.transitionTemperatureC;
                                next.transitionHumidity = parseSetting.transitionHumidity;
                                next.transitionVpd = parseSetting.transitionVpd;
                                next.typeOn = parseSetting.typeOn;
                                next.typeOff = parseSetting.typeOff;
                            }
                            return deviceSetting;
                        }
                    }));
                }
                return Single.merge(arrayList).toList().map(new Function<List<DeviceSetting>, DeviceSetting>() {
                    public DeviceSetting apply(List<DeviceSetting> list) throws Exception {
                        return deviceSetting;
                    }
                });
            }
        });
    }

    public Single<DeviceSetting> getFSetting(final BleStatue bleStatue, byte b) {
        SingleMessage singleMessage = new SingleMessage(EFamilialResolution.getFSettingData((byte) 0, bleStatue.getVersion(), bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], DeviceSetting>() {
            public DeviceSetting apply(byte[] bArr) throws Exception {
                return EFamilialResolution.parseSetting(bArr, bleStatue.getVersion());
            }
        }).flatMap(new Function<DeviceSetting, SingleSource<DeviceSetting>>() {
            public SingleSource<DeviceSetting> apply(final DeviceSetting deviceSetting) throws Exception {
                ArrayList arrayList = new ArrayList();
                for (final PortSetting next : deviceSetting.portList) {
                    SingleMessage singleMessage = new SingleMessage(EFamilialResolution.getFSettingData(next.f138id, bleStatue.getVersion(), bleStatue.getLastSequence()));
                    BleController.this.server.tryWrite(bleStatue, singleMessage);
                    arrayList.add(singleMessage.getSingle().map(new Function<byte[], DeviceSetting>() {
                        public DeviceSetting apply(byte[] bArr) throws Exception {
                            DeviceSetting parseSetting = EFamilialResolution.parseSetting(bArr, bleStatue.getVersion());
                            if (parseSetting != null) {
                                next.transitionTemperature = parseSetting.transitionTemperature;
                                next.transitionTemperatureC = parseSetting.transitionTemperatureC;
                                next.transitionHumidity = parseSetting.transitionHumidity;
                                next.transitionVpd = parseSetting.transitionVpd;
                                next.typeOn = parseSetting.typeOn;
                                next.typeOff = parseSetting.typeOff;
                            }
                            return deviceSetting;
                        }
                    }));
                }
                return Single.merge(arrayList).toList().map(new Function<List<DeviceSetting>, DeviceSetting>() {
                    public DeviceSetting apply(List<DeviceSetting> list) throws Exception {
                        return deviceSetting;
                    }
                });
            }
        });
    }

    public Single<Boolean> getTempUnit(BleStatue bleStatue) {
        byte[] bArr;
        if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
            bArr = EFamilialResolution.getTempUnitData((byte) 0, bleStatue.getLastSequence());
        } else {
            bArr = ProtocolResolution.getTempUnitData(bleStatue.getLastSequence());
        }
        SingleMessage singleMessage = new SingleMessage(bArr);
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) {
                return EFamilialResolution.parseTempUnit(bArr);
            }
        });
    }

    public Single<DeviceMinModel> getModel(final BleStatue bleStatue, final byte b) {
        final DeviceMinModel deviceMinModel = new DeviceMinModel();
        return Single.create(new SingleOnSubscribe<SingleMessage>() {
            public void subscribe(SingleEmitter<SingleMessage> singleEmitter) throws Exception {
                byte[] bArr;
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    bArr = EFamilialResolution.getModelData(b, bleStatue.getLastSequence());
                } else {
                    bArr = ProtocolResolution.getModelData(bleStatue.getLastSequence());
                }
                SingleMessage singleMessage = new SingleMessage(bArr);
                BleController.this.server.tryWrite(bleStatue, singleMessage);
                singleEmitter.onSuccess(singleMessage);
            }
        }).flatMap(new Function<SingleMessage, SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(SingleMessage singleMessage) throws Exception {
                return singleMessage.getSingle();
            }
        }).flatMap(new Function<byte[], SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(byte[] bArr) throws Exception {
                byte[] bArr2;
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    EFamilialResolution.parseMinModel(deviceMinModel, bArr, bleStatue.getVersion());
                } else {
                    ProtocolResolution.parseMinModel(deviceMinModel, bArr);
                }
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    bArr2 = BleController.this.getEModeData(bleStatue, b, deviceMinModel.model);
                } else {
                    bArr2 = BleController.this.getModeData(bleStatue, deviceMinModel.model);
                }
                SingleMessage singleMessage = new SingleMessage(bArr2);
                BleController.this.server.tryWrite(bleStatue, singleMessage);
                return singleMessage.getSingle();
            }
        }).map(new Function<byte[], DeviceMinModel>() {
            public DeviceMinModel apply(byte[] bArr) throws Exception {
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    EFamilialResolution.initDeviceMinModel(deviceMinModel, bArr, bleStatue.getVersion());
                } else {
                    ProtocolResolution.initDeviceMinModel(deviceMinModel, bArr, bleStatue.getVersion());
                    if (bleStatue.getType() == 6 && deviceMinModel.model == 3) {
                        deviceMinModel.values[0] = (byte) (deviceMinModel.values[0] & -52);
                    }
                }
                deviceMinModel.port = b;
                return deviceMinModel;
            }
        });
    }

    public Single<PortList> getPortList(final BleStatue bleStatue) {
        SingleMessage singleMessage = new SingleMessage(EFamilialResolution.getPortListData((byte) 0, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], PortList>() {
            public PortList apply(byte[] bArr) {
                return EFamilialResolution.parsePortList(bArr, bleStatue.getVersion());
            }
        });
    }

    public Single<DeviceMinModel> getModel(final BleStatue bleStatue, final byte b, byte b2) {
        final DeviceMinModel deviceMinModel = new DeviceMinModel();
        deviceMinModel.model = b2;
        return Single.create(new SingleOnSubscribe<SingleMessage>() {
            public void subscribe(SingleEmitter<SingleMessage> singleEmitter) throws Exception {
                byte[] bArr;
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    bArr = BleController.this.getEModeData(bleStatue, b, deviceMinModel.model);
                } else {
                    bArr = BleController.this.getModeData(bleStatue, deviceMinModel.model);
                }
                SingleMessage singleMessage = new SingleMessage(bArr);
                BleController.this.server.tryWrite(bleStatue, singleMessage);
                singleEmitter.onSuccess(singleMessage);
            }
        }).flatMap(new Function<SingleMessage, SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(SingleMessage singleMessage) throws Exception {
                return singleMessage.getSingle();
            }
        }).map(new Function<byte[], DeviceMinModel>() {
            public DeviceMinModel apply(byte[] bArr) throws Exception {
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    EFamilialResolution.initDeviceMinModel(deviceMinModel, bArr, bleStatue.getVersion());
                } else {
                    ProtocolResolution.initDeviceMinModel(deviceMinModel, bArr, bleStatue.getVersion());
                    if (bleStatue.getType() == 6 && deviceMinModel.model == 3) {
                        deviceMinModel.values[0] = (byte) (deviceMinModel.values[0] & -52);
                    }
                }
                deviceMinModel.port = b;
                return deviceMinModel;
            }
        });
    }

    /* access modifiers changed from: private */
    public byte[] getModeData(BleStatue bleStatue, byte b) {
        switch (b) {
            case 1:
                return ProtocolResolution.getOffModelData(bleStatue.getLastSequence());
            case 2:
                return ProtocolResolution.getOnModelData(bleStatue.getLastSequence());
            case 3:
                return ProtocolResolution.getAutoModelData(bleStatue.getLastSequence(), ProtocolTransformer.isOutletDevice(bleStatue.getType()));
            case 4:
                return ProtocolResolution.getTimeOnModelData(bleStatue.getLastSequence());
            case 5:
                return ProtocolResolution.getTimeOffModelData(bleStatue.getLastSequence());
            case 6:
                return ProtocolResolution.getCycleModelData(bleStatue.getLastSequence());
            default:
                return ProtocolResolution.getSchedModelData(bleStatue.getLastSequence());
        }
    }

    /* access modifiers changed from: private */
    public byte[] getEModeData(BleStatue bleStatue, byte b, byte b2) {
        switch (b2) {
            case 1:
                return EFamilialResolution.getOffModelData(b, bleStatue.getLastSequence());
            case 2:
                return EFamilialResolution.getOnModelData(b, bleStatue.getLastSequence());
            case 3:
                return EFamilialResolution.getAutoModelData(b, bleStatue.getLastSequence(), ProtocolTransformer.isOutletDevice(bleStatue.getType()));
            case 4:
                return EFamilialResolution.getTimeOnModelData(b, bleStatue.getLastSequence());
            case 5:
                return EFamilialResolution.getTimeOffModelData(b, bleStatue.getLastSequence());
            case 6:
                return EFamilialResolution.getCycleModelData(b, bleStatue.getLastSequence());
            case 8:
                return EFamilialResolution.getVpdModelData(b, bleStatue.getLastSequence());
            default:
                return EFamilialResolution.getSchedModelData(b, bleStatue.getLastSequence());
        }
    }

    public Single<Boolean> setModel(final BleStatue bleStatue, byte b, DeviceModel deviceModel) {
        byte[] bArr;
        if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
            bArr = EFamilialResolution.setModelData(b, deviceModel, bleStatue.getLastSequence());
        } else {
            bArr = ProtocolResolution.setModelData(deviceModel, bleStatue.getLastSequence());
        }
        SingleMessage singleMessage = new SingleMessage(bArr);
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    return Boolean.valueOf(EFamilialResolution.checkResult(bArr));
                }
                return Boolean.valueOf(ProtocolResolution.checkResult(bArr));
            }
        });
    }

    public Single<Boolean> setModel(final BleStatue bleStatue, byte b, byte b2) {
        byte[] bArr;
        if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
            bArr = EFamilialResolution.setModelData(b, b2, bleStatue.getLastSequence());
        } else {
            bArr = ProtocolResolution.setModelData(b2, bleStatue.getLastSequence());
        }
        SingleMessage singleMessage = new SingleMessage(bArr);
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    return Boolean.valueOf(EFamilialResolution.checkResult(bArr));
                }
                return Boolean.valueOf(ProtocolResolution.checkResult(bArr));
            }
        });
    }

    public Single<DeviceModel> initMode(final BleStatue bleStatue, final byte b) {
        byte[] bArr;
        if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
            bArr = EFamilialResolution.getAllModelData(b, bleStatue.getLastSequence());
        } else {
            bArr = ProtocolResolution.getAllModelData(bleStatue.getLastSequence());
        }
        SingleMessage singleMessage = new SingleMessage(bArr);
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], DeviceModel>() {
            public DeviceModel apply(byte[] bArr) {
                DeviceModel deviceModel;
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    deviceModel = EFamilialResolution.parseDeviceModel(bArr);
                } else {
                    deviceModel = ProtocolResolution.parseDeviceModel(bArr);
                }
                deviceModel.port = b;
                return deviceModel;
            }
        });
    }

    public Single<Boolean> setNotification(final BleStatue bleStatue, Notification notification, boolean z, boolean z2, boolean z3, boolean z4) {
        byte[] bArr;
        if (bleStatue.getVersion() >= 5 && notification.type == 1 && z3) {
            bArr = EFamilialResolutionV4.setAutomationOperation((byte) notification.f144id, z4 ? notification.childId : 15, z, z2, bleStatue.getLastSequence());
        } else if (bleStatue.getType() != 7 && bleStatue.getType() != 11 && bleStatue.getType() != 9 && bleStatue.getType() != 12) {
            bArr = ProtocolResolution.setNotificationData(notification, z, z2, bleStatue.getLastSequence());
        } else if (bleStatue.getVersion() >= 5) {
            bArr = EFamilialResolutionV4.setNotificationData(notification, z, z2, bleStatue.getLastSequence());
        } else {
            bArr = EFamilialResolution.setNotificationData(notification, z, z2, bleStatue.getLastSequence());
        }
        SingleMessage singleMessage = new SingleMessage(bArr);
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                boolean z;
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    z = EFamilialResolution.checkResult(bArr);
                } else {
                    z = ProtocolResolution.checkResult(bArr);
                }
                if (!z) {
                    KLog.m65e("result false:" + ByteUtils.bytes2HexString(bArr));
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public Single<Boolean> setAutomationOrder(final BleStatue bleStatue, List<Byte> list, byte b, boolean z) {
        SingleMessage singleMessage = new SingleMessage(EFamilialResolutionV4.setAutomationOrder(list, b, z, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                boolean z;
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    z = EFamilialResolution.checkResult(bArr);
                } else {
                    z = ProtocolResolution.checkResult(bArr);
                }
                if (!z) {
                    KLog.m65e("result false:" + ByteUtils.bytes2HexString(bArr));
                }
                return Boolean.valueOf(z);
            }
        });
    }

    public Single<List<Notification>> getNotifications(final BleStatue bleStatue, final byte b) {
        return Single.create(new SingleOnSubscribe<SingleMessage>() {
            public void subscribe(SingleEmitter<SingleMessage> singleEmitter) throws Exception {
                byte[] bArr;
                if (bleStatue.getType() != 7 && bleStatue.getType() != 11 && bleStatue.getType() != 9 && bleStatue.getType() != 12) {
                    bArr = ProtocolResolution.getNotificationData(bleStatue.getLastSequence());
                } else if (bleStatue.getVersion() >= 5) {
                    bArr = EFamilialResolutionV4.getNotificationData(b, bleStatue.getLastSequence());
                } else {
                    bArr = EFamilialResolution.getNotificationData(b, bleStatue.getLastSequence());
                }
                SingleMessage singleMessage = new SingleMessage(bArr);
                BleController.this.server.tryWrite(bleStatue, singleMessage);
                singleEmitter.onSuccess(singleMessage);
            }
        }).flatMap(new Function<SingleMessage, SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(SingleMessage singleMessage) throws Exception {
                return singleMessage.getSingle();
            }
        }).map(new Function<byte[], List<Notification>>() {
            public List<Notification> apply(byte[] bArr) throws Exception {
                if (bleStatue.getType() != 7 && bleStatue.getType() != 11 && bleStatue.getType() != 9 && bleStatue.getType() != 12) {
                    List<Notification> parseNotifications = ProtocolResolution.parseNotifications(bArr, bleStatue.getType() == 6);
                    if (bleStatue.getType() == 6) {
                        for (int i = 0; i < parseNotifications.size(); i++) {
                            Notification notification = parseNotifications.get(i);
                            notification.tmpHum = (byte) (notification.tmpHum & -68);
                        }
                    }
                    return parseNotifications;
                } else if (bleStatue.getVersion() >= 5) {
                    return EFamilialResolutionV4.parseNotifications(bArr);
                } else {
                    return EFamilialResolution.parseNotifications(b, bArr);
                }
            }
        });
    }

    public Observable<Log> getLog(final BleStatue bleStatue, long j, int i) {
        BaseParse baseParse;
        long currentTimeMillis = System.currentTimeMillis();
        if (bleStatue.getType() == 6) {
            baseParse = new LogDParse(currentTimeMillis);
        } else {
            baseParse = new LogParse(currentTimeMillis);
        }
        final BaseParse baseParse2 = baseParse;
        SingleMessage singleMessage = new SingleMessage(ProtocolResolution.getLogStart(j, currentTimeMillis, i, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().observeOn(Schedulers.m983io()).subscribeOn(Schedulers.m983io()).map(new Function<byte[], Integer>() {
            public Integer apply(byte[] bArr) {
                return Integer.valueOf(ProtocolResolution.parseLogSize(bArr));
            }
        }).observeOn(Schedulers.m983io()).subscribeOn(Schedulers.m983io()).flatMapObservable(new Function<Integer, ObservableSource<Log>>() {
            /* access modifiers changed from: private */
            public int now;
            /* access modifiers changed from: private */
            public int total;

            static /* synthetic */ int access$404(C151357 r1) {
                int i = r1.now + 1;
                r1.now = i;
                return i;
            }

            public ObservableSource<Log> apply(Integer num) {
                this.total = num.intValue();
                this.now = 0;
                return Observable.create(new ObservableOnSubscribe<ObservableMessage>() {
                    public void subscribe(ObservableEmitter<ObservableMessage> observableEmitter) throws Exception {
                        ObservableMessage observableMessage = new ObservableMessage(ProtocolResolution.getLog(C151357.this.total, C151357.this.now, bleStatue.getLastSequence()));
                        BleController.this.server.tryWrite(bleStatue, observableMessage);
                        observableEmitter.onNext(observableMessage);
                        observableEmitter.onComplete();
                    }
                }).flatMap(new Function<ObservableMessage, ObservableSource<byte[]>>() {
                    public ObservableSource<byte[]> apply(final ObservableMessage observableMessage) throws Exception {
                        return observableMessage.getSingle().doOnDispose(new Action() {
                            public void run() throws Exception {
                                observableMessage.reset().flatMap(new Function<byte[], ObservableSource<?>>() {
                                    public ObservableSource<?> apply(byte[] bArr) throws Exception {
                                        baseParse2.setNow(bArr);
                                        return Observable.create(baseParse2).doOnComplete(new Action() {
                                            public void run() throws Exception {
                                                if (baseParse2.isEnd()) {
                                                    BleController.this.server.onComplement(bleStatue);
                                                }
                                            }
                                        });
                                    }
                                }).ignoreElements().subscribe();
                            }
                        });
                    }
                }).flatMap(new Function<byte[], ObservableSource<Log>>() {
                    public ObservableSource<Log> apply(byte[] bArr) throws Exception {
                        baseParse2.setNow(bArr);
                        return Observable.create(baseParse2).doOnComplete(new Action() {
                            public void run() throws Exception {
                                if (baseParse2.isEnd()) {
                                    BleController.this.server.onComplement(bleStatue);
                                }
                            }
                        });
                    }
                }).repeatUntil(new BooleanSupplier() {
                    public boolean getAsBoolean() throws Exception {
                        C151357.access$404(C151357.this);
                        return C151357.this.total < C151357.this.now;
                    }
                });
            }
        });
    }

    public Observable<Log> getELog(final BleStatue bleStatue, final byte b, long j, int i) {
        byte[] bArr;
        long currentTimeMillis = System.currentTimeMillis();
        final LogEParse logEParse = new LogEParse(currentTimeMillis);
        if (bleStatue.getVersion() >= 5) {
            bArr = EFamilialResolutionV4.getLogStart(j, currentTimeMillis, i, bleStatue.getLastSequence());
        } else {
            bArr = EFamilialResolution.getLogStart(b, j, currentTimeMillis, i, bleStatue.getLastSequence());
        }
        SingleMessage singleMessage = new SingleMessage(bArr);
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().observeOn(Schedulers.m983io()).subscribeOn(Schedulers.m983io()).map(new Function<byte[], Integer>() {
            public Integer apply(byte[] bArr) {
                return Integer.valueOf(EFamilialResolution.parseLogSize(bArr));
            }
        }).observeOn(Schedulers.m983io()).subscribeOn(Schedulers.m983io()).flatMapObservable(new Function<Integer, ObservableSource<Log>>() {
            /* access modifiers changed from: private */
            public int now;
            /* access modifiers changed from: private */
            public int total;

            static /* synthetic */ int access$604(C152359 r1) {
                int i = r1.now + 1;
                r1.now = i;
                return i;
            }

            public ObservableSource<Log> apply(Integer num) {
                this.total = num.intValue();
                this.now = 0;
                return Observable.create(new ObservableOnSubscribe<ObservableMessage>() {
                    public void subscribe(ObservableEmitter<ObservableMessage> observableEmitter) throws Exception {
                        byte[] bArr;
                        if (bleStatue.getVersion() >= 5) {
                            bArr = EFamilialResolutionV4.getLog(C152359.this.total, C152359.this.now, bleStatue.getLastSequence());
                        } else {
                            bArr = EFamilialResolution.getLog(b, C152359.this.total, C152359.this.now, bleStatue.getLastSequence());
                        }
                        ObservableMessage observableMessage = new ObservableMessage(bArr);
                        BleController.this.server.tryWrite(bleStatue, observableMessage);
                        observableEmitter.onNext(observableMessage);
                        observableEmitter.onComplete();
                    }
                }).flatMap(new Function<ObservableMessage, ObservableSource<byte[]>>() {
                    public ObservableSource<byte[]> apply(final ObservableMessage observableMessage) throws Exception {
                        return observableMessage.getSingle().doOnDispose(new Action() {
                            public void run() throws Exception {
                                observableMessage.reset().flatMap(new Function<byte[], ObservableSource<?>>() {
                                    public ObservableSource<?> apply(byte[] bArr) throws Exception {
                                        logEParse.setNow(bArr);
                                        return Observable.create(logEParse).doOnComplete(new Action() {
                                            public void run() throws Exception {
                                                if (logEParse.isEnd()) {
                                                    BleController.this.server.onComplement(bleStatue);
                                                }
                                            }
                                        });
                                    }
                                }).ignoreElements().subscribe();
                            }
                        });
                    }
                }).flatMap(new Function<byte[], ObservableSource<Log>>() {
                    public ObservableSource<Log> apply(byte[] bArr) throws Exception {
                        logEParse.setNow(bArr);
                        return Observable.create(logEParse).doOnComplete(new Action() {
                            public void run() throws Exception {
                                if (logEParse.isEnd()) {
                                    BleController.this.server.onComplement(bleStatue);
                                }
                            }
                        });
                    }
                }).repeatUntil(new BooleanSupplier() {
                    public boolean getAsBoolean() throws Exception {
                        C152359.access$604(C152359.this);
                        return C152359.this.total < C152359.this.now;
                    }
                });
            }
        });
    }

    public Observable<Log> getCLog(final BleStatue bleStatue, long j) {
        KLog.m65e("refresh c log");
        final LogCParse logCParse = new LogCParse();
        SingleMessage singleMessage = new SingleMessage(CFamilialResolution.getInfo(bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        final LogCParse logCParse2 = logCParse;
        final long j2 = j;
        final BleStatue bleStatue2 = bleStatue;
        return singleMessage.getSingle().observeOn(Schedulers.m983io()).subscribeOn(Schedulers.m983io()).flatMap(new Function<byte[], SingleSource<int[]>>() {
            public SingleSource<int[]> apply(byte[] bArr) {
                final int[] iArr = new int[2];
                KLog.m65e("result" + ByteUtils.bytes2HexString(bArr));
                iArr[0] = CFamilialResolution.parseLogSize(bArr);
                logCParse2.setList(CFamilialResolution.parseHistoryInfo(bArr));
                SingleMessage singleMessage = new SingleMessage(CFamilialResolution.getLogId(j2, bleStatue2.getLastSequence()));
                BleController.this.server.tryWrite(bleStatue2, singleMessage);
                return singleMessage.getSingle().map(new Function<byte[], int[]>() {
                    public int[] apply(byte[] bArr) {
                        iArr[1] = CFamilialResolution.parseLogId(bArr);
                        return iArr;
                    }
                });
            }
        }).observeOn(Schedulers.m983io()).subscribeOn(Schedulers.m983io()).flatMapObservable(new Function<int[], ObservableSource<Log>>() {
            /* access modifiers changed from: private */
            public int now;
            /* access modifiers changed from: private */
            public int total;

            static /* synthetic */ int access$812(C153461 r1, int i) {
                int i2 = r1.now + i;
                r1.now = i2;
                return i2;
            }

            public ObservableSource<Log> apply(int[] iArr) throws Exception {
                this.total = iArr[0];
                this.now = iArr[1];
                return Maybe.create(new MaybeOnSubscribe<ObservableMessage>() {
                    public void subscribe(MaybeEmitter<ObservableMessage> maybeEmitter) throws Exception {
                        ObservableMessage observableMessage;
                        int access$900 = C153461.this.total - C153461.this.now;
                        if (access$900 <= 0) {
                            maybeEmitter.onComplete();
                            return;
                        }
                        if (access$900 < 20) {
                            observableMessage = new ObservableMessage(CFamilialResolution.getLog(C153461.this.now, access$900, bleStatue.getLastSequence()));
                        } else {
                            observableMessage = new ObservableMessage(CFamilialResolution.getLog(C153461.this.now, 20, bleStatue.getLastSequence()));
                        }
                        BleController.this.server.tryWrite(bleStatue, observableMessage);
                        maybeEmitter.onSuccess(observableMessage);
                    }
                }).observeOn(Schedulers.m983io()).subscribeOn(Schedulers.m983io()).flatMapObservable(new Function<ObservableMessage, ObservableSource<byte[]>>() {
                    public ObservableSource<byte[]> apply(ObservableMessage observableMessage) throws Exception {
                        return observableMessage.getSingle();
                    }
                }).flatMap(new Function<byte[], ObservableSource<Log>>() {
                    public ObservableSource<Log> apply(byte[] bArr) {
                        logCParse.setNow(bArr);
                        return Observable.create(logCParse).doOnComplete(new Action() {
                            public void run() {
                                if (logCParse.total == -1) {
                                    BleController.this.server.onComplement(bleStatue);
                                }
                            }
                        });
                    }
                }).repeatUntil(new BooleanSupplier() {
                    public boolean getAsBoolean() throws Exception {
                        C153461.access$812(C153461.this, 20);
                        return C153461.this.now > C153461.this.total;
                    }
                });
            }
        });
    }

    public Observable<History> getHistory(final BleStatue bleStatue, long j) {
        final BaseParse baseParse;
        long currentTimeMillis = System.currentTimeMillis();
        if (bleStatue.getType() == 6) {
            baseParse = new HistoryDParse(currentTimeMillis);
        } else {
            baseParse = new HistoryParse(currentTimeMillis);
        }
        SingleMessage singleMessage = new SingleMessage(ProtocolResolution.getHistoryStart(j, currentTimeMillis, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Integer>() {
            public Integer apply(byte[] bArr) throws Exception {
                return Integer.valueOf(ProtocolResolution.parseLogSize(bArr));
            }
        }).flatMapObservable(new Function<Integer, ObservableSource<History>>() {
            /* access modifiers changed from: private */
            public int now;
            /* access modifiers changed from: private */
            public int total;

            static /* synthetic */ int access$1004(C154263 r1) {
                int i = r1.now + 1;
                r1.now = i;
                return i;
            }

            public ObservableSource<History> apply(Integer num) {
                this.total = num.intValue();
                this.now = 0;
                return Observable.create(new ObservableOnSubscribe<ObservableMessage>() {
                    public void subscribe(ObservableEmitter<ObservableMessage> observableEmitter) throws Exception {
                        ObservableMessage observableMessage = new ObservableMessage(ProtocolResolution.getHistory(C154263.this.total, C154263.this.now, bleStatue.getLastSequence()));
                        BleController.this.server.tryWrite(bleStatue, observableMessage);
                        observableEmitter.onNext(observableMessage);
                        observableEmitter.onComplete();
                    }
                }).flatMap(new Function<ObservableMessage, ObservableSource<byte[]>>() {
                    public ObservableSource<byte[]> apply(final ObservableMessage observableMessage) throws Exception {
                        return observableMessage.getSingle().doOnDispose(new Action() {
                            public void run() {
                                observableMessage.reset().flatMap(new Function<byte[], ObservableSource<?>>() {
                                    public ObservableSource<?> apply(byte[] bArr) {
                                        baseParse.setNow(bArr);
                                        return Observable.create(baseParse).doOnComplete(new Action() {
                                            public void run() {
                                                if (baseParse.isEnd()) {
                                                    BleController.this.server.onComplement(bleStatue);
                                                }
                                            }
                                        });
                                    }
                                }).ignoreElements().subscribe();
                            }
                        });
                    }
                }).flatMap(new Function<byte[], ObservableSource<History>>() {
                    public ObservableSource<History> apply(byte[] bArr) {
                        baseParse.setNow(bArr);
                        return Observable.create(baseParse).doOnComplete(new Action() {
                            public void run() throws Exception {
                                if (baseParse.isEnd()) {
                                    BleController.this.server.onComplement(bleStatue);
                                }
                            }
                        });
                    }
                }).repeatUntil(new BooleanSupplier() {
                    public boolean getAsBoolean() throws Exception {
                        C154263.access$1004(C154263.this);
                        RxBus.getDefault().post(new ProgressEvent((byte) 2, (((float) C154263.this.now) / ((float) C154263.this.total)) * 100.0f));
                        return C154263.this.total < C154263.this.now;
                    }
                });
            }
        });
    }

    public Observable<History> getEHistory(final BleStatue bleStatue, long j) {
        long currentTimeMillis = System.currentTimeMillis();
        final HistoryEParse historyEParse = new HistoryEParse(currentTimeMillis);
        SingleMessage singleMessage = new SingleMessage(ProtocolResolution.getHistoryStart(j, currentTimeMillis, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Integer>() {
            public Integer apply(byte[] bArr) throws Exception {
                return Integer.valueOf(ProtocolResolution.parseLogSize(bArr));
            }
        }).flatMapObservable(new Function<Integer, ObservableSource<History>>() {
            /* access modifiers changed from: private */
            public int now;
            /* access modifiers changed from: private */
            public int total;

            static /* synthetic */ int access$1204(C155265 r1) {
                int i = r1.now + 1;
                r1.now = i;
                return i;
            }

            public ObservableSource<History> apply(Integer num) {
                this.total = num.intValue();
                this.now = 0;
                return Observable.create(new ObservableOnSubscribe<ObservableMessage>() {
                    public void subscribe(ObservableEmitter<ObservableMessage> observableEmitter) throws Exception {
                        ObservableMessage observableMessage = new ObservableMessage(ProtocolResolution.getHistory(C155265.this.total, C155265.this.now, bleStatue.getLastSequence()));
                        BleController.this.server.tryWrite(bleStatue, observableMessage);
                        observableEmitter.onNext(observableMessage);
                        observableEmitter.onComplete();
                    }
                }).flatMap(new Function<ObservableMessage, ObservableSource<byte[]>>() {
                    public ObservableSource<byte[]> apply(final ObservableMessage observableMessage) throws Exception {
                        return observableMessage.getSingle().doOnDispose(new Action() {
                            public void run() {
                                observableMessage.reset().flatMap(new Function<byte[], ObservableSource<?>>() {
                                    public ObservableSource<?> apply(byte[] bArr) {
                                        historyEParse.setNow(bArr);
                                        return Observable.create(historyEParse).doOnComplete(new Action() {
                                            public void run() {
                                                if (historyEParse.isEnd()) {
                                                    BleController.this.server.onComplement(bleStatue);
                                                }
                                            }
                                        });
                                    }
                                }).ignoreElements().subscribe();
                            }
                        });
                    }
                }).flatMap(new Function<byte[], ObservableSource<History>>() {
                    public ObservableSource<History> apply(byte[] bArr) {
                        historyEParse.setNow(bArr);
                        return Observable.create(historyEParse).doOnComplete(new Action() {
                            public void run() throws Exception {
                                if (historyEParse.isEnd()) {
                                    BleController.this.server.onComplement(bleStatue);
                                }
                            }
                        });
                    }
                }).repeatUntil(new BooleanSupplier() {
                    public boolean getAsBoolean() throws Exception {
                        C155265.access$1204(C155265.this);
                        RxBus.getDefault().post(new ProgressEvent((byte) 2, (((float) C155265.this.now) / ((float) C155265.this.total)) * 100.0f));
                        return C155265.this.total < C155265.this.now;
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public int getHistoryInfo(List<HistoryInfo> list, long j) {
        int i = 0;
        if (list.size() == 1) {
            return 0;
        }
        int size = list.size() - 1;
        while (i + 1 != size) {
            int i2 = (i + size) / 2;
            HistoryInfo historyInfo = list.get(i2);
            if (((long) historyInfo.calibrated) > j) {
                size = i2;
            } else if (((long) historyInfo.calibrated) >= j) {
                return i2;
            } else {
                i = i2;
            }
        }
        return i;
    }

    public Observable<History> getCHistory(BleStatue bleStatue, long j) {
        final HistoryCParse historyCParse = new HistoryCParse();
        SingleMessage singleMessage = new SingleMessage(CFamilialResolution.getInfo(bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        final long j2 = j;
        final BleStatue bleStatue2 = bleStatue;
        return singleMessage.getSingle().map(new Function<byte[], List<HistoryInfo>>() {
            public List<HistoryInfo> apply(byte[] bArr) {
                return CFamilialResolution.parseHistoryInfo(bArr);
            }
        }).flatMapObservable(new Function<List<HistoryInfo>, ObservableSource<History>>() {
            /* access modifiers changed from: private */
            public int chunk;
            /* access modifiers changed from: private */
            public int index;
            /* access modifiers changed from: private */
            public HistoryInfo info;
            /* access modifiers changed from: private */
            public int now;
            /* access modifiers changed from: private */
            public int total;

            static /* synthetic */ int access$1608(C156267 r2) {
                int i = r2.chunk;
                r2.chunk = i + 1;
                return i;
            }

            static /* synthetic */ int access$1712(C156267 r1, int i) {
                int i2 = r1.index + i;
                r1.index = i2;
                return i2;
            }

            static /* synthetic */ int access$1812(C156267 r1, int i) {
                int i2 = r1.now + i;
                r1.now = i2;
                return i2;
            }

            static /* synthetic */ int access$1820(C156267 r1, int i) {
                int i2 = r1.now - i;
                r1.now = i2;
                return i2;
            }

            public ObservableSource<History> apply(final List<HistoryInfo> list) {
                int access$1400 = BleController.this.getHistoryInfo(list, j2);
                this.chunk = access$1400;
                HistoryInfo historyInfo = list.get(access$1400);
                this.info = historyInfo;
                this.total = 0;
                if (((long) historyInfo.calibrated) >= j2) {
                    this.index = 0;
                } else {
                    long j = j2;
                    if (((long) (this.info.calibrated + this.info.length)) < j) {
                        this.index = 0;
                        int i = this.chunk + 1;
                        this.chunk = i;
                        try {
                            HistoryInfo historyInfo2 = list.get(i);
                            this.info = historyInfo2;
                            long j2 = j2;
                            if (((long) (historyInfo2.calibrated + this.info.length)) < j2) {
                                return Observable.empty();
                            }
                            this.index = ((int) j2) - this.info.calibrated;
                        } catch (Exception unused) {
                            return Observable.empty();
                        }
                    } else {
                        this.index = ((int) j) - this.info.calibrated;
                    }
                }
                this.total = this.info.length - this.index;
                int i2 = this.chunk;
                while (true) {
                    i2++;
                    if (i2 >= list.size()) {
                        return Single.create(new SingleOnSubscribe<ObservableMessage>() {
                            public void subscribe(SingleEmitter<ObservableMessage> singleEmitter) throws Exception {
                                ObservableMessage observableMessage;
                                C156267 r0 = C156267.this;
                                HistoryInfo unused = r0.info = (HistoryInfo) list.get(r0.chunk);
                                if (C156267.this.info.length - C156267.this.index > 40) {
                                    observableMessage = new ObservableMessage(CFamilialResolution.getHistory(C156267.this.info.startId + C156267.this.index, 40, bleStatue2.getLastSequence()));
                                } else {
                                    observableMessage = new ObservableMessage(CFamilialResolution.getHistory(C156267.this.info.startId + C156267.this.index, C156267.this.info.length - C156267.this.index, bleStatue2.getLastSequence()));
                                }
                                BleController.this.server.tryWrite(bleStatue2, observableMessage);
                                historyCParse.setTime(C156267.this.info.calibrated + C156267.this.index);
                                singleEmitter.onSuccess(observableMessage);
                            }
                        }).flatMapObservable(new Function<ObservableMessage, ObservableSource<byte[]>>() {
                            public ObservableSource<byte[]> apply(final ObservableMessage observableMessage) {
                                return observableMessage.getSingle().doOnDispose(new Action() {
                                    public void run() {
                                        observableMessage.reset().flatMap(new Function<byte[], ObservableSource<?>>() {
                                            public ObservableSource<?> apply(byte[] bArr) {
                                                historyCParse.setNow(bArr);
                                                return Observable.create(historyCParse).doOnComplete(new Action() {
                                                    public void run() {
                                                        if (historyCParse.isEnd()) {
                                                            BleController.this.server.onComplement(bleStatue2);
                                                        }
                                                    }
                                                });
                                            }
                                        }).ignoreElements().subscribe();
                                    }
                                });
                            }
                        }).flatMap(new Function<byte[], ObservableSource<History>>() {
                            public ObservableSource<History> apply(byte[] bArr) {
                                historyCParse.setNow(bArr);
                                return Observable.create(historyCParse).doOnComplete(new Action() {
                                    public void run() {
                                        if (historyCParse.isEnd()) {
                                            BleController.this.server.onComplement(bleStatue2);
                                        }
                                    }
                                });
                            }
                        }).repeatUntil(new BooleanSupplier() {
                            public boolean getAsBoolean() {
                                C156267 r0 = C156267.this;
                                HistoryInfo unused = r0.info = (HistoryInfo) list.get(r0.chunk);
                                C156267.access$1712(C156267.this, 40);
                                C156267.access$1812(C156267.this, 40);
                                if (C156267.this.info.length <= C156267.this.index) {
                                    C156267 r02 = C156267.this;
                                    C156267.access$1820(r02, r02.index - C156267.this.info.length);
                                    C156267.access$1608(C156267.this);
                                    if (C156267.this.chunk < list.size()) {
                                        int unused2 = C156267.this.index = 0;
                                    } else {
                                        RxBus.getDefault().post(new ProgressEvent((byte) 2, 100.0f));
                                        return true;
                                    }
                                }
                                RxBus.getDefault().post(new ProgressEvent((byte) 2, (((float) C156267.this.now) / ((float) C156267.this.total)) * 100.0f));
                                return false;
                            }
                        });
                    }
                    this.total += list.get(i2).length;
                }
            }
        });
    }

    public Single<Boolean> setChoosePort(BleStatue bleStatue, byte b) {
        SingleMessage singleMessage = new SingleMessage(EFamilialResolution.setChoosePort(b, bleStatue.getLastSequence()));
        this.server.tryWrite(bleStatue, singleMessage);
        return singleMessage.getSingle().map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                boolean checkResult = ProtocolResolution.checkResult(bArr);
                if (!checkResult) {
                    KLog.m65e("result false:" + ByteUtils.bytes2HexString(bArr));
                }
                return Boolean.valueOf(checkResult);
            }
        });
    }

    public Single<Boolean> setConfigSta(final BleStatue bleStatue, final String str, final String str2, final String str3) {
        return Single.create(new SingleOnSubscribe<NoneAckMessage>() {
            public void subscribe(SingleEmitter<NoneAckMessage> singleEmitter) throws Exception {
                KLog.m65e("1设置配网模式");
                NoneAckMessage noneAckMessage = new NoneAckMessage(EFamilialResolution.setDeviceMode(bleStatue.getLastCSequence()));
                BleController.this.server.tryWrite(bleStatue, noneAckMessage);
                singleEmitter.onSuccess(noneAckMessage);
            }
        }).subscribeOn(Schedulers.m983io()).flatMap(new Function<NoneAckMessage, SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(NoneAckMessage noneAckMessage) throws Exception {
                return noneAckMessage.getSingle();
            }
        }).flatMap(new Function<byte[], SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(byte[] bArr) throws Exception {
                BleController.this.sleep(20);
                NoneAckMessage noneAckMessage = new NoneAckMessage(EFamilialResolution.setStaWifiSSID(str, bleStatue.getLastCSequence()));
                BleController.this.server.tryWrite(bleStatue, noneAckMessage);
                return noneAckMessage.getSingle();
            }
        }).flatMap(new Function<byte[], SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(byte[] bArr) throws Exception {
                KLog.m65e("3.设置密码配网 接收据：");
                BleController.this.sleep(20);
                NoneAckMessage noneAckMessage = new NoneAckMessage(EFamilialResolution.setStaWifiPwd(str2, bleStatue.getLastCSequence()));
                BleController.this.server.tryWrite(bleStatue, noneAckMessage);
                return noneAckMessage.getSingle();
            }
        }).flatMap(new Function<byte[], SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(byte[] bArr) throws Exception {
                KLog.m65e("4.确认配网 接收据：");
                BleController.this.sleep(20);
                CustomMessage customMessage = new CustomMessage(EFamilialResolution.setStaWifiConfirm(bleStatue.getLastCSequence()));
                BleController.this.server.tryWrite(bleStatue, customMessage);
                return customMessage.getSingle();
            }
        }).flatMap(new Function<byte[], SingleSource<byte[]>>() {
            public SingleSource<byte[]> apply(byte[] bArr) throws Exception {
                BleController.this.sleep(20);
                CustomMessage customMessage = new CustomMessage(EFamilialResolution.setStaIpInfo(str3, bleStatue.getLastCSequence()));
                BleController.this.server.tryWrite(bleStatue, customMessage);
                return customMessage.getSingle();
            }
        }).map(new Function<byte[], Boolean>() {
            public Boolean apply(byte[] bArr) throws Exception {
                return true;
            }
        });
    }

    public void sleep(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    public int compare(BleDevice bleDevice, BleDevice bleDevice2) {
        return bleDevice2.getRssi() - bleDevice.getRssi();
    }
}
