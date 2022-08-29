package com.eternal.control;

import android.app.Application;
import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.data.DeviceRepository;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.RxUtils;
import p014io.reactivex.Single;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class ControlCModel extends BaseViewModel {
    public MutableLiveData<Boolean> connected = new MutableLiveData<>();
    public MutableLiveData<Boolean> controlTypeByHumModel = new MutableLiveData<>();
    public String devId;
    public ObservableField<String> hintText = new ObservableField<>();
    public DeviceModel info;
    public boolean isRefreshComplete;
    public DeviceModel lastInfo;
    public String mac;
    public BindingCommand<Void> onCloseOverlay = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ControlCModel.this.overlayVisible.setValue(false);
        }
    });
    public MutableLiveData<Integer> overlayRes = new MutableLiveData<>();
    public MutableLiveData<Boolean> overlayVisible = new MutableLiveData<>();
    public ObservableField<String> perSize = new ObservableField<>();
    private byte port;
    private Disposable refresh;
    public boolean refreshStopped;
    private DeviceRepository repository;
    public MutableLiveData<String> show = new MutableLiveData<>();
    public ObservableField<String> tmpFlag = new ObservableField<>();
    public ObservableField<String> tmpSize = new ObservableField<>();
    public ObservableField<String> vpdSize = new ObservableField<>();

    public ControlCModel(Application application) {
        super(application);
    }

    public void init(final DeviceRepository deviceRepository, final String str, String str2, byte b) {
        this.repository = deviceRepository;
        this.mac = str;
        this.devId = str2;
        this.port = b;
        this.connected.setValue(Boolean.valueOf(deviceRepository.isConnect(str)));
        deviceRepository.getModel(str, b).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
                if (deviceRepository.isConnect(str)) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }
        }).subscribe(new Consumer<DeviceModel>() {
            public void accept(DeviceModel deviceModel) {
                ControlCModel.this.info = deviceModel;
                Messenger.getDefault().sendNoMsg(ControlCFragment.REFRESH);
                if (deviceRepository.isConnect(str)) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                }
                ControlCModel.this.start();
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
                KLog.m65e(th);
            }
        });
        register();
    }

    public void register() {
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) throws Exception {
                if (activityEvent.what == 3) {
                    byte byteValue = ((Byte) activityEvent.obj).byteValue();
                    if (ControlCModel.this.info != null) {
                        ControlCModel.this.info.leafTemperatureC = byteValue;
                    }
                }
            }
        }));
    }

    public void sendData() {
        sendData((Consumer<Boolean>) null);
    }

    public void sendData(Consumer<Boolean> consumer) {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            this.refreshStopped = false;
            Single<R> doOnError = this.repository.setCModel(connect, this.info).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    ControlCModel.this.start();
                }
            }).doOnError(new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                }
            });
            if (consumer == null) {
                doOnError.subscribe();
            } else {
                doOnError.subscribe((Consumer<? super R>) consumer);
            }
        }
    }

    public void cancelUpdate() {
        stop();
        if (this.repository.isConnect(this.mac)) {
            RxBus.getDefault().post(new ProgressEvent((byte) 0));
        }
    }

    public void setControlTypeByHum(boolean z) {
        this.repository.setControlTypeByHum(this.mac, z).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).subscribe();
    }

    /* access modifiers changed from: package-private */
    public void start() {
        this.refreshStopped = false;
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            Disposable disposable = this.refresh;
            if (disposable == null || disposable.isDisposed()) {
                this.refresh = this.repository.getCModel(connect).subscribe(new Consumer<DeviceModel>() {
                    public void accept(DeviceModel deviceModel) throws Exception {
                        if (deviceModel != null && ControlCModel.this.info != null) {
                            ControlCModel controlCModel = ControlCModel.this;
                            controlCModel.lastInfo = controlCModel.info.clone();
                            deviceModel.isControlTypeByHum = ControlCModel.this.info.isControlTypeByHum;
                            deviceModel.leafTemperatureC = ControlCModel.this.info.leafTemperatureC;
                            ControlCModel.this.info = deviceModel;
                            Messenger.getDefault().sendNoMsg(ControlCFragment.REFRESH);
                            ControlCModel.this.isRefreshComplete = true;
                        }
                    }
                }, new Consumer<Throwable>() {
                    public void accept(Throwable th) throws Exception {
                        KLog.m65e(th);
                        ControlCModel.this.isRefreshComplete = true;
                    }
                });
                return;
            }
            return;
        }
        this.isRefreshComplete = true;
    }

    /* access modifiers changed from: package-private */
    public void stop() {
        Disposable disposable = this.refresh;
        if (disposable != null && !disposable.isDisposed()) {
            this.refresh.dispose();
            this.refresh = null;
        }
        this.refreshStopped = true;
    }

    public void save() {
        this.repository.saveModel(this.mac, (byte) 0, this.info).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public boolean isConnect() {
        return this.repository.isConnect(this.mac);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        stop();
        super.onCleared();
    }
}
