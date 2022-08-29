package com.eternal.control;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.DeviceMinModel;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.concat.NetDeviceMode;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.RxHttpUtils;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.BeanUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.RxUtils;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import p014io.reactivex.Flowable;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.SingleSource;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class ControlModel extends BaseViewModel {
    public static final int MSG_START = 16;
    private static final String TAG_CHOOSE_PORT = "tag choose port";
    private static final String TAG_FETCH_MODE = "tag fetch mode";
    private static final String TAG_SET_MODE = "tag set mode";
    public MutableLiveData<Boolean> automationActivated = new MutableLiveData<>();
    public MutableLiveData<Boolean> automationVisible = new MutableLiveData<>();
    public ObservableField<String> bufferHumSize = new ObservableField<>();
    public ObservableField<String> bufferTempSize = new ObservableField<>();
    public MutableLiveData<Boolean> bufferVisible = new MutableLiveData<>();
    public byte connectType;
    public MutableLiveData<Boolean> connected = new MutableLiveData<>();
    public MutableLiveData<Boolean> controlTypeByHumModel = new MutableLiveData<>();
    public MutableLiveData<Boolean> currentResidueOnVisible = new MutableLiveData<>();
    public MutableLiveData<Byte> cycleModel = new MutableLiveData<>();
    public String devId;
    public MutableLiveData<Boolean> expand = new MutableLiveData<>();
    public NetDeviceMode fallbackNetMode;
    public ObservableField<String> fanSize = new ObservableField<>();
    public ObservableInt fanType = new ObservableInt();
    public ObservableField<String> fanTypeTitle = new ObservableField<>();
    public ObservableBoolean fanVisibility = new ObservableBoolean();
    private final ControlHandler handler = new ControlHandler(Looper.getMainLooper());
    public ObservableField<String> hintText = new ObservableField<>();
    public MutableLiveData<Boolean> humModeVisible = new MutableLiveData<>();
    public ObservableBoolean humVisibility = new ObservableBoolean();
    public DeviceModel info;
    public boolean isRefreshComplete;
    public boolean isSetDragState = false;
    public boolean isStart;
    public DeviceModel lastInfo;
    public NetDeviceMode lastNetMode;
    private String mac;
    public ObservableInt maxFanType = new ObservableInt();
    public ObservableInt minFanType = new ObservableInt();
    public MutableLiveData<String> model = new MutableLiveData<>();
    public NetDeviceMode netMode;
    public ObservableField<String> offFanSize = new ObservableField<>();
    public BindingCommand<Void> onCloseOverlay = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ControlModel.this.overlayVisible.setValue(false);
        }
    });
    public ObservableField<String> onFanSize = new ObservableField<>();
    public View.OnClickListener onModel = new View.OnClickListener() {
        public void onClick(View view) {
            if (ControlModel.this.expand.getValue().equals(Boolean.TRUE)) {
                ControlModel.this.expand.postValue(false);
            } else {
                ControlModel.this.expand.postValue(true);
            }
        }
    };
    public View.OnClickListener onReModel = new View.OnClickListener() {
        public void onClick(View view) {
            ControlModel.this.changeModel(view.getId());
            ControlModel.this.expand.setValue(false);
        }
    };
    public MutableLiveData<Integer> overlayRes = new MutableLiveData<>();
    public MutableLiveData<Boolean> overlayVisible = new MutableLiveData<>();
    public ObservableField<String> perSize = new ObservableField<>();
    public byte port;
    public ObservableBoolean powerOff = new ObservableBoolean();
    public ObservableBoolean powerVisibility = new ObservableBoolean();
    private Disposable refresh;
    public boolean refreshStopped;
    private Disposable refreshV3Mode;
    /* access modifiers changed from: private */
    public DeviceRepository repository;
    public MutableLiveData<Integer> residueFlag = new MutableLiveData<>();
    public MutableLiveData<Boolean> schedModeVisible = new MutableLiveData<>();
    public ObservableField<String> tmpFlag = new ObservableField<>();
    public ObservableField<String> tmpSize = new ObservableField<>();
    private byte type;
    private byte version;
    public MutableLiveData<Boolean> vpdModeVisible = new MutableLiveData<>();
    public ObservableField<String> vpdSize = new ObservableField<>();
    public ObservableBoolean vpdVisibility = new ObservableBoolean();

    public ControlModel(Application application) {
        super(application);
        this.expand.setValue(false);
    }

    private final class ControlHandler extends Handler {
        ControlHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 16) {
                ControlModel.this.start();
            }
        }
    }

    /* access modifiers changed from: private */
    public void changeModel(int i) {
        cancelUpdate();
        if (i == C1760R.C1763id.txt_mode_off) {
            sendModel((byte) 1);
        } else if (i == C1760R.C1763id.txt_mode_on) {
            sendModel((byte) 2);
        } else if (i == C1760R.C1763id.txt_mode_auto) {
            sendModel((byte) 3);
        } else if (i == C1760R.C1763id.txt_mode_time_to_on) {
            sendModel((byte) 4);
        } else if (i == C1760R.C1763id.txt_mode_time_to_off) {
            sendModel((byte) 5);
        } else if (i == C1760R.C1763id.txt_mode_cycle) {
            sendModel((byte) 6);
        } else if (i == C1760R.C1763id.txt_mode_sched) {
            sendModel((byte) 7);
        } else if (i == C1760R.C1763id.txt_mode_vpd) {
            sendModel((byte) 8);
        }
    }

    public void init(final DeviceRepository deviceRepository, final String str, String str2, byte b, byte b2, byte b3, byte b4) {
        this.repository = deviceRepository;
        this.mac = str;
        this.devId = str2;
        this.port = b;
        this.version = b3;
        this.type = b2;
        this.connectType = b4;
        this.connected.setValue(Boolean.valueOf(deviceRepository.isConnect(str)));
        this.isStart = true;
        addSubscribe(deviceRepository.getModel(str, b).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
                if (deviceRepository.isConnect(str)) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }
        }).subscribe(new Consumer<DeviceModel>() {
            public void accept(DeviceModel deviceModel) {
                ControlModel.this.info = deviceModel;
                ControlModel.this.lastNetMode = new NetDeviceMode(deviceModel);
                ControlModel controlModel = ControlModel.this;
                controlModel.fallbackNetMode = controlModel.lastNetMode.clone();
                RxBus.getDefault().post(new ActivityEvent(1, deviceModel));
                ControlModel.this.cycleModel.setValue(Byte.valueOf(deviceModel.workType));
                if (deviceRepository.isConnect(str)) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    ControlModel.this.isSetDragState = false;
                }
                ControlModel.this.start();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                KLog.m65e(th);
            }
        }));
        register();
    }

    public void onPause() {
        stop();
    }

    public void onResume() {
        if (this.repository.isConnect(this.mac)) {
            start();
        }
    }

    public void sendModel(byte b) {
        Messenger.getDefault().send(this, ControlFragment.SCROLL_TO_TOP);
        KLog.m65e("控制页 开始设置模式");
        if (!this.repository.isConnect(this.mac)) {
            this.cycleModel.setValue(Byte.valueOf(b));
            this.info.workType = b;
        } else if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            setModelForNet(b);
        } else {
            setModelForBle(b);
        }
    }

    public void choosePort(byte b) {
        KLog.m65e("控制页 开始切换端口");
        if (!this.repository.isConnect(this.mac)) {
            return;
        }
        if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            choosePortForNet(this.devId, b);
        } else {
            choosePortForBle(b);
        }
    }

    private void choosePortForNet(String str, byte b) {
        this.port = b;
        cancelUpdate();
        RxHttpUtils.cancel(TAG_CHOOSE_PORT);
        ApiHelper.getDeviceApi().updateMsterPort(str, b).subscribeOn(Schedulers.m983io()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            /* access modifiers changed from: protected */
            public String setTag() {
                return ControlModel.TAG_CHOOSE_PORT;
            }

            public void doOnSubscribe(Disposable disposable) {
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                ControlModel.this.showFailDialog(str);
                ControlModel controlModel = ControlModel.this;
                controlModel.updateView(controlModel.fallbackNetMode, true);
                KLog.m65e(str);
                ControlModel.this.start();
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                if (ControlModel.this.lastNetMode != null) {
                    ControlModel controlModel = ControlModel.this;
                    controlModel.fallbackNetMode = controlModel.lastNetMode.clone();
                }
                ControlModel.this.dismissDialog();
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                ControlModel.this.fetchDevices(false);
            }
        });
    }

    private void choosePortForBle(byte b) {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            cancelUpdate();
            addSubscribe(this.repository.setChoosePort(connect, b).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    if (!bool.booleanValue()) {
                        KLog.m65e("choose port error");
                    }
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    ControlModel.this.start(false);
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    th.printStackTrace();
                    KLog.m65e(th);
                    ControlModel.this.start();
                }
            }));
        }
    }

    private void setModelForBle(byte b) {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            addSubscribe(this.repository.setModel(connect, this.port, b).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) throws Exception {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    ControlModel.this.start();
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    KLog.m65e("控制页 设置模式成功");
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    ControlModel.this.start();
                    th.printStackTrace();
                    KLog.m65e(th);
                }
            }));
        }
    }

    public void cancelUpdate() {
        if (this.repository.isConnect(this.mac)) {
            RxBus.getDefault().post(new ProgressEvent((byte) 0));
        }
        stop();
    }

    public void sendData() {
        sendData(false, (Consumer<Boolean>) null);
    }

    public void sendData(boolean z, Consumer<Boolean> consumer) {
        boolean z2;
        if (this.cycleModel.getValue() != null) {
            switch (this.cycleModel.getValue().byteValue()) {
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                    z2 = true;
                    break;
            }
        }
        z2 = false;
        if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            NetDeviceMode netDeviceMode = this.lastNetMode;
            if (netDeviceMode != null) {
                netDeviceMode.update(this.info);
                sendDataForNet(this.lastNetMode, true, consumer, z);
                return;
            }
            return;
        }
        sendDataForBle(z2, consumer);
    }

    public void sendDataForBle(final boolean z, Consumer<Boolean> consumer) {
        DeviceModel deviceModel;
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null && (deviceModel = this.info) != null) {
            this.refreshStopped = false;
            Single<R> doOnError = this.repository.setModel(connect, this.port, deviceModel).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    ControlModel.this.start(z);
                }
            }).doOnError(new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                }
            });
            if (consumer == null) {
                doOnError.subscribe();
            } else {
                addSubscribe(doOnError.subscribe((Consumer<? super R>) consumer));
            }
        }
    }

    private void sendDataForNet(final NetDeviceMode netDeviceMode, final boolean z, final Consumer<Boolean> consumer, boolean z2) {
        KLog.m65e("控制页 发送设置信息 isDelay：" + z);
        ApiHelper.getDeviceApi().setModel(BeanUtils.transBean2Map(netDeviceMode)).subscribeOn(Schedulers.m983io()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
            }
        }).observeOn(AndroidSchedulers.mainThread()).doOnNext(new Consumer<BaseData<Void>>() {
            public void accept(BaseData<Void> baseData) throws Exception {
                Consumer consumer = consumer;
                if (consumer != null) {
                    consumer.accept(Boolean.valueOf(baseData.getCode() == 200));
                }
            }
        }).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                Consumer consumer = consumer;
                if (consumer != null) {
                    consumer.accept(false);
                }
            }
        }).subscribe(new DataObserver<Void>() {
            /* access modifiers changed from: protected */
            public String setTag() {
                return ControlModel.TAG_SET_MODE;
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                KLog.m65e(str);
                ControlModel controlModel = ControlModel.this;
                controlModel.updateView(controlModel.fallbackNetMode, true);
                ControlModel.this.showFailDialog(str);
                ControlModel.this.start();
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                ControlModel.this.fallbackNetMode = netDeviceMode.clone();
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                ControlModel.this.dismissDialog();
                ControlModel.this.start(z);
            }
        });
    }

    private void setModelForNet(byte b) {
        NetDeviceMode netDeviceMode = this.lastNetMode;
        if (netDeviceMode == null) {
            start();
            return;
        }
        netDeviceMode.atType = b;
        DeviceModel deviceModel = this.info;
        if (deviceModel != null) {
            deviceModel.update(this.lastNetMode);
            this.cycleModel.setValue(Byte.valueOf(this.info.workType));
        }
        RxHttpUtils.cancel(TAG_SET_MODE);
        sendDataForNet(this.lastNetMode, true, (Consumer<Boolean>) null, false);
    }

    public void save() {
        DeviceModel deviceModel = this.info;
        if (deviceModel != null) {
            deviceModel.workType = this.cycleModel.getValue() != null ? this.cycleModel.getValue().byteValue() : 0;
            this.repository.saveModel(this.mac, this.port, this.info).subscribeOn(Schedulers.m983io()).subscribe();
        }
    }

    public void onDestroy() {
        save();
        if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            RxHttpUtils.cancel(TAG_CHOOSE_PORT);
        }
    }

    /* access modifiers changed from: private */
    public void updateInfo(DeviceMinModel deviceMinModel) {
        DeviceModel deviceModel = this.info;
        if (deviceModel != null) {
            this.lastInfo = deviceModel.clone();
            this.info.choosePort = deviceMinModel.choosePort;
            setPortList(deviceMinModel.portList);
            this.port = deviceMinModel.choosePort;
            this.info.isDegree = deviceMinModel.isDegree;
            this.info.fanType = deviceMinModel.fanType;
            this.info.tmp = deviceMinModel.tmp;
            this.info.hum = deviceMinModel.hum;
            this.info.fan = deviceMinModel.fan;
            this.info.fanState = deviceMinModel.fanState;
            this.info.humState = deviceMinModel.humState;
            this.info.tmpState = deviceMinModel.tmpState;
            this.info.vpd = deviceMinModel.vpd;
            this.info.vpdState = deviceMinModel.vpdState;
            updateMinModel(deviceMinModel);
        }
    }

    /* access modifiers changed from: package-private */
    public void setPortList(List<PortInfo> list) {
        DeviceModel deviceModel = this.info;
        if (deviceModel != null && deviceModel.portList != null && this.info.portList.size() > 0 && list != null && list.size() > 0) {
            for (PortInfo next : this.info.portList) {
                Iterator<PortInfo> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    PortInfo next2 = it.next();
                    if (next.f138id == next2.f138id) {
                        next2.name = next.name;
                        next2.portType = next.portType;
                        if (this.info.port == next2.f138id) {
                            this.info.fanType = next2.fanType;
                        }
                    }
                }
            }
            this.info.portList = list;
        }
    }

    private void updateMinModel(DeviceMinModel deviceMinModel) {
        DeviceModel deviceModel = this.info;
        if (deviceModel != null) {
            deviceModel.isDegree = deviceMinModel.isDegree;
            this.info.workType = deviceMinModel.model;
            this.info.typeOn = deviceMinModel.typeOn;
            this.info.typeOff = deviceMinModel.typeOff;
            switch (deviceMinModel.model) {
                case 2:
                    this.info.typeOn = deviceMinModel.getFan();
                    return;
                case 3:
                    this.info.autoHighTmpSwitch = deviceMinModel.hTmpSwitch();
                    this.info.autoLowTmpSwitch = deviceMinModel.lTmpSwitch();
                    this.info.autoHighTmp = deviceMinModel.getHighTmp();
                    this.info.autoLowTmp = deviceMinModel.getLowTmp();
                    this.info.autoHighHumSwitch = deviceMinModel.hHumSwitch();
                    this.info.autoLowHumSwitch = deviceMinModel.lHumSwitch();
                    this.info.autoHighHum = deviceMinModel.getHighHum();
                    this.info.autoLowHum = deviceMinModel.getLowHum();
                    this.info.transitionTemperature = deviceMinModel.transitionTemperature;
                    this.info.transitionTemperatureC = deviceMinModel.transitionTemperatureC;
                    this.info.transitionHumidity = deviceMinModel.transitionHumidity;
                    return;
                case 4:
                    this.info.timeOn = deviceMinModel.getTime();
                    this.info.currentTypeResidueTime = (int) deviceMinModel.time.time;
                    this.info.currentTypeResidueOn = !deviceMinModel.time.isOff;
                    return;
                case 5:
                    this.info.timeOff = deviceMinModel.getTime();
                    this.info.currentTypeResidueTime = (int) deviceMinModel.time.time;
                    this.info.currentTypeResidueOn = !deviceMinModel.time.isOff;
                    return;
                case 6:
                    this.info.cycleOn = deviceMinModel.getOn();
                    this.info.cycleOff = deviceMinModel.getOff();
                    this.info.currentTypeResidueTime = (int) deviceMinModel.time.time;
                    this.info.currentTypeResidueOn = !deviceMinModel.time.isOff;
                    return;
                case 7:
                    this.info.schedOn = deviceMinModel.getStart();
                    this.info.schedOff = deviceMinModel.getEnd();
                    this.info.currentTypeResidueTime = (int) deviceMinModel.time.time;
                    this.info.currentTypeResidueOn = !deviceMinModel.time.isOff;
                    return;
                case 8:
                    this.info.highVpdSwitch = deviceMinModel.hVpdSwitch();
                    this.info.lowVpdSwitch = deviceMinModel.lVpdSwitch();
                    this.info.highVpd = deviceMinModel.getHighVpd();
                    this.info.lowVpd = deviceMinModel.getLowVpd();
                    return;
                default:
                    this.info.typeOff = deviceMinModel.getFan();
                    return;
            }
        }
    }

    public void setControlTypeByHum(boolean z) {
        this.repository.setControlTypeByHum(this.mac, this.port, z).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).subscribe();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void start() {
        this.refreshStopped = false;
        if (!this.repository.isConnect(this.mac)) {
            this.isRefreshComplete = true;
        } else if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            startNet();
        } else {
            startBle();
        }
    }

    public void start(boolean z) {
        this.refreshStopped = false;
        if (z) {
            RxHttpUtils.cancel(TAG_FETCH_MODE);
            this.handler.removeMessages(16);
            Message obtainMessage = this.handler.obtainMessage();
            obtainMessage.what = 16;
            this.handler.sendMessageDelayed(obtainMessage, 3000);
            return;
        }
        start();
    }

    private void startBle() {
        final BleStatue connect = this.repository.getConnect(this.mac);
        if (connect == null) {
            this.isRefreshComplete = true;
        } else if (this.refresh == null) {
            KLog.m65e("控制页 开始刷新");
            this.refresh = Single.create(new SingleOnSubscribe<Byte>() {
                public void subscribe(SingleEmitter<Byte> singleEmitter) throws Exception {
                    singleEmitter.onSuccess(Byte.valueOf(ControlModel.this.port));
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(Schedulers.m983io()).flatMap(new Function<Byte, SingleSource<DeviceMinModel>>() {
                public SingleSource<DeviceMinModel> apply(Byte b) throws Exception {
                    return ControlModel.this.repository.getModel(connect, b.byteValue());
                }
            }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).repeatWhen(new Function<Flowable<Object>, Publisher<?>>() {
                public Publisher<?> apply(Flowable<Object> flowable) throws Exception {
                    return flowable.delay(900, TimeUnit.MILLISECONDS);
                }
            }).subscribe(new Consumer<DeviceMinModel>() {
                public void accept(DeviceMinModel deviceMinModel) {
                    KLog.m65e("控制页 刷新成功");
                    if (ControlModel.this.info != null) {
                        ControlModel.this.updateInfo(deviceMinModel);
                        ControlModel.this.cycleModel.setValue(Byte.valueOf(deviceMinModel.model));
                        ControlModel.this.isRefreshComplete = true;
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                    KLog.m65e(th);
                    ControlModel.this.isRefreshComplete = true;
                }
            });
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void stop() {
        if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            RxHttpUtils.cancel(TAG_FETCH_MODE);
            RxHttpUtils.cancel(TAG_SET_MODE);
            dismissDialog();
            this.handler.removeMessages(16);
        } else {
            if (this.refresh != null) {
                KLog.m65e("control refresh end ");
                this.refresh.dispose();
                this.refresh = null;
            }
            Disposable disposable = this.refreshV3Mode;
            if (disposable != null) {
                disposable.dispose();
                this.refreshV3Mode = null;
            }
        }
        this.refreshStopped = true;
    }

    /* access modifiers changed from: private */
    public void fetchDevices(final boolean z) {
        Observable<R> observable;
        RxHttpUtils.cancel(TAG_FETCH_MODE);
        if (z) {
            observable = ApiHelper.getDeviceApi().getDeviceInfo(this.devId).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<BaseData<NetDevice>, Byte>() {
                public Byte apply(BaseData<NetDevice> baseData) throws Exception {
                    if (baseData.getCode() != 200) {
                        return Byte.valueOf(ControlModel.this.port);
                    }
                    if (baseData.getData() == null || baseData.getData().deviceInfo == null) {
                        return Byte.valueOf(ControlModel.this.port);
                    }
                    ControlModel.this.setPortList(baseData.getData().deviceInfo.getPortList());
                    return Byte.valueOf(baseData.getData().deviceInfo.choosePort);
                }
            }).observeOn(Schedulers.m983io()).concatMap(new Function<Byte, ObservableSource<BaseData<NetDeviceMode>>>() {
                public ObservableSource<BaseData<NetDeviceMode>> apply(Byte b) throws Exception {
                    return ApiHelper.getDeviceApi().getModeSettingList(ControlModel.this.devId, b.byteValue()).subscribeOn(Schedulers.m983io());
                }
            }).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                public ObservableSource<?> apply(Observable<Throwable> observable) throws Exception {
                    return observable.delay(1000, TimeUnit.MILLISECONDS);
                }
            }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                public ObservableSource<?> apply(Observable<Object> observable) throws Exception {
                    return observable.delay(1000, TimeUnit.MILLISECONDS);
                }
            });
        } else {
            observable = Single.create(new SingleOnSubscribe<Byte>() {
                public void subscribe(SingleEmitter<Byte> singleEmitter) throws Exception {
                    singleEmitter.onSuccess(Byte.valueOf(ControlModel.this.port));
                }
            }).flatMapObservable(new Function<Byte, ObservableSource<BaseData<NetDeviceMode>>>() {
                public ObservableSource<BaseData<NetDeviceMode>> apply(Byte b) throws Exception {
                    return ApiHelper.getDeviceApi().getModeSettingList(ControlModel.this.devId, ControlModel.this.port).subscribeOn(Schedulers.m983io());
                }
            }).subscribeOn(Schedulers.m983io());
        }
        observable.subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<NetDeviceMode>() {
            /* access modifiers changed from: protected */
            public String setTag() {
                return ControlModel.TAG_FETCH_MODE;
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                KLog.m65e(str);
                ControlModel.this.isRefreshComplete = true;
                if (!z) {
                    ControlModel.this.start(true);
                }
            }

            /* access modifiers changed from: protected */
            public void onSuccess(NetDeviceMode netDeviceMode) {
                ControlModel.this.netMode = netDeviceMode;
                ControlModel.this.fallbackNetMode = netDeviceMode.clone();
                ControlModel.this.updateView(netDeviceMode, z);
                ControlModel.this.automationActivated.setValue(Boolean.valueOf(netDeviceMode.isOpenAutomation != 0));
                ControlModel.this.isRefreshComplete = true;
                if (!z) {
                    ControlModel.this.start(true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateView(NetDeviceMode netDeviceMode, boolean z) {
        if (netDeviceMode != null) {
            if (this.info != null) {
                if (z) {
                    this.port = netDeviceMode.externalPort;
                }
                this.info.update(netDeviceMode);
                this.cycleModel.setValue(Byte.valueOf(this.info.workType));
            }
            this.lastNetMode = netDeviceMode.clone();
        }
    }

    public void startNet() {
        fetchDevices(true);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        this.handler.removeMessages(16);
        super.onCleared();
    }

    public void register() {
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) throws Exception {
                if (activityEvent.what == 3) {
                    byte byteValue = ((Byte) activityEvent.obj).byteValue();
                    if (ControlModel.this.info != null) {
                        ControlModel.this.info.leafTemperatureC = byteValue;
                    }
                } else if (activityEvent.what == 4) {
                    ControlModel.this.automationActivated.setValue(Boolean.valueOf(((Boolean) activityEvent.obj).booleanValue()));
                }
            }
        }));
    }
}
