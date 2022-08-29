package com.eternal.device.model;

import android.animation.ValueAnimator;
import android.app.Application;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.DeviceIndex;
import com.eternal.base.concat.NetServe;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.ble.BleServer;
import com.eternal.base.exception.NotCloseScanException;
import com.eternal.base.exception.NotEnableBluetoothException;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.CommonObserver;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.CustomToastUtils;
import com.eternal.device.C1922R;
import com.eternal.device.ChooseActivity;
import com.eternal.device.GuideActivity;
import com.eternal.device.HelpActivity;
import com.eternal.device.PromptActivity;
import com.eternal.device.WiFiConnectActivity;
import com.eternal.device.utils.wifimanager.IWifiManager;
import com.eternal.device.utils.wifimanager.WifiManager;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.NetworkUtil;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.List;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.CompositeDisposable;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class AddModel extends BaseViewModel {
    private ValueAnimator animator;
    /* access modifiers changed from: private */
    public boolean backThere;
    public MutableLiveData<Boolean> backVisible = new MutableLiveData<>();
    public MutableLiveData<String> btnText = new MutableLiveData<>();
    public MutableLiveData<Boolean> btnVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> cancelVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> connectBleVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> contentIconVisible = new MutableLiveData<>();
    public MutableLiveData<String> contentText = new MutableLiveData<>();
    public MutableLiveData<Boolean> contentVisible = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public byte deviceType;
    private int index;
    /* access modifiers changed from: private */
    public boolean isConnected;
    /* access modifiers changed from: private */
    public boolean isSaved;
    private Disposable loginDis;
    /* access modifiers changed from: private */
    public String mac;
    public MutableLiveData<Boolean> maxVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> minVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> normalVisible = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AddModel.this.closeScan();
            AddModel.this.repository.removeConnectConsumer();
            AddModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(AddModel.this, "show dialog");
        }
    });
    public BindingCommand<Void> onConfirm = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!AddModel.this.isConnected) {
                AddModel.this.onBack.execute();
            } else if (AddModel.this.deviceType != 11 || AddModel.this.isSaved) {
                AddModel.this.pushToMain();
            } else if (!TextUtils.isEmpty(AddModel.this.mac)) {
                if (TextUtils.isEmpty(UserCache.getInstance().getToken())) {
                    AddModel.this.pushToLoginPromptPage();
                } else if (!TextUtils.isEmpty(AddModel.this.socketIp)) {
                    AddModel.this.pushToWiFiConnectPage();
                } else {
                    AddModel.this.querySocketIp(true);
                }
            }
        }
    });
    public BindingCommand<Void> onConnectBle = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(AddModel.this, "show connect dialog");
        }
    });
    private Disposable permission;
    /* access modifiers changed from: private */
    public DeviceRepository repository;
    /* access modifiers changed from: private */
    public CompositeDisposable scan;
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String socketIp;
    /* access modifiers changed from: private */
    public int socketPort;
    public MutableLiveData<Integer> stateIcon = new MutableLiveData<>();
    public MutableLiveData<String> titleText = new MutableLiveData<>();
    private String typeName;
    private IWifiManager wifiManager;

    public AddModel(Application application) {
        super(application);
        this.minVisible.setValue(false);
        this.normalVisible.setValue(false);
        this.maxVisible.setValue(false);
        this.index = -1;
        this.isConnected = false;
        this.cancelVisible.setValue(true);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 4});
        this.animator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (intValue == 0) {
                    AddModel.this.maxVisible.setValue(false);
                    AddModel.this.normalVisible.setValue(false);
                    AddModel.this.minVisible.setValue(false);
                } else if (intValue == 1) {
                    AddModel.this.minVisible.setValue(true);
                } else if (intValue == 2) {
                    AddModel.this.normalVisible.setValue(true);
                } else {
                    AddModel.this.maxVisible.setValue(true);
                }
            }
        });
        this.animator.setRepeatCount(-1);
        this.animator.setDuration(2000);
    }

    public void saveDevice() {
        addSubscribe(this.repository.saveDevice(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceIndex>() {
            public void accept(DeviceIndex deviceIndex) throws Exception {
                AddModel.this.onSuccess(deviceIndex);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                KLog.m65e(th);
                AddModel.this.onError(th);
            }
        }));
    }

    public void querySocketIp(final boolean z) {
        if (z) {
            this.showLoading.setValue(true);
        }
        ApiHelper.getDeviceApi().getSockerIp().subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<BaseData<NetServe>>() {
            /* access modifiers changed from: protected */
            public void onError(String str) {
                if (z) {
                    AddModel.this.showLoading.setValue(false);
                    AddModel.this.showFailDialog(str);
                }
            }

            /* access modifiers changed from: protected */
            public void onSuccess(BaseData<NetServe> baseData) {
                if (z) {
                    AddModel.this.showLoading.setValue(false);
                }
                if (baseData.getCode() == 200 && baseData.getData() != null && !TextUtils.isEmpty(baseData.getData().socketIp)) {
                    String unused = AddModel.this.socketIp = baseData.getData().socketIp;
                    int unused2 = AddModel.this.socketPort = baseData.getData().socketPort;
                    if (z) {
                        AddModel.this.pushToWiFiConnectPage();
                    }
                } else if (z) {
                    AddModel.this.showFailDialog(baseData.getMsg());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void pushToWiFiConnectPage() {
        WifiInfo connectedInfo = this.wifiManager.getConnectedInfo();
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_WIFI_CONNECT).withString(ActivityEvent.SSID, (connectedInfo == null || !NetworkUtil.is24GHz(connectedInfo.getFrequency())) ? "" : this.wifiManager.getConnectedSSID()).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_TYPE_NAME, this.typeName).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withString(ActivityEvent.SOCKET_IP, this.socketIp).withInt(ActivityEvent.SOCKET_PORT, this.socketPort).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void pushToMain() {
        closeScan();
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1922R.anim.left_in, C1922R.anim.right_out).withInt(ActivityEvent.DEVICE_INDEX, this.index).withAction(ActivityEvent.ACTION_ADD_DEVICE).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AddModel.this.finish();
                AppManager.getAppManager().finishActivity((Class<?>) GuideActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) ChooseActivity.class);
            }
        });
    }

    public void init(DeviceRepository deviceRepository, byte b) {
        this.repository = deviceRepository;
        this.deviceType = b;
        scanAndConnect();
        if (b == 11) {
            querySocketIp(false);
            this.backThere = true;
            registerEvent();
            this.wifiManager = WifiManager.create(AppManager.getAppManager().currentActivity());
        }
    }

    private void registerEvent() {
        Messenger.getDefault().register((Object) this, (Object) ActivityEvent.BACK_WIFI_PAGE, Boolean.class, new BindingConsumer<Boolean>() {
            public void call(Boolean bool) {
                boolean unused = AddModel.this.backThere = bool.booleanValue();
                AddModel.this.updateConnectState();
            }
        });
        addSubscribe(RxBus.getDefault().toObservable(BluetoothEvent.class).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BluetoothEvent>() {
            public void accept(BluetoothEvent bluetoothEvent) throws Exception {
                if (!TextUtils.isEmpty(AddModel.this.mac) && AddModel.this.mac.equals(bluetoothEvent.mac) && bluetoothEvent.what == 0) {
                    AddModel.this.updateConnectState();
                }
            }
        }));
    }

    /* access modifiers changed from: package-private */
    public void updateConnectState() {
        if (!TextUtils.isEmpty(this.mac) && !this.repository.isConnect(this.mac) && this.backThere) {
            AppManager.getAppManager().finishActivity((Class<?>) HelpActivity.class);
            AppManager.getAppManager().finishActivity((Class<?>) WiFiConnectActivity.class);
            finish();
            setTransition(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    }

    /* access modifiers changed from: private */
    public void setCallback() {
        this.repository.setConnectConsumer(new androidx.core.util.Consumer<Disposable>() {
            public void accept(Disposable disposable) {
                KLog.m65e("AddModel 开始添加设备");
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    AddModel.this.scanStart();
                } else {
                    Completable.create(new CompletableOnSubscribe() {
                        public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                            AddModel.this.scanStart();
                        }
                    }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
                }
            }
        }, new androidx.core.util.Consumer<DeviceIndex>() {
            public void accept(final DeviceIndex deviceIndex) {
                KLog.m65e("AddModel 添加设备成功");
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    AddModel.this.onSuccess(deviceIndex);
                } else {
                    Completable.create(new CompletableOnSubscribe() {
                        public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                            AddModel.this.onSuccess(deviceIndex);
                            completableEmitter.onComplete();
                        }
                    }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
                }
                AddModel.this.repository.removeConnectConsumer();
            }
        }, new androidx.core.util.Consumer<Throwable>() {
            public void accept(Throwable th) {
                KLog.m65e("AddModel 添加设备失败");
                AddModel.this.onError(th);
                AddModel.this.repository.removeConnectConsumer();
            }
        });
    }

    /* access modifiers changed from: private */
    public void onSuccess(DeviceIndex deviceIndex) {
        this.typeName = deviceIndex.typeName;
        if (this.deviceType == 11) {
            if (this.index == -1) {
                this.isSaved = deviceIndex.isSaved;
                this.index = deviceIndex.index;
                this.isConnected = true;
                this.mac = deviceIndex.mac;
                this.cancelVisible.setValue(Boolean.valueOf(!this.isSaved));
                this.connectBleVisible.setValue(Boolean.valueOf(!this.isSaved));
                this.backVisible.setValue(Boolean.valueOf(!this.isSaved));
                if (this.isSaved) {
                    scanEnd(true, Utils.getString(C1922R.string.tip_connect_success, deviceIndex.typeName, deviceIndex.name));
                    return;
                }
                scanEnd(true, deviceIndex.typeName);
                return;
            }
            scanEnd(false, Utils.getString(C1922R.string.err_connect_fail, deviceIndex.typeName));
            this.cancelVisible.setValue(false);
            this.connectBleVisible.setValue(false);
            this.backVisible.setValue(false);
        } else if (deviceIndex.index == -1) {
            this.index = deviceIndex.index;
            this.isConnected = true;
            this.cancelVisible.setValue(false);
            scanEnd(true, Utils.getString(C1922R.string.tip_connect_success, deviceIndex.typeName, deviceIndex.name));
        } else {
            RxBus.getDefault().post(new BluetoothEvent((byte) 12, deviceIndex.mac));
            scanEnd(false, Utils.getString(C1922R.string.err_connect_fail, deviceIndex.typeName));
        }
    }

    private void scanAndConnect() {
        String[] strArr;
        if (AppUtils.isNeedTurnOnLocationService(Utils.getApp())) {
            scanEnd(false, "Before you continue, AC Infinity needs to access to your location. Turn on Location Service in your device settings.");
            return;
        }
        if (Build.VERSION.SDK_INT >= 31) {
            strArr = new String[]{"android.permission.BLUETOOTH_CONNECT", "android.permission.BLUETOOTH_SCAN"};
        } else {
            strArr = new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        }
        this.permission = new RxPermissions((FragmentActivity) AppManager.getAppManager().currentActivity()).request(strArr).toList().subscribe(new Consumer<List<Boolean>>() {
            public void accept(List<Boolean> list) throws Exception {
                boolean z = false;
                if (list.get(0).booleanValue()) {
                    KLog.m65e("授权成功");
                    AddModel.this.setCallback();
                    AddModel addModel = AddModel.this;
                    DeviceRepository access$300 = addModel.repository;
                    byte access$500 = AddModel.this.deviceType;
                    if (AddModel.this.deviceType != 11) {
                        z = true;
                    }
                    CompositeDisposable unused = addModel.scan = access$300.scanConnectAndSave(access$500, z);
                    return;
                }
                CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(Build.VERSION.SDK_INT >= 31 ? C2663R.string.permission_reject_device : C2663R.string.permission_reject_location));
                AddModel.this.scanEnd(false, Utils.getString(C1922R.string.err_no_scanned_device));
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                KLog.m65e("AddModel 添加设备失败2");
                AddModel.this.onError(th);
            }
        });
    }

    /* access modifiers changed from: private */
    public void onError(final Throwable th) {
        th.printStackTrace();
        addSubscribe(Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                if (th instanceof NotCloseScanException) {
                    BleServer.getInstance().disable();
                    do {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                        }
                    } while (BleServer.getInstance().isEnable());
                    BleServer.getInstance().enable();
                }
                completableEmitter.onComplete();
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Action) new Action() {
            public void run() throws Exception {
                if (th instanceof NotEnableBluetoothException) {
                    AddModel.this.scanEnd(false, Utils.getString(C1922R.string.err_not_enable_blue));
                } else {
                    AddModel.this.scanEnd(false, Utils.getString(C1922R.string.err_no_scanned_device));
                }
                KLog.m65e(th);
            }
        }));
    }

    /* access modifiers changed from: private */
    public void scanStart() {
        if (!this.animator.isStarted()) {
            this.btnVisible.setValue(false);
            this.contentVisible.setValue(false);
            if (this.deviceType == 11) {
                this.titleText.setValue(Utils.getString(C1922R.string.tip_device_searching));
            } else {
                this.titleText.setValue(Utils.getString(C1922R.string.tip_device_connecting));
            }
            this.stateIcon.setValue(Integer.valueOf(C1922R.mipmap.bluetooth_icon_white));
            this.animator.start();
        }
    }

    /* access modifiers changed from: private */
    public void scanEnd(boolean z, String str) {
        this.animator.end();
        if (!z) {
            this.stateIcon.setValue(Integer.valueOf(C1922R.mipmap.terms_of_use));
            this.contentVisible.setValue(true);
            this.contentText.setValue("If you are pairing another smart device, \nmove your mobile device closer.");
            this.btnText.setValue("OK");
            this.contentIconVisible.setValue(false);
            this.titleText.setValue(str);
        } else if (this.deviceType != 11 || this.isSaved) {
            this.stateIcon.setValue(Integer.valueOf(C1922R.mipmap.success));
            this.contentVisible.setValue(false);
            this.btnText.setValue("DONE");
            this.contentIconVisible.setValue(false);
            this.titleText.setValue(str);
        } else {
            this.stateIcon.setValue(Integer.valueOf(C1922R.mipmap.controller_69));
            this.titleText.setValue("FOUND");
            this.contentVisible.setValue(true);
            this.contentText.setValue(str);
            this.contentIconVisible.setValue(true);
            this.btnText.setValue("CONNECT TO WI-FI");
        }
        this.btnVisible.setValue(true);
        this.maxVisible.setValue(true);
        this.normalVisible.setValue(true);
        this.minVisible.setValue(true);
    }

    /* access modifiers changed from: private */
    public void pushToLoginPromptPage() {
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_PROMPT).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        registerEventBus();
    }

    public void registerEventBus() {
        Disposable disposable = this.loginDis;
        if (disposable == null || disposable.isDisposed()) {
            this.loginDis = RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
                public void accept(ActivityEvent activityEvent) {
                    if (activityEvent.what == 20) {
                        AppManager.getAppManager().finishActivity((Class<?>) PromptActivity.class);
                    }
                }
            });
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        Disposable disposable = this.loginDis;
        if (disposable != null && !disposable.isDisposed()) {
            this.loginDis.dispose();
        }
    }

    /* access modifiers changed from: private */
    public void closeScan() {
        CompositeDisposable compositeDisposable = this.scan;
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            KLog.m65e("closeScan");
            this.scan.clear();
            this.scan = null;
        }
        if (!this.isConnected) {
            this.repository.closeScan();
        }
    }

    public void onDestroy() {
        if (this.deviceType == 11 && !TextUtils.isEmpty(this.mac) && this.repository.isConnect(this.mac) && !this.isSaved) {
            this.repository.disconnectBle(this.mac).subscribeOn(Schedulers.m983io()).subscribe();
        }
        IWifiManager iWifiManager = this.wifiManager;
        if (iWifiManager != null) {
            iWifiManager.destroy();
        }
        CompositeDisposable compositeDisposable = this.scan;
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            KLog.m65e("closeScan");
            this.scan.clear();
        }
        Disposable disposable = this.permission;
        if (disposable != null && !disposable.isDisposed()) {
            this.permission.isDisposed();
        }
        ValueAnimator valueAnimator = this.animator;
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
        }
        Messenger.getDefault().unregister(this);
    }
}
