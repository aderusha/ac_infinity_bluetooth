package com.eternal.advance.model;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.advance.C1200BR;
import com.eternal.advance.C1202R;
import com.eternal.base.ILevel;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.DeviceMinModel;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.NetAdvance;
import com.eternal.base.concat.NetDevice;
import com.eternal.base.concat.NetDeviceMode;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.PortList;
import com.eternal.base.data.NotificationRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.observer.DataObserver;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.binding.command.BindingConsumer;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.RxHttpUtils;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.utils.GsonUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.RxUtils;
import com.eternal.framework.utils.Utils;
import com.eternal.res.C2663R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import p014io.reactivex.Flowable;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
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
import p018me.tatarka.bindingcollectionadapter2.ItemBinding;

public class AdvanceModelV4 extends BaseViewModel {
    static final String ITEM_ADD_CHILD = "add child item";
    static final String ITEM_EDIT = "edit item";
    static final String ITEM_OPEN = "open item";
    public static final int MSG_START = 16;
    private static final String TAG_CHOOSE_PORT = "tag choose port";
    private static final String TAG_DELETE_ADV = "tag delete adv";
    private static final String TAG_FETCH_ADV = "tag fetch adv";
    private static final String TAG_SET_OPEN = "tag set open";
    public ObservableList<ItemModelV4> alarms = new ObservableArrayList();
    public ObservableList<ItemModelV4> automations = new ObservableArrayList();
    public byte connectType;
    public String devId;
    /* access modifiers changed from: private */
    public byte deviceType;
    /* access modifiers changed from: private */
    public byte deviceVersion;
    /* access modifiers changed from: private */
    public List<Notification> fallbackNetMode;
    /* access modifiers changed from: private */
    public PortList fallbackNetPortList;
    /* access modifiers changed from: private */
    public final AdvanceHandler handler = new AdvanceHandler(Looper.getMainLooper());
    public DeviceModel info;
    private boolean isDegree;
    public ItemBinding<ItemModelV4> itemBinding = ItemBinding.m1008of(C1200BR.itemV4, C1202R.layout.item_notification_v4);
    /* access modifiers changed from: private */
    public String mac;
    public boolean needSyncData;
    public ObservableList<ItemModelV4> notifications = new ObservableArrayList();
    public BindingCommand<Void> onAdd = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceModelV4.this.mac)) {
                AdvanceModelV4 advanceModelV4 = AdvanceModelV4.this;
                advanceModelV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceModelV4.deviceType, AdvanceModelV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                return;
            }
            byte b = (AdvanceModelV4.this.selectTab.getValue() == null || AdvanceModelV4.this.selectTab.getValue().intValue() == 0) ? (byte) 1 : 3;
            if (b == 1 && AdvanceModelV4.this.automations.size() >= 10) {
                return;
            }
            if (b != 3 || AdvanceModelV4.this.alarms.size() < 10) {
                AdvanceModelV4.this.start((String) null, b, -1);
                Messenger.getDefault().sendNoMsg("close menu");
            }
        }
    });
    public BindingCommand<Void> onAlarms = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AdvanceModelV4.this.selectTab.setValue(1);
        }
    });
    public BindingCommand<Void> onAutomations = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AdvanceModelV4.this.selectTab.setValue(0);
        }
    });
    public BindingCommand<Void> onNotifications = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            AdvanceModelV4.this.selectTab.setValue(2);
        }
    });
    public byte port;
    private Disposable refresh;
    private Disposable refreshDeviceInfo;
    /* access modifiers changed from: private */
    public NotificationRepository repository;
    public MutableLiveData<Integer> selectTab = new MutableLiveData<>();

    private void updateModelInfoForNet() {
    }

    public AdvanceModelV4(Application application) {
        super(application);
    }

    private final class AdvanceHandler extends Handler {
        AdvanceHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 16) {
                AdvanceModelV4.this.start();
            }
        }
    }

    public void init(NotificationRepository notificationRepository, String str, String str2, byte b, byte b2, byte b3, byte b4) {
        this.deviceType = b2;
        this.deviceVersion = b3;
        this.connectType = b4;
        this.repository = notificationRepository;
        this.mac = str;
        this.devId = str2;
        this.port = b;
        this.needSyncData = true;
        initNotify();
        initMessenger();
    }

    /* access modifiers changed from: private */
    public void initNotify() {
        if (this.info != null) {
            addSubscribe(this.repository.getNotifications(this.mac).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Notification>>() {
                public void accept(List<Notification> list) throws Exception {
                    boolean access$300 = AdvanceModelV4.this.isAutomationActivated(list);
                    if (!ProtocolTransformer.isWorkWiFi(AdvanceModelV4.this.deviceType, AdvanceModelV4.this.connectType)) {
                        RxBus.getDefault().post(new ActivityEvent(4, Boolean.valueOf(access$300)));
                    }
                    AdvanceModelV4.this.alarms.clear();
                    AdvanceModelV4.this.automations.clear();
                    HashMap hashMap = new HashMap();
                    for (Notification next : list) {
                        if (next.type == 1) {
                            List list2 = (List) hashMap.get(Integer.valueOf(next.f144id));
                            if (list2 == null) {
                                list2 = new ArrayList();
                            }
                            list2.add(next);
                            hashMap.put(Integer.valueOf(next.f144id), list2);
                        } else {
                            AdvanceModelV4.this.alarms.add(new ItemModelV4(Collections.singletonList(next), AdvanceModelV4.this.info, AdvanceModelV4.this.deviceType, AdvanceModelV4.this.deviceVersion));
                        }
                    }
                    ArrayList<List> arrayList = new ArrayList<>(hashMap.values());
                    Collections.sort(arrayList, new Comparator<List<Notification>>() {
                        public int compare(List<Notification> list, List<Notification> list2) {
                            return list.get(0).f144id - list2.get(0).f144id;
                        }
                    });
                    for (List itemModelV4 : arrayList) {
                        AdvanceModelV4.this.automations.add(new ItemModelV4(itemModelV4, AdvanceModelV4.this.info, AdvanceModelV4.this.deviceType, AdvanceModelV4.this.deviceVersion));
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

    public void registerRxBus() {
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) {
                if (activityEvent.what == 0) {
                    if (!ProtocolTransformer.isWorkWiFi(AdvanceModelV4.this.deviceType, AdvanceModelV4.this.connectType)) {
                        AdvanceModelV4.this.initNotify();
                    }
                } else if (activityEvent.what == 1) {
                    AdvanceModelV4.this.setModelInfo((DeviceModel) activityEvent.obj);
                    if (AdvanceModelV4.this.automations.isEmpty() && AdvanceModelV4.this.alarms.isEmpty()) {
                        AdvanceModelV4.this.initNotify();
                    }
                    Messenger.getDefault().sendNoMsg("update port tab");
                } else if (activityEvent.what == 3) {
                    byte byteValue = ((Byte) activityEvent.obj).byteValue();
                    if (AdvanceModelV4.this.info != null) {
                        AdvanceModelV4.this.info.leafTemperatureC = byteValue;
                    }
                }
            }
        }));
    }

    public void setDegree(boolean z) {
        DeviceModel deviceModel = this.info;
        if (!(deviceModel == null || this.isDegree == z)) {
            deviceModel.isDegree = z;
            updateModelInfo();
        }
        this.isDegree = z;
    }

    public void updateModelInfo() {
        if (RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
                updateModelInfoForNet();
            } else {
                updateModelInfoForBle();
            }
        }
    }

    private void updateModelInfoForBle() {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            this.refreshDeviceInfo = RepositoryInjection.providerDeviceRepository().getModel(connect, this.port).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceMinModel>() {
                public void accept(DeviceMinModel deviceMinModel) {
                    AdvanceModelV4.this.updateInfo(deviceMinModel);
                    AdvanceModelV4.this.updateAll();
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                    KLog.m65e(th);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateInfo(DeviceMinModel deviceMinModel) {
        DeviceModel deviceModel = this.info;
        if (deviceModel != null) {
            deviceModel.isDegree = deviceMinModel.isDegree;
            this.info.workType = deviceMinModel.model;
            this.info.tmp = deviceMinModel.tmp;
            this.info.hum = deviceMinModel.hum;
            this.info.fan = deviceMinModel.fan;
            this.info.fanState = deviceMinModel.fanState;
            this.info.humState = deviceMinModel.humState;
            this.info.tmpState = deviceMinModel.tmpState;
            this.info.typeOn = deviceMinModel.typeOn;
            this.info.typeOff = deviceMinModel.typeOff;
            switch (deviceMinModel.model) {
                case 1:
                    this.info.typeOff = deviceMinModel.getFan();
                    return;
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
                case 8:
                    this.info.highVpdSwitch = deviceMinModel.hVpdSwitch();
                    this.info.lowVpdSwitch = deviceMinModel.lVpdSwitch();
                    this.info.highVpd = deviceMinModel.getHighVpd();
                    this.info.lowVpd = deviceMinModel.getLowVpd();
                    this.info.autoHighTmpSwitch = deviceMinModel.hTmpSwitch();
                    this.info.autoLowTmpSwitch = deviceMinModel.lTmpSwitch();
                    this.info.autoHighTmp = deviceMinModel.getHighTmp();
                    this.info.autoLowTmp = deviceMinModel.getLowTmp();
                    this.info.autoHighHumSwitch = deviceMinModel.hHumSwitch();
                    this.info.autoLowHumSwitch = deviceMinModel.lHumSwitch();
                    this.info.autoHighHum = deviceMinModel.getHighHum();
                    this.info.autoLowHum = deviceMinModel.getLowHum();
                    return;
                default:
                    this.info.schedOn = deviceMinModel.getStart();
                    this.info.schedOff = deviceMinModel.getEnd();
                    this.info.currentTypeResidueTime = (int) deviceMinModel.time.time;
                    this.info.currentTypeResidueOn = !deviceMinModel.time.isOff;
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void setModelInfo(DeviceModel deviceModel) {
        this.info = deviceModel;
        this.isDegree = deviceModel.isDegree;
        if (!this.automations.isEmpty() || !this.alarms.isEmpty()) {
            updateAll();
        }
    }

    private void initMessenger() {
        final Messenger messenger = Messenger.getDefault();
        messenger.register((Object) this, (Object) ITEM_OPEN, ItemModelV4.class, new BindingConsumer<ItemModelV4>() {
            public void call(ItemModelV4 itemModelV4) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceModelV4.this.mac)) {
                    AdvanceModelV4 advanceModelV4 = AdvanceModelV4.this;
                    advanceModelV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceModelV4.deviceType, AdvanceModelV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                AdvanceModelV4.this.sendOpen(itemModelV4);
            }
        });
        messenger.register((Object) this, (Object) ITEM_EDIT, ItemModelV4.class, new BindingConsumer<ItemModelV4>() {
            public void call(ItemModelV4 itemModelV4) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceModelV4.this.mac)) {
                    AdvanceModelV4 advanceModelV4 = AdvanceModelV4.this;
                    advanceModelV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceModelV4.deviceType, AdvanceModelV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                Notification notification = itemModelV4.getNotification();
                AdvanceModelV4.this.start(GsonUtils.toJson(notification), notification.type, notification.f144id);
                messenger.sendNoMsg("close menu");
            }
        });
        messenger.register((Object) this, (Object) ITEM_ADD_CHILD, ItemModelV4.class, new BindingConsumer<ItemModelV4>() {
            public void call(ItemModelV4 itemModelV4) {
                if (!RepositoryInjection.providerDeviceRepository().isConnect(AdvanceModelV4.this.mac)) {
                    AdvanceModelV4 advanceModelV4 = AdvanceModelV4.this;
                    advanceModelV4.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(advanceModelV4.deviceType, AdvanceModelV4.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
                    return;
                }
                Notification notification = itemModelV4.getNotification();
                AdvanceModelV4.this.start((String) null, notification.type, notification.f144id);
                Messenger.getDefault().sendNoMsg("close menu");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        RxHttpUtils.cancel(TAG_SET_OPEN);
        RxHttpUtils.cancel(TAG_DELETE_ADV);
        stop();
        super.onCleared();
        Messenger.getDefault().unregister(this);
    }

    /* access modifiers changed from: private */
    public void start(String str, byte b, int i) {
        Postcard withBoolean = ARouter.getInstance().build(RouterActivityPath.Notification.PAGE_ADD).withString(ActivityEvent.NOTIFICATION, str).withString(ActivityEvent.DEVICE_ID, this.devId).withInt(ActivityEvent.NOTIFICATION_GROUP_ID, i).withByte(ActivityEvent.DEVICE_TYPE, this.deviceType).withByte(ActivityEvent.DEVICE_VERSION, this.deviceVersion).withByte(ActivityEvent.DEVICE_CONNECT_TYPE, this.connectType).withByte(ActivityEvent.NOTIFICATION_TYPE, b).withString(ActivityEvent.DEVICE_MAC, this.mac).withByte(ActivityEvent.DEVICE_PORT, this.port).withBoolean(ActivityEvent.DEVICE_DEGREE, this.info.isDegree);
        Activity currentActivity = AppManager.getAppManager().currentActivity();
        if (currentActivity instanceof ILevel) {
            byte[] levels = ((ILevel) currentActivity).getLevels();
            withBoolean.withByte(ActivityEvent.DEVICE_MODEL_ON_LEVEL, levels[0]).withByte(ActivityEvent.DEVICE_MODEL_OFF_LEVEL, levels[1]);
        }
        withBoolean.withTransition(C1202R.anim.right_in, C1202R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
    }

    /* access modifiers changed from: private */
    public void sendOpen(ItemModelV4 itemModelV4) {
        if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
            sendOpenForNet(itemModelV4.getNotification());
        } else {
            sendOpenForBle(itemModelV4.getNotification());
        }
    }

    private void sendOpenForNet(final Notification notification) {
        stop();
        RxHttpUtils.cancel(TAG_SET_OPEN);
        ApiHelper.getDeviceApi().updateADVStatus(this.devId, notification.advId).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            /* access modifiers changed from: protected */
            public String setTag() {
                return AdvanceModelV4.TAG_SET_OPEN;
            }

            public void doOnSubscribe(Disposable disposable) {
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                AdvanceModelV4.this.showFailDialog(str);
                if (AdvanceModelV4.this.fallbackNetMode != null) {
                    AdvanceModelV4 advanceModelV4 = AdvanceModelV4.this;
                    advanceModelV4.save(advanceModelV4.fallbackNetMode);
                }
                AdvanceModelV4.this.start();
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                if (AdvanceModelV4.this.fallbackNetMode != null) {
                    Iterator it = AdvanceModelV4.this.fallbackNetMode.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        Notification notification = (Notification) it.next();
                        if (notification.f144id == notification.f144id && notification.mac.equals(notification.mac) && notification.type == notification.type) {
                            notification.open = notification.open;
                            break;
                        }
                    }
                }
                AdvanceModelV4.this.repository.setNotificationOpen(notification.mac, notification.port, notification.f144id, notification.type, notification.open);
                AdvanceModelV4.this.updateAll();
                Message obtainMessage = AdvanceModelV4.this.handler.obtainMessage();
                obtainMessage.what = 16;
                AdvanceModelV4.this.handler.sendMessageDelayed(obtainMessage, 3000);
            }
        });
    }

    private void sendOpenForBle(Notification notification) {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            stop();
            addSubscribe(this.repository.openNotification(connect, notification).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    if (!bool.booleanValue()) {
                        KLog.m65e("notification set error");
                    }
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    AdvanceModelV4.this.start();
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    th.printStackTrace();
                    KLog.m65e(th);
                    AdvanceModelV4.this.start();
                }
            }));
        }
    }

    public void remove(ItemModelV4 itemModelV4, Notification notification) {
        if (!RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
        } else if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
            removeForNet(itemModelV4, notification);
        } else {
            removeForBle(itemModelV4, notification);
        }
    }

    private void removeForBle(final ItemModelV4 itemModelV4, Notification notification) {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            stop();
            addSubscribe(this.repository.removeNotification(connect, notification, itemModelV4 == null).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    ItemModelV4 itemModelV4;
                    if (bool.booleanValue() && (itemModelV4 = itemModelV4) != null) {
                        if (itemModelV4.model.type == 1) {
                            AdvanceModelV4.this.automations.remove(itemModelV4);
                        } else {
                            AdvanceModelV4.this.alarms.remove(itemModelV4);
                        }
                    }
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    AdvanceModelV4.this.start();
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    th.printStackTrace();
                    KLog.m65e(th);
                    AdvanceModelV4.this.start();
                }
            }));
        }
    }

    public void updateSequences(List<Byte> list, byte b, boolean z) {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            stop();
            addSubscribe(this.repository.updateSequences(connect, list, b, z).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    AdvanceModelV4.this.start();
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    th.printStackTrace();
                    KLog.m65e(th);
                    AdvanceModelV4.this.start();
                }
            }));
        }
    }

    private void removeForNet(final ItemModelV4 itemModelV4, Notification notification) {
        stop();
        ApiHelper.getDeviceApi().delADVInfo(this.devId, notification.advId).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            /* access modifiers changed from: protected */
            public String setTag() {
                return AdvanceModelV4.TAG_DELETE_ADV;
            }

            public void doOnSubscribe(Disposable disposable) {
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                AdvanceModelV4.this.showFailDialog(str);
                AdvanceModelV4 advanceModelV4 = AdvanceModelV4.this;
                advanceModelV4.save(advanceModelV4.fallbackNetMode);
                AdvanceModelV4.this.start();
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                ItemModelV4 itemModelV4 = itemModelV4;
                if (itemModelV4 != null) {
                    AdvanceModelV4.this.removeItem(itemModelV4.getNotification());
                    if (itemModelV4.model.type == 1) {
                        AdvanceModelV4.this.automations.remove(itemModelV4);
                    } else {
                        AdvanceModelV4.this.alarms.remove(itemModelV4);
                    }
                }
                Message obtainMessage = AdvanceModelV4.this.handler.obtainMessage();
                obtainMessage.what = 16;
                AdvanceModelV4.this.handler.sendMessageDelayed(obtainMessage, 3000);
            }
        });
    }

    public void updateAll() {
        if (this.info != null) {
            for (ItemModelV4 updateInfo : this.automations) {
                updateInfo.updateInfo(this.info);
            }
            for (ItemModelV4 updateInfo2 : this.alarms) {
                updateInfo2.updateInfo(this.info);
            }
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void start() {
        if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
            startForNet(true);
            Messenger.getDefault().sendNoMsg("update port tab");
            return;
        }
        startForBle();
    }

    /* access modifiers changed from: private */
    public void startForNet(final boolean z) {
        Observable<BaseData<List<NetAdvance>>> observable;
        RxHttpUtils.cancel(TAG_FETCH_ADV);
        if (z) {
            observable = ApiHelper.getDeviceApi().getDeviceInfo(this.devId).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<BaseData<NetDevice>, Byte>() {
                public Byte apply(BaseData<NetDevice> baseData) throws Exception {
                    if (baseData.getCode() != 200) {
                        return Byte.valueOf(AdvanceModelV4.this.port);
                    }
                    if (baseData.getData().deviceInfo == null || baseData.getData() == null) {
                        return Byte.valueOf(AdvanceModelV4.this.port);
                    }
                    if (AdvanceModelV4.this.port != baseData.getData().deviceInfo.choosePort) {
                        AdvanceModelV4.this.needSyncData = true;
                    }
                    PortList unused = AdvanceModelV4.this.fallbackNetPortList = new PortList(baseData.getData().deviceInfo.getPortList(), baseData.getData().deviceInfo.choosePort);
                    AdvanceModelV4.this.setPortList(baseData.getData().deviceInfo.getPortList(), baseData.getData().deviceInfo.choosePort);
                    return Byte.valueOf(baseData.getData().deviceInfo.choosePort);
                }
            }).observeOn(Schedulers.m983io()).flatMap(new Function<Byte, ObservableSource<BaseData<List<NetAdvance>>>>() {
                public ObservableSource<BaseData<List<NetAdvance>>> apply(Byte b) throws Exception {
                    return ApiHelper.getDeviceApi().getADVInfoList(AdvanceModelV4.this.devId, b.byteValue()).subscribeOn(Schedulers.m983io());
                }
            }).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                public ObservableSource<?> apply(Observable<Throwable> observable) throws Exception {
                    return observable.delay(2000, TimeUnit.MILLISECONDS);
                }
            }).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
                public ObservableSource<?> apply(Observable<Object> observable) throws Exception {
                    return observable.delay(2000, TimeUnit.MILLISECONDS);
                }
            });
        } else {
            observable = ApiHelper.getDeviceApi().getADVInfoList(this.devId, this.port).subscribeOn(Schedulers.m983io());
        }
        observable.flatMap(new Function<BaseData<List<NetAdvance>>, ObservableSource<BaseData<List<NetAdvance>>>>() {
            public ObservableSource<BaseData<List<NetAdvance>>> apply(final BaseData<List<NetAdvance>> baseData) throws Exception {
                return ApiHelper.getDeviceApi().getModeSettingList(AdvanceModelV4.this.devId, AdvanceModelV4.this.port).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).flatMap(new Function<BaseData<NetDeviceMode>, ObservableSource<BaseData<List<NetAdvance>>>>() {
                    public ObservableSource<BaseData<List<NetAdvance>>> apply(BaseData<NetDeviceMode> baseData) throws Exception {
                        if (!(baseData.getCode() != 200 || baseData.getData() == null || AdvanceModelV4.this.info == null)) {
                            AdvanceModelV4.this.info.update(baseData.getData());
                            AdvanceModelV4.this.updateAll();
                        }
                        return Observable.create(new ObservableOnSubscribe<BaseData<List<NetAdvance>>>() {
                            public void subscribe(ObservableEmitter<BaseData<List<NetAdvance>>> observableEmitter) throws Exception {
                                observableEmitter.onNext(baseData);
                            }
                        });
                    }
                });
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<List<NetAdvance>>() {
            public void doOnSubscribe(Disposable disposable) {
            }

            /* access modifiers changed from: protected */
            public String setTag() {
                return AdvanceModelV4.TAG_FETCH_ADV;
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                KLog.m65e("ADV??? ?????? ????????????");
                if (!z) {
                    AdvanceModelV4.this.start();
                }
            }

            /* access modifiers changed from: protected */
            public void onSuccess(List<NetAdvance> list) {
                ArrayList arrayList = new ArrayList();
                if (list != null) {
                    int i = 0;
                    while (i < list.size()) {
                        int i2 = i + 1;
                        for (int i3 = i2; i3 < list.size(); i3++) {
                            if (!list.get(i).isError && list.get(i).advCode == list.get(i3).advCode && list.get(i).advType == list.get(i3).advType && list.get(i).externalPort == list.get(i3).externalPort) {
                                list.get(i).isError = true;
                            }
                        }
                        i = i2;
                    }
                    for (NetAdvance next : list) {
                        if (!next.isError) {
                            arrayList.add(next.toNotification(AdvanceModelV4.this.mac));
                        }
                    }
                }
                boolean unused = AdvanceModelV4.this.isAutomationActivated(arrayList);
                List unused2 = AdvanceModelV4.this.fallbackNetMode = GsonUtils.getList(GsonUtils.toJson(arrayList), Notification.class);
                AdvanceModelV4.this.updateItems(arrayList);
                if (!z) {
                    Message obtainMessage = AdvanceModelV4.this.handler.obtainMessage();
                    obtainMessage.what = 16;
                    AdvanceModelV4.this.handler.sendMessageDelayed(obtainMessage, 3000);
                }
            }
        });
    }

    private void startForBle() {
        Single<List<Notification>> single;
        final BleStatue connect = this.repository.getConnect(this.mac);
        if (this.refresh == null && connect != null) {
            byte b = this.deviceType;
            if (b == 7 || b == 11 || b == 9 || b == 12) {
                single = Single.create(new SingleOnSubscribe<Byte>() {
                    public void subscribe(SingleEmitter<Byte> singleEmitter) throws Exception {
                        singleEmitter.onSuccess(Byte.valueOf(AdvanceModelV4.this.port));
                    }
                }).flatMap(new Function<Byte, SingleSource<PortList>>() {
                    public SingleSource<PortList> apply(Byte b) throws Exception {
                        return RepositoryInjection.providerDeviceRepository().getPortList(connect).subscribeOn(Schedulers.m983io());
                    }
                }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<PortList, Byte>() {
                    public Byte apply(PortList portList) throws Exception {
                        if (portList.choosePort != AdvanceModelV4.this.port) {
                            AdvanceModelV4.this.needSyncData = true;
                        }
                        AdvanceModelV4.this.setPortList(portList.portList, portList.choosePort);
                        return Byte.valueOf(portList.choosePort);
                    }
                }).observeOn(Schedulers.m983io()).flatMap(new Function<Byte, SingleSource<List<Notification>>>() {
                    public SingleSource<List<Notification>> apply(Byte b) throws Exception {
                        return AdvanceModelV4.this.repository.getNotifications(connect, b.byteValue()).subscribeOn(Schedulers.m983io());
                    }
                });
            } else {
                single = this.repository.getNotifications(connect, this.port);
            }
            this.refresh = single.subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).repeatWhen(new Function<Flowable<Object>, Publisher<?>>() {
                public Publisher<?> apply(Flowable<Object> flowable) throws Exception {
                    return flowable.delay(1000, TimeUnit.MILLISECONDS);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Notification>>() {
                public void accept(List<Notification> list) throws Exception {
                    KLog.m65e("adv ????????????");
                    RxBus.getDefault().post(new ActivityEvent(4, Boolean.valueOf(AdvanceModelV4.this.isAutomationActivated(list))));
                    AdvanceModelV4.this.updateItems(list);
                }
            });
        }
    }

    public void setPortList(List<PortInfo> list, byte b) {
        DeviceModel deviceModel;
        if (list != null && list.size() > 0 && (deviceModel = this.info) != null && deviceModel.portList != null && this.info.portList.size() > 0) {
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
                        break;
                    }
                }
            }
            this.info.choosePort = b;
            this.info.port = b;
            this.port = b;
            this.info.portList = list;
            Messenger.getDefault().sendNoMsg("update port tab");
        }
    }

    /* access modifiers changed from: private */
    public boolean isAutomationActivated(List<Notification> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        for (Notification notification : list) {
            notification.advActive = false;
        }
        Calendar instance = Calendar.getInstance();
        int i = (instance.get(11) * 60) + instance.get(12);
        for (Notification next : list) {
            if (next.type == 1 && next.open) {
                if (this.deviceType == 6) {
                    next.advActive = true;
                } else if (next.start < next.end) {
                    if (i >= next.start && i < next.end) {
                        next.advActive = true;
                    }
                } else if (next.start > next.end) {
                    if (i >= next.start || i < next.end) {
                        next.advActive = true;
                    }
                } else if (i == next.start) {
                    next.advActive = true;
                }
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void updateItems(List<Notification> list) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (Notification next : list) {
            if (next.type == 1) {
                List list2 = (List) hashMap.get(Integer.valueOf(next.f144id));
                if (list2 == null) {
                    list2 = new ArrayList();
                }
                list2.add(next);
                hashMap.put(Integer.valueOf(next.f144id), list2);
            } else {
                arrayList.add(Collections.singletonList(next));
            }
        }
        ArrayList arrayList2 = new ArrayList(hashMap.values());
        Collections.sort(arrayList2, new Comparator<List<Notification>>() {
            public int compare(List<Notification> list, List<Notification> list2) {
                return list.get(0).f144id - list2.get(0).f144id;
            }
        });
        Collections.sort(arrayList, new Comparator<List<Notification>>() {
            public int compare(List<Notification> list, List<Notification> list2) {
                return list.get(0).f144id - list2.get(0).f144id;
            }
        });
        if (arrayList2.size() == this.automations.size() && arrayList.size() == this.alarms.size()) {
            int i = 0;
            while (true) {
                if (i >= this.automations.size()) {
                    z = false;
                    break;
                } else if (((ItemModelV4) this.automations.get(i)).getNotification().f144id != ((Notification) ((List) arrayList2.get(i)).get(0)).f144id) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            int i2 = 0;
            while (true) {
                if (i2 >= this.alarms.size()) {
                    break;
                } else if (((ItemModelV4) this.alarms.get(i2)).getNotification().f144id != ((Notification) ((List) arrayList.get(i2)).get(0)).f144id) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (z) {
                this.needSyncData = true;
            }
        } else {
            this.needSyncData = true;
        }
        if (this.needSyncData) {
            KLog.m65e("????????????????????????");
            this.needSyncData = false;
            save(list);
            return;
        }
        updateItems(this.automations, arrayList2);
        updateItems(this.alarms, arrayList);
    }

    /* access modifiers changed from: private */
    public void save(List<Notification> list) {
        addSubscribe(RepositoryInjection.providerDeviceRepository().saveNotifications(this.mac, this.port, this.deviceType, list, this.deviceVersion, this.connectType).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                AdvanceModelV4.this.initNotify();
            }
        }));
    }

    private void updateItems(List<ItemModelV4> list, List<List<Notification>> list2) {
        Iterator<ItemModelV4> it = list.iterator();
        while (it.hasNext()) {
            ItemModelV4 next = it.next();
            Iterator<List<Notification>> it2 = list2.iterator();
            Notification notification = next.getNotification();
            boolean z = false;
            while (it2.hasNext()) {
                List next2 = it2.next();
                if (((Notification) next2.get(0)).f144id == notification.f144id && ((Notification) next2.get(0)).type == notification.type) {
                    z = true;
                    it2.remove();
                    next.update(next2, ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType));
                }
            }
            if (!z) {
                removeItem(next.getNotification());
                it.remove();
            }
        }
        if (list2.size() > 0) {
            for (List<Notification> itemModelV4 : list2) {
                list.add(new ItemModelV4(itemModelV4, this.info, this.deviceType, this.deviceVersion));
            }
        }
    }

    /* access modifiers changed from: private */
    public void removeItem(Notification notification) {
        addSubscribe(this.repository.deleteNotification(notification.mac, notification.port, notification.f144id, notification.type).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
            }
        }));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void stop() {
        this.handler.removeMessages(16);
        RxHttpUtils.cancel(TAG_FETCH_ADV);
        Disposable disposable = this.refresh;
        if (disposable != null) {
            disposable.dispose();
            this.refresh = null;
        }
    }

    public void choosePort(byte b) {
        if (b != this.port) {
            KLog.m65e("????????? ??????????????????");
            if (!RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
                return;
            }
            if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
                choosePortForNet(this.devId, b);
            } else {
                choosePortForBle(b);
            }
        }
    }

    private void choosePortForNet(String str, final byte b) {
        stop();
        RxHttpUtils.cancel(TAG_CHOOSE_PORT);
        this.port = b;
        ApiHelper.getDeviceApi().updateMsterPort(str, b).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            /* access modifiers changed from: protected */
            public String setTag() {
                return AdvanceModelV4.TAG_CHOOSE_PORT;
            }

            public void doOnSubscribe(Disposable disposable) {
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                AdvanceModelV4.this.showFailDialog(str);
                if (AdvanceModelV4.this.fallbackNetPortList != null) {
                    AdvanceModelV4 advanceModelV4 = AdvanceModelV4.this;
                    advanceModelV4.setPortList(advanceModelV4.fallbackNetPortList.portList, AdvanceModelV4.this.fallbackNetPortList.choosePort);
                }
                KLog.m65e(str);
                AdvanceModelV4.this.start();
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                if (AdvanceModelV4.this.fallbackNetPortList != null) {
                    AdvanceModelV4.this.fallbackNetPortList.choosePort = b;
                }
                RxBus.getDefault().post(new ProgressEvent((byte) 1));
                AdvanceModelV4.this.startForNet(false);
            }
        });
    }

    private void choosePortForBle(byte b) {
        BleStatue connect = this.repository.getConnect(this.mac);
        if (connect != null) {
            stop();
            addSubscribe(RepositoryInjection.providerDeviceRepository().setChoosePort(connect, b).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                public void accept(Disposable disposable) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 0));
                }
            }).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    if (!bool.booleanValue()) {
                        KLog.m65e("choose port error");
                    }
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    AdvanceModelV4.this.start();
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) {
                    th.printStackTrace();
                    KLog.m65e(th);
                    AdvanceModelV4.this.start();
                }
            }));
        }
    }
}
