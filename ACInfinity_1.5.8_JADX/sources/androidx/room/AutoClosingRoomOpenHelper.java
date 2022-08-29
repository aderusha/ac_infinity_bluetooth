package androidx.room;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.arch.core.util.Function;
import androidx.room.util.SneakyThrow;
import androidx.sqlite.p005db.SupportSQLiteCompat;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import androidx.sqlite.p005db.SupportSQLiteOpenHelper;
import androidx.sqlite.p005db.SupportSQLiteQuery;
import androidx.sqlite.p005db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

final class AutoClosingRoomOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    private final AutoCloser mAutoCloser;
    private final AutoClosingSupportSQLiteDatabase mAutoClosingDb;
    private final SupportSQLiteOpenHelper mDelegateOpenHelper;

    AutoClosingRoomOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper, AutoCloser autoCloser) {
        this.mDelegateOpenHelper = supportSQLiteOpenHelper;
        this.mAutoCloser = autoCloser;
        autoCloser.init(supportSQLiteOpenHelper);
        this.mAutoClosingDb = new AutoClosingSupportSQLiteDatabase(autoCloser);
    }

    public String getDatabaseName() {
        return this.mDelegateOpenHelper.getDatabaseName();
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        this.mDelegateOpenHelper.setWriteAheadLoggingEnabled(z);
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        this.mAutoClosingDb.pokeOpen();
        return this.mAutoClosingDb;
    }

    public SupportSQLiteDatabase getReadableDatabase() {
        this.mAutoClosingDb.pokeOpen();
        return this.mAutoClosingDb;
    }

    public void close() {
        try {
            this.mAutoClosingDb.close();
        } catch (IOException e) {
            SneakyThrow.reThrow(e);
        }
    }

    /* access modifiers changed from: package-private */
    public AutoCloser getAutoCloser() {
        return this.mAutoCloser;
    }

    /* access modifiers changed from: package-private */
    public SupportSQLiteDatabase getAutoClosingDb() {
        return this.mAutoClosingDb;
    }

    public SupportSQLiteOpenHelper getDelegate() {
        return this.mDelegateOpenHelper;
    }

    static final class AutoClosingSupportSQLiteDatabase implements SupportSQLiteDatabase {
        private final AutoCloser mAutoCloser;

        static /* synthetic */ Object lambda$pokeOpen$0(SupportSQLiteDatabase supportSQLiteDatabase) {
            return null;
        }

        public /* synthetic */ void execPerConnectionSQL(String str, Object[] objArr) {
            SupportSQLiteDatabase.CC.$default$execPerConnectionSQL(this, str, objArr);
        }

        public /* synthetic */ boolean isExecPerConnectionSQLSupported() {
            return SupportSQLiteDatabase.CC.$default$isExecPerConnectionSQLSupported(this);
        }

        AutoClosingSupportSQLiteDatabase(AutoCloser autoCloser) {
            this.mAutoCloser = autoCloser;
        }

        /* access modifiers changed from: package-private */
        public void pokeOpen() {
            this.mAutoCloser.executeRefCountingFunction(C0662x9c57900e.INSTANCE);
        }

        public SupportSQLiteStatement compileStatement(String str) {
            return new AutoClosingSupportSqliteStatement(str, this.mAutoCloser);
        }

        public void beginTransaction() {
            try {
                this.mAutoCloser.incrementCountAndEnsureDbIsOpen().beginTransaction();
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void beginTransactionNonExclusive() {
            try {
                this.mAutoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionNonExclusive();
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
            try {
                this.mAutoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionWithListener(sQLiteTransactionListener);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
            try {
                this.mAutoCloser.incrementCountAndEnsureDbIsOpen().beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public void endTransaction() {
            if (this.mAutoCloser.getDelegateDatabase() != null) {
                try {
                    this.mAutoCloser.getDelegateDatabase().endTransaction();
                } finally {
                    this.mAutoCloser.decrementCountAndScheduleClose();
                }
            } else {
                throw new IllegalStateException("End transaction called but delegateDb is null");
            }
        }

        public void setTransactionSuccessful() {
            SupportSQLiteDatabase delegateDatabase = this.mAutoCloser.getDelegateDatabase();
            if (delegateDatabase != null) {
                delegateDatabase.setTransactionSuccessful();
                return;
            }
            throw new IllegalStateException("setTransactionSuccessful called but delegateDb is null");
        }

        public boolean inTransaction() {
            if (this.mAutoCloser.getDelegateDatabase() == null) {
                return false;
            }
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0666x9c579012.INSTANCE)).booleanValue();
        }

        public boolean isDbLockedByCurrentThread() {
            if (this.mAutoCloser.getDelegateDatabase() == null) {
                return false;
            }
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0646xee9a7185.INSTANCE)).booleanValue();
        }

        public boolean yieldIfContendedSafely() {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0649xee9a7188.INSTANCE)).booleanValue();
        }

        public boolean yieldIfContendedSafely(long j) {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0649xee9a7188.INSTANCE)).booleanValue();
        }

        public int getVersion() {
            return ((Integer) this.mAutoCloser.executeRefCountingFunction(C0665x9c579011.INSTANCE)).intValue();
        }

        public void setVersion(int i) {
            this.mAutoCloser.executeRefCountingFunction(new C0652xee9a718b(i));
        }

        public long getMaximumSize() {
            return ((Long) this.mAutoCloser.executeRefCountingFunction(C0650xee9a7189.INSTANCE)).longValue();
        }

        public long setMaximumSize(long j) {
            return ((Long) this.mAutoCloser.executeRefCountingFunction(new C0653xee9a718c(j))).longValue();
        }

        public long getPageSize() {
            return ((Long) this.mAutoCloser.executeRefCountingFunction(C0651xee9a718a.INSTANCE)).longValue();
        }

        public void setPageSize(long j) {
            this.mAutoCloser.executeRefCountingFunction(new C0654xee9a718d(j));
        }

        public Cursor query(String str) {
            try {
                return new KeepAliveCursor(this.mAutoCloser.incrementCountAndEnsureDbIsOpen().query(str), this.mAutoCloser);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public Cursor query(String str, Object[] objArr) {
            try {
                return new KeepAliveCursor(this.mAutoCloser.incrementCountAndEnsureDbIsOpen().query(str, objArr), this.mAutoCloser);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
            try {
                return new KeepAliveCursor(this.mAutoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery), this.mAutoCloser);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
            try {
                return new KeepAliveCursor(this.mAutoCloser.incrementCountAndEnsureDbIsOpen().query(supportSQLiteQuery, cancellationSignal), this.mAutoCloser);
            } catch (Throwable th) {
                this.mAutoCloser.decrementCountAndScheduleClose();
                throw th;
            }
        }

        public long insert(String str, int i, ContentValues contentValues) throws SQLException {
            return ((Long) this.mAutoCloser.executeRefCountingFunction(new C0657xee9a71a4(str, i, contentValues))).longValue();
        }

        public int delete(String str, String str2, Object[] objArr) {
            return ((Integer) this.mAutoCloser.executeRefCountingFunction(new C0659xee9a71a6(str, str2, objArr))).intValue();
        }

        public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
            return ((Integer) this.mAutoCloser.executeRefCountingFunction(new C0658xee9a71a5(str, i, contentValues, str2, objArr))).intValue();
        }

        public void execSQL(String str) throws SQLException {
            this.mAutoCloser.executeRefCountingFunction(new C0655xee9a718e(str));
        }

        public void execSQL(String str, Object[] objArr) throws SQLException {
            this.mAutoCloser.executeRefCountingFunction(new C0660xee9a71a7(str, objArr));
        }

        public boolean isReadOnly() {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0648xee9a7187.INSTANCE)).booleanValue();
        }

        public boolean isOpen() {
            SupportSQLiteDatabase delegateDatabase = this.mAutoCloser.getDelegateDatabase();
            if (delegateDatabase == null) {
                return false;
            }
            return delegateDatabase.isOpen();
        }

        public boolean needUpgrade(int i) {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(new C0644x9c57900a(i))).booleanValue();
        }

        public String getPath() {
            return (String) this.mAutoCloser.executeRefCountingFunction(C0664x9c579010.INSTANCE);
        }

        public void setLocale(Locale locale) {
            this.mAutoCloser.executeRefCountingFunction(new C0645x9c57900b(locale));
        }

        public void setMaxSqlCacheSize(int i) {
            this.mAutoCloser.executeRefCountingFunction(new C0647xee9a7186(i));
        }

        public void setForeignKeyConstraintsEnabled(boolean z) {
            this.mAutoCloser.executeRefCountingFunction(new C0656x9c57900c(z));
        }

        static /* synthetic */ Object lambda$setForeignKeyConstraintsEnabled$12(boolean z, SupportSQLiteDatabase supportSQLiteDatabase) {
            if (Build.VERSION.SDK_INT < 16) {
                return null;
            }
            supportSQLiteDatabase.setForeignKeyConstraintsEnabled(z);
            return null;
        }

        public boolean enableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        public void disableWriteAheadLogging() {
            throw new UnsupportedOperationException("Enable/disable write ahead logging on the OpenHelper instead of on the database directly.");
        }

        public boolean isWriteAheadLoggingEnabled() {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0661x9c57900d.INSTANCE)).booleanValue();
        }

        static /* synthetic */ Boolean lambda$isWriteAheadLoggingEnabled$13(SupportSQLiteDatabase supportSQLiteDatabase) {
            if (Build.VERSION.SDK_INT >= 16) {
                return Boolean.valueOf(supportSQLiteDatabase.isWriteAheadLoggingEnabled());
            }
            return false;
        }

        public List<Pair<String, String>> getAttachedDbs() {
            return (List) this.mAutoCloser.executeRefCountingFunction(C0663x9c57900f.INSTANCE);
        }

        public boolean isDatabaseIntegrityOk() {
            return ((Boolean) this.mAutoCloser.executeRefCountingFunction(C0667x9c579013.INSTANCE)).booleanValue();
        }

        public void close() throws IOException {
            this.mAutoCloser.closeDatabaseIfOpen();
        }
    }

    private static final class KeepAliveCursor implements Cursor {
        private final AutoCloser mAutoCloser;
        private final Cursor mDelegate;

        KeepAliveCursor(Cursor cursor, AutoCloser autoCloser) {
            this.mDelegate = cursor;
            this.mAutoCloser = autoCloser;
        }

        public void close() {
            this.mDelegate.close();
            this.mAutoCloser.decrementCountAndScheduleClose();
        }

        public boolean isClosed() {
            return this.mDelegate.isClosed();
        }

        public int getCount() {
            return this.mDelegate.getCount();
        }

        public int getPosition() {
            return this.mDelegate.getPosition();
        }

        public boolean move(int i) {
            return this.mDelegate.move(i);
        }

        public boolean moveToPosition(int i) {
            return this.mDelegate.moveToPosition(i);
        }

        public boolean moveToFirst() {
            return this.mDelegate.moveToFirst();
        }

        public boolean moveToLast() {
            return this.mDelegate.moveToLast();
        }

        public boolean moveToNext() {
            return this.mDelegate.moveToNext();
        }

        public boolean moveToPrevious() {
            return this.mDelegate.moveToPrevious();
        }

        public boolean isFirst() {
            return this.mDelegate.isFirst();
        }

        public boolean isLast() {
            return this.mDelegate.isLast();
        }

        public boolean isBeforeFirst() {
            return this.mDelegate.isBeforeFirst();
        }

        public boolean isAfterLast() {
            return this.mDelegate.isAfterLast();
        }

        public int getColumnIndex(String str) {
            return this.mDelegate.getColumnIndex(str);
        }

        public int getColumnIndexOrThrow(String str) throws IllegalArgumentException {
            return this.mDelegate.getColumnIndexOrThrow(str);
        }

        public String getColumnName(int i) {
            return this.mDelegate.getColumnName(i);
        }

        public String[] getColumnNames() {
            return this.mDelegate.getColumnNames();
        }

        public int getColumnCount() {
            return this.mDelegate.getColumnCount();
        }

        public byte[] getBlob(int i) {
            return this.mDelegate.getBlob(i);
        }

        public String getString(int i) {
            return this.mDelegate.getString(i);
        }

        public void copyStringToBuffer(int i, CharArrayBuffer charArrayBuffer) {
            this.mDelegate.copyStringToBuffer(i, charArrayBuffer);
        }

        public short getShort(int i) {
            return this.mDelegate.getShort(i);
        }

        public int getInt(int i) {
            return this.mDelegate.getInt(i);
        }

        public long getLong(int i) {
            return this.mDelegate.getLong(i);
        }

        public float getFloat(int i) {
            return this.mDelegate.getFloat(i);
        }

        public double getDouble(int i) {
            return this.mDelegate.getDouble(i);
        }

        public int getType(int i) {
            return this.mDelegate.getType(i);
        }

        public boolean isNull(int i) {
            return this.mDelegate.isNull(i);
        }

        @Deprecated
        public void deactivate() {
            this.mDelegate.deactivate();
        }

        @Deprecated
        public boolean requery() {
            return this.mDelegate.requery();
        }

        public void registerContentObserver(ContentObserver contentObserver) {
            this.mDelegate.registerContentObserver(contentObserver);
        }

        public void unregisterContentObserver(ContentObserver contentObserver) {
            this.mDelegate.unregisterContentObserver(contentObserver);
        }

        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            this.mDelegate.registerDataSetObserver(dataSetObserver);
        }

        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            this.mDelegate.unregisterDataSetObserver(dataSetObserver);
        }

        public void setNotificationUri(ContentResolver contentResolver, Uri uri) {
            this.mDelegate.setNotificationUri(contentResolver, uri);
        }

        public void setNotificationUris(ContentResolver contentResolver, List<Uri> list) {
            SupportSQLiteCompat.Api29Impl.setNotificationUris(this.mDelegate, contentResolver, list);
        }

        public Uri getNotificationUri() {
            return SupportSQLiteCompat.Api19Impl.getNotificationUri(this.mDelegate);
        }

        public List<Uri> getNotificationUris() {
            return SupportSQLiteCompat.Api29Impl.getNotificationUris(this.mDelegate);
        }

        public boolean getWantsAllOnMoveCalls() {
            return this.mDelegate.getWantsAllOnMoveCalls();
        }

        public void setExtras(Bundle bundle) {
            SupportSQLiteCompat.Api23Impl.setExtras(this.mDelegate, bundle);
        }

        public Bundle getExtras() {
            return this.mDelegate.getExtras();
        }

        public Bundle respond(Bundle bundle) {
            return this.mDelegate.respond(bundle);
        }
    }

    private static class AutoClosingSupportSqliteStatement implements SupportSQLiteStatement {
        private final AutoCloser mAutoCloser;
        private final ArrayList<Object> mBinds = new ArrayList<>();
        private final String mSql;

        public void close() throws IOException {
        }

        AutoClosingSupportSqliteStatement(String str, AutoCloser autoCloser) {
            this.mSql = str;
            this.mAutoCloser = autoCloser;
        }

        private <T> T executeSqliteStatementWithRefCount(Function<SupportSQLiteStatement, T> function) {
            return this.mAutoCloser.executeRefCountingFunction(new C0668xd7aa7ffe(this, function));
        }

        /* renamed from: lambda$executeSqliteStatementWithRefCount$0$AutoClosingRoomOpenHelper$AutoClosingSupportSqliteStatement */
        public /* synthetic */ Object mo9734xdabdfde4(Function function, SupportSQLiteDatabase supportSQLiteDatabase) {
            SupportSQLiteStatement compileStatement = supportSQLiteDatabase.compileStatement(this.mSql);
            doBinds(compileStatement);
            return function.apply(compileStatement);
        }

        private void doBinds(SupportSQLiteStatement supportSQLiteStatement) {
            int i = 0;
            while (i < this.mBinds.size()) {
                int i2 = i + 1;
                Object obj = this.mBinds.get(i);
                if (obj == null) {
                    supportSQLiteStatement.bindNull(i2);
                } else if (obj instanceof Long) {
                    supportSQLiteStatement.bindLong(i2, ((Long) obj).longValue());
                } else if (obj instanceof Double) {
                    supportSQLiteStatement.bindDouble(i2, ((Double) obj).doubleValue());
                } else if (obj instanceof String) {
                    supportSQLiteStatement.bindString(i2, (String) obj);
                } else if (obj instanceof byte[]) {
                    supportSQLiteStatement.bindBlob(i2, (byte[]) obj);
                }
                i = i2;
            }
        }

        private void saveBinds(int i, Object obj) {
            int i2 = i - 1;
            if (i2 >= this.mBinds.size()) {
                for (int size = this.mBinds.size(); size <= i2; size++) {
                    this.mBinds.add((Object) null);
                }
            }
            this.mBinds.set(i2, obj);
        }

        public void execute() {
            executeSqliteStatementWithRefCount(C0669xd7aa7fff.INSTANCE);
        }

        public int executeUpdateDelete() {
            return ((Integer) executeSqliteStatementWithRefCount(C0670xd7aa8000.INSTANCE)).intValue();
        }

        public long executeInsert() {
            return ((Long) executeSqliteStatementWithRefCount(C0672xd7aa8002.INSTANCE)).longValue();
        }

        public long simpleQueryForLong() {
            return ((Long) executeSqliteStatementWithRefCount(C0673xd7aa8003.INSTANCE)).longValue();
        }

        public String simpleQueryForString() {
            return (String) executeSqliteStatementWithRefCount(C0671xd7aa8001.INSTANCE);
        }

        public void bindNull(int i) {
            saveBinds(i, (Object) null);
        }

        public void bindLong(int i, long j) {
            saveBinds(i, Long.valueOf(j));
        }

        public void bindDouble(int i, double d) {
            saveBinds(i, Double.valueOf(d));
        }

        public void bindString(int i, String str) {
            saveBinds(i, str);
        }

        public void bindBlob(int i, byte[] bArr) {
            saveBinds(i, bArr);
        }

        public void clearBindings() {
            this.mBinds.clear();
        }
    }
}
