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
import com.eternal.base.concat.TmpHum;
import com.eternal.base.database.entity.History;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlinx.coroutines.DebugKt;

public final class HistoryDao_Impl implements HistoryDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<History> __insertionAdapterOfHistory;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfRemoveOldData;
    private final SharedSQLiteStatement __preparedStmtOfRemoveOther;
    private final SharedSQLiteStatement __preparedStmtOfSetDelete;

    public HistoryDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfHistory = new EntityInsertionAdapter<History>(roomDatabase) {
            public String createQuery() {
                return "INSERT OR ABORT INTO `History` (`time`,`mac`,`tmp`,`hum`,`off`,`isDelete`,`portState`,`portFan`,`fan`,`vpd`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            }

            public void bind(SupportSQLiteStatement supportSQLiteStatement, History history) {
                supportSQLiteStatement.bindLong(1, history.time);
                if (history.mac == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, history.mac);
                }
                supportSQLiteStatement.bindLong(3, (long) history.tmp);
                supportSQLiteStatement.bindLong(4, (long) history.hum);
                supportSQLiteStatement.bindLong(5, history.off ? 1 : 0);
                supportSQLiteStatement.bindLong(6, history.isDelete ? 1 : 0);
                supportSQLiteStatement.bindLong(7, (long) history.portState);
                supportSQLiteStatement.bindLong(8, history.portFan);
                supportSQLiteStatement.bindLong(9, (long) history.fan);
                supportSQLiteStatement.bindLong(10, (long) history.vpd);
            }
        };
        this.__preparedStmtOfSetDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "update history set isDelete=1 where mac=? and time=?";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from history where mac=?";
            }
        };
        this.__preparedStmtOfRemoveOther = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from history where mac=? and time!=?";
            }
        };
        this.__preparedStmtOfRemoveOldData = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "delete from history where mac=? and time!=? and time<?";
            }
        };
    }

    public void insert(History history) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfHistory.insert(history);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void setDelete(String str, int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfSetDelete.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) i);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfSetDelete.release(acquire);
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

    public void removeOther(String str, int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveOther.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) i);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveOther.release(acquire);
        }
    }

    public void removeOldData(String str, int i, long j) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveOldData.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) i);
        acquire.bindLong(3, j);
        this.__db.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveOldData.release(acquire);
        }
    }

    public DataSource.Factory<Integer, TmpHum> loadAllHistoryFromMac(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select time, tmp, hum, off, portState, vpd, fan, portFan from history where mac=? and isDelete=0 and time>coalesce((select max(time) from history where mac=? and isDelete=1),0) order by time desc", 2);
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
        return new DataSource.Factory<Integer, TmpHum>() {
            public LimitOffsetDataSource<TmpHum> create() {
                return new LimitOffsetDataSource<TmpHum>(HistoryDao_Impl.this.__db, acquire, false, true, "history") {
                    /* access modifiers changed from: protected */
                    public List<TmpHum> convertRows(Cursor cursor) {
                        ArrayList arrayList = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            TmpHum tmpHum = new TmpHum();
                            boolean z = false;
                            tmpHum.time = cursor.getLong(0);
                            tmpHum.tmp = cursor.getInt(1);
                            tmpHum.hum = (char) cursor.getInt(2);
                            if (cursor.getInt(3) != 0) {
                                z = true;
                            }
                            tmpHum.off = z;
                            tmpHum.portState = cursor.getInt(4);
                            tmpHum.vpd = cursor.getInt(5);
                            tmpHum.fan = (byte) cursor.getShort(6);
                            tmpHum.portFan = cursor.getLong(7);
                            arrayList.add(tmpHum);
                        }
                        return arrayList;
                    }
                };
            }
        };
    }

    public List<TmpHum> loadAllHistoryFromMac(String str, long j, long j2) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select time, tmp, hum, off, portState, vpd, fan, portFan from history where mac=? and isDelete=0 and time>? and time<? and time>coalesce((select max(time) from history where mac=? and isDelete=1),0)", 4);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, j);
        acquire.bindLong(3, j2);
        if (str == null) {
            acquire.bindNull(4);
        } else {
            acquire.bindString(4, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TmpHum tmpHum = new TmpHum();
                tmpHum.time = query.getLong(0);
                tmpHum.tmp = query.getInt(1);
                tmpHum.hum = (char) query.getInt(2);
                tmpHum.off = query.getInt(3) != 0;
                tmpHum.portState = query.getInt(4);
                tmpHum.vpd = query.getInt(5);
                tmpHum.fan = (byte) query.getShort(6);
                tmpHum.portFan = query.getLong(7);
                arrayList.add(tmpHum);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public int loadLastTime(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select time from history where mac=? order by time desc limit 1", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<TmpHum> loadAllHistoryFromMacMin(String str, List<String> list, long j, long j2) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select time, tmp, hum, off, portState, vpd, fan, portFan from history where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=0 and strftime('%M', datetime(time, 'unixepoch','localtime')) in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") and time>");
        newStringBuilder.append("?");
        newStringBuilder.append(" and time<");
        newStringBuilder.append("?");
        newStringBuilder.append(" and time>coalesce((select max(time) from history where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=1),0)");
        int i = size + 4;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), i);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        int i2 = 2;
        for (String next : list) {
            if (next == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindString(i2, next);
            }
            i2++;
        }
        acquire.bindLong(size + 2, j);
        acquire.bindLong(size + 3, j2);
        if (str == null) {
            acquire.bindNull(i);
        } else {
            acquire.bindString(i, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TmpHum tmpHum = new TmpHum();
                tmpHum.time = query.getLong(0);
                tmpHum.tmp = query.getInt(1);
                tmpHum.hum = (char) query.getInt(2);
                tmpHum.off = query.getInt(3) != 0;
                tmpHum.portState = query.getInt(4);
                tmpHum.vpd = query.getInt(5);
                tmpHum.fan = (byte) query.getShort(6);
                tmpHum.portFan = query.getLong(7);
                arrayList.add(tmpHum);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<TmpHum> loadAllHistoryFromMacHour(String str, List<String> list, String str2, long j, long j2) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select time, tmp, hum, off, portState, vpd, fan, portFan from history where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=0 and strftime('%H', datetime(time, 'unixepoch','localtime')) in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(")and strftime('%M', datetime(time, 'unixepoch','localtime'))=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and time>");
        newStringBuilder.append("?");
        newStringBuilder.append(" and time<");
        newStringBuilder.append("?");
        newStringBuilder.append(" and time>coalesce((select max(time) from history where mac=");
        newStringBuilder.append("?");
        newStringBuilder.append(" and isDelete=1),0)");
        int i = size + 5;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), i);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        int i2 = 2;
        for (String next : list) {
            if (next == null) {
                acquire.bindNull(i2);
            } else {
                acquire.bindString(i2, next);
            }
            i2++;
        }
        int i3 = size + 2;
        if (str2 == null) {
            acquire.bindNull(i3);
        } else {
            acquire.bindString(i3, str2);
        }
        acquire.bindLong(size + 3, j);
        acquire.bindLong(size + 4, j2);
        if (str == null) {
            acquire.bindNull(i);
        } else {
            acquire.bindString(i, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                TmpHum tmpHum = new TmpHum();
                tmpHum.time = query.getLong(0);
                tmpHum.tmp = query.getInt(1);
                tmpHum.hum = (char) query.getInt(2);
                tmpHum.off = query.getInt(3) != 0;
                tmpHum.portState = query.getInt(4);
                tmpHum.vpd = query.getInt(5);
                tmpHum.fan = (byte) query.getShort(6);
                tmpHum.portFan = query.getLong(7);
                arrayList.add(tmpHum);
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<History> loadAllTest(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        String str2 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from history where mac=? order by time desc", 1);
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "time");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "mac");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "tmp");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "hum");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, DebugKt.DEBUG_PROPERTY_VALUE_OFF);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "isDelete");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "portState");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "portFan");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "fan");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "vpd");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                History history = new History();
                roomSQLiteQuery = acquire;
                try {
                    history.time = query.getLong(columnIndexOrThrow);
                    if (query.isNull(columnIndexOrThrow2)) {
                        history.mac = null;
                    } else {
                        history.mac = query.getString(columnIndexOrThrow2);
                    }
                    history.tmp = query.getInt(columnIndexOrThrow3);
                    history.hum = (char) query.getInt(columnIndexOrThrow4);
                    history.off = query.getInt(columnIndexOrThrow5) != 0;
                    history.isDelete = query.getInt(columnIndexOrThrow6) != 0;
                    history.portState = query.getInt(columnIndexOrThrow7);
                    history.portFan = query.getLong(columnIndexOrThrow8);
                    history.fan = (byte) query.getShort(columnIndexOrThrow9);
                    history.vpd = query.getInt(columnIndexOrThrow10);
                    arrayList.add(history);
                    acquire = roomSQLiteQuery;
                } catch (Throwable th) {
                    th = th;
                    query.close();
                    roomSQLiteQuery.release();
                    throw th;
                }
            }
            query.close();
            acquire.release();
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public int loadFirstTime(String str) {
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select time from history where mac=? and isDelete=0 and time>coalesce((select max(time) from history where mac=? and isDelete=1),0) order by time asc limit 1", 2);
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
        this.__db.assertNotSuspendingTransaction();
        int i = 0;
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
