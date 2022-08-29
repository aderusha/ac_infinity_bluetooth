package com.eternal.base.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.paging.DataSource;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.p005db.SupportSQLiteStatement;
import com.eternal.base.database.entity.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlinx.coroutines.DebugKt;

public final class LogDao_Impl implements LogDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<Log> __insertionAdapterOfLog;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfRemoveOther;
    private final SharedSQLiteStatement __preparedStmtOfSetDelete;
    private final SharedSQLiteStatement __preparedStmtOfSetDeleteLessTime;
    private final SharedSQLiteStatement __preparedStmtOfSetRead;

    public LogDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfLog = new EntityInsertionAdapter<Log>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `Log` (`mac`,`id`,`time`,`logType`,`notifyId`,`start`,`end`,`day`,`model`,`fan`,`tmpHum`,`hTmpF`,`lTmpF`,`hTmpC`,`lTmpC`,`hVpd`,`lVpd`,`hHum`,`lHum`,`on`,`off`,`isStart`,`isDelete`,`isRead`,`port`,`netId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, Log log) {
                if (log.mac == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, log.mac);
                }
                supportSQLiteStatement.bindLong(2, (long) log.f142id);
                supportSQLiteStatement.bindLong(3, log.time);
                supportSQLiteStatement.bindLong(4, (long) log.logType);
                supportSQLiteStatement.bindLong(5, (long) log.notifyId);
                supportSQLiteStatement.bindLong(6, (long) log.start);
                supportSQLiteStatement.bindLong(7, (long) log.end);
                supportSQLiteStatement.bindLong(8, (long) log.day);
                supportSQLiteStatement.bindLong(9, (long) log.model);
                supportSQLiteStatement.bindLong(10, (long) log.fan);
                supportSQLiteStatement.bindLong(11, (long) log.tmpHum);
                supportSQLiteStatement.bindLong(12, (long) log.hTmpF);
                supportSQLiteStatement.bindLong(13, (long) log.lTmpF);
                supportSQLiteStatement.bindLong(14, (long) log.hTmpC);
                supportSQLiteStatement.bindLong(15, (long) log.lTmpC);
                supportSQLiteStatement.bindLong(16, (long) log.hVpd);
                supportSQLiteStatement.bindLong(17, (long) log.lVpd);
                supportSQLiteStatement.bindLong(18, (long) log.hHum);
                supportSQLiteStatement.bindLong(19, (long) log.lHum);
                supportSQLiteStatement.bindLong(20, (long) log.f143on);
                supportSQLiteStatement.bindLong(21, (long) log.off);
                supportSQLiteStatement.bindLong(22, log.isStart ? 1 : 0);
                supportSQLiteStatement.bindLong(23, log.isDelete ? 1 : 0);
                supportSQLiteStatement.bindLong(24, log.isRead ? 1 : 0);
                supportSQLiteStatement.bindLong(25, (long) log.port);
                supportSQLiteStatement.bindLong(26, (long) log.netId);
            }
        };
        this.__preparedStmtOfSetDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update log set isDelete=1 where mac=? and time=?";
            }
        };
        this.__preparedStmtOfSetDeleteLessTime = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update log set isDelete=1 where mac=? and time<?";
            }
        };
        this.__preparedStmtOfSetRead = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update log set isRead=1 where mac=? and port=?";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from log where mac=?";
            }
        };
        this.__preparedStmtOfRemoveOther = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from log where mac=? and time!=?";
            }
        };
    }

    public void insertLog(Log log) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfLog.insert(log);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void setDelete(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetDelete.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetDelete.release(acquire);
        }
    }

    public void setDeleteLessTime(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetDeleteLessTime.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetDeleteLessTime.release(acquire);
        }
    }

    public void setRead(String str, byte b) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetRead.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) b);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetRead.release(acquire);
        }
    }

    public void clear(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfClear.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfClear.release(acquire);
        }
    }

    public void removeOther(String str, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveOther.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveOther.release(acquire);
        }
    }

    public List<Log> loadLogByTime(String str, long j, long j2) {
        RoomSQLiteQuery roomSQLiteQuery;
        ArrayList arrayList;
        boolean z;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from log where mac=? and time>=? and time<=? order by time desc, id desc", 3);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mac");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "logType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "notifyId");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "end");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "day");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "model");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fan");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "tmpHum");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "hTmpF");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "lTmpF");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "hTmpC");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "lTmpC");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "hVpd");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lVpd");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "hHum");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "lHum");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_ON);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "isStart");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "isDelete");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "isRead");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "port");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "netId");
                int i = columnIndexOrThrow14;
                ArrayList arrayList2 = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    Log log = new Log();
                    if (query.isNull(columnIndexOrThrow)) {
                        arrayList = arrayList2;
                        log.mac = null;
                    } else {
                        arrayList = arrayList2;
                        log.mac = query.getString(columnIndexOrThrow);
                    }
                    log.f142id = query.getInt(columnIndexOrThrow2);
                    int i2 = columnIndexOrThrow12;
                    log.time = query.getLong(columnIndexOrThrow3);
                    log.logType = (byte) query.getShort(columnIndexOrThrow4);
                    log.notifyId = (byte) query.getShort(columnIndexOrThrow5);
                    log.start = (char) query.getInt(columnIndexOrThrow6);
                    log.end = (char) query.getInt(columnIndexOrThrow7);
                    log.day = (byte) query.getShort(columnIndexOrThrow8);
                    log.model = (byte) query.getShort(columnIndexOrThrow9);
                    log.fan = (byte) query.getShort(columnIndexOrThrow10);
                    log.tmpHum = query.getShort(columnIndexOrThrow11);
                    log.hTmpF = (byte) query.getShort(i2);
                    int i3 = columnIndexOrThrow13;
                    log.lTmpF = (byte) query.getShort(i3);
                    int i4 = i;
                    int i5 = columnIndexOrThrow;
                    log.hTmpC = (byte) query.getShort(i4);
                    int i6 = columnIndexOrThrow15;
                    int i7 = i2;
                    log.lTmpC = (byte) query.getShort(i6);
                    int i8 = columnIndexOrThrow16;
                    columnIndexOrThrow15 = i6;
                    log.hVpd = (byte) query.getShort(i8);
                    int i9 = columnIndexOrThrow17;
                    columnIndexOrThrow16 = i8;
                    log.lVpd = (byte) query.getShort(i9);
                    columnIndexOrThrow17 = i9;
                    int i10 = columnIndexOrThrow18;
                    log.hHum = (byte) query.getShort(i10);
                    columnIndexOrThrow18 = i10;
                    int i11 = columnIndexOrThrow19;
                    log.lHum = (byte) query.getShort(i11);
                    columnIndexOrThrow19 = i11;
                    int i12 = columnIndexOrThrow20;
                    log.f143on = (char) query.getInt(i12);
                    columnIndexOrThrow20 = i12;
                    int i13 = columnIndexOrThrow21;
                    log.off = (char) query.getInt(i13);
                    int i14 = columnIndexOrThrow22;
                    if (query.getInt(i14) != 0) {
                        columnIndexOrThrow21 = i13;
                        z = true;
                    } else {
                        columnIndexOrThrow21 = i13;
                        z = false;
                    }
                    log.isStart = z;
                    int i15 = columnIndexOrThrow23;
                    columnIndexOrThrow23 = i15;
                    log.isDelete = query.getInt(i15) != 0;
                    int i16 = columnIndexOrThrow24;
                    columnIndexOrThrow24 = i16;
                    log.isRead = query.getInt(i16) != 0;
                    columnIndexOrThrow22 = i14;
                    int i17 = columnIndexOrThrow25;
                    log.port = (byte) query.getShort(i17);
                    columnIndexOrThrow25 = i17;
                    int i18 = columnIndexOrThrow26;
                    log.netId = query.getInt(i18);
                    ArrayList arrayList3 = arrayList;
                    arrayList3.add(log);
                    columnIndexOrThrow26 = i18;
                    arrayList2 = arrayList3;
                    columnIndexOrThrow = i5;
                    i = i4;
                    columnIndexOrThrow13 = i3;
                    columnIndexOrThrow12 = i7;
                }
                ArrayList arrayList4 = arrayList2;
                query.close();
                roomSQLiteQuery.release();
                return arrayList4;
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

    public DataSource.Factory<Integer, Log> loadAllMacByModel(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from log where mac=? order by time desc, id desc", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return new DataSource.Factory<Integer, Log>() {
            public LimitOffsetDataSource<Log> create() {
                return new LimitOffsetDataSource<Log>(LogDao_Impl.this.__db, acquire, false, true, "log") {
                    /* access modifiers changed from: protected */
                    public List<Log> convertRows(Cursor cursor) {
                        ArrayList arrayList;
                        int i;
                        boolean z;
                        boolean z2;
                        boolean z3;
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "mac");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "time");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "logType");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, "notifyId");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "start");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "end");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "day");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "model");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "fan");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "tmpHum");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "hTmpF");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "lTmpF");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "hTmpC");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "lTmpC");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "hVpd");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "lVpd");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "hHum");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursor2, "lHum");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursor2, DebugKt.DEBUG_PROPERTY_VALUE_ON);
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursor2, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursor2, "isStart");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursor2, "isDelete");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursor2, "isRead");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursor2, "port");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursor2, "netId");
                        int i2 = columnIndexOrThrow14;
                        ArrayList arrayList2 = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            Log log = new Log();
                            if (cursor2.isNull(columnIndexOrThrow)) {
                                arrayList = arrayList2;
                                log.mac = null;
                            } else {
                                arrayList = arrayList2;
                                log.mac = cursor2.getString(columnIndexOrThrow);
                            }
                            log.f142id = cursor2.getInt(columnIndexOrThrow2);
                            int i3 = columnIndexOrThrow;
                            int i4 = columnIndexOrThrow2;
                            log.time = cursor2.getLong(columnIndexOrThrow3);
                            log.logType = (byte) cursor2.getShort(columnIndexOrThrow4);
                            log.notifyId = (byte) cursor2.getShort(columnIndexOrThrow5);
                            log.start = (char) cursor2.getInt(columnIndexOrThrow6);
                            log.end = (char) cursor2.getInt(columnIndexOrThrow7);
                            log.day = (byte) cursor2.getShort(columnIndexOrThrow8);
                            log.model = (byte) cursor2.getShort(columnIndexOrThrow9);
                            log.fan = (byte) cursor2.getShort(columnIndexOrThrow10);
                            log.tmpHum = cursor2.getShort(columnIndexOrThrow11);
                            log.hTmpF = (byte) cursor2.getShort(columnIndexOrThrow12);
                            log.lTmpF = (byte) cursor2.getShort(columnIndexOrThrow13);
                            log.hTmpC = (byte) cursor2.getShort(i2);
                            log.lTmpC = (byte) cursor2.getShort(columnIndexOrThrow15);
                            log.hVpd = (byte) cursor2.getShort(columnIndexOrThrow16);
                            log.lVpd = (byte) cursor2.getShort(columnIndexOrThrow17);
                            log.hHum = (byte) cursor2.getShort(columnIndexOrThrow18);
                            log.lHum = (byte) cursor2.getShort(columnIndexOrThrow19);
                            log.f143on = (char) cursor2.getInt(columnIndexOrThrow20);
                            int i5 = columnIndexOrThrow21;
                            log.off = (char) cursor2.getInt(i5);
                            int i6 = columnIndexOrThrow22;
                            if (cursor2.getInt(i6) != 0) {
                                i = i6;
                                z = true;
                            } else {
                                i = i6;
                                z = false;
                            }
                            log.isStart = z;
                            int i7 = columnIndexOrThrow23;
                            if (cursor2.getInt(i7) != 0) {
                                columnIndexOrThrow23 = i7;
                                z2 = true;
                            } else {
                                columnIndexOrThrow23 = i7;
                                z2 = false;
                            }
                            log.isDelete = z2;
                            int i8 = columnIndexOrThrow24;
                            if (cursor2.getInt(i8) != 0) {
                                columnIndexOrThrow24 = i8;
                                z3 = true;
                            } else {
                                columnIndexOrThrow24 = i8;
                                z3 = false;
                            }
                            log.isRead = z3;
                            int i9 = i5;
                            log.port = (byte) cursor2.getShort(columnIndexOrThrow25);
                            log.netId = cursor2.getInt(columnIndexOrThrow26);
                            ArrayList arrayList3 = arrayList;
                            arrayList3.add(log);
                            columnIndexOrThrow2 = i4;
                            int i10 = i3;
                            arrayList2 = arrayList3;
                            columnIndexOrThrow = i10;
                            int i11 = i9;
                            columnIndexOrThrow22 = i;
                            columnIndexOrThrow21 = i11;
                        }
                        return arrayList2;
                    }
                };
            }
        };
    }

    public Log loadFirstLog(String str, byte b) {
        RoomSQLiteQuery roomSQLiteQuery;
        Log log;
        int i;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from log where mac=? and port=? order by time desc, id desc", 2);
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
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "logType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "notifyId");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "end");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "day");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "model");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fan");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "tmpHum");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "hTmpF");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "lTmpF");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "hTmpC");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "lTmpC");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "hVpd");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lVpd");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "hHum");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "lHum");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_ON);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "isStart");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "isDelete");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "isRead");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "port");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "netId");
                if (query.moveToFirst()) {
                    int i2 = columnIndexOrThrow26;
                    Log log2 = new Log();
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow14;
                        log2.mac = null;
                    } else {
                        i = columnIndexOrThrow14;
                        log2.mac = query.getString(columnIndexOrThrow);
                    }
                    log2.f142id = query.getInt(columnIndexOrThrow2);
                    log2.time = query.getLong(columnIndexOrThrow3);
                    log2.logType = (byte) query.getShort(columnIndexOrThrow4);
                    log2.notifyId = (byte) query.getShort(columnIndexOrThrow5);
                    log2.start = (char) query.getInt(columnIndexOrThrow6);
                    log2.end = (char) query.getInt(columnIndexOrThrow7);
                    log2.day = (byte) query.getShort(columnIndexOrThrow8);
                    log2.model = (byte) query.getShort(columnIndexOrThrow9);
                    log2.fan = (byte) query.getShort(columnIndexOrThrow10);
                    log2.tmpHum = query.getShort(columnIndexOrThrow11);
                    log2.hTmpF = (byte) query.getShort(columnIndexOrThrow12);
                    log2.lTmpF = (byte) query.getShort(columnIndexOrThrow13);
                    log2.hTmpC = (byte) query.getShort(i);
                    log2.lTmpC = (byte) query.getShort(columnIndexOrThrow15);
                    log2.hVpd = (byte) query.getShort(columnIndexOrThrow16);
                    log2.lVpd = (byte) query.getShort(columnIndexOrThrow17);
                    log2.hHum = (byte) query.getShort(columnIndexOrThrow18);
                    log2.lHum = (byte) query.getShort(columnIndexOrThrow19);
                    log2.f143on = (char) query.getInt(columnIndexOrThrow20);
                    log2.off = (char) query.getInt(columnIndexOrThrow21);
                    log2.isStart = query.getInt(columnIndexOrThrow22) != 0;
                    log2.isDelete = query.getInt(columnIndexOrThrow23) != 0;
                    log2.isRead = query.getInt(columnIndexOrThrow24) != 0;
                    log2.port = (byte) query.getShort(columnIndexOrThrow25);
                    log2.netId = query.getInt(i2);
                    log = log2;
                } else {
                    log = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return log;
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

    public Log loadFirstLog(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        Log log;
        int i;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from log where mac=? order by time desc, id desc", 1);
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
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "logType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "notifyId");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "end");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "day");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "model");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fan");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "tmpHum");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "hTmpF");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "lTmpF");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "hTmpC");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "lTmpC");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "hVpd");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lVpd");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "hHum");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "lHum");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_ON);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "isStart");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "isDelete");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "isRead");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "port");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "netId");
                if (query.moveToFirst()) {
                    int i2 = columnIndexOrThrow26;
                    Log log2 = new Log();
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow14;
                        log2.mac = null;
                    } else {
                        i = columnIndexOrThrow14;
                        log2.mac = query.getString(columnIndexOrThrow);
                    }
                    log2.f142id = query.getInt(columnIndexOrThrow2);
                    log2.time = query.getLong(columnIndexOrThrow3);
                    log2.logType = (byte) query.getShort(columnIndexOrThrow4);
                    log2.notifyId = (byte) query.getShort(columnIndexOrThrow5);
                    log2.start = (char) query.getInt(columnIndexOrThrow6);
                    log2.end = (char) query.getInt(columnIndexOrThrow7);
                    log2.day = (byte) query.getShort(columnIndexOrThrow8);
                    log2.model = (byte) query.getShort(columnIndexOrThrow9);
                    log2.fan = (byte) query.getShort(columnIndexOrThrow10);
                    log2.tmpHum = query.getShort(columnIndexOrThrow11);
                    log2.hTmpF = (byte) query.getShort(columnIndexOrThrow12);
                    log2.lTmpF = (byte) query.getShort(columnIndexOrThrow13);
                    log2.hTmpC = (byte) query.getShort(i);
                    log2.lTmpC = (byte) query.getShort(columnIndexOrThrow15);
                    log2.hVpd = (byte) query.getShort(columnIndexOrThrow16);
                    log2.lVpd = (byte) query.getShort(columnIndexOrThrow17);
                    log2.hHum = (byte) query.getShort(columnIndexOrThrow18);
                    log2.lHum = (byte) query.getShort(columnIndexOrThrow19);
                    log2.f143on = (char) query.getInt(columnIndexOrThrow20);
                    log2.off = (char) query.getInt(columnIndexOrThrow21);
                    log2.isStart = query.getInt(columnIndexOrThrow22) != 0;
                    log2.isDelete = query.getInt(columnIndexOrThrow23) != 0;
                    log2.isRead = query.getInt(columnIndexOrThrow24) != 0;
                    log2.port = (byte) query.getShort(columnIndexOrThrow25);
                    log2.netId = query.getInt(i2);
                    log = log2;
                } else {
                    log = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return log;
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

    public Log loadFirstNetLog(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        Log log;
        int i;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from log where mac=? order by netId desc,time desc", 1);
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
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "time");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "logType");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "notifyId");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "start");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "end");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "day");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "model");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "fan");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "tmpHum");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "hTmpF");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "lTmpF");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "hTmpC");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "lTmpC");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "hVpd");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lVpd");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "hHum");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "lHum");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_ON);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "isStart");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(query, "isDelete");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(query, "isRead");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(query, "port");
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(query, "netId");
                if (query.moveToFirst()) {
                    int i2 = columnIndexOrThrow26;
                    Log log2 = new Log();
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow14;
                        log2.mac = null;
                    } else {
                        i = columnIndexOrThrow14;
                        log2.mac = query.getString(columnIndexOrThrow);
                    }
                    log2.f142id = query.getInt(columnIndexOrThrow2);
                    log2.time = query.getLong(columnIndexOrThrow3);
                    log2.logType = (byte) query.getShort(columnIndexOrThrow4);
                    log2.notifyId = (byte) query.getShort(columnIndexOrThrow5);
                    log2.start = (char) query.getInt(columnIndexOrThrow6);
                    log2.end = (char) query.getInt(columnIndexOrThrow7);
                    log2.day = (byte) query.getShort(columnIndexOrThrow8);
                    log2.model = (byte) query.getShort(columnIndexOrThrow9);
                    log2.fan = (byte) query.getShort(columnIndexOrThrow10);
                    log2.tmpHum = query.getShort(columnIndexOrThrow11);
                    log2.hTmpF = (byte) query.getShort(columnIndexOrThrow12);
                    log2.lTmpF = (byte) query.getShort(columnIndexOrThrow13);
                    log2.hTmpC = (byte) query.getShort(i);
                    log2.lTmpC = (byte) query.getShort(columnIndexOrThrow15);
                    log2.hVpd = (byte) query.getShort(columnIndexOrThrow16);
                    log2.lVpd = (byte) query.getShort(columnIndexOrThrow17);
                    log2.hHum = (byte) query.getShort(columnIndexOrThrow18);
                    log2.lHum = (byte) query.getShort(columnIndexOrThrow19);
                    log2.f143on = (char) query.getInt(columnIndexOrThrow20);
                    log2.off = (char) query.getInt(columnIndexOrThrow21);
                    log2.isStart = query.getInt(columnIndexOrThrow22) != 0;
                    log2.isDelete = query.getInt(columnIndexOrThrow23) != 0;
                    log2.isRead = query.getInt(columnIndexOrThrow24) != 0;
                    log2.port = (byte) query.getShort(columnIndexOrThrow25);
                    log2.netId = query.getInt(i2);
                    log = log2;
                } else {
                    log = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return log;
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

    public DataSource.Factory<Integer, Log> loadByTypeAndModel(String str, List<Byte> list, List<Byte> list2) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select * from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=0 and time>coalesce((select max(time) from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=1),0) and (logType in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") or (logType=0 and port in (");
        int size2 = list2.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append("))) order by time desc, id desc");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 2 + size2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        int i = 3;
        for (Byte next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, (long) next.byteValue());
            }
            i++;
        }
        int i2 = size + 3;
        for (Byte next2 : list2) {
            if (next2 == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindLong(i2, (long) next2.byteValue());
            }
            i2++;
        }
        return new DataSource.Factory<Integer, Log>() {
            public LimitOffsetDataSource<Log> create() {
                return new LimitOffsetDataSource<Log>(LogDao_Impl.this.__db, acquire, false, true, "log") {
                    /* access modifiers changed from: protected */
                    public List<Log> convertRows(Cursor cursor) {
                        ArrayList arrayList;
                        int i;
                        boolean z;
                        boolean z2;
                        boolean z3;
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "mac");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "time");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "logType");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, "notifyId");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "start");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "end");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "day");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "model");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "fan");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "tmpHum");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "hTmpF");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "lTmpF");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "hTmpC");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "lTmpC");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "hVpd");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "lVpd");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "hHum");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursor2, "lHum");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursor2, DebugKt.DEBUG_PROPERTY_VALUE_ON);
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursor2, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursor2, "isStart");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursor2, "isDelete");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursor2, "isRead");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursor2, "port");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursor2, "netId");
                        int i2 = columnIndexOrThrow14;
                        ArrayList arrayList2 = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            Log log = new Log();
                            if (cursor2.isNull(columnIndexOrThrow)) {
                                arrayList = arrayList2;
                                log.mac = null;
                            } else {
                                arrayList = arrayList2;
                                log.mac = cursor2.getString(columnIndexOrThrow);
                            }
                            log.f142id = cursor2.getInt(columnIndexOrThrow2);
                            int i3 = columnIndexOrThrow;
                            int i4 = columnIndexOrThrow2;
                            log.time = cursor2.getLong(columnIndexOrThrow3);
                            log.logType = (byte) cursor2.getShort(columnIndexOrThrow4);
                            log.notifyId = (byte) cursor2.getShort(columnIndexOrThrow5);
                            log.start = (char) cursor2.getInt(columnIndexOrThrow6);
                            log.end = (char) cursor2.getInt(columnIndexOrThrow7);
                            log.day = (byte) cursor2.getShort(columnIndexOrThrow8);
                            log.model = (byte) cursor2.getShort(columnIndexOrThrow9);
                            log.fan = (byte) cursor2.getShort(columnIndexOrThrow10);
                            log.tmpHum = cursor2.getShort(columnIndexOrThrow11);
                            log.hTmpF = (byte) cursor2.getShort(columnIndexOrThrow12);
                            log.lTmpF = (byte) cursor2.getShort(columnIndexOrThrow13);
                            log.hTmpC = (byte) cursor2.getShort(i2);
                            log.lTmpC = (byte) cursor2.getShort(columnIndexOrThrow15);
                            log.hVpd = (byte) cursor2.getShort(columnIndexOrThrow16);
                            log.lVpd = (byte) cursor2.getShort(columnIndexOrThrow17);
                            log.hHum = (byte) cursor2.getShort(columnIndexOrThrow18);
                            log.lHum = (byte) cursor2.getShort(columnIndexOrThrow19);
                            log.f143on = (char) cursor2.getInt(columnIndexOrThrow20);
                            int i5 = columnIndexOrThrow21;
                            log.off = (char) cursor2.getInt(i5);
                            int i6 = columnIndexOrThrow22;
                            if (cursor2.getInt(i6) != 0) {
                                i = i6;
                                z = true;
                            } else {
                                i = i6;
                                z = false;
                            }
                            log.isStart = z;
                            int i7 = columnIndexOrThrow23;
                            if (cursor2.getInt(i7) != 0) {
                                columnIndexOrThrow23 = i7;
                                z2 = true;
                            } else {
                                columnIndexOrThrow23 = i7;
                                z2 = false;
                            }
                            log.isDelete = z2;
                            int i8 = columnIndexOrThrow24;
                            if (cursor2.getInt(i8) != 0) {
                                columnIndexOrThrow24 = i8;
                                z3 = true;
                            } else {
                                columnIndexOrThrow24 = i8;
                                z3 = false;
                            }
                            log.isRead = z3;
                            int i9 = i5;
                            log.port = (byte) cursor2.getShort(columnIndexOrThrow25);
                            log.netId = cursor2.getInt(columnIndexOrThrow26);
                            ArrayList arrayList3 = arrayList;
                            arrayList3.add(log);
                            columnIndexOrThrow2 = i4;
                            int i10 = i3;
                            arrayList2 = arrayList3;
                            columnIndexOrThrow = i10;
                            int i11 = i9;
                            columnIndexOrThrow22 = i;
                            columnIndexOrThrow21 = i11;
                        }
                        return arrayList2;
                    }
                };
            }
        };
    }

    public DataSource.Factory<Integer, Log> loadCByType(String str, List<Byte> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select * from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=0 and time>coalesce((select max(time) from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=1),0) and model in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") order by time desc, id desc");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        int i = 3;
        for (Byte next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, (long) next.byteValue());
            }
            i++;
        }
        return new DataSource.Factory<Integer, Log>() {
            public LimitOffsetDataSource<Log> create() {
                return new LimitOffsetDataSource<Log>(LogDao_Impl.this.__db, acquire, false, true, "log") {
                    /* access modifiers changed from: protected */
                    public List<Log> convertRows(Cursor cursor) {
                        ArrayList arrayList;
                        int i;
                        boolean z;
                        boolean z2;
                        boolean z3;
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "mac");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "time");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "logType");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, "notifyId");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "start");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "end");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "day");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "model");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "fan");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "tmpHum");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "hTmpF");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "lTmpF");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "hTmpC");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "lTmpC");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "hVpd");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "lVpd");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "hHum");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursor2, "lHum");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursor2, DebugKt.DEBUG_PROPERTY_VALUE_ON);
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursor2, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursor2, "isStart");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursor2, "isDelete");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursor2, "isRead");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursor2, "port");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursor2, "netId");
                        int i2 = columnIndexOrThrow14;
                        ArrayList arrayList2 = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            Log log = new Log();
                            if (cursor2.isNull(columnIndexOrThrow)) {
                                arrayList = arrayList2;
                                log.mac = null;
                            } else {
                                arrayList = arrayList2;
                                log.mac = cursor2.getString(columnIndexOrThrow);
                            }
                            log.f142id = cursor2.getInt(columnIndexOrThrow2);
                            int i3 = columnIndexOrThrow;
                            int i4 = columnIndexOrThrow2;
                            log.time = cursor2.getLong(columnIndexOrThrow3);
                            log.logType = (byte) cursor2.getShort(columnIndexOrThrow4);
                            log.notifyId = (byte) cursor2.getShort(columnIndexOrThrow5);
                            log.start = (char) cursor2.getInt(columnIndexOrThrow6);
                            log.end = (char) cursor2.getInt(columnIndexOrThrow7);
                            log.day = (byte) cursor2.getShort(columnIndexOrThrow8);
                            log.model = (byte) cursor2.getShort(columnIndexOrThrow9);
                            log.fan = (byte) cursor2.getShort(columnIndexOrThrow10);
                            log.tmpHum = cursor2.getShort(columnIndexOrThrow11);
                            log.hTmpF = (byte) cursor2.getShort(columnIndexOrThrow12);
                            log.lTmpF = (byte) cursor2.getShort(columnIndexOrThrow13);
                            log.hTmpC = (byte) cursor2.getShort(i2);
                            log.lTmpC = (byte) cursor2.getShort(columnIndexOrThrow15);
                            log.hVpd = (byte) cursor2.getShort(columnIndexOrThrow16);
                            log.lVpd = (byte) cursor2.getShort(columnIndexOrThrow17);
                            log.hHum = (byte) cursor2.getShort(columnIndexOrThrow18);
                            log.lHum = (byte) cursor2.getShort(columnIndexOrThrow19);
                            log.f143on = (char) cursor2.getInt(columnIndexOrThrow20);
                            int i5 = columnIndexOrThrow21;
                            log.off = (char) cursor2.getInt(i5);
                            int i6 = columnIndexOrThrow22;
                            if (cursor2.getInt(i6) != 0) {
                                i = i6;
                                z = true;
                            } else {
                                i = i6;
                                z = false;
                            }
                            log.isStart = z;
                            int i7 = columnIndexOrThrow23;
                            if (cursor2.getInt(i7) != 0) {
                                columnIndexOrThrow23 = i7;
                                z2 = true;
                            } else {
                                columnIndexOrThrow23 = i7;
                                z2 = false;
                            }
                            log.isDelete = z2;
                            int i8 = columnIndexOrThrow24;
                            if (cursor2.getInt(i8) != 0) {
                                columnIndexOrThrow24 = i8;
                                z3 = true;
                            } else {
                                columnIndexOrThrow24 = i8;
                                z3 = false;
                            }
                            log.isRead = z3;
                            int i9 = i5;
                            log.port = (byte) cursor2.getShort(columnIndexOrThrow25);
                            log.netId = cursor2.getInt(columnIndexOrThrow26);
                            ArrayList arrayList3 = arrayList;
                            arrayList3.add(log);
                            columnIndexOrThrow2 = i4;
                            int i10 = i3;
                            arrayList2 = arrayList3;
                            columnIndexOrThrow = i10;
                            int i11 = i9;
                            columnIndexOrThrow22 = i;
                            columnIndexOrThrow21 = i11;
                        }
                        return arrayList2;
                    }
                };
            }
        };
    }

    public DataSource.Factory<Integer, Log> loadByType(String str, List<Byte> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select * from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=0 and time>coalesce((select max(time) from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=1),0) and logType in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") order by time desc, id desc");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size + 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        int i = 3;
        for (Byte next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, (long) next.byteValue());
            }
            i++;
        }
        return new DataSource.Factory<Integer, Log>() {
            public LimitOffsetDataSource<Log> create() {
                return new LimitOffsetDataSource<Log>(LogDao_Impl.this.__db, acquire, false, true, "log") {
                    /* access modifiers changed from: protected */
                    public List<Log> convertRows(Cursor cursor) {
                        ArrayList arrayList;
                        int i;
                        boolean z;
                        boolean z2;
                        boolean z3;
                        Cursor cursor2 = cursor;
                        int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursor2, "mac");
                        int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursor2, "id");
                        int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursor2, "time");
                        int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursor2, "logType");
                        int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursor2, "notifyId");
                        int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursor2, "start");
                        int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursor2, "end");
                        int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursor2, "day");
                        int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursor2, "model");
                        int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursor2, "fan");
                        int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursor2, "tmpHum");
                        int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursor2, "hTmpF");
                        int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursor2, "lTmpF");
                        int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursor2, "hTmpC");
                        int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursor2, "lTmpC");
                        int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursor2, "hVpd");
                        int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursor2, "lVpd");
                        int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursor2, "hHum");
                        int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursor2, "lHum");
                        int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursor2, DebugKt.DEBUG_PROPERTY_VALUE_ON);
                        int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursor2, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                        int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursor2, "isStart");
                        int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursor2, "isDelete");
                        int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursor2, "isRead");
                        int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursor2, "port");
                        int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursor2, "netId");
                        int i2 = columnIndexOrThrow14;
                        ArrayList arrayList2 = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            Log log = new Log();
                            if (cursor2.isNull(columnIndexOrThrow)) {
                                arrayList = arrayList2;
                                log.mac = null;
                            } else {
                                arrayList = arrayList2;
                                log.mac = cursor2.getString(columnIndexOrThrow);
                            }
                            log.f142id = cursor2.getInt(columnIndexOrThrow2);
                            int i3 = columnIndexOrThrow;
                            int i4 = columnIndexOrThrow2;
                            log.time = cursor2.getLong(columnIndexOrThrow3);
                            log.logType = (byte) cursor2.getShort(columnIndexOrThrow4);
                            log.notifyId = (byte) cursor2.getShort(columnIndexOrThrow5);
                            log.start = (char) cursor2.getInt(columnIndexOrThrow6);
                            log.end = (char) cursor2.getInt(columnIndexOrThrow7);
                            log.day = (byte) cursor2.getShort(columnIndexOrThrow8);
                            log.model = (byte) cursor2.getShort(columnIndexOrThrow9);
                            log.fan = (byte) cursor2.getShort(columnIndexOrThrow10);
                            log.tmpHum = cursor2.getShort(columnIndexOrThrow11);
                            log.hTmpF = (byte) cursor2.getShort(columnIndexOrThrow12);
                            log.lTmpF = (byte) cursor2.getShort(columnIndexOrThrow13);
                            log.hTmpC = (byte) cursor2.getShort(i2);
                            log.lTmpC = (byte) cursor2.getShort(columnIndexOrThrow15);
                            log.hVpd = (byte) cursor2.getShort(columnIndexOrThrow16);
                            log.lVpd = (byte) cursor2.getShort(columnIndexOrThrow17);
                            log.hHum = (byte) cursor2.getShort(columnIndexOrThrow18);
                            log.lHum = (byte) cursor2.getShort(columnIndexOrThrow19);
                            log.f143on = (char) cursor2.getInt(columnIndexOrThrow20);
                            int i5 = columnIndexOrThrow21;
                            log.off = (char) cursor2.getInt(i5);
                            int i6 = columnIndexOrThrow22;
                            if (cursor2.getInt(i6) != 0) {
                                i = i6;
                                z = true;
                            } else {
                                i = i6;
                                z = false;
                            }
                            log.isStart = z;
                            int i7 = columnIndexOrThrow23;
                            if (cursor2.getInt(i7) != 0) {
                                columnIndexOrThrow23 = i7;
                                z2 = true;
                            } else {
                                columnIndexOrThrow23 = i7;
                                z2 = false;
                            }
                            log.isDelete = z2;
                            int i8 = columnIndexOrThrow24;
                            if (cursor2.getInt(i8) != 0) {
                                columnIndexOrThrow24 = i8;
                                z3 = true;
                            } else {
                                columnIndexOrThrow24 = i8;
                                z3 = false;
                            }
                            log.isRead = z3;
                            int i9 = i5;
                            log.port = (byte) cursor2.getShort(columnIndexOrThrow25);
                            log.netId = cursor2.getInt(columnIndexOrThrow26);
                            ArrayList arrayList3 = arrayList;
                            arrayList3.add(log);
                            columnIndexOrThrow2 = i4;
                            int i10 = i3;
                            arrayList2 = arrayList3;
                            columnIndexOrThrow = i10;
                            int i11 = i9;
                            columnIndexOrThrow22 = i;
                            columnIndexOrThrow21 = i11;
                        }
                        return arrayList2;
                    }
                };
            }
        };
    }

    public int numByMacAndTime(String str, long j, List<Byte> list, List<Byte> list2) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select count(1) from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=0 and time>coalesce((select max(time) from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=1),0) and (logType in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") or (logType=0 and model in (");
        int size2 = list2.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append("))) and time >");
        newStringBuilder.append("?");
        int i = 3;
        int i2 = size + 3;
        int i3 = size2 + i2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), i3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        for (Byte next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, (long) next.byteValue());
            }
            i++;
        }
        for (Byte next2 : list2) {
            if (next2 == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindLong(i2, (long) next2.byteValue());
            }
            i2++;
        }
        acquire.bindLong(i3, j);
        this.__db.assertNotSuspendingTransaction();
        int i4 = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                i4 = query.getInt(0);
            }
            return i4;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public int numByMacAndTimeC(String str, long j, List<Byte> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select count(1) from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append("  and isDelete=0 and time>coalesce((select max(time) from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=1),0) and model in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") and time >");
        newStringBuilder.append("?");
        int i = 3;
        int i2 = size + 3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), i2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
        }
        for (Byte next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, (long) next.byteValue());
            }
            i++;
        }
        acquire.bindLong(i2, j);
        this.__db.assertNotSuspendingTransaction();
        int i3 = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                i3 = query.getInt(0);
            }
            return i3;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public void setRead(String str, byte b, Integer[] numArr) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("update log set isRead=1 where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and port=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and id in (");
        StringUtil.appendPlaceholders(newStringBuilder, numArr.length);
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        if (str == null) {
            compileStatement.bindNull(1);
        } else {
            compileStatement.bindString(1, str);
        }
        compileStatement.bindLong(2, (long) b);
        int i = 3;
        for (Integer num : numArr) {
            if (num == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, (long) num.intValue());
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void removeOther(String str, Integer[] numArr) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("delete from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and id not in (");
        StringUtil.appendPlaceholders(newStringBuilder, numArr.length);
        newStringBuilder.append(")");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        if (str == null) {
            compileStatement.bindNull(1);
        } else {
            compileStatement.bindString(1, str);
        }
        int i = 2;
        for (Integer num : numArr) {
            if (num == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, (long) num.intValue());
            }
            i++;
        }
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void removeOldData(String str, Integer[] numArr, long j) {
        this.__db.assertNotSuspendingTransaction();
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("delete from log where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and id not in (");
        int length = numArr.length;
        StringUtil.appendPlaceholders(newStringBuilder, length);
        newStringBuilder.append(") and time<");
        newStringBuilder.append("?");
        SupportSQLiteStatement compileStatement = this.__db.compileStatement(newStringBuilder.toString());
        if (str == null) {
            compileStatement.bindNull(1);
        } else {
            compileStatement.bindString(1, str);
        }
        int i = 2;
        for (Integer num : numArr) {
            if (num == null) {
                compileStatement.bindNull(i);
            } else {
                compileStatement.bindLong(i, (long) num.intValue());
            }
            i++;
        }
        compileStatement.bindLong(length + 2, j);
        this.__db.beginTransaction();
        try {
            compileStatement.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
