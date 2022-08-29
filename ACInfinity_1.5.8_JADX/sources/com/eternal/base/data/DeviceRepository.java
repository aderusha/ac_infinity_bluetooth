package com.eternal.base.data;

import androidx.core.util.Consumer;
import com.alibaba.android.arouter.utils.TextUtils;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.eternal.base.LogService;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceIndex;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.DeviceMinModel;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.DeviceTarget;
import com.eternal.base.concat.PortCountAndLeafTmp;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.PortList;
import com.eternal.base.concat.PortSetting;
import com.eternal.base.data.ble.BleServer;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.data.ble.IBleControl;
import com.eternal.base.data.source.IDeviceSource;
import com.eternal.base.database.entity.Device;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.exception.BleException;
import com.eternal.base.exception.NoScanDevice;
import com.eternal.base.protocol.ProtocolResolution;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.bus.RxSubscriptions;
import com.eternal.framework.utils.KLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.BackpressureStrategy;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Flowable;
import p014io.reactivex.FlowableEmitter;
import p014io.reactivex.FlowableOnSubscribe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Observer;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.SingleSource;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class DeviceRepository extends BaseRepository implements BleServer.DisconnectListener {
    /* access modifiers changed from: private */
    public Consumer<Throwable> onConnectError;
    /* access modifiers changed from: private */
    public Consumer<Disposable> onConnectSubscribe;
    /* access modifiers changed from: private */
    public Consumer<DeviceIndex> onConnectSuccess;
    /* access modifiers changed from: private */
    public IDeviceSource source;

    DeviceRepository(IDeviceSource iDeviceSource, IBleControl iBleControl) {
        super(iBleControl);
        this.source = iDeviceSource;
    }

    public void onDisconnect(final BleDevice bleDevice) {
        LogService.getInstance().remove(bleDevice.getMac());
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                DeviceRepository.this.source.setTime(bleDevice.getMac(), System.currentTimeMillis());
                RxBus.getDefault().post(new BluetoothEvent((byte) 0, bleDevice.getMac()));
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public void updateSequences(final List<String> list) {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                for (int i = 0; i < list.size(); i++) {
                    DeviceRepository.this.source.setSequence((String) list.get(i), i);
                }
                completableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public void disableBle() {
        LogService.getInstance().clear();
        Single.create(new SingleOnSubscribe<List<BleDevice>>() {
            public void subscribe(SingleEmitter<List<BleDevice>> singleEmitter) throws Exception {
                singleEmitter.onSuccess(DeviceRepository.this.control.disableBle());
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.m983io()).map(new Function<List<BleDevice>, Boolean>() {
            public Boolean apply(List<BleDevice> list) throws Exception {
                RxBus rxBus = RxBus.getDefault();
                long currentTimeMillis = System.currentTimeMillis();
                for (BleDevice next : list) {
                    DeviceRepository.this.source.setTime(next.getMac(), currentTimeMillis);
                    rxBus.post(new BluetoothEvent((byte) 0, next.getMac()));
                }
                return true;
            }
        }).subscribe();
    }

    public Observable<List<BleDevice>> scanCycle() {
        return this.control.scanCycle(new BleScanRuleConfig.Builder().setAutoConnect(false).setContinuous(true).setScanTimeOut(0).build(), false);
    }

    public Disposable tryConnect(List<BleDevice> list, final Consumer<String> consumer, final Consumer<Throwable> consumer2, final Action action) {
        ArrayList arrayList = new ArrayList();
        for (String next : this.source.getAllMac()) {
            Iterator<BleDevice> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                BleDevice next2 = it.next();
                if (next2.getMac().equals(next)) {
                    arrayList.add(next2);
                    it.remove();
                    break;
                }
            }
        }
        return Observable.fromIterable(arrayList).flatMap(new Function<BleDevice, ObservableSource<BleStatue>>() {
            public ObservableSource<BleStatue> apply(BleDevice bleDevice) throws Exception {
                return DeviceRepository.this.connect(bleDevice).toObservable();
            }
        }).observeOn(Schedulers.m983io()).map(new Function<BleStatue, String>() {
            public String apply(BleStatue bleStatue) {
                String mac = bleStatue.getDevice().getMac();
                byte type = DeviceRepository.this.source.getType(mac);
                if (ProtocolTransformer.isDeviceC(type) || type == 6) {
                    DeviceRepository.this.control.sleep(800);
                }
                if (type == 1 || type == 2 || type == 6) {
                    DeviceRepository.this.refreshNotifications(bleStatue, (byte) 0);
                    LogService.getInstance().refreshNotificationMessage(mac, (byte) 0, true);
                } else if (type == 7 || type == 11 || type == 9 || type == 12) {
                    DeviceRepository.this.refreshENotifications(bleStatue);
                }
                return bleStatue.getDevice().getMac();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new p014io.reactivex.functions.Consumer<String>() {
            public void accept(String str) throws Exception {
                Consumer consumer = consumer;
                if (consumer != null) {
                    consumer.accept(str);
                }
            }
        }, new p014io.reactivex.functions.Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Consumer consumer = consumer2;
                if (consumer != null) {
                    consumer.accept(th);
                }
            }
        }, new Action() {
            public void run() throws Exception {
                Action action = action;
                if (action != null) {
                    action.run();
                }
            }
        });
    }

    public void setConnectConsumer(Consumer<Disposable> consumer, Consumer<DeviceIndex> consumer2, Consumer<Throwable> consumer3) {
        this.onConnectSubscribe = consumer;
        this.onConnectSuccess = consumer2;
        this.onConnectError = consumer3;
    }

    public void removeConnectConsumer() {
        if (this.onConnectSubscribe != null) {
            this.onConnectSubscribe = null;
        }
        if (this.onConnectSuccess != null) {
            this.onConnectSuccess = null;
        }
        if (this.onConnectError != null) {
            this.onConnectError = null;
        }
    }

    public CompositeDisposable scanConnectAndSave(final byte b, final boolean z) {
        final CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(Observable.create(new ObservableOnSubscribe<BleDevice>() {
            /* access modifiers changed from: private */
            public List<BleDevice> mBleDevices;
            Disposable scanDis;
            Disposable timeDis;

            public void subscribe(final ObservableEmitter<BleDevice> observableEmitter) throws Exception {
                Disposable subscribe = DeviceRepository.this.control.scanCycle(new BleScanRuleConfig.Builder().setAutoConnect(false).setContinuous(false).build(), true).subscribe(new p014io.reactivex.functions.Consumer<List<BleDevice>>() {
                    public void accept(List<BleDevice> list) throws Exception {
                        List unused = C134216.this.mBleDevices = list;
                    }
                }, new p014io.reactivex.functions.Consumer<Throwable>() {
                    public void accept(Throwable th) throws Exception {
                        if (DeviceRepository.this.onConnectError != null) {
                            DeviceRepository.this.onConnectError.accept(th);
                        }
                        if (C134216.this.timeDis != null) {
                            C134216.this.timeDis.dispose();
                        }
                        DeviceRepository.this.closeScan();
                    }
                });
                this.scanDis = subscribe;
                compositeDisposable.add(subscribe);
                int i = (ProtocolTransformer.isDeviceC(b) || b == 6) ? 20 : 8;
                KLog.m65e("开始计时 " + i);
                Observable.interval(0, 1, TimeUnit.SECONDS).take((long) i).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe(new Observer<Long>() {
                    public void onSubscribe(Disposable disposable) {
                        C134216.this.timeDis = disposable;
                        compositeDisposable.add(disposable);
                    }

                    public void onNext(Long l) {
                        if (C134216.this.mBleDevices != null && !C134216.this.mBleDevices.isEmpty()) {
                            BleDevice bleDevice = null;
                            Iterator it = C134216.this.mBleDevices.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    BleDevice bleDevice2 = (BleDevice) it.next();
                                    Device parseScanRecordData = ProtocolResolution.parseScanRecordData(bleDevice2);
                                    if (parseScanRecordData != null && b == parseScanRecordData.type) {
                                        bleDevice = bleDevice2;
                                        break;
                                    }
                                } else {
                                    break;
                                }
                            }
                            if (bleDevice != null) {
                                observableEmitter.onNext(bleDevice);
                                clear();
                            }
                        }
                    }

                    public void onError(Throwable th) {
                        clear();
                        if (DeviceRepository.this.onConnectError != null) {
                            DeviceRepository.this.onConnectError.accept(th);
                        }
                    }

                    private void clear() {
                        if (C134216.this.timeDis != null) {
                            C134216.this.timeDis.dispose();
                        }
                        if (C134216.this.scanDis != null) {
                            C134216.this.scanDis.dispose();
                            DeviceRepository.this.closeScan();
                        }
                    }

                    public void onComplete() {
                        onError(new NoScanDevice());
                    }
                });
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnSubscribe(new p014io.reactivex.functions.Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
                if (DeviceRepository.this.onConnectSubscribe != null) {
                    DeviceRepository.this.onConnectSubscribe.accept(disposable);
                }
            }
        }).flatMapSingle(new Function<BleDevice, SingleSource<BleStatue>>() {
            public SingleSource<BleStatue> apply(BleDevice bleDevice) throws Exception {
                return DeviceRepository.this.connect(bleDevice);
            }
        }).observeOn(Schedulers.m983io()).doOnError(new p014io.reactivex.functions.Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (DeviceRepository.this.onConnectError != null) {
                    DeviceRepository.this.onConnectError.accept(th);
                }
            }
        }).map(new Function<BleStatue, DeviceIndex>() {
            public DeviceIndex apply(BleStatue bleStatue) throws Exception {
                Device parseScanRecordData = ProtocolResolution.parseScanRecordData(bleStatue.getDevice());
                if (parseScanRecordData != null) {
                    parseScanRecordData.softwareVersion = bleStatue.softwareVersion;
                    parseScanRecordData.hardwareVersion = bleStatue.hardwareVersion;
                    parseScanRecordData.firmwareVersion = bleStatue.firmwareVersion;
                    DeviceIndex connect = DeviceRepository.this.source.connect(parseScanRecordData, z);
                    if (!z) {
                        return connect;
                    }
                    if (connect.index == -1) {
                        if (parseScanRecordData.type == 7 || parseScanRecordData.type == 11 || parseScanRecordData.type == 9 || parseScanRecordData.type == 12) {
                            DeviceRepository.this.initEPort(bleStatue, parseScanRecordData);
                        } else {
                            LogService.getInstance().initAndRefresh(parseScanRecordData.mac, (byte) 0);
                        }
                    }
                    if (parseScanRecordData.type == 1 || parseScanRecordData.type == 2 || parseScanRecordData.type == 6) {
                        if (parseScanRecordData.type == 6) {
                            DeviceRepository.this.control.sleep(800);
                        }
                        DeviceRepository.this.refreshNotifications(bleStatue, (byte) 0);
                        LogService.getInstance().refreshNotificationMessage(parseScanRecordData.mac, (byte) 0, true);
                    } else if (parseScanRecordData.type == 7 || parseScanRecordData.type == 11 || parseScanRecordData.type == 9 || parseScanRecordData.type == 12) {
                        DeviceRepository.this.refreshENotifications(bleStatue);
                    }
                    return connect;
                }
                throw new BleException("not parse device info");
            }
        }).subscribe(new p014io.reactivex.functions.Consumer<DeviceIndex>() {
            public void accept(DeviceIndex deviceIndex) throws Exception {
                if (DeviceRepository.this.onConnectSuccess != null) {
                    DeviceRepository.this.onConnectSuccess.accept(deviceIndex);
                }
            }
        }, new p014io.reactivex.functions.Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                if (DeviceRepository.this.onConnectError != null) {
                    DeviceRepository.this.onConnectError.accept(th);
                }
            }
        }));
        return compositeDisposable;
    }

    public Single<DeviceIndex> saveDevice(final String str) {
        return Single.create(new SingleOnSubscribe<DeviceIndex>() {
            public void subscribe(SingleEmitter<DeviceIndex> singleEmitter) throws Exception {
                BleStatue connect = DeviceRepository.this.control.getConnect(str);
                if (connect != null) {
                    Device parseScanRecordData = ProtocolResolution.parseScanRecordData(connect.getDevice());
                    if (parseScanRecordData != null) {
                        parseScanRecordData.softwareVersion = connect.softwareVersion;
                        parseScanRecordData.hardwareVersion = connect.hardwareVersion;
                        parseScanRecordData.firmwareVersion = connect.firmwareVersion;
                        DeviceIndex connect2 = DeviceRepository.this.source.connect(parseScanRecordData, true);
                        if (connect2.index == -1) {
                            if (parseScanRecordData.type == 7 || parseScanRecordData.type == 11 || parseScanRecordData.type == 9 || parseScanRecordData.type == 12) {
                                DeviceRepository.this.initEPort(connect, parseScanRecordData);
                            } else {
                                LogService.getInstance().initAndRefresh(parseScanRecordData.mac, (byte) 0);
                            }
                        }
                        if (parseScanRecordData.type == 1 || parseScanRecordData.type == 2 || parseScanRecordData.type == 6) {
                            if (parseScanRecordData.type == 6) {
                                DeviceRepository.this.control.sleep(800);
                            }
                            DeviceRepository.this.refreshNotifications(connect, (byte) 0);
                            LogService.getInstance().refreshNotificationMessage(parseScanRecordData.mac, (byte) 0, true);
                        } else if (parseScanRecordData.type == 7 || parseScanRecordData.type == 11 || parseScanRecordData.type == 9 || parseScanRecordData.type == 12) {
                            DeviceRepository.this.refreshENotifications(connect);
                        }
                        singleEmitter.onSuccess(connect2);
                        return;
                    }
                    throw new BleException("not parse device info");
                }
                throw new BleException("disconnect");
            }
        });
    }

    public void closeScan() {
        this.control.closeScan();
    }

    public boolean isScanning() {
        return this.control.isScanning();
    }

    /* access modifiers changed from: private */
    public Single<BleStatue> connect(BleDevice bleDevice) {
        return this.control.connect(bleDevice, this);
    }

    public Completable removeDevice(final String str) {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                BleStatue connect = DeviceRepository.this.control.getConnect(str);
                if (connect != null) {
                    DeviceRepository.this.control.disConnect(connect);
                }
                DeviceRepository.this.source.deleteDevice(str);
                completableEmitter.onComplete();
            }
        });
    }

    public Completable disconnectBle(final String str) {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                BleStatue connect = DeviceRepository.this.control.getConnect(str);
                if (connect != null) {
                    DeviceRepository.this.control.disConnect(connect);
                }
                completableEmitter.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public void initEPort(final BleStatue bleStatue, final Device device) {
        if (bleStatue.getVersion() < 3) {
            RxSubscriptions.add(getPortList(bleStatue).observeOn(Schedulers.m983io()).subscribe(new p014io.reactivex.functions.Consumer<PortList>() {
                public void accept(PortList portList) throws Exception {
                    if (portList != null && portList.portList != null) {
                        DeviceRepository.this.saveEPort(bleStatue, device, portList.portList.size());
                    }
                }
            }));
        } else {
            saveEPort(bleStatue, device, bleStatue.getPortCount());
        }
    }

    /* access modifiers changed from: private */
    public void saveEPort(BleStatue bleStatue, Device device, int i) {
        for (byte b = 1; b <= i; b = (byte) (b + 1)) {
            StringBuilder sb = new StringBuilder();
            sb.append((bleStatue.getType() == 9 || bleStatue.getType() == 12) ? "Outlet " : "Port ");
            sb.append(b);
            device.name = sb.toString();
            device.port = b;
            this.source.addDevice(device);
        }
        LogService.getInstance().initELog(device.mac, (byte) i);
    }

    public void addDevice(final Device device, final byte b) {
        RxSubscriptions.add(Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) {
                if (ProtocolTransformer.isDeviceMultiPort(device.type)) {
                    DeviceRepository.this.source.addDevice(device);
                    if (device.portList != null) {
                        for (byte b = 0; b < device.portList.size(); b = (byte) (b + 1)) {
                            Device device = device;
                            device.port = device.portList.get(b).f138id;
                            Device device2 = device;
                            device2.name = device2.portList.get(b).name;
                            Device device3 = device;
                            device3.isPlug = device3.portList.get(b).isPlug;
                            Device device4 = device;
                            device4.fan = device4.portList.get(b).fan;
                            Device device5 = device;
                            device5.fanState = device5.portList.get(b).fanState;
                            try {
                                DeviceRepository.this.source.addDevice(device);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else if (b > 0) {
                        for (byte b2 = 1; b2 <= b; b2 = (byte) (b2 + 1)) {
                            device.port = b2;
                            Device device6 = device;
                            Locale locale = Locale.ENGLISH;
                            Object[] objArr = new Object[2];
                            objArr[0] = ProtocolTransformer.isOutletDevice(device.type) ? "OUTLET" : "PORT";
                            objArr[1] = Byte.valueOf(b2);
                            device6.name = String.format(locale, "%s %d", objArr);
                            device.isPlug = false;
                            try {
                                DeviceRepository.this.source.addDevice(device);
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
                completableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m983io()).subscribe());
    }

    /* access modifiers changed from: private */
    public void refreshENotifications(final BleStatue bleStatue) {
        if (bleStatue.getVersion() < 3) {
            RxSubscriptions.add(getPortList(bleStatue).observeOn(Schedulers.m983io()).subscribe(new p014io.reactivex.functions.Consumer<PortList>() {
                public void accept(PortList portList) throws Exception {
                    if (portList != null && portList.portList != null) {
                        for (byte b = 0; b <= portList.portList.size(); b = (byte) (b + 1)) {
                            DeviceRepository.this.refreshNotifications(bleStatue, b);
                        }
                    }
                }
            }));
            return;
        }
        for (byte b = 0; b <= bleStatue.getPortCount(); b = (byte) (b + 1)) {
            refreshNotifications(bleStatue, b);
        }
    }

    /* access modifiers changed from: private */
    public void refreshNotifications(final BleStatue bleStatue, final byte b) {
        RxSubscriptions.add(this.control.getNotifications(bleStatue, b).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe(new p014io.reactivex.functions.Consumer<List<Notification>>() {
            public void accept(List<Notification> list) {
                if (bleStatue.getVersion() >= 5) {
                    DeviceRepository.this.source.initNotificationV4(bleStatue.getDevice().getMac(), list);
                } else {
                    DeviceRepository.this.source.initNotification(bleStatue.getDevice().getMac(), b, list);
                }
                DeviceRepository.this.saveModel(bleStatue, b);
            }
        }));
    }

    public Single<Boolean> saveNotifications(String str, byte b, byte b2, List<Notification> list, byte b3, byte b4) {
        final byte b5 = b2;
        final byte b6 = b4;
        final String str2 = str;
        final byte b7 = b;
        final List<Notification> list2 = list;
        final byte b8 = b3;
        return Single.create(new SingleOnSubscribe<Boolean>() {
            public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                if (ProtocolTransformer.isWorkWiFi(b5, b6)) {
                    DeviceRepository.this.source.initNetNotification(str2, b7, list2);
                } else if (b8 >= 5) {
                    DeviceRepository.this.source.initNotificationV4(str2, list2);
                } else {
                    DeviceRepository.this.source.initNotification(str2, b7, list2);
                }
                singleEmitter.onSuccess(true);
            }
        }).subscribeOn(Schedulers.m983io());
    }

    public Flowable<List<DeviceInfo>> getInfo() {
        return this.source.getInfo();
    }

    public Flowable<DeviceName> getDeviceName(String str, byte b) {
        return this.source.getDeviceName(str, b);
    }

    public Flowable<List<DeviceName>> getDeviceName(String str) {
        return this.source.getDeviceName(str);
    }

    public Single<DeviceName> getName(final String str, final byte b) {
        return Single.create(new SingleOnSubscribe<DeviceName>() {
            public void subscribe(SingleEmitter<DeviceName> singleEmitter) throws Exception {
                singleEmitter.onSuccess(DeviceRepository.this.source.getName(str, b));
            }
        });
    }

    public void setDeviceName(final String str, final byte b, final String str2) {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                DeviceRepository.this.source.setDeviceName(str, b, str2);
                completableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public void setConnectType(final String str, final String str2, final byte b) {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                DeviceRepository.this.source.setConnectType(str, str2, b);
                completableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public void setSoftwareVersion(final String str, final String str2) {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                DeviceRepository.this.source.setSoftwareVersion(str, str2);
                completableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public Single<String> getSoftwareVersion(final String str) {
        return Single.create(new SingleOnSubscribe<String>() {
            public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                String softwareVersion = DeviceRepository.this.source.getSoftwareVersion(str);
                if (TextUtils.isEmpty(softwareVersion)) {
                    softwareVersion = "";
                }
                singleEmitter.onSuccess(softwareVersion);
            }
        });
    }

    public Flowable<Long> getConnectTime(String str) {
        return this.source.getConnectTime(str);
    }

    public Flowable<ConnectInfo> getConnectInfo(String str) {
        return this.source.getConnectInfo(str);
    }

    public void setConnectTime(String str) {
        setConnectTime(str, System.currentTimeMillis());
    }

    public void setConnectTime(final String str, final long j) {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                DeviceRepository.this.source.setTime(str, j);
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    /* access modifiers changed from: private */
    public void saveModel(final BleStatue bleStatue, byte b) {
        RxSubscriptions.add(this.control.initMode(bleStatue, b).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe(new p014io.reactivex.functions.Consumer<DeviceModel>() {
            public void accept(DeviceModel deviceModel) {
                DeviceRepository.this.source.setModelData(bleStatue.getDevice().getMac(), deviceModel);
                KLog.m68i("init model success");
            }
        }));
    }

    public Flowable<DeviceModel> getCModel(BleStatue bleStatue) {
        return this.control.getBroadcast(bleStatue);
    }

    public Flowable<DeviceModel> getV3Model(String str) {
        BleStatue connect = this.control.getConnect(str);
        if (connect == null) {
            return Flowable.create(new FlowableOnSubscribe<DeviceModel>() {
                public void subscribe(FlowableEmitter<DeviceModel> flowableEmitter) throws Exception {
                    flowableEmitter.onError(new BleException("disconnect"));
                }
            }, BackpressureStrategy.LATEST);
        }
        return this.control.getBroadcast(connect);
    }

    public Single<DeviceMinModel> getModel(BleStatue bleStatue, byte b, byte b2) {
        return this.control.getModel(bleStatue, b, b2);
    }

    public Single<DeviceMinModel> getModel(BleStatue bleStatue, byte b) {
        return this.control.getModel(bleStatue, b);
    }

    public Single<PortList> getPortList(BleStatue bleStatue) {
        return this.control.getPortList(bleStatue);
    }

    public Single<DeviceModel> getModel(final String str, final byte b) {
        return Single.create(new SingleOnSubscribe<DeviceModel>() {
            public void subscribe(SingleEmitter<DeviceModel> singleEmitter) throws Exception {
                singleEmitter.onSuccess(DeviceRepository.this.source.getModel(str, b));
            }
        });
    }

    public int getPortType(String str, byte b) {
        DeviceSetting setting = this.source.getSetting(str, b);
        if (setting != null) {
            return setting.portType;
        }
        return 0;
    }

    public Single<Boolean> setModel(BleStatue bleStatue, byte b, DeviceModel deviceModel) {
        return this.control.setModel(bleStatue, b, deviceModel);
    }

    public Single<Boolean> setCModel(BleStatue bleStatue, DeviceModel deviceModel) {
        return this.control.setCModel(bleStatue, deviceModel);
    }

    public Single<Boolean> setModel(BleStatue bleStatue, byte b, byte b2) {
        return this.control.setModel(bleStatue, b, b2);
    }

    public Completable saveModel(final String str, final byte b, final DeviceModel deviceModel) {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) {
                DeviceRepository.this.source.setDeviceModel(str, b, deviceModel);
                completableEmitter.onComplete();
            }
        });
    }

    public void saveModelInfo(final String str, final DeviceInfo deviceInfo) {
        RxSubscriptions.add(Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) {
                DeviceRepository.this.source.setDeviceInfo(str, deviceInfo);
                if (ProtocolTransformer.isDeviceMultiPort(deviceInfo.type) && deviceInfo.portList != null) {
                    for (PortInfo portInfo : new ArrayList(deviceInfo.portList)) {
                        DeviceRepository.this.source.setPortInfo(str, portInfo);
                    }
                }
                completableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m983io()).subscribe());
    }

    public Completable syncTime() {
        return this.control.syncConnectedTime();
    }

    public Single<DeviceTFP> getTFP(String str, byte b) {
        return this.control.getTFP(str, b);
    }

    public Flowable<DeviceTFP> getTFP(String str) {
        BleStatue connect = this.control.getConnect(str);
        if (connect == null) {
            return Flowable.create(new FlowableOnSubscribe<DeviceTFP>() {
                public void subscribe(FlowableEmitter<DeviceTFP> flowableEmitter) throws Exception {
                    flowableEmitter.onError(new BleException("disconnect"));
                }
            }, BackpressureStrategy.LATEST);
        }
        return this.control.getBroadcast(connect).map(new Function<DeviceModel, DeviceTFP>() {
            public DeviceTFP apply(DeviceModel deviceModel) throws Exception {
                return deviceModel.toTFP();
            }
        });
    }

    public Single<DeviceSetting> getSetting(final String str, byte b, byte b2, boolean z) {
        Single<DeviceSetting> single;
        if (z) {
            return Single.create(new SingleOnSubscribe<DeviceSetting>() {
                public void subscribe(SingleEmitter<DeviceSetting> singleEmitter) throws Exception {
                    DeviceSetting setting = DeviceRepository.this.source.getSetting(str, (byte) 0);
                    setting.portList = DeviceRepository.this.source.getPortSetting(str);
                    singleEmitter.onSuccess(setting);
                }
            });
        }
        final BleStatue connect = this.control.getConnect(str);
        if (connect == null) {
            return Single.create(new SingleOnSubscribe<DeviceSetting>() {
                public void subscribe(SingleEmitter<DeviceSetting> singleEmitter) throws Exception {
                    singleEmitter.onError(new BleException("disconnect"));
                }
            });
        }
        if (b2 == 1 || b2 == 6) {
            single = this.control.getSetting(connect);
        } else if (b2 == 2) {
            single = this.control.getBSetting(connect);
        } else if (b2 == 7 || b2 == 11) {
            single = this.control.getESetting(connect, b);
        } else if (b2 == 9 || b2 == 12) {
            single = this.control.getFSetting(connect, b);
        } else {
            single = this.control.getCSetting(connect);
        }
        return single.subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).map(new Function<DeviceSetting, DeviceSetting>() {
            public DeviceSetting apply(DeviceSetting deviceSetting) throws Exception {
                List<PortInfo> portInfo;
                if (deviceSetting.portList == null || (portInfo = DeviceRepository.this.source.getPortInfo(str)) == null) {
                    return deviceSetting;
                }
                for (PortInfo next : deviceSetting.portList) {
                    Iterator<PortInfo> it = portInfo.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        PortInfo next2 = it.next();
                        if (next.f138id == next2.f138id) {
                            next.name = next2.name;
                            next.portType = next2.portType;
                            break;
                        }
                    }
                }
                return deviceSetting;
            }
        }).doOnSuccess(new p014io.reactivex.functions.Consumer<DeviceSetting>() {
            public void accept(DeviceSetting deviceSetting) throws Exception {
                deviceSetting.name = DeviceRepository.this.source.getSetting(str, (byte) 0).name;
                DeviceRepository.this.source.setSettings(str, (byte) 0, deviceSetting);
                if (deviceSetting.portList != null) {
                    for (PortSetting portSettings : deviceSetting.portList) {
                        DeviceRepository.this.source.setPortSettings(str, portSettings);
                    }
                }
                if (connect.getVersion() >= 3) {
                    DeviceRepository.this.source.setLeafTemperature(str, deviceSetting.leafTemperatureC, deviceSetting.leafTemperatureC);
                }
            }
        });
    }

    public Single<Boolean> getTempUnit(BleStatue bleStatue) {
        return this.control.getTempUnit(bleStatue);
    }

    public Single<Boolean> setSetting(String str, byte b, byte b2, DeviceSetting deviceSetting, boolean z, boolean z2) {
        Single<Boolean> single;
        if (b2 == 1 || b2 == 6) {
            single = this.control.setSetting(str, deviceSetting);
        } else if (b2 == 2) {
            single = this.control.setBSetting(str, deviceSetting);
        } else if (b2 == 9 || b2 == 12 || (b2 == 11 && z2)) {
            single = this.control.setFSetting(str, b, deviceSetting);
        } else if (b2 == 7 || b2 == 11) {
            single = this.control.setESetting(str, b, deviceSetting);
        } else {
            single = this.control.setCSetting(str, deviceSetting);
        }
        final boolean z3 = z;
        final String str2 = str;
        final byte b3 = b;
        final DeviceSetting deviceSetting2 = deviceSetting;
        return single.subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnSuccess(new p014io.reactivex.functions.Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                KLog.m65e("upDataSetting : " + bool);
                if (!bool.booleanValue()) {
                    return;
                }
                if (z3) {
                    DeviceRepository.this.source.setPortSettings(str2, b3 == 0 ? null : deviceSetting2.name, b3, deviceSetting2.transitionTemperature, deviceSetting2.transitionTemperatureC, deviceSetting2.transitionHumidity, deviceSetting2.transitionVpd, deviceSetting2.portType);
                    return;
                }
                DeviceRepository.this.source.setSettings(str2, b3, deviceSetting2);
                DeviceRepository.this.source.setLeafTemperature(str2, deviceSetting2.leafTemperatureC, deviceSetting2.leafTemperatureF);
            }
        });
    }

    public void updateSetting(String str, byte b, DeviceSetting deviceSetting, boolean z) {
        final boolean z2 = z;
        final DeviceSetting deviceSetting2 = deviceSetting;
        final String str2 = str;
        final byte b2 = b;
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                if (z2) {
                    KLog.m65e(String.format("设置 保存%s", new Object[]{deviceSetting2.toString()}));
                    DeviceRepository.this.source.setPortSettings(str2, b2 == 0 ? null : deviceSetting2.name, b2, deviceSetting2.transitionTemperature, deviceSetting2.transitionTemperatureC, deviceSetting2.transitionHumidity, deviceSetting2.transitionVpd, deviceSetting2.portType);
                    return;
                }
                DeviceRepository.this.source.setSettings(str2, b2, deviceSetting2);
                DeviceRepository.this.source.setLeafTemperature(str2, deviceSetting2.leafTemperatureC, deviceSetting2.leafTemperatureF);
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public void updateSetting(final String str, final DeviceSetting deviceSetting) {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                DeviceRepository.this.source.setSettings(str, (byte) 0, deviceSetting);
                DeviceRepository.this.source.setLeafTemperature(str, deviceSetting.leafTemperatureC, deviceSetting.leafTemperatureF);
                if (deviceSetting.portList != null) {
                    for (PortSetting next : deviceSetting.portList) {
                        KLog.m65e(String.format("设置 保存%s", new Object[]{deviceSetting.toString()}));
                        DeviceRepository.this.source.setPortSettings(str, next.name, next.f138id, next.transitionTemperature, next.transitionTemperatureC, next.transitionHumidity, next.transitionVpd, next.portType);
                    }
                }
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public void updateTarget(String str, int i, int i2, int i3, int i4) {
        final String str2 = str;
        final int i5 = i;
        final int i6 = i2;
        final int i7 = i3;
        final int i8 = i4;
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                DeviceRepository.this.source.setTarget(str2, i5, i6, i7, i8);
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public Single<DeviceTarget> getTarget(final String str) {
        return Single.create(new SingleOnSubscribe<DeviceTarget>() {
            public void subscribe(SingleEmitter<DeviceTarget> singleEmitter) throws Exception {
                singleEmitter.onSuccess(DeviceRepository.this.source.getTarget(str));
            }
        });
    }

    public Single<PortCountAndLeafTmp> getLeafTemperatureC(final String str) {
        return Single.create(new SingleOnSubscribe<PortCountAndLeafTmp>() {
            public void subscribe(SingleEmitter<PortCountAndLeafTmp> singleEmitter) throws Exception {
                singleEmitter.onSuccess(DeviceRepository.this.source.getLeafTemperatureC(str));
            }
        });
    }

    public Completable setControlTypeByHum(final String str, final byte b, final boolean z) {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) {
                DeviceRepository.this.source.setControlTypeByHum(str, b, z);
                completableEmitter.onComplete();
            }
        });
    }

    public Completable setControlTypeByHum(String str, boolean z) {
        return setControlTypeByHum(str, (byte) 0, z);
    }

    public Single<Boolean> resetDeviceToFactory(String str) {
        if (this.control.getConnect(str) == null) {
            return Single.create(new SingleOnSubscribe<Boolean>() {
                public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                    singleEmitter.onError(new BleException("disconnect"));
                }
            });
        }
        return this.control.resetDeviceToFactory(str);
    }

    public Single<Boolean> isDegree(final String str) {
        BleStatue connect = this.control.getConnect(str);
        if (connect != null) {
            return getModel(connect, (byte) 0).observeOn(Schedulers.m983io()).map(new Function<DeviceMinModel, Boolean>() {
                public Boolean apply(DeviceMinModel deviceMinModel) throws Exception {
                    return Boolean.valueOf(deviceMinModel.isDegree);
                }
            });
        }
        return Single.create(new SingleOnSubscribe<Boolean>() {
            public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                singleEmitter.onSuccess(Boolean.valueOf(DeviceRepository.this.source.isDegree(str)));
            }
        });
    }

    public Single<Boolean> setChoosePort(BleStatue bleStatue, byte b) {
        return this.control.setChoosePort(bleStatue, b).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnSuccess(new p014io.reactivex.functions.Consumer<Boolean>() {
            public void accept(Boolean bool) {
                bool.booleanValue();
            }
        });
    }

    public Single<Boolean> setConfigSta(BleStatue bleStatue, String str, String str2, String str3) {
        return this.control.setConfigSta(bleStatue, str, str2, str3).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnSuccess(new p014io.reactivex.functions.Consumer<Boolean>() {
            public void accept(Boolean bool) {
                bool.booleanValue();
            }
        });
    }
}
