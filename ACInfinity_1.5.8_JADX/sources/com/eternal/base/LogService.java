package com.eternal.base;

import com.eternal.base.data.LogRepository;
import com.eternal.base.data.RepositoryInjection;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.framework.utils.KLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableSource;
import p014io.reactivex.Flowable;
import p014io.reactivex.Maybe;
import p014io.reactivex.disposables.Disposable;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class LogService {
    private static LogService instance = new LogService();
    private List<LogDisposable> logDisposables = new ArrayList();
    private List<LogDisposable> notificationDisposables = new ArrayList();
    private LogRepository repository = RepositoryInjection.providerLogRepository();

    private LogService() {
    }

    public static LogService getInstance() {
        return instance;
    }

    public void remove(String str) {
        clearDisposables(str, this.logDisposables);
        clearDisposables(str, this.notificationDisposables);
        this.repository.stopPlay(str);
    }

    private void clearDisposables(String str, List<LogDisposable> list) {
        Iterator<LogDisposable> it = list.iterator();
        while (it.hasNext()) {
            LogDisposable next = it.next();
            if (next.mac.equals(str)) {
                if (next.disposable != null) {
                    next.disposable.dispose();
                }
                it.remove();
            }
        }
    }

    public void clear() {
        this.repository.stopPlay((String) null);
        for (LogDisposable next : this.logDisposables) {
            if (next.disposable != null) {
                next.disposable.dispose();
            }
        }
        this.logDisposables.clear();
        for (LogDisposable next2 : this.notificationDisposables) {
            if (next2.disposable != null) {
                next2.disposable.dispose();
            }
        }
        this.notificationDisposables.clear();
    }

    public boolean isLoding(String str) {
        for (LogDisposable next : this.logDisposables) {
            if (next.mac.equals(str) && !next.disposable.isDisposed()) {
                return true;
            }
        }
        return false;
    }

    public void refreshNotificationMessage(String str, byte b, boolean z) {
        Disposable disposable;
        BleStatue connect = this.repository.getConnect(str);
        if (connect != null) {
            if (z) {
                disposable = this.repository.getNotificationMessage(connect, b).repeatWhen(new Function<Flowable<Object>, Publisher<?>>() {
                    public Publisher<?> apply(Flowable<Object> flowable) throws Exception {
                        return flowable.delay(2, TimeUnit.SECONDS);
                    }
                }).ignoreElements().subscribe();
            } else {
                disposable = this.repository.getNotificationMessage(connect, b).ignoreElement().subscribe();
            }
            this.notificationDisposables.add(new LogDisposable(str, b, disposable));
        }
    }

    public void initAndRefresh(String str, byte b) {
        BleStatue connect = this.repository.getConnect(str);
        if (connect != null) {
            this.logDisposables.add(new LogDisposable(str, b, this.repository.initLog(connect, b).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe(new Action() {
                public void run() throws Exception {
                    KLog.m65e("end init refresh");
                }
            }, new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                    KLog.m65e(th);
                }
            })));
        }
    }

    public void initELog(String str, byte b) {
        BleStatue connect = this.repository.getConnect(str);
        if (connect != null) {
            Completable completable = null;
            for (byte b2 = 0; b2 <= b; b2 = (byte) (b2 + 1)) {
                if (b2 == 0) {
                    completable = this.repository.initLog(connect, b2).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io());
                } else {
                    completable = completable.andThen((CompletableSource) this.repository.initLog(connect, b2).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()));
                }
            }
            if (completable != null) {
                this.logDisposables.add(new LogDisposable(str, (byte) 0, completable.subscribe()));
            }
        }
    }

    public void refresh(String str, byte b) {
        Maybe<Integer> maybe;
        BleStatue connect = this.repository.getConnect(str);
        if (connect != null) {
            if (connect.getType() == 1 || connect.getType() == 2 || connect.getType() == 6) {
                maybe = this.repository.refreshLog(connect);
            } else if (connect.getType() == 7 || connect.getType() == 11 || connect.getType() == 9 || connect.getType() == 12) {
                maybe = this.repository.refreshELog(connect, b);
            } else {
                maybe = this.repository.refreshCLog(connect);
            }
            this.logDisposables.add(new LogDisposable(str, b, maybe.subscribeOn(Schedulers.m983io()).doOnSuccess(new Consumer<Integer>() {
                public void accept(Integer num) throws Exception {
                }
            }).ignoreElement().subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).subscribe()));
        }
    }

    public class LogDisposable {
        public Disposable disposable;
        public String mac;
        public byte port;

        public LogDisposable(String str, byte b, Disposable disposable2) {
            this.mac = str;
            this.port = b;
            this.disposable = disposable2;
        }
    }
}
