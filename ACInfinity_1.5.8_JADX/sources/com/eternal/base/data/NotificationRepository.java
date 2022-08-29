package com.eternal.base.data;

import com.eternal.base.data.ble.BleStatue;
import com.eternal.base.data.ble.IBleControl;
import com.eternal.base.data.source.INotificationSource;
import com.eternal.base.database.entity.Notification;
import java.util.ArrayList;
import java.util.List;
import p014io.reactivex.Completable;
import p014io.reactivex.CompletableEmitter;
import p014io.reactivex.CompletableOnSubscribe;
import p014io.reactivex.Single;
import p014io.reactivex.SingleEmitter;
import p014io.reactivex.SingleOnSubscribe;
import p014io.reactivex.SingleSource;
import p014io.reactivex.functions.Consumer;
import p014io.reactivex.functions.Function;
import p014io.reactivex.schedulers.Schedulers;

public class NotificationRepository extends BaseRepository {
    INotificationSource source;

    public NotificationRepository(INotificationSource iNotificationSource, IBleControl iBleControl) {
        super(iBleControl);
        this.source = iNotificationSource;
    }

    public Single<List<Notification>> getNotifications(final String str) {
        return Single.create(new SingleOnSubscribe<List<Notification>>() {
            public void subscribe(SingleEmitter<List<Notification>> singleEmitter) throws Exception {
                singleEmitter.onSuccess(NotificationRepository.this.source.getNotifications(str));
            }
        });
    }

    public Single<List<Notification>> getNotifications(BleStatue bleStatue, byte b) {
        return this.control.getNotifications(bleStatue, b);
    }

    public Single<List<Notification>> getNotifications(final String str, final byte b) {
        return Single.create(new SingleOnSubscribe<List<Notification>>() {
            public void subscribe(SingleEmitter<List<Notification>> singleEmitter) throws Exception {
                singleEmitter.onSuccess(NotificationRepository.this.source.getNotifications(str, b));
            }
        });
    }

    public Single<Boolean> openNotification(BleStatue bleStatue, final Notification notification) {
        return this.control.setNotification(bleStatue, notification, true, notification.open, true, false).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnSuccess(new Consumer<Boolean>() {
            public void accept(Boolean bool) {
                if (bool.booleanValue()) {
                    NotificationRepository.this.source.setNotificationOpen(notification.mac, notification.port, notification.f144id, notification.type, notification.open);
                }
            }
        });
    }

    public void setNotificationOpen(String str, byte b, int i, byte b2, boolean z) {
        final String str2 = str;
        final byte b3 = b;
        final int i2 = i;
        final byte b4 = b2;
        final boolean z2 = z;
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                NotificationRepository.this.source.setNotificationOpen(str2, b3, i2, b4, z2);
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }

    public Single<Boolean> removeNotification(BleStatue bleStatue, final Notification notification, final boolean z) {
        return this.control.setNotification(bleStatue, notification, false, false, true, z).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).map(new Function<Boolean, Boolean>() {
            public Boolean apply(Boolean bool) throws Exception {
                if (z) {
                    NotificationRepository.this.source.deleteNotification(notification.mac, notification.port, notification.f144id, notification.type, notification.childId);
                } else {
                    NotificationRepository.this.source.deleteNotification(notification.mac, notification.port, notification.f144id, notification.type);
                }
                return bool;
            }
        });
    }

    public Single<Boolean> updateSequences(BleStatue bleStatue, List<Byte> list, byte b, boolean z) {
        return this.control.setAutomationOrder(bleStatue, list, b, z).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).map(new Function<Boolean, Boolean>() {
            public Boolean apply(Boolean bool) throws Exception {
                return bool;
            }
        });
    }

    public Single<Boolean> clearNotification(String str, int i) {
        final BleStatue connect = getConnect(str);
        if (connect == null) {
            return Single.create(new SingleOnSubscribe<Boolean>() {
                public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                    singleEmitter.onSuccess(true);
                }
            });
        }
        ArrayList arrayList = new ArrayList();
        for (byte b = 0; b <= i; b = (byte) (b + 1)) {
            arrayList.add(getNotifications(connect, b).subscribeOn(Schedulers.m983io()).flatMap(new Function<List<Notification>, SingleSource<Boolean>>() {
                public SingleSource<Boolean> apply(List<Notification> list) throws Exception {
                    ArrayList arrayList = new ArrayList();
                    for (Notification removeNotification : list) {
                        arrayList.add(NotificationRepository.this.removeNotification(connect, removeNotification, false).subscribeOn(Schedulers.m983io()));
                    }
                    if (arrayList.size() > 0) {
                        return Single.zip(arrayList, new Function<Object[], Boolean>() {
                            public Boolean apply(Object[] objArr) throws Exception {
                                return true;
                            }
                        });
                    }
                    return Single.create(new SingleOnSubscribe<Boolean>() {
                        public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                            singleEmitter.onSuccess(true);
                        }
                    });
                }
            }));
        }
        if (arrayList.size() > 0) {
            return Single.zip(arrayList, new Function<Object[], Boolean>() {
                public Boolean apply(Object[] objArr) throws Exception {
                    return true;
                }
            });
        }
        return Single.create(new SingleOnSubscribe<Boolean>() {
            public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                singleEmitter.onSuccess(true);
            }
        });
    }

    public Single<Boolean> deleteNotification(String str, byte b, int i, byte b2) {
        final String str2 = str;
        final byte b3 = b;
        final int i2 = i;
        final byte b4 = b2;
        return Single.create(new SingleOnSubscribe<Boolean>() {
            public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                NotificationRepository.this.source.deleteNotification(str2, b3, i2, b4);
            }
        });
    }

    public Single<Boolean> nameIsExists(String str, byte b, String str2, byte b2) {
        final String str3 = str;
        final byte b3 = b;
        final String str4 = str2;
        final byte b4 = b2;
        return Single.create(new SingleOnSubscribe<Boolean>() {
            public void subscribe(SingleEmitter<Boolean> singleEmitter) throws Exception {
                singleEmitter.onSuccess(NotificationRepository.this.source.nameExists(str3, b3, str4, b4));
            }
        });
    }

    public Single<Boolean> saveNotification(final BleStatue bleStatue, final Notification notification, final boolean z) {
        return Single.create(new SingleOnSubscribe<Notification>() {
            public void subscribe(SingleEmitter<Notification> singleEmitter) throws Exception {
                if (z) {
                    NotificationRepository.this.source.create(notification, true, bleStatue.getVersion());
                } else if (NotificationRepository.this.source.getNotification(notification.mac, notification.port, notification.f144id, notification.type) != null) {
                    NotificationRepository.this.source.create(notification, false, bleStatue.getVersion());
                }
                singleEmitter.onSuccess(notification);
            }
        }).flatMap(new Function<Notification, SingleSource<? extends Boolean>>() {
            public SingleSource<? extends Boolean> apply(Notification notification) throws Exception {
                return NotificationRepository.this.control.setNotification(bleStatue, notification, true, z || notification.open, false, false);
            }
        }).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnSuccess(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    NotificationRepository.this.source.addNotification(notification);
                }
            }
        });
    }

    public Single<Notification> getName(final Notification notification, final boolean z, final byte b) {
        return Single.create(new SingleOnSubscribe<Notification>() {
            public void subscribe(SingleEmitter<Notification> singleEmitter) throws Exception {
                if (z) {
                    NotificationRepository.this.source.create(notification, true, b);
                } else if (NotificationRepository.this.source.getNotification(notification.mac, notification.port, notification.f144id, notification.type) != null) {
                    NotificationRepository.this.source.create(notification, false, b);
                }
                singleEmitter.onSuccess(notification);
            }
        });
    }

    public Single<Boolean> setNotification(BleStatue bleStatue, final Notification notification) {
        return this.control.setNotification(bleStatue, notification, true, notification.open, false, false).subscribeOn(Schedulers.m983io()).observeOn(Schedulers.m983io()).doOnSuccess(new Consumer<Boolean>() {
            public void accept(Boolean bool) throws Exception {
                if (bool.booleanValue()) {
                    NotificationRepository.this.source.setNotification(notification);
                }
            }
        });
    }

    public void setNotification(final Notification notification, final boolean z) {
        Completable.create(new CompletableOnSubscribe() {
            public void subscribe(CompletableEmitter completableEmitter) throws Exception {
                if (z) {
                    NotificationRepository.this.source.addNotification(notification);
                } else {
                    NotificationRepository.this.source.setNotification(notification);
                }
            }
        }).subscribeOn(Schedulers.m983io()).subscribe();
    }
}
