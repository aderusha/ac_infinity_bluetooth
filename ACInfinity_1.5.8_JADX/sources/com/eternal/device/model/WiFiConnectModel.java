package com.eternal.device.model;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
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
import com.eternal.base.concat.WiFiDevice;
import com.eternal.base.config.AppUrlConfig;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.protocol.EFamilialResolution;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.ByteUtils;
import com.eternal.base.utils.CustomToastUtils;
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
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.AppUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.NetworkUtil;
import com.eternal.framework.utils.SPUtils;
import com.eternal.framework.utils.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.bugly.crashreport.BuglyLog;
import com.tencent.bugly.crashreport.CrashReport;
import java.net.Inet4Address;
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

public class WiFiConnectModel extends BaseViewModel implements OnWifiChangeListener {
    private final String SP_KEY_SSID = "sp key ssid";
    /* access modifiers changed from: private */
    public String SSID;
    /* access modifiers changed from: private */
    public final String TAG = "WiFiConnectModel";
    public MutableLiveData<Boolean> confirmAble = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public byte deviceType;
    private String inetAddress;
    /* access modifiers changed from: private */

    /* renamed from: ip */
    public byte[] f172ip;
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
            WiFiConnectModel.this.finishAnimate(C1922R.anim.left_in, C1922R.anim.right_out);
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiConnectModel.this, "show dialog");
        }
    });
    public BindingCommand<Void> onDismiss = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiConnectModel.this, "dismiss sheet dialog");
        }
    });
    public BindingCommand<Void> onHelp = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Device.PAGE_HELP).withTransition(C1922R.anim.right_in, C1922R.anim.left_out).withByte("page type", (byte) 1).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (WiFiConnectModel.this.deviceType == 11) {
                WiFiConnectModel.this.sendAPConfigData();
            } else {
                WiFiConnectModel.this.showLoading.setValue(true);
                boolean unused = WiFiConnectModel.this.sending = true;
                int unused2 = WiFiConnectModel.this.retryConnectCount = 0;
                if (WiFiConnectModel.this.isConnected) {
                    WiFiConnectModel.this.sendConfigData();
                } else {
                    WiFiConnectModel.this.reconnect(false);
                }
            }
            SPUtils.getInstance().put("sp key ssid", WiFiConnectModel.this.SSID);
            if (WiFiConnectModel.this.isSavePwd.getValue() != Boolean.TRUE || WiFiConnectModel.this.passwordText.getValue() == null) {
                SPUtils.getInstance().put(WiFiConnectModel.this.SSID, "");
            } else {
                SPUtils.getInstance().put(WiFiConnectModel.this.SSID, WiFiConnectModel.this.passwordText.getValue());
            }
        }
    });
    public BindingCommand<Void> onPwdShow = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            WiFiConnectModel.this.textVisiblePassword.setValue(Boolean.valueOf(!WiFiConnectModel.this.textVisiblePassword.getValue().booleanValue()));
        }
    });
    public BindingCommand<Void> onRefresh = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            WiFiConnectModel.this.addSubscribe(Single.timer(5, TimeUnit.SECONDS).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
                public void accept(Long l) throws Exception {
                    WiFiConnectModel.this.isLoading.setValue(false);
                }
            }));
            if (AppUtils.isLocationEnabled(Utils.getApp())) {
                WiFiConnectModel.this.scanWifi();
            }
        }
    });
    public BindingCommand<Void> onSavePwd = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            MutableLiveData<Boolean> mutableLiveData = WiFiConnectModel.this.isSavePwd;
            boolean z = true;
            if (WiFiConnectModel.this.isSavePwd.getValue() == null) {
                Boolean bool = Boolean.TRUE;
            } else if (WiFiConnectModel.this.isSavePwd.getValue().booleanValue()) {
                z = false;
            }
            mutableLiveData.setValue(Boolean.valueOf(z));
        }
    });
    public BindingCommand<Void> onSetting = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(WiFiConnectModel.this, "show sheet dialog");
        }
    });
    public MutableLiveData<String> passwordText = new MutableLiveData<>();
    public MutableLiveData<Integer> pwdColor = new MutableLiveData<>();
    public MutableLiveData<String> pwdErrText = new MutableLiveData<>();
    public MutableLiveData<Boolean> pwdErrVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> pwdLineColor = new MutableLiveData<>();
    public MutableLiveData<String> pwdShowText = new MutableLiveData<>();
    public BindingCommand<String> pwdTextChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            WiFiConnectModel.this.onTextChanged(str);
        }
    });
    private Disposable reconnect;
    /* access modifiers changed from: private */
    public Handler refreshHandler = new Handler();
    /* access modifiers changed from: private */
    public Runnable refreshRunnable = new Runnable() {
        public void run() {
            WiFiConnectModel.this.scanWifi();
            WiFiConnectModel.this.refreshHandler.postDelayed(WiFiConnectModel.this.refreshRunnable, 30000);
        }
    };
    private DeviceRepository repository;
    /* access modifiers changed from: private */
    public int retryConnectCount;
    /* access modifiers changed from: private */
    public boolean sending;
    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();
    private ISocketActionListener socketActionListener = new SocketActionListener() {
        public void onSocketConnSuccess(SocketAddress socketAddress) {
            KLog.m62d("端口" + socketAddress.getPort() + "---> 连接成功");
            boolean unused = WiFiConnectModel.this.isConnected = true;
            if (WiFiConnectModel.this.showLoading.getValue() != null && WiFiConnectModel.this.showLoading.getValue().booleanValue()) {
                WiFiConnectModel.this.sendConfigData();
            }
        }

        public void onSocketConnFail(SocketAddress socketAddress, boolean z) {
            boolean unused = WiFiConnectModel.this.isConnected = false;
            KLog.m65e(socketAddress.getPort() + "端口---> socket断开连接，是否需要重连：" + z);
            WiFiConnectModel.this.reconnect(true);
        }

        public void onSocketDisconnect(SocketAddress socketAddress, boolean z) {
            boolean unused = WiFiConnectModel.this.isConnected = false;
            KLog.m65e(socketAddress.getPort() + "端口---> socket断开连接，是否需要重连：" + z);
            WiFiConnectModel.this.reconnect(true);
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
                    String access$900 = WiFiConnectModel.this.TAG;
                    KLog.m66e(access$900, "配网 接收数据： " + ByteUtils.bytes2HexString(originDataBytes) + ", sending: " + WiFiConnectModel.this.sending);
                    if (WiFiConnectModel.this.sending && originDataBytes[0] == -91 && originDataBytes[4] == 0 && originDataBytes[5] == 1 && originDataBytes[10] == 0) {
                        WiFiConnectModel.this.showLoading.setValue(false);
                        boolean unused = WiFiConnectModel.this.sending = false;
                        WiFiDevice parseE_WifiDeviceInfo = EFamilialResolution.parseE_WifiDeviceInfo(originDataBytes);
                        if (parseE_WifiDeviceInfo == null) {
                            WiFiConnectModel.this.showFailDialog(Utils.getString(C1922R.string.response_time_out));
                            return;
                        }
                        byte[] aPConfig = EFamilialResolution.setAPConfig(WiFiConnectModel.this.SSID, WiFiConnectModel.this.passwordText.getValue(), WiFiConnectModel.this.f172ip, WiFiConnectModel.this.socketPort, 2);
                        KLog.m65e("配网 发送数据2： " + ByteUtils.bytes2HexString(aPConfig));
                        EasySocket.getInstance().upMessage(aPConfig);
                        WiFiConnectModel.this.mac = parseE_WifiDeviceInfo.mac;
                        WiFiConnectModel.this.typeName = parseE_WifiDeviceInfo.typeName;
                    } else if (originDataBytes[0] != -91 || originDataBytes[4] != 0 || originDataBytes[5] != 2 || originDataBytes[10] != 8) {
                        WiFiConnectModel.this.showLoading.setValue(false);
                        WiFiConnectModel.this.showFailDialog(Utils.getString(C1922R.string.response_time_out));
                    } else if (TextUtils.isEmpty(WiFiConnectModel.this.typeName) || TextUtils.isEmpty(WiFiConnectModel.this.mac)) {
                        WiFiConnectModel.this.showLoading.setValue(false);
                        WiFiConnectModel.this.showFailDialog(Utils.getString(C1922R.string.response_time_out));
                    } else if (EFamilialResolution.checkResult(originDataBytes)) {
                        Messenger.getDefault().send(Boolean.FALSE, ActivityEvent.BACK_WIFI_PAGE);
                        WiFiConnectModel.this.pushToAddPage(WiFiConnectModel.this.mac, WiFiConnectModel.this.typeName);
                    } else {
                        WiFiConnectModel.this.showLoading.setValue(false);
                        MaterialDialog unused2 = WiFiConnectModel.this.mDialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), Utils.getString(C1922R.string.err_config_net_title), Utils.getString(C1922R.string.err_config_net_content), (String) null, Utils.getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null);
                    }
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
            KLog.m62d(socketAddress.getPort() + "端口SocketActionListener收到数据-->" + ByteUtils.bytes2HexString(originReadData.getOriginDataBytes()));
        }
    };
    /* access modifiers changed from: private */
    public int socketPort;
    public MutableLiveData<Boolean> textVisiblePassword = new MutableLiveData<>();
    public String typeName;

    static /* synthetic */ int access$308(WiFiConnectModel wiFiConnectModel) {
        int i = wiFiConnectModel.retryConnectCount;
        wiFiConnectModel.retryConnectCount = i + 1;
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

    public WiFiConnectModel(Application application) {
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
                WiFiConnectModel.this.finish();
            }
        });
    }

    public void init(String str, String str2, int i, byte b, String str3, String str4) {
        this.repository = RepositoryInjection.providerDeviceRepository();
        this.mac = str3;
        this.typeName = str4;
        this.retryConnectCount = 3;
        if (TextUtils.isEmpty(str)) {
            str = SPUtils.getInstance().getString("sp key ssid", "");
        }
        setSSID(str);
        try {
            this.f172ip = Inet4Address.getByName(str2).getAddress();
        } catch (Throwable th) {
            th.printStackTrace();
            BuglyLog.m283e(this.TAG, "init 获取 socketIp失败");
            CrashReport.postCatchedException(th);
        }
        this.inetAddress = str2 + ":" + i;
        this.socketPort = i;
        this.deviceType = b;
        this.pwdLineColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C1922R.C1923color.color_838383)));
        this.pwdColor.setValue(Integer.valueOf(AppManager.getAppManager().currentActivity().getResources().getColor(C1922R.C1923color.color_838383)));
        this.confirmAble.setValue(Boolean.FALSE);
        this.showLoading.setValue(Boolean.FALSE);
        this.isSavePwd.setValue(Boolean.TRUE);
        this.textVisiblePassword.setValue(false);
        initPermission();
        IWifiManager create = WifiManager.create(AppManager.getAppManager().currentActivity());
        this.manager = create;
        create.setOnWifiChangeListener(this);
        if (b != 11) {
            initEasySocket();
        }
    }

    public void setSSID(String str) {
        this.SSID = str;
        this.name.setValue(str);
        this.passwordText.setValue(SPUtils.getInstance().getString(str, ""));
    }

    /* access modifiers changed from: package-private */
    public void sendAPConfigData() {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect == null) {
            Messenger.getDefault().send(Boolean.TRUE, ActivityEvent.BACK_WIFI_PAGE);
            return;
        }
        this.showLoading.setValue(true);
        addSubscribe(this.repository.setConfigSta(connect, this.SSID, this.passwordText.getValue(), this.inetAddress).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                KLog.m65e("配网回调" + bool);
                if (bool.booleanValue()) {
                    Messenger.getDefault().send(Boolean.FALSE, ActivityEvent.BACK_WIFI_PAGE);
                    WiFiConnectModel wiFiConnectModel = WiFiConnectModel.this;
                    wiFiConnectModel.pushToAddPage(wiFiConnectModel.mac, WiFiConnectModel.this.typeName);
                } else {
                    MaterialDialog unused = WiFiConnectModel.this.mDialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), Utils.getString(C1922R.string.err_config_net_title), Utils.getString(C1922R.string.err_config_net_content), (String) null, Utils.getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null);
                }
                WiFiConnectModel.this.showLoading.setValue(false);
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                WiFiConnectModel.this.showLoading.setValue(false);
                MaterialDialog unused = WiFiConnectModel.this.mDialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), Utils.getString(C1922R.string.err_config_net_title), Utils.getString(C1922R.string.err_config_net_content), (String) null, Utils.getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null);
                th.printStackTrace();
            }
        }));
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
                        MaterialDialog unused = WiFiConnectModel.this.mDialog = TipDialog.showTipDialog(AppManager.getAppManager().currentActivity(), "5GHz Connection Detected", Utils.getString(C1922R.string.tip_5g_detect_content), (String) null, AppManager.getAppManager().currentActivity().getString(C1922R.string.tip_ok), true, false, (View.OnClickListener) null, (View.OnClickListener) null, new DialogInterface.OnDismissListener() {
                            public void onDismiss(DialogInterface dialogInterface) {
                            }
                        });
                        return;
                    }
                    WiFiConnectModel.this.setSSID(iWifi.name());
                    WiFiConnectModel.this.onDismiss.execute();
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
                    if (WiFiConnectModel.this.retryConnectCount < 3 && WiFiConnectModel.this.showLoading.getValue() != null && WiFiConnectModel.this.showLoading.getValue().booleanValue()) {
                        WiFiConnectModel.access$308(WiFiConnectModel.this);
                        BuglyLog.m283e(WiFiConnectModel.this.TAG, "Socket 重连");
                        EasySocket.getInstance().connect();
                    } else if (WiFiConnectModel.this.showLoading.getValue() != null && WiFiConnectModel.this.showLoading.getValue().booleanValue()) {
                        WiFiConnectModel.this.showLoading.setValue(false);
                        WiFiConnectModel.this.showFailDialog(Utils.getString(C1922R.string.response_time_out));
                    }
                }
            });
        }
    }

    public void onDestroy() {
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
