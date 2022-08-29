package com.eternal.account.model;

import android.app.Application;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.StringUtils;
import com.eternal.framework.utils.Utils;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class VersionModel extends BaseViewModel {
    public byte connectType;
    public MutableLiveData<String> currentVersionText = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String devId;
    private byte deviceType;
    private String firmwareUrl;
    public MutableLiveData<Boolean> isAppUpdate = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String mac;
    private Disposable nameRefresh;
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            VersionModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
        }
    });
    public BindingCommand<Void> onNext = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(VersionModel.this, "show update dialog");
        }
    });
    private byte port;
    DeviceRepository repository;
    private String softwareVersion;
    public MutableLiveData<String> subtitle = new MutableLiveData<>();
    private Disposable timeRefresh;
    public MutableLiveData<String> timeText = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeVisible = new MutableLiveData<>();
    public MutableLiveData<String> tipText = new MutableLiveData<>();
    public MutableLiveData<Boolean> updateAvailable = new MutableLiveData<>();
    private String updateVersion;
    public MutableLiveData<String> versionText = new MutableLiveData<>();

    public VersionModel(Application application) {
        super(application);
    }

    public void setUpdateAvailable(boolean z) {
        MutableLiveData<Boolean> mutableLiveData = this.isAppUpdate;
        if (mutableLiveData == null || mutableLiveData.getValue() != Boolean.TRUE) {
            if (z) {
                MutableLiveData<String> mutableLiveData2 = this.tipText;
                mutableLiveData2.setValue("VERSION " + this.updateVersion + " IS AVAILABLE");
                this.versionText.setValue("Please update the controller to get the \nlatest features.");
            } else {
                this.tipText.setValue("YOUR FIRMWARE IS UP-TO-DATE");
                this.versionText.setValue("Version: " + this.softwareVersion);
            }
            MutableLiveData<String> mutableLiveData3 = this.currentVersionText;
            mutableLiveData3.setValue("Current firmware version:\n" + this.softwareVersion);
        } else {
            if (z) {
                MutableLiveData<String> mutableLiveData4 = this.tipText;
                mutableLiveData4.setValue("VERSION " + this.updateVersion + " IS AVAILABLE");
                this.versionText.setValue("Please update your app to get \nthe latest features.");
            } else {
                this.tipText.setValue("YOUR APP IS UP-TO-DATE");
                this.versionText.setValue("Version: " + this.softwareVersion);
            }
            MutableLiveData<String> mutableLiveData5 = this.currentVersionText;
            mutableLiveData5.setValue("Current version:\n" + this.softwareVersion);
        }
        this.updateAvailable.setValue(Boolean.valueOf(z));
    }

    public void init(DeviceRepository deviceRepository, String str, String str2, byte b, byte b2, String str3, boolean z, String str4, String str5, byte b3) {
        BleStatue connect = deviceRepository.getConnect(str);
        if (connect != null && !TextUtils.isEmpty(connect.softwareVersion)) {
            str5 = connect.softwareVersion;
        }
        this.softwareVersion = str5;
        this.updateVersion = str4;
        this.firmwareUrl = str3;
        this.isAppUpdate.setValue(Boolean.valueOf(z));
        this.mac = str;
        this.devId = str2;
        this.port = b;
        this.connectType = b3;
        this.deviceType = b2;
        this.repository = deviceRepository;
        boolean z2 = true;
        if (!z) {
            setUpdateAvailable(true ^ TextUtils.isEmpty(str3));
        } else if (TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5)) {
            setUpdateAvailable(false);
        } else {
            if (StringUtils.compareVersion(str4, str5) != 1) {
                z2 = false;
            }
            setUpdateAvailable(z2);
        }
        refreshName();
        refreshTime();
    }

    private void refreshTime() {
        if (!TextUtils.isEmpty(this.mac)) {
            Disposable disposable = this.timeRefresh;
            if (disposable == null || disposable.isDisposed()) {
                Disposable subscribe = RepositoryInjection.providerDeviceRepository().getConnectInfo(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConnectInfo>() {
                    public void accept(ConnectInfo connectInfo) {
                        if (RepositoryInjection.providerDeviceRepository().isConnect(VersionModel.this.mac)) {
                            VersionModel.this.timeVisible.setValue(false);
                        } else {
                            VersionModel.this.timeVisible.setValue(true);
                            VersionModel.this.timeText.setValue(Utils.getString(C0997R.string.tip_last, ProtocolTransformer.getTime(connectInfo.connectTime)));
                        }
                        if (connectInfo.connectType != VersionModel.this.connectType) {
                            VersionModel.this.connectType = connectInfo.connectType;
                            String unused = VersionModel.this.devId = connectInfo.deviceId;
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
            Disposable subscribe = RepositoryInjection.providerDeviceRepository().getDeviceName(this.mac, (byte) 0).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceName>() {
                public void accept(DeviceName deviceName) {
                    if (deviceName != null) {
                        VersionModel.this.subtitle.setValue(deviceName.name);
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

    public void pushToFirmwareUpdatePage() {
        ARouter.getInstance().build(RouterActivityPath.Account.PAGE_FIRMWARE_UPDATE).withTransition(C0997R.anim.right_in, C0997R.anim.left_out).withString(ActivityEvent.DEVICE_MAC, this.mac).withString(ActivityEvent.DEVICE_ID, this.devId).withByte(ActivityEvent.DEVICE_PORT, this.port).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).withString(ActivityEvent.DEVICE_FIRMWARE_URL, this.firmwareUrl).withString(ActivityEvent.UPDATE_VERSION, this.updateVersion).navigation(AppManager.getAppManager().currentActivity());
    }
}
