package androidx.room;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteTransactionListener;
import android.os.CancellationSignal;
import android.util.Pair;
import androidx.room.RoomDatabase;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import androidx.sqlite.p005db.SupportSQLiteQuery;
import androidx.sqlite.p005db.SupportSQLiteStatement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

final class QueryInterceptorDatabase implements SupportSQLiteDatabase {
    private final SupportSQLiteDatabase mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;

    public /* synthetic */ void execPerConnectionSQL(String str, Object[] objArr) {
        SupportSQLiteDatabase.CC.$default$execPerConnectionSQL(this, str, objArr);
    }

    public /* synthetic */ boolean isExecPerConnectionSQLSupported() {
        return SupportSQLiteDatabase.CC.$default$isExecPerConnectionSQLSupported(this);
    }

    QueryInterceptorDatabase(SupportSQLiteDatabase supportSQLiteDatabase, RoomDatabase.QueryCallback queryCallback, Executor executor) {
        this.mDelegate = supportSQLiteDatabase;
        this.mQueryCallback = queryCallback;
        this.mQueryCallbackExecutor = executor;
    }

    public SupportSQLiteStatement compileStatement(String str) {
        return new QueryInterceptorStatement(this.mDelegate.compileStatement(str), this.mQueryCallback, str, this.mQueryCallbackExecutor);
    }

    public void beginTransaction() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda0(this));
        this.mDelegate.beginTransaction();
    }

    public /* synthetic */ void lambda$beginTransaction$0$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    public void beginTransactionNonExclusive() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda3(this));
        this.mDelegate.beginTransactionNonExclusive();
    }

    public /* synthetic */ void lambda$beginTransactionNonExclusive$1$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    public void beginTransactionWithListener(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda4(this));
        this.mDelegate.beginTransactionWithListener(sQLiteTransactionListener);
    }

    public /* synthetic */ void lambda$beginTransactionWithListener$2$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("BEGIN EXCLUSIVE TRANSACTION", Collections.emptyList());
    }

    public void beginTransactionWithListenerNonExclusive(SQLiteTransactionListener sQLiteTransactionListener) {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda5(this));
        this.mDelegate.beginTransactionWithListenerNonExclusive(sQLiteTransactionListener);
    }

    /* renamed from: lambda$beginTransactionWithListenerNonExclusive$3$QueryInterceptorDatabase */
    public /* synthetic */ void mo9914x3b234e70() {
        this.mQueryCallback.onQuery("BEGIN DEFERRED TRANSACTION", Collections.emptyList());
    }

    public void endTransaction() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda6(this));
        this.mDelegate.endTransaction();
    }

    public /* synthetic */ void lambda$endTransaction$4$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("END TRANSACTION", Collections.emptyList());
    }

    public /* synthetic */ void lambda$setTransactionSuccessful$5$QueryInterceptorDatabase() {
        this.mQueryCallback.onQuery("TRANSACTION SUCCESSFUL", Collections.emptyList());
    }

    public void setTransactionSuccessful() {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda7(this));
        this.mDelegate.setTransactionSuccessful();
    }

    public boolean inTransaction() {
        return this.mDelegate.inTransaction();
    }

    public boolean isDbLockedByCurrentThread() {
        return this.mDelegate.isDbLockedByCurrentThread();
    }

    public boolean yieldIfContendedSafely() {
        return this.mDelegate.yieldIfContendedSafely();
    }

    public boolean yieldIfContendedSafely(long j) {
        return this.mDelegate.yieldIfContendedSafely(j);
    }

    public int getVersion() {
        return this.mDelegate.getVersion();
    }

    public void setVersion(int i) {
        this.mDelegate.setVersion(i);
    }

    public long getMaximumSize() {
        return this.mDelegate.getMaximumSize();
    }

    public long setMaximumSize(long j) {
        return this.mDelegate.setMaximumSize(j);
    }

    public long getPageSize() {
        return this.mDelegate.getPageSize();
    }

    public void setPageSize(long j) {
        this.mDelegate.setPageSize(j);
    }

    public /* synthetic */ void lambda$query$6$QueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, Collections.emptyList());
    }

    public Cursor query(String str) {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda11(this, str));
        return this.mDelegate.query(str);
    }

    public Cursor query(String str, Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda2(this, str, arrayList));
        return this.mDelegate.query(str, objArr);
    }

    public /* synthetic */ void lambda$query$7$QueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery) {
        QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        supportSQLiteQuery.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda8(this, supportSQLiteQuery, queryInterceptorProgram));
        return this.mDelegate.query(supportSQLiteQuery);
    }

    public /* synthetic */ void lambda$query$8$QueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    public Cursor query(SupportSQLiteQuery supportSQLiteQuery, CancellationSignal cancellationSignal) {
        QueryInterceptorProgram queryInterceptorProgram = new QueryInterceptorProgram();
        supportSQLiteQuery.bindTo(queryInterceptorProgram);
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda9(this, supportSQLiteQuery, queryInterceptorProgram));
        return this.mDelegate.query(supportSQLiteQuery);
    }

    public /* synthetic */ void lambda$query$9$QueryInterceptorDatabase(SupportSQLiteQuery supportSQLiteQuery, QueryInterceptorProgram queryInterceptorProgram) {
        this.mQueryCallback.onQuery(supportSQLiteQuery.getSql(), queryInterceptorProgram.getBindArgs());
    }

    public long insert(String str, int i, ContentValues contentValues) throws SQLException {
        return this.mDelegate.insert(str, i, contentValues);
    }

    public int delete(String str, String str2, Object[] objArr) {
        return this.mDelegate.delete(str, str2, objArr);
    }

    public int update(String str, int i, ContentValues contentValues, String str2, Object[] objArr) {
        return this.mDelegate.update(str, i, contentValues, str2, objArr);
    }

    public void execSQL(String str) throws SQLException {
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda10(this, str));
        this.mDelegate.execSQL(str);
    }

    public /* synthetic */ void lambda$execSQL$10$QueryInterceptorDatabase(String str) {
        this.mQueryCallback.onQuery(str, new ArrayList(0));
    }

    public void execSQL(String str, Object[] objArr) throws SQLException {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(objArr));
        this.mQueryCallbackExecutor.execute(new QueryInterceptorDatabase$$ExternalSyntheticLambda1(this, str, arrayList));
        this.mDelegate.execSQL(str, arrayList.toArray());
    }

    public /* synthetic */ void lambda$execSQL$11$QueryInterceptorDatabase(String str, List list) {
        this.mQueryCallback.onQuery(str, list);
    }

    public boolean isReadOnly() {
        return this.mDelegate.isReadOnly();
    }

    public boolean isOpen() {
        return this.mDelegate.isOpen();
    }

    public boolean needUpgrade(int i) {
        return this.mDelegate.needUpgrade(i);
    }

    public String getPath() {
        return this.mDelegate.getPath();
    }

    public void setLocale(Locale locale) {
        this.mDelegate.setLocale(locale);
    }

    public void setMaxSqlCacheSize(int i) {
        this.mDelegate.setMaxSqlCacheSize(i);
    }

    public void setForeignKeyConstraintsEnabled(boolean z) {
        this.mDelegate.setForeignKeyConstraintsEnabled(z);
    }

    public boolean enableWriteAheadLogging() {
        return this.mDelegate.enableWriteAheadLogging();
    }

    public void disableWriteAheadLogging() {
        this.mDelegate.disableWriteAheadLogging();
    }

    public boolean isWriteAheadLoggingEnabled() {
        return this.mDelegate.isWriteAheadLoggingEnabled();
    }

    public List<Pair<String, String>> getAttachedDbs() {
        return this.mDelegate.getAttachedDbs();
    }

    public boolean isDatabaseIntegrityOk() {
        return this.mDelegate.isDatabaseIntegrityOk();
    }

    public void close() throws IOException {
        this.mDelegate.close();
    }
}
