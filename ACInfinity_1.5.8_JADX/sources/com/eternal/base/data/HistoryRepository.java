package com.eternal.base.data;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import com.eternal.base.LivePagedBuilder;
import com.eternal.base.concat.DeviceInfo;
import com.eternal.base.concat.NetHistory;
import com.eternal.base.concat.TmpHum;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.data.ble.IBleControl;
import com.eternal.base.data.source.IHistorySource;
import com.eternal.base.database.entity.History;
import com.eternal.framework.utils.KLog;
import java.util.List;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Flowable;
import p014io.reactivex.ObservableSource;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class HistoryRepository extends BaseRepository {
    private PagedList.C0510Config historyConfig;
    /* access modifiers changed from: private */
    public IHistorySource source;

    public HistoryRepository(IHistorySource iHistorySource, IBleControl iBleControl) {
        super(iBleControl);
        this.source = iHistorySource;
    }

    public Completable refreshHistory(String str) {
        BleStatue connect = getConnect(str);
        if (connect == null) {
            return Completable.complete();
        }
        if (connect.getType() == 1 || connect.getType() == 2 || connect.getType() == 6 || connect.getType() == 7 || connect.getType() == 11 || connect.getType() == 9 || connect.getType() == 12) {
            return refreshHistory(connect);
        }
        return refreshCHistory(connect);
    }

    private Completable refreshHistory(final BleStatue bleStatue) {
        return Single.create(new SingleOnSubscribe<Long>() {
            public void subscribe(SingleEmitter<Long> singleEmitter) {
                singleEmitter.onSuccess(Long.valueOf(HistoryRepository.this.source.getTime(bleStatue.getMac())));
            }
        }).flatMapObservable(new Function<Long, ObservableSource<History>>() {
            public ObservableSource<History> apply(Long l) {
                if (l.longValue() == 0) {
                    l = 946656000000L;
                }
                if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
                    return HistoryRepository.this.control.getEHistory(bleStatue, l.longValue());
                }
                return HistoryRepository.this.control.getHistory(bleStatue, l.longValue());
            }
        }).observeOn(Schedulers.m983io()).doOnNext(new Consumer<History>() {
            public void accept(History history) {
                if (history.hum <= 10000) {
                    try {
                        history.mac = bleStatue.getMac();
                        HistoryRepository.this.source.add(history);
                    } catch (Exception unused) {
                    }
                }
            }
        }).ignoreElements();
    }

    public boolean addHistorys(String str, List<NetHistory> list) {
        if (list == null || TextUtils.isEmpty(str)) {
            return false;
        }
        for (NetHistory history : list) {
            History history2 = history.toHistory(str);
            if (history2.hum <= 10000) {
                try {
                    this.source.add(history2);
                } catch (Exception e) {
                    KLog.m65e(e);
                }
            }
        }
        return true;
    }

    private Completable refreshCHistory(final BleStatue bleStatue) {
        return Single.create(new SingleOnSubscribe<Long>() {
            public void subscribe(SingleEmitter<Long> singleEmitter) {
                singleEmitter.onSuccess(Long.valueOf(HistoryRepository.this.source.getTime(bleStatue.getMac())));
            }
        }).flatMapObservable(new Function<Long, ObservableSource<History>>() {
            public ObservableSource<History> apply(Long l) {
                return HistoryRepository.this.control.getCHistory(bleStatue, ((l.longValue() / 1000) - 1577808000) / 60);
            }
        }).observeOn(Schedulers.m983io()).doOnNext(new Consumer<History>() {
            public void accept(History history) throws Exception {
                if (history.hum <= 10000) {
                    try {
                        history.mac = bleStatue.getMac();
                        history.time = (history.time * 60) + 1577808000;
                        HistoryRepository.this.source.add(history);
                    } catch (Exception unused) {
                    }
                }
            }
        }).ignoreElements();
    }

    public Completable deleteAll(final String str) {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                HistoryRepository.this.source.deleteAll(str);
                completableEmitter.onComplete();
            }
        });
    }

    public Completable deleteOldData(final String str, final long j) {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                HistoryRepository.this.source.deleteOldData(str, j);
                completableEmitter.onComplete();
            }
        });
    }

    public Flowable<DeviceInfo> getInfo(String str) {
        return this.source.getInfo(str);
    }

    public LiveData<PagedList<TmpHum>> getHistory(String str) {
        return new LivePagedBuilder(this.source.getHistory(str), getHistoryConfig()).build();
    }

    public Single<List<TmpHum>> getHistory(String str, long j, long j2) {
        final String str2 = str;
        final long j3 = j;
        final long j4 = j2;
        return Single.create(new SingleOnSubscribe<List<TmpHum>>() {
            public void subscribe(SingleEmitter<List<TmpHum>> singleEmitter) throws Exception {
                singleEmitter.onSuccess(HistoryRepository.this.source.getHistory(str2, j3, j4));
            }
        });
    }

    public Single<List<TmpHum>> getHistoryMin(String str, List<String> list, long j, long j2) {
        final String str2 = str;
        final List<String> list2 = list;
        final long j3 = j;
        final long j4 = j2;
        return Single.create(new SingleOnSubscribe<List<TmpHum>>() {
            public void subscribe(SingleEmitter<List<TmpHum>> singleEmitter) throws Exception {
                singleEmitter.onSuccess(HistoryRepository.this.source.getHistoryMin(str2, list2, j3, j4));
            }
        });
    }

    public Single<List<TmpHum>> getHistoryHour(String str, List<String> list, String str2, long j, long j2) {
        final String str3 = str;
        final List<String> list2 = list;
        final String str4 = str2;
        final long j3 = j;
        final long j4 = j2;
        return Single.create(new SingleOnSubscribe<List<TmpHum>>() {
            public void subscribe(SingleEmitter<List<TmpHum>> singleEmitter) throws Exception {
                singleEmitter.onSuccess(HistoryRepository.this.source.getHistoryHour(str3, list2, str4, j3, j4));
            }
        });
    }

    private PagedList.C0510Config getExportConfig() {
        return new PagedList.C0510Config.Builder().setPageSize(20).setEnablePlaceholders(true).setInitialLoadSizeHint(20).build();
    }

    private PagedList.C0510Config getHistoryConfig() {
        if (this.historyConfig == null) {
            this.historyConfig = new PagedList.C0510Config.Builder().setPageSize(262800).setEnablePlaceholders(false).setInitialLoadSizeHint(525600).build();
        }
        return this.historyConfig;
    }

    public long getLastTime(String str) {
        return this.source.getTime(str);
    }

    public Long getFirstTime(String str) {
        return Long.valueOf(this.source.getFirstTime(str));
    }
}
