package com.eternal.log.model;

import android.app.Application;
import android.text.TextUtils;
import android.view.View;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.paging.PositionalDataSource;
import com.eternal.base.LivePagedBuilder;
import com.eternal.base.concat.LogConfig;
import com.eternal.base.concat.LogExtra;
import com.eternal.base.data.LogRepository;
import com.eternal.base.database.entity.Log;
import com.eternal.base.global.ActivityEvent;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.GsonUtils;
import com.eternal.framework.utils.RxUtils;
import com.eternal.framework.utils.SPUtils;
import com.eternal.log.LogFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class LogModel extends BaseViewModel {
    public MutableLiveData<Boolean> alarms = new MutableLiveData<>();
    public MutableLiveData<Boolean> auto = new MutableLiveData<>();
    public MutableLiveData<Boolean> automation = new MutableLiveData<>();
    public MutableLiveData<Boolean> cycle = new MutableLiveData<>();
    public String devId;
    public byte deviceType;
    public boolean isDegree;
    public LogConfig logConfig;
    public LiveData<PagedList<Log>> logs;
    public String mac;
    public MutableLiveData<Boolean> notification = new MutableLiveData<>();
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LogModel.this.show.setValue(false);
            Messenger.getDefault().sendNoMsg("show delete log dialog");
        }
    });
    public View.OnClickListener onFilter = new View.OnClickListener() {
        public void onClick(View view) {
            view.setSelected(!view.isSelected());
            Byte b = (Byte) view.getTag();
            if (b.byteValue() == 102) {
                if (view.isSelected()) {
                    if (!LogModel.this.logConfig.types.contains((byte) 2)) {
                        LogModel.this.logConfig.types.add((byte) 2);
                    }
                    if (!LogModel.this.logConfig.types.contains((byte) 3)) {
                        LogModel.this.logConfig.types.add((byte) 3);
                    }
                } else {
                    if (LogModel.this.logConfig.types.contains((byte) 2)) {
                        LogModel.this.logConfig.types.remove((byte) 2);
                    }
                    if (LogModel.this.logConfig.types.contains((byte) 3)) {
                        LogModel.this.logConfig.types.remove((byte) 3);
                    }
                }
            } else if (b.byteValue() == 101) {
                if (view.isSelected()) {
                    if (!LogModel.this.logConfig.types.contains((byte) 1)) {
                        LogModel.this.logConfig.types.add((byte) 1);
                    }
                } else if (LogModel.this.logConfig.types.contains((byte) 1)) {
                    LogModel.this.logConfig.types.remove((byte) 1);
                }
            } else if (view.isSelected()) {
                if (!LogModel.this.logConfig.portSelects.contains(b)) {
                    LogModel.this.logConfig.portSelects.add(b);
                }
            } else if (LogModel.this.logConfig.portSelects.contains(b)) {
                LogModel.this.logConfig.portSelects.remove(b);
            }
            Messenger.getDefault().sendNoMsg("refresh log");
        }
    };
    public BindingCommand<Void> onName = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (LogModel.this.show.getValue() == null || LogModel.this.show.getValue() != Boolean.TRUE) {
                LogModel.this.show.setValue(true);
            } else {
                LogModel.this.show.setValue(false);
            }
        }
    });
    public byte port;
    private LogRepository repository;
    public MutableLiveData<Boolean> schedVisible = new MutableLiveData<>();
    public MutableLiveData<Boolean> schedules = new MutableLiveData<>();
    public MutableLiveData<Boolean> show = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeOff = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeOn = new MutableLiveData<>();
    public MutableLiveData<Boolean> vpd = new MutableLiveData<>();
    public MutableLiveData<Boolean> vpdVisible = new MutableLiveData<>();

    public LogModel(Application application) {
        super(application);
        this.show.setValue(false);
    }

    private void changeState(MutableLiveData<Boolean> mutableLiveData) {
        mutableLiveData.setValue(Boolean.valueOf(!mutableLiveData.getValue().booleanValue()));
    }

    public void refresh() {
        this.logs = getSource();
    }

    public void init(LogRepository logRepository, String str, String str2, byte b, byte b2) {
        this.mac = str;
        this.devId = str2;
        this.port = b;
        this.repository = logRepository;
        this.deviceType = b2;
        boolean z = true;
        this.vpdVisible.setValue(Boolean.valueOf(b2 == 11));
        MutableLiveData<Boolean> mutableLiveData = this.schedVisible;
        if (b2 == 6) {
            z = false;
        }
        mutableLiveData.setValue(Boolean.valueOf(z));
    }

    /* access modifiers changed from: private */
    public LiveData<PagedList<Log>> getSource() {
        final String json = GsonUtils.toJson(this.logConfig.portSelects);
        final String json2 = GsonUtils.toJson(this.logConfig.types);
        Single.create(new SingleOnSubscribe<Boolean>() {
            public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                SPUtils instance = SPUtils.getInstance();
                instance.put(ActivityEvent.SHARED_PREFERENCES_KEY_LOG_CHECKED_PORT_MODELS + LogModel.this.mac, json);
                SPUtils instance2 = SPUtils.getInstance();
                instance2.put(ActivityEvent.SHARED_PREFERENCES_KEY_LOG_CHECKED_TYPES + LogModel.this.mac, json2);
            }
        }).subscribeOn(Schedulers.m983io()).ignoreElement().subscribe();
        if (this.logConfig.portSelects.size() == 0 && this.logConfig.types.size() == 0) {
            return new LivePagedBuilder(new EmptyFactory(), new PagedList.C0510Config.Builder().setPageSize(12).setInitialLoadSizeHint(0).setEnablePlaceholders(false).build()).build();
        }
        return new LivePagedBuilder(this.repository.getLogFactory(this.mac, this.logConfig.types, this.logConfig.portSelects), new PagedList.C0510Config.Builder().setPageSize(15).setInitialLoadSizeHint(100).setEnablePlaceholders(false).build()).build();
    }

    private void all() {
        this.auto.setValue(false);
        this.schedules.setValue(false);
        this.vpd.setValue(false);
        this.timeOn.setValue(false);
        this.timeOff.setValue(false);
        this.cycle.setValue(false);
        this.alarms.setValue(false);
        this.notification.setValue(false);
        this.automation.setValue(false);
    }

    public void resetTime(Consumer<Long> consumer) {
        addSubscribe(this.repository.resetTime(this.mac).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer));
    }

    public void setRead(Integer[] numArr, Consumer<Boolean> consumer) {
        addSubscribe(this.repository.setRead(this.mac, this.port, numArr).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer));
    }

    public void deleteAll() {
        this.repository.deleteAll(this.mac).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public void deleteOldData(long j) {
        this.repository.deleteOldData(this.mac, j).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public Single<LogExtra> initAdapter() {
        return this.repository.getPortCount(this.mac).subscribeOn(Schedulers.m983io()).map(new Function<Integer, LogConfig>() {
            public LogConfig apply(Integer num) throws Exception {
                List<Byte> list;
                SPUtils instance = SPUtils.getInstance();
                String string = instance.getString(ActivityEvent.SHARED_PREFERENCES_KEY_LOG_CHECKED_TYPES + LogModel.this.mac);
                ArrayList arrayList = new ArrayList();
                if (TextUtils.isEmpty(string)) {
                    arrayList.addAll(Arrays.asList(new Byte[]{(byte) 1, (byte) 3, (byte) 2}));
                } else {
                    arrayList.addAll(GsonUtils.getList(string, Byte.class));
                    if (arrayList.contains((byte) 3) && !arrayList.contains((byte) 2)) {
                        arrayList.add((byte) 2);
                    } else if (!arrayList.contains((byte) 3) && arrayList.contains((byte) 2)) {
                        arrayList.add((byte) 3);
                    }
                }
                SPUtils instance2 = SPUtils.getInstance();
                String string2 = instance2.getString(ActivityEvent.SHARED_PREFERENCES_KEY_LOG_CHECKED_PORT_MODELS + LogModel.this.mac);
                byte byteValue = num.byteValue();
                if (TextUtils.isEmpty(string2)) {
                    list = new ArrayList<>();
                    for (byte b = 0; b < byteValue; b = (byte) (b + 1)) {
                        list.add(Byte.valueOf(b));
                    }
                } else {
                    list = GsonUtils.getList(string2, Byte.class);
                }
                return new LogConfig(list, byteValue, arrayList);
            }
        }).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(new Consumer<LogConfig>() {
            public void accept(LogConfig logConfig) throws Exception {
                LogModel.this.logConfig = logConfig;
                Messenger.getDefault().send(logConfig, LogFragment.REFRESH_FILTER_VIEW);
                LogModel logModel = LogModel.this;
                logModel.logs = logModel.getSource();
            }
        }).ignoreElement().andThen(this.repository.getExtra(this.mac, this.deviceType).subscribeOn(Schedulers.m983io()));
    }

    public Single<LogExtra> getExtra() {
        return this.repository.getExtra(this.mac, this.deviceType);
    }

    static class EmptyFactory extends DataSource.Factory<Integer, Log> {
        static final List<Log> empty = new ArrayList();

        EmptyFactory() {
        }

        public DataSource<Integer, Log> create() {
            return new PositionalDataSource<Log>() {
                public void loadInitial(PositionalDataSource.LoadInitialParams loadInitialParams, PositionalDataSource.LoadInitialCallback<Log> loadInitialCallback) {
                    loadInitialCallback.onResult(EmptyFactory.empty, loadInitialParams.requestedStartPosition);
                }

                public void loadRange(PositionalDataSource.LoadRangeParams loadRangeParams, PositionalDataSource.LoadRangeCallback<Log> loadRangeCallback) {
                    loadRangeCallback.onResult(EmptyFactory.empty);
                }
            };
        }
    }
}
