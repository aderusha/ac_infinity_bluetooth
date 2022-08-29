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
import com.eternal.log.C2303R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class LogCModel extends BaseViewModel {
    public MutableLiveData<Boolean> highHumSwitch = new MutableLiveData<>();
    public MutableLiveData<Boolean> highTmpSwitch = new MutableLiveData<>();
    public LiveData<PagedList<Log>> logs;
    public MutableLiveData<Boolean> lowHumSwitch = new MutableLiveData<>();
    public MutableLiveData<Boolean> lowTmpSwitch = new MutableLiveData<>();
    public String mac;
    public BindingCommand<Void> onDelete = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            LogCModel.this.show.setValue(false);
            Messenger.getDefault().sendNoMsg("show delete log dialog");
        }
    });
    public View.OnClickListener onFilter = new View.OnClickListener() {
        public void onClick(View view) {
            int id = view.getId();
            if (id == C2303R.C2306id.rl_high_tmp) {
                LogCModel logCModel = LogCModel.this;
                logCModel.changeState(logCModel.highTmpSwitch);
            } else if (id == C2303R.C2306id.rl_low_tmp) {
                LogCModel logCModel2 = LogCModel.this;
                logCModel2.changeState(logCModel2.lowTmpSwitch);
            } else if (id == C2303R.C2306id.rl_high_hum) {
                LogCModel logCModel3 = LogCModel.this;
                logCModel3.changeState(logCModel3.highHumSwitch);
            } else if (id == C2303R.C2306id.rl_low_hum) {
                LogCModel logCModel4 = LogCModel.this;
                logCModel4.changeState(logCModel4.lowHumSwitch);
            }
        }
    };
    public BindingCommand<Void> onName = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            if (LogCModel.this.show.getValue() == null || LogCModel.this.show.getValue() != Boolean.TRUE) {
                LogCModel.this.show.setValue(true);
            } else {
                LogCModel.this.show.setValue(false);
            }
        }
    });
    private LogRepository repository;
    public MutableLiveData<Boolean> show = new MutableLiveData<>();
    public byte type;

    public LogCModel(Application application) {
        super(application);
        this.show.setValue(false);
    }

    /* access modifiers changed from: private */
    public void changeState(MutableLiveData<Boolean> mutableLiveData) {
        mutableLiveData.setValue(Boolean.valueOf(!mutableLiveData.getValue().booleanValue()));
        Messenger.getDefault().sendNoMsg("refresh log");
    }

    public void refresh() {
        this.logs = getSource();
    }

    public Single<LogExtra> init(LogRepository logRepository, final String str, byte b) {
        this.type = b;
        all();
        addSubscribe(Single.create(new SingleOnSubscribe<String>() {
            public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                SPUtils instance = SPUtils.getInstance();
                singleEmitter.onSuccess(instance.getString(ActivityEvent.SHARED_PREFERENCES_KEY_LOG_CHECKED_TYPES + str));
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            public void accept(String str) throws Exception {
                ArrayList arrayList = new ArrayList();
                if (TextUtils.isEmpty(str)) {
                    arrayList.addAll(Arrays.asList(new Byte[]{(byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6}));
                } else {
                    arrayList.addAll(GsonUtils.getList(str, Byte.class));
                }
                if (arrayList.contains((byte) 1)) {
                    LogCModel.this.highTmpSwitch.setValue(true);
                }
                if (arrayList.contains((byte) 2)) {
                    LogCModel.this.lowTmpSwitch.setValue(true);
                }
                if (arrayList.contains((byte) 4)) {
                    LogCModel.this.highHumSwitch.setValue(true);
                }
                if (arrayList.contains((byte) 5)) {
                    LogCModel.this.lowHumSwitch.setValue(true);
                }
                LogCModel logCModel = LogCModel.this;
                logCModel.logs = logCModel.getSource();
            }
        }));
        this.mac = str;
        this.repository = logRepository;
        return getExtra();
    }

    /* access modifiers changed from: private */
    public LiveData<PagedList<Log>> getSource() {
        boolean z;
        boolean z2;
        ArrayList arrayList = new ArrayList(6);
        boolean z3 = true;
        if (this.highTmpSwitch.getValue() == Boolean.TRUE) {
            arrayList.add((byte) 1);
            z = true;
        } else {
            z = false;
        }
        if (this.lowTmpSwitch.getValue() == Boolean.TRUE) {
            arrayList.add((byte) 2);
            z = true;
        }
        if (this.highHumSwitch.getValue() == Boolean.TRUE) {
            arrayList.add((byte) 4);
            z2 = true;
        } else {
            z2 = false;
        }
        if (this.lowHumSwitch.getValue() == Boolean.TRUE) {
            arrayList.add((byte) 5);
        } else {
            z3 = z2;
        }
        if (z) {
            arrayList.add((byte) 3);
        }
        if (z3) {
            arrayList.add((byte) 6);
        }
        SPUtils instance = SPUtils.getInstance();
        instance.put(ActivityEvent.SHARED_PREFERENCES_KEY_LOG_CHECKED_TYPES + this.mac, GsonUtils.toJson(arrayList));
        if (arrayList.size() == 0) {
            return new LivePagedBuilder(new EmptyFactory(), new PagedList.C0510Config.Builder().setPageSize(12).setInitialLoadSizeHint(0).setEnablePlaceholders(false).build()).build();
        }
        return new LivePagedBuilder(this.repository.getLogFactory(this.mac, arrayList), new PagedList.C0510Config.Builder().setPageSize(15).setInitialLoadSizeHint(100).setEnablePlaceholders(false).build()).build();
    }

    private void all() {
        this.highTmpSwitch.setValue(false);
        this.lowTmpSwitch.setValue(false);
        this.highHumSwitch.setValue(false);
        this.lowHumSwitch.setValue(false);
    }

    public void resetTime(Consumer<Long> consumer) {
        addSubscribe(this.repository.resetCTime(this.mac).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(consumer));
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

    public void deleteAll() {
        this.repository.deleteAll(this.mac).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public void deleteOldData(long j) {
        this.repository.deleteOldData(this.mac, j).compose(RxUtils.bindToLifecycle(getLifecycleProvider())).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public Single<LogExtra> getExtra() {
        return this.repository.getExtra(this.mac, (byte) 15);
    }
}
