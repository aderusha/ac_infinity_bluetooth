package com.eternal.main.model;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.text.TextUtils;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.clj.fastble.data.BleDevice;
import com.eternal.base.LogService;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.concat.NetMessageInfo;
import com.eternal.base.concat.NetPort;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.Version;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleServer;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.exception.NotCloseScanException;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.CommonObserver;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.RxHttpUtils;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.RxUtils;
import com.eternal.framework.utils.StringUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.main.C2323BR;
import com.eternal.main.C2343R;
import com.eternal.main.MainActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import p014io.reactivex.Completable;
import p014io.reactivex.Flowable;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class MainModel extends BaseViewModel {
    static final String CHOOSE_PORT = "choose port";
    static final String CHOOSE_PORT_NET = "choose port net";
    static final String TAG_FETCH_DEVICES = "tag fetch devices";
    /* access modifiers changed from: private */
    public Disposable autoConnect;
    private CompositeDisposable composite = new CompositeDisposable();
    /* access modifiers changed from: private */
    public ObservableEmitter<BluetoothEvent> configNetEmitter;
    /* access modifiers changed from: private */
    public String configNetMac;
    private Disposable configNetSubs;
    private Disposable connect;
    /* access modifiers changed from: private */
    public List<ItemModel> connected = new ArrayList();
    /* access modifiers changed from: private */
    public int currentState;
    public MutableLiveData<String> emailText = new MutableLiveData<>();
    public boolean hasPermission;
    public MutableLiveData<Boolean> isLogin = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public volatile boolean isReady;
    /* access modifiers changed from: private */
    public boolean isUserVisible;
    public ItemBinding<ItemModel> itemBinding = ItemBinding.m1008of(C2323BR.item, C2343R.layout.item_main);
    public ObservableList<ItemModel> items = new ObservableArrayList();
    /* access modifiers changed from: private */
    public String newDeviceMac;
    public BindingCommand<Void> onAbout = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            MainModel.this.openDrawer.setValue(true);
            MainModel.this.fetchShareDevices();
        }
    });
    public BindingCommand<Void> onAccount = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_ACCOUNT).withBoolean(ActivityEvent.SHOW_SHARE_DOT, MainModel.this.showShareDot.getValue() == Boolean.TRUE).withTransition(C2343R.anim.right_in, C2343R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onAdd = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            boolean unused = MainModel.this.pageAddVisible = true;
            MainModel.this.stopScan();
            ARouter.getInstance().build(RouterActivityPath.Device.PAGE_CHOOSE).withTransition(C2343R.anim.right_in, C2343R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onCreateAccount = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            MainModel.this.pushToPage(RouterActivityPath.Account.PAGE_CREATE);
        }
    });
    public BindingCommand<Void> onLegal = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            MainModel.this.pushToPage(RouterActivityPath.Account.PAGE_LEGAL_INFORMATION);
        }
    });
    public BindingCommand<Void> onLogin = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (MainModel.this.isLogin.getValue() == Boolean.TRUE) {
                Messenger.getDefault().send(MainModel.this, MainActivity.SHOW_LOGOUT_DIALOG);
            } else {
                MainModel.this.pushToLogin(false, 1, false);
            }
        }
    });
    public BindingCommand<Void> onPromptLogin = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            MainModel.this.showPromptLogin.setValue(false);
        }
    });
    public BindingCommand<Void> onSupport = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            MainModel.this.pushToPage(RouterActivityPath.Account.PAGE_SUPPORT);
        }
    });
    public BindingCommand<Void> onUpdate = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            MainModel.this.pushToVersionPage();
        }
    });
    public MutableLiveData<Boolean> openDrawer = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public boolean pageAddVisible;
    /* access modifiers changed from: private */
    public Disposable refresh;
    private Disposable refreshC;
    /* access modifiers changed from: private */
    public Disposable refreshNet;
    /* access modifiers changed from: private */
    public DeviceRepository repository;
    /* access modifiers changed from: private */
    public Disposable scan;
    List<BleDevice> scanBleDevices = new ArrayList();
    private Disposable scanSubs;
    /* access modifiers changed from: private */
    public DeviceInfo selectedDevice;
    public MutableLiveData<Boolean> showPromptLogin = new MutableLiveData<>();
    public MutableLiveData<Boolean> showShareDot = new MutableLiveData<>();
    public MutableLiveData<Boolean> showTip = new MutableLiveData<>();
    private Disposable timeSubs;
    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<Boolean> updateAvailable = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String updateVersion;

    private void initPermission() {
    }

    public MainModel(Application application) {
        super(application);
    }

    public void init(DeviceRepository deviceRepository) {
        this.repository = deviceRepository;
        this.title.setValue("DEVICES");
        initLoginState();
        initPermission();
        initDeviceAndConnect();
        registerEventBus();
        initMessenger();
        connectDevices();
        queryVersion();
        startHeartbeat();
        initConfigNetObservable();
    }

    private void initConfigNetObservable() {
        addSubscribe(Observable.create(new ObservableOnSubscribe<BluetoothEvent>() {
            public void subscribe(ObservableEmitter<BluetoothEvent> observableEmitter) throws Exception {
                ObservableEmitter unused = MainModel.this.configNetEmitter = observableEmitter;
            }
        }).debounce(15, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BluetoothEvent>() {
            public void accept(BluetoothEvent bluetoothEvent) {
                if (bluetoothEvent.f135i1 == 1) {
                    String unused = MainModel.this.configNetMac = bluetoothEvent.mac;
                } else {
                    String unused2 = MainModel.this.configNetMac = null;
                }
            }
        }));
    }

    public boolean isReady() {
        return this.isReady;
    }

    /* access modifiers changed from: private */
    public void fetchShareDevices() {
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            ApiHelper.getDeviceApi().shareWithYouDevList(token).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<List<NetDevice>>() {
                /* access modifiers changed from: protected */
                public String setTag() {
                    Class<MainModel> cls = MainModel.class;
                    return "MainModel";
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    KLog.m65e(str);
                }

                /* access modifiers changed from: protected */
                public void onSuccess(List<NetDevice> list) {
                    boolean z;
                    if (list != null) {
                        Iterator<NetDevice> it = list.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (it.next().isShare == 0) {
                                    z = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                    z = false;
                    MainModel.this.showShareDot.setValue(Boolean.valueOf(z));
                }
            });
        }
    }

    private void queryVersion() {
        ApiHelper.getAccountApi().getVersion(0, AppUtils.getAppVersionName()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<Version>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                KLog.m65e(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Version version) {
                Version version2;
                if (version != null) {
                    if (!TextUtils.isEmpty(version.version) || !TextUtils.isEmpty(version.update)) {
                        String appVersionName = AppUtils.getAppVersionName();
                        boolean z = !TextUtils.isEmpty(version.update) && StringUtils.compareVersion(version.update, appVersionName) == 1;
                        boolean z2 = !TextUtils.isEmpty(version.version) && StringUtils.compareVersion(version.version, appVersionName) == 1;
                        if (z || z2) {
                            if (z && z2) {
                                version2 = new Version(StringUtils.compareVersion(version.update, version.version) == 1 ? version.update : version.version, true);
                            } else if (z) {
                                version2 = new Version(version.update, true);
                            } else {
                                version2 = new Version(version.version, false);
                            }
                            MainModel.this.updateAvailable.setValue(true);
                            String unused = MainModel.this.updateVersion = version2.showVersion;
                            Messenger.getDefault().send(version2, "show update dialog");
                        }
                    }
                }
            }
        });
    }

    public void startHeartbeat() {
        addSubscribe(Single.create(new SingleOnSubscribe<String>() {
            public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                singleEmitter.onSuccess(UserCache.getInstance().getToken());
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                if (!TextUtils.isEmpty(str)) {
                    MainModel.this.fetchDevices(str, 2000);
                } else {
                    MainModel.this.updateNetDevicesItem((List<NetDevice>) null);
                }
            }
        }));
    }

    /* access modifiers changed from: private */
    public void fetchDevices(String str, final int i) {
        Disposable disposable = this.refreshNet;
        if (disposable == null || disposable.isDisposed()) {
            ApiHelper.getDeviceApi().devInfoListAll(str).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                public ObservableSource<?> apply(Observable<Throwable> observable) throws Exception {
                    return observable.delay((long) i, TimeUnit.MILLISECONDS);
                }
            }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                public ObservableSource<?> apply(Observable<Object> observable) throws Exception {
                    return observable.delay((long) i, TimeUnit.MILLISECONDS);
                }
            }).subscribe(new CommonObserver<BaseData<List<NetDevice>>>() {
                /* access modifiers changed from: protected */
                public String setTag() {
                    return MainModel.TAG_FETCH_DEVICES;
                }

                public void doOnSubscribe(Disposable disposable) {
                    Disposable unused = MainModel.this.refreshNet = disposable;
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    KLog.m65e(str);
                }

                /* access modifiers changed from: protected */
                public void onSuccess(BaseData<List<NetDevice>> baseData) {
                    if (baseData.getCode() == 200) {
                        MainModel.this.updateNetDevicesItem(baseData.getData());
                    } else if (baseData.getCode() == 403) {
                        MainModel.this.updateNetDevicesItem((List<NetDevice>) null);
                        MainModel.this.logout(false, true);
                    } else {
                        KLog.m65e(baseData.getMsg());
                    }
                }
            });
        }
    }

    public void setNewDeviceMac(String str) {
        this.newDeviceMac = str;
    }

    /* access modifiers changed from: private */
    public void updateNetDevicesItem(List<NetDevice> list) {
        boolean z;
        if (list == null) {
            list = new ArrayList<>();
        }
        for (ItemModel itemModel : this.items) {
            if (itemModel.getType() == 8 || itemModel.getType() == 11) {
                Iterator<NetDevice> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    }
                    NetDevice next = it.next();
                    if (itemModel.getMac().equalsIgnoreCase(StringUtils.appendSymbol(next.devMacAddr, ":"))) {
                        it.remove();
                        DeviceTFP tfp = next.toTFP();
                        if (!itemModel.getMac().equalsIgnoreCase(this.configNetMac)) {
                            if (itemModel.connectType != tfp.connectType) {
                                this.repository.setConnectType(itemModel.getMac(), tfp.devId, tfp.connectType);
                            }
                            itemModel.updateConnectType(tfp.connectType, tfp.devId);
                        }
                        if (tfp.isOnline) {
                            this.repository.connectNet(itemModel.getMac(), next.toDevice());
                            if (!itemModel.isConnet.get()) {
                                this.repository.setConnectTime(itemModel.getMac(), tfp.connectTime);
                                if (!TextUtils.isEmpty(next.firmwareVersion) && !next.firmwareVersion.equalsIgnoreCase(itemModel.getDeviceInfo().softwareVersion)) {
                                    RepositoryInjection.providerDeviceRepository().setSoftwareVersion(itemModel.getMac(), next.firmwareVersion);
                                }
                            }
                        } else {
                            this.repository.disconnectNet(itemModel.getMac());
                            if (itemModel.connectTime != tfp.connectTime) {
                                this.repository.setConnectTime(itemModel.getMac(), tfp.connectTime);
                            }
                        }
                        updateItem(tfp, itemModel);
                        itemModel.updateVersion(next.devVersion, "", next.hardwareVersion, next.firmwareVersion);
                        if (next.deviceInfo != null) {
                            showNotify(itemModel, next.deviceInfo.portInfo, next.deviceInfo.choosePort, itemModel.getMac(), next.deviceInfo.messageInfo, next.deviceInfo.unit == 1);
                            DeviceInfo deviceInfo = this.selectedDevice;
                            if (deviceInfo != null && deviceInfo.mac.equalsIgnoreCase(itemModel.getMac())) {
                                RxBus.getDefault().post(new BluetoothEvent((byte) 5, itemModel.getMac(), (int) next.deviceInfo.unit));
                                RxBus.getDefault().post(new BluetoothEvent((byte) 7, itemModel.getMac(), (Object) tfp));
                                RxBus.getDefault().post(new BluetoothEvent((byte) 8, itemModel.getMac(), (Object) next.devTimeZone));
                            }
                        }
                        z = true;
                    }
                }
                if (!z) {
                    this.repository.disconnectNet(itemModel.getMac());
                    if (itemModel.getMac().equalsIgnoreCase(this.configNetMac)) {
                        itemModel.disconnect();
                        this.configNetMac = null;
                    } else if (itemModel.connectType == 1) {
                        remove(itemModel, false);
                        DeviceInfo deviceInfo2 = this.selectedDevice;
                        if (deviceInfo2 != null && deviceInfo2.mac.equalsIgnoreCase(itemModel.getMac())) {
                            pushToMain();
                        }
                    }
                }
            }
        }
        for (NetDevice next2 : list) {
            this.repository.addDevice(next2.toDevice(), next2.portCount);
        }
    }

    private void showNotify(ItemModel itemModel, List<NetPort> list, byte b, String str, NetMessageInfo netMessageInfo, boolean z) {
        ItemModel itemModel2 = itemModel;
        NetMessageInfo netMessageInfo2 = netMessageInfo;
        if (RepositoryInjection.providerLogRepository().showNotify(list, itemModel.getTriggerNotifyTime(), itemModel.getLastNotifyType(), b, str, itemModel.getDevId(), netMessageInfo, z, itemModel.getType(), itemModel.getDeviceInfo().version)) {
            itemModel.setTriggerNotifyTime(netMessageInfo2.triggerTimeSecond);
            itemModel.setLastNotifyType(netMessageInfo2.type);
        }
    }

    private void initLoginState() {
        addSubscribe(Single.create(new SingleOnSubscribe<String[]>() {
            public void subscribe(SingleEmitter<String[]> singleEmitter) throws Exception {
                String token = UserCache.getInstance().getToken();
                String email = UserCache.getInstance().getEmail();
                singleEmitter.onSuccess(new String[]{token, email, UserCache.getInstance().getBindingD() + ""});
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.m983io()).subscribe(new Consumer<String[]>() {
            public void accept(String[] strArr) throws Exception {
                MainModel.this.isLogin.setValue(Boolean.valueOf(!TextUtils.isEmpty(strArr[0])));
                MainModel.this.emailText.setValue(strArr[1]);
                if (MainModel.this.isLogin.getValue() != Boolean.FALSE || !strArr[2].equals("2")) {
                    MainModel.this.showPromptLogin.setValue(false);
                } else {
                    MainModel.this.showPromptLogin.setValue(true);
                }
            }
        }));
    }

    /* access modifiers changed from: private */
    public void connectDevices() {
        Disposable disposable = this.connect;
        if (disposable == null || disposable.isDisposed()) {
            this.connect = Observable.create(new ObservableOnSubscribe<List<BleDevice>>() {
                public void subscribe(final ObservableEmitter<List<BleDevice>> observableEmitter) throws Exception {
                    BleDevice bleDevice;
                    if (MainModel.this.pageAddVisible || MainModel.this.scanBleDevices == null || MainModel.this.scanBleDevices.size() == 0) {
                        observableEmitter.onComplete();
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    for (BleDevice next : MainModel.this.scanBleDevices) {
                        if (!MainModel.this.repository.isConnect(next.getMac())) {
                            arrayList.add(next);
                        }
                    }
                    MainModel.this.scanBleDevices.clear();
                    if (arrayList.size() == 0) {
                        observableEmitter.onComplete();
                        return;
                    }
                    if (MainModel.this.selectedDevice != null) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            bleDevice = null;
                            if (!it.hasNext()) {
                                break;
                            }
                            bleDevice = (BleDevice) it.next();
                            if (bleDevice.getMac().equals(MainModel.this.selectedDevice.mac)) {
                                it.remove();
                                break;
                            }
                        }
                        if (bleDevice != null) {
                            arrayList.add(0, bleDevice);
                        }
                    }
                    MainModel mainModel = MainModel.this;
                    Disposable unused = mainModel.autoConnect = mainModel.repository.tryConnect(arrayList, new androidx.core.util.Consumer<String>() {
                        public void accept(String str) {
                            if (MainModel.this.updateConnectState(str)) {
                                MainModel.this.stop();
                                MainModel.this.syncTimeAndStart();
                                RxBus.getDefault().post(new BluetoothEvent((byte) 2, str));
                            }
                        }
                    }, new androidx.core.util.Consumer<Throwable>() {
                        public void accept(Throwable th) {
                            th.printStackTrace();
                            observableEmitter.onComplete();
                        }
                    }, new Action() {
                        public void run() throws Exception {
                            observableEmitter.onComplete();
                        }
                    });
                }
            }).subscribeOn(Schedulers.m983io()).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                public ObservableSource<?> apply(Observable<Object> observable) throws Exception {
                    return observable.delay(500, TimeUnit.MILLISECONDS);
                }
            }).subscribe();
        }
    }

    /* access modifiers changed from: private */
    public boolean updateConnectState(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (ItemModel itemModel : this.items) {
            if (itemModel.getMac().equals(str)) {
                BleStatue connect2 = this.repository.getConnect(str);
                if (connect2 != null) {
                    itemModel.updateVersion(connect2.getVersion(), connect2.firmwareVersion, connect2.hardwareVersion, connect2.softwareVersion);
                    RepositoryInjection.providerDeviceRepository().setSoftwareVersion(itemModel.getMac(), connect2.softwareVersion);
                }
                itemModel.connect(System.currentTimeMillis());
                this.connected.add(itemModel);
                this.repository.setConnectTime(str);
                return true;
            }
        }
        return false;
    }

    private void initDeviceAndConnect() {
        addSubscribe(this.repository.getInfo().subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<DeviceInfo>>() {
            public void accept(List<DeviceInfo> list) {
                boolean unused = MainModel.this.isReady = true;
                if (list.isEmpty()) {
                    MainModel.this.showTip.setValue(true);
                    return;
                }
                MainModel.this.showTip.setValue(false);
                ArrayList<DeviceInfo> arrayList = new ArrayList<>();
                for (DeviceInfo next : list) {
                    if (next.port == 0) {
                        arrayList.add(next);
                    }
                }
                for (DeviceInfo deviceInfo : arrayList) {
                    if (deviceInfo.type == 7 || deviceInfo.type == 8 || deviceInfo.type == 11 || deviceInfo.type == 9 || deviceInfo.type == 12) {
                        ArrayList arrayList2 = new ArrayList();
                        for (DeviceInfo next2 : list) {
                            if (next2.port != 0 && next2.mac.equals(deviceInfo.mac)) {
                                PortInfo portInfo = new PortInfo();
                                portInfo.f138id = next2.port;
                                portInfo.name = next2.name;
                                portInfo.fan = next2.fan;
                                portInfo.fanType = next2.fanType;
                                portInfo.portType = next2.portType;
                                portInfo.fanState = next2.fanState;
                                portInfo.isPlug = next2.isPlug;
                                arrayList2.add(portInfo);
                            }
                        }
                        deviceInfo.portList = arrayList2;
                    }
                    if (!ProtocolTransformer.isWorkWiFi(deviceInfo.type, deviceInfo.connectType)) {
                        Messenger.getDefault().sendNoMsg(MainActivity.REQUEST_PERMISSION);
                    }
                }
                if (arrayList.size() == MainModel.this.items.size()) {
                    for (ItemModel itemModel : MainModel.this.items) {
                        Iterator it = arrayList.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            DeviceInfo deviceInfo2 = (DeviceInfo) it.next();
                            if (itemModel.getMac().equalsIgnoreCase(deviceInfo2.mac)) {
                                itemModel.updateLeafTemperatureC(deviceInfo2.leafTemperatureC);
                                itemModel.updateConnectType(deviceInfo2.connectType, deviceInfo2.deviceId);
                                if (!ProtocolTransformer.isWorkWiFi(itemModel.getType(), itemModel.connectType)) {
                                    itemModel.update(deviceInfo2.portList);
                                    itemModel.update(deviceInfo2.name);
                                    itemModel.updateSoftwareVersion(deviceInfo2.softwareVersion);
                                }
                            }
                        }
                    }
                } else if (MainModel.this.items.size() < arrayList.size()) {
                    boolean isEmpty = MainModel.this.items.isEmpty();
                    MainModel.this.items.clear();
                    KLog.m65e(String.format(Locale.ENGLISH, "设备列表 clear devices size ： %d items size: %d", new Object[]{Integer.valueOf(arrayList.size()), Integer.valueOf(MainModel.this.items.size())}));
                    MainModel.this.connected.clear();
                    for (DeviceInfo deviceInfo3 : arrayList) {
                        ItemModel itemModel2 = new ItemModel(deviceInfo3);
                        if (MainModel.this.repository.isConnect(deviceInfo3.mac) && itemModel2.connectType == 0) {
                            MainModel.this.connected.add(itemModel2);
                            itemModel2.connect(System.currentTimeMillis());
                        }
                        if (!TextUtils.isEmpty(MainModel.this.newDeviceMac) && MainModel.this.newDeviceMac.equalsIgnoreCase(deviceInfo3.mac)) {
                            itemModel2.setShowLoading();
                            String unused2 = MainModel.this.newDeviceMac = null;
                        }
                        KLog.m65e(String.format(Locale.ENGLISH, "设备列表 add devices size ： %d items size: %d", new Object[]{Integer.valueOf(arrayList.size()), Integer.valueOf(MainModel.this.items.size())}));
                        MainModel.this.items.add(itemModel2);
                    }
                    if (MainModel.this.refresh != null && !MainModel.this.connected.isEmpty()) {
                        MainModel.this.stop();
                        MainModel.this.syncTimeAndStart();
                    }
                    if (MainModel.this.scan == null && isEmpty) {
                        MainModel.this.scanDevice();
                    }
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) {
                th.printStackTrace();
            }
        }));
    }

    public void scanDevice() {
        BluetoothAdapter defaultAdapter;
        if (this.currentState == 11 || this.items.isEmpty() || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null || defaultAdapter.getState() != 12) {
            return;
        }
        if (!this.hasPermission) {
            KLog.m65e("扫描失败，没有开启位置权限");
        } else if (AppUtils.isNeedTurnOnLocationService(Utils.getContext())) {
            KLog.m65e("扫描失败，没有开启定位服务");
        } else if (!this.repository.isScanning()) {
            Disposable disposable = this.scan;
            if (disposable != null && !disposable.isDisposed()) {
                this.scan.dispose();
            }
            this.scan = this.repository.scanCycle().subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe(new Consumer<List<BleDevice>>() {
                public void accept(List<BleDevice> list) {
                    for (BleDevice next : list) {
                        for (ItemModel itemModel : MainModel.this.items) {
                            if (next.getMac().equals(itemModel.getMac())) {
                                MainModel mainModel = MainModel.this;
                                if (!mainModel.isContainer(mainModel.scanBleDevices, next.getMac()) && itemModel.connectType == 0) {
                                    MainModel.this.scanBleDevices.add(next);
                                }
                            }
                        }
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                    KLog.m65e(th);
                    if (th instanceof NotCloseScanException) {
                        BleServer.getInstance().disable();
                        do {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } while (BleServer.getInstance().isEnable());
                        BleServer.getInstance().enable();
                        MainModel.this.stopScan();
                        MainModel.this.connectDevices();
                        MainModel.this.scanDevice();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean isContainer(List<BleDevice> list, String str) {
        for (BleDevice mac : list) {
            if (mac.getMac().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void stopScan() {
        Disposable disposable = this.connect;
        if (disposable != null && !disposable.isDisposed()) {
            this.connect.dispose();
            this.connect = null;
        }
        this.repository.closeScan();
        Disposable disposable2 = this.scan;
        if (disposable2 != null && !disposable2.isDisposed()) {
            this.scan.dispose();
            this.scan = null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void registerNotify() {
        this.pageAddVisible = false;
        this.isUserVisible = true;
        connectDevices();
        scanDevice();
        this.timeSubs = RxBus.getDefault().toObservable(Long.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            public void accept(Long l) {
                for (ItemModel itemModel : MainModel.this.connected) {
                    itemModel.time.set(ProtocolTransformer.getTime(l.longValue()));
                }
            }
        });
        for (ItemModel itemModel : this.connected) {
            itemModel.time.set(ProtocolTransformer.getTime(System.currentTimeMillis()));
        }
        stop();
        syncTimeAndStart();
        if (this.selectedDevice != null) {
            this.selectedDevice = null;
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void unregisterNotify() {
        this.isUserVisible = false;
        this.showPromptLogin.setValue(Boolean.FALSE);
        Disposable disposable = this.timeSubs;
        if (disposable != null) {
            disposable.dispose();
            this.timeSubs = null;
        }
        stop();
        syncTimeAndStart();
    }

    public void registerEventBus() {
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) {
                if (activityEvent.what == 11) {
                    int unused = MainModel.this.currentState = 11;
                } else if (activityEvent.what == 10) {
                    int unused2 = MainModel.this.currentState = 10;
                    if (!MainModel.this.pageAddVisible) {
                        MainModel.this.scanDevice();
                    }
                    if (!(AppManager.getAppManager().currentActivity() instanceof MainActivity) && MainModel.this.refresh != null && !MainModel.this.connected.isEmpty()) {
                        MainModel.this.stop();
                        MainModel.this.syncTimeAndStart();
                    }
                    MainModel.this.syncTimeForNet();
                } else if (activityEvent.what == 20) {
                    MainModel.this.isLogin.setValue(true);
                    MainModel.this.emailText.setValue(UserCache.getInstance().getEmail());
                    MainModel.this.showPromptLogin.setValue(false);
                    MainModel.this.startHeartbeat();
                } else if (activityEvent.what == 21) {
                    MainModel.this.logout(((Boolean) activityEvent.obj).booleanValue(), true);
                } else if (activityEvent.what == 40) {
                    MainModel.this.showShareDot.setValue((Boolean) activityEvent.obj);
                }
            }
        }));
        addSubscribe(RxBus.getDefault().toObservable(BluetoothEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BluetoothEvent>() {
            public void accept(BluetoothEvent bluetoothEvent) throws Exception {
                if (bluetoothEvent.what == 0) {
                    Iterator it = MainModel.this.connected.iterator();
                    while (it.hasNext()) {
                        ItemModel itemModel = (ItemModel) it.next();
                        if (itemModel.getMac().equals(bluetoothEvent.mac)) {
                            itemModel.disconnect();
                            it.remove();
                            return;
                        }
                    }
                } else if (bluetoothEvent.what == 12) {
                    boolean unused = MainModel.this.updateConnectState(bluetoothEvent.mac);
                } else if (bluetoothEvent.what == 3) {
                    MainModel.this.stopScan();
                } else if (bluetoothEvent.what == 4 || bluetoothEvent.what == 9) {
                    if (!MainModel.this.pageAddVisible) {
                        MainModel.this.scanDevice();
                        MainModel.this.connectDevices();
                    }
                } else if (bluetoothEvent.what == 11) {
                    if (bluetoothEvent.f135i1 == 1) {
                        String unused2 = MainModel.this.configNetMac = bluetoothEvent.mac;
                    }
                    MainModel.this.configNetEmitter.onNext(bluetoothEvent);
                }
            }
        }));
    }

    public void requestConnect(int i) {
        if (i != -1 && this.items.size() > i) {
            ItemModel itemModel = (ItemModel) this.items.get(i);
            this.connected.add(itemModel);
            itemModel.connect(System.currentTimeMillis());
        }
    }

    /* access modifiers changed from: private */
    public void syncTimeForNet() {
        addSubscribe(Single.create(new SingleOnSubscribe<String>() {
            public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                String token = UserCache.getInstance().getToken();
                if (TextUtils.isEmpty(token)) {
                    singleEmitter.onError(new Throwable());
                } else {
                    singleEmitter.onSuccess(token);
                }
            }
        }).subscribeOn(Schedulers.m983io()).flatMapObservable(new Function<String, ObservableSource<BaseData<Void>>>() {
            public ObservableSource<BaseData<Void>> apply(String str) throws Exception {
                return ApiHelper.getDeviceApi().syncTime(str).subscribeOn(Schedulers.m983io());
            }
        }).subscribe(new Consumer<BaseData<Void>>() {
            public void accept(BaseData<Void> baseData) throws Exception {
                if (baseData.getCode() == 200) {
                    KLog.m65e("控制页 同步时间成功");
                } else {
                    KLog.m65e("控制页 同步时间失败");
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                KLog.m65e("控制页 同步时间失败");
                KLog.m65e(th.getMessage());
            }
        }));
    }

    private void initMessenger() {
        Messenger.getDefault().register((Object) this, (Object) CHOOSE_PORT, ItemModel.class, new BindingConsumer<ItemModel>() {
            public void call(ItemModel itemModel) {
                if (ProtocolTransformer.isWorkWiFi(itemModel.getType(), itemModel.connectType)) {
                    MainModel.this.switchMaster(itemModel.getDevId());
                } else {
                    MainModel.this.choosePort(itemModel);
                }
            }
        });
        Messenger.getDefault().register((Object) this, (Object) CHOOSE_PORT_NET, ItemModel.class, new BindingConsumer<ItemModel>() {
            public void call(ItemModel itemModel) {
                if (ProtocolTransformer.isWorkWiFi(itemModel.getType(), itemModel.connectType)) {
                    MainModel.this.choosePort4Net(itemModel.getDevId(), itemModel.switchToPort);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void choosePort4Net(String str, byte b) {
        ApiHelper.getDeviceApi().updateMsterPort(str, b).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                MainModel.this.showFailDialog(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void switchMaster(String str) {
        ApiHelper.getDeviceApi().updateMster(str).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                MainModel.this.showFailDialog(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void choosePort(ItemModel itemModel) {
        BleStatue connect2 = this.repository.getConnect(itemModel.getMac());
        if (connect2 != null) {
            addSubscribe(this.repository.setChoosePort(connect2, itemModel.switchToPort).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) {
                }
            }).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    if (!bool.booleanValue()) {
                        KLog.m65e("choose port error");
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    th.printStackTrace();
                    KLog.m65e(th);
                }
            }));
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        Disposable disposable = this.autoConnect;
        if (disposable != null && !disposable.isDisposed()) {
            this.autoConnect.dispose();
        }
        super.onCleared();
        Messenger.getDefault().unregister(this);
    }

    public void remove(final ItemModel itemModel, boolean z) {
        BleStatue connect2 = this.repository.getConnect(itemModel.getMac());
        if (connect2 == null || !z) {
            removeItemBle(itemModel);
            return;
        }
        int i = 0;
        if (ProtocolTransformer.isDeviceC(itemModel.getType())) {
            DeviceModel cModel = itemModel.getDeviceInfo().toCModel();
            cModel.autoHighTmpSwitch = false;
            cModel.autoLowTmpSwitch = false;
            cModel.autoHighHumSwitch = false;
            cModel.autoLowHumSwitch = false;
            RepositoryInjection.providerDeviceRepository().setCModel(connect2, cModel).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) throws Exception {
                    MainModel.this.showDialog((String) null, disposable);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    MainModel.this.removeItemBle(itemModel);
                    MainModel.this.dismissDialog();
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    MainModel.this.removeItemBle(itemModel);
                    MainModel.this.dismissDialog();
                }
            });
            return;
        }
        if (itemModel.getDeviceInfo().portList != null) {
            i = itemModel.getDeviceInfo().portList.size();
        }
        RepositoryInjection.providerNotificationRepository().clearNotification(itemModel.getMac(), i).subscribeOn(Schedulers.m983io()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
                MainModel.this.showDialog((String) null, disposable);
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                MainModel.this.removeItemBle(itemModel);
                MainModel.this.dismissDialog();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                MainModel.this.removeItemBle(itemModel);
                MainModel.this.dismissDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void removeItemBle(final ItemModel itemModel) {
        stop();
        addSubscribe(this.repository.removeDevice(itemModel.getMac()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Action) new Action() {
            public void run() {
                MainModel.this.connected.remove(itemModel);
                itemModel.onCleared();
                MainModel.this.items.remove(itemModel);
                if (!MainModel.this.connected.isEmpty()) {
                    KLog.m65e("reset refresh tfp");
                    MainModel.this.syncTimeAndStart();
                }
            }
        }));
    }

    public void removeFromNet(String str, boolean z) {
        ItemModel itemModel;
        Iterator it = this.items.iterator();
        while (true) {
            if (!it.hasNext()) {
                itemModel = null;
                break;
            }
            itemModel = (ItemModel) it.next();
            if (ProtocolTransformer.isWorkWiFi(itemModel.getType(), itemModel.connectType) && !TextUtils.isEmpty(itemModel.getDevId()) && itemModel.getDevId().equals(str)) {
                break;
            }
        }
        removeFromNet(itemModel, z);
    }

    public void removeFromNet(ItemModel itemModel, boolean z) {
        Observable<BaseData<Void>> observable;
        if (itemModel != null) {
            String token = UserCache.getInstance().getToken();
            if (!TextUtils.isEmpty(token)) {
                if (itemModel.isShare.getValue() == null || itemModel.isShare.getValue() != Boolean.TRUE) {
                    observable = ApiHelper.getDeviceApi().unbindDev(token, itemModel.getDevId(), z);
                } else {
                    observable = ApiHelper.getDeviceApi().delShareDev(itemModel.getDevId(), itemModel.getDeviceInfo().shareEmail);
                }
                observable.subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                    public void doOnSubscribe(Disposable disposable) {
                        MainModel.this.showDialog((String) null, disposable);
                    }

                    /* access modifiers changed from: protected */
                    public String setTag() {
                        Class<MainModel> cls = MainModel.class;
                        return "MainModel";
                    }

                    /* access modifiers changed from: protected */
                    public void onError(String str) {
                        MainModel.this.showFailDialog(str);
                    }

                    /* access modifiers changed from: protected */
                    public void onSuccess(Void voidR) {
                        MainModel.this.dismissDialog();
                    }
                });
            }
        }
    }

    public void remove(String str, boolean z) {
        r1 = null;
        for (ItemModel itemModel : this.items) {
            if (itemModel.getMac().equals(str)) {
                break;
            }
        }
        if (itemModel != null) {
            remove(itemModel, z);
        }
    }

    /* access modifiers changed from: private */
    public void syncTimeAndStart() {
        KLog.m68i("start refresh ftp");
        ArrayList arrayList = new ArrayList();
        for (final ItemModel next : this.connected) {
            if (this.isUserVisible) {
                if (next.needRefresh()) {
                    arrayList.add(getDeviceTFP(next));
                } else if (!(next.getType() == 8 || (next.getType() == 11 && next.connectType == 1))) {
                    this.composite.add(this.repository.getTFP(next.getMac()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceTFP>() {
                        public void accept(DeviceTFP deviceTFP) throws Exception {
                            MainModel.this.updateItem(deviceTFP, next);
                        }
                    }));
                }
            } else if ((next.getType() == 11 && next.connectType == 0) || next.getType() == 7 || next.getType() == 9 || next.getType() == 12) {
                arrayList.add(getDeviceTFP(next));
            }
        }
        this.refresh = this.repository.syncTime().andThen(Single.merge(arrayList).repeatWhen(new Function<Flowable<Object>, Publisher<?>>() {
            public Publisher<?> apply(Flowable<Object> flowable) throws Exception {
                return flowable.delay(MainModel.this.isUserVisible ? 1 : 2, TimeUnit.SECONDS);
            }
        })).ignoreElements().subscribe();
        startHeartbeat();
    }

    private Single<DeviceTFP> getDeviceTFP(final ItemModel itemModel) {
        return this.repository.getTFP(itemModel.getMac(), (byte) 1).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(new Consumer<DeviceTFP>() {
            public void accept(DeviceTFP deviceTFP) throws Exception {
                MainModel.this.updateItem(deviceTFP, itemModel);
                if (MainModel.this.selectedDevice != null) {
                    MainModel.this.selectedDevice.mac.equalsIgnoreCase(itemModel.getMac());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateItem(DeviceTFP deviceTFP, ItemModel itemModel) {
        if (itemModel.getType() == 7 || ((itemModel.getType() == 11 && deviceTFP.connectType == 0) || itemModel.getType() == 9 || itemModel.getType() == 12)) {
            itemModel.isRefreshNotify = !itemModel.isRefreshNotify;
            if (itemModel.isRefreshNotify) {
                refreshNotify(deviceTFP, itemModel);
            }
            checkPort(deviceTFP, itemModel);
            if (this.isUserVisible) {
                itemModel.updateTFP(deviceTFP);
            }
            itemModel.onFlushAliveTime();
        } else if (itemModel.getType() == 8 || itemModel.getType() == 11) {
            if (deviceTFP.isOnline) {
                itemModel.connect(deviceTFP.connectTime);
            } else {
                itemModel.disconnect(deviceTFP.connectTime);
            }
            checkPort(deviceTFP, itemModel);
            itemModel.updateTFP(deviceTFP);
            itemModel.update(deviceTFP.name);
        } else {
            itemModel.updateTFP(deviceTFP);
        }
    }

    private void checkPort(DeviceTFP deviceTFP, ItemModel itemModel) {
        DeviceInfo deviceInfo = this.selectedDevice;
        if (deviceInfo == null) {
            return;
        }
        if ((deviceInfo.type != 7 && this.selectedDevice.type != 9 && this.selectedDevice.type != 12 && this.selectedDevice.type != 8 && this.selectedDevice.type != 11) || !this.selectedDevice.mac.equalsIgnoreCase(itemModel.getMac())) {
            return;
        }
        if (this.selectedDevice.choosePort == 0 && deviceTFP.choosePort != 0) {
            return;
        }
        if ((this.selectedDevice.choosePort == 0 || deviceTFP.choosePort != 0) && deviceTFP.portList != null) {
            for (PortInfo next : deviceTFP.portList) {
                if (next.f138id == this.selectedDevice.choosePort) {
                    boolean z = next.isPlug;
                }
            }
        }
    }

    private void refreshNotify(DeviceTFP deviceTFP, ItemModel itemModel) {
        if (deviceTFP.choosePort == 0) {
            LogService.getInstance().refreshNotificationMessage(itemModel.getMac(), (byte) 0, false);
            return;
        }
        for (PortInfo next : deviceTFP.portList) {
            if (next.isPlug) {
                LogService.getInstance().refreshNotificationMessage(itemModel.getMac(), next.f138id, false);
            }
        }
    }

    public void setSelectedDevice(DeviceInfo deviceInfo) {
        this.selectedDevice = deviceInfo;
    }

    /* access modifiers changed from: private */
    public void stop() {
        Disposable disposable = this.refresh;
        if (disposable != null) {
            disposable.dispose();
            this.refresh = null;
        }
        this.composite.clear();
    }

    private void stopNet() {
        Disposable disposable = this.refreshNet;
        if (disposable != null && !disposable.isDisposed()) {
            this.refreshNet.dispose();
            RxHttpUtils.cancel(TAG_FETCH_DEVICES);
        }
    }

    public void save() {
        for (ItemModel itemModel : this.items) {
            this.repository.saveModelInfo(itemModel.getMac(), itemModel.getDeviceInfo());
        }
    }

    public void updateSequences() {
        ArrayList arrayList = new ArrayList();
        for (ItemModel mac : this.items) {
            arrayList.add(mac.getMac());
        }
        this.repository.updateSequences(arrayList);
    }

    public void logout(boolean z, boolean z2) {
        stopNet();
        int i = 1;
        for (ItemModel itemModel : this.items) {
            if (ProtocolTransformer.isWorkWiFi(itemModel.getType(), itemModel.connectType)) {
                remove(itemModel, false);
                i = 2;
            }
        }
        UserCache.getInstance().setBingD(i);
        UserCache.getInstance().setEmail("");
        UserCache.getInstance().setToken("");
        pushToLogin(z, i, z2);
        this.isLogin.setValue(false);
    }

    public void pushToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C2343R.anim.left_in, C2343R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishOtherActivity(MainActivity.class);
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToVersionPage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_VERSION).withString(ActivityEvent.UPDATE_VERSION, this.updateVersion).withString(ActivityEvent.DEVICE_SOFTWARE_VERSION, AppUtils.getAppVersionName()).withBoolean(ActivityEvent.IS_APP_UPDATE, true).withTransition(C2343R.anim.right_in, C2343R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToPage(String str) {
        ARouter.getInstance().build(str).withTransition(C2343R.anim.right_in, C2343R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToLogin(boolean z, final int i, final boolean z2) {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_LOGIN).withBoolean(ActivityEvent.AFTER_LOGOUT, z).withTransition(C2343R.anim.right_in, C2343R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                MainModel.this.addSubscribe(Completable.timer(1500, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe((Action) new Action() {
                    public void run() {
                        if (z2 && i == 2) {
                            MainModel.this.showPromptLogin.setValue(true);
                        }
                    }
                }));
            }
        });
    }
}
