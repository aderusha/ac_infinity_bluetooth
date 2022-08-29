package com.eternal.export;

import android.app.Application;
import android.text.TextUtils;
import android.widget.RadioGroup;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.eternal.base.concat.ConnectInfo;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.data.HistoryRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.framework.binding.command.BindingAction;
import com.eternal.framework.binding.command.BindingCommand;
import com.eternal.framework.bus.Messenger;
import com.eternal.framework.component.BaseViewModel;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.Utils;
import com.google.common.collect.Lists;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import p014io.reactivex.Observable;
import p014io.reactivex.ObservableEmitter;
import p014io.reactivex.ObservableOnSubscribe;
import p014io.reactivex.Single;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.schedulers.Schedulers;

public class ExportModel extends BaseViewModel {
    private String devId;
    public MutableLiveData<String> end = new MutableLiveData<>();
    public int endDayNum;
    public MutableLiveData<Boolean> endEdit = new MutableLiveData<>();
    public int endHourNum;
    public boolean endIsAm;
    public int endMinuteNum;
    public MutableLiveData<Integer> endMonthNum = new MutableLiveData<>();
    public MutableLiveData<Integer> endYearNum = new MutableLiveData<>();
    public int frequency = 1;
    public RadioGroup.OnCheckedChangeListener frequencyListener = new RadioGroup.OnCheckedChangeListener() {
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if (i == C2164R.C2167id.frequency_15) {
                ExportModel.this.frequency = 15;
            } else if (i == C2164R.C2167id.frequency_30) {
                ExportModel.this.frequency = 30;
            } else if (i == C2164R.C2167id.frequency_60) {
                ExportModel.this.frequency = 60;
            } else if (i == C2164R.C2167id.frequency_720) {
                ExportModel.this.frequency = 720;
            } else if (i == C2164R.C2167id.frequency_1440) {
                ExportModel.this.frequency = 1440;
            }
        }
    };
    /* access modifiers changed from: private */
    public String mac;
    public ArrayList<String> months = Lists.newArrayList((E[]) new String[]{"JAN.", "FEB.", "MAR.", "APR.", "MAY.", "JUN.", "JUL.", "AUG.", "SEP.", "OCT.", "NOV.", "DEC."});
    public BindingCommand<Void> onBack = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            ExportModel.this.finishAnimate(C2164R.anim.left_in, C2164R.anim.right_out);
        }
    });
    public BindingCommand<Void> onExport = new BindingCommand<>((BindingAction) new BindingAction() {
        public void call() {
            Messenger.getDefault().sendNoMsg(ExportActivity.EXPORT);
        }
    });
    private byte port;
    private HistoryRepository repository;
    public MutableLiveData<String> start = new MutableLiveData<>();
    public int startDayNum;
    public MutableLiveData<Boolean> startEdit = new MutableLiveData<>();
    public int startHourNum;
    public boolean startIsAm;
    public int startMinuteNum;
    public MutableLiveData<Integer> startMonthNum = new MutableLiveData<>();
    public MutableLiveData<Integer> startYearNum = new MutableLiveData<>();
    private TimeStore store = new TimeStore();
    public Disposable timeRefresh;
    public MutableLiveData<String> timeText = new MutableLiveData<>();
    public MutableLiveData<Boolean> timeVisible = new MutableLiveData<>();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void updateTime() {
    }

    public ExportModel(Application application) {
        super(application);
    }

    private void saveStart() {
        this.store.startYearNum = this.startYearNum.getValue().intValue();
        this.store.startMonthNum = this.startMonthNum.getValue().intValue();
        this.store.startDayNum = this.startDayNum;
        this.store.startHourNum = this.startHourNum;
        this.store.startMinuteNum = this.startMinuteNum;
    }

    private void saveEnd() {
        this.store.endMonthNum = this.endMonthNum.getValue().intValue();
        this.store.endYearNum = this.endYearNum.getValue().intValue();
        this.store.endDayNum = this.endDayNum;
        this.store.endHourNum = this.endHourNum;
        this.store.endMinuteNum = this.endMinuteNum;
    }

    public void loadStart() {
        this.startYearNum.setValue(Integer.valueOf(this.store.startYearNum));
        this.startMonthNum.setValue(Integer.valueOf(this.store.startMonthNum));
        this.startDayNum = this.store.startDayNum;
        this.startHourNum = this.store.startHourNum;
        this.startMinuteNum = this.store.startMinuteNum;
        updateStartTime();
    }

    public void loadEnd() {
        this.endYearNum.setValue(Integer.valueOf(this.store.endYearNum));
        this.endMonthNum.setValue(Integer.valueOf(this.store.endMonthNum));
        this.endDayNum = this.store.endDayNum;
        this.endHourNum = this.store.endHourNum;
        this.endMinuteNum = this.store.endMinuteNum;
        updateEndTime();
    }

    public Observable<ArrayList<String>> init(final HistoryRepository historyRepository, final String str, String str2, byte b) {
        this.repository = historyRepository;
        this.mac = str;
        this.devId = str2;
        this.port = b;
        return Observable.create(new ObservableOnSubscribe<ArrayList<String>>() {
            public void subscribe(ObservableEmitter<ArrayList<String>> observableEmitter) {
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(historyRepository.getFirstTime(str).longValue());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm am @ MMM d yyyy", Locale.ENGLISH);
                ExportModel.this.start.postValue(simpleDateFormat.format(instance.getTime()));
                ExportModel.this.startYearNum.postValue(Integer.valueOf(instance.get(1)));
                ExportModel.this.startDayNum = instance.get(5);
                ExportModel.this.startHourNum = instance.get(11);
                ExportModel.this.startIsAm = true;
                if (ExportModel.this.startHourNum >= 12) {
                    ExportModel.this.startHourNum -= 12;
                    ExportModel.this.startIsAm = false;
                }
                if (ExportModel.this.startHourNum == 0) {
                    ExportModel.this.startHourNum = 12;
                }
                ExportModel.this.startMinuteNum = instance.get(12);
                ExportModel.this.startMonthNum.postValue(Integer.valueOf(instance.get(2)));
                instance.setTimeInMillis(System.currentTimeMillis());
                ExportModel.this.end.postValue(simpleDateFormat.format(instance.getTime()));
                ExportModel.this.endYearNum.postValue(Integer.valueOf(instance.get(1)));
                ExportModel.this.endDayNum = instance.get(5);
                ExportModel.this.endHourNum = instance.get(11);
                ExportModel.this.endIsAm = true;
                if (ExportModel.this.endHourNum >= 12) {
                    ExportModel.this.endHourNum -= 12;
                    ExportModel.this.endIsAm = false;
                }
                if (ExportModel.this.endHourNum == 0) {
                    ExportModel.this.endHourNum = 12;
                }
                ExportModel.this.endMinuteNum = instance.get(12);
                ExportModel.this.endMonthNum.postValue(Integer.valueOf(instance.get(2)));
                observableEmitter.onNext(ExportModel.this.createNumber(1970, 2999));
                observableEmitter.onComplete();
            }
        });
    }

    private void refreshTime() {
        if (!TextUtils.isEmpty(this.mac)) {
            Disposable disposable = this.timeRefresh;
            if (disposable == null || disposable.isDisposed()) {
                Disposable subscribe = RepositoryInjection.providerDeviceRepository().getConnectInfo(this.mac).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ConnectInfo>() {
                    public void accept(ConnectInfo connectInfo) {
                        if (RepositoryInjection.providerDeviceRepository().isConnect(ExportModel.this.mac)) {
                            ExportModel.this.timeVisible.setValue(false);
                            return;
                        }
                        ExportModel.this.timeVisible.setValue(true);
                        ExportModel.this.timeText.setValue(Utils.getString(C2164R.string.tip_last, ProtocolTransformer.getTime(connectInfo.connectTime)));
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

    public Single<List<TmpHum>> export(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(1000 * j);
        int i = this.frequency;
        if (i == 1) {
            return this.repository.getHistory(this.mac, j, j2);
        }
        if (i == 15) {
            int i2 = instance.get(12);
            ArrayList newArrayListWithCapacity = Lists.newArrayListWithCapacity(4);
            newArrayListWithCapacity.add(format(i2));
            newArrayListWithCapacity.add(format((i2 + 15) % 60));
            newArrayListWithCapacity.add(format((i2 + 30) % 60));
            newArrayListWithCapacity.add(format((i2 + 45) % 60));
            return this.repository.getHistoryMin(this.mac, newArrayListWithCapacity, j, j2);
        } else if (i == 30) {
            int i3 = instance.get(12);
            ArrayList newArrayListWithCapacity2 = Lists.newArrayListWithCapacity(2);
            newArrayListWithCapacity2.add(format(i3));
            newArrayListWithCapacity2.add(format((i3 + 30) % 60));
            return this.repository.getHistoryMin(this.mac, newArrayListWithCapacity2, j, j2);
        } else if (i == 60) {
            int i4 = instance.get(12);
            return this.repository.getHistoryMin(this.mac, Lists.newArrayList((E[]) new String[]{format(i4)}), j, j2);
        } else if (i == 720) {
            int i5 = instance.get(10);
            ArrayList newArrayListWithCapacity3 = Lists.newArrayListWithCapacity(2);
            newArrayListWithCapacity3.add(format(i5));
            newArrayListWithCapacity3.add(format((i5 + 12) % 24));
            return this.repository.getHistoryHour(this.mac, newArrayListWithCapacity3, format(instance.get(12)), j, j2);
        } else {
            int i6 = instance.get(10);
            return this.repository.getHistoryHour(this.mac, Lists.newArrayList((E[]) new String[]{format(i6)}), format(instance.get(12)), j, j2);
        }
    }

    public final String format(int i) {
        return String.format(Locale.ENGLISH, "%02d", new Object[]{Integer.valueOf(i)});
    }

    public final ArrayList<String> createNumber(int i, int i2) {
        ArrayList<String> newArrayListWithCapacity = Lists.newArrayListWithCapacity((i2 - i) + 1);
        while (i <= i2) {
            newArrayListWithCapacity.add(String.valueOf(i));
            i++;
        }
        return newArrayListWithCapacity;
    }

    public void updateStartTime() {
        this.start.setValue(String.format(Locale.ENGLISH, "%d:%02d %s @ %s %d %d", new Object[]{Integer.valueOf(this.startHourNum), Integer.valueOf(this.startMinuteNum), this.startIsAm ? "AM" : "PM", this.months.get(this.startMonthNum.getValue().intValue()), Integer.valueOf(this.startDayNum), this.startYearNum.getValue()}));
    }

    public void updateEndTime() {
        this.end.setValue(String.format(Locale.ENGLISH, "%d:%02d %s @ %s %d %d", new Object[]{Integer.valueOf(this.endHourNum), Integer.valueOf(this.endMinuteNum), this.endIsAm ? "AM" : "PM", this.months.get(this.endMonthNum.getValue().intValue()), Integer.valueOf(this.endDayNum), this.endYearNum.getValue()}));
    }

    public Observable<DeviceInfo> getDeviceInfo() {
        return this.repository.getInfo(this.mac).toObservable();
    }

    private static class TimeStore {
        public int endDayNum;
        public int endHourNum;
        public int endMinuteNum;
        public int endMonthNum;
        public int endYearNum;
        public int startDayNum;
        public int startHourNum;
        public int startMinuteNum;
        public int startMonthNum;
        public int startYearNum;

        private TimeStore() {
        }
    }
}
