package com.eternal.device.model;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.Build;
import android.text.TextUtils;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.CustomToastUtils;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.ChooseActivity;
import com.eternal.device.GuideActivity;
import com.eternal.device.HelpActivity;
import com.eternal.device.WiFiActivity;
import com.eternal.device.WiFiConnectActivity;
import com.eternal.device.WiFiListActivity;
import com.eternal.device.utils.wifimanager.IWifi;
import com.eternal.device.utils.wifimanager.IWifiManager;
import com.eternal.device.utils.wifimanager.OnWifiChangeListener;
import com.eternal.device.utils.wifimanager.OnWifiConnectListener;
import com.eternal.device.utils.wifimanager.OnWifiStateChangeListener;
import com.eternal.device.utils.wifimanager.State;
import com.eternal.device.utils.wifimanager.WifiManager;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.NetworkUtil;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.Single;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class WiFiModel extends BaseViewModel implements OnWifiChangeListener, OnWifiConnectListener, OnWifiStateChangeListener {
    public static final String PASSWORD_DEVICE_WIFI = "Aci1215+-*/";
    public static final String PREFIX_WIFI_NAME = "WI-FI CONTROLLER 69";
    private boolean backThere;
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    public MutableLiveData<Boolean> connectIconVisible = new MutableLiveData<>();
    ConnectivityManager connectivityManager;
    public MutableLiveData<String> contentText = new MutableLiveData<>();
    ObservableEmitter<Boolean> delayEmitter;
    private byte deviceType;
    private boolean firstConnect;
    /* access modifiers changed from: private */
    public boolean isConnect;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public ItemBinding<ItemModel> itemBinding = ItemBinding.m1008of(C1909BR.Item, C1922R.layout.item_wifi_select);
    public ObservableList<ItemModel> items = new ObservableArrayList();
    IWifiManager manager;
    ConnectivityManager.NetworkCallback networkCallback;
    private int networkId = -1;
    public MutableLiveData<String> nextText = new MutableLiveData<>();
    public MutableLiveData<Boolean> nextVisible = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            WiFiModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiModel.this, "show dialog");
        }
    });
    public BindingCommand<Void> onDismiss = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiModel.this, "dismiss sheet dialog");
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (WiFiModel.this.confirmAble.getValue() == Boolean.TRUE) {
                ARouter.getInstance().build(RouterActivityPath.Device.PAGE_WIFI_CONNECT).withString(ActivityEvent.SSID, TextUtils.isEmpty(WiFiModel.this.wifiName) ? "" : WiFiModel.this.wifiName).withString(ActivityEvent.SOCKET_IP, WiFiModel.this.socketIp).withInt(ActivityEvent.SOCKET_PORT, WiFiModel.this.socketPort).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
            } else if (!WiFiModel.this.manager.isOpened()) {
                CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C1922R.string.err_not_enable_wifi));
            } else {
                WiFiModel.this.connectWifi();
            }
        }
    });
    public BindingCommand<Void> onRefresh = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
        }
    });
    public MutableLiveData<Boolean> ovalVisible = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String socketIp;
    /* access modifiers changed from: private */
    public int socketPort;
    private String ssid;
    public MutableLiveData<Integer> state = new MutableLiveData<>();
    public MutableLiveData<Boolean> stateVisible = new MutableLiveData<>();
    private Disposable timeDis;
    public MutableLiveData<String> titleText = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String wifiName;

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
    }

    public void onWifiChanged(List<IWifi> list) {
    }

    public WiFiModel(Application application) {
        super(application);
    }

    public void connectWifi() {
        if (!this.manager.isOpened()) {
            CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C1922R.string.err_not_enable_wifi));
        } else if (Build.VERSION.SDK_INT >= 29) {
            scanStart(0);
        } else {
            scanStart(3);
        }
    }

    private void requestNetwork() {
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        builder.addTransportType(1);
        builder.addCapability(14);
        builder.addCapability(13);
        builder.removeCapability(12);
        WifiNetworkSpecifier.Builder builder2 = new WifiNetworkSpecifier.Builder();
        builder2.setSsid(PREFIX_WIFI_NAME);
        builder2.setWpa2Passphrase(PASSWORD_DEVICE_WIFI);
        builder.setNetworkSpecifier(builder2.build());
        NetworkRequest build = builder.build();
        this.connectivityManager = (ConnectivityManager) AppManager.getAppManager().currentActivity().getApplicationContext().getSystemService("connectivity");
        C20763 r1 = new ConnectivityManager.NetworkCallback() {
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                if (networkCapabilities.getTransportInfo() instanceof WifiInfo) {
                    KLog.m65e("配网 可用的wifi网络");
                    WiFiModel.this.addSubscribe(Completable.create(new CompletableOnSubscribe() {
                        public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                            boolean unused = WiFiModel.this.isConnect = true;
                            WiFiModel.this.updateStateDelay();
                            completableEmitter.onComplete();
                        }
                    }).subscribeOn(AndroidSchedulers.mainThread()).subscribe());
                }
            }

            public void onAvailable(final Network network) {
                super.onAvailable(network);
                KLog.m65e("配网 可用的wifi网络");
                WiFiModel.this.addSubscribe(Completable.create(new CompletableOnSubscribe() {
                    public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                        WiFiModel.this.connectivityManager.bindProcessToNetwork(network);
                        boolean unused = WiFiModel.this.isConnect = true;
                        WiFiModel.this.updateStateDelay();
                        completableEmitter.onComplete();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).subscribe());
            }

            public void onUnavailable() {
                super.onUnavailable();
                WiFiModel.this.addSubscribe(Completable.create(new CompletableOnSubscribe() {
                    public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                        boolean unused = WiFiModel.this.isConnect = false;
                        WiFiModel.this.scanEnd(false);
                        WiFiModel.this.updateStateDelay();
                        completableEmitter.onComplete();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).subscribe());
                KLog.m65e("配网 断开连接了");
            }

            public void onLost(Network network) {
                super.onLost(network);
                KLog.m65e("配网 断开连接了");
                WiFiModel.this.addSubscribe(Completable.create(new CompletableOnSubscribe() {
                    public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                        boolean unused = WiFiModel.this.isConnect = false;
                        WiFiModel.this.updateStateDelay();
                        completableEmitter.onComplete();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).subscribe());
            }
        };
        this.networkCallback = r1;
        this.connectivityManager.requestNetwork(build, r1);
    }

    public void onStateChanged(State state2) {
        KLog.m65e("添加WiFi设备" + state2.name());
    }

    /* access modifiers changed from: private */
    public void scanStart(final int i) {
        long j;
        if (Build.VERSION.SDK_INT >= 29) {
            requestNetwork();
            j = 30;
        } else {
            j = 5;
            this.networkId = this.manager.connectEncryptWifi(PREFIX_WIFI_NAME, "[WPA2-PSK-CCMP][RSN-PSK-CCMP][WPA-PSK-CCMP][ESS]", PASSWORD_DEVICE_WIFI);
        }
        this.connectIconVisible.setValue(true);
        this.ovalVisible.setValue(false);
        this.stateVisible.setValue(false);
        this.titleText.setValue(Utils.getString(C1922R.string.tip_device_connecting));
        this.contentText.setValue("Make sure your controller and mobile device are\nwithin 5 feet of distance.");
        this.nextVisible.setValue(false);
        Disposable disposable = this.timeDis;
        if (disposable != null && !disposable.isDisposed()) {
            this.timeDis.dispose();
        }
        Disposable subscribe = Single.timer(j, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            public void accept(Long l) throws Exception {
                if (WiFiModel.this.confirmAble.getValue() != Boolean.TRUE) {
                    int i = i;
                    if (i > 0) {
                        WiFiModel.this.scanStart(i - 1);
                        return;
                    }
                    boolean unused = WiFiModel.this.isConnect = false;
                    WiFiModel.this.scanEnd(false);
                    WiFiModel.this.updateStateDelay();
                }
            }
        });
        this.timeDis = subscribe;
        addSubscribe(subscribe);
    }

    /* access modifiers changed from: private */
    public void scanEnd(boolean z) {
        Disposable disposable = this.timeDis;
        if (disposable != null && !disposable.isDisposed()) {
            this.timeDis.dispose();
        }
        if (z) {
            this.state.setValue(Integer.valueOf(C1922R.mipmap.guide_success));
            this.connectIconVisible.setValue(false);
            this.ovalVisible.setValue(true);
            this.stateVisible.setValue(true);
            this.titleText.setValue("CONNECTED TO");
            this.contentText.setValue(PREFIX_WIFI_NAME);
            this.nextVisible.setValue(true);
            this.nextText.setValue(Utils.getString(C1922R.string.btn_next));
            return;
        }
        this.state.setValue(Integer.valueOf(C1922R.mipmap.guide_error));
        this.connectIconVisible.setValue(false);
        this.ovalVisible.setValue(true);
        this.stateVisible.setValue(true);
        this.titleText.setValue("NO DEVICE FOUND");
        this.contentText.setValue("Check for the flashing Wi-Fi icon and move \nyour mobile device closer to your controller.");
        this.nextVisible.setValue(true);
        this.nextText.setValue(Utils.getString(C1922R.string.btn_try_again));
    }

    public void setBackThere(boolean z) {
        this.backThere = z;
        updateState();
    }

    private void showNetworkActivity() {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.wifi.WifiSettings"));
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Intent intent2 = new Intent("android.settings.WIFI_SETTINGS");
            intent2.addFlags(268435456);
            startActivity(intent2);
        }
    }

    public void pushToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1922R.anim.left_in, C1922R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) GuideActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) ChooseActivity.class);
                WiFiModel.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateState() {
        String connectedSSID = this.manager.getConnectedSSID();
        this.ssid = connectedSSID;
        if (!TextUtils.isEmpty(connectedSSID)) {
            WifiInfo connectedInfo = this.manager.getConnectedInfo();
            if (!this.ssid.toLowerCase().startsWith(PREFIX_WIFI_NAME.toLowerCase()) && connectedInfo != null && NetworkUtil.is24GHz(connectedInfo.getFrequency())) {
                this.wifiName = this.ssid;
            }
        } else if (this.networkId >= 0) {
            WifiInfo connectedInfo2 = this.manager.getConnectedInfo();
            if (connectedInfo2 == null || connectedInfo2.getNetworkId() != this.networkId) {
                this.isConnect = false;
            } else {
                this.isConnect = true;
            }
        }
        KLog.m65e("isConnect：" + this.isConnect);
        if (this.isConnect) {
            this.confirmAble.setValue(true);
            this.onDismiss.execute();
            scanEnd(true);
            return;
        }
        if (this.confirmAble.getValue() == Boolean.TRUE) {
            scanEnd(false);
        }
        this.confirmAble.setValue(false);
        if (AppManager.getAppManager().currentActivity().getClass().equals(WiFiActivity.class)) {
            return;
        }
        if (this.backThere) {
            AppManager.getAppManager().finishActivity((Class<?>) HelpActivity.class);
            AppManager.getAppManager().finishActivity((Class<?>) WiFiListActivity.class);
            AppManager.getAppManager().finishActivity((Class<?>) WiFiConnectActivity.class);
            setTransition(C1922R.anim.left_in, C1922R.anim.right_out);
            return;
        }
        unregisterNetwork(true);
    }

    public void updateStateDelay() {
        if (Build.VERSION.SDK_INT >= 29) {
            updateState();
            return;
        }
        ObservableEmitter<Boolean> observableEmitter = this.delayEmitter;
        if (observableEmitter != null) {
            observableEmitter.onNext(true);
        } else {
            updateState();
        }
    }

    public void onConnectChanged(boolean z) {
        KLog.m65e("添加WiFi设备 status:" + z);
        String connectedSSID = this.manager.getConnectedSSID();
        this.ssid = connectedSSID;
        if (!TextUtils.isEmpty(connectedSSID) && Build.VERSION.SDK_INT < 29) {
            this.isConnect = this.ssid.toLowerCase().startsWith(PREFIX_WIFI_NAME.toLowerCase()) && z;
        }
        updateStateDelay();
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= 29) {
            connectWifi();
        } else {
            addSubscribe(new RxPermissions((FragmentActivity) AppManager.getAppManager().currentActivity()).request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").toList().subscribe(new Consumer<List<Boolean>>() {
                public void accept(List<Boolean> list) throws Exception {
                    if (!list.get(0).booleanValue()) {
                        CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C2663R.string.permission_reject_location));
                    } else {
                        WiFiModel.this.connectWifi();
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                    KLog.m65e(th);
                }
            }));
        }
    }

    public void init(String str, int i) {
        this.socketIp = str;
        this.socketPort = i;
        this.backThere = true;
        IWifiManager create = WifiManager.create(AppManager.getAppManager().currentActivity());
        this.manager = create;
        create.setOnWifiChangeListener(this);
        this.manager.setOnWifiConnectListener(this);
        this.manager.setOnWifiStateChangeListener(this);
        initDelayObservable();
        connectWifi();
    }

    private void initDelayObservable() {
        addSubscribe(Observable.create(new ObservableOnSubscribe<Boolean>() {
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                WiFiModel.this.delayEmitter = observableEmitter;
            }
        }).debounce(4, TimeUnit.SECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                WiFiModel.this.updateState();
            }
        }));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (!this.manager.isOpened()) {
            CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C1922R.string.err_not_enable_wifi));
            scanEnd(false);
        }
    }

    public void destroy() {
        KLog.m65e("destroy");
        unregisterNetwork(true);
        IWifiManager iWifiManager = this.manager;
        if (iWifiManager != null) {
            iWifiManager.destroy();
        }
        Disposable disposable = this.timeDis;
        if (disposable != null && !disposable.isDisposed()) {
            this.timeDis.dispose();
        }
    }

    public void unregisterNetwork(boolean z) {
        ConnectivityManager.NetworkCallback networkCallback2;
        KLog.m65e("添加WiFi设备 解绑网络");
        if (Build.VERSION.SDK_INT >= 29) {
            ConnectivityManager connectivityManager2 = this.connectivityManager;
            if (connectivityManager2 != null) {
                connectivityManager2.bindProcessToNetwork((Network) null);
                if (z && (networkCallback2 = this.networkCallback) != null) {
                    this.connectivityManager.unregisterNetworkCallback(networkCallback2);
                    return;
                }
                return;
            }
            return;
        }
        int i = this.networkId;
        if (i >= 0) {
            this.manager.removeWifi(i);
        }
    }
}
