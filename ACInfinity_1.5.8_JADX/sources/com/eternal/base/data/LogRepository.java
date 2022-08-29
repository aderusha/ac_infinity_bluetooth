package com.eternal.base.data;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import androidx.paging.DataSource;
import com.alibaba.android.arouter.core.LogisticsCenter;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;
import com.eternal.base.C1323R;
import com.eternal.base.LogService;
import com.eternal.base.NotificationDialog;
import com.eternal.base.concat.BluetoothEvent;
import com.eternal.base.concat.DeviceModel;
import com.eternal.base.concat.LogExtra;
import com.eternal.base.concat.NetLog;
import com.eternal.base.concat.NotificationName;
import com.eternal.base.concat.PortInfo;
import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.data.ble.IBleControl;
import com.eternal.base.data.source.ILogSource;
import com.eternal.base.database.entity.Log;
import com.eternal.base.database.entity.Notification;
import com.eternal.base.database.entity.NotificationMessage;
import com.eternal.base.global.ActivityEvent;
import com.eternal.base.protocol.ProtocolTransformer;
import com.eternal.base.router.RouterActivityPath;
import com.eternal.base.utils.SoundPoolHelper;
import com.eternal.base.utils.TimeUtil;
import com.eternal.framework.bus.RxBus;
import com.eternal.framework.component.AppManager;
import com.eternal.framework.utils.KLog;
import com.eternal.framework.utils.SPUtils;
import com.eternal.framework.utils.Utils;
import com.google.common.base.Ascii;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Maybe;
import p014io.reactivex.MaybeEmitter;
import p014io.reactivex.MaybeOnSubscribe;
import p014io.reactivex.MaybeSource;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.android.schedulers.AndroidSchedulers;
import p014io.reactivex.functions.Action;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;
import p018me.leolin.shortcutbadger.ShortcutBadger;

public class LogRepository extends BaseRepository {
    /* access modifiers changed from: private */
    public Intent cache;
    /* access modifiers changed from: private */
    public String cacheStr;
    /* access modifiers changed from: private */
    public NotificationDialog dialog;
    private DialogInterface.OnDismissListener notificationListener = new DialogInterface.OnDismissListener() {
        public void onDismiss(DialogInterface dialogInterface) {
            if (LogRepository.this.cache == null || AppManager.getAppManager().getActivityCount() <= 0) {
                NotificationDialog unused = LogRepository.this.dialog = null;
                return;
            }
            NotificationDialog unused2 = LogRepository.this.dialog = new NotificationDialog(AppManager.getAppManager().currentActivity()).setSense(5000).setMessage(LogRepository.this.cacheStr).setIntent(LogRepository.this.cache).setDismissListener(this);
            LogRepository.this.dialog.show();
            Intent unused3 = LogRepository.this.cache = null;
            String unused4 = LogRepository.this.cacheStr = null;
        }
    };
    private int notifyId = 0;
    private int notifySize;
    private SoundPoolHelper soundPoolHelper;
    /* access modifiers changed from: private */
    public ILogSource source;

    public void stopPlay(String str) {
    }

    public LogRepository(ILogSource iLogSource, IBleControl iBleControl) {
        super(iBleControl);
        this.source = iLogSource;
        this.soundPoolHelper = new SoundPoolHelper(2, 5).setRingtoneType(2).load(Utils.getContext(), "beep1", C1323R.raw.alert_one_beep).load(Utils.getContext(), "beep2", C1323R.raw.alarm_two_beeps);
    }

    public DataSource.Factory<Integer, Log> getLogFactory(String str, List<Byte> list, List<Byte> list2) {
        return this.source.getLogs(str, list, list2);
    }

    public DataSource.Factory<Integer, Log> getLogFactory(String str, List<Byte> list) {
        return this.source.getLogs(str, list);
    }

    public Single<Long> resetTime(final String str) {
        return Single.create(new SingleOnSubscribe<Long>() {
            public void subscribe(SingleEmitter<Long> singleEmitter) throws Exception {
                Long valueOf = Long.valueOf(LogRepository.this.source.getFirstLog(str).time);
                LogRepository.this.setLogTime(valueOf, str);
                singleEmitter.onSuccess(valueOf);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setLogTime(Long l, String str) {
        long logTime = this.source.getLogTime(str);
        if (l.longValue() > 0 && logTime != l.longValue()) {
            this.source.setLogTime(str, l.longValue());
            SPUtils instance = SPUtils.getInstance();
            instance.put(str + "0", l.longValue());
        }
    }

    public Single<Boolean> setRead(final String str, final byte b, final Integer[] numArr) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                LogRepository.this.source.setRead(str, b, numArr);
                singleEmitter.onSuccess(true);
            }
        });
    }

    public Single<Long> resetCTime(final String str) {
        return Single.create(new SingleOnSubscribe<Long>() {
            public void subscribe(SingleEmitter<Long> singleEmitter) throws Exception {
                Long valueOf = Long.valueOf(LogRepository.this.source.getFirstCLog(str).time);
                LogRepository.this.setLogTime(valueOf, str);
                singleEmitter.onSuccess(valueOf);
            }
        });
    }

    public void clearNotify() {
        this.notifySize = 0;
    }

    public Single<Log> getFirstNetLog(final String str) {
        return Single.create(new SingleOnSubscribe<Log>() {
            public void subscribe(SingleEmitter<Log> singleEmitter) throws Exception {
                singleEmitter.onSuccess(LogRepository.this.source.getFirstNetLog(str));
            }
        });
    }

    public Single<LogExtra> getExtra(final String str, final byte b) {
        return Single.create(new SingleOnSubscribe<LogExtra>() {
            public void subscribe(SingleEmitter<LogExtra> singleEmitter) throws Exception {
                List<NotificationName> notificationNames = LogRepository.this.source.getNotificationNames(str);
                List<PortInfo> portInfo = LogRepository.this.source.getPortInfo(str);
                SparseArray sparseArray = new SparseArray(notificationNames.size() + portInfo.size());
                for (NotificationName next : notificationNames) {
                    sparseArray.put(next.f137id + (next.type * 1000) + (next.port * Ascii.DLE), next.name);
                }
                for (PortInfo next2 : portInfo) {
                    sparseArray.put(next2.f138id, ProtocolTransformer.getLogPortName(next2.f138id, b, next2.name));
                }
                singleEmitter.onSuccess(new LogExtra(LogRepository.this.source.getLogTime(str), LogRepository.this.source.isDegree(str), sparseArray, (byte) portInfo.size()));
            }
        });
    }

    public Single<Integer> getPortCount(final String str) {
        return Single.create(new SingleOnSubscribe<Integer>() {
            public void subscribe(SingleEmitter<Integer> singleEmitter) throws Exception {
                singleEmitter.onSuccess(Integer.valueOf(LogRepository.this.source.getPortInfo(str).size()));
            }
        });
    }

    public Single<List<NotificationMessage>> getNotificationMessage(final BleStatue bleStatue, byte b) {
        return this.control.getNotificationMessage(bleStatue, b).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).doOnSuccess(new Consumer<List<NotificationMessage>>() {
            public void accept(List<NotificationMessage> list) throws Exception {
                if (list != null && list.size() != 0) {
                    RxBus.getDefault().post(new BluetoothEvent((byte) 5, bleStatue.getMac(), list.get(0).isDegree ? 1 : 0));
                    for (NotificationMessage next : list) {
                        if (next.type == 0 || next.type == 1) {
                            if (next.notifyType != 0) {
                                LogRepository.this.showNotify(next, bleStatue);
                            }
                        } else if (next.type == 2) {
                            LogRepository.this.play(bleStatue.getMac());
                        }
                    }
                }
            }
        });
    }

    public Completable initLog(BleStatue bleStatue, final byte b) {
        if (bleStatue.getType() == 1 || bleStatue.getType() == 2 || bleStatue.getType() == 6) {
            return refreshLog(bleStatue).ignoreElement().doOnComplete(new Action() {
                public void run() throws Exception {
                    KLog.m65e("on complete set time");
                }
            }).doOnError(new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    KLog.m65e("on error set time");
                }
            });
        }
        if (bleStatue.getType() == 7 || bleStatue.getType() == 11 || bleStatue.getType() == 9 || bleStatue.getType() == 12) {
            return refreshELog(bleStatue, b).ignoreElement().doOnComplete(new Action() {
                public void run() throws Exception {
                    KLog.m65e("日志：初始化完成 port:" + b);
                }
            }).doOnError(new Consumer<Throwable>() {
                public void accept(Throwable th) throws Exception {
                    KLog.m65e("日志：初始化失败 port:" + b);
                }
            });
        }
        return refreshCLog(bleStatue).ignoreElement().doOnComplete(new Action() {
            public void run() throws Exception {
                KLog.m65e("on complete set time");
            }
        }).doOnError(new Consumer<Throwable>() {
            public void accept(Throwable th) throws Exception {
                KLog.m65e("on error set time");
            }
        });
    }

    public Maybe<Integer> refreshCLog(final BleStatue bleStatue) {
        return Single.create(new SingleOnSubscribe<Log>() {
            public void subscribe(SingleEmitter<Log> singleEmitter) {
                singleEmitter.onSuccess(LogRepository.this.source.getFirstCLog(bleStatue.getMac()));
            }
        }).observeOn(Schedulers.m983io()).subscribeOn(Schedulers.m983io()).flatMapMaybe(new Function<Log, MaybeSource<? extends Integer>>() {
            /* access modifiers changed from: private */
            public boolean hasNew;

            public MaybeSource<? extends Integer> apply(Log log) {
                this.hasNew = false;
                IBleControl iBleControl = LogRepository.this.control;
                BleStatue bleStatue = bleStatue;
                long j = 0;
                if (log.time != 0) {
                    j = (log.time / 1000) - 1577808000;
                }
                return iBleControl.getCLog(bleStatue, j / 60).observeOn(Schedulers.m983io()).doOnNext(new Consumer<Log>() {
                    public void accept(Log log) {
                        if (log.time != 0 && log.f142id != -1 && log.model != 0) {
                            log.time = ((log.time * 60) + 1577808000) * 1000;
                            log.mac = bleStatue.getMac();
                            try {
                                LogRepository.this.source.addLog(log);
                                boolean unused = C140714.this.hasNew = true;
                            } catch (Exception e) {
                                KLog.m65e(e);
                            }
                        }
                    }
                }).ignoreElements().andThen(Maybe.create(new MaybeOnSubscribe<Integer>() {
                    public void subscribe(MaybeEmitter<Integer> maybeEmitter) {
                        if (C140714.this.hasNew) {
                            maybeEmitter.onSuccess(0);
                        } else {
                            maybeEmitter.onComplete();
                        }
                    }
                }));
            }
        });
    }

    public Maybe<Integer> refreshLog(final BleStatue bleStatue) {
        return Single.create(new SingleOnSubscribe<Log>() {
            public void subscribe(SingleEmitter<Log> singleEmitter) {
                singleEmitter.onSuccess(LogRepository.this.source.getFirstLog(bleStatue.getDevice().getMac(), (byte) 0));
            }
        }).flatMapMaybe(new Function<Log, Maybe<Integer>>() {
            boolean hasNew;

            public Maybe<Integer> apply(Log log) {
                this.hasNew = false;
                return LogRepository.this.control.getLog(bleStatue, log.time, log.f142id).observeOn(Schedulers.m983io()).doOnNext(new Consumer<Log>() {
                    public void accept(Log log) {
                        if (LogRepository.this.checkLog(log)) {
                            log.mac = bleStatue.getDevice().getMac();
                            try {
                                LogRepository.this.source.addLog(log);
                                C141116.this.hasNew = true;
                            } catch (Exception e) {
                                KLog.m65e("添加日志 失败" + log.toString());
                                KLog.m65e(e);
                            }
                        }
                    }
                }).ignoreElements().andThen(Maybe.create(new MaybeOnSubscribe<Integer>() {
                    public void subscribe(MaybeEmitter<Integer> maybeEmitter) throws Exception {
                        if (C141116.this.hasNew) {
                            maybeEmitter.onSuccess(1);
                        } else {
                            maybeEmitter.onComplete();
                        }
                    }
                }));
            }
        });
    }

    public Maybe<Integer> refreshELog(final BleStatue bleStatue, final byte b) {
        return Single.create(new SingleOnSubscribe<Log>() {
            public void subscribe(SingleEmitter<Log> singleEmitter) {
                singleEmitter.onSuccess(LogRepository.this.source.getFirstLog(bleStatue.getDevice().getMac(), b));
            }
        }).flatMapMaybe(new Function<Log, Maybe<Integer>>() {
            boolean hasNew;

            public Maybe<Integer> apply(Log log) {
                this.hasNew = false;
                return LogRepository.this.control.getELog(bleStatue, b, log.time, log.f142id).observeOn(Schedulers.m983io()).doOnNext(new Consumer<Log>() {
                    public void accept(Log log) {
                        log.port = b;
                        if (LogRepository.this.checkLog(log)) {
                            log.mac = bleStatue.getDevice().getMac();
                            try {
                                LogRepository.this.source.addLog(log);
                                C141518.this.hasNew = true;
                            } catch (Exception unused) {
                            }
                        }
                    }
                }).ignoreElements().andThen(Maybe.create(new MaybeOnSubscribe<Integer>() {
                    public void subscribe(MaybeEmitter<Integer> maybeEmitter) throws Exception {
                        if (C141518.this.hasNew) {
                            maybeEmitter.onSuccess(1);
                        } else {
                            maybeEmitter.onComplete();
                        }
                    }
                }));
            }
        });
    }

    public Single<Boolean> addLogs(final String str, final List<NetLog> list) {
        return Single.create(new SingleOnSubscribe<Boolean>() {
            public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                for (NetLog log : list) {
                    Log log2 = log.toLog(str);
                    KLog.m65e(String.format(Locale.ENGLISH, "日志 拉取成功  id：%d 时间：%s", new Object[]{Integer.valueOf(log2.netId), TimeUtil.formatTime(log2.time)}));
                    if (LogRepository.this.checkLog(log2)) {
                        try {
                            LogRepository.this.source.addLog(log2);
                        } catch (Exception e) {
                            KLog.m65e(e);
                            KLog.m65e("日志 添加失败");
                        }
                    }
                }
                singleEmitter.onSuccess(true);
            }
        });
    }

    public Completable deleteAll(final String str) {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                LogRepository.this.source.deleteAll(str);
                completableEmitter.onComplete();
            }
        });
    }

    public Completable deleteOldData(final String str, final long j) {
        return Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                LogRepository.this.source.deleteOldData(str, j);
                completableEmitter.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean checkLog(Log log) {
        if (log.logType == 0 && log.notifyId != 0) {
            return false;
        }
        if ((log.logType == 1 || (log.start == 0 && log.end == 0)) && log.model >= 1 && log.model <= 10 && log.time >= 946656000000L) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void play(String str) {
        this.soundPoolHelper.play("beep1", false, str);
    }

    public void showNotify(final DeviceModel deviceModel, final BleStatue bleStatue) {
        if (deviceModel != null && deviceModel.alert != 0) {
            if (deviceModel.alert == 1) {
                if (deviceModel.autoHighTmpTrigger || deviceModel.autoLowTmpTrigger || deviceModel.autoHighHumTrigger || deviceModel.autoLowHumTrigger) {
                    Single.create(new SingleOnSubscribe<String>() {
                        public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                            singleEmitter.onSuccess(ProtocolTransformer.getNotifyString(deviceModel, LogRepository.this.source.getDeviceName(bleStatue.getMac(), (byte) 0).name));
                        }
                    }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                        public void accept(String str) {
                            LogRepository.this.configureNotify(bleStatue.getMac(), "", (byte) 0, str, (byte) bleStatue.getType(), bleStatue.getVersion(), bleStatue.softwareVersion, bleStatue.hardwareVersion, bleStatue.firmwareVersion, (byte) 0);
                            Single.create(new SingleOnSubscribe<Boolean>() {
                                public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                                    singleEmitter.onSuccess(true);
                                }
                            }).delay(1000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Boolean>() {
                                public void accept(Boolean bool) throws Exception {
                                    if (!LogService.getInstance().isLoding(bleStatue.getMac())) {
                                        LogService.getInstance().refresh(bleStatue.getMac(), (byte) 0);
                                    }
                                }
                            });
                        }
                    });
                }
            } else if (deviceModel.alert == 2) {
                play(bleStatue.getMac());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        if (r3.isPlug == 0) goto L_0x0039;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean showNotify(java.util.List<com.eternal.base.concat.NetPort> r16, long r17, int r19, byte r20, java.lang.String r21, java.lang.String r22, com.eternal.base.concat.NetMessageInfo r23, boolean r24, byte r25, byte r26) {
        /*
            r15 = this;
            r0 = r23
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            if (r20 != 0) goto L_0x000d
            int r2 = r0.portIndex
            if (r2 == 0) goto L_0x000d
            return r1
        L_0x000d:
            if (r20 == 0) goto L_0x003a
            int r2 = r0.portIndex
            if (r2 != 0) goto L_0x0014
            return r1
        L_0x0014:
            if (r16 == 0) goto L_0x0039
            int r2 = r16.size()
            int r3 = r0.portIndex
            if (r2 >= r3) goto L_0x001f
            goto L_0x0039
        L_0x001f:
            java.util.Iterator r2 = r16.iterator()
        L_0x0023:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x003a
            java.lang.Object r3 = r2.next()
            com.eternal.base.concat.NetPort r3 = (com.eternal.base.concat.NetPort) r3
            int r4 = r3.portIndex
            int r5 = r0.portIndex
            if (r4 != r5) goto L_0x0023
            byte r2 = r3.isPlug
            if (r2 != 0) goto L_0x003a
        L_0x0039:
            return r1
        L_0x003a:
            long r2 = r0.triggerTimeSecond
            int r4 = (r17 > r2 ? 1 : (r17 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0047
            int r2 = r0.type
            r3 = r19
            if (r3 != r2) goto L_0x0047
            return r1
        L_0x0047:
            int r2 = r0.type
            r3 = 1
            if (r2 == 0) goto L_0x005f
            int r2 = r0.type
            if (r2 != r3) goto L_0x0051
            goto L_0x005f
        L_0x0051:
            int r0 = r0.type
            r2 = 2
            if (r0 != r2) goto L_0x005d
            r2 = r15
            r5 = r21
            r15.play(r5)
            return r3
        L_0x005d:
            r2 = r15
            return r1
        L_0x005f:
            r2 = r15
            r5 = r21
            java.lang.String r8 = com.eternal.base.protocol.ProtocolTransformer.getNotifyString((com.eternal.base.concat.NetMessageInfo) r23, (boolean) r24)
            com.eternal.base.data.LogRepository r4 = com.eternal.base.data.RepositoryInjection.providerLogRepository()
            int r0 = r0.portIndex
            byte r7 = (byte) r0
            r14 = 1
            java.lang.String r11 = "1.0.4"
            java.lang.String r12 = "1.0"
            java.lang.String r13 = "1.0"
            r5 = r21
            r6 = r22
            r9 = r25
            r10 = r26
            r4.configureNotify(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.eternal.base.data.LogRepository.showNotify(java.util.List, long, int, byte, java.lang.String, java.lang.String, com.eternal.base.concat.NetMessageInfo, boolean, byte, byte):boolean");
    }

    /* access modifiers changed from: private */
    public void showNotify(final NotificationMessage notificationMessage, final BleStatue bleStatue) {
        Single.create(new SingleOnSubscribe<String>() {
            public void subscribe(SingleEmitter<String> singleEmitter) throws Exception {
                Notification notification;
                if (notificationMessage.type == 0) {
                    notification = LogRepository.this.source.getNotification(bleStatue.getMac(), notificationMessage.port, notificationMessage.notifyId, (byte) 3);
                } else {
                    notification = notificationMessage.type == 1 ? LogRepository.this.source.getNotification(bleStatue.getMac(), notificationMessage.port, notificationMessage.notifyId, (byte) 2) : null;
                }
                if (notification != null) {
                    singleEmitter.onSuccess(ProtocolTransformer.getNotifyString(notificationMessage, notification.name, notification.type, LogRepository.this.source.isDegree(bleStatue.getMac())));
                }
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
            public void accept(String str) {
                LogRepository.this.configureNotify(notificationMessage.mac, "", notificationMessage.port, str, (byte) bleStatue.getType(), bleStatue.getVersion(), bleStatue.softwareVersion, bleStatue.hardwareVersion, bleStatue.firmwareVersion, (byte) 0);
            }
        });
    }

    public synchronized void configureNotify(String str, String str2, byte b, String str3, byte b2, byte b3, String str4, String str5, String str6, byte b4) {
        NotificationCompat.Builder builder;
        Postcard build = ARouter.getInstance().build(RouterActivityPath.Main.PAGE_MAIN);
        LogisticsCenter.completion(build);
        Intent intent = new Intent(Utils.getContext(), build.getDestination());
        intent.setFlags(268435456);
        intent.setAction(ActivityEvent.ACTION_LOG);
        intent.putExtra(ActivityEvent.DEVICE_MAC, str);
        intent.putExtra(ActivityEvent.DEVICE_ID, str2);
        intent.putExtra(ActivityEvent.DEVICE_PORT, b);
        intent.putExtra(ActivityEvent.DEVICE_TYPE, b2);
        intent.putExtra(ActivityEvent.DEVICE_VERSION, b3);
        intent.putExtra(ActivityEvent.DEVICE_CONNECT_TYPE, b4);
        intent.putExtra(ActivityEvent.DEVICE_SOFTWARE_VERSION, str4);
        intent.putExtra(ActivityEvent.DEVICE_HARDWARE_VERSION, str5);
        intent.putExtra(ActivityEvent.DEVICE_FIRMWARE_VERSION, str6);
        if (AppManager.getAppManager().isBack()) {
            Context context = Utils.getContext();
            int i = this.notifySize + 1;
            this.notifySize = i;
            ShortcutBadger.applyCount(context, i);
            int i2 = 134217728;
            if (Build.VERSION.SDK_INT >= 31) {
                i2 = 167772160;
            }
            PendingIntent activity = PendingIntent.getActivity(Utils.getContext(), 0, intent, i2);
            NotificationManager notificationManager = (NotificationManager) Utils.getContext().getSystemService(ActivityEvent.NOTIFICATION);
            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle().bigText(str3);
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel(ActivityEvent.NOTIFICATION, ActivityEvent.NOTIFICATION, 4);
                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                builder = new NotificationCompat.Builder(Utils.getContext(), ActivityEvent.NOTIFICATION);
            } else {
                builder = new NotificationCompat.Builder(Utils.getContext());
            }
            if (Build.VERSION.SDK_INT < 31) {
                builder.setLargeIcon(BitmapFactory.decodeResource(Utils.getContext().getResources(), C1323R.mipmap.ic_launcher));
                builder.setSmallIcon(C1323R.mipmap.ic_launcher);
            } else {
                builder.setSmallIcon(C1323R.C1325drawable.default_notification_icon);
            }
            android.app.Notification build2 = builder.setStyle(bigText).setDefaults(1).setContentText(str3).setContentIntent(activity).setFullScreenIntent(activity, true).setPriority(2).build();
            int i3 = this.notifyId;
            this.notifyId = i3 + 1;
            notificationManager.notify(i3, build2);
        } else {
            NotificationDialog notificationDialog = this.dialog;
            if (notificationDialog == null || !notificationDialog.isShowing()) {
                NotificationDialog notificationDialog2 = new NotificationDialog(AppManager.getAppManager().currentActivity());
                this.dialog = notificationDialog2;
                notificationDialog2.setSense(5000).setMessage(str3).setIntent(intent);
                this.dialog.setDismissListener(this.notificationListener).show();
                showNotifyBar(str3, intent);
            } else {
                this.cache = intent;
                this.cacheStr = str3;
            }
        }
    }

    private void showNotifyBar(String str, Intent intent) {
        Notification.Builder builder;
        NotificationManager notificationManager = (NotificationManager) Utils.getContext().getSystemService(ActivityEvent.NOTIFICATION);
        if (Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel = new NotificationChannel(ActivityEvent.NOTIFICATION, ActivityEvent.NOTIFICATION, 3);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
            builder = new Notification.Builder(Utils.getContext(), ActivityEvent.NOTIFICATION);
        } else {
            builder = new Notification.Builder(Utils.getContext());
        }
        PendingIntent activity = PendingIntent.getActivity(Utils.getContext(), 0, intent, Build.VERSION.SDK_INT >= 31 ? 67108864 : 0);
        builder.setDefaults(1);
        builder.setContentIntent(activity);
        if (Build.VERSION.SDK_INT < 31) {
            builder.setLargeIcon(BitmapFactory.decodeResource(Utils.getContext().getResources(), C1323R.mipmap.ic_launcher));
            builder.setSmallIcon(C1323R.mipmap.ic_launcher);
        } else {
            builder.setSmallIcon(C1323R.C1325drawable.default_notification_icon);
        }
        builder.setAutoCancel(true);
        builder.setContentTitle(str);
        builder.setFullScreenIntent(activity, true);
        int i = this.notifyId;
        this.notifyId = i + 1;
        notificationManager.notify(i, builder.build());
    }
}
