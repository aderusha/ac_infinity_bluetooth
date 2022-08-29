package androidx.room;

import androidx.room.RoomDatabase;
import androidx.sqlite.p005db.SupportSQLiteOpenHelper;
import java.util.concurrent.Executor;

final class QueryInterceptorOpenHelperFactory implements SupportSQLiteOpenHelper.Factory {
    private final SupportSQLiteOpenHelper.Factory mDelegate;
    private final RoomDatabase.QueryCallback mQueryCallback;
    private final Executor mQueryCallbackExecutor;

    QueryInterceptorOpenHelperFactory(SupportSQLiteOpenHelper.Factory factory, RoomDatabase.QueryCallback queryCallback, Executor executor) {
        this.mDelegate = factory;
        this.mQueryCallback = queryCallback;
        this.mQueryCallbackExecutor = executor;
    }

    public SupportSQLiteOpenHelper create(SupportSQLiteOpenHelper.Configuration configuration) {
        return new QueryInterceptorOpenHelper(this.mDelegate.create(configuration), this.mQueryCallback, this.mQueryCallbackExecutor);
    }
}
