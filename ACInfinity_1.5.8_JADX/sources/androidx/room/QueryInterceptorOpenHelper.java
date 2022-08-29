package androidx.room;

import androidx.room.RoomDatabase;
import androidx.sqlite.p005db.SupportSQLiteDatabase;
import androidx.sqlite.p005db.SupportSQLiteOpenHelper;
import java.util.concurrent.Executor;

final class QueryInterceptorOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    private final SupportSQLiteOpenHelper mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;

    QueryInterceptorOpenHelper(SupportSQLiteOpenHelper supportSQLiteOpenHelper, RoomDatabase.QueryCallback queryCallback, Executor executor) {
        this.mDelegate = supportSQLiteOpenHelper;
        this.mQueryCallback = queryCallback;
        this.mQueryCallbackExecutor = executor;
    }

    public String getDatabaseName() {
        return this.mDelegate.getDatabaseName();
    }

    public void setWriteAheadLoggingEnabled(boolean z) {
        this.mDelegate.setWriteAheadLoggingEnabled(z);
    }

    public SupportSQLiteDatabase getWritableDatabase() {
        return new QueryInterceptorDatabase(this.mDelegate.getWritableDatabase(), this.mQueryCallback, this.mQueryCallbackExecutor);
    }

    public SupportSQLiteDatabase getReadableDatabase() {
        return new QueryInterceptorDatabase(this.mDelegate.getReadableDatabase(), this.mQueryCallback, this.mQueryCallbackExecutor);
    }

    public void close() {
        this.mDelegate.close();
    }

    public SupportSQLiteOpenHelper getDelegate() {
        return this.mDelegate;
    }
}
