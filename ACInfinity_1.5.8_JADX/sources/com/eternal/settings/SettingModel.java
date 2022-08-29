package com.eternal.settings;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.MutableLiveData;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.TipDialog;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.concat.DeviceSetting;
import com.eternal.base.concat.FirmwareVersion;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.concat.NetDeviceInfo;
import com.eternal.base.concat.NetDeviceSetting;
import com.eternal.base.concat.NetPort;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.PortSetting;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.database.entity.Device;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.TimeUtil;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.BeanUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.RxUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Maybe;
import p014io.reactivex.Observable;
import p014io.reactivex.SingleSource;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class SettingModel extends BaseViewModel {
    public final int TAB_TAG_CONTROLLER = 0;
    public final int TAB_TAG_PORT = 1;
    public final int TAB_TAG_SYSTEM = 2;
    public MutableLiveData<String> allPortText = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String appSupportVersion;
    public byte brightness;
    public MutableLiveData<Boolean> bufferVisible = new MutableLiveData<>();
    public byte caliHum;
    public byte caliTmp;
    public ObservableBoolean committing = new ObservableBoolean();
    /* access modifiers changed from: private */
    public byte connectType;
    /* access modifiers changed from: private */
    public boolean connected;
    /* access modifiers changed from: private */
    public String devId;
    public MutableLiveData<String> deviceName = new MutableLiveData<>();
    public MutableLiveData<String> deviceTime = new MutableLiveData<>();
    public MutableLiveData<Boolean> deviceTimeAvailable = new MutableLiveData<>();
    public MutableLiveData<Boolean> deviceTimeVisible = new MutableLiveData<>();
    public MutableLiveData<String> deviceTimeZone = new MutableLiveData<>();
    public MaterialDialog dialog;
    /* access modifiers changed from: private */
    public String firmwareUrl;
    /* access modifiers changed from: private */
    public String firmwareVersion;
    public MutableLiveData<String> firstTabText = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String hardwareVersion;
    public MutableLiveData<Boolean> humVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> isDegree = new MutableLiveData<>();
    public ObservableBoolean isPlugPortType = new ObservableBoolean();
    public ObservableBoolean isShare = new ObservableBoolean();
    public byte leafTmpC;
    public byte leafTmpF;
    public MutableLiveData<Boolean> levelVisible = new MutableLiveData<>();
    private Disposable localSubs;
    /* access modifiers changed from: private */
    public String mac;
    public BindingCommand<String> nameChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            String str2;
            if (!SettingModel.this.isShare.get()) {
                str2 = String.format(Locale.ENGLISH, "NAME (%d/20)", new Object[]{Integer.valueOf(str.length())});
            } else {
                str2 = String.format(Locale.ENGLISH, "From %s", new Object[]{SettingModel.this.shareEmail});
            }
            SettingModel.this.nameSuggest.setValue(str2);
        }
    });
    private Disposable nameRefresh;
    public MutableLiveData<String> nameSuggest = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public NetDeviceInfo netDevice;
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SettingModel.this.checkModify(new OnModifyCallback() {
                public void onModifyCallback() {
                    SettingModel.this.finishAnimate(C2668R.anim.left_in, C2668R.anim.right_out);
                }
            });
        }
    });
    public BindingCommand<Void> onConfirm = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!RepositoryInjection.providerDeviceRepository().isConnect(SettingModel.this.mac)) {
                SettingModel settingModel = SettingModel.this;
                settingModel.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(settingModel.type, SettingModel.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                return;
            }
            DeviceSetting clone = SettingModel.this.setting.clone();
            boolean z = true;
            if (SettingModel.this.selectTabDevice.getValue() != null && SettingModel.this.selectTabDevice.getValue().intValue() != 0) {
                if (SettingModel.this.port != 0) {
                    if (SettingModel.this.portName.getValue() == null || TextUtils.isEmpty(SettingModel.this.portName.getValue().trim())) {
                        SettingModel.this.showEmptyDialog();
                        return;
                    }
                    if (SettingModel.this.port != 0) {
                        clone.name = SettingModel.this.portName.getValue().trim();
                    }
                    clone.portType = SettingModel.this.portType;
                }
                clone.transitionTemperatureC = SettingModel.this.transTmpC;
                clone.transitionTemperature = SettingModel.this.transTmpF;
                clone.transitionHumidity = SettingModel.this.transHum;
                clone.transitionVpd = SettingModel.this.transVpd;
                clone.typeOff = SettingModel.this.typeOff;
                clone.typeOn = SettingModel.this.typeOn;
                clone.choosePort = SettingModel.this.port;
                if (SettingModel.this.realDegree.getValue() != null) {
                    clone.isDegree = SettingModel.this.realDegree.getValue().booleanValue();
                }
            } else if (SettingModel.this.deviceName.getValue() == null || TextUtils.isEmpty(SettingModel.this.deviceName.getValue().trim())) {
                SettingModel.this.showEmptyDialog();
                return;
            } else {
                clone.name = SettingModel.this.deviceName.getValue().trim();
                if (SettingModel.this.isDegree.getValue() == null || SettingModel.this.isDegree.getValue() != Boolean.TRUE) {
                    z = false;
                }
                clone.isDegree = z;
                clone.brightness = SettingModel.this.brightness;
                clone.calibrationTemperature = SettingModel.this.caliTmp;
                clone.calibrationHumidity = SettingModel.this.caliHum;
                clone.leafTemperatureC = SettingModel.this.leafTmpC;
                clone.leafTemperatureF = SettingModel.this.leafTmpF;
                clone.choosePort = 0;
                z = false;
            }
            if (ProtocolTransformer.isWorkWiFi(SettingModel.this.type, SettingModel.this.connectType)) {
                SettingModel.this.setSettingForNet(clone, clone.choosePort, z);
            } else {
                SettingModel.this.setSettingForBle(clone, clone.choosePort, z);
            }
        }
    });
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SettingModel settingModel = SettingModel.this;
            settingModel.addSubscribe(settingModel.repository.getName(SettingModel.this.mac, (byte) 0).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceName>() {
                public void accept(DeviceName deviceName) throws Exception {
                    SettingModel.this.showDelete(deviceName.name);
                }
            }));
        }
    });
    public BindingCommand<Void> onEditTimeZone = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_TIME_ZONE_SEARCH).withString(ActivityEvent.DEVICE_ID, SettingModel.this.devId).withString(ActivityEvent.DEVICE_TIME_ZONE, SettingModel.this.timeZone).withTransition(C2668R.anim.right_in, C2668R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onEditType = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SettingModel.this.portTypeExpand.set(!SettingModel.this.portTypeExpand.get());
            if (!SettingModel.this.portTypeExpand.get()) {
                Messenger.getDefault().sendNoMsg("update port type");
            }
        }
    });
    public BindingCommand<Void> onReset = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SettingModel settingModel = SettingModel.this;
            settingModel.addSubscribe(settingModel.repository.getName(SettingModel.this.mac, (byte) 0).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceName>() {
                public void accept(DeviceName deviceName) throws Exception {
                    SettingModel.this.showResetFactory(deviceName.name);
                }
            }));
        }
    });
    public BindingCommand<Void> onShare = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_INVITE).withString(ActivityEvent.DEVICE_ID, SettingModel.this.devId).withTransition(C2668R.anim.right_in, C2668R.anim.left_out).navigation();
        }
    });
    public BindingCommand<Void> onTabController = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (SettingModel.this.selectTabDevice.getValue() != null && SettingModel.this.selectTabDevice.getValue().intValue() != 0) {
                SettingModel.this.selectTabDevice.setValue(0);
                SettingModel settingModel = SettingModel.this;
                settingModel.choosePort(settingModel.setting.choosePort);
            }
        }
    });
    public BindingCommand<Void> onTabPort = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (SettingModel.this.selectTabDevice.getValue() != null && SettingModel.this.selectTabDevice.getValue().intValue() != 1) {
                SettingModel.this.selectTabDevice.setValue(1);
                SettingModel.this.updateControllerView();
                SettingModel settingModel = SettingModel.this;
                settingModel.choosePort(settingModel.port);
            }
        }
    });
    public BindingCommand<Void> onTabSystem = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (SettingModel.this.selectTabDevice.getValue() != null && SettingModel.this.selectTabDevice.getValue().intValue() != 2) {
                SettingModel.this.selectTabDevice.setValue(2);
                SettingModel.this.updateControllerView();
                SettingModel settingModel = SettingModel.this;
                settingModel.choosePort(settingModel.setting.choosePort);
            }
        }
    });
    public BindingCommand<Void> onTmpC = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SettingModel.this.isDegree.setValue(true);
        }
    });
    public BindingCommand<Void> onTmpF = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            SettingModel.this.isDegree.setValue(false);
        }
    });
    public BindingCommand<Void> onUpdate = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!RepositoryInjection.providerDeviceRepository().isConnect(SettingModel.this.mac)) {
                SettingModel settingModel = SettingModel.this;
                settingModel.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(settingModel.type, SettingModel.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                return;
            }
            ARouter.getInstance().build(RouterActivityPath.Account.PAGE_VERSION).withString(ActivityEvent.DEVICE_MAC, SettingModel.this.mac).withString(ActivityEvent.DEVICE_ID, SettingModel.this.devId).withByte(ActivityEvent.DEVICE_PORT, SettingModel.this.port).withByte(ActivityEvent.DEVICE_TYPE, SettingModel.this.type).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, SettingModel.this.connectType).withString(ActivityEvent.DEVICE_FIRMWARE_URL, SettingModel.this.firmwareUrl).withString(ActivityEvent.UPDATE_VERSION, SettingModel.this.updateVersion).withString(ActivityEvent.DEVICE_SOFTWARE_VERSION, SettingModel.this.softwareVersion).withString(ActivityEvent.DEVICE_FIRMWARE_VERSION, SettingModel.this.firmwareVersion).withString(ActivityEvent.DEVICE_HARDWARE_VERSION, SettingModel.this.hardwareVersion).withBoolean(ActivityEvent.IS_APP_UPDATE, false).withTransition(C2668R.anim.right_in, C2668R.anim.left_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
                public void onArrival(Postcard postcard) {
                }
            });
        }
    });
    public BindingCommand<Void> onWifiSetting = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (TextUtils.isEmpty(UserCache.getInstance().getToken())) {
                ARouter.getInstance().build(RouterActivityPath.Device.PAGE_PROMPT).withTransition(C2668R.anim.right_in, C2668R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
            } else {
                SettingModel.this.pushToWiFiSettingPage();
            }
        }
    });
    public byte port;
    public MutableLiveData<String> portName = new MutableLiveData<>();
    public BindingCommand<String> portNameChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            String str2;
            if (!SettingModel.this.isShare.get()) {
                str2 = String.format(Locale.ENGLISH, "NAME (%d/20)", new Object[]{Integer.valueOf(str.length())});
            } else {
                str2 = "NAME";
            }
            SettingModel.this.portNameSuggest.setValue(str2);
        }
    });
    public MutableLiveData<String> portNameSuggest = new MutableLiveData<>();
    public MutableLiveData<Boolean> portNameVisible = new MutableLiveData<>();
    public MutableLiveData<String> portTabText = new MutableLiveData<>();
    public int portType;
    public ObservableBoolean portTypeExpand = new ObservableBoolean();
    public MutableLiveData<Integer> portTypeRes = new MutableLiveData<>();
    public MutableLiveData<String> portTypeText = new MutableLiveData<>();
    public MutableLiveData<Boolean> portTypeVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> progress = new MutableLiveData<>();
    public MutableLiveData<Boolean> realDegree = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public DeviceRepository repository;
    public MutableLiveData<Boolean> resetVisible = new MutableLiveData<>();
    public int resistance;
    public MutableLiveData<Integer> selectTabDevice = new MutableLiveData<>();
    DeviceSetting setting;
    /* access modifiers changed from: private */
    public String shareEmail;
    public MutableLiveData<Boolean> shareVisible = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String softwareVersion;
    private Disposable subs;
    public MutableLiveData<String> subtitle = new MutableLiveData<>();
    public MutableLiveData<Boolean> tabLayoutVisible = new MutableLiveData<>();
    private Disposable timeEvent;
    private Disposable timeRefresh;
    public MutableLiveData<String> timeText = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeVisible = new MutableLiveData<>();
    public String timeZone;
    public MutableLiveData<Integer> tmpRes = new MutableLiveData<>();
    public MutableLiveData<Boolean> transAndBufferVpdVisible = new MutableLiveData<>();
    public byte transHum;
    public byte transTmpC;
    public byte transTmpF;
    public byte transVpd;
    public MutableLiveData<Boolean> transitionVisible = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public byte type;
    public byte typeOff;
    public byte typeOn;
    public MutableLiveData<Boolean> updateAvailable = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String updateVersion;
    public MutableLiveData<Boolean> updateVisible = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public byte version;
    public MutableLiveData<Boolean> vpdLeafVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> wifiSettingVisible = new MutableLiveData<>();

    public interface OnModifyCallback {
        void onModifyCallback();
    }

    private void updateTabName(List<DeviceName> list) {
    }

    public SettingModel(Application application) {
        super(application);
        this.tmpRes.setValue(Integer.valueOf(C2668R.mipmap.group_c));
    }

    /* access modifiers changed from: package-private */
    public void init(DeviceRepository deviceRepository, String str, String str2, byte b, byte b2, byte b3, String str3, String str4, String str5, byte b4) {
        this.repository = deviceRepository;
        this.softwareVersion = str3;
        this.hardwareVersion = str4;
        this.firmwareVersion = str5;
        this.version = b3;
        this.connectType = b4;
        this.timeZone = TimeUtil.getCurrentTimeZone3();
        this.type = b2;
        this.mac = str;
        this.devId = str2;
        this.port = b;
        boolean isConnect = RepositoryInjection.providerDeviceRepository().isConnect(str);
        boolean z = true;
        if (isConnect) {
            this.connected = true;
        } else {
            this.connected = false;
        }
        updateSystemItemVisible();
        this.transAndBufferVpdVisible.setValue(Boolean.valueOf(b2 == 11 || b3 >= 4));
        this.resetVisible.setValue(Boolean.valueOf(ProtocolTransformer.isDeviceC(b2)));
        this.allPortText.setValue(ProtocolTransformer.isOutletDevice(b2) ? "ALL OUTLETS SETTINGS" : "ALL PORTS SETTINGS");
        this.portTabText.setValue(ProtocolTransformer.isOutletDevice(b2) ? "OUTLET" : "PORT");
        this.firstTabText.setValue(ProtocolTransformer.isDeviceC(b2) ? "DEVICE" : "CONTROLLER");
        this.shareVisible.setValue(Boolean.valueOf(ProtocolTransformer.isWorkWiFi(b2, b4)));
        connectEvent();
        MutableLiveData<Boolean> mutableLiveData = this.portNameVisible;
        if (b == 0) {
            z = false;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
        initData();
        refreshData();
        refreshName();
        refreshTime();
        queryVersion();
        this.selectTabDevice.setValue(0);
    }

    public void updateSystemItemVisible() {
        this.updateVisible.setValue(Boolean.valueOf(ProtocolTransformer.isSupportFirmwareUpdate(this.softwareVersion, this.type)));
        this.deviceTimeVisible.setValue(Boolean.valueOf(ProtocolTransformer.isWorkWiFi(this.type, this.connectType)));
        this.shareVisible.setValue(Boolean.valueOf(ProtocolTransformer.isWorkWiFi(this.type, this.connectType)));
    }

    private void refreshTime() {
        if (!TextUtils.isEmpty(this.mac)) {
            Disposable disposable = this.timeRefresh;
            if (disposable == null || disposable.isDisposed()) {
                Disposable subscribe = RepositoryInjection.providerDeviceRepository().getConnectInfo(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConnectInfo>() {
                    public void accept(ConnectInfo connectInfo) {
                        boolean isConnect = RepositoryInjection.providerDeviceRepository().isConnect(SettingModel.this.mac);
                        if (isConnect) {
                            SettingModel.this.timeVisible.setValue(false);
                        } else {
                            SettingModel.this.timeVisible.setValue(true);
                            SettingModel.this.timeText.setValue(Utils.getString(C2668R.string.tip_last, ProtocolTransformer.getTime(connectInfo.connectTime)));
                        }
                        if (connectInfo.connectType != SettingModel.this.connectType) {
                            byte unused = SettingModel.this.connectType = connectInfo.connectType;
                            String unused2 = SettingModel.this.devId = connectInfo.deviceId;
                            SettingModel.this.updateSystemItemVisible();
                            if (isConnect) {
                                SettingModel.this.refreshData();
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    public void accept(Throwable th) {
                        KLog.m65e(th);
                    }
                });
                this.timeRefresh = subscribe;
                addSubscribe(subscribe);
            }
        }
    }

    private void refreshName() {
        Disposable disposable = this.nameRefresh;
        if (disposable == null || disposable.isDisposed()) {
            Disposable subscribe = RepositoryInjection.providerDeviceRepository().getDeviceName(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<DeviceName>>() {
                public void accept(List<DeviceName> list) {
                    if (list != null) {
                        Iterator<DeviceName> it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            DeviceName next = it.next();
                            if (next.port == 0) {
                                SettingModel.this.subtitle.setValue(next.name);
                                break;
                            }
                        }
                    }
                    if (list != null && list.size() != 0 && SettingModel.this.setting != null && SettingModel.this.setting.portList != null && SettingModel.this.setting.portList.size() != 0) {
                        Collections.sort(list, new Comparator<DeviceName>() {
                            public int compare(DeviceName deviceName, DeviceName deviceName2) {
                                return deviceName.port - deviceName2.port;
                            }
                        });
                        for (PortInfo next2 : SettingModel.this.setting.portList) {
                            Iterator<DeviceName> it2 = list.iterator();
                            while (true) {
                                if (!it2.hasNext()) {
                                    break;
                                }
                                DeviceName next3 = it2.next();
                                if (next2.f138id == next3.port) {
                                    next2.name = next3.name;
                                    break;
                                }
                            }
                        }
                        Messenger.getDefault().sendNoMsg("update tab name");
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    KLog.m65e(th);
                }
            });
            this.nameRefresh = subscribe;
            addSubscribe(subscribe);
        }
    }

    private void connectEvent() {
        addSubscribe(RxBus.getDefault().toObservable(BluetoothEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BluetoothEvent>() {
            public void accept(BluetoothEvent bluetoothEvent) {
                if (SettingModel.this.mac.equals(bluetoothEvent.mac)) {
                    boolean z = false;
                    if (bluetoothEvent.what == 0) {
                        boolean unused = SettingModel.this.connected = false;
                        Messenger.getDefault().send(SettingModel.this.setting, "update");
                        SettingModel.this.unregisterRxBus();
                    } else if (bluetoothEvent.what == 2) {
                        boolean unused2 = SettingModel.this.connected = true;
                        SettingModel.this.refreshData();
                        SettingModel.this.queryVersion();
                    } else if (bluetoothEvent.what == 8) {
                        if (bluetoothEvent.obj != null) {
                            SettingModel.this.timeZone = (String) bluetoothEvent.obj;
                            SettingModel.this.deviceTimeZone.setValue(SettingModel.this.timeZone);
                            SettingModel.this.deviceTime.setValue(ProtocolTransformer.getTime(System.currentTimeMillis(), (String) bluetoothEvent.obj));
                        }
                    } else if (bluetoothEvent.what == 5 && SettingModel.this.realDegree.getValue() != null) {
                        if (bluetoothEvent.f135i1 == 1) {
                            z = true;
                        }
                        if (SettingModel.this.realDegree.getValue().booleanValue() != z) {
                            SettingModel.this.realDegree.setValue(Boolean.valueOf(z));
                            SettingModel settingModel = SettingModel.this;
                            settingModel.choosePort(settingModel.port);
                        }
                    }
                }
            }
        }));
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) {
                if (activityEvent.what == 20) {
                    RxBus.getDefault().post(new ActivityEvent(12, RouterActivityPath.Device.PAGE_PROMPT));
                    SettingModel.this.pushToWiFiSettingPage();
                }
            }
        }));
    }

    public void putTimeZone() {
        ApiHelper.getDeviceApi().putDevTimeZone(this.devId, this.timeZone).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
                SettingModel.this.addSubscribe(disposable);
                SettingModel.this.showDialog((String) null, disposable);
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                SettingModel.this.showFailDialog(str);
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                SettingModel.this.finishAnimate(C2668R.anim.left_in, C2668R.anim.right_out);
            }
        });
    }

    public void unregisterRxBus() {
        Disposable disposable = this.timeEvent;
        if (disposable != null) {
            disposable.dispose();
            this.timeEvent = null;
        }
    }

    private void initData() {
        Disposable subscribe = this.repository.getSetting(this.mac, this.port, this.type, true).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) {
            }
        }).subscribe(new Consumer<DeviceSetting>() {
            public void accept(DeviceSetting deviceSetting) {
                boolean z = true;
                SettingModel.this.isShare.set(deviceSetting.isShare != 0);
                MutableLiveData<Boolean> mutableLiveData = SettingModel.this.wifiSettingVisible;
                if (!(deviceSetting.isShare == 0 && SettingModel.this.type == 11)) {
                    z = false;
                }
                mutableLiveData.setValue(Boolean.valueOf(z));
                String unused = SettingModel.this.shareEmail = deviceSetting.shareEmail;
                if (!ProtocolTransformer.isWorkWiFi(SettingModel.this.type, SettingModel.this.connectType)) {
                    SettingModel.this.port = deviceSetting.choosePort;
                }
                SettingModel.this.update(deviceSetting);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) {
                KLog.m65e(th);
            }
        });
        this.localSubs = subscribe;
        addSubscribe(subscribe);
    }

    /* access modifiers changed from: private */
    public void refreshData() {
        if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            refreshDataForNet();
        } else {
            refreshDataForBle();
        }
    }

    public void choosePort(byte b) {
        KLog.m65e("设置页 切换端口");
        this.port = b;
        if (b == 0) {
            this.portNameVisible.setValue(false);
            this.portTypeVisible.setValue(false);
            this.tabLayoutVisible.setValue(false);
            Messenger.getDefault().send(Byte.valueOf(this.realDegree.getValue() == Boolean.TRUE ? this.setting.transitionTemperatureC : this.setting.transitionTemperature), "set trans tmp");
            Messenger.getDefault().send(Byte.valueOf(this.setting.transitionHumidity), "set trans hum");
            Messenger.getDefault().send(Byte.valueOf(this.setting.transitionVpd), "set trans vpd");
            Messenger.getDefault().send(Byte.valueOf(this.setting.typeOff), "set level off");
            Messenger.getDefault().send(Byte.valueOf(this.setting.typeOn), "set level on");
            if (isOutletDevice(0)) {
                this.bufferVisible.setValue(true);
                this.transitionVisible.setValue(false);
                this.levelVisible.setValue(false);
                return;
            }
            this.bufferVisible.setValue(false);
            this.transitionVisible.setValue(true);
            this.levelVisible.setValue(true);
            return;
        }
        this.portNameVisible.setValue(true);
        this.tabLayoutVisible.setValue(true);
        this.portTypeVisible.setValue(true);
        if (this.setting.portList != null) {
            for (PortSetting next : this.setting.portList) {
                if (next.f138id == b) {
                    if (isOutletDevice(next.fanType)) {
                        this.bufferVisible.setValue(true);
                        this.transitionVisible.setValue(false);
                        this.levelVisible.setValue(false);
                    } else {
                        this.bufferVisible.setValue(false);
                        this.transitionVisible.setValue(true);
                        this.levelVisible.setValue(true);
                    }
                    Messenger.getDefault().send(Byte.valueOf(this.realDegree.getValue() == Boolean.TRUE ? next.transitionTemperatureC : next.transitionTemperature), "set trans tmp");
                    Messenger.getDefault().send(Byte.valueOf(next.transitionHumidity), "set trans hum");
                    Messenger.getDefault().send(Byte.valueOf(next.transitionVpd), "set trans vpd");
                    Messenger.getDefault().send(Byte.valueOf(next.typeOff), "set level off");
                    Messenger.getDefault().send(Byte.valueOf(next.typeOn), "set level on");
                    this.portName.setValue(next.name);
                    Messenger.getDefault().send(next, "set port type");
                    return;
                }
            }
        }
    }

    public boolean isOutletDevice(int i) {
        DeviceSetting deviceSetting = this.setting;
        if (deviceSetting == null) {
            return ProtocolTransformer.isOutletDevice(this.type, i);
        }
        if (this.type == 11 && this.port == 0 && deviceSetting.portList != null && this.setting.portList.size() > 0) {
            for (PortInfo next : this.setting.portList) {
                if (next.f138id != 0 && next.isPlug) {
                    return ProtocolTransformer.isOutletDevice(this.type, next.fanType);
                }
            }
        }
        return ProtocolTransformer.isOutletDevice(this.type, i);
    }

    public void checkModify(OnModifyCallback onModifyCallback) {
        if (this.selectTabDevice.getValue() == null) {
            onModifyCallback.onModifyCallback();
        } else if (this.selectTabDevice.getValue().intValue() == 0) {
            if (isControlModify()) {
                showModifyDialog(onModifyCallback);
            } else {
                onModifyCallback.onModifyCallback();
            }
        } else if (this.selectTabDevice.getValue().intValue() != 1) {
            onModifyCallback.onModifyCallback();
        } else if (isPortModify()) {
            showModifyDialog(onModifyCallback);
        } else {
            onModifyCallback.onModifyCallback();
        }
    }

    private void showModifyDialog(final OnModifyCallback onModifyCallback) {
        MaterialDialog materialDialog = this.dialog;
        if (materialDialog == null || !materialDialog.isShowing()) {
            this.dialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), "Leave without saving?", "Any changes you made will be lost.", Utils.getString(C2668R.string.tip_cancel_lowercase), Utils.getString(C2668R.string.tip_leave), true, true, new View.OnClickListener() {
                public void onClick(View view) {
                }
            }, new View.OnClickListener() {
                public void onClick(View view) {
                    onModifyCallback.onModifyCallback();
                }
            });
        }
    }

    private boolean isPortModify() {
        DeviceSetting deviceSetting = this.setting;
        if (deviceSetting == null) {
            return false;
        }
        if (this.port == 0) {
            if (this.realDegree.getValue() == Boolean.TRUE) {
                if (this.setting.transitionTemperatureC != this.transTmpC) {
                    return true;
                }
            } else if (this.setting.transitionTemperature != this.transTmpF) {
                return true;
            }
            if (this.setting.transitionHumidity == this.transHum && this.setting.typeOff == this.typeOff && this.setting.typeOn == this.typeOn) {
                return false;
            }
            return true;
        }
        if (deviceSetting.portList != null) {
            for (PortSetting next : this.setting.portList) {
                if (next.f138id == this.port) {
                    if (this.isDegree.getValue() == Boolean.TRUE) {
                        if (next.transitionTemperatureC != this.transTmpC) {
                            return true;
                        }
                    } else if (next.transitionTemperature != this.transTmpF) {
                        return true;
                    }
                    if (next.transitionHumidity != this.transHum || next.typeOff != this.typeOff || next.typeOn != this.typeOn || !this.portName.getValue().trim().equals(next.name) || next.portType != this.portType) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isControlModify() {
        DeviceSetting deviceSetting = this.setting;
        if (deviceSetting == null) {
            return false;
        }
        if (!deviceSetting.name.trim().equals(this.deviceName.getValue().trim())) {
            return true;
        }
        if (this.isDegree.getValue() != null && this.setting.isDegree != this.isDegree.getValue().booleanValue()) {
            return true;
        }
        if ((this.setting.brightness != this.brightness && (this.setting.brightness != 0 || this.brightness != -93)) || this.setting.calibrationTemperature != this.caliTmp || this.setting.calibrationHumidity != this.caliHum) {
            return true;
        }
        if (this.isDegree.getValue() == Boolean.TRUE) {
            if (this.setting.leafTemperatureC != this.leafTmpC) {
                return true;
            }
            return false;
        } else if (this.setting.leafTemperatureF != this.leafTmpF) {
            return true;
        } else {
            return false;
        }
    }

    private void refreshDataForBle() {
        Disposable subscribe = this.repository.getSetting(this.mac, this.port, this.type, false).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) {
                if (SettingModel.this.connected) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }
        }).subscribe(new Consumer<DeviceSetting>() {
            public void accept(DeviceSetting deviceSetting) {
                if (SettingModel.this.version < 3 && SettingModel.this.setting != null) {
                    deviceSetting.leafTemperatureC = SettingModel.this.setting.leafTemperatureC;
                    deviceSetting.leafTemperatureF = SettingModel.this.setting.leafTemperatureF;
                }
                SettingModel.this.port = deviceSetting.choosePort;
                SettingModel.this.update(deviceSetting);
                if (SettingModel.this.connected) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) {
                KLog.m65e(th);
            }
        });
        this.subs = subscribe;
        addSubscribe(subscribe);
    }

    /* access modifiers changed from: private */
    public void update(DeviceSetting deviceSetting) {
        this.setting = deviceSetting;
        this.port = deviceSetting.choosePort;
        this.realDegree.setValue(Boolean.valueOf(deviceSetting.isDegree));
        updateControllerView();
        Messenger.getDefault().send(deviceSetting, "update");
    }

    /* access modifiers changed from: private */
    public void updateControllerView() {
        this.isDegree.setValue(Boolean.valueOf(this.setting.isDegree));
        this.deviceName.setValue(this.setting.name);
        Messenger messenger = Messenger.getDefault();
        messenger.send(Byte.valueOf(this.setting.brightness), "set brightness");
        messenger.send(Byte.valueOf(this.setting.calibrationTemperature), "set cali tmp");
        messenger.send(Byte.valueOf(this.setting.calibrationHumidity), "set cali hum");
        Messenger.getDefault().send(Byte.valueOf(this.setting.isDegree ? this.setting.leafTemperatureC : this.setting.leafTemperatureF), "set leaf tmp");
    }

    /* access modifiers changed from: private */
    public String getOriginName(byte b) {
        DeviceSetting deviceSetting = this.setting;
        if (deviceSetting == null) {
            return null;
        }
        if (b == 0) {
            return deviceSetting.name;
        }
        if (deviceSetting.portList == null) {
            return null;
        }
        for (PortInfo next : this.setting.portList) {
            if (next.f138id == b) {
                return next.name;
            }
        }
        return null;
    }

    private void refreshDataForNet() {
        this.subs = ApiHelper.getDeviceApi().getDeviceInfo(this.devId).subscribeOn(Schedulers.m983io()).toList().observeOn(AndroidSchedulers.mainThread()).map(new Function<List<BaseData<NetDevice>>, NetDeviceInfo>() {
            public NetDeviceInfo apply(List<BaseData<NetDevice>> list) throws Exception {
                if (list.get(0).getCode() != 200 || list.get(0).getData() == null) {
                    SettingModel.this.showFailDialog(list.get(0).getMsg());
                    return new NetDeviceInfo();
                } else if (((NetDevice) list.get(0).getData()).deviceInfo == null) {
                    return new NetDeviceInfo();
                } else {
                    NetDeviceInfo unused = SettingModel.this.netDevice = ((NetDevice) list.get(0).getData()).deviceInfo;
                    return SettingModel.this.netDevice;
                }
            }
        }).observeOn(Schedulers.m983io()).flatMap(new Function<NetDeviceInfo, SingleSource<List<BaseData<NetDeviceSetting>>>>() {
            public SingleSource<List<BaseData<NetDeviceSetting>>> apply(NetDeviceInfo netDeviceInfo) throws Exception {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i <= netDeviceInfo.portInfo.size(); i++) {
                    arrayList.add(ApiHelper.getDeviceApi().getDevSetting(SettingModel.this.devId, (byte) i).subscribeOn(Schedulers.m983io()).map(new Function<BaseData<NetDeviceSetting>, BaseData<NetDeviceSetting>>() {
                        public BaseData<NetDeviceSetting> apply(BaseData<NetDeviceSetting> baseData) throws Exception {
                            if (baseData.getData() != null) {
                                baseData.getData().portType = SettingModel.this.repository.getPortType(SettingModel.this.mac, (byte) baseData.getData().externalPort);
                            }
                            return baseData;
                        }
                    }));
                }
                return Observable.merge(arrayList).toList();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<BaseData<NetDeviceSetting>>>() {
            public void accept(List<BaseData<NetDeviceSetting>> list) throws Exception {
                boolean z;
                int i;
                boolean z2;
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                ArrayList arrayList = new ArrayList();
                DeviceSetting deviceSetting = null;
                for (int i2 = 0; i2 < list.size(); i2++) {
                    if (list.get(i2).getCode() != 200) {
                        SettingModel.this.showFailDialog(list.get(i2).getMsg());
                        return;
                    }
                    NetDeviceSetting netDeviceSetting = (NetDeviceSetting) list.get(i2).getData();
                    if (netDeviceSetting.externalPort == 0) {
                        Iterator<NetPort> it = SettingModel.this.netDevice.portInfo.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                z2 = false;
                                break;
                            }
                            NetPort next = it.next();
                            if (next.isPlug == 1) {
                                z2 = ProtocolTransformer.isOutletDevice(SettingModel.this.type, next.portResistance);
                                break;
                            }
                        }
                        deviceSetting = netDeviceSetting.toDeviceSetting(SettingModel.this.getOriginName((byte) netDeviceSetting.externalPort), z2);
                        deviceSetting.choosePort = SettingModel.this.netDevice.choosePort;
                    } else {
                        Iterator<NetPort> it2 = SettingModel.this.netDevice.portInfo.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                z = false;
                                i = 0;
                                break;
                            }
                            NetPort next2 = it2.next();
                            if (next2.portIndex == netDeviceSetting.externalPort) {
                                z = next2.isPlug == 1;
                                i = next2.portResistance;
                            }
                        }
                        arrayList.add(netDeviceSetting.toPortSetting((byte) netDeviceSetting.externalPort, SettingModel.this.type, SettingModel.this.getOriginName((byte) netDeviceSetting.externalPort), z, i));
                    }
                }
                if (deviceSetting != null) {
                    Collections.sort(arrayList, new Comparator<PortSetting>() {
                        public int compare(PortSetting portSetting, PortSetting portSetting2) {
                            return portSetting.f138id - portSetting2.f138id;
                        }
                    });
                    deviceSetting.portList = arrayList;
                    SettingModel.this.update(deviceSetting);
                    SettingModel.this.repository.updateSetting(SettingModel.this.mac, deviceSetting);
                }
            }
        });
    }

    public void resetDeviceToFactory() {
        if (!RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(this.type, this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
        } else {
            addSubscribe(this.repository.resetDeviceToFactory(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) throws Exception {
                    if (SettingModel.this.repository.isConnect(SettingModel.this.mac)) {
                        RxBus.getDefault().post(new ProgressEvent((byte) 0));
                    }
                }
            }).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    if (SettingModel.this.repository.isConnect(SettingModel.this.mac)) {
                        RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    }
                }
            }));
        }
    }

    /* access modifiers changed from: private */
    public void showResetFactory(String str) {
        Activity currentActivity = AppManager.getAppManager().currentActivity();
        if (RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            this.dialog = TipDialog.showTipDialog(currentActivity, "Are you sure you want to reset to factory?", Utils.getString(C2668R.string.tip_reset_factory_content, str), Utils.getString(C2668R.string.tip_cancel_lowercase), Utils.getString(C2668R.string.tip_confirm_lowercase), true, true, new View.OnClickListener() {
                public void onClick(View view) {
                }
            }, new View.OnClickListener() {
                public void onClick(View view) {
                    SettingModel.this.resetDeviceToFactory();
                }
            });
            return;
        }
        this.dialog = TipDialog.showTipDialog(currentActivity, Utils.getString(C2668R.string.tip_connection_lost), Utils.getString(C2668R.string.tip_connect_device), (String) null, Utils.getString(C2668R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null);
    }

    /* access modifiers changed from: private */
    public void showDelete(String str) {
        final Activity currentActivity = AppManager.getAppManager().currentActivity();
        if (!RepositoryInjection.providerDeviceRepository().isConnect(this.mac) || this.isShare.get()) {
            TipDialog.showTipDialog(currentActivity, Utils.getString(C2668R.string.tip_remove_device_title, str), Utils.getString(C2668R.string.tip_remove_device_only_content, str), currentActivity.getResources().getString(C2668R.string.tip_cancel_lowercase), currentActivity.getResources().getString(C2668R.string.tip_delete_lowercase), true, true, new View.OnClickListener() {
                public void onClick(View view) {
                }
            }, new View.OnClickListener() {
                public void onClick(View view) {
                    SettingModel.this.pushToMain(currentActivity, false);
                }
            });
            return;
        }
        int i = C2668R.string.tip_remove_device_content;
        Object[] objArr = new Object[1];
        objArr[0] = ProtocolTransformer.isDeviceC(this.type) ? NotificationCompat.CATEGORY_ALARM : "advance";
        TipDialog.showTipDialog2(currentActivity, Utils.getString(C2668R.string.tip_remove_device_title, str), Utils.getString(i, objArr), currentActivity.getResources().getString(C2668R.string.tip_cancel_lowercase), currentActivity.getResources().getString(ProtocolTransformer.isDeviceC(this.type) ? C2668R.string.tip_delete_device_and_alarm : C2668R.string.tip_delete_device_and_advance), currentActivity.getResources().getString(C2668R.string.tip_remove_device), true, true, new View.OnClickListener() {
            public void onClick(View view) {
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                SettingModel.this.pushToMain(currentActivity, true);
            }
        }, new View.OnClickListener() {
            public void onClick(View view) {
                SettingModel.this.pushToMain(currentActivity, false);
            }
        }, (DialogInterface.OnDismissListener) null);
    }

    /* access modifiers changed from: private */
    public void pushToMain(Activity activity, boolean z) {
        AppManager.getAppManager().finishActivity(ARouter.getInstance().build(RouterActivityPath.Detail.PAGE_DETAIL).getDestination());
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C2668R.anim.left_in, C2668R.anim.right_out).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_TYPE, this.type).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).withBoolean(ActivityEvent.DELETE_ADVANCE_ALL, z).withAction(ActivityEvent.ACTION_DELETE_DEVICE).navigation((Context) activity, (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                SettingModel.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToWiFiSettingPage() {
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_WIFI_SETTING).withString(ActivityEvent.SSID, "").withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_TYPE, this.type).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).withTransition(C2668R.anim.right_in, C2668R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    /* access modifiers changed from: private */
    public void queryVersion() {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect == null || TextUtils.isEmpty(connect.softwareVersion)) {
            String str = null;
            Device connectNet = this.repository.getConnectNet(this.mac);
            if (connectNet != null) {
                str = connectNet.softwareVersion;
            }
            if (TextUtils.isEmpty(str)) {
                addSubscribe(this.repository.getSoftwareVersion(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    public void accept(String str) throws Exception {
                        if (!TextUtils.isEmpty(str)) {
                            String unused = SettingModel.this.softwareVersion = str;
                        }
                        SettingModel.this.fetchFirmwareVersion();
                    }
                }));
                return;
            }
            this.softwareVersion = str;
            fetchFirmwareVersion();
            return;
        }
        this.softwareVersion = connect.softwareVersion;
        fetchFirmwareVersion();
    }

    /* access modifiers changed from: private */
    public void fetchFirmwareVersion() {
        ApiHelper.getAccountApi().getFirmwareVersion(this.type, this.softwareVersion, this.hardwareVersion).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<FirmwareVersion>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                KLog.m65e(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(FirmwareVersion firmwareVersion) {
                if (firmwareVersion != null) {
                    if (TextUtils.isEmpty(firmwareVersion.firmwareVersion)) {
                        SettingModel.this.updateAvailable.setValue(false);
                        return;
                    }
                    String unused = SettingModel.this.updateVersion = firmwareVersion.fVersion;
                    String unused2 = SettingModel.this.firmwareUrl = firmwareVersion.fUrl;
                    String unused3 = SettingModel.this.appSupportVersion = firmwareVersion.androidSupportVersion;
                    if (SettingModel.this.updateVisible.getValue() == Boolean.TRUE) {
                        SettingModel.this.updateAvailable.setValue(true);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void setSettingForNet(final DeviceSetting deviceSetting, final byte b, final boolean z) {
        ApiHelper.getDeviceApi().updateSetting(BeanUtils.transBean2Map(new NetDeviceSetting(this.devId, b, deviceSetting, this.bufferVisible.getValue() == Boolean.TRUE))).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
                SettingModel.this.addSubscribe(disposable);
                KLog.m65e("发送设置");
                SettingModel.this.committing.set(true);
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                SettingModel.this.showFailDialog(str);
                SettingModel.this.committing.set(false);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                SettingModel.this.repository.updateSetting(SettingModel.this.mac, b, deviceSetting, z);
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                if (!z) {
                    RxBus.getDefault().post(new ActivityEvent(3, Byte.valueOf(deviceSetting.leafTemperatureC)));
                }
                SettingModel.this.addSubscribe(Maybe.just(true).delay(1, TimeUnit.SECONDS).subscribe(new Consumer<Boolean>() {
                    public void accept(Boolean bool) throws Exception {
                        SettingModel.this.finishAnimate(C2668R.anim.left_in, C2668R.anim.right_out);
                    }
                }));
            }
        });
    }

    /* access modifiers changed from: private */
    public void setSettingForBle(final DeviceSetting deviceSetting, byte b, final boolean z) {
        addSubscribe(this.repository.setSetting(this.mac, b, this.type, deviceSetting, z, this.bufferVisible.getValue() == Boolean.TRUE).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
            }
        }).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                if (bool.booleanValue()) {
                    SettingModel.this.finishAnimate(C2668R.anim.left_in, C2668R.anim.right_out);
                    if (!z) {
                        RxBus.getDefault().post(new ActivityEvent(3, Byte.valueOf(deviceSetting.leafTemperatureC)));
                    }
                }
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) {
                th.printStackTrace();
            }
        }));
    }

    public void showEmptyDialog() {
        showFailDialog(Utils.getString(C2668R.string.tip_empty_name));
        if (this.deviceName.getValue() == null || TextUtils.isEmpty(this.deviceName.getValue().trim())) {
            this.deviceName.setValue(getOriginName((byte) 0));
        }
        if (this.port == 0) {
            return;
        }
        if (this.portName.getValue() == null || TextUtils.isEmpty(this.portName.getValue().trim())) {
            this.portName.setValue(getOriginName(this.port));
        }
    }
}
