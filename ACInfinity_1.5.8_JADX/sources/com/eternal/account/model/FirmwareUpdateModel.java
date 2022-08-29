package com.eternal.account.model;

import android.animation.ValueAnimator;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.content.Context;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.account.C0997R;
import com.eternal.account.DfuService;
import com.eternal.account.VersionActivity;
import com.eternal.account.bleota.BleOTAClient;
import com.eternal.account.bleota.message.BleOTAMessage;
import com.eternal.account.bleota.message.EndCommandAckMessage;
import com.eternal.account.bleota.message.StartCommandAckMessage;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.DeviceName;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.FileUtil;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.RxHttpUtils;
import com.eternal.framework.http.download.DownloadObserver;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.PathUtils;
import com.eternal.framework.utils.Utils;
import com.telink.p010lt.ble.Device;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.ResponseBody;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.Observer;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;
import p019no.nordicsemi.android.dfu.DfuProgressListener;
import p019no.nordicsemi.android.dfu.DfuProgressListenerAdapter;
import p019no.nordicsemi.android.dfu.DfuServiceController;
import p019no.nordicsemi.android.dfu.DfuServiceInitiator;
import p019no.nordicsemi.android.dfu.DfuServiceListenerHelper;

public class FirmwareUpdateModel extends BaseViewModel {
    /* access modifiers changed from: private */
    public static final String TAG = "FirmwareUpdateModel";
    private ValueAnimator animator;
    public MutableLiveData<String> btnText = new MutableLiveData<>();
    public MutableLiveData<Boolean> btnVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> cancelVisible = new MutableLiveData<>();
    private Disposable connectEvent;
    private byte connectType;
    public MutableLiveData<String> contentText = new MutableLiveData<>();
    public MutableLiveData<Boolean> contentVisible = new MutableLiveData<>();
    private String devId;
    private Device.DeviceStateCallback deviceCallback = new Device.DeviceStateCallback() {
        public void onConnected(Device device) {
            KLog.m65e(FirmwareUpdateModel.TAG + " # onConnected");
            int unused = FirmwareUpdateModel.this.mConnectState = 2;
        }

        public void onDisconnected(Device device) {
            KLog.m65e(FirmwareUpdateModel.TAG + " # onDisconnected");
            int unused = FirmwareUpdateModel.this.mConnectState = 0;
        }

        public void onServicesDiscovered(Device device, List<BluetoothGattService> list) {
            KLog.m65e(FirmwareUpdateModel.TAG + " # onServicesDiscovered");
            UUID uuid = null;
            for (BluetoothGattService next : list) {
                Iterator<BluetoothGattCharacteristic> it = next.getCharacteristics().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (it.next().getUuid().equals(Device.CHARACTERISTIC_UUID_WRITE)) {
                            uuid = next.getUuid();
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (uuid != null) {
                device.SERVICE_UUID = uuid;
            }
        }

        public void onOtaStateChanged(final Device device, final int i) {
            KLog.m65e(FirmwareUpdateModel.TAG + " # onOtaStateChanged");
            if (i == 0) {
                KLog.m65e(FirmwareUpdateModel.TAG + " ota failure : ");
            }
            Completable.create(new CompletableOnSubscribe() {
                public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                    int i = i;
                    if (i == 0) {
                        KLog.m65e("ota failure : ");
                        FirmwareUpdateModel.this.end(false);
                    } else if (i == 1) {
                        KLog.m65e("ota success : ");
                        FirmwareUpdateModel.this.end(true);
                    } else if (i == 2) {
                        MutableLiveData<String> mutableLiveData = FirmwareUpdateModel.this.progressText;
                        mutableLiveData.setValue(device.getOtaProgress() + "%");
                        KLog.m65e("ota progress : " + device.getOtaProgress());
                    }
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
        }
    };
    /* access modifiers changed from: private */
    public byte deviceType;
    private DfuServiceController dfuServiceController;
    /* access modifiers changed from: private */
    public String filePath;
    private String firmwareUrl;
    /* access modifiers changed from: private */
    public boolean isUpdated;
    /* access modifiers changed from: private */
    public int mConnectState = 0;
    private Device mDevice;
    private final DfuProgressListener mDfuProgressListener = new DfuProgressListenerAdapter() {
        public void onDeviceConnecting(String str) {
            KLog.m63d(FirmwareUpdateModel.TAG, "onDeviceConnecting");
        }

        public void onDfuProcessStarting(String str) {
            KLog.m63d(FirmwareUpdateModel.TAG, "onDfuProcessStarting");
        }

        public void onEnablingDfuMode(String str) {
            KLog.m63d(FirmwareUpdateModel.TAG, "onEnablingDfuMode");
        }

        public void onFirmwareValidating(String str) {
            KLog.m63d(FirmwareUpdateModel.TAG, "onFirmwareValidating");
        }

        public void onDeviceDisconnecting(String str) {
            KLog.m63d(FirmwareUpdateModel.TAG, "onDeviceDisconnecting");
        }

        public void onDfuCompleted(String str) {
            KLog.m63d(FirmwareUpdateModel.TAG, "onDfuCompleted");
            FirmwareUpdateModel.this.end(true);
        }

        public void onDfuAborted(String str) {
            KLog.m63d(FirmwareUpdateModel.TAG, "onDfuAborted");
            FirmwareUpdateModel.this.end(false);
        }

        public void onProgressChanged(String str, int i, float f, float f2, int i2, int i3) {
            String access$300 = FirmwareUpdateModel.TAG;
            KLog.m63d(access$300, "onDfuAborted partsTotal:" + i3 + " ,percent:" + i + "%");
            MutableLiveData<String> mutableLiveData = FirmwareUpdateModel.this.progressText;
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append("%");
            mutableLiveData.setValue(sb.toString());
        }

        public void onError(String str, int i, int i2, String str2) {
            String access$300 = FirmwareUpdateModel.TAG;
            KLog.m63d(access$300, "onError " + str2);
            FirmwareUpdateModel.this.end(false);
        }
    };
    /* access modifiers changed from: private */
    public BleOTAClient mOtaClient;
    /* access modifiers changed from: private */
    public String mac;
    public MutableLiveData<Boolean> maxVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> minVisible = new MutableLiveData<>();
    private Disposable nameRefresh;
    public MutableLiveData<Boolean> normalVisible = new MutableLiveData<>();
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!FirmwareUpdateModel.this.isUpdated) {
                AppManager.getAppManager().finishActivity((Class<?>) VersionActivity.class);
                FirmwareUpdateModel.this.finishAnimate(C0997R.anim.left_in, C0997R.anim.right_out);
                return;
            }
            FirmwareUpdateModel.this.pushToMain();
        }
    });
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().send(FirmwareUpdateModel.this, "show dialog");
        }
    });
    public BindingCommand<Void> onConfirm = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!FirmwareUpdateModel.this.isUpdated) {
                FirmwareUpdateModel.this.downloadFile();
            } else {
                FirmwareUpdateModel.this.pushToMain();
            }
        }
    });
    private byte port;
    ObservableEmitter<Integer> progressEmitter;
    public MutableLiveData<String> progressText = new MutableLiveData<>();
    public MutableLiveData<Boolean> progressVisible = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public DeviceRepository repository;
    public MutableLiveData<Integer> state = new MutableLiveData<>();
    public MutableLiveData<String> stateText = new MutableLiveData<>();
    public MutableLiveData<String> subtitle = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public Disposable timeEvent;
    /* access modifiers changed from: private */
    public String updateVersion;

    public FirmwareUpdateModel(Application application) {
        super(application);
        this.minVisible.setValue(false);
        this.normalVisible.setValue(false);
        this.maxVisible.setValue(false);
        this.isUpdated = false;
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{0, 4});
        this.animator = ofInt;
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                if (intValue == 0) {
                    FirmwareUpdateModel.this.maxVisible.setValue(false);
                    FirmwareUpdateModel.this.normalVisible.setValue(false);
                    FirmwareUpdateModel.this.minVisible.setValue(false);
                } else if (intValue == 1) {
                    FirmwareUpdateModel.this.minVisible.setValue(true);
                } else if (intValue == 2) {
                    FirmwareUpdateModel.this.normalVisible.setValue(true);
                } else {
                    FirmwareUpdateModel.this.maxVisible.setValue(true);
                }
            }
        });
        this.animator.setRepeatCount(-1);
        this.animator.setDuration(2000);
    }

    private void registerEventBus() {
        addSubscribe(RxBus.getDefault().toObservable(BluetoothEvent.class).subscribe(new Consumer<BluetoothEvent>() {
            public void accept(BluetoothEvent bluetoothEvent) throws Exception {
                if (bluetoothEvent.what != 3 && bluetoothEvent.what == 4) {
                    FirmwareUpdateModel firmwareUpdateModel = FirmwareUpdateModel.this;
                    firmwareUpdateModel.initCDevice(firmwareUpdateModel.mac);
                }
            }
        }));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void registerListener() {
        DfuServiceListenerHelper.registerProgressListener(AppManager.getAppManager().currentActivity(), this.mDfuProgressListener);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void unregisterListener() {
        DfuServiceListenerHelper.unregisterProgressListener(AppManager.getAppManager().currentActivity(), this.mDfuProgressListener);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestroy() {
        Device device;
        unregisterEvent();
        RxHttpUtils.cancel(TAG);
        if ((ProtocolTransformer.isDeviceC(this.deviceType) || this.deviceType == 6) && (device = this.mDevice) != null) {
            device.setDeviceStateCallback((Device.DeviceStateCallback) null);
            if (this.mConnectState == 2) {
                this.mDevice.disconnect();
            }
        }
        DfuServiceController dfuServiceController2 = this.dfuServiceController;
        if (dfuServiceController2 != null) {
            dfuServiceController2.abort();
        }
        close();
    }

    public void pushToMain() {
        ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN).withTransition(C0997R.anim.left_in, C0997R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AppManager.getAppManager().finishActivity((Class<?>) VersionActivity.class);
                FirmwareUpdateModel.this.finish();
            }
        });
    }

    public void init(DeviceRepository deviceRepository, String str, String str2, byte b, byte b2, String str3, String str4, byte b3) {
        this.repository = deviceRepository;
        this.mac = str;
        this.devId = str2;
        this.port = b;
        this.deviceType = b2;
        this.connectType = b3;
        this.firmwareUrl = str3;
        this.updateVersion = str4;
        registerEventBus();
        initCDevice(str);
        refreshName();
        downloadFile();
        initProgress();
    }

    private void upgradeForNet() {
        start();
        ApiHelper.getDeviceApi().function(this.devId, 4, this.firmwareUrl).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
            }

            /* access modifiers changed from: protected */
            public String setTag() {
                return FirmwareUpdateModel.TAG;
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                FirmwareUpdateModel.this.end(false);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                Observable.interval(0, 1, TimeUnit.SECONDS).take(180).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Long>() {
                    public void onSubscribe(Disposable disposable) {
                        Disposable unused = FirmwareUpdateModel.this.timeEvent = disposable;
                    }

                    public void onNext(Long l) {
                        if (l.longValue() >= 20 && FirmwareUpdateModel.this.repository.isConnect(FirmwareUpdateModel.this.mac)) {
                            FirmwareUpdateModel.this.unregisterEvent();
                            if (FirmwareUpdateModel.this.updateVersion.equalsIgnoreCase(FirmwareUpdateModel.this.repository.getConnectNet(FirmwareUpdateModel.this.mac).softwareVersion)) {
                                FirmwareUpdateModel.this.end(true);
                            } else {
                                FirmwareUpdateModel.this.end(false);
                            }
                        }
                    }

                    public void onError(Throwable th) {
                        FirmwareUpdateModel.this.unregisterEvent();
                        FirmwareUpdateModel.this.end(false);
                    }

                    public void onComplete() {
                        FirmwareUpdateModel.this.unregisterEvent();
                        FirmwareUpdateModel.this.end(false);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void unregisterEvent() {
        Disposable disposable = this.timeEvent;
        if (disposable != null) {
            disposable.dispose();
            this.timeEvent = null;
        }
        Disposable disposable2 = this.connectEvent;
        if (disposable2 != null) {
            disposable2.dispose();
            this.connectEvent = null;
        }
    }

    /* access modifiers changed from: private */
    public void initCDevice(String str) {
        if (ProtocolTransformer.isDeviceC(this.deviceType) || this.deviceType == 6) {
            Device device = new Device(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str), new byte[0], 1);
            this.mDevice = device;
            device.disconnect();
            this.mDevice.setDeviceStateCallback(this.deviceCallback);
            this.mDevice.connect(AppManager.getAppManager().currentActivity());
        }
    }

    /* access modifiers changed from: package-private */
    public void downloadFile() {
        if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
            upgradeForNet();
        } else if (TextUtils.isEmpty(this.firmwareUrl)) {
            end(false);
        } else if (!RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            showFailDialog(Utils.getString(C0997R.string.tip_ble_disconnect));
            end(false);
        } else {
            start();
            String str = this.firmwareUrl.endsWith(".bin") ? "firmware.bin" : "firmware.zip";
            Observable<ResponseBody> observeOn = RxHttpUtils.downloadFile(this.firmwareUrl).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread());
            observeOn.subscribe((Observer<? super ResponseBody>) new DownloadObserver(str, PathUtils.getInternalAppFilesPath() + File.separator) {
                /* access modifiers changed from: protected */
                public String setTag() {
                    return FirmwareUpdateModel.TAG;
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    FirmwareUpdateModel.this.end(false);
                }

                /* access modifiers changed from: protected */
                public void onSuccess(long j, long j2, float f, boolean z, String str) {
                    String access$300 = FirmwareUpdateModel.TAG;
                    KLog.m63d(access$300, "下载中：" + f + "%");
                    if (FirmwareUpdateModel.this.deviceType == 11) {
                        FirmwareUpdateModel.this.progressEmitter.onNext(Integer.valueOf((int) (((double) f) * 0.1d)));
                    }
                    String unused = FirmwareUpdateModel.this.filePath = str;
                    if (!z) {
                        return;
                    }
                    if (ProtocolTransformer.isDeviceC(FirmwareUpdateModel.this.deviceType) || FirmwareUpdateModel.this.deviceType == 6) {
                        FirmwareUpdateModel.this.startOta_C();
                    } else if (FirmwareUpdateModel.this.deviceType == 11) {
                        FirmwareUpdateModel.this.startOta_G();
                    } else {
                        FirmwareUpdateModel.this.startOta(str);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void startOta_C() {
        byte[] readFirmware = readFirmware(this.filePath);
        if (readFirmware == null) {
            end(false);
        } else if (this.mConnectState != 2) {
            end(false);
            initCDevice(this.mac);
        } else {
            this.mDevice.startOta(readFirmware);
        }
    }

    private byte[] readFirmware(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return bArr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void startOta(String str) {
        DfuServiceInitiator keepBond = new DfuServiceInitiator(this.mac).setDeviceName("deviceName").setForeground(false).setKeepBond(true);
        keepBond.setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(false);
        keepBond.setPrepareDataObjectDelay(300);
        keepBond.setZip(Uri.fromFile(new File(str)), str);
        this.dfuServiceController = keepBond.start(Utils.getContext(), DfuService.class);
    }

    private void refreshName() {
        Disposable disposable = this.nameRefresh;
        if (disposable == null || disposable.isDisposed()) {
            Disposable subscribe = RepositoryInjection.providerDeviceRepository().getDeviceName(this.mac, (byte) 0).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceName>() {
                public void accept(DeviceName deviceName) {
                    if (deviceName != null) {
                        FirmwareUpdateModel.this.subtitle.setValue(deviceName.name);
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

    private void start() {
        if (!this.animator.isStarted()) {
            this.cancelVisible.setValue(true);
            this.btnVisible.setValue(false);
            this.contentVisible.setValue(true);
            if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
                this.progressVisible.setValue(false);
                this.state.setValue(Integer.valueOf(C0997R.mipmap.controller_69));
            } else {
                this.progressVisible.setValue(true);
                this.progressText.setValue("0%");
            }
            this.stateText.setValue("UPDATING…");
            this.contentText.setValue(Utils.getString(C0997R.string.firmware_update_processing_description));
            this.animator.start();
        }
    }

    /* access modifiers changed from: private */
    public void end(final boolean z) {
        KLog.m65e("BleOTAClient end ");
        if (Looper.myLooper() == null || Looper.myLooper() != Looper.getMainLooper()) {
            Completable.create(new CompletableOnSubscribe() {
                public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                    FirmwareUpdateModel.this.updateStatus(z);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
        } else {
            updateStatus(z);
        }
    }

    /* access modifiers changed from: private */
    public void updateStatus(boolean z) {
        this.animator.end();
        if (z) {
            this.state.setValue(Integer.valueOf(C0997R.mipmap.success));
            this.btnText.setValue("DONE");
            this.stateText.setValue("SUCCESS!");
            this.isUpdated = true;
            this.cancelVisible.setValue(false);
            this.repository.setSoftwareVersion(this.mac, this.updateVersion);
        } else {
            this.state.setValue(Integer.valueOf(C0997R.mipmap.terms_of_use));
            this.btnText.setValue("TRY AGAIN");
            this.stateText.setValue("FAILED TO UPDATE");
        }
        close();
        this.progressVisible.setValue(false);
        this.contentVisible.setValue(false);
        this.btnVisible.setValue(true);
        this.maxVisible.setValue(true);
        this.normalVisible.setValue(true);
        this.minVisible.setValue(true);
        if (!TextUtils.isEmpty(this.filePath)) {
            FileUtil.deleteSingleFile(this.filePath);
        }
    }

    /* access modifiers changed from: private */
    public void startOta_G() {
        close();
        addSubscribe(RepositoryInjection.providerDeviceRepository().disconnectBle(this.mac).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe((Action) new Action() {
            public void run() throws Exception {
                BleOTAClient unused = FirmwareUpdateModel.this.mOtaClient = new BleOTAClient(FirmwareUpdateModel.this.getApplication(), BluetoothAdapter.getDefaultAdapter().getRemoteDevice(FirmwareUpdateModel.this.mac), new File(FirmwareUpdateModel.this.filePath));
                FirmwareUpdateModel.this.mOtaClient.start(new GattCallBack());
            }
        }));
    }

    private class GattCallBack extends BleOTAClient.GattCallback {
        private GattCallBack() {
        }

        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onConnectionStateChange(bluetoothGatt, i, i2);
            KLog.m65e("BleOTAClient onConnectionStateChange");
            if (i != 0 || i2 == 0) {
                FirmwareUpdateModel.this.end(false);
            }
        }

        public void onMtuChanged(BluetoothGatt bluetoothGatt, int i, int i2) {
            super.onMtuChanged(bluetoothGatt, i, i2);
            KLog.m65e("BleOTAClient onMtuChanged");
        }

        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            KLog.m65e("BleOTAClient onServicesDiscovered");
            if (i != 0) {
                FirmwareUpdateModel.this.end(false);
            } else if (FirmwareUpdateModel.this.mOtaClient == null) {
                FirmwareUpdateModel.this.end(false);
            } else if (FirmwareUpdateModel.this.mOtaClient.getService() == null || FirmwareUpdateModel.this.mOtaClient.getRecvFwChar() == null || FirmwareUpdateModel.this.mOtaClient.getProgressChar() == null || FirmwareUpdateModel.this.mOtaClient.getCommandChar() == null || FirmwareUpdateModel.this.mOtaClient.getCustomerChar() == null) {
                FirmwareUpdateModel.this.end(false);
            }
        }

        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            KLog.m65e("BleOTAClient onDescriptorWrite");
            if (i != 0) {
                FirmwareUpdateModel.this.end(false);
            }
        }

        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            KLog.m65e("BleOTAClient onCharacteristicWrite");
            if (i != 0) {
                FirmwareUpdateModel.this.end(false);
            }
        }

        public void onOTA(BleOTAMessage bleOTAMessage) {
            super.onOTA(bleOTAMessage);
            KLog.m65e("BleOTAClient onOTA");
            if (bleOTAMessage instanceof StartCommandAckMessage) {
                if (((StartCommandAckMessage) bleOTAMessage).getStatus() == 1) {
                    FirmwareUpdateModel.this.end(false);
                }
            } else if (bleOTAMessage instanceof EndCommandAckMessage) {
                EndCommandAckMessage endCommandAckMessage = (EndCommandAckMessage) bleOTAMessage;
                if (endCommandAckMessage.getStatus() == 0) {
                    FirmwareUpdateModel.this.end(true);
                } else if (endCommandAckMessage.getStatus() == 1) {
                    FirmwareUpdateModel.this.end(false);
                }
            }
        }

        public void onError(int i) {
            super.onError(i);
            KLog.m65e("BleOTAClient onError");
            FirmwareUpdateModel.this.end(false);
        }

        public void onProgressChanged(int i) {
            super.onProgressChanged(i);
            if (FirmwareUpdateModel.this.progressEmitter != null) {
                FirmwareUpdateModel.this.progressEmitter.onNext(Integer.valueOf(((int) (((double) i) * 0.9d)) + 10));
            }
        }
    }

    private void initProgress() {
        addSubscribe(Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                FirmwareUpdateModel.this.progressEmitter = observableEmitter;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() {
            public void accept(Integer num) throws Exception {
                int min = Math.min(100, Math.max(0, num.intValue()));
                MutableLiveData<String> mutableLiveData = FirmwareUpdateModel.this.progressText;
                mutableLiveData.setValue(min + "%");
            }
        }));
    }

    private void close() {
        BleOTAClient bleOTAClient = this.mOtaClient;
        if (bleOTAClient != null) {
            bleOTAClient.close();
            this.mOtaClient = null;
        }
    }
}
