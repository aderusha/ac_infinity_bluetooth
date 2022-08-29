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
import com.eternal.data.DataFragment;
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
import com.eternal.notification.p008ui.AutomationFragment;
import com.eternal.notification.p008ui.NotificationActivity;
import com.eternal.res.C2663R;
import com.google.common.primitives.UnsignedBytes;
import java.util.concurrent.TimeUnit;
import kotlin.text.Typography;
import p014io.reactivex.Maybe;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Single;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class AutomationModel extends BaseViewModel {
    public byte connectType;
    public MutableLiveData<Boolean> cycle = new MutableLiveData<>();
    public MutableLiveData<Character> cycleOff = new MutableLiveData<>();
    public MutableLiveData<Character> cycleOn = new MutableLiveData<>();
    public String devId;
    /* access modifiers changed from: private */
    public byte deviceType;
    private byte deviceVersion;
    public MutableLiveData<Boolean> disClickable = new MutableLiveData<>();
    public MutableLiveData<Integer> distanceHum = new MutableLiveData<>();
    public MutableLiveData<Integer> distanceTmp = new MutableLiveData<>();
    public MutableLiveData<Integer> distanceVpd = new MutableLiveData<>();
    private String edit;
    public MutableLiveData<String> end = new MutableLiveData<>();
    public MutableLiveData<Byte> endAp = new MutableLiveData<>();
    public MutableLiveData<String> endEdit = new MutableLiveData<>();
    public MutableLiveData<Byte> endHour = new MutableLiveData<>();
    public MutableLiveData<Byte> endMin = new MutableLiveData<>();
    public MutableLiveData<Byte> hHum = new MutableLiveData<>();
    public Byte hHumSaved;
    public MutableLiveData<Boolean> hHumSwitch = new MutableLiveData<>();
    public MutableLiveData<Character> hTmp = new MutableLiveData<>();
    public Character hTmpSaved;
    public MutableLiveData<Boolean> hTmpSwitch = new MutableLiveData<>();
    public MutableLiveData<Byte> hVpd = new MutableLiveData<>();
    public Byte hVpdSaved;
    public MutableLiveData<Boolean> hVpdSwitch = new MutableLiveData<>();
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
    public MutableLiveData<Integer> levelOff = new MutableLiveData<>();
    public MutableLiveData<String> levelOffDes = new MutableLiveData<>();
    public MutableLiveData<Integer> levelOffRes = new MutableLiveData<>();
    public MutableLiveData<String> levelOffTitle = new MutableLiveData<>();
    public MutableLiveData<Boolean> levelOffVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> levelOn = new MutableLiveData<>();
    public MutableLiveData<String> levelOnDes = new MutableLiveData<>();
    public MutableLiveData<Integer> levelOnRes = new MutableLiveData<>();
    public MutableLiveData<String> levelOnTitle = new MutableLiveData<>();
    public MutableLiveData<Boolean> levelOnVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> levelVisible = new MutableLiveData<>();
    public MutableLiveData<Integer> minHum = new MutableLiveData<>();
    public MutableLiveData<Integer> minTmp = new MutableLiveData<>();
    public MutableLiveData<Integer> minVpd = new MutableLiveData<>();
    public MutableLiveData<String> name = new MutableLiveData<>();
    public BindingCommand<String> nameChanged = new BindingCommand<>(new BindingConsumer<String>() {
        public void call(String str) {
            MutableLiveData<String> mutableLiveData = AutomationModel.this.nameSuggest;
            mutableLiveData.setValue("NAME (" + str.length() + "/20)");
        }
    });
    public MutableLiveData<String> nameSuggest = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public Notification notification;
    public MutableLiveData<Boolean> off = new MutableLiveData<>();

    /* renamed from: on */
    public MutableLiveData<Boolean> f215on = new MutableLiveData<>();
    public BindingCommand<Void> onCancel = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Detail.PAGE_DETAIL).withTransition(C2420R.anim.left_in, C2420R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
                public void onArrival(Postcard postcard) {
                    AutomationModel.this.finish();
                }
            });
        }
    });
    public BindingCommand<Void> onConfirm = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (!RepositoryInjection.providerDeviceRepository().isConnect(AutomationModel.this.notification.mac)) {
                AutomationModel automationModel = AutomationModel.this;
                automationModel.showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(automationModel.deviceType, AutomationModel.this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
            } else if (AutomationModel.this.saveDis != null && !AutomationModel.this.saveDis.isDisposed()) {
            } else {
                if (AutomationModel.this.name.getValue() == null || TextUtils.isEmpty(AutomationModel.this.name.getValue().trim())) {
                    AutomationModel.this.showFailDialog(Utils.getString(C2420R.string.tip_empty_name));
                    AutomationModel.this.name.setValue(AutomationModel.this.originName);
                } else if (ProtocolTransformer.isWorkWiFi(AutomationModel.this.deviceType, AutomationModel.this.connectType)) {
                    AutomationModel.this.saveForNet();
                } else {
                    AutomationModel.this.saveForBle();
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
    public Disposable removeDis;
    /* access modifiers changed from: private */
    public NotificationRepository repository;
    public Disposable saveDis;
    public MutableLiveData<String> start = new MutableLiveData<>();
    public MutableLiveData<Byte> startAp = new MutableLiveData<>();
    public MutableLiveData<String> startEdit = new MutableLiveData<>();
    public MutableLiveData<Byte> startHour = new MutableLiveData<>();
    public MutableLiveData<Byte> startMin = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeVisible = new MutableLiveData<>();
    public MutableLiveData<String> tmpUnit = new MutableLiveData<>();
    public MutableLiveData<Boolean> tmphum = new MutableLiveData<>();
    public MutableLiveData<Boolean> vpd = new MutableLiveData<>();
    public MutableLiveData<Boolean> vpdModeVisible = new MutableLiveData<>();

    public AutomationModel(Application application) {
        super(application);
        this.startEdit.setValue(this.edit);
        this.endEdit.setValue(this.edit);
    }

    /* access modifiers changed from: private */
    public void saveForNet() {
        Observable<BaseData<Void>> observable;
        final Notification create = create();
        if (TextUtils.isEmpty(create.advId)) {
            create.devId = this.devId;
        }
        final boolean z = this.isCreate.getValue() != null && this.isCreate.getValue() == Boolean.TRUE;
        if (z) {
            observable = this.repository.getName(create, true, this.deviceVersion).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).flatMapObservable(new Function<Notification, ObservableSource<BaseData<Void>>>() {
                public ObservableSource<BaseData<Void>> apply(Notification notification) throws Exception {
                    return ApiHelper.getDeviceApi().addAdvInfo(BeanUtils.transBean2Map(new NetAdvance(notification)));
                }
            });
        } else {
            observable = ApiHelper.getDeviceApi().updateAdvInfo(BeanUtils.transBean2Map(new NetAdvance(create)));
        }
        if (this.disClickable.getValue() != Boolean.TRUE) {
            this.disClickable.setValue(true);
            RxBus.getDefault().post(new ProgressEvent((byte) 0));
            observable.subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
                public void doOnSubscribe(Disposable disposable) {
                    AutomationModel.this.saveDis = disposable;
                    AutomationModel.this.showDialog((String) null, disposable);
                }

                /* access modifiers changed from: protected */
                public void onError(String str) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    AutomationModel.this.showFailDialog(str);
                    AutomationModel.this.disClickable.setValue(false);
                }

                /* access modifiers changed from: protected */
                public void onSuccess(Void voidR) {
                    Notification unused = AutomationModel.this.notification = create;
                    AutomationModel.this.repository.setNotification(AutomationModel.this.notification, z);
                    RxBus.getDefault().post(new ProgressEvent((byte) 1));
                    AutomationModel.this.saveDis = Maybe.just(true).delay(1500, TimeUnit.MILLISECONDS).subscribe(new Consumer<Boolean>() {
                        public void accept(Boolean bool) throws Exception {
                            AutomationModel.this.back();
                            AutomationModel.this.dismissDialog();
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void saveForBle() {
        Single<Boolean> single;
        BleStatue connect = this.repository.getConnect(this.notification.mac);
        if (connect != null) {
            final Notification create = create();
            if (this.isCreate.getValue() == null || this.isCreate.getValue() != Boolean.TRUE) {
                single = this.repository.setNotification(connect, create);
            } else {
                single = this.repository.saveNotification(connect, create, true);
            }
            this.saveDis = single.compose(RxUtils.bindToLifecycle(getLifecycleProvider())).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) throws Exception {
                    Notification unused = AutomationModel.this.notification = create;
                    AutomationModel.this.back();
                }
            });
        }
    }

    public void init(NotificationRepository notificationRepository, String str, String str2, byte b, byte b2, byte b3, Notification notification2, boolean z, byte b4, byte b5, byte b6) {
        this.devId = str2;
        this.repository = notificationRepository;
        this.isDegree = z;
        this.deviceType = b2;
        this.connectType = b6;
        this.deviceVersion = b3;
        this.humVisible.setValue(Boolean.valueOf(b2 != 6));
        this.timeVisible.setValue(Boolean.valueOf(b2 != 6));
        if (b2 == 7 || b2 == 8 || b2 == 11) {
            this.levelOffRes.setValue(Integer.valueOf(C2420R.mipmap.level_icon));
            this.levelOffTitle.setValue(Utils.getString(C2420R.string.notify_min_level, "LEVEL"));
            this.levelOffDes.setValue(Utils.getString(C2420R.string.notify_min_level_des, "level"));
            this.levelOnRes.setValue(Integer.valueOf(C2420R.mipmap.level_icon));
            this.levelOnTitle.setValue(Utils.getString(C2420R.string.notify_max_level, "LEVEL"));
            this.levelOnDes.setValue(Utils.getString(C2420R.string.notify_max_level_des, "level"));
        } else {
            this.levelOffRes.setValue(Integer.valueOf(C2420R.mipmap.fan_icon));
            this.levelOffTitle.setValue(Utils.getString(C2420R.string.notify_min_level, "SPEED"));
            this.levelOffDes.setValue(Utils.getString(C2420R.string.notify_min_level_des, "speed"));
            this.levelOnRes.setValue(Integer.valueOf(C2420R.mipmap.fan_icon));
            this.levelOnTitle.setValue(Utils.getString(C2420R.string.notify_max_level, "SPEED"));
            this.levelOnDes.setValue(Utils.getString(C2420R.string.notify_max_level_des, "speed"));
        }
        if (notification2 == null) {
            notification2 = new Notification();
            notification2.name = Utils.getString(C2420R.string.tip_default_automation_name);
            notification2.model = 2;
            notification2.open = true;
            notification2.type = 1;
            notification2.mac = str;
            notification2.port = b;
            notification2.hTmpC = 90;
            notification2.lTmpC = 0;
            notification2.hTmpF = -62;
            notification2.lTmpF = 32;
            notification2.hHum = DataFragment.TAG_FILTER_TEMPERATURE;
            notification2.lHum = 0;
            notification2.hVpd = 99;
            notification2.lVpd = 0;
            notification2.tmpHum = 15;
            notification2.start = 540;
            notification2.end = 1020;
            this.isCreate.setValue(true);
            notification2.levelOn = b4;
            notification2.levelOff = b5;
        } else {
            this.isCreate.setValue(false);
        }
        this.notification = notification2;
        init(notification2);
    }

    private void init(Notification notification2) {
        this.name.setValue(notification2.name);
        this.originName = notification2.name;
        boolean z = true;
        if (ProtocolTransformer.isOutletDevice(this.deviceType)) {
            this.levelVisible.setValue(false);
        } else {
            MutableLiveData<Boolean> mutableLiveData = this.levelVisible;
            if (this.deviceVersion < 2) {
                z = false;
            }
            mutableLiveData.setValue(Boolean.valueOf(z));
        }
        initTmpHum(notification2);
        initVpd(notification2);
        byte b = notification2.model;
        if (b == 2) {
            this.cycle.setValue(false);
            this.tmphum.setValue(false);
            this.f215on.setValue(false);
            this.off.setValue(true);
            this.vpd.setValue(false);
        } else if (b == 3) {
            this.cycle.setValue(true);
            this.tmphum.setValue(false);
            this.f215on.setValue(false);
            this.off.setValue(false);
            this.vpd.setValue(false);
        } else if (b == 4) {
            this.cycle.setValue(false);
            this.tmphum.setValue(true);
            this.f215on.setValue(false);
            this.off.setValue(false);
            this.vpd.setValue(false);
        } else if (b != 5) {
            this.cycle.setValue(false);
            this.tmphum.setValue(false);
            this.f215on.setValue(true);
            this.off.setValue(false);
            this.vpd.setValue(false);
        } else {
            this.cycle.setValue(false);
            this.tmphum.setValue(false);
            this.f215on.setValue(false);
            this.off.setValue(false);
            this.vpd.setValue(true);
        }
        this.cycleOn.setValue(Character.valueOf(notification2.cycleOn));
        this.cycleOff.setValue(Character.valueOf(notification2.cycleOff));
        this.levelOff.setValue(Integer.valueOf(notification2.levelOff));
        this.levelOn.setValue(Integer.valueOf(notification2.levelOn));
        initTime(notification2);
        Messenger.getDefault().sendNoMsg(AutomationFragment.UPDATE_PARAM);
    }

    private void initTime(Notification notification2) {
        byte b;
        Object obj;
        byte b2;
        Object obj2;
        Notification notification3 = notification2;
        int i = notification3.start / Typography.less;
        int i2 = notification3.start % Typography.less;
        int i3 = 12;
        if (i >= 12) {
            i -= 12;
            b = 1;
        } else {
            b = 0;
        }
        if (i <= 0 || i > 12) {
            i = 12;
        }
        int i4 = C2420R.string.notify_time;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.valueOf(i);
        if (i2 < 9) {
            obj = "0" + i2;
        } else {
            obj = Integer.valueOf(i2);
        }
        objArr[1] = obj;
        String str = "AM";
        objArr[2] = b == 0 ? str : "PM";
        String string = Utils.getString(i4, objArr);
        this.startHour.setValue(Byte.valueOf((byte) (i - 1)));
        this.startMin.setValue(Byte.valueOf((byte) i2));
        this.startAp.setValue(Byte.valueOf(b));
        this.start.setValue(string);
        int i5 = notification3.end / Typography.less;
        int i6 = notification3.end % Typography.less;
        if (i5 >= 12) {
            i5 -= 12;
            b2 = 1;
        } else {
            b2 = 0;
        }
        if (i5 > 0 && i5 <= 12) {
            i3 = i5;
        }
        int i7 = C2420R.string.notify_time;
        Object[] objArr2 = new Object[3];
        objArr2[0] = Integer.valueOf(i3);
        if (i6 < 9) {
            obj2 = "0" + i6;
        } else {
            obj2 = Integer.valueOf(i6);
        }
        objArr2[1] = obj2;
        if (b2 != 0) {
            str = "PM";
        }
        objArr2[2] = str;
        String string2 = Utils.getString(i7, objArr2);
        this.endHour.setValue(Byte.valueOf((byte) (i3 - 1)));
        this.endMin.setValue(Byte.valueOf((byte) i6));
        this.endAp.setValue(Byte.valueOf(b2));
        this.end.setValue(string2);
    }

    private void initTmpHum(Notification notification2) {
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
        boolean z = false;
        this.hTmpSwitch.setValue(Boolean.valueOf((notification2.tmpHum & 8) != 0));
        this.lTmpSwitch.setValue(Boolean.valueOf((notification2.tmpHum & 4) != 0));
        this.hHumSwitch.setValue(Boolean.valueOf((notification2.tmpHum & 2) != 0));
        MutableLiveData<Boolean> mutableLiveData = this.lHumSwitch;
        if ((notification2.tmpHum & 1) != 0) {
            z = true;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
    }

    private void initVpd(Notification notification2) {
        boolean z = true;
        if (this.deviceType == 11) {
            this.vpdModeVisible.setValue(true);
        }
        this.hVpd.setValue(Byte.valueOf(notification2.hVpd));
        this.lVpd.setValue(Byte.valueOf(notification2.lVpd));
        this.hVpdSaved = this.hVpd.getValue();
        this.lVpdSaved = this.lVpd.getValue();
        this.hVpdSwitch.setValue(Boolean.valueOf((notification2.tmpHum & 2) != 0));
        MutableLiveData<Boolean> mutableLiveData = this.lVpdSwitch;
        if ((notification2.tmpHum & 1) == 0) {
            z = false;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
    }

    private Notification create() {
        Notification clone = this.notification.clone();
        if (this.name.getValue() == null || this.name.getValue().equals(Utils.getString(C2420R.string.tip_default_automation_name))) {
            clone.name = null;
        } else {
            clone.name = TextUtils.isEmpty(this.name.getValue()) ? this.name.getValue() : this.name.getValue().trim();
        }
        if (this.off.getValue().booleanValue()) {
            clone.model = 2;
        } else if (this.cycle.getValue().booleanValue()) {
            clone.model = 3;
        } else if (this.tmphum.getValue().booleanValue()) {
            clone.model = 4;
        } else if (this.vpd.getValue().booleanValue()) {
            clone.model = 5;
            clone.tmpHum = ProtocolTransformer.setVpd(this.hVpdSwitch.getValue().booleanValue(), this.lVpdSwitch.getValue().booleanValue());
        } else {
            clone.model = 1;
        }
        if (this.vpd.getValue().booleanValue()) {
            clone.tmpHum = ProtocolTransformer.setVpd(this.hVpdSwitch.getValue().booleanValue(), this.lVpdSwitch.getValue().booleanValue());
        } else {
            clone.tmpHum = ProtocolTransformer.setVpd(this.hTmpSwitch.getValue().booleanValue(), this.lTmpSwitch.getValue().booleanValue(), this.hHumSwitch.getValue().booleanValue(), this.lHumSwitch.getValue().booleanValue());
        }
        if (this.isDegree) {
            char charValue = (this.hTmpSwitch.getValue() == Boolean.TRUE ? this.hTmp.getValue() : this.hTmpSaved).charValue();
            clone.hTmpC = (byte) charValue;
            clone.hTmpF = (byte) Math.round(ProtocolTransformer.getFah((float) charValue));
            char charValue2 = (this.lTmpSwitch.getValue() == Boolean.TRUE ? this.lTmp.getValue() : this.lTmpSaved).charValue();
            clone.lTmpC = (byte) charValue2;
            clone.lTmpF = (byte) Math.round(ProtocolTransformer.getFah((float) charValue2));
        } else {
            char charValue3 = (this.hTmpSwitch.getValue() == Boolean.TRUE ? this.hTmp.getValue() : this.hTmpSaved).charValue();
            clone.hTmpF = (byte) charValue3;
            clone.hTmpC = (byte) Math.round(ProtocolTransformer.getDegree((float) charValue3));
            char charValue4 = (this.lTmpSwitch.getValue() == Boolean.TRUE ? this.lTmp.getValue() : this.lTmpSaved).charValue();
            clone.lTmpF = (byte) charValue4;
            clone.lTmpC = (byte) Math.round(ProtocolTransformer.getDegree((float) charValue4));
        }
        clone.hHum = (this.hHumSwitch.getValue() == Boolean.TRUE ? this.hHum.getValue() : this.hHumSaved).byteValue();
        clone.lHum = (this.lHumSwitch.getValue() == Boolean.TRUE ? this.lHum.getValue() : this.lHumSaved).byteValue();
        clone.hVpd = (this.hVpdSwitch.getValue() == Boolean.TRUE ? this.hVpd.getValue() : this.hVpdSaved).byteValue();
        clone.lVpd = (this.lVpdSwitch.getValue() == Boolean.TRUE ? this.lVpd.getValue() : this.lVpdSaved).byteValue();
        clone.cycleOn = this.cycleOn.getValue().charValue();
        clone.cycleOff = this.cycleOff.getValue().charValue();
        clone.levelOff = this.levelOff.getValue().intValue();
        clone.levelOn = this.levelOn.getValue().intValue();
        int byteValue = this.startHour.getValue().byteValue() + 1;
        if (byteValue >= 12) {
            byteValue -= 12;
        }
        clone.start = (char) ((byteValue * 60) + this.startMin.getValue().byteValue() + (this.startAp.getValue().byteValue() * 720));
        int byteValue2 = this.endHour.getValue().byteValue() + 1;
        if (byteValue2 >= 12) {
            byteValue2 -= 12;
        }
        clone.end = (char) ((byteValue2 * 60) + this.endMin.getValue().byteValue() + (this.endAp.getValue().byteValue() * 720));
        return clone;
    }

    public String getNotifyName() {
        return this.notification.name;
    }

    public void delete() {
        if (!RepositoryInjection.providerDeviceRepository().isConnect(this.notification.mac)) {
            showFailDialog(Utils.getString(ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType) ? C2663R.string.tip_wifi_disconnect : C2663R.string.tip_ble_disconnect));
            return;
        }
        Disposable disposable = this.removeDis;
        if (disposable != null && !disposable.isDisposed()) {
            return;
        }
        if (ProtocolTransformer.isWorkWiFi(this.deviceType, this.connectType)) {
            removeForNet();
        } else {
            removeForBle();
        }
    }

    private void removeForBle() {
        BleStatue connect = this.repository.getConnect(this.notification.mac);
        if (connect != null) {
            this.removeDis = this.repository.removeNotification(connect, this.notification, false).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                public void accept(Boolean bool) {
                    if (bool.booleanValue()) {
                        AutomationModel.this.back();
                    }
                }
            });
        }
    }

    private void removeForNet() {
        ApiHelper.getDeviceApi().delADVInfo(this.devId, this.notification.advId).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DataObserver<Void>() {
            public void doOnSubscribe(Disposable disposable) {
                AutomationModel.this.removeDis = disposable;
                AutomationModel.this.showDialog((String) null, disposable);
                RxBus.getDefault().post(new ProgressEvent((byte) 0));
            }

            /* access modifiers changed from: protected */
            public void onError(String str) {
                AutomationModel.this.showFailDialog(str);
            }

            /* access modifiers changed from: protected */
            public void onSuccess(Void voidR) {
                AutomationModel.this.dismissDialog();
                AutomationModel.this.back();
            }
        });
    }

    /* access modifiers changed from: private */
    public void back() {
        RxBus.getDefault().post(new ActivityEvent(0));
        ARouter.getInstance().build(RouterActivityPath.Detail.PAGE_DETAIL).withTransition(C2420R.anim.left_in, C2420R.anim.right_out).navigation((Context) AppManager.getAppManager().currentActivity(), (NavigationCallback) new NavCallback() {
            public void onArrival(Postcard postcard) {
                AutomationModel.this.finish();
            }
        });
    }

    public void unregisterRxBus() {
        super.unregisterRxBus();
        Messenger.getDefault().unregister(this);
    }

    public void registerRxBus() {
        super.registerRxBus();
    }
}
