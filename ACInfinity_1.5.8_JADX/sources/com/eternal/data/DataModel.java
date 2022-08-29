package com.eternal.data;

import android.app.Application;
import android.text.TextUtils;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.UserCache;
import com.eternal.base.api.ApiHelper;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.DeviceTFP;
import com.eternal.base.concat.DeviceTarget;
import com.eternal.base.concat.NetHistoryData;
import com.eternal.base.concat.PortCountAndLeafTmp;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.data.HistoryRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.global.ProgressEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.http.bean.BaseData;
import com.eternal.framework.http.utils.NetUtils;
import com.eternal.framework.utils.GsonUtils;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.RxUtils;
import com.eternal.framework.utils.SPUtils;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableObserver;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class DataModel extends BaseViewModel {
    private static final String TAG_FETCH_HISTORY = "tag fetch history";
    public ObservableField<String> avgHum = new ObservableField<>();
    public ObservableField<String> avgTmp = new ObservableField<>();
    public ObservableField<String> avgVpd = new ObservableField<>();
    private Consumer<ActivityEvent> con2;
    public byte connectType;
    private Disposable dcDispose;
    public String devId;
    public ObservableField<String> fanSize = new ObservableField<>();
    public ObservableInt fanType = new ObservableInt();
    public ObservableField<String> fanTypeTitle = new ObservableField<>();
    public ObservableBoolean fanVisibility = new ObservableBoolean();
    public List<Byte> filterTypes;
    private DecimalFormat format;
    public LiveData<PagedList<TmpHum>> history;
    public MutableLiveData<Boolean> humGraphVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> humVisible = new MutableLiveData<>();
    public boolean isDegree;
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public byte leafTemperatureC;
    /* access modifiers changed from: private */
    public String mac;
    public ObservableField<String> maxHum = new ObservableField<>();
    public ObservableField<String> maxTmp = new ObservableField<>();
    public ObservableField<String> maxVpd = new ObservableField<>();
    public ObservableField<String> minHum = new ObservableField<>();
    public ObservableField<String> minTmp = new ObservableField<>();
    public ObservableField<String> minVpd = new ObservableField<>();
    public BindingCommand<Void> onCloseOverlay = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            DataModel.this.overlayVisible.setValue(false);
        }
    });
    public BindingCommand<Void> onDay = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            DataModel.this.selectTab.setValue((byte) 1);
            DataModel.this.timeDistance.setValue(86400);
        }
    });
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().sendNoMsg("show delete dialog");
        }
    });
    public BindingCommand<Void> onExport = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ARouter.getInstance().build(RouterActivityPath.Export.PAGE_EXPORT).withString(ActivityEvent.DEVICE_MAC, DataModel.this.mac).withString(ActivityEvent.DEVICE_ID, DataModel.this.devId).withBoolean(ActivityEvent.DEVICE_DEGREE, DataModel.this.isDegree).withByte(ActivityEvent.DEVICE_TYPE, DataModel.this.type).withTransition(C1835R.anim.right_in, C1835R.anim.left_out).navigation(AppManager.getAppManager().currentActivity());
        }
    });
    public BindingCommand<Void> onHour = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            DataModel.this.selectTab.setValue((byte) 0);
            DataModel.this.timeDistance.setValue(3600);
        }
    });
    public BindingCommand<Void> onMonth = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            DataModel.this.selectTab.setValue((byte) 3);
            DataModel.this.timeDistance.setValue(2678400);
        }
    });
    public BindingCommand<Void> onRefresh = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            KLog.m65e("数据页 开始刷新");
            if (ProtocolTransformer.isWorkWiFi(DataModel.this.type, DataModel.this.connectType)) {
                if (!DataModel.this.isReady()) {
                    DataModel.this.isLoading.setValue(false);
                    return;
                }
                if (DataModel.this.timeDispose != null && !DataModel.this.timeDispose.isDisposed()) {
                    DataModel.this.timeDispose.dispose();
                }
                DataModel dataModel = DataModel.this;
                Disposable unused = dataModel.timeDispose = dataModel.refreshHistoryForNet().subscribeOn(Schedulers.m983io()).compose(RxUtils.bindToLifecycle(DataModel.this.getLifecycleProvider())).andThen((CompletableSource) new Completable() {
                    /* access modifiers changed from: protected */
                    public void subscribeActual(CompletableObserver completableObserver) {
                        DataModel.this.isLoading.setValue(false);
                    }
                }.subscribeOn(AndroidSchedulers.mainThread())).subscribe();
            } else if (!RepositoryInjection.providerDeviceRepository().isConnect(DataModel.this.mac)) {
                DataModel.this.isLoading.setValue(false);
            } else if (!DataModel.this.isReady()) {
                KLog.m65e("数据页 初始化未完成");
                DataModel.this.isLoading.setValue(false);
            } else {
                if (DataModel.this.timeDispose != null && !DataModel.this.timeDispose.isDisposed()) {
                    DataModel.this.timeDispose.dispose();
                }
                DataModel dataModel2 = DataModel.this;
                Disposable unused2 = dataModel2.timeDispose = dataModel2.repository.refreshHistory(DataModel.this.mac).subscribeOn(Schedulers.m983io()).compose(RxUtils.bindToLifecycle(DataModel.this.getLifecycleProvider())).andThen((CompletableSource) new Completable() {
                    /* access modifiers changed from: protected */
                    public void subscribeActual(CompletableObserver completableObserver) {
                        DataModel.this.isLoading.setValue(false);
                    }
                }.subscribeOn(AndroidSchedulers.mainThread())).subscribe();
            }
        }
    });
    public BindingCommand<Void> onTargetHum = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (DataModel.this.targetHumModel.expand.getValue() == null || DataModel.this.targetHumModel.expand.getValue().intValue() == 0) {
                DataModel.this.targetTmpModel.expand.setValue(0);
                DataModel.this.targetVpdModel.expand.setValue(0);
                DataModel.this.targetHumModel.expand.setValue(2);
                return;
            }
            DataModel.this.targetHumModel.expand.setValue(0);
        }
    });
    public BindingCommand<Void> onTargetTmp = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (DataModel.this.targetTmpModel.expand.getValue() == null || DataModel.this.targetTmpModel.expand.getValue().intValue() == 0) {
                DataModel.this.targetHumModel.expand.setValue(0);
                DataModel.this.targetVpdModel.expand.setValue(0);
                DataModel.this.targetTmpModel.expand.setValue(2);
                return;
            }
            DataModel.this.targetTmpModel.expand.setValue(0);
        }
    });
    public BindingCommand<Void> onTargetVpd = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (DataModel.this.targetVpdModel.expand.getValue() == null || DataModel.this.targetVpdModel.expand.getValue().intValue() == 0) {
                DataModel.this.targetTmpModel.expand.setValue(0);
                DataModel.this.targetHumModel.expand.setValue(0);
                DataModel.this.targetVpdModel.expand.setValue(2);
                return;
            }
            DataModel.this.targetVpdModel.expand.setValue(0);
        }
    });
    public BindingCommand<Void> onWeek = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            DataModel.this.selectTab.setValue((byte) 2);
            DataModel.this.timeDistance.setValue(604800);
        }
    });
    public BindingCommand<Void> onYear = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            DataModel.this.selectTab.setValue((byte) 4);
            DataModel.this.timeDistance.setValue(31536000);
        }
    });
    public BindingCommand<Void> onZoom = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            DataModel.this.selectTab.setValue((byte) 5);
        }
    });
    public MutableLiveData<Integer> overlayRes = new MutableLiveData<>();
    public MutableLiveData<Boolean> overlayVisible = new MutableLiveData<>();
    public ObservableField<String> perSize = new ObservableField<>();
    private byte port;
    public int portCount;
    public ObservableBoolean powerOff = new ObservableBoolean();
    public ObservableBoolean powerVisibility = new ObservableBoolean();
    private boolean ready;
    private Disposable refresh;
    public HistoryRepository repository;
    public MutableLiveData<Byte> selectTab = new MutableLiveData<>();
    public BindingCommand<Void> showFilterPopup = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().sendNoMsg(DataFragment.SHOW_FILTER_POPUP);
        }
    });
    public BindingCommand<Void> showPopup = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().sendNoMsg(DataFragment.SHOW_POPUP);
        }
    });
    public int targetHum;
    public TargetModel targetHumModel = new TargetModel(1);
    public MutableLiveData<String> targetHumTxt = new MutableLiveData<>();
    public int targetTmpC;
    public int targetTmpF;
    public TargetModel targetTmpModel = new TargetModel(0);
    public MutableLiveData<String> targetTmpTxt = new MutableLiveData<>();
    public int targetVpd;
    public TargetModel targetVpdModel = new TargetModel(2);
    public MutableLiveData<String> targetVpdTxt = new MutableLiveData<>();
    public MutableLiveData<Boolean> tfpVisible = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public Disposable timeDispose;
    public MutableLiveData<Integer> timeDistance = new MutableLiveData<>();
    public ObservableField<String> tmpFlag = new ObservableField<>();
    public MutableLiveData<Boolean> tmpGraphVisible = new MutableLiveData<>();
    public ObservableField<String> tmpSize = new ObservableField<>();
    /* access modifiers changed from: private */
    public byte type;
    private byte version;
    private DecimalFormat vpdFormat;
    public MutableLiveData<Boolean> vpdGraphVisible = new MutableLiveData<>();
    public ObservableField<String> vpdSize = new ObservableField<>();
    public MutableLiveData<Boolean> vpdVisible = new MutableLiveData<>();

    public DataModel(Application application) {
        super(application);
    }

    public void init(HistoryRepository historyRepository, String str, String str2, byte b, byte b2, byte b3, byte b4) {
        this.mac = str;
        this.devId = str2;
        this.port = b;
        this.type = b2;
        this.connectType = b4;
        this.version = b3;
        this.repository = historyRepository;
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        this.format = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
        this.vpdFormat = decimalFormat2;
        decimalFormat2.setRoundingMode(RoundingMode.HALF_UP);
        boolean z = false;
        this.ready = false;
        this.fanType.set(((b2 == 1 || b2 == 6) ? 1 : (b2 == 7 || b2 == 8 || b2 == 11) ? (char) 2 : 0) == 2 ? C1835R.mipmap.level_icon : C1835R.mipmap.fan_icon);
        MutableLiveData<Boolean> mutableLiveData = this.tfpVisible;
        if (b3 >= 3) {
            z = true;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
        register2();
    }

    public void initTargetData() {
        addSubscribe(RepositoryInjection.providerDeviceRepository().getTarget(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<DeviceTarget>() {
            public void accept(DeviceTarget deviceTarget) throws Exception {
                DataModel.this.targetTmpF = deviceTarget.targetTmpF;
                DataModel.this.targetTmpC = deviceTarget.targetTmpC;
                DataModel.this.targetHum = deviceTarget.targetHum;
                DataModel.this.targetVpd = deviceTarget.targetVpd;
                if (DataModel.this.isDegree) {
                    DataModel.this.targetTmpModel.setTarget(DataModel.this.targetTmpC);
                } else {
                    DataModel.this.targetTmpModel.setTarget(DataModel.this.targetTmpF);
                }
                DataModel.this.targetHumModel.setTarget(DataModel.this.targetHum);
                DataModel.this.targetVpdModel.setTarget(DataModel.this.targetVpd);
                Messenger.getDefault().sendNoMsg(DataFragment.UPDATE_TARGET);
            }
        }));
    }

    public Single<Boolean> initHistory(Consumer<Boolean> consumer, Consumer<ActivityEvent> consumer2) {
        Single<Boolean> single;
        this.con2 = consumer2;
        if (ProtocolTransformer.isWorkWiFi(this.type, this.connectType)) {
            if (NetUtils.isNetworkConnected()) {
                single = initHistoryForNet(consumer, consumer2);
            } else {
                this.history = this.repository.getHistory(this.mac);
                single = isDegree();
            }
        } else if (RepositoryInjection.providerDeviceRepository().isConnect(this.mac)) {
            single = initHistoryForBle(consumer, consumer2);
        } else {
            this.history = this.repository.getHistory(this.mac);
            single = isDegree();
        }
        return RepositoryInjection.providerDeviceRepository().getLeafTemperatureC(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(new Consumer<PortCountAndLeafTmp>() {
            public void accept(PortCountAndLeafTmp portCountAndLeafTmp) throws Exception {
                DataModel.this.leafTemperatureC = portCountAndLeafTmp.leafTemperatureC;
                Messenger.getDefault().send(Integer.valueOf(portCountAndLeafTmp.portCount), DataFragment.SET_PORT_COUNT);
                DataModel.this.portCount = portCountAndLeafTmp.portCount;
            }
        }).observeOn(Schedulers.m983io()).map(new Function<PortCountAndLeafTmp, List<Byte>>() {
            public List<Byte> apply(PortCountAndLeafTmp portCountAndLeafTmp) throws Exception {
                SPUtils instance = SPUtils.getInstance();
                String string = instance.getString(ActivityEvent.SHARED_PREFERENCES_KEY_DATA_CHECKED_TYPES + DataModel.this.mac);
                ArrayList arrayList = new ArrayList();
                if (TextUtils.isEmpty(string)) {
                    arrayList.addAll(Arrays.asList(new Byte[]{Byte.valueOf(DataFragment.TAG_FILTER_TEMPERATURE), Byte.valueOf(DataFragment.TAG_FILTER_HUMIDITY), Byte.valueOf(DataFragment.TAG_FILTER_VPD)}));
                    for (byte b = 0; b < portCountAndLeafTmp.portCount; b = (byte) (b + 1)) {
                        arrayList.add(Byte.valueOf(b));
                    }
                } else {
                    arrayList.addAll(GsonUtils.getList(string, Byte.class));
                }
                return arrayList;
            }
        }).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(new Consumer<List<Byte>>() {
            public void accept(List<Byte> list) throws Exception {
                DataModel.this.filterTypes = list;
                Messenger.getDefault().sendNoMsg(DataFragment.UPDATE_FILTER);
            }
        }).ignoreElement().andThen(single.subscribeOn(Schedulers.m983io()));
    }

    private Single<Boolean> initHistoryForNet(final Consumer<Boolean> consumer, final Consumer<ActivityEvent> consumer2) {
        return getPageAndNext(1, 500, true).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
                KLog.m65e("数据页 开始");
                DataModel.this.register(disposable, consumer, consumer2);
            }
        }).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        }).ignoreElements().andThen(isDegree()).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                KLog.m65e("数据页 初始化完成");
                DataModel.this.ready("change history");
            }
        }).doOnSuccess(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                KLog.m65e("数据页 初始化完成");
                DataModel.this.ready("change history");
            }
        });
    }

    /* access modifiers changed from: package-private */
    public Observable<BaseData<NetHistoryData>> getPageAndNext(final int i, final int i2, final boolean z) {
        return Single.create(new SingleOnSubscribe<Long>() {
            public void subscribe(SingleEmitter<Long> singleEmitter) {
                singleEmitter.onSuccess(Long.valueOf(DataModel.this.repository.getLastTime(DataModel.this.mac)));
            }
        }).subscribeOn(Schedulers.m983io()).flatMapObservable(new Function<Long, ObservableSource<BaseData<NetHistoryData>>>() {
            public ObservableSource<BaseData<NetHistoryData>> apply(Long l) throws Exception {
                if (l.longValue() == 0) {
                    l = 946656000000L;
                }
                KLog.m65e("数据页 开始刷新 time：" + (l.longValue() / 1000));
                if (z) {
                    RxBus.getDefault().post(new ProgressEvent((byte) 2, 3.0f));
                }
                return ApiHelper.getLogApi().getDataListPage(UserCache.getInstance().getToken(), DataModel.this.devId, l.longValue() / 1000, i2).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io());
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).map(new Function<BaseData<NetHistoryData>, BaseData<NetHistoryData>>() {
            public BaseData<NetHistoryData> apply(BaseData<NetHistoryData> baseData) throws Exception {
                if (baseData.getData() != null) {
                    if (baseData.getData().rows != null && baseData.getData().rows.size() > 0) {
                        DataModel.this.repository.addHistorys(DataModel.this.mac, baseData.getData().rows);
                    }
                    if (z) {
                        float f = 100.0f;
                        if (baseData.getData().total != 0) {
                            int i = i;
                            int i2 = i2;
                            float f2 = (((float) (i * i2)) * 100.0f) / ((((float) (i * i2)) * 1.0f) + ((float) baseData.getData().total));
                            if (f2 <= 100.0f) {
                                f = f2;
                            }
                        }
                        RxBus.getDefault().post(new ProgressEvent((byte) 2, f));
                    }
                }
                return baseData;
            }
        }).concatMap(new Function<BaseData<NetHistoryData>, ObservableSource<? extends BaseData<NetHistoryData>>>() {
            public ObservableSource<? extends BaseData<NetHistoryData>> apply(BaseData<NetHistoryData> baseData) throws Exception {
                if (baseData.getData() == null || baseData.getData().rows == null || baseData.getData().rows.size() < i2) {
                    return Observable.just(baseData);
                }
                return Observable.just(baseData).concatWith(DataModel.this.getPageAndNext(i + 1, i2, z));
            }
        });
    }

    /* access modifiers changed from: private */
    public Completable refreshHistoryForNet() {
        return getPageAndNext(1, 500, false).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                th.printStackTrace();
            }
        }).ignoreElements();
    }

    private Single<Boolean> initHistoryForBle(final Consumer<Boolean> consumer, final Consumer<ActivityEvent> consumer2) {
        KLog.m65e("数据页 开始初始化数据");
        return this.repository.refreshHistory(this.mac).subscribeOn(Schedulers.m983io()).doOnSubscribe(new Consumer<Disposable>() {
            public void accept(Disposable disposable) throws Exception {
                DataModel.this.register(disposable, consumer, consumer2);
            }
        }).andThen(isDegree()).doOnSuccess(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                DataModel.this.ready("数据页 初始化数据完成");
            }
        }).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                DataModel.this.ready("数据页 初始化数据完成");
            }
        });
    }

    /* access modifiers changed from: private */
    public void ready(String str) {
        Disposable disposable = this.dcDispose;
        if (disposable != null && !disposable.isDisposed()) {
            this.dcDispose.dispose();
            this.dcDispose = null;
        }
        this.history = this.repository.getHistory(this.mac);
        KLog.m65e(str);
        this.ready = true;
    }

    public Single<Boolean> isDegree() {
        return RepositoryInjection.providerDeviceRepository().isDegree(this.mac);
    }

    public boolean isReady() {
        return this.ready;
    }

    /* access modifiers changed from: private */
    public void register(final Disposable disposable, Consumer<Boolean> consumer, Consumer<ActivityEvent> consumer2) {
        Disposable subscribe = RxBus.getDefault().toObservable(BluetoothEvent.class).flatMapMaybe(new Function<BluetoothEvent, MaybeSource<Boolean>>() {
            public MaybeSource<Boolean> apply(BluetoothEvent bluetoothEvent) throws Exception {
                if (bluetoothEvent.what != 0 || !DataModel.this.mac.equals(bluetoothEvent.mac)) {
                    return Maybe.empty();
                }
                disposable.dispose();
                DataModel dataModel = DataModel.this;
                dataModel.history = dataModel.repository.getHistory(DataModel.this.mac);
                return DataModel.this.isDegree().subscribeOn(Schedulers.m983io()).toMaybe();
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer);
        this.dcDispose = subscribe;
        addSubscribe(subscribe);
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(consumer2));
    }

    private void register2() {
        addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ActivityEvent>() {
            public void accept(ActivityEvent activityEvent) throws Exception {
                if (activityEvent.what == 1) {
                    DataModel.this.setModelInfo((DeviceModel) activityEvent.obj);
                } else if (activityEvent.what == 3) {
                    DataModel.this.leafTemperatureC = ((Byte) activityEvent.obj).byteValue();
                }
            }
        }));
    }

    public boolean isConnect() {
        return RepositoryInjection.providerDeviceRepository().isConnect(this.mac);
    }

    public void onResume() {
        if (this.ready) {
            if (this.con2 != null) {
                addSubscribe(RxBus.getDefault().toObservable(ActivityEvent.class).subscribe(this.con2));
            }
            this.onRefresh.execute();
        }
    }

    public void onPause() {
        Disposable disposable = this.timeDispose;
        if (disposable != null) {
            disposable.dispose();
            this.isLoading.setValue(false);
        }
        Disposable disposable2 = this.refresh;
        if (disposable2 != null) {
            disposable2.dispose();
        }
    }

    public void startTFPBle() {
        if (this.repository.getConnect(this.mac) != null && this.version >= 3) {
            Disposable disposable = this.refresh;
            if (disposable == null || disposable.isDisposed()) {
                KLog.m68i("data refresh start");
                this.refresh = RepositoryInjection.providerDeviceRepository().getV3Model(this.mac).subscribeOn(Schedulers.m983io()).subscribe(new Consumer<DeviceModel>() {
                    public void accept(DeviceModel deviceModel) throws Exception {
                        if (deviceModel.choosePort != 0 && deviceModel.portList != null) {
                            Iterator<PortInfo> it = deviceModel.portList.iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    break;
                                }
                                PortInfo next = it.next();
                                if (next.f138id == deviceModel.choosePort) {
                                    deviceModel.fanState = next.fanState;
                                    deviceModel.fan = next.fan;
                                    break;
                                }
                            }
                        }
                        DataModel.this.setModelInfo(deviceModel);
                    }
                }, new Consumer<Throwable>() {
                    public void accept(Throwable th) throws Exception {
                        KLog.m65e(th);
                    }
                });
            }
        }
    }

    public void refreshTFP(DeviceTFP deviceTFP) {
        if (deviceTFP != null) {
            this.tmpSize.set(ProtocolTransformer.getString4Degree(deviceTFP.tmp, deviceTFP.isDegree, this.format));
            this.tmpFlag.set(deviceTFP.isDegree ? ProtocolTransformer.DEGREE : ProtocolTransformer.FAH);
            this.perSize.set(ProtocolTransformer.getPer(deviceTFP.hum, this.format));
            boolean z = true;
            if (this.version < 3) {
                this.vpdSize.set(ProtocolTransformer.getVPDString(deviceTFP.tmp, deviceTFP.hum, deviceTFP.leafTemperatureC, true, this.vpdFormat));
            } else {
                this.vpdSize.set(ProtocolTransformer.getVPDString(deviceTFP.vpd, this.vpdFormat));
            }
            ObservableField<String> observableField = this.fanTypeTitle;
            byte b = this.type;
            observableField.set((b == 8 || b == 11 || b == 7) ? "LEVEL" : "FAN");
            byte b2 = this.type;
            if (b2 == 2 || b2 == 9 || b2 == 12) {
                this.fanVisibility.set(false);
                this.powerVisibility.set(true);
                ObservableBoolean observableBoolean = this.powerOff;
                if (deviceTFP.fan != 0) {
                    z = false;
                }
                observableBoolean.set(z);
                return;
            }
            this.fanVisibility.set(true);
            this.powerVisibility.set(false);
            this.fanSize.set(String.valueOf(deviceTFP.fan));
        }
    }

    /* access modifiers changed from: private */
    public void setModelInfo(DeviceModel deviceModel) {
        if (deviceModel != null) {
            refreshTFP(deviceModel.toTFP());
        }
    }

    public void deleteAll() {
        addSubscribe(this.repository.deleteAll(this.mac).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe());
    }

    public void deleteOldData(long j) {
        addSubscribe(this.repository.deleteOldData(this.mac, j).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe());
    }
}
