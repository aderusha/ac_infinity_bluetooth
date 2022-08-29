package com.eternal.device.model;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableList;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.afollestad.materialdialogs.MaterialDialog;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.easysocket.EasySocket;
import com.easysocket.config.EasySocketOptions;
import com.easysocket.connection.reconnect.AbsReconnection;
import com.easysocket.entity.OriginReadData;
import com.easysocket.entity.SocketAddress;
import com.easysocket.exception.NotNullException;
import com.easysocket.interfaces.conn.ISocketActionListener;
import com.easysocket.interfaces.conn.SocketActionListener;
import com.eternal.base.TipDialog;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.NetServe;
import com.eternal.base.concat.WiFiDevice;
import com.eternal.base.config.AppUrlConfig;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.observer.CommonObserver;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.protocol.EFamilialResolution;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.ByteUtils;
import com.eternal.base.utils.CustomToastUtils;
import com.eternal.base.utils.TimeUtil;
import com.eternal.device.C1909BR;
import com.eternal.device.C1922R;
import com.eternal.device.ChooseActivity;
import com.eternal.device.GuideActivity;
import com.eternal.device.WiFiActivity;
import com.eternal.device.WiFiListActivity;
import com.eternal.device.model.ItemModel;
import com.eternal.device.utils.wifimanager.IWifi;
import com.eternal.device.utils.wifimanager.IWifiManager;
import com.eternal.device.utils.wifimanager.OnWifiChangeListener;
import com.eternal.device.utils.wifimanager.WifiManager;
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
import com.eternal.framework.utils.NetworkUtil;
import com.eternal.framework.utils.SPUtils;
import com.eternal.framework.utils.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.bugly.crashreport.BuglyLog;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class WiFiSettingModel extends BaseViewModel implements OnWifiChangeListener {
    private final String SP_KEY_SSID = "sp key ssid";
    /* access modifiers changed from: private */
    public String SSID;
    /* access modifiers changed from: private */
    public final String TAG = "WiFiSettingModel";
    public Observable.OnPropertyChangedCallback callback;
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public byte connectType;
    /* access modifiers changed from: private */
    public String devId;
    /* access modifiers changed from: private */
    public byte deviceType;
    /* access modifiers changed from: private */
    public String inetAddress;
    /* access modifiers changed from: private */

    /* renamed from: ip */
    public byte[] f173ip;
    /* access modifiers changed from: private */
    public boolean isCancel;
    /* access modifiers changed from: private */
    public boolean isConnected;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSavePwd = new MutableLiveData<>();
    public ItemBinding<ItemModel> itemBinding = ItemBinding.m1008of(C1909BR.item, C1922R.layout.item_wifi);
    public ObservableList<ItemModel> items = new ObservableArrayList();
    /* access modifiers changed from: private */
    public MaterialDialog mDialog;
    public String mac;
    IWifiManager manager;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            WiFiSettingModel.this.cancel();
            WiFiSettingModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiSettingModel.this, "show dialog");
        }
    });
    public BindingCommand<Void> onDismiss = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiSettingModel.this, "dismiss sheet dialog");
        }
    });
    public BindingCommand<Void> onHelp = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Device.PAGE_HELP).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).withByte("page type", (byte) 1).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!RepositoryInjection.providerDeviceRepository().isConnect(WiFiSettingModel.this.mac)) {
                WiFiSettingModel wiFiSettingModel = WiFiSettingModel.this;
                byte access$100 = wiFiSettingModel.connectType;
                wiFiSettingModel.showFailDialog(Utils.getString(C1922R.string.tip_wifi_disconnect));
                return;
            }
            if (WiFiSettingModel.this.deviceType != 11) {
                WiFiSettingModel.this.showLoading.setValue(true);
                boolean unused = WiFiSettingModel.this.sending = true;
                int unused2 = WiFiSettingModel.this.retryConnectCount = 0;
                if (WiFiSettingModel.this.isConnected) {
                    WiFiSettingModel.this.sendConfigData();
                } else {
                    WiFiSettingModel.this.reconnect(false);
                }
            } else if (WiFiSettingModel.this.connectType == 0) {
                WiFiSettingModel.this.querySocketIp();
            } else {
                WiFiSettingModel.this.chengNetWorkSetting();
            }
            SPUtils.getInstance().put("sp key ssid", WiFiSettingModel.this.SSID);
            if (WiFiSettingModel.this.isSavePwd.getValue() != Boolean.TRUE || WiFiSettingModel.this.passwordText.getValue() == null) {
                SPUtils.getInstance().put(WiFiSettingModel.this.SSID, "");
            } else {
                SPUtils.getInstance().put(WiFiSettingModel.this.SSID, WiFiSettingModel.this.passwordText.getValue());
            }
        }
    });
    public BindingCommand<Void> onPwdShow = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            WiFiSettingModel.this.textVisiblePassword.setValue(Boolean.valueOf(!WiFiSettingModel.this.textVisiblePassword.getValue().booleanValue()));
        }
    });
    public BindingCommand<Void> onRefresh = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            WiFiSettingModel.this.addSubscribe(Single.timer(5, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                public void accept(Long l) throws Exception {
                    WiFiSettingModel.this.isLoading.setValue(false);
                }
            }));
            if (AppUtils.isLocationEnabled(Utils.getApp())) {
                WiFiSettingModel.this.scanWifi();
            }
        }
    });
    public BindingCommand<Void> onSavePwd = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            MutableLiveData<Boolean> mutableLiveData = WiFiSettingModel.this.isSavePwd;
            boolean z = true;
            if (WiFiSettingModel.this.isSavePwd.getValue() == null) {
                Boolean bool = Boolean.TRUE;
            } else if (WiFiSettingModel.this.isSavePwd.getValue().booleanValue()) {
                z = false;
            }
            mutableLiveData.setValue(Boolean.valueOf(z));
        }
    });
    public BindingCommand<Void> onSetting = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiSettingModel.this, "show sheet dialog");
        }
    });
    public ObservableBoolean open = new ObservableBoolean();
    public MutableLiveData<String> passwordText = new MutableLiveData<>();
    public MutableLiveData<Integer> pwdColor = new MutableLiveData<>();
    public MutableLiveData<String> pwdErrText = new MutableLiveData<>();
    public MutableLiveData<Boolean> pwdErrVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> pwdLineColor = new MutableLiveData<>();
    public MutableLiveData<String> pwdShowText = new MutableLiveData<>();
    public BindingCommand<String> pwdTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            WiFiSettingModel.this.onTextChanged(str);
        }
    });
    private Disposable reconnect;
    /* access modifiers changed from: private */
    public Handler refreshHandler = new Handler();
    /* access modifiers changed from: private */
    public Runnable refreshRunnable = new Runnable() {
        public void run() {
            WiFiSettingModel.this.scanWifi();
            WiFiSettingModel.this.refreshHandler.postDelayed(WiFiSettingModel.this.refreshRunnable, 30000);
        }
    };
    /* access modifiers changed from: private */
    public DeviceRepository repository;
    /* access modifiers changed from: private */
    public int retryConnectCount;
    /* access modifiers changed from: private */
    public boolean sending;
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    private ISocketActionListener socketActionListener = new SocketActionListener() {
        public void onSocketConnSuccess(SocketAddress socketAddress) {
            KLog.m62d("端口" + socketAddress.getPort() + "---> 连接成功");
            boolean unused = WiFiSettingModel.this.isConnected = true;
            if (WiFiSettingModel.this.showLoading.getValue() != null && WiFiSettingModel.this.showLoading.getValue().booleanValue()) {
                WiFiSettingModel.this.sendConfigData();
            }
        }

        public void onSocketConnFail(SocketAddress socketAddress, boolean z) {
            boolean unused = WiFiSettingModel.this.isConnected = false;
            KLog.m65e(socketAddress.getPort() + "端口---> socket断开连接，是否需要重连：" + z);
            WiFiSettingModel.this.reconnect(true);
        }

        public void onSocketDisconnect(SocketAddress socketAddress, boolean z) {
            boolean unused = WiFiSettingModel.this.isConnected = false;
            KLog.m65e(socketAddress.getPort() + "端口---> socket断开连接，是否需要重连：" + z);
            WiFiSettingModel.this.reconnect(true);
        }

        public void onSocketResponse(SocketAddress socketAddress, String str) {
            super.onSocketResponse(socketAddress, str);
            KLog.m62d(socketAddress.getPort() + "端口SocketActionListener收到数据-->" + str);
        }

        public void onSocketResponse(SocketAddress socketAddress, final OriginReadData originReadData) {
            super.onSocketResponse(socketAddress, originReadData);
            Completable.create(new CompletableOnSubscribe() {
                public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                    byte[] originDataBytes = originReadData.getOriginDataBytes();
                    String access$900 = WiFiSettingModel.this.TAG;
                    KLog.m66e(access$900, "配网 接收数据： " + ByteUtils.bytes2HexString(originDataBytes) + ", sending: " + WiFiSettingModel.this.sending);
                    if (WiFiSettingModel.this.sending && originDataBytes[0] == -91 && originDataBytes[4] == 0 && originDataBytes[5] == 1 && originDataBytes[10] == 0) {
                        WiFiSettingModel.this.showLoading.setValue(false);
                        boolean unused = WiFiSettingModel.this.sending = false;
                        WiFiDevice parseE_WifiDeviceInfo = EFamilialResolution.parseE_WifiDeviceInfo(originDataBytes);
                        if (parseE_WifiDeviceInfo == null) {
                            WiFiSettingModel.this.showFailDialog(Utils.getString(C1922R.string.response_time_out));
                            return;
                        }
                        byte[] aPConfig = EFamilialResolution.setAPConfig(WiFiSettingModel.this.SSID, WiFiSettingModel.this.passwordText.getValue(), WiFiSettingModel.this.f173ip, WiFiSettingModel.this.socketPort, 2);
                        KLog.m65e("配网 发送数据2： " + ByteUtils.bytes2HexString(aPConfig));
                        EasySocket.getInstance().upMessage(aPConfig);
                        WiFiSettingModel.this.mac = parseE_WifiDeviceInfo.mac;
                        WiFiSettingModel.this.typeName = parseE_WifiDeviceInfo.typeName;
                    } else if (originDataBytes[0] != -91 || originDataBytes[4] != 0 || originDataBytes[5] != 2 || originDataBytes[10] != 8) {
                        WiFiSettingModel.this.showLoading.setValue(false);
                        WiFiSettingModel.this.showFailDialog(Utils.getString(C1922R.string.response_time_out));
                    } else if (TextUtils.isEmpty(WiFiSettingModel.this.typeName) || TextUtils.isEmpty(WiFiSettingModel.this.mac)) {
                        WiFiSettingModel.this.showLoading.setValue(false);
                        WiFiSettingModel.this.showFailDialog(Utils.getString(C1922R.string.response_time_out));
                    } else if (EFamilialResolution.checkResult(originDataBytes)) {
                        Messenger.getDefault().send(Boolean.FALSE, ActivityEvent.BACK_WIFI_PAGE);
                        WiFiSettingModel.this.pushToAddPage(WiFiSettingModel.this.mac, WiFiSettingModel.this.typeName);
                    } else {
                        WiFiSettingModel.this.showLoading.setValue(false);
                        MaterialDialog unused2 = WiFiSettingModel.this.mDialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), Utils.getString(C1922R.string.err_config_net_title), Utils.getString(C1922R.string.err_config_net_content), (String) null, Utils.getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null);
                    }
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
            KLog.m62d(socketAddress.getPort() + "端口SocketActionListener收到数据-->" + ByteUtils.bytes2HexString(originReadData.getOriginDataBytes()));
        }
    };
    /* access modifiers changed from: private */
    public String socketIp;
    /* access modifiers changed from: private */
    public int socketPort;
    public MutableLiveData<Boolean> textVisiblePassword = new MutableLiveData<>();
    private Disposable timeRefresh;
    public MutableLiveData<String> timeText = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeVisible = new MutableLiveData<>();
    public String typeName;

    static /* synthetic */ int access$408(WiFiSettingModel wiFiSettingModel) {
        int i = wiFiSettingModel.retryConnectCount;
        wiFiSettingModel.retryConnectCount = i + 1;
        return i;
    }

    /* access modifiers changed from: private */
    public void onTextChanged(String str) {
        boolean z = false;
        boolean z2 = !TextUtils.isEmpty(str) && str.length() >= 8 && str.length() <= 63;
        if (z2 && this.pwdErrVisible.getValue() == Boolean.TRUE) {
            this.pwdErrVisible.setValue(Boolean.FALSE);
            this.pwdLineColor.setValue(this.pwdColor.getValue());
        }
        boolean z3 = !TextUtils.isEmpty(this.SSID);
        MutableLiveData<Boolean> mutableLiveData = this.confirmAble;
        if (z2 && z3) {
            z = true;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
    }

    public WiFiSettingModel(Application application) {
        super(application);
    }

    /* access modifiers changed from: private */
    public void pushToAddPage(String str, String str2) {
        ARouter.getInstance().build(RouterActivityPath.Device.PAGE_ADD_WIFI).withString(ActivityEvent.DEVICE_MAC, str).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withString(ActivityEvent.DEVICE_TYPE_NAME, str2).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    public void pushToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C1922R.anim.left_in, C1922R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) GuideActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) ChooseActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) WiFiActivity.class);
                AppManager.getAppManager().finishActivity((Class<?>) WiFiListActivity.class);
                WiFiSettingModel.this.finish();
            }
        });
    }

    public void init(String str, String str2, int i, byte b, String str3, String str4, byte b2, String str5) {
        this.repository = RepositoryInjection.providerDeviceRepository();
        this.mac = str3;
        this.devId = str5;
        this.typeName = str4;
        this.retryConnectCount = 3;
        this.connectType = b2;
        if (TextUtils.isEmpty(str)) {
            str = SPUtils.getInstance().getString("sp key ssid", "");
        }
        setSSID(str);
        this.inetAddress = str2 + ":" + i;
        this.socketPort = i;
        this.deviceType = b;
        this.pwdLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C1922R.C1923color.color_838383)));
        this.pwdColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C1922R.C1923color.color_838383)));
        this.confirmAble.setValue(Boolean.FALSE);
        this.showLoading.setValue(Boolean.FALSE);
        this.isSavePwd.setValue(Boolean.TRUE);
        boolean z = false;
        this.textVisiblePassword.setValue(false);
        initPermission();
        IWifiManager create = WifiManager.create(AppManager.getAppManager().currentActivity());
        this.manager = create;
        create.setOnWifiChangeListener(this);
        if (b != 11) {
            initEasySocket();
        }
        ObservableBoolean observableBoolean = this.open;
        if (b2 == 1) {
            z = true;
        }
        observableBoolean.set(z);
        C208911 r2 = new Observable.OnPropertyChangedCallback() {
            public void onPropertyChanged(Observable observable, int i) {
                if (!WiFiSettingModel.this.open.get() && WiFiSettingModel.this.connectType != 0) {
                    Messenger.getDefault().send(WiFiSettingModel.this, "show connect dialog");
                }
            }
        };
        this.callback = r2;
        this.open.addOnPropertyChangedCallback(r2);
        refreshTime();
    }

    private void refreshTime() {
        if (!TextUtils.isEmpty(this.mac)) {
            Disposable disposable = this.timeRefresh;
            if (disposable == null || disposable.isDisposed()) {
                Disposable subscribe = RepositoryInjection.providerDeviceRepository().getConnectInfo(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConnectInfo>() {
                    public void accept(ConnectInfo connectInfo) {
                        if (RepositoryInjection.providerDeviceRepository().isConnect(WiFiSettingModel.this.mac)) {
                            WiFiSettingModel.this.timeVisible.setValue(false);
                        } else {
                            WiFiSettingModel.this.timeVisible.setValue(true);
                            WiFiSettingModel.this.timeText.setValue(Utils.getString(C1922R.string.tip_last, ProtocolTransformer.getTime(connectInfo.connectTime)));
                        }
                        if (WiFiSettingModel.this.connectType != connectInfo.connectType) {
                            byte unused = WiFiSettingModel.this.connectType = connectInfo.connectType;
                            String unused2 = WiFiSettingModel.this.devId = connectInfo.deviceId;
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

    public void changeModeToBle() {
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            ApiHelper.getDeviceApi().unbindDev(token, this.devId, false).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                public void doOnSubscribe(Disposable disposable) {
                    RxBus.getDefault().post(new BluetoothEvent((byte) 11, WiFiSettingModel.this.mac, 1));
                    WiFiSettingModel.this.showDialog((String) null, disposable);
                }

                /* access modifiers changed from: protected */
                public String setTag() {
                    return WiFiSettingModel.this.TAG;
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    WiFiSettingModel.this.showFailDialog(str);
                    RxBus.getDefault().post(new BluetoothEvent((byte) 11, WiFiSettingModel.this.mac, 0));
                }

                /* access modifiers changed from: protected */
                public void onSuccess(Void voidR) {
                    byte unused = WiFiSettingModel.this.connectType = (byte) 0;
                    RepositoryInjection.providerDeviceRepository().setConnectType(WiFiSettingModel.this.mac, WiFiSettingModel.this.devId, WiFiSettingModel.this.connectType);
                    RxBus.getDefault().post(new BluetoothEvent((byte) 11, WiFiSettingModel.this.mac, 0));
                    WiFiSettingModel.this.showSuccessDialog(Utils.getString(C1922R.string.change_mode_success));
                }
            });
        }
    }

    public void setOpen(boolean z) {
        this.open.removeOnPropertyChangedCallback(this.callback);
        this.open.set(z);
        this.open.addOnPropertyChangedCallback(this.callback);
    }

    public void setSSID(String str) {
        this.SSID = str;
        this.name.setValue(str);
        this.passwordText.setValue(SPUtils.getInstance().getString(str, ""));
    }

    public void chengNetWorkSetting() {
        ApiHelper.getDeviceApi().netWorkSetting(this.devId, this.SSID, this.passwordText.getValue()).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
                WiFiSettingModel.this.showDialog((String) null, disposable);
            }

            /* access modifiers changed from: protected */
            public String setTag() {
                return WiFiSettingModel.this.TAG;
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                WiFiSettingModel.this.showFailDialog(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                byte unused = WiFiSettingModel.this.connectType = (byte) 1;
                if (WiFiSettingModel.this.deviceType == 11) {
                    WiFiSettingModel.this.repository.disconnectBle(WiFiSettingModel.this.mac).subscribeOn(Schedulers.m983io()).subscribe();
                }
                WiFiSettingModel.this.showSuccessDialog(Utils.getString(C1922R.string.change_mode_success));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void sendAPConfigData() {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect == null) {
            showFailDialog(Utils.getString(C1922R.string.tip_ble_disconnect));
        } else {
            addSubscribe(this.repository.setConfigSta(connect, this.SSID, this.passwordText.getValue(), this.inetAddress).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    KLog.m65e("配网回调" + bool);
                    if (bool.booleanValue()) {
                        boolean unused = WiFiSettingModel.this.isCancel = false;
                        WiFiSettingModel.this.fetch(20, 1, PathInterpolatorCompat.MAX_NUM_POINTS);
                        return;
                    }
                    WiFiSettingModel.this.dismissDialog();
                    WiFiSettingModel.this.cancel();
                    MaterialDialog unused2 = WiFiSettingModel.this.mDialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), Utils.getString(C1922R.string.err_config_net_title), Utils.getString(C1922R.string.err_config_net_content), (String) null, Utils.getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null);
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    WiFiSettingModel.this.dismissDialog();
                    WiFiSettingModel.this.cancel();
                    MaterialDialog unused = WiFiSettingModel.this.mDialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), Utils.getString(C1922R.string.err_config_net_title), Utils.getString(C1922R.string.err_config_net_content), (String) null, Utils.getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null);
                    th.printStackTrace();
                }
            }));
        }
    }

    public void querySocketIp() {
        ApiHelper.getDeviceApi().getSockerIp().subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CommonObserver<BaseData<NetServe>>() {
            public void doOnSubscribe(Disposable disposable) {
                WiFiSettingModel.this.showDialog(Utils.getString(C1922R.string.tip_connect_time), disposable);
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                WiFiSettingModel.this.showFailDialog(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(BaseData<NetServe> baseData) {
                if (baseData.getCode() != 200 || baseData.getData() == null || TextUtils.isEmpty(baseData.getData().socketIp)) {
                    WiFiSettingModel.this.showFailDialog(baseData.getMsg());
                    return;
                }
                String unused = WiFiSettingModel.this.socketIp = baseData.getData().socketIp;
                int unused2 = WiFiSettingModel.this.socketPort = baseData.getData().socketPort;
                WiFiSettingModel wiFiSettingModel = WiFiSettingModel.this;
                String unused3 = wiFiSettingModel.inetAddress = WiFiSettingModel.this.socketIp + ":" + WiFiSettingModel.this.socketPort;
                WiFiSettingModel.this.sendAPConfigData();
            }
        });
    }

    /* access modifiers changed from: private */
    public void fetch(final int i, final int i2, final int i3) {
        ApiHelper.getDeviceApi().bindDev(UserCache.getInstance().getToken(), this.mac.replace(":", ""), TimeUtil.getCurrentTimeZoneNumber()).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe(new DataObserver<Void>() {
            /* access modifiers changed from: protected */
            public String setTag() {
                return WiFiSettingModel.this.TAG;
            }

            /* access modifiers changed from: protected */
            public void onError(final String str) {
                KLog.m65e(str);
                if (WiFiSettingModel.this.isCancel || i2 >= i) {
                    Single.create(new SingleOnSubscribe<Void>() {
                        public void subscribe(SingleEmitter<Void> singleEmitter) throws Exception {
                            WiFiSettingModel.this.showFailDialog(str);
                            WiFiSettingModel.this.cancel();
                        }
                    }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
                    return;
                }
                SystemClock.sleep((long) i3);
                WiFiSettingModel.this.fetch(i, i2 + 1, i3);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                KLog.m65e("配网成功");
                Single.create(new SingleOnSubscribe<Void>() {
                    public void subscribe(SingleEmitter<Void> singleEmitter) throws Exception {
                        byte unused = WiFiSettingModel.this.connectType = (byte) 1;
                        if (WiFiSettingModel.this.deviceType == 11) {
                            WiFiSettingModel.this.repository.disconnectBle(WiFiSettingModel.this.mac).subscribeOn(Schedulers.m983io()).subscribe();
                        }
                        WiFiSettingModel.this.showSuccessDialog(Utils.getString(C1922R.string.change_mode_success));
                    }
                }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
            }
        });
    }

    public void cancel() {
        this.isCancel = true;
        RxHttpUtils.cancel(this.TAG);
    }

    private void delDevNetworkInfo() {
        KLog.m65e("配网 清除配网");
        String token = UserCache.getInstance().getToken();
        if (!TextUtils.isEmpty(token)) {
            ApiHelper.getDeviceApi().delDevNetWorkInfo(token, this.mac.replace(":", "")).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                public void doOnSubscribe(Disposable disposable) {
                }

                /* access modifiers changed from: protected */
                public void onSuccess(Void voidR) {
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    WiFiSettingModel.this.showFailDialog(str);
                }
            });
        }
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT < 30) {
            addSubscribe(new RxPermissions((FragmentActivity) AppManager.getAppManager().currentActivity()).request("android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION").subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    if (!bool.booleanValue() && Build.VERSION.SDK_INT < 26) {
                        CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C1922R.string.permission_reject_location));
                    } else if (!AppUtils.isLocationEnabled(Utils.getApp())) {
                        CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C1922R.string.location_disabled));
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

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResume() {
        if (!this.manager.isOpened()) {
            CustomToastUtils.showCenterRoundRectToast(Utils.getContext(), Utils.getString(C1922R.string.err_not_enable_wifi));
        } else {
            this.refreshHandler.postDelayed(this.refreshRunnable, 30000);
        }
    }

    public void scanWifi() {
        IWifiManager iWifiManager = this.manager;
        if (iWifiManager != null) {
            if (!iWifiManager.isOpened()) {
                this.manager.openWifi();
            }
            this.manager.scanWifi();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void onPause() {
        this.refreshHandler.removeCallbacks(this.refreshRunnable);
    }

    private void initEasySocket() {
        if (!this.isConnected) {
            EasySocket.getInstance().setDebug(true);
            EasySocket.getInstance().createConnection(new EasySocketOptions.Builder().setReconnectionManager(new NoneReConnection()).setOpenRequestTimeout(false).setSocketAddress(new SocketAddress(AppUrlConfig.e_wifi_ip, AppUrlConfig.e_wifi_port)).build(), Utils.getContext());
            EasySocket.getInstance().subscribeSocketAction(this.socketActionListener);
        }
    }

    public void onWifiChanged(List<IWifi> list) {
        this.items.clear();
        for (IWifi itemModel : list) {
            this.items.add(new ItemModel(itemModel, (String) null, this.socketPort, new ItemModel.OnItemClickListener() {
                public void onItemClick(IWifi iWifi) {
                    if (!NetworkUtil.is24GHz(iWifi.frequency())) {
                        MaterialDialog unused = WiFiSettingModel.this.mDialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), "5GHz Connection Detected", "Go to setting and connect your mobile device to a 2.4GHz network to continue.", (String) null, AppManager.getAppManager().currentActivity().getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null, new DialogInterface.OnDismissListener() {
                            public void onDismiss(DialogInterface dialogInterface) {
                            }
                        });
                        return;
                    }
                    WiFiSettingModel.this.setSSID(iWifi.name());
                    WiFiSettingModel.this.onDismiss.execute();
                }
            }));
        }
    }

    class NoneReConnection extends AbsReconnection {
        public boolean isReconning() {
            return false;
        }

        NoneReConnection() {
        }
    }

    /* access modifiers changed from: package-private */
    public void sendConfigData() {
        if (this.isConnected) {
            KLog.m65e("配网 发送数据1： " + ByteUtils.bytes2HexString(EFamilialResolution.getE_WifiDeviceInfo(1)));
            EasySocket.getInstance().upMessage(EFamilialResolution.getE_WifiDeviceInfo(1));
        }
    }

    /* access modifiers changed from: private */
    public void reconnect(boolean z) {
        Disposable disposable = this.reconnect;
        if (disposable == null || disposable.isDisposed()) {
            Single create = Single.create(new SingleOnSubscribe<Boolean>() {
                public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                    singleEmitter.onSuccess(true);
                }
            });
            if (z) {
                create = create.delay(1, TimeUnit.SECONDS);
            }
            this.reconnect = create.subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    if (WiFiSettingModel.this.retryConnectCount < 3 && WiFiSettingModel.this.showLoading.getValue() != null && WiFiSettingModel.this.showLoading.getValue().booleanValue()) {
                        WiFiSettingModel.access$408(WiFiSettingModel.this);
                        BuglyLog.m283e(WiFiSettingModel.this.TAG, "Socket 重连");
                        EasySocket.getInstance().connect();
                    } else if (WiFiSettingModel.this.showLoading.getValue() != null && WiFiSettingModel.this.showLoading.getValue().booleanValue()) {
                        WiFiSettingModel.this.showLoading.setValue(false);
                        WiFiSettingModel.this.showFailDialog(Utils.getString(C1922R.string.response_time_out));
                    }
                }
            });
        }
    }

    public void onDestroy() {
        cancel();
        Disposable disposable = this.reconnect;
        if (disposable != null) {
            disposable.dispose();
            this.reconnect = null;
        }
        IWifiManager iWifiManager = this.manager;
        if (iWifiManager != null) {
            iWifiManager.destroy();
        }
        MaterialDialog materialDialog = this.mDialog;
        if (materialDialog != null && materialDialog.isShowing()) {
            this.mDialog.dismiss();
        }
        if (this.deviceType != 11) {
            try {
                EasySocket.getInstance().unSubscribeSocketAction(this.socketActionListener);
                EasySocket.getInstance().destroyConnection();
            } catch (NotNullException unused) {
            }
        }
    }
}
