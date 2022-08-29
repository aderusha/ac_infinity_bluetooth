package com.eternal.notification.model;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import androidx.lifecycle.MutableLiveData;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.NetAdvance;
import com.eternal.base.data.NotificationRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.ByteUtils;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.BeanUtils;
import com.eternal.framework.utils.RxUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.notification.C2420R;
import com.eternal.notification.p008ui.NotificationActivity;
import com.eternal.notification.p008ui.OtherFragmentV4;
import com.eternal.res.C2663R;
import com.google.common.base.Ascii;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Maybe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Single;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class OtherModelV4 extends BaseViewModel {
    public MutableLiveData<Byte> alarmType = new MutableLiveData<>();
    public MutableLiveData<Boolean> auto = new MutableLiveData<>();
    public MutableLiveData<String> beepText = new MutableLiveData<>();
    public byte connectType;
    public MutableLiveData<Boolean> cycle = new MutableLiveData<>();
    public MutableLiveData<String> deleteText = new MutableLiveData<>();
    public String devId;
    /* access modifiers changed from: private */
    public byte deviceType;
    public MutableLiveData<Boolean> disClickable = new MutableLiveData<>();
    public String edit;
    public MutableLiveData<Byte> hHum = new MutableLiveData<>();
    public Byte hHumSaved;
    public MutableLiveData<Boolean> hHumSwitch = new MutableLiveData<>();
    public MutableLiveData<Character> hTmp = new MutableLiveData<>();
    public Character hTmpSaved;
    public MutableLiveData<Boolean> hTmpSwitch = new MutableLiveData<>();
    public MutableLiveData<Byte> hVpd = new MutableLiveData<>();
    public Byte hVpdSaved;
    public MutableLiveData<Boolean> hVpdSwitch = new MutableLiveData<>();
    public MutableLiveData<Boolean> hum = new MutableLiveData<>();
    public MutableLiveData<Boolean> humVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> isCreate = new MutableLiveData<>();
    public boolean isDegree;
    public MutableLiveData<Byte> lHum = new MutableLiveData<>();
    public Byte lHumSaved;
    public MutableLiveData<Boolean> lHumSwitch = new MutableLiveData<>();
    public MutableLiveData<Character> lTmp = new MutableLiveData<>();
    public Character lTmpSaved;
    public MutableLiveData<Boolean> lTmpSwitch = new MutableLiveData<>();
    public MutableLiveData<Byte> lVpd = new MutableLiveData<>();
    public Byte lVpdSaved;
    public MutableLiveData<Boolean> lVpdSwitch = new MutableLiveData<>();
    public MutableLiveData<Boolean> modes = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public BindingCommand<String> nameChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            MutableLiveData<String> mutableLiveData = OtherModelV4.this.nameSuggest;
            mutableLiveData.setValue("NAME (" + str.length() + "/20)");
        }
    });
    public MutableLiveData<String> nameEdit = new MutableLiveData<>();
    public MutableLiveData<String> nameSuggest = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public Notification notification;
    private byte notifyType;
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Detail.PAGE_DETAIL).withTransition(C2420R.anim.left_in, C2420R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
                public void onArrival(Postcard postcard) {
                    OtherModelV4.this.finish();
                }
            });
        }
    });
    public BindingCommand<Void> onConfirm = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!RepositoryInjection.providerDeviceRepository().isConnect(OtherModelV4.this.notification.mac)) {
                OtherModelV4 otherModelV4 = OtherModelV4.this;
                otherModelV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(otherModelV4.deviceType, OtherModelV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
            } else if (OtherModelV4.this.saveDis != null && !OtherModelV4.this.saveDis.isDisposed()) {
            } else {
                if (OtherModelV4.this.name.getValue() == null || TextUtils.isEmpty(OtherModelV4.this.name.getValue().trim())) {
                    OtherModelV4.this.showFailDialog(Utils.getString(C2420R.string.tip_empty_name));
                    OtherModelV4.this.name.setValue(OtherModelV4.this.originName);
                } else if (ProtocolTransformer.isWorkWiFi(OtherModelV4.this.deviceType, OtherModelV4.this.connectType)) {
                    OtherModelV4.this.saveForNet();
                } else {
                    OtherModelV4.this.saveForBle();
                }
            }
        }
    });
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().sendNoMsg(NotificationActivity.SHOW_DELETE_DIALOG);
        }
    });
    public String originName;
    public MutableLiveData<Boolean> port1 = new MutableLiveData<>();
    public MutableLiveData<Boolean> port2 = new MutableLiveData<>();
    public MutableLiveData<Boolean> port3 = new MutableLiveData<>();
    public MutableLiveData<Boolean> port4 = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public NotificationRepository repository;
    public Disposable saveDis;
    public MutableLiveData<Boolean> schedule = new MutableLiveData<>();
    public MutableLiveData<Boolean> scheduleVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeOff = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeOn = new MutableLiveData<>();
    public MutableLiveData<Boolean> tmp = new MutableLiveData<>();
    public MutableLiveData<String> tmpUnit = new MutableLiveData<>();
    private byte version;
    public MutableLiveData<Boolean> vpd = new MutableLiveData<>();
    public MutableLiveData<Boolean> vpdMode = new MutableLiveData<>();
    public MutableLiveData<Boolean> vpdModeVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> vpdVisible = new MutableLiveData<>();

    public OtherModelV4(Application application) {
        super(application);
        String string = Utils.getString(C2420R.string.tip_edit_lowercase);
        this.edit = string;
        this.nameEdit.setValue(string);
    }

    /* access modifiers changed from: private */
    public void saveForNet() {
        Observable<BaseData<Void>> observable;
        final Notification create = create();
        if (TextUtils.isEmpty(create.advId)) {
            create.devId = this.devId;
        }
        final boolean z = this.isCreate.getValue() != null && this.isCreate.getValue() == Boolean.TRUE;
        RxBus.getDefault().post(new ProgressEvent((byte) 0));
        if (z) {
            observable = this.repository.getName(create, true, this.version).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).flatMapObservable(new Function<Notification, ObservableSource<BaseData<Void>>>() {
                public ObservableSource<BaseData<Void>> apply(Notification notification) throws Exception {
                    return ApiHelper.getDeviceApi().addAdvInfo(BeanUtils.transBean2Map(new NetAdvance(notification)));
                }
            });
        } else {
            observable = ApiHelper.getDeviceApi().updateAdvInfo(BeanUtils.transBean2Map(new NetAdvance(create)));
        }
        if (this.disClickable.getValue() != Boolean.TRUE) {
            this.disClickable.setValue(true);
            observable.subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                public void doOnSubscribe(Disposable disposable) {
                    OtherModelV4.this.showDialog((String) null, disposable);
                    OtherModelV4.this.saveDis = disposable;
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    OtherModelV4.this.showFailDialog(str);
                    OtherModelV4.this.disClickable.setValue(false);
                }

                /* access modifiers changed from: protected */
                public void onSuccess(Void voidR) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    Notification unused = OtherModelV4.this.notification = create;
                    OtherModelV4.this.repository.setNotification(OtherModelV4.this.notification, z);
                    OtherModelV4.this.addSubscribe(Maybe.just(true).delay(1500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Boolean>() {
                        public void accept(Boolean bool) throws Exception {
                            OtherModelV4.this.back();
                            OtherModelV4.this.dismissDialog();
                        }
                    }));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void saveForBle() {
        final Notification create = create();
        boolean z = this.isCreate.getValue() == Boolean.TRUE;
        if (z || create.type == this.notifyType) {
            saveForBle(create, z);
        } else {
            removeForBle(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    if (bool.booleanValue()) {
                        OtherModelV4.this.saveForBle(create, true);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void saveForBle(final Notification notification2, boolean z) {
        Single<Boolean> single;
        BleStatue connect = this.repository.getConnect(this.notification.mac);
        if (connect != null) {
            if (z) {
                single = this.repository.saveNotification(connect, notification2, this.isCreate.getValue() == Boolean.TRUE);
            } else {
                single = this.repository.setNotification(connect, notification2);
            }
            this.saveDis = single.compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    if (bool.booleanValue()) {
                        Notification unused = OtherModelV4.this.notification = notification2;
                        OtherModelV4.this.back();
                    }
                }
            });
        }
    }

    public String getNotifyName() {
        return this.notification.name;
    }

    /* access modifiers changed from: private */
    public void back() {
        RxBus.getDefault().post(new ActivityEvent(0));
        ARouter.getInstance().build(RouterActivityPath.Detail.PAGE_DETAIL).withTransition(C2420R.anim.left_in, C2420R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                OtherModelV4.this.finish();
            }
        });
    }

    public void init(NotificationRepository notificationRepository, String str, String str2, byte b, byte b2, Notification notification2, boolean z, byte b3, byte b4, byte b5) {
        this.repository = notificationRepository;
        this.isDegree = z;
        this.devId = str2;
        this.deviceType = b2;
        this.version = b4;
        this.connectType = b5;
        boolean z2 = true;
        if (notification2 == null) {
            notification2 = new Notification();
            notification2.type = b3;
            notification2.mac = str;
            notification2.port = b;
            notification2.model = 0;
            notification2.name = Utils.getString(C2420R.string.tip_default_alerts_name);
            notification2.lHum = ActivityEvent.REFRESH_SHARE_DOT;
            notification2.hHum = 90;
            if (z) {
                notification2.lTmpC = 0;
                notification2.hTmpC = 90;
                notification2.lTmpF = 32;
                notification2.hTmpF = -62;
            } else {
                notification2.lTmpF = ActivityEvent.REFRESH_SHARE_DOT;
                notification2.hTmpF = 110;
                notification2.lTmpC = (byte) Math.round(ProtocolTransformer.getDegree(40.0f));
                notification2.hTmpC = (byte) Math.round(ProtocolTransformer.getDegree(110.0f));
            }
            notification2.lVpd = 0;
            notification2.hVpd = 99;
            notification2.tmpHum = 63;
            notification2.open = true;
            this.isCreate.setValue(true);
        } else {
            this.isCreate.setValue(false);
            this.notifyType = notification2.type;
        }
        this.notification = notification2;
        init(notification2);
        this.deleteText.setValue("DELETE ALERTS");
        if (b4 >= 3 && b2 != 6) {
            this.vpdVisible.setValue(true);
        }
        if (b2 == 11) {
            this.vpdModeVisible.setValue(true);
        }
        this.humVisible.setValue(Boolean.valueOf(b2 != 6));
        MutableLiveData<Boolean> mutableLiveData = this.scheduleVisible;
        if (b2 == 6) {
            z2 = false;
        }
        mutableLiveData.setValue(Boolean.valueOf(z2));
        Messenger.getDefault().sendNoMsg(OtherFragmentV4.UPDATE_PARAM);
    }

    private void init(Notification notification2) {
        this.name.setValue(notification2.name);
        this.originName = notification2.name;
        boolean z = false;
        this.tmp.setValue(false);
        this.hum.setValue(false);
        this.modes.setValue(false);
        this.vpd.setValue(false);
        if (this.version >= 4) {
            if (!ByteUtils.getBit(notification2.model, 2)) {
                this.tmp.setValue(true);
                this.hTmpSwitch.setValue(Boolean.valueOf((2 & notification2.tmpHum) != 0));
                MutableLiveData<Boolean> mutableLiveData = this.lTmpSwitch;
                if ((notification2.tmpHum & 1) != 0) {
                    z = true;
                }
                mutableLiveData.setValue(Boolean.valueOf(z));
            } else if (!ByteUtils.getBit(notification2.model, 3)) {
                this.hum.setValue(true);
                this.hHumSwitch.setValue(Boolean.valueOf((2 & notification2.tmpHum) != 0));
                MutableLiveData<Boolean> mutableLiveData2 = this.lHumSwitch;
                if ((notification2.tmpHum & 1) != 0) {
                    z = true;
                }
                mutableLiveData2.setValue(Boolean.valueOf(z));
            } else if (!ByteUtils.getBit(notification2.model, 4)) {
                this.vpd.setValue(true);
                this.hVpdSwitch.setValue(Boolean.valueOf((2 & notification2.tmpHum) != 0));
                MutableLiveData<Boolean> mutableLiveData3 = this.lVpdSwitch;
                if ((notification2.tmpHum & 1) != 0) {
                    z = true;
                }
                mutableLiveData3.setValue(Boolean.valueOf(z));
            } else if (!ByteUtils.getBit(notification2.model, 5)) {
                this.modes.setValue(true);
            } else if (ByteUtils.getBit(notification2.model, 6)) {
                ByteUtils.getBit(notification2.model, 7);
            }
            this.alarmType.setValue(Byte.valueOf(notification2.beeps));
            this.port1.setValue(Boolean.valueOf(!ByteUtils.getBit(notification2.port, 7)));
            this.port2.setValue(Boolean.valueOf(!ByteUtils.getBit(notification2.port, 6)));
            this.port3.setValue(Boolean.valueOf(!ByteUtils.getBit(notification2.port, 5)));
            this.port4.setValue(Boolean.valueOf(!ByteUtils.getBit(notification2.port, 4)));
        } else {
            if ((notification2.model & 1) != 0) {
                if ((notification2.tmpHum & 4) == 0 && (notification2.tmpHum & 8) == 0 && ((notification2.tmpHum & 1) != 0 || (notification2.tmpHum & 2) != 0)) {
                    this.hum.setValue(true);
                } else {
                    this.tmp.setValue(true);
                }
            } else if ((notification2.model & 2) != 0 || (notification2.model & 4) != 0 || (notification2.model & 8) != 0 || (notification2.model & Ascii.DLE) != 0 || (notification2.model & 32) != 0 || (notification2.model & 128) != 0) {
                this.modes.setValue(true);
            } else if ((notification2.model & SignedBytes.MAX_POWER_OF_TWO) != 0) {
                this.vpd.setValue(true);
            }
            this.hVpdSwitch.setValue(Boolean.valueOf((notification2.tmpHum & 32) != 0));
            this.lVpdSwitch.setValue(Boolean.valueOf((notification2.tmpHum & Ascii.DLE) != 0));
            this.hTmpSwitch.setValue(Boolean.valueOf((notification2.tmpHum & 8) != 0));
            this.lTmpSwitch.setValue(Boolean.valueOf((4 & notification2.tmpHum) != 0));
            this.hHumSwitch.setValue(Boolean.valueOf((notification2.tmpHum & 2) != 0));
            MutableLiveData<Boolean> mutableLiveData4 = this.lHumSwitch;
            if ((notification2.tmpHum & 1) != 0) {
                z = true;
            }
            mutableLiveData4.setValue(Boolean.valueOf(z));
            this.alarmType.setValue(Byte.valueOf(notification2.type));
        }
        if (this.isDegree) {
            this.hTmp.setValue(Character.valueOf((char) notification2.hTmpC));
            this.lTmp.setValue(Character.valueOf((char) notification2.lTmpC));
        } else {
            this.hTmp.setValue(Character.valueOf((char) UnsignedBytes.toInt(notification2.hTmpF)));
            this.lTmp.setValue(Character.valueOf((char) UnsignedBytes.toInt(notification2.lTmpF)));
        }
        this.hTmpSaved = this.hTmp.getValue();
        this.lTmpSaved = this.lTmp.getValue();
        this.hHum.setValue(Byte.valueOf(notification2.hHum));
        this.lHum.setValue(Byte.valueOf(notification2.lHum));
        this.hHumSaved = this.hHum.getValue();
        this.lHumSaved = this.lHum.getValue();
        this.hVpd.setValue(Byte.valueOf(notification2.hVpd));
        this.lVpd.setValue(Byte.valueOf(notification2.lVpd));
        this.hVpdSaved = this.hVpd.getValue();
        this.lVpdSaved = this.lVpd.getValue();
    }

    public void delete() {
        if (RepositoryInjection.providerDeviceRepository().isConnect(this.notification.mac)) {
            if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
                removeForNet(new Consumer<Boolean>() {
                    public void accept(Boolean bool) {
                        RxBus.getDefault().post(new ProgressEvent((byte) 1));
                        OtherModelV4.this.dismissDialog();
                        OtherModelV4.this.back();
                    }
                });
            } else {
                removeForBle(new Consumer<Boolean>() {
                    public void accept(Boolean bool) throws Exception {
                        if (bool.booleanValue()) {
                            OtherModelV4.this.back();
                        }
                    }
                });
            }
        }
    }

    private void removeForBle(final Consumer<Boolean> consumer) {
        BleStatue connect = this.repository.getConnect(this.notification.mac);
        if (connect != null) {
            addSubscribe(this.repository.removeNotification(connect, this.notification, true).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    Consumer consumer = consumer;
                    if (consumer != null) {
                        consumer.accept(bool);
                    }
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                }
            }));
        }
    }

    private void removeForNet(final Consumer<Boolean> consumer) {
        ApiHelper.getDeviceApi().delADVInfo(this.devId, this.notification.advId).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
                OtherModelV4.this.showDialog((String) null, disposable);
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                OtherModelV4.this.showFailDialog(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                Consumer consumer = consumer;
                if (consumer != null) {
                    try {
                        consumer.accept(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private Notification create() {
        Notification clone = this.notification.clone();
        byte b = 0;
        if (this.version >= 4) {
            clone.type = 2;
            clone.beeps = this.alarmType.getValue().byteValue();
            clone.model = 0;
            clone.tmpHum = 0;
            if (this.tmp.getValue() == Boolean.TRUE) {
                clone.model = 32;
                if (this.hTmpSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 2);
                }
                if (this.lTmpSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 1);
                }
            } else if (this.hum.getValue() == Boolean.TRUE) {
                clone.model = Ascii.DLE;
                if (this.hHumSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 2);
                }
                if (this.lHumSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 1);
                }
            } else if (this.vpd.getValue() == Boolean.TRUE) {
                clone.model = 8;
                if (this.hVpdSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 2);
                }
                if (this.lVpdSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 1);
                }
            } else if (this.modes.getValue() == Boolean.TRUE) {
                clone.model = 4;
            }
            if (this.port1.getValue() == Boolean.TRUE) {
                b = (byte) 1;
            }
            if (this.port2.getValue() == Boolean.TRUE) {
                b = (byte) (b | 2);
            }
            if (this.port3.getValue() == Boolean.TRUE) {
                b = (byte) (b | 4);
            }
            if (this.port4.getValue() == Boolean.TRUE) {
                b = (byte) (b | 8);
            }
            clone.port = b;
        } else {
            clone.type = this.alarmType.getValue().byteValue();
            clone.model = 0;
            clone.tmpHum = 0;
            if (this.tmp.getValue() == Boolean.TRUE) {
                clone.model = 1;
                if (this.hTmpSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 8);
                }
                if (this.lTmpSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 4);
                }
            } else if (this.hum.getValue() == Boolean.TRUE) {
                clone.model = 1;
                if (this.hHumSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 2);
                }
                if (this.lHumSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 1);
                }
            } else if (this.modes.getValue() == Boolean.TRUE) {
                clone.model = (byte) (clone.model | 2);
                clone.model = (byte) (clone.model | 4);
                clone.model = (byte) (clone.model | 8);
                clone.model = (byte) (clone.model | Ascii.DLE);
                clone.model = (byte) (clone.model | 32);
                clone.model = (byte) (clone.model | 128);
            } else if (this.vpd.getValue() == Boolean.TRUE) {
                clone.model = (byte) (clone.model | SignedBytes.MAX_POWER_OF_TWO);
                if (this.hVpdSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | 32);
                }
                if (this.lVpdSwitch.getValue() == Boolean.TRUE) {
                    clone.tmpHum = (byte) (clone.tmpHum | Ascii.DLE);
                }
            }
        }
        if (Utils.getString(C2420R.string.tip_default_alerts_name).equals(this.name.getValue())) {
            clone.name = null;
        } else {
            clone.name = this.name.getValue() == null ? this.name.getValue() : this.name.getValue().trim();
        }
        clone.open = this.notification.open;
        if (this.isDegree) {
            char charValue = this.hTmp.getValue().charValue();
            clone.hTmpC = (byte) charValue;
            clone.hTmpF = (byte) Math.round(ProtocolTransformer.getFah((float) charValue));
            char charValue2 = this.lTmp.getValue().charValue();
            clone.lTmpC = (byte) charValue2;
            clone.lTmpF = (byte) Math.round(ProtocolTransformer.getFah((float) charValue2));
        } else {
            char charValue3 = this.hTmp.getValue().charValue();
            clone.hTmpF = (byte) charValue3;
            clone.hTmpC = (byte) Math.round(ProtocolTransformer.getDegree((float) charValue3));
            char charValue4 = this.lTmp.getValue().charValue();
            clone.lTmpF = (byte) charValue4;
            clone.lTmpC = (byte) Math.round(ProtocolTransformer.getDegree((float) charValue4));
        }
        clone.hHum = this.hHum.getValue().byteValue();
        clone.lHum = this.lHum.getValue().byteValue();
        clone.hVpd = this.hVpd.getValue().byteValue();
        clone.lVpd = this.lVpd.getValue().byteValue();
        return clone;
    }

    public void unregisterRxBus() {
        super.unregisterRxBus();
        Messenger.getDefault().unregister(this);
    }

    public void registerRxBus() {
        super.registerRxBus();
    }
}
