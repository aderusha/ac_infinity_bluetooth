package com.eternal.base.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.p005db.SupportSQLiteStatement;
import com.eternal.base.concat.NotificationName;
import com.eternal.base.database.entity.Notification;
import com.eternal.framework.http.cookie.SerializableCookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class NotificationDao_Impl implements NotificationDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Notification> __insertionAdapterOfNotification;
    private final SharedSQLiteStatement __preparedStmtOfDeleteNotification;
    private final SharedSQLiteStatement __preparedStmtOfDeleteNotification_1;
    private final SharedSQLiteStatement __preparedStmtOfDeleteNotification_2;
    private final SharedSQLiteStatement __preparedStmtOfUpdateOpen;
    private final EntityDeletionOrUpdateAdapter<Notification> __updateAdapterOfNotification;

    public NotificationDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfNotification = new EntityInsertionAdapter<Notification>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `Notification` (`mac`,`id`,`name`,`open`,`type`,`model`,`levelOn`,`levelOff`,`tmpHum`,`start`,`end`,`cycleOn`,`cycleOff`,`hTmpC`,`lTmpC`,`hTmpF`,`lTmpF`,`hHum`,`lHum`,`hVpd`,`lVpd`,`port`,`advId`,`devId`,`devCode`,`nameCode`,`codeAddress`,`advActive`,`groupIndex`,`childId`,`childIndex`,`day`,`beeps`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Notification notification) {
                if (notification.mac == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, notification.mac);
                }
                supportSQLiteStatement.bindLong(2, (long) notification.f144id);
                if (notification.name == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, notification.name);
                }
                supportSQLiteStatement.bindLong(4, notification.open ? 1 : 0);
                supportSQLiteStatement.bindLong(5, (long) notification.type);
                supportSQLiteStatement.bindLong(6, (long) notification.model);
                supportSQLiteStatement.bindLong(7, (long) notification.levelOn);
                supportSQLiteStatement.bindLong(8, (long) notification.levelOff);
                supportSQLiteStatement.bindLong(9, (long) notification.tmpHum);
                supportSQLiteStatement.bindLong(10, (long) notification.start);
                supportSQLiteStatement.bindLong(11, (long) notification.end);
                supportSQLiteStatement.bindLong(12, (long) notification.cycleOn);
                supportSQLiteStatement.bindLong(13, (long) notification.cycleOff);
                supportSQLiteStatement.bindLong(14, (long) notification.hTmpC);
                supportSQLiteStatement.bindLong(15, (long) notification.lTmpC);
                supportSQLiteStatement.bindLong(16, (long) notification.hTmpF);
                supportSQLiteStatement.bindLong(17, (long) notification.lTmpF);
                supportSQLiteStatement.bindLong(18, (long) notification.hHum);
                supportSQLiteStatement.bindLong(19, (long) notification.lHum);
                supportSQLiteStatement.bindLong(20, (long) notification.hVpd);
                supportSQLiteStatement.bindLong(21, (long) notification.lVpd);
                supportSQLiteStatement.bindLong(22, (long) notification.port);
                if (notification.advId == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, notification.advId);
                }
                if (notification.devId == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, notification.devId);
                }
                if (notification.devCode == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, notification.devCode);
                }
                supportSQLiteStatement.bindLong(26, (long) notification.nameCode);
                supportSQLiteStatement.bindLong(27, (long) notification.codeAddress);
                supportSQLiteStatement.bindLong(28, notification.advActive ? 1 : 0);
                supportSQLiteStatement.bindLong(29, (long) notification.groupIndex);
                supportSQLiteStatement.bindLong(30, (long) notification.childId);
                supportSQLiteStatement.bindLong(31, (long) notification.childIndex);
                supportSQLiteStatement.bindLong(32, (long) notification.day);
                supportSQLiteStatement.bindLong(33, (long) notification.beeps);
            }
        };
        this.__updateAdapterOfNotification = new EntityDeletionOrUpdateAdapter<Notification>(roomDatabase) {
            public String createQuery() {
                return "UPDATE OR ABORT `Notification` SET `mac` = ?,`id` = ?,`name` = ?,`open` = ?,`type` = ?,`model` = ?,`levelOn` = ?,`levelOff` = ?,`tmpHum` = ?,`start` = ?,`end` = ?,`cycleOn` = ?,`cycleOff` = ?,`hTmpC` = ?,`lTmpC` = ?,`hTmpF` = ?,`lTmpF` = ?,`hHum` = ?,`lHum` = ?,`hVpd` = ?,`lVpd` = ?,`port` = ?,`advId` = ?,`devId` = ?,`devCode` = ?,`nameCode` = ?,`codeAddress` = ?,`advActive` = ?,`groupIndex` = ?,`childId` = ?,`childIndex` = ?,`day` = ?,`beeps` = ? WHERE `mac` = ? AND `id` = ? AND `type` = ? AND `port` = ? AND `childId` = ?";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Notification notification) {
                if (notification.mac == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, notification.mac);
                }
                supportSQLiteStatement.bindLong(2, (long) notification.f144id);
                if (notification.name == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, notification.name);
                }
                supportSQLiteStatement.bindLong(4, notification.open ? 1 : 0);
                supportSQLiteStatement.bindLong(5, (long) notification.type);
                supportSQLiteStatement.bindLong(6, (long) notification.model);
                supportSQLiteStatement.bindLong(7, (long) notification.levelOn);
                supportSQLiteStatement.bindLong(8, (long) notification.levelOff);
                supportSQLiteStatement.bindLong(9, (long) notification.tmpHum);
                supportSQLiteStatement.bindLong(10, (long) notification.start);
                supportSQLiteStatement.bindLong(11, (long) notification.end);
                supportSQLiteStatement.bindLong(12, (long) notification.cycleOn);
                supportSQLiteStatement.bindLong(13, (long) notification.cycleOff);
                supportSQLiteStatement.bindLong(14, (long) notification.hTmpC);
                supportSQLiteStatement.bindLong(15, (long) notification.lTmpC);
                supportSQLiteStatement.bindLong(16, (long) notification.hTmpF);
                supportSQLiteStatement.bindLong(17, (long) notification.lTmpF);
                supportSQLiteStatement.bindLong(18, (long) notification.hHum);
                supportSQLiteStatement.bindLong(19, (long) notification.lHum);
                supportSQLiteStatement.bindLong(20, (long) notification.hVpd);
                supportSQLiteStatement.bindLong(21, (long) notification.lVpd);
                supportSQLiteStatement.bindLong(22, (long) notification.port);
                if (notification.advId == null) {
                    supportSQLiteStatement.bindNull(23);
                } else {
                    supportSQLiteStatement.bindString(23, notification.advId);
                }
                if (notification.devId == null) {
                    supportSQLiteStatement.bindNull(24);
                } else {
                    supportSQLiteStatement.bindString(24, notification.devId);
                }
                if (notification.devCode == null) {
                    supportSQLiteStatement.bindNull(25);
                } else {
                    supportSQLiteStatement.bindString(25, notification.devCode);
                }
                supportSQLiteStatement.bindLong(26, (long) notification.nameCode);
                supportSQLiteStatement.bindLong(27, (long) notification.codeAddress);
                supportSQLiteStatement.bindLong(28, notification.advActive ? 1 : 0);
                supportSQLiteStatement.bindLong(29, (long) notification.groupIndex);
                supportSQLiteStatement.bindLong(30, (long) notification.childId);
                supportSQLiteStatement.bindLong(31, (long) notification.childIndex);
                supportSQLiteStatement.bindLong(32, (long) notification.day);
                supportSQLiteStatement.bindLong(33, (long) notification.beeps);
                if (notification.mac == null) {
                    supportSQLiteStatement.bindNull(34);
                } else {
                    supportSQLiteStatement.bindString(34, notification.mac);
                }
                supportSQLiteStatement.bindLong(35, (long) notification.f144id);
                supportSQLiteStatement.bindLong(36, (long) notification.type);
                supportSQLiteStatement.bindLong(37, (long) notification.port);
                supportSQLiteStatement.bindLong(38, (long) notification.childId);
            }
        };
        this.__preparedStmtOfDeleteNotification = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from notification where mac=? and port=? and id=? and type=?";
            }
        };
        this.__preparedStmtOfDeleteNotification_1 = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from notification where mac=? and id=? and type=? and childId=?";
            }
        };
        this.__preparedStmtOfDeleteNotification_2 = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from notification where mac=? and id=? and type=?";
            }
        };
        this.__preparedStmtOfUpdateOpen = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update notification set open=? where mac=? and port=? and id=? and type=?";
            }
        };
    }

    public void insertNotification(Notification notification) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfNotification.insert(notification);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(Notification notification) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfNotification.handle(notification);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteNotification(String str, byte b, int i, byte b2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteNotification.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) b);
        acquire.bindLong(3, (long) i);
        acquire.bindLong(4, (long) b2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteNotification.release(acquire);
        }
    }

    public void deleteNotification(String str, int i, byte b, byte b2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteNotification_1.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) i);
        acquire.bindLong(3, (long) b);
        acquire.bindLong(4, (long) b2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteNotification_1.release(acquire);
        }
    }

    public void deleteNotification(String str, int i, byte b) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDeleteNotification_2.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) i);
        acquire.bindLong(3, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteNotification_2.release(acquire);
        }
    }

    public void updateOpen(String str, byte b, int i, byte b2, boolean z) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfUpdateOpen.acquire();
        acquire.bindLong(1, z ? 1 : 0);
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        acquire.bindLong(3, (long) b);
        acquire.bindLong(4, (long) i);
        acquire.bindLong(5, (long) b2);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfUpdateOpen.release(acquire);
        }
    }

    public List<Notification> loadMacAll(String str, byte b) {
        RoomSQLiteQuery roomSQLiteQuery;
        ArrayList arrayList;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from notification where mac=? and port=? order by id asc, type asc, name asc", 2);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        acquire.bindLong(2, (long) b);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SerializableCookie.NAME);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "open");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "model");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "levelOn");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "levelOff");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "tmpHum");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "start");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "end");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "cycleOn");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "cycleOff");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "hTmpC");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "lTmpC");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "hTmpF");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lTmpF");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "hHum");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "lHum");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "hVpd");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "lVpd");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "port");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "advId");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "devId");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "devCode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "nameCode");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "codeAddress");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "advActive");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "groupIndex");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "childId");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "childIndex");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "day");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "beeps");
                int i5 = columnIndexOrThrow14;
                ArrayList arrayList2 = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Notification notification = new Notification();
                    if (query.isNull(columnIndexOrThrow)) {
                        arrayList = arrayList2;
                        notification.mac = null;
                    } else {
                        arrayList = arrayList2;
                        notification.mac = query.getString(columnIndexOrThrow);
                    }
                    notification.f144id = query.getInt(columnIndexOrThrow2);
                    if (query.isNull(columnIndexOrThrow3)) {
                        notification.name = null;
                    } else {
                        notification.name = query.getString(columnIndexOrThrow3);
                    }
                    notification.open = query.getInt(columnIndexOrThrow4) != 0;
                    notification.type = (byte) query.getShort(columnIndexOrThrow5);
                    notification.model = (byte) query.getShort(columnIndexOrThrow6);
                    notification.levelOn = query.getInt(columnIndexOrThrow7);
                    notification.levelOff = query.getInt(columnIndexOrThrow8);
                    notification.tmpHum = (byte) query.getShort(columnIndexOrThrow9);
                    notification.start = (char) query.getInt(columnIndexOrThrow10);
                    notification.end = (char) query.getInt(columnIndexOrThrow11);
                    notification.cycleOn = (char) query.getInt(columnIndexOrThrow12);
                    notification.cycleOff = (char) query.getInt(columnIndexOrThrow13);
                    int i6 = i5;
                    int i7 = columnIndexOrThrow;
                    notification.hTmpC = (byte) query.getShort(i6);
                    int i8 = columnIndexOrThrow15;
                    int i9 = i6;
                    notification.lTmpC = (byte) query.getShort(i8);
                    int i10 = columnIndexOrThrow16;
                    int i11 = i8;
                    notification.hTmpF = (byte) query.getShort(i10);
                    int i12 = columnIndexOrThrow17;
                    int i13 = i10;
                    notification.lTmpF = (byte) query.getShort(i12);
                    int i14 = columnIndexOrThrow18;
                    int i15 = i12;
                    notification.hHum = (byte) query.getShort(i14);
                    int i16 = columnIndexOrThrow19;
                    int i17 = i14;
                    notification.lHum = (byte) query.getShort(i16);
                    int i18 = columnIndexOrThrow20;
                    int i19 = i16;
                    notification.hVpd = (byte) query.getShort(i18);
                    int i20 = columnIndexOrThrow21;
                    int i21 = i18;
                    notification.lVpd = (byte) query.getShort(i20);
                    int i22 = columnIndexOrThrow22;
                    int i23 = i20;
                    notification.port = (byte) query.getShort(i22);
                    int i24 = columnIndexOrThrow23;
                    if (query.isNull(i24)) {
                        i = i22;
                        notification.advId = null;
                    } else {
                        i = i22;
                        notification.advId = query.getString(i24);
                    }
                    int i25 = columnIndexOrThrow24;
                    if (query.isNull(i25)) {
                        i2 = i24;
                        notification.devId = null;
                    } else {
                        i2 = i24;
                        notification.devId = query.getString(i25);
                    }
                    int i26 = columnIndexOrThrow25;
                    if (query.isNull(i26)) {
                        i3 = i25;
                        notification.devCode = null;
                    } else {
                        i3 = i25;
                        notification.devCode = query.getString(i26);
                    }
                    int i27 = columnIndexOrThrow26;
                    int i28 = i26;
                    notification.nameCode = query.getInt(i27);
                    int i29 = columnIndexOrThrow27;
                    int i30 = i27;
                    notification.codeAddress = query.getInt(i29);
                    int i31 = columnIndexOrThrow28;
                    if (query.getInt(i31) != 0) {
                        i4 = i29;
                        z = true;
                    } else {
                        i4 = i29;
                        z = false;
                    }
                    notification.advActive = z;
                    int i32 = columnIndexOrThrow29;
                    int i33 = i31;
                    notification.groupIndex = (byte) query.getShort(i32);
                    int i34 = columnIndexOrThrow30;
                    int i35 = i32;
                    notification.childId = (byte) query.getShort(i34);
                    int i36 = columnIndexOrThrow31;
                    int i37 = i34;
                    notification.childIndex = (byte) query.getShort(i36);
                    int i38 = columnIndexOrThrow32;
                    int i39 = i36;
                    notification.day = (byte) query.getShort(i38);
                    int i40 = columnIndexOrThrow33;
                    int i41 = i38;
                    notification.beeps = (byte) query.getShort(i40);
                    arrayList2 = arrayList;
                    arrayList2.add(notification);
                    int i42 = i41;
                    columnIndexOrThrow33 = i40;
                    columnIndexOrThrow = i7;
                    i5 = i9;
                    columnIndexOrThrow15 = i11;
                    columnIndexOrThrow16 = i13;
                    columnIndexOrThrow17 = i15;
                    columnIndexOrThrow18 = i17;
                    columnIndexOrThrow19 = i19;
                    columnIndexOrThrow20 = i21;
                    columnIndexOrThrow21 = i23;
                    columnIndexOrThrow22 = i;
                    columnIndexOrThrow23 = i2;
                    columnIndexOrThrow24 = i3;
                    columnIndexOrThrow25 = i28;
                    columnIndexOrThrow26 = i30;
                    columnIndexOrThrow27 = i4;
                    columnIndexOrThrow28 = i33;
                    columnIndexOrThrow29 = i35;
                    columnIndexOrThrow30 = i37;
                    columnIndexOrThrow31 = i39;
                    columnIndexOrThrow32 = i42;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList2;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<Notification> loadMacAll(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        ArrayList arrayList;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from notification where mac=? order by type asc", 1);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SerializableCookie.NAME);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "open");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "model");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "levelOn");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "levelOff");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "tmpHum");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "start");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "end");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "cycleOn");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "cycleOff");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "hTmpC");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "lTmpC");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "hTmpF");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lTmpF");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "hHum");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "lHum");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "hVpd");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "lVpd");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "port");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "advId");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "devId");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "devCode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "nameCode");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "codeAddress");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "advActive");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "groupIndex");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "childId");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "childIndex");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "day");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "beeps");
                int i5 = columnIndexOrThrow14;
                ArrayList arrayList2 = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Notification notification = new Notification();
                    if (query.isNull(columnIndexOrThrow)) {
                        arrayList = arrayList2;
                        notification.mac = null;
                    } else {
                        arrayList = arrayList2;
                        notification.mac = query.getString(columnIndexOrThrow);
                    }
                    notification.f144id = query.getInt(columnIndexOrThrow2);
                    if (query.isNull(columnIndexOrThrow3)) {
                        notification.name = null;
                    } else {
                        notification.name = query.getString(columnIndexOrThrow3);
                    }
                    notification.open = query.getInt(columnIndexOrThrow4) != 0;
                    notification.type = (byte) query.getShort(columnIndexOrThrow5);
                    notification.model = (byte) query.getShort(columnIndexOrThrow6);
                    notification.levelOn = query.getInt(columnIndexOrThrow7);
                    notification.levelOff = query.getInt(columnIndexOrThrow8);
                    notification.tmpHum = (byte) query.getShort(columnIndexOrThrow9);
                    notification.start = (char) query.getInt(columnIndexOrThrow10);
                    notification.end = (char) query.getInt(columnIndexOrThrow11);
                    notification.cycleOn = (char) query.getInt(columnIndexOrThrow12);
                    notification.cycleOff = (char) query.getInt(columnIndexOrThrow13);
                    int i6 = i5;
                    int i7 = columnIndexOrThrow;
                    notification.hTmpC = (byte) query.getShort(i6);
                    int i8 = columnIndexOrThrow15;
                    int i9 = i6;
                    notification.lTmpC = (byte) query.getShort(i8);
                    int i10 = columnIndexOrThrow16;
                    int i11 = i8;
                    notification.hTmpF = (byte) query.getShort(i10);
                    int i12 = columnIndexOrThrow17;
                    int i13 = i10;
                    notification.lTmpF = (byte) query.getShort(i12);
                    int i14 = columnIndexOrThrow18;
                    int i15 = i12;
                    notification.hHum = (byte) query.getShort(i14);
                    int i16 = columnIndexOrThrow19;
                    int i17 = i14;
                    notification.lHum = (byte) query.getShort(i16);
                    int i18 = columnIndexOrThrow20;
                    int i19 = i16;
                    notification.hVpd = (byte) query.getShort(i18);
                    int i20 = columnIndexOrThrow21;
                    int i21 = i18;
                    notification.lVpd = (byte) query.getShort(i20);
                    int i22 = columnIndexOrThrow22;
                    int i23 = i20;
                    notification.port = (byte) query.getShort(i22);
                    int i24 = columnIndexOrThrow23;
                    if (query.isNull(i24)) {
                        i = i22;
                        notification.advId = null;
                    } else {
                        i = i22;
                        notification.advId = query.getString(i24);
                    }
                    int i25 = columnIndexOrThrow24;
                    if (query.isNull(i25)) {
                        i2 = i24;
                        notification.devId = null;
                    } else {
                        i2 = i24;
                        notification.devId = query.getString(i25);
                    }
                    int i26 = columnIndexOrThrow25;
                    if (query.isNull(i26)) {
                        i3 = i25;
                        notification.devCode = null;
                    } else {
                        i3 = i25;
                        notification.devCode = query.getString(i26);
                    }
                    int i27 = columnIndexOrThrow26;
                    int i28 = i26;
                    notification.nameCode = query.getInt(i27);
                    int i29 = columnIndexOrThrow27;
                    int i30 = i27;
                    notification.codeAddress = query.getInt(i29);
                    int i31 = columnIndexOrThrow28;
                    if (query.getInt(i31) != 0) {
                        i4 = i29;
                        z = true;
                    } else {
                        i4 = i29;
                        z = false;
                    }
                    notification.advActive = z;
                    int i32 = columnIndexOrThrow29;
                    int i33 = i31;
                    notification.groupIndex = (byte) query.getShort(i32);
                    int i34 = columnIndexOrThrow30;
                    int i35 = i32;
                    notification.childId = (byte) query.getShort(i34);
                    int i36 = columnIndexOrThrow31;
                    int i37 = i34;
                    notification.childIndex = (byte) query.getShort(i36);
                    int i38 = columnIndexOrThrow32;
                    int i39 = i36;
                    notification.day = (byte) query.getShort(i38);
                    int i40 = columnIndexOrThrow33;
                    int i41 = i38;
                    notification.beeps = (byte) query.getShort(i40);
                    arrayList2 = arrayList;
                    arrayList2.add(notification);
                    int i42 = i41;
                    columnIndexOrThrow33 = i40;
                    columnIndexOrThrow = i7;
                    i5 = i9;
                    columnIndexOrThrow15 = i11;
                    columnIndexOrThrow16 = i13;
                    columnIndexOrThrow17 = i15;
                    columnIndexOrThrow18 = i17;
                    columnIndexOrThrow19 = i19;
                    columnIndexOrThrow20 = i21;
                    columnIndexOrThrow21 = i23;
                    columnIndexOrThrow22 = i;
                    columnIndexOrThrow23 = i2;
                    columnIndexOrThrow24 = i3;
                    columnIndexOrThrow25 = i28;
                    columnIndexOrThrow26 = i30;
                    columnIndexOrThrow27 = i4;
                    columnIndexOrThrow28 = i33;
                    columnIndexOrThrow29 = i35;
                    columnIndexOrThrow30 = i37;
                    columnIndexOrThrow31 = i39;
                    columnIndexOrThrow32 = i42;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList2;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public List<NotificationName> loadMacAllName(String str, byte b) {
        String str2;
        Object obj;
        String str3 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select id,name,type,port,childId,childIndex,groupIndex from notification where mac=? and port=?", 2);
        if (str3 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str3);
        }
        acquire.bindLong(2, (long) b);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                int i = query.getInt(0);
                if (query.isNull(1)) {
                    str2 = null;
                } else {
                    str2 = query.getString(1);
                }
                new NotificationName(i, str2, (byte) query.getShort(2), (byte) query.getShort(3), (byte) query.getShort(6), (byte) query.getShort(4), (byte) query.getShort(5));
                arrayList.add(obj);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<NotificationName> loadMacAllName(String str) {
        String str2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select id,name,type,port,childId,childIndex,groupIndex from notification where mac=?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                int i = query.getInt(0);
                if (query.isNull(1)) {
                    str2 = null;
                } else {
                    str2 = query.getString(1);
                }
                arrayList.add(new NotificationName(i, str2, (byte) query.getShort(2), (byte) query.getShort(3), (byte) query.getShort(6), (byte) query.getShort(4), (byte) query.getShort(5)));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public Notification loadByMacAndId(String str, byte b, int i, byte b2) {
        RoomSQLiteQuery roomSQLiteQuery;
        Notification notification;
        int i2;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from notification where mac=? and port=? and id=? and type=?", 4);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        acquire.bindLong(2, (long) b);
        acquire.bindLong(3, (long) i);
        acquire.bindLong(4, (long) b2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, SerializableCookie.NAME);
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "open");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "model");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "levelOn");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "levelOff");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "tmpHum");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "start");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "end");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "cycleOn");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "cycleOff");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "hTmpC");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "lTmpC");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "hTmpF");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lTmpF");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "hHum");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "lHum");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "hVpd");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "lVpd");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "port");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "advId");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "devId");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "devCode");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "nameCode");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(query, "codeAddress");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(query, "advActive");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(query, "groupIndex");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(query, "childId");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(query, "childIndex");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(query, "day");
                int columnIndexOrThrow33 = CursorUtil.getColumnIndexOrThrow(query, "beeps");
                if (query.moveToFirst()) {
                    int i3 = columnIndexOrThrow33;
                    Notification notification2 = new Notification();
                    if (query.isNull(columnIndexOrThrow)) {
                        i2 = columnIndexOrThrow14;
                        notification2.mac = null;
                    } else {
                        i2 = columnIndexOrThrow14;
                        notification2.mac = query.getString(columnIndexOrThrow);
                    }
                    notification2.f144id = query.getInt(columnIndexOrThrow2);
                    if (query.isNull(columnIndexOrThrow3)) {
                        notification2.name = null;
                    } else {
                        notification2.name = query.getString(columnIndexOrThrow3);
                    }
                    notification2.open = query.getInt(columnIndexOrThrow4) != 0;
                    notification2.type = (byte) query.getShort(columnIndexOrThrow5);
                    notification2.model = (byte) query.getShort(columnIndexOrThrow6);
                    notification2.levelOn = query.getInt(columnIndexOrThrow7);
                    notification2.levelOff = query.getInt(columnIndexOrThrow8);
                    notification2.tmpHum = (byte) query.getShort(columnIndexOrThrow9);
                    notification2.start = (char) query.getInt(columnIndexOrThrow10);
                    notification2.end = (char) query.getInt(columnIndexOrThrow11);
                    notification2.cycleOn = (char) query.getInt(columnIndexOrThrow12);
                    notification2.cycleOff = (char) query.getInt(columnIndexOrThrow13);
                    notification2.hTmpC = (byte) query.getShort(i2);
                    notification2.lTmpC = (byte) query.getShort(columnIndexOrThrow15);
                    notification2.hTmpF = (byte) query.getShort(columnIndexOrThrow16);
                    notification2.lTmpF = (byte) query.getShort(columnIndexOrThrow17);
                    notification2.hHum = (byte) query.getShort(columnIndexOrThrow18);
                    notification2.lHum = (byte) query.getShort(columnIndexOrThrow19);
                    notification2.hVpd = (byte) query.getShort(columnIndexOrThrow20);
                    notification2.lVpd = (byte) query.getShort(columnIndexOrThrow21);
                    notification2.port = (byte) query.getShort(columnIndexOrThrow22);
                    int i4 = columnIndexOrThrow23;
                    if (query.isNull(i4)) {
                        notification2.advId = null;
                    } else {
                        notification2.advId = query.getString(i4);
                    }
                    int i5 = columnIndexOrThrow24;
                    if (query.isNull(i5)) {
                        notification2.devId = null;
                    } else {
                        notification2.devId = query.getString(i5);
                    }
                    int i6 = columnIndexOrThrow25;
                    if (query.isNull(i6)) {
                        notification2.devCode = null;
                    } else {
                        notification2.devCode = query.getString(i6);
                    }
                    notification2.nameCode = query.getInt(columnIndexOrThrow26);
                    notification2.codeAddress = query.getInt(columnIndexOrThrow27);
                    notification2.advActive = query.getInt(columnIndexOrThrow28) != 0;
                    notification2.groupIndex = (byte) query.getShort(columnIndexOrThrow29);
                    notification2.childId = (byte) query.getShort(columnIndexOrThrow30);
                    notification2.childIndex = (byte) query.getShort(columnIndexOrThrow31);
                    notification2.day = (byte) query.getShort(columnIndexOrThrow32);
                    notification2.beeps = (byte) query.getShort(i3);
                    notification = notification2;
                } else {
                    notification = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return notification;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public String loadNameByMac(String str, byte b, String str2, List<Byte> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select name from notification where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and port=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and name=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and type in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) b);
        if (str2 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str2);
        }
        int i = 4;
        for (Byte next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, (long) next.byteValue());
            }
            i++;
        }
        this.__db.assertNotSuspendingTransaction();
        String str3 = null;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                if (!query.isNull(0)) {
                    str3 = query.getString(0);
                }
            }
            return str3;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
